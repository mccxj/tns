/*    */ package oracle.jdbc.internal;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.driver.InternalFactory;
/*    */ public abstract class KeywordValueLong
/*    */ {
/*    */   public abstract int getKeyword()
/*    */     throws SQLException;
/*    */   
/*    */   public abstract byte[] getBinaryValue()
/*    */     throws SQLException;
/*    */   
/*    */   public abstract String getTextValue()
/*    */     throws SQLException;
/*    */   
/*    */   public static final KeywordValueLong constructKeywordValue(int paramInt, String paramString)
/*    */     throws SQLException
/*    */   {
/* 58 */     return InternalFactory.createKeywordValueLong(paramInt, paramString, null);
/*    */   }
/*    */   
/*    */   public static final KeywordValueLong constructKeywordValue(int paramInt, byte[] paramArrayOfByte)
/*    */     throws SQLException
/*    */   {
/* 70 */     return InternalFactory.createKeywordValueLong(paramInt, null, paramArrayOfByte);
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/KeywordValueLong.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */