package grafchart.sfc.actions;

import java.io.PrintStream;

public class Statement
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
  
  public Statement clone()
    throws CloneNotSupportedException
  {
    Statement localStatement = (Statement)super.clone();
    localStatement.in$Circle(false);
    localStatement.is$Final(false);
    return localStatement;
  }
  
  public Statement copy()
  {
    try
    {
      Statement localStatement = clone();
      if (this.children != null) {
        localStatement.children = ((ASTNode[])this.children.clone());
      }
      return localStatement;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Statement fullCopy()
  {
    Statement localStatement = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localStatement.setChild(localASTNode, i);
    }
    return localStatement;
  }
  
  public void executeNormalActions(boolean paramBoolean)
  {
    for (int i = 0; i < getNumAction(); i++) {
      getAction(i).executeNormalActions(paramBoolean);
    }
  }
  
  public void effectuateNormalActions()
  {
    for (int i = 0; i < getNumAction(); i++) {
      getAction(i).effectuateNormalActions();
    }
  }
  
  public void executeStoredActions()
  {
    for (int i = 0; i < getNumAction(); i++) {
      getAction(i).executeStoredActions();
    }
  }
  
  public void executeExitActions()
  {
    for (int i = 0; i < getNumAction(); i++) {
      getAction(i).executeExitActions();
    }
  }
  
  public void executePeriodicActions()
  {
    for (int i = 0; i < getNumAction(); i++) {
      getAction(i).executePeriodicActions();
    }
  }
  
  public void executeAbortiveActions()
  {
    for (int i = 0; i < getNumAction(); i++) {
      getAction(i).executeAbortiveActions();
    }
  }
  
  public Statement(int paramInt)
  {
    super(paramInt);
    is$Final(true);
  }
  
  public Statement(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
    is$Final(true);
  }
  
  public Statement()
  {
    this(0);
    setChild(new List(), 0);
    is$Final(true);
  }
  
  public Statement(List<ActionGC> paramList)
  {
    setChild(paramList, 0);
    is$Final(true);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Statement");
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
    if (paramInt == 0)
    {
      if (!(paramNode instanceof List)) {
        throw new Error("Child number 0 of Statement has the type " + paramNode.getClass().getName() + " which is not an instance of List");
      }
      for (int i = 0; i < ((List)paramNode).getNumChildNoTransform(); i++) {
        if (!(((List)paramNode).getChildNoTransform(i) instanceof ActionGC)) {
          throw new Error("Child number " + i + " in ActionList has the type " + ((List)paramNode).getChildNoTransform(i).getClass().getName() + " which is not an instance of Action");
        }
      }
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
  
  public void setActionList(List<ActionGC> paramList)
  {
    setChild(paramList, 0);
  }
  
  public int getNumAction()
  {
    return getActionList().getNumChild();
  }
  
  public ActionGC getAction(int paramInt)
  {
    return (ActionGC)getActionList().getChild(paramInt);
  }
  
  public void addAction(ActionGC paramAction)
  {
    List localList = (this.parent == null) || (state == null) ? getActionListNoTransform() : getActionList();
    localList.addChild(paramAction);
  }
  
  public void addActionNoTransform(ActionGC paramAction)
  {
    List localList = getActionListNoTransform();
    localList.addChild(paramAction);
  }
  
  public void setAction(ActionGC paramAction, int paramInt)
  {
    List localList = getActionList();
    localList.setChild(paramAction, paramInt);
  }
  
  public List<ActionGC> getActions()
  {
    return getActionList();
  }
  
  public List<ActionGC> getActionsNoTransform()
  {
    return getActionListNoTransform();
  }
  
  public List<ActionGC> getActionList()
  {
    List localList = (List)getChild(0);
    localList.getNumChild();
    return localList;
  }
  
  public List<ActionGC> getActionListNoTransform()
  {
    return (List)getChildNoTransform(0);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Statement.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */