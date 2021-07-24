package grafchart.sfc.builtin;

import grafchart.sfc.InternalVariable;

public class MethodSetUpdated
  extends AbstractExecutable
{
  public MethodSetUpdated()
  {
    super("setUpdated", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    InternalVariable localInternalVariable = (InternalVariable)paramObject;
    localInternalVariable.isUpdated = paramArrayOfEvaluatable[0].evaluateBoolean();
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof InternalVariable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetUpdated.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */