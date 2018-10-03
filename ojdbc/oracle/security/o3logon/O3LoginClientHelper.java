package oracle.security.o3logon;
public final class O3LoginClientHelper
{
  private b jdField_a_of_type_OracleSecurityO3logonB;
  private boolean jdField_a_of_type_Boolean;
  
  public O3LoginClientHelper()
  {
    this.jdField_a_of_type_Boolean = true;
    this.jdField_a_of_type_OracleSecurityO3logonB = new b();
  }
  
  public O3LoginClientHelper(boolean paramBoolean)
  {
    this.jdField_a_of_type_Boolean = paramBoolean;
    this.jdField_a_of_type_OracleSecurityO3logonB = new b();
  }
  
  public final byte[] getSessionKey(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte;
    return b.a(arrayOfByte = this.jdField_a_of_type_OracleSecurityO3logonB.a(paramString1, paramString2, this.jdField_a_of_type_Boolean), paramArrayOfByte);
  }
  
  public final byte[] getEPasswd(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return b.b(paramArrayOfByte1, paramArrayOfByte2);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/security/o3logon/O3LoginClientHelper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */