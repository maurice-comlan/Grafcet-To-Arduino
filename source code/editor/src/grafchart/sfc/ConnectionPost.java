package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import com.nwoods.jgo.JGoView;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;
import org.w3c.dom.Element;

public abstract class ConnectionPost
  extends GrafcetObject
  implements Referencable, Connectable
{
  protected String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
  public JGoPort myPort = null;
  public JGoRectangle myRectangle = null;
  public JGoEllipse myCircle = null;
  public JGoStroke myLine = null;
  public JGoText myName = null;
  public String remoteString = "";
  public ConnectionPost remote = null;
  public static ConnectionPost colored1 = null;
  public static ConnectionPost colored2 = null;
  public static JGoBrush liteRed = new JGoBrush(65535, new Color(1.0F, 0.5F, 0.5F));
  
  public ConnectionPost() {}
  
  public ConnectionPost(Point paramPoint, String paramString)
  {
    this();
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myRectangle = new JGoRectangle();
    this.myRectangle.setSize(20, 20);
    this.myRectangle.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myRectangle.setSelectable(false);
    this.myRectangle.setDraggable(false);
    this.myName = new JGoText(paramString);
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(1);
    this.myName.setTransparent(true);
    this.myCircle = new JGoEllipse();
    this.myCircle.setSize(18, 18);
    this.myCircle.setSelectable(false);
    this.myCircle.setDraggable(false);
    this.myCircle.setPen(new JGoPen(65535, 1, new Color(0.0F, 0.0F, 0.0F)));
    this.myLine = new JGoStroke();
    this.myLine.addPoint(10, 0);
    this.myLine.addPoint(10, 5);
    this.myLine.setSelectable(false);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myLine);
    addObjectAtTail(this.myCircle);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    ConnectionPost localConnectionPost = (ConnectionPost)paramJGoArea;
    localConnectionPost.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localConnectionPost.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localConnectionPost.myLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine));
    localConnectionPost.myCircle = ((JGoEllipse)paramJGoCopyEnvironment.copy(this.myCircle));
    localConnectionPost.addObjectAtHead(localConnectionPost.myRectangle);
    localConnectionPost.addObjectAtTail(localConnectionPost.myName);
    localConnectionPost.addObjectAtTail(localConnectionPost.myLine);
    localConnectionPost.addObjectAtTail(localConnectionPost.myCircle);
    localConnectionPost.remoteString = this.remoteString;
    localConnectionPost.remote = this.remote;
  }
  
  public abstract Point getLocation(Point paramPoint);
  
  public abstract void setLocation(int paramInt1, int paramInt2);
  
  public void layoutChildren()
  {
    Point localPoint = this.myRectangle.getSpotLocation(4);
    this.myName.setSpotLocation(8, (int)localPoint.getX() + 8, (int)localPoint.getY());
    this.myCircle.setSpotLocation(0, this.myRectangle, 0);
  }
  
  protected Rectangle handleResize(Graphics2D paramGraphics2D, JGoView paramJGoView, Rectangle paramRectangle, Point paramPoint, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Dimension localDimension = getMinimumSize();
    return super.handleResize(paramGraphics2D, paramJGoView, paramRectangle, paramPoint, paramInt1, paramInt2, localDimension.width, localDimension.height);
  }
  
  public double getMaxScaleX()
  {
    Dimension localDimension1 = getSize();
    Dimension localDimension2 = getMinimumSize();
    if (localDimension1.getWidth() <= localDimension2.getWidth()) {
      return 1.0D;
    }
    return localDimension1.getWidth() / localDimension2.getWidth();
  }
  
  public double getMaxScaleY()
  {
    Dimension localDimension1 = getSize();
    Dimension localDimension2 = getMinimumSize();
    if (localDimension1.getHeight() <= localDimension2.getHeight()) {
      return 1.0D;
    }
    return localDimension1.getHeight() / localDimension2.getHeight();
  }
  
  public Dimension getMinimumSize()
  {
    Dimension localDimension = new Dimension(0, 0);
    if (this.myName != null)
    {
      localDimension.width = ((int)Math.ceil(this.myName.getBoundingRect().getWidth() + 30.0D));
      localDimension.height = ((int)Math.ceil(this.myName.getBoundingRect().getHeight() + 10.0D));
    }
    return localDimension;
  }
  
  public int getNoScaleRight(Rectangle paramRectangle)
  {
    return paramRectangle.x + paramRectangle.width - (this.myRectangle.getLeft() + this.myRectangle.getWidth());
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle, this.myCircle, this.myPort, this.myLine };
  }
  
  public abstract Vector getAllLinks();
  
  public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    paramVector1.add(this.id);
    paramVector2.add(this);
    paramElement.setAttribute("id", this.id);
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("remoteString", this.remoteString);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    return paramElement;
  }
  
  public static ConnectionPost loadXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    String str1 = paramElement.getTagName();
    String str2 = paramElement.getAttribute("name");
    Object localObject = null;
    if (str1.equals(GCDocument.connectionPostInTag)) {
      localObject = new ConnectionPostIn(new Point(), str2);
    } else if (str1.equals(GCDocument.connectionPostOutTag)) {
      localObject = new ConnectionPostOut(new Point(), str2);
    }
    String str3 = paramElement.getAttribute("id");
    if (Utils.getSaveVersion(paramElement) >= 2) {
      ((ConnectionPost)localObject).id = str3;
    }
    paramVector1.add(str3);
    paramVector2.add(localObject);
    ((ConnectionPost)localObject).remoteString = paramElement.getAttribute("remoteString");
    if (!((ConnectionPost)localObject).isEmpty()) {
      ((ConnectionPost)localObject).remote = ((ConnectionPost)Editor.findObject(((ConnectionPost)localObject).remoteString));
    }
    ((ConnectionPost)localObject).removeObject(((ConnectionPost)localObject).myName);
    XMLUtil.restoreBoundingRectAny(paramElement, (GrafcetObject)localObject, ((ConnectionPost)localObject).myRectangle, 2);
    ((ConnectionPost)localObject).addObjectAtTail(((ConnectionPost)localObject).myName);
    ((ConnectionPost)localObject).layoutChildren();
    return (ConnectionPost)localObject;
  }
  
  public String getName()
  {
    return this.myName.getText();
  }
  
  public void setName(String paramString)
  {
    this.myName.setText(paramString);
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
  
  public boolean doUncapturedMouseMove(int paramInt, Point paramPoint1, Point paramPoint2, JGoView paramJGoView)
  {
    if (this.remote != null)
    {
      colored1 = this.remote;
      colored2 = this;
      this.remote.myRectangle.setBrush(liteRed);
      this.myRectangle.setBrush(liteRed);
    }
    return true;
  }
  
  public String getToolTipText()
  {
    if (!this.remoteString.equals("")) {
      return this.remoteString;
    }
    return null;
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_ConnectionPosts";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ConnectionPost.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */