package oracle.sql;
class utilpack
{
  protected static int LEFTSHIFTFIRSTNIBBLE(byte paramByte)
  {
/*  38 */     int i = 0;
    
/*  40 */     i = (paramByte & 0xFF) << INTYM3BYTE;
    
/*  42 */     return i;
  }
  
  protected static int LEFTSHIFTSECONDNIBBLE(byte paramByte)
  {
/*  49 */     int i = 0;
    
/*  51 */     i = (paramByte & 0xFF) << INTYM2BYTE;
    
/*  53 */     return i;
  }
  
  protected static int LEFTSHIFTTHIRDNIBBLE(byte paramByte)
  {
/*  59 */     int i = 0;
    
/*  61 */     i = (paramByte & 0xFF) << INTYM1BYTE;
    
/*  63 */     return i;
  }
  
  protected static byte RIGHTSHIFTFIRSTNIBBLE(int paramInt)
  {
/*  70 */     byte b = 0;
    
/*  73 */     b = (byte)(paramInt >> INTYM3BYTE & 0xFF);
    
/*  75 */     return b;
  }
  
  protected static byte RIGHTSHIFTSECONDNIBBLE(int paramInt)
  {
/*  82 */     byte b = 0;
    
/*  85 */     b = (byte)(paramInt >> INTYM2BYTE & 0xFF);
    
/*  87 */     return b;
  }
  
  protected static byte RIGHTSHIFTTHIRDNIBBLE(int paramInt)
  {
/*  93 */     byte b = 0;
    
/*  96 */     b = (byte)(paramInt >> INTYM1BYTE & 0xFF);
    
/*  98 */     return b;
  }
  
  protected static byte RIGHTSHIFTFOURTHNIBBLE(int paramInt)
  {
/* 105 */     byte b = 0;
    
/* 108 */     b = (byte)(paramInt & 0xFF);
    
/* 110 */     return b;
  }
  
/* 113 */   private static int INTYM3BYTE = 24;
/* 114 */   private static int INTYM2BYTE = 16;
/* 115 */   private static int INTYM1BYTE = 8;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/utilpack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */