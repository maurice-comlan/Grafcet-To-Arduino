package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoText;
import grafchart.graphics.MyJGoImage;
import grafchart.sfc.actions.Goal;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import org.w3c.dom.Element;

public class SFCPlotter
  extends GrafcetObject
{
  public JGoRectangle myRectangle = null;
  public JGoText myName = new JGoText();
  public MyJGoImage myIcon = null;
  public String chan1String = "";
  public String chan2String = "";
  public transient Goal chan1Node = null;
  public transient Goal chan2Node = null;
  transient PlotPanel plotter;
  int channels = 0;
  boolean running = false;
  boolean pausing = false;
  transient JInternalFrame frame;
  boolean visible;
  public Rectangle bounds = null;
  transient GCView parentView = null;
  double xRangeSeconds = 60.0D;
  double yMin = -1.0D;
  double yMax = 1.0D;
  int xTicks = 5;
  int xGrid = 10;
  int yTicks = 5;
  int yGrid = 10;
  int updateFreq = 4;
  String legend = "";
  Color color1 = Color.black;
  Color color2 = Color.red;
  
  public SFCPlotter() {}
  
  public SFCPlotter(Point paramPoint, String paramString)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myRectangle = new JGoRectangle();
    this.myRectangle.setSize(75, 55);
    this.myRectangle.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myRectangle.setSelectable(false);
    this.myRectangle.setDraggable(false);
    this.myName = new JGoText(paramString);
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    this.myIcon = new MyJGoImage(paramPoint, new Dimension(75, 55));
    this.myIcon.loadImage("../iconlib/sinusoid.jpg");
    this.myIcon.setSelectable(false);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myIcon);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    SFCPlotter localSFCPlotter = (SFCPlotter)paramJGoArea;
    localSFCPlotter.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localSFCPlotter.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localSFCPlotter.myIcon = ((MyJGoImage)paramJGoCopyEnvironment.copy(this.myIcon));
    localSFCPlotter.addObjectAtHead(localSFCPlotter.myRectangle);
    localSFCPlotter.addObjectAtTail(localSFCPlotter.myName);
    localSFCPlotter.addObjectAtTail(localSFCPlotter.myIcon);
    localSFCPlotter.chan1String = this.chan1String;
    localSFCPlotter.chan2String = this.chan2String;
    localSFCPlotter.plotter = this.plotter;
    localSFCPlotter.frame = this.frame;
    localSFCPlotter.xRangeSeconds = this.xRangeSeconds;
    localSFCPlotter.yMin = this.yMin;
    localSFCPlotter.yMax = this.yMax;
    localSFCPlotter.xTicks = this.xTicks;
    localSFCPlotter.xGrid = this.xGrid;
    localSFCPlotter.yTicks = this.yTicks;
    localSFCPlotter.yGrid = this.yGrid;
    localSFCPlotter.updateFreq = this.updateFreq;
    localSFCPlotter.pausing = this.pausing;
    localSFCPlotter.running = this.running;
  }
  
  public void layoutChildren()
  {
    Point localPoint = this.myRectangle.getSpotLocation(6);
    this.myName.setSpotLocation(2, localPoint.x, localPoint.y);
    this.myIcon.setSpotLocation(0, this.myRectangle, 0);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle, this.myIcon };
  }
  
  public int getNoScaleBottom(Rectangle paramRectangle)
  {
    return paramRectangle.y + paramRectangle.height - (this.myRectangle.getTop() + this.myRectangle.getHeight());
  }
  
  public Dimension getMinimumSize()
  {
    return new Dimension(this.myName.getWidth() + 10, this.myName.getHeight() + 10);
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.myName.getText());
    if (this.bounds != null)
    {
      XMLUtil.setInt(paramElement, "boundsX", this.bounds.x);
      XMLUtil.setInt(paramElement, "boundsY", this.bounds.y);
      XMLUtil.setInt(paramElement, "boundsWidth", this.bounds.width);
      XMLUtil.setInt(paramElement, "boundsHeight", this.bounds.height);
    }
    XMLUtil.setDouble(paramElement, "xRange", this.xRangeSeconds);
    XMLUtil.setInt(paramElement, "xTicks", this.xTicks);
    XMLUtil.setInt(paramElement, "xGrid", this.xGrid);
    XMLUtil.setDouble(paramElement, "ymin", this.yMin);
    XMLUtil.setDouble(paramElement, "ymax", this.yMax);
    XMLUtil.setInt(paramElement, "yTicks", this.yTicks);
    XMLUtil.setInt(paramElement, "yGrid", this.yGrid);
    XMLUtil.setInt(paramElement, "updateFreq", this.updateFreq);
    paramElement.setAttribute("chan1", this.chan1String);
    paramElement.setAttribute("chan2", this.chan2String);
    paramElement.setAttribute("legend", this.legend);
    XMLUtil.setInt(paramElement, "channels", this.channels);
    XMLUtil.setInt(paramElement, "rgb1", this.color1.getRGB());
    XMLUtil.setInt(paramElement, "rgb2", this.color2.getRGB());
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    return paramElement;
  }
  
  public static SFCPlotter loadXML(Element paramElement)
  {
    String str = paramElement.getAttribute("name");
    SFCPlotter localSFCPlotter = new SFCPlotter(new Point(), str);
    if (paramElement.hasAttribute("boundsX")) {
      if (Utils.getSaveVersion(paramElement) >= 3) {
        localSFCPlotter.bounds = new Rectangle(XMLUtil.getInt(paramElement, "boundsX"), XMLUtil.getInt(paramElement, "boundsY"), XMLUtil.getInt(paramElement, "boundsWidth"), XMLUtil.getInt(paramElement, "boundsHeight"));
      } else {
        localSFCPlotter.bounds = new Rectangle((int)Math.round(XMLUtil.getDouble(paramElement, "boundsX")), (int)Math.round(XMLUtil.getDouble(paramElement, "boundsY")), (int)Math.round(XMLUtil.getDouble(paramElement, "boundsWidth")), (int)Math.round(XMLUtil.getDouble(paramElement, "boundsHeight")));
      }
    }
    localSFCPlotter.xRangeSeconds = XMLUtil.getDouble(paramElement, "xRange", 60.0D);
    localSFCPlotter.xTicks = XMLUtil.getInt(paramElement, "xTicks", 5);
    localSFCPlotter.xGrid = XMLUtil.getInt(paramElement, "xGrid", 10);
    localSFCPlotter.yMin = XMLUtil.getDouble(paramElement, "ymin", -1.0D);
    localSFCPlotter.yMax = XMLUtil.getDouble(paramElement, "ymax", 1.0D);
    localSFCPlotter.yTicks = XMLUtil.getInt(paramElement, "yTicks", 5);
    localSFCPlotter.yGrid = XMLUtil.getInt(paramElement, "yGrid", 10);
    localSFCPlotter.updateFreq = XMLUtil.getInt(paramElement, "updateFreq", 4);
    localSFCPlotter.chan1String = paramElement.getAttribute("chan1");
    localSFCPlotter.chan2String = paramElement.getAttribute("chan2");
    localSFCPlotter.legend = paramElement.getAttribute("legend");
    localSFCPlotter.channels = XMLUtil.getInt(paramElement, "channels");
    localSFCPlotter.color1 = new Color(XMLUtil.getInt(paramElement, "rgb1", 0));
    localSFCPlotter.color2 = new Color(XMLUtil.getInt(paramElement, "rgb2", 16711680));
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      localSFCPlotter.removeObject(localSFCPlotter.myName);
      XMLUtil.restoreBoundingRect(paramElement, localSFCPlotter);
      localSFCPlotter.addObjectAtTail(localSFCPlotter.myName);
    }
    else
    {
      localSFCPlotter.setTopLeft((int)Math.round(Double.parseDouble(paramElement.getAttribute("x"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("y"))));
      localSFCPlotter.setHeight((int)Math.round(Double.parseDouble(paramElement.getAttribute("height"))));
      localSFCPlotter.removeObject(localSFCPlotter.myName);
      localSFCPlotter.setWidth((int)Math.round(Double.parseDouble(paramElement.getAttribute("width"))));
      localSFCPlotter.addObjectAtTail(localSFCPlotter.myName);
    }
    return localSFCPlotter;
  }
  
  public void initialize(int paramInt)
  {
    if ((this.plotter == null) || (paramInt != this.channels))
    {
      this.channels = paramInt;
      AppAction.updateAllActions();
      this.plotter = new PlotPanel(this.channels, 5);
      this.plotter.setBorder(BorderFactory.createEtchedBorder());
      this.plotter.setXAxis(this.xRangeSeconds, this.xTicks, this.xGrid);
      this.plotter.setYAxis(this.yMax - this.yMin, this.yMin, this.yTicks, this.yGrid);
      this.plotter.setUpdateFreq(this.updateFreq);
      this.plotter.setTitle(this.legend);
      if (this.channels > 0) {
        this.plotter.setColor(1, this.color1);
      }
      if (this.channels > 1) {
        this.plotter.setColor(2, this.color2);
      }
    }
  }
  
  public void updatePlot(double paramDouble)
  {
    double d1 = 0.0D;
    double d2 = 0.0D;
    if (this.channels > 0) {
      d1 = this.chan1Node.evaluateReal();
    }
    if (this.channels > 1) {
      d2 = this.chan2Node.evaluateReal();
    }
    if ((this.channels == 1) && (!this.pausing)) {
      this.plotter.putData(paramDouble, d1);
    } else if ((this.channels == 2) && (!this.pausing)) {
      this.plotter.putData(paramDouble, d1, d2);
    }
  }
  
  public void setVisible(boolean paramBoolean)
  {
    this.frame.setVisible(paramBoolean);
  }
  
  public String getFullName()
  {
    GCDocument localGCDocument = getDocument();
    return localGCDocument.getFullName() + "." + this.myName.getText();
  }
  
  public String getHelpID()
  {
    return "LangRef_GUI_Plotter";
  }
  
  public String toString()
  {
    return this.myName.getText();
  }
  
  public double getXRangeSeconds()
  {
    return this.xRangeSeconds;
  }
  
  public void setXRangeSeconds(double paramDouble)
  {
    this.xRangeSeconds = paramDouble;
  }
  
  public int getXTicks()
  {
    return this.xTicks;
  }
  
  public void setXTicks(int paramInt)
  {
    this.xTicks = paramInt;
  }
  
  public int getXGrid()
  {
    return this.xGrid;
  }
  
  public void setXGrid(int paramInt)
  {
    this.xGrid = paramInt;
  }
  
  public double getYMin()
  {
    return this.yMin;
  }
  
  public void setYMin(double paramDouble)
  {
    this.yMin = paramDouble;
  }
  
  public double getYMax()
  {
    return this.yMax;
  }
  
  public void setYMax(double paramDouble)
  {
    this.yMax = paramDouble;
  }
  
  public int getYTicks()
  {
    return this.yTicks;
  }
  
  public void setYTicks(int paramInt)
  {
    this.yTicks = paramInt;
  }
  
  public int getYGrid()
  {
    return this.yGrid;
  }
  
  public void setYGrid(int paramInt)
  {
    this.yGrid = paramInt;
  }
  
  public int getUpdateFreq()
  {
    return this.updateFreq;
  }
  
  public void setUpdateFreq(int paramInt)
  {
    this.updateFreq = paramInt;
  }
  
  public String getLegend()
  {
    return this.legend;
  }
  
  public void setLegend(String paramString)
  {
    this.legend = paramString;
  }
  
  public Color getColor1()
  {
    return this.color1;
  }
  
  public void setColor1(Color paramColor)
  {
    this.color1 = paramColor;
  }
  
  public Color getColor2()
  {
    return this.color2;
  }
  
  public void setColor2(Color paramColor)
  {
    this.color2 = paramColor;
  }
  
  public void updatePlotter()
  {
    if (this.plotter != null)
    {
      this.plotter.setXAxis(this.xRangeSeconds, this.xTicks, this.xGrid);
      this.plotter.setYAxis(this.yMax - this.yMin, this.yMin, this.yTicks, this.yGrid);
      this.plotter.setUpdateFreq(this.updateFreq);
      this.plotter.setTitle(this.legend);
      if (this.channels > 0) {
        this.plotter.setColor(1, this.color1);
      }
      if (this.channels > 1) {
        this.plotter.setColor(2, this.color2);
      }
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/SFCPlotter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */