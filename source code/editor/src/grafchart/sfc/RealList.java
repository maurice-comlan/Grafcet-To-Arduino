package grafchart.sfc;

import java.awt.Point;

public class RealList
  extends BasicList
{
  public RealList() {}
  
  public RealList(Point paramPoint)
  {
    super(paramPoint, "R");
  }
  
  public void addInitialValue(String paramString)
  {
    try
    {
      add(Double.parseDouble(paramString));
    }
    catch (NumberFormatException localNumberFormatException)
    {
      Utils.writeError("Real List \"" + getFullName() + "\" has illegal initial value \"" + paramString + "\".");
    }
  }
  
  public void add(int paramInt, double paramDouble)
  {
    basicAdd(paramInt, new Double(paramDouble));
  }
  
  public void add(double paramDouble)
  {
    basicAdd(new Double(paramDouble));
  }
  
  public void set(int paramInt, double paramDouble)
  {
    basicSet(paramInt, new Double(paramDouble));
  }
  
  public void addFirst(double paramDouble)
  {
    basicAddFirst(new Double(paramDouble));
  }
  
  public int indexOf(double paramDouble)
  {
    return basicIndexOf(new Double(paramDouble));
  }
  
  public double get(int paramInt)
  {
    Object localObject = basicGet(paramInt);
    Double localDouble = (Double)localObject;
    return localDouble.doubleValue();
  }
  
  public String getHelpID()
  {
    return "LangRef_Var_RealList";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/RealList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */