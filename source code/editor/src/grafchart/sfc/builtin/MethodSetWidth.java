package grafchart.sfc.builtin;

import grafchart.sfc.Referencable;

public class MethodSetWidth
  extends AbstractExecutable
{
  public MethodSetWidth()
  {
    super("setWidth", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    ((Referencable)paramObject).setWidth(paramArrayOfEvaluatable[0].evaluateInt());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Referencable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetWidth.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */