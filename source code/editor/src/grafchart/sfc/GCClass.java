package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoDocument;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPolygon;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoSelection;
import com.nwoods.jgo.JGoText;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JInternalFrame;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GCClass
  extends GrafcetObject
  implements Readable
{
  private double rectSeparation = 4.0D;
  private double rectSpacing1 = 19.0D;
  private double rectSpacing2 = 32.0D;
  private double rectSpacing3 = 45.0D;
  private Vector instanceVec = new Vector();
  public String superClass = "";
  public JGoPolygon myPolygon = null;
  public JGoRectangle myRectangle1 = null;
  public JGoRectangle myRectangle2 = null;
  public JGoRectangle myRectangle3 = null;
  public JGoText myLabel = null;
  public GCDocument myContentDocument = null;
  public transient JInternalFrame frame = null;
  public transient GCView parentView = null;
  public transient GCView view = null;
  public Rectangle bounds = null;
  public double currentScale = 1.0D;
  public Point point = new Point(0, 0);
  public int rgbColor;
  public int stepCounterInt = 2;
  
  public GCClass() {}
  
  public GCClass(Point paramPoint, String paramString)
  {
    setSize(70, 60);
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myPolygon = new JGoPolygon();
    this.myPolygon.addPoint(getSpotLocation(2));
    this.myPolygon.addPoint(getSpotLocation(7));
    this.myPolygon.addPoint(getSpotLocation(5));
    this.myPolygon.setBrush(JGoBrush.Null);
    this.myPolygon.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myPolygon.setSelectable(false);
    this.myPolygon.setDraggable(false);
    this.myRectangle1 = new JGoRectangle(new Rectangle(10, 8));
    this.myRectangle1.setSelectable(false);
    this.myRectangle1.setDraggable(false);
    this.myRectangle2 = new JGoRectangle(new Rectangle(10, 8));
    this.myRectangle2.setSelectable(false);
    this.myRectangle2.setDraggable(false);
    this.myRectangle3 = new JGoRectangle(new Rectangle(10, 8));
    this.myRectangle3.setSelectable(false);
    this.myRectangle3.setDraggable(false);
    if (paramString != null)
    {
      this.myLabel = new JGoText(paramString);
      this.myLabel.setSelectable(true);
      this.myLabel.setEditable(true);
      this.myLabel.setEditOnSingleClick(true);
      this.myLabel.setDraggable(false);
      this.myLabel.setAlignment(2);
      this.myLabel.setTransparent(true);
    }
    else
    {
      this.myLabel = new JGoText("W1");
      this.myLabel.setSelectable(true);
      this.myLabel.setEditable(true);
      this.myLabel.setEditOnSingleClick(true);
      this.myLabel.setDraggable(false);
      this.myLabel.setAlignment(2);
      this.myLabel.setTransparent(true);
    }
    addObjectAtHead(this.myPolygon);
    addObjectAtTail(this.myRectangle1);
    addObjectAtTail(this.myRectangle2);
    addObjectAtTail(this.myRectangle3);
    if (this.myLabel != null) {
      addObjectAtTail(this.myLabel);
    }
    setLocation(paramPoint);
    layoutChildren();
    this.myContentDocument = new GCDocument(this);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    GCClass localGCClass = (GCClass)paramJGoArea;
    if (this.myPolygon != null)
    {
      localGCClass.myPolygon = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myPolygon));
      localGCClass.addObjectAtHead(localGCClass.myPolygon);
    }
    if (this.myRectangle1 != null)
    {
      localGCClass.myRectangle1 = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle1));
      localGCClass.addObjectAtTail(localGCClass.myRectangle1);
    }
    if (this.myRectangle2 != null)
    {
      localGCClass.myRectangle2 = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle2));
      localGCClass.addObjectAtTail(localGCClass.myRectangle2);
    }
    if (this.myRectangle3 != null)
    {
      localGCClass.myRectangle3 = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle3));
      localGCClass.addObjectAtTail(localGCClass.myRectangle3);
    }
    if (this.myLabel != null)
    {
      localGCClass.myLabel = ((JGoText)paramJGoCopyEnvironment.copy(this.myLabel));
      localGCClass.addObjectAtTail(localGCClass.myLabel);
    }
    if (this.myContentDocument != null)
    {
      localGCClass.myContentDocument = new GCDocument(localGCClass);
      localGCClass.myContentDocument.copyFromCollection(this.myContentDocument);
    }
    localGCClass.rectSeparation = this.rectSeparation;
    localGCClass.rectSpacing1 = this.rectSpacing1;
    localGCClass.rectSpacing2 = this.rectSpacing2;
    localGCClass.rectSpacing3 = this.rectSpacing3;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("x", Double.toString(getBoundingRect().getX()));
    paramElement.setAttribute("y", Double.toString(getBoundingRect().getY()));
    paramElement.setAttribute("height", Double.toString(getBoundingRect().getHeight()));
    removeObject(this.myLabel);
    paramElement.setAttribute("width", Double.toString(getBoundingRect().getWidth()));
    addObjectAtTail(this.myLabel);
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("superClass", this.superClass);
    Element localElement = paramElement.getOwnerDocument().createElement(GCDocument.documentTag);
    if (this.bounds != null)
    {
      localElement.setAttribute("boundsX", Double.toString(this.bounds.getX()));
      localElement.setAttribute("boundsY", Double.toString(this.bounds.getY()));
      localElement.setAttribute("boundsHeight", Double.toString(this.bounds.getHeight()));
      localElement.setAttribute("boundsWidth", Double.toString(this.bounds.getWidth()));
    }
    else
    {
      localElement.setAttribute("boundsX", Double.toString(400.0D));
      localElement.setAttribute("boundsY", Double.toString(600.0D));
      localElement.setAttribute("boundsHeight", Double.toString(400.0D));
      localElement.setAttribute("boundsWidth", Double.toString(400.0D));
    }
    localElement.setAttribute("scale", Double.toString(this.currentScale));
    if (this.point != null) {
      localElement.setAttribute("viewPositionX", Double.toString(this.point.getX()));
    }
    if (this.point != null) {
      localElement.setAttribute("viewPositionY", Double.toString(this.point.getY()));
    }
    localElement.setAttribute("color", Integer.toString(this.rgbColor));
    this.myContentDocument.storeXMLRec(localElement);
    paramElement.appendChild(localElement);
    return paramElement;
  }
  
  public static GCClass loadXML(Element paramElement)
  {
    double d1 = Double.parseDouble(paramElement.getAttribute("x"));
    double d2 = Double.parseDouble(paramElement.getAttribute("y"));
    Point localPoint1 = new Point((int)d1, (int)d2);
    GCClass localGCClass = new GCClass(localPoint1, null);
    localGCClass.setLeft((int)Math.round(d1));
    localGCClass.setTop((int)Math.round(d2));
    int i = (int)Math.round(Double.parseDouble(paramElement.getAttribute("height")));
    localGCClass.setHeight(i);
    int j = (int)Math.round(Double.parseDouble(paramElement.getAttribute("width")));
    localGCClass.setWidth(j);
    String str1 = paramElement.getAttribute("name");
    localGCClass.myLabel.setText(str1);
    String str2 = paramElement.getAttribute("superClass");
    localGCClass.superClass = str2;
    NodeList localNodeList = paramElement.getChildNodes();
    for (int k = 0; k < localNodeList.getLength(); k++)
    {
      Node localNode = localNodeList.item(k);
      if (localNode.getNodeType() == 1)
      {
        Element localElement = (Element)localNode;
        if (localElement.getTagName().equals(GCDocument.documentTag))
        {
          String str3 = localElement.getAttribute("boundsHeight");
          i = (int)Math.round(Double.parseDouble(str3));
          String str4 = localElement.getAttribute("boundsWidth");
          j = (int)Math.round(Double.parseDouble(str4));
          String str5 = localElement.getAttribute("boundsX");
          d1 = Double.parseDouble(str5);
          String str6 = localElement.getAttribute("boundsY");
          d2 = Double.parseDouble(str6);
          Rectangle localRectangle = new Rectangle((int)d1, (int)d2, j, i);
          localGCClass.bounds = localRectangle;
          String str7 = localElement.getAttribute("scale");
          localGCClass.currentScale = Double.parseDouble(str7);
          if (localElement.hasAttribute("viewPositionX"))
          {
            double d3 = Double.parseDouble(localElement.getAttribute("viewPositionX"));
            double d4 = Double.parseDouble(localElement.getAttribute("viewPositionY"));
            Point localPoint2 = new Point((int)Math.round(d3), (int)Math.round(d4));
            localGCClass.point = localPoint2;
          }
          String str8 = localElement.getAttribute("color");
          localGCClass.rgbColor = Integer.parseInt(str8);
          localGCClass.myContentDocument.setPaperColor(new Color(localGCClass.rgbColor));
          localGCClass.myContentDocument.loadXMLRec(localElement);
        }
      }
    }
    return localGCClass;
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
    if (this.myPolygon == null) {
      return;
    }
    Point localPoint = this.myPolygon.getSpotLocation(2);
    if (this.myRectangle1 != null) {
      this.myRectangle1.setSpotLocation(2, (int)Math.round(localPoint.getX() - this.rectSeparation), (int)Math.round(localPoint.getY() + this.rectSpacing1));
    }
    if (this.myRectangle2 != null) {
      this.myRectangle2.setSpotLocation(2, (int)Math.round(localPoint.getX() + this.rectSeparation), (int)Math.round(localPoint.getY() + this.rectSpacing2));
    }
    if (this.myRectangle3 != null) {
      this.myRectangle3.setSpotLocation(2, (int)Math.round(localPoint.getX() - this.rectSeparation), (int)Math.round(localPoint.getY() + this.rectSpacing3));
    }
    if (this.myLabel != null)
    {
      localPoint = this.myPolygon.getSpotLocation(6);
      this.myLabel.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 10);
    }
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width == getWidth()) && (paramRectangle.height == getHeight()))
    {
      super.geometryChange(paramRectangle);
    }
    else
    {
      double d1 = getScaleFactorX(paramRectangle);
      double d2 = getScaleFactorY(paramRectangle);
      int k;
      if (this.myPolygon != null)
      {
        double d3 = this.myPolygon.getBoundingRect().getWidth() * d1;
        k = (int)Math.round(getBoundingRect().getX() + (this.myPolygon.getBoundingRect().getX() - paramRectangle.getX()) * d1);
        double d4 = this.myPolygon.getBoundingRect().getHeight() * d2;
        double d5 = getBoundingRect().getY() + getBoundingRect().getHeight();
        double d6 = paramRectangle.getY() + paramRectangle.getHeight();
        double d7 = this.myPolygon.getBoundingRect().getY() + this.myPolygon.getBoundingRect().getHeight();
        double d8 = d6 - d7;
        int n = (int)Math.round(d5 - d8 - d4);
        this.myPolygon.setBoundingRect(k, n, (int)Math.round(d3), (int)Math.round(d4));
      }
      int i;
      int j;
      int m;
      if (this.myRectangle1 != null)
      {
        i = getLeft() + (int)Math.rint((this.myRectangle1.getLeft() - paramRectangle.x) * d1);
        j = getTop() + (int)Math.rint((this.myRectangle1.getTop() - paramRectangle.y) * d2);
        k = (int)Math.round(this.myRectangle1.getBoundingRect().getWidth() * d1);
        m = (int)Math.round(this.myRectangle1.getBoundingRect().getHeight() * d2);
        this.myRectangle1.setBoundingRect(i, j, k, m);
      }
      if (this.myRectangle2 != null)
      {
        i = getLeft() + (int)Math.rint((this.myRectangle2.getLeft() - paramRectangle.x) * d1);
        j = getTop() + (int)Math.rint((this.myRectangle2.getTop() - paramRectangle.y) * d2);
        k = (int)Math.round(this.myRectangle2.getBoundingRect().getWidth() * d1);
        m = (int)Math.round(this.myRectangle2.getBoundingRect().getHeight() * d2);
        this.myRectangle2.setBoundingRect(i, j, k, m);
      }
      if (this.myRectangle3 != null)
      {
        i = getLeft() + (int)Math.rint((this.myRectangle3.getLeft() - paramRectangle.x) * d1);
        j = getTop() + (int)Math.rint((this.myRectangle3.getTop() - paramRectangle.y) * d2);
        k = (int)Math.round(this.myRectangle3.getBoundingRect().getWidth() * d1);
        m = (int)Math.round(this.myRectangle3.getBoundingRect().getHeight() * d2);
        this.myRectangle3.setBoundingRect(i, j, k, m);
      }
      this.rectSpacing1 *= d2;
      this.rectSpacing2 *= d2;
      this.rectSpacing3 *= d2;
      this.rectSeparation *= d1;
    }
    layoutChildren();
  }
  
  public Dimension getMinimumSize()
  {
    int i = (int)Math.ceil(this.myLabel.getBoundingRect().getWidth() + 10.0D);
    int j = (int)Math.ceil(this.myLabel.getBoundingRect().getHeight() + 20.0D);
    return new Dimension(i, j);
  }
  
  public double getScaleFactorY(Rectangle paramRectangle)
  {
    double d1 = 1.0D;
    if (paramRectangle.height != 0)
    {
      double d2 = paramRectangle.getY() + paramRectangle.getHeight();
      double d3 = d2 - this.myPolygon.getSpotLocation(6).getY();
      d1 = (getBoundingRect().getHeight() - d3) / (paramRectangle.getHeight() - d3);
    }
    return d1;
  }
  
  public void instantiate()
  {
    Point localPoint1 = getTopLeft();
    localPoint1.translate(50, 50);
    WorkspaceObject localWorkspaceObject = new WorkspaceObject(localPoint1, "W1", this.myLabel.getText() + ":");
    Point localPoint2 = new Point(0, 0);
    localWorkspaceObject.myContentDocument.copyFromCollection(this.myContentDocument, localPoint2, null);
    GCClass localGCClass = getSuperClass();
    localPoint2.translate((int)this.myContentDocument.getDocumentSize().getWidth(), (int)this.myContentDocument.getDocumentSize().getHeight());
    while (localGCClass != null)
    {
      localWorkspaceObject.myContentDocument.copyFromCollection(localGCClass.myContentDocument, localPoint2, null);
      localPoint2.translate((int)localGCClass.myContentDocument.getDocumentSize().getWidth(), (int)localGCClass.myContentDocument.getDocumentSize().getHeight());
      localGCClass = localGCClass.getSuperClass();
    }
    GCDocument localGCDocument = getDocument();
    if (localGCDocument != null)
    {
      localGCDocument.addObjectAtTail(localWorkspaceObject);
      this.instanceVec.add(localWorkspaceObject);
    }
  }
  
  public GCClass getSuperClass()
  {
    Object localObject1 = null;
    int i = 0;
    String str = this.superClass;
    ArrayList localArrayList = Editor.topGrafcharts.getStorage();
    Object localObject3;
    for (int j = str.indexOf('.'); j != -1; j = str.indexOf('.'))
    {
        String localObject2 = str.substring(0, j);
      str = str.substring(j + 1);
      i = 0;
      localObject3 = localArrayList.iterator();
      while ((i == 0) && (((Iterator)localObject3).hasNext()))
      {
        localObject1 = ((Iterator)localObject3).next();
        if ((localObject1 instanceof Referencable))
        {
          Referencable localReferencable = (Referencable)localObject1;
          if (localReferencable.getName().compareTo((String)localObject2) == 0)
          {
            i = 1;
            if ((localReferencable instanceof GCDocument)) {
              localArrayList = ((GCDocument)localReferencable).getSymbolTable();
            }
            if ((localReferencable instanceof WorkspaceObject)) {
              localArrayList = ((WorkspaceObject)localReferencable).getSymbolTable();
            }
          }
        }
      }
    }
    Object localObject2 = localArrayList.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject1 = ((Iterator)localObject2).next();
      if ((localObject1 instanceof Referencable))
      {
        localObject3 = (Referencable)localObject1;
        if ((((Referencable)localObject3).getName().compareTo(str) == 0) && ((localObject3 instanceof GCClass))) {
          return (GCClass)localObject3;
        }
      }
    }
    return null;
  }
  
  public Vector getSubClasses()
  {
    Vector localVector = new Vector();
    Object localObject1 = null;
    String str = getPath();
    ArrayList localArrayList1 = Editor.topGrafcharts.getStorage();
    Iterator localIterator1 = localArrayList1.iterator();
    while (localIterator1.hasNext())
    {
      localObject1 = localIterator1.next();
      if ((localObject1 instanceof GCDocument))
      {
        ArrayList localArrayList2 = ((GCDocument)localObject1).getSymbolTable();
        Iterator localIterator2 = localArrayList2.iterator();
        while (localIterator2.hasNext())
        {
          Object localObject2 = localIterator2.next();
          if ((localObject2 instanceof GCClass))
          {
            GCClass localGCClass = (GCClass)localObject2;
            if (localGCClass.superClass.equals(str)) {
              localVector.add(localGCClass);
            }
          }
        }
      }
    }
    return localVector;
  }
  
  public void updateInstances()
  {
    GCDocument localGCDocument = new GCDocument();
    Point localPoint = new Point(0, 0);
    for (GCClass localGCClass = this; localGCClass != null; localGCClass = localGCClass.getSuperClass())
    {
      localGCDocument.copyFromCollection(localGCClass.myContentDocument, localPoint, null);
      localPoint.translate((int)localGCClass.myContentDocument.getDocumentSize().getWidth(), (int)localGCClass.myContentDocument.getDocumentSize().getHeight());
    }
    Enumeration localEnumeration = this.instanceVec.elements();
    JGoObject localJGoObject;
    while (localEnumeration.hasMoreElements())
    {
      localJGoObject = (JGoObject)localEnumeration.nextElement();
      if ((localJGoObject instanceof WorkspaceObject)) {
        updateRec(localGCDocument, ((WorkspaceObject)localJGoObject).myContentDocument);
      }
    }
    localEnumeration = getSubClasses().elements();
    while (localEnumeration.hasMoreElements())
    {
      localJGoObject = (JGoObject)localEnumeration.nextElement();
      if ((localJGoObject instanceof GCClass)) {
        ((GCClass)localJGoObject).updateInstances();
      }
    }
  }
  
  private void updateRec(GCDocument paramGCDocument1, GCDocument paramGCDocument2)
  {
    copyObjects(paramGCDocument1, paramGCDocument2);
    removeObjects(paramGCDocument1, paramGCDocument2);
    for (JGoListPosition localJGoListPosition = paramGCDocument1.getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = getNextObjectPos(localJGoListPosition))
    {
      JGoObject localJGoObject = getObjectAtPos(localJGoListPosition);
      Referencable localReferencable;
      if ((localJGoObject instanceof WorkspaceObject))
      {
        localReferencable = findObjectByName((Referencable)localJGoObject, paramGCDocument2);
        if ((localReferencable instanceof WorkspaceObject)) {
          updateRec(((WorkspaceObject)localJGoObject).myContentDocument, ((WorkspaceObject)localReferencable).myContentDocument);
        }
      }
      if ((localJGoObject instanceof GrafcetProcedure))
      {
        localReferencable = findObjectByName((Referencable)localJGoObject, paramGCDocument2);
        if ((localReferencable instanceof GrafcetProcedure)) {
          updateRec(((GrafcetProcedure)localJGoObject).myContentDocument, ((GrafcetProcedure)localReferencable).myContentDocument);
        }
      }
    }
  }
  
  private void copyObjects(GCDocument paramGCDocument1, GCDocument paramGCDocument2)
  {
    JGoSelection localJGoSelection = new JGoSelection();
    for (JGoListPosition localJGoListPosition = paramGCDocument1.getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = paramGCDocument1.getNextObjectPos(localJGoListPosition))
    {
      JGoObject localJGoObject = paramGCDocument1.getObjectAtPos(localJGoListPosition);
      if ((localJGoObject instanceof Referencable))
      {
        Referencable localReferencable = findObjectByName((Referencable)localJGoObject, paramGCDocument2);
        if (localReferencable == null)
        {
          localJGoSelection.extendSelection(localJGoObject);
          Utils.writeDebug("added object" + localJGoObject);
        }
      }
    }
    paramGCDocument2.copyFromCollection(localJGoSelection);
  }
  
  private void removeObjects(GCDocument paramGCDocument1, GCDocument paramGCDocument2)
  {
    ArrayList localArrayList = paramGCDocument2.getSymbolTable();
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      JGoObject localJGoObject = (JGoObject)localIterator.next();
      if ((localJGoObject instanceof Referencable))
      {
        Referencable localReferencable = findObjectByName((Referencable)localJGoObject, paramGCDocument1);
        if (localReferencable == null)
        {
          paramGCDocument2.removeObject(localJGoObject);
          Utils.writeDebug("removed object" + localJGoObject);
        }
      }
    }
  }
  
  private Referencable findObjectByName(Referencable paramReferencable, GCDocument paramGCDocument)
  {
    for (JGoListPosition localJGoListPosition = paramGCDocument.getFirstObjectPos(); localJGoListPosition != null; localJGoListPosition = getNextObjectPos(localJGoListPosition))
    {
      JGoObject localJGoObject = getObjectAtPos(localJGoListPosition);
      if (((localJGoObject instanceof Referencable)) && (paramReferencable.getName().equals(((Referencable)localJGoObject).getName())) && (localJGoObject.getClass().getName().equals(paramReferencable.getClass().getName()))) {
        return (Referencable)localJGoObject;
      }
    }
    return null;
  }
  
  public JGoPen getPen()
  {
    return this.myPolygon.getPen();
  }
  
  public void setPen(JGoPen paramJGoPen)
  {
    this.myPolygon.setPen(paramJGoPen);
  }
  
  public JGoBrush getBrush()
  {
    return this.myPolygon.getBrush();
  }
  
  public void setBrush(JGoBrush paramJGoBrush)
  {
    this.myPolygon.setBrush(paramJGoBrush);
  }
  
  public ArrayList getSymbolTable()
  {
    return Utils.collectionToSymbolTable(this.myContentDocument);
  }
  
  public String getPath()
  {
    StringBuffer localStringBuffer = new StringBuffer(this.myLabel.getText());
    GCDocument localGCDocument = getDocument();
    localStringBuffer.insert(0, localGCDocument.getName() + ".");
    for (GrafcetObject localGrafcetObject = localGCDocument.owner; (localGrafcetObject instanceof Referencable); localGrafcetObject = localGCDocument.owner)
    {
      localGCDocument = (GCDocument)localGrafcetObject.getDocument();
      localStringBuffer.insert(0, ((Referencable)localGrafcetObject).getName() + ".");
    }
    return localStringBuffer.toString();
  }
  
  public String getName()
  {
    return this.myLabel.getText();
  }
  
  public void setName(String paramString)
  {
    this.myLabel.setText(paramString);
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
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCClass.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */