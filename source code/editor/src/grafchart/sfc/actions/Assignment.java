package grafchart.sfc.actions;

import grafchart.sfc.Hierarchical;
import grafchart.sfc.InternalVariable;
import grafchart.sfc.Utils;
import grafchart.sfc.Writable;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.Set;

public class Assignment
  extends Stmt
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
  
  public Assignment clone()
    throws CloneNotSupportedException
  {
    Assignment localAssignment = (Assignment)super.clone();
    localAssignment.in$Circle(false);
    localAssignment.is$Final(false);
    return localAssignment;
  }
  
  public Assignment copy()
  {
    try
    {
      Assignment localAssignment = clone();
      if (this.children != null) {
        localAssignment.children = ((ASTNode[])this.children.clone());
      }
      return localAssignment;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Assignment fullCopy()
  {
    Assignment localAssignment = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localAssignment.setChild(localASTNode, i);
    }
    return localAssignment;
  }
  
  public void execute()
  {
    Writable localWritable = Utils.referencableToWritable(getLeft().runtimeDecl());
    if (localWritable != null) {
      if (((localWritable instanceof InternalVariable)) && (((InternalVariable)localWritable).isConstant()))
      {
        Utils.writeError("Cannot assign to constant variable \"" + localWritable.getFullName() + "\".");
      }
      else if (localWritable.isBoolean())
      {
        boolean bool = getRight().evaluateBoolean();
        localWritable.setStoredBoolAction(bool);
      }
      else if (localWritable.isInteger())
      {
        int i = getRight().evaluateInt();
        localWritable.setStoredIntAction(i);
      }
      else if (localWritable.isString())
      {
        String str = getRight().evaluateString();
        localWritable.setStoredStringAction(str);
      }
      else if (localWritable.isReal())
      {
        double d = getRight().evaluateReal();
        localWritable.setStoredRealAction(d);
      }
      else if ((localWritable instanceof Hierarchical))
      {
        Hierarchical localHierarchical1 = (Hierarchical)localWritable;
        Hierarchical localHierarchical2 = getRight().evaluateWorkspace();
        if (localHierarchical2 != null) {
          localHierarchical1.assignWorkspace(localHierarchical2);
        }
      }
    }
  }
  
  public Assignment(int paramInt)
  {
    super(paramInt);
  }
  
  public Assignment(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public Assignment()
  {
    this(0);
  }
  
  public Assignment(Expr paramExpr1, Expr paramExpr2)
  {
    setChild(paramExpr1, 0);
    setChild(paramExpr2, 1);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Assignment");
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
      throw new Error("Child number 0 of Assignment has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
    }
    if ((paramInt == 1) && (!(paramNode instanceof Expr))) {
      throw new Error("Child number 1 of Assignment has the type " + paramNode.getClass().getName() + " which is not an instance of Expr");
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
  
  public boolean Define_boolean_localLookupOnly(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getLeftNoTransform()) {
      return root() instanceof ProcParam;
    }
    return getParent().Define_boolean_localLookupOnly(this, paramASTNode1);
  }
  
  public boolean Define_boolean_localLookupSkip(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getRightNoTransform()) {
      return root() instanceof ProcParam;
    }
    return getParent().Define_boolean_localLookupSkip(this, paramASTNode1);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
  
  protected void collect_contributors_Goal_messages()
  {
    Goal localGoal;
    if (getLeft().getVariable() == null)
    {
      localGoal = root();
      if (localGoal != null) {
        localGoal.Goal_messages_contributors().add(this);
      }
    }
    if ((!getLeft().hasReferences()) && (!getLeft().declMissing()) && (getLeft().getVariable() != null) && (!(getLeft().getVariable().decl() instanceof Writable)))
    {
      localGoal = root();
      if (localGoal != null) {
        localGoal.Goal_messages_contributors().add(this);
      }
    }
    if ((!getLeft().declMissing()) && (getLeft().getVariable() != null) && ((getLeft().getVariable().decl() instanceof InternalVariable)) && (((InternalVariable)getLeft().getVariable().decl()).isConstant()))
    {
      localGoal = root();
      if (localGoal != null) {
        localGoal.Goal_messages_contributors().add(this);
      }
    }
    if ((!getLeft().hasReferences()) && (!getRight().hasReferences()) && (getLeft().isWorkspace()) && (!getRight().isWorkspace()))
    {
      localGoal = root();
      if (localGoal != null) {
        localGoal.Goal_messages_contributors().add(this);
      }
    }
    super.collect_contributors_Goal_messages();
  }
  
  protected void contributeTo_Goal_Goal_messages(SortedList<CompilationMessage> paramSortedList)
  {
    super.contributeTo_Goal_Goal_messages(paramSortedList);
    if (getLeft().getVariable() == null) {
      paramSortedList.add(createError("Assignment target is not a variable: \"" + getLeft() + "\"."));
    }
    if ((!getLeft().hasReferences()) && (!getLeft().declMissing()) && (getLeft().getVariable() != null) && (!(getLeft().getVariable().decl() instanceof Writable))) {
      paramSortedList.add(createError("\"" + getLeft() + "\" cannot be assigned."));
    }
    if ((!getLeft().declMissing()) && (getLeft().getVariable() != null) && ((getLeft().getVariable().decl() instanceof InternalVariable)) && (((InternalVariable)getLeft().getVariable().decl()).isConstant())) {
      paramSortedList.add(createError("Assignment to constant variable \"" + getLeft() + "\"."));
    }
    if ((!getLeft().hasReferences()) && (!getRight().hasReferences()) && (getLeft().isWorkspace()) && (!getRight().isWorkspace())) {
      paramSortedList.add(createError("Cannot assign \"" + getRight() + "\" to workspace variable \"" + getLeft() + "\"."));
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Assignment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */