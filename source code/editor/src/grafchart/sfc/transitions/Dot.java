package grafchart.sfc.transitions;

import grafchart.sfc.Hierarchical;
import grafchart.sfc.LabCommObject;
import grafchart.sfc.Readable;
import grafchart.sfc.Referencable;
import grafchart.sfc.SymbolTableObject;
import grafchart.sfc.Utils;
import grafchart.sfc.Utils.DataType;
import grafchart.sfc.builtin.AbstractExecutable;
import grafchart.sfc.builtin.BuiltInFunctions;
import grafchart.sfc.builtin.BuiltInFunctions.Language;
import grafchart.sfc.builtin.Executable;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.Set;

public class Dot
  extends BinExpr
  implements Cloneable
{
  private boolean retBool;
  private String retString;
  private double retReal;
  private int retInt;
  protected int localSymbolTable_visited = -1;
  protected int declMissing_visited = -1;
  protected int rewriteDotX_visited = -1;
  protected int opToString_visited = -1;
  protected int isString_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.localSymbolTable_visited = -1;
    this.declMissing_visited = -1;
    this.hasReferences_visited = -1;
    this.hasReferences_computed = false;
    this.rewriteDotX_visited = -1;
    this.opToString_visited = -1;
    this.isString_visited = -1;
    this.staticDecl_visited = -1;
    this.staticDecl_computed = false;
    this.staticDecl_value = null;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Dot clone()
    throws CloneNotSupportedException
  {
    Dot localDot = (Dot)super.clone();
    localDot.localSymbolTable_visited = -1;
    localDot.declMissing_visited = -1;
    localDot.hasReferences_visited = -1;
    localDot.hasReferences_computed = false;
    localDot.rewriteDotX_visited = -1;
    localDot.opToString_visited = -1;
    localDot.isString_visited = -1;
    localDot.staticDecl_visited = -1;
    localDot.staticDecl_computed = false;
    localDot.staticDecl_value = null;
    localDot.in$Circle(false);
    localDot.is$Final(false);
    return localDot;
  }
  
  public Dot copy()
  {
    try
    {
      Dot localDot = clone();
      if (this.children != null) {
        localDot.children = ((ASTNode[])this.children.clone());
      }
      return localDot;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Dot fullCopy()
  {
    Dot localDot = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localDot.setChild(localASTNode, i);
    }
    return localDot;
  }
  
  public boolean evaluateBoolean()
  {
    if (hasReferences())
    {
      evaluate(Utils.DataType.eBool);
      return this.retBool;
    }
    return getRight().evaluateBoolean();
  }
  
  public String evaluateString()
  {
    if (hasReferences())
    {
      evaluate(Utils.DataType.eString);
      return this.retString;
    }
    return getRight().evaluateString();
  }
  
  public double evaluateReal()
  {
    if (hasReferences())
    {
      evaluate(Utils.DataType.eReal);
      return this.retReal;
    }
    return getRight().evaluateReal();
  }
  
  public int evaluateInt()
  {
    if (hasReferences())
    {
      evaluate(Utils.DataType.eInt);
      return this.retInt;
    }
    return getRight().evaluateInt();
  }
  
  private void evaluate(Utils.DataType paramDataType)
  {
    this.retBool = false;
    this.retString = "";
    this.retReal = 0.0D;
    this.retInt = 0;
    Object localObject;
    if ((getRight() instanceof Function))
    {
      localObject = getLeft().evaluateReference(root().symbolTable);
      execute((Referencable)localObject, paramDataType);
    }
    else
    {
      localObject = Utils.referencableToReadable(evaluateReference(root().symbolTable));
      if (localObject != null) {
        switch (paramDataType)
        {
        case eBool: 
          this.retBool = ((Readable)localObject).getBoolVal();
          break;
        case eString: 
          this.retString = ((Readable)localObject).getStringVal();
          break;
        case eReal: 
          this.retReal = ((Readable)localObject).getRealVal();
          break;
        case eInt: 
          this.retInt = ((Readable)localObject).getIntVal();
          break;
        default: 
          Utils.writeInternalError("Dot.evaluate() called with illegal datatype \"" + paramDataType + "\".");
        }
      }
    }
  }
  
  private void execute(Referencable paramReferencable, Utils.DataType paramDataType)
  {
    Function localFunction = (Function)getRight();
    AbstractExecutable localAbstractExecutable = null;
    if (paramReferencable != null)
    {
      String str = BuiltInFunctions.getError(localFunction.getID(), localFunction.argumentsToArray(), paramReferencable, BuiltInFunctions.Language.Transition);
      if (str == null)
      {
        localAbstractExecutable = BuiltInFunctions.getExecutable(localFunction.getID(), paramReferencable);
        if (localAbstractExecutable != null) {
          switch (paramDataType)
          {
          case eBool: 
            this.retBool = localAbstractExecutable.executeBool(localFunction.argumentsToArray(), paramReferencable);
            break;
          case eString: 
            this.retString = localAbstractExecutable.executeString(localFunction.argumentsToArray(), paramReferencable);
            break;
          case eReal: 
            this.retReal = localAbstractExecutable.executeReal(localFunction.argumentsToArray(), paramReferencable);
            break;
          case eInt: 
            this.retInt = ((int)localAbstractExecutable.executeReal(localFunction.argumentsToArray(), paramReferencable));
            break;
          default: 
            Utils.writeInternalError("Dot.execute() called with illegal datatype \"" + paramDataType + "\".");
            break;
          }
        }
      }
      else
      {
        Utils.writeError(str);
      }
    }
  }
  
  public boolean isEventTriggered(Event paramEvent)
  {
    if (hasReferences())
    {
      Readable localReadable = Utils.referencableToReadable(evaluateReference(root().symbolTable));
      if (localReadable != null) {
        return paramEvent.isEventTriggered(localReadable.getOldBoolVal(), localReadable.getBoolVal());
      }
    }
    else
    {
      return getRight().isEventTriggered(paramEvent);
    }
    return super.isEventTriggered(paramEvent);
  }
  
  public Referencable evaluateReference(SymbolTableObject paramSymbolTableObject)
  {
    Referencable localReferencable = getLeft().evaluateReference(paramSymbolTableObject);
    Object localObject = Utils.referencableToSymbolTableObject(localReferencable);
    if ((localObject instanceof Hierarchical)) {
      localObject = ((Hierarchical)localObject).getContentDocument();
    } else if (!(localObject instanceof LabCommObject)) {
      Utils.writeDebug("Dot.evaluateReference() had unexpected localSymbolTable. Trying anyway...");
    }
    if (localObject != null) {
      return getRight().evaluateReference((SymbolTableObject)localObject);
    }
    return null;
  }
  
  public Dot(int paramInt)
  {
    super(paramInt);
  }
  
  public Dot(TransitionParser paramTransitionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramTransitionParser;
  }
  
  public Dot()
  {
    this(0);
  }
  
  public Dot(Expr paramExpr1, Expr paramExpr2)
  {
    setChild(paramExpr1, 0);
    setChild(paramExpr2, 1);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Dot");
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
      throw new Error("Child number 0 of BinExpr has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
    }
    if ((paramInt == 1) && (!(paramNode instanceof Expr))) {
      throw new Error("Child number 1 of BinExpr has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
    }
  }
  
  public int getNumChild()
  {
    return 2;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public void setLeft(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public Expr getLeft()
  {
    return (Expr)getChild(0);
  }
  
  public Expr getLeftNoTransform()
  {
    return (Expr)getChildNoTransform(0);
  }
  
  public void setRight(Expr paramExpr)
  {
    setChild(paramExpr, 1);
  }
  
  public Expr getRight()
  {
    return (Expr)getChild(1);
  }
  
  public Expr getRightNoTransform()
  {
    return (Expr)getChildNoTransform(1);
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
    return getRight().localSymbolTable();
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
    return (getLeft().declMissing()) || (getRight().declMissing());
  }
  
  public boolean hasReferences()
  {
    if (this.hasReferences_computed) {
      return this.hasReferences_value;
    }
    ASTNode.State localState = state();
    if (this.hasReferences_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: hasReferences in class: ");
    }
    this.hasReferences_visited = state().boundariesCrossed;
    int i = localState.boundariesCrossed;
    boolean bool = is$Final();
    this.hasReferences_value = hasReferences_compute();
    if ((bool) && (i == state().boundariesCrossed)) {
      this.hasReferences_computed = true;
    }
    this.hasReferences_visited = -1;
    return this.hasReferences_value;
  }
  
  private boolean hasReferences_compute()
  {
    return (getLeft().hasReferences()) || (getRight().hasReferences());
  }
  
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
    return getRight().rewriteDotX();
  }
  
  public String opToString()
  {
    ASTNode.State localState = state();
    if (this.opToString_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: opToString in class: ");
    }
    this.opToString_visited = state().boundariesCrossed;
    String str = opToString_compute();
    this.opToString_visited = -1;
    return str;
  }
  
  private String opToString_compute()
  {
    return ".";
  }
  
  public boolean isString()
  {
    ASTNode.State localState = state();
    if (this.isString_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: isString in class: ");
    }
    this.isString_visited = state().boundariesCrossed;
    boolean bool = isString_compute();
    this.isString_visited = -1;
    return bool;
  }
  
  private boolean isString_compute()
  {
    return getRight().isString();
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
    return getRight().staticDecl();
  }
  
  public SymbolTableObject Define_SymbolTableObject_symbolTable(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getRightNoTransform()) {
      return getLeft().localSymbolTable();
    }
    return getParent().Define_SymbolTableObject_symbolTable(this, paramASTNode1);
  }
  
  public boolean Define_boolean_localLookupOnly(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getRightNoTransform()) {
      return true;
    }
    return getParent().Define_boolean_localLookupOnly(this, paramASTNode1);
  }
  
  public Object Define_Object_calledOn(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getRightNoTransform())
    {
      Expr localExpr;
      if ((getLeft() instanceof Dot)) {
        localExpr = ((Dot)getLeft()).getRight();
      } else {
        localExpr = getLeft();
      }
      if (((localExpr instanceof Var)) && (((Var)localExpr).decl() != null)) {
        return ((Var)localExpr).decl();
      }
      return localExpr;
    }
    return getParent().Define_Object_calledOn(this, paramASTNode1);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
  
  protected void collect_contributors_Goal_messages()
  {
    Start localStart;
    if ((!(getRight() instanceof Function)) && (!getLeft().hasReferences()) && (!getLeft().declMissing()) && (getLeft().localSymbolTable() == null))
    {
      localStart = root();
      if (localStart != null) {
        localStart.Goal_messages_contributors().add(this);
      }
    }
    if ((!(getRight() instanceof Var)) && (!(getRight() instanceof Reference)) && (!(getRight() instanceof Function)))
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
    if ((!(getRight() instanceof Function)) && (!getLeft().hasReferences()) && (!getLeft().declMissing()) && (getLeft().localSymbolTable() == null)) {
      paramSortedList.add(createError("Dot lhs must be a workspace unless rhs is a function call. Lhs was \"" + getLeft().toString() + "\"."));
    }
    if ((!(getRight() instanceof Var)) && (!(getRight() instanceof Reference)) && (!(getRight() instanceof Function))) {
      paramSortedList.add(createError("Dot rhs must be a variable, reference or function call. Lhs \"" + getLeft().toString() + "\" has rhs \"" + getRight().toString() + "\"."));
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/Dot.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */