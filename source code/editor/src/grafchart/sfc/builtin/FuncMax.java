package grafchart.sfc.builtin;

public class FuncMax
  extends AbstractExecutable
{
  public FuncMax()
  {
    super("max", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    double d = paramArrayOfEvaluatable[0].evaluateReal();
    for (int i = 1; i < paramArrayOfEvaluatable.length; i++) {
      d = Math.max(d, paramArrayOfEvaluatable[i].evaluateReal());
    }
    return d;
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    return paramInt > 0;
  }
  
  public String getCorrectNrOfArguments()
  {
    return ">0";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncMax.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */