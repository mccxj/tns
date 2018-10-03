package oracle.sql;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.internal.OracleConnection;
public class LobPlsqlUtil
{
/*  70 */   static boolean PLSQL_DEBUG = false;
  
  static final int MAX_PLSQL_SIZE = 32512;
  
  static final int MAX_PLSQL_INSTR_SIZE = 32512;
  
  static final int MAX_CHUNK_SIZE = 32512;
  
  public static long hasPattern(BLOB paramBLOB, byte[] paramArrayOfByte, long paramLong)
    throws SQLException
  {
/*  87 */     return hasPattern(paramBLOB.getInternalConnection(), paramBLOB, 2004, paramArrayOfByte, paramLong);
  }
  
  public static long isSubLob(BLOB paramBLOB1, BLOB paramBLOB2, long paramLong)
    throws SQLException
  {
/*  96 */     return isSubLob(paramBLOB1.getInternalConnection(), paramBLOB1, 2004, paramBLOB2, paramLong);
  }
  
  public static long hasPattern(CLOB paramCLOB, char[] paramArrayOfChar, long paramLong)
    throws SQLException
  {
/* 110 */     if ((paramArrayOfChar == null) || (paramLong <= 0L)) {
/* 111 */       return 0L;
    }
/* 113 */     OracleConnection localOracleConnection = paramCLOB.getInternalConnection();
/* 114 */     long l1 = paramArrayOfChar.length;
/* 115 */     long l2 = length(localOracleConnection, paramCLOB, 2005);
    
/* 118 */     if ((l1 == 0L) || (l1 > l2 - paramLong + 1L) || (paramLong > l2))
    {
/* 120 */       return 0L;
    }
    
/* 124 */     if (l1 <= getPlsqlMaxInstrSize(localOracleConnection))
    {
/* 128 */       OracleCallableStatement localOracleCallableStatement = null;
      
      try
      {
/* 132 */         localOracleCallableStatement = (OracleCallableStatement)localOracleConnection.prepareCall("begin :1 := dbms_lob.instr(:2, :3, :4); end;");
        
/* 135 */         localOracleCallableStatement.registerOutParameter(1, 2);
        
/* 137 */         if (paramCLOB.isNCLOB())
        {
/* 139 */           localOracleCallableStatement.setFormOfUse(2, (short)2);
/* 140 */           localOracleCallableStatement.setFormOfUse(3, (short)2);
        }
        
/* 143 */         localOracleCallableStatement.setCLOB(2, paramCLOB);
/* 144 */         localOracleCallableStatement.setString(3, new String(paramArrayOfChar));
/* 145 */         localOracleCallableStatement.setLong(4, paramLong);
/* 146 */         localOracleCallableStatement.execute();
        
/* 148 */         return localOracleCallableStatement.getLong(1);
      }
      finally
      {
/* 152 */         localOracleCallableStatement.close();
        
/* 154 */         localOracleCallableStatement = null;
      }
    }
    
/* 163 */     int i = 0;
/* 164 */     long l3 = paramLong;
/* 165 */     int j = 0;
    
/* 168 */     long l5 = 0L;
    
/* 171 */     while (j == 0)
    {
/* 173 */       if (l1 > l2 - l3 + 1L) {
/* 174 */         return 0L;
      }
/* 176 */       i = 0;
      
/* 179 */       int k = (int)Math.min(getPlsqlMaxInstrSize(localOracleConnection), l1 - i);
      
/* 183 */       char[] arrayOfChar = new char[k];
      
/* 185 */       System.arraycopy(paramArrayOfChar, i, arrayOfChar, 0, k);
      
/* 188 */       long l4 = hasPattern(paramCLOB, arrayOfChar, l3);
      
/* 190 */       if (l4 == 0L)
      {
/* 192 */         return 0L;
      }
      
/* 196 */       l5 = l4;
      
/* 198 */       i += k;
/* 199 */       l3 = l4 + k;
      
/* 202 */       int m = 1;
      
/* 204 */       while (m != 0)
      {
/* 206 */         k = (int)Math.min(getPlsqlMaxInstrSize(localOracleConnection), l1 - i);
        
/* 210 */         arrayOfChar = new char[k];
        
/* 212 */         System.arraycopy(paramArrayOfChar, i, arrayOfChar, 0, k);
        
/* 216 */         l4 = hasPattern(paramCLOB, arrayOfChar, l3);
        
/* 218 */         if (l4 == l3)
        {
/* 222 */           i += k;
/* 223 */           l3 += k;
          
/* 225 */           if (i == l1)
          {
/* 227 */             m = 0;
/* 228 */             j = 1;
          }
        } else {
/* 231 */           if (l4 == 0L)
          {
/* 236 */             return 0L;
          }
          
/* 243 */           l3 = l4 - i;
          
/* 245 */           m = 0;
        }
      }
    }
    
/* 251 */     return l5;
  }
  
  public static long isSubLob(CLOB paramCLOB1, CLOB paramCLOB2, long paramLong)
    throws SQLException
  {
/* 261 */     if ((paramCLOB2 == null) || (paramLong <= 0L)) {
/* 262 */       return 0L;
    }
/* 264 */     OracleConnection localOracleConnection = paramCLOB1.getInternalConnection();
/* 265 */     long l1 = length(localOracleConnection, paramCLOB2, 2005);
/* 266 */     long l2 = length(localOracleConnection, paramCLOB1, 2005);
    
/* 269 */     if ((l1 == 0L) || (l1 > l2 - paramLong + 1L) || (paramLong > l2))
    {
/* 271 */       return 0L;
    }
    
/* 275 */     if (l1 <= getPlsqlMaxInstrSize(localOracleConnection))
    {
/* 279 */       char[] arrayOfChar1 = new char[(int)l1];
      
/* 281 */       paramCLOB2.getChars(1L, (int)l1, arrayOfChar1);
      
/* 283 */       return hasPattern(paramCLOB1, arrayOfChar1, paramLong);
    }
    
/* 291 */     int i = 0;
/* 292 */     long l3 = paramLong;
/* 293 */     int j = 0;
    
/* 296 */     long l5 = 0L;
    
/* 299 */     while (j == 0)
    {
/* 301 */       if (l1 > l2 - l3 + 1L) {
/* 302 */         return 0L;
      }
/* 304 */       i = 0;
      
/* 307 */       int k = (int)Math.min(getPlsqlMaxInstrSize(localOracleConnection), l1 - i);
      
/* 311 */       char[] arrayOfChar2 = new char[k];
      
/* 313 */       paramCLOB2.getChars(i + 1, k, arrayOfChar2);
      
/* 316 */       long l4 = hasPattern(paramCLOB1, arrayOfChar2, l3);
      
/* 318 */       if (l4 == 0L)
      {
/* 320 */         return 0L;
      }
      
/* 324 */       l5 = l4;
      
/* 326 */       i += k;
/* 327 */       l3 = l4 + k;
      
/* 330 */       int m = 1;
      
/* 332 */       while (m != 0)
      {
/* 334 */         k = (int)Math.min(getPlsqlMaxInstrSize(localOracleConnection), l1 - i);
        
/* 338 */         arrayOfChar2 = new char[k];
        
/* 340 */         paramCLOB2.getChars(i + 1, k, arrayOfChar2);
        
/* 343 */         l4 = hasPattern(paramCLOB1, arrayOfChar2, l3);
        
/* 345 */         if (l4 == l3)
        {
/* 349 */           i += k;
/* 350 */           l3 += k;
          
/* 352 */           if (i == l1)
          {
/* 354 */             m = 0;
/* 355 */             j = 1;
          }
        } else {
/* 358 */           if (l4 == 0L)
          {
/* 363 */             return 0L;
          }
          
/* 370 */           l3 = l4 - i;
          
/* 372 */           m = 0;
        }
      }
    }
    
/* 378 */     return l5;
  }
  
  public static long hasPattern(BFILE paramBFILE, byte[] paramArrayOfByte, long paramLong)
    throws SQLException
  {
/* 395 */     return hasPattern(paramBFILE.getInternalConnection(), paramBFILE, -13, paramArrayOfByte, paramLong);
  }
  
  public static long isSubLob(BFILE paramBFILE1, BFILE paramBFILE2, long paramLong)
    throws SQLException
  {
/* 404 */     return isSubLob(paramBFILE1.getInternalConnection(), paramBFILE1, -13, paramBFILE2, paramLong);
  }
  
  public static String fileGetName(BFILE paramBFILE)
    throws SQLException
  {
/* 412 */     OracleCallableStatement localOracleCallableStatement = null;
/* 413 */     String str = null;
    
    try
    {
/* 417 */       localOracleCallableStatement = (OracleCallableStatement)paramBFILE.getInternalConnection().prepareCall("begin dbms_lob.fileGetName(:1, :2, :3); end; ");
      
/* 420 */       localOracleCallableStatement.setBFILE(1, paramBFILE);
/* 421 */       localOracleCallableStatement.registerOutParameter(2, 12);
/* 422 */       localOracleCallableStatement.registerOutParameter(3, 12);
/* 423 */       localOracleCallableStatement.execute();
      
/* 425 */       str = localOracleCallableStatement.getString(3);
    }
    finally
    {
/* 429 */       if (localOracleCallableStatement != null)
      {
/* 431 */         localOracleCallableStatement.close();
        
/* 433 */         localOracleCallableStatement = null;
      }
    }
/* 436 */     return str;
  }
  
  public static String fileGetDirAlias(BFILE paramBFILE)
    throws SQLException
  {
/* 443 */     OracleCallableStatement localOracleCallableStatement = null;
/* 444 */     String str = null;
    
    try
    {
/* 448 */       localOracleCallableStatement = (OracleCallableStatement)paramBFILE.getInternalConnection().prepareCall("begin dbms_lob.fileGetName(:1, :2, :3); end; ");
      
/* 451 */       localOracleCallableStatement.setBFILE(1, paramBFILE);
/* 452 */       localOracleCallableStatement.registerOutParameter(2, 12);
/* 453 */       localOracleCallableStatement.registerOutParameter(3, 12);
/* 454 */       localOracleCallableStatement.execute();
      
/* 456 */       str = localOracleCallableStatement.getString(2);
    }
    finally
    {
/* 461 */       if (localOracleCallableStatement != null)
      {
/* 463 */         localOracleCallableStatement.close();
        
/* 465 */         localOracleCallableStatement = null;
      }
    }
/* 468 */     return str;
  }
  
  private static int getPlsqlMaxInstrSize(OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 481 */     boolean bool = paramOracleConnection.isCharSetMultibyte(paramOracleConnection.getDriverCharSet());
    
/* 483 */     int i = paramOracleConnection.getMaxCharbyteSize();
    
/* 485 */     int j = 32512;
    
/* 487 */     if (bool) {
/* 488 */       j = 32512 / (paramOracleConnection.getC2SNlsRatio() * i);
    }
/* 490 */     return j;
  }
  
  public static long read(OracleConnection paramOracleConnection, Datum paramDatum, int paramInt, long paramLong1, long paramLong2, byte[] paramArrayOfByte)
    throws SQLException
  {
/* 498 */     OracleCallableStatement localOracleCallableStatement = null;
/* 499 */     int i = 0;
    
    try
    {
/* 503 */       localOracleCallableStatement = (OracleCallableStatement)paramOracleConnection.prepareCall("begin dbms_lob.read (:1, :2, :3, :4); end;");
      
/* 506 */       int j = 0;
/* 507 */       int k = 0;
      
/* 510 */       if (isNCLOB(paramDatum))
      {
/* 512 */         localOracleCallableStatement.setFormOfUse(1, (short)2);
/* 513 */         localOracleCallableStatement.setFormOfUse(4, (short)2);
      }
      
/* 516 */       localOracleCallableStatement.setObject(1, paramDatum, paramInt);
/* 517 */       localOracleCallableStatement.registerOutParameter(2, 2);
/* 518 */       localOracleCallableStatement.registerOutParameter(4, -3);
      
/* 520 */       while (i < paramLong2)
      {
/* 522 */         k = Math.min((int)paramLong2, 32512);
        
/* 526 */         localOracleCallableStatement.setInt(2, k);
/* 527 */         localOracleCallableStatement.setInt(3, (int)paramLong1 + i);
/* 528 */         localOracleCallableStatement.execute();
        
/* 530 */         j = localOracleCallableStatement.getInt(2);
/* 531 */         byte[] arrayOfByte = localOracleCallableStatement.getBytes(4);
        
/* 536 */         j = Math.min(j, arrayOfByte.length);
        
/* 538 */         System.arraycopy(arrayOfByte, 0, paramArrayOfByte, i, j);
        
/* 543 */         i += j;
/* 544 */         paramLong2 -= j;
      }
      
    }
    catch (SQLException localSQLException)
    {
/* 551 */       if (localSQLException.getErrorCode() != 1403)
      {
/* 555 */         throw localSQLException;
      }
      
    }
    finally
    {
/* 565 */       if (localOracleCallableStatement != null)
      {
/* 567 */         localOracleCallableStatement.close();
        
/* 569 */         localOracleCallableStatement = null;
      }
    }
    
/* 573 */     return i;
  }
  
  public static long length(OracleConnection paramOracleConnection, Datum paramDatum, int paramInt)
    throws SQLException
  {
/* 581 */     long l = 0L;
/* 582 */     OracleCallableStatement localOracleCallableStatement = null;
    
    try
    {
/* 586 */       localOracleCallableStatement = (OracleCallableStatement)paramOracleConnection.prepareCall("begin :1 := dbms_lob.getLength (:2); end;");
      
/* 589 */       if (isNCLOB(paramDatum)) {
/* 590 */         localOracleCallableStatement.setFormOfUse(2, (short)2);
      }
/* 592 */       localOracleCallableStatement.setObject(2, paramDatum, paramInt);
/* 593 */       localOracleCallableStatement.registerOutParameter(1, 2);
/* 594 */       localOracleCallableStatement.execute();
      
/* 596 */       l = localOracleCallableStatement.getLong(1);
    }
    finally
    {
/* 600 */       if (localOracleCallableStatement != null)
      {
/* 602 */         localOracleCallableStatement.close();
        
/* 604 */         localOracleCallableStatement = null;
      }
    }
    
/* 608 */     return l;
  }
  
  public static long hasPattern(OracleConnection paramOracleConnection, Datum paramDatum, int paramInt, byte[] paramArrayOfByte, long paramLong)
    throws SQLException
  {
/* 618 */     if ((paramArrayOfByte == null) || (paramLong <= 0L)) {
/* 619 */       return 0L;
    }
/* 621 */     long l1 = paramArrayOfByte.length;
/* 622 */     long l2 = length(paramOracleConnection, paramDatum, paramInt);
    
/* 625 */     if ((l1 == 0L) || (l1 > l2 - paramLong + 1L) || (paramLong > l2))
    {
/* 627 */       return 0L;
    }
    
/* 631 */     if (l1 <= 32512L)
    {
/* 635 */       OracleCallableStatement localOracleCallableStatement = null;
      
      try
      {
/* 639 */         localOracleCallableStatement = (OracleCallableStatement)paramOracleConnection.prepareCall("begin :1 := dbms_lob.instr(:2, :3, :4); end;");
        
/* 642 */         localOracleCallableStatement.registerOutParameter(1, 2);
/* 643 */         localOracleCallableStatement.setObject(2, paramDatum, paramInt);
/* 644 */         localOracleCallableStatement.setBytes(3, paramArrayOfByte);
/* 645 */         localOracleCallableStatement.setLong(4, paramLong);
/* 646 */         localOracleCallableStatement.execute();
        
/* 648 */         return localOracleCallableStatement.getLong(1);
      }
      finally
      {
/* 652 */         localOracleCallableStatement.close();
        
/* 654 */         localOracleCallableStatement = null;
      }
    }
    
/* 663 */     int i = 0;
/* 664 */     long l3 = paramLong;
/* 665 */     int j = 0;
    
/* 668 */     long l5 = 0L;
    
/* 671 */     while (j == 0)
    {
/* 673 */       if (l1 > l2 - l3 + 1L) {
/* 674 */         return 0L;
      }
/* 676 */       i = 0;
      
/* 679 */       int k = (int)Math.min(32512L, l1 - i);
      
/* 683 */       byte[] arrayOfByte = new byte[k];
      
/* 685 */       System.arraycopy(paramArrayOfByte, i, arrayOfByte, 0, k);
      
/* 688 */       long l4 = hasPattern(paramOracleConnection, paramDatum, paramInt, arrayOfByte, l3);
      
/* 690 */       if (l4 == 0L)
      {
/* 692 */         return 0L;
      }
      
/* 696 */       l5 = l4;
      
/* 698 */       i += k;
/* 699 */       l3 = l4 + k;
      
/* 702 */       int m = 1;
      
/* 704 */       while (m != 0)
      {
/* 706 */         k = (int)Math.min(32512L, l1 - i);
        
/* 710 */         arrayOfByte = new byte[k];
        
/* 712 */         System.arraycopy(paramArrayOfByte, i, arrayOfByte, 0, k);
        
/* 716 */         l4 = hasPattern(paramOracleConnection, paramDatum, paramInt, arrayOfByte, l3);
        
/* 719 */         if (l4 == l3)
        {
/* 723 */           i += k;
/* 724 */           l3 += k;
          
/* 726 */           if (i == l1)
          {
/* 728 */             m = 0;
/* 729 */             j = 1;
          }
        } else {
/* 732 */           if (l4 == 0L)
          {
/* 737 */             return 0L;
          }
          
/* 744 */           l3 = l4 - i;
          
/* 746 */           m = 0;
        }
      }
    }
    
/* 752 */     return l5;
  }
  
  public static long isSubLob(OracleConnection paramOracleConnection, Datum paramDatum1, int paramInt, Datum paramDatum2, long paramLong)
    throws SQLException
  {
/* 762 */     if ((paramDatum2 == null) || (paramLong <= 0L)) {
/* 763 */       return 0L;
    }
/* 765 */     long l1 = length(paramOracleConnection, paramDatum2, paramInt);
/* 766 */     long l2 = length(paramOracleConnection, paramDatum1, paramInt);
    
/* 768 */     if ((l1 == 0L) || (l1 > l2 - paramLong + 1L) || (paramLong > l2))
    {
/* 770 */       return 0L;
    }
    
/* 774 */     if (l1 <= 32512L)
    {
/* 778 */       byte[] arrayOfByte1 = new byte[(int)l1];
      
/* 780 */       read(paramOracleConnection, paramDatum2, paramInt, 1L, l1, arrayOfByte1);
      
/* 782 */       return hasPattern(paramOracleConnection, paramDatum1, paramInt, arrayOfByte1, paramLong);
    }
    
/* 790 */     int i = 0;
/* 791 */     long l3 = paramLong;
/* 792 */     int j = 0;
    
/* 795 */     long l5 = 0L;
    
/* 798 */     while (j == 0)
    {
/* 800 */       if (l1 > l2 - l3 + 1L) {
/* 801 */         return 0L;
      }
/* 803 */       i = 0;
      
/* 806 */       int k = (int)Math.min(32512L, l1 - i);
      
/* 810 */       byte[] arrayOfByte2 = new byte[k];
      
/* 812 */       read(paramOracleConnection, paramDatum2, paramInt, i + 1, k, arrayOfByte2);
      
/* 815 */       long l4 = hasPattern(paramOracleConnection, paramDatum1, paramInt, arrayOfByte2, l3);
      
/* 817 */       if (l4 == 0L)
      {
/* 819 */         return 0L;
      }
      
/* 823 */       l5 = l4;
      
/* 825 */       i += k;
/* 826 */       l3 = l4 + k;
      
/* 829 */       int m = 1;
      
/* 831 */       while (m != 0)
      {
/* 833 */         k = (int)Math.min(32512L, l1 - i);
        
/* 837 */         arrayOfByte2 = new byte[k];
        
/* 839 */         read(paramOracleConnection, paramDatum2, paramInt, i + 1, k, arrayOfByte2);
        
/* 842 */         l4 = hasPattern(paramOracleConnection, paramDatum1, paramInt, arrayOfByte2, l3);
        
/* 845 */         if (l4 == l3)
        {
/* 849 */           i += k;
/* 850 */           l3 += k;
          
/* 852 */           if (i == l1)
          {
/* 854 */             m = 0;
/* 855 */             j = 1;
          }
        } else {
/* 858 */           if (l4 == 0L)
          {
/* 863 */             return 0L;
          }
          
/* 870 */           l3 = l4 - i;
          
/* 872 */           m = 0;
        }
      }
    }
    
/* 878 */     return l5;
  }
  
  private static boolean isNCLOB(Datum paramDatum)
  {
/* 888 */     Class localClass = null;
    
    try
    {
/* 892 */       localClass = Class.forName("oracle.sql.CLOB");
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
/* 897 */       return false;
    }
    
/* 900 */     if (!localClass.isInstance(paramDatum)) {
/* 901 */       return false;
    }
/* 903 */     CLOB localCLOB = (CLOB)paramDatum;
    
/* 905 */     return localCLOB.isNCLOB();
  }
  
  public static Datum createTemporaryLob(Connection paramConnection, boolean paramBoolean, int paramInt1, int paramInt2, short paramShort)
    throws SQLException
  {
/* 914 */     OracleCallableStatement localOracleCallableStatement = null;
/* 915 */     Datum localDatum = null;
    
    try
    {
/* 919 */       localOracleCallableStatement = (OracleCallableStatement)paramConnection.prepareCall("begin dbms_lob.createTemporary (:1," + (paramBoolean ? "TRUE" : "FALSE") + ", :2); end;");
      
/* 923 */       localOracleCallableStatement.registerOutParameter(1, paramInt2);
/* 924 */       localOracleCallableStatement.setFormOfUse(1, paramShort);
      
/* 927 */       localOracleCallableStatement.setInt(2, paramInt1);
/* 928 */       localOracleCallableStatement.execute();
      
/* 930 */       localDatum = localOracleCallableStatement.getOracleObject(1);
    }
    finally
    {
/* 934 */       if (localOracleCallableStatement != null)
      {
/* 936 */         localOracleCallableStatement.close();
        
/* 938 */         localOracleCallableStatement = null;
      }
    }
    
/* 942 */     return localDatum;
  }
  
  public static void freeTemporaryLob(Connection paramConnection, Datum paramDatum, int paramInt)
    throws SQLException
  {
/* 950 */     OracleCallableStatement localOracleCallableStatement = null;
    
    try
    {
/* 954 */       localOracleCallableStatement = (OracleCallableStatement)paramConnection.prepareCall("begin dbms_lob.freeTemporary (:1); end;");
      
/* 957 */       localOracleCallableStatement.registerOutParameter(1, paramInt);
      
/* 959 */       if (isNCLOB(paramDatum)) {
/* 960 */         localOracleCallableStatement.setFormOfUse(1, (short)2);
      }
/* 962 */       localOracleCallableStatement.setOracleObject(1, paramDatum);
/* 963 */       localOracleCallableStatement.execute();
/* 964 */       Datum localDatum = localOracleCallableStatement.getOracleObject(1);
/* 965 */       byte[] arrayOfByte = localDatum.shareBytes();
/* 966 */       paramDatum.setShareBytes(arrayOfByte);
    }
    finally
    {
/* 970 */       if (localOracleCallableStatement != null)
      {
/* 972 */         localOracleCallableStatement.close();
        
/* 974 */         localOracleCallableStatement = null;
      }
    }
  }
  
/* 981 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/LobPlsqlUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */