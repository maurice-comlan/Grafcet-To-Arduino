package grafchart.graphics;

import grafchart.sfc.Utils;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyJFrame
  extends JFrame
{
  public MyJFrame()
  {
    init();
  }
  
  public MyJFrame(String paramString)
  {
    super(paramString);
    init();
  }
  
  private void init()
  {
    setIconImage(Utils.newImageIcon("JGrafchartIcon.png").getImage());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJFrame.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */