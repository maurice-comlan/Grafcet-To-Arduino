package grafchart.sfc;

import java.awt.Point;

public class StringList
  extends BasicList
{
  public StringList() {}
  
  public StringList(Point paramPoint)
  {
    super(paramPoint, "S");
  }
  
  public void addInitialValue(String paramString)
  {
    add(paramString);
  }
  
  public void add(int paramInt, String paramString)
  {
    basicAdd(paramInt, paramString);
  }
  
  public void add(String paramString)
  {
    basicAdd(paramString);
  }
  
  public void addFirst(String paramString)
  {
    basicAddFirst(paramString);
  }
  
  public int indexOf(String paramString)
  {
    return basicIndexOf(paramString);
  }
  
  public void set(int paramInt, String paramString)
  {
    basicSet(paramInt, paramString);
  }
  
  public String get(int paramInt)
  {
    Object localObject = basicGet(paramInt);
    String str = (String)localObject;
    return str;
  }
  
  public String getHelpID()
  {
    return "LangRef_Var_StringList";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/StringList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */