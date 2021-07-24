package grafchart.graphics;

import grafchart.sfc.AppAction;
import javax.swing.JMenuItem;

public class MyJMenuItem
  extends JMenuItem
{
  public MyJMenuItem(AppAction paramAppAction)
  {
    super(paramAppAction);
  }
  
  public MyJMenuItem(String paramString)
  {
    super(paramString);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJMenuItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */