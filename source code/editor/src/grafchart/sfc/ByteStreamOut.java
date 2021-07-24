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
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import org.w3c.dom.Element;

public class ByteStreamOut
  extends GCVariable
  implements Writable
{
  protected JGoStroke myBorder = null;
  protected JGoText myValue = null;
  public JGoText myName = null;
  protected JGoStroke myLine1 = null;
  protected JGoStroke myLine2 = null;
  private double lineSeparation = 3.0D;
  private double linePositionX = 78.0D;
  public String host = "";
  public int port = -1;
  public transient Socket socket;
  public transient PrintWriter out;
  public String val = "";
  
  public ByteStreamOut() {}
  
  public ByteStreamOut(Point paramPoint)
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
    this.myName = new JGoText("BSOut");
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
    addObjectAtHead(this.myBorder);
    addObjectAtHead(this.myLine1);
    addObjectAtHead(this.myLine2);
    addObjectAtTail(this.myValue);
    addObjectAtTail(this.myName);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    ByteStreamOut localByteStreamOut = (ByteStreamOut)paramJGoArea;
    localByteStreamOut.myBorder = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBorder));
    localByteStreamOut.myLine1 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine1));
    localByteStreamOut.myLine2 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine2));
    localByteStreamOut.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localByteStreamOut.myValue = ((JGoText)paramJGoCopyEnvironment.copy(this.myValue));
    localByteStreamOut.addObjectAtHead(localByteStreamOut.myBorder);
    localByteStreamOut.addObjectAtHead(localByteStreamOut.myLine1);
    localByteStreamOut.addObjectAtHead(localByteStreamOut.myLine2);
    localByteStreamOut.addObjectAtTail(localByteStreamOut.myName);
    localByteStreamOut.addObjectAtTail(localByteStreamOut.myValue);
    localByteStreamOut.lineSeparation = this.lineSeparation;
    localByteStreamOut.linePositionX = this.linePositionX;
    localByteStreamOut.port = this.port;
    localByteStreamOut.host = this.host;
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
    paramElement.setAttribute("port", "" + this.port);
    paramElement.setAttribute("host", this.host);
    paramElement.setAttribute("value", this.myValue.getText());
    removeTextFields();
    XMLUtil.saveBoundingRect(paramElement, this);
    restoreTextFields();
    return paramElement;
  }
  
  public static ByteStreamOut loadXML(Element paramElement)
  {
    ByteStreamOut localByteStreamOut = new ByteStreamOut(new Point());
    localByteStreamOut.myName.setText(paramElement.getAttribute("name"));
    localByteStreamOut.host = paramElement.getAttribute("host");
    localByteStreamOut.port = XMLUtil.getInt(paramElement, "port", -1);
    localByteStreamOut.myValue.setText(paramElement.getAttribute("value"));
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      localByteStreamOut.removeTextFields();
      XMLUtil.restoreBoundingRect(paramElement, localByteStreamOut);
      localByteStreamOut.restoreTextFields();
    }
    else
    {
      localByteStreamOut.setTopLeft((int)Math.round(Double.parseDouble(paramElement.getAttribute("x"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("y"))));
      localByteStreamOut.setHeight((int)Math.round(Double.parseDouble(paramElement.getAttribute("height"))));
      localByteStreamOut.removeObject(localByteStreamOut.myName);
      localByteStreamOut.removeObject(localByteStreamOut.myValue);
      localByteStreamOut.setWidth((int)Math.round(Double.parseDouble(paramElement.getAttribute("width"))));
      localByteStreamOut.addObjectAtTail(localByteStreamOut.myName);
      localByteStreamOut.addObjectAtTail(localByteStreamOut.myValue);
    }
    return localByteStreamOut;
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
  
  public void setStoredStringAction(String paramString)
  {
    this.val = paramString;
    this.myValue.setText(this.val);
    if (isConnected())
    {
      this.out.println(this.val);
      this.out.flush();
    }
    layoutChildren();
  }
  
  public void setStoredBoolAction(boolean paramBoolean)
  {
    setStoredStringAction(paramBoolean ? "1" : "0");
  }
  
  public void setStoredRealAction(double paramDouble)
  {
    setStoredStringAction("" + paramDouble);
  }
  
  public void setStoredIntAction(int paramInt)
  {
    setStoredStringAction("" + paramInt);
  }
  
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
    return true;
  }
  
  public boolean isReal()
  {
    return false;
  }
  
  public void shutdownConnection()
  {
    if (isConnected())
    {
      this.out = null;
      try
      {
        this.socket.close();
      }
      catch (IOException localIOException)
      {
        Utils.writeError("Close socket failed for \"" + getFullName() + "\"");
      }
      this.socket = null;
      AppAction.updateAllActions();
    }
  }
  
  public void connect()
  {
    this.out = null;
    int i = 0;
    for (int j = 1; (j < 10) && (i == 0); j++) {
      try
      {
        this.socket = new Socket(this.host, this.port);
        i = 1;
        try
        {
          this.out = new PrintWriter(this.socket.getOutputStream(), true);
        }
        catch (Exception localException1)
        {
          Utils.writeError("Could not open output stream for \"" + getFullName() + "\".");
          break;
        }
      }
      catch (Exception localException2)
      {
        Utils.writeInformation("Attempt " + j + ": \"" + getFullName() + "\" could not establish connection with \"" + this.host + "\" on port " + this.port + ".");
        try
        {
          Thread.sleep(1000L);
        }
        catch (Exception localException3) {}
      }
    }
    if (i == 0) {
      Utils.writeError("Giving up.");
    }
    AppAction.updateAllActions();
  }
  
  public void initialize()
  {
    if ((isConfigured()) && (!isConnected())) {
      connect();
    }
  }
  
  public boolean isConfigured()
  {
    return (!this.host.isEmpty()) && (this.port != -1);
  }
  
  public boolean isConnected()
  {
    return this.out != null;
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_ByteStream_Out";
  }
  
  public String toString()
  {
    return this.myName.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ByteStreamOut.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */