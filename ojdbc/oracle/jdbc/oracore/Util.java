package oracle.jdbc.oracore;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class Util
{
  static void checkNextByte(InputStream paramInputStream, byte paramByte)
    throws SQLException
  {
    try
    {
/*  37 */       if (paramInputStream.read() != paramByte)
      {
/*  39 */         SQLException localSQLException1 = DatabaseError.createSqlException(null, 47, "parseTDS");
/*  40 */         localSQLException1.fillInStackTrace();
/*  41 */         throw localSQLException1;
      }
      
    }
    catch (IOException localIOException)
    {
/*  47 */       SQLException localSQLException2 = DatabaseError.createSqlException(null, localIOException);
/*  48 */       localSQLException2.fillInStackTrace();
/*  49 */       throw localSQLException2;
    }
  }
  
  public static int[] toJavaUnsignedBytes(byte[] paramArrayOfByte)
  {
/*  66 */     int[] arrayOfInt = new int[paramArrayOfByte.length];
    
/*  68 */     for (int i = 0; i < paramArrayOfByte.length; i++) {
/*  69 */       paramArrayOfByte[i] &= 0xFF;
    }
/*  71 */     return arrayOfInt;
  }
  
  static byte[] readBytes(InputStream paramInputStream, int paramInt)
    throws SQLException
  {
/*  78 */     byte[] arrayOfByte = new byte[paramInt];
    
    try
    {
/*  82 */       int i = paramInputStream.read(arrayOfByte);
      
/*  84 */       if (i != paramInt)
      {
/*  86 */         localObject = new byte[i];
        
/*  88 */         System.arraycopy(arrayOfByte, 0, localObject, 0, i);
        
/*  90 */         return (byte[])localObject;
      }
      
    }
    catch (IOException localIOException)
    {
/*  96 */       Object localObject = DatabaseError.createSqlException(null, localIOException);
/*  97 */       ((SQLException)localObject).fillInStackTrace();
/*  98 */       throw ((Throwable)localObject);
    }
    
/* 102 */     return arrayOfByte;
  }
  
  static void writeBytes(OutputStream paramOutputStream, byte[] paramArrayOfByte)
    throws SQLException
  {
    try
    {
/* 111 */       paramOutputStream.write(paramArrayOfByte);
    }
    catch (IOException localIOException)
    {
/* 116 */       SQLException localSQLException = DatabaseError.createSqlException(null, localIOException);
/* 117 */       localSQLException.fillInStackTrace();
/* 118 */       throw localSQLException;
    }
  }
  
  static void skipBytes(InputStream paramInputStream, int paramInt)
    throws SQLException
  {
    try
    {
/* 129 */       paramInputStream.skip(paramInt);
    }
    catch (IOException localIOException)
    {
/* 134 */       SQLException localSQLException = DatabaseError.createSqlException(null, localIOException);
/* 135 */       localSQLException.fillInStackTrace();
/* 136 */       throw localSQLException;
    }
  }
  
  static long readLong(InputStream paramInputStream)
    throws SQLException
  {
/* 145 */     byte[] arrayOfByte = new byte[4];
    
    try
    {
/* 149 */       paramInputStream.read(arrayOfByte);
      
/* 151 */       return (((arrayOfByte[0] & 0xFF) * 256 + (arrayOfByte[1] & 0xFF)) * 256 + (arrayOfByte[2] & 0xFF)) * 256 + (arrayOfByte[3] & 0xFF);
    }
    catch (IOException localIOException)
    {
/* 157 */       SQLException localSQLException = DatabaseError.createSqlException(null, localIOException);
/* 158 */       localSQLException.fillInStackTrace();
/* 159 */       throw localSQLException;
    }
  }
  
  static short readShort(InputStream paramInputStream)
    throws SQLException
  {
/* 169 */     byte[] arrayOfByte = new byte[2];
    
    try
    {
/* 173 */       paramInputStream.read(arrayOfByte);
      
/* 175 */       return (short)((arrayOfByte[0] & 0xFF) * 256 + (arrayOfByte[1] & 0xFF));
    }
    catch (IOException localIOException)
    {
/* 180 */       SQLException localSQLException = DatabaseError.createSqlException(null, localIOException);
/* 181 */       localSQLException.fillInStackTrace();
/* 182 */       throw localSQLException;
    }
  }
  
  static byte readByte(InputStream paramInputStream)
    throws SQLException
  {
    try
    {
/* 194 */       return (byte)paramInputStream.read();
    }
    catch (IOException localIOException)
    {
/* 199 */       SQLException localSQLException = DatabaseError.createSqlException(null, localIOException);
/* 200 */       localSQLException.fillInStackTrace();
/* 201 */       throw localSQLException;
    }
  }
  
  static byte fdoGetSize(byte[] paramArrayOfByte, int paramInt)
  {
/* 214 */     int i = fdoGetEntry(paramArrayOfByte, paramInt);
    
/* 217 */     return (byte)(i >> 3 & 0x1F);
  }
  
  static byte fdoGetAlign(byte[] paramArrayOfByte, int paramInt)
  {
/* 227 */     int i = fdoGetEntry(paramArrayOfByte, paramInt);
    
/* 230 */     return (byte)(i & 0x7);
  }
  
  static int ldsRound(int paramInt1, int paramInt2)
  {
/* 242 */     int i = ldsRoundTable[paramInt2];
    
/* 244 */     return (paramInt1 >> i) + 1 << i;
  }
  
  private static byte fdoGetEntry(byte[] paramArrayOfByte, int paramInt)
  {
/* 254 */     int i = getUnsignedByte(paramArrayOfByte[5]);
/* 255 */     byte b = paramArrayOfByte[(6 + i + paramInt)];
    
/* 257 */     return b;
  }
  
/* 261 */   private static int[] ldsRoundTable = { 0, 1, 0, 2, 0, 0, 0, 3, 0 };
  
  public static short getUnsignedByte(byte paramByte)
  {
/* 269 */     return (short)(paramByte & 0xFF);
  }
  
  public static byte[] serializeObject(Object paramObject)
    throws IOException
  {
/* 276 */     if (paramObject == null) {
/* 277 */       return null;
    }
/* 279 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/* 280 */     ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
    
/* 282 */     localObjectOutputStream.writeObject(paramObject);
/* 283 */     localObjectOutputStream.flush();
    
/* 285 */     return localByteArrayOutputStream.toByteArray();
  }
  
  public static Object deserializeObject(byte[] paramArrayOfByte)
    throws IOException, ClassNotFoundException
  {
/* 293 */     if (paramArrayOfByte == null) {
/* 294 */       return null;
    }
/* 296 */     ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    
/* 298 */     return new ObjectInputStream(localByteArrayInputStream).readObject();
  }
  
  public static void printByteArray(byte[] paramArrayOfByte)
  {
/* 306 */     System.out.println("DONT CALL THIS -- oracle.jdbc.oracore.Util.printByteArray");
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 320 */     return null;
  }
  
/* 347 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */