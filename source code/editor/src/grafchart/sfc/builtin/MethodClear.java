package grafchart.sfc.builtin;

import grafchart.sfc.BasicList;
import grafchart.sfc.Browser;

public class MethodClear
  extends AbstractExecutable
{
  public MethodClear()
  {
    super("clear", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Object localObject;
    if ((paramObject instanceof Browser))
    {
      localObject = (Browser)paramObject;
      int i = ((Browser)localObject).getNumItems();
      for (int j = i - 1; j >= 0; j--) {
        ((Browser)localObject).removeItem(j);
      }
    }
    else
    {
      localObject = (BasicList)paramObject;
      ((BasicList)localObject).clear();
    }
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return ((paramObject instanceof Browser)) || ((paramObject instanceof BasicList));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodClear.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */