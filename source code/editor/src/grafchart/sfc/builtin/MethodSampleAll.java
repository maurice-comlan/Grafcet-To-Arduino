package grafchart.sfc.builtin;

import grafchart.sfc.Workspace;

public class MethodSampleAll
  extends AbstractExecutable
{
  public MethodSampleAll()
  {
    super("sampleAll", 0, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Workspace localWorkspace = (Workspace)paramObject;
    localWorkspace.sampleAll();
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Workspace;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSampleAll.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */