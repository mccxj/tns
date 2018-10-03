package oracle.net.ns;
public abstract interface SQLnetDef
{
  public static final boolean DEBUG = false;
  public static final boolean ASSERT = false;
  public static final int NSPTCN = 1;
  public static final int NSPTAC = 2;
  public static final int NSPTAK = 3;
  public static final int NSPTRF = 4;
  public static final int NSPTRD = 5;
  public static final int NSPTDA = 6;
  public static final int NSPTNL = 7;
  public static final int NSPTAB = 9;
  public static final int NSPTRS = 11;
  public static final int NSPTMK = 12;
  public static final int NSPTAT = 13;
  public static final int NSPTCNL = 14;
  public static final int NSPTDD = 15;
  public static final int NSPTHI = 19;
  public static final byte NSPHDLEN = 0;
  public static final byte NSPHDPSM = 2;
  public static final byte NSPHDTYP = 4;
  public static final byte NSPHDFLGS = 5;
  public static final byte NSPHDHSM = 6;
  public static final byte NSPSIZHD = 8;
  public static final byte NSPFSID = 1;
  public static final byte NSPFRDS = 2;
  public static final byte NSPFRDR = 4;
  public static final byte NO_HEADER_FLAGS = 0;
  public static final byte NSPFSRN = 8;
  public static final byte NSPCNVSN = 8;
  public static final byte NSPCNLOV = 10;
  public static final byte NSPCNOPT = 12;
  public static final byte NSPCNSDU = 14;
  public static final byte NSPCNTDU = 16;
  public static final byte NSPCNNTC = 18;
  public static final byte NSPCNTNA = 20;
  public static final byte NSPCNONE = 22;
  public static final byte NSPCNLEN = 24;
  public static final byte NSPCNOFF = 26;
  public static final byte NSPCNMXC = 28;
  public static final byte NSPCNFL0 = 32;
  public static final byte NSPCNFL1 = 33;
  public static final byte NSPCNTMO = 50;
  public static final byte NSPCNTCK = 52;
  public static final byte NSPCNADL = 54;
  public static final byte NSPCNAOF = 56;
  public static final byte NSPCNV310DAT = 58;
  public static final byte NSPCNDAT = 58;
  public static final int NSPMXCDATA = 230;
  public static final int NSINAWANTED = 1;
  public static final int NSINAINTCHG = 2;
  public static final int NSINADISABLEFORCONNECTION = 4;
  public static final int NSINANOSERVICES = 8;
  public static final int NSINAREQUIRED = 16;
  public static final int NSINAAUTHWANTED = 32;
  public static final int NSISUPSECRENEG = 128;
  public static final int NSGDONTCARE = 1;
  public static final int NSGHDX = 2;
  public static final int NSGFDX = 4;
  public static final int NSGHDRCHKSUM = 8;
  public static final int NSGPAKCHKSUM = 16;
  public static final int NSGBROKEN = 32;
  public static final int NSGUSEVIO = 64;
  public static final int NSGOSAUTHOK = 128;
  public static final int NSGSENDATTN = 512;
  public static final int NSGRECVATTN = 1024;
  public static final int NSGNOATTNPR = 2048;
  public static final int NSGRAW = 4096;
  public static final int SESSION_ID_SIZE = 16;
  public static final int PROBE_PACKET_SIZE = 26;
  public static final byte NSPACVSN = 8;
  public static final byte NSPACOPT = 10;
  public static final byte NSPACSDU = 12;
  public static final byte NSPACTDU = 14;
  public static final byte NSPACONE = 16;
  public static final byte NSPACLEN = 18;
  public static final byte NSPACOFF = 20;
  public static final byte NSPACFL0 = 22;
  public static final byte NSPACFL1 = 23;
  public static final byte NSPACTMO = 24;
  public static final byte NSPACTCK = 26;
  public static final byte NSPACADL = 28;
  public static final byte NSPACAOF = 30;
  public static final byte NSPACV310DAT = 32;
  public static final byte NSPRFURS = 8;
  public static final byte NSPRFSRS = 9;
  public static final byte NSPRFLEN = 10;
  public static final byte NSPRFDAT = 12;
  public static final byte NSPRDLEN = 8;
  public static final byte NSPRDDAT = 10;
  public static final int NSPDAFLG = 8;
  public static final int NSPDADAT = 10;
  public static final int NSPDAFZER = 0;
  public static final int NSPDAFTKN = 1;
  public static final int NSPDAFRCF = 2;
  public static final int NSPDAFCFM = 4;
  public static final int NSPDAFRSV = 8;
  public static final int NSPDAFMOR = 32;
  public static final int NSPDAFEOF = 64;
  public static final int NSPDAFIMM = 128;
  public static final int NSPDAFRTS = 256;
  public static final int NSPDAFRNT = 512;
  public static final int NSPMKTYP = 8;
  public static final int NSPMKODT = 9;
  public static final int NSPMKDAT = 10;
  public static final int NSPMKTD0 = 0;
  public static final int NSPMKTD1 = 1;
  public static final byte NIQBMARK = 1;
  public static final byte NIQRMARK = 2;
  public static final byte NIQIMARK = 3;
  public static final int NSPDDFLG = 8;
  public static final int NSPDDFMRK = 1;
  public static final int NSPDDFSSZ = 2;
  public static final int NSPDDDLN = 12;
  public static final int NSPDDCNT = 16;
  public static final int NSPDD0 = 20;
  public static final int NSPDDCNTMAX = 26;
  public static final int NSPDDSZOFSS = 2;
  public static final int NSPDDSZOFLS = 4;
  public static final int NSPDDSSLMAX = 65535;
  public static final long NSPDDLSLMAX = 4294967295L;
  public static final int NSPDDSMAXTO = 1703910;
  public static final int NSPDDSIZ = 72;
  public static final int NSPDFSDULN = 8192;
  public static final int NSPMXSDULN = 65535;
  public static final int NSPMNSDULN = 512;
  public static final int NSPDFTDULN = 32767;
  public static final int NSPMXTDULN = 65535;
  public static final int NSPMNTDULN = 255;
  public static final int NSPINSDULN = 255;
  public static final String TCP_NODELAY_STR = "TCP.NODELAY";
  public static final String TCP_CONNTIMEOUT_STR = "oracle.net.CONNECT_TIMEOUT";
  public static final String TCP_READTIMEOUT_STR = "oracle.net.READ_TIMEOUT";
  public static final String DISABLE_OOB_STR = "DISABLE_OOB";
  public static final String USE_ZERO_COPY_IO_STR = "USE_ZERO_COPY_IO";
  public static final String FORCE_DNS_LOAD_BALANCING_STR = "FORCE_DNS_LOAD_BALANCING";
  public static final String ENABLE_JAVANET_FASTPATH_STR = "ENABLE_JAVANET_FASTPATH";
  public static final String SSL_SERVER_DN_MATCH = "oracle.net.ssl_server_dn_match";
  public static final String ORACLE_NET_WALLET_LOCATION = "oracle.net.wallet_location";
  public static final String ORACLE_NET_WALLET_PASSWORD = "oracle.net.wallet_password";
  public static final String SSL_VERSION = "oracle.net.ssl_version";
  public static final String SSL_CIPHER_SUITES = "oracle.net.ssl_cipher_suites";
  public static final String JAVAX_NET_SSL_KEYSTORE = "javax.net.ssl.keyStore";
  public static final String JAVAX_NET_SSL_KEYSTORETYPE = "javax.net.ssl.keyStoreType";
  public static final String JAVAX_NET_SSL_KEYSTOREPASSWORD = "javax.net.ssl.keyStorePassword";
  public static final String JAVAX_NET_SSL_TRUSTSTORE = "javax.net.ssl.trustStore";
  public static final String JAVAX_NET_SSL_TRUSTSTORETYPE = "javax.net.ssl.trustStoreType";
  public static final String JAVAX_NET_SSL_TRUSTSTOREPASSWORD = "javax.net.ssl.trustStorePassword";
  public static final String SSL_KEYMANAGERFACTORY_ALGORITHM = "ssl.keyManagerFactory.algorithm";
  public static final String SSL_TRUSTMANAGERFACTORY_ALGORITHM = "ssl.trustManagerFactory.algorithm";
  public static final String ENABLE_SDP_STR = "oracle.net.SDP";
  public static final int TCP_NODELAY_OFF = 0;
  public static final int TCP_KEEPALIVE_OFF = 1;
  public static final int TCP_CONNTIMEOUT_OFF = 2;
  public static final int TCP_READTIMEOUT_OFF = 3;
  public static final int SSL_SERVER_DN_MATCH_OFF = 4;
  public static final int ORACLE_NET_WALLET_LOCATION_OFF = 5;
  public static final int SSL_VERSION_OFF = 6;
  public static final int SSL_CIPHER_SUITES_OFF = 7;
  public static final int JAVAX_NET_SSL_KEYSTORE_OFF = 8;
  public static final int JAVAX_NET_SSL_KEYSTORETYPE_OFF = 9;
  public static final int JAVAX_NET_SSL_KEYSTOREPASSWORD_OFF = 10;
  public static final int JAVAX_NET_SSL_TRUSTSTORE_OFF = 11;
  public static final int JAVAX_NET_SSL_TRUSTSTORETYPE_OFF = 12;
  public static final int JAVAX_NET_SSL_TRUSTSTOREPASSWORD_OFF = 13;
  public static final int SSL_KEYMANAGERFACTORY_ALGORITHM_OFF = 14;
  public static final int SSL_TRUSTMANAGERFACTORY_ALGORITHM_OFF = 15;
  public static final int ORACLE_NET_WALLET_PASSWORD_OFF = 16;
  public static final int CONNECT_RETRY_COUNT_OFF = 17;
  public static final int FORCE_DNS_LOAD_BALANCING_OFF = 18;
  public static final int ENABLE_SDP_OFF = 19;
  public static final int ORACLE_NET_NTMINOPT = 0;
  public static final int ORACLE_NET_READ_TIMEOUT = 1;
  public static final int DEFAULT_CONNECT_TIMEOUT = 60000;
  public static final int ORACLE_NET_SSL_ENCRYPTION_ENABLED = 2;
  public static final int ORACLE_NET_SSL_PEER_CERT_DN = 3;
  public static final int ORACLE_NET_SSL_PEER_CERT_CHAIN = 4;
  public static final int ORACLE_NET_SSL_CIPHER_SUITE = 5;
  public static final int ORACLE_NET_SSL_MATCH_SERVER_DN = 6;
  public static final int ORACLE_NET_SSL_FULL_DN_MATCH = 7;
  public static final int ORACLE_NET_SSL_MATCH_SERVER_DN_WITH = 8;
  public static final int ORACLE_NET_NTMAXOPT = 10;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/SQLnetDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */