package grafchart.sfc.actions;

import java.io.PrintStream;

public class SimpleNode
  implements Node
{
  protected Node parent;
  protected Node[] children;
  protected int id;
  protected Object value;
  protected ActionParser parser;
  
  public SimpleNode(int paramInt)
  {
    this.id = paramInt;
  }
  
  public SimpleNode(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public void jjtOpen() {}
  
  public void jjtClose() {}
  
  public void jjtSetParent(Node paramNode)
  {
    this.parent = paramNode;
  }
  
  public Node jjtGetParent()
  {
    return this.parent;
  }
  
  public void jjtAddChild(Node paramNode, int paramInt)
  {
    if (this.children == null)
    {
      this.children = new Node[paramInt + 1];
    }
    else if (paramInt >= this.children.length)
    {
      Node[] arrayOfNode = new Node[paramInt + 1];
      System.arraycopy(this.children, 0, arrayOfNode, 0, this.children.length);
      this.children = arrayOfNode;
    }
    this.children[paramInt] = paramNode;
  }
  
  public Node jjtGetChild(int paramInt)
  {
    return this.children[paramInt];
  }
  
  public int jjtGetNumChildren()
  {
    return this.children == null ? 0 : this.children.length;
  }
  
  public void jjtSetValue(Object paramObject)
  {
    this.value = paramObject;
  }
  
  public Object jjtGetValue()
  {
    return this.value;
  }
  
  public Object jjtAccept(ActionParserVisitor paramActionParserVisitor, Object paramObject)
  {
    return paramActionParserVisitor.visit(this, paramObject);
  }
  
  public Object childrenAccept(ActionParserVisitor paramActionParserVisitor, Object paramObject)
  {
    if (this.children != null) {
      for (int i = 0; i < this.children.length; i++) {
        this.children[i].jjtAccept(paramActionParserVisitor, paramObject);
      }
    }
    return paramObject;
  }
  
  public String toString()
  {
    return ActionParserTreeConstants.jjtNodeName[this.id];
  }
  
  public String toString(String paramString)
  {
    return paramString + toString();
  }
  
  public void dump(String paramString)
  {
    System.out.println(toString(paramString));
    if (this.children != null) {
      for (int i = 0; i < this.children.length; i++)
      {
        SimpleNode localSimpleNode = (SimpleNode)this.children[i];
        if (localSimpleNode != null) {
          localSimpleNode.dump(paramString + " ");
        }
      }
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/SimpleNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */