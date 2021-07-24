package grafchart.sfc.builtin;

import grafchart.sfc.Referencable;
import java.awt.Point;

public class MethodGetYLocation
  extends AbstractExecutable
{
  public MethodGetYLocation()
  {
    super("getYLocation", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Point localPoint = ((Referencable)paramObject).getLocation();
    return localPoint.getY();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Referencable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetYLocation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */