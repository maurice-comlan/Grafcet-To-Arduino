package AST;

import beaver.Symbol;
import grafchart.sfc.GCDocument;
import java.io.PrintStream;

public class TypeDecl
  extends Decl
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
  
  public TypeDecl clone()
    throws CloneNotSupportedException
  {
    TypeDecl localTypeDecl = (TypeDecl)super.clone();
    localTypeDecl.in$Circle(false);
    localTypeDecl.is$Final(false);
    return localTypeDecl;
  }
  
  public TypeDecl copy()
  {
    try
    {
      TypeDecl localTypeDecl = clone();
      if (this.children != null) {
        localTypeDecl.children = ((ASTNode[])this.children.clone());
      }
      return localTypeDecl;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public TypeDecl fullCopy()
  {
    TypeDecl localTypeDecl = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localTypeDecl.setChild(localASTNode, i);
    }
    return localTypeDecl;
  }
  
  public void generateGCDocument(GCDocument paramGCDocument, String paramString) {}
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    getType().flatSignature(paramSignatureList);
  }
  
  public TypeDecl() {}
  
  public TypeDecl(Type paramType, String paramString)
  {
    setChild(paramType, 0);
    setName(paramString);
  }
  
  public TypeDecl(Type paramType, Symbol paramSymbol)
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
  
  public boolean isNull()
  {
    ASTNode.State localState = state();
    boolean bool = isNull_compute();
    return bool;
  }
  
  private boolean isNull_compute()
  {
    return getType().isNull();
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/TypeDecl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */