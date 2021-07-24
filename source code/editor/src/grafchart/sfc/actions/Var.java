package grafchart.sfc.actions;

import grafchart.sfc.Hierarchical;
import grafchart.sfc.LabCommObject;
import grafchart.sfc.Readable;
import grafchart.sfc.Referencable;
import grafchart.sfc.SymbolTableObject;
import grafchart.sfc.Utils;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import grafchart.util.SymbolTableHelper;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Var
  extends Expr
  implements Cloneable
{
  protected String tokenString_ID;
  protected int decl_visited = -1;
  protected boolean decl_computed = false;
  protected Referencable decl_value;
  protected int localSymbolTable_visited = -1;
  protected int isWorkspace_visited = -1;
  protected int declMissing_visited = -1;
  protected int getVariable_visited = -1;
  protected Map dumpNode_String_visited;
  protected int toString_visited = -1;
  protected int symbolTable_visited = -1;
  protected int localLookupOnly_visited = -1;
  protected int localLookupSkip_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.decl_visited = -1;
    this.decl_computed = false;
    this.decl_value = null;
    this.localSymbolTable_visited = -1;
    this.isWorkspace_visited = -1;
    this.declMissing_visited = -1;
    this.getVariable_visited = -1;
    this.dumpNode_String_visited = null;
    this.toString_visited = -1;
    this.staticDecl_visited = -1;
    this.staticDecl_computed = false;
    this.staticDecl_value = null;
    this.symbolTable_visited = -1;
    this.localLookupOnly_visited = -1;
    this.localLookupSkip_visited = -1;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Var clone()
    throws CloneNotSupportedException
  {
    Var localVar = (Var)super.clone();
    localVar.decl_visited = -1;
    localVar.decl_computed = false;
    localVar.decl_value = null;
    localVar.localSymbolTable_visited = -1;
    localVar.isWorkspace_visited = -1;
    localVar.declMissing_visited = -1;
    localVar.getVariable_visited = -1;
    localVar.dumpNode_String_visited = null;
    localVar.toString_visited = -1;
    localVar.staticDecl_visited = -1;
    localVar.staticDecl_computed = false;
    localVar.staticDecl_value = null;
    localVar.symbolTable_visited = -1;
    localVar.localLookupOnly_visited = -1;
    localVar.localLookupSkip_visited = -1;
    localVar.in$Circle(false);
    localVar.is$Final(false);
    return localVar;
  }
  
  public Var copy()
  {
    try
    {
      Var localVar = clone();
      if (this.children != null) {
        localVar.children = ((ASTNode[])this.children.clone());
      }
      return localVar;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Var fullCopy()
  {
    Var localVar = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localVar.setChild(localASTNode, i);
    }
    return localVar;
  }
  
  public void collectReferences(ArrayList paramArrayList)
  {
    paramArrayList.add(decl());
  }
  
  public boolean evaluateBoolean()
  {
    return ((Readable)staticDecl()).getBoolVal();
  }
  
  public int evaluateInt()
  {
    return ((Readable)staticDecl()).getIntVal();
  }
  
  public double evaluateReal()
  {
    return ((Readable)staticDecl()).getRealVal();
  }
  
  public String evaluateString()
  {
    return ((Readable)staticDecl()).getStringVal();
  }
  
  public Referencable evaluateReference(SymbolTableObject paramSymbolTableObject)
  {
    if ((paramSymbolTableObject != null) && (!localLookupOnly()) && (localLookupSkip())) {
      paramSymbolTableObject = SymbolTableHelper.getParentSymbolTableObject(paramSymbolTableObject);
    }
    return SymbolTableHelper.lookup(paramSymbolTableObject, getID(), localLookupOnly(), true);
  }
  
  public Var(int paramInt)
  {
    super(paramInt);
  }
  
  public Var(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public Var()
  {
    this(0);
  }
  
  public Var(String paramString)
  {
    setID(paramString);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Var" + "\"" + getID() + "\"");
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
    return true;
  }
  
  public void setID(String paramString)
  {
    this.tokenString_ID = paramString;
  }
  
  public String getID()
  {
    return this.tokenString_ID != null ? this.tokenString_ID : "";
  }
  
  public Referencable decl()
  {
    if (this.decl_computed) {
      return this.decl_value;
    }
    ASTNode.State localState = state();
    if (this.decl_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: decl in class: ");
    }
    this.decl_visited = state().boundariesCrossed;
    int i = localState.boundariesCrossed;
    boolean bool = is$Final();
    this.decl_value = decl_compute();
    if ((bool) && (i == state().boundariesCrossed)) {
      this.decl_computed = true;
    }
    this.decl_visited = -1;
    return this.decl_value;
  }
  
  private Referencable decl_compute()
  {
    SymbolTableObject localSymbolTableObject = symbolTable();
    if ((localSymbolTableObject != null) && (!localLookupOnly()) && (localLookupSkip())) {
      localSymbolTableObject = SymbolTableHelper.getParentSymbolTableObject(localSymbolTableObject);
    }
    return SymbolTableHelper.lookup(localSymbolTableObject, getID(), localLookupOnly());
  }
  
  public SymbolTableObject localSymbolTable()
  {
    ASTNode.State localState = state();
    if (this.localSymbolTable_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: localSymbolTable in class: ");
    }
    this.localSymbolTable_visited = state().boundariesCrossed;
    SymbolTableObject localSymbolTableObject = localSymbolTable_compute();
    this.localSymbolTable_visited = -1;
    return localSymbolTableObject;
  }
  
  private SymbolTableObject localSymbolTable_compute()
  {
    return Utils.referencableToSymbolTableObject(decl(), false);
  }
  
  public boolean isWorkspace()
  {
    ASTNode.State localState = state();
    if (this.isWorkspace_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: isWorkspace in class: ");
    }
    this.isWorkspace_visited = state().boundariesCrossed;
    boolean bool = isWorkspace_compute();
    this.isWorkspace_visited = -1;
    return bool;
  }
  
  private boolean isWorkspace_compute()
  {
    return decl() instanceof Hierarchical;
  }
  
  public boolean declMissing()
  {
    ASTNode.State localState = state();
    if (this.declMissing_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: declMissing in class: ");
    }
    this.declMissing_visited = state().boundariesCrossed;
    boolean bool = declMissing_compute();
    this.declMissing_visited = -1;
    return bool;
  }
  
  private boolean declMissing_compute()
  {
    return (decl() == null) || (((decl() instanceof LabCommObject)) && (((LabCommObject)decl()).getSymbolTable().isEmpty()));
  }
  
  public Var getVariable()
  {
    ASTNode.State localState = state();
    if (this.getVariable_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: getVariable in class: ");
    }
    this.getVariable_visited = state().boundariesCrossed;
    Var localVar = getVariable_compute();
    this.getVariable_visited = -1;
    return localVar;
  }
  
  private Var getVariable_compute()
  {
    return this;
  }
  
  public String dumpNode(String paramString)
  {
    String str1 = paramString;
    if (this.dumpNode_String_visited == null) {
      this.dumpNode_String_visited = new HashMap(4);
    }
    ASTNode.State localState = state();
    if (Integer.valueOf(state().boundariesCrossed).equals(this.dumpNode_String_visited.get(str1))) {
      throw new RuntimeException("Circular definition of attr: dumpNode in class: ");
    }
    this.dumpNode_String_visited.put(str1, Integer.valueOf(state().boundariesCrossed));
    String str2 = dumpNode_compute(paramString);
    this.dumpNode_String_visited.remove(str1);
    return str2;
  }
  
  private String dumpNode_compute(String paramString)
  {
    return super.toString(paramString) + ": " + getID();
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
    return getID();
  }
  
  public Referencable staticDecl()
  {
    if (this.staticDecl_computed) {
      return this.staticDecl_value;
    }
    ASTNode.State localState = state();
    if (this.staticDecl_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: staticDecl in class: ");
    }
    this.staticDecl_visited = state().boundariesCrossed;
    int i = localState.boundariesCrossed;
    boolean bool = is$Final();
    this.staticDecl_value = staticDecl_compute();
    if ((bool) && (i == state().boundariesCrossed)) {
      this.staticDecl_computed = true;
    }
    this.staticDecl_visited = -1;
    return this.staticDecl_value;
  }
  
  private Referencable staticDecl_compute()
  {
    return decl();
  }
  
  public SymbolTableObject symbolTable()
  {
    ASTNode.State localState = state();
    if (this.symbolTable_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: symbolTable in class: ");
    }
    this.symbolTable_visited = state().boundariesCrossed;
    SymbolTableObject localSymbolTableObject = getParent().Define_SymbolTableObject_symbolTable(this, null);
    this.symbolTable_visited = -1;
    return localSymbolTableObject;
  }
  
  public boolean localLookupOnly()
  {
    ASTNode.State localState = state();
    if (this.localLookupOnly_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: localLookupOnly in class: ");
    }
    this.localLookupOnly_visited = state().boundariesCrossed;
    boolean bool = getParent().Define_boolean_localLookupOnly(this, null);
    this.localLookupOnly_visited = -1;
    return bool;
  }
  
  public boolean localLookupSkip()
  {
    ASTNode.State localState = state();
    if (this.localLookupSkip_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: localLookupSkip in class: ");
    }
    this.localLookupSkip_visited = state().boundariesCrossed;
    boolean bool = getParent().Define_boolean_localLookupSkip(this, null);
    this.localLookupSkip_visited = -1;
    return bool;
  }
  
  public ASTNode rewriteTo()
  {
    if (getID().equalsIgnoreCase("t"))
    {
      state().duringRewrites += 1;
      Function localFunction = rewriteRule0();
      state().duringRewrites -= 1;
      return localFunction;
    }
    return super.rewriteTo();
  }
  
  private Function rewriteRule0()
  {
    return new Function(getID().toLowerCase(), new List());
  }
  
  protected void collect_contributors_Goal_messages()
  {
    if ((decl() == null) && (symbolTable() != null))
    {
      Goal localGoal = root();
      if (localGoal != null) {
        localGoal.Goal_messages_contributors().add(this);
      }
    }
    super.collect_contributors_Goal_messages();
  }
  
  protected void contributeTo_Goal_Goal_messages(SortedList<CompilationMessage> paramSortedList)
  {
    super.contributeTo_Goal_Goal_messages(paramSortedList);
    if ((decl() == null) && (symbolTable() != null)) {
      paramSortedList.add(createError("Missing declaration of \"" + getID() + "\"."));
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Var.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */