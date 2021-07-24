package AST;

import beaver.Symbol;
import grafchart.sfc.BooleanVariable;
import grafchart.sfc.GCDocument;
import grafchart.sfc.IntegerVariable;
import grafchart.sfc.InternalVariable;
import grafchart.sfc.RealVariable;
import grafchart.sfc.StringVariable;
import grafchart.sfc.Utils;
import java.awt.Point;
import java.io.IOException;
import java.io.PrintStream;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;

public class PrimType
  extends Type
  implements Cloneable
{
  protected String tokenString_Name;
  public int Namestart;
  public int Nameend;
  protected int tokenint_Token;
  
  public void flushCache()
  {
    super.flushCache();
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public PrimType clone()
    throws CloneNotSupportedException
  {
    PrimType localPrimType = (PrimType)super.clone();
    localPrimType.in$Circle(false);
    localPrimType.is$Final(false);
    return localPrimType;
  }
  
  public PrimType copy()
  {
    try
    {
      PrimType localPrimType = clone();
      if (this.children != null) {
        localPrimType.children = ((ASTNode[])this.children.clone());
      }
      return localPrimType;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public PrimType fullCopy()
  {
    PrimType localPrimType = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localPrimType.setChild(localASTNode, i);
    }
    return localPrimType;
  }
  
  public void generateGCDocument(GCDocument paramGCDocument, String paramString)
  {
    switch (getToken())
    {
    case 32: 
      this.var = new BooleanVariable(new Point());
      break;
    case 33: 
    case 34: 
    case 35: 
    case 36: 
      this.var = new IntegerVariable(new Point());
      break;
    case 37: 
    case 38: 
      this.var = new RealVariable(new Point());
      break;
    case 39: 
      this.var = new StringVariable(new Point());
    }
    if (this.var != null)
    {
      ((InternalVariable)this.var).setName(paramString);
      paramGCDocument.addObjectAtTail(this.var);
    }
    else
    {
      Utils.writeInternalError("Unsupported primitive LabComm type: " + getToken());
    }
  }
  
  public void decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    if (this.var != null) {
      switch (getToken())
      {
      case 32: 
        ((BooleanVariable)this.var).setStoredBoolAction(paramLabCommDecoder.decodeBoolean());
        break;
      case 33: 
        ((IntegerVariable)this.var).setStoredIntAction(paramLabCommDecoder.decodeByte());
        break;
      case 34: 
        ((IntegerVariable)this.var).setStoredIntAction(paramLabCommDecoder.decodeShort());
        break;
      case 35: 
        ((IntegerVariable)this.var).setStoredIntAction(paramLabCommDecoder.decodeInt());
        break;
      case 36: 
        ((IntegerVariable)this.var).setStoredIntAction((int)paramLabCommDecoder.decodeLong());
        break;
      case 37: 
        ((RealVariable)this.var).setStoredRealAction(paramLabCommDecoder.decodeFloat());
        break;
      case 38: 
        ((RealVariable)this.var).setStoredRealAction(paramLabCommDecoder.decodeDouble());
        break;
      case 39: 
        ((StringVariable)this.var).setStoredStringAction(paramLabCommDecoder.decodeString());
        break;
      default: 
        Utils.writeInternalError("Forgor to add primitive type to decode(): " + getToken());
      }
    }
  }
  
  public void encode(LabCommEncoder paramLabCommEncoder)
    throws IOException
  {
    if (this.var != null) {
      switch (getToken())
      {
      case 32: 
        paramLabCommEncoder.encodeBoolean(((BooleanVariable)this.var).getBoolVal());
        break;
      case 33: 
        paramLabCommEncoder.encodeByte((byte)((IntegerVariable)this.var).getIntVal());
        break;
      case 34: 
        paramLabCommEncoder.encodeShort((short)((IntegerVariable)this.var).getIntVal());
        break;
      case 35: 
        paramLabCommEncoder.encodeInt(((IntegerVariable)this.var).getIntVal());
        break;
      case 36: 
        paramLabCommEncoder.encodeLong(((IntegerVariable)this.var).getIntVal());
        break;
      case 37: 
        paramLabCommEncoder.encodeFloat((float)((RealVariable)this.var).getRealVal());
        break;
      case 38: 
        paramLabCommEncoder.encodeDouble(((RealVariable)this.var).getRealVal());
        break;
      case 39: 
        paramLabCommEncoder.encodeString(((StringVariable)this.var).getStringVal());
        break;
      default: 
        Utils.writeInternalError("Forgot to add primitive type to encode(): " + getToken());
      }
    }
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    paramSignatureList.addInt(getToken(), null);
  }
  
  public String signatureComment()
  {
    return getName();
  }
  
  public PrimType() {}
  
  public PrimType(String paramString, int paramInt)
  {
    setName(paramString);
    setToken(paramInt);
  }
  
  public PrimType(Symbol paramSymbol, int paramInt)
  {
    setName(paramSymbol);
    setToken(paramInt);
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
  
  public void setToken(int paramInt)
  {
    this.tokenint_Token = paramInt;
  }
  
  public int getToken()
  {
    return this.tokenint_Token;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/PrimType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */