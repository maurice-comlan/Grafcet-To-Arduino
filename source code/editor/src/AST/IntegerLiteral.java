package AST;

import beaver.Symbol;
import java.io.PrintStream;

public class IntegerLiteral
  extends Exp
  implements Cloneable
{
  protected String tokenString_Value;
  public int Valuestart;
  public int Valueend;
  
  public void flushCache()
  {
    super.flushCache();
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public IntegerLiteral clone()
    throws CloneNotSupportedException
  {
    IntegerLiteral localIntegerLiteral = (IntegerLiteral)super.clone();
    localIntegerLiteral.in$Circle(false);
    localIntegerLiteral.is$Final(false);
    return localIntegerLiteral;
  }
  
  public IntegerLiteral copy()
  {
    try
    {
      IntegerLiteral localIntegerLiteral = clone();
      if (this.children != null) {
        localIntegerLiteral.children = ((ASTNode[])this.children.clone());
      }
      return localIntegerLiteral;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public IntegerLiteral fullCopy()
  {
    IntegerLiteral localIntegerLiteral = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localIntegerLiteral.setChild(localASTNode, i);
    }
    return localIntegerLiteral;
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    paramSignatureList.addInt(Integer.parseInt(getValue()), null);
  }
  
  public String signatureComment()
  {
    return getValue();
  }
  
  public IntegerLiteral() {}
  
  public IntegerLiteral(String paramString)
  {
    setValue(paramString);
  }
  
  public IntegerLiteral(Symbol paramSymbol)
  {
    setValue(paramSymbol);
  }
  
  protected int numChildren()
  {
    return 0;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public void setValue(String paramString)
  {
    this.tokenString_Value = paramString;
  }
  
  public void setValue(Symbol paramSymbol)
  {
    if ((paramSymbol.value != null) && (!(paramSymbol.value instanceof String))) {
      throw new UnsupportedOperationException("setValue is only valid for String lexemes");
    }
    this.tokenString_Value = ((String)paramSymbol.value);
    this.Valuestart = paramSymbol.getStart();
    this.Valueend = paramSymbol.getEnd();
  }
  
  public String getValue()
  {
    return this.tokenString_Value != null ? this.tokenString_Value : "";
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/IntegerLiteral.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */