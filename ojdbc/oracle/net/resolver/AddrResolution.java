package oracle.net.resolver;
import java.io.IOException;
import java.net.SocketException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Pattern;
import oracle.net.jdbc.TNSAddress.SOException;
import oracle.net.jdbc.nl.NLException;
import oracle.net.jdbc.nl.NVFactory;
import oracle.net.jdbc.nl.NVNavigator;
import oracle.net.jdbc.nl.NVPair;
import oracle.net.jndi.JndiAttrs;
import oracle.net.ns.NetException;
import oracle.net.nt.ConnOption;
import oracle.net.nt.ConnStrategy;
public class AddrResolution
{
  private ConnStrategy cs;
  private Properties up;
  private static final String default_proxy_rules = "__jdbc__";
  private static final String service_alias_name = "ora-net-service-alias";
  private static final String service_attr_name = "orclnetdescstring";
  private static final int length_of_alias_prefix = 6;
  public static final int DEFAULT_DATABASE_PORT = 1521;
  public static final String DEFAULT_CONNECT_PROTOCOL = "TCP";
/* 121 */   private boolean newSyntax = true;
/* 122 */   public boolean connection_revised = false;
/* 123 */   public boolean connection_redirected = false;
  
  private String TNSAddress;
  
  public AddrResolution(String paramString, Properties paramProperties)
    throws NetException
  {
/* 165 */     this.up = paramProperties;
/* 166 */     this.TNSAddress = paramString;
    
/* 168 */     if ((this.up.containsKey("java.naming.provider.url")) || (paramString.startsWith("ldap:")) || (paramString.startsWith("ldaps:")))
    {
/* 172 */       int i = 0;
/* 173 */       Vector localVector1 = null;
      
/* 175 */       if ((paramString.startsWith("ldap:")) || (paramString.startsWith("ldaps:")))
      {
/* 178 */         localVector1 = computeLdapList(paramString);
/* 179 */         if (localVector1.size() > 1)
        {
/* 181 */           i = 1;
        }
        else
        {
          int j;
/* 186 */           if ((j = paramString.lastIndexOf('/')) == -1) {
/* 187 */             throw new NetException(124);
          }
/* 189 */           this.up.put("java.naming.provider.url", paramString.substring(0, j));
/* 190 */           this.TNSAddress = paramString.substring(j + 1, paramString.length());
        }
      }
      
/* 194 */       if (i == 0)
      {
/* 196 */         String[] arrayOfString = new String[1];
        
/* 200 */         JndiAttrs localJndiAttrs = new JndiAttrs(this.up);
/* 201 */         arrayOfString[0] = "orclnetdescstring";
/* 202 */         Vector localVector2 = null;
        try
        {
/* 205 */           localVector2 = localJndiAttrs.getAttrs(this.TNSAddress, arrayOfString);
        }
        finally
        {
/* 209 */           localJndiAttrs.close();
        }
/* 211 */         this.TNSAddress = ((String)localVector2.firstElement());
/* 212 */         this.connection_revised = true;
      }
      else
      {
/* 218 */         processLdapFailoverLoadblance(localVector1);
      }
    }
    
/* 222 */     String str = this.up.getProperty("oracle.net.oldSyntax");
/* 223 */     if ((str != null) && (
/* 224 */       (str.equalsIgnoreCase("ON")) || (str.equalsIgnoreCase("TRUE")) || (str.equalsIgnoreCase("YES"))))
    {
/* 227 */       this.newSyntax = false;
    }
  }
  
/* 233 */   private static final Pattern pattern = Pattern.compile("(?=ldaps?://)");
  
  private Vector<String> computeLdapList(String paramString)
  {
/* 245 */     String[] arrayOfString = pattern.split(paramString);
    
/* 247 */     int i = 0;
/* 248 */     for (int j = 0; j < arrayOfString.length; j++)
    {
/* 250 */       arrayOfString[j] = arrayOfString[j].trim();
/* 251 */       if (arrayOfString[j].length() != 0) {
/* 252 */         i++;
      }
    }
/* 255 */     Vector localVector = new Vector(i);
/* 256 */     for (int k = 0; k < arrayOfString.length; k++) {
/* 257 */       if (arrayOfString[k].length() != 0)
/* 258 */         localVector.add(arrayOfString[k]);
    }
/* 260 */     return localVector;
  }
  
  private void processLdapFailoverLoadblance(Vector<String> paramVector)
    throws NetException
  {
/* 266 */     int i = 0;
    
/* 270 */     if (paramVector.size() <= 1) {
/* 271 */       throw new NetException(124);
    }
    
/* 274 */     boolean bool1 = true;
/* 275 */     boolean bool2 = true;
    
    String str2;
/* 278 */     if ((str2 = this.up.getProperty("oracle.net.ldap_failover")) != null)
    {
/* 280 */       if ((str2.equalsIgnoreCase("OFF")) || (str2.equalsIgnoreCase("FALSE")) || (str2.equalsIgnoreCase("NO")))
      {
/* 283 */         bool1 = false;
      }
    }
/* 286 */     if ((str2 = this.up.getProperty("oracle.net.ldap_loadbalance")) != null)
    {
/* 288 */       if ((str2.equalsIgnoreCase("OFF")) || (str2.equalsIgnoreCase("FALSE")) || (str2.equalsIgnoreCase("NO")))
      {
/* 291 */         bool2 = false;
      }
    }
/* 294 */     if (paramVector.size() > 1)
    {
/* 297 */       paramVector = NavDescriptionList.setActiveChildren(paramVector, bool1, bool2);
    }
    
/* 302 */     StringBuffer localStringBuffer = new StringBuffer();
    
/* 304 */     int j = paramVector.size();
    
/* 307 */     Hashtable localHashtable = new Hashtable(j);
    
/* 309 */     for (int k = 0; k < j; k++)
    {
/* 315 */       String str1 = (String)paramVector.elementAt(k);
/* 316 */       int m; if ((m = str1.lastIndexOf('/')) == -1) {
/* 317 */         throw new NetException(124);
      }
/* 319 */       str4 = str1.substring(0, m);
/* 320 */       localObject1 = str1.substring(m + 1, str1.length());
      
/* 322 */       localStringBuffer.append(str4);
/* 323 */       if (k < j - 1) { localStringBuffer.append(' ');
      }
      
/* 329 */       localHashtable.put(str4.substring(str4.indexOf('/')), localObject1);
    }
    
/* 332 */     String str3 = new String(localStringBuffer);
    
/* 336 */     this.up.put("java.naming.provider.url", str3);
/* 337 */     JndiAttrs localJndiAttrs = new JndiAttrs(this.up);
    
/* 340 */     String str4 = localJndiAttrs.getLdapUrlUsed();
/* 341 */     this.TNSAddress = ((String)localHashtable.get(str4.substring(str4.indexOf('/'))));
    
/* 344 */     Object localObject1 = null;
/* 345 */     String[] arrayOfString = new String[1];
/* 346 */     arrayOfString[0] = "orclnetdescstring";
    
    try
    {
/* 350 */       localObject1 = localJndiAttrs.getAttrs(this.TNSAddress, arrayOfString);
    }
    finally
    {
/* 354 */       localJndiAttrs.close();
    }
    
/* 357 */     this.TNSAddress = ((String)((Vector)localObject1).firstElement());
/* 358 */     this.connection_revised = true;
  }
  
  public String getTNSAddress()
  {
/* 367 */     return this.TNSAddress.toUpperCase();
  }
  
  public ConnOption resolveAndExecute(String paramString)
    throws NetException, IOException
  {
/* 384 */     ConnStrategy localConnStrategy = this.cs;
    
/* 386 */     if (paramString != null) {
/* 387 */       this.cs = new ConnStrategy(this.up);
      
/* 389 */       if (this.connection_redirected) {
/* 390 */         this.cs.sdu = localConnStrategy.sdu;
/* 391 */         this.cs.tdu = localConnStrategy.tdu;
/* 392 */         this.cs.retryCount = localConnStrategy.retryCount;
        
/* 394 */         this.cs.socketOptions = localConnStrategy.socketOptions;
/* 395 */         this.cs.reuseOpt = true;
      }
      
/* 398 */       if (paramString.indexOf(')') == -1)
      {
/* 412 */         paramString = paramString.trim();
/* 413 */         if ((paramString.startsWith("//")) || (paramString.matches("[\\w]*")) || (paramString.matches("[\\[[\\w:]*\\]]")) || (paramString.matches("[[\\w-]\\.]*:[\\d]*/[[\\w\\$\\#]\\.]*(?i)(:pooled)?(?-i)")))
        {
/* 422 */           String str = System.getProperty("oracle.net.tns_admin");
/* 423 */           NameResolver localNameResolver = NameResolverFactory.getNameResolver(str, this.cs.getOSUsername(), this.cs.getProgramName());
          
/* 429 */           paramString = paramString.replaceAll("#", "\\\\#");
          
/* 431 */           this.TNSAddress = localNameResolver.resolveName(paramString);
/* 432 */           resolveAddrTree(this.TNSAddress);
        }
        else
        {
/* 437 */           resolveSimple(paramString);
        }
        
      }
/* 442 */       else if (this.newSyntax) {
/* 443 */         resolveAddrTree(paramString);
      }
      else {
/* 446 */         resolveAddr(paramString);
      }
      
    }
/* 451 */     else if ((this.cs == null) || (!this.cs.hasMoreOptions())) { return null;
    }
    
/* 454 */     return this.cs.execute();
  }
  
  private void resolveSimple(String paramString)
    throws NetException
  {
/* 477 */     ConnOption localConnOption = new ConnOption();
/* 478 */     int i = 0;
/* 479 */     int j = 0;
/* 480 */     int k = 0;
    
/* 484 */     int m = 0;
/* 485 */     int n = 0;
/* 486 */     if (paramString.startsWith("["))
    {
/* 488 */       m = paramString.indexOf(']');
/* 489 */       if (m == -1)
/* 490 */         throw new NetException(115);
/* 491 */       n = 1;
    }
    
/* 495 */     if (((i = paramString.indexOf(':', m)) == -1) || ((j = paramString.indexOf(':', i + 1)) == -1))
    {
/* 498 */       throw new NetException(115);
    }
    
/* 502 */     if ((k = paramString.indexOf(':', j + 1)) != -1)
    {
/* 504 */       throw new NetException(115);
    }
    
    try
    {
/* 511 */       if (n != 0) {
/* 512 */         localConnOption.host = paramString.substring(1, i - 1);
      } else
/* 514 */         localConnOption.host = paramString.substring(0, i);
/* 515 */       localConnOption.port = Integer.parseInt(paramString.substring(i + 1, j));
/* 516 */       localConnOption.addr = ("(ADDRESS=(PROTOCOL=tcp)(HOST=" + localConnOption.host + ")(PORT=" + localConnOption.port + "))");
      
/* 519 */       localConnOption.sid = paramString.substring(j + 1, paramString.length());
/* 520 */       String str = "(DESCRIPTION=(CONNECT_DATA=(SID=" + localConnOption.sid + ")(CID=(PROGRAM=" + this.cs.getProgramName() + ")(HOST=__jdbc__)(USER=" + this.cs.getOSUsername() + ")))" + "(ADDRESS=" + "(PROTOCOL=tcp)(HOST=" + localConnOption.host + ")(PORT=" + localConnOption.port + ")))";
      
/* 526 */       localConnOption.protocol = "TCP";
/* 527 */       localConnOption.conn_data = new StringBuffer(str);
/* 528 */       this.cs.addOption(localConnOption);
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 532 */       throw new NetException(116);
    }
  }
  
  private void resolveAddr(String paramString)
    throws NetException
  {
/* 543 */     if (paramString.startsWith("alias=")) {
/* 544 */       localObject1 = paramString;
/* 545 */       paramString = ((String)localObject1).substring(((String)localObject1).indexOf("alias=") + 6, ((String)localObject1).length());
    }
    
/* 549 */     Object localObject1 = new ConnOption();
/* 550 */     NVFactory localNVFactory = new NVFactory();
/* 551 */     NVNavigator localNVNavigator = new NVNavigator();
/* 552 */     NVPair localNVPair1 = null;
/* 553 */     NVPair localNVPair2 = null;
    
    try
    {
/* 557 */       localNVPair1 = localNVNavigator.findNVPairRecurse(localNVFactory.createNVPair(paramString), "CID");
      
/* 559 */       localNVPair2 = localNVNavigator.findNVPairRecurse(localNVFactory.createNVPair(paramString), "address");
    }
    catch (NLException localNLException1)
    {
/* 564 */       localObject2 = new NetException(101);
/* 565 */       ((NetException)localObject2).initCause(localNLException1);
/* 566 */       throw ((Throwable)localObject2);
    }
/* 568 */     NVPair localNVPair3 = localNVNavigator.findNVPair(localNVPair2, "protocol");
/* 569 */     if (localNVPair3 == null) {
/* 570 */       throw new NetException(100);
    }
    
/* 574 */     ((ConnOption)localObject1).protocol = localNVPair3.getAtom();
/* 575 */     if ((!((ConnOption)localObject1).protocol.equals("TCP")) && (!((ConnOption)localObject1).protocol.equals("tcp")) && (!((ConnOption)localObject1).protocol.equals("SSL")) && (!((ConnOption)localObject1).protocol.equals("ssl")) && (!((ConnOption)localObject1).protocol.equals("ANO")) && (!((ConnOption)localObject1).protocol.equals("ano")))
    {
/* 578 */       throw new NetException(102);
    }
    
/* 582 */     localNVPair3 = localNVNavigator.findNVPair(localNVPair2, "Host");
/* 583 */     if (localNVPair3 == null) {
/* 584 */       throw new NetException(103);
    }
/* 586 */     ((ConnOption)localObject1).host = localNVPair3.getAtom();
    
/* 589 */     localNVPair3 = localNVNavigator.findNVPair(localNVPair2, "Port");
/* 590 */     if (localNVPair3 == null) {
/* 591 */       throw new NetException(104);
    }
/* 593 */     ((ConnOption)localObject1).port = Integer.parseInt(localNVPair3.getAtom());
    
/* 596 */     localNVPair3 = localNVNavigator.findNVPair(localNVPair2, "sduSize");
/* 597 */     if (localNVPair3 != null) {
/* 598 */       ((ConnOption)localObject1).sdu = Integer.parseInt(localNVPair3.getAtom());
    }
    
/* 601 */     localNVPair3 = localNVNavigator.findNVPair(localNVPair2, "tduSize");
/* 602 */     if (localNVPair3 != null) {
/* 603 */       ((ConnOption)localObject1).tdu = Integer.parseInt(localNVPair3.getAtom());
    }
    
/* 606 */     Object localObject2 = null;
    
    try
    {
/* 610 */       localObject2 = localNVNavigator.findNVPairRecurse(localNVFactory.createNVPair(paramString), "connect_data");
    }
    catch (NLException localNLException2)
    {
/* 615 */       NetException localNetException = new NetException(101);
/* 616 */       localNetException.initCause(localNLException2);
/* 617 */       throw localNetException;
    }
    
/* 620 */     StringBuffer localStringBuffer = new StringBuffer(paramString);
/* 621 */     ((ConnOption)localObject1).conn_data = (localObject2 != null ? insertCID(paramString) : localStringBuffer);
    
/* 623 */     ((ConnOption)localObject1).addr = ("(ADDRESS=(PROTOCOL=tcp)(HOST=" + ((ConnOption)localObject1).host + ")(PORT=" + ((ConnOption)localObject1).port + "))");
    
/* 625 */     this.cs.addOption((ConnOption)localObject1);
  }
  
  private void resolveAddrTree(String paramString)
    throws NetException
  {
/* 639 */     NavSchemaObjectFactory localNavSchemaObjectFactory = new NavSchemaObjectFactory();
/* 640 */     NavServiceAlias localNavServiceAlias = (NavServiceAlias)localNavSchemaObjectFactory.create(4);
    try
    {
/* 643 */       String str = "alias=" + paramString;
      
/* 645 */       localNavServiceAlias.initFromString(str);
    }
    catch (NLException localNLException) {
/* 648 */       throw new NetException(501);
    }
    catch (SOException localSOException) {
/* 651 */       throw new NetException(502, localSOException.getMessage());
    }
/* 653 */     localNavServiceAlias.navigate(this.cs, null);
  }
  
  private StringBuffer insertCID(String paramString)
    throws NetException
  {
/* 665 */     NVFactory localNVFactory = new NVFactory();
/* 666 */     NVNavigator localNVNavigator = new NVNavigator();
    
/* 668 */     StringBuffer localStringBuffer = new StringBuffer(2048);
/* 669 */     NVPair localNVPair1 = null;
/* 670 */     NVPair localNVPair2 = null;
/* 671 */     NVPair localNVPair3 = null;
/* 672 */     NVPair localNVPair4 = null;
/* 673 */     NVPair localNVPair5 = null;
    
    try
    {
/* 678 */       localNVPair1 = localNVNavigator.findNVPairRecurse(localNVFactory.createNVPair(paramString), "description");
      
/* 680 */       localNVPair2 = localNVNavigator.findNVPairRecurse(localNVFactory.createNVPair(paramString), "address_list");
      
/* 682 */       localNVPair3 = localNVNavigator.findNVPairRecurse(localNVFactory.createNVPair(paramString), "address");
      
/* 684 */       localNVPair4 = localNVNavigator.findNVPairRecurse(localNVFactory.createNVPair(paramString), "connect_data");
      
/* 686 */       localNVPair5 = localNVNavigator.findNVPairRecurse(localNVFactory.createNVPair(paramString), "source_route");
    }
    catch (NLException localNLException)
    {
/* 691 */       localObject = new NetException(101);
/* 692 */       ((NetException)localObject).initCause(localNLException);
/* 693 */       throw ((Throwable)localObject);
    }
/* 695 */     NVPair localNVPair6 = null;
/* 696 */     Object localObject = null;
/* 697 */     NVPair localNVPair7 = null;
    
/* 700 */     if (localNVPair4 != null) {
/* 701 */       localNVPair6 = localNVNavigator.findNVPair(localNVPair4, "SID");
/* 702 */       localObject = localNVNavigator.findNVPair(localNVPair4, "CID");
/* 703 */       localNVPair7 = localNVNavigator.findNVPair(localNVPair4, "SERVICE_NAME");
    }
    else {
/* 706 */       throw new NetException(105);
    }
/* 708 */     if ((localNVPair6 == null) && (localNVPair7 == null)) {
/* 709 */       throw new NetException(106);
    }
/* 711 */     localStringBuffer.append("(DESCRIPTION=");
/* 712 */     if ((localNVPair2 != null) && (localNVPair2.getListSize() > 0))
    {
/* 714 */       for (int i = 0; i < localNVPair2.getListSize(); i++)
      {
/* 716 */         NVPair localNVPair8 = localNVPair2.getListElement(i);
/* 717 */         localStringBuffer.append(localNVPair8.toString());
      }
      
    }
/* 721 */     else if (localNVPair3 != null) {
/* 722 */       localStringBuffer.append(localNVPair3.toString());
    } else {
/* 724 */       throw new NetException(107);
    }
    
/* 729 */     if (localNVPair7 != null) {
/* 730 */       localStringBuffer.append("(CONNECT_DATA=" + localNVPair7.toString() + "(CID=(PROGRAM=" + this.cs.getProgramName() + ")(HOST=__jdbc__)(USER=" + this.cs.getOSUsername() + ")))");
    }
    else {
/* 733 */       localStringBuffer.append("(CONNECT_DATA=" + localNVPair6.toString() + "(CID=(PROGRAM=" + this.cs.getProgramName() + ")(HOST=__jdbc__)(USER=" + this.cs.getOSUsername() + ")))");
    }
    
/* 736 */     if (localNVPair5 != null) {
/* 737 */       localStringBuffer.append(localNVPair5.toString());
    }
/* 739 */     localStringBuffer.append(")");
/* 740 */     return localStringBuffer;
  }
  
  public Properties getUp()
  {
/* 748 */     return this.up;
  }
  
  public boolean isConnectionSocketKeepAlive()
    throws SocketException
  {
/* 768 */     return this.cs.isConnectionSocketKeepAlive();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/AddrResolution.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */