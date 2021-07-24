package grafchart.sfc.transitions;

public abstract interface Node
{
  public abstract void jjtOpen();
  
  public abstract void jjtClose();
  
  public abstract void jjtSetParent(Node paramNode);
  
  public abstract Node jjtGetParent();
  
  public abstract void jjtAddChild(Node paramNode, int paramInt);
  
  public abstract Node jjtGetChild(int paramInt);
  
  public abstract int jjtGetNumChildren();
  
  public abstract Object jjtAccept(TransitionParserVisitor paramTransitionParserVisitor, Object paramObject);
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/Node.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */