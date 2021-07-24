package grafchart.sfc.transitions;

import java.util.ArrayList;
import java.util.List;

public class JJTTransitionParserState
{
  private List<Node> nodes = new ArrayList();
  private List<Integer> marks = new ArrayList();
  private int sp = 0;
  private int mk = 0;
  private boolean node_created;
  
  public boolean nodeCreated()
  {
    return this.node_created;
  }
  
  public void reset()
  {
    this.nodes.clear();
    this.marks.clear();
    this.sp = 0;
    this.mk = 0;
  }
  
  public Node rootNode()
  {
    return (Node)this.nodes.get(0);
  }
  
  public void pushNode(Node paramNode)
  {
    this.nodes.add(paramNode);
    this.sp += 1;
  }
  
  public Node popNode()
  {
    if (--this.sp < this.mk) {
      this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
    }
    return (Node)this.nodes.remove(this.nodes.size() - 1);
  }
  
  public Node peekNode()
  {
    return (Node)this.nodes.get(this.nodes.size() - 1);
  }
  
  public int nodeArity()
  {
    return this.sp - this.mk;
  }
  
  public void clearNodeScope(Node paramNode)
  {
    while (this.sp > this.mk) {
      popNode();
    }
    this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
  }
  
  public void openNodeScope(Node paramNode)
  {
    this.marks.add(Integer.valueOf(this.mk));
    this.mk = this.sp;
    paramNode.jjtOpen();
  }
  
  public void closeNodeScope(Node paramNode, int paramInt)
  {
    this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
    while (paramInt-- > 0)
    {
      Node localNode = popNode();
      localNode.jjtSetParent(paramNode);
      paramNode.jjtAddChild(localNode, paramInt);
    }
    paramNode.jjtClose();
    pushNode(paramNode);
    this.node_created = true;
  }
  
  public void closeNodeScope(Node paramNode, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      int i = nodeArity();
      this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
      while (i-- > 0)
      {
        Node localNode = popNode();
        localNode.jjtSetParent(paramNode);
        paramNode.jjtAddChild(localNode, i);
      }
      paramNode.jjtClose();
      pushNode(paramNode);
      this.node_created = true;
    }
    else
    {
      this.mk = ((Integer)this.marks.remove(this.marks.size() - 1)).intValue();
      this.node_created = false;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/JJTTransitionParserState.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */