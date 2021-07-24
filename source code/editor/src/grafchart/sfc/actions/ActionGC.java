package grafchart.sfc.actions;

import java.io.PrintStream;

public abstract class ActionGC
  extends ASTNode<ASTNode>
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
  
  public ActionGC clone()
    throws CloneNotSupportedException
  {
    ActionGC localAction = (ActionGC)super.clone();
    localAction.in$Circle(false);
    localAction.is$Final(false);
    return localAction;
  }
  
  public void executeNormalActions(boolean paramBoolean) {}
  
  public void effectuateNormalActions() {}
  
  public void executeStoredActions() {}
  
  public void executePeriodicActions() {}
  
  public void executeExitActions() {}
  
  public void executeAbortiveActions() {}
  
  public ActionGC(int paramInt)
  {
    super(paramInt);
  }
  
  public ActionGC(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public ActionGC()
  {
    this(0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Action");
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
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Action.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */