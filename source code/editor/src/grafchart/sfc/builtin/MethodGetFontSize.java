package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;

public class MethodGetFontSize
  extends AbstractExecutable
{
  public MethodGetFontSize()
  {
    super("getFontSize", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoText localJGoText = (JGoText)paramObject;
    return localJGoText.getFontSize();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoText;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetFontSize.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */