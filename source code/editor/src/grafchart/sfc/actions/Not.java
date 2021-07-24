package grafchart.sfc.actions;

import java.io.PrintStream;

public class Not
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
  
  public Not clone()
    throws CloneNotSupportedException
  {
    Not localNot = (Not)super.clone();
    localNot.toString_visited = -1;
    localNot.in$Circle(false);
    localNot.is$Final(false);
    return localNot;
  }
  
  public Not copy()
  {
    try
    {
      Not localNot = clone();
      if (this.children != null) {
        localNot.children = ((ASTNode[])this.children.clone());
      }
      return localNot;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Not fullCopy()
  {
    Not localNot = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localNot.setChild(localASTNode, i);
    }
    return localNot;
  }
  
  public boolean evaluateBoolean()
  {
    return !getExpr().evaluateBoolean();
  }
  
  public double evaluateReal()
  {
    if ((int)getExpr().evaluateReal() == 1) {
      return 0.0D;
    }
    return 1.0D;
  }
  
  public Not(int paramInt)
  {
    super(paramInt);
  }
  
  public Not(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public Not()
  {
    this(0);
  }
  
  public Not(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Not");
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
    return "!" + getExpr();
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Not.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */