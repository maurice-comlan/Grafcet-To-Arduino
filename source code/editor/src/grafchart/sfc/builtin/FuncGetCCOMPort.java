package grafchart.sfc.builtin;

import grafchart.sfc.Editor;

public class FuncGetCCOMPort
  extends AbstractExecutable
{
  public FuncGetCCOMPort()
  {
    super("getCCOMPort", 0);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return Editor.port;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncGetCCOMPort.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */