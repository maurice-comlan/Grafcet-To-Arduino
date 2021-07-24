package grafchart.sfc.actions;

import grafchart.sfc.SymbolTableObject;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

public abstract class Goal
  extends ASTNode<ASTNode>
  implements Cloneable
{
  public SymbolTableObject symbolTable;
  private boolean collect_contributors_Goal_messages = false;
  protected int root_visited = -1;
  protected int Goal_messages_visited = -1;
  protected boolean Goal_messages_computed = false;
  protected SortedList<CompilationMessage> Goal_messages_value;
  Set Goal_messages_contributors;
  
  public void flushCache()
  {
    super.flushCache();
    this.root_visited = -1;
    this.Goal_messages_visited = -1;
    this.Goal_messages_computed = false;
    this.Goal_messages_value = null;
    this.Goal_messages_contributors = null;
    this.collect_contributors_Goal_messages = false;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
    this.Goal_messages_visited = -1;
    this.Goal_messages_computed = false;
    this.Goal_messages_value = null;
    this.Goal_messages_contributors = null;
    this.collect_contributors_Goal_messages = false;
  }
  
  public Goal clone()
    throws CloneNotSupportedException
  {
    Goal localGoal = (Goal)super.clone();
    localGoal.root_visited = -1;
    localGoal.in$Circle(false);
    localGoal.is$Final(false);
    return localGoal;
  }
  
  public boolean compile(SymbolTableObject paramSymbolTableObject)
  {
    if ((paramSymbolTableObject == null) || (paramSymbolTableObject.getSymbolTable() == null))
    {
      System.out.println("Internal error: SymbolTable was null in call to actions compiler.");
      return false;
    }
    this.symbolTable = paramSymbolTableObject;
    Iterator localIterator = messages().iterator();
    while (localIterator.hasNext())
    {
      CompilationMessage localCompilationMessage = (CompilationMessage)localIterator.next();
      if (localCompilationMessage.isError()) {
        return false;
      }
    }
    return true;
  }
  
  public Goal(int paramInt)
  {
    super(paramInt);
  }
  
  public Goal(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
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
  
  protected void collect_contributors_Goal_messages()
  {
    if (this.collect_contributors_Goal_messages) {
      return;
    }
    super.collect_contributors_Goal_messages();
    this.collect_contributors_Goal_messages = true;
  }
  
  public Goal root()
  {
    ASTNode.State localState = state();
    if (this.root_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: root in class: ");
    }
    this.root_visited = state().boundariesCrossed;
    Goal localGoal = root_compute();
    this.root_visited = -1;
    return localGoal;
  }
  
  private Goal root_compute()
  {
    return this;
  }
  
  public SymbolTableObject Define_SymbolTableObject_symbolTable(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    int i = getIndexOfChild(paramASTNode1);
    return this.symbolTable;
  }
  
  public boolean Define_boolean_localLookupOnly(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    int i = getIndexOfChild(paramASTNode1);
    return false;
  }
  
  public boolean Define_boolean_localLookupSkip(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    int i = getIndexOfChild(paramASTNode1);
    return false;
  }
  
  public Object Define_Object_calledOn(ASTNode paramASTNode1, ASTNode paramASTNode2, String paramString)
  {
    int i = getIndexOfChild(paramASTNode1);
    if (paramString.equals("getTickTime")) {
      return this.symbolTable;
    }
    return null;
  }
  
  public Goal Define_Goal_root(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    int i = getIndexOfChild(paramASTNode1);
    return this;
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
    for (localObject = this; (((ASTNode)localObject).getParent() != null) && (!(localObject instanceof Goal)); localObject = ((ASTNode)localObject).getParent()) {}
    Goal localGoal = (Goal)localObject;
    localGoal.collect_contributors_Goal_messages();
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Goal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */