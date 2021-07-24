package grafchart.sfc.transitions;

import grafchart.sfc.Referencable;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class EventDown
  extends Event
  implements Cloneable
{
  protected Map isPureEvent_Referencable_visited;
  protected int toString_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.isPureEvent_Referencable_visited = null;
    this.toString_visited = -1;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public EventDown clone()
    throws CloneNotSupportedException
  {
    EventDown localEventDown = (EventDown)super.clone();
    localEventDown.isPureEvent_Referencable_visited = null;
    localEventDown.toString_visited = -1;
    localEventDown.in$Circle(false);
    localEventDown.is$Final(false);
    return localEventDown;
  }
  
  public EventDown copy()
  {
    try
    {
      EventDown localEventDown = clone();
      if (this.children != null) {
        localEventDown.children = ((ASTNode[])this.children.clone());
      }
      return localEventDown;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public EventDown fullCopy()
  {
    EventDown localEventDown = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localEventDown.setChild(localASTNode, i);
    }
    return localEventDown;
  }
  
  public boolean isEventTriggered(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (paramBoolean1) && (!paramBoolean2);
  }
  
  public EventDown(int paramInt)
  {
    super(paramInt);
  }
  
  public EventDown(TransitionParser paramTransitionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramTransitionParser;
  }
  
  public EventDown()
  {
    this(0);
  }
  
  public EventDown(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "EventDown");
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
    return true;
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
  
  public boolean isPureEvent(Referencable paramReferencable)
  {
    Referencable localReferencable = paramReferencable;
    if (this.isPureEvent_Referencable_visited == null) {
      this.isPureEvent_Referencable_visited = new HashMap(4);
    }
    ASTNode.State localState = state();
    if (Integer.valueOf(state().boundariesCrossed).equals(this.isPureEvent_Referencable_visited.get(localReferencable))) {
      throw new RuntimeException("Circular definition of attr: isPureEvent in class: ");
    }
    this.isPureEvent_Referencable_visited.put(localReferencable, Integer.valueOf(state().boundariesCrossed));
    boolean bool = isPureEvent_compute(paramReferencable);
    this.isPureEvent_Referencable_visited.remove(localReferencable);
    return bool;
  }
  
  private boolean isPureEvent_compute(Referencable paramReferencable)
  {
    return false;
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
    return "\\" + getExpr();
  }
  
  public ASTNode rewriteTo()
  {
    if (rewriteDotX())
    {
      state().duringRewrites += 1;
      Dot localDot = rewriteRule0();
      state().duringRewrites -= 1;
      return localDot;
    }
    return super.rewriteTo();
  }
  
  private Dot rewriteRule0()
  {
    return new Dot(((Dot)getExpr()).getLeft(), new Function("xEventDown", new List()));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/EventDown.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */