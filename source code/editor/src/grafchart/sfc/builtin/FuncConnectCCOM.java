package grafchart.sfc.builtin;

import grafchart.sfc.Editor;

public class FuncConnectCCOM
  extends AbstractExecutable
{
  public FuncConnectCCOM()
  {
    super("connectCCOM", 0, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Editor.initBlasterAction();
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncConnectCCOM.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */