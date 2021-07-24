package grafchart.sfc.actions;

import java.io.PrintStream;

public class AbortiveAction
  extends GeneralAction
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
  
  public AbortiveAction clone()
    throws CloneNotSupportedException
  {
    AbortiveAction localAbortiveAction = (AbortiveAction)super.clone();
    localAbortiveAction.in$Circle(false);
    localAbortiveAction.is$Final(false);
    return localAbortiveAction;
  }
  
  public AbortiveAction copy()
  {
    try
    {
      AbortiveAction localAbortiveAction = clone();
      if (this.children != null) {
        localAbortiveAction.children = ((ASTNode[])this.children.clone());
      }
      return localAbortiveAction;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public AbortiveAction fullCopy()
  {
    AbortiveAction localAbortiveAction = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localAbortiveAction.setChild(localASTNode, i);
    }
    return localAbortiveAction;
  }
  
  public void executeAbortiveActions()
  {
    getStmt().execute();
  }
  
  public AbortiveAction(int paramInt)
  {
    super(paramInt);
  }
  
  public AbortiveAction(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public AbortiveAction()
  {
    this(0);
  }
  
  public AbortiveAction(Stmt paramStmt)
  {
    setChild(paramStmt, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "AbortiveAction");
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
    if ((paramInt == 0) && (!(paramNode instanceof Stmt))) {
      throw new Error("Child number 0 of GeneralAction has the type " + paramNode.getClass().getName() + " which is not an instance of Stmt");
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
  
  public void setStmt(Stmt paramStmt)
  {
    setChild(paramStmt, 0);
  }
  
  public Stmt getStmt()
  {
    return (Stmt)getChild(0);
  }
  
  public Stmt getStmtNoTransform()
  {
    return (Stmt)getChildNoTransform(0);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/AbortiveAction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */