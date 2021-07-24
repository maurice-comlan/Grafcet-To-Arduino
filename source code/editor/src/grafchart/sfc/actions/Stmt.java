package grafchart.sfc.actions;

import java.io.PrintStream;

public abstract class Stmt
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
  
  public Stmt clone()
    throws CloneNotSupportedException
  {
    Stmt localStmt = (Stmt)super.clone();
    localStmt.in$Circle(false);
    localStmt.is$Final(false);
    return localStmt;
  }
  
  public abstract void execute();
  
  public Stmt(int paramInt)
  {
    super(paramInt);
  }
  
  public Stmt(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public Stmt()
  {
    this(0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "Stmt");
    String str = paramString + "  ";
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).dumpTree(str, paramPrintStream);
    }
  }
  
  public Object jjtAccept(ActionParserVisitor paramActionParserVisitor, Object paramObject)
  {
    return paramActionParserVisitor.visit(this, paramObject);
  }
  
  public void jjtAddChild(Node paramNode, int paramInt)
  {
    checkChild(paramNode, paramInt);
    super.jjtAddChild(paramNode, paramInt);
  }
  
  public void checkChild(Node paramNode, int paramInt) {}
  
  public int getNumChild()
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/Stmt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */