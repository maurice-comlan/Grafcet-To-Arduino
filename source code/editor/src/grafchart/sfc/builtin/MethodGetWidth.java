package grafchart.sfc.builtin;

import grafchart.sfc.Referencable;

public class MethodGetWidth
  extends AbstractExecutable
{
  public MethodGetWidth()
  {
    super("getWidth", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return ((Referencable)paramObject).getWidth();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Referencable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetWidth.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */