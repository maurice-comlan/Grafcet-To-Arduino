package grafchart.sfc.actions;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Number
  extends Expr
  implements Cloneable
{
  protected String tokenString_VALUE;
  protected Map dumpNode_String_visited;
  protected int toString_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.dumpNode_String_visited = null;
    this.toString_visited = -1;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Number clone()
    throws CloneNotSupportedException
  {
    Number localNumber = (Number)super.clone();
    localNumber.dumpNode_String_visited = null;
    localNumber.toString_visited = -1;
    localNumber.in$Circle(false);
    localNumber.is$Final(false);
    return localNumber;
  }
  
  public Number copy()
  {
    try
    {
      Number localNumber = clone();
      if (this.children != null) {
        localNumber.children = ((ASTNode[])this.children.clone());
      }
      return localNumber;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Number fullCopy()
  {
    Number localNumber = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localNumber.setChild(localASTNode, i);
    }
    return localNumber;
  }
  
  public String evaluateString()
  {
    return getVALUE();
  }
  
  public boolean evaluateBoolean()
  {
    int i = Integer.parseInt(getVALUE());
    return i == 1;
  }
  
  public double evaluateReal()
  {
    return Double.parseDouble(getVALUE());
  }
  
  public Number(int paramInt)
  {
    super(paramInt);
  }
  
  public Number(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public Number()
  {
    this(0);
  }
  
  public Number(String paramString)
  {
    setVALUE(paramString);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Number" + "\"" + getVALUE() + "\"");
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
    return super.toString(paramString) + ": " + getVALUE();
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
    return getVALUE();
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Number.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */