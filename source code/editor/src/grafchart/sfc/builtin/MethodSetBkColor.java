package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;
import java.awt.Color;

public class MethodSetBkColor
  extends AbstractExecutable
{
  public MethodSetBkColor()
  {
    super("setBkColor", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoText localJGoText = (JGoText)paramObject;
    Color localColor = new Color(paramArrayOfEvaluatable[0].evaluateInt());
    localJGoText.setBkColor(localColor);
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoText;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetBkColor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */