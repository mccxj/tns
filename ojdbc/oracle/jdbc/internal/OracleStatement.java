package oracle.jdbc.internal;
import java.sql.SQLException;
public abstract interface OracleStatement
  extends oracle.jdbc.OracleStatement
{
  public static final int DEFAULT_RSET_TYPE = 1;
  public static final int CLOSED = 0;
  public static final int ACTIVE = 1;
  public static final int CACHED = 2;
  public static final int NON_CACHED = 3;
  
  public abstract void setFixedString(boolean paramBoolean);
  
  public abstract boolean getFixedString();
  
  public abstract int sendBatch()
    throws SQLException;
  
  public abstract boolean getserverCursor();
  
  public abstract int getcacheState();
  
  public abstract int getstatementType();
  
  public abstract SqlKind getSqlKind()
    throws SQLException;
  
  public abstract long getChecksum()
    throws SQLException;
  
  public abstract void setSnapshotSCN(long paramLong)
    throws SQLException;
  
  public static enum SqlKind
  {
/* 137 */     SELECT(false, false, true, false), 
/* 138 */     DELETE(false, true, false, false), 
/* 139 */     INSERT(false, true, false, false), 
/* 140 */     MERGE(false, true, false, false), 
/* 141 */     UPDATE(false, true, false, false), 
/* 142 */     PLSQL_BLOCK(true, false, false, false), 
/* 143 */     CALL_BLOCK(true, false, false, false), 
/* 144 */     SELECT_FOR_UPDATE(false, false, true, false), 
/* 145 */     ALTER_SESSION(false, false, false, true), 
/* 146 */     OTHER(false, false, false, true), 
/* 147 */     UNINITIALIZED(false, false, false, false);
    
    private final boolean dml;
    
    private final boolean plsqlOrCall;
    
    private final boolean select;
    private final boolean other;
    
    private SqlKind(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
    {
/* 159 */       this.dml = paramBoolean2;
/* 160 */       this.plsqlOrCall = paramBoolean1;
/* 161 */       this.select = paramBoolean3;
/* 162 */       this.other = paramBoolean4;
    }
    
/* 165 */     public boolean isPlsqlOrCall() { return this.plsqlOrCall; }
/* 166 */     public boolean isDML() { return this.dml; }
/* 167 */     public boolean isSELECT() { return this.select; }
/* 168 */     public boolean isOTHER() { return this.other; }
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */