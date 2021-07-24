package grafchart.sfc;

import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import grafchart.graphics.MyJGoImage;
import grafchart.util.XMLUtil;
import java.awt.Dimension;
import java.awt.Point;
import org.w3c.dom.Element;

public class Icon
  extends MyJGoImage
  implements Referencable, Helpable
{
  public String name = "";
  
  public Icon() {}
  
  public Icon(Point paramPoint, Dimension paramDimension)
  {
    super(paramPoint, paramDimension);
  }
  
  public JGoObject copyObject(JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    Icon localIcon = (Icon)super.copyObject(paramJGoCopyEnvironment);
    if (localIcon != null) {
      localIcon.name = this.name;
    }
    return localIcon;
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
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.name);
    paramElement.setAttribute("file", getFilename());
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static Icon loadXML(Element paramElement)
  {
    Icon localIcon = new Icon();
    localIcon.setName(paramElement.getAttribute("name"));
    String str = paramElement.getAttribute("file");
    localIcon.loadImage(str);
    XMLUtil.restoreBoundingRectAny(paramElement, localIcon);
    return localIcon;
  }
  
  public void setNormalSize()
  {
    Dimension localDimension = getNaturalSize();
    if ((localDimension.getWidth() > 0.0D) && (localDimension.getHeight() > 0.0D)) {
      setSize(localDimension);
    }
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_Icon";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Icon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */