/*    */ package oracle.jdbc;
/*    */ import java.sql.ResultSetMetaData;
/*    */ import java.sql.SQLException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import oracle.sql.SQLName;
/*    */ public abstract interface OracleTypeMetaData
/*    */ {
/*    */   public abstract Kind getKind();
/*    */   
/*    */   public abstract String getName()
/*    */     throws SQLException;
/*    */   
/*    */   public abstract SQLName getSQLName()
/*    */     throws SQLException;
/*    */   
/*    */   public abstract String getSchemaName()
/*    */     throws SQLException;
/*    */   
/*    */   public abstract int getTypeCode()
/*    */     throws SQLException;
/*    */   
/*    */   public abstract String getTypeCodeName()
/*    */     throws SQLException;
/*    */   
/*    */   public static enum Kind
/*    */   {
/* 35 */     ARRAY, 
/* 36 */     OPAQUE, 
/* 37 */     STRUCT, 
/* 38 */     TYPE;
/*    */     
/*    */     private Kind() {}
/*    */   }
/*    */   
/*    */   public static enum ArrayStorage
/*    */   {
/* 45 */     VARRAY(3), 
/* 46 */     NESTED_TABLE(2);
/*    */     
/* 48 */     static { lookup = new HashMap(2);
/*    */       
/* 52 */       for (ArrayStorage localArrayStorage : values())
/* 53 */         lookup.put(Integer.valueOf(localArrayStorage.getCode()), localArrayStorage);
/*    */     }
/*    */     
/*    */     private static final Map<Integer, ArrayStorage> lookup;
/* 57 */     public static ArrayStorage withCode(int paramInt) { return (ArrayStorage)lookup.get(Integer.valueOf(paramInt)); }
/*    */     
/*    */     private final int code;
/*    */     private ArrayStorage(int paramInt)
/*    */     {
/* 63 */       this.code = paramInt;
/*    */     }
/*    */     
/* 66 */     public int getCode() { return this.code; }
/*    */   }
/*    */   
/*    */   public static abstract interface Array
/*    */     extends OracleTypeMetaData
/*    */   {
/*    */     public abstract int getBaseType()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract String getBaseName()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract OracleTypeMetaData.ArrayStorage getArrayStorage()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract long getMaxLength()
/*    */       throws SQLException;
/*    */   }
/*    */   
/*    */   public static abstract interface Opaque
/*    */     extends OracleTypeMetaData
/*    */   {
/*    */     public abstract long getMaxLength()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract boolean isTrustedLibrary()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract boolean isModeledInC()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract boolean hasUnboundedSize()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract boolean hasFixedSize()
/*    */       throws SQLException;
/*    */   }
/*    */   
/*    */   public static abstract interface Struct
/*    */     extends OracleTypeMetaData
/*    */   {
/*    */     public abstract int getTypeVersion()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract int getLength()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract ResultSetMetaData getMetaData()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract boolean isFinalType()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract boolean isSubtype()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract boolean isInstantiable()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract String getSupertypeName()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract int getLocalAttributeCount()
/*    */       throws SQLException;
/*    */     
/*    */     public abstract String[] getSubtypeNames()
/*    */       throws SQLException;
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleTypeMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */