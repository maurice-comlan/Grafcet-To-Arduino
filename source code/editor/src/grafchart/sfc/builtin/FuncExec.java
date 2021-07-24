package grafchart.sfc.builtin;

import grafchart.sfc.Utils;

public class FuncExec
  extends AbstractExecutable
{
  public FuncExec()
  {
    super("exec", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    try
    {
      Runtime.getRuntime().exec(paramArrayOfEvaluatable[0].evaluateString());
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncExec.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */