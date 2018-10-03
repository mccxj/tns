package oracle.core.lmx;
public class CoreException
  extends Exception
{
  public static final byte UNIMPLEMENTED = 1;
  
  public static final byte UNDERFLOW = 2;
  
  public static final byte OVERFLOW = 3;
  
  public static final byte INVALIDORLN = 4;
  
  public static final byte BADFORMATORLN = 5;
  
  public static final byte INVALIDORLD = 6;
  
  public static final byte BADFORMATORLD = 7;
  
  public static final byte BADYEAR = 8;
  
  public static final byte BADDAYYEAR = 9;
  public static final byte BADJULIANDATE = 10;
  public static final byte INVALIDINPUTN = 11;
  public static final byte NLSNOTSUPPORTED = 12;
  public static final byte INVALIDINPUT = 13;
  public static final byte CONVERSIONERROR = 14;
  
  public CoreException() {}
  
  public CoreException(String paramString)
  {
/*  34 */     super(paramString);
  }
  
  public CoreException(byte paramByte)
  {
/*  43 */     this.ecode = paramByte;
  }
  
  public void setErrorCode(byte paramByte)
  {
/*  53 */     this.ecode = paramByte;
  }
  
  public byte getErrorCode()
  {
/*  63 */     return this.ecode;
  }
  
  public String getMessage()
  {
/*  73 */     if (this.ecode == 0) {
/*  74 */       return super.getMessage();
    }
/*  76 */     return getMessage(this.ecode);
  }
  
  public static String getMessage(byte paramByte)
  {
/*  90 */     if ((paramByte < 1) || (paramByte > 14)) {
/*  91 */       return "Unknown exception";
    }
/*  93 */     return _errmsgs[paramByte];
  }
  
/* 169 */   private static final String[] _errmsgs = { "Unknown Exception", "Unimplemented method called", "Underflow Exception", "Overflow Exception", "Invalid Oracle Number", "Bad Oracle Number format", "Invalid Oracle Date", "Bad Oracle Date format", "Year Not in Range", "Day of Year Not in Range", "Julian Date Not in Range", "Invalid Input Number", "NLS Not Supported", "Invalid Input", "Conversion Error" };
  private byte ecode;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/core/lmx/CoreException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */