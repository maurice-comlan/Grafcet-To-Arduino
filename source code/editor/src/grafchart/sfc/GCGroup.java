package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoDocument;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoView;
import grafchart.graphics.MyJGoArea;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GCGroup
  extends JGoArea
  implements Referencable, GCCollection, SymbolTableObject
{
  private JGoRectangle boundingRect = new JGoRectangle();
  private JGoEllipse myToken = null;
  private Vector linkVec = new Vector();
  private Vector contVec = new Vector();
  private boolean objectified = false;
  private boolean contentHidden = false;
  private boolean showBorder = false;
  private boolean tokenShowing = false;
  private boolean isModifying = false;
  private int minWidth = 0;
  private int minHeight = 0;
  private String name = "";
  
  public GCGroup()
  {
    this.boundingRect.setBrush(JGoBrush.Null);
    this.boundingRect.setPen(JGoPen.Null);
    this.boundingRect.setSelectable(false);
    this.myToken = new JGoEllipse();
    this.myToken.setSize(20, 20);
    this.myToken.setSelectable(false);
    this.myToken.setDraggable(false);
    this.myToken.setPen(JGoPen.Null);
    this.myToken.setBrush(JGoBrush.Null);
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
  
  public GrafcetObject getOwner()
  {
    GCDocument localGCDocument = getDocument();
    return localGCDocument.owner;
  }
  
  public ArrayList<Referencable> getSymbolTable()
  {
    return Utils.collectionToSymbolTable(this);
  }
  
  public void toggleObjectify()
  {
    this.objectified = (!this.objectified);
    this.contentHidden = this.objectified;
    redraw();
  }
  
  public void toggleBorder()
  {
    this.showBorder = (!this.showBorder);
    redraw();
  }
  
  private void redraw()
  {
    if (this.objectified)
    {
      setGrabChildSelection(true);
      if (this.contentHidden)
      {
        showBorder(2);
        hideContent();
      }
      else
      {
        hideBorder();
        showContent();
      }
    }
    else
    {
      setGrabChildSelection(false);
      showContent();
      if (this.showBorder) {
        showBorder(1);
      } else {
        hideBorder();
      }
    }
  }
  
  public boolean isBoundingRect(JGoObject paramJGoObject)
  {
    return paramJGoObject == this.boundingRect;
  }
  
  private void showContent()
  {
    this.boundingRect.setBrush(JGoBrush.Null);
    undrawToken();
  }
  
  private void hideContent()
  {
    Color localColor = new Color(getDocument().getPaperColor().getRGB());
    this.boundingRect.setBrush(new JGoBrush(65535, localColor));
    if (this.tokenShowing) {
      drawToken();
    } else {
      undrawToken();
    }
  }
  
  public boolean isObjectified()
  {
    return this.objectified;
  }
  
  public boolean isHidden()
  {
    return this.contentHidden;
  }
  
  private void drawToken()
  {
    this.myToken.setBrush(JGoBrush.black);
    int i = getWidth();
    int j = getHeight();
    this.myToken.setSize((int)(Math.min(i, j) * 0.5D), (int)(Math.min(i, j) * 0.5D));
    this.myToken.setSpotLocation(0, this.boundingRect, 0);
    super.addObjectAtTail(this.myToken);
  }
  
  private void undrawToken()
  {
    this.myToken.setBrush(JGoBrush.Null);
    super.removeObject(this.myToken);
  }
  
  private void showBorder(int paramInt)
  {
    this.boundingRect.setPen(new JGoPen(65535, paramInt, Color.black));
  }
  
  private void hideBorder()
  {
    this.boundingRect.setPen(JGoPen.Null);
  }
  
  public void showToken()
  {
    this.tokenShowing = true;
    redraw();
    if ((getParent() instanceof GCGroup)) {
      ((GCGroup)getParent()).showToken();
    }
  }
  
  public void hideToken()
  {
    this.tokenShowing = false;
    redraw();
    if ((getParent() instanceof GCGroup)) {
      ((GCGroup)getParent()).hideToken();
    }
  }
  
  void updateChildren()
  {
    for (JGoListPosition localJGoListPosition = getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = getNextObjectPos(localJGoListPosition))
    {
      JGoObject localJGoObject = getObjectAtPos(localJGoListPosition);
      if ((localJGoObject instanceof Connectable))
      {
        Vector localVector = ((Connectable)localJGoObject).getAllLinks();
        Enumeration localEnumeration = localVector.elements();
        while (localEnumeration.hasMoreElements())
        {
          GCLink localGCLink = (GCLink)localEnumeration.nextElement();
          if ((!this.linkVec.contains(localGCLink)) && (findObject(localGCLink) == null)) {
            this.linkVec.add(localGCLink);
          }
        }
      }
      if ((localJGoObject instanceof GCGroup)) {
        ((GCGroup)localJGoObject).updateChildren();
      }
      if (!this.contVec.contains(localJGoObject)) {
        this.contVec.add(localJGoObject);
      }
    }
  }
  
  public void reGroup(GCGroup paramGCGroup)
  {
    GCDocument localGCDocument = getDocument();
    if (localGCDocument != null)
    {
      boolean bool = this.isModifying;
      if (!this.isModifying) {
        notifyModificationBegin();
      }
      localGCDocument.removeObject(this);
      if (paramGCGroup != null) {
        paramGCGroup.addObjectAtTail(this);
      } else {
        localGCDocument.addObjectAtTail(this);
      }
      Enumeration localEnumeration = this.contVec.elements();
      Object localObject1;
      Object localObject2;
      while (localEnumeration.hasMoreElements())
      {
        localObject1 = localEnumeration.nextElement();
        if (((localObject1 instanceof JGoObject)) && (!(localObject1 instanceof GCLink)) && (!(localObject1 instanceof GCGroup)))
        {
          localObject2 = (JGoObject)localObject1;
          removeObject((JGoObject)localObject2);
          addObjectAtTail((JGoObject)localObject2);
        }
      }
      localEnumeration = this.contVec.elements();
      while (localEnumeration.hasMoreElements())
      {
        localObject1 = localEnumeration.nextElement();
        if ((localObject1 instanceof GCGroup)) {
          ((GCGroup)localObject1).reGroup(this);
        }
      }
      localEnumeration = this.contVec.elements();
      Object localObject3;
      JGoPort localJGoPort1;
      while (localEnumeration.hasMoreElements())
      {
        localObject1 = localEnumeration.nextElement();
        if ((localObject1 instanceof GCLink))
        {
          localObject2 = (GCLink)localObject1;
          removeObject((JGoObject)localObject2);
          addObjectAtTail((JGoObject)localObject2);
          localObject3 = ((GCLink)localObject2).getFromPort();
          localJGoPort1 = ((GCLink)localObject2).getToPort();
          ((GCLink)localObject2).setFromPort(null);
          ((GCLink)localObject2).setToPort(null);
          ((GCLink)localObject2).setFromPort((JGoPort)localObject3);
          ((GCLink)localObject2).setToPort(localJGoPort1);
        }
      }
      localEnumeration = this.linkVec.elements();
      while (localEnumeration.hasMoreElements())
      {
        localObject1 = localEnumeration.nextElement();
        if ((localObject1 instanceof GCLink))
        {
          localObject2 = (GCLink)localObject1;
          localObject3 = ((GCLink)localObject2).getParent();
          if (localObject3 != null) {
            ((JGoArea)localObject3).addObjectAtTail((JGoObject)localObject2);
          } else {
            localGCDocument.addObjectAtTail((JGoObject)localObject2);
          }
          localJGoPort1 = ((GCLink)localObject2).getFromPort();
          JGoPort localJGoPort2 = ((GCLink)localObject2).getToPort();
          ((GCLink)localObject2).setFromPort(null);
          ((GCLink)localObject2).setToPort(null);
          ((GCLink)localObject2).setFromPort(localJGoPort1);
          ((GCLink)localObject2).setToPort(localJGoPort2);
        }
      }
      if (!bool) {
        notifyModificationEnd();
      }
    }
    else
    {
      Utils.writeInternalError("No owner document");
    }
  }
  
  public void notifyModificationBegin()
  {
    if (this.isModifying)
    {
      Utils.writeInternalError("Already modifying group.");
      return;
    }
    super.removeObject(this.boundingRect);
    this.isModifying = true;
  }
  
  public void notifyModificationEnd()
  {
    if (!this.isModifying)
    {
      Utils.writeInternalError("Group is not being modified.");
      return;
    }
    super.addObjectAtTail(this.boundingRect);
    redraw();
    this.isModifying = false;
  }
  
  public JGoListPosition addObjectAtTail(JGoObject paramJGoObject)
  {
    if (!this.isModifying)
    {
      Utils.writeInternalError("Group modification has not been notified.");
      return null;
    }
    return super.addObjectAtTail(paramJGoObject);
  }
  
  public JGoListPosition addObjectAtHead(JGoObject paramJGoObject)
  {
    if (!this.isModifying)
    {
      Utils.writeInternalError("Group modification has not been notified.");
      return null;
    }
    return super.addObjectAtHead(paramJGoObject);
  }
  
  public void setBoundingRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.setBoundingRect(paramInt1, paramInt2, paramInt3, paramInt4);
    this.boundingRect.setBoundingRect(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected Rectangle handleResize(Graphics2D paramGraphics2D, JGoView paramJGoView, Rectangle paramRectangle, Point paramPoint, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.minWidth > paramInt3) {
      paramInt3 = this.minWidth;
    }
    if (this.minHeight > paramInt4) {
      paramInt4 = this.minHeight;
    }
    return super.handleResize(paramGraphics2D, paramJGoView, paramRectangle, paramPoint, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected boolean geometryChangeChild(JGoObject paramJGoObject, Rectangle paramRectangle)
  {
    boolean bool = super.geometryChangeChild(paramJGoObject, paramRectangle);
    this.minWidth = ((int)Math.ceil(getBoundingRect().getWidth() / getMaxScaleX()));
    this.minHeight = ((int)Math.ceil(getBoundingRect().getHeight() / getMaxScaleY()));
    return bool;
  }
  
  public double getMaxScaleX()
  {
    double d1 = Double.MAX_VALUE;
    double d2 = Double.MAX_VALUE;
    for (JGoListPosition localJGoListPosition = getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = getNextObjectPos(localJGoListPosition))
    {
      JGoObject localJGoObject = getObjectAtPos(localJGoListPosition);
      if (((localJGoObject instanceof MyJGoArea)) || ((localJGoObject instanceof GCGroup)))
      {
        if ((localJGoObject instanceof MyJGoArea)) {
          d2 = ((MyJGoArea)localJGoObject).getMaxScaleX();
        } else {
          d2 = ((GCGroup)localJGoObject).getMaxScaleX();
        }
        if (d2 < d1) {
          d1 = d2;
        }
      }
    }
    return d1;
  }
  
  public double getMaxScaleY()
  {
    double d1 = Double.MAX_VALUE;
    double d2 = Double.MAX_VALUE;
    for (JGoListPosition localJGoListPosition = getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = getNextObjectPos(localJGoListPosition))
    {
      JGoObject localJGoObject = getObjectAtPos(localJGoListPosition);
      if (((localJGoObject instanceof MyJGoArea)) || ((localJGoObject instanceof GCGroup)))
      {
        if ((localJGoObject instanceof MyJGoArea)) {
          d2 = ((MyJGoArea)localJGoObject).getMaxScaleY();
        } else {
          d2 = ((GCGroup)localJGoObject).getMaxScaleY();
        }
        if (d2 < d1) {
          d1 = d2;
        }
      }
    }
    return d1;
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    super.geometryChange(paramRectangle);
    for (JGoListPosition localJGoListPosition = getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = getNextObjectPos(localJGoListPosition))
    {
      JGoObject localJGoObject = getObjectAtPos(localJGoListPosition);
      if ((localJGoObject instanceof GCLink)) {
        ((GCLink)localJGoObject).calculateStroke();
      }
    }
  }
  
  public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    paramElement.setAttribute("name", this.name);
    paramElement.setAttribute("hidden", this.contentHidden ? "1" : "0");
    paramElement.setAttribute("objectified", this.objectified ? "1" : "0");
    paramElement.setAttribute("showBorder", this.showBorder ? "1" : "0");
    JGoListPosition localJGoListPosition = getFirstObjectPos();
    for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); localJGoListPosition != null; localJGoObject = getObjectAtPos(localJGoListPosition))
    {
      getDocument().storeObjectAtTag(localJGoObject, paramElement, paramVector1, paramVector2);
      localJGoListPosition = getNextObjectPosAtTop(localJGoListPosition);
    }
    localJGoListPosition = getFirstObjectPos();
      JGoObject localJGoObject;
    for (localJGoObject = getObjectAtPos(localJGoListPosition); localJGoListPosition != null; localJGoObject = getObjectAtPos(localJGoListPosition))
    {
      if ((localJGoObject instanceof GCGroup)) {
        getDocument().storeGCGroupAtTag((GCGroup)localJGoObject, paramElement, paramVector1, paramVector2);
      }
      localJGoListPosition = getNextObjectPosAtTop(localJGoListPosition);
    }
    localJGoListPosition = getFirstObjectPos();
    for (localJGoObject = getObjectAtPos(localJGoListPosition); localJGoListPosition != null; localJGoObject = getObjectAtPos(localJGoListPosition))
    {
      if ((localJGoObject instanceof GCLink)) {
        getDocument().storeLinkAtTag((GCLink)localJGoObject, paramElement, paramVector1, paramVector2);
      }
      localJGoListPosition = getNextObjectPosAtTop(localJGoListPosition);
    }
    return paramElement;
  }
  
  public static GCGroup loadXML(Element paramElement, Vector paramVector1, Vector paramVector2, GCDocument paramGCDocument)
  {
    GCGroup localGCGroup = new GCGroup();
    localGCGroup.notifyModificationBegin();
    if (paramElement.hasAttribute("name")) {
      localGCGroup.setName(paramElement.getAttribute("name"));
    }
    localGCGroup.objectified = paramElement.getAttribute("objectified").equals("1");
    localGCGroup.contentHidden = paramElement.getAttribute("hidden").equals("1");
    localGCGroup.showBorder = paramElement.getAttribute("showBorder").equals("1");
    NodeList localNodeList = paramElement.getChildNodes();
    Node localNode;
    Element localElement;
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        localElement = (Element)localNode;
        paramGCDocument.loadObjectAtTag(localElement, localGCGroup, paramVector1, paramVector2);
      }
    }
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        localElement = (Element)localNode;
        if (localElement.getTagName().equals(GCDocument.gcGroupTag)) {
          paramGCDocument.loadGCGroupAtTag(localElement, localGCGroup, paramVector1, paramVector2);
        }
      }
    }
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        localElement = (Element)localNode;
        if (localElement.getTagName().equals(GCDocument.gcLinkTag)) {
          paramGCDocument.loadLinkAtTag(localElement, localGCGroup, paramVector1, paramVector2);
        }
      }
    }
    localGCGroup.notifyModificationEnd();
    return localGCGroup;
  }
  
  public String toString()
  {
    return "Group " + this.name;
  }
  
  public JGoObject copyObject(JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    GCGroup localGCGroup = (GCGroup)super.copyObject(paramJGoCopyEnvironment);
    if (localGCGroup != null) {
      localGCGroup.name = this.name;
    }
    return localGCGroup;
  }
  
  public GCDocument getDocument()
  {
    return (GCDocument)super.getDocument();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCGroup.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */