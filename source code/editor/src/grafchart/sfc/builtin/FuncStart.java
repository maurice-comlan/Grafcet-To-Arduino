package grafchart.sfc.builtin;

import grafchart.sfc.Editor;
import grafchart.sfc.Utils;

public class FuncStart
  extends AbstractExecutable
{
  public FuncStart()
  {
    super("start", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    try
    {
      Editor.singleton.startNamedWorkspace(paramArrayOfEvaluatable[0].evaluateString());
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncStart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */