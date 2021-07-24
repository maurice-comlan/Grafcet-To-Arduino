package grafchart.sfc.transitions;

import java.io.Serializable;

public class Token
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public int kind;
  public int beginLine;
  public int beginColumn;
  public int endLine;
  public int endColumn;
  public String image;
  public Token next;
  public Token specialToken;
  
  public Object getValue()
  {
    return null;
  }
  
  public Token() {}
  
  public Token(int paramInt)
  {
    this(paramInt, null);
  }
  
  public Token(int paramInt, String paramString)
  {
    this.kind = paramInt;
    this.image = paramString;
  }
  
  public String toString()
  {
    return this.image;
  }
  
  public static Token newToken(int paramInt, String paramString)
  {
    switch (paramInt)
    {
    }
    return new Token(paramInt, paramString);
  }
  
  public static Token newToken(int paramInt)
  {
    return newToken(paramInt, null);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/Token.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */