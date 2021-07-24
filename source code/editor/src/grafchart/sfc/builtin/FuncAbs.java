package grafchart.sfc.builtin;

public class FuncAbs
  extends AbstractExecutable
{
  public FuncAbs()
  {
    super("abs", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return Math.abs(paramArrayOfEvaluatable[0].evaluateReal());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncAbs.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */