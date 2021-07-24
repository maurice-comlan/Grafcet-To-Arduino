package grafchart.sfc.actions;

public class TokenMgrError
  extends Error
{
  private static final long serialVersionUID = 1L;
  static final int LEXICAL_ERROR = 0;
  static final int STATIC_LEXER_ERROR = 1;
  static final int INVALID_LEXICAL_STATE = 2;
  static final int LOOP_DETECTED = 3;
  int errorCode;
  
  protected static final String addEscapes(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramString.length(); i++) {
      switch (paramString.charAt(i))
      {
      case '\000': 
        break;
      case '\b': 
        localStringBuffer.append("\\b");
        break;
      case '\t': 
        localStringBuffer.append("\\t");
        break;
      case '\n': 
        localStringBuffer.append("\\n");
        break;
      case '\f': 
        localStringBuffer.append("\\f");
        break;
      case '\r': 
        localStringBuffer.append("\\r");
        break;
      case '"': 
        localStringBuffer.append("\\\"");
        break;
      case '\'': 
        localStringBuffer.append("\\'");
        break;
      case '\\': 
        localStringBuffer.append("\\\\");
        break;
      default: 
        char c;
        if (((c = paramString.charAt(i)) < ' ') || (c > '~'))
        {
          String str = "0000" + Integer.toString(c, 16);
          localStringBuffer.append("\\u" + str.substring(str.length() - 4, str.length()));
        }
        else
        {
          localStringBuffer.append(c);
        }
        break;
      }
    }
    return localStringBuffer.toString();
  }
  
  protected static String LexicalError(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, String paramString, char paramChar)
  {
    return "Lexical error at line " + paramInt2 + ", column " + paramInt3 + ".  Encountered: " + (paramBoolean ? "<EOF> " : new StringBuilder().append("\"").append(addEscapes(String.valueOf(paramChar))).append("\"").append(" (").append(paramChar).append("), ").toString()) + "after : \"" + addEscapes(paramString) + "\"";
  }
  
  public String getMessage()
  {
    return super.getMessage();
  }
  
  public TokenMgrError() {}
  
  public TokenMgrError(String paramString, int paramInt)
  {
    super(paramString);
    this.errorCode = paramInt;
  }
  
  public TokenMgrError(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, String paramString, char paramChar, int paramInt4)
  {
    this(LexicalError(paramBoolean, paramInt1, paramInt2, paramInt3, paramString, paramChar), paramInt4);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/TokenMgrError.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */