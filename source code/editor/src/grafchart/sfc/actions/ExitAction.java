package grafchart.sfc.actions;

import java.io.PrintStream;

public class ExitAction
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
  
  public ExitAction clone()
    throws CloneNotSupportedException
  {
    ExitAction localExitAction = (ExitAction)super.clone();
    localExitAction.in$Circle(false);
    localExitAction.is$Final(false);
    return localExitAction;
  }
  
  public ExitAction copy()
  {
    try
    {
      ExitAction localExitAction = clone();
      if (this.children != null) {
        localExitAction.children = ((ASTNode[])this.children.clone());
      }
      return localExitAction;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public ExitAction fullCopy()
  {
    ExitAction localExitAction = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localExitAction.setChild(localASTNode, i);
    }
    return localExitAction;
  }
  
  public void executeExitActions()
  {
    getStmt().execute();
  }
  
  public ExitAction(int paramInt)
  {
    super(paramInt);
  }
  
  public ExitAction(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public ExitAction()
  {
    this(0);
  }
  
  public ExitAction(Stmt paramStmt)
  {
    setChild(paramStmt, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "ExitAction");
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ExitAction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */