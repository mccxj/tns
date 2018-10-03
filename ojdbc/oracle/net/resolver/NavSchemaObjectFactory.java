/*    */ package oracle.net.resolver;
/*    */ import oracle.net.jdbc.TNSAddress.SchemaObject;
/*    */ import oracle.net.jdbc.TNSAddress.SchemaObjectFactoryInterface;
/*    */ public class NavSchemaObjectFactory
/*    */   implements SchemaObjectFactoryInterface
/*    */ {
/*    */   public SchemaObject create(int paramInt)
/*    */   {
/* 53 */     switch (paramInt)
/*    */     {
/*    */     case 0: 
/* 56 */       return new NavAddress(this);
/*    */     case 1: 
/* 58 */       return new NavAddressList(this);
/*    */     case 2: 
/* 60 */       return new NavDescription(this);
/*    */     case 3: 
/* 62 */       return new NavDescriptionList(this);
/*    */     case 4: 
/* 64 */       return new NavServiceAlias(this);
/*    */     }
/* 66 */     return null;
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/NavSchemaObjectFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */