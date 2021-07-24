package grafchart.sfc.transitions;

import java.io.PrintStream;

public class Negation
  extends UnExpr
  implements Cloneable
{
  protected int toString_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.toString_visited = -1;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Negation clone()
    throws CloneNotSupportedException
  {
    Negation localNegation = (Negation)super.clone();
    localNegation.toString_visited = -1;
    localNegation.in$Circle(false);
    localNegation.is$Final(false);
    return localNegation;
  }
  
  public Negation copy()
  {
    try
    {
      Negation localNegation = clone();
      if (this.children != null) {
        localNegation.children = ((ASTNode[])this.children.clone());
      }
      return localNegation;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Negation fullCopy()
  {
    Negation localNegation = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localNegation.setChild(localASTNode, i);
    }
    return localNegation;
  }
  
  public boolean evaluateBoolean()
  {
    return !getExpr().evaluateBoolean();
  }
  
  public double evaluateReal()
  {
    return -getExpr().evaluateReal();
  }
  
  public Negation(int paramInt)
  {
    super(paramInt);
  }
  
  public Negation(TransitionParser paramTransitionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramTransitionParser;
  }
  
  public Negation()
  {
    this(0);
  }
  
  public Negation(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Negation");
    String str = paramString + "  ";
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).dumpTree(str, paramPrintStream);
    }
  }
  
  public Object jjtAccept(TransitionParserVisitor paramTransitionParserVisitor, Object paramObject)
  {
    return paramTransitionParserVisitor.visit(this, paramObject);
  }
  
  public void jjtAddChild(Node paramNode, int paramInt)
  {
    checkChild(paramNode, paramInt);
    super.jjtAddChild(paramNode, paramInt);
  }
  
  public void checkChild(Node paramNode, int paramInt)
  {
    if ((paramInt == 0) && (!(paramNode instanceof Expr))) {
      throw new Error("Child number 0 of UnExpr has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
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
  
  public String toString()
  {
    ASTNode.State localState = state();
    if (this.toString_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: toString in class: ");
    }
    this.toString_visited = state().boundariesCrossed;
    String str = toString_compute();
    this.toString_visited = -1;
    return str;
  }
  
  private String toString_compute()
  {
    return "-" + getExpr();
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/Negation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */