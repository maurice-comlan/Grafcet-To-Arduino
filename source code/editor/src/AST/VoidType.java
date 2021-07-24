package AST;

import grafchart.sfc.GCDocument;
import grafchart.sfc.WorkspaceObject;
import java.awt.Point;
import java.io.IOException;
import java.io.PrintStream;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;

public class VoidType
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
  
  public VoidType clone()
    throws CloneNotSupportedException
  {
    VoidType localVoidType = (VoidType)super.clone();
    localVoidType.in$Circle(false);
    localVoidType.is$Final(false);
    return localVoidType;
  }
  
  public VoidType copy()
  {
    try
    {
      VoidType localVoidType = clone();
      if (this.children != null) {
        localVoidType.children = ((ASTNode[])this.children.clone());
      }
      return localVoidType;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public VoidType fullCopy()
  {
    VoidType localVoidType = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localVoidType.setChild(localASTNode, i);
    }
    return localVoidType;
  }
  
  public void decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {}
  
  public void encode(LabCommEncoder paramLabCommEncoder)
    throws IOException
  {}
  
  public void generateGCDocument(GCDocument paramGCDocument, String paramString)
  {
    WorkspaceObject localWorkspaceObject = new WorkspaceObject(new Point(), paramString);
    paramGCDocument.addObjectAtTail(localWorkspaceObject);
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    paramSignatureList.addInt(17, "void");
    paramSignatureList.addInt(0, null);
  }
  
  protected int numChildren()
  {
    return 0;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public boolean isNull()
  {
    ASTNode.State localState = state();
    boolean bool = isNull_compute();
    return bool;
  }
  
  private boolean isNull_compute()
  {
    return true;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/VoidType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */