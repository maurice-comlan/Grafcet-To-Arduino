package grafchart.sfc.builtin;

import grafchart.sfc.Browser;

public class MethodGetTextSize
  extends AbstractExecutable
{
  public MethodGetTextSize()
  {
    super("getTextSize", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Browser localBrowser = (Browser)paramObject;
    return localBrowser.getFontSize();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Browser;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetTextSize.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */