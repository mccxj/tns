/*      */ package oracle.jdbc;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.sql.Array;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Connection;
/*      */ import java.sql.Date;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.EnumSet;
/*      */ import java.util.Properties;
/*      */ import java.util.TimeZone;
/*      */ import oracle.jdbc.aq.AQDequeueOptions;
/*      */ import oracle.jdbc.aq.AQEnqueueOptions;
/*      */ import oracle.jdbc.aq.AQMessage;
/*      */ import oracle.jdbc.aq.AQNotificationRegistration;
/*      */ import oracle.jdbc.dcn.DatabaseChangeRegistration;
/*      */ import oracle.jdbc.pool.OracleConnectionCacheCallback;
/*      */ import oracle.sql.ARRAY;
/*      */ import oracle.sql.BINARY_DOUBLE;
/*      */ import oracle.sql.BINARY_FLOAT;
/*      */ import oracle.sql.DATE;
/*      */ import oracle.sql.INTERVALDS;
/*      */ import oracle.sql.INTERVALYM;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.TIMESTAMP;
/*      */ import oracle.sql.TIMESTAMPLTZ;
/*      */ import oracle.sql.TIMESTAMPTZ;
/*      */ import oracle.sql.TypeDescriptor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract interface OracleConnection
/*      */   extends Connection
/*      */ {
/*      */   public static final byte ACCESSMODE_JAVAPROP = 1;
/*      */   public static final byte ACCESSMODE_SYSTEMPROP = 2;
/*      */   public static final byte ACCESSMODE_BOTH = 3;
/*      */   public static final String CONNECTION_PROPERTY_RETAIN_V9_BIND_BEHAVIOR = "oracle.jdbc.RetainV9LongBindBehavior";
/*      */   public static final String CONNECTION_PROPERTY_RETAIN_V9_BIND_BEHAVIOR_DEFAULT = "false";
/*      */   public static final byte CONNECTION_PROPERTY_RETAIN_V9_BIND_BEHAVIOR_ACCESSMODE = 3;
/*      */   public static final String CONNECTION_PROPERTY_USER_NAME = "user";
/*   82 */   public static final String CONNECTION_PROPERTY_USER_NAME_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_USER_NAME_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_DATABASE = "database";
/*      */   
/*   88 */   public static final String CONNECTION_PROPERTY_DATABASE_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_DATABASE_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_AUTOCOMMIT = "autoCommit";
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_AUTOCOMMIT_DEFAULT = "true";
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_AUTOCOMMIT_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_PROTOCOL = "protocol";
/*      */   
/*  100 */   public static final String CONNECTION_PROPERTY_PROTOCOL_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_PROTOCOL_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_STREAM_CHUNK_SIZE = "oracle.jdbc.StreamChunkSize";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_STREAM_CHUNK_SIZE_DEFAULT = "16384";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_STREAM_CHUNK_SIZE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_SET_FLOAT_AND_DOUBLE_USE_BINARY = "SetFloatAndDoubleUseBinary";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_SET_FLOAT_AND_DOUBLE_USE_BINARY_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_SET_FLOAT_AND_DOUBLE_USE_BINARY_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_TERMINAL = "v$session.terminal";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_TERMINAL_DEFAULT = "unknown";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_VSESSION_TERMINAL_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_MACHINE = "v$session.machine";
/*      */   
/*      */ 
/*  139 */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_MACHINE_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_VSESSION_MACHINE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_OSUSER = "v$session.osuser";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  172 */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_OSUSER_DEFAULT = null;
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_VSESSION_OSUSER_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_PROGRAM = "v$session.program";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_PROGRAM_DEFAULT = "JDBC Thin Client";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_VSESSION_PROGRAM_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_PROCESS = "v$session.process";
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_PROCESS_DEFAULT = "1234";
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_VSESSION_PROCESS_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_INAME = "v$session.iname";
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_INAME_DEFAULT = "jdbc_ttc_impl";
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_VSESSION_INAME_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_ENAME = "v$session.ename";
/*      */   
/*  200 */   public static final String CONNECTION_PROPERTY_THIN_VSESSION_ENAME_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_VSESSION_ENAME_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_PROFILE = "oracle.net.profile";
/*      */   
/*  206 */   public static final String CONNECTION_PROPERTY_THIN_NET_PROFILE_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_PROFILE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_AUTHENTICATION_SERVICES = "oracle.net.authentication_services";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  237 */   public static final String CONNECTION_PROPERTY_THIN_NET_AUTHENTICATION_SERVICES_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_AUTHENTICATION_SERVICES_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_AUTHENTICATION_KRB5_MUTUAL = "oracle.net.kerberos5_mutual_authentication";
/*      */   
/*  243 */   public static final String CONNECTION_PROPERTY_THIN_NET_AUTHENTICATION_KRB5_MUTUAL_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_AUTHENTICATION_KRB5_MUTUAL_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_AUTHENTICATION_KRB5_CC_NAME = "oracle.net.kerberos5_cc_name";
/*      */   
/*  249 */   public static final String CONNECTION_PROPERTY_THIN_NET_AUTHENTICATION_KRB5_CC_NAME_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_AUTHENTICATION_KRB5_CC_NAME_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_ENCRYPTION_LEVEL = "oracle.net.encryption_client";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  285 */   public static final String CONNECTION_PROPERTY_THIN_NET_ENCRYPTION_LEVEL_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_ENCRYPTION_LEVEL_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_ENCRYPTION_TYPES = "oracle.net.encryption_types_client";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  315 */   public static final String CONNECTION_PROPERTY_THIN_NET_ENCRYPTION_TYPES_DEFAULT = null;
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_ENCRYPTION_TYPES_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_CHECKSUM_LEVEL = "oracle.net.crypto_checksum_client";
/*      */   
/*  323 */   public static final String CONNECTION_PROPERTY_THIN_NET_CHECKSUM_LEVEL_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_CHECKSUM_LEVEL_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES = "oracle.net.crypto_checksum_types_client";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  343 */   public static final String CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES_DEFAULT = null;
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_CRYPTO_SEED = "oracle.net.crypto_seed";
/*      */   
/*  351 */   public static final String CONNECTION_PROPERTY_THIN_NET_CRYPTO_SEED_DEFAULT = null;
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_CRYPTO_SEED_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_TCP_NO_DELAY = "oracle.jdbc.TcpNoDelay";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_TCP_NO_DELAY_DEFAULT = "false";
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_TCP_NO_DELAY_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_READ_TIMEOUT = "oracle.jdbc.ReadTimeout";
/*      */   
/*  366 */   public static final String CONNECTION_PROPERTY_THIN_READ_TIMEOUT_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_READ_TIMEOUT_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_CONNECT_TIMEOUT = "oracle.net.CONNECT_TIMEOUT";
/*      */   
/*  372 */   public static final String CONNECTION_PROPERTY_THIN_NET_CONNECT_TIMEOUT_DEFAULT = null;
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_CONNECT_TIMEOUT_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_DISABLE_OUT_OF_BAND_BREAK = "oracle.net.disableOob";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_DISABLE_OUT_OF_BAND_BREAK_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_DISABLE_OUT_OF_BAND_BREAK_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_USE_ZERO_COPY_IO = "oracle.net.useZeroCopyIO";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_USE_ZERO_COPY_IO_DEFAULT = "true";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_USE_ZERO_COPY_IO_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_ENABLE_SDP = "oracle.net.SDP";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_ENABLE_SDP_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_ENABLE_SDP_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_USE_1900_AS_YEAR_FOR_TIME = "oracle.jdbc.use1900AsYearForTime";
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_USE_1900_AS_YEAR_FOR_TIME_DEFAULT = "false";
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_USE_1900_AS_YEAR_FOR_TIME_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_TIMESTAMPTZ_IN_GMT = "oracle.jdbc.timestampTzInGmt";
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_TIMESTAMPTZ_IN_GMT_DEFAULT = "true";
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_TIMESTAMPTZ_IN_GMT_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_TIMEZONE_AS_REGION = "oracle.jdbc.timezoneAsRegion";
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_TIMEZONE_AS_REGION_DEFAULT = "true";
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_TIMEZONE_AS_REGION_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_SSL_SERVER_DN_MATCH = "oracle.net.ssl_server_dn_match";
/*      */   
/*  424 */   public static final String CONNECTION_PROPERTY_THIN_SSL_SERVER_DN_MATCH_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_SSL_SERVER_DN_MATCH_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_SSL_VERSION = "oracle.net.ssl_version";
/*      */   
/*  430 */   public static final String CONNECTION_PROPERTY_THIN_SSL_VERSION_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_SSL_VERSION_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_SSL_CIPHER_SUITES = "oracle.net.ssl_cipher_suites";
/*      */   
/*  436 */   public static final String CONNECTION_PROPERTY_THIN_SSL_CIPHER_SUITES_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_SSL_CIPHER_SUITES_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTORE = "javax.net.ssl.keyStore";
/*      */   
/*  442 */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTORE_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTORE_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTORETYPE = "javax.net.ssl.keyStoreType";
/*      */   
/*  448 */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTORETYPE_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTORETYPE_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTOREPASSWORD = "javax.net.ssl.keyStorePassword";
/*      */   
/*  454 */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTOREPASSWORD_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_KEYSTOREPASSWORD_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTORE = "javax.net.ssl.trustStore";
/*      */   
/*  460 */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTORE_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTORE_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTORETYPE = "javax.net.ssl.trustStoreType";
/*      */   
/*  466 */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTORETYPE_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTORETYPE_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTOREPASSWORD = "javax.net.ssl.trustStorePassword";
/*      */   
/*  472 */   public static final String CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTOREPASSWORD_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_JAVAX_NET_SSL_TRUSTSTOREPASSWORD_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_SSL_KEYMANAGERFACTORY_ALGORITHM = "ssl.keyManagerFactory.algorithm";
/*      */   
/*  478 */   public static final String CONNECTION_PROPERTY_THIN_SSL_KEYMANAGERFACTORY_ALGORITHM_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_SSL_KEYMANAGERFACTORY_ALGORITHM_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_SSL_TRUSTMANAGERFACTORY_ALGORITHM = "ssl.trustManagerFactory.algorithm";
/*      */   
/*  484 */   public static final String CONNECTION_PROPERTY_THIN_SSL_TRUSTMANAGERFACTORY_ALGORITHM_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_SSL_TRUSTMANAGERFACTORY_ALGORITHM_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NET_OLDSYNTAX = "oracle.net.oldSyntax";
/*      */   
/*  490 */   public static final String CONNECTION_PROPERTY_THIN_NET_OLDSYNTAX_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NET_OLDSYNTAX_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NAMING_CONTEXT_INITIAL = "java.naming.factory.initial";
/*      */   
/*  496 */   public static final String CONNECTION_PROPERTY_THIN_NAMING_CONTEXT_INITIAL_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NAMING_CONTEXT_INITIAL_ACCESSMODE = 1;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NAMING_PROVIDER_URL = "java.naming.provider.url";
/*      */   
/*  502 */   public static final String CONNECTION_PROPERTY_THIN_NAMING_PROVIDER_URL_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NAMING_PROVIDER_URL_ACCESSMODE = 1;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NAMING_SECURITY_AUTHENTICATION = "java.naming.security.authentication";
/*      */   
/*  508 */   public static final String CONNECTION_PROPERTY_THIN_NAMING_SECURITY_AUTHENTICATION_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NAMING_SECURITY_AUTHENTICATION_ACCESSMODE = 1;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NAMING_SECURITY_PRINCIPAL = "java.naming.security.principal";
/*      */   
/*  514 */   public static final String CONNECTION_PROPERTY_THIN_NAMING_SECURITY_PRINCIPAL_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NAMING_SECURITY_PRINCIPAL_ACCESSMODE = 1;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_THIN_NAMING_SECURITY_CREDENTIALS = "java.naming.security.credentials";
/*      */   
/*  520 */   public static final String CONNECTION_PROPERTY_THIN_NAMING_SECURITY_CREDENTIALS_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_NAMING_SECURITY_CREDENTIALS_ACCESSMODE = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_WALLET_LOCATION = "oracle.net.wallet_location";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  548 */   public static final String CONNECTION_PROPERTY_WALLET_LOCATION_DEFAULT = null;
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_WALLET_LOCATION_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_WALLET_PASSWORD = "oracle.net.wallet_password";
/*      */   
/*  555 */   public static final String CONNECTION_PROPERTY_WALLET_PASSWORD_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_WALLET_PASSWORD_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_PROXY_CLIENT_NAME = "oracle.jdbc.proxyClientName";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  573 */   public static final String CONNECTION_PROPERTY_PROXY_CLIENT_NAME_DEFAULT = null;
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_PROXY_CLIENT_NAME_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULT_USE_NIO = "oracle.jdbc.useNio";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULT_USE_NIO_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_DEFAULT_USE_NIO_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_OCI_DRIVER_CHARSET = "JDBCDriverCharSetId";
/*      */   
/*      */ 
/*  591 */   public static final String CONNECTION_PROPERTY_OCI_DRIVER_CHARSET_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_OCI_DRIVER_CHARSET_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_EDITION_NAME = "oracle.jdbc.editionName";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  612 */   public static final String CONNECTION_PROPERTY_EDITION_NAME_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_EDITION_NAME_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_INTERNAL_LOGON = "internal_logon";
/*      */   
/*      */ 
/*  622 */   public static final String CONNECTION_PROPERTY_INTERNAL_LOGON_DEFAULT = null;
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_INTERNAL_LOGON_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_CREATE_DESCRIPTOR_USE_CURRENT_SCHEMA_FOR_SCHEMA_NAME = "oracle.jdbc.createDescriptorUseCurrentSchemaForSchemaName";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_CREATE_DESCRIPTOR_USE_CURRENT_SCHEMA_FOR_SCHEMA_NAME_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_CREATE_DESCRIPTOR_USE_CURRENT_SCHEMA_FOR_SCHEMA_NAME_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_OCI_SVC_CTX_HANDLE = "OCISvcCtxHandle";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_OCI_SVC_CTX_HANDLE_DEFAULT = "0";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_OCI_SVC_CTX_HANDLE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_OCI_ENV_HANDLE = "OCIEnvHandle";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_OCI_ENV_HANDLE_DEFAULT = "0";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_OCI_ENV_HANDLE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_OCI_ERR_HANDLE = "OCIErrHandle";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_OCI_ERR_HANDLE_DEFAULT = "0";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_OCI_ERR_HANDLE_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_PRELIM_AUTH = "prelim_auth";
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_PRELIM_AUTH_DEFAULT = "false";
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_PRELIM_AUTH_ACCESSMODE = 3;
/*      */   
/*      */   public static final String CONNECTION_PROPERTY_SET_NEW_PASSWORD = "OCINewPassword";
/*      */   
/*  671 */   public static final String CONNECTION_PROPERTY_SET_NEW_PASSWORD_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_SET_NEW_PASSWORD_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULT_EXECUTE_BATCH = "defaultExecuteBatch";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULT_EXECUTE_BATCH_DEFAULT = "1";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_DEFAULT_EXECUTE_BATCH_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH = "defaultRowPrefetch";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH_DEFAULT = "10";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULT_LOB_PREFETCH_SIZE = "oracle.jdbc.defaultLobPrefetchSize";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULT_LOB_PREFETCH_SIZE_DEFAULT = "4000";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_DEFAULT_LOB_PREFETCH_SIZE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_ENABLE_DATA_IN_LOCATOR = "oracle.jdbc.enableDataInLocator";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_ENABLE_DATA_IN_LOCATOR_DEFAULT = "true";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_ENABLE_DATA_IN_LOCATOR_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_ENABLE_READ_DATA_IN_LOCATOR = "oracle.jdbc.enableReadDataInLocator";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_ENABLE_READ_DATA_IN_LOCATOR_DEFAULT = "true";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_ENABLE_READ_DATA_IN_LOCATOR_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_REPORT_REMARKS = "remarksReporting";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_REPORT_REMARKS_DEFAULT = "false";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_REPORT_REMARKS_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_INCLUDE_SYNONYMS = "includeSynonyms";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_INCLUDE_SYNONYMS_DEFAULT = "false";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_INCLUDE_SYNONYMS_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_RESTRICT_GETTABLES = "restrictGetTables";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_RESTRICT_GETTABLES_DEFAULT = "false";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_RESTRICT_GETTABLES_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_ACCUMULATE_BATCH_RESULT = "AccumulateBatchResult";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_ACCUMULATE_BATCH_RESULT_DEFAULT = "true";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_ACCUMULATE_BATCH_RESULT_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_USE_FETCH_SIZE_WITH_LONG_COLUMN = "useFetchSizeWithLongColumn";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_USE_FETCH_SIZE_WITH_LONG_COLUMN_DEFAULT = "false";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_USE_FETCH_SIZE_WITH_LONG_COLUMN_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_PROCESS_ESCAPES = "processEscapes";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_PROCESS_ESCAPES_DEFAULT = "true";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_PROCESS_ESCAPES_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_FIXED_STRING = "fixedString";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_FIXED_STRING_DEFAULT = "false";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_FIXED_STRING_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULTNCHAR = "defaultNChar";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DEFAULTNCHAR_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_DEFAULTNCHAR_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_RESOURCE_MANAGER_ID = "RessourceManagerId";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_RESOURCE_MANAGER_ID_DEFAULT = "0000";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_RESOURCE_MANAGER_ID_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DISABLE_DEFINECOLUMNTYPE = "disableDefineColumnType";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DISABLE_DEFINECOLUMNTYPE_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_DISABLE_DEFINECOLUMNTYPE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_CONVERT_NCHAR_LITERALS = "oracle.jdbc.convertNcharLiterals";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_CONVERT_NCHAR_LITERALS_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_CONVERT_NCHAR_LITERALS_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_J2EE13_COMPLIANT = "oracle.jdbc.J2EE13Compliant";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_J2EE13_COMPLIANT_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_J2EE13_COMPLIANT_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_MAP_DATE_TO_TIMESTAMP = "oracle.jdbc.mapDateToTimestamp";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_MAP_DATE_TO_TIMESTAMP_DEFAULT = "true";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_MAP_DATE_TO_TIMESTAMP_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_USE_THREADLOCAL_BUFFER_CACHE = "oracle.jdbc.useThreadLocalBufferCache";
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_USE_THREADLOCAL_BUFFER_CACHE_DEFAULT = "false";
/*      */   
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_USE_THREADLOCAL_BUFFER_CACHE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_DRIVER_NAME_ATTRIBUTE = "oracle.jdbc.driverNameAttribute";
/*      */   
/*      */ 
/*  888 */   public static final String CONNECTION_PROPERTY_DRIVER_NAME_ATTRIBUTE_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_DRIVER_NAME_ATTRIBUTE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_MAX_CACHED_BUFFER_SIZE = "oracle.jdbc.maxCachedBufferSize";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_MAX_CACHED_BUFFER_SIZE_DEFAULT = "30";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_MAX_CACHED_BUFFER_SIZE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_IMPLICIT_STATEMENT_CACHE_SIZE = "oracle.jdbc.implicitStatementCacheSize";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_IMPLICIT_STATEMENT_CACHE_SIZE_DEFAULT = "0";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_IMPLICIT_STATEMENT_CACHE_SIZE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_LOB_STREAM_POS_STANDARD_COMPLIANT = "oracle.jdbc.LobStreamPosStandardCompliant";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_LOB_STREAM_POS_STANDARD_COMPLIANT_DEFAULT = "false";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_LOB_STREAM_POS_STANDARD_COMPLIANT_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_STRICT_ASCII_CONVERSION = "oracle.jdbc.strictASCIIConversion";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_STRICT_ASCII_CONVERSION_DEFAULT = "false";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_STRICT_ASCII_CONVERSION_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_FORCE_DNS_LOAD_BALANCING = "oracle.jdbc.thinForceDNSLoadBalancing";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_THIN_FORCE_DNS_LOAD_BALANCING_DEFAULT = "false";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_THIN_FORCE_DNS_LOAD_BALANCING_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_ENABLE_TEMP_LOB_REF_COUNT = "oracle.jdbc.enableTempLobRefCnt";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_ENABLE_TEMP_LOB_REF_COUNT_DEFAULT = "true";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_ENABLE_TEMP_LOB_REF_COUNT_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_NET_KEEPALIVE = "oracle.net.keepAlive";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_NET_KEEPALIVE_DEFAULT = "false";
/*      */   
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_NET_KEEPALIVE_ACCESSMODE = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_PASSWORD = "password";
/*      */   
/*      */ 
/*      */ 
/* 1004 */   public static final String CONNECTION_PROPERTY_PASSWORD_DEFAULT = null;
/*      */   public static final byte CONNECTION_PROPERTY_PASSWORD_ACCESSMODE = 1;
/*      */   public static final String CONNECTION_PROPERTY_SERVER = "server";
/* 1007 */   public static final String CONNECTION_PROPERTY_SERVER_DEFAULT = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte CONNECTION_PROPERTY_SERVER_ACCESSMODE = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String CONNECTION_PROPERTY_COMMIT_OPTION = "oracle.jdbc.commitOption";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1029 */   public static final String CONNECTION_PROPERTY_COMMIT_OPTION_DEFAULT = null;
/*      */   
/*      */   public static final byte CONNECTION_PROPERTY_COMMIT_OPTION_ACCESSMODE = 3;
/*      */   
/*      */   public static final int DATABASE_OK = 0;
/*      */   
/*      */   public static final int DATABASE_CLOSED = -1;
/*      */   public static final int DATABASE_NOTOK = -2;
/*      */   public static final int DATABASE_TIMEOUT = -3;
/*      */   public static final int INVALID_CONNECTION = 4096;
/*      */   public static final int PROXY_SESSION = 1;
/*      */   public static final int ABANDONED_CONNECTION_CALLBACK = 1;
/*      */   public static final int RELEASE_CONNECTION_CALLBACK = 2;
/*      */   public static final int ALL_CONNECTION_CALLBACKS = 4;
/*      */   public static final int CONNECTION_RELEASE_LOCKED = 256;
/*      */   public static final int CONNECTION_RELEASE_LOW = 512;
/*      */   public static final int CONNECTION_RELEASE_HIGH = 1024;
/*      */   public static final int PROXYTYPE_USER_NAME = 1;
/*      */   public static final int PROXYTYPE_DISTINGUISHED_NAME = 2;
/*      */   public static final int PROXYTYPE_CERTIFICATE = 3;
/*      */   public static final String PROXY_USER_NAME = "PROXY_USER_NAME";
/*      */   public static final String PROXY_USER_PASSWORD = "PROXY_USER_PASSWORD";
/*      */   public static final String PROXY_DISTINGUISHED_NAME = "PROXY_DISTINGUISHED_NAME";
/*      */   public static final String PROXY_CERTIFICATE = "PROXY_CERTIFICATE";
/*      */   public static final String PROXY_ROLES = "PROXY_ROLES";
/*      */   public static final int END_TO_END_ACTION_INDEX = 0;
/*      */   public static final int END_TO_END_CLIENTID_INDEX = 1;
/*      */   public static final int END_TO_END_ECID_INDEX = 2;
/*      */   public static final int END_TO_END_MODULE_INDEX = 3;
/*      */   public static final int END_TO_END_STATE_INDEX_MAX = 4;
/*      */   public static final int CACHE_SIZE_NOT_SET = -1;
/*      */   public static final String NTF_TIMEOUT = "NTF_TIMEOUT";
/*      */   public static final String NTF_QOS_PURGE_ON_NTFN = "NTF_QOS_PURGE_ON_NTFN";
/*      */   public static final String NTF_QOS_RELIABLE = "NTF_QOS_RELIABLE";
/*      */   public static final String NTF_AQ_PAYLOAD = "NTF_AQ_PAYLOAD";
/*      */   public static final String NTF_LOCAL_TCP_PORT = "NTF_LOCAL_TCP_PORT";
/*      */   public static final int NTF_DEFAULT_TCP_PORT = 47632;
/*      */   public static final String NTF_LOCAL_HOST = "NTF_LOCAL_HOST";
/*      */   public static final String NTF_GROUPING_CLASS = "NTF_GROUPING_CLASS";
/*      */   public static final String NTF_GROUPING_CLASS_NONE = "NTF_GROUPING_CLASS_NONE";
/*      */   public static final String NTF_GROUPING_CLASS_TIME = "NTF_GROUPING_CLASS_TIME";
/*      */   public static final String NTF_GROUPING_VALUE = "NTF_GROUPING_VALUE";
/*      */   public static final String NTF_GROUPING_TYPE = "NTF_GROUPING_TYPE";
/*      */   public static final String NTF_GROUPING_TYPE_SUMMARY = "NTF_GROUPING_TYPE_SUMMARY";
/*      */   public static final String NTF_GROUPING_TYPE_LAST = "NTF_GROUPING_TYPE_LAST";
/*      */   public static final String NTF_GROUPING_START_TIME = "NTF_GROUPING_START_TIME";
/*      */   public static final String NTF_GROUPING_REPEAT_TIME = "NTF_GROUPING_REPEAT_TIME";
/*      */   public static final String NTF_GROUPING_REPEAT_FOREVER = "NTF_GROUPING_REPEAT_FOREVER";
/*      */   public static final String DCN_NOTIFY_ROWIDS = "DCN_NOTIFY_ROWIDS";
/*      */   public static final String DCN_IGNORE_INSERTOP = "DCN_IGNORE_INSERTOP";
/*      */   public static final String DCN_IGNORE_UPDATEOP = "DCN_IGNORE_UPDATEOP";
/*      */   public static final String DCN_IGNORE_DELETEOP = "DCN_IGNORE_DELETEOP";
/*      */   public static final String DCN_NOTIFY_CHANGELAG = "DCN_NOTIFY_CHANGELAG";
/*      */   public static final String DCN_QUERY_CHANGE_NOTIFICATION = "DCN_QUERY_CHANGE_NOTIFICATION";
/*      */   public static final String DCN_BEST_EFFORT = "DCN_BEST_EFFORT";
/*      */   
/*      */   public abstract void commit(EnumSet<CommitOption> paramEnumSet)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void archive(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void openProxySession(int paramInt, Properties paramProperties)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract boolean getAutoClose()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract int getDefaultExecuteBatch();
/*      */   
/*      */   public abstract int getDefaultRowPrefetch();
/*      */   
/*      */   public abstract Object getDescriptor(String paramString);
/*      */   
/*      */   public abstract String[] getEndToEndMetrics()
/*      */     throws SQLException;
/*      */   
/*      */   public static enum DatabaseShutdownMode
/*      */   {
/* 1111 */     CONNECT(0), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1116 */     TRANSACTIONAL(1), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1121 */     TRANSACTIONAL_LOCAL(2), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1127 */     IMMEDIATE(3), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1136 */     ABORT(4), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1141 */     FINAL(5);
/*      */     
/*      */     private final int mode;
/*      */     
/*      */     private DatabaseShutdownMode(int paramInt) {
/* 1146 */       this.mode = paramInt;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1153 */     public final int getMode() { return this.mode; }
/*      */   }
/*      */   
/*      */   public abstract short getEndToEndECIDSequenceNumber() throws SQLException;
/*      */   
/*      */   public abstract boolean getIncludeSynonyms();
/*      */   
/*      */   public static enum DatabaseStartupMode {
/* 1161 */     NO_RESTRICTION(0), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1166 */     FORCE(1), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1172 */     RESTRICT(2);
/*      */     
/*      */     private final int mode;
/*      */     
/*      */     private DatabaseStartupMode(int paramInt) {
/* 1177 */       this.mode = paramInt;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1184 */     public final int getMode() { return this.mode; } }
/*      */   
/*      */   public abstract boolean getRestrictGetTables();
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract Object getJavaObject(String paramString) throws SQLException;
/*      */   
/*      */   public abstract boolean getRemarksReporting();
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract String getSQLType(Object paramObject) throws SQLException;
/*      */   
/* 1200 */   public static enum CommitOption { WRITEBATCH(1), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1207 */     WRITEIMMED(2), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1214 */     WAIT(4), 
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1227 */     NOWAIT(8);
/*      */     
/*      */     private final int code;
/*      */     
/* 1231 */     private CommitOption(int paramInt) { this.code = paramInt; }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     public final int getCode()
/*      */     {
/* 1238 */       return this.code;
/*      */     }
/*      */   }
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract int getStmtCacheSize();
/*      */   
/*      */   public abstract short getStructAttrCsId()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract String getUserName()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract String getCurrentSchema()
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract boolean getUsingXAFlag();
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract boolean getXAErrorFlag();
/*      */   
/*      */   public abstract int pingDatabase()
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract int pingDatabase(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void putDescriptor(String paramString, Object paramObject)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void registerSQLType(String paramString, Class paramClass)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void registerSQLType(String paramString1, String paramString2)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setAutoClose(boolean paramBoolean)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setDefaultExecuteBatch(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setDefaultRowPrefetch(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setEndToEndMetrics(String[] paramArrayOfString, short paramShort)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setIncludeSynonyms(boolean paramBoolean);
/*      */   
/*      */   public abstract void setRemarksReporting(boolean paramBoolean);
/*      */   
/*      */   public abstract void setRestrictGetTables(boolean paramBoolean);
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void setStmtCacheSize(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void setStmtCacheSize(int paramInt, boolean paramBoolean)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setStatementCacheSize(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract int getStatementCacheSize()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setImplicitCachingEnabled(boolean paramBoolean)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract boolean getImplicitCachingEnabled()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setExplicitCachingEnabled(boolean paramBoolean)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract boolean getExplicitCachingEnabled()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void purgeImplicitCache()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void purgeExplicitCache()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract PreparedStatement getStatementWithKey(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract CallableStatement getCallWithKey(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void setUsingXAFlag(boolean paramBoolean);
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void setXAErrorFlag(boolean paramBoolean);
/*      */   
/*      */   public abstract void shutdown(DatabaseShutdownMode paramDatabaseShutdownMode)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void startup(String paramString, int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void startup(DatabaseStartupMode paramDatabaseStartupMode)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract PreparedStatement prepareStatementWithKey(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract CallableStatement prepareCallWithKey(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setCreateStatementAsRefCursor(boolean paramBoolean);
/*      */   
/*      */   public abstract boolean getCreateStatementAsRefCursor();
/*      */   
/*      */   public abstract void setSessionTimeZone(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract String getSessionTimeZone();
/*      */   
/*      */   public abstract String getSessionTimeZoneOffset()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract Properties getProperties();
/*      */   
/*      */   public abstract Connection _getPC();
/*      */   
/*      */   public abstract boolean isLogicalConnection();
/*      */   
/*      */   public abstract void registerTAFCallback(OracleOCIFailover paramOracleOCIFailover, Object paramObject)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract OracleConnection unwrap();
/*      */   
/*      */   public abstract void setWrapper(OracleConnection paramOracleConnection);
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract oracle.jdbc.internal.OracleConnection physicalConnectionWithin();
/*      */   
/*      */   public abstract OracleSavepoint oracleSetSavepoint()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract OracleSavepoint oracleSetSavepoint(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void oracleRollback(OracleSavepoint paramOracleSavepoint)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void oracleReleaseSavepoint(OracleSavepoint paramOracleSavepoint)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void close(Properties paramProperties)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void close(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract boolean isProxySession();
/*      */   
/*      */   public abstract void applyConnectionAttributes(Properties paramProperties)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract Properties getConnectionAttributes()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract Properties getUnMatchedConnectionAttributes()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void registerConnectionCacheCallback(OracleConnectionCacheCallback paramOracleConnectionCacheCallback, Object paramObject, int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setConnectionReleasePriority(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract int getConnectionReleasePriority()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setPlsqlWarnings(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract AQNotificationRegistration[] registerAQNotification(String[] paramArrayOfString, Properties[] paramArrayOfProperties, Properties paramProperties)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void unregisterAQNotification(AQNotificationRegistration paramAQNotificationRegistration)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract AQMessage dequeue(String paramString, AQDequeueOptions paramAQDequeueOptions, byte[] paramArrayOfByte)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract AQMessage dequeue(String paramString1, AQDequeueOptions paramAQDequeueOptions, String paramString2)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void enqueue(String paramString, AQEnqueueOptions paramAQEnqueueOptions, AQMessage paramAQMessage)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract DatabaseChangeRegistration registerDatabaseChangeNotification(Properties paramProperties)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract DatabaseChangeRegistration getDatabaseChangeRegistration(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void unregisterDatabaseChangeNotification(DatabaseChangeRegistration paramDatabaseChangeRegistration)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void unregisterDatabaseChangeNotification(int paramInt1, String paramString, int paramInt2)
/*      */     throws SQLException;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public abstract void unregisterDatabaseChangeNotification(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void unregisterDatabaseChangeNotification(long paramLong, String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract ARRAY createARRAY(String paramString, Object paramObject)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract Array createOracleArray(String paramString, Object paramObject)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract BINARY_DOUBLE createBINARY_DOUBLE(double paramDouble)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract BINARY_FLOAT createBINARY_FLOAT(float paramFloat)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract DATE createDATE(Date paramDate)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract DATE createDATE(Time paramTime)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract DATE createDATE(Timestamp paramTimestamp)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract DATE createDATE(Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract DATE createDATE(Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract DATE createDATE(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract DATE createDATE(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract INTERVALDS createINTERVALDS(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract INTERVALYM createINTERVALYM(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(boolean paramBoolean)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(byte paramByte)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(short paramShort)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(long paramLong)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(float paramFloat)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(double paramDouble)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(BigDecimal paramBigDecimal)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(BigInteger paramBigInteger)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract NUMBER createNUMBER(String paramString, int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMP createTIMESTAMP(Date paramDate)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMP createTIMESTAMP(DATE paramDATE)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMP createTIMESTAMP(Time paramTime)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMP createTIMESTAMP(Timestamp paramTimestamp)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMP createTIMESTAMP(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ createTIMESTAMPTZ(Date paramDate)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ createTIMESTAMPTZ(Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ createTIMESTAMPTZ(Time paramTime)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ createTIMESTAMPTZ(Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ createTIMESTAMPTZ(Timestamp paramTimestamp)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ createTIMESTAMPTZ(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ createTIMESTAMPTZ(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ createTIMESTAMPTZ(String paramString, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPTZ createTIMESTAMPTZ(DATE paramDATE)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPLTZ createTIMESTAMPLTZ(Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPLTZ createTIMESTAMPLTZ(Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPLTZ createTIMESTAMPLTZ(Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPLTZ createTIMESTAMPLTZ(String paramString, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TIMESTAMPLTZ createTIMESTAMPLTZ(DATE paramDATE, Calendar paramCalendar)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void cancel()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void abort()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TypeDescriptor[] getAllTypeDescriptorsInCurrentSchema()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TypeDescriptor[] getTypeDescriptorsFromListInCurrentSchema(String[] paramArrayOfString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TypeDescriptor[] getTypeDescriptorsFromList(String[][] paramArrayOfString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract String getDataIntegrityAlgorithmName()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract String getEncryptionAlgorithmName()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract String getAuthenticationAdaptorName()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract boolean isUsable();
/*      */   
/*      */   public abstract void setDefaultTimeZone(TimeZone paramTimeZone)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract TimeZone getDefaultTimeZone()
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void setApplicationContext(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract void clearAllApplicationContext(String paramString)
/*      */     throws SQLException;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */