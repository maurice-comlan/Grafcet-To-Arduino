package grafchart.sfc.builtin;

import grafchart.sfc.BasicList;
import grafchart.sfc.BooleanList;
import grafchart.sfc.IntegerList;
import grafchart.sfc.RealList;
import grafchart.sfc.StringList;

public class MethodAddFirst
  extends AbstractExecutable
{
  public MethodAddFirst()
  {
    super("addFirst", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Object localObject;
    if ((paramObject instanceof RealList))
    {
      localObject = (RealList)paramObject;
      ((RealList)localObject).addFirst(paramArrayOfEvaluatable[0].evaluateReal());
    }
    else if ((paramObject instanceof StringList))
    {
      localObject = (StringList)paramObject;
      ((StringList)localObject).addFirst(paramArrayOfEvaluatable[0].evaluateString());
    }
    else if ((paramObject instanceof IntegerList))
    {
      localObject = (IntegerList)paramObject;
      ((IntegerList)localObject).addFirst(paramArrayOfEvaluatable[0].evaluateInt());
    }
    else if ((paramObject instanceof BooleanList))
    {
      localObject = (BooleanList)paramObject;
      ((BooleanList)localObject).addFirst(paramArrayOfEvaluatable[0].evaluateBoolean());
    }
    else
    {
      error("addFirst - Illegal list type for " + ((BasicList)paramObject).getFullName());
    }
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof BasicList;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodAddFirst.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */