package oracle.net.ns;
import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
public class Message11
  implements Message, Serializable
{
  private static final boolean DEBUG = false;
  private String msg;
  private transient ResourceBundle rBundle;
  private static final String messageFile = "oracle.net.mesg.Message";
  
  public String getMessage(int paramInt, String paramString)
  {
    try
    {
/*  64 */       this.rBundle = ResourceBundle.getBundle("oracle.net.mesg.Message");
    } catch (Exception localException) {
/*  66 */       return "Message file 'oracle.net.mesg.Message' is missing.";
    }
    
    try
    {
/*  71 */       this.msg = number2string(paramInt, paramString);
    } catch (MissingResourceException localMissingResourceException) {
/*  73 */       this.msg = "Undefined Error";
    }
    
/*  84 */     return this.msg;
  }
  
  private String number2string(int paramInt, String paramString)
    throws MissingResourceException
  {
/* 100 */     String str1 = null;
    
/* 102 */     String str2 = " " + paramString;
    
/* 107 */     if (paramInt > 12000)
    {
/* 109 */       if ((paramInt >= 12500) && (paramInt <= 12530)) {
/* 110 */         str1 = this.rBundle.getString("LISTENER_REFUSES_CONNECTION") + ":\n" + "ORA-" + paramInt + ", " + this.rBundle.getString(String.valueOf(paramInt)) + "\n" + str2;
      }
      else
      {
/* 115 */         str1 = this.rBundle.getString("ORACLE_ERROR") + " ORA-" + paramInt;
      }
/* 117 */       return str1;
    }
    
/* 120 */     switch (paramInt)
    {
    case 0: 
/* 124 */       str1 = this.rBundle.getString("GOT_MINUS_ONE") + str2;
/* 125 */       break;
    case 1: 
/* 127 */       str1 = this.rBundle.getString("ASSERTION_FAILED") + str2;
/* 128 */       break;
    
    case 20: 
/* 132 */       str1 = this.rBundle.getString("NT_CONNECTION_FAILED") + str2;
/* 133 */       break;
    case 21: 
/* 135 */       str1 = this.rBundle.getString("INVALID_NT_ADAPTER") + str2;
/* 136 */       break;
    
    case 100: 
/* 140 */       str1 = this.rBundle.getString("PROTOCOL_NOT_SPECIFIED") + str2;
/* 141 */       break;
    case 101: 
/* 143 */       str1 = this.rBundle.getString("CSTRING_PARSING") + str2;
/* 144 */       break;
    case 102: 
/* 146 */       str1 = this.rBundle.getString("INVALID_CONNECT_DATA") + str2;
/* 147 */       break;
    case 103: 
/* 149 */       str1 = this.rBundle.getString("HOSTNAME_NOT_SPECIFIED") + str2;
/* 150 */       break;
    case 104: 
/* 152 */       str1 = this.rBundle.getString("PORT_NOT_SPECIFIED") + str2;
/* 153 */       break;
    case 105: 
/* 155 */       str1 = this.rBundle.getString("CONNECT_DATA_MISSING") + str2;
/* 156 */       break;
    case 106: 
/* 158 */       str1 = this.rBundle.getString("SID_INFORMATION_MISSING") + str2;
/* 159 */       break;
    case 107: 
/* 161 */       str1 = this.rBundle.getString("ADDRESS_NOT_DEFINED") + str2;
/* 162 */       break;
    case 108: 
/* 164 */       str1 = this.rBundle.getString("JNDI_THREW_EXCEPTION") + str2;
/* 165 */       break;
    case 109: 
/* 167 */       str1 = this.rBundle.getString("JNDI_NOT_INITIALIZED") + str2;
/* 168 */       break;
    case 110: 
/* 170 */       str1 = this.rBundle.getString("JNDI_CLASSES_NOT_FOUND") + str2;
/* 171 */       break;
    case 111: 
/* 173 */       str1 = this.rBundle.getString("USER_PROPERTIES_NOT_DEFINED") + str2;
/* 174 */       break;
    case 112: 
/* 176 */       str1 = this.rBundle.getString("NAMING_FACTORY_NOT_DEFINED") + str2;
/* 177 */       break;
    case 113: 
/* 179 */       str1 = this.rBundle.getString("NAMING_PROVIDER_NOT_DEFINED") + str2;
/* 180 */       break;
    case 114: 
/* 182 */       str1 = this.rBundle.getString("PROFILE_NAME_NOT_DEFINED") + str2;
/* 183 */       break;
    case 115: 
/* 185 */       str1 = this.rBundle.getString("HOST_PORT_SID_EXPECTED") + str2;
/* 186 */       break;
    case 116: 
/* 188 */       str1 = this.rBundle.getString("PORT_NUMBER_ERROR") + str2;
/* 189 */       break;
    case 117: 
/* 191 */       str1 = this.rBundle.getString("EZ_CONNECT_FORMAT_EXPECTED") + str2;
/* 192 */       break;
    case 118: 
/* 194 */       str1 = this.rBundle.getString("EZ_CONNECT_UNKNOWN_HOST") + str2;
/* 195 */       break;
    case 121: 
/* 197 */       str1 = this.rBundle.getString("INVALID_READ_PATH") + str2;
/* 198 */       break;
    case 119: 
/* 200 */       str1 = this.rBundle.getString("TNS_ADMIN_EMPTY") + str2;
/* 201 */       break;
    case 120: 
/* 203 */       str1 = this.rBundle.getString("CONNECT_STRING_EMPTY") + str2;
/* 204 */       break;
    case 122: 
/* 206 */       str1 = this.rBundle.getString("NAMELOOKUP_FAILED") + str2;
/* 207 */       break;
    case 123: 
/* 209 */       str1 = this.rBundle.getString("NAMELOOKUP_FILE_ERROR") + str2;
/* 210 */       break;
    case 124: 
/* 212 */       str1 = this.rBundle.getString("INVALID_LDAP_URL") + str2;
/* 213 */       break;
    
    case 200: 
/* 217 */       str1 = this.rBundle.getString("NOT_CONNECTED") + str2;
/* 218 */       break;
    case 201: 
/* 220 */       str1 = this.rBundle.getString("CONNECTED_ALREADY") + str2;
/* 221 */       break;
    case 202: 
/* 223 */       str1 = this.rBundle.getString("DATA_EOF") + str2;
/* 224 */       break;
    case 203: 
/* 226 */       str1 = this.rBundle.getString("SDU_MISMATCH") + str2;
/* 227 */       break;
    case 204: 
/* 229 */       str1 = this.rBundle.getString("BAD_PKT_TYPE") + str2;
/* 230 */       break;
    case 205: 
/* 232 */       str1 = this.rBundle.getString("UNEXPECTED_PKT") + str2;
/* 233 */       break;
    case 206: 
/* 235 */       str1 = this.rBundle.getString("REFUSED_CONNECT") + str2;
/* 236 */       break;
    case 207: 
/* 238 */       str1 = this.rBundle.getString("INVALID_PKT_LENGTH") + str2;
/* 239 */       break;
    case 208: 
/* 241 */       str1 = this.rBundle.getString("CONNECTION_STRING_NULL") + str2;
/* 242 */       break;
    
    case 300: 
/* 247 */       str1 = this.rBundle.getString("FAILED_TO_TURN_ENCRYPTION_ON") + str2;
/* 248 */       break;
    case 301: 
/* 250 */       str1 = this.rBundle.getString("WRONG_BYTES_IN_NAPACKET") + str2;
/* 251 */       break;
    case 302: 
/* 253 */       str1 = this.rBundle.getString("WRONG_MAGIC_NUMBER") + str2;
/* 254 */       break;
    case 303: 
/* 256 */       str1 = this.rBundle.getString("UNKNOWN_ALGORITHM_12649") + str2;
/* 257 */       break;
    case 304: 
/* 259 */       str1 = this.rBundle.getString("INVALID_ENCRYPTION_PARAMETER") + str2;
/* 260 */       break;
    case 305: 
/* 262 */       str1 = this.rBundle.getString("WRONG_SERVICE_SUBPACKETS") + str2;
/* 263 */       break;
    case 306: 
/* 265 */       str1 = this.rBundle.getString("SUPERVISOR_STATUS_FAILURE") + str2;
/* 266 */       break;
    case 307: 
/* 268 */       str1 = this.rBundle.getString("AUTHENTICATION_STATUS_FAILURE") + str2;
/* 269 */       break;
    case 308: 
/* 271 */       str1 = this.rBundle.getString("SERVICE_CLASSES_NOT_INSTALLED") + str2;
/* 272 */       break;
    case 309: 
/* 274 */       str1 = this.rBundle.getString("INVALID_DRIVER") + str2;
/* 275 */       break;
    case 310: 
/* 277 */       str1 = this.rBundle.getString("ARRAY_HEADER_ERROR") + str2;
/* 278 */       break;
    case 311: 
/* 280 */       str1 = this.rBundle.getString("RECEIVED_UNEXPECTED_LENGTH_FOR_TYPE") + str2;
/* 281 */       break;
    case 312: 
/* 283 */       str1 = this.rBundle.getString("INVALID_NA_PACKET_TYPE_LENGTH") + str2;
/* 284 */       break;
    case 313: 
/* 286 */       str1 = this.rBundle.getString("INVALID_NA_PACKET_TYPE") + str2;
/* 287 */       break;
    case 314: 
/* 289 */       str1 = this.rBundle.getString("UNEXPECTED_NA_PACKET_TYPE_RECEIVED") + str2;
/* 290 */       break;
    case 315: 
/* 292 */       str1 = this.rBundle.getString("UNKNOWN_ENC_OR_DATAINT_ALGORITHM") + str2;
/* 293 */       break;
    case 316: 
/* 295 */       str1 = this.rBundle.getString("INVALID_ENCRYPTION_ALGORITHM_FROM_SERVER") + str2;
/* 296 */       break;
    case 317: 
/* 298 */       str1 = this.rBundle.getString("ENCRYPTION_CLASS_NOT_INSTALLED") + str2;
/* 299 */       break;
    case 318: 
/* 301 */       str1 = this.rBundle.getString("DATAINTEGRITY_CLASS_NOT_INSTALLED") + str2;
/* 302 */       break;
    case 319: 
/* 304 */       str1 = this.rBundle.getString("INVALID_DATAINTEGRITY_ALGORITHM_FROM_SERVER") + str2;
/* 305 */       break;
    case 320: 
/* 307 */       str1 = this.rBundle.getString("INVALID_SERVICES_FROM_SERVER") + str2;
/* 308 */       break;
    case 321: 
/* 310 */       str1 = this.rBundle.getString("INCOMPLETE_SERVICES_FROM_SERVER") + str2;
/* 311 */       break;
    case 322: 
/* 313 */       str1 = this.rBundle.getString("INVALID_LEVEL") + str2;
/* 314 */       break;
    case 323: 
/* 316 */       str1 = this.rBundle.getString("INVALID_SERVICE") + str2;
/* 317 */       break;
    case 324: 
/* 319 */       str1 = this.rBundle.getString("AUTHENTICATION_KERBEROS5_NO_TGT") + str2;
/* 320 */       break;
    case 325: 
/* 322 */       str1 = this.rBundle.getString("AUTHENTICATION_KERBEROS5_FAILURE") + str2;
/* 323 */       break;
    case 326: 
/* 325 */       str1 = this.rBundle.getString("AUTHENTICATION_KERBEROS5_NO_CONTEXT") + str2;
/* 326 */       break;
    case 327: 
/* 328 */       str1 = this.rBundle.getString("AUTHENTICATION_KERBEROS5_MUTUAL_AUTH_FAILED") + str2;
/* 329 */       break;
    
    case 400: 
/* 343 */       str1 = this.rBundle.getString("INVALID_SSL_VERSION") + str2;
/* 344 */       break;
    
    case 401: 
/* 347 */       str1 = this.rBundle.getString("UNSUPPORTED_SSL_PROTOCOL") + str2;
/* 348 */       break;
    
    case 403: 
/* 351 */       str1 = this.rBundle.getString("INVALID_SSL_CIPHER_SUITES") + str2;
/* 352 */       break;
    
    case 404: 
/* 355 */       str1 = this.rBundle.getString("UNSUPPORTED_CIPHER_SUITE") + str2;
/* 356 */       break;
    
    case 405: 
/* 359 */       str1 = this.rBundle.getString("MISMATCH_SERVER_CERT_DN") + str2;
/* 360 */       break;
    
    case 406: 
/* 363 */       str1 = this.rBundle.getString("DOUBLE_ENCRYPTION_NOT_ALLOWED") + str2;
/* 364 */       break;
    
    case 407: 
/* 367 */       str1 = this.rBundle.getString("UNABLE_TO_PARSE_WALLET_LOCATION") + str2;
/* 368 */       break;
    
    case 408: 
/* 371 */       str1 = this.rBundle.getString("UNABLE_TO_INIT_KEY_STORE") + str2;
/* 372 */       break;
    
    case 409: 
/* 375 */       str1 = this.rBundle.getString("UNABLE_TO_INIT_TRUST_STORE") + str2;
/* 376 */       break;
    
    case 410: 
/* 379 */       str1 = this.rBundle.getString("UNABLE_TO_INIT_SSL_CONTEXT") + str2;
/* 380 */       break;
    
    case 411: 
/* 383 */       str1 = this.rBundle.getString("SSL_UNVERIFIED_PEER") + str2;
/* 384 */       break;
    
    case 412: 
/* 387 */       str1 = this.rBundle.getString("UNSUPPORTED_METHOD_IN_WALLET_LOCATION") + str2;
      
/* 389 */       break;
    
    case 500: 
/* 395 */       str1 = this.rBundle.getString("NS_BREAK") + str2;
/* 396 */       break;
    case 501: 
/* 398 */       str1 = this.rBundle.getString("NL_EXCEPTION") + str2;
/* 399 */       break;
    case 502: 
/* 401 */       str1 = this.rBundle.getString("SO_EXCEPTION") + str2;
/* 402 */       break;
    case 503: 
/* 404 */       str1 = this.rBundle.getString("SO_CONNECTTIMEDOUT") + str2;
/* 405 */       break;
    case 504: 
/* 407 */       str1 = this.rBundle.getString("SO_READTIMEDOUT") + str2;
/* 408 */       break;
    case 505: 
/* 410 */       str1 = this.rBundle.getString("INVALID_CONNECTTIMEOUT") + str2;
/* 411 */       break;
    case 506: 
/* 413 */       str1 = this.rBundle.getString("INVALID_READTIMEOUT") + str2;
/* 414 */       break;
    default: 
/* 416 */       str1 = this.rBundle.getString("UNDEFINED_ERROR") + str2;
    }
    
/* 419 */     return str1;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/Message11.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */