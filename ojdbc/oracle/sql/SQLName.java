package oracle.sql;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
public class SQLName
  implements Serializable
{
/*  64 */   static boolean DEBUG = false;
/*  65 */   static boolean s_parseAllFormat = false;
  
  static final long serialVersionUID = 2266340348729491526L;
  
  String name;
  
  String schema;
  
  String simple;
  
  int version;
  
  boolean synonym;
  
  protected SQLName() {}
  
  public SQLName(String paramString, oracle.jdbc.OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  90 */     init(paramString, paramOracleConnection);
    
/*  92 */     this.version = 2;
/*  93 */     this.synonym = false;
  }
  
  public SQLName(String paramString1, String paramString2, oracle.jdbc.OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 107 */     this.schema = paramString1;
/* 108 */     this.simple = paramString2;
/* 109 */     this.name = (this.schema + "." + this.simple);
    
/* 111 */     this.version = 2;
/* 112 */     this.synonym = false;
  }
  
  private void init(String paramString, oracle.jdbc.OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 124 */     String[] arrayOfString1 = new String[1];
/* 125 */     String[] arrayOfString2 = new String[1];
    
/* 127 */     if (parse(paramString, arrayOfString1, arrayOfString2, true))
    {
/* 129 */       this.schema = arrayOfString1[0];
/* 130 */       this.simple = arrayOfString2[0];
    }
    else
    {
/* 134 */       this.schema = paramOracleConnection.physicalConnectionWithin().getDefaultSchemaNameForNamedTypes();
/* 135 */       this.simple = arrayOfString2[0];
    }
    
/* 138 */     this.name = (this.schema + "." + this.simple);
  }
  
  public String getName()
    throws SQLException
  {
/* 149 */     return this.name;
  }
  
  public String getSchema()
    throws SQLException
  {
/* 159 */     return this.schema;
  }
  
  public String getSimpleName()
    throws SQLException
  {
/* 169 */     return this.simple;
  }
  
  public int getVersion()
    throws SQLException
  {
/* 179 */     return this.version;
  }
  
  public static boolean parse(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws SQLException
  {
/* 202 */     return parse(paramString, paramArrayOfString1, paramArrayOfString2, s_parseAllFormat);
  }
  
  public static boolean parse(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean)
    throws SQLException
  {
/* 212 */     if (paramString == null) {
/* 213 */       return false;
    }
/* 215 */     if ((paramArrayOfString1 == null) || (paramArrayOfString1.length < 1) || (paramArrayOfString2 == null) || (paramArrayOfString2.length < 1))
    {
/* 219 */       SQLException localSQLException = DatabaseError.createSqlException(null, 68);
/* 220 */       localSQLException.fillInStackTrace();
/* 221 */       throw localSQLException;
    }
    
/* 240 */     if (!paramBoolean)
    {
/* 242 */       i = paramString.indexOf(".");
      
/* 244 */       if (i < 0)
      {
/* 246 */         paramArrayOfString2[0] = paramString;
        
/* 248 */         return false;
      }
      
/* 252 */       paramArrayOfString1[0] = paramString.substring(0, i);
/* 253 */       paramArrayOfString2[0] = paramString.substring(i + 1);
      
/* 255 */       return true;
    }
    
/* 260 */     int i = paramString.length();
/* 261 */     int j = paramString.indexOf("\"");
/* 262 */     int k = paramString.indexOf("\"", j + 1);
/* 263 */     int m = -1;
    
/* 265 */     if (j < 0)
    {
/* 267 */       m = paramString.indexOf(".");
      
/* 269 */       if (m < 0)
      {
/* 271 */         paramArrayOfString2[0] = paramString;
        
/* 273 */         return false;
      }
      
/* 277 */       paramArrayOfString1[0] = paramString.substring(0, m);
/* 278 */       paramArrayOfString2[0] = paramString.substring(m + 1);
      
/* 280 */       return true;
    }
    
/* 283 */     if (j == 0)
    {
/* 285 */       if (k == i - 1)
      {
/* 287 */         paramArrayOfString2[0] = paramString.substring(j + 1, k);
        
/* 289 */         return false;
      }
      
/* 293 */       m = paramString.indexOf(".", k);
/* 294 */       paramArrayOfString1[0] = paramString.substring(j + 1, k);
      
/* 296 */       j = paramString.indexOf("\"", m);
/* 297 */       k = paramString.indexOf("\"", j + 1);
      
/* 299 */       if (j < 0)
      {
/* 301 */         paramArrayOfString2[0] = paramString.substring(m + 1);
        
/* 303 */         return true;
      }
      
/* 309 */       paramArrayOfString2[0] = paramString.substring(j + 1, k);
      
/* 311 */       return true;
    }
    
/* 317 */     m = paramString.indexOf(".");
/* 318 */     paramArrayOfString1[0] = paramString.substring(0, m);
/* 319 */     paramArrayOfString2[0] = paramString.substring(j + 1, k);
    
/* 321 */     return true;
  }
  
  public static void setHandleDoubleQuote(boolean paramBoolean)
    throws SQLException
  {
/* 340 */     s_parseAllFormat = paramBoolean;
  }
  
  public static boolean getHandleDoubleQuote()
    throws SQLException
  {
/* 354 */     return s_parseAllFormat;
  }
  
  public boolean equals(Object paramObject)
  {
/* 361 */     if (paramObject == this) return true;
/* 362 */     if (!(paramObject instanceof SQLName)) return false;
/* 363 */     return ((SQLName)paramObject).name.equals(this.name);
  }
  
  public int hashCode()
  {
/* 369 */     return this.name == null ? -1 : this.name.hashCode();
  }
  
  public String toString()
  {
/* 376 */     return this.name;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 387 */     paramObjectOutputStream.writeUTF(this.name);
/* 388 */     paramObjectOutputStream.writeUTF(this.schema);
/* 389 */     paramObjectOutputStream.writeUTF(this.simple);
/* 390 */     paramObjectOutputStream.writeInt(this.version);
/* 391 */     paramObjectOutputStream.writeBoolean(this.synonym);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 399 */     this.name = paramObjectInputStream.readUTF();
/* 400 */     this.schema = paramObjectInputStream.readUTF();
/* 401 */     this.simple = paramObjectInputStream.readUTF();
/* 402 */     this.version = paramObjectInputStream.readInt();
/* 403 */     this.synonym = paramObjectInputStream.readBoolean();
  }
  
  protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
  {
/* 418 */     return null;
  }
  
/* 423 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/SQLName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */