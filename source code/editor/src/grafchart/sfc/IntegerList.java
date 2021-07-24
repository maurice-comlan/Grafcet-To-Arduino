package grafchart.sfc;

import java.awt.Point;

public class IntegerList
  extends BasicList
{
  public IntegerList() {}
  
  public IntegerList(Point paramPoint)
  {
    super(paramPoint, "I");
  }
  
  public void addInitialValue(String paramString)
  {
    try
    {
      add(Integer.parseInt(paramString));
    }
    catch (NumberFormatException localNumberFormatException)
    {
      Utils.writeError("Integer List \"" + getFullName() + "\" has illegal initial value \"" + paramString + "\".");
    }
  }
  
  public void add(int paramInt1, int paramInt2)
  {
    basicAdd(paramInt1, new Integer(paramInt2));
  }
  
  public void add(int paramInt)
  {
    basicAdd(new Integer(paramInt));
  }
  
  public void addFirst(int paramInt)
  {
    basicAddFirst(new Integer(paramInt));
  }
  
  public int indexOf(int paramInt)
  {
    return basicIndexOf(new Integer(paramInt));
  }
  
  public void set(int paramInt1, int paramInt2)
  {
    basicSet(paramInt1, new Integer(paramInt2));
  }
  
  public int get(int paramInt)
  {
    Object localObject = basicGet(paramInt);
    Integer localInteger = (Integer)localObject;
    return localInteger.intValue();
  }
  
  public String getHelpID()
  {
    return "LangRef_Var_IntegerList";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/IntegerList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */