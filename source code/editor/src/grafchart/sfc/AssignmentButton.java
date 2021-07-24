package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoSelection;
import com.nwoods.jgo.JGoText;
import com.nwoods.jgo.JGoView;
import grafchart.graphics.MyJGoArea;
import grafchart.sfc.actions.Statement;
import grafchart.util.XMLUtil;
import java.awt.Point;
import org.w3c.dom.Element;

public class AssignmentButton
  extends MyJGoArea
  implements Helpable
{
  private GC3DRect myOuterRectangle = null;
  public JGoText myName = null;
  public String actionString = ";";
  public transient Statement node = null;
  public boolean enabledWhenStopped = false;
  
  public AssignmentButton() {}
  
  public AssignmentButton(Point paramPoint)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myOuterRectangle = new GC3DRect();
    this.myOuterRectangle.setSize(50, 40);
    this.myOuterRectangle.setSelectable(false);
    this.myOuterRectangle.setDraggable(false);
    this.myName = new JGoText("Button");
    this.myName.setSelectable(false);
    this.myName.setEditable(false);
    this.myName.setEditOnSingleClick(false);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    this.myName.setAutoResize(true);
    addObjectAtHead(this.myOuterRectangle);
    addObjectAtTail(this.myName);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    AssignmentButton localAssignmentButton = (AssignmentButton)paramJGoArea;
    localAssignmentButton.myOuterRectangle = ((GC3DRect)paramJGoCopyEnvironment.copy(this.myOuterRectangle));
    localAssignmentButton.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localAssignmentButton.addObjectAtHead(localAssignmentButton.myOuterRectangle);
    localAssignmentButton.addObjectAtTail(localAssignmentButton.myName);
    localAssignmentButton.actionString = this.actionString;
    localAssignmentButton.enabledWhenStopped = this.enabledWhenStopped;
  }
  
  public Point getLocation(Point paramPoint)
  {
    return getSpotLocation(1, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    setSpotLocation(1, paramInt1, paramInt2);
  }
  
  public void layoutChildren()
  {
    this.myName.setSpotLocation(0, this.myOuterRectangle, 0);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myOuterRectangle };
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.myName.getText());
    paramElement.setAttribute("action", this.actionString);
    XMLUtil.setBool(paramElement, "alwaysAct", this.enabledWhenStopped);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    return paramElement;
  }
  
  public static AssignmentButton loadXML(Element paramElement)
  {
    AssignmentButton localAssignmentButton = new AssignmentButton(new Point());
    localAssignmentButton.actionString = paramElement.getAttribute("action");
    localAssignmentButton.myName.setText(paramElement.getAttribute("name"));
    localAssignmentButton.enabledWhenStopped = XMLUtil.getBool(paramElement, "alwaysAct");
    localAssignmentButton.removeObject(localAssignmentButton.myName);
    XMLUtil.restoreBoundingRectAny(paramElement, localAssignmentButton);
    localAssignmentButton.addObjectAtTail(localAssignmentButton.myName);
    return localAssignmentButton;
  }
  
  public void modifySize()
  {
    Point localPoint = getLocation();
    this.myOuterRectangle.setSize(this.myName.getWidth() + 40, this.myName.getHeight() + 20);
    setLocation(localPoint);
    layoutChildren();
  }
  
  public void performAction()
  {
    if ((this.node != null) && ((this.enabledWhenStopped) || (getDocument().executing))) {
      this.node.executeStoredActions();
    }
  }
  
  public void doPress(int paramInt)
  {
    if ((paramInt & 0x10) != 0) {
      this.myOuterRectangle.setBrush(JGoBrush.gray);
    }
  }
  
  public void doUnpress()
  {
    this.myOuterRectangle.setBrush(JGoBrush.lightGray);
  }
  
  public boolean doMouseClick(int paramInt, Point paramPoint1, Point paramPoint2, JGoView paramJGoView)
  {
    if ((paramJGoView instanceof GCView)) {
      performAction();
    }
    return true;
  }
  
  protected void gainedSelection(JGoSelection paramJGoSelection)
  {
    doUnpress();
    super.gainedSelection(paramJGoSelection);
  }
  
  public String getName()
  {
    return this.myName.getText();
  }
  
  public String getFullName()
  {
    String str = getName();
    GCDocument localGCDocument = getDocument();
    for (GrafcetObject localGrafcetObject = localGCDocument.owner; localGrafcetObject != null; localGrafcetObject = localGCDocument.owner)
    {
      Referencable localReferencable = (Referencable)localGrafcetObject;
      str = localReferencable.getName() + "." + str;
      localGCDocument = localGrafcetObject.getDocument();
    }
    str = localGCDocument.getName() + "." + str;
    return str;
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_ActionButton";
  }
  
  public String toString()
  {
    return "";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/AssignmentButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */