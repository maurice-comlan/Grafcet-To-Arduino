package AST;

import java.io.PrintStream;

public class ParseArrayType
  extends Type
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
  
  public ParseArrayType clone()
    throws CloneNotSupportedException
  {
    ParseArrayType localParseArrayType = (ParseArrayType)super.clone();
    localParseArrayType.in$Circle(false);
    localParseArrayType.is$Final(false);
    return localParseArrayType;
  }
  
  public ParseArrayType copy()
  {
    try
    {
      ParseArrayType localParseArrayType = clone();
      if (this.children != null) {
        localParseArrayType.children = ((ASTNode[])this.children.clone());
      }
      return localParseArrayType;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public ParseArrayType fullCopy()
  {
    ParseArrayType localParseArrayType = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localParseArrayType.setChild(localASTNode, i);
    }
    return localParseArrayType;
  }
  
  public void nullTypeCheck()
  {
    if (getType().isNull()) {
      error("elements of array " + declName() + " may not be of type void");
    }
  }
  
  public ParseArrayType()
  {
    setChild(new List(), 1);
  }
  
  public ParseArrayType(Type paramType, List<Dim> paramList)
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
    return true;
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
  
  public void setDimList(List<Dim> paramList)
  {
    setChild(paramList, 1);
  }
  
  public int getNumDim()
  {
    return getDimList().getNumChild();
  }
  
  public Dim getDim(int paramInt)
  {
    return (Dim)getDimList().getChild(paramInt);
  }
  
  public void addDim(Dim paramDim)
  {
    List localList = (this.parent == null) || (state == null) ? getDimListNoTransform() : getDimList();
    localList.addChild(paramDim);
  }
  
  public void addDimNoTransform(Dim paramDim)
  {
    List localList = getDimListNoTransform();
    localList.addChild(paramDim);
  }
  
  public void setDim(Dim paramDim, int paramInt)
  {
    List localList = getDimList();
    localList.setChild(paramDim, paramInt);
  }
  
  public List<Dim> getDims()
  {
    return getDimList();
  }
  
  public List<Dim> getDimsNoTransform()
  {
    return getDimListNoTransform();
  }
  
  public List<Dim> getDimList()
  {
    List localList = (List)getChild(1);
    localList.getNumChild();
    return localList;
  }
  
  public List<Dim> getDimListNoTransform()
  {
    return (List)getChildNoTransform(1);
  }
  
  public ASTNode rewriteTo()
  {
    Object localObject;
    if (!getDim(0).isVariable())
    {
      state().duringArrayTypeRewrite += 1;
      localObject = rewriteRule0();
      state().duringArrayTypeRewrite -= 1;
      return (ASTNode)localObject;
    }
    if (getDim(0).isVariable())
    {
      state().duringArrayTypeRewrite += 1;
      localObject = rewriteRule1();
      state().duringArrayTypeRewrite -= 1;
      return (ASTNode)localObject;
    }
    return super.rewriteTo();
  }
  
  private FixedArrayType rewriteRule0()
  {
    if (getNumDim() == 1) {
      return new FixedArrayType(getType(), getDim(0).getExpList());
    }
    List localList = new List();
    for (int i = 1; i < getNumDim(); i++) {
      localList.add(getDim(i));
    }
    return new FixedArrayType(new ParseArrayType(getType(), localList), getDim(0).getExpList());
  }
  
  private VariableArrayType rewriteRule1()
  {
    if (getNumDim() == 1) {
      return new VariableArrayType(getType(), getDim(0).getExpList());
    }
    List localList = new List();
    for (int i = 1; i < getNumDim(); i++) {
      localList.add(getDim(i));
    }
    return new VariableArrayType(new ParseArrayType(getType(), localList), getDim(0).getExpList());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/ParseArrayType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */