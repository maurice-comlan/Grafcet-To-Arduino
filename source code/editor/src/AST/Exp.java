package AST;

public abstract class Exp
  extends ASTNode<ASTNode>
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
  
  public Exp clone()
    throws CloneNotSupportedException
  {
    Exp localExp = (Exp)super.clone();
    localExp.in$Circle(false);
    localExp.is$Final(false);
    return localExp;
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/Exp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */