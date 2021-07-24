package AST;

import beaver.Symbol;
import grafchart.sfc.GCDocument;
import java.io.IOException;
import java.io.PrintStream;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;

public class UserType
  extends Type
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
  
  public UserType clone()
    throws CloneNotSupportedException
  {
    UserType localUserType = (UserType)super.clone();
    localUserType.in$Circle(false);
    localUserType.is$Final(false);
    return localUserType;
  }
  
  public UserType copy()
  {
    try
    {
      UserType localUserType = clone();
      if (this.children != null) {
        localUserType.children = ((ASTNode[])this.children.clone());
      }
      return localUserType;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public UserType fullCopy()
  {
    UserType localUserType = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localUserType.setChild(localASTNode, i);
    }
    return localUserType;
  }
  
  public void decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    decl().getType().decode(paramLabCommDecoder);
  }
  
  public void encode(LabCommEncoder paramLabCommEncoder)
    throws IOException
  {
    decl().getType().encode(paramLabCommEncoder);
  }
  
  public void generateGCDocument(GCDocument paramGCDocument, String paramString)
  {
    decl().getType().generateGCDocument(paramGCDocument, paramString);
  }
  
  public void nameCheck()
  {
    if (decl() == null) {
      error("Use of undeclared type");
    }
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    lookupType(getName()).flatSignature(paramSignatureList);
  }
  
  public String signatureComment()
  {
    return getName();
  }
  
  public UserType() {}
  
  public UserType(String paramString)
  {
    setName(paramString);
  }
  
  public UserType(Symbol paramSymbol)
  {
    setName(paramSymbol);
  }
  
  protected int numChildren()
  {
    return 0;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
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
  
  public TypeDecl decl()
  {
    ASTNode.State localState = state();
    TypeDecl localTypeDecl = decl_compute();
    return localTypeDecl;
  }
  
  private TypeDecl decl_compute()
  {
    return lookupType(getName());
  }
  
  public boolean isNull()
  {
    ASTNode.State localState = state();
    boolean bool = isNull_compute();
    return bool;
  }
  
  private boolean isNull_compute()
  {
    if (decl() != null) {
      return decl().isNull();
    }
    return false;
  }
  
  public TypeDecl lookupType(String paramString)
  {
    ASTNode.State localState = state();
    TypeDecl localTypeDecl = getParent().Define_TypeDecl_lookupType(this, null, paramString);
    return localTypeDecl;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/UserType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */