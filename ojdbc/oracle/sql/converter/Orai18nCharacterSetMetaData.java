/*    */ package oracle.sql.converter;
/*    */ import java.util.List;
/*    */ import oracle.i18n.text.OraBoot;
/*    */ class Orai18nCharacterSetMetaData
/*    */   implements InternalCharacterSetMetaData
/*    */ {
/* 41 */   OraBoot oraBoot = OraBoot.getInstance();
/*    */   
/*    */   public boolean isFixedWidth(int paramInt)
/*    */   {
/* 45 */     String str = this.oraBoot.getCharSetName("" + paramInt);
/* 46 */     return this.oraBoot.getCharSetIsFixed().contains(str);
/*    */   }
/*    */   
/*    */   public int getMaxCharLength(int paramInt)
/*    */   {
/* 51 */     String str = this.oraBoot.getCharsetMaxCharLen("" + paramInt);
/* 52 */     return str != null ? Integer.parseInt(str) & 0xFF : 0;
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/converter/Orai18nCharacterSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */