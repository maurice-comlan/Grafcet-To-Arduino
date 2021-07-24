package AST;

import beaver.Symbol;
import grafchart.sfc.GCDocument;
import grafchart.sfc.Utils;
import java.io.PrintStream;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

public class ASTNode<T extends ASTNode>
  extends Symbol
  implements Cloneable, Iterable<T>
{
  protected String errors = null;
  public static final int LABCOMM_TYPEDEF = 1;
  public static final int LABCOMM_SAMPLE = 2;
  public static final int LABCOMM_ARRAY = 16;
  public static final int LABCOMM_STRUCT = 17;
  public static final int LABCOMM_BOOLEAN = 32;
  public static final int LABCOMM_BYTE = 33;
  public static final int LABCOMM_SHORT = 34;
  public static final int LABCOMM_INT = 35;
  public static final int LABCOMM_LONG = 36;
  public static final int LABCOMM_FLOAT = 37;
  public static final int LABCOMM_DOUBLE = 38;
  public static final int LABCOMM_STRING = 39;
  public static final boolean generatedWithCircularEnabled = true;
  public static final boolean generatedWithCacheCycle = true;
  public static final boolean generatedWithComponentCheck = false;
  protected static ASTNode.State state = new ASTNode.State();
  public boolean in$Circle = false;
  public boolean is$Final = false;
  private int childIndex;
  protected int numChildren;
  protected ASTNode parent;
  protected ASTNode[] children;
  
  public void flushCache() {}
  
  public void flushCollectionCache() {}
  
  public ASTNode<T> clone()
    throws CloneNotSupportedException
  {
    ASTNode localASTNode = (ASTNode)super.clone();
    localASTNode.in$Circle(false);
    localASTNode.is$Final(false);
    return localASTNode;
  }
  
  public ASTNode<T> copy()
  {
    try
    {
      ASTNode localASTNode = clone();
      if (this.children != null) {
        localASTNode.children = ((ASTNode[])this.children.clone());
      }
      return localASTNode;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      System.err.println("Error: Could not clone node of type " + getClass().getName() + "!");
    }
    return null;
  }
  
  public ASTNode<T> fullCopy()
  {
    ASTNode localASTNode1 = copy();
    for (int i = 0; i < getNumChildNoTransform(); i++)
    {
      ASTNode localASTNode2 = getChildNoTransform(i);
      if (localASTNode2 != null) {
        localASTNode2 = localASTNode2.fullCopy();
      }
      localASTNode1.setChild(localASTNode2, i);
    }
    return localASTNode1;
  }
  
  protected void error(String paramString)
  {
    paramString = "Error at " + lineNumber() + ": " + paramString;
    if (this.errors == null) {
      this.errors = paramString;
    } else {
      this.errors = (this.errors + "\n" + paramString);
    }
  }
  
  protected boolean hasErrors()
  {
    return this.errors != null;
  }
  
  public void errorCheck(Collection paramCollection)
  {
    nameCheck();
    typeCheck();
    jgCheck();
    if (hasErrors()) {
      paramCollection.add(this.errors);
    }
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).errorCheck(paramCollection);
    }
  }
  
  public void tick()
  {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).tick();
    }
  }
  
  public void jgCheck() {}
  
  public void generateGCDocument(GCDocument paramGCDocument, String paramString)
  {
    Utils.writeInternalError("generateGCDocument() not implemented for " + getClass().getName());
  }
  
  public void nameCheck()
  {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).nameCheck();
    }
  }
  
  public void flatSignature(SignatureList paramSignatureList)
  {
    throw new Error(getClass().getName() + ".flatSignature(SignatureList list)" + " not declared");
  }
  
  public String signatureComment()
  {
    throw new Error(getClass().getName() + ".signatureComment()" + " not declared");
  }
  
  public void typeCheck()
  {
    nullTypeCheck();
  }
  
  public void nullTypeCheck() {}
  
  public final ASTNode.State state()
  {
    return state;
  }
  
  public boolean in$Circle()
  {
    return this.in$Circle;
  }
  
  public void in$Circle(boolean paramBoolean)
  {
    this.in$Circle = paramBoolean;
  }
  
  public boolean is$Final()
  {
    return this.is$Final;
  }
  
  public void is$Final(boolean paramBoolean)
  {
    this.is$Final = paramBoolean;
  }
  
  public T getChild(int paramInt)
  {
    return (T) getChild(this, paramInt);
  }
  
  public static ASTNode getChild(ASTNode paramASTNode, int paramInt)
  {
    ASTNode localASTNode1 = paramASTNode.getChildNoTransform(paramInt);
    if (localASTNode1.is$Final()) {
      return localASTNode1;
    }
    if (!localASTNode1.mayHaveRewrite())
    {
      localASTNode1.is$Final(paramASTNode.is$Final());
      return localASTNode1;
    }
    if (!localASTNode1.in$Circle())
    {
      int j = paramASTNode.state().boundariesCrossed;
      int i;
      do
      {
        paramASTNode.state().push(1);
        ASTNode localASTNode2 = localASTNode1;
        localASTNode2.in$Circle(true);
        localASTNode1 = localASTNode1.rewriteTo();
        if (localASTNode1 != localASTNode2) {
          paramASTNode.setChild(localASTNode1, paramInt);
        }
        localASTNode2.in$Circle(false);
        i = paramASTNode.state().pop();
      } while (i == 1);
      if ((i == 2) && (paramASTNode.is$Final()))
      {
        localASTNode1.is$Final(true);
        paramASTNode.state().boundariesCrossed = j;
      }
    }
    else if (paramASTNode.is$Final() != localASTNode1.is$Final())
    {
      paramASTNode.state().boundariesCrossed += 1;
    }
    return localASTNode1;
  }
  
  public int getIndexOfChild(ASTNode paramASTNode)
  {
    if ((paramASTNode != null) && (paramASTNode.childIndex < getNumChildNoTransform()) && (paramASTNode == getChildNoTransform(paramASTNode.childIndex))) {
      return paramASTNode.childIndex;
    }
    for (int i = 0; i < getNumChildNoTransform(); i++) {
      if (getChildNoTransform(i) == paramASTNode)
      {
        paramASTNode.childIndex = i;
        return i;
      }
    }
    return -1;
  }
  
  public void addChild(T paramT)
  {
    setChild(paramT, getNumChildNoTransform());
  }
  
  public final T getChildNoTransform(int paramInt)
  {
    return (T) this.children[paramInt];
  }
  
  protected int numChildren()
  {
    return this.numChildren;
  }
  
  public int getNumChild()
  {
    return numChildren();
  }
  
  public final int getNumChildNoTransform()
  {
    return numChildren();
  }
  
  public void setChild(ASTNode paramASTNode, int paramInt)
  {
    if (this.children == null)
    {
      this.children = new ASTNode[paramInt + 1];
    }
    else if (paramInt >= this.children.length)
    {
      ASTNode[] arrayOfASTNode = new ASTNode[paramInt << 1];
      System.arraycopy(this.children, 0, arrayOfASTNode, 0, this.children.length);
      this.children = arrayOfASTNode;
    }
    this.children[paramInt] = paramASTNode;
    if (paramInt >= this.numChildren) {
      this.numChildren = (paramInt + 1);
    }
    if (paramASTNode != null)
    {
      paramASTNode.setParent(this);
      paramASTNode.childIndex = paramInt;
    }
  }
  
  public void insertChild(ASTNode paramASTNode, int paramInt)
  {
    if (this.children == null)
    {
      this.children = new ASTNode[paramInt + 1];
      this.children[paramInt] = paramASTNode;
    }
    else
    {
      ASTNode[] arrayOfASTNode = new ASTNode[this.children.length + 1];
      System.arraycopy(this.children, 0, arrayOfASTNode, 0, paramInt);
      arrayOfASTNode[paramInt] = paramASTNode;
      if (paramInt < this.children.length) {
        System.arraycopy(this.children, paramInt, arrayOfASTNode, paramInt + 1, this.children.length - paramInt);
      }
      this.children = arrayOfASTNode;
    }
    this.numChildren += 1;
    if (paramASTNode != null)
    {
      paramASTNode.setParent(this);
      paramASTNode.childIndex = paramInt;
    }
  }
  
  public void removeChild(int paramInt)
  {
    if (this.children != null)
    {
      ASTNode localASTNode = this.children[paramInt];
      if (localASTNode != null)
      {
        localASTNode.setParent(null);
        localASTNode.childIndex = -1;
      }
      System.arraycopy(this.children, paramInt + 1, this.children, paramInt, this.children.length - paramInt - 1);
      this.numChildren -= 1;
    }
  }
  
  public ASTNode getParent()
  {
    if ((this.parent != null) && (this.parent.is$Final() != is$Final())) {
      state().boundariesCrossed += 1;
    }
    return this.parent;
  }
  
  public void setParent(ASTNode paramASTNode)
  {
    this.parent = paramASTNode;
  }
  
  protected boolean duringArrayTypeRewrite()
  {
    if (state().duringArrayTypeRewrite == 0) {
      return false;
    }
    state().pop();
    state().push(3);
    return true;
  }
  
  public Iterator<T> iterator()
  {
    return new Iterator()
    {
      private int counter = 0;
      
      public boolean hasNext()
      {
        return this.counter < ASTNode.this.getNumChild();
      }
      
      public T next()
      {
        if (hasNext()) {
          return ASTNode.this.getChild(this.counter++);
        }
        return null;
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException();
      }
    };
  }
  
  public boolean mayHaveRewrite()
  {
    return false;
  }
  
  public int lineNumber()
  {
    ASTNode.State localState = state();
    int i = lineNumber_compute();
    return i;
  }
  
  private int lineNumber_compute()
  {
    return getLine(getStart());
  }
  
  public Program root()
  {
    ASTNode.State localState = state();
    Program localProgram = getParent().Define_Program_root(this, null);
    return localProgram;
  }
  
  public ASTNode rewriteTo()
  {
    if (state().peek() == 1)
    {
      state().pop();
      state().push(2);
    }
    return this;
  }
  
  public Program Define_Program_root(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    return getParent().Define_Program_root(this, paramASTNode1);
  }
  
  public String Define_String_lookupName(ASTNode paramASTNode1, ASTNode paramASTNode2, String paramString)
  {
    return getParent().Define_String_lookupName(this, paramASTNode1, paramString);
  }
  
  public TypeDecl Define_TypeDecl_lookupType(ASTNode paramASTNode1, ASTNode paramASTNode2, String paramString)
  {
    return getParent().Define_TypeDecl_lookupType(this, paramASTNode1, paramString);
  }
  
  public String Define_String_declName(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    return getParent().Define_String_declName(this, paramASTNode1);
  }
  
  
  public static class State
{
  public boolean IN_CIRCLE = false;
  public int CIRCLE_INDEX = 1;
  public boolean CHANGE = false;
  public boolean LAST_CYCLE = false;
  public boolean RESET_CYCLE = false;
  public static final int REWRITE_CHANGE = 1;
  public static final int REWRITE_NOCHANGE = 2;
  public static final int REWRITE_INTERRUPT = 3;
  public int boundariesCrossed = 0;
  private int[] stack = new int[64];
  private int pos = 0;
  protected int duringArrayTypeRewrite = 0;
  
  private void ensureSize(int paramInt)
  {
    if (paramInt < this.stack.length) {
      return;
    }
    int[] arrayOfInt = new int[this.stack.length * 2];
    System.arraycopy(this.stack, 0, arrayOfInt, 0, this.stack.length);
    this.stack = arrayOfInt;
  }
  
  public void push(int paramInt)
  {
    ensureSize(this.pos + 1);
    this.stack[(this.pos++)] = paramInt;
  }
  
  public int pop()
  {
    return this.stack[(--this.pos)];
  }
  
  public int peek()
  {
    return this.stack[(this.pos - 1)];
  }
  
  public void reset()
  {
    this.IN_CIRCLE = false;
    this.CIRCLE_INDEX = 1;
    this.CHANGE = false;
    this.LAST_CYCLE = false;
    this.boundariesCrossed = 0;
    if (this.duringArrayTypeRewrite != 0)
    {
      System.out.println("Warning: resetting duringArrayTypeRewrite");
      this.duringArrayTypeRewrite = 0;
    }
  }
  
  static class IdentityHashSet
    extends AbstractSet
    implements Set
  {
    private IdentityHashMap map;
    private static final Object PRESENT = new Object();
    
    public IdentityHashSet(int paramInt)
    {
      this.map = new IdentityHashMap(paramInt);
    }
    
    public Iterator iterator()
    {
      return this.map.keySet().iterator();
    }
    
    public int size()
    {
      return this.map.size();
    }
    
    public boolean isEmpty()
    {
      return this.map.isEmpty();
    }
    
    public boolean contains(Object paramObject)
    {
      return this.map.containsKey(paramObject);
    }
    
    public boolean add(Object paramObject)
    {
      return this.map.put(paramObject, PRESENT) == null;
    }
    
    public boolean remove(Object paramObject)
    {
      return this.map.remove(paramObject) == PRESENT;
    }
    
    public void clear()
    {
      this.map.clear();
    }
  }
  
  public static class CircularValue
  {
    Object value;
    int visited = -1;
  }
}
  
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/ASTNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */