/*    */ package oracle.jdbc.driver;
/*    */ import oracle.jdbc.OracleResultSet.AuthorizationIndicator;
/*    */ import oracle.sql.Datum;
/*    */ class CachedRowElement
/*    */ {
/*    */   private final int row;
/*    */   private final int col;
/*    */   private final OracleResultSet.AuthorizationIndicator securityIndicator;
/*    */   private final byte[] data;
/*    */   private Datum dataAsDatum;
/*    */   
/*    */   CachedRowElement(int paramInt1, int paramInt2, OracleResultSet.AuthorizationIndicator paramAuthorizationIndicator, byte[] paramArrayOfByte)
/*    */   {
/* 50 */     this.row = paramInt1;
/* 51 */     this.col = paramInt2;
/* 52 */     this.securityIndicator = paramAuthorizationIndicator;
/* 53 */     this.data = paramArrayOfByte;
/* 54 */     this.dataAsDatum = null;
/*    */   }
/*    */   
/*    */   OracleResultSet.AuthorizationIndicator getIndicator()
/*    */   {
/* 61 */     return this.securityIndicator;
/*    */   }
/*    */   
/*    */   void setData(Datum paramDatum)
/*    */   {
/* 68 */     this.dataAsDatum = paramDatum;
/*    */   }
/*    */   
/*    */   byte[] getData()
/*    */   {
/* 74 */     return this.data;
/*    */   }
/*    */   
/*    */   Datum getDataAsDatum()
/*    */   {
/* 80 */     return this.dataAsDatum;
/*    */   }
/*    */   
/* 84 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/CachedRowElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */