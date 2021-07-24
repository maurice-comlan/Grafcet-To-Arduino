package grafchart.sfc.builtin;

import grafchart.sfc.Editor;

public class FuncGetCCOMServer
  extends AbstractExecutable
{
  public FuncGetCCOMServer()
  {
    super("getCCOMServer", 0);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return Editor.serverIP;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncGetCCOMServer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */