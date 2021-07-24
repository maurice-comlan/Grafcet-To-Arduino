package grafchart.sfc;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import org.w3c.dom.Element;

public class GCEllipse
  extends JGoEllipse
  implements Referencable, Helpable
{
  public String name = "";
  
  public GCEllipse() {}
  
  public GCEllipse(Point paramPoint, Dimension paramDimension)
  {
    super(paramPoint, paramDimension);
  }
  
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
    GCEllipse localGCEllipse = (GCEllipse)super.copyObject(paramJGoCopyEnvironment);
    if (localGCEllipse != null) {
      localGCEllipse.name = this.name;
    }
    return localGCEllipse;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.name);
    XMLUtil.setBool(paramElement, "visible", isVisible());
    XMLUtil.setBool(paramElement, "selectable", isSelectable());
    XMLUtil.setBool(paramElement, "resizable", isResizable());
    XMLUtil.setBool(paramElement, "draggable", isDraggable());
    XMLUtil.setInt(paramElement, "penColor", getPen().getColor().getRGB());
    XMLUtil.setInt(paramElement, "penStyle", getPen().getStyle());
    XMLUtil.setInt(paramElement, "penWidth", getPen().getWidth());
    XMLUtil.setBool(paramElement, "isFilled", getBrush() != JGoBrush.Null);
    if (getBrush() != JGoBrush.Null)
    {
      XMLUtil.setInt(paramElement, "fillColor", getBrush().getColor().getRGB());
      XMLUtil.setInt(paramElement, "fillStyle", getBrush().getStyle());
    }
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static GCEllipse loadXML(Element paramElement)
  {
    GCEllipse localGCEllipse = new GCEllipse();
    localGCEllipse.setVisible(XMLUtil.getBool(paramElement, "visible", true));
    localGCEllipse.setName(paramElement.getAttribute("name"));
    localGCEllipse.setSelectable(XMLUtil.getBool(paramElement, "selectable", true));
    localGCEllipse.setResizable(XMLUtil.getBool(paramElement, "resizable", true));
    localGCEllipse.setDraggable(XMLUtil.getBool(paramElement, "draggable", true));
    int i = XMLUtil.getInt(paramElement, "penColor", 0);
    int j = XMLUtil.getInt(paramElement, "penStyle", 65535);
    int k = XMLUtil.getInt(paramElement, "penWidth", 1);
    localGCEllipse.setPen(new JGoPen(j, k, new Color(i)));
    if (XMLUtil.getBool(paramElement, "isFilled"))
    {
      int m = XMLUtil.getInt(paramElement, "fillColor", 12632256);
      int n = XMLUtil.getInt(paramElement, "fillStyle", 65535);
      localGCEllipse.setBrush(new JGoBrush(n, new Color(m)));
    }
    else
    {
      localGCEllipse.setBrush(JGoBrush.Null);
    }
    XMLUtil.restoreBoundingRectAny(paramElement, localGCEllipse);
    return localGCEllipse;
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_Ellipse";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCEllipse.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */