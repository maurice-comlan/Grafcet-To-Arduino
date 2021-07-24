package grafchart.sfc.builtin;

import grafchart.sfc.InputVariable;

public class MethodSample
  extends AbstractExecutable
{
  public MethodSample()
  {
    super("sample", 0, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    InputVariable localInputVariable = (InputVariable)paramObject;
    return localInputVariable.getSample();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof InputVariable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */