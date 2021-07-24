package grafchart.sfc;

import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoSelection;
import com.nwoods.jgo.JGoView;
import grafchart.graphics.MyJGoImage;
import grafchart.sfc.actions.Statement;
import grafchart.util.XMLUtil;
import java.awt.Dimension;
import java.awt.Point;
import org.w3c.dom.Element;

public class GraphicalButton
  extends MyJGoImage
  implements Helpable
{
  public String actionString = ";";
  public transient Statement node = null;
  public boolean enableWhenStopped = false;
  
  public GraphicalButton() {}
  
  public GraphicalButton(Point paramPoint, Dimension paramDimension)
  {
    super(paramPoint, paramDimension);
  }
  
  public JGoObject copyObject(JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    GraphicalButton localGraphicalButton = (GraphicalButton)super.copyObject(paramJGoCopyEnvironment);
    if (localGraphicalButton != null)
    {
      localGraphicalButton.actionString = this.actionString;
      localGraphicalButton.enableWhenStopped = this.enableWhenStopped;
    }
    return localGraphicalButton;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("file", getFilename());
    paramElement.setAttribute("action", this.actionString);
    XMLUtil.setBool(paramElement, "alwaysAct", this.enableWhenStopped);
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static GraphicalButton loadXML(Element paramElement)
  {
    GraphicalButton localGraphicalButton = new GraphicalButton();
    localGraphicalButton.actionString = paramElement.getAttribute("action");
    localGraphicalButton.loadImage(paramElement.getAttribute("file"));
    localGraphicalButton.enableWhenStopped = XMLUtil.getBool(paramElement, "alwaysAct");
    XMLUtil.restoreBoundingRectAny(paramElement, localGraphicalButton);
    return localGraphicalButton;
  }
  
  public void performAction()
  {
    if ((this.node != null) && ((this.enableWhenStopped) || (((GCDocument)getDocument()).executing))) {
      this.node.executeStoredActions();
    }
  }
  
  public boolean doMouseClick(int paramInt, Point paramPoint1, Point paramPoint2, JGoView paramJGoView)
  {
    if (((paramJGoView instanceof GCView)) && (this.node != null)) {
      performAction();
    }
    return true;
  }
  
  protected void gainedSelection(JGoSelection paramJGoSelection)
  {
    if (((!this.enableWhenStopped) || (this.node == null)) && ((this.enableWhenStopped) || (!(getDocument() instanceof GCDocument)) || (!((GCDocument)getDocument()).executing))) {
      super.gainedSelection(paramJGoSelection);
    }
  }
  
  public String getName()
  {
    return "Icon " + getFilename();
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
  
  public void setNormalSize()
  {
    Dimension localDimension = getNaturalSize();
    if ((localDimension.getWidth() > 0.0D) && (localDimension.getHeight() > 0.0D)) {
      setSize(localDimension);
    }
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_GraphicalActionButton";
  }
  
  public String toString()
  {
    return "";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GraphicalButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */