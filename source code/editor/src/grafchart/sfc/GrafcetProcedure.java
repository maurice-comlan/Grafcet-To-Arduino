package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.actions.ProcParam;
import grafchart.util.ActionCompiler;
import grafchart.util.ActionCompiler.CompileType;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JInternalFrame;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GrafcetProcedure
  extends GrafcetObject
  implements Readable, CallSource
{
  private double rectangleSpacing = 10.0D;
  public JGoRectangle myRectangle = null;
  public JGoRectangle myRectangle1 = null;
  public JGoRectangle myRectangle2 = null;
  public JGoRectangle myRectangle3 = null;
  public JGoStroke myStroke1 = null;
  public JGoStroke myStroke2 = null;
  public JGoText myName = null;
  public boolean simulation = true;
  public int threadSpeed = 40;
  public GCDocument myContentDocument = null;
  public transient JInternalFrame frame = null;
  public transient GCView parentView = null;
  public transient GCView view = null;
  public Rectangle bounds = null;
  public Point point = new Point(0, 0);
  public int rgbColor;
  public int stepCounterInt = 2;
  public String parameters = "";
  public String defaultParameters = "";
  public SymbolTableObject symbolTableObject = null;
  private ObservableList<GCDocument> procedureCalls = null;
  public transient GCView viewReference = null;
  public transient ProcParam paramNode = null;
  
  public GrafcetProcedure() {}
  
  public GrafcetProcedure(Point paramPoint, String paramString)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myRectangle = new JGoRectangle();
    this.myRectangle.setSize(60, 60);
    this.myRectangle.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myRectangle.setSelectable(false);
    this.myRectangle.setDraggable(false);
    this.myRectangle1 = new JGoRectangle(new Rectangle(7, 7));
    this.myRectangle1.setSelectable(false);
    this.myRectangle1.setDraggable(false);
    this.myRectangle2 = new JGoRectangle(new Rectangle(7, 7));
    this.myRectangle2.setSelectable(false);
    this.myRectangle2.setDraggable(false);
    this.myRectangle3 = new JGoRectangle(new Rectangle(7, 7));
    this.myRectangle3.setSelectable(false);
    this.myRectangle3.setDraggable(false);
    this.myStroke1 = new JGoStroke();
    this.myStroke1.addPoint(0, 0);
    this.myStroke1.addPoint(0, 7);
    this.myStroke1.setSelectable(false);
    this.myStroke1.setDraggable(false);
    this.myStroke2 = new JGoStroke();
    this.myStroke2.addPoint(0, 0);
    this.myStroke2.addPoint(0, 7);
    this.myStroke2.setSelectable(false);
    this.myStroke2.setDraggable(false);
    this.myName = new JGoText(paramString);
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myRectangle1);
    addObjectAtTail(this.myRectangle2);
    addObjectAtTail(this.myRectangle3);
    addObjectAtTail(this.myStroke1);
    addObjectAtTail(this.myStroke2);
    addObjectAtTail(this.myName);
    this.myContentDocument = new GCDocument(this);
    this.procedureCalls = new ObservableList();
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    GrafcetProcedure localGrafcetProcedure = (GrafcetProcedure)paramJGoArea;
    localGrafcetProcedure.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localGrafcetProcedure.myRectangle1 = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle1));
    localGrafcetProcedure.myRectangle2 = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle2));
    localGrafcetProcedure.myRectangle3 = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle3));
    localGrafcetProcedure.myStroke1 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myStroke1));
    localGrafcetProcedure.myStroke2 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myStroke2));
    localGrafcetProcedure.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localGrafcetProcedure.addObjectAtHead(localGrafcetProcedure.myRectangle);
    localGrafcetProcedure.addObjectAtTail(localGrafcetProcedure.myRectangle1);
    localGrafcetProcedure.addObjectAtTail(localGrafcetProcedure.myRectangle2);
    localGrafcetProcedure.addObjectAtTail(localGrafcetProcedure.myRectangle3);
    localGrafcetProcedure.addObjectAtTail(localGrafcetProcedure.myStroke1);
    localGrafcetProcedure.addObjectAtTail(localGrafcetProcedure.myStroke2);
    localGrafcetProcedure.addObjectAtTail(localGrafcetProcedure.myName);
    localGrafcetProcedure.myContentDocument = new GCDocument(localGrafcetProcedure);
    localGrafcetProcedure.myContentDocument.copyFromCollection(this.myContentDocument);
    localGrafcetProcedure.procedureCalls = new ObservableList();
    localGrafcetProcedure.rectangleSpacing = this.rectangleSpacing;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", getName());
    XMLUtil.setInt(paramElement, "threadSpeed", this.threadSpeed);
    XMLUtil.setBool(paramElement, "simulationMode", this.simulation);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    this.myContentDocument.storeXMLRec(paramElement, this.bounds, this.point);
    return paramElement;
  }
  
  public static GrafcetProcedure loadXML(Element paramElement)
  {
    GrafcetProcedure localGrafcetProcedure = new GrafcetProcedure(new Point(), paramElement.getAttribute("name"));
    localGrafcetProcedure.threadSpeed = XMLUtil.getInt(paramElement, "threadSpeed", 40);
    localGrafcetProcedure.simulation = XMLUtil.getBool(paramElement, "simulationMode", true);
    localGrafcetProcedure.removeObject(localGrafcetProcedure.myName);
    XMLUtil.restoreBoundingRectAny(paramElement, localGrafcetProcedure);
    localGrafcetProcedure.addObjectAtTail(localGrafcetProcedure.myName);
    NodeList localNodeList = paramElement.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        Element localElement = (Element)localNode;
        if (localElement.getTagName().equals(GCDocument.documentTag))
        {
          localGrafcetProcedure.bounds = XMLUtil.getWorkspaceBoundingRect(localElement);
          localGrafcetProcedure.myContentDocument.currentScale = XMLUtil.getDouble(localElement, "scale", 1.0D);
          localGrafcetProcedure.point = XMLUtil.getViewPosition(localElement);
          localGrafcetProcedure.rgbColor = XMLUtil.getInt(localElement, "color", -1);
          localGrafcetProcedure.myContentDocument.setPaperColor(new Color(localGrafcetProcedure.rgbColor));
          localGrafcetProcedure.myContentDocument.loadXMLRec(localElement);
        }
      }
    }
    return localGrafcetProcedure;
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
    Point localPoint = this.myRectangle.getSpotLocation(2);
    this.myRectangle1.setSpotLocation(2, (int)localPoint.getX(), (int)Math.round(localPoint.getY() + this.rectangleSpacing));
    this.myStroke1.setSpotLocation(2, this.myRectangle1, 6);
    this.myRectangle2.setSpotLocation(2, this.myStroke1, 6);
    this.myStroke2.setSpotLocation(2, this.myRectangle2, 6);
    this.myRectangle3.setSpotLocation(2, this.myStroke2, 6);
    localPoint = this.myRectangle.getSpotLocation(6);
    this.myName.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 10);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle, this.myRectangle1, this.myRectangle2, this.myRectangle3, this.myStroke1, this.myStroke2 };
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width != getWidth()) || (paramRectangle.height != getHeight())) {
      this.rectangleSpacing *= getScaleFactorY(paramRectangle);
    }
    super.geometryChange(paramRectangle);
  }
  
  public int getNoScaleBottom(Rectangle paramRectangle)
  {
    return paramRectangle.y + paramRectangle.height - this.myRectangle.getSpotLocation(6).y;
  }
  
  public Dimension getMinimumSize()
  {
    int i = (int)Math.ceil(this.myName.getBoundingRect().getWidth() + 10.0D);
    i = Math.min(i, 60);
    int j = (int)Math.ceil(this.myName.getBoundingRect().getHeight() + 20.0D);
    return new Dimension(i, j);
  }
  
  public String getDefaultParameters()
  {
    String str = "";
    GCDocument localGCDocument = this.myContentDocument;
    JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos();
    for (JGoObject localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
    {
      Object localObject;
      if ((localJGoObject instanceof BooleanVariable))
      {
        localObject = (BooleanVariable)localJGoObject;
        str = str + "V " + ((BooleanVariable)localObject).getName() + " = " + ((BooleanVariable)localObject).getIntVal() + ";\n";
      }
      if ((localJGoObject instanceof IntegerVariable))
      {
        localObject = (IntegerVariable)localJGoObject;
        str = str + "V " + ((IntegerVariable)localObject).getName() + " = " + ((IntegerVariable)localObject).getIntVal() + ";\n";
      }
      if ((localJGoObject instanceof RealVariable))
      {
        localObject = (RealVariable)localJGoObject;
        str = str + "V " + ((RealVariable)localObject).getName() + " = " + ((RealVariable)localObject).getRealVal() + ";\n";
      }
      if ((localJGoObject instanceof StringVariable))
      {
        localObject = (StringVariable)localJGoObject;
        str = str + "V " + ((StringVariable)localObject).getName() + " = \"" + ((StringVariable)localObject).getStringVal() + "\";\n";
      }
      localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
    }
    return str;
  }
  
  public synchronized void start(String paramString, SymbolTableObject paramSymbolTableObject)
  {
    SymbolTableObject localSymbolTableObject = this.symbolTableObject;
    this.symbolTableObject = paramSymbolTableObject;
    String str = this.parameters;
    this.parameters = paramString;
    ProcParam localProcParam = this.paramNode;
    startProcedure(false);
    this.symbolTableObject = localSymbolTableObject;
    this.parameters = str;
    this.paramNode = localProcParam;
  }
  
  public synchronized void startProcedure(boolean paramBoolean)
  {
    GCDocument localGCDocument = new GCDocument(this);
    localGCDocument.setSkipsUndoManager(true);
    GCLink.setLinkModification(false);
    localGCDocument.copyFromCollection(this.myContentDocument);
    GCLink.setLinkModification(true);
    localGCDocument.setPaperColor(this.myContentDocument.getPaperColor());
    Object localObject1;
    Object localObject2;
    Object localObject3;
    if (!paramBoolean)
    {
      localGCDocument.owner = null;
      localObject1 = (GCDocument)this.symbolTableObject;
      localObject2 = ((GCDocument)localObject1).getFirstObjectPos();
      for (localObject3 = ((GCDocument)localObject1).getObjectAtPos((JGoListPosition)localObject2); localGCDocument.owner == null; localObject3 = localGCDocument.getObjectAtPos((JGoListPosition)localObject2))
      {
        if ((localObject3 instanceof GrafcetObject)) {
          localGCDocument.owner = ((GrafcetObject)localObject3);
        }
        localObject2 = localGCDocument.getNextObjectPos((JGoListPosition)localObject2);
      }
    }
    Editor.printWarnings = true;
    this.paramNode = ((ProcParam)ActionCompiler.compile(this.parameters, localGCDocument, ActionCompiler.CompileType.ProcParam, "procedure parameters in \"" + getFullName() + "\"", null));
    Editor.printWarnings = false;
    if (!paramBoolean) {
      localGCDocument.owner = this;
    }
    if (this.paramNode != null)
    {
      this.paramNode.initParameters();
      Editor.singleton.compileDocument(localGCDocument, false);
      localGCDocument.setProcedure(this);
      localGCDocument.setName(getName());
      this.procedureCalls.add(localGCDocument);
      localGCDocument.initializeDocument(localGCDocument, localGCDocument.isSimulating());
      localGCDocument.setSpeed(this.threadSpeed);
      localObject1 = localGCDocument.getFirstObjectPos();
      for (localObject2 = localGCDocument.getObjectAtPos((JGoListPosition)localObject1); (localObject2 != null) && (localObject1 != null); localObject2 = localGCDocument.getObjectAtPos((JGoListPosition)localObject1))
      {
        if ((localObject2 instanceof EnterStep))
        {
          localObject3 = (EnterStep)localObject2;
          ((EnterStep)localObject3).activate();
        }
        if (((localObject2 instanceof MacroStep)) && (!(localObject2 instanceof ProcedureStep)))
        {
          localObject3 = (MacroStep)localObject2;
          ((MacroStep)localObject3).myContentDocument.switchUndoManager(true);
        }
        if ((localObject2 instanceof WorkspaceObject))
        {
          localObject3 = (WorkspaceObject)localObject2;
          ((WorkspaceObject)localObject3).myContentDocument.switchUndoManager(true);
        }
        localObject1 = localGCDocument.getNextObjectPos((JGoListPosition)localObject1);
      }
      localGCDocument.simulation = this.simulation;
      localGCDocument.terminateWhenReady = true;
      localGCDocument.start();
    }
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
    return "";
  }
  
  public String getOldStringVal()
  {
    return "";
  }
  
  public ObservableList<GCDocument> getCalls()
  {
    return this.procedureCalls;
  }
  
  public Rectangle getBounds()
  {
    return this.bounds;
  }
  
  public int getColor()
  {
    return this.rgbColor;
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_Procedure";
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GrafcetProcedure.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */