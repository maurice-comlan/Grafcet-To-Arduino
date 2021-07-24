package grafchart.sfc.actions;

import java.io.PrintStream;

public class ConditionalExpression
  extends Expr
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
  
  public ConditionalExpression clone()
    throws CloneNotSupportedException
  {
    ConditionalExpression localConditionalExpression = (ConditionalExpression)super.clone();
    localConditionalExpression.toString_visited = -1;
    localConditionalExpression.in$Circle(false);
    localConditionalExpression.is$Final(false);
    return localConditionalExpression;
  }
  
  public ConditionalExpression copy()
  {
    try
    {
      ConditionalExpression localConditionalExpression = clone();
      if (this.children != null) {
        localConditionalExpression.children = ((ASTNode[])this.children.clone());
      }
      return localConditionalExpression;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public ConditionalExpression fullCopy()
  {
    ConditionalExpression localConditionalExpression = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localConditionalExpression.setChild(localASTNode, i);
    }
    return localConditionalExpression;
  }
  
  public boolean evaluateBoolean()
  {
    boolean bool;
    if (getCondition().evaluateBoolean()) {
      bool = getTrueExpr().evaluateBoolean();
    } else {
      bool = getFalseExpr().evaluateBoolean();
    }
    return bool;
  }
  
  public double evaluateReal()
  {
    double d;
    if (getCondition().evaluateBoolean()) {
      d = getTrueExpr().evaluateReal();
    } else {
      d = getFalseExpr().evaluateReal();
    }
    return d;
  }
  
  public String evaluateString()
  {
    String str;
    if (getCondition().evaluateBoolean()) {
      str = getTrueExpr().evaluateString();
    } else {
      str = getFalseExpr().evaluateString();
    }
    return str;
  }
  
  public ConditionalExpression(int paramInt)
  {
    super(paramInt);
  }
  
  public ConditionalExpression(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public ConditionalExpression()
  {
    this(0);
  }
  
  public ConditionalExpression(Expr paramExpr1, Expr paramExpr2, Expr paramExpr3)
  {
    setChild(paramExpr1, 0);
    setChild(paramExpr2, 1);
    setChild(paramExpr3, 2);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "ConditionalExpression");
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
      throw new Error("Child number 0 of ConditionalExpression has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
    }
    if ((paramInt == 1) && (!(paramNode instanceof Expr))) {
      throw new Error("Child number 1 of ConditionalExpression has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
    }
    if ((paramInt == 2) && (!(paramNode instanceof Expr))) {
      throw new Error("Child number 2 of ConditionalExpression has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
    }
  }
  
  public int getNumChild()
  {
    return 3;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public void setCondition(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public Expr getCondition()
  {
    return (Expr)getChild(0);
  }
  
  public Expr getConditionNoTransform()
  {
    return (Expr)getChildNoTransform(0);
  }
  
  public void setTrueExpr(Expr paramExpr)
  {
    setChild(paramExpr, 1);
  }
  
  public Expr getTrueExpr()
  {
    return (Expr)getChild(1);
  }
  
  public Expr getTrueExprNoTransform()
  {
    return (Expr)getChildNoTransform(1);
  }
  
  public void setFalseExpr(Expr paramExpr)
  {
    setChild(paramExpr, 2);
  }
  
  public Expr getFalseExpr()
  {
    return (Expr)getChild(2);
  }
  
  public Expr getFalseExprNoTransform()
  {
    return (Expr)getChildNoTransform(2);
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
    return getCondition() + " ? " + getTrueExpr() + " : " + getFalseExpr();
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ConditionalExpression.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */