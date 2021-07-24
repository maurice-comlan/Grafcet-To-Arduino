package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.Browser;

public class MethodGetItem
  extends AbstractExecutable
{
  public MethodGetItem()
  {
    super("getItem", 1);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Browser localBrowser = (Browser)paramObject;
    int i = paramArrayOfEvaluatable[0].evaluateInt();
    JGoObject localJGoObject = localBrowser.getItem(i);
    if (localJGoObject != null) {
      return ((JGoText)localJGoObject).getText();
    }
    runtimeError("Invalid index \"" + i + "\"");
    return "";
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Browser;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */