package grafchart.sfc.builtin;

import grafchart.sfc.Editor;
import grafchart.sfc.Utils;

public class FuncCompile
  extends AbstractExecutable
{
  public FuncCompile()
  {
    super("compile", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    internalExecuteBool(paramArrayOfEvaluatable, paramObject);
    return 0.0D;
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    boolean bool = false;
    try
    {
      bool = Editor.singleton.compileNamedWorkspace(paramArrayOfEvaluatable[0].evaluateString());
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
    return bool;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncCompile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */