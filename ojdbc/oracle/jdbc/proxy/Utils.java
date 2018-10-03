package oracle.jdbc.proxy;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
class Utils
{
  static String makeSlashed(Class paramClass)
  {
/*  55 */     return makeSlashed(paramClass.getName());
  }
  
  static String makeSlashed(String paramString)
  {
/*  60 */     return paramString.replace('.', '/');
  }
  
  static String makeSlashed(String paramString1, String paramString2)
  {
/*  65 */     return makeSlashed(paramString1) + '/' + paramString2;
  }
  
  static String makeType(Class paramClass)
  {
/*  70 */     return makeType(paramClass.getName());
  }
  
  static String makeType(String paramString)
  {
/*  75 */     String str = makeSlashed(paramString);
    
/*  77 */     if ("boolean".equals(str)) {
/*  78 */       return "Z";
    }
/*  80 */     if ("char".equals(str)) {
/*  81 */       return "C";
    }
/*  83 */     if ("byte".equals(str)) {
/*  84 */       return "B";
    }
/*  86 */     if ("short".equals(str)) {
/*  87 */       return "S";
    }
/*  89 */     if ("int".equals(str)) {
/*  90 */       return "I";
    }
/*  92 */     if ("long".equals(str)) {
/*  93 */       return "J";
    }
/*  95 */     if ("float".equals(str)) {
/*  96 */       return "F";
    }
/*  98 */     if ("double".equals(str)) {
/*  99 */       return "D";
    }
/* 101 */     if ("void".equals(str)) {
/* 102 */       return "V";
    }
/* 104 */     if (str.startsWith("[")) {
/* 105 */       return str;
    }
/* 107 */     return 'L' + str + ';';
  }
  
  static String makeSignature(Class[] paramArrayOfClass, Class paramClass)
  {
/* 113 */     StringBuilder localStringBuilder = new StringBuilder();
/* 114 */     localStringBuilder.append('(');
    
/* 116 */     for (Class localClass : paramArrayOfClass) {
/* 117 */       localStringBuilder.append(makeType(makeSlashed(localClass.getName())));
    }
/* 119 */     localStringBuilder.append(')').append(makeType(makeSlashed(paramClass.getName())));
/* 120 */     return localStringBuilder.toString();
  }
  
  static String[] makeThrowables(Class[] paramArrayOfClass)
  {
/* 125 */     int i = paramArrayOfClass.length;
    String[] arrayOfString;
/* 127 */     if (0 == i) {
/* 128 */       arrayOfString = null;
    }
    else {
/* 131 */       arrayOfString = new String[i];
/* 132 */       for (int j = 0; j < i; j++) {
/* 133 */         arrayOfString[j] = makeSlashed(paramArrayOfClass[j].getName());
      }
    }
/* 136 */     return arrayOfString;
  }
  
  static int loadOpcode(Class paramClass)
  {
/* 141 */     String str = paramClass.getName();
/* 142 */     if (("boolean".equals(str)) || ("byte".equals(str)) || ("char".equals(str)) || ("short".equals(str)) || ("int".equals(str)))
    {
/* 147 */       return 21;
    }
/* 149 */     if ("long".equals(str)) {
/* 150 */       return 22;
    }
/* 152 */     if ("float".equals(str)) {
/* 153 */       return 23;
    }
/* 155 */     if ("double".equals(str)) {
/* 156 */       return 24;
    }
/* 158 */     return 25;
  }
  
  static int storeOpcode(Class paramClass)
  {
/* 163 */     String str = paramClass.getName();
/* 164 */     if (("boolean".equals(str)) || ("byte".equals(str)) || ("char".equals(str)) || ("short".equals(str)) || ("int".equals(str)))
    {
/* 169 */       return 54;
    }
/* 171 */     if ("long".equals(str)) {
/* 172 */       return 55;
    }
/* 174 */     if ("float".equals(str)) {
/* 175 */       return 56;
    }
/* 177 */     if ("double".equals(str)) {
/* 178 */       return 57;
    }
/* 180 */     return 58;
  }
  
  static int returnOpcode(Class paramClass)
  {
/* 185 */     String str = paramClass.getName();
/* 186 */     if (("boolean".equals(str)) || ("byte".equals(str)) || ("char".equals(str)) || ("short".equals(str)) || ("int".equals(str)))
    {
/* 191 */       return 172;
    }
/* 193 */     if ("long".equals(str)) {
/* 194 */       return 173;
    }
/* 196 */     if ("float".equals(str)) {
/* 197 */       return 174;
    }
/* 199 */     if ("double".equals(str)) {
/* 200 */       return 175;
    }
/* 202 */     if ("void".equals(str)) {
/* 203 */       return 177;
    }
/* 205 */     return 176;
  }
  
  static int varSize(Class paramClass)
  {
/* 210 */     String str = paramClass.getName();
/* 211 */     if (("double".equals(str)) || ("long".equals(str))) {
/* 212 */       return 2;
    }
/* 214 */     return 1;
  }
  
  static void cast(MethodVisitor paramMethodVisitor, Class paramClass1, Class paramClass2)
  {
/* 221 */     boolean bool1 = isPrimitive(paramClass1);
/* 222 */     boolean bool2 = isPrimitive(paramClass2);
    
/* 224 */     if ((!bool1) && (bool2)) {
/* 225 */       autoUnbox(paramMethodVisitor, paramClass2);
/* 226 */     } else if ((bool1) && (!bool2)) {
/* 227 */       autoBox(paramMethodVisitor, paramClass1);
/* 228 */     } else if ((!bool1) && (!bool2) && (!paramClass1.equals(paramClass2))) {
/* 229 */       paramMethodVisitor.visitTypeInsn(192, makeSlashed(paramClass2));
    }
  }
  
  static boolean isPrimitive(Class paramClass) {
/* 234 */     return (Boolean.TYPE.equals(paramClass)) || (Character.TYPE.equals(paramClass)) || (Byte.TYPE.equals(paramClass)) || (Short.TYPE.equals(paramClass)) || (Integer.TYPE.equals(paramClass)) || (Long.TYPE.equals(paramClass)) || (Float.TYPE.equals(paramClass)) || (Double.TYPE.equals(paramClass));
  }
  
  static void autoBox(MethodVisitor paramMethodVisitor, Class paramClass)
  {
/* 247 */     if (Boolean.TYPE.equals(paramClass)) {
/* 248 */       paramMethodVisitor.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
    }
/* 254 */     else if (Character.TYPE.equals(paramClass)) {
/* 255 */       paramMethodVisitor.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
    }
/* 261 */     else if (Byte.TYPE.equals(paramClass)) {
/* 262 */       paramMethodVisitor.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
    }
/* 268 */     else if (Short.TYPE.equals(paramClass)) {
/* 269 */       paramMethodVisitor.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
    }
/* 275 */     else if (Integer.TYPE.equals(paramClass)) {
/* 276 */       paramMethodVisitor.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
    }
/* 282 */     else if (Long.TYPE.equals(paramClass)) {
/* 283 */       paramMethodVisitor.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
    }
/* 289 */     else if (Float.TYPE.equals(paramClass)) {
/* 290 */       paramMethodVisitor.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
    }
/* 296 */     else if (Double.TYPE.equals(paramClass)) {
/* 297 */       paramMethodVisitor.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
    }
  }
  
  static void autoUnbox(MethodVisitor paramMethodVisitor, Class paramClass)
  {
/* 306 */     if (Boolean.TYPE.equals(paramClass))
    {
/* 308 */       paramMethodVisitor.visitTypeInsn(192, "java/lang/Boolean");
      
/* 312 */       paramMethodVisitor.visitMethodInsn(182, "java/lang/Boolean", "booleanValue", "()Z");
    }
/* 319 */     else if (Character.TYPE.equals(paramClass))
    {
/* 321 */       paramMethodVisitor.visitTypeInsn(192, "java/lang/Character");
      
/* 325 */       paramMethodVisitor.visitMethodInsn(182, "java/lang/Character", "charValue", "()C");
    }
/* 332 */     else if (Byte.TYPE.equals(paramClass))
    {
/* 334 */       paramMethodVisitor.visitTypeInsn(192, "java/lang/Byte");
      
/* 338 */       paramMethodVisitor.visitMethodInsn(182, "java/lang/Byte", "byteValue", "()B");
    }
/* 345 */     else if (Short.TYPE.equals(paramClass))
    {
/* 347 */       paramMethodVisitor.visitTypeInsn(192, "java/lang/Short");
      
/* 351 */       paramMethodVisitor.visitMethodInsn(182, "java/lang/Short", "shortValue", "()S");
    }
/* 358 */     else if (Integer.TYPE.equals(paramClass))
    {
/* 360 */       paramMethodVisitor.visitTypeInsn(192, "java/lang/Integer");
      
/* 364 */       paramMethodVisitor.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I");
    }
/* 371 */     else if (Long.TYPE.equals(paramClass))
    {
/* 373 */       paramMethodVisitor.visitTypeInsn(192, "java/lang/Long");
      
/* 377 */       paramMethodVisitor.visitMethodInsn(182, "java/lang/Long", "longValue", "()J");
    }
/* 384 */     else if (Float.TYPE.equals(paramClass))
    {
/* 386 */       paramMethodVisitor.visitTypeInsn(192, "java/lang/Float");
      
/* 390 */       paramMethodVisitor.visitMethodInsn(182, "java/lang/Float", "floatValue", "()F");
    }
/* 397 */     else if (Double.TYPE.equals(paramClass))
    {
/* 399 */       paramMethodVisitor.visitTypeInsn(192, "java/lang/Double");
      
/* 403 */       paramMethodVisitor.visitMethodInsn(182, "java/lang/Double", "doubleValue", "()D");
    }
    else
    {
/* 411 */       paramMethodVisitor.visitTypeInsn(192, makeSlashed(paramClass));
    }
  }
  
  static void loadClass(MethodVisitor paramMethodVisitor, Class paramClass)
  {
/* 418 */     String str = paramClass.getName();
    
/* 420 */     if ("boolean".equals(str)) {
/* 421 */       paramMethodVisitor.visitFieldInsn(178, "java/lang/Boolean", "TYPE", "Ljava/lang/Class;");
    }
/* 427 */     else if ("char".equals(str)) {
/* 428 */       paramMethodVisitor.visitFieldInsn(178, "java/lang/Character", "TYPE", "Ljava/lang/Class;");
    }
/* 434 */     else if ("byte".equals(str)) {
/* 435 */       paramMethodVisitor.visitFieldInsn(178, "java/lang/Byte", "TYPE", "Ljava/lang/Class;");
    }
/* 441 */     else if ("short".equals(str)) {
/* 442 */       paramMethodVisitor.visitFieldInsn(178, "java/lang/Short", "TYPE", "Ljava/lang/Class;");
    }
/* 448 */     else if ("int".equals(str)) {
/* 449 */       paramMethodVisitor.visitFieldInsn(178, "java/lang/Integer", "TYPE", "Ljava/lang/Class;");
    }
/* 455 */     else if ("long".equals(str)) {
/* 456 */       paramMethodVisitor.visitFieldInsn(178, "java/lang/Long", "TYPE", "Ljava/lang/Class;");
    }
/* 462 */     else if ("float".equals(str)) {
/* 463 */       paramMethodVisitor.visitFieldInsn(178, "java/lang/Float", "TYPE", "Ljava/lang/Class;");
    }
/* 469 */     else if ("double".equals(str)) {
/* 470 */       paramMethodVisitor.visitFieldInsn(178, "java/lang/Double", "TYPE", "Ljava/lang/Class;");
    }
    else
    {
/* 477 */       paramMethodVisitor.visitLdcInsn(Type.getType(paramClass));
    }
  }
  
  static void loadConst(MethodVisitor paramMethodVisitor, int paramInt) {
/* 482 */     switch (paramInt) {
    case 0: 
/* 484 */       paramMethodVisitor.visitInsn(3);return;
/* 485 */     case 1:  paramMethodVisitor.visitInsn(4);return;
/* 486 */     case 2:  paramMethodVisitor.visitInsn(5);return;
/* 487 */     case 3:  paramMethodVisitor.visitInsn(6);return;
/* 488 */     case 4:  paramMethodVisitor.visitInsn(7);return;
/* 489 */     case 5:  paramMethodVisitor.visitInsn(8);return;
    }
    
/* 492 */     if ((paramInt >= -128) && (paramInt <= 127))
    {
/* 494 */       paramMethodVisitor.visitIntInsn(16, paramInt);
/* 495 */       return;
    }
    
/* 498 */     throw new RuntimeException("do not know how to load " + paramInt);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */