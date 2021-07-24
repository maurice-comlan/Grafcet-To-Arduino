package grafchart.sfc.builtin;

import com.nwoods.jgo.JGoText;
import grafchart.sfc.Browser;

public class MethodGetSelectedItem
  extends AbstractExecutable
{
  public MethodGetSelectedItem()
  {
    super("getSelectedItem", 0);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Browser localBrowser = (Browser)paramObject;
    JGoText localJGoText = localBrowser.getSelectedItem();
    if (localJGoText != null) {
      return localJGoText.getText();
    }
    return "";
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Browser;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodGetSelectedItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */