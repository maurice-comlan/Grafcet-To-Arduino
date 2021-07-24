package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoDrawable;
import com.nwoods.jgo.JGoPen;

public class MethodGetPenWidth
  extends AbstractExecutable
{
  public MethodGetPenWidth()
  {
    super("getPenWidth", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoDrawable localJGoDrawable = (JGoDrawable)paramObject;
    JGoPen localJGoPen = localJGoDrawable.getPen();
    return localJGoPen.getWidth();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoDrawable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetPenWidth.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */