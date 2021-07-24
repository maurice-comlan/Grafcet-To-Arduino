package grafchart.sfc.builtin;

import grafchart.sfc.Icon;

public class MethodLoadURL
  extends AbstractExecutable
{
  public MethodLoadURL()
  {
    super("loadURL", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Icon localIcon = (Icon)paramObject;
    localIcon.loadImage(paramArrayOfEvaluatable[0].evaluateString());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Icon;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodLoadURL.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */