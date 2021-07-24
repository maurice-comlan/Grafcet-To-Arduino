package AST;

import grafchart.sfc.GCDocument;
import grafchart.sfc.WorkspaceObject;
import java.awt.Point;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;

public class StructType
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
  
  public StructType clone()
    throws CloneNotSupportedException
  {
    StructType localStructType = (StructType)super.clone();
    localStructType.in$Circle(false);
    localStructType.is$Final(false);
    return localStructType;
  }
  
  public StructType copy()
  {
    try
    {
      StructType localStructType = clone();
      if (this.children != null) {
        localStructType.children = ((ASTNode[])this.children.clone());
      }
      return localStructType;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public StructType fullCopy()
  {
    StructType localStructType = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localStructType.setChild(localASTNode, i);
    }
    return localStructType;
  }
  
  public void decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    Iterator localIterator = getFields().iterator();
    while (localIterator.hasNext())
    {
      Field localField = (Field)localIterator.next();
      localField.getType().decode(paramLabCommDecoder);
    }
  }
  
  public void encode(LabCommEncoder paramLabCommEncoder)
    throws IOException
  {
    Iterator localIterator = getFields().iterator();
    while (localIterator.hasNext())
    {
      Field localField = (Field)localIterator.next();
      localField.getType().encode(paramLabCommEncoder);
    }
  }
  
  public void generateGCDocument(GCDocument paramGCDocument, String paramString)
  {
    WorkspaceObject localWorkspaceObject = new WorkspaceObject(new Point(), paramString);
    paramGCDocument.addObjectAtTail(localWorkspaceObject);
    Iterator localIterator = getFields().iterator();
    while (localIterator.hasNext())
    {
      Field localField = (Field)localIterator.next();
      localField.getType().generateGCDocument(localWorkspaceObject.myContentDocument, localField.getName());
    }
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    paramSignatureList.addInt(17, "struct { " + getNumField() + " fields");
    paramSignatureList.indent();
    paramSignatureList.addInt(getNumField(), null);
    for (int i = 0; i < getNumField(); i++) {
      getField(i).flatSignature(paramSignatureList);
    }
    paramSignatureList.unindent();
    paramSignatureList.add(null, "}");
  }
  
  public String signatureComment()
  {
    return "struct";
  }
  
  public StructType()
  {
    setChild(new List(), 0);
  }
  
  public StructType(List<Field> paramList)
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
  
  public void setFieldList(List<Field> paramList)
  {
    setChild(paramList, 0);
  }
  
  public int getNumField()
  {
    return getFieldList().getNumChild();
  }
  
  public Field getField(int paramInt)
  {
    return (Field)getFieldList().getChild(paramInt);
  }
  
  public void addField(Field paramField)
  {
    List localList = (this.parent == null) || (state == null) ? getFieldListNoTransform() : getFieldList();
    localList.addChild(paramField);
  }
  
  public void addFieldNoTransform(Field paramField)
  {
    List localList = getFieldListNoTransform();
    localList.addChild(paramField);
  }
  
  public void setField(Field paramField, int paramInt)
  {
    List localList = getFieldList();
    localList.setChild(paramField, paramInt);
  }
  
  public List<Field> getFields()
  {
    return getFieldList();
  }
  
  public List<Field> getFieldsNoTransform()
  {
    return getFieldListNoTransform();
  }
  
  public List<Field> getFieldList()
  {
    List localList = (List)getChild(0);
    localList.getNumChild();
    return localList;
  }
  
  public List<Field> getFieldListNoTransform()
  {
    return (List)getChildNoTransform(0);
  }
  
  public String Define_String_declName(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    if (paramASTNode1 == getFieldListNoTransform())
    {
      int i = paramASTNode1.getIndexOfChild(paramASTNode2);
      return declName();
    }
    return getParent().Define_String_declName(this, paramASTNode1);
  }
  
  public String Define_String_lookupName(ASTNode paramASTNode1, ASTNode paramASTNode2, String paramString)
  {
    if (paramASTNode1 == getFieldListNoTransform())
    {
      int i = paramASTNode1.getIndexOfChild(paramASTNode2);
      for (int j = 0; j < i; j++)
      {
        String str = getField(j).getName();
        if (str.equals(paramString)) {
          return str;
        }
      }
      return null;
    }
    return getParent().Define_String_lookupName(this, paramASTNode1, paramString);
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/StructType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */