package AST;

import com.nwoods.jgo.JGoObject;
import grafchart.sfc.BasicList;
import grafchart.sfc.BooleanList;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCVariable;
import grafchart.sfc.IntegerList;
import grafchart.sfc.RealList;
import grafchart.sfc.StringList;
import grafchart.sfc.Utils;
import java.awt.Point;
import java.io.IOException;
import java.util.Iterator;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;

public abstract class ArrayType
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
  
  public ArrayType clone()
    throws CloneNotSupportedException
  {
    ArrayType localArrayType = (ArrayType)super.clone();
    localArrayType.in$Circle(false);
    localArrayType.is$Final(false);
    return localArrayType;
  }
  
  public void jgCheck()
  {
    if (getNumExp() > 1) {
      error("Multidimensional arrays are not supported: \"" + root().getName() + "\".");
    } else if ((getType() instanceof ArrayType)) {
      error("Arrays of arrays are not supported: \"" + root().getName() + "\".");
    } else if (!(getType() instanceof PrimType)) {
      error("Only arrays of primitive types are supported: \"" + root().getName() + "\".");
    }
  }
  
  public void generateGCDocument(GCDocument paramGCDocument, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    switch (((PrimType)getType()).getToken())
    {
    case 32: 
      localObject1 = new BooleanList(new Point());
      localObject2 = Boolean.valueOf(false);
      break;
    case 33: 
    case 34: 
    case 35: 
    case 36: 
      localObject1 = new IntegerList(new Point());
      localObject2 = Integer.valueOf(0);
      break;
    case 37: 
    case 38: 
      localObject1 = new RealList(new Point());
      localObject2 = Double.valueOf(0.0D);
      break;
    case 39: 
      localObject1 = new StringList(new Point());
      localObject2 = "";
    }
    if (localObject1 != null)
    {
      for (int i = 0; i < size(); i++) {
        ((BasicList)localObject1).basicAdd(localObject2);
      }
      ((BasicList)localObject1).setName(paramString);
      paramGCDocument.addObjectAtTail((JGoObject)localObject1);
      this.var = ((GCVariable)localObject1);
    }
    else
    {
      Utils.writeInternalError("Forgot to add primitive LabComm type in FixedArrayType.generateGCDocument(): " + ((PrimType)getType()).getToken());
    }
  }
  
  public void decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    if (this.var != null)
    {
      int i = ((PrimType)getType()).getToken();
      java.util.List localList = ((BasicList)this.var).getValues();
      synchronized (localList)
      {
        localList.clear();
        int j = size();
        if (j == -1) {
          j = paramLabCommDecoder.decodePacked32();
        }
        int k;
        switch (i)
        {
        case 32: 
          for (k = 0; k < j; k++) {
            localList.add(Boolean.valueOf(paramLabCommDecoder.decodeBoolean()));
          }
          break;
        case 33: 
          for (k = 0; k < j; k++) {
            localList.add(Integer.valueOf(paramLabCommDecoder.decodeByte()));
          }
          break;
        case 34: 
          for (k = 0; k < j; k++) {
            localList.add(Integer.valueOf(paramLabCommDecoder.decodeShort()));
          }
          break;
        case 35: 
          for (k = 0; k < j; k++) {
            localList.add(Integer.valueOf(paramLabCommDecoder.decodeInt()));
          }
          break;
        case 36: 
          for (k = 0; k < j; k++) {
            localList.add(Integer.valueOf((int)paramLabCommDecoder.decodeLong()));
          }
          break;
        case 37: 
          for (k = 0; k < j; k++) {
            localList.add(Double.valueOf(paramLabCommDecoder.decodeFloat()));
          }
          break;
        case 38: 
          for (k = 0; k < j; k++) {
            localList.add(Double.valueOf(paramLabCommDecoder.decodeDouble()));
          }
          break;
        case 39: 
          for (k = 0; k < j; k++) {
            localList.add(paramLabCommDecoder.decodeString());
          }
          break;
        default: 
          Utils.writeInternalError("Forgot to add primitive type to ArrayType.decode(): " + i);
        }
      }
    }
  }
  
  public void encode(LabCommEncoder paramLabCommEncoder)
    throws IOException
  {
    if (this.var != null)
    {
      int i = ((PrimType)getType()).getToken();
      java.util.List localList = ((BasicList)this.var).getValues();
      synchronized (localList)
      {
        if ((this instanceof VariableArrayType)) {
          paramLabCommEncoder.encodePacked32(localList.size());
        }
        Iterator localIterator;
        Object localObject1;
        switch (i)
        {
        case 32: 
          localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = localIterator.next();
            paramLabCommEncoder.encodeBoolean(((Boolean)localObject1).booleanValue());
          }
          break;
        case 33: 
          localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = localIterator.next();
            paramLabCommEncoder.encodeByte(((Integer)localObject1).byteValue());
          }
          break;
        case 34: 
          localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = localIterator.next();
            paramLabCommEncoder.encodeShort(((Integer)localObject1).shortValue());
          }
          break;
        case 35: 
          localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = localIterator.next();
            paramLabCommEncoder.encodeInt(((Integer)localObject1).intValue());
          }
          break;
        case 36: 
          localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = localIterator.next();
            paramLabCommEncoder.encodeLong(((Integer)localObject1).longValue());
          }
          break;
        case 37: 
          localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = localIterator.next();
            paramLabCommEncoder.encodeFloat(((Double)localObject1).floatValue());
          }
          break;
        case 38: 
          localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = localIterator.next();
            paramLabCommEncoder.encodeDouble(((Double)localObject1).doubleValue());
          }
          break;
        case 39: 
          localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = localIterator.next();
            paramLabCommEncoder.encodeString((String)localObject1);
          }
          break;
        default: 
          Utils.writeInternalError("Forgot to add primitive type to ArrayType.encode(): " + i);
        }
      }
    }
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    paramSignatureList.addInt(16, signatureComment());
    paramSignatureList.indent();
    paramSignatureList.addInt(getNumExp(), null);
    for (int i = 0; i < getNumExp(); i++) {
      getExp(i).flatSignature(paramSignatureList);
    }
    getType().flatSignature(paramSignatureList);
    paramSignatureList.unindent();
    paramSignatureList.add(null, "}");
  }
  
  public String signatureComment()
  {
    StringBuffer localStringBuffer = new StringBuffer("array [");
    for (int i = 0; i < getNumExp(); i++)
    {
      if (i > 0) {
        localStringBuffer.append(", ");
      }
      localStringBuffer.append(getExp(i).signatureComment());
    }
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
  
  public void nullTypeCheck()
  {
    if (getType().isNull()) {
      error("elements of array " + declName() + " may not be of type void");
    }
  }
  
  public ArrayType()
  {
    setChild(new List(), 1);
  }
  
  public ArrayType(Type paramType, List<Exp> paramList)
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
  
  public abstract int size();
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/ArrayType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */