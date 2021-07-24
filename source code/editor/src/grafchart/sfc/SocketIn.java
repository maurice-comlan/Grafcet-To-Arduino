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
import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.Element;

public abstract class SocketIn
  extends GCVariable
  implements Readable, SymbolTableObject
{
  protected JGoStroke myBorder = null;
  protected JGoText myValue = null;
  public JGoText myName = null;
  protected JGoStroke myLine1 = null;
  protected JGoStroke myLine2 = null;
  protected JGoText myFixedText = null;
  private String identifier = "";
  private double lineSeparation = 3.0D;
  private BooleanVariable hasNewMessage = null;
  private boolean hasNewMessageNext = false;
  public static ArrayList<SocketIn> socketInputs = new ArrayList();
  private static boolean hasPrintedIgnoredWarning = false;
  
  public SocketIn() {}
  
  public SocketIn(Point paramPoint, String paramString)
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
    this.myName = new JGoText("SIn");
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
    init();
  }
  
  private void init()
  {
    this.hasNewMessage = new BooleanVariable(new Point());
    this.hasNewMessage.setName("msg");
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    SocketIn localSocketIn = (SocketIn)paramJGoArea;
    localSocketIn.myBorder = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBorder));
    localSocketIn.myLine1 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine1));
    localSocketIn.myLine2 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine2));
    localSocketIn.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localSocketIn.myValue = ((JGoText)paramJGoCopyEnvironment.copy(this.myValue));
    localSocketIn.myFixedText = ((JGoText)paramJGoCopyEnvironment.copy(this.myFixedText));
    localSocketIn.addObjectAtHead(localSocketIn.myBorder);
    localSocketIn.addObjectAtHead(localSocketIn.myLine1);
    localSocketIn.addObjectAtHead(localSocketIn.myLine2);
    localSocketIn.addObjectAtTail(localSocketIn.myName);
    localSocketIn.addObjectAtTail(localSocketIn.myValue);
    localSocketIn.addObjectAtTail(localSocketIn.myFixedText);
    localSocketIn.lineSeparation = this.lineSeparation;
    localSocketIn.init();
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
    removeTextFields();
    XMLUtil.saveBoundingRect(paramElement, this);
    restoreTextFields();
    return paramElement;
  }
  
  public static SocketIn loadXML(Element paramElement)
  {
    Object localObject = null;
    String str = paramElement.getTagName();
    if (str.equals(GCDocument.sBooleanInTag)) {
      localObject = new SocketBooleanIn(new Point());
    } else if (str.equals(GCDocument.sIntInTag)) {
      localObject = new SocketIntIn(new Point());
    } else if (str.equals(GCDocument.sRealInTag)) {
      localObject = new SocketRealIn(new Point());
    } else if (str.equals(GCDocument.sStringInTag)) {
      localObject = new SocketStringIn(new Point());
    }
    ((SocketIn)localObject).myName.setText(paramElement.getAttribute("name"));
    ((SocketIn)localObject).myValue.setText(paramElement.getAttribute("value"));
    ((SocketIn)localObject).identifier = paramElement.getAttribute("identifier");
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      ((SocketIn)localObject).removeTextFields();
      XMLUtil.restoreBoundingRect(paramElement, (JGoObject)localObject);
      ((SocketIn)localObject).restoreTextFields();
    }
    else
    {
      ((SocketIn)localObject).setTopLeft((int)Math.round(Double.parseDouble(paramElement.getAttribute("x"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("y"))));
      ((SocketIn)localObject).setHeight((int)Math.round(Double.parseDouble(paramElement.getAttribute("height"))));
      ((SocketIn)localObject).removeObject(((SocketIn)localObject).myName);
      ((SocketIn)localObject).removeObject(((SocketIn)localObject).myValue);
      ((SocketIn)localObject).setWidth((int)Math.round(Double.parseDouble(paramElement.getAttribute("width"))));
      ((SocketIn)localObject).addObjectAtTail(((SocketIn)localObject).myName);
      ((SocketIn)localObject).addObjectAtTail(((SocketIn)localObject).myValue);
    }
    return (SocketIn)localObject;
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
    this.myFixedText.setSpotLocation(0, this.myBorder, 0);
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
  
  public void compile() {}
  
  public void setValue(String paramString)
  {
    if (this.hasNewMessageNext) {
      Utils.writeWarning(getFullName() + " updated more than once since previous scan cycle.");
    }
    this.hasNewMessageNext = true;
  }
  
  public void readInput()
  {
    this.hasNewMessage.setStoredBoolAction(this.hasNewMessageNext);
    this.hasNewMessageNext = false;
  }
  
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
  
  public boolean getBoolVal()
  {
    return false;
  }
  
  public boolean getOldBoolVal()
  {
    return false;
  }
  
  public int getIntVal()
  {
    return 0;
  }
  
  public int getOldIntVal()
  {
    return 0;
  }
  
  public double getRealVal()
  {
    return 0.0D;
  }
  
  public double getOldRealVal()
  {
    return 0.0D;
  }
  
  public String getStringVal()
  {
    return new String("");
  }
  
  public String getOldStringVal()
  {
    return new String("");
  }
  
  public static void addSocketInput(SocketIn paramSocketIn)
  {
    int i = socketInputs.indexOf(paramSocketIn);
    if (i == -1) {
      socketInputs.add(paramSocketIn);
    }
  }
  
  public static void removeSocketInput(SocketIn paramSocketIn)
  {
    for (int i = socketInputs.indexOf(paramSocketIn); i != -1; i = socketInputs.indexOf(paramSocketIn)) {
      socketInputs.remove(i);
    }
  }
  
  private String getSocketIdentifier()
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
  
  public static void setSocketIn(String paramString1, String paramString2)
  {
    int i = 0;
    Iterator localIterator = socketInputs.iterator();
    while (localIterator.hasNext())
    {
        SocketIn localObject = (SocketIn)localIterator.next();
      if (paramString1.equals(((SocketIn)localObject).getSocketIdentifier()))
      {
        ((SocketIn)localObject).setValue(paramString2);
        i = 1;
      }
    }
    int j = Editor.topGrafcharts.getStorage().size() > 0 ? 1 : 0;
    Object localObject = Editor.topGrafcharts.getStorage().iterator();
    while (((Iterator)localObject).hasNext())
    {
      GCDocument localGCDocument = (GCDocument)((Iterator)localObject).next();
      if (!localGCDocument.executing) {
        j = 0;
      }
    }
    if (i == 0) {
      if (j != 0)
      {
        Utils.writeWarning("Tag \"" + paramString1 + "\" not found. Ignoring Socket input value \"" + paramString2 + "\".");
      }
      else if (!hasPrintedIgnoredWarning)
      {
        Utils.writeWarning("Socket input value was ignored, possibly due to an application not executing. Details about ignored socket input values are available when all applications are executing.");
        hasPrintedIgnoredWarning = true;
      }
    }
  }
  
  public void evaluate()
  {
    getDocument().socketSend(getSocketIdentifier(), "", true);
  }
  
  public static String evaluateOne(SocketIn paramSocketIn)
  {
    return "<var>" + paramSocketIn.getSocketIdentifier() + "</var>";
  }
  
  public String toString()
  {
    return getName();
  }
  
  public ArrayList<Referencable> getSymbolTable()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this.hasNewMessage);
    return localArrayList;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/SocketIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */