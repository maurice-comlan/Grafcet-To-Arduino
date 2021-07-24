package grafchart.sfc.builtin;

import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.Utils;

public class FuncLoadCompileStart
  extends AbstractExecutable
{
  public FuncLoadCompileStart()
  {
    super("loadCompileStart", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    try
    {
      GCDocument localGCDocument = Editor.singleton.openWorkspace(paramArrayOfEvaluatable[0].evaluateString());
      boolean bool = Editor.singleton.compileWorkspace(localGCDocument);
      if (bool) {
        Editor.singleton.startWorkspace(localGCDocument);
      }
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncLoadCompileStart.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */