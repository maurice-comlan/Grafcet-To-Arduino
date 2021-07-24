package grafchart.sfc.transitions;

import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

public abstract class Goal
  extends ASTNode<ASTNode>
  implements Cloneable
{
  protected int Goal_messages_visited = -1;
  protected boolean Goal_messages_computed = false;
  protected SortedList<CompilationMessage> Goal_messages_value;
  Set Goal_messages_contributors;
  
  public void flushCache()
  {
    super.flushCache();
    this.Goal_messages_visited = -1;
    this.Goal_messages_computed = false;
    this.Goal_messages_value = null;
    this.Goal_messages_contributors = null;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
    this.Goal_messages_visited = -1;
    this.Goal_messages_computed = false;
    this.Goal_messages_value = null;
    this.Goal_messages_contributors = null;
  }
  
  public Goal clone()
    throws CloneNotSupportedException
  {
    Goal localGoal = (Goal)super.clone();
    localGoal.in$Circle(false);
    localGoal.is$Final(false);
    return localGoal;
  }
  
  public Goal(int paramInt)
  {
    super(paramInt);
  }
  
  public Goal(TransitionParser paramTransitionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramTransitionParser;
  }
  
  public Goal()
  {
    this(0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Goal");
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
  
  public SortedList<CompilationMessage> messages()
  {
    if (this.Goal_messages_computed) {
      return this.Goal_messages_value;
    }
    ASTNode.State localState = state();
    if (this.Goal_messages_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: messages in class: ");
    }
    this.Goal_messages_visited = state().boundariesCrossed;
    int i = localState.boundariesCrossed;
    boolean bool = is$Final();
    this.Goal_messages_value = messages_compute();
    if ((bool) && (i == state().boundariesCrossed)) {
      this.Goal_messages_computed = true;
    }
    this.Goal_messages_visited = -1;
    return this.Goal_messages_value;
  }
  
  public Set Goal_messages_contributors()
  {
    if (this.Goal_messages_contributors == null) {
      this.Goal_messages_contributors = new ASTNode.State.IdentityHashSet(4);
    }
    return this.Goal_messages_contributors;
  }
  
  private SortedList<CompilationMessage> messages_compute()
  {
      Object localObject;
    for (localObject = this; (((ASTNode)localObject).getParent() != null) && (!(localObject instanceof Start)); localObject = ((ASTNode)localObject).getParent()) {}
    Start localStart = (Start)localObject;
    localStart.collect_contributors_Goal_messages();
    this.Goal_messages_value = new SortedList();
    if (this.Goal_messages_contributors != null)
    {
      Iterator localIterator = this.Goal_messages_contributors.iterator();
      while (localIterator.hasNext())
      {
        ASTNode localASTNode = (ASTNode)localIterator.next();
        localASTNode.contributeTo_Goal_Goal_messages(this.Goal_messages_value);
      }
    }
    return this.Goal_messages_value;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/Goal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */