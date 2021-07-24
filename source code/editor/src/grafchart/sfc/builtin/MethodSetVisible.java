package grafchart.sfc.builtin;

import grafchart.sfc.Referencable;

public class MethodSetVisible
  extends AbstractExecutable
{
  public MethodSetVisible()
  {
    super("setVisible", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    ((Referencable)paramObject).setVisible(paramArrayOfEvaluatable[0].evaluateBoolean());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Referencable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetVisible.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */