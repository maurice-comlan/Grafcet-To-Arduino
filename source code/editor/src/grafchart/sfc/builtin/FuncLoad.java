package grafchart.sfc.builtin;

import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.Utils;

public class FuncLoad
  extends AbstractExecutable
{
  public FuncLoad()
  {
    super("load", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    internalExecuteString(paramArrayOfEvaluatable, paramObject);
    return 0.0D;
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    String str = "";
    try
    {
      GCDocument localGCDocument = Editor.singleton.openWorkspace(paramArrayOfEvaluatable[0].evaluateString());
      if (localGCDocument != null) {
        str = localGCDocument.getName();
      }
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
    return str;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncLoad.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */