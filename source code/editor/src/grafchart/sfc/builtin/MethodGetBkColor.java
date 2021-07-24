package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;
import java.awt.Color;

public class MethodGetBkColor
  extends AbstractExecutable
{
  public MethodGetBkColor()
  {
    super("getBkColor", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoText localJGoText = (JGoText)paramObject;
    Color localColor = localJGoText.getBkColor();
    return localColor.getRGB();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoText;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetBkColor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */