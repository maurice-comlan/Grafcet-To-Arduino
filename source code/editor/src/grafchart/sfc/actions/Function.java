package grafchart.sfc.actions;

import grafchart.sfc.SymbolTableObject;
import grafchart.sfc.builtin.BuiltInFunctions;
import grafchart.sfc.builtin.BuiltInFunctions.Language;
import grafchart.sfc.builtin.Evaluatable;
import grafchart.sfc.builtin.Executable;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Function
  extends Expr
  implements Cloneable
{
  protected String tokenString_ID;
  protected int decl_visited = -1;
  protected boolean decl_computed = false;
  protected Executable decl_value;
  protected int getFunction_visited = -1;
  protected Map dumpNode_String_visited;
  protected int toString_visited = -1;
  protected Map calledOn_String_visited;
  
  public void flushCache()
  {
    super.flushCache();
    this.decl_visited = -1;
    this.decl_computed = false;
    this.decl_value = null;
    this.getFunction_visited = -1;
    this.dumpNode_String_visited = null;
    this.toString_visited = -1;
    this.calledOn_String_visited = null;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Function clone()
    throws CloneNotSupportedException
  {
    Function localFunction = (Function)super.clone();
    localFunction.decl_visited = -1;
    localFunction.decl_computed = false;
    localFunction.decl_value = null;
    localFunction.getFunction_visited = -1;
    localFunction.dumpNode_String_visited = null;
    localFunction.toString_visited = -1;
    localFunction.calledOn_String_visited = null;
    localFunction.in$Circle(false);
    localFunction.is$Final(false);
    return localFunction;
  }
  
  public Function copy()
  {
    try
    {
      Function localFunction = clone();
      if (this.children != null) {
        localFunction.children = ((ASTNode[])this.children.clone());
      }
      return localFunction;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Function fullCopy()
  {
    Function localFunction = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localFunction.setChild(localASTNode, i);
    }
    return localFunction;
  }
  
  public void collectReferences(ArrayList paramArrayList)
  {
    paramArrayList.add(calledOn(getID()));
  }
  
  public Evaluatable[] argumentsToArray()
  {
    Evaluatable[] arrayOfEvaluatable = new Evaluatable[getNumArgument()];
    for (int i = 0; i < getNumArgument(); i++) {
      arrayOfEvaluatable[i] = getArgument(i);
    }
    return arrayOfEvaluatable;
  }
  
  public String evaluateString()
  {
    return decl().executeString(argumentsToArray(), calledOn(getID()));
  }
  
  public double evaluateReal()
  {
    return decl().executeReal(argumentsToArray(), calledOn(getID()));
  }
  
  public int evaluateInt()
  {
    return (int)Math.round(evaluateReal());
  }
  
  public boolean evaluateBoolean()
  {
    return decl().executeBool(argumentsToArray(), calledOn(getID()));
  }
  
  public Function(int paramInt)
  {
    super(paramInt);
  }
  
  public Function(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public Function()
  {
    this(0);
    setChild(new List(), 0);
  }
  
  public Function(String paramString, List<Expr> paramList)
  {
    setID(paramString);
    setChild(paramList, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Function" + "\"" + getID() + "\"");
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
        throw new Error("Child number 0 of Function has the type " + paramNode.getClass().getName() + " which is not an instance of List");
      }
      for (int i = 0; i < ((List)paramNode).getNumChildNoTransform(); i++) {
        if (!(((List)paramNode).getChildNoTransform(i) instanceof Expr)) {
          throw new Error("Child number " + i + " in ArgumentList has the type " + ((List)paramNode).getChildNoTransform(i).getClass().getName() + " which is not an instance of Expr");
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
  
  public void setID(String paramString)
  {
    this.tokenString_ID = paramString;
  }
  
  public String getID()
  {
    return this.tokenString_ID != null ? this.tokenString_ID : "";
  }
  
  public void setArgumentList(List<Expr> paramList)
  {
    setChild(paramList, 0);
  }
  
  public int getNumArgument()
  {
    return getArgumentList().getNumChild();
  }
  
  public Expr getArgument(int paramInt)
  {
    return (Expr)getArgumentList().getChild(paramInt);
  }
  
  public void addArgument(Expr paramExpr)
  {
    List localList = (this.parent == null) || (state == null) ? getArgumentListNoTransform() : getArgumentList();
    localList.addChild(paramExpr);
  }
  
  public void addArgumentNoTransform(Expr paramExpr)
  {
    List localList = getArgumentListNoTransform();
    localList.addChild(paramExpr);
  }
  
  public void setArgument(Expr paramExpr, int paramInt)
  {
    List localList = getArgumentList();
    localList.setChild(paramExpr, paramInt);
  }
  
  public List<Expr> getArguments()
  {
    return getArgumentList();
  }
  
  public List<Expr> getArgumentsNoTransform()
  {
    return getArgumentListNoTransform();
  }
  
  public List<Expr> getArgumentList()
  {
    List localList = (List)getChild(0);
    localList.getNumChild();
    return localList;
  }
  
  public List<Expr> getArgumentListNoTransform()
  {
    return (List)getChildNoTransform(0);
  }
  
  public Executable decl()
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
  
  private Executable decl_compute()
  {
    if (BuiltInFunctions.getError(getID(), argumentsToArray(), calledOn(getID()), BuiltInFunctions.Language.Action) == null) {
      return BuiltInFunctions.getExecutable(getID(), calledOn(getID()));
    }
    return null;
  }
  
  public Function getFunction()
  {
    ASTNode.State localState = state();
    if (this.getFunction_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: getFunction in class: ");
    }
    this.getFunction_visited = state().boundariesCrossed;
    Function localFunction = getFunction_compute();
    this.getFunction_visited = -1;
    return localFunction;
  }
  
  private Function getFunction_compute()
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
    String str = getID() + "(";
    for (int i = 0; i < getNumArgument(); i++)
    {
      if (i > 0) {
        str = str + ", ";
      }
      str = str + getArgument(i);
    }
    str = str + ")";
    return str;
  }
  
  public Object calledOn(String paramString)
  {
    String str = paramString;
    if (this.calledOn_String_visited == null) {
      this.calledOn_String_visited = new HashMap(4);
    }
    ASTNode.State localState = state();
    if (Integer.valueOf(state().boundariesCrossed).equals(this.calledOn_String_visited.get(str))) {
      throw new RuntimeException("Circular definition of attr: calledOn in class: ");
    }
    this.calledOn_String_visited.put(str, Integer.valueOf(state().boundariesCrossed));
    Object localObject = getParent().Define_Object_calledOn(this, null, paramString);
    this.calledOn_String_visited.remove(str);
    return localObject;
  }
  
  public SymbolTableObject Define_SymbolTableObject_symbolTable(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getArgumentListNoTransform())
    {
      int i = paramASTNode1.getIndexOfChild(paramASTNode2);
      return root().symbolTable;
    }
    return getParent().Define_SymbolTableObject_symbolTable(this, paramASTNode1);
  }
  
  public boolean Define_boolean_localLookupOnly(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getArgumentListNoTransform())
    {
      int i = paramASTNode1.getIndexOfChild(paramASTNode2);
      return false;
    }
    return getParent().Define_boolean_localLookupOnly(this, paramASTNode1);
  }
  
  public Object Define_Object_calledOn(ASTNode paramASTNode1, ASTNode paramASTNode2, String paramString)
  {
    if (paramASTNode1 == getArgumentListNoTransform())
    {
      int i = paramASTNode1.getIndexOfChild(paramASTNode2);
      return null;
    }
    return getParent().Define_Object_calledOn(this, paramASTNode1, paramString);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
  
  protected void collect_contributors_Goal_messages()
  {
    if ((decl() == null) && ((!(calledOn(getID()) instanceof Var)) || (((Var)calledOn(getID())).decl() != null)) && (!(calledOn(getID()) instanceof Reference)))
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
    if ((decl() == null) && ((!(calledOn(getID()) instanceof Var)) || (((Var)calledOn(getID())).decl() != null)) && (!(calledOn(getID()) instanceof Reference))) {
      paramSortedList.add(createError(BuiltInFunctions.getError(getID(), argumentsToArray(), calledOn(getID()), BuiltInFunctions.Language.Action)));
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Function.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */