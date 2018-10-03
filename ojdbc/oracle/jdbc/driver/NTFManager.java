package oracle.jdbc.driver;
import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import oracle.jdbc.internal.OracleConnection;
class NTFManager
{
/*  54 */   private Hashtable<Integer, NTFListener> nsListeners = new Hashtable();
  
/*  59 */   private Hashtable<Integer, NTFRegistration> ntfRegistrations = new Hashtable();
  
/*  69 */   private byte[] listOfJdbcRegId = new byte[20];
  
  synchronized boolean listenOnPortT4C(int[] paramArrayOfInt, boolean paramBoolean)
    throws SQLException
  {
/* 104 */     int i = paramArrayOfInt[0];
/* 105 */     boolean bool = false;
    
/* 109 */     while (this.nsListeners.get(Integer.valueOf(i)) == null)
    {
      try
      {
/* 117 */         ServerSocketChannel localServerSocketChannel = ServerSocketChannel.open();
/* 118 */         localServerSocketChannel.configureBlocking(false);
        
/* 120 */         ServerSocket localServerSocket = localServerSocketChannel.socket();
        
/* 122 */         localObject = new InetSocketAddress(i);
        
        try
        {
/* 126 */           localServerSocket.bind((SocketAddress)localObject);
          
/* 128 */           bool = true;
/* 129 */           NTFListener localNTFListener = new NTFListener(this, localServerSocketChannel, i);
/* 130 */           this.nsListeners.put(Integer.valueOf(i), localNTFListener);
/* 131 */           localNTFListener.start();
        }
        catch (BindException localBindException)
        {
/* 136 */           if (!paramBoolean)
          {
/* 138 */             localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 250);
/* 139 */             localSQLException.fillInStackTrace();
/* 140 */             throw localSQLException;
          }
          
/* 143 */           i++;
        }
        catch (IOException localIOException2) {
          SQLException localSQLException;
/* 147 */           if (!paramBoolean)
          {
/* 149 */             localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 250);
/* 150 */             localSQLException.fillInStackTrace();
/* 151 */             throw localSQLException;
          }
          
/* 154 */           i++;
        }
        
      }
      catch (IOException localIOException1)
      {
/* 160 */         Object localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/* 161 */         ((SQLException)localObject).fillInStackTrace();
/* 162 */         throw ((Throwable)localObject);
      }
    }
    
/* 166 */     paramArrayOfInt[0] = i;
/* 167 */     return bool;
  }
  
  synchronized int getNextJdbcRegId()
  {
/* 178 */     for (int i = 1; 
/* 179 */         i < this.listOfJdbcRegId.length; i++)
    {
/* 181 */       if (this.listOfJdbcRegId[i] == 0)
        break;
    }
/* 184 */     if (i == this.listOfJdbcRegId.length - 1)
    {
/* 186 */       byte[] arrayOfByte = new byte[this.listOfJdbcRegId.length * 2];
/* 187 */       System.arraycopy(this.listOfJdbcRegId, 0, arrayOfByte, 0, this.listOfJdbcRegId.length);
/* 188 */       this.listOfJdbcRegId = arrayOfByte;
    }
/* 190 */     this.listOfJdbcRegId[i] = 2;
/* 191 */     return i;
  }
  
  synchronized void addRegistration(NTFRegistration paramNTFRegistration)
  {
/* 201 */     Integer localInteger = Integer.valueOf(paramNTFRegistration.getJdbcRegId());
/* 202 */     Hashtable localHashtable = (Hashtable)this.ntfRegistrations.clone();
/* 203 */     localHashtable.put(localInteger, paramNTFRegistration);
    
/* 210 */     this.ntfRegistrations = localHashtable;
  }
  
  synchronized boolean removeRegistration(NTFRegistration paramNTFRegistration)
  {
/* 222 */     Integer localInteger = Integer.valueOf(paramNTFRegistration.getJdbcRegId());
/* 223 */     Hashtable localHashtable = (Hashtable)this.ntfRegistrations.clone();
/* 224 */     Object localObject = localHashtable.remove(localInteger);
    
/* 231 */     this.ntfRegistrations = localHashtable;
    
/* 236 */     boolean bool = false;
    
/* 238 */     if (localObject != null)
/* 239 */       bool = true;
/* 240 */     return bool;
  }
  
  synchronized void freeJdbcRegId(int paramInt)
  {
/* 248 */     if ((this.listOfJdbcRegId != null) && (this.listOfJdbcRegId.length > paramInt)) {
/* 249 */       this.listOfJdbcRegId[paramInt] = 0;
    }
  }
  
  synchronized void cleanListenersT4C(int paramInt)
  {
/* 268 */     Enumeration localEnumeration = this.ntfRegistrations.keys();
/* 269 */     int i = 0;
/* 270 */     Object localObject; while ((i == 0) && (localEnumeration.hasMoreElements()))
    {
/* 272 */       localObject = localEnumeration.nextElement();
/* 273 */       NTFRegistration localNTFRegistration = (NTFRegistration)this.ntfRegistrations.get(localObject);
/* 274 */       if (localNTFRegistration.getClientTCPPort() == paramInt)
/* 275 */         i = 1;
    }
/* 277 */     if (i == 0)
    {
/* 279 */       localObject = (NTFListener)this.nsListeners.get(Integer.valueOf(paramInt));
/* 280 */       if (localObject != null)
      {
/* 282 */         ((NTFListener)localObject).closeThisListener();
/* 283 */         ((NTFListener)localObject).interrupt();
/* 284 */         this.nsListeners.remove(Integer.valueOf(paramInt));
      }
    }
  }
  
  NTFRegistration getRegistration(int paramInt)
  {
/* 300 */     Integer localInteger = Integer.valueOf(paramInt);
    
/* 302 */     Hashtable localHashtable = this.ntfRegistrations;
/* 303 */     NTFRegistration localNTFRegistration = (NTFRegistration)localHashtable.get(localInteger);
/* 304 */     return localNTFRegistration;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 319 */     return null;
  }
  
/* 324 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */