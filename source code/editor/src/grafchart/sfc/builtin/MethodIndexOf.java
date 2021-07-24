package grafchart.sfc.builtin;

import grafchart.sfc.BasicList;
import grafchart.sfc.BooleanList;
import grafchart.sfc.IntegerList;
import grafchart.sfc.RealList;
import grafchart.sfc.StringList;

public class MethodIndexOf
  extends AbstractExecutable
{
  public MethodIndexOf()
  {
    super("indexOf", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Object localObject;
    if ((paramObject instanceof RealList))
    {
      localObject = (RealList)paramObject;
      return ((RealList)localObject).indexOf(paramArrayOfEvaluatable[0].evaluateReal());
    }
    if ((paramObject instanceof StringList))
    {
      localObject = (StringList)paramObject;
      return ((StringList)localObject).indexOf(paramArrayOfEvaluatable[0].evaluateString());
    }
    if ((paramObject instanceof IntegerList))
    {
      localObject = (IntegerList)paramObject;
      return ((IntegerList)localObject).indexOf(paramArrayOfEvaluatable[0].evaluateInt());
    }
    if ((paramObject instanceof BooleanList))
    {
      localObject = (BooleanList)paramObject;
      return ((BooleanList)localObject).indexOf(paramArrayOfEvaluatable[0].evaluateBoolean());
    }
    error("indexOf - Illegal list type for " + ((BasicList)paramObject).getFullName());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof BasicList;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodIndexOf.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */