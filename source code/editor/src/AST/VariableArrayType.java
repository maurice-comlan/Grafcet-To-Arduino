package AST;

import java.io.PrintStream;

public class VariableArrayType
  extends ArrayType
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
  
  public VariableArrayType clone()
    throws CloneNotSupportedException
  {
    VariableArrayType localVariableArrayType = (VariableArrayType)super.clone();
    localVariableArrayType.in$Circle(false);
    localVariableArrayType.is$Final(false);
    return localVariableArrayType;
  }
  
  public VariableArrayType copy()
  {
    try
    {
      VariableArrayType localVariableArrayType = clone();
      if (this.children != null) {
        localVariableArrayType.children = ((ASTNode[])this.children.clone());
      }
      return localVariableArrayType;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public VariableArrayType fullCopy()
  {
    VariableArrayType localVariableArrayType = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localVariableArrayType.setChild(localASTNode, i);
    }
    return localVariableArrayType;
  }
  
  public VariableArrayType()
  {
    setChild(new List(), 1);
  }
  
  public VariableArrayType(Type paramType, List<Exp> paramList)
  {
    setChild(paramType, 0);
    setChild(paramList, 1);
  }
  
  protected int numChildren()
  {
    return 2;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public void setType(Type paramType)
  {
    setChild(paramType, 0);
  }
  
  public Type getType()
  {
    return (Type)getChild(0);
  }
  
  public Type getTypeNoTransform()
  {
    return (Type)getChildNoTransform(0);
  }
  
  public void setExpList(List<Exp> paramList)
  {
    setChild(paramList, 1);
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
    List localList = (List)getChild(1);
    localList.getNumChild();
    return localList;
  }
  
  public List<Exp> getExpListNoTransform()
  {
    return (List)getChildNoTransform(1);
  }
  
  public int size()
  {
    ASTNode.State localState = state();
    int i = size_compute();
    return i;
  }
  
  private int size_compute()
  {
    return -1;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/VariableArrayType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */