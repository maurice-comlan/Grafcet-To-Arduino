package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;
import grafchart.sfc.Browser;
import java.awt.Color;

public class MethodSetTextColor
  extends AbstractExecutable
{
  public MethodSetTextColor()
  {
    super("setTextColor", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Object localObject;
    Color localColor;
    if ((paramObject instanceof Browser))
    {
      localObject = (Browser)paramObject;
      localColor = new Color(paramArrayOfEvaluatable[0].evaluateInt());
      ((Browser)localObject).setTextColor(localColor);
    }
    else
    {
      localObject = (JGoText)paramObject;
      localColor = new Color(paramArrayOfEvaluatable[0].evaluateInt());
      ((JGoText)localObject).setTextColor(localColor);
    }
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return ((paramObject instanceof Browser)) || ((paramObject instanceof JGoText));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetTextColor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */