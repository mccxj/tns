package oracle.net.ano;
import com.sun.security.auth.module.Krb5LoginModule;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.security.auth.Subject;
import javax.security.auth.kerberos.KerberosPrincipal;
import javax.security.auth.kerberos.KerberosTicket;
import oracle.net.ns.ClientProfile;
import oracle.net.ns.NetException;
import oracle.net.ns.SQLnetDef;
import oracle.net.ns.SessionAtts;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.Checksum;
import sun.security.krb5.EncryptedData;
import sun.security.krb5.EncryptionKey;
import sun.security.krb5.KrbCryptoException;
import sun.security.krb5.Realm;
import sun.security.krb5.RealmException;
import sun.security.krb5.internal.APReq;
import sun.security.krb5.internal.Authenticator;
import sun.security.krb5.internal.KRBCred;
import sun.security.krb5.internal.KdcErrException;
import sun.security.krb5.internal.KrbApErrException;
import sun.security.util.DerValue;
public class AuthenticationService
  extends Service
  implements SQLnetDef, PrivilegedExceptionAction
{
  static final String[] j = { "", "RADIUS", "KERBEROS5", "TCPS" };
  private static final String[] k = { "", "RADIUS", "KERBEROS5", "tcps" };
  private static final byte[] l = { 0, 1, 1, 2 };
  private static Method m = k();
  private static Method n = null;
  private boolean o = false;
  private Subject p = null;
  private String q = null;
  private String r = null;
  private int s;
  
  final int a(SessionAtts paramSessionAtts)
    throws NetException
  {
    super.a(paramSessionAtts);
    this.h = 1;
    this.s = 64767;
    String[] arrayOfString;
    a(arrayOfString = paramSessionAtts.profile.getAuthenticationServices(), j);
    this.f = new int[arrayOfString.length];
    for (int i = 0; i < this.f.length; i++) {
      this.f[i] = a(j, arrayOfString[i]);
    }
    return 1;
  }
  
  final void b()
    throws NetException, IOException
  {
    int i = 0;
    i = 3 + this.f.length * 2;
    b(i);
    this.c.c();
    this.c.a(57569);
    this.c.b(this.s);
    for (int i1 = 0; i1 < this.f.length; i1++)
    {
      this.c.a((short)l[this.f[i1]]);
      this.c.a(k[this.f[i1]]);
    }
  }
  
  final int c()
  {
    int i = 20;
    for (int i1 = 0; i1 < this.f.length; i1++)
    {
      i += 5;
      i += 4 + k[this.f[i1]].length();
    }
    return i;
  }
  
  final void a(int paramInt)
    throws NetException, IOException
  {
    this.c.j();
    int i;
    if (((i = this.c.i()) == 64255) && (paramInt > 2))
    {
      this.c.e();
      String str = this.c.k();
      this.i = ((short)a(k, str));
      if (paramInt > 4)
      {
        this.c.j();
        this.c.g();
        this.c.g();
      }
      this.o = true;
      return;
    }
    if (i == 64511)
    {
      this.o = false;
      return;
    }
    throw new NetException(323, "Authentication service received status failure");
  }
  
  public boolean isActive()
  {
    return this.o;
  }
  
  final int a()
  {
    if (isActive())
    {
      if (this.i == 1) {
        return 32;
      }
      if (this.i == 2) {
        return 37;
      }
      return 0;
    }
    return 0;
  }
  
  final void g()
    throws NetException, IOException
  {
    if (this.o)
    {
      if (this.i == 1)
      {
        b(3);
        this.c.c();
        this.c.a(2L);
        this.c.a(2L);
        return;
      }
      if (this.i == 2)
      {
        b(4);
        this.c.c();
        this.c.a(2L);
        this.c.a(2L);
        this.c.a((short)0);
      }
    }
  }
  
  final void h()
    throws NetException, IOException
  {
    if (this.o)
    {
      this.d.ano.a();
      Service.a(this.c);
      if (this.i == 1)
      {
        this.c.n();
        this.c.n();
        return;
      }
      if (this.i == 2)
      {
        String str = this.c.k();
        Object localObject1 = this.c.k();
        this.q = (str + "/" + (String)localObject1);
        Object localObject2;
        try
        {
          if ((localObject2 = InetAddress.getByName((String)localObject1).getCanonicalHostName()).toLowerCase().startsWith(((String)localObject1).toLowerCase() + ".")) {
            localObject1 = localObject2;
          }
        }
        catch (UnknownHostException localUnknownHostException)
        {
          localObject1 = ((String)localObject1).toLowerCase();
        }
        this.r = a((String)localObject1);
        if ((localObject2 = AccessController.getContext()) != null) {
          this.p = Subject.getSubject((AccessControlContext)localObject2);
        }
        if (this.p == null) {
          this.p = j();
        }
        try
        {
          Subject.doAs(this.p, this);
          return;
        }
        catch (PrivilegedActionException localPrivilegedActionException2)
        {
          PrivilegedActionException localPrivilegedActionException1;
          Exception localException = (localPrivilegedActionException1 = localPrivilegedActionException2).getException();
          NetException localNetException = null;
          if ((localException instanceof NetException)) {
            localNetException = (NetException)localException;
          } else {
            (localNetException = new NetException(323, localPrivilegedActionException1.getMessage())).initCause(localPrivilegedActionException1);
          }
          throw localNetException;
        }
      }
    }
  }
  
  private static String a(String paramString)
  {
    String str = null;
    if (n == null) {
      i();
    }
    try
    {
      str = (String)n.invoke(null, new Object[] { paramString });
    }
    catch (InvocationTargetException localInvocationTargetException) {}catch (IllegalAccessException localIllegalAccessException) {}
    return str;
  }
  
  private static void i()
  {
    try
    {
      Class localClass1 = Class.forName("sun.security.krb5.PrincipalName");
      Class localClass2 = String.class;
      Method localMethod = localClass1.getDeclaredMethod("mapHostToRealm", new Class[] { localClass2 });
      AccessController.doPrivileged(new a(localMethod));
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}catch (NoSuchMethodException localNoSuchMethodException) {}catch (PrivilegedActionException localPrivilegedActionException) {}
  }
  
  private final Subject j()
    throws NetException
  {
    Subject localSubject = new Subject();
    Krb5LoginModule localKrb5LoginModule = new Krb5LoginModule();
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2;
    (localHashMap2 = new HashMap()).put("useTicketCache", "true");
    localHashMap2.put("doNotPrompt", "true");
    String str;
    if (((str = (String)this.d.profile.get("oracle.net.kerberos5_cc_name")) != null) && (!str.equals(""))) {
      localHashMap2.put("ticketCache", str);
    }
    localKrb5LoginModule.initialize(localSubject, null, localHashMap1, localHashMap2);
    boolean bool = false;
    try
    {
      bool = localKrb5LoginModule.login();
      localKrb5LoginModule.commit();
    }
    catch (Exception localException)
    {
      NetException localNetException;
      (localNetException = new NetException(323, localException.getMessage())).initCause(localException);
      throw localNetException;
    }
    if (!bool) {
      throw new NetException(323, "Kerberos5 adaptor couldn't retrieve credentials (TGT) from the cache");
    }
    return localSubject;
  }
  
  public Object run()
    throws Exception
  {
    try
    {
      GSSManager localGSSManager = GSSManager.getInstance();
      localObject1 = new Oid("1.2.840.113554.1.2.2");
      Oid localOid = new Oid("1.2.840.113554.1.2.2.1");
      byte[] arrayOfByte1 = ((Oid)localObject1).getDER();
      KerberosPrincipal localKerberosPrincipal = null;
      Set localSet;
      if (((localObject2 = (localSet = this.p.getPrincipals()).iterator()).hasNext()) && (((localObject3 = (Principal)((Iterator)localObject2).next()) instanceof KerberosPrincipal))) {
        localKerberosPrincipal = (KerberosPrincipal)localObject3;
      }
      String str1 = Realm.parseRealmAtSeparator(localObject3 = this.q + "@" + localKerberosPrincipal.getRealm());
      Object localObject2 = this.r != null ? new Realm(this.r) : new Realm(str1);
      Object localObject3 = this.q + "@" + ((Realm)localObject2).toString();
      str1 = localKerberosPrincipal.getName();
      GSSName localGSSName1 = localGSSManager.createName((String)localObject3, localOid);
      GSSName localGSSName2 = localGSSManager.createName(str1, localOid);
      GSSCredential localGSSCredential = localGSSManager.createCredential(localGSSName2, 0, (Oid)localObject1, 1);
      GSSContext localGSSContext = localGSSManager.createContext(localGSSName1, (Oid)localObject1, localGSSCredential, 0);
      boolean bool = true;
      String str2;
      if ((str2 = (String)this.d.profile.get("oracle.net.kerberos5_mutual_authentication")) != "true") {
        bool = false;
      }
      localGSSContext.requestMutualAuth(bool);
      localGSSContext.requestConf(false);
      localGSSContext.requestInteg(false);
      localGSSContext.requestCredDeleg(true);
      byte[] arrayOfByte2 = new byte[0];
      byte[] arrayOfByte3 = new byte[(arrayOfByte2 = localGSSContext.initSecContext(arrayOfByte2, 0, 0)).length - 17];
      System.arraycopy(arrayOfByte2, 17, arrayOfByte3, 0, arrayOfByte3.length);
      InetAddress localInetAddress;
      byte[] arrayOfByte4 = (localInetAddress = InetAddress.getLocalHost()).getAddress();
      int i = 39 + arrayOfByte4.length + 4 + arrayOfByte3.length;
      this.d.ano.a(i, this.h, (short)0);
      b(4);
      this.c.a(2);
      this.c.a(4L);
      this.c.a(arrayOfByte4);
      this.c.a(arrayOfByte3);
      this.c.b();
      this.d.ano.a();
      int[] arrayOfInt = Service.a(this.c);
      this.c.e();
      byte[] arrayOfByte5;
      if (bool)
      {
        if (arrayOfInt[1] < 2) {
          throw new NetException(323, "Mutual authentication failed during Kerberos5 authentication");
        }
        arrayOfByte2 = this.c.l();
        arrayOfByte5 = new byte[arrayOfByte1.length + 2 + arrayOfByte2.length];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte5, 0, arrayOfByte1.length);
        arrayOfByte5[arrayOfByte1.length] = 2;
        arrayOfByte5[(arrayOfByte1.length + 1)] = 0;
        System.arraycopy(arrayOfByte2, 0, arrayOfByte5, arrayOfByte1.length + 2, arrayOfByte2.length);
        DerValue localDerValue;
        byte[] arrayOfByte6 = (localDerValue = new DerValue(DerValue.createTag((byte)64, true, (byte)0), arrayOfByte5)).toByteArray();
        try
        {
          localGSSContext.initSecContext(arrayOfByte6, 0, arrayOfByte6.length);
        }
        catch (GSSException localGSSException2)
        {
          NetException localNetException;
          (localNetException = new NetException(323, localGSSException2.getMessage())).initCause(localGSSException2);
          throw localNetException;
        }
        if (!localGSSContext.getMutualAuthState()) {
          throw new NetException(323, "Mutual authentication failed during Kerberos5 authentication");
        }
      }
      if (!localGSSContext.isEstablished()) {
        throw new NetException(323, "Kerberos5 adaptor couldn't create context");
      }
      if ((arrayOfByte5 = a(localGSSContext, arrayOfByte3)) == null) {
        arrayOfByte5 = new byte[0];
      }
      i = 25 + arrayOfByte5.length;
      this.d.ano.a(i, this.h, (short)0);
      b(1);
      this.c.a(arrayOfByte5);
      this.c.b();
    }
    catch (GSSException localGSSException1)
    {
      Object localObject1;
      (localObject1 = new NetException(323, localGSSException1.getMessage())).initCause(localGSSException1);
      throw ((Throwable)localObject1);
    }
    return null;
  }
  
  private final byte[] a(GSSContext paramGSSContext, byte[] paramArrayOfByte)
    throws KdcErrException, KrbApErrException, KrbCryptoException, Asn1Exception, RealmException, IOException
  {
    byte[] arrayOfByte1 = null;
    if (paramGSSContext.getCredDelegState())
    {
      Set localSet;
      Object[] arrayOfObject = (localSet = this.p.getPrivateCredentials()).toArray();
      Object localObject1 = null;
      int i = -1;
      Object localObject5;
      for (int i1 = 0; i1 < arrayOfObject.length; i1++)
      {
        localObject4 = (localObject3 = (localObject2 = (KerberosTicket)arrayOfObject[i1]).getServer()).getName();
        localObject5 = ((KerberosTicket)localObject2).getSessionKey().getEncoded();
        int i2 = ((KerberosTicket)localObject2).getSessionKeyType();
        if (!((String)localObject4).startsWith("krbtgt"))
        {
          localObject1 = localObject5;
          i = i2;
        }
      }
      APReq localAPReq = new APReq(paramArrayOfByte);
      Object localObject2 = new EncryptionKey(i, (byte[])localObject1);
      Object localObject3 = localAPReq.authenticator.decrypt((EncryptionKey)localObject2, 11);
      Object localObject4 = a(localAPReq.authenticator, new Object[] { localObject3, Boolean.valueOf(true) });
      Checksum localChecksum;
      byte[] arrayOfByte2;
      if ((arrayOfByte2 = (localChecksum = (localObject5 = new Authenticator((byte[])localObject4)).getChecksum()).getBytes()).length >= 26)
      {
        int i3;
        byte[] arrayOfByte3 = new byte[i3 = ((arrayOfByte2[27] & 0xFF) << 8) + (arrayOfByte2[26] & 0xFF)];
        System.arraycopy(arrayOfByte2, 28, arrayOfByte3, 0, i3);
        KRBCred localKRBCred1 = new KRBCred(arrayOfByte3);
        byte[] arrayOfByte4 = null;
        try
        {
          arrayOfByte4 = localKRBCred1.encPart.decrypt(EncryptionKey.NULL_KEY, 14);
        }
        catch (Exception localException)
        {
          arrayOfByte4 = localKRBCred1.encPart.decrypt((EncryptionKey)localObject2, 14);
        }
        byte[] arrayOfByte5 = a(localKRBCred1.encPart, new Object[] { arrayOfByte4, Boolean.valueOf(true) });
        EncryptedData localEncryptedData = new EncryptedData((EncryptionKey)localObject2, arrayOfByte5, 14);
        KRBCred localKRBCred2;
        arrayOfByte1 = (localKRBCred2 = new KRBCred(localKRBCred1.tickets, localEncryptedData)).asn1Encode();
      }
    }
    return arrayOfByte1;
  }
  
  private static byte[] a(EncryptedData paramEncryptedData, Object... paramVarArgs)
  {
    byte[] arrayOfByte = null;
    try
    {
      if (m.getParameterTypes().length == 1) {
        arrayOfByte = (byte[])m.invoke(paramEncryptedData, new Object[] { paramVarArgs[0] });
      } else {
        arrayOfByte = (byte[])m.invoke(paramEncryptedData, paramVarArgs);
      }
    }
    catch (InvocationTargetException localInvocationTargetException) {}catch (IllegalAccessException localIllegalAccessException) {}
    return arrayOfByte;
  }
  
  private static Method k()
  {
    localMethod = null;
    try
    {
      Class localClass = Class.forName("sun.security.krb5.EncryptedData");
      Class[] arrayOfClass = { byte[].class, Boolean.TYPE };
      try
      {
        localMethod = localClass.getDeclaredMethod("reset", arrayOfClass);
      }
      catch (NoSuchMethodException localNoSuchMethodException1)
      {
        localMethod = localClass.getDeclaredMethod("reset", new Class[] { arrayOfClass[0] });
      }
      return localMethod;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}catch (NoSuchMethodException localNoSuchMethodException2) {}
  }
  
  final void d()
    throws NetException, IOException
  {}
  
  public static final byte[] obfuscatePasswordForRadius(byte[] paramArrayOfByte)
  {
    return oracle.net.aso.a.a(paramArrayOfByte);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ano/AuthenticationService.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */