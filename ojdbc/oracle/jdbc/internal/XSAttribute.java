/*    */ package oracle.jdbc.internal;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.driver.InternalFactory;
/*    */ public abstract class XSAttribute
/*    */ {
/*    */   public static final XSAttribute constructXSAttribute()
/*    */     throws SQLException
/*    */   {
/* 46 */     return InternalFactory.createXSAttribute();
/*    */   }
/*    */   
/*    */   public abstract void setAttributeName(String paramString)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract void setAttributeValue(String paramString)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract void setAttributeDefaultValue(String paramString)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract void setFlag(long paramLong)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract String getAttributeName();
/*    */   
/*    */   public abstract String getAttributeValue();
/*    */   
/*    */   public abstract String getAttributeDefaultValue();
/*    */   
/*    */   public abstract long getFlag();
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/XSAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */