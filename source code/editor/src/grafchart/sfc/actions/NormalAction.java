package grafchart.sfc.actions;

import grafchart.sfc.BooleanVariable;
import grafchart.sfc.DigitalOut;
import grafchart.sfc.Referencable;
import grafchart.sfc.SocketBoolOut;
import grafchart.sfc.Utils;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.Set;

public class NormalAction
  extends ActionGC
  implements Cloneable
{
  public void flushCache()
  {
    super.flushCache();
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public NormalAction clone()
    throws CloneNotSupportedException
  {
    NormalAction localNormalAction = (NormalAction)super.clone();
    localNormalAction.in$Circle(false);
    localNormalAction.is$Final(false);
    return localNormalAction;
  }
  
  public NormalAction copy()
  {
    try
    {
      NormalAction localNormalAction = clone();
      if (this.children != null) {
        localNormalAction.children = ((ASTNode[])this.children.clone());
      }
      return localNormalAction;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public NormalAction fullCopy()
  {
    NormalAction localNormalAction = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localNormalAction.setChild(localASTNode, i);
    }
    return localNormalAction;
  }
  
  public void executeNormalActions(boolean paramBoolean)
  {
    Referencable localReferencable = getExpr().runtimeDecl();
    Object localObject;
    if ((localReferencable instanceof DigitalOut))
    {
      localObject = (DigitalOut)localReferencable;
      ((DigitalOut)localObject).setNormalAction(paramBoolean);
    }
    else if ((localReferencable instanceof BooleanVariable))
    {
      localObject = (BooleanVariable)localReferencable;
      ((BooleanVariable)localObject).setNormalAction(paramBoolean);
    }
    else if ((localReferencable instanceof SocketBoolOut))
    {
      ((SocketBoolOut)localReferencable).setNormalAction(paramBoolean);
    }
    else
    {
      Utils.writeError("N action not supported for \"" + localReferencable.getFullName() + "\".");
    }
  }
  
  public void effectuateNormalActions()
  {
    Referencable localReferencable = getExpr().runtimeDecl();
    Object localObject;
    if ((localReferencable instanceof DigitalOut))
    {
      localObject = (DigitalOut)localReferencable;
      ((DigitalOut)localObject).effectuateNormalActions();
    }
    else if ((localReferencable instanceof BooleanVariable))
    {
      localObject = (BooleanVariable)localReferencable;
      ((BooleanVariable)localObject).effectuateNormalActions();
    }
    else if ((localReferencable instanceof SocketBoolOut))
    {
      ((SocketBoolOut)localReferencable).effectuateNormalActions();
    }
  }
  
  public NormalAction(int paramInt)
  {
    super(paramInt);
  }
  
  public NormalAction(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public NormalAction()
  {
    this(0);
  }
  
  public NormalAction(Expr paramExpr)
  {
    setChild(paramExpr, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "NormalAction");
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
      throw new Error("Child number 0 of NormalAction has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
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
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
  
  protected void collect_contributors_Goal_messages()
  {
    if (getExpr().getVariable() == null)
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
    if (getExpr().getVariable() == null) {
      paramSortedList.add(createError("Normal action statement is not a variable: \"" + getExpr() + "\"."));
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/NormalAction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */