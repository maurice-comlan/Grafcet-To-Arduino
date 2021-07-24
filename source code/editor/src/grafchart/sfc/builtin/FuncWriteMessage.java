package grafchart.sfc.builtin;

import grafchart.sfc.Editor;

public class FuncWriteMessage
  extends AbstractExecutable
{
  public FuncWriteMessage()
  {
    super("writeMessage", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Editor.writeMessage(paramArrayOfEvaluatable[0].evaluateString());
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncWriteMessage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */