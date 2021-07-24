package grafchart.sfc.builtin;

import grafchart.sfc.GrafcetObject;

public class MethodXEventUp
  extends AbstractExecutable
{
  public MethodXEventUp()
  {
    super("xEventUp", 0);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    GrafcetObject localGrafcetObject = (GrafcetObject)paramObject;
    return (localGrafcetObject.curX) && (!localGrafcetObject.oldX);
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof GrafcetObject;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodXEventUp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */