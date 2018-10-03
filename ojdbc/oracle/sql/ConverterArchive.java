package oracle.sql;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
public class ConverterArchive
{
  private String m_izipName;
/*  91 */   private FileOutputStream m_ifStream = null;
/*  92 */   private ZipOutputStream m_izStream = null;
/*  93 */   private InputStream m_riStream = null;
/*  94 */   private ZipFile m_rzipFile = null;
  
  private static final String TEMPFILE = "gsstemp.zip";
  
  public void openArchiveforInsert(String paramString)
  {
/* 110 */     this.m_izipName = paramString;
    
    try
    {
/* 114 */       this.m_ifStream = new FileOutputStream(this.m_izipName);
/* 115 */       this.m_izStream = new ZipOutputStream(this.m_ifStream);
    }
    catch (FileNotFoundException localFileNotFoundException) {}catch (IOException localIOException) {}
  }
  
  public void closeArchiveforInsert()
  {
    try
    {
/* 128 */       this.m_izStream.close();
/* 129 */       this.m_ifStream.close();
    }
    catch (IOException localIOException) {}
  }
  
  public void insertObj(Object paramObject, String paramString)
  {
/* 141 */     ZipEntry localZipEntry = null;
/* 142 */     ObjectOutputStream localObjectOutputStream = null;
    
/* 144 */     localZipEntry = new ZipEntry(paramString);
    
    try
    {
/* 148 */       this.m_izStream.putNextEntry(localZipEntry);
      
/* 150 */       localObjectOutputStream = new ObjectOutputStream(this.m_izStream);
      
/* 152 */       localObjectOutputStream.writeObject(paramObject);
/* 153 */       localObjectOutputStream.close();
/* 154 */       this.m_izStream.closeEntry();
    }
    catch (IOException localIOException) {}
  }
  
  public void insertSingleObj(String paramString1, Object paramObject, String paramString2)
    throws IOException
  {
/* 168 */     FileInputStream localFileInputStream = null;
/* 169 */     ZipInputStream localZipInputStream = null;
/* 170 */     FileOutputStream localFileOutputStream = null;
/* 171 */     ZipOutputStream localZipOutputStream = null;
/* 172 */     ZipEntry localZipEntry = null;
    
/* 174 */     ObjectInputStream localObjectInputStream = null;
/* 175 */     ObjectOutputStream localObjectOutputStream = null;
    
/* 178 */     File localFile1 = new File(paramString1);
    
/* 181 */     if (localFile1.isFile())
    {
      try
      {
/* 189 */         localFileInputStream = new FileInputStream(paramString1);
/* 190 */         localZipInputStream = new ZipInputStream(localFileInputStream);
        
/* 193 */         localFileOutputStream = new FileOutputStream("gsstemp.zip");
/* 194 */         localZipOutputStream = new ZipOutputStream(localFileOutputStream);
        
/* 201 */         while ((localZipEntry = localZipInputStream.getNextEntry()) != null)
        {
/* 203 */           if (!localZipEntry.getName().equals(paramString2))
          {
/* 206 */             localZipOutputStream.putNextEntry(localZipEntry);
            
/* 208 */             localObjectInputStream = new ObjectInputStream(localZipInputStream);
/* 209 */             localObjectOutputStream = new ObjectOutputStream(localZipOutputStream);
/* 210 */             Object localObject = localObjectInputStream.readObject();
            
/* 212 */             localObjectOutputStream.writeObject(localObject);
          }
        }
        
/* 217 */         localZipEntry = new ZipEntry(paramString2);
        
/* 219 */         localZipOutputStream.putNextEntry(localZipEntry);
        
/* 221 */         localObjectOutputStream = new ObjectOutputStream(localZipOutputStream);
        
/* 223 */         localObjectOutputStream.writeObject(paramObject);
/* 224 */         localObjectOutputStream.close();
/* 225 */         localZipInputStream.close();
      }
      catch (FileNotFoundException localFileNotFoundException1)
      {
/* 231 */         throw new IOException(localFileNotFoundException1.getMessage());
      }
      catch (StreamCorruptedException localStreamCorruptedException1)
      {
/* 235 */         throw new IOException(localStreamCorruptedException1.getMessage());
      }
      catch (IOException localIOException1)
      {
/* 239 */         throw localIOException1;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
/* 243 */         throw new IOException(localClassNotFoundException.getMessage());
      }
      
/* 246 */       File localFile2 = new File("gsstemp.zip");
      
/* 248 */       localFile1.delete();
      
      try
      {
/* 252 */         if (!localFile2.renameTo(localFile1)) {
/* 253 */           throw new IOException("can't write to target file " + paramString1);
        }
      }
      catch (SecurityException localSecurityException) {
/* 257 */         throw new IOException(localSecurityException.getMessage());
      }
      catch (NullPointerException localNullPointerException)
      {
/* 263 */         throw new IOException(localNullPointerException.getMessage());
      }
      
    }
    else
    {
      try
      {
/* 276 */         localFileOutputStream = new FileOutputStream(paramString1);
/* 277 */         localZipOutputStream = new ZipOutputStream(localFileOutputStream);
/* 278 */         localZipEntry = new ZipEntry(paramString2);
        
/* 280 */         localZipOutputStream.putNextEntry(localZipEntry);
        
/* 282 */         localObjectOutputStream = new ObjectOutputStream(localZipOutputStream);
        
/* 284 */         localObjectOutputStream.writeObject(paramObject);
/* 285 */         localObjectOutputStream.close();
      }
      catch (FileNotFoundException localFileNotFoundException2)
      {
/* 289 */         throw new IOException(localFileNotFoundException2.getMessage());
      }
      catch (StreamCorruptedException localStreamCorruptedException2)
      {
/* 293 */         throw new IOException(localStreamCorruptedException2.getMessage());
      }
      catch (IOException localIOException2)
      {
/* 297 */         throw localIOException2;
      }
    }
    
/* 301 */     System.out.print(paramString2 + " has been successfully stored in ");
/* 302 */     System.out.println(paramString1);
  }
  
  public void insertObjtoFile(String paramString1, String paramString2, Object paramObject)
    throws IOException
  {
/* 314 */     File localFile1 = new File(paramString1);
/* 315 */     File localFile2 = new File(paramString1 + paramString2);
    
/* 320 */     if (!localFile1.isDirectory())
    {
/* 324 */       throw new IOException("directory " + paramString1 + " doesn't exist");
    }
    
/* 327 */     if (localFile2.exists())
    {
      try
      {
/* 333 */         localFile2.delete();
      }
      catch (SecurityException localSecurityException)
      {
/* 337 */         throw new IOException("file exist,can't overwrite file.");
      }
    }
    
    try
    {
/* 343 */       FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
/* 344 */       ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
      
/* 346 */       localObjectOutputStream.writeObject(paramObject);
/* 347 */       localObjectOutputStream.close();
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
/* 351 */       throw new IOException("file can't be created.");
    }
    
/* 354 */     System.out.print(paramString2 + " has been successfully stored in ");
/* 355 */     System.out.println(paramString1);
  }
  
  public void openArchiveforRead()
  {
    try
    {
/* 367 */       this.m_rzipFile = new ZipFile(this.m_izipName);
    }
    catch (IOException localIOException)
    {
/* 371 */       localIOException.printStackTrace();
/* 372 */       System.exit(0);
    }
  }
  
  public void closeArchiveforRead()
  {
    try
    {
/* 382 */       this.m_rzipFile.close();
    }
    catch (IOException localIOException)
    {
/* 386 */       localIOException.printStackTrace();
/* 387 */       System.exit(0);
    }
  }
  
  public Object readObj(String paramString)
  {
/* 395 */     URL localURL = getClass().getResource(paramString);
/* 396 */     Object localObject1 = null;
/* 397 */     ObjectInputStream localObjectInputStream = null;
/* 398 */     InputStream localInputStream = null;
    
/* 400 */     if (localURL == null) {
/* 401 */       return null;
    }
    try
    {
/* 405 */       localInputStream = localURL.openStream();
      
/* 407 */       localObjectInputStream = new ObjectInputStream(localInputStream);
/* 408 */       localObject1 = localObjectInputStream.readObject();
      
/* 410 */       return localObject1;
    }
    catch (IOException localIOException1) {}catch (ClassNotFoundException localClassNotFoundException) {}finally
    {
      try
      {
/* 425 */         if (localObjectInputStream != null)
/* 426 */           localObjectInputStream.close();
/* 427 */         if (localInputStream != null) {
/* 428 */           localInputStream.close();
        }
      }
      catch (IOException localIOException5) {}
    }
    
/* 435 */     return null;
  }
  
  public Object readObj(String paramString1, String paramString2)
  {
    try
    {
/* 450 */       FileInputStream localFileInputStream = new FileInputStream(paramString1);
/* 451 */       ZipInputStream localZipInputStream = new ZipInputStream(localFileInputStream);
/* 452 */       ZipEntry localZipEntry = null;
/* 453 */       ObjectInputStream localObjectInputStream = null;
/* 454 */       Object localObject = null;
      
/* 456 */       while (localZipInputStream.available() != 0)
      {
/* 458 */         localZipEntry = localZipInputStream.getNextEntry();
        
/* 460 */         if ((localZipEntry != null) && (localZipEntry.getName().equals(paramString2)))
        {
/* 464 */           localObjectInputStream = new ObjectInputStream(localZipInputStream);
/* 465 */           localObject = localObjectInputStream.readObject();
        }
      }
      
/* 471 */       localZipInputStream.close();
      
/* 473 */       return localObject;
    }
    catch (IOException localIOException) {}catch (ClassNotFoundException localClassNotFoundException) {}
    
/* 478 */     return null;
  }
  
/* 498 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/ConverterArchive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */