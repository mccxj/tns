/*    */ package oracle.net.resolver;
/*    */ import java.util.Vector;
/*    */ import oracle.net.jdbc.TNSAddress.Address;
/*    */ import oracle.net.jdbc.TNSAddress.SchemaObjectFactoryInterface;
/*    */ import oracle.net.nt.ConnOption;
/*    */ import oracle.net.nt.ConnStrategy;
/*    */ public class NavAddress
/*    */   extends Address
/*    */   implements NavSchemaObject
/*    */ {
/*    */   public NavAddress(SchemaObjectFactoryInterface paramSchemaObjectFactoryInterface)
/*    */   {
/* 48 */     super(paramSchemaObjectFactoryInterface);
/*    */   }
/*    */   
/*    */   public void navigate(ConnStrategy paramConnStrategy, StringBuffer paramStringBuffer)
/*    */   {
/* 57 */     ConnOption localConnOption = new ConnOption();
/* 58 */     localConnOption.addr = this.addr;
/* 59 */     localConnOption.conn_data.append(paramStringBuffer.toString());
/* 60 */     localConnOption.conn_data.append(toString());
/*    */     
/* 69 */     paramConnStrategy.addOption(localConnOption);
/*    */   }
/*    */   
/*    */   public void addToString(ConnStrategy paramConnStrategy)
/*    */   {
/* 78 */     String str = toString();
/*    */     
/* 80 */     for (int i = paramConnStrategy.cOpts.size() - 1; 
/* 81 */         (i >= 0) && (!((ConnOption)paramConnStrategy.cOpts.elementAt(i)).done); i--)
/*    */     {
/* 83 */       ((ConnOption)paramConnStrategy.cOpts.elementAt(i)).conn_data.append(str);
/*    */     }
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/NavAddress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */