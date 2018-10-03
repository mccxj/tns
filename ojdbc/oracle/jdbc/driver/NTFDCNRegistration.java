package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executor;
import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
class NTFDCNRegistration
  extends NTFRegistration
  implements DatabaseChangeRegistration
{
  private final long regid;
/*  49 */   private String[] tables = new String[10];
/*  50 */   private int nbOfStringsInTable = 0;
  
  NTFDCNRegistration(int paramInt1, boolean paramBoolean, String paramString1, long paramLong, String paramString2, String paramString3, int paramInt2, Properties paramProperties, short paramShort)
  {
/*  64 */     super(paramInt1, 2, paramBoolean, paramString1, paramString3, paramInt2, paramProperties, paramString2, paramShort);
    
/*  75 */     this.regid = paramLong;
  }
  
  NTFDCNRegistration(String paramString1, long paramLong, String paramString2, short paramShort)
  {
/*  85 */     super(0, 2, false, paramString1, null, 0, null, paramString2, paramShort);
    
/*  95 */     this.regid = paramLong;
  }
  
  public int getRegistrationId()
  {
/* 103 */     return (int)this.regid;
  }
  
  public long getRegId()
  {
/* 110 */     return this.regid;
  }
  
  public void addListener(DatabaseChangeListener paramDatabaseChangeListener, Executor paramExecutor)
    throws SQLException
  {
/* 121 */     NTFEventListener localNTFEventListener = new NTFEventListener(paramDatabaseChangeListener);
/* 122 */     localNTFEventListener.setExecutor(paramExecutor);
/* 123 */     addListener(localNTFEventListener);
  }
  
  public void addListener(DatabaseChangeListener paramDatabaseChangeListener)
    throws SQLException
  {
/* 132 */     NTFEventListener localNTFEventListener = new NTFEventListener(paramDatabaseChangeListener);
/* 133 */     addListener(localNTFEventListener);
  }
  
  public void removeListener(DatabaseChangeListener paramDatabaseChangeListener)
    throws SQLException
  {
/* 141 */     super.removeListener(paramDatabaseChangeListener);
  }
  
  synchronized void addTablesName(String[] paramArrayOfString, int paramInt)
  {
/* 148 */     if (this.nbOfStringsInTable + paramInt > this.tables.length)
    {
/* 150 */       String[] arrayOfString = new String[(this.nbOfStringsInTable + paramInt) * 2];
/* 151 */       System.arraycopy(this.tables, 0, arrayOfString, 0, this.tables.length);
/* 152 */       this.tables = arrayOfString;
    }
    
/* 155 */     System.arraycopy(paramArrayOfString, 0, this.tables, this.nbOfStringsInTable, paramInt);
/* 156 */     this.nbOfStringsInTable += paramInt;
  }
  
  public String[] getTables()
  {
/* 163 */     String[] arrayOfString = new String[this.nbOfStringsInTable];
    
/* 168 */     System.arraycopy(this.tables, 0, arrayOfString, 0, this.nbOfStringsInTable);
/* 169 */     return arrayOfString;
  }
  
/* 191 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFDCNRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */