package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoDrawable;
import com.nwoods.jgo.JGoPen;
import java.awt.Color;

public class MethodSetPenColor
  extends AbstractExecutable
{
  public MethodSetPenColor()
  {
    super("setPenColor", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    JGoDrawable localJGoDrawable = (JGoDrawable)paramObject;
    JGoPen localJGoPen = localJGoDrawable.getPen();
    int i = localJGoPen.getStyle();
    int j = localJGoPen.getWidth();
    Color localColor = new Color(paramArrayOfEvaluatable[0].evaluateInt());
    localJGoDrawable.setPen(JGoPen.make(i, j, localColor));
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof JGoDrawable;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetPenColor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */