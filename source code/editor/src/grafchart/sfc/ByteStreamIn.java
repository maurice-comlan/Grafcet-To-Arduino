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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import org.w3c.dom.Element;

public class ByteStreamIn
  extends GCVariable
  implements Readable
{
  protected JGoStroke myBorder = null;
  protected JGoText myValue = null;
  public JGoText myName = null;
  protected JGoStroke myLine1 = null;
  protected JGoStroke myLine2 = null;
  private double lineSeparation = 3.0D;
  public String val = "";
  public String oldval = "";
  public String host = "";
  public int port = -1;
  public transient Socket socket;
  public transient BufferedReader in;
  
  public ByteStreamIn() {}
  
  public ByteStreamIn(Point paramPoint)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myBorder = new JGoStroke();
    this.myBorder.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 1.0F)));
    this.myBorder.addPoint(0, 0);
    this.myBorder.addPoint(80, 0);
    this.myBorder.addPoint(80, 60);
    this.myBorder.addPoint(0, 60);
    this.myBorder.addPoint(20, 30);
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
    this.myName = new JGoText("BSIn");
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
    ByteStreamIn localByteStreamIn = (ByteStreamIn)paramJGoArea;
    localByteStreamIn.myBorder = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBorder));
    localByteStreamIn.myLine1 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine1));
    localByteStreamIn.myLine2 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine2));
    localByteStreamIn.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localByteStreamIn.myValue = ((JGoText)paramJGoCopyEnvironment.copy(this.myValue));
    localByteStreamIn.addObjectAtHead(localByteStreamIn.myBorder);
    localByteStreamIn.addObjectAtHead(localByteStreamIn.myLine1);
    localByteStreamIn.addObjectAtHead(localByteStreamIn.myLine2);
    localByteStreamIn.addObjectAtTail(localByteStreamIn.myName);
    localByteStreamIn.addObjectAtTail(localByteStreamIn.myValue);
    localByteStreamIn.lineSeparation = this.lineSeparation;
    localByteStreamIn.port = this.port;
    localByteStreamIn.host = this.host;
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
    paramElement.setAttribute("port", "" + this.port);
    paramElement.setAttribute("host", this.host);
    removeTextFields();
    XMLUtil.saveBoundingRect(paramElement, this);
    restoreTextFields();
    return paramElement;
  }
  
  public static ByteStreamIn loadXML(Element paramElement)
  {
    ByteStreamIn localByteStreamIn = new ByteStreamIn(new Point());
    localByteStreamIn.myName.setText(paramElement.getAttribute("name"));
    localByteStreamIn.myValue.setText(paramElement.getAttribute("value"));
    localByteStreamIn.host = paramElement.getAttribute("host");
    localByteStreamIn.port = XMLUtil.getInt(paramElement, "port", -1);
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      localByteStreamIn.removeTextFields();
      XMLUtil.restoreBoundingRect(paramElement, localByteStreamIn);
      localByteStreamIn.restoreTextFields();
    }
    else
    {
      localByteStreamIn.removeObject(localByteStreamIn.myName);
      localByteStreamIn.removeObject(localByteStreamIn.myValue);
      XMLUtil.restoreBoundingRectOld(paramElement, localByteStreamIn);
      localByteStreamIn.addObjectAtTail(localByteStreamIn.myName);
      localByteStreamIn.addObjectAtTail(localByteStreamIn.myValue);
    }
    return localByteStreamIn;
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
    Point localPoint = this.myBorder.getSpotLocation(8);
    this.myLine1.setSpotLocation(8, (int)localPoint.getX(), (int)Math.round(localPoint.getY() - this.lineSeparation));
    this.myLine2.setSpotLocation(8, (int)localPoint.getX(), (int)Math.round(localPoint.getY() + this.lineSeparation));
    localPoint = this.myBorder.getSpotLocation(6);
    this.myName.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 7);
    localPoint = this.myBorder.getSpotLocation(0);
    this.myValue.setSpotLocation(8, (int)localPoint.getX() + 5, (int)localPoint.getY());
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myBorder, this.myLine1, this.myLine2 };
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width != getWidth()) || (paramRectangle.height != getHeight())) {
      this.lineSeparation *= getScaleFactorY(paramRectangle);
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
  
  public void readInput()
  {
    this.oldval = this.val;
    this.val = "";
    try
    {
      while ((isConnected()) && (this.in.ready()))
      {
        int i = this.in.read();
        if (i != -1)
        {
          this.val += (char)i;
        }
        else
        {
          Utils.writeInformation("\"" + getFullName() + "\" was disconnected.");
          shutdownConnection();
        }
      }
    }
    catch (IOException localIOException)
    {
      Utils.writeError("IOException for \"" + getFullName() + "\": " + localIOException.getMessage());
      shutdownConnection();
    }
    this.myValue.setText(this.val);
  }
  
  public boolean getBoolVal()
  {
    String str = getStringVal();
    return str.equals("1");
  }
  
  public boolean getOldBoolVal()
  {
    String str = getOldStringVal();
    return str.equals("1");
  }
  
  public String getStringVal()
  {
    return this.val;
  }
  
  public String getOldStringVal()
  {
    return this.oldval;
  }
  
  public int getIntVal()
  {
    String str = getStringVal();
    int i = 0;
    try
    {
      i = Integer.parseInt(str);
    }
    catch (Exception localException) {}
    return i;
  }
  
  public int getOldIntVal()
  {
    String str = getOldStringVal();
    int i = 0;
    try
    {
      i = Integer.parseInt(str);
    }
    catch (Exception localException) {}
    return i;
  }
  
  public double getRealVal()
  {
    String str = getStringVal();
    double d = 0.0D;
    try
    {
      d = Double.parseDouble(str);
    }
    catch (Exception localException) {}
    return d;
  }
  
  public double getOldRealVal()
  {
    String str = getOldStringVal();
    double d = 0.0D;
    try
    {
      d = Double.parseDouble(str);
    }
    catch (Exception localException) {}
    return d;
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
  
  public void shutdownConnection()
  {
    if (isConnected())
    {
      this.in = null;
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
    this.in = null;
    int i = 0;
    for (int j = 1; (j < 10) && (i == 0); j++) {
      try
      {
        this.socket = new Socket(this.host, this.port);
        i = 1;
        try
        {
          this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }
        catch (Exception localException1)
        {
          Utils.writeError("Could not open input stream for \"" + getFullName() + "\".");
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
    return this.in != null;
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_ByteStream_In";
  }
  
  public String toString()
  {
    return this.myName.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ByteStreamIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */