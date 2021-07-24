package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoDrawable;
import grafchart.sfc.Browser;
import java.awt.Color;

public class MethodSetFillColor
  extends AbstractExecutable
{
  public MethodSetFillColor()
  {
    super("setFillColor", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Object localObject;
    Color localColor;
    if ((paramObject instanceof JGoDrawable))
    {
      localObject = (JGoDrawable)paramObject;
      localColor = new Color(paramArrayOfEvaluatable[0].evaluateInt());
      ((JGoDrawable)localObject).setBrush(JGoBrush.make(65535, localColor));
    }
    else
    {
      localObject = (Browser)paramObject;
      localColor = new Color(paramArrayOfEvaluatable[0].evaluateInt());
      ((Browser)localObject).setFillColor(localColor);
    }
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return ((paramObject instanceof JGoDrawable)) || ((paramObject instanceof Browser));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetFillColor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */