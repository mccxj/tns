package oracle.security.o5logon;
import java.security.MessageDigest;
import java.security.SecureRandom;
import oracle.security.o3logon.O3LoginProtocolHelper;
public final class O5Logon
{
  private static final SecureRandom jdField_a_of_type_JavaSecuritySecureRandom = ;
  private final MessageDigest jdField_a_of_type_JavaSecurityMessageDigest = a();
  private final MessageDigest jdField_b_of_type_JavaSecurityMessageDigest = b();
  private byte[] jdField_a_of_type_ArrayOfByte = null;
  private String jdField_a_of_type_JavaLangString = null;
  private String jdField_b_of_type_JavaLangString = null;
  
  private static final SecureRandom a()
  {
    SecureRandom localSecureRandom = null;
    try
    {
      localSecureRandom = SecureRandom.getInstance("SHA1PRNG");
      byte[] arrayOfByte = new byte[32];
      localSecureRandom.nextBytes(arrayOfByte);
    }
    catch (Exception localException) {}
    return localSecureRandom;
  }
  
  private static MessageDigest a()
  {
    MessageDigest localMessageDigest = null;
    try
    {
      localMessageDigest = MessageDigest.getInstance("MD5");
    }
    catch (Exception localException) {}
    return localMessageDigest;
  }
  
  private static MessageDigest b()
  {
    MessageDigest localMessageDigest = null;
    try
    {
      localMessageDigest = MessageDigest.getInstance("SHA1");
    }
    catch (Exception localException) {}
    return localMessageDigest;
  }
  
  private final byte[] a(int paramInt1, byte[] paramArrayOfByte1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3)
  {
    byte[] arrayOfByte1 = null;
    byte[] arrayOfByte2 = null;
    int i;
    switch (paramInt1)
    {
    case 2361: 
    case 40674: 
    case 59694: 
      arrayOfByte2 = new byte[16];
      for (i = 0; i < 16; i++) {
        arrayOfByte2[i] = ((byte)(paramArrayOfByte1[(i + paramInt2)] ^ paramArrayOfByte2[(i + paramInt3)]));
      }
      this.jdField_a_of_type_JavaSecurityMessageDigest.reset();
      tmpTernaryOp = this.jdField_a_of_type_JavaSecurityMessageDigest.digest(arrayOfByte2);
      break;
    case 6949: 
    case 45394: 
      arrayOfByte2 = new byte[24];
      for (i = 0; i < 24; i++) {
        arrayOfByte2[i] = ((byte)(paramArrayOfByte1[(i + paramInt2)] ^ paramArrayOfByte2[(i + paramInt3)]));
      }
      arrayOfByte1 = new byte[24];
      this.jdField_a_of_type_JavaSecurityMessageDigest.reset();
      this.jdField_a_of_type_JavaSecurityMessageDigest.update(arrayOfByte2, 0, 16);
      byte[] arrayOfByte3;
      System.arraycopy(arrayOfByte3 = this.jdField_a_of_type_JavaSecurityMessageDigest.digest(), 0, arrayOfByte1, 0, 16);
      this.jdField_a_of_type_JavaSecurityMessageDigest.reset();
      this.jdField_a_of_type_JavaSecurityMessageDigest.update(arrayOfByte2, 16, 8);
      System.arraycopy(arrayOfByte3 = this.jdField_a_of_type_JavaSecurityMessageDigest.digest(), 0, arrayOfByte1, 16, 8);
      break;
    }
    arrayOfByte1 = new byte[0];
    return arrayOfByte1;
  }
  
  public final boolean validateServerIdentity(String paramString)
  {
    boolean bool = false;
    try
    {
      byte[] arrayOfByte = a(paramString, this.jdField_b_of_type_JavaLangString);
      String str;
      bool = (str = new String(arrayOfByte, 16, arrayOfByte.length - 16, "US-ASCII")).compareTo("SERVER_TO_CLIENT") == 0;
    }
    catch (Exception localException) {}
    return bool;
  }
  
  private final byte[] a(String paramString1, String paramString2)
    throws a
  {
    return a(this.jdField_a_of_type_ArrayOfByte, paramString1, paramString2);
  }
  
  private final byte[] a(byte[] paramArrayOfByte, String paramString1, String paramString2)
    throws a
  {
    if (paramArrayOfByte == null) {
      return new byte[0];
    }
    byte[] arrayOfByte1 = new byte[16];
    for (int i = 0; i < arrayOfByte1.length; i++) {
      arrayOfByte1[0] = 0;
    }
    byte[] arrayOfByte2 = null;
    byte[] arrayOfByte3 = a(paramString1);
    int j = paramArrayOfByte.length == 24 ? 2 : paramArrayOfByte.length == 16 ? 1 : 3;
    int k = paramString2.endsWith("PKCS5Padding") ? 2 : 0;
    b localb;
    (localb = new b(1, j, k)).a(paramArrayOfByte);
    return arrayOfByte2 = localb.a(arrayOfByte3);
  }
  
  private final byte[] a(byte[] paramArrayOfByte, String paramString)
    throws a
  {
    return a(this.jdField_a_of_type_ArrayOfByte, paramArrayOfByte, paramString);
  }
  
  private static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString)
    throws a
  {
    if (paramArrayOfByte1 == null) {
      return new byte[0];
    }
    byte[] arrayOfByte1 = new byte[16];
    for (int i = 0; i < arrayOfByte1.length; i++) {
      arrayOfByte1[0] = 0;
    }
    byte[] arrayOfByte2 = null;
    int j = paramArrayOfByte1.length == 24 ? 2 : paramArrayOfByte1.length == 16 ? 1 : 3;
    int k = paramString.endsWith("PKCS5Padding") ? 2 : 0;
    b localb;
    (localb = new b(1, j, k)).a(paramArrayOfByte1);
    return arrayOfByte2 = localb.b(paramArrayOfByte2);
  }
  
  public final void generateOAuthResponse(int paramInt, byte[] paramArrayOfByte1, String paramString1, String paramString2, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[] paramArrayOfByte5, int[] paramArrayOfInt, boolean paramBoolean, byte paramByte)
    throws Exception
  {
    byte[] arrayOfByte1 = null;
    if ((jdField_a_of_type_JavaSecuritySecureRandom == null) || (this.jdField_b_of_type_JavaSecurityMessageDigest == null) || (this.jdField_a_of_type_JavaSecurityMessageDigest == null)) {
      throw new Exception("Resource A missing.");
    }
    if (paramArrayOfInt.length != 1) {
      throw new Exception("Resource B missing.");
    }
    Object localObject1;
    if (paramInt == 2361)
    {
      this.jdField_a_of_type_JavaLangString = "AES/CBC/NoPadding";
      this.jdField_b_of_type_JavaLangString = "AES/CBC/PKCS5Padding";
      byte[] arrayOfByte2 = (localObject1 = new O3LoginProtocolHelper()).getVerifier(paramString1, paramString2, Boolean.valueOf(paramBoolean));
      arrayOfByte1 = new byte[16];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, 8);
      for (int j = 8; j < 16; j++) {
        arrayOfByte1[j] = 0;
      }
    }
    else
    {
      this.jdField_a_of_type_JavaLangString = ((paramByte & 0x2) != 0 ? "AES/CBC/NoPadding" : "AES/CBC/PKCS5Padding");
      this.jdField_b_of_type_JavaLangString = "AES/CBC/PKCS5Padding";
      this.jdField_b_of_type_JavaSecurityMessageDigest.reset();
      this.jdField_b_of_type_JavaSecurityMessageDigest.update(paramString2.getBytes("UTF-8"));
      if ((paramInt == 6949) && (paramArrayOfByte1 != null)) {
        this.jdField_b_of_type_JavaSecurityMessageDigest.update(a(new String(paramArrayOfByte1, "US-ASCII")));
      }
      localObject1 = this.jdField_b_of_type_JavaSecurityMessageDigest.digest();
      arrayOfByte1 = new byte[24];
      int i = 0;
      arrayOfByte1[i] = 0;
      System.arraycopy(localObject1, 0, arrayOfByte1, 0, localObject1.length);
      if ((paramInt == 40674) || (paramInt == 59694))
      {
        this.jdField_a_of_type_JavaLangString = "AES/CBC/NoPadding";
        this.jdField_b_of_type_JavaLangString = "AES/CBC/PKCS5Padding";
        this.jdField_a_of_type_JavaSecurityMessageDigest.reset();
        this.jdField_a_of_type_JavaSecurityMessageDigest.update(paramString2.getBytes("UTF-8"));
        if (paramInt == 59694) {
          this.jdField_a_of_type_JavaSecurityMessageDigest.update(a(new String(paramArrayOfByte1, "US-ASCII")));
        }
        arrayOfByte1 = this.jdField_a_of_type_JavaSecurityMessageDigest.digest();
      }
      else
      {
        throw ((paramInt == 6949) || (paramInt == 45394) ? this : new Exception("Resource C missing."));
      }
    }
    byte[] arrayOfByte3 = new byte[(localObject1 = a(arrayOfByte1, new String(paramArrayOfByte3, "US-ASCII"), this.jdField_a_of_type_JavaLangString)).length];
    synchronized (jdField_a_of_type_JavaSecuritySecureRandom)
    {
      jdField_a_of_type_JavaSecuritySecureRandom.nextBytes(arrayOfByte3);
    }
    ??? = a(arrayOfByte1, arrayOfByte3, this.jdField_a_of_type_JavaLangString);
    if ((paramArrayOfByte4 == null) || (paramArrayOfByte4.length != paramArrayOfByte3.length)) {
      throw new Exception("Resource D missing.");
    }
    a((byte[])???, paramArrayOfByte4);
    this.jdField_a_of_type_ArrayOfByte = a(paramInt, (byte[])localObject1, 16, arrayOfByte3, 16);
    byte[] arrayOfByte4 = new byte[16];
    synchronized (jdField_a_of_type_JavaSecuritySecureRandom)
    {
      jdField_a_of_type_JavaSecuritySecureRandom.nextBytes(arrayOfByte4);
    }
    if (paramArrayOfByte5 == null) {
      throw new Exception("Resource E missing.");
    }
    ??? = new byte[16 + paramArrayOfByte2.length];
    System.arraycopy(arrayOfByte4, 0, ???, 0, 16);
    System.arraycopy(paramArrayOfByte2, 0, ???, 16, paramArrayOfByte2.length);
    byte[] arrayOfByte5 = a((byte[])???, this.jdField_b_of_type_JavaLangString);
    paramArrayOfInt[0] = a(arrayOfByte5, paramArrayOfByte5);
  }
  
  private static byte a(byte paramByte)
  {
    return (byte)(paramByte - 10 + ((paramByte = (byte)(paramByte & 0xF)) < 10 ? 48 : 65));
  }
  
  private final int a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    for (int i = 0; i < paramArrayOfByte1.length; i++)
    {
      paramArrayOfByte2[(i * 2)] = a((byte)((paramArrayOfByte1[i] & 0xF0) >> 4));
      paramArrayOfByte2[(i * 2 + 1)] = a((byte)(paramArrayOfByte1[i] & 0xF));
    }
    return i * 2;
  }
  
  private static byte[] a(String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    for (int i = 0; i < paramString.length() / 2; i++)
    {
      int j = Byte.parseByte(paramString.substring(2 * i, 2 * i + 1), 16);
      int k;
      int m = (k = Byte.parseByte(paramString.substring(2 * i + 1, 2 * i + 2), 16)) | j << 4;
      arrayOfByte[i] = ((byte)m);
    }
    return arrayOfByte;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/security/o5logon/O5Logon.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */