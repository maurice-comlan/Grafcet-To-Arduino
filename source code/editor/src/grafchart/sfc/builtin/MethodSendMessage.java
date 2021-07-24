package grafchart.sfc.builtin;

import grafchart.sfc.XMLMessageOut;

public class MethodSendMessage
  extends AbstractExecutable
{
  public MethodSendMessage()
  {
    super("sendMessage", 0, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    XMLMessageOut localXMLMessageOut = (XMLMessageOut)paramObject;
    localXMLMessageOut.sendMessage();
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof XMLMessageOut;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSendMessage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */