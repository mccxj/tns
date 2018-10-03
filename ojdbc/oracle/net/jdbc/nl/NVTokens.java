package oracle.net.jdbc.nl;
import java.io.PrintStream;
import java.util.Vector;
public final class NVTokens
{
  public static final int TKN_NONE = 0;
  public static final int TKN_LPAREN = 1;
  public static final int TKN_RPAREN = 2;
  public static final int TKN_COMMA = 3;
  public static final int TKN_EQUAL = 4;
  public static final int TKN_LITERAL = 8;
  public static final int TKN_EOS = 9;
  private static final char TKN_LPAREN_VALUE = '(';
  private static final char TKN_RPAREN_VALUE = ')';
  private static final char TKN_COMMA_VALUE = ',';
  private static final char TKN_EQUAL_VALUE = '=';
  private static final char TKN_BKSLASH_VALUE = '\\';
  private static final char TKN_DQUOTE_VALUE = '"';
  private static final char TKN_SQUOTE_VALUE = '\'';
  private static final char TKN_EOS_VALUE = '%';
  private static final char TKN_SPC_VALUE = ' ';
  private static final char TKN_TAB_VALUE = '\t';
  private static final char TKN_LF_VALUE = '\n';
  private static final char TKN_CR_VALUE = '\r';
  private Vector _tkType = null;
  private Vector _tkValue = null;
  private int _numTokens = 0;
  private int _tkPos = 0;
  
  private static boolean _isWhiteSpace(char paramChar)
  {
    return (paramChar == ' ') || (paramChar == '\t') || (paramChar == '\n') || (paramChar == '\r');
  }
  
  private static String _trimWhiteSpace(String paramString)
  {
    int i = paramString.length();
    int j = 0;
    int k = i;
    while ((j < i) && (_isWhiteSpace(paramString.charAt(j)))) {
      j++;
    }
    while ((j < k) && (_isWhiteSpace(paramString.charAt(k - 1)))) {
      k--;
    }
    return paramString.substring(j, k);
  }
  
  public boolean parseTokens(String paramString)
  {
    this._numTokens = 0;
    this._tkPos = 0;
    this._tkType = new Vector(25, 25);
    this._tkValue = new Vector(25, 25);
    int i = paramString.length();
    int j = 0;
    char[] arrayOfChar = paramString.toCharArray();
    int k = 0;
    while (k < i)
    {
      while ((k < i) && (_isWhiteSpace(arrayOfChar[k]))) {
        k++;
      }
      if (k < i) {
        switch (arrayOfChar[k])
        {
        case '(': 
          j = 0;
          _addToken(1, '(');
          k++;
          break;
        case '=': 
          j = 1;
          _addToken(4, '=');
          k++;
          break;
        case ')': 
          j = 0;
          _addToken(2, ')');
          k++;
          break;
        case ',': 
          j = 0;
          _addToken(3, ',');
          k++;
          break;
        default: 
          int m = k;
          int n = -1;
          int i1 = 0;
          int i2 = 34;
          if ((arrayOfChar[k] == '\'') || (arrayOfChar[k] == '"'))
          {
            i1 = 1;
            i2 = arrayOfChar[k];
            k++;
          }
          while (k < i) {
            if (arrayOfChar[k] == '\\')
            {
              k += 2;
            }
            else
            {
              if (i1 != 0)
              {
                if (arrayOfChar[k] == i2)
                {
                  k++;
                  n = k;
                  break;
                }
              }
              else if ((arrayOfChar[k] == '(') || (arrayOfChar[k] == ')') || ((arrayOfChar[k] == ',') && (j == 0)) || ((arrayOfChar[k] == '=') && (j == 0)))
              {
                n = k;
                break;
              }
              k++;
            }
          }
          if (n == -1) {
            n = k;
          }
          _addToken(8, _trimWhiteSpace(paramString.substring(m, n)));
        }
      }
    }
    _addToken(9, '%');
    return true;
  }
  
  public int getToken()
    throws NLException
  {
    if (this._tkType == null) {
      throw new UninitializedObjectException("ParseError-04604", "");
    }
    if (this._tkPos < this._numTokens) {
      return ((Integer)this._tkType.elementAt(this._tkPos)).intValue();
    }
    throw new NLException("NoLiterals-04610", "");
  }
  
  public int popToken()
    throws UninitializedObjectException, NLException
  {
    int i = 0;
    if (this._tkType == null) {
      throw new UninitializedObjectException("ParseError-04604", "");
    }
    if (this._tkPos < this._numTokens) {
      i = ((Integer)this._tkType.elementAt(this._tkPos++)).intValue();
    } else {
      throw new NLException("NoLiterals-04610", "");
    }
    return i;
  }
  
  public String getLiteral()
    throws NLException
  {
    String str = null;
    if (this._tkValue == null) {
      throw new UninitializedObjectException("ParseError-04604", "");
    }
    if (this._tkPos < this._numTokens) {
      str = (String)this._tkValue.elementAt(this._tkPos);
    } else {
      throw new NLException("NoLiterals-04610", "");
    }
    return str;
  }
  
  public String popLiteral()
    throws NLException
  {
    String str = null;
    if (this._tkValue == null) {
      throw new UninitializedObjectException("ParseError-04604", "");
    }
    if (this._tkPos < this._numTokens) {
      str = (String)this._tkValue.elementAt(this._tkPos++);
    } else {
      throw new NLException("NoLiterals-04610", "");
    }
    return str;
  }
  
  public void eatToken()
  {
    if (this._tkPos < this._numTokens) {
      this._tkPos += 1;
    }
  }
  
  public String toString()
  {
    if (this._tkType == null) {
      return "*NO TOKENS*";
    }
    String str = "Tokens";
    for (int i = 0; i < this._numTokens; i++) {
      str = str + " : " + this._tkValue.elementAt(i);
    }
    return str;
  }
  
  public void println()
  {
    System.out.println(toString());
  }
  
  private void _addToken(int paramInt, char paramChar)
  {
    _addToken(paramInt, String.valueOf(paramChar));
  }
  
  private void _addToken(int paramInt, String paramString)
  {
    this._tkType.addElement(new Integer(paramInt));
    this._tkValue.addElement(paramString);
    this._numTokens += 1;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/jdbc/nl/NVTokens.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */