package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;
import grafchart.sfc.Browser;
import java.awt.Color;

public class MethodGetTextColor
  extends AbstractExecutable
{
  public MethodGetTextColor()
  {
    super("getTextColor", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Object localObject;
    Color localColor;
    if ((paramObject instanceof Browser))
    {
      localObject = (Browser)paramObject;
      localColor = ((Browser)localObject).getTextColor();
    }
    else
    {
      localObject = (JGoText)paramObject;
      localColor = ((JGoText)localObject).getTextColor();
    }
    return localColor.getRGB();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return ((paramObject instanceof Browser)) || ((paramObject instanceof JGoText));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetTextColor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */