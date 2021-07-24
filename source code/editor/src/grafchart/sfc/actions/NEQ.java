package grafchart.sfc.actions;

import java.io.PrintStream;

public class NEQ
  extends BinExpr
  implements Cloneable
{
  protected int opToString_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.opToString_visited = -1;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public NEQ clone()
    throws CloneNotSupportedException
  {
    NEQ localNEQ = (NEQ)super.clone();
    localNEQ.opToString_visited = -1;
    localNEQ.in$Circle(false);
    localNEQ.is$Final(false);
    return localNEQ;
  }
  
  public NEQ copy()
  {
    try
    {
      NEQ localNEQ = clone();
      if (this.children != null) {
        localNEQ.children = ((ASTNode[])this.children.clone());
      }
      return localNEQ;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public NEQ fullCopy()
  {
    NEQ localNEQ = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localNEQ.setChild(localASTNode, i);
    }
    return localNEQ;
  }
  
  public boolean evaluateBoolean()
  {
    return getLeft().evaluateReal() != getRight().evaluateReal();
  }
  
  public NEQ(int paramInt)
  {
    super(paramInt);
  }
  
  public NEQ(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public NEQ()
  {
    this(0);
  }
  
  public NEQ(Expr paramExpr1, Expr paramExpr2)
  {
    setChild(paramExpr1, 0);
    setChild(paramExpr2, 1);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "NEQ");
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
      throw new Error("Child number 0 of BinExpr has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
    }
    if ((paramInt == 1) && (!(paramNode instanceof Expr))) {
      throw new Error("Child number 1 of BinExpr has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
    }
  }
  
  public int getNumChild()
  {
    return 2;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public void setLeft(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public Expr getLeft()
  {
    return (Expr)getChild(0);
  }
  
  public Expr getLeftNoTransform()
  {
    return (Expr)getChildNoTransform(0);
  }
  
  public void setRight(Expr paramExpr)
  {
    setChild(paramExpr, 1);
  }
  
  public Expr getRight()
  {
    return (Expr)getChild(1);
  }
  
  public Expr getRightNoTransform()
  {
    return (Expr)getChildNoTransform(1);
  }
  
  public String opToString()
  {
    ASTNode.State localState = state();
    if (this.opToString_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: opToString in class: ");
    }
    this.opToString_visited = state().boundariesCrossed;
    String str = opToString_compute();
    this.opToString_visited = -1;
    return str;
  }
  
  private String opToString_compute()
  {
    return " != ";
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/NEQ.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */