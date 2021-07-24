package grafchart.sfc;

import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoText;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Point;
import org.w3c.dom.Element;

public class FreeText
  extends JGoText
  implements Referencable, Helpable
{
  public String name = "";
  public Color textCol = null;
  public Color bkCol = null;
  
  public FreeText() {}
  
  public FreeText(Point paramPoint, int paramInt1, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, boolean paramBoolean4, boolean paramBoolean5)
  {
    super(paramPoint, paramInt1, paramString1, paramString2, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, paramBoolean4, paramBoolean5);
  }
  
  public FreeText(String paramString)
  {
    super(paramString);
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
    FreeText localFreeText = (FreeText)super.copyObject(paramJGoCopyEnvironment);
    if (localFreeText != null)
    {
      localFreeText.name = this.name;
      localFreeText.textCol = this.textCol;
      localFreeText.bkCol = this.bkCol;
    }
    return localFreeText;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.name);
    XMLUtil.setInt(paramElement, "fontSize", getFontSize());
    paramElement.setAttribute("text", getText());
    paramElement.setAttribute("fontName", getFaceName());
    XMLUtil.setBool(paramElement, "bold", isBold());
    XMLUtil.setBool(paramElement, "underline", isUnderline());
    XMLUtil.setBool(paramElement, "italic", isItalic());
    XMLUtil.setInt(paramElement, "alignment", getAlignment());
    XMLUtil.setBool(paramElement, "multiline", isMultiline());
    XMLUtil.setBool(paramElement, "editable", isEditable());
    XMLUtil.setBool(paramElement, "visible", isVisible());
    XMLUtil.setBool(paramElement, "selectable", isSelectable());
    XMLUtil.setBool(paramElement, "resizable", isResizable());
    XMLUtil.setBool(paramElement, "draggable", isDraggable());
    XMLUtil.setBool(paramElement, "twoDScale", is2DScale());
    XMLUtil.setBool(paramElement, "autoResize", isAutoResize());
    XMLUtil.setBool(paramElement, "clipping", isClipping());
    XMLUtil.setBool(paramElement, "editOnSingleClick", isEditOnSingleClick());
    XMLUtil.setBool(paramElement, "strikeThrough", isStrikeThrough());
    XMLUtil.setInt(paramElement, "textColor", getTextColor().getRGB());
    XMLUtil.setInt(paramElement, "backgroundColor", getBkColor().getRGB());
    XMLUtil.setBool(paramElement, "transparent", isTransparent());
    XMLUtil.setBool(paramElement, "selectBackground", isSelectBackground());
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static FreeText loadXML(Element paramElement)
  {
    int i = XMLUtil.getInt(paramElement, "fontSize", 16);
    FreeText localFreeText = new FreeText(new Point(), i, paramElement.getAttribute("text"), paramElement.getAttribute("fontName"), XMLUtil.getBool(paramElement, "bold"), XMLUtil.getBool(paramElement, "underline"), XMLUtil.getBool(paramElement, "italic"), XMLUtil.getInt(paramElement, "alignment", 1), XMLUtil.getBool(paramElement, "multiline"), XMLUtil.getBool(paramElement, "editable", true));
    localFreeText.setName(paramElement.getAttribute("name"));
    localFreeText.setVisible(XMLUtil.getBool(paramElement, "visible", true));
    localFreeText.setSelectable(XMLUtil.getBool(paramElement, "selectable", true));
    localFreeText.setResizable(XMLUtil.getBool(paramElement, "resizable", true));
    localFreeText.setDraggable(XMLUtil.getBool(paramElement, "draggable", true));
    localFreeText.set2DScale(XMLUtil.getBool(paramElement, "twoDScale"));
    localFreeText.setAutoResize(XMLUtil.getBool(paramElement, "autoResize", true));
    localFreeText.setClipping(XMLUtil.getBool(paramElement, "clipping"));
    localFreeText.setEditOnSingleClick(XMLUtil.getBool(paramElement, "editOnSingleClick", true));
    localFreeText.setStrikeThrough(XMLUtil.getBool(paramElement, "strikeThrough"));
    localFreeText.setTextColor(new Color(XMLUtil.getInt(paramElement, "textColor", 0)));
    localFreeText.setBkColor(new Color(XMLUtil.getInt(paramElement, "backgroundColor", 16777215)));
    localFreeText.setTransparent(XMLUtil.getBool(paramElement, "transparent", true));
    localFreeText.setSelectBackground(XMLUtil.getBool(paramElement, "selectBackground"));
    XMLUtil.restoreBoundingRectAny(paramElement, localFreeText);
    localFreeText.setFontSize(i);
    return localFreeText;
  }
  
  public Point getLocation(Point paramPoint)
  {
    return getSpotLocation(1, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    setSpotLocation(1, paramInt1, paramInt2);
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_Text";
  }
  
  public String toString()
  {
    return getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/FreeText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */