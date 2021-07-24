package grafchart.sfc.actions;

import java.io.PrintStream;

public class StoredAction
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
  
  public StoredAction clone()
    throws CloneNotSupportedException
  {
    StoredAction localStoredAction = (StoredAction)super.clone();
    localStoredAction.in$Circle(false);
    localStoredAction.is$Final(false);
    return localStoredAction;
  }
  
  public StoredAction copy()
  {
    try
    {
      StoredAction localStoredAction = clone();
      if (this.children != null) {
        localStoredAction.children = ((ASTNode[])this.children.clone());
      }
      return localStoredAction;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public StoredAction fullCopy()
  {
    StoredAction localStoredAction = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localStoredAction.setChild(localASTNode, i);
    }
    return localStoredAction;
  }
  
  public void executeStoredActions()
  {
    getStmt().execute();
  }
  
  public StoredAction(int paramInt)
  {
    super(paramInt);
  }
  
  public StoredAction(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public StoredAction()
  {
    this(0);
  }
  
  public StoredAction(Stmt paramStmt)
  {
    setChild(paramStmt, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "StoredAction");
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/StoredAction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */