package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoDrawable;
import grafchart.sfc.Browser;
import java.awt.Color;

public class MethodGetFillColor
  extends AbstractExecutable
{
  public MethodGetFillColor()
  {
    super("getFillColor", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Object localObject;
    Color localColor;
    if ((paramObject instanceof JGoDrawable))
    {
      localObject = (JGoDrawable)paramObject;
      JGoBrush localJGoBrush = ((JGoDrawable)localObject).getBrush();
      localColor = localJGoBrush.getColor();
    }
    else
    {
      localObject = (Browser)paramObject;
      localColor = ((Browser)localObject).getFillColor();
    }
    return localColor.getRGB();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return ((paramObject instanceof JGoDrawable)) || ((paramObject instanceof Browser));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetFillColor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */