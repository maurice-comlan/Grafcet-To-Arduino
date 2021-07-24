package AST;

import java.io.PrintStream;

public class List<T extends ASTNode>
  extends ASTNode<T>
  implements Cloneable
{
  private boolean list$touched = true;
  
  public void flushCache()
  {
    super.flushCache();
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public List<T> clone()
    throws CloneNotSupportedException
  {
    List localList = (List)super.clone();
    localList.in$Circle(false);
    localList.is$Final(false);
    return localList;
  }
  
  public List<T> copy()
  {
    try
    {
      List localList = clone();
      if (this.children != null) {
        localList.children = ((ASTNode[])this.children.clone());
      }
      return localList;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public List<T> fullCopy()
  {
    List localList = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localList.setChild(localASTNode, i);
    }
    return localList;
  }
  
  public List<T> add(T paramT)
  {
    addChild(paramT);
    return this;
  }
  
  public void insertChild(ASTNode paramASTNode, int paramInt)
  {
    this.list$touched = true;
    super.insertChild(paramASTNode, paramInt);
  }
  
  public void addChild(T paramT)
  {
    this.list$touched = true;
    super.addChild(paramT);
  }
  
  public void removeChild(int paramInt)
  {
    this.list$touched = true;
    super.removeChild(paramInt);
  }
  
  public int getNumChild()
  {
    if (this.list$touched)
    {
      for (int i = 0; i < getNumChildNoTransform(); i++) {
        getChild(i);
      }
      this.list$touched = false;
    }
    return getNumChildNoTransform();
  }
  
  public boolean mayHaveRewrite()
  {
    return true;
  }
  
  public ASTNode rewriteTo()
  {
    if (this.list$touched)
    {
      for (int i = 0; i < getNumChildNoTransform(); i++) {
        getChild(i);
      }
      this.list$touched = false;
      return this;
    }
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/List.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */