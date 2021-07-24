package grafchart.sfc.builtin;

import grafchart.sfc.XMLMessageOut;

public class MethodGetPublish
  extends AbstractExecutable
{
  public MethodGetPublish()
  {
    super("getPublish", 0);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    XMLMessageOut localXMLMessageOut = (XMLMessageOut)paramObject;
    return localXMLMessageOut.getPublish();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof XMLMessageOut;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetPublish.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */