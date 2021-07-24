package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;

public class MethodSetFontSize
  extends AbstractExecutable
{
  public MethodSetFontSize()
  {
    super("setFontSize", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoText localJGoText = (JGoText)paramObject;
    localJGoText.setFontSize(paramArrayOfEvaluatable[0].evaluateInt());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoText;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetFontSize.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */