package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;
import grafchart.sfc.Browser;

public class MethodAddItem
  extends AbstractExecutable
{
  public MethodAddItem()
  {
    super("addItem", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Browser localBrowser = (Browser)paramObject;
    if (paramArrayOfEvaluatable.length == 1)
    {
      localBrowser.addItem(localBrowser.createItem(paramArrayOfEvaluatable[0].evaluateString()));
    }
    else
    {
      JGoText localJGoText = localBrowser.createItem(paramArrayOfEvaluatable[1].evaluateString());
      localBrowser.insertItem(paramArrayOfEvaluatable[0].evaluateInt(), localJGoText);
    }
    return 0.0D;
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    return (paramInt == 1) || (paramInt == 2);
  }
  
  public String getCorrectNrOfArguments()
  {
    return "1 or 2";
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Browser;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodAddItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */