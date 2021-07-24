package grafchart.sfc.builtin;

import grafchart.sfc.XMLMessageIn;
import grafchart.sfc.XMLMessageOut;

public class MethodGetHandle
  extends AbstractExecutable
{
  public MethodGetHandle()
  {
    super("getHandle", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    if ((paramObject instanceof XMLMessageIn))
    {
        XMLMessageIn localObject = (XMLMessageIn)paramObject;
      return ((XMLMessageIn)localObject).handle;
    }
    Object localObject = (XMLMessageOut)paramObject;
    return ((XMLMessageOut)localObject).getHandle();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return ((paramObject instanceof XMLMessageIn)) || ((paramObject instanceof XMLMessageOut));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetHandle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */