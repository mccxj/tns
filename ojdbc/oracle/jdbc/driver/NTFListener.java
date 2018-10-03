package oracle.jdbc.driver;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
class NTFListener
  extends Thread
{
/*  50 */   private NTFConnection[] connections = null;
/*  51 */   private int nbOfConnections = 0;
/*  52 */   private boolean needsToBeClosed = false;
  
  NTFManager dcnManager;
  ServerSocketChannel ssChannel;
  int tcpport;
  
  NTFListener(NTFManager paramNTFManager, ServerSocketChannel paramServerSocketChannel, int paramInt)
  {
/*  60 */     this.dcnManager = paramNTFManager;
/*  61 */     this.connections = new NTFConnection[10];
/*  62 */     this.ssChannel = paramServerSocketChannel;
/*  63 */     this.tcpport = paramInt;
  }
  
  public void run()
  {
    try
    {
/*  72 */       Selector localSelector = Selector.open();
/*  73 */       this.ssChannel.register(localSelector, 16);
      
      for (;;)
      {
/*  80 */         localSelector.select();
/*  81 */         if (this.needsToBeClosed) {
          break;
        }
/*  84 */         Iterator localIterator = localSelector.selectedKeys().iterator();
/*  85 */         while (localIterator.hasNext())
        {
/*  87 */           SelectionKey localSelectionKey = (SelectionKey)localIterator.next();
          
/*  89 */           if ((localSelectionKey.readyOps() & 0x10) == 16)
          {
/*  94 */             ServerSocketChannel localServerSocketChannel = (ServerSocketChannel)localSelectionKey.channel();
            
/*  96 */             SocketChannel localSocketChannel = localServerSocketChannel.accept();
/*  97 */             NTFConnection localNTFConnection = new NTFConnection(this.dcnManager, localSocketChannel);
            
/* 100 */             if (this.connections.length == this.nbOfConnections)
            {
/* 103 */               NTFConnection[] arrayOfNTFConnection = new NTFConnection[this.connections.length * 2];
/* 104 */               System.arraycopy(this.connections, 0, arrayOfNTFConnection, 0, this.connections.length);
/* 105 */               this.connections = arrayOfNTFConnection;
            }
/* 107 */             this.connections[(this.nbOfConnections++)] = localNTFConnection;
/* 108 */             localNTFConnection.start();
/* 109 */             localIterator.remove();
          }
        }
      }
/* 113 */       localSelector.close();
/* 114 */       this.ssChannel.close();
    }
    catch (IOException localIOException) {}
  }
  
  synchronized void closeThisListener()
  {
/* 128 */     for (int i = 0; i < this.nbOfConnections; i++)
    {
/* 130 */       this.connections[i].closeThisConnection();
/* 131 */       this.connections[i].interrupt();
    }
/* 133 */     this.needsToBeClosed = true;
  }
  
/* 137 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */