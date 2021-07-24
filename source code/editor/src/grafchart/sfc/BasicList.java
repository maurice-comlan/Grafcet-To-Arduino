package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;

public abstract class BasicList
  extends GCVariable
  implements Referencable
{
  private JGoRectangle myBorder = null;
  private JGoRectangle myBox = null;
  private JGoStroke myLine = null;
  private String initialValues = "";
  private JGoText myTag1 = null;
  private JGoText myTag2 = null;
  private JGoText myName = null;
  private List values = Collections.synchronizedList(new ArrayList());
  
  public BasicList() {}
  
  public BasicList(Point paramPoint, String paramString)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myBorder = new JGoRectangle();
    this.myBorder.setSize(85, 65);
    this.myBorder.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myBorder.setSelectable(false);
    this.myBorder.setDraggable(false);
    this.myBox = new JGoRectangle(new Rectangle(50, 20));
    this.myBox.setSelectable(false);
    this.myBox.setDraggable(false);
    this.myLine = new JGoStroke();
    this.myLine.addPoint(10, 0);
    this.myLine.addPoint(10, 20);
    this.myLine.setSelectable(false);
    this.myLine.setDraggable(false);
    this.myName = new JGoText("List");
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    this.myTag1 = new JGoText(paramString);
    this.myTag1.setSelectable(false);
    this.myTag1.setEditable(false);
    this.myTag1.setDraggable(false);
    this.myTag1.setAlignment(2);
    this.myTag1.setTransparent(true);
    this.myTag2 = new JGoText(paramString);
    this.myTag2.setSelectable(false);
    this.myTag2.setEditable(false);
    this.myTag2.setDraggable(false);
    this.myTag2.setAlignment(2);
    this.myTag2.setTransparent(true);
    addObjectAtHead(this.myBorder);
    addObjectAtHead(this.myBox);
    addObjectAtHead(this.myLine);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myTag1);
    addObjectAtTail(this.myTag2);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    BasicList localBasicList = (BasicList)paramJGoArea;
    localBasicList.myBorder = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myBorder));
    localBasicList.myBox = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myBox));
    localBasicList.myLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myLine));
    localBasicList.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localBasicList.myTag1 = ((JGoText)paramJGoCopyEnvironment.copy(this.myTag1));
    localBasicList.myTag2 = ((JGoText)paramJGoCopyEnvironment.copy(this.myTag2));
    localBasicList.addObjectAtHead(localBasicList.myBorder);
    localBasicList.addObjectAtTail(localBasicList.myBox);
    localBasicList.addObjectAtTail(localBasicList.myLine);
    localBasicList.addObjectAtTail(localBasicList.myName);
    localBasicList.addObjectAtTail(localBasicList.myTag1);
    localBasicList.addObjectAtTail(localBasicList.myTag2);
    localBasicList.values = new ArrayList(this.values);
    localBasicList.initialValues = this.initialValues;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.myName.getText());
    paramElement.setAttribute("initialValue", this.initialValues);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    return paramElement;
  }
  
  public static BasicList loadXML(Element paramElement)
  {
    Object localObject = null;
    String str = paramElement.getTagName();
    if (str.equals(GCDocument.booleanListTag)) {
      localObject = new BooleanList(new Point());
    } else if (str.equals(GCDocument.integerListTag)) {
      localObject = new IntegerList(new Point());
    } else if (str.equals(GCDocument.stringListTag)) {
      localObject = new StringList(new Point());
    } else if (str.equals(GCDocument.realListTag)) {
      localObject = new RealList(new Point());
    }
    ((BasicList)localObject).myName.setText(paramElement.getAttribute("name"));
    ((BasicList)localObject).initialValues = paramElement.getAttribute("initialValue");
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      ((BasicList)localObject).removeObject(((BasicList)localObject).myName);
      XMLUtil.restoreBoundingRect(paramElement, (JGoObject)localObject);
      ((BasicList)localObject).addObjectAtTail(((BasicList)localObject).myName);
    }
    else
    {
      XMLUtil.restoreBoundingRectOld(paramElement, ((BasicList)localObject).myBorder);
      ((BasicList)localObject).layoutChildren();
    }
    return (BasicList)localObject;
  }
  
  public void layoutChildren()
  {
    this.myBox.setSpotLocation(0, this.myBorder, 0);
    this.myLine.setSpotLocation(0, this.myBorder, 0);
    this.myName.setSpotLocation(2, this.myBorder, 6);
    Point localPoint = this.myBox.getSpotLocation(8);
    this.myTag1.setSpotLocation(8, localPoint.x + 5, localPoint.y);
    localPoint = this.myLine.getSpotLocation(8);
    this.myTag2.setSpotLocation(8, localPoint.x + 5, localPoint.y);
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
  
  public Point getLocation(Point paramPoint)
  {
    return getSpotLocation(1, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    setSpotLocation(1, paramInt1, paramInt2);
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
    return false;
  }
  
  public boolean isReal()
  {
    return false;
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
  
  public String getInitialValues()
  {
    return this.initialValues;
  }
  
  public void setInitialValues(String paramString)
  {
    this.initialValues = paramString;
  }
  
  public void initialize()
  {
    this.values.clear();
    String str1 = this.initialValues;
    if (!str1.equals(""))
    {
      for (int i = str1.indexOf(";"); i != -1; i = str1.indexOf(";"))
      {
        String str2 = str1.substring(0, i);
        str1 = str1.substring(i + 1);
        addInitialValue(str2);
      }
      addInitialValue(str1);
    }
  }
  
  public List getValues()
  {
    return this.values;
  }
  
  public abstract void addInitialValue(String paramString);
  
  public void basicAdd(int paramInt, Object paramObject)
  {
    this.values.add(paramInt, paramObject);
  }
  
  public void basicAdd(Object paramObject)
  {
    this.values.add(paramObject);
  }
  
  public void basicAddFirst(Object paramObject)
  {
    this.values.add(0, paramObject);
  }
  
  public boolean isListEmpty()
  {
    return this.values.isEmpty();
  }
  
  public void append(BasicList paramBasicList)
  {
    this.values.addAll(paramBasicList.values);
  }
  
  public void clear()
  {
    this.values.clear();
  }
  
  public void basicSet(int paramInt, Object paramObject)
  {
    this.values.set(paramInt, paramObject);
  }
  
  public int size()
  {
    return this.values.size();
  }
  
  public Object basicGet(int paramInt)
  {
    return this.values.get(paramInt);
  }
  
  public int basicIndexOf(Object paramObject)
  {
    return this.values.indexOf(paramObject);
  }
  
  public void remove(int paramInt)
  {
    this.values.remove(paramInt);
  }
  
  public void writeFile(String paramString)
  {
    try
    {
      FileWriter localFileWriter = new FileWriter(new File(paramString).getCanonicalFile());
      PrintWriter localPrintWriter = new PrintWriter(localFileWriter, false);
      Iterator localIterator = this.values.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        localPrintWriter.println(localObject.toString());
      }
      localPrintWriter.close();
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/BasicList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */