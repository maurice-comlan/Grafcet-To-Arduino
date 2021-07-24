package grafchart.sfc.builtin;

import grafchart.sfc.Hierarchical;

public class MethodSetWorkspaceColor
  extends AbstractExecutable
{
  public MethodSetWorkspaceColor()
  {
    super("setWorkspaceColor", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Hierarchical localHierarchical = (Hierarchical)paramObject;
    localHierarchical.setWorkspaceColor(paramArrayOfEvaluatable[0].evaluateInt());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Hierarchical;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetWorkspaceColor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */