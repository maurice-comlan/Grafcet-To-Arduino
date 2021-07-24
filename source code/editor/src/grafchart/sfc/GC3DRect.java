package grafchart.sfc;

import com.nwoods.jgo.JGo3DRect;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import org.w3c.dom.Element;

public class GC3DRect
  extends JGo3DRect
  implements Referencable, Helpable
{
  public String name = "";
  
  public GC3DRect() {}
  
  public GC3DRect(Point paramPoint, Dimension paramDimension)
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
    GC3DRect localGC3DRect = (GC3DRect)super.copyObject(paramJGoCopyEnvironment);
    if (localGC3DRect != null) {
      localGC3DRect.name = this.name;
    }
    return localGC3DRect;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.name);
    XMLUtil.setBool(paramElement, "visible", isVisible());
    XMLUtil.setBool(paramElement, "selectable", isSelectable());
    XMLUtil.setBool(paramElement, "resizable", isResizable());
    XMLUtil.setBool(paramElement, "draggable", isDraggable());
    XMLUtil.setInt(paramElement, "state", getState());
    XMLUtil.setInt(paramElement, "penColor", getPen().getColor().getRGB());
    XMLUtil.setInt(paramElement, "penStyle", getPen().getStyle());
    XMLUtil.setInt(paramElement, "penWidth", getPen().getWidth());
    XMLUtil.setInt(paramElement, "fillColor", getBrush().getColor().getRGB());
    XMLUtil.setInt(paramElement, "fillStyle", getBrush().getStyle());
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static GC3DRect loadXML(Element paramElement)
  {
    GC3DRect localGC3DRect = new GC3DRect();
    localGC3DRect.setVisible(XMLUtil.getBool(paramElement, "visible", true));
    if (paramElement.hasAttribute("name")) {
      localGC3DRect.setName(paramElement.getAttribute("name"));
    }
    localGC3DRect.setSelectable(XMLUtil.getBool(paramElement, "selectable", true));
    localGC3DRect.setResizable(XMLUtil.getBool(paramElement, "resizable", true));
    localGC3DRect.setDraggable(XMLUtil.getBool(paramElement, "draggable", true));
    int i = XMLUtil.getInt(paramElement, "state");
    localGC3DRect.setState(i);
    int j = XMLUtil.getInt(paramElement, "penStyle", 65535);
    localGC3DRect.setPen(new JGoPen(j, XMLUtil.getInt(paramElement, "penWidth", 1), new Color(XMLUtil.getInt(paramElement, "penColor", 12632256))));
    int k = XMLUtil.getInt(paramElement, "fillColor", 12632256);
    localGC3DRect.setBrush(new JGoBrush(j, new Color(k)));
    XMLUtil.restoreBoundingRectAny(paramElement, localGC3DRect);
    return localGC3DRect;
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_3DRectangle";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GC3DRect.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */