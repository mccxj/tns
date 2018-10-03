/*    */ package oracle.jdbc.driver;
/*    */ class Namespace
/*    */ {
/*    */   static final int ATTRIBUTE_MAX_LENGTH = 30;
/*    */   
/*    */   static final int VALUE_MAX_LENGTH = 4000;
/*    */   
/*    */   String name;
/*    */   
/*    */   boolean clear;
/*    */   
/*    */   String[] keys;
/*    */   
/*    */   String[] values;
/*    */   
/*    */   int nbPairs;
/*    */   
/*    */   Namespace(String paramString)
/*    */   {
/* 48 */     if (paramString == null) {
/* 49 */       throw new NullPointerException();
/*    */     }
/* 51 */     this.name = paramString;
/* 52 */     this.clear = false;
/* 53 */     this.nbPairs = 0;
/* 54 */     this.keys = new String[5];
/* 55 */     this.values = new String[5];
/*    */   }
/*    */   
/*    */   void clear()
/*    */   {
/* 60 */     this.clear = true;
/*    */     
/* 62 */     for (int i = 0; i < this.nbPairs; i++)
/*    */     {
/* 64 */       this.keys[i] = null;
/* 65 */       this.values[i] = null;
/*    */     }
/* 67 */     this.nbPairs = 0;
/*    */   }
/*    */   
/*    */   void setAttribute(String paramString1, String paramString2)
/*    */   {
/* 72 */     if ((paramString1 == null) || (paramString2 == null) || (paramString1.equals(""))) {
/* 73 */       throw new NullPointerException();
/*    */     }
/* 75 */     if (this.nbPairs == this.keys.length)
/*    */     {
/* 77 */       String[] arrayOfString1 = new String[this.keys.length * 2];
/* 78 */       String[] arrayOfString2 = new String[this.keys.length * 2];
/* 79 */       System.arraycopy(this.keys, 0, arrayOfString1, 0, this.keys.length);
/* 80 */       System.arraycopy(this.values, 0, arrayOfString2, 0, this.values.length);
/* 81 */       this.keys = arrayOfString1;
/* 82 */       this.values = arrayOfString2;
/*    */     }
/* 84 */     this.keys[this.nbPairs] = paramString1;
/* 85 */     this.values[this.nbPairs] = paramString2;
/* 86 */     this.nbPairs += 1;
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/Namespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */