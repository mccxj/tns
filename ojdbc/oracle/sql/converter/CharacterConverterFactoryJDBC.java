/*    */ package oracle.sql.converter;
/*    */ public class CharacterConverterFactoryJDBC
/*    */   extends CharacterConverterFactory
/*    */ {
/*    */   public JdbcCharacterConverters make(int paramInt)
/*    */   {
/* 45 */     return CharacterConverterJDBC.getInstance(paramInt);
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/converter/CharacterConverterFactoryJDBC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */