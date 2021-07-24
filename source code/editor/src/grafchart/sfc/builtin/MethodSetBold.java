package grafchart.sfc.builtin;

import grafchart.sfc.Browser;

public class MethodSetBold
  extends AbstractExecutable
{
  public MethodSetBold()
  {
    super("setBold", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Browser localBrowser = (Browser)paramObject;
    localBrowser.setBold(paramArrayOfEvaluatable[0].evaluateBoolean());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Browser;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSetBold.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */