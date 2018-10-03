/*    */ package oracle.net.resolver;
/*    */ import oracle.net.jdbc.TNSAddress.SchemaObjectFactoryInterface;
/*    */ import oracle.net.jdbc.TNSAddress.ServiceAlias;
/*    */ import oracle.net.nt.ConnStrategy;
/*    */ public class NavServiceAlias
/*    */   extends ServiceAlias
/*    */   implements NavSchemaObject
/*    */ {
/*    */   public NavServiceAlias(SchemaObjectFactoryInterface paramSchemaObjectFactoryInterface)
/*    */   {
/* 51 */     super(paramSchemaObjectFactoryInterface);
/*    */   }
/*    */   
/*    */   public void navigate(ConnStrategy paramConnStrategy, StringBuffer paramStringBuffer)
/*    */   {
/* 62 */     StringBuffer localStringBuffer = new StringBuffer("");
/* 63 */     ((NavSchemaObject)this.child).navigate(paramConnStrategy, localStringBuffer);
/*    */   }
/*    */   
/*    */   public void addToString(ConnStrategy paramConnStrategy) {}
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/NavServiceAlias.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */