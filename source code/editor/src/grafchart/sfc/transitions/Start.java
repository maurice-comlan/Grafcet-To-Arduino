package grafchart.sfc.transitions;

import grafchart.sfc.SymbolTableObject;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.Iterator;

public class Start
  extends Goal
  implements Cloneable
{
  public SymbolTableObject symbolTable;
  private boolean collect_contributors_Goal_messages = false;
  protected int root_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.root_visited = -1;
    this.collect_contributors_Goal_messages = false;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
    this.collect_contributors_Goal_messages = false;
  }
  
  public Start clone()
    throws CloneNotSupportedException
  {
    Start localStart = (Start)super.clone();
    localStart.root_visited = -1;
    localStart.in$Circle(false);
    localStart.is$Final(false);
    return localStart;
  }
  
  public Start copy()
  {
    try
    {
      Start localStart = clone();
      if (this.children != null) {
        localStart.children = ((ASTNode[])this.children.clone());
      }
      return localStart;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Start fullCopy()
  {
    Start localStart = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localStart.setChild(localASTNode, i);
    }
    return localStart;
  }
  
  public boolean compile(SymbolTableObject paramSymbolTableObject)
  {
    if ((paramSymbolTableObject == null) || (paramSymbolTableObject.getSymbolTable() == null))
    {
      System.out.println("Internal error: SymbolTable was null in call to transitions compiler.");
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
  
  public boolean evaluateBoolean()
  {
    return getExpr().evaluateBoolean();
  }
  
  public Start(int paramInt)
  {
    super(paramInt);
    is$Final(true);
  }
  
  public Start(TransitionParser paramTransitionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramTransitionParser;
    is$Final(true);
  }
  
  public Start()
  {
    this(0);
    is$Final(true);
  }
  
  public Start(Expr paramExpr)
  {
    setChild(paramExpr, 0);
    is$Final(true);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Start");
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
      throw new Error("Child number 0 of Start has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
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
  
  protected void collect_contributors_Goal_messages()
  {
    if (this.collect_contributors_Goal_messages) {
      return;
    }
    super.collect_contributors_Goal_messages();
    this.collect_contributors_Goal_messages = true;
  }
  
  public Start root()
  {
    ASTNode.State localState = state();
    if (this.root_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: root in class: ");
    }
    this.root_visited = state().boundariesCrossed;
    Start localStart = root_compute();
    this.root_visited = -1;
    return localStart;
  }
  
  private Start root_compute()
  {
    return this;
  }
  
  public SymbolTableObject Define_SymbolTableObject_symbolTable(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getExprNoTransform()) {
      return this.symbolTable;
    }
    return getParent().Define_SymbolTableObject_symbolTable(this, paramASTNode1);
  }
  
  public boolean Define_boolean_localLookupOnly(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getExprNoTransform()) {
      return false;
    }
    return getParent().Define_boolean_localLookupOnly(this, paramASTNode1);
  }
  
  public Object Define_Object_calledOn(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getExprNoTransform()) {
      return null;
    }
    return getParent().Define_Object_calledOn(this, paramASTNode1);
  }
  
  public Start Define_Start_root(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    int i = getIndexOfChild(paramASTNode1);
    return this;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/Start.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */