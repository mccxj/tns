/*    */ package oracle.sql.converter;
/*    */ import oracle.i18n.text.converter.CharacterConverter;
/*    */ import oracle.i18n.text.converter.CharacterConverterOGS;
/*    */ public class CharacterConverterFactoryOGS
/*    */   extends CharacterConverterFactory
/*    */ {
/*    */   public JdbcCharacterConverters make(int paramInt)
/*    */   {
/* 43 */     CharacterConverter localCharacterConverter = CharacterConverterOGS.getInstance(paramInt);
/* 44 */     return localCharacterConverter == null ? null : new I18CharacterConvertersWrapper(localCharacterConverter);
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/converter/CharacterConverterFactoryOGS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */