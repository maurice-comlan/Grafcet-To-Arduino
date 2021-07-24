package grafchart.sfc;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoSelection;
import com.nwoods.jgo.JGoText;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.w3c.dom.Element;

public class Browser
  extends ListArea
  implements Referencable, Helpable
{
  private static final int defaultFillColor = 15132390;
  private String name = "";
  private Color textColor = Color.black;
  private Color fillColor = new Color(15132390);
  private boolean bold = false;
  private String faceName = "Serif";
  private int fontSize = 12;
  private JGoText selectedText = null;
  
  public Browser() {}
  
  public Browser(Dimension paramDimension)
  {
    super(paramDimension);
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
    Browser localBrowser = (Browser)super.copyObject(paramJGoCopyEnvironment);
    if (localBrowser != null) {
      localBrowser.name = this.name;
    }
    return localBrowser;
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_Browser";
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("bold", isBold() ? "1" : "0");
    paramElement.setAttribute("fontSize", Integer.toString(getFontSize()));
    paramElement.setAttribute("faceName", getFaceName());
    paramElement.setAttribute("textColor", Integer.toString(getTextColor().getRGB()));
    paramElement.setAttribute("fillColor", Integer.toString(getFillColor().getRGB()));
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static Browser loadXML(Element paramElement)
  {
    Browser localBrowser = new Browser(new Dimension());
    localBrowser.setBold(XMLUtil.getBool(paramElement, "bold"));
    localBrowser.setTextColor(new Color(XMLUtil.getInt(paramElement, "textColor", 0)));
    localBrowser.setFillColor(new Color(XMLUtil.getInt(paramElement, "fillColor", 15132390)));
    localBrowser.setFontSize(XMLUtil.getInt(paramElement, "fontSize", 12));
    localBrowser.setFaceName(paramElement.getAttribute("faceName"));
    localBrowser.setName(paramElement.getAttribute("name"));
    XMLUtil.restoreBoundingRectAny(paramElement, localBrowser);
    return localBrowser;
  }
  
  public Color getTextColor()
  {
    return this.textColor;
  }
  
  public void setTextColor(Color paramColor)
  {
    this.textColor = paramColor;
  }
  
  public Color getFillColor()
  {
    return this.fillColor;
  }
  
  public void setFillColor(Color paramColor)
  {
    this.fillColor = paramColor;
    this.myRect.setBrush(JGoBrush.makeStockBrush(this.fillColor));
  }
  
  public boolean isBold()
  {
    return this.bold;
  }
  
  public void setBold(boolean paramBoolean)
  {
    this.bold = paramBoolean;
  }
  
  public void setFaceName(String paramString)
  {
    this.faceName = paramString;
  }
  
  public String getFaceName()
  {
    return this.faceName;
  }
  
  public int getFontSize()
  {
    return this.fontSize;
  }
  
  public void setFontSize(int paramInt)
  {
    this.fontSize = paramInt;
  }
  
  public void setSelected(JGoText paramJGoText)
  {
    if (this.selectedText != paramJGoText)
    {
      invertColors(this.selectedText);
      invertColors(paramJGoText);
      this.selectedText = paramJGoText;
    }
    else
    {
      invertColors(paramJGoText);
      this.selectedText = null;
    }
  }
  
  private void invertColors(JGoText paramJGoText)
  {
    if (paramJGoText != null)
    {
      Color localColor = paramJGoText.getTextColor();
      paramJGoText.setTextColor(paramJGoText.getBkColor());
      paramJGoText.setBkColor(localColor);
    }
  }
  
  public int getSelectedIndex()
  {
    if (this.selectedText == null) {
      return -1;
    }
    for (int i = 0; i < getNumItems(); i++)
    {
      JGoObject localJGoObject = getItem(i);
      if (localJGoObject == this.selectedText) {
        return i;
      }
    }
    return -1;
  }
  
  public JGoText getSelectedItem()
  {
    return this.selectedText;
  }
  
  public void removeItem(int paramInt)
  {
    if (getItem(paramInt) == this.selectedText) {
      this.selectedText = null;
    }
    super.removeItem(paramInt);
  }
  
  public void setItem(int paramInt, JGoObject paramJGoObject)
  {
    int i = getSelectedIndex() == paramInt ? 1 : 0;
    super.setItem(paramInt, paramJGoObject);
    if (i != 0)
    {
      this.selectedText = ((JGoText)paramJGoObject);
      invertColors(this.selectedText);
    }
  }
  
  public String toString()
  {
    return getName();
  }
  
  public void writeFile(String paramString)
  {
    try
    {
      FileWriter localFileWriter = new FileWriter(new File(paramString).getCanonicalFile());
      PrintWriter localPrintWriter = new PrintWriter(localFileWriter, false);
      int i = getNumItems();
      for (int j = i - 1; j >= 0; j--)
      {
        JGoObject localJGoObject = getItem(j);
        localPrintWriter.println(((JGoText)localJGoObject).getText());
      }
      localPrintWriter.close();
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
  }
  
  public JGoText createItem(String paramString)
  {
    JGoText localJGoText = new JGoText(paramString);
    localJGoText.setFontSize(getFontSize());
    localJGoText.setTextColor(getTextColor());
    localJGoText.setBkColor(getFillColor());
    localJGoText.setFaceName(getFaceName());
    localJGoText.setBold(isBold());
    return localJGoText;
  }
  
  protected void gainedSelection(JGoSelection paramJGoSelection)
  {
    setSelected(null);
    super.gainedSelection(paramJGoSelection);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Browser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */