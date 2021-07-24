package grafchart.sfc.builtin;

public class FuncRandom
  extends AbstractExecutable
{
  public FuncRandom()
  {
    super("random", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return Math.random();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncRandom.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */