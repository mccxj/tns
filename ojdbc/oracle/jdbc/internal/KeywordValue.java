/*    */ package oracle.jdbc.internal;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.driver.InternalFactory;
/*    */ public abstract class KeywordValue
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
/*    */   public static final KeywordValue constructKeywordValue(int paramInt, String paramString)
/*    */     throws SQLException
/*    */   {
/* 52 */     return InternalFactory.createKeywordValue(paramInt, paramString, null);
/*    */   }
/*    */   
/*    */   public static final KeywordValue constructKeywordValue(int paramInt, byte[] paramArrayOfByte)
/*    */     throws SQLException
/*    */   {
/* 64 */     return InternalFactory.createKeywordValue(paramInt, null, paramArrayOfByte);
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/KeywordValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */