package grafchart.sfc.builtin;

import grafchart.sfc.Browser;

public class MethodSetFont
  extends AbstractExecutable
{
  public MethodSetFont()
  {
    super("setFont", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Browser localBrowser = (Browser)paramObject;
    localBrowser.setFaceName(paramArrayOfEvaluatable[0].evaluateString());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Browser;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetFont.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */