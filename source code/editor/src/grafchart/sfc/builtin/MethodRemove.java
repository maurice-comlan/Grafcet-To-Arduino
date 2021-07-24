package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoLayer;
import com.nwoods.jgo.JGoObject;
import grafchart.sfc.BasicList;
import grafchart.sfc.GCEllipse;
import grafchart.sfc.GCRectangle;

public class MethodRemove
  extends AbstractExecutable
{
  private Object hack;
  
  public MethodRemove()
  {
    super("remove", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    if ((paramObject instanceof BasicList))
    {
      BasicList localBasicList = (BasicList)paramObject;
      try
      {
        localBasicList.remove(paramArrayOfEvaluatable[0].evaluateInt());
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        error("remove - Index out of bounds for " + localBasicList.getFullName());
      }
    }
    else
    {
      ((JGoObject)paramObject).getLayer().removeObject((JGoObject)paramObject);
    }
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    this.hack = paramObject;
    return ((paramObject instanceof BasicList)) || ((paramObject instanceof GCRectangle)) || ((paramObject instanceof GCEllipse));
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    if ((this.hack instanceof BasicList)) {
      return paramInt == 1;
    }
    return paramInt == 0;
  }
  
  public String getCorrectNrOfArguments()
  {
    if ((this.hack instanceof BasicList)) {
      return "1";
    }
    return "0";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodRemove.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */