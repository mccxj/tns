package oracle.jdbc.proxy;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLData;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Wrapper;
import javax.sql.CommonDataSource;
import javax.sql.ConnectionEventListener;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import javax.sql.RowSet;
import javax.sql.RowSetInternal;
import javax.sql.RowSetListener;
import javax.sql.RowSetMetaData;
import javax.sql.RowSetReader;
import javax.sql.RowSetWriter;
import javax.sql.StatementEventListener;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import oracle.jdbc.NotificationRegistration;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleOCIFailover;
import oracle.jdbc.OracleParameterMetaData;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleResultSetCache;
import oracle.jdbc.OracleResultSetMetaData;
import oracle.jdbc.OracleSavepoint;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.StructMetaData;
import oracle.jdbc.proxy.annotation.ProxyFor;
@ProxyFor({OraclePreparedStatement.class, OracleCallableStatement.class, NotificationRegistration.class, OracleOCIFailover.class, OracleConnection.class, OracleParameterMetaData.class, OracleResultSet.class, OracleResultSetMetaData.class, OracleResultSetCache.class, OracleSavepoint.class, OracleStatement.class, StructMetaData.class, Array.class, Blob.class, CallableStatement.class, Clob.class, Connection.class, DatabaseMetaData.class, Driver.class, NClob.class, ParameterMetaData.class, PreparedStatement.class, Ref.class, ResultSet.class, ResultSetMetaData.class, RowId.class, Savepoint.class, SQLData.class, SQLInput.class, SQLOutput.class, SQLXML.class, Statement.class, Struct.class, Wrapper.class, CommonDataSource.class, ConnectionEventListener.class, ConnectionPoolDataSource.class, DataSource.class, PooledConnection.class, RowSet.class, RowSetInternal.class, RowSetListener.class, RowSetMetaData.class, RowSetReader.class, RowSetWriter.class, StatementEventListener.class, XAConnection.class, XADataSource.class})
public class NullProxy {}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/NullProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */