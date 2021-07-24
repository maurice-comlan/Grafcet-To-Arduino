package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoObjectCollection;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.io.DigitalOutput;
import grafchart.sfc.io.LocalIO;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import org.w3c.dom.Element;

public class DigitalOut
  extends GCVariable
  implements Writable, Readable
{
  protected JGoStroke myBorder = null;
  public JGoText myName = null;
  public JGoText myChannelLabel = null;
  public JGoText myChannel = null;
  public String channel = "";
  protected JGoText myValue = null;
  public boolean val = false;
  public boolean setLow = false;
  public boolean setHigh = false;
  static Color red = new Color(1.0F, 0.0F, 0.0F);
  static Color green = new Color(0.0F, 1.0F, 0.0F);
  static JGoBrush redSolidBrush = new JGoBrush(65535, red);
  static JGoBrush greenSolidBrush = new JGoBrush(65535, green);
  static JGoBrush noFill = new JGoBrush();
  static JGoPen redPen = new JGoPen(65535, 2, red);
  static JGoPen greenPen = new JGoPen(65535, 2, green);
  static JGoPen standardPen = new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F));
  public transient DigitalOutput digOut = null;
  
  public DigitalOut() {}
  
  public DigitalOut(Point paramPoint, String paramString)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myBorder = new JGoStroke();
    this.myBorder.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myBorder.addPoint(0, 0);
    this.myBorder.addPoint(60, 0);
    this.myBorder.addPoint(80, 30);
    this.myBorder.addPoint(60, 60);
    this.myBorder.addPoint(0, 60);
    this.myBorder.addPoint(0, 0);
    this.myBorder.setSelectable(false);
    this.myBorder.setDraggable(false);
    this.myBorder.setPen(redPen);
    this.myName = new JGoText("DOut");
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    this.myChannelLabel = new JGoText("Chan:");
    this.myChannelLabel.setSelectable(false);
    this.myChannelLabel.setEditable(false);
    this.myChannelLabel.setDraggable(false);
    this.myChannelLabel.setAlignment(1);
    this.myChannelLabel.setTransparent(true);
    this.myChannel = new JGoText("0");
    this.myChannel.setSelectable(true);
    this.myChannel.setEditable(true);
    this.myChannel.setEditOnSingleClick(true);
    this.myChannel.setDraggable(false);
    this.myChannel.setAlignment(1);
    this.myChannel.setTransparent(true);
    this.myValue = new JGoText(paramString);
    this.myValue.setSelectable(false);
    this.myValue.setEditable(false);
    this.myValue.setDraggable(false);
    this.myValue.setAlignment(1);
    this.myValue.setTransparent(true);
    addObjectAtHead(this.myBorder);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myChannel);
    addObjectAtTail(this.myChannelLabel);
    addObjectAtTail(this.myValue);
    if (paramString.equals("1"))
    {
      this.myBorder.setPen(greenPen);
      this.val = true;
    }
    else
    {
      this.myBorder.setPen(redPen);
      this.val = false;
      this.myValue.setText("0");
    }
    setLocation(paramPoint);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    DigitalOut localDigitalOut = (DigitalOut)paramJGoArea;
    localDigitalOut.myBorder = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBorder));
    localDigitalOut.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localDigitalOut.myChannel = ((JGoText)paramJGoCopyEnvironment.copy(this.myChannel));
    localDigitalOut.myChannelLabel = ((JGoText)paramJGoCopyEnvironment.copy(this.myChannelLabel));
    localDigitalOut.myValue = ((JGoText)paramJGoCopyEnvironment.copy(this.myValue));
    localDigitalOut.addObjectAtHead(localDigitalOut.myBorder);
    localDigitalOut.addObjectAtTail(localDigitalOut.myName);
    localDigitalOut.addObjectAtTail(localDigitalOut.myChannel);
    localDigitalOut.addObjectAtTail(localDigitalOut.myChannelLabel);
    localDigitalOut.addObjectAtTail(localDigitalOut.myValue);
    localDigitalOut.val = this.val;
  }
  
  private void removeTextFields()
  {
    removeObject(this.myName);
    removeObject(this.myChannel);
    removeObject(this.myChannelLabel);
    removeObject(this.myValue);
  }
  
  private void restoreTextFields()
  {
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myChannel);
    addObjectAtTail(this.myChannelLabel);
    addObjectAtTail(this.myValue);
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.myName.getText());
    paramElement.setAttribute("value", this.myValue.getText());
    paramElement.setAttribute("channel", this.myChannel.getText());
    removeTextFields();
    XMLUtil.saveBoundingRect(paramElement, this);
    restoreTextFields();
    return paramElement;
  }
  
  public static DigitalOut loadXML(Element paramElement)
  {
    String str1 = paramElement.getAttribute("value");
    String str2 = paramElement.getTagName();
    Object localObject = null;
    if ((str2.equals(GCDocument.digitalOut0Tag)) || (str2.equals(GCDocument.digitalOutTag))) {
      localObject = new DigitalOut(new Point(), str1);
    } else if (str2.equals(GCDocument.digitalOut1Tag)) {
      localObject = new DigitalOut1(new Point(), str1);
    }
    String str3 = paramElement.getAttribute("name");
    ((DigitalOut)localObject).myName.setText(str3);
    String str4 = paramElement.getAttribute("channel");
    ((DigitalOut)localObject).myChannel.setText(str4);
    ((DigitalOut)localObject).channel = str4;
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      ((DigitalOut)localObject).removeTextFields();
      XMLUtil.restoreBoundingRect(paramElement, (JGoObject)localObject);
      ((DigitalOut)localObject).restoreTextFields();
    }
    else
    {
      ((DigitalOut)localObject).removeObject(((DigitalOut)localObject).myName);
      if ((localObject instanceof DigitalOut1)) {
        ((DigitalOut)localObject).removeObject(((DigitalOut1)localObject).myCircle);
      }
      XMLUtil.restoreBoundingRectOld(paramElement, (JGoObject)localObject);
      ((DigitalOut)localObject).addObjectAtTail(((DigitalOut)localObject).myName);
      if ((localObject instanceof DigitalOut1)) {
        ((DigitalOut)localObject).addObjectAtTail(((DigitalOut1)localObject).myCircle);
      }
    }
    return (DigitalOut)localObject;
  }
  
  public Point getLocation(Point paramPoint)
  {
    return getSpotLocation(1, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    setSpotLocation(1, paramInt1, paramInt2);
    layoutChildren();
  }
  
  public void layoutChildren()
  {
    Point localPoint = this.myBorder.getSpotLocation(6);
    this.myName.setSpotLocation(2, (int)localPoint.getX() - 5, (int)localPoint.getY() + 7);
    this.myChannelLabel.setSpotLocation(3, this.myBorder, 2);
    this.myChannel.setSpotLocation(8, this.myChannelLabel, 4);
    this.myValue.setSpotLocation(0, this.myBorder, 0);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myBorder };
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
  
  public void compile()
  {
    if (!getDocument().isSimulating())
    {
      String str = this.myChannel.getText();
      if ((!this.channel.equals(str)) || (this.digOut == null))
      {
        this.channel = str;
        try
        {
          this.digOut = Editor.localIO.createDigitalOutput(this.channel);
        }
        catch (Exception localException)
        {
          Utils.writeException(localException);
        }
      }
    }
    drawBorder(standardPen);
  }
  
  public void setStoredBoolAction(boolean paramBoolean)
  {
    this.val = paramBoolean;
    if (paramBoolean)
    {
      this.myValue.setText("1");
      drawBorder(greenPen);
      updateDigitalOut(true);
    }
    else
    {
      this.myValue.setText("0");
      drawBorder(redPen);
      updateDigitalOut(false);
    }
    Inspector.refresh();
    layoutChildren();
  }
  
  public void setStoredIntAction(int paramInt) {}
  
  public void setStoredRealAction(double paramDouble) {}
  
  public void setNormalAction(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.setHigh = true;
    } else {
      this.setLow = true;
    }
  }
  
  public void effectuateNormalActions()
  {
    if (this.setHigh)
    {
      this.myValue.setText("1");
      drawBorder(greenPen);
      updateDigitalOut(true);
      Inspector.refresh();
      this.val = true;
      layoutChildren();
    }
    else if (this.setLow)
    {
      this.myValue.setText("0");
      drawBorder(redPen);
      updateDigitalOut(false);
      Inspector.refresh();
      this.val = false;
      layoutChildren();
    }
    this.setLow = false;
    this.setHigh = false;
  }
  
  public boolean isBoolean()
  {
    return true;
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
  
  public void setStoredStringAction(String paramString) {}
  
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
  
  public boolean getBoolVal()
  {
    return this.val;
  }
  
  public boolean getOldBoolVal()
  {
    return this.val;
  }
  
  public int getIntVal()
  {
    return getBoolVal() ? 1 : 0;
  }
  
  public int getOldIntVal()
  {
    return getOldBoolVal() ? 1 : 0;
  }
  
  public double getRealVal()
  {
    return getBoolVal() ? 1.0D : 0.0D;
  }
  
  public double getOldRealVal()
  {
    return getOldBoolVal() ? 1.0D : 0.0D;
  }
  
  public String getStringVal()
  {
    return "" + this.val;
  }
  
  public String getOldStringVal()
  {
    return "" + this.val;
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_DigitalOutput";
  }
  
  protected void ownerChange(JGoObjectCollection paramJGoObjectCollection1, JGoObjectCollection paramJGoObjectCollection2, JGoObject paramJGoObject)
  {
    if (paramJGoObjectCollection2 == null) {
      this.digOut = null;
    }
    super.ownerChange(paramJGoObjectCollection1, paramJGoObjectCollection2, paramJGoObject);
  }
  
  public String toString()
  {
    return getName() + ": " + getIntVal();
  }
  
  protected void drawBorder(JGoPen paramJGoPen)
  {
    this.myBorder.setPen(paramJGoPen);
  }
  
  protected void updateDigitalOut(boolean paramBoolean)
  {
    if (!getDocument().isSimulating()) {
      this.digOut.set(paramBoolean);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/DigitalOut.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */