package AST;

import beaver.Symbol;
import java.io.PrintStream;

public class Field
  extends ASTNode<ASTNode>
  implements Cloneable
{
  protected String tokenString_Name;
  public int Namestart;
  public int Nameend;
  
  public void flushCache()
  {
    super.flushCache();
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Field clone()
    throws CloneNotSupportedException
  {
    Field localField = (Field)super.clone();
    localField.in$Circle(false);
    localField.is$Final(false);
    return localField;
  }
  
  public Field copy()
  {
    try
    {
      Field localField = clone();
      if (this.children != null) {
        localField.children = ((ASTNode[])this.children.clone());
      }
      return localField;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public Field fullCopy()
  {
    Field localField = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localField.setChild(localASTNode, i);
    }
    return localField;
  }
  
  public void nameCheck()
  {
    if (lookupName(getName()) != null) {
      error(getName() + " multiply declared");
    }
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    paramSignatureList.addString(getName(), signatureComment());
    getType().flatSignature(paramSignatureList);
  }
  
  public String signatureComment()
  {
    return getType().signatureComment() + " '" + getName() + "'";
  }
  
  public void nullTypeCheck()
  {
    if (getType().isNull()) {
      error("field " + getName() + " of struct " + declName() + " may not be of type void");
    }
  }
  
  public Field() {}
  
  public Field(Type paramType, String paramString)
  {
    setChild(paramType, 0);
    setName(paramString);
  }
  
  public Field(Type paramType, Symbol paramSymbol)
  {
    setChild(paramType, 0);
    setName(paramSymbol);
  }
  
  protected int numChildren()
  {
    return 1;
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
  
  public void setName(String paramString)
  {
    this.tokenString_Name = paramString;
  }
  
  public void setName(Symbol paramSymbol)
  {
    if ((paramSymbol.value != null) && (!(paramSymbol.value instanceof String))) {
      throw new UnsupportedOperationException("setName is only valid for String lexemes");
    }
    this.tokenString_Name = ((String)paramSymbol.value);
    this.Namestart = paramSymbol.getStart();
    this.Nameend = paramSymbol.getEnd();
  }
  
  public String getName()
  {
    return this.tokenString_Name != null ? this.tokenString_Name : "";
  }
  
  public String declName()
  {
    ASTNode.State localState = state();
    String str = getParent().Define_String_declName(this, null);
    return str;
  }
  
  public String lookupName(String paramString)
  {
    ASTNode.State localState = state();
    String str = getParent().Define_String_lookupName(this, null, paramString);
    return str;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/Field.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */