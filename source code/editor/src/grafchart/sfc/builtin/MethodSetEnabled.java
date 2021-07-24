package grafchart.sfc.builtin;

import grafchart.sfc.WorkspaceObject;

public class MethodSetEnabled
  extends AbstractExecutable
{
  public MethodSetEnabled()
  {
    super("setEnabled", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    WorkspaceObject localWorkspaceObject = (WorkspaceObject)paramObject;
    localWorkspaceObject.enabled = paramArrayOfEvaluatable[0].evaluateBoolean();
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof WorkspaceObject;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetEnabled.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */