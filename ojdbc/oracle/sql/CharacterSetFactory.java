package oracle.sql;
import java.io.PrintStream;
import java.sql.SQLException;
abstract class CharacterSetFactory
{
  public static final short DEFAULT_CHARSET = -1;
  public static final short ASCII_CHARSET = 1;
  public static final short ISO_LATIN_1_CHARSET = 31;
  public static final short UNICODE_1_CHARSET = 870;
  public static final short UNICODE_2_CHARSET = 871;
  
  public abstract CharacterSet make(int paramInt);
  
  public static void main(String[] paramArrayOfString)
  {
/*  80 */     CharacterSet localCharacterSet1 = CharacterSet.make(871);
/*  81 */     int[] arrayOfInt = { 1, 31, 870, 871 };
    
/*  86 */     for (int i = 0; i < arrayOfInt.length; i++)
    {
/*  88 */       CharacterSet localCharacterSet2 = CharacterSet.make(arrayOfInt[i]);
      
/*  91 */       String str1 = "longlonglonglong";
/*  92 */       str1 = str1 + str1 + str1 + str1;
/*  93 */       str1 = str1 + str1 + str1 + str1;
/*  94 */       str1 = str1 + str1 + str1 + str1;
/*  95 */       str1 = str1 + str1 + str1 + str1;
      
/*  97 */       String[] arrayOfString = { "abc", "ab?c", "XYZ", str1 };
      
/* 102 */       for (int j = 0; j < arrayOfString.length; j++)
      {
/* 104 */         String str2 = arrayOfString[j];
/* 105 */         String str3 = str2;
        
/* 107 */         if (str2.length() > 16)
        {
/* 109 */           str3 = str3.substring(0, 16) + "...";
        }
        
/* 112 */         System.out.println("testing " + localCharacterSet2 + " against <" + str3 + ">");
        
/* 114 */         int k = 1;
        
        try
        {
/* 118 */           byte[] arrayOfByte1 = localCharacterSet2.convertWithReplacement(str2);
/* 119 */           String str4 = localCharacterSet2.toStringWithReplacement(arrayOfByte1, 0, arrayOfByte1.length);
          
/* 122 */           arrayOfByte1 = localCharacterSet2.convert(str4);
          
/* 124 */           String str5 = localCharacterSet2.toString(arrayOfByte1, 0, arrayOfByte1.length);
          
/* 126 */           if (!str4.equals(str5))
          {
/* 128 */             System.out.println("    FAILED roundTrip " + str5);
            
/* 130 */             k = 0;
          }
          Object localObject;
/* 133 */           if (localCharacterSet2.isLossyFrom(localCharacterSet1))
          {
            try
            {
/* 137 */               byte[] arrayOfByte2 = localCharacterSet2.convert(str2);
/* 138 */               localObject = localCharacterSet2.toString(arrayOfByte2, 0, arrayOfByte2.length);
              
/* 140 */               if (!((String)localObject).equals(str5))
              {
/* 142 */                 System.out.println("    FAILED roundtrip, no throw");
              }
            }
            catch (SQLException localSQLException) {}
          }
          else
          {
/* 149 */             if (!str5.equals(str2))
            {
/* 151 */               System.out.println("    FAILED roundTrip " + str5);
              
/* 153 */               k = 0;
            }
            
/* 156 */             byte[] arrayOfByte3 = localCharacterSet1.convert(str2);
/* 157 */             localObject = localCharacterSet2.convert(localCharacterSet1, arrayOfByte3, 0, arrayOfByte3.length);
            
/* 159 */             String str6 = localCharacterSet2.toString((byte[])localObject, 0, localObject.length);
            
/* 163 */             if (!str6.equals(str2))
            {
/* 165 */               System.out.println("    FAILED withoutReplacement " + str6);
              
/* 168 */               k = 0;
            }
          }
        }
        catch (Exception localException)
        {
/* 174 */           System.out.println("    FAILED with Exception " + localException);
        }
        
/* 177 */         if (k != 0)
        {
/* 179 */           System.out.println("    PASSED " + (localCharacterSet2.isLossyFrom(localCharacterSet1) ? "LOSSY" : ""));
        }
      }
    }
  }
  
/* 187 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */