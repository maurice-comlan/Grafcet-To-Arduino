package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoStroke;

public class MethodGetPointY
  extends AbstractExecutable
{
  public MethodGetPointY()
  {
    super("getPointY", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoStroke localJGoStroke = (JGoStroke)paramObject;
    return localJGoStroke.getPointY(paramArrayOfEvaluatable[0].evaluateInt());
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoStroke;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetPointY.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */