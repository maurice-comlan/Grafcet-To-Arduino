package grafchart.sfc.actions;

import java.io.PrintStream;

public class SingleExpression
  extends Goal
  implements Cloneable
{
  public void flushCache()
  {
    super.flushCache();
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public SingleExpression clone()
    throws CloneNotSupportedException
  {
    SingleExpression localSingleExpression = (SingleExpression)super.clone();
    localSingleExpression.in$Circle(false);
    localSingleExpression.is$Final(false);
    return localSingleExpression;
  }
  
  public SingleExpression copy()
  {
    try
    {
      SingleExpression localSingleExpression = clone();
      if (this.children != null) {
        localSingleExpression.children = ((ASTNode[])this.children.clone());
      }
      return localSingleExpression;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public SingleExpression fullCopy()
  {
    SingleExpression localSingleExpression = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localSingleExpression.setChild(localASTNode, i);
    }
    return localSingleExpression;
  }
  
  public double evaluateReal()
  {
    return getExpr().evaluateReal();
  }
  
  public boolean evaluateBoolean()
  {
    return getExpr().evaluateBoolean();
  }
  
  public String evaluateString()
  {
    return getExpr().evaluateString();
  }
  
  public SingleExpression(int paramInt)
  {
    super(paramInt);
    is$Final(true);
  }
  
  public SingleExpression(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
    is$Final(true);
  }
  
  public SingleExpression()
  {
    this(0);
    is$Final(true);
  }
  
  public SingleExpression(Expr paramExpr)
  {
    setChild(paramExpr, 0);
    is$Final(true);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "SingleExpression");
    String str = paramString + "  ";
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).dumpTree(str, paramPrintStream);
    }
  }
  
  public Object jjtAccept(ActionParserVisitor paramActionParserVisitor, Object paramObject)
  {
    return paramActionParserVisitor.visit(this, paramObject);
  }
  
  public void jjtAddChild(Node paramNode, int paramInt)
  {
    checkChild(paramNode, paramInt);
    super.jjtAddChild(paramNode, paramInt);
  }
  
  public void checkChild(Node paramNode, int paramInt)
  {
    if ((paramInt == 0) && (!(paramNode instanceof Expr))) {
      throw new Error("Child number 0 of SingleExpression has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
    }
  }
  
  public int getNumChild()
  {
    return 1;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public void setExpr(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public Expr getExpr()
  {
    return (Expr)getChild(0);
  }
  
  public Expr getExprNoTransform()
  {
    return (Expr)getChildNoTransform(0);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/SingleExpression.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */