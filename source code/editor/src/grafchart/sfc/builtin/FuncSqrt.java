package grafchart.sfc.builtin;

public class FuncSqrt
  extends AbstractExecutable
{
  public FuncSqrt()
  {
    super("sqrt", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    double d = Math.sqrt(paramArrayOfEvaluatable[0].evaluateReal());
    if (Double.isNaN(d))
    {
      runtimeError("sqrt(" + paramArrayOfEvaluatable[0] + ") is NaN. Returning 0.0.");
      d = 0.0D;
    }
    return d;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncSqrt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */