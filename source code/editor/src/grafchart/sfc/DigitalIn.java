package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoObjectCollection;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import com.nwoods.jgo.JGoView;
import grafchart.graphics.MyJGoText;
import grafchart.sfc.io.DigitalInput;
import grafchart.sfc.io.LocalIO;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import org.w3c.dom.Element;

public class DigitalIn
  extends GCVariable
  implements Readable, InputVariable, Observer
{
  protected JGoStroke myBorder = null;
  public JGoText myName = null;
  public JGoText myChannelLabel = null;
  protected JGoText myChannel = null;
  protected String channel = "";
  protected MyJGoText myValue = null;
  protected boolean prevScanVal = false;
  protected boolean curScanVal = false;
  protected boolean val = false;
  static Color red = new Color(1.0F, 0.0F, 0.0F);
  static Color green = new Color(0.0F, 1.0F, 0.0F);
  static JGoBrush redSolidBrush = new JGoBrush(65535, red);
  static JGoBrush greenSolidBrush = new JGoBrush(65535, green);
  static JGoBrush noFill = new JGoBrush();
  static JGoPen redPen = new JGoPen(65535, 2, red);
  static JGoPen greenPen = new JGoPen(65535, 2, green);
  static JGoPen standardPen = new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F));
  public transient DigitalInput digIn = null;
  public boolean cyclicUpdated = true;
  
  public DigitalIn() {}
  
  public DigitalIn(Point paramPoint)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myBorder = new JGoStroke();
    this.myBorder.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myBorder.addPoint(0, 0);
    this.myBorder.addPoint(80, 0);
    this.myBorder.addPoint(80, 60);
    this.myBorder.addPoint(0, 60);
    this.myBorder.addPoint(20, 30);
    this.myBorder.addPoint(0, 0);
    this.myBorder.setSelectable(false);
    this.myBorder.setDraggable(false);
    this.myBorder.setPen(redPen);
    this.myName = new JGoText("DIn");
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
    this.myValue = new MyJGoText("0");
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
    DigitalIn localDigitalIn = (DigitalIn)paramJGoArea;
    localDigitalIn.myBorder = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBorder));
    localDigitalIn.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localDigitalIn.myChannel = ((JGoText)paramJGoCopyEnvironment.copy(this.myChannel));
    localDigitalIn.myChannelLabel = ((JGoText)paramJGoCopyEnvironment.copy(this.myChannelLabel));
    localDigitalIn.myValue = ((MyJGoText)paramJGoCopyEnvironment.copy(this.myValue));
    localDigitalIn.addObjectAtHead(localDigitalIn.myBorder);
    localDigitalIn.addObjectAtTail(localDigitalIn.myName);
    localDigitalIn.addObjectAtTail(localDigitalIn.myChannel);
    localDigitalIn.addObjectAtTail(localDigitalIn.myChannelLabel);
    localDigitalIn.addObjectAtTail(localDigitalIn.myValue);
    localDigitalIn.cyclicUpdated = this.cyclicUpdated;
    localDigitalIn.startObserving();
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
  
  public static DigitalIn loadXML(Element paramElement)
  {
    String str1 = paramElement.getTagName();
    Object localObject = null;
    if (str1.equals(GCDocument.digitalIn1Tag)) {
      localObject = new DigitalIn1(new Point());
    } else if (str1.equals(GCDocument.digitalInTag)) {
      localObject = new DigitalIn(new Point());
    }
    String str2 = paramElement.getAttribute("name");
    ((DigitalIn)localObject).myName.setText(str2);
    String str3 = paramElement.getAttribute("value");
    ((DigitalIn)localObject).myValue.setText(str3);
    if (str3.equals("0"))
    {
      ((DigitalIn)localObject).drawBorder(redPen);
      ((DigitalIn)localObject).val = false;
    }
    else
    {
      ((DigitalIn)localObject).drawBorder(greenPen);
      ((DigitalIn)localObject).val = true;
    }
    String str4 = paramElement.getAttribute("channel");
    ((DigitalIn)localObject).myChannel.setText(str4);
    ((DigitalIn)localObject).channel = str4;
    ((DigitalIn)localObject).cyclicUpdated = XMLUtil.getBool(paramElement, "cyclic", true);
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      ((DigitalIn)localObject).removeTextFields();
      XMLUtil.restoreBoundingRect(paramElement, (JGoObject)localObject);
      ((DigitalIn)localObject).restoreTextFields();
    }
    else
    {
      ((DigitalIn)localObject).removeObject(((DigitalIn)localObject).myName);
      XMLUtil.restoreBoundingRectOld(paramElement, (JGoObject)localObject);
      ((DigitalIn)localObject).addObjectAtTail(((DigitalIn)localObject).myName);
    }
    ((DigitalIn)localObject).startObserving();
    return (DigitalIn)localObject;
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
      if ((!this.channel.equals(str)) || (this.digIn == null))
      {
        this.channel = str;
        try
        {
          this.digIn = Editor.localIO.createDigitalInput(this.channel);
        }
        catch (Exception localException)
        {
          Utils.writeException(localException);
        }
      }
    }
  }
  
  private void updateValue()
  {
    if (getDocument().isSimulating()) {
      setValue(!this.myValue.getText().equals("0"));
    } else {
      setValue(updateDigitalIn());
    }
  }
  
  protected boolean updateDigitalIn()
  {
    return this.digIn.get();
  }
  
  public void readInput()
  {
    updateValue();
    this.prevScanVal = this.curScanVal;
    this.curScanVal = this.val;
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
    if (this.curScanVal) {
      return 1.0D;
    }
    return 0.0D;
  }
  
  public void initialize()
  {
    compile();
    readInput();
  }
  
  public void stop()
  {
    this.digIn = null;
  }
  
  public boolean doMouseDblClick(int paramInt, Point paramPoint1, Point paramPoint2, JGoView paramJGoView)
  {
    setValue(!this.val);
    return true;
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
    return this.curScanVal;
  }
  
  public boolean getOldBoolVal()
  {
    return this.prevScanVal;
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
    return getBoolVal() ? "1" : "0";
  }
  
  public String getOldStringVal()
  {
    return getOldBoolVal() ? "1" : "0";
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_DigitalInput";
  }
  
  protected void ownerChange(JGoObjectCollection paramJGoObjectCollection1, JGoObjectCollection paramJGoObjectCollection2, JGoObject paramJGoObject)
  {
    if (paramJGoObjectCollection2 == null) {
      this.digIn = null;
    }
    super.ownerChange(paramJGoObjectCollection1, paramJGoObjectCollection2, paramJGoObject);
  }
  
  public String toString()
  {
    return getName() + ": " + getIntVal();
  }
  
  public void setValue(boolean paramBoolean)
  {
    boolean bool = this.val;
    this.val = paramBoolean;
    if ((this.val) && (!bool))
    {
      drawBorder(greenPen);
      this.myValue.setText("1");
    }
    else if ((!this.val) && (bool))
    {
      drawBorder(redPen);
      this.myValue.setText("0");
    }
    if ((this.val) && (!this.myValue.getText().equals("1"))) {
      this.myValue.setText("1");
    }
    Inspector.refresh();
  }
  
  protected void drawBorder(JGoPen paramJGoPen)
  {
    this.myBorder.setPen(paramJGoPen);
  }
  
  public boolean getValue()
  {
    return this.val;
  }
  
  private void startObserving()
  {
    this.myValue.addObserver(this);
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    setValue(!this.myValue.getText().equals("0"));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/DigitalIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */