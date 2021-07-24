package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPolygon;
import com.nwoods.jgo.JGoRectangle;
import grafchart.graphics.MyJGoArea;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.Element;

public class ShowWorkspaceButton
  extends MyJGoArea
  implements Helpable
{
  public JGoRectangle myRectangle = null;
  public JGoPolygon myArrow = null;
  public String ws = "";
  
  public ShowWorkspaceButton() {}
  
  public ShowWorkspaceButton(Point paramPoint)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(false);
    this.myRectangle = new JGoRectangle();
    this.myRectangle.setSize(30, 30);
    this.myRectangle.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myRectangle.setSelectable(false);
    this.myRectangle.setDraggable(false);
    this.myArrow = new JGoPolygon();
    this.myArrow.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myArrow.addPoint(10, 10);
    this.myArrow.addPoint(15, 10);
    this.myArrow.addPoint(15, 5);
    this.myArrow.addPoint(20, 15);
    this.myArrow.addPoint(15, 25);
    this.myArrow.addPoint(15, 20);
    this.myArrow.addPoint(10, 20);
    this.myArrow.setSelectable(false);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myArrow);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    ShowWorkspaceButton localShowWorkspaceButton = (ShowWorkspaceButton)paramJGoArea;
    localShowWorkspaceButton.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localShowWorkspaceButton.myArrow = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myArrow));
    localShowWorkspaceButton.addObjectAtHead(localShowWorkspaceButton.myRectangle);
    localShowWorkspaceButton.addObjectAtTail(localShowWorkspaceButton.myArrow);
    localShowWorkspaceButton.ws = this.ws;
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
    this.myArrow.setSpotLocation(0, this.myRectangle, 0);
  }
  
  public void showWorkspace()
  {
    Object localObject1 = null;
    int i = 0;
    String str = this.ws;
    Object localObject2 = null;
    ArrayList localArrayList = Editor.topGrafcharts.getStorage();
    Object localObject4;
    for (int j = str.indexOf('.'); j != -1; j = str.indexOf('.'))
    {
        String localObject3 = str.substring(0, j);
      str = str.substring(j + 1);
      i = 0;
      localObject4 = localArrayList.iterator();
      while ((i == 0) && (((Iterator)localObject4).hasNext()))
      {
        localObject1 = ((Iterator)localObject4).next();
        if ((localObject1 instanceof Referencable))
        {
          Referencable localReferencable = (Referencable)localObject1;
          if (localReferencable.getName().compareTo((String)localObject3) == 0)
          {
            i = 1;
            if ((localReferencable instanceof GCDocument)) {
              localArrayList = ((GCDocument)localReferencable).getSymbolTable();
            }
            if ((localReferencable instanceof WorkspaceObject)) {
              localArrayList = ((WorkspaceObject)localReferencable).getSymbolTable();
            }
          }
        }
      }
    }
    i = 0;
    Object localObject3 = localArrayList.iterator();
    while ((i == 0) && (((Iterator)localObject3).hasNext()))
    {
      localObject1 = ((Iterator)localObject3).next();
      if ((localObject1 instanceof Referencable))
      {
        localObject4 = (Referencable)localObject1;
        if (((Referencable)localObject4).getName().compareTo(str) == 0)
        {
          i = 1;
          localObject2 = localObject4;
        }
      }
    }
    if ((localObject2 instanceof WorkspaceObject))
    {
      localObject3 = (WorkspaceObject)localObject2;
      ((WorkspaceObject)localObject3).showWorkspace();
    }
    else if (localObject2 == null)
    {
      Utils.writeError("Workspace \"" + this.ws + "\" not found.");
    }
    else
    {
      Utils.writeError("\"" + this.ws + "\" is not a workspace.");
    }
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("workspace", this.ws);
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static ShowWorkspaceButton loadXML(Element paramElement)
  {
    ShowWorkspaceButton localShowWorkspaceButton = new ShowWorkspaceButton(new Point());
    localShowWorkspaceButton.ws = paramElement.getAttribute("workspace");
    XMLUtil.restoreBoundingRectAny(paramElement, localShowWorkspaceButton);
    return localShowWorkspaceButton;
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_ShowWorkspaceButton";
  }
  
  public String toString()
  {
    return " ";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ShowWorkspaceButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */