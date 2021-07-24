package grafchart.sfc.transitions;

import grafchart.dpws.db.DPWSPortType;
import grafchart.sfc.DPWSObject;
import grafchart.sfc.Readable;
import grafchart.sfc.Referencable;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.Set;

public abstract class Event
  extends UnExpr
  implements Cloneable
{
  protected int rewriteDotX_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.rewriteDotX_visited = -1;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Event clone()
    throws CloneNotSupportedException
  {
    Event localEvent = (Event)super.clone();
    localEvent.rewriteDotX_visited = -1;
    localEvent.in$Circle(false);
    localEvent.is$Final(false);
    return localEvent;
  }
  
  public boolean evaluateBoolean()
  {
    Referencable localReferencable = getExpr().runtimeDecl();
    if (localReferencable != null)
    {
      if (isPureEvent(localReferencable)) {
        return getExpr().isPureEventTriggered();
      }
      return getExpr().isEventTriggered(this);
    }
    return false;
  }
  
  public abstract boolean isEventTriggered(boolean paramBoolean1, boolean paramBoolean2);
  
  public Event(int paramInt)
  {
    super(paramInt);
  }
  
  public Event(TransitionParser paramTransitionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramTransitionParser;
  }
  
  public Event()
  {
    this(0);
  }
  
  public Event(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Event");
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
  
  public abstract boolean isPureEvent(Referencable paramReferencable);
  
  public boolean rewriteDotX()
  {
    ASTNode.State localState = state();
    if (this.rewriteDotX_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: rewriteDotX in class: ");
    }
    this.rewriteDotX_visited = state().boundariesCrossed;
    boolean bool = rewriteDotX_compute();
    this.rewriteDotX_visited = -1;
    return bool;
  }
  
  private boolean rewriteDotX_compute()
  {
    return getExpr().rewriteDotX();
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
  
  protected void collect_contributors_Goal_messages()
  {
    Start localStart;
    if ((!getExpr().hasReferences()) && (!getExpr().declMissing()) && (!isPureEvent(getExpr().staticDecl())) && (!(getExpr().staticDecl() instanceof Readable)))
    {
      localStart = root();
      if (localStart != null) {
        localStart.Goal_messages_contributors().add(this);
      }
    }
    if ((!getExpr().hasReferences()) && (!getExpr().declMissing()) && ((getExpr().staticDecl() instanceof DPWSObject)) && (((DPWSObject)getExpr().staticDecl()).getPortType() != null) && (!((DPWSObject)getExpr().staticDecl()).getPortType().isEventSource()))
    {
      localStart = root();
      if (localStart != null) {
        localStart.Goal_messages_contributors().add(this);
      }
    }
    super.collect_contributors_Goal_messages();
  }
  
  protected void contributeTo_Goal_Goal_messages(SortedList<CompilationMessage> paramSortedList)
  {
    super.contributeTo_Goal_Goal_messages(paramSortedList);
    if ((!getExpr().hasReferences()) && (!getExpr().declMissing()) && (!isPureEvent(getExpr().staticDecl())) && (!(getExpr().staticDecl() instanceof Readable))) {
      paramSortedList.add(createError("Events are not supported for \"" + getExpr().staticDecl() + "\"."));
    }
    if ((!getExpr().hasReferences()) && (!getExpr().declMissing()) && ((getExpr().staticDecl() instanceof DPWSObject)) && (((DPWSObject)getExpr().staticDecl()).getPortType() != null) && (!((DPWSObject)getExpr().staticDecl()).getPortType().isEventSource())) {
      paramSortedList.add(createError("The bound porttype for \"" + getExpr().staticDecl() + "\" does not support eventing."));
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/Event.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */