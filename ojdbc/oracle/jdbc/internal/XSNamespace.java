/*    */ package oracle.jdbc.internal;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.driver.InternalFactory;
/*    */ import oracle.sql.TIMESTAMPTZ;
/*    */ public abstract class XSNamespace
/*    */ {
/*    */   public static final int ACL_ID_LENGTH = 16;
/*    */   
/*    */   public static final XSNamespace constructXSNamespace()
/*    */     throws SQLException
/*    */   {
/* 42 */     return InternalFactory.createXSNamespace();
/*    */   }
/*    */   
/*    */   public abstract void setNamespaceName(String paramString)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract void setTimestamp(TIMESTAMPTZ paramTIMESTAMPTZ)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract void setFlag(long paramLong)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract void setAttributes(XSAttribute[] paramArrayOfXSAttribute)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract void setACLIdList(byte[][] paramArrayOfByte)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract String getNamespaceName();
/*    */   
/*    */   public abstract TIMESTAMPTZ getTimestamp();
/*    */   
/*    */   public abstract long getFlag();
/*    */   
/*    */   public abstract XSAttribute[] getAttributes();
/*    */   
/*    */   public abstract byte[][] getACLIdList();
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/XSNamespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */