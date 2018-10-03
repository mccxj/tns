package oracle.net.ns;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.SocketException;
import java.util.Properties;
import oracle.net.ano.Ano;
import oracle.net.jdbc.nl.NLException;
import oracle.net.jdbc.nl.NVFactory;
import oracle.net.jdbc.nl.NVNavigator;
import oracle.net.jdbc.nl.NVPair;
import oracle.net.nt.ConnOption;
import oracle.net.nt.NTAdapter;
import oracle.net.resolver.AddrResolution;
public class NSProtocol
  implements Communication, SQLnetDef
{
  private static final boolean ACTIVATE_ANO = true;
  private AddrResolution addrRes;
  private SessionAtts sAtts;
  private MarkerPacket mkPkt;
  private DataPacket probePacket;
  private Packet packet;
  
  public NSProtocol()
  {
/* 150 */     this.sAtts = new SessionAtts(this, 65535, 65535);
/* 151 */     this.sAtts.connected = false;
  }
  
  public SessionAtts getSessionAttributes()
  {
/* 156 */     return this.sAtts;
  }
  
  public void connect(String paramString, Properties paramProperties)
    throws IOException, NetException
  {
/* 204 */     if (this.sAtts.connected) {
/* 205 */       throw new NetException(201);
    }
/* 207 */     if (paramString == null) {
/* 208 */       throw new NetException(208);
    }
    
/* 218 */     NVFactory localNVFactory = new NVFactory();
/* 219 */     NVNavigator localNVNavigator = new NVNavigator();
/* 220 */     NVPair localNVPair1 = null;
/* 221 */     String str1 = null;
    
/* 223 */     this.addrRes = new AddrResolution(paramString, paramProperties);
    
/* 225 */     String str2 = (String)paramProperties.get("DISABLE_OOB");
/* 226 */     int i = (str2 != null) && ("true".equals(str2)) ? 1 : 0;
    
/* 228 */     String str3 = (String)paramProperties.get("USE_ZERO_COPY_IO");
/* 229 */     boolean bool1 = true;
/* 230 */     if ((str3 != null) && ("false".equals(str3))) {
/* 231 */       bool1 = false;
    }
/* 233 */     String str4 = (String)paramProperties.get("ENABLE_JAVANET_FASTPATH");
/* 234 */     boolean bool2 = false;
/* 235 */     if ((str4 != null) && ("true".equals(str4)))
/* 236 */       bool2 = true;
/* 237 */     this.sAtts.enableJavaNetFastPath = bool2;
    
/* 239 */     this.sAtts.traceId = ((String)paramProperties.get("T4CConnection.hashCode"));
    
/* 241 */     if (this.addrRes.connection_revised)
    {
/* 243 */       paramString = this.addrRes.getTNSAddress();
/* 244 */       paramProperties = this.addrRes.getUp();
    }
    
/* 247 */     this.sAtts.profile = new ClientProfile(paramProperties);
    
/* 251 */     establishConnection(paramString);
    
/* 258 */     Object localObject1 = null;
    
    try
    {
/* 264 */       localObject1 = Class.forName("oracle.net.ano.Ano").newInstance();
/* 265 */       this.sAtts.anoEnabled = true;
    }
    catch (Exception localException1)
    {
/* 271 */       this.sAtts.anoEnabled = false;
    }
    
/* 277 */     if (localObject1 != null)
    {
/* 279 */       ((Ano)localObject1).init(this.sAtts);
/* 280 */       this.sAtts.ano = ((Ano)localObject1);
/* 281 */       this.sAtts.anoEnabled = true;
    }
    
    Object localObject3;
    
    for (;;)
    {
/* 291 */       localObject2 = null;
/* 292 */       localConnectPacket = new ConnectPacket(this.sAtts, i == 0, bool1);
/* 293 */       this.packet = new Packet(this.sAtts, this.sAtts.getSDU());
      
      try
      {
/* 297 */         localConnectPacket.send();
        
/* 300 */         this.packet.receive();
      }
      catch (InterruptedIOException localInterruptedIOException)
      {
/* 305 */         throw localInterruptedIOException;
      }
      catch (IOException localIOException)
      {
/* 311 */         this.packet.type = 4;
/* 312 */         localObject2 = localIOException;
      }
      
/* 320 */       switch (this.packet.type)
      {
      case 2: 
/* 325 */         localAcceptPacket = new AcceptPacket(this.packet);
        
/* 327 */         break;
      
      case 5: 
/* 334 */         localRedirectPacket = new RedirectPacket(this.packet);
        
/* 343 */         localObject3 = this.sAtts.cOption;
/* 344 */         this.addrRes.connection_redirected = true;
        
/* 346 */         this.sAtts.cOption.nt.disconnect();
/* 347 */         this.sAtts = establishConnection(localRedirectPacket.getData());
        
/* 354 */         this.sAtts.cOption.restoreFromOrigCoption((ConnOption)localObject3);
        
/* 356 */         break;
      
      case 4: 
/* 360 */         localRefusePacket = new RefusePacket(this.packet);
        
/* 363 */         this.sAtts.cOption.nt.disconnect();
/* 364 */         this.sAtts.cOption = null;
        
/* 367 */         establishConnection(null);
        
/* 372 */         if (this.sAtts.cOption == null)
        {
/* 376 */           if (localObject2 != null) {
/* 377 */             throw ((Throwable)localObject2);
          }
          
          try
          {
/* 382 */             localNVPair1 = localNVNavigator.findNVPairRecurse(localNVFactory.createNVPair(localRefusePacket.getData()), "ERROR");
            
/* 384 */             if (localNVPair1 != null)
            {
/* 386 */               NVPair localNVPair2 = localNVNavigator.findNVPairRecurse(localNVPair1, "CODE");
/* 387 */               if (localNVPair2 != null) {
/* 388 */                 str1 = localNVPair2.valueToString();
              }
            }
          }
          catch (NLException localNLException) {}
          
/* 399 */           throw new NetException(str1 == null ? 206 : Integer.parseInt(str1), "");
        }
        break;
      case 11: 
/* 403 */         if ((this.packet.flags & 0x8) == 8)
        {
/* 407 */           this.sAtts.renegotiateSSLSession();
        }
        break;
      case 3: case 6: case 7: case 8: case 9: case 10: default: 
/* 411 */         this.sAtts.cOption.nt.disconnect();
/* 412 */         throw new NetException(205);
      }
      
    }
    
/* 418 */     setNetStreams();
    
/* 421 */     this.sAtts.connected = true;
    
/* 426 */     this.sAtts.nt.setReadTimeoutIfRequired(paramProperties);
    
/* 431 */     Object localObject2 = (String)this.sAtts.nt.getOption(6);
    
/* 434 */     if ((localObject2 != null) && (((String)localObject2).equalsIgnoreCase("false"))) {
/* 435 */       throw new NetException(405);
    }
    
/* 439 */     if (!this.sAtts.noAnoServices)
    {
/* 442 */       if (this.sAtts.ano != null) {
/* 443 */         this.sAtts.ano.negotiation(this.addrRes.connection_redirected);
        
/* 446 */         localObject3 = (String)this.sAtts.nt.getOption(2);
        
/* 449 */         if ((localObject3 != null) && (((String)localObject3).equals("TRUE")))
        {
          try
          {
/* 454 */             Method localMethod = this.sAtts.ano.getClass().getMethod("getEncryptionAlg", (Class[])null);
            
/* 456 */             if (localMethod.invoke(this.sAtts.ano, (Object[])null) != null) {
/* 457 */               throw new NetException(406);
            }
          }
          catch (Exception localException2) {}
        }
      }
    }
    
/* 468 */     this.addrRes.connection_redirected = false;
    
/* 471 */     this.packet = null;
/* 472 */     ConnectPacket localConnectPacket = null;
/* 473 */     AcceptPacket localAcceptPacket = null;
/* 474 */     RedirectPacket localRedirectPacket = null;
/* 475 */     RefusePacket localRefusePacket = null;
  }
  
  public void disconnect()
    throws IOException, NetException
  {
/* 490 */     if (!this.sAtts.connected)
/* 491 */       throw new NetException(200);
/* 492 */     Object localObject = null;
    try
    {
/* 495 */       this.sAtts.nsOutputStream.close();
    }
    catch (IOException localIOException) {
/* 498 */       localObject = localIOException;
    }
/* 500 */     this.sAtts.connected = false;
    
/* 504 */     this.sAtts.cOption.nt.disconnect();
/* 505 */     if (localObject != null) {
/* 506 */       throw ((Throwable)localObject);
    }
  }
  
  public void sendBreak()
    throws IOException, NetException
  {
/* 525 */     if ((this.sAtts.negotiatedOptions & 0x400) == 1024)
    {
/* 528 */       this.sAtts.nt.sendUrgentByte(33);
/* 529 */       if ((this.sAtts.negotiatedOptions & 0x800) != 2048)
      {
/* 531 */         sendMarker(1);
      }
    }
    else
    {
/* 536 */       sendMarker(1);
    }
  }
  
  public void sendInterrupt()
    throws IOException, NetException
  {
/* 557 */     if ((this.sAtts.negotiatedOptions & 0x400) == 1024)
    {
/* 560 */       this.sAtts.nt.sendUrgentByte(33);
/* 561 */       if ((this.sAtts.negotiatedOptions & 0x800) != 2048)
      {
/* 563 */         sendMarker(3);
      }
    }
    else
    {
/* 568 */       sendMarker(3);
    }
  }
  
  public void sendReset()
    throws IOException, NetException
  {
/* 594 */     if (!this.sAtts.connected) {
/* 595 */       throw new NetException(200);
    }
    
/* 598 */     sendMarker(2);
    
/* 600 */     while (this.sAtts.onBreakReset)
    {
/* 604 */       Packet localPacket = ((NetInputStream)this.sAtts.getInputStream()).getCurrentPacket();
/* 605 */       localPacket.receive();
      
/* 607 */       if (localPacket.type == 12)
      {
/* 609 */         this.mkPkt = new MarkerPacket(localPacket);
/* 610 */         if (this.mkPkt.data == 2)
/* 611 */           this.sAtts.onBreakReset = false;
      }
    }
/* 614 */     this.mkPkt = null;
  }
  
  public NetInputStream getNetInputStream()
    throws NetException
  {
/* 632 */     if (!this.sAtts.connected) {
/* 633 */       throw new NetException(200);
    }
/* 635 */     return this.sAtts.nsInputStream;
  }
  
  public InputStream getInputStream() throws NetException
  {
/* 640 */     return getNetInputStream();
  }
  
  public NetOutputStream getNetOutputStream()
    throws NetException
  {
/* 657 */     if (!this.sAtts.connected) {
/* 658 */       throw new NetException(200);
    }
/* 660 */     return this.sAtts.nsOutputStream;
  }
  
  public OutputStream getOutputStream() throws NetException
  {
/* 665 */     return getNetOutputStream();
  }
  
  private SessionAtts establishConnection(String paramString)
    throws NetException, IOException
  {
/* 693 */     this.sAtts.cOption = this.addrRes.resolveAndExecute(paramString);
    
/* 699 */     if (this.sAtts.cOption == null)
/* 700 */       return null;
/* 701 */     this.sAtts.nt = this.sAtts.cOption.nt;
/* 702 */     this.sAtts.ntInputStream = this.sAtts.cOption.nt.getInputStream();
/* 703 */     this.sAtts.ntOutputStream = this.sAtts.cOption.nt.getOutputStream();
    
/* 706 */     this.sAtts.setTDU(this.sAtts.cOption.tdu);
    
/* 709 */     this.sAtts.setSDU(this.sAtts.cOption.sdu);
    
/* 714 */     if (this.sAtts.attemptingReconnect)
    {
/* 717 */       this.sAtts.nsOutputStream.reinitialize(this.sAtts);
/* 718 */       this.sAtts.nsInputStream.reinitialize(this.sAtts);
/* 719 */       if (this.mkPkt != null)
/* 720 */         this.mkPkt.reinitialize(this.sAtts);
    } else {
/* 722 */       this.sAtts.nsOutputStream = new NetOutputStream(this.sAtts, 255);
/* 723 */       this.sAtts.nsInputStream = new NetInputStream(this.sAtts);
    }
    
/* 729 */     return this.sAtts;
  }
  
  private void setNetStreams()
    throws NetException, IOException
  {
/* 746 */     this.sAtts.nsOutputStream = new NetOutputStream(this.sAtts);
    
/* 752 */     this.sAtts.nsInputStream = new NetInputStream(this.sAtts);
  }
  
  private void sendMarker(int paramInt)
    throws IOException, NetException
  {
/* 760 */     if (!this.sAtts.connected) {
/* 761 */       throw new NetException(200);
    }
/* 763 */     this.mkPkt = new MarkerPacket(this.sAtts, paramInt);
    
/* 766 */     this.mkPkt.send();
/* 767 */     this.mkPkt = null;
  }
  
  public void setO3logSessionKey(byte[] paramArrayOfByte)
    throws NetException
  {
/* 781 */     if (paramArrayOfByte != null)
    {
/* 783 */       this.sAtts.ano.setO3logSessionKey(paramArrayOfByte);
    }
  }
  
  public void setOption(int paramInt, Object paramObject)
    throws NetException, IOException
  {
/* 798 */     if ((paramInt > 0) && (paramInt < 10))
    {
/* 801 */       NTAdapter localNTAdapter = this.sAtts.getNTAdapter();
/* 802 */       localNTAdapter.setOption(paramInt, paramObject);
    }
  }
  
  public Object getOption(int paramInt)
    throws NetException, IOException
  {
/* 815 */     if ((paramInt > 0) && (paramInt < 10))
    {
/* 818 */       NTAdapter localNTAdapter = this.sAtts.getNTAdapter();
/* 819 */       return localNTAdapter.getOption(paramInt);
    }
/* 821 */     return null;
  }
  
  public void abort()
    throws NetException, IOException
  {
/* 834 */     NTAdapter localNTAdapter = this.sAtts.getNTAdapter();
/* 835 */     if (localNTAdapter != null) {
/* 836 */       localNTAdapter.abort();
    }
  }
  
  public String getEncryptionName()
  {
/* 842 */     String str = null;
/* 843 */     NTAdapter localNTAdapter = this.sAtts.getNTAdapter();
    
    try
    {
/* 847 */       str = (String)localNTAdapter.getOption(5);
    }
    catch (Exception localException) {}
    
/* 853 */     if ((str == null) && (this.sAtts.ano != null))
    {
/* 855 */       str = this.sAtts.ano.getEncryptionName();
    }
    
/* 858 */     if (str == null)
/* 859 */       str = "";
/* 860 */     return str;
  }
  
  public String getDataIntegrityName()
  {
/* 865 */     String str = "";
/* 866 */     if (this.sAtts.ano != null)
    {
/* 868 */       str = this.sAtts.ano.getDataIntegrityName();
    }
/* 870 */     return str;
  }
  
  public String getAuthenticationAdaptorName()
  {
/* 875 */     String str = "";
/* 876 */     if (this.sAtts.ano != null)
    {
/* 878 */       str = this.sAtts.ano.getAuthenticationAdaptorName();
    }
/* 880 */     return str;
  }
  
  public void reconnectIfRequired(boolean paramBoolean)
    throws IOException
  {
/* 892 */     long l1 = System.currentTimeMillis();
/* 893 */     long l2 = l1 - this.sAtts.timestampLastIO;
    
/* 895 */     if (l2 > this.sAtts.timeout) {
/* 896 */       reconnect(paramBoolean);
    }
  }
  
  public void reconnect(boolean paramBoolean)
    throws IOException
  {
    try
    {
/* 910 */       String str = new String(this.sAtts.reconnectAddress);
/* 911 */       this.sAtts.attemptingReconnect = true;
/* 912 */       ConnOption localConnOption = this.sAtts.cOption;
/* 913 */       this.addrRes.connection_redirected = true;
      
/* 915 */       this.sAtts.cOption.nt.disconnect();
      
/* 917 */       establishConnection(str);
      
/* 924 */       this.sAtts.cOption.restoreFromOrigCoption(localConnOption);
      
/* 929 */       if (paramBoolean)
      {
/* 932 */         if (this.probePacket == null) {
/* 933 */           this.probePacket = new DataPacket(this.sAtts, 26);
        } else {
/* 935 */           this.probePacket.reinitialize(this.sAtts);
        }
/* 937 */         this.probePacket.send();
      }
    } finally {
/* 940 */       this.sAtts.attemptingReconnect = false;
    }
  }
  
  public boolean isConnectionSocketKeepAlive()
    throws SocketException
  {
/* 953 */     return this.addrRes.isConnectionSocketKeepAlive();
  }
  
/* 958 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/NSProtocol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */