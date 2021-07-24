package grafchart.sfc.actions;

public class ParseException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  public Token currentToken;
  public int[][] expectedTokenSequences;
  public String[] tokenImage;
  protected String eol = System.getProperty("line.separator", "\n");
  
  public ParseException(Token paramToken, int[][] paramArrayOfInt, String[] paramArrayOfString)
  {
    super(initialise(paramToken, paramArrayOfInt, paramArrayOfString));
    this.currentToken = paramToken;
    this.expectedTokenSequences = paramArrayOfInt;
    this.tokenImage = paramArrayOfString;
  }
  
  public ParseException() {}
  
  public ParseException(String paramString)
  {
    super(paramString);
  }
  
  private static String initialise(Token paramToken, int[][] paramArrayOfInt, String[] paramArrayOfString)
  {
    String str1 = System.getProperty("line.separator", "\n");
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    for (int j = 0; j < paramArrayOfInt.length; j++)
    {
      if (i < paramArrayOfInt[j].length) {
        i = paramArrayOfInt[j].length;
      }
      for (int k = 0; k < paramArrayOfInt[j].length; k++) {
        localStringBuffer.append(paramArrayOfString[paramArrayOfInt[j][k]]).append(' ');
      }
      if (paramArrayOfInt[j][(paramArrayOfInt[j].length - 1)] != 0) {
        localStringBuffer.append("...");
      }
      localStringBuffer.append(str1).append("    ");
    }
    String str2 = "Encountered \"";
    Token localToken = paramToken.next;
    for (int m = 0; m < i; m++)
    {
      if (m != 0) {
        str2 = str2 + " ";
      }
      if (localToken.kind == 0)
      {
        str2 = str2 + paramArrayOfString[0];
        break;
      }
      str2 = str2 + " " + paramArrayOfString[localToken.kind];
      str2 = str2 + " \"";
      str2 = str2 + add_escapes(localToken.image);
      str2 = str2 + " \"";
      localToken = localToken.next;
    }
    str2 = str2 + "\" at line " + paramToken.next.beginLine + ", column " + paramToken.next.beginColumn;
    str2 = str2 + "." + str1;
    if (paramArrayOfInt.length == 1) {
      str2 = str2 + "Was expecting:" + str1 + "    ";
    } else {
      str2 = str2 + "Was expecting one of:" + str1 + "    ";
    }
    str2 = str2 + localStringBuffer.toString();
    return str2;
  }
  
  static String add_escapes(String paramString)
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
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ParseException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */