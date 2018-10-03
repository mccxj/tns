package oracle.security.o3logon;
import java.security.SecureRandom;
public final class O3LoginProtocolHelper
{
  private final byte[] jdField_a_of_type_ArrayOfByte;
  private final byte[] b = new byte[8];
  private static long jdField_a_of_type_Long = ;
  private static int jdField_a_of_type_Int = 0;
  private static b jdField_a_of_type_OracleSecurityO3logonB;
  
  public O3LoginProtocolHelper()
  {
    this.jdField_a_of_type_ArrayOfByte = null;
  }
  
  public O3LoginProtocolHelper(byte[] paramArrayOfByte)
  {
    this.jdField_a_of_type_ArrayOfByte = paramArrayOfByte;
  }
  
  public final byte[] getVerifier(String paramString1, String paramString2)
  {
    return getVerifier(paramString1, paramString2, Boolean.valueOf(true));
  }
  
  public final byte[] getVerifier(String paramString1, String paramString2, Boolean paramBoolean)
  {
    if (jdField_a_of_type_OracleSecurityO3logonB == null) {
      jdField_a_of_type_OracleSecurityO3logonB = new b();
    }
    return jdField_a_of_type_OracleSecurityO3logonB.a(paramString1, paramString2, paramBoolean.booleanValue());
  }
  
  public final boolean authenticate(String paramString1, String paramString2)
  {
    try
    {
      Thread.sleep(jdField_a_of_type_Int * 1000);
    }
    catch (InterruptedException localInterruptedException) {}
    if (jdField_a_of_type_OracleSecurityO3logonB == null) {
      jdField_a_of_type_OracleSecurityO3logonB = new b();
    }
    byte[] arrayOfByte = jdField_a_of_type_OracleSecurityO3logonB.a(paramString1, paramString2);
    if (this.jdField_a_of_type_ArrayOfByte.length != arrayOfByte.length)
    {
      jdField_a_of_type_Int += 1;
      return false;
    }
    for (int i = 0; i < arrayOfByte.length; i++) {
      if (arrayOfByte[i] != this.jdField_a_of_type_ArrayOfByte[i])
      {
        jdField_a_of_type_Int += 1;
        return false;
      }
    }
    return true;
  }
  
  public final byte[] getChallenge(byte[] paramArrayOfByte)
  {
    SecureRandom localSecureRandom = null;
    localSecureRandom = new SecureRandom(paramArrayOfByte);
    jdField_a_of_type_Long += System.currentTimeMillis();
    localSecureRandom.setSeed(jdField_a_of_type_Long);
    localSecureRandom.setSeed(this.jdField_a_of_type_ArrayOfByte);
    localSecureRandom.nextBytes(this.b);
    a locala;
    byte[] arrayOfByte;
    return arrayOfByte = (locala = new a()).a(this.jdField_a_of_type_ArrayOfByte, this.b);
  }
  
  public final String getPassword(byte[] paramArrayOfByte)
  {
    a locala = new a();
    int i = paramArrayOfByte[(paramArrayOfByte.length - 1)];
    byte[] arrayOfByte1 = new byte[paramArrayOfByte.length - 1];
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte1, 0, arrayOfByte1.length);
    byte[] arrayOfByte2 = null;
    try
    {
      arrayOfByte2 = locala.b(this.b, arrayOfByte1);
    }
    catch (Exception localException)
    {
      return null;
    }
    byte[] arrayOfByte3 = new byte[arrayOfByte2.length - i];
    System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, arrayOfByte3.length);
    String str;
    return str = new String(arrayOfByte3).toUpperCase();
  }
  
  public static byte[] getResponse(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    if (jdField_a_of_type_OracleSecurityO3logonB == null) {
      jdField_a_of_type_OracleSecurityO3logonB = new b();
    }
    byte[] arrayOfByte1 = jdField_a_of_type_OracleSecurityO3logonB.a(paramString1, paramString2);
    a locala;
    byte[] arrayOfByte3 = (locala = new a()).b(arrayOfByte1, paramArrayOfByte);
    byte[] arrayOfByte4;
    int i = (arrayOfByte4 = paramString2.getBytes()).length % 8 > 0 ? (byte)(8 - arrayOfByte4.length % 8) : 0;
    byte[] arrayOfByte2 = new byte[arrayOfByte4.length + i];
    System.arraycopy(arrayOfByte4, 0, arrayOfByte2, 0, arrayOfByte4.length);
    byte[] arrayOfByte5;
    byte[] arrayOfByte6 = new byte[(arrayOfByte5 = locala.a(arrayOfByte3, arrayOfByte2)).length + 1];
    System.arraycopy(arrayOfByte5, 0, arrayOfByte6, 0, arrayOfByte5.length);
    arrayOfByte6[(arrayOfByte6.length - 1)] = i;
    return arrayOfByte6;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/security/o3logon/O3LoginProtocolHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */