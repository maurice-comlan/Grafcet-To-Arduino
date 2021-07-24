package grafchart.sfc.builtin;

public abstract interface Executable
{
  public abstract boolean executeBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject);
  
  public abstract double executeReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject);
  
  public abstract String executeString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject);
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/Executable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */