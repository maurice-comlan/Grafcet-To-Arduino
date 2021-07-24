package grafchart.sfc.builtin;

import grafchart.sfc.GrafcetObject;

public class MethodT
  extends AbstractExecutable
{
  public MethodT()
  {
    super("t", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    GrafcetObject localGrafcetObject = (GrafcetObject)paramObject;
    return localGrafcetObject.timer;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof GrafcetObject;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodT.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */