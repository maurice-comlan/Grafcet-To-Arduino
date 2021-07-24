package grafchart.sfc.builtin;

public class FuncSetCCOMLoginName
  extends AbstractExecutable
{
  public FuncSetCCOMLoginName()
  {
    super("setCCOMLoginName", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    grafchart.sfc.Editor.loginName = paramArrayOfEvaluatable[0].evaluateString();
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncSetCCOMLoginName.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */