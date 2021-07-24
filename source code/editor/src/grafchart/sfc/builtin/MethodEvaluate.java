package grafchart.sfc.builtin;

import grafchart.sfc.SocketIn;

public class MethodEvaluate
  extends AbstractExecutable
{
  public MethodEvaluate()
  {
    super("evaluate", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    SocketIn localSocketIn = (SocketIn)paramObject;
    localSocketIn.evaluate();
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof SocketIn;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodEvaluate.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */