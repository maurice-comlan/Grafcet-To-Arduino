package grafchart.graphics;

import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyJPopupMenu
  extends JPopupMenu
{
  public JMenuItem add(Action paramAction, int paramInt)
  {
    JMenuItem localJMenuItem = add(paramAction);
    localJMenuItem.setMnemonic(paramInt);
    return localJMenuItem;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJPopupMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */