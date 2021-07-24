package AST;

import java.io.PrintStream;

public class VariableSize
  extends Exp
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
  
  public VariableSize clone()
    throws CloneNotSupportedException
  {
    VariableSize localVariableSize = (VariableSize)super.clone();
    localVariableSize.in$Circle(false);
    localVariableSize.is$Final(false);
    return localVariableSize;
  }
  
  public VariableSize copy()
  {
    try
    {
      VariableSize localVariableSize = clone();
      if (this.children != null) {
        localVariableSize.children = ((ASTNode[])this.children.clone());
      }
      return localVariableSize;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public VariableSize fullCopy()
  {
    VariableSize localVariableSize = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode = getChildNoTransform(i);
      if (localASTNode != null) {
        localASTNode = localASTNode.fullCopy();
      }
      localVariableSize.setChild(localASTNode, i);
    }
    return localVariableSize;
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    paramSignatureList.addInt(0, null);
  }
  
  public String signatureComment()
  {
    return "_";
  }
  
  protected int numChildren()
  {
    return 0;
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public ASTNode rewriteTo()
  {
    return super.rewriteTo();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/VariableSize.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */