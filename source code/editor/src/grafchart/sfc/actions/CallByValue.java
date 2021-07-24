package grafchart.sfc.actions;

import java.io.PrintStream;

public class CallByValue
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
  
  public CallByValue clone()
    throws CloneNotSupportedException
  {
    CallByValue localCallByValue = (CallByValue)super.clone();
    localCallByValue.in$Circle(false);
    localCallByValue.is$Final(false);
    return localCallByValue;
  }
  
  public CallByValue copy()
  {
    try
    {
      CallByValue localCallByValue = clone();
      if (this.children != null) {
        localCallByValue.children = ((ASTNode[])this.children.clone());
      }
      return localCallByValue;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public CallByValue fullCopy()
  {
    CallByValue localCallByValue = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localCallByValue.setChild(localASTNode, i);
    }
    return localCallByValue;
  }
  
  public void initParameters()
  {
    getAssignment().execute();
  }
  
  public CallByValue(int paramInt)
  {
    super(paramInt);
  }
  
  public CallByValue(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public CallByValue()
  {
    this(0);
  }
  
  public CallByValue(Assignment paramAssignment)
  {
    setChild(paramAssignment, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "CallByValue");
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
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/CallByValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */