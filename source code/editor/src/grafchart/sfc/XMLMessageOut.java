package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoText;
import grafchart.graphics.MyJDesktopPane;
import grafchart.util.XMLUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLMessageOut
  extends GrafcetObject
  implements Readable, Writable, Hierarchical, SymbolTableObject
{
  protected double rectSeparation = 5.0D;
  private double rectSpacing1 = 10.0D;
  private double rectSpacing2 = 25.0D;
  private double rectSpacing3 = 40.0D;
  public JGoRectangle myRectangle = null;
  public JGoText myName = null;
  public JGoText myXML = null;
  public GCDocument myContentDocument = null;
  public transient JInternalFrame frame = null;
  public transient GCView parentView = null;
  public transient GCView view = null;
  public Rectangle bounds = null;
  private Point point = new Point(0, 0);
  public int rgbColor;
  public int stepCounterInt = 2;
  protected String identifier = "";
  public boolean publish = true;
  public String destination = "";
  public String type = "";
  public String mustReply = "";
  public String origin = Editor.getLoginName();
  public int handle = 0;
  public int sendHandle = 0;
  
  public XMLMessageOut() {}
  
  public XMLMessageOut(Point paramPoint, String paramString)
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
    this.myXML = new JGoText("XML-out");
    this.myXML.setBold(true);
    this.myXML.setSelectable(false);
    this.myXML.setEditable(false);
    this.myXML.setEditOnSingleClick(false);
    this.myXML.setDraggable(false);
    this.myXML.setAlignment(2);
    this.myXML.setTransparent(true);
    this.myName = new JGoText(paramString);
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myXML);
    addObjectAtTail(this.myName);
    this.myContentDocument = new GCDocument(this);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    XMLMessageOut localXMLMessageOut = (XMLMessageOut)paramJGoArea;
    localXMLMessageOut.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localXMLMessageOut.myXML = ((JGoText)paramJGoCopyEnvironment.copy(this.myXML));
    localXMLMessageOut.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localXMLMessageOut.addObjectAtHead(localXMLMessageOut.myRectangle);
    localXMLMessageOut.addObjectAtTail(localXMLMessageOut.myXML);
    localXMLMessageOut.addObjectAtTail(localXMLMessageOut.myName);
    localXMLMessageOut.myContentDocument = new GCDocument(localXMLMessageOut);
    localXMLMessageOut.myContentDocument.copyFromCollection(this.myContentDocument);
    localXMLMessageOut.rectSeparation = this.rectSeparation;
    localXMLMessageOut.rectSpacing1 = this.rectSpacing1;
    localXMLMessageOut.rectSpacing2 = this.rectSpacing2;
    localXMLMessageOut.rectSpacing3 = this.rectSpacing3;
    localXMLMessageOut.bounds = this.bounds;
    localXMLMessageOut.myContentDocument.currentScale = this.myContentDocument.currentScale;
    localXMLMessageOut.point = this.point;
    localXMLMessageOut.rgbColor = this.rgbColor;
    localXMLMessageOut.identifier = this.identifier;
    localXMLMessageOut.publish = this.publish;
    localXMLMessageOut.destination = this.destination;
    localXMLMessageOut.type = this.type;
    localXMLMessageOut.mustReply = this.mustReply;
    localXMLMessageOut.origin = this.origin;
    localXMLMessageOut.handle = this.handle;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("identifier", this.identifier);
    XMLUtil.setBool(paramElement, "publish", this.publish);
    paramElement.setAttribute("destination", this.destination);
    paramElement.setAttribute("type", this.type);
    paramElement.setAttribute("origin", this.origin);
    paramElement.setAttribute("mustReply", this.mustReply);
    paramElement.setAttribute("handle", "" + this.handle);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    this.myContentDocument.storeXMLRec(paramElement, this.bounds, this.point);
    return paramElement;
  }
  
  public static XMLMessageOut loadXML(Element paramElement)
  {
    String str = paramElement.getAttribute("name");
    XMLMessageOut localXMLMessageOut = new XMLMessageOut(new Point(), str);
    localXMLMessageOut.identifier = paramElement.getAttribute("identifier");
    localXMLMessageOut.publish = XMLUtil.getBool(paramElement, "publish", true);
    localXMLMessageOut.destination = paramElement.getAttribute("destination");
    localXMLMessageOut.type = paramElement.getAttribute("type");
    localXMLMessageOut.origin = paramElement.getAttribute("origin");
    localXMLMessageOut.mustReply = paramElement.getAttribute("mustReply");
    localXMLMessageOut.handle = Utils.parseInt(paramElement.getAttribute("handle"));
    localXMLMessageOut.removeObject(localXMLMessageOut.myName);
    XMLUtil.restoreBoundingRectAny(paramElement, localXMLMessageOut);
    localXMLMessageOut.addObjectAtTail(localXMLMessageOut.myName);
    NodeList localNodeList = paramElement.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        Element localElement = (Element)localNode;
        if (localElement.getTagName().equals(GCDocument.documentTag))
        {
          localXMLMessageOut.bounds = XMLUtil.getWorkspaceBoundingRect(localElement);
          localXMLMessageOut.myContentDocument.currentScale = XMLUtil.getDouble(localElement, "scale", 1.0D);
          localXMLMessageOut.point = XMLUtil.getViewPosition(localElement);
          localXMLMessageOut.rgbColor = XMLUtil.getInt(localElement, "color", -1);
          localXMLMessageOut.myContentDocument.setPaperColor(new Color(localXMLMessageOut.rgbColor));
          localXMLMessageOut.myContentDocument.loadXMLRec(localElement);
        }
      }
    }
    return localXMLMessageOut;
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
    this.myXML.setSpotLocation(0, this.myRectangle.getSpotLocation(0));
    localPoint = this.myRectangle.getSpotLocation(6);
    this.myName.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 10);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle };
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width != getWidth()) || (paramRectangle.height != getHeight()))
    {
      double d = getScaleFactorY(paramRectangle);
      this.rectSpacing1 *= d;
      this.rectSpacing2 *= d;
      this.rectSpacing3 *= d;
      this.rectSeparation *= getScaleFactorX(paramRectangle);
    }
    super.geometryChange(paramRectangle);
  }
  
  public Dimension getMinimumSize()
  {
    int i = (int)Math.ceil(this.myName.getBoundingRect().getWidth() + 10.0D);
    i = Math.min(i, 60);
    int j = (int)Math.ceil(this.myName.getBoundingRect().getHeight() + 20.0D);
    return new Dimension(i, j);
  }
  
  public int getNoScaleBottom(Rectangle paramRectangle)
  {
    return paramRectangle.y + paramRectangle.height - this.myRectangle.getSpotLocation(6).y;
  }
  
  public ArrayList<Referencable> getSymbolTable()
  {
    return Utils.collectionToSymbolTable(this.myContentDocument);
  }
  
  public void setWorkspaceColor(int paramInt)
  {
    this.myContentDocument.setPaperColor(new Color(paramInt));
  }
  
  public int getWorkspaceColor()
  {
    Color localColor = this.myContentDocument.getPaperColor();
    return localColor.getRGB();
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
    return new String("");
  }
  
  public String getOldStringVal()
  {
    return new String("");
  }
  
  public void setPublish(boolean paramBoolean)
  {
    this.publish = paramBoolean;
  }
  
  public boolean getPublish()
  {
    return this.publish;
  }
  
  public String getDestination()
  {
    return this.destination;
  }
  
  public void setDestination(String paramString)
  {
    this.destination = paramString;
  }
  
  public String getTopic()
  {
    return getDestination();
  }
  
  public void setTopic(String paramString)
  {
    setDestination(paramString);
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String paramString)
  {
    this.type = paramString;
  }
  
  public String getOrigin()
  {
    return this.origin;
  }
  
  public void setOrigin(String paramString)
  {
    this.origin = paramString;
  }
  
  public String getMustReply()
  {
    return this.mustReply;
  }
  
  public void setMustReply(String paramString)
  {
    this.mustReply = paramString;
  }
  
  public int getHandle()
  {
    return this.handle;
  }
  
  public void setHandle(int paramInt)
  {
    this.handle = paramInt;
  }
  
  public boolean hasFrame()
  {
    return this.frame != null;
  }
  
  public boolean isShowing()
  {
    return (this.frame != null) && (this.frame.isShowing());
  }
  
  public void showWorkspace()
  {
    if (!hasFrame())
    {
      this.myContentDocument.setName(this.myName.getText());
      JInternalFrame localJInternalFrame = new JInternalFrame(this.myName.getText(), true, true, true, true);
      localJInternalFrame.setDefaultCloseOperation(2);
      final GCView localGCView = new GCView(this.myContentDocument);
      this.myContentDocument.setView(localGCView);
      localGCView.setStepCounter(this.stepCounterInt);
      localGCView.initialize(Editor.singleton, localJInternalFrame);
      localGCView.layer = (Editor.myCurrentView.layer + 1);
      final XMLMessageOut localXMLMessageOut = this;
      this.myContentDocument.setPaperColor(new Color(255, 255, 255));
      localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
      {
        public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent)
        {
          Editor.myCurrentObject = localXMLMessageOut;
          Editor.myCurrentView = localGCView;
          Editor.topLevelView = false;
          Editor.compilationView = true;
          localGCView.requestFocus();
          AppAction.updateAllActions();
        }
        
        public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
        
        public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
        
        public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
        
        public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent)
        {
          Editor.myCurrentObject = null;
          XMLMessageOut.this.rgbColor = XMLMessageOut.this.myContentDocument.getPaperColor().getRGB();
          XMLMessageOut.this.bounds = XMLMessageOut.this.frame.getBounds();
          XMLMessageOut.this.myContentDocument.currentScale = XMLMessageOut.this.view.getScale();
          XMLMessageOut.this.point = XMLMessageOut.this.view.getViewPosition();
          XMLMessageOut.this.stepCounterInt = XMLMessageOut.this.view.getStepCounter();
          Editor.myCurrentView = XMLMessageOut.this.parentView;
          XMLMessageOut.this.frame = null;
          XMLMessageOut.this.view = null;
          AppAction.updateAllActions();
        }
        
        public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
        
        public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
      });
      this.parentView = Editor.myCurrentView;
      Container localContainer = localJInternalFrame.getContentPane();
      localContainer.setLayout(new BorderLayout());
      localContainer.add(localGCView);
      Editor.getDesktop().add(localJInternalFrame, new Integer(localGCView.layer));
      if (this.bounds == null)
      {
        localJInternalFrame.setSize(400, 600);
        Point localPoint = getLocation();
        Editor.myCurrentView.convertDocToView(localPoint);
        localJInternalFrame.setLocation((int)localPoint.getX() + 10, (int)localPoint.getY() + 40);
      }
      else
      {
        this.myContentDocument.setPaperColor(new Color(this.rgbColor));
        localGCView.setScale(this.myContentDocument.currentScale);
        if (this.point != null) {
          localGCView.setViewPosition(this.point);
        }
        localJInternalFrame.setBounds(this.bounds);
      }
      localJInternalFrame.show();
      localGCView.initializeDragDropHandling();
      Editor.myCurrentView = localGCView;
      this.frame = localJInternalFrame;
      this.view = localGCView;
      this.view.setDragDropEnabled(!this.myContentDocument.executing);
      Editor.topLevelView = false;
      localGCView.requestFocus();
      this.myContentDocument.setModifiable(this.myContentDocument.modificationProperty);
      this.frame.setResizable(this.myContentDocument.modificationProperty);
      this.frame.setMaximizable(this.myContentDocument.modificationProperty);
      if (this.myContentDocument.modificationProperty) {
        this.frame.setFrameIcon(GCDocument.defaultIcon);
      } else {
        this.frame.setFrameIcon(GCDocument.lockIcon);
      }
      if (this.myContentDocument.executing) {
        this.frame.setFrameIcon(GCDocument.runIcon);
      }
      this.myContentDocument.setView(this.view);
      AppAction.updateAllActions();
    }
  }
  
  public void showWorkspace(int paramInt1, int paramInt2)
  {
    if (!hasFrame())
    {
      this.myContentDocument.setName(this.myName.getText());
      JInternalFrame localJInternalFrame = new JInternalFrame(this.myName.getText(), true, true, true, true);
      localJInternalFrame.setDefaultCloseOperation(2);
      final GCView localGCView = new GCView(this.myContentDocument);
      this.myContentDocument.setView(localGCView);
      localGCView.setStepCounter(this.stepCounterInt);
      localGCView.initialize(Editor.singleton, localJInternalFrame);
      localGCView.layer = (Editor.myCurrentView.layer + 1);
      final XMLMessageOut localXMLMessageOut = this;
      this.myContentDocument.setPaperColor(new Color(255, 255, 255));
      localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
      {
        public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent)
        {
          Editor.myCurrentObject = localXMLMessageOut;
          Editor.myCurrentView = localGCView;
          Editor.topLevelView = false;
          Editor.compilationView = true;
          localGCView.requestFocus();
          AppAction.updateAllActions();
        }
        
        public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
        
        public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
        
        public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
        
        public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent)
        {
          Editor.myCurrentObject = null;
          XMLMessageOut.this.rgbColor = XMLMessageOut.this.myContentDocument.getPaperColor().getRGB();
          XMLMessageOut.this.bounds = XMLMessageOut.this.frame.getBounds();
          XMLMessageOut.this.myContentDocument.currentScale = XMLMessageOut.this.view.getScale();
          XMLMessageOut.this.point = XMLMessageOut.this.view.getViewPosition();
          XMLMessageOut.this.stepCounterInt = XMLMessageOut.this.view.getStepCounter();
          Editor.myCurrentView = XMLMessageOut.this.parentView;
          XMLMessageOut.this.frame = null;
          XMLMessageOut.this.view = null;
          AppAction.updateAllActions();
        }
        
        public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
        
        public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
      });
      this.parentView = Editor.myCurrentView;
      Container localContainer = localJInternalFrame.getContentPane();
      localContainer.setLayout(new BorderLayout());
      localContainer.add(localGCView);
      Editor.getDesktop().add(localJInternalFrame, new Integer(localGCView.layer));
      if (this.bounds == null)
      {
        localJInternalFrame.setSize(400, 600);
        Point localPoint = getLocation();
        Editor.myCurrentView.convertDocToView(localPoint);
        localJInternalFrame.setLocation((int)localPoint.getX() + 10, (int)localPoint.getY() + 40);
      }
      else
      {
        this.myContentDocument.setPaperColor(new Color(this.rgbColor));
        localGCView.setScale(this.myContentDocument.currentScale);
        if (this.point != null) {
          localGCView.setViewPosition(this.point);
        }
        localJInternalFrame.setBounds(paramInt1, paramInt2, this.bounds.width, this.bounds.height);
      }
      localJInternalFrame.show();
      localGCView.initializeDragDropHandling();
      Editor.myCurrentView = localGCView;
      this.frame = localJInternalFrame;
      this.view = localGCView;
      this.view.setDragDropEnabled(!this.myContentDocument.executing);
      Editor.topLevelView = false;
      localGCView.requestFocus();
      this.myContentDocument.setModifiable(this.myContentDocument.modificationProperty);
      this.frame.setResizable(this.myContentDocument.modificationProperty);
      this.frame.setMaximizable(this.myContentDocument.modificationProperty);
      if (this.myContentDocument.modificationProperty) {
        this.frame.setFrameIcon(GCDocument.defaultIcon);
      } else {
        this.frame.setFrameIcon(GCDocument.lockIcon);
      }
      if (this.myContentDocument.executing) {
        this.frame.setFrameIcon(GCDocument.runIcon);
      }
      this.myContentDocument.setView(this.view);
      AppAction.updateAllActions();
    }
  }
  
  public void showWorkspaceLite()
  {
    if (this.frame != null)
    {
      if (this.frame.isIcon()) {
        try
        {
          this.frame.setIcon(false);
        }
        catch (Exception localException) {}
      }
      this.frame.show();
      this.view.requestFocus();
    }
  }
  
  public void hideWorkspace()
  {
    if ((hasFrame()) && (isShowing()))
    {
      this.rgbColor = this.myContentDocument.getPaperColor().getRGB();
      this.bounds = this.frame.getBounds();
      this.myContentDocument.currentScale = this.view.getScale();
      this.point = this.view.getViewPosition();
      this.stepCounterInt = this.view.getStepCounter();
      try
      {
        this.frame.setClosed(true);
      }
      catch (Exception localException) {}
      this.frame = null;
      this.view = null;
      Editor.myCurrentView = this.parentView;
      Editor.myCurrentView.requestFocus();
      AppAction.updateAllActions();
    }
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
  
  public void setStoredBoolAction(boolean paramBoolean) {}
  
  public void setStoredIntAction(int paramInt) {}
  
  public void setStoredStringAction(String paramString) {}
  
  public void setStoredRealAction(double paramDouble) {}
  
  public GCDocument getContentDocument()
  {
    return this.myContentDocument;
  }
  
  public void assignWorkspace(Hierarchical paramHierarchical)
  {
    GCDocument localGCDocument = paramHierarchical.getContentDocument();
    JGoListPosition localJGoListPosition1 = localGCDocument.getFirstObjectPos();
    for (JGoObject localJGoObject1 = localGCDocument.getObjectAtPos(localJGoListPosition1); (localJGoObject1 != null) && (localJGoListPosition1 != null); localJGoObject1 = localGCDocument.getObjectAtPos(localJGoListPosition1))
    {
      if ((localJGoObject1 instanceof InternalVariable)) {
        assignInternalVariable((InternalVariable)localJGoObject1);
      }
      if ((localJGoObject1 instanceof Hierarchical))
      {
        Hierarchical localHierarchical1 = (Hierarchical)localJGoObject1;
        JGoListPosition localJGoListPosition2 = this.myContentDocument.getFirstObjectPos();
        for (JGoObject localJGoObject2 = this.myContentDocument.getObjectAtPos(localJGoListPosition2); (localJGoObject2 != null) && (localJGoListPosition2 != null); localJGoObject2 = this.myContentDocument.getObjectAtPos(localJGoListPosition2))
        {
          if ((localJGoObject2 instanceof Hierarchical))
          {
            Hierarchical localHierarchical2 = (Hierarchical)localJGoObject2;
            if (localHierarchical2.getName().equals(localHierarchical1.getName())) {
              ((Hierarchical)localJGoObject2).assignWorkspace(localHierarchical1);
            }
          }
          localJGoListPosition2 = this.myContentDocument.getNextObjectPos(localJGoListPosition2);
        }
      }
      localJGoListPosition1 = localGCDocument.getNextObjectPos(localJGoListPosition1);
    }
  }
  
  private void assignInternalVariable(InternalVariable paramInternalVariable)
  {
    JGoListPosition localJGoListPosition = this.myContentDocument.getFirstObjectPos();
    for (JGoObject localJGoObject = this.myContentDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = this.myContentDocument.getObjectAtPos(localJGoListPosition))
    {
      if ((localJGoObject instanceof InternalVariable))
      {
        InternalVariable localInternalVariable = (InternalVariable)localJGoObject;
        if (localInternalVariable.getName().equals(paramInternalVariable.getName()))
        {
          if (((paramInternalVariable instanceof IntegerVariable)) && ((localInternalVariable instanceof IntegerVariable))) {
            ((IntegerVariable)localInternalVariable).setStoredIntAction(((IntegerVariable)paramInternalVariable).getIntVal());
          }
          if (((paramInternalVariable instanceof RealVariable)) && ((localInternalVariable instanceof RealVariable))) {
            ((RealVariable)localInternalVariable).setStoredRealAction(((RealVariable)paramInternalVariable).getRealVal());
          }
          if (((paramInternalVariable instanceof BooleanVariable)) && ((localInternalVariable instanceof BooleanVariable))) {
            ((BooleanVariable)localInternalVariable).setStoredBoolAction(((BooleanVariable)paramInternalVariable).getBoolVal());
          }
          if (((paramInternalVariable instanceof StringVariable)) && ((localInternalVariable instanceof StringVariable))) {
            ((StringVariable)localInternalVariable).setStoredStringAction(((StringVariable)paramInternalVariable).getStringVal());
          }
        }
      }
      localJGoListPosition = this.myContentDocument.getNextObjectPos(localJGoListPosition);
    }
  }
  
  private String getXMLIdentifier()
  {
    return getXMLIdentifier(true);
  }
  
  public String getXMLIdentifier(boolean paramBoolean)
  {
    if ((this.identifier.isEmpty()) && (paramBoolean)) {
      return getName();
    }
    return this.identifier;
  }
  
  public void setXMLIdentifier(String paramString)
  {
    this.identifier = paramString;
  }
  
  public String getXML()
  {
    return generateXMLStructure();
  }
  
  public void publishMessage()
  {
    Editor.publishMessage(this.destination, generateXMLStructure(), this.handle, Editor.loginName, this.mustReply, this.type);
  }
  
  public void sendMessage()
  {
    int i = Editor.sendMessage(this.destination, generateXMLStructure(), this.handle, Editor.loginName, this.mustReply, this.type);
    if (this.handle == 0) {
      this.sendHandle = i;
    }
  }
  
  public void outputMessage()
  {
    if (this.publish) {
      Editor.publishMessage(this.destination, generateXMLStructure(), this.handle, Editor.loginName, this.mustReply, this.type);
    } else {
      sendMessage();
    }
  }
  
  public int getSendHandle()
  {
    return this.sendHandle;
  }
  
  private String generateXMLStructure()
  {
    return generateXMLElement(getXMLIdentifier(), this.myContentDocument);
  }
  
  private String generateXMLElement(String paramString, GCDocument paramGCDocument)
  {
    return "<" + paramString + generateXMLAttributeContents(paramGCDocument) + ">" + generateXMLElementContents(paramGCDocument) + "</" + paramString + ">";
  }
  
  private String generateXMLAttributeContents(GCDocument paramGCDocument)
  {
    String str = new String();
    GCDocument localGCDocument = paramGCDocument;
    JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos();
    for (JGoObject localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
    {
      Object localObject;
      if ((localJGoObject instanceof IntegerAttributeVariable))
      {
        localObject = (IntegerAttributeVariable)localJGoObject;
        str = str + " " + ((IntegerAttributeVariable)localObject).getName() + " = \"" + ((IntegerAttributeVariable)localObject).getIntVal() + "\"";
      }
      if ((localJGoObject instanceof RealAttributeVariable))
      {
        localObject = (RealAttributeVariable)localJGoObject;
        str = str + " " + ((RealAttributeVariable)localObject).getName() + " = \"" + ((RealAttributeVariable)localObject).getRealVal() + "\"";
      }
      if ((localJGoObject instanceof StringAttributeVariable))
      {
        localObject = (StringAttributeVariable)localJGoObject;
        str = str + " " + ((StringAttributeVariable)localObject).getName() + " = \"" + ((StringAttributeVariable)localObject).getStringVal() + "\"";
      }
      localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
    }
    return str;
  }
  
  private String generateXMLElementContents(GCDocument paramGCDocument)
  {
    GCDocument localGCDocument = paramGCDocument;
    ArrayList localArrayList = new ArrayList();
    JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos();
    for (JGoObject localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
    {
      if ((localJGoObject instanceof InternalVariable)) {
        localArrayList.add(localJGoObject);
      }
      if ((localJGoObject instanceof WorkspaceObject)) {
        localArrayList.add(localJGoObject);
      }
      localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
    }
    Collections.sort(localArrayList, new Comparator()
    {
      public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        return (int)(((JGoObject)paramAnonymousObject1).getLocation().getY() - ((JGoObject)paramAnonymousObject2).getLocation().getY());
      }
    });
    String str1 = new String();
    String str2 = "999999";
    int i = 0;
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2;
      if ((localObject1 instanceof IndexVariable))
      {
        localObject2 = (IndexVariable)localObject1;
        i = ((IndexVariable)localObject2).getIntVal();
        str2 = ((IndexVariable)localObject2).getName();
      }
      String str3;
      if (((localObject1 instanceof IntegerVariable)) && (!(localObject1 instanceof IndexVariable)) && (!(localObject1 instanceof IntegerAttributeVariable)))
      {
        localObject2 = (IntegerVariable)localObject1;
        str3 = ((IntegerVariable)localObject2).getName();
        if (arrayMatch(str2, str3))
        {
          str3 = str2;
          if (i > 0)
          {
            i--;
            str1 = str1 + "<" + str3 + ">" + ((IntegerVariable)localObject2).getIntVal() + "</" + str3 + ">";
          }
        }
        else
        {
          str1 = str1 + "<" + str3 + ">" + ((IntegerVariable)localObject2).getIntVal() + "</" + str3 + ">";
        }
      }
      if (((localObject1 instanceof RealVariable)) && (!(localObject1 instanceof RealAttributeVariable)))
      {
        localObject2 = (RealVariable)localObject1;
        str3 = ((RealVariable)localObject2).getName();
        if (arrayMatch(str2, str3))
        {
          str3 = str2;
          if (i > 0)
          {
            i--;
            str1 = str1 + "<" + str3 + ">" + ((RealVariable)localObject2).getRealVal() + "</" + str3 + ">";
          }
        }
        else
        {
          str1 = str1 + "<" + str3 + ">" + ((RealVariable)localObject2).getRealVal() + "</" + str3 + ">";
        }
      }
      if (((localObject1 instanceof StringVariable)) && (!(localObject1 instanceof StringAttributeVariable)))
      {
        localObject2 = (StringVariable)localObject1;
        str3 = ((StringVariable)localObject2).getName();
        if (arrayMatch(str2, str3))
        {
          str3 = str2;
          if (i > 0)
          {
            i--;
            str1 = str1 + "<" + str3 + ">" + ((StringVariable)localObject2).getStringVal() + "</" + str3 + ">";
          }
        }
        else
        {
          str1 = str1 + "<" + str3 + ">" + ((StringVariable)localObject2).getStringVal() + "</" + str3 + ">";
        }
      }
      if ((localObject1 instanceof BooleanVariable))
      {
        localObject2 = (BooleanVariable)localObject1;
        str3 = ((BooleanVariable)localObject2).getName();
        if (arrayMatch(str2, str3))
        {
          str3 = str2;
          if (i > 0)
          {
            i--;
            str1 = str1 + "<" + str3 + ">" + (((BooleanVariable)localObject2).getBoolVal() ? "true" : "false") + "</" + str3 + ">";
          }
        }
        else
        {
          str1 = str1 + "<" + str3 + ">" + (((BooleanVariable)localObject2).getBoolVal() ? "true" : "false") + "</" + str3 + ">";
        }
      }
      if ((localObject1 instanceof WorkspaceObject))
      {
        localObject2 = (WorkspaceObject)localObject1;
        str3 = ((WorkspaceObject)localObject2).getName();
        if (arrayMatch(str2, str3))
        {
          str3 = str2;
          if (i > 0)
          {
            i--;
            str1 = str1 + generateXMLElement(str3, ((WorkspaceObject)localObject2).myContentDocument);
          }
        }
        else
        {
          str1 = str1 + generateXMLElement(str3, ((WorkspaceObject)localObject2).myContentDocument);
        }
      }
    }
    return str1;
  }
  
  private boolean arrayMatch(String paramString1, String paramString2)
  {
    return paramString2.startsWith(paramString1);
  }
  
  public String getHelpID()
  {
    return "LangRef_XML_Object_XMLMessageOut";
  }
  
  public String toString()
  {
    String str = getName();
    if (str.equals("")) {
      return "    ";
    }
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/XMLMessageOut.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */