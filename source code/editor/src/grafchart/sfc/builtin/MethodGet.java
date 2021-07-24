package grafchart.sfc.builtin;

import grafchart.sfc.BasicList;
import grafchart.sfc.BooleanList;
import grafchart.sfc.IntegerList;
import grafchart.sfc.RealList;
import grafchart.sfc.StringList;

public class MethodGet
  extends AbstractExecutable
{
  public MethodGet()
  {
    super("get", 1);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    try
    {
      if ((paramObject instanceof BooleanList))
      {
        BooleanList localBooleanList = (BooleanList)paramObject;
        return localBooleanList.get(paramArrayOfEvaluatable[0].evaluateInt());
      }
      error("get - Illegal list type for " + ((BasicList)paramObject).getFullName());
      return false;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      error("get - Index out of bounds for " + ((BasicList)paramObject).getFullName());
    }
    return false;
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    try
    {
      Object localObject;
      if ((paramObject instanceof RealList))
      {
        localObject = (RealList)paramObject;
        return ((RealList)localObject).get(paramArrayOfEvaluatable[0].evaluateInt());
      }
      if ((paramObject instanceof IntegerList))
      {
        localObject = (IntegerList)paramObject;
        return ((IntegerList)localObject).get(paramArrayOfEvaluatable[0].evaluateInt());
      }
      error("get - Illegal list type for " + ((BasicList)paramObject).getFullName());
      return 0.0D;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      error("get - Index out of bounds for " + ((BasicList)paramObject).getFullName());
    }
    return 0.0D;
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    try
    {
      if ((paramObject instanceof StringList))
      {
        StringList localStringList = (StringList)paramObject;
        return localStringList.get(paramArrayOfEvaluatable[0].evaluateInt());
      }
      error("get - Illegal list type for " + ((BasicList)paramObject).getFullName());
      return "Error";
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      error("get - Index out of bounds for " + ((BasicList)paramObject).getFullName());
    }
    return "Error";
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof BasicList;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */