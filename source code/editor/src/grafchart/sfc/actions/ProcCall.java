package grafchart.sfc.actions;

import java.io.PrintStream;

public class ProcCall
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
  
  public ProcCall clone()
    throws CloneNotSupportedException
  {
    ProcCall localProcCall = (ProcCall)super.clone();
    localProcCall.in$Circle(false);
    localProcCall.is$Final(false);
    return localProcCall;
  }
  
  public ProcCall copy()
  {
    try
    {
      ProcCall localProcCall = clone();
      if (this.children != null) {
        localProcCall.children = ((ASTNode[])this.children.clone());
      }
      return localProcCall;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public ProcCall fullCopy()
  {
    ProcCall localProcCall = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localProcCall.setChild(localASTNode, i);
    }
    return localProcCall;
  }
  
  public String evaluateString()
  {
    return getExpr().evaluateString();
  }
  
  public ProcCall(int paramInt)
  {
    super(paramInt);
    is$Final(true);
  }
  
  public ProcCall(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
    is$Final(true);
  }
  
  public ProcCall()
  {
    this(0);
    is$Final(true);
  }
  
  public ProcCall(Expr paramExpr)
  {
    setChild(paramExpr, 0);
    is$Final(true);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "ProcCall");
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
      throw new Error("Child number 0 of ProcCall has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ProcCall.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */