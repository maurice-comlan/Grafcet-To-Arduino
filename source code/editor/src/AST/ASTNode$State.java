package AST;

import java.io.PrintStream;
import java.util.AbstractSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

/*
public class ASTNode$State
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/ASTNode$State.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */