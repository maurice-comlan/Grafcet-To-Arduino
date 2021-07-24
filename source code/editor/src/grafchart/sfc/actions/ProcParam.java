package grafchart.sfc.actions;

import java.io.PrintStream;

public class ProcParam
  extends Goal
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
  
  public ProcParam clone()
    throws CloneNotSupportedException
  {
    ProcParam localProcParam = (ProcParam)super.clone();
    localProcParam.in$Circle(false);
    localProcParam.is$Final(false);
    return localProcParam;
  }
  
  public ProcParam copy()
  {
    try
    {
      ProcParam localProcParam = clone();
      if (this.children != null) {
        localProcParam.children = ((ASTNode[])this.children.clone());
      }
      return localProcParam;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public ProcParam fullCopy()
  {
    ProcParam localProcParam = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localProcParam.setChild(localASTNode, i);
    }
    return localProcParam;
  }
  
  public void initParameters()
  {
    for (int i = 0; i < getNumParameterAssignment(); i++) {
      getParameterAssignment(i).initParameters();
    }
  }
  
  public ProcParam(int paramInt)
  {
    super(paramInt);
    is$Final(true);
  }
  
  public ProcParam(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
    is$Final(true);
  }
  
  public ProcParam()
  {
    this(0);
    setChild(new List(), 0);
    is$Final(true);
  }
  
  public ProcParam(List<ParameterAssignment> paramList)
  {
    setChild(paramList, 0);
    is$Final(true);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "ProcParam");
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
        throw new Error("Child number 0 of ProcParam has the type " + paramNode.getClass().getName() + " which is not an instance of List");
      }
      for (int i = 0; i < ((List)paramNode).getNumChildNoTransform(); i++) {
        if (!(((List)paramNode).getChildNoTransform(i) instanceof ParameterAssignment)) {
          throw new Error("Child number " + i + " in ParameterAssignmentList has the type " + ((List)paramNode).getChildNoTransform(i).getClass().getName() + " which is not an instance of ParameterAssignment");
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
  
  public void setParameterAssignmentList(List<ParameterAssignment> paramList)
  {
    setChild(paramList, 0);
  }
  
  public int getNumParameterAssignment()
  {
    return getParameterAssignmentList().getNumChild();
  }
  
  public ParameterAssignment getParameterAssignment(int paramInt)
  {
    return (ParameterAssignment)getParameterAssignmentList().getChild(paramInt);
  }
  
  public void addParameterAssignment(ParameterAssignment paramParameterAssignment)
  {
    List localList = (this.parent == null) || (state == null) ? getParameterAssignmentListNoTransform() : getParameterAssignmentList();
    localList.addChild(paramParameterAssignment);
  }
  
  public void addParameterAssignmentNoTransform(ParameterAssignment paramParameterAssignment)
  {
    List localList = getParameterAssignmentListNoTransform();
    localList.addChild(paramParameterAssignment);
  }
  
  public void setParameterAssignment(ParameterAssignment paramParameterAssignment, int paramInt)
  {
    List localList = getParameterAssignmentList();
    localList.setChild(paramParameterAssignment, paramInt);
  }
  
  public List<ParameterAssignment> getParameterAssignments()
  {
    return getParameterAssignmentList();
  }
  
  public List<ParameterAssignment> getParameterAssignmentsNoTransform()
  {
    return getParameterAssignmentListNoTransform();
  }
  
  public List<ParameterAssignment> getParameterAssignmentList()
  {
    List localList = (List)getChild(0);
    localList.getNumChild();
    return localList;
  }
  
  public List<ParameterAssignment> getParameterAssignmentListNoTransform()
  {
    return (List)getChildNoTransform(0);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ProcParam.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */