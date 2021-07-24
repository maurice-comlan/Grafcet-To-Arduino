package grafchart.sfc.builtin;

import grafchart.sfc.Hierarchical;

public class MethodShowWorkspaceAt
  extends AbstractExecutable
{
  public MethodShowWorkspaceAt()
  {
    super("showWorkspaceAt", 2, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Hierarchical localHierarchical = (Hierarchical)paramObject;
    localHierarchical.showWorkspace(paramArrayOfEvaluatable[0].evaluateInt(), paramArrayOfEvaluatable[1].evaluateInt());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Hierarchical;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodShowWorkspaceAt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */