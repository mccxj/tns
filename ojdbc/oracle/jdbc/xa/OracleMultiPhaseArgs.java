package oracle.jdbc.xa;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
public class OracleMultiPhaseArgs
{
/*  28 */   int action = 0;
/*  29 */   int nsites = 0;
/*  30 */   Vector dbLinks = null;
/*  31 */   Vector xids = null;
  
  public OracleMultiPhaseArgs() {}
  
  public OracleMultiPhaseArgs(int paramInt1, int paramInt2, Vector paramVector1, Vector paramVector2)
  {
/*  45 */     if (paramInt2 <= 1)
    {
/*  47 */       this.action = 0;
/*  48 */       this.nsites = 0;
/*  49 */       this.dbLinks = null;
/*  50 */       this.xids = null;
    }
/*  56 */     else if ((!paramVector1.isEmpty()) && (!paramVector2.isEmpty()) && (paramVector2.size() == paramInt2) && (paramVector1.size() == 3 * paramInt2))
    {
/*  63 */       this.action = paramInt1;
/*  64 */       this.nsites = paramInt2;
/*  65 */       this.xids = paramVector1;
/*  66 */       this.dbLinks = paramVector2;
    }
  }
  
  public OracleMultiPhaseArgs(byte[] paramArrayOfByte)
  {
/*  79 */     ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
/*  80 */     DataInputStream localDataInputStream = new DataInputStream(localByteArrayInputStream);
    
/*  82 */     this.xids = new Vector();
/*  83 */     this.dbLinks = new Vector();
    
    try
    {
/*  87 */       this.action = localDataInputStream.readInt();
/*  88 */       this.nsites = localDataInputStream.readInt();
      
/*  90 */       int i = localDataInputStream.readInt();
/*  91 */       int j = localDataInputStream.readInt();
/*  92 */       byte[] arrayOfByte1 = new byte[j];
/*  93 */       int k = localDataInputStream.read(arrayOfByte1, 0, j);
      
/*  95 */       for (int m = 0; m < this.nsites; m++)
      {
/*  97 */         int n = localDataInputStream.readInt();
/*  98 */         byte[] arrayOfByte2 = new byte[n];
/*  99 */         int i1 = localDataInputStream.read(arrayOfByte2, 0, n);
        
/* 102 */         this.xids.addElement(Integer.valueOf(i));
/* 103 */         this.xids.addElement(arrayOfByte1);
/* 104 */         this.xids.addElement(arrayOfByte2);
        
/* 106 */         String str = localDataInputStream.readUTF();
        
/* 109 */         this.dbLinks.addElement(str);
      }
    }
    catch (IOException localIOException)
    {
/* 114 */       localIOException.printStackTrace();
    }
  }
  
  public byte[] toByteArray()
  {
/* 126 */     return toByteArrayOS().toByteArray();
  }
  
  public ByteArrayOutputStream toByteArrayOS()
  {
/* 132 */     Object localObject = null;
/* 133 */     int i = 0;
    
/* 137 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/* 138 */     DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
    
    try
    {
/* 142 */       localDataOutputStream.writeInt(this.action);
/* 143 */       localDataOutputStream.writeInt(this.nsites);
      
/* 145 */       for (int j = 0; j < this.nsites; j++)
      {
/* 147 */         String str = (String)this.dbLinks.elementAt(j);
/* 148 */         int k = ((Integer)this.xids.elementAt(j * 3)).intValue();
/* 149 */         byte[] arrayOfByte1 = (byte[])this.xids.elementAt(j * 3 + 1);
/* 150 */         byte[] arrayOfByte2 = (byte[])this.xids.elementAt(j * 3 + 2);
        
/* 152 */         if (j == 0)
        {
/* 154 */           i = k;
/* 155 */           localObject = arrayOfByte1;
          
/* 157 */           localDataOutputStream.writeInt(k);
/* 158 */           localDataOutputStream.writeInt(arrayOfByte1.length);
/* 159 */           localDataOutputStream.write(arrayOfByte1, 0, arrayOfByte1.length);
        }
        
/* 170 */         localDataOutputStream.writeInt(arrayOfByte2.length);
/* 171 */         localDataOutputStream.write(arrayOfByte2, 0, arrayOfByte2.length);
/* 172 */         localDataOutputStream.writeUTF(str);
      }
    }
    catch (IOException localIOException)
    {
/* 177 */       localIOException.printStackTrace();
    }
    
/* 182 */     return localByteArrayOutputStream;
  }
  
  public int getAction()
  {
/* 190 */     return this.action;
  }
  
  public int getnsite()
  {
/* 198 */     return this.nsites;
  }
  
  public Vector getdbLinks()
  {
/* 206 */     return this.dbLinks;
  }
  
  public Vector getXids()
  {
/* 214 */     return this.xids;
  }
  
  public void printMPArgs()
  {
/* 227 */     for (int i = 0; i < this.nsites; i++)
    {
/* 229 */       String str = (String)this.dbLinks.elementAt(i);
/* 230 */       int j = ((Integer)this.xids.elementAt(i * 3)).intValue();
/* 231 */       byte[] arrayOfByte1 = (byte[])this.xids.elementAt(i * 3 + 1);
/* 232 */       byte[] arrayOfByte2 = (byte[])this.xids.elementAt(i * 3 + 2);
      
/* 236 */       printByteArray(arrayOfByte1);
      
/* 239 */       printByteArray(arrayOfByte2);
    }
  }
  
  private void printByteArray(byte[] paramArrayOfByte)
  {
/* 251 */     StringBuffer localStringBuffer = new StringBuffer();
    
/* 253 */     for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 254 */       localStringBuffer.append(paramArrayOfByte[i] + " ");
    }
  }
  
/* 262 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/OracleMultiPhaseArgs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */