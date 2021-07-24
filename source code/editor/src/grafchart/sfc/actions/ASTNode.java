package grafchart.sfc.actions;

import grafchart.sfc.SymbolTableObject;
import grafchart.sfc.builtin.Evaluatable;
import grafchart.util.CompilationMessage;
import grafchart.util.CompilationMessage.Kind;
import grafchart.util.SortedList;
import java.io.PrintStream;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ASTNode<T extends ASTNode>
  extends SimpleNode
  implements Cloneable, Evaluatable, Iterable<T>
{
  public static final boolean generatedWithCircularEnabled = true;
  public static final boolean generatedWithCacheCycle = true;
  public static final boolean generatedWithComponentCheck = false;
  protected static ASTNode.State state = new ASTNode.State();
  public boolean in$Circle = false;
  public boolean is$Final = false;
  private int childIndex;
  protected int numChildren;
  protected int isWorkspace_visited = -1;
  protected Map createError_String_visited;
  protected Map createWarning_String_visited;
  protected Map toString_String_visited;
  protected Map dumpNode_String_visited;
  protected int root_visited = -1;
  
  public void flushCache()
  {
    this.isWorkspace_visited = -1;
    this.createError_String_visited = null;
    this.createWarning_String_visited = null;
    this.toString_String_visited = null;
    this.dumpNode_String_visited = null;
    this.root_visited = -1;
  }
  
  public void flushCollectionCache() {}
  
  public ASTNode<T> clone()
    throws CloneNotSupportedException
  {
    ASTNode localASTNode = (ASTNode)super.clone();
    localASTNode.isWorkspace_visited = -1;
    localASTNode.createError_String_visited = null;
    localASTNode.createWarning_String_visited = null;
    localASTNode.toString_String_visited = null;
    localASTNode.dumpNode_String_visited = null;
    localASTNode.root_visited = -1;
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
  
  public void collectReferences(ArrayList paramArrayList)
  {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collectReferences(paramArrayList);
    }
  }
  
  public void dump()
  {
    dump("");
  }
  
  public void dump(String paramString)
  {
    System.out.println(dumpNode(paramString));
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).dump(paramString + " ");
    }
  }
  
  public boolean evaluateBoolean()
  {
    return false;
  }
  
  public int evaluateInt()
  {
    return (int)evaluateReal();
  }
  
  public String evaluateString()
  {
    return "";
  }
  
  public double evaluateReal()
  {
    return 0.0D;
  }
  
  public ASTNode(int paramInt)
  {
    super(paramInt);
  }
  
  public ASTNode(ActionParser paramActionParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramActionParser;
  }
  
  public ASTNode()
  {
    this(0);
  }
  
  public void dumpTree(String paramString, PrintStream paramPrintStream)
  {
    paramPrintStream.println(paramString + "ASTNode");
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
    if (paramInt >= this.numChildren) {
      this.numChildren = (paramInt + 1);
    }
    super.jjtAddChild(paramNode, paramInt);
  }
  
  public void checkChild(Node paramNode, int paramInt) {}
  
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
    return (T)(ASTNode)this.children[paramInt];
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
      ASTNode localASTNode = (ASTNode)this.children[paramInt];
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
    if ((this.parent != null) && (((ASTNode)this.parent).is$Final() != is$Final())) {
      state().boundariesCrossed += 1;
    }
    return (ASTNode)this.parent;
  }
  
  public void setParent(ASTNode paramASTNode)
  {
    this.parent = paramASTNode;
  }
  
  protected boolean duringRewrites()
  {
    if (state().duringRewrites == 0) {
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
  
  protected void collect_contributors_Goal_messages()
  {
    for (int i = 0; i < getNumChild(); i++) {
      getChild(i).collect_contributors_Goal_messages();
    }
  }
  
  protected void contributeTo_Goal_Goal_messages(SortedList<CompilationMessage> paramSortedList) {}
  
  public boolean isWorkspace()
  {
    ASTNode.State localState = state();
    if (this.isWorkspace_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: isWorkspace in class: ");
    }
    this.isWorkspace_visited = state().boundariesCrossed;
    boolean bool = isWorkspace_compute();
    this.isWorkspace_visited = -1;
    return bool;
  }
  
  private boolean isWorkspace_compute()
  {
    return true;
  }
  
  public CompilationMessage createError(String paramString)
  {
    String str = paramString;
    if (this.createError_String_visited == null) {
      this.createError_String_visited = new HashMap(4);
    }
    ASTNode.State localState = state();
    if (Integer.valueOf(state().boundariesCrossed).equals(this.createError_String_visited.get(str))) {
      throw new RuntimeException("Circular definition of attr: createError in class: ");
    }
    this.createError_String_visited.put(str, Integer.valueOf(state().boundariesCrossed));
    CompilationMessage localCompilationMessage = createError_compute(paramString);
    this.createError_String_visited.remove(str);
    return localCompilationMessage;
  }
  
  private CompilationMessage createError_compute(String paramString)
  {
    return new CompilationMessage(paramString, CompilationMessage.Kind.eError);
  }
  
  public CompilationMessage createWarning(String paramString)
  {
    String str = paramString;
    if (this.createWarning_String_visited == null) {
      this.createWarning_String_visited = new HashMap(4);
    }
    ASTNode.State localState = state();
    if (Integer.valueOf(state().boundariesCrossed).equals(this.createWarning_String_visited.get(str))) {
      throw new RuntimeException("Circular definition of attr: createWarning in class: ");
    }
    this.createWarning_String_visited.put(str, Integer.valueOf(state().boundariesCrossed));
    CompilationMessage localCompilationMessage = createWarning_compute(paramString);
    this.createWarning_String_visited.remove(str);
    return localCompilationMessage;
  }
  
  private CompilationMessage createWarning_compute(String paramString)
  {
    return new CompilationMessage(paramString, CompilationMessage.Kind.eWarning);
  }
  
  public String toString(String paramString)
  {
    String str1 = paramString;
    if (this.toString_String_visited == null) {
      this.toString_String_visited = new HashMap(4);
    }
    ASTNode.State localState = state();
    if (Integer.valueOf(state().boundariesCrossed).equals(this.toString_String_visited.get(str1))) {
      throw new RuntimeException("Circular definition of attr: toString in class: ");
    }
    this.toString_String_visited.put(str1, Integer.valueOf(state().boundariesCrossed));
    String str2 = toString_compute(paramString);
    this.toString_String_visited.remove(str1);
    return str2;
  }
  
  private String toString_compute(String paramString)
  {
    String str = getClass().getName();
    str = paramString + str.substring(str.lastIndexOf(".") + 1);
    return str;
  }
  
  public String dumpNode(String paramString)
  {
    String str1 = paramString;
    if (this.dumpNode_String_visited == null) {
      this.dumpNode_String_visited = new HashMap(4);
    }
    ASTNode.State localState = state();
    if (Integer.valueOf(state().boundariesCrossed).equals(this.dumpNode_String_visited.get(str1))) {
      throw new RuntimeException("Circular definition of attr: dumpNode in class: ");
    }
    this.dumpNode_String_visited.put(str1, Integer.valueOf(state().boundariesCrossed));
    String str2 = dumpNode_compute(paramString);
    this.dumpNode_String_visited.remove(str1);
    return str2;
  }
  
  private String dumpNode_compute(String paramString)
  {
    return toString(paramString);
  }
  
  public Goal root()
  {
    ASTNode.State localState = state();
    if (this.root_visited == state().boundariesCrossed) {
      throw new RuntimeException("Circular definition of attr: root in class: ");
    }
    this.root_visited = state().boundariesCrossed;
    Goal localGoal = getParent().Define_Goal_root(this, null);
    this.root_visited = -1;
    return localGoal;
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
  
  public SymbolTableObject Define_SymbolTableObject_symbolTable(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    return getParent().Define_SymbolTableObject_symbolTable(this, paramASTNode1);
  }
  
  public boolean Define_boolean_localLookupOnly(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    return getParent().Define_boolean_localLookupOnly(this, paramASTNode1);
  }
  
  public boolean Define_boolean_localLookupSkip(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    return getParent().Define_boolean_localLookupSkip(this, paramASTNode1);
  }
  
  public Object Define_Object_calledOn(ASTNode paramASTNode1, ASTNode paramASTNode2, String paramString)
  {
    return getParent().Define_Object_calledOn(this, paramASTNode1, paramString);
  }
  
  public Goal Define_Goal_root(ASTNode paramASTNode1, ASTNode paramASTNode2)
  {
    return getParent().Define_Goal_root(this, paramASTNode1);
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
  protected int duringRewrites = 0;
  
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
    if (this.duringRewrites != 0)
    {
      System.out.println("Warning: resetting duringRewrites");
      this.duringRewrites = 0;
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ASTNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */