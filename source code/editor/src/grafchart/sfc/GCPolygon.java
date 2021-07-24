package grafchart.sfc;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPolygon;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Point;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GCPolygon
  extends JGoPolygon
  implements Referencable, Helpable
{
  public String name = "";
  private String strokePointTag = new String("Stroke");
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public String getFullName()
  {
    String str = getName();
    GCDocument localGCDocument = (GCDocument)getDocument();
    for (GrafcetObject localGrafcetObject = localGCDocument.owner; localGrafcetObject != null; localGrafcetObject = localGCDocument.owner)
    {
      Referencable localReferencable = (Referencable)localGrafcetObject;
      str = localReferencable.getName() + "." + str;
      localGCDocument = localGrafcetObject.getDocument();
    }
    str = localGCDocument.getName() + "." + str;
    return str;
  }
  
  public JGoObject copyObject(JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    GCPolygon localGCPolygon = (GCPolygon)super.copyObject(paramJGoCopyEnvironment);
    if (localGCPolygon != null) {
      localGCPolygon.name = this.name;
    }
    return localGCPolygon;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.name);
    XMLUtil.setBool(paramElement, "visible", isVisible());
    XMLUtil.setBool(paramElement, "selectable", isSelectable());
    XMLUtil.setBool(paramElement, "resizable", isResizable());
    XMLUtil.setBool(paramElement, "draggable", isDraggable());
    paramElement.setAttribute("penColor", Integer.toString(getPen().getColor().getRGB()));
    paramElement.setAttribute("penStyle", Integer.toString(getPen().getStyle()));
    paramElement.setAttribute("penWidth", Integer.toString(getPen().getWidth()));
    XMLUtil.setBool(paramElement, "isFilled", getBrush() != JGoBrush.Null);
    if (getBrush() != JGoBrush.Null)
    {
      paramElement.setAttribute("fillColor", Integer.toString(getBrush().getColor().getRGB()));
      paramElement.setAttribute("fillStyle", Integer.toString(getBrush().getStyle()));
    }
    XMLUtil.setBool(paramElement, "cubic", isCubic());
    for (int i = 0; i < getNumPoints(); i++)
    {
      Element localElement = paramElement.getOwnerDocument().createElement(this.strokePointTag);
      localElement.setAttribute("x", Integer.toString(getPointX(i)));
      localElement.setAttribute("y", Integer.toString(getPointY(i)));
      paramElement.appendChild(localElement);
    }
    return paramElement;
  }
  
  public static GCPolygon loadXML(Element paramElement)
  {
    GCPolygon localGCPolygon = new GCPolygon();
    NodeList localNodeList = paramElement.getElementsByTagName("Stroke");
    int m;
    int n;
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        Element localElement = (Element)localNode;
        m = Integer.parseInt(localElement.getAttribute("x"));
        n = Integer.parseInt(localElement.getAttribute("y"));
        localGCPolygon.addPoint(new Point(m, n));
      }
    }
    localGCPolygon.setVisible(XMLUtil.getBool(paramElement, "visible", true));
    localGCPolygon.setName(paramElement.getAttribute("name"));
    localGCPolygon.setSelectable(XMLUtil.getBool(paramElement, "selectable", true));
    if (paramElement.hasAttribute("cubic")) {
      localGCPolygon.setCubic(XMLUtil.getBool(paramElement, "cubic", false));
    }
    localGCPolygon.setResizable(XMLUtil.getBool(paramElement, "resizable", true));
    localGCPolygon.setDraggable(XMLUtil.getBool(paramElement, "draggable", true));
      int i = XMLUtil.getInt(paramElement, "penColor", 8421504);
    int j = XMLUtil.getInt(paramElement, "penStyle", 65535);
    int k = XMLUtil.getInt(paramElement, "penWidth", 3);
    localGCPolygon.setPen(new JGoPen(j, k, new Color(i)));
    if (XMLUtil.getBool(paramElement, "isFilled"))
    {
      m = XMLUtil.getInt(paramElement, "fillColor", 12632256);
      n = XMLUtil.getInt(paramElement, "fillStyle", 65535);
      localGCPolygon.setBrush(new JGoBrush(n, new Color(m)));
    }
    else
    {
      localGCPolygon.setBrush(JGoBrush.Null);
    }
    return localGCPolygon;
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_Polygon";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCPolygon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */