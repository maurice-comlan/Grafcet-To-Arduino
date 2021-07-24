package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoStroke;

public class MethodSetPoint
  extends AbstractExecutable
{
  public MethodSetPoint()
  {
    super("setPoint", 3);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoStroke localJGoStroke = (JGoStroke)paramObject;
    localJGoStroke.setPoint(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateInt(), paramArrayOfEvaluatable[2].evaluateInt());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoStroke;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */