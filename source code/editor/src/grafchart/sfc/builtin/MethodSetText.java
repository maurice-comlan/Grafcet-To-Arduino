package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;

public class MethodSetText
  extends AbstractExecutable
{
  public MethodSetText()
  {
    super("setText", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoText localJGoText = (JGoText)paramObject;
    localJGoText.setText(paramArrayOfEvaluatable[0].evaluateString());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoText;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */