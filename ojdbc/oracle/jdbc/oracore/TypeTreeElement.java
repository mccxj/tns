/*    */ package oracle.jdbc.oracore;
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ public class TypeTreeElement
/*    */ {
/* 20 */   String schemaName = null;
/* 21 */   String typeName = null;
/* 22 */   String[] childSchemaNames = null;
/* 23 */   String[] childTypeNames = null;
/* 24 */   int size = 0;
/*    */   
/*    */   public TypeTreeElement(String paramString1, String paramString2)
/*    */   {
/* 29 */     this.schemaName = paramString1;
/* 30 */     this.typeName = paramString2;
/*    */   }
/*    */   
/*    */   public void putChild(String paramString1, String paramString2, int paramInt)
/*    */   {
/*    */     int i;
/* 37 */     if (this.childTypeNames == null)
/*    */     {
/* 39 */       i = 10;
/* 40 */       if (paramInt > i) i = paramInt * 2;
/* 41 */       this.childSchemaNames = new String[i];
/* 42 */       this.childTypeNames = new String[i];
/*    */     }
/* 44 */     if (paramInt >= this.childTypeNames.length)
/*    */     {
/* 46 */       i = (paramInt + 10) * 2;
/* 47 */       String[] arrayOfString = new String[i];
/* 48 */       System.arraycopy(this.childSchemaNames, 0, arrayOfString, 0, this.childSchemaNames.length);
/* 49 */       this.childSchemaNames = arrayOfString;
/* 50 */       arrayOfString = new String[i];
/* 51 */       System.arraycopy(this.childTypeNames, 0, arrayOfString, 0, this.childTypeNames.length);
/* 52 */       this.childTypeNames = arrayOfString;
/*    */     }
/* 54 */     this.childSchemaNames[paramInt] = paramString1;
/* 55 */     this.childTypeNames[paramInt] = paramString2;
/* 56 */     if (paramInt > this.size) { this.size = paramInt;
/*    */     }
/*    */   }
/*    */   
/*    */   public String getChildSchemaName(int paramInt)
/*    */   {
/* 63 */     return this.childSchemaNames[paramInt];
/*    */   }
/*    */   
/*    */   public String getChildTypeName(int paramInt)
/*    */   {
/* 70 */     return this.childTypeNames[paramInt];
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 77 */     StringWriter localStringWriter = new StringWriter();
/* 78 */     PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
/* 79 */     localPrintWriter.println("schemaName: " + this.schemaName + " typeName: " + this.typeName);
/* 80 */     for (int i = 0; i < this.size; i++)
/*    */     {
/* 82 */       localPrintWriter.println("index: " + i + " schema name: " + this.childSchemaNames[i] + " type name: " + this.childTypeNames[i]);
/*    */     }
/*    */     
/* 87 */     return localStringWriter.getBuffer().substring(0);
/*    */   }
/*    */   
/* 91 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/TypeTreeElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */