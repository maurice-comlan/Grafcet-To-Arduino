package grafchart.sfc.builtin;

import grafchart.sfc.Editor;

public class FuncDisconnectCCOM
  extends AbstractExecutable
{
  public FuncDisconnectCCOM()
  {
    super("disconnectCCOM", 0, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Editor.logoutBlasterAction();
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncDisconnectCCOM.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */