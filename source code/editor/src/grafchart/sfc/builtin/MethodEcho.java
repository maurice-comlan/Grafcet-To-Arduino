package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;
import java.io.PrintStream;

public class MethodEcho
  extends AbstractExecutable
{
  public MethodEcho()
  {
    super("echo", 0, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoText localJGoText = (JGoText)paramObject;
    System.out.println("Echo = " + localJGoText.getText());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoText;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodEcho.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */