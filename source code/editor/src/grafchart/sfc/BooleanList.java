package grafchart.sfc;

import java.awt.Point;

public class BooleanList
  extends BasicList
{
  public BooleanList() {}
  
  public BooleanList(Point paramPoint)
  {
    super(paramPoint, "B");
  }
  
  public void addInitialValue(String paramString)
  {
    if (paramString.equals("1")) {
      add(true);
    } else if (paramString.equals("0")) {
      add(false);
    }
  }
  
  public void add(int paramInt, boolean paramBoolean)
  {
    basicAdd(paramInt, new Boolean(paramBoolean));
  }
  
  public void add(boolean paramBoolean)
  {
    basicAdd(new Boolean(paramBoolean));
  }
  
  public void addFirst(boolean paramBoolean)
  {
    basicAddFirst(new Boolean(paramBoolean));
  }
  
  public int indexOf(boolean paramBoolean)
  {
    return basicIndexOf(new Boolean(paramBoolean));
  }
  
  public void set(int paramInt, boolean paramBoolean)
  {
    basicSet(paramInt, new Boolean(paramBoolean));
  }
  
  public boolean get(int paramInt)
  {
    Object localObject = basicGet(paramInt);
    Boolean localBoolean = (Boolean)localObject;
    return localBoolean.booleanValue();
  }
  
  public String getHelpID()
  {
    return "LangRef_Var_BooleanList";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/BooleanList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */