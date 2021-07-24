package grafchart.sfc;

import grafchart.graphics.MyJGoImage;
import grafchart.util.XMLUtil;
import java.awt.Dimension;
import java.awt.Point;
import org.w3c.dom.Element;

public class ChemIcon
  extends MyJGoImage
  implements Helpable
{
  public ChemIcon()
  {
    loadImage();
  }
  
  public ChemIcon(Point paramPoint, Dimension paramDimension)
  {
    super(paramPoint, paramDimension);
    loadImage();
  }
  
  private void loadImage()
  {
    loadImage(Editor.getIconLib() + "/graphics/CHEMIcon.gif");
  }
  
  public Element storeXML(Element paramElement)
  {
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static ChemIcon loadXML(Element paramElement)
  {
    ChemIcon localChemIcon = new ChemIcon();
    XMLUtil.restoreBoundingRectAny(paramElement, localChemIcon);
    return localChemIcon;
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_CHEMIcon";
  }
  
  public String toString()
  {
    return " ";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ChemIcon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */