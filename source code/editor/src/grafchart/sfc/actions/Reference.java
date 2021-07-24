package grafchart.sfc.actions;

import grafchart.sfc.Readable;
import grafchart.sfc.Referencable;
import grafchart.sfc.SymbolTableObject;
import grafchart.sfc.Utils;
import grafchart.util.ActionCompiler;
import grafchart.util.ActionCompiler.CompileType;
import grafchart.util.SymbolTableHelper;
import java.io.PrintStream;

public class Reference
  extends Expr
  implements Cloneable
{
  protected int isWorkspace_visited = -1;
  protected int getVariable_visited = -1;
  protected int toString_visited = -1;
  protected int localLookupOnly_visited = -1;
  protected int localLookupSkip_visited = -1;
  
  public void flushCache()
  {
    super.flushCache();
    this.isWorkspace_visited = -1;
    this.hasReferences_visited = -1;
    this.hasReferences_computed = false;
    this.getVariable_visited = -1;
    this.toString_visited = -1;
    this.localLookupOnly_visited = -1;
    this.localLookupSkip_visited = -1;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Reference clone()
    throws CloneNotSupportedException
  {
    Reference localReference = (Reference)super.clone();
    localReference.isWorkspace_visited = -1;
    localReference.hasReferences_visited = -1;
    localReference.hasReferences_computed = false;
    localReference.getVariable_visited = -1;
    localReference.toString_visited = -1;
    localReference.localLookupOnly_visited = -1;
    localReference.localLookupSkip_visited = -1;
    localReference.in$Circle(false);
    localReference.is$Final(false);
    return localReference;
  }
  
  public Reference copy()
  {
    try
    {
      Reference localReference = clone();
      if (this.children != null) {
        localReference.children = ((ASTNode[])this.children.clone());
      }
      return localReference;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Reference fullCopy()
  {
    Reference localReference = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localReference.setChild(localASTNode, i);
    }
    return localReference;
  }
  
  public boolean evaluateBoolean()
  {
    Readable localReadable = Utils.referencableToReadable(evaluateReference(root().symbolTable));
    if (localReadable != null) {
      return localReadable.getBoolVal();
    }
    return false;
  }
  
  public String evaluateString()
  {
    Readable localReadable = Utils.referencableToReadable(evaluateReference(root().symbolTable));
    if (localReadable != null) {
      return localReadable.getStringVal();
    }
    return "";
  }
  
  public double evaluateReal()
  {
    Readable localReadable = Utils.referencableToReadable(evaluateReference(root().symbolTable));
    if (localReadable != null) {
      return localReadable.getRealVal();
    }
    return 0.0D;
  }
  
  public int evaluateInt()
  {
    Readable localReadable = Utils.referencableToReadable(evaluateReference(root().symbolTable));
    if (localReadable != null) {
      return localReadable.getIntVal();
    }
    return 0;
  }
  
  public Referencable evaluateReference(SymbolTableObject paramSymbolTableObject)
  {
    Referencable localReferencable = null;
    Readable localReadable = Utils.referencableToReadable(getExpr().evaluateReference(paramSymbolTableObject));
    if (localReadable != null)
    {
      String str = localReadable.getStringVal();
      if ((paramSymbolTableObject != null) && (!localLookupOnly()) && (localLookupSkip())) {
        paramSymbolTableObject = SymbolTableHelper.getParentSymbolTableObject(paramSymbolTableObject);
      }
      grafchart.sfc.Editor.printWarnings = true;
      SingleExpression localSingleExpression = (SingleExpression)ActionCompiler.compile(str, paramSymbolTableObject, ActionCompiler.CompileType.SingleExpression, "runtime reference evaluation", null);
      grafchart.sfc.Editor.printWarnings = false;
      if (localSingleExpression != null) {
        localReferencable = localSingleExpression.getExpr().runtimeDecl();
      } else {
        Utils.writeInformation("Expression \"" + str + "\", Context \"" + paramSymbolTableObject.getFullName() + "\".");
      }
    }
    return localReferencable;
  }
  
  public Reference(int paramInt)
  {
    super(paramInt);
  }
  
  public Reference(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public Reference()
  {
    this(0);
  }
  
  public Reference(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Reference");
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
    if ((paramInt == 0) && (!(paramNode instanceof Expr))) {
      throw new Error("Child number 0 of Reference has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
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
    return true;
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
    return true;
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
    return getExpr().getVariable();
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
    return getExpr().toString() + "^";
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
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Reference.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */