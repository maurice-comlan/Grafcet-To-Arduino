package grafchart.sfc.builtin;

import grafchart.sfc.Editor;

public class FuncIsConnectedToCCOM
  extends AbstractExecutable
{
  public FuncIsConnectedToCCOM()
  {
    super("isConnectedToCCOM", 0);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return Editor.connectedToBlasterServer;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncIsConnectedToCCOM.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */