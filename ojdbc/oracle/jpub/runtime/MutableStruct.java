package oracle.jpub.runtime;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.sql.CustomDatumFactory;
import oracle.sql.Datum;
import oracle.sql.ORADataFactory;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
public class MutableStruct
{
  int length;
  STRUCT pickled;
  Datum[] datums;
  Object[] attributes;
  CustomDatumFactory[] old_factories;
  ORADataFactory[] factories;
  int[] sqlTypes;
  boolean pickledCorrect;
  boolean[] isNChar;
  
  public MutableStruct(STRUCT paramSTRUCT, int[] paramArrayOfInt, ORADataFactory[] paramArrayOfORADataFactory)
  {
/*  56 */     this.length = paramArrayOfORADataFactory.length;
/*  57 */     this.pickled = paramSTRUCT;
/*  58 */     this.factories = paramArrayOfORADataFactory;
/*  59 */     this.sqlTypes = paramArrayOfInt;
/*  60 */     initNChar(paramArrayOfInt.length);
/*  61 */     this.pickledCorrect = true;
  }
  
  public MutableStruct(Object[] paramArrayOfObject, int[] paramArrayOfInt, ORADataFactory[] paramArrayOfORADataFactory)
  {
/*  69 */     this.length = paramArrayOfORADataFactory.length;
/*  70 */     this.attributes = paramArrayOfObject;
/*  71 */     this.factories = paramArrayOfORADataFactory;
/*  72 */     this.sqlTypes = paramArrayOfInt;
/*  73 */     initNChar(paramArrayOfInt.length);
/*  74 */     this.pickledCorrect = false;
  }
  
  public MutableStruct(STRUCT paramSTRUCT, int[] paramArrayOfInt, CustomDatumFactory[] paramArrayOfCustomDatumFactory)
  {
/*  81 */     this.length = paramArrayOfCustomDatumFactory.length;
/*  82 */     this.pickled = paramSTRUCT;
/*  83 */     this.old_factories = paramArrayOfCustomDatumFactory;
/*  84 */     this.sqlTypes = paramArrayOfInt;
/*  85 */     initNChar(paramArrayOfInt.length);
/*  86 */     this.pickledCorrect = true;
  }
  
  public MutableStruct(Object[] paramArrayOfObject, int[] paramArrayOfInt, CustomDatumFactory[] paramArrayOfCustomDatumFactory)
  {
/*  94 */     this.length = paramArrayOfCustomDatumFactory.length;
/*  95 */     this.attributes = paramArrayOfObject;
/*  96 */     this.old_factories = paramArrayOfCustomDatumFactory;
/*  97 */     this.sqlTypes = paramArrayOfInt;
/*  98 */     initNChar(paramArrayOfInt.length);
/*  99 */     this.pickledCorrect = false;
  }
  
  public Datum toDatum(Connection paramConnection, String paramString)
    throws SQLException
  {
/* 108 */     if (!this.pickledCorrect)
    {
/* 112 */       this.pickled = new STRUCT(StructDescriptor.createDescriptor(paramString, paramConnection), paramConnection, getDatumAttributes(paramConnection));
      
/* 114 */       this.pickledCorrect = true;
    }
    
/* 117 */     return this.pickled;
  }
  
  public Datum toDatum(oracle.jdbc.OracleConnection paramOracleConnection, String paramString)
    throws SQLException
  {
/* 125 */     return toDatum(paramOracleConnection, paramString);
  }
  
  /**
   * @deprecated
   */
  public Datum toDatum(oracle.jdbc.driver.OracleConnection paramOracleConnection, String paramString)
    throws SQLException
  {
/* 136 */     return toDatum(paramOracleConnection, paramString);
  }
  
  public Object getAttribute(int paramInt)
    throws SQLException
  {
/* 144 */     Object localObject = getLazyAttributes()[paramInt];
    
/* 146 */     if (localObject == null)
    {
/* 148 */       Datum localDatum = getLazyDatums()[paramInt];
      
/* 150 */       if (this.old_factories == null)
      {
/* 152 */         localObject = Util.convertToObject(localDatum, this.sqlTypes[paramInt], this.factories[paramInt]);
/* 153 */         this.attributes[paramInt] = localObject;
        
/* 155 */         if (Util.isMutable(localDatum, this.factories[paramInt])) {
/* 156 */           resetDatum(paramInt);
        }
      }
      else {
/* 160 */         localObject = Util.convertToObject(localDatum, this.sqlTypes[paramInt], this.old_factories[paramInt]);
/* 161 */         this.attributes[paramInt] = localObject;
        
/* 163 */         if (Util.isMutable(localDatum, this.old_factories[paramInt]))
/* 164 */           resetDatum(paramInt);
      }
    }
/* 167 */     return localObject;
  }
  
  public Object getOracleAttribute(int paramInt)
    throws SQLException
  {
    Object localObject;
    Datum localDatum;
/* 176 */     if (this.old_factories == null)
    {
/* 178 */       if (this.factories[paramInt] == null)
      {
/* 180 */         localObject = getDatumAttribute(paramInt, null);
        
/* 182 */         localDatum = getLazyDatums()[paramInt];
        
/* 184 */         if (Util.isMutable(localDatum, this.factories[paramInt])) {
/* 185 */           this.pickledCorrect = false;
        }
      } else {
/* 188 */         localObject = getAttribute(paramInt);
      }
      
    }
/* 192 */     else if (this.old_factories[paramInt] == null)
    {
/* 194 */       localObject = getDatumAttribute(paramInt, null);
      
/* 196 */       localDatum = getLazyDatums()[paramInt];
      
/* 198 */       if (Util.isMutable(localDatum, this.old_factories[paramInt])) {
/* 199 */         this.pickledCorrect = false;
      }
    } else {
/* 202 */       localObject = getAttribute(paramInt);
    }
/* 204 */     return localObject;
  }
  
  public Object[] getAttributes()
    throws SQLException
  {
/* 211 */     for (int i = 0; i < this.length; i++)
    {
/* 213 */       getAttribute(i);
    }
/* 215 */     return this.attributes;
  }
  
  public Object[] getOracleAttributes()
    throws SQLException
  {
/* 222 */     Object[] arrayOfObject = new Object[this.length];
    
/* 224 */     for (int i = 0; i < this.length; i++)
    {
/* 226 */       arrayOfObject[i] = getOracleAttribute(i);
    }
/* 228 */     return arrayOfObject;
  }
  
  public void setAttribute(int paramInt, Object paramObject)
    throws SQLException
  {
/* 244 */     if (paramObject == null)
    {
/* 246 */       getLazyDatums();
    }
    
/* 249 */     resetDatum(paramInt);
    
/* 251 */     getLazyAttributes()[paramInt] = paramObject;
  }
  
  public void setDoubleAttribute(int paramInt, double paramDouble)
    throws SQLException
  {
/* 258 */     setAttribute(paramInt, Double.valueOf(paramDouble));
  }
  
  public void setFloatAttribute(int paramInt, float paramFloat)
    throws SQLException
  {
/* 265 */     setAttribute(paramInt, Float.valueOf(paramFloat));
  }
  
  public void setIntAttribute(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 272 */     setAttribute(paramInt1, Integer.valueOf(paramInt2));
  }
  
  public void setOracleAttribute(int paramInt, Object paramObject)
    throws SQLException
  {
/* 279 */     if (this.old_factories == null)
    {
/* 281 */       if (this.factories[paramInt] == null) {
/* 282 */         setDatumAttribute(paramInt, (Datum)paramObject);
      } else {
/* 284 */         setAttribute(paramInt, paramObject);
      }
      
    }
/* 288 */     else if (this.old_factories[paramInt] == null) {
/* 289 */       setDatumAttribute(paramInt, (Datum)paramObject);
    } else {
/* 291 */       setAttribute(paramInt, paramObject);
    }
  }
  
  Datum getDatumAttribute(int paramInt, Connection paramConnection)
    throws SQLException
  {
/* 299 */     Datum localDatum = getLazyDatums()[paramInt];
    
/* 301 */     if (localDatum == null)
    {
/* 303 */       Object localObject = getLazyAttributes()[paramInt];
      
/* 305 */       localDatum = Util.convertToOracle(localObject, paramConnection, isNChar(paramInt));
/* 306 */       this.datums[paramInt] = localDatum;
    }
/* 308 */     return localDatum;
  }
  
  void setDatumAttribute(int paramInt, Datum paramDatum)
    throws SQLException
  {
/* 315 */     resetAttribute(paramInt);
    
/* 317 */     getLazyDatums()[paramInt] = paramDatum;
/* 318 */     this.pickledCorrect = false;
  }
  
  Datum[] getDatumAttributes(Connection paramConnection)
    throws SQLException
  {
/* 325 */     for (int i = 0; i < this.length; i++)
    {
/* 327 */       getDatumAttribute(i, paramConnection);
    }
    
/* 330 */     return (Datum[])this.datums.clone();
  }
  
  void resetAttribute(int paramInt)
    throws SQLException
  {
/* 337 */     if (this.attributes != null)
    {
/* 339 */       this.attributes[paramInt] = null;
    }
  }
  
  void resetDatum(int paramInt)
    throws SQLException
  {
/* 347 */     if (this.datums != null)
    {
/* 349 */       this.datums[paramInt] = null;
    }
    
/* 357 */     this.pickledCorrect = false;
  }
  
  Object[] getLazyAttributes()
  {
/* 364 */     if (this.attributes == null)
    {
/* 366 */       this.attributes = new Object[this.length];
    }
    
/* 369 */     return this.attributes;
  }
  
  Datum[] getLazyDatums()
    throws SQLException
  {
/* 376 */     if (this.datums == null)
    {
/* 380 */       if (this.pickled != null)
      {
/* 384 */         this.datums = this.pickled.getOracleAttributes();
/* 385 */         this.pickledCorrect = true;
        
/* 391 */         if (this.attributes != null)
        {
/* 393 */           for (int i = 0; i < this.length; i++)
          {
/* 395 */             if (this.attributes[i] != null)
            {
/* 397 */               this.datums[i] = null;
/* 398 */               this.pickledCorrect = false;
            }
          }
        }
      }
      else
      {
/* 405 */         this.datums = new Datum[this.length];
      }
    }
/* 408 */     return this.datums;
  }
  
  private void initNChar(int paramInt)
  {
/* 415 */     this.isNChar = new boolean[paramInt];
/* 416 */     for (int i = 0; i < paramInt; i++) {
/* 417 */       this.isNChar[i] = false;
    }
  }
  
  public void setNChar(int paramInt)
    throws SQLException
  {
/* 424 */     this.isNChar[paramInt] = true;
  }
  
  public boolean isNChar(int paramInt)
    throws SQLException
  {
/* 430 */     int i = this.isNChar[paramInt];
/* 431 */     return i;
  }
  
/* 451 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jpub/runtime/MutableStruct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */