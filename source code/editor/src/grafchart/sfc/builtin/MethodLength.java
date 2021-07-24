package grafchart.sfc.builtin;

import grafchart.sfc.StringVariable;

public class MethodLength
  extends AbstractExecutable
{
  public MethodLength()
  {
    super("length", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    StringVariable localStringVariable = (StringVariable)paramObject;
    return localStringVariable.getStringVal().length();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof StringVariable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodLength.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */