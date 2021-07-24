package grafchart.sfc.builtin;

import grafchart.sfc.InternalVariable;

public class MethodIsUpdated
  extends AbstractExecutable
{
  public MethodIsUpdated()
  {
    super("isUpdated", 0);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    InternalVariable localInternalVariable = (InternalVariable)paramObject;
    return localInternalVariable.isUpdated;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof InternalVariable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodIsUpdated.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */