package grafchart.graphics;

import com.nwoods.jgo.JGoText;
import com.nwoods.jgo.JGoView;
import java.awt.Point;

public class JGoTextModify
  extends JGoText
{
  public JGoTextModify() {}
  
  public JGoTextModify(String paramString)
  {
    super(paramString);
  }
  
  public boolean doMouseClick(int paramInt, Point paramPoint1, Point paramPoint2, JGoView paramJGoView)
  {
    if (!isEditable()) {
      return false;
    }
    if (!isEditOnSingleClick()) {
      return false;
    }
    doStartEdit(paramJGoView, paramPoint2);
    return true;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/JGoTextModify.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */