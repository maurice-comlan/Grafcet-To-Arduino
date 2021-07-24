package grafchart.sfc.actions;

import grafchart.sfc.InternalVariable;
import grafchart.util.CompilationMessage;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.Set;

public class CallByReference
  extends ParameterAssignment
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
  
  public CallByReference clone()
    throws CloneNotSupportedException
  {
    CallByReference localCallByReference = (CallByReference)super.clone();
    localCallByReference.in$Circle(false);
    localCallByReference.is$Final(false);
    return localCallByReference;
  }
  
  public CallByReference copy()
  {
    try
    {
      CallByReference localCallByReference = clone();
      if (this.children != null) {
        localCallByReference.children = ((ASTNode[])this.children.clone());
      }
      return localCallByReference;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public CallByReference fullCopy()
  {
    CallByReference localCallByReference = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localCallByReference.setChild(localASTNode, i);
    }
    return localCallByReference;
  }
  
  public void initParameters()
  {
    InternalVariable localInternalVariable1 = (InternalVariable)getAssignment().getLeft().runtimeDecl();
    InternalVariable localInternalVariable2 = (InternalVariable)getAssignment().getRight().runtimeDecl();
    localInternalVariable1.setRedirect(localInternalVariable2);
  }
  
  public CallByReference(int paramInt)
  {
    super(paramInt);
  }
  
  public CallByReference(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public CallByReference()
  {
    this(0);
  }
  
  public CallByReference(Assignment paramAssignment)
  {
    setChild(paramAssignment, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "CallByReference");
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
    if ((paramInt == 0) && (!(paramNode instanceof Assignment))) {
      throw new Error("Child number 0 of ParameterAssignment has the type " + paramNode.getClass().getName() + " which is not an instance of Assignment");
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
  
  public void setAssignment(Assignment paramAssignment)
  {
    setChild(paramAssignment, 0);
  }
  
  public Assignment getAssignment()
  {
    return (Assignment)getChild(0);
  }
  
  public Assignment getAssignmentNoTransform()
  {
    return (Assignment)getChildNoTransform(0);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
  
  protected void collect_contributors_Goal_messages()
  {
    if ((!getAssignment().getRight().declMissing()) && (getAssignment().getRight().getVariable() == null))
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
    if ((!getAssignment().getRight().declMissing()) && (getAssignment().getRight().getVariable() == null)) {
      paramSortedList.add(createError("Cannot reference: \"" + getAssignment().getRight() + "\". Only variables can be referenced."));
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/CallByReference.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */