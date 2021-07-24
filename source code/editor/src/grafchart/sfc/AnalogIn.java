package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoObjectCollection;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.io.AnalogInput;
import grafchart.sfc.io.LocalIO;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.w3c.dom.Element;

public class AnalogIn
  extends GCVariable
  implements Readable, InputVariable
{
  protected JGoStroke myBorder = null;
  public JGoText myName = null;
  public JGoText myChannelLabel = null;
  protected JGoText myChannel = null;
  protected String channel = "";
  public JGoText myValue = null;
  public double oldval = 0.0D;
  public double val = 0.0D;
  public boolean cyclicUpdated = true;
  public transient AnalogInput analogIn = null;
  public static DecimalFormat df = new DecimalFormat();
  
  public AnalogIn() {}
  
  public AnalogIn(Point paramPoint)
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
    this.myName = new JGoText("AIn");
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
    this.myValue = new JGoText("0.0");
    this.myValue.setSelectable(true);
    this.myValue.setEditable(true);
    this.myValue.setEditOnSingleClick(true);
    this.myValue.setDraggable(false);
    this.myValue.setAlignment(2);
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
    AnalogIn localAnalogIn = (AnalogIn)paramJGoArea;
    localAnalogIn.myBorder = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBorder));
    localAnalogIn.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localAnalogIn.myChannel = ((JGoText)paramJGoCopyEnvironment.copy(this.myChannel));
    localAnalogIn.myChannelLabel = ((JGoText)paramJGoCopyEnvironment.copy(this.myChannelLabel));
    localAnalogIn.myValue = ((JGoText)paramJGoCopyEnvironment.copy(this.myValue));
    localAnalogIn.addObjectAtHead(localAnalogIn.myBorder);
    localAnalogIn.addObjectAtTail(localAnalogIn.myName);
    localAnalogIn.addObjectAtTail(localAnalogIn.myChannel);
    localAnalogIn.addObjectAtTail(localAnalogIn.myChannelLabel);
    localAnalogIn.addObjectAtTail(localAnalogIn.myValue);
    localAnalogIn.cyclicUpdated = this.cyclicUpdated;
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
    XMLUtil.setBool(paramElement, "cyclic", this.cyclicUpdated);
    removeTextFields();
    XMLUtil.saveBoundingRect(paramElement, this);
    restoreTextFields();
    return paramElement;
  }
  
  public static AnalogIn loadXML(Element paramElement)
  {
    AnalogIn localAnalogIn = new AnalogIn(new Point());
    String str1 = paramElement.getAttribute("name");
    localAnalogIn.myName.setText(str1);
    String str2 = paramElement.getAttribute("value");
    localAnalogIn.myValue.setText(str2);
    try
    {
      localAnalogIn.val = Double.parseDouble(str2);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localAnalogIn.myValue.setTextColor(Color.RED);
    }
    String str3 = paramElement.getAttribute("channel");
    localAnalogIn.myChannel.setText(str3);
    localAnalogIn.channel = str3;
    localAnalogIn.cyclicUpdated = XMLUtil.getBool(paramElement, "cyclic", true);
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      localAnalogIn.removeTextFields();
      XMLUtil.restoreBoundingRect(paramElement, localAnalogIn);
      localAnalogIn.restoreTextFields();
    }
    else
    {
      localAnalogIn.removeObject(localAnalogIn.myName);
      XMLUtil.restoreBoundingRectOld(paramElement, localAnalogIn);
      localAnalogIn.addObjectAtTail(localAnalogIn.myName);
    }
    return localAnalogIn;
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
    this.myName.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 7);
    this.myChannelLabel.setSpotLocation(2, this.myBorder, 2);
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
      this.channel = str;
      try
      {
        this.analogIn = Editor.localIO.createAnalogInput(this.channel);
      }
      catch (Exception localException)
      {
        Utils.writeException(localException);
      }
    }
  }
  
  private void setText(String paramString)
  {
    this.myValue.setText(paramString);
    this.myValue.setTextColor(Color.BLACK);
    Inspector.refresh();
  }
  
  public void setValue(double paramDouble)
  {
    this.oldval = this.val;
    this.val = paramDouble;
    setText(df.format(paramDouble));
  }
  
  public boolean isCyclicUpdated()
  {
    return this.cyclicUpdated;
  }
  
  public void setCyclicUpdated(boolean paramBoolean)
  {
    this.cyclicUpdated = paramBoolean;
  }
  
  public double getSample()
  {
    readInput();
    return this.val;
  }
  
  public void readInput()
  {
    this.oldval = this.val;
    if (getDocument().isSimulating())
    {
      double d = Utils.parseDouble(this.myValue.getText(), this.val);
      this.oldval = this.val;
      this.val = d;
    }
    else
    {
      this.oldval = this.val;
      this.val = this.analogIn.get();
      setText(df.format(this.val));
    }
  }
  
  public void initialize()
  {
    compile();
    if (getDocument().isSimulating())
    {
      double d = Utils.parseDouble(this.myValue.getText());
      this.val = d;
      this.oldval = this.val;
    }
    else
    {
      this.oldval = this.val;
      this.val = this.analogIn.get();
      setText(df.format(this.val));
    }
  }
  
  public void stop()
  {
    this.analogIn = null;
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
  
  public boolean getBoolVal()
  {
    int i = getIntVal();
    return i == 1;
  }
  
  public boolean getOldBoolVal()
  {
    int i = getOldIntVal();
    return i == 1;
  }
  
  public int getIntVal()
  {
    return (int)this.val;
  }
  
  public int getOldIntVal()
  {
    return (int)this.oldval;
  }
  
  public double getRealVal()
  {
    return this.val;
  }
  
  public double getOldRealVal()
  {
    return this.oldval;
  }
  
  public String getStringVal()
  {
    return "" + this.val;
  }
  
  public String getOldStringVal()
  {
    return "" + this.oldval;
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_AnalogInput";
  }
  
  protected void ownerChange(JGoObjectCollection paramJGoObjectCollection1, JGoObjectCollection paramJGoObjectCollection2, JGoObject paramJGoObject)
  {
    if (paramJGoObjectCollection2 == null) {
      this.analogIn = null;
    }
    super.ownerChange(paramJGoObjectCollection1, paramJGoObjectCollection2, paramJGoObject);
  }
  
  public String toString()
  {
    return getName() + ": " + getRealVal();
  }
  
  public String getChannel()
  {
    return this.channel;
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/AnalogIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */