package grafchart.sfc;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import org.w3c.dom.Element;

public class GCRectangle
  extends JGoRectangle
  implements Referencable, Helpable
{
  public String name = "";
  
  public GCRectangle() {}
  
  public GCRectangle(Point paramPoint, Dimension paramDimension)
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
    GCRectangle localGCRectangle = (GCRectangle)super.copyObject(paramJGoCopyEnvironment);
    if (localGCRectangle != null) {
      localGCRectangle.name = this.name;
    }
    return localGCRectangle;
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
    if (getBrush() != JGoBrush.Null)
    {
      paramElement.setAttribute("fillColor", Integer.toString(getBrush().getColor().getRGB()));
      paramElement.setAttribute("fillStyle", Integer.toString(getBrush().getStyle()));
    }
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static GCRectangle loadXML(Element paramElement)
  {
    GCRectangle localGCRectangle = new GCRectangle();
    localGCRectangle.setVisible(XMLUtil.getBool(paramElement, "visible", true));
    localGCRectangle.setName(paramElement.getAttribute("name"));
    localGCRectangle.setSelectable(XMLUtil.getBool(paramElement, "selectable", true));
    localGCRectangle.setResizable(XMLUtil.getBool(paramElement, "resizable", true));
    localGCRectangle.setDraggable(XMLUtil.getBool(paramElement, "draggable", true));
    int i = XMLUtil.getInt(paramElement, "penColor", 0);
    int j = XMLUtil.getInt(paramElement, "penStyle", 65535);
    int k = XMLUtil.getInt(paramElement, "penWidth", 1);
    localGCRectangle.setPen(new JGoPen(j, k, new Color(i)));
    if ((paramElement.hasAttribute("fillColor")) && (paramElement.hasAttribute("fillStyle")))
    {
      int m = XMLUtil.getInt(paramElement, "fillColor", 12632256);
      int n = XMLUtil.getInt(paramElement, "fillStyle", 65535);
      localGCRectangle.setBrush(new JGoBrush(n, new Color(m)));
    }
    else
    {
      localGCRectangle.setBrush(JGoBrush.Null);
    }
    XMLUtil.restoreBoundingRectAny(paramElement, localGCRectangle);
    return localGCRectangle;
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_Rectangle";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCRectangle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */