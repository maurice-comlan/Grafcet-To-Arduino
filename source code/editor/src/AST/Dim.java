package AST;

import java.io.PrintStream;

public class Dim
  extends ASTNode<ASTNode>
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
  
  public Dim clone()
    throws CloneNotSupportedException
  {
    Dim localDim = (Dim)super.clone();
    localDim.in$Circle(false);
    localDim.is$Final(false);
    return localDim;
  }
  
  public Dim copy()
  {
    try
    {
      Dim localDim = clone();
      if (this.children != null) {
        localDim.children = ((ASTNode[])this.children.clone());
      }
      return localDim;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Dim fullCopy()
  {
    Dim localDim = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localDim.setChild(localASTNode, i);
    }
    return localDim;
  }
  
  public Dim()
  {
    setChild(new List(), 0);
  }
  
  public Dim(List<Exp> paramList)
  {
    setChild(paramList, 0);
  }
  
  protected int numChildren()
  {
    return 1;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public void setExpList(List<Exp> paramList)
  {
    setChild(paramList, 0);
  }
  
  public int getNumExp()
  {
    return getExpList().getNumChild();
  }
  
  public Exp getExp(int paramInt)
  {
    return (Exp)getExpList().getChild(paramInt);
  }
  
  public void addExp(Exp paramExp)
  {
    List localList = (this.parent == null) || (state == null) ? getExpListNoTransform() : getExpList();
    localList.addChild(paramExp);
  }
  
  public void addExpNoTransform(Exp paramExp)
  {
    List localList = getExpListNoTransform();
    localList.addChild(paramExp);
  }
  
  public void setExp(Exp paramExp, int paramInt)
  {
    List localList = getExpList();
    localList.setChild(paramExp, paramInt);
  }
  
  public List<Exp> getExps()
  {
    return getExpList();
  }
  
  public List<Exp> getExpsNoTransform()
  {
    return getExpListNoTransform();
  }
  
  public List<Exp> getExpList()
  {
    List localList = (List)getChild(0);
    localList.getNumChild();
    return localList;
  }
  
  public List<Exp> getExpListNoTransform()
  {
    return (List)getChildNoTransform(0);
  }
  
  public boolean isVariable()
  {
    ASTNode.State localState = state();
    boolean bool = isVariable_compute();
    return bool;
  }
  
  private boolean isVariable_compute()
  {
    for (int i = 0; i < getNumExp(); i++) {
      if ((getExp(i) instanceof VariableSize)) {
        return true;
      }
    }
    return false;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/Dim.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */