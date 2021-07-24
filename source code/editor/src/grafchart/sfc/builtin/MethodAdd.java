package grafchart.sfc.builtin;

import grafchart.sfc.BasicList;
import grafchart.sfc.BooleanList;
import grafchart.sfc.IntegerList;
import grafchart.sfc.RealList;
import grafchart.sfc.StringList;

public class MethodAdd
  extends AbstractExecutable
{
  public MethodAdd()
  {
    super("add", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    try
    {
      Object localObject;
      if ((paramObject instanceof RealList))
      {
        localObject = (RealList)paramObject;
        if (paramArrayOfEvaluatable.length == 1) {
          ((RealList)localObject).add(paramArrayOfEvaluatable[0].evaluateReal());
        } else if (paramArrayOfEvaluatable.length == 2) {
          ((RealList)localObject).add(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateReal());
        }
      }
      else if ((paramObject instanceof StringList))
      {
        localObject = (StringList)paramObject;
        if (paramArrayOfEvaluatable.length == 1) {
          ((StringList)localObject).add(paramArrayOfEvaluatable[0].evaluateString());
        } else if (paramArrayOfEvaluatable.length == 2) {
          ((StringList)localObject).add(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateString());
        }
      }
      else if ((paramObject instanceof IntegerList))
      {
        localObject = (IntegerList)paramObject;
        if (paramArrayOfEvaluatable.length == 1) {
          ((IntegerList)localObject).add(paramArrayOfEvaluatable[0].evaluateInt());
        } else if (paramArrayOfEvaluatable.length == 2) {
          ((IntegerList)localObject).add(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateInt());
        }
      }
      else if ((paramObject instanceof BooleanList))
      {
        localObject = (BooleanList)paramObject;
        if (paramArrayOfEvaluatable.length == 1)
        {
          ((BooleanList)localObject).add(paramArrayOfEvaluatable[0].evaluateBoolean());
          return 0.0D;
        }
        if (paramArrayOfEvaluatable.length == 2) {
          ((BooleanList)localObject).add(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateBoolean());
        }
      }
      else
      {
        error("add - Illegal list type for " + ((BasicList)paramObject).getFullName());
      }
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      error("add - Index out of bounds for " + ((BasicList)paramObject).getFullName());
    }
    return 0.0D;
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    return (paramInt == 1) || (paramInt == 2);
  }
  
  public String getCorrectNrOfArguments()
  {
    return "1 or 2";
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof BasicList;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodAdd.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */