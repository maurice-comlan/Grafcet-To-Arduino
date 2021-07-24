package grafchart.sfc;

import grafchart.graphics.MyJGoImage;
import grafchart.util.XMLUtil;
import java.awt.Dimension;
import java.awt.Point;
import org.w3c.dom.Element;

public class LUCASLogo
  extends MyJGoImage
  implements Helpable
{
  public LUCASLogo()
  {
    loadImage();
  }
  
  public LUCASLogo(Dimension paramDimension)
  {
    super(new Point(), paramDimension);
    loadImage();
  }
  
  private void loadImage()
  {
    loadImage(Editor.getIconLib() + "/graphics/LUCAS.gif");
  }
  
  public Element storeXML(Element paramElement)
  {
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static LUCASLogo loadXML(Element paramElement)
  {
    LUCASLogo localLUCASLogo = new LUCASLogo();
    XMLUtil.restoreBoundingRectAny(paramElement, localLUCASLogo);
    return localLUCASLogo;
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_LUCASIcon";
  }
  
  public String toString()
  {
    return " ";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/LUCASLogo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */