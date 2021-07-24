package grafchart.sfc.builtin;

import grafchart.sfc.XMLMessageOut;

public class MethodGetDestination
  extends AbstractExecutable
{
  public MethodGetDestination()
  {
    super("getDestination", 0);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    XMLMessageOut localXMLMessageOut = (XMLMessageOut)paramObject;
    return localXMLMessageOut.getDestination();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof XMLMessageOut;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetDestination.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */