package grafchart.sfc.builtin;

import grafchart.sfc.Workspace;

public class MethodGetTickTime
  extends AbstractExecutable
{
  public MethodGetTickTime()
  {
    super("getTickTime", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return ((Workspace)paramObject).getTickTime();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Workspace;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetTickTime.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */