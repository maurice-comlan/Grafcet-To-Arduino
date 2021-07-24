package AST;

import beaver.Symbol;
import java.io.IOException;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;

public abstract class Decl
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
  
  public Decl clone()
    throws CloneNotSupportedException
  {
    Decl localDecl = (Decl)super.clone();
    localDecl.in$Circle(false);
    localDecl.is$Final(false);
    return localDecl;
  }
  
  public void register(LabCommEncoder paramLabCommEncoder, LabCommDecoder paramLabCommDecoder)
    throws IOException
  {}
  
  public boolean hasReceived()
  {
    return false;
  }
  
  public boolean send(LabCommEncoder paramLabCommEncoder, String paramString)
    throws IOException
  {
    return false;
  }
  
  public void nameCheck()
  {
    if ((lookupType(getName()) != null) || (lookupName(getName()) != null)) {
      error(getName() + " multiply declared");
    }
  }
  
  public SignatureList signature(int paramInt)
  {
    SignatureList localSignatureList = new SignatureList(paramInt);
    flatSignature(localSignatureList);
    return localSignatureList;
  }
  
  public Decl() {}
  
  public Decl(Type paramType, String paramString)
  {
    setChild(paramType, 0);
    setName(paramString);
  }
  
  public Decl(Type paramType, Symbol paramSymbol)
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
  
  public String lookupName(String paramString)
  {
    ASTNode.State localState = state();
    String str = getParent().Define_String_lookupName(this, null, paramString);
    return str;
  }
  
  public TypeDecl lookupType(String paramString)
  {
    ASTNode.State localState = state();
    TypeDecl localTypeDecl = getParent().Define_TypeDecl_lookupType(this, null, paramString);
    return localTypeDecl;
  }
  
  public String Define_String_declName(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getTypeNoTransform()) {
      return getName();
    }
    return getParent().Define_String_declName(this, paramASTNode1);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/Decl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */