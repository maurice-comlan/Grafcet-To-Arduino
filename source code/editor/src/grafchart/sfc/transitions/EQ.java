package grafchart.sfc.transitions;

import java.io.PrintStream;

public class EQ
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
  
  public EQ clone()
    throws CloneNotSupportedException
  {
    EQ localEQ = (EQ)super.clone();
    localEQ.opToString_visited = -1;
    localEQ.in$Circle(false);
    localEQ.is$Final(false);
    return localEQ;
  }
  
  public EQ copy()
  {
    try
    {
      EQ localEQ = clone();
      if (this.children != null) {
        localEQ.children = ((ASTNode[])this.children.clone());
      }
      return localEQ;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public EQ fullCopy()
  {
    EQ localEQ = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localEQ.setChild(localASTNode, i);
    }
    return localEQ;
  }
  
  public boolean evaluateBoolean()
  {
    boolean bool;
    if ((getLeft().isString()) && (getRight().isString())) {
      bool = getLeft().evaluateString().equals(getRight().evaluateString());
    } else {
      bool = getLeft().evaluateReal() == getRight().evaluateReal();
    }
    return bool;
  }
  
  public EQ(int paramInt)
  {
    super(paramInt);
  }
  
  public EQ(TransitionParser paramTransitionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramTransitionParser;
  }
  
  public EQ()
  {
    this(0);
  }
  
  public EQ(Expr paramExpr1, Expr paramExpr2)
  {
    setChild(paramExpr1, 0);
    setChild(paramExpr2, 1);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "EQ");
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
    return " == ";
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/EQ.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */