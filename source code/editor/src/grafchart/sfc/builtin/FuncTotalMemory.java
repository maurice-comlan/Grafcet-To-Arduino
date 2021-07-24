package grafchart.sfc.builtin;

import java.io.PrintStream;

public class FuncTotalMemory
  extends AbstractExecutable
{
  public FuncTotalMemory()
  {
    super("totalMemory", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    System.out.println("Total Memory: " + Runtime.getRuntime().totalMemory() + " B");
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncTotalMemory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */