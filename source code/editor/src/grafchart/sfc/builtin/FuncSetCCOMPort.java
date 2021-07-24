package grafchart.sfc.builtin;

public class FuncSetCCOMPort
  extends AbstractExecutable
{
  public FuncSetCCOMPort()
  {
    super("setCCOMPort", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    grafchart.sfc.Editor.port = paramArrayOfEvaluatable[0].evaluateString();
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncSetCCOMPort.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */