package grafchart.sfc.builtin;

public class FuncCos
  extends AbstractExecutable
{
  public FuncCos()
  {
    super("cos", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return Math.cos(paramArrayOfEvaluatable[0].evaluateReal());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncCos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */