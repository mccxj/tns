/*    */ package oracle.sql;
/*    */ public class AttributeDescriptor
/*    */ {
/*    */   String attributeName;
/*    */   
/*    */   short attributeIdentifier;
/*    */   
/*    */   int attributeFlag;
/*    */   
/*    */   TypeDescriptor td;
/*    */   
/*    */   AttributeDescriptor(String paramString, short paramShort, int paramInt, TypeDescriptor paramTypeDescriptor)
/*    */   {
/* 41 */     this.attributeName = paramString;
/* 42 */     this.attributeIdentifier = paramShort;
/* 43 */     this.attributeFlag = paramInt;
/* 44 */     this.td = paramTypeDescriptor;
/*    */   }
/*    */   
/*    */   void setTypeDescriptor(TypeDescriptor paramTypeDescriptor)
/*    */   {
/* 51 */     this.td = paramTypeDescriptor;
/*    */   }
/*    */   
/*    */   public TypeDescriptor getTypeDescriptor()
/*    */   {
/* 58 */     return this.td;
/*    */   }
/*    */   
/*    */   public String getAttributeName()
/*    */   {
/* 68 */     return this.attributeName;
/*    */   }
/*    */   
/* 72 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/AttributeDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */