package oracle.jdbc.replay.driver;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
abstract interface Replayable
{
  public abstract void fillInChecksum(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry)
    throws SQLException;
  
  public abstract Object replayOneCall(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry, SQLRecoverableException paramSQLRecoverableException)
    throws SQLException;
  
  public abstract void addToSameProxyList(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry);
  
  public abstract void removeFromSameProxyList(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry);
  
  public abstract void purgeSameProxyList();
  
  public abstract void setReplayingCallContext(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry, SQLRecoverableException paramSQLRecoverableException);
  
  public abstract FailoverManagerImpl getFailoverManager();
  
  public abstract void setFailoverManager(FailoverManagerImpl paramFailoverManagerImpl);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/Replayable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */