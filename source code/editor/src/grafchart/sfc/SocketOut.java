package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import org.w3c.dom.Element;

public abstract class SocketOut
  extends GCVariable
  implements Writable
{
  protected JGoStroke myBorder = null;
  protected JGoText myValue = null;
  public JGoText myName = null;
  protected JGoStroke myLine1 = null;
  protected JGoStroke myLine2 = null;
  private JGoText myFixedText = null;
  protected String identifier = "";
  private double lineSeparation = 3.0D;
  private double linePositionX = 78.0D;
  public boolean procelMode = false;
  public SendMode sendMode = SendMode.Inherit;
  
  public SocketOut() {}
  
  public SocketOut(Point paramPoint, String paramString)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myBorder = new JGoStroke();
    this.myBorder.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 1.0F)));
    this.myBorder.addPoint(0, 0);
    this.myBorder.addPoint(60, 0);
    this.myBorder.addPoint(80, 30);
    this.myBorder.addPoint(60, 60);
    this.myBorder.addPoint(0, 60);
    this.myBorder.addPoint(0, 0);
    this.myBorder.setSelectable(false);
    this.myBorder.setDraggable(false);
    this.myLine1 = new JGoStroke();
    this.myLine1.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 1.0F)));
    this.myLine1.addPoint(0, 0);
    this.myLine1.addPoint(18, 0);
    this.myLine1.setDraggable(false);
    this.myLine1.setSelectable(false);
    this.myLine2 = new JGoStroke();
    this.myLine2.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 1.0F)));
    this.myLine2.addPoint(0, 0);
    this.myLine2.addPoint(18, 0);
    this.myLine2.setDraggable(false);
    this.myLine2.setSelectable(false);
    this.myName = new JGoText("SOut");
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    this.myValue = new JGoText("");
    this.myValue.setSelectable(true);
    this.myValue.setEditable(true);
    this.myValue.setEditOnSingleClick(true);
    this.myValue.setDraggable(false);
    this.myValue.setAlignment(1);
    this.myValue.setTransparent(true);
    this.myFixedText = new JGoText(paramString);
    this.myFixedText.setSelectable(false);
    this.myFixedText.setEditable(false);
    this.myFixedText.setDraggable(false);
    this.myFixedText.setAlignment(1);
    this.myFixedText.setTransparent(true);
    addObjectAtHead(this.myBorder);
    addObjectAtHead(this.myLine1);
    addObjectAtHead(this.myLine2);
    addObjectAtTail(this.myValue);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myFixedText);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    SocketOut localSocketOut = (SocketOut)paramJGoArea;
    localSocketOut.myBorder = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBorder));
    localSocketOut.myLine1 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine1));
    localSocketOut.myLine2 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine2));
    localSocketOut.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localSocketOut.myValue = ((JGoText)paramJGoCopyEnvironment.copy(this.myValue));
    localSocketOut.myFixedText = ((JGoText)paramJGoCopyEnvironment.copy(this.myFixedText));
    localSocketOut.addObjectAtHead(localSocketOut.myBorder);
    localSocketOut.addObjectAtHead(localSocketOut.myLine1);
    localSocketOut.addObjectAtHead(localSocketOut.myLine2);
    localSocketOut.addObjectAtTail(localSocketOut.myName);
    localSocketOut.addObjectAtTail(localSocketOut.myValue);
    localSocketOut.addObjectAtTail(localSocketOut.myFixedText);
    localSocketOut.lineSeparation = this.lineSeparation;
    localSocketOut.linePositionX = this.linePositionX;
    localSocketOut.procelMode = this.procelMode;
    localSocketOut.identifier = this.identifier;
    localSocketOut.sendMode = this.sendMode;
  }
  
  public Point getLocation(Point paramPoint)
  {
    return getSpotLocation(1, paramPoint);
  }
  
  private void removeTextFields()
  {
    removeObject(this.myName);
    removeObject(this.myValue);
  }
  
  private void restoreTextFields()
  {
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myValue);
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.myName.getText());
    paramElement.setAttribute("value", this.myValue.getText());
    paramElement.setAttribute("identifier", this.identifier);
    paramElement.setAttribute("sendMode", this.sendMode.name());
    XMLUtil.setBool(paramElement, "procelmode", this.procelMode);
    removeTextFields();
    XMLUtil.saveBoundingRect(paramElement, this);
    restoreTextFields();
    return paramElement;
  }
  
  public static SocketOut loadXML(Element paramElement)
  {
    Object localObject = null;
    String str = paramElement.getTagName();
    if (str.equals(GCDocument.sBooleanOutTag)) {
      localObject = new SocketBoolOut(new Point());
    } else if (str.equals(GCDocument.sIntOutTag)) {
      localObject = new SocketIntOut(new Point());
    } else if (str.equals(GCDocument.sRealOutTag)) {
      localObject = new SocketRealOut(new Point());
    } else if (str.equals(GCDocument.sStringOutTag)) {
      localObject = new SocketStringOut(new Point());
    }
    ((SocketOut)localObject).myName.setText(paramElement.getAttribute("name"));
    ((SocketOut)localObject).myValue.setText(paramElement.getAttribute("value"));
    ((SocketOut)localObject).identifier = paramElement.getAttribute("identifier");
    ((SocketOut)localObject).sendMode = SendMode.Inherit;
    if (Utils.getSaveVersion(paramElement) >= 7) {
      try
      {
        ((SocketOut)localObject).sendMode = SendMode.valueOf(paramElement.getAttribute("sendMode"));
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        if (!Editor.isTest) {
          Utils.writeError("Illegal send mode for \"" + ((SocketOut)localObject).getFullName() + "\"");
        }
      }
    }
    ((SocketOut)localObject).procelMode = XMLUtil.getBool(paramElement, "procelmode");
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      ((SocketOut)localObject).removeTextFields();
      XMLUtil.restoreBoundingRect(paramElement, (JGoObject)localObject);
      ((SocketOut)localObject).restoreTextFields();
    }
    else
    {
      ((SocketOut)localObject).setTopLeft((int)Math.round(Double.parseDouble(paramElement.getAttribute("x"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("y"))));
      ((SocketOut)localObject).setHeight((int)Math.round(Double.parseDouble(paramElement.getAttribute("height"))));
      ((SocketOut)localObject).removeObject(((SocketOut)localObject).myName);
      ((SocketOut)localObject).removeObject(((SocketOut)localObject).myValue);
      ((SocketOut)localObject).setWidth((int)Math.round(Double.parseDouble(paramElement.getAttribute("width"))));
      ((SocketOut)localObject).addObjectAtTail(((SocketOut)localObject).myName);
      ((SocketOut)localObject).addObjectAtTail(((SocketOut)localObject).myValue);
    }
    return (SocketOut)localObject;
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    setSpotLocation(1, paramInt1, paramInt2);
  }
  
  public void layoutChildren()
  {
    Point localPoint = this.myBorder.getSpotLocation(8);
    this.myLine1.setSpotLocation(8, (int)Math.round(localPoint.getX() + this.linePositionX), (int)Math.round(localPoint.getY() - this.lineSeparation));
    this.myLine2.setSpotLocation(8, (int)Math.round(localPoint.getX() + this.linePositionX), (int)Math.round(localPoint.getY() + this.lineSeparation));
    localPoint = this.myBorder.getSpotLocation(6);
    this.myName.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 7);
    localPoint = this.myBorder.getSpotLocation(0);
    this.myValue.setSpotLocation(8, (int)localPoint.getX() - 10, (int)localPoint.getY());
    this.myFixedText.setSpotLocation(8, (int)localPoint.getX() - 25, (int)localPoint.getY());
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myBorder, this.myLine1, this.myLine2 };
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width != getWidth()) || (paramRectangle.height != getHeight()))
    {
      this.lineSeparation *= getScaleFactorY(paramRectangle);
      this.linePositionX *= getScaleFactorX(paramRectangle);
    }
    super.geometryChange(paramRectangle);
  }
  
  public Dimension getMinimumSize()
  {
    int i = (int)Math.ceil(this.myName.getBoundingRect().getWidth() + 10.0D);
    int j = (int)Math.ceil(this.myName.getBoundingRect().getHeight() + 20.0D);
    return new Dimension(i, j);
  }
  
  public int getNoScaleBottom(Rectangle paramRectangle)
  {
    return paramRectangle.y + paramRectangle.height - (this.myBorder.getTop() + this.myBorder.getHeight());
  }
  
  public void readInput() {}
  
  public void initialize() {}
  
  public void stop() {}
  
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
  
  public String getSocketIdentifier()
  {
    return getSocketIdentifier(true);
  }
  
  public String getSocketIdentifier(boolean paramBoolean)
  {
    if ((this.identifier.isEmpty()) && (paramBoolean)) {
      return getName();
    }
    return this.identifier;
  }
  
  public void setSocketIdentifier(String paramString)
  {
    this.identifier = paramString;
  }
  
  public SendMode getSendMode()
  {
    if (this.sendMode != SendMode.Inherit) {
      return this.sendMode;
    }
    return getDocument().getSendMode();
  }
  
  public void setStoredBoolAction(boolean paramBoolean) {}
  
  public void setStoredRealAction(double paramDouble) {}
  
  public void setStoredIntAction(int paramInt) {}
  
  public void setStoredStringAction(String paramString) {}
  
  public boolean isBoolean()
  {
    return false;
  }
  
  public boolean isInteger()
  {
    return false;
  }
  
  public boolean isString()
  {
    return false;
  }
  
  public boolean isReal()
  {
    return false;
  }
  
  public boolean getBoolVal()
  {
    return false;
  }
  
  public boolean getOldBoolVal()
  {
    return false;
  }
  
  public String toString()
  {
    return getName();
  }
  
  public static enum SendMode
  {
    Inherit("<Inherit>"),  Assigned,  Changed;
    
    private String guiName;
    
    private SendMode()
    {
      this.guiName = name();
    }
    
    private SendMode(String paramString)
    {
      this.guiName = paramString;
    }
    
    public String toString()
    {
      return this.guiName;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/SocketOut.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */