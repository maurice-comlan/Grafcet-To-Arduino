package grafchart.sfc;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoStroke;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GCStroke
  extends JGoStroke
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
    GCStroke localGCStroke = (GCStroke)super.copyObject(paramJGoCopyEnvironment);
    if (localGCStroke != null) {
      localGCStroke.name = this.name;
    }
    return localGCStroke;
  }
  
  public Element storeXML(Element paramElement)
  {
    Document localDocument = paramElement.getOwnerDocument();
    paramElement.setAttribute("name", this.name);
    XMLUtil.setBool(paramElement, "visible", isVisible());
    XMLUtil.setBool(paramElement, "selectable", isSelectable());
    XMLUtil.setBool(paramElement, "resizable", isResizable());
    XMLUtil.setBool(paramElement, "draggable", isDraggable());
    XMLUtil.setInt(paramElement, "penColor", getPen().getColor().getRGB());
    XMLUtil.setInt(paramElement, "penStyle", getPen().getStyle());
    XMLUtil.setInt(paramElement, "penWidth", getPen().getWidth());
    XMLUtil.setBool(paramElement, "arrowEnd", hasArrowAtEnd());
    XMLUtil.setBool(paramElement, "arrowStart", hasArrowAtStart());
    XMLUtil.setBool(paramElement, "cubic", isCubic());
    int i = getNumPoints();
    for (int j = 0; j < i; j++)
    {
      Element localElement = localDocument.createElement(this.strokePointTag);
      localElement.setAttribute("x", Integer.toString(getPointX(j)));
      localElement.setAttribute("y", Integer.toString(getPointY(j)));
      paramElement.appendChild(localElement);
    }
    return paramElement;
  }
  
  public static GCStroke loadXML(Element paramElement)
  {
    GCStroke localGCStroke = new GCStroke();
    localGCStroke.setVisible(XMLUtil.getBool(paramElement, "visible", true));
    localGCStroke.setName(paramElement.getAttribute("name"));
    localGCStroke.setSelectable(XMLUtil.getBool(paramElement, "selectable", true));
    localGCStroke.setCubic(XMLUtil.getBool(paramElement, "cubic"));
    localGCStroke.setResizable(XMLUtil.getBool(paramElement, "resizable", true));
    localGCStroke.setDraggable(XMLUtil.getBool(paramElement, "draggable", true));
    localGCStroke.setArrowHeads(XMLUtil.getBool(paramElement, "arrowStart"), XMLUtil.getBool(paramElement, "arrowEnd"));
    Color localColor = new Color(XMLUtil.getInt(paramElement, "penColor", 0));
    localGCStroke.setPen(new JGoPen(XMLUtil.getInt(paramElement, "penStyle", 65535), XMLUtil.getInt(paramElement, "penWidth", 1), localColor));
    localGCStroke.setBrush(new JGoBrush(65535, localColor));
    NodeList localNodeList = paramElement.getElementsByTagName("Stroke");
    localGCStroke.removeAllPoints();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        Element localElement = (Element)localNode;
        int j = Integer.parseInt(localElement.getAttribute("x"));
        int k = Integer.parseInt(localElement.getAttribute("y"));
        localGCStroke.insertPoint(i, j, k);
      }
    }
    return localGCStroke;
  }
  
  public Vector getPoints()
  {
    return this.myPoints;
  }
  
  public String getHelpID()
  {
    if (isCubic()) {
      return "LangRef_GUI_Spline";
    }
    return "LangRef_GUI_Line";
  }
  
  public String toString()
  {
    return "";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCStroke.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */