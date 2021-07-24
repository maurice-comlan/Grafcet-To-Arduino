package AST;

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
  
  public Opt() {}
  
  public Opt(T paramT)
  {
    setChild(paramT, 0);
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/Opt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */