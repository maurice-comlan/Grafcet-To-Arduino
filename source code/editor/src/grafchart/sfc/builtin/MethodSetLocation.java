package grafchart.sfc.builtin;

import grafchart.sfc.Referencable;

public class MethodSetLocation
  extends AbstractExecutable
{
  public MethodSetLocation()
  {
    super("setLocation", 2);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    ((Referencable)paramObject).setLocation(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateInt());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Referencable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetLocation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */