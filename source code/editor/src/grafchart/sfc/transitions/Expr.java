package grafchart.sfc.transitions;

import grafchart.sfc.Referencable;
import grafchart.sfc.SymbolTableObject;
import grafchart.sfc.Utils;
import java.io.PrintStream;

public abstract class Expr
  extends ASTNode<ASTNode>
  implements Cloneable
{
  protected int localSymbolTable_visited = -1;
  protected int declMissing_visited = -1;
  protected int hasReferences_visited = -1;
  protected boolean hasReferences_computed = false;
  protected boolean hasReferences_value;
  protected int rewriteDotX_visited = -1;
  protected int staticDecl_visited = -1;
  protected boolean staticDecl_computed = false;
  protected Referencable staticDecl_value;
  
  public void flushCache()
  {
    super.flushCache();
    this.localSymbolTable_visited = -1;
    this.declMissing_visited = -1;
    this.hasReferences_visited = -1;
    this.hasReferences_computed = false;
    this.rewriteDotX_visited = -1;
    this.staticDecl_visited = -1;
    this.staticDecl_computed = false;
    this.staticDecl_value = null;
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Expr clone()
    throws CloneNotSupportedException
  {
    Expr localExpr = (Expr)super.clone();
    localExpr.localSymbolTable_visited = -1;
    localExpr.declMissing_visited = -1;
    localExpr.hasReferences_visited = -1;
    localExpr.hasReferences_computed = false;
    localExpr.rewriteDotX_visited = -1;
    localExpr.staticDecl_visited = -1;
    localExpr.staticDecl_computed = false;
    localExpr.staticDecl_value = null;
    localExpr.in$Circle(false);
    localExpr.is$Final(false);
    return localExpr;
  }
  
  public boolean isPureEventTriggered()
  {
    return false;
  }
  
  public boolean isEventTriggered(Event paramEvent)
  {
    return false;
  }
  
  public Referencable runtimeDecl()
  {
    if (hasReferences()) {
      return evaluateReference(root().symbolTable);
    }
    return staticDecl();
  }
  
  public Referencable evaluateReference(SymbolTableObject paramSymbolTableObject)
  {
    Utils.writeInternalError("evaluateReference() is not implemented for \"" + toString() + "\".");
    return null;
  }
  
  public Expr(int paramInt)
  {
    super(paramInt);
  }
  
  public Expr(TransitionParser paramTransitionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramTransitionParser;
  }
  
  public Expr()
  {
    this(0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Expr");
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
    return null;
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
    return false;
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
    return false;
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
    return false;
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
    try
    {
      throw new Exception();
    }
    catch (Exception localException)
    {
      Utils.writeException(localException, "staticDecl() is not implemented for \"" + toString() + "\".");
    }
    return null;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/Expr.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */