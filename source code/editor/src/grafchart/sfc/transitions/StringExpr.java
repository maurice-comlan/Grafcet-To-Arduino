package grafchart.sfc.transitions;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class StringExpr
  extends Expr
  implements Cloneable
{
  protected String tokenString_VALUE;
  protected Map dumpNode_String_visited;
  protected int toString_visited = -1;
  protected int isString_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.dumpNode_String_visited = null;
    this.toString_visited = -1;
    this.isString_visited = -1;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public StringExpr clone()
    throws CloneNotSupportedException
  {
    StringExpr localStringExpr = (StringExpr)super.clone();
    localStringExpr.dumpNode_String_visited = null;
    localStringExpr.toString_visited = -1;
    localStringExpr.isString_visited = -1;
    localStringExpr.in$Circle(false);
    localStringExpr.is$Final(false);
    return localStringExpr;
  }
  
  public StringExpr copy()
  {
    try
    {
      StringExpr localStringExpr = clone();
      if (this.children != null) {
        localStringExpr.children = ((ASTNode[])this.children.clone());
      }
      return localStringExpr;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public StringExpr fullCopy()
  {
    StringExpr localStringExpr = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localStringExpr.setChild(localASTNode, i);
    }
    return localStringExpr;
  }
  
  public String evaluateString()
  {
    return getVALUE();
  }
  
  public StringExpr(int paramInt)
  {
    super(paramInt);
  }
  
  public StringExpr(TransitionParser paramTransitionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramTransitionParser;
  }
  
  public StringExpr()
  {
    this(0);
  }
  
  public StringExpr(String paramString)
  {
    setVALUE(paramString);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "StringExpr" + "\"" + getVALUE() + "\"");
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
  
  public void checkChild(Node paramNode, int paramInt) {}
  
  public int getNumChild()
  {
    return 0;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public void setVALUE(String paramString)
  {
    this.tokenString_VALUE = paramString;
  }
  
  public String getVALUE()
  {
    return this.tokenString_VALUE != null ? this.tokenString_VALUE : "";
  }
  
  public String dumpNode(String paramString)
  {
    String str1 = paramString;
    if (this.dumpNode_String_visited == null) {
      this.dumpNode_String_visited = new HashMap(4);
    }
    ASTNode.State localState = state();
    if (Integer.valueOf(state().boundariesCrossed).equals(this.dumpNode_String_visited.get(str1))) {
      throw new RuntimeException("Circular definition of attr: dumpNode in class: ");
    }
    this.dumpNode_String_visited.put(str1, Integer.valueOf(state().boundariesCrossed));
    String str2 = dumpNode_compute(paramString);
    this.dumpNode_String_visited.remove(str1);
    return str2;
  }
  
  private String dumpNode_compute(String paramString)
  {
    return super.toString(paramString) + ": \"" + getVALUE() + "\"";
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
    return "\"" + getVALUE() + "\"";
  }
  
  public boolean isString()
  {
    ASTNode.State localState = state();
    if (this.isString_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: isString in class: ");
    }
    this.isString_visited = state().boundariesCrossed;
    boolean bool = isString_compute();
    this.isString_visited = -1;
    return bool;
  }
  
  private boolean isString_compute()
  {
    return true;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/StringExpr.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */