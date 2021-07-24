package grafchart.sfc.builtin;

import grafchart.sfc.BasicList;

public class MethodIsEmpty
  extends AbstractExecutable
{
  public MethodIsEmpty()
  {
    super("isEmpty", 0);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    BasicList localBasicList = (BasicList)paramObject;
    return localBasicList.isListEmpty();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof BasicList;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodIsEmpty.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */