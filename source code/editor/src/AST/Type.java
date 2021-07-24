package AST;

import grafchart.sfc.GCVariable;
import grafchart.sfc.Utils;
import java.io.IOException;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;

public abstract class Type
  extends ASTNode<ASTNode>
  implements Cloneable
{
  public GCVariable var = null;
  
  public void flushCache()
  {
    super.flushCache();
  }
  
  public void flushCollectionCache()
  {
    super.flushCollectionCache();
  }
  
  public Type clone()
    throws CloneNotSupportedException
  {
    Type localType = (Type)super.clone();
    localType.in$Circle(false);
    localType.is$Final(false);
    return localType;
  }
  
  public void decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    Utils.writeInternalError("decode() not implemented for " + getClass().getName());
  }
  
  public void encode(LabCommEncoder paramLabCommEncoder)
    throws IOException
  {
    Utils.writeInternalError("encode() not implemented for " + getClass().getName());
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
    return false;
  }
  
  public String declName()
  {
    ASTNode.State localState = state();
    String str = getParent().Define_String_declName(this, null);
    return str;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/Type.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */