package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoObjectCollection;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.io.AnalogOutput;
import grafchart.sfc.io.LocalIO;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.w3c.dom.Element;

public class AnalogOut
  extends GCVariable
  implements Writable, Readable
{
  protected JGoStroke myBorder = null;
  public JGoText myName = null;
  public JGoText myChannelLabel = null;
  public JGoText myChannel = null;
  public String channel = "";
  protected JGoText myValue = null;
  public double val = 0.0D;
  public transient AnalogOutput analogOut = null;
  public static DecimalFormat df = new DecimalFormat();
  
  public AnalogOut() {}
  
  public AnalogOut(Point paramPoint, String paramString)
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
    this.myName = new JGoText("AOut");
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
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    AnalogOut localAnalogOut = (AnalogOut)paramJGoArea;
    localAnalogOut.myBorder = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBorder));
    localAnalogOut.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localAnalogOut.myChannel = ((JGoText)paramJGoCopyEnvironment.copy(this.myChannel));
    localAnalogOut.myChannelLabel = ((JGoText)paramJGoCopyEnvironment.copy(this.myChannelLabel));
    localAnalogOut.myValue = ((JGoText)paramJGoCopyEnvironment.copy(this.myValue));
    localAnalogOut.addObjectAtHead(localAnalogOut.myBorder);
    localAnalogOut.addObjectAtTail(localAnalogOut.myName);
    localAnalogOut.addObjectAtTail(localAnalogOut.myChannel);
    localAnalogOut.addObjectAtTail(localAnalogOut.myChannelLabel);
    localAnalogOut.addObjectAtTail(localAnalogOut.myValue);
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
  
  public static AnalogOut loadXML(Element paramElement)
  {
    String str1 = paramElement.getAttribute("value");
    AnalogOut localAnalogOut = new AnalogOut(new Point(), str1);
    localAnalogOut.myName.setText(paramElement.getAttribute("name"));
    String str2 = paramElement.getAttribute("channel");
    localAnalogOut.myChannel.setText(str2);
    localAnalogOut.channel = str2;
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      localAnalogOut.removeTextFields();
      XMLUtil.restoreBoundingRect(paramElement, localAnalogOut);
      localAnalogOut.restoreTextFields();
    }
    else
    {
      localAnalogOut.removeObject(localAnalogOut.myName);
      XMLUtil.restoreBoundingRectOld(paramElement, localAnalogOut);
      localAnalogOut.addObjectAtTail(localAnalogOut.myName);
    }
    return localAnalogOut;
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
      if ((!this.channel.equals(str)) || (this.analogOut == null))
      {
        this.channel = str;
        try
        {
          this.analogOut = Editor.localIO.createAnalogOutput(this.channel);
        }
        catch (Exception localException)
        {
          Utils.writeException(localException);
        }
      }
    }
    this.myValue.setText("");
  }
  
  public void setStoredRealAction(double paramDouble)
  {
    this.val = paramDouble;
    this.myValue.setText(df.format(this.val));
    Inspector.refresh();
    if (!getDocument().isSimulating()) {
      this.analogOut.set(this.val);
    }
    layoutChildren();
  }
  
  public void setStoredBoolAction(boolean paramBoolean) {}
  
  public void setStoredIntAction(int paramInt) {}
  
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
    return true;
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
    return getIntVal() == 1;
  }
  
  public boolean getOldBoolVal()
  {
    return getOldIntVal() == 1;
  }
  
  public int getIntVal()
  {
    return (int)this.val;
  }
  
  public int getOldIntVal()
  {
    return (int)this.val;
  }
  
  public double getRealVal()
  {
    return this.val;
  }
  
  public double getOldRealVal()
  {
    return this.val;
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
    return "LangRef_IO_AnalogOutput";
  }
  
  protected void ownerChange(JGoObjectCollection paramJGoObjectCollection1, JGoObjectCollection paramJGoObjectCollection2, JGoObject paramJGoObject)
  {
    if (paramJGoObjectCollection2 == null) {
      this.analogOut = null;
    }
    super.ownerChange(paramJGoObjectCollection1, paramJGoObjectCollection2, paramJGoObject);
  }
  
  public String toString()
  {
    return getName() + ": " + getRealVal();
  }
  
  static
  {
    DecimalFormatSymbols localDecimalFormatSymbols = df.getDecimalFormatSymbols();
    localDecimalFormatSymbols.setDecimalSeparator('.');
    df.setGroupingUsed(false);
    df.setMaximumFractionDigits(3);
    df.setMinimumFractionDigits(1);
    df.setDecimalFormatSymbols(localDecimalFormatSymbols);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/AnalogOut.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */