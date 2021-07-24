package grafchart.sfc.builtin;

import grafchart.sfc.XMLMessageIn;
import grafchart.sfc.XMLMessageOut;

public class MethodGetOrigin
  extends AbstractExecutable
{
  public MethodGetOrigin()
  {
    super("getOrigin", 0);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    if ((paramObject instanceof XMLMessageIn))
    {
        XMLMessageIn localObject = (XMLMessageIn)paramObject;
      return ((XMLMessageIn)localObject).origin;
    }
    Object localObject = (XMLMessageOut)paramObject;
    return ((XMLMessageOut)localObject).getOrigin();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return ((paramObject instanceof XMLMessageIn)) || ((paramObject instanceof XMLMessageOut));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetOrigin.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */