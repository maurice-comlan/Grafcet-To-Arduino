package grafchart.sfc.builtin;

import grafchart.sfc.BasicList;
import grafchart.sfc.BooleanList;
import grafchart.sfc.IntegerList;
import grafchart.sfc.RealList;
import grafchart.sfc.StringList;

public class MethodSet
  extends AbstractExecutable
{
  public MethodSet()
  {
    super("set", 2);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    try
    {
      Object localObject;
      if ((paramObject instanceof RealList))
      {
        localObject = (RealList)paramObject;
        ((RealList)localObject).set(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateReal());
      }
      else if ((paramObject instanceof StringList))
      {
        localObject = (StringList)paramObject;
        ((StringList)localObject).set(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateString());
      }
      else if ((paramObject instanceof IntegerList))
      {
        localObject = (IntegerList)paramObject;
        ((IntegerList)localObject).set(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateInt());
      }
      else if ((paramObject instanceof BooleanList))
      {
        localObject = (BooleanList)paramObject;
        ((BooleanList)localObject).set(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateBoolean());
      }
      else
      {
        error("set - Illegal list type for " + ((BasicList)paramObject).getFullName());
      }
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      error("set - Index out of bounds for " + ((BasicList)paramObject).getFullName());
    }
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof BasicList;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */