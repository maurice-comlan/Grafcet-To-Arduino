package grafchart.sfc.builtin;

import grafchart.sfc.Editor;

public class FuncClearMessages
  extends AbstractExecutable
{
  public FuncClearMessages()
  {
    super("clearMessages", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Editor.clearMessages();
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncClearMessages.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */