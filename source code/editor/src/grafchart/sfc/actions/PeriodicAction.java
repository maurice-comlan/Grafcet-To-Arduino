package grafchart.sfc.actions;

import java.io.PrintStream;

public class PeriodicAction
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
  
  public PeriodicAction clone()
    throws CloneNotSupportedException
  {
    PeriodicAction localPeriodicAction = (PeriodicAction)super.clone();
    localPeriodicAction.in$Circle(false);
    localPeriodicAction.is$Final(false);
    return localPeriodicAction;
  }
  
  public PeriodicAction copy()
  {
    try
    {
      PeriodicAction localPeriodicAction = clone();
      if (this.children != null) {
        localPeriodicAction.children = ((ASTNode[])this.children.clone());
      }
      return localPeriodicAction;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public PeriodicAction fullCopy()
  {
    PeriodicAction localPeriodicAction = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localPeriodicAction.setChild(localASTNode, i);
    }
    return localPeriodicAction;
  }
  
  public void executePeriodicActions()
  {
    getStmt().execute();
  }
  
  public PeriodicAction(int paramInt)
  {
    super(paramInt);
  }
  
  public PeriodicAction(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public PeriodicAction()
  {
    this(0);
  }
  
  public PeriodicAction(Stmt paramStmt)
  {
    setChild(paramStmt, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "PeriodicAction");
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/PeriodicAction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */