package oracle.jdbc.driver;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import oracle.jdbc.aq.AQNotificationEvent.AdditionalEventType;
import oracle.jdbc.aq.AQNotificationEvent.EventType;
import oracle.jdbc.dcn.DatabaseChangeEvent.AdditionalEventType;
import oracle.jdbc.dcn.DatabaseChangeEvent.EventType;
import oracle.sql.CharacterSet;
class NTFConnection
  extends Thread
{
  private static final int NS_HEADER_SIZE = 10;
  private SocketChannel channel;
/*  76 */   private ByteBuffer inBuffer = null;
/*  77 */   private ByteBuffer outBuffer = null;
  
  private int currentNSPacketLength;
  
  private int currentNSPacketType;
  
  private ByteBuffer currentNSPacketDataBuffer;
  
/*  88 */   private boolean needsToBeClosed = false;
  
  private NTFManager ntfManager;
  
/*  92 */   private Selector selector = null;
/*  93 */   private Iterator iterator = null;
/*  94 */   private SelectionKey aKey = null;
  
  int remotePort;
  
  String remoteAddress;
  
  String remoteName;
  int localPort;
  String localAddress;
  String localName;
  String connectionDescription;
/* 105 */   CharacterSet charset = null;
  
  static final int NSPTCN = 1;
  
  static final int NSPTAC = 2;
  
  static final int NSPTAK = 3;
  
  static final int NSPTRF = 4;
  static final int NSPTRD = 5;
  static final int NSPTDA = 6;
  static final int NSPTNL = 7;
  static final int NSPTAB = 9;
  static final int NSPTRS = 11;
  static final int NSPTMK = 12;
  static final int NSPTAT = 13;
  static final int NSPTCNL = 14;
  static final int NSPTHI = 19;
  static final short KPDNFY_TIMEOUT = 1;
  static final short KPDNFY_GROUPING = 2;
  
  NTFConnection(NTFManager paramNTFManager, SocketChannel paramSocketChannel)
  {
    try
    {
/* 130 */       this.ntfManager = paramNTFManager;
/* 131 */       this.channel = paramSocketChannel;
/* 132 */       this.channel.configureBlocking(false);
      
/* 148 */       this.inBuffer = ByteBuffer.allocate(4096);
/* 149 */       this.outBuffer = ByteBuffer.allocate(2048);
/* 150 */       Socket localSocket = this.channel.socket();
/* 151 */       InetAddress localInetAddress1 = localSocket.getInetAddress();
/* 152 */       InetAddress localInetAddress2 = localSocket.getLocalAddress();
/* 153 */       this.remotePort = localSocket.getPort();
/* 154 */       this.localPort = localSocket.getLocalPort();
/* 155 */       this.remoteAddress = localInetAddress1.getHostAddress();
/* 156 */       this.remoteName = localInetAddress1.getHostName();
/* 157 */       this.localAddress = localInetAddress2.getHostAddress();
/* 158 */       this.localName = localInetAddress2.getHostName();
/* 159 */       this.connectionDescription = ("local=" + this.localName + "/" + this.localAddress + ":" + this.localPort + ", remote=" + this.remoteName + "/" + this.remoteAddress + ":" + this.remotePort);
    }
    catch (IOException localIOException) {}
  }
  
  public void run()
  {
    try
    {
/* 175 */       this.selector = Selector.open();
/* 176 */       this.channel.register(this.selector, 1);
      
/* 178 */       int i = 0;
      
/* 181 */       this.inBuffer.limit(0);
      
/* 183 */       while (!this.needsToBeClosed)
      {
/* 186 */         if (!this.inBuffer.hasRemaining()) {
          do {
/* 188 */             i = readFromNetwork();
          }
/* 190 */           while (i == 0);
        }
/* 192 */         unmarshalOneNSPacket();
      }
      
      return;
    }
    catch (IOException localIOException2) {}catch (InterruptedException localInterruptedException) {}finally
    {
      try
      {
/* 202 */         this.selector.close();
/* 203 */         this.channel.close();
      }
      catch (IOException localIOException5) {}
    }
  }
  
  private int readFromNetwork()
    throws IOException, InterruptedException
  {
/* 234 */     this.inBuffer.compact();
    for (;;)
    {
/* 237 */       if ((this.iterator == null) || (!this.iterator.hasNext()))
      {
/* 244 */         this.selector.select();
        
/* 248 */         if (this.needsToBeClosed)
        {
/* 250 */           throw new InterruptedException();
        }
/* 252 */         this.iterator = this.selector.selectedKeys().iterator();
      } else {
/* 254 */         this.aKey = ((SelectionKey)this.iterator.next());
        
/* 256 */         if ((this.aKey.readyOps() & 0x1) == 1) {
          break;
        }
      }
    }
    
/* 267 */     int i = this.channel.read(this.inBuffer);
    
/* 269 */     if (i < 0)
    {
/* 271 */       throw new EOFException(); }
/* 272 */     if (i > 0)
    {
/* 275 */       this.inBuffer.flip();
    }
    
/* 279 */     this.iterator.remove();
    
/* 281 */     return i;
  }
  
  private void getNextNSPacket()
    throws IOException, InterruptedException
  {
    int i;
    
/* 298 */     while ((!this.inBuffer.hasRemaining()) || (this.inBuffer.remaining() < 10)) {
/* 299 */       i = readFromNetwork();
    }
    
/* 302 */     this.currentNSPacketLength = this.inBuffer.getShort();
    
/* 305 */     if (this.currentNSPacketLength <= 0) {
/* 306 */       throw new IOException("Invalid NS packet length.");
    }
    
/* 310 */     this.inBuffer.position(this.inBuffer.position() + 2);
    
/* 312 */     this.currentNSPacketType = this.inBuffer.get();
    
/* 315 */     validatePacketType();
    
/* 317 */     this.inBuffer.position(this.inBuffer.position() + 5);
    
/* 321 */     while (this.inBuffer.remaining() < this.currentNSPacketLength - 10)
    {
/* 323 */       i = readFromNetwork();
    }
    
/* 327 */     int j = this.inBuffer.limit();
/* 328 */     int k = this.inBuffer.position() + this.currentNSPacketLength - 10;
    
/* 332 */     this.inBuffer.limit(k);
/* 333 */     this.currentNSPacketDataBuffer = this.inBuffer.slice();
/* 334 */     this.inBuffer.limit(j);
    
/* 336 */     this.inBuffer.position(k);
  }
  
  private void unmarshalOneNSPacket()
    throws IOException, InterruptedException
  {
/* 355 */     getNextNSPacket();
    
/* 359 */     if (this.currentNSPacketDataBuffer.hasRemaining())
    {
/* 361 */       switch (this.currentNSPacketType)
      {
      case 1: 
/* 365 */         byte[] arrayOfByte1 = { 0, 24, 0, 0, 2, 0, 0, 0, 1, 52, 0, 0, 8, 0, Byte.MAX_VALUE, -1, 1, 0, 0, 0, 0, 24, 65, 1 };
        
/* 373 */         this.outBuffer.clear();
/* 374 */         this.outBuffer.put(arrayOfByte1);
/* 375 */         this.outBuffer.limit(24);
/* 376 */         this.outBuffer.rewind();
/* 377 */         this.channel.write(this.outBuffer);
/* 378 */         break;
      
      case 6: 
/* 381 */         if ((this.currentNSPacketDataBuffer.get(0) == -34) && (this.currentNSPacketDataBuffer.get(1) == -83))
        {
/* 384 */           byte[] arrayOfByte2 = { 0, Byte.MAX_VALUE, 0, 0, 6, 0, 0, 0, 0, 0, -34, -83, -66, -17, 0, 117, 10, 32, 1, 0, 0, 4, 0, 0, 4, 0, 3, 0, 0, 0, 0, 0, 4, 0, 5, 10, 32, 1, 0, 0, 2, 0, 6, 0, 31, 0, 14, 0, 1, -34, -83, -66, -17, 0, 3, 0, 0, 0, 2, 0, 4, 0, 1, 0, 1, 0, 2, 0, 0, 0, 0, 0, 4, 0, 5, 10, 32, 1, 0, 0, 2, 0, 6, -5, -1, 0, 2, 0, 2, 0, 0, 0, 0, 0, 4, 0, 5, 10, 32, 1, 0, 0, 1, 0, 2, 0, 0, 3, 0, 2, 0, 0, 0, 0, 0, 4, 0, 5, 10, 32, 1, 0, 0, 1, 0, 2, 0 };
          
/* 418 */           this.outBuffer.clear();
/* 419 */           this.outBuffer.put(arrayOfByte2);
/* 420 */           this.outBuffer.limit(arrayOfByte2.length);
/* 421 */           this.outBuffer.rewind();
/* 422 */           this.channel.write(this.outBuffer);
        }
        else
        {
/* 467 */           unmarshalNSDataPacket();
        }
        
        break;
      }
      
    }
  }
  
  private void unmarshalNSDataPacket()
    throws IOException, InterruptedException
  {
/* 547 */     int i = readShort();
    
/* 550 */     int j = readInt();
    
/* 552 */     int k = readByte();
/* 553 */     int m = readInt();
/* 554 */     int n = readShort();
/* 555 */     if ((this.charset == null) || (this.charset.getOracleId() != n)) {
/* 556 */       this.charset = CharacterSet.make(n);
    }
    
/* 560 */     int i1 = readByte();
/* 561 */     int i2 = readInt();
/* 562 */     int i3 = readShort();
    
/* 565 */     int i4 = readByte();
/* 566 */     int i5 = readInt();
/* 567 */     int i6 = readShort();
    
/* 573 */     int i7 = (j - 21) / 9;
/* 574 */     int[] arrayOfInt = new int[i7];
    
/* 576 */     for (int i8 = 0; i8 < i7; i8++) {
/* 577 */       int i9 = readByte();
/* 578 */       i10 = readInt();
/* 579 */       byte[] arrayOfByte = new byte[i10];
/* 580 */       readBuffer(arrayOfByte, 0, i10);
      
/* 585 */       for (int i11 = 0; i11 < i10; i11++) {
/* 586 */         if (i11 < 4) {
/* 587 */           arrayOfInt[i8] |= (arrayOfByte[i11] & 0xFF) << 8 * (i10 - i11 - 1);
        }
      }
    }
    
/* 593 */     NTFDCNEvent localNTFDCNEvent = null;
/* 594 */     NTFAQEvent localNTFAQEvent = null;
/* 595 */     int i10 = 0;
/* 596 */     short s = 0;
/* 597 */     NTFRegistration[] arrayOfNTFRegistration = null;
    
    int i13;
/* 600 */     if (i >= 2)
    {
/* 602 */       i12 = readShort();
/* 603 */       arrayOfNTFRegistration = new NTFRegistration[arrayOfInt.length];
/* 604 */       for (i13 = 0; i13 < arrayOfInt.length; i13++) {
/* 605 */         arrayOfNTFRegistration[i13] = this.ntfManager.getRegistration(arrayOfInt[i13]);
/* 606 */         if (arrayOfNTFRegistration[i13] != null) {
/* 607 */           i10 = arrayOfNTFRegistration[i13].getNamespace();
/* 608 */           s = arrayOfNTFRegistration[i13].getDatabaseVersion();
        }
      }
      
/* 612 */       if (i10 == 2)
      {
/* 614 */         localNTFDCNEvent = new NTFDCNEvent(this, s);
      }
/* 616 */       else if (i10 == 1)
      {
/* 618 */         localNTFAQEvent = new NTFAQEvent(this, s);
      }
/* 620 */       else if (i10 != 0) {}
    }
    
/* 631 */     int i12 = 0;
/* 632 */     if (i >= 3)
    {
/* 634 */       i13 = readShort();
/* 635 */       int i14 = readInt();
/* 636 */       int i15 = readByte();
/* 637 */       int i16 = readInt();
/* 638 */       i12 = readShort();
/* 639 */       if ((i10 == 2) && (localNTFDCNEvent != null))
      {
/* 641 */         localNTFDCNEvent.setAdditionalEventType(DatabaseChangeEvent.AdditionalEventType.getEventType(i12));
        
/* 644 */         if (i12 == 1) {
/* 645 */           localNTFDCNEvent.setEventType(DatabaseChangeEvent.EventType.DEREG);
        }
        
      }
/* 649 */       else if ((i10 == 1) && (localNTFAQEvent != null))
      {
/* 651 */         localNTFAQEvent.setAdditionalEventType(AQNotificationEvent.AdditionalEventType.getEventType(i12));
        
/* 654 */         if (i12 == 1) {
/* 655 */           localNTFAQEvent.setEventType(AQNotificationEvent.EventType.DEREG);
        }
      }
    }
    
/* 661 */     if ((i <= 3) || 
    
/* 665 */       (arrayOfNTFRegistration != null)) {
/* 666 */       if (i10 == 2) {
/* 667 */         for (i13 = 0; i13 < arrayOfNTFRegistration.length; i13++) {
/* 668 */           if ((arrayOfNTFRegistration[i13] != null) && (localNTFDCNEvent != null)) {
/* 669 */             arrayOfNTFRegistration[i13].notify(localNTFDCNEvent);
          }
          
        }
/* 673 */       } else if (i10 == 1) {
/* 674 */         for (i13 = 0; i13 < arrayOfNTFRegistration.length; i13++) {
/* 675 */           if ((arrayOfNTFRegistration[i13] != null) && (localNTFAQEvent != null)) {
/* 676 */             arrayOfNTFRegistration[i13].notify(localNTFAQEvent);
          }
        }
      }
    }
  }
  
  void closeThisConnection()
  {
/* 695 */     this.needsToBeClosed = true;
  }
  
  byte readByte()
    throws IOException, InterruptedException
  {
/* 707 */     byte b = 0;
/* 708 */     if (this.currentNSPacketDataBuffer.hasRemaining()) {
/* 709 */       b = this.currentNSPacketDataBuffer.get();
    }
    else {
/* 712 */       getNextNSPacket();
/* 713 */       b = this.currentNSPacketDataBuffer.get();
    }
/* 715 */     return b;
  }
  
  short readShort()
    throws IOException, InterruptedException
  {
/* 726 */     short s = 0;
/* 727 */     if (this.currentNSPacketDataBuffer.remaining() >= 2) {
/* 728 */       s = this.currentNSPacketDataBuffer.getShort();
    }
    else
    {
/* 734 */       int i = readByte() & 0xFF;
/* 735 */       int j = readByte() & 0xFF;
/* 736 */       s = (short)(i << 8 | j);
    }
/* 738 */     return s;
  }
  
  int readInt()
    throws IOException, InterruptedException
  {
/* 749 */     int i = 0;
/* 750 */     if (this.currentNSPacketDataBuffer.remaining() >= 4) {
/* 751 */       i = this.currentNSPacketDataBuffer.getInt();
    }
    else
    {
/* 757 */       int j = readByte() & 0xFF;
/* 758 */       int k = readByte() & 0xFF;
/* 759 */       int m = readByte() & 0xFF;
/* 760 */       int n = readByte() & 0xFF;
/* 761 */       i = j << 24 | k << 16 | m << 8 | n;
    }
/* 763 */     return i;
  }
  
  long readLong()
    throws IOException, InterruptedException
  {
/* 774 */     long l1 = 0L;
/* 775 */     if (this.currentNSPacketDataBuffer.remaining() >= 8) {
/* 776 */       l1 = this.currentNSPacketDataBuffer.getLong();
    }
    else
    {
/* 782 */       long l2 = readByte() & 0xFF;
/* 783 */       long l3 = readByte() & 0xFF;
/* 784 */       long l4 = readByte() & 0xFF;
/* 785 */       long l5 = readByte() & 0xFF;
/* 786 */       long l6 = readByte() & 0xFF;
/* 787 */       long l7 = readByte() & 0xFF;
/* 788 */       long l8 = readByte() & 0xFF;
/* 789 */       long l9 = readByte() & 0xFF;
/* 790 */       l1 = l2 << 56 | l3 << 48 | l4 << 40 | l5 << 32 | l6 << 24 | l7 << 16 | l8 << 8 | l9;
    }
    
/* 793 */     return l1;
  }
  
  void readBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, InterruptedException
  {
/* 807 */     if (this.currentNSPacketDataBuffer.remaining() >= paramInt2) {
/* 808 */       this.currentNSPacketDataBuffer.get(paramArrayOfByte, paramInt1, paramInt2);
    }
    else {
/* 811 */       int i = 0;
/* 812 */       int j = 0;
/* 813 */       int k = 0;
/* 814 */       int m = this.currentNSPacketDataBuffer.remaining();
      
/* 816 */       this.currentNSPacketDataBuffer.get(paramArrayOfByte, paramInt1, m);
      
/* 818 */       paramInt1 += m;
/* 819 */       j += m;
      
/* 821 */       while (i == 0)
      {
/* 823 */         getNextNSPacket();
/* 824 */         m = this.currentNSPacketDataBuffer.remaining();
/* 825 */         k = Math.min(m, paramInt2 - j);
        
/* 828 */         this.currentNSPacketDataBuffer.get(paramArrayOfByte, paramInt1, k);
/* 829 */         paramInt1 += k;
/* 830 */         j += k;
/* 831 */         if (j == paramInt2) {
/* 832 */           i = 1;
        }
      }
    }
  }
  
  private String packetToString(ByteBuffer paramByteBuffer)
    throws IOException
  {
/* 846 */     int i = 0;
    
/* 848 */     char[] arrayOfChar = new char[8];
/* 849 */     StringBuffer localStringBuffer = new StringBuffer();
/* 850 */     int k = paramByteBuffer.position();
    
/* 852 */     while (paramByteBuffer.hasRemaining()) {
/* 853 */       int j = paramByteBuffer.get();
/* 854 */       String str = Integer.toHexString(j & 0xFF);
/* 855 */       str = str.toUpperCase();
/* 856 */       if (str.length() == 1) {
/* 857 */         str = "0" + str;
      }
/* 859 */       localStringBuffer.append(str);
/* 860 */       localStringBuffer.append(' ');
/* 861 */       if ((j > 32) && (j < 127)) {
/* 862 */         arrayOfChar[i] = ((char)j);
      }
      else {
/* 865 */         arrayOfChar[i] = '.';
      }
/* 867 */       i++;
/* 868 */       if (i == 8) {
/* 869 */         localStringBuffer.append('|');
/* 870 */         localStringBuffer.append(arrayOfChar);
/* 871 */         localStringBuffer.append('|');
/* 872 */         localStringBuffer.append('\n');
/* 873 */         i = 0;
      }
    }
/* 876 */     if (i != 0) {
/* 877 */       int m = 8 - i;
/* 878 */       for (int n = 0; n < m * 3; n++) {
/* 879 */         localStringBuffer.append(' ');
      }
/* 881 */       localStringBuffer.append('|');
/* 882 */       localStringBuffer.append(arrayOfChar, 0, i);
/* 883 */       for (n = 0; n < m; n++) {
/* 884 */         localStringBuffer.append(' ');
      }
/* 886 */       localStringBuffer.append('|');
/* 887 */       localStringBuffer.append('\n');
    }
/* 889 */     localStringBuffer.append("\nEnd of Packet\n\n");
/* 890 */     paramByteBuffer.position(k);
/* 891 */     return localStringBuffer.toString();
  }
  
  private void validatePacketType()
    throws IOException
  {
/* 899 */     if ((this.currentNSPacketType < 1) || (this.currentNSPacketType > 19))
    {
/* 901 */       throw new IOException("Invalid NS packet type.");
    }
  }
  
/* 907 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */