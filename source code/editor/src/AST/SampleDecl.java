package AST;

import beaver.Symbol;
import grafchart.sfc.GCDocument;
import java.io.IOException;
import java.io.PrintStream;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommDispatcher;
import se.lth.control.labcomm.api.LabCommEncoder;
import se.lth.control.labcomm.api.LabCommHandler;
import se.lth.control.labcomm.api.LabCommSample;

public class SampleDecl
  extends Decl
  implements Cloneable, LabCommSample, LabCommHandler, LabCommDispatcher
{
  private boolean hasNewMessage = false;
  private boolean hasNewMessageNext = false;
  
  public void flushCache()
  {
    super.flushCache();
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public SampleDecl clone()
    throws CloneNotSupportedException
  {
    SampleDecl localSampleDecl = (SampleDecl)super.clone();
    localSampleDecl.in$Circle(false);
    localSampleDecl.is$Final(false);
    return localSampleDecl;
  }
  
  public SampleDecl copy()
  {
    try
    {
      SampleDecl localSampleDecl = clone();
      if (this.children != null) {
        localSampleDecl.children = ((ASTNode[])this.children.clone());
      }
      return localSampleDecl;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public SampleDecl fullCopy()
  {
    SampleDecl localSampleDecl = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localSampleDecl.setChild(localASTNode, i);
    }
    return localSampleDecl;
  }
  
  public void register(LabCommEncoder paramLabCommEncoder, LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    if (paramLabCommEncoder != null) {
      paramLabCommEncoder.register(this);
    }
    if (paramLabCommDecoder != null) {
      paramLabCommDecoder.register(this, this);
    }
  }
  
  public String getSampleIdentifier()
  {
    return getName();
  }
  
  public byte[] getSignature()
  {
    SignatureList localSignatureList = signature(root().version);
    int i = 0;
    for (int j = 0; j < localSignatureList.size(); j++)
    {
      byte[] arrayOfByte2 = localSignatureList.getData(j);
      if (arrayOfByte2 != null) {
        i += arrayOfByte2.length;
      }
    }
    byte[] arrayOfByte1 = new byte[i];
    int k = 0;
    for (int m = 0; m < localSignatureList.size(); m++)
    {
      byte[] arrayOfByte3 = localSignatureList.getData(m);
      if (arrayOfByte3 != null)
      {
        System.arraycopy(arrayOfByte3, 0, arrayOfByte1, k, arrayOfByte3.length);
        k += arrayOfByte3.length;
      }
    }
    return arrayOfByte1;
  }
  
  public void decodeAndHandle(LabCommDecoder paramLabCommDecoder, LabCommHandler paramLabCommHandler)
    throws Exception
  {
    decode(paramLabCommDecoder);
  }
  
  public synchronized void tick()
  {
    this.hasNewMessage = this.hasNewMessageNext;
    this.hasNewMessageNext = false;
  }
  
  public void decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    getType().decode(paramLabCommDecoder);
    synchronized (this)
    {
      if (this.hasNewMessageNext) {}
      this.hasNewMessageNext = true;
    }
  }
  
  public synchronized boolean hasReceived()
  {
    return this.hasNewMessage;
  }
  
  public boolean send(LabCommEncoder paramLabCommEncoder, String paramString)
    throws IOException
  {
    if ((paramString == null) || (paramString.equals(getName())))
    {
      encode(paramLabCommEncoder);
      return true;
    }
    return false;
  }
  
  public void encode(LabCommEncoder paramLabCommEncoder)
    throws IOException
  {
    paramLabCommEncoder.begin(getName());
    getType().encode(paramLabCommEncoder);
    paramLabCommEncoder.end(getName());
  }
  
  public void generateGCDocument(GCDocument paramGCDocument, String paramString)
  {
    getType().generateGCDocument(paramGCDocument, getName());
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    getType().flatSignature(paramSignatureList);
  }
  
  public SampleDecl() {}
  
  public SampleDecl(Type paramType, String paramString)
  {
    setChild(paramType, 0);
    setName(paramString);
  }
  
  public SampleDecl(Type paramType, Symbol paramSymbol)
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
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/SampleDecl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */