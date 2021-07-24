package grafchart.sfc.builtin;

import java.io.PrintStream;

public class FuncMaxMemory
  extends AbstractExecutable
{
  public FuncMaxMemory()
  {
    super("maxMemory", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    System.out.println("Max Memory:   " + Runtime.getRuntime().maxMemory() + " B");
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncMaxMemory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */