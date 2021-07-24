package grafchart.sfc.actions;

import java.io.PrintStream;

public class Opt<T extends ASTNode>
  extends ASTNode<T>
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
  
  public Opt<T> clone()
    throws CloneNotSupportedException
  {
    Opt localOpt = (Opt)super.clone();
    localOpt.in$Circle(false);
    localOpt.is$Final(false);
    return localOpt;
  }
  
  public Opt<T> copy()
  {
    try
    {
      Opt localOpt = clone();
      if (this.children != null) {
        localOpt.children = ((ASTNode[])this.children.clone());
      }
      return localOpt;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Opt<T> fullCopy()
  {
    Opt localOpt = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localOpt.setChild(localASTNode, i);
    }
    return localOpt;
  }
  
  public Opt(int paramInt)
  {
    super(paramInt);
  }
  
  public Opt(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public Opt()
  {
    this(0);
  }
  
  public Opt(T paramT)
  {
    setChild(paramT, 0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Opt");
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
    if (paramInt > 0) {
      throw new Error("Optional nodes can only have one child");
    }
    if (!(paramNode instanceof ASTNode)) {
      throw new Error("Node type must be an instance of ASTNode");
    }
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Opt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */