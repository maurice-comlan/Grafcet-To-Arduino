package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoText;
import grafchart.graphics.MyJDesktopPane;
import grafchart.sfc.actions.Expr;
import grafchart.sfc.actions.Goal;
import grafchart.sfc.actions.ProcCall;
import grafchart.sfc.actions.ProcParam;
import grafchart.sfc.actions.SingleExpression;
import grafchart.util.ActionCompiler;
import grafchart.util.ActionCompiler.CompileType;
import grafchart.util.XMLUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLMessageIn
  extends GrafcetObject
  implements Readable, Writable, CallSource, Hierarchical, ProcessStepAble, XMLInSource, SymbolTableObject
{
  protected double rectSeparation = 5.0D;
  private double rectSpacing1 = 10.0D;
  private double rectSpacing2 = 25.0D;
  private double rectSpacing3 = 40.0D;
  public JGoRectangle myRectangle = null;
  public JGoText myName = null;
  public JGoText myXML = null;
  public GCDocument myContentDocument = null;
  public GCDocument myContentDocumentProc = null;
  public transient JInternalFrame frame = null;
  public transient GCView parentView = null;
  public transient GCView view = null;
  public Rectangle bounds = null;
  private Point point = new Point(0, 0);
  public int rgbColor;
  public int stepCounterInt = 2;
  public String topic = "";
  protected String identifier = "ChemMessageContent";
  public String subject = "";
  public boolean newValue = false;
  public boolean newValueRead = false;
  public String val;
  public String oldval;
  public String xmlVal;
  public String type = "";
  public int handle = 0;
  public String origin = "";
  public String mustReply = "";
  public String rawMessage = "";
  public transient ProcCall procNode = null;
  public transient ProcParam paramNode = null;
  public transient boolean isDynamicProcedureCall = false;
  public boolean useProcedure = false;
  public ExitStep exStep = null;
  public String gp = new String("");
  public String parameters = new String("");
  public transient GCView viewOwner = null;
  public GrafcetProcedure procedure = null;
  private ObservableList<GCDocument> procedureCalls = null;
  
  public XMLMessageIn() {}
  
  public XMLMessageIn(Point paramPoint, String paramString)
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
    this.myXML = new JGoText("XML-in");
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
    this.myContentDocumentProc = new GCDocument();
    this.procedureCalls = new ObservableList();
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    XMLMessageIn localXMLMessageIn = (XMLMessageIn)paramJGoArea;
    localXMLMessageIn.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localXMLMessageIn.myXML = ((JGoText)paramJGoCopyEnvironment.copy(this.myXML));
    localXMLMessageIn.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localXMLMessageIn.addObjectAtHead(localXMLMessageIn.myRectangle);
    localXMLMessageIn.addObjectAtTail(localXMLMessageIn.myXML);
    localXMLMessageIn.addObjectAtTail(localXMLMessageIn.myName);
    localXMLMessageIn.myContentDocument = new GCDocument(localXMLMessageIn);
    localXMLMessageIn.myContentDocument.copyFromCollection(this.myContentDocument);
    localXMLMessageIn.procedureCalls = new ObservableList();
    localXMLMessageIn.rectSeparation = this.rectSeparation;
    localXMLMessageIn.rectSpacing1 = this.rectSpacing1;
    localXMLMessageIn.rectSpacing2 = this.rectSpacing2;
    localXMLMessageIn.rectSpacing3 = this.rectSpacing3;
    localXMLMessageIn.bounds = this.bounds;
    localXMLMessageIn.myContentDocument.currentScale = this.myContentDocument.currentScale;
    localXMLMessageIn.point = this.point;
    localXMLMessageIn.rgbColor = this.rgbColor;
    localXMLMessageIn.val = this.val;
    localXMLMessageIn.oldval = this.oldval;
    localXMLMessageIn.xmlVal = this.xmlVal;
    localXMLMessageIn.topic = this.topic;
    localXMLMessageIn.identifier = this.identifier;
    localXMLMessageIn.subject = this.subject;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("identifier", this.identifier);
    paramElement.setAttribute("subject", this.subject);
    paramElement.setAttribute("topic", this.topic);
    paramElement.setAttribute("type", this.type);
    paramElement.setAttribute("origin", this.origin);
    paramElement.setAttribute("mustReply", this.mustReply);
    paramElement.setAttribute("handle", "" + this.handle);
    paramElement.setAttribute("grafcetProcedure", this.gp);
    paramElement.setAttribute("parameters", this.parameters);
    XMLUtil.setBool(paramElement, "useProcedure", this.useProcedure);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    this.myContentDocument.storeXMLRec(paramElement, this.bounds, this.point);
    return paramElement;
  }
  
  public static XMLMessageIn loadXML(Element paramElement)
  {
    XMLMessageIn localXMLMessageIn = new XMLMessageIn(new Point(), paramElement.getAttribute("name"));
    localXMLMessageIn.identifier = paramElement.getAttribute("identifier");
    localXMLMessageIn.subject = paramElement.getAttribute("subject");
    localXMLMessageIn.topic = paramElement.getAttribute("topic");
    localXMLMessageIn.type = paramElement.getAttribute("type");
    localXMLMessageIn.origin = paramElement.getAttribute("origin");
    localXMLMessageIn.mustReply = paramElement.getAttribute("mustReply");
    localXMLMessageIn.handle = Utils.parseInt(paramElement.getAttribute("handle"));
    localXMLMessageIn.gp = paramElement.getAttribute("grafcetProcedure");
    String str = paramElement.getAttribute("parameters");
    if (Utils.getSaveVersion(paramElement) < 1) {
      str = Utils.newlineHack(str);
    }
    localXMLMessageIn.parameters = str;
    localXMLMessageIn.useProcedure = XMLUtil.getBool(paramElement, "useProcedure");
    localXMLMessageIn.removeObject(localXMLMessageIn.myName);
    XMLUtil.restoreBoundingRectAny(paramElement, localXMLMessageIn);
    localXMLMessageIn.addObjectAtTail(localXMLMessageIn.myName);
    NodeList localNodeList = paramElement.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        Element localElement = (Element)localNode;
        if (localElement.getTagName().equals(GCDocument.documentTag))
        {
          localXMLMessageIn.bounds = XMLUtil.getWorkspaceBoundingRect(localElement);
          localXMLMessageIn.myContentDocument.currentScale = XMLUtil.getDouble(localElement, "scale", 1.0D);
          localXMLMessageIn.point = XMLUtil.getViewPosition(localElement);
          localXMLMessageIn.rgbColor = XMLUtil.getInt(localElement, "color", -1);
          localXMLMessageIn.myContentDocument.setPaperColor(new Color(localXMLMessageIn.rgbColor));
          localXMLMessageIn.myContentDocument.loadXMLRec(localElement);
        }
      }
    }
    return localXMLMessageIn;
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
      final XMLMessageIn localXMLMessageIn = this;
      this.myContentDocument.setPaperColor(new Color(255, 255, 255));
      localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
      {
        public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent)
        {
          Editor.myCurrentObject = localXMLMessageIn;
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
          XMLMessageIn.this.rgbColor = XMLMessageIn.this.myContentDocument.getPaperColor().getRGB();
          XMLMessageIn.this.bounds = XMLMessageIn.this.frame.getBounds();
          XMLMessageIn.this.myContentDocument.currentScale = XMLMessageIn.this.view.getScale();
          XMLMessageIn.this.point = XMLMessageIn.this.view.getViewPosition();
          XMLMessageIn.this.stepCounterInt = XMLMessageIn.this.view.getStepCounter();
          Editor.myCurrentView = XMLMessageIn.this.parentView;
          XMLMessageIn.this.frame = null;
          XMLMessageIn.this.view = null;
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
      final XMLMessageIn localXMLMessageIn = this;
      this.myContentDocument.setPaperColor(new Color(255, 255, 255));
      localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
      {
        public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent)
        {
          Editor.myCurrentObject = localXMLMessageIn;
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
          XMLMessageIn.this.rgbColor = XMLMessageIn.this.myContentDocument.getPaperColor().getRGB();
          XMLMessageIn.this.bounds = XMLMessageIn.this.frame.getBounds();
          XMLMessageIn.this.myContentDocument.currentScale = XMLMessageIn.this.view.getScale();
          XMLMessageIn.this.point = XMLMessageIn.this.view.getViewPosition();
          XMLMessageIn.this.stepCounterInt = XMLMessageIn.this.view.getStepCounter();
          Editor.myCurrentView = XMLMessageIn.this.parentView;
          XMLMessageIn.this.frame = null;
          XMLMessageIn.this.view = null;
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
  
  public void setValue(String paramString)
  {
    this.rawMessage = paramString;
    String str1 = "";
    this.newValue = true;
    int i = paramString.indexOf("<CCOMBody>");
    String str2 = paramString.substring(0, i);
    int j = str2.indexOf("<CCOMHeader");
    str2 = str2.substring(j);
    Document localDocument = XMLProtocol.parseXML(str2);
    Element localElement = localDocument.getDocumentElement();
    NamedNodeMap localNamedNodeMap = localElement.getAttributes();
    for (int k = 0; k < localNamedNodeMap.getLength(); k++)
    {
      Attr localAttr = (Attr)localNamedNodeMap.item(k);
      String str3 = localAttr.getName();
      if (str3.equals("Type")) {
        this.type = localAttr.getValue();
      }
      if (str3.equals("Origin")) {
        this.origin = localAttr.getValue();
      }
      if (str3.equals("MustReply")) {
        this.mustReply = localAttr.getValue();
      }
      if (str3.equals("Handle")) {
        str1 = localAttr.getValue();
      }
    }
    if (str1 != "") {
      this.handle = Integer.parseInt(str1);
    } else {
      this.handle = 0;
    }
    paramString = paramString.substring(i + 10);
    i = paramString.indexOf("</CCOMBody>");
    paramString = paramString.substring(0, i);
    this.xmlVal = paramString;
    decodeXMLDocument(paramString);
  }
  
  private void decodeXMLDocument(String paramString)
  {
    Document localDocument = XMLProtocol.parseXML(paramString);
    Element localElement = localDocument.getDocumentElement();
    this.myContentDocument.initializeDocumentVariables();
    decodeXMLElement(localElement, this.myContentDocument);
  }
  
  private void decodeXMLElement(Element paramElement, GCDocument paramGCDocument)
  {
    GCDocument localGCDocument = paramGCDocument;
    JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos();
    for (JGoObject localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
    {
      if ((localJGoObject instanceof IndexVariable)) {
        ((IndexVariable)localJGoObject).setStoredIntAction(0);
      }
      localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
    }
    NamedNodeMap localNamedNodeMap = paramElement.getAttributes();
    Object localObject1;
    Object localObject2;
      JGoObject localJGoObject;
    for (int i = 0; i < localNamedNodeMap.getLength(); i++)
    {
      Attr localAttr = (Attr)localNamedNodeMap.item(i);
      localObject1 = localAttr.getName();
      localObject1 = filterTag((String)localObject1);
      localJGoListPosition = localGCDocument.getFirstObjectPos();
      for (localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
      {
        if ((localJGoObject instanceof IntegerAttributeVariable))
        {
          localObject2 = (IntegerAttributeVariable)localJGoObject;
          if (((IntegerAttributeVariable)localObject2).getName().equals(localObject1)) {
            ((IntegerAttributeVariable)localObject2).setStoredIntAction(Integer.parseInt(localAttr.getValue()));
          }
        }
        if ((localJGoObject instanceof RealAttributeVariable))
        {
          localObject2 = (RealAttributeVariable)localJGoObject;
          if (((RealAttributeVariable)localObject2).getName().equals(localObject1)) {
            ((RealAttributeVariable)localObject2).setStoredRealAction(Double.parseDouble(localAttr.getValue()));
          }
        }
        if ((localJGoObject instanceof StringAttributeVariable))
        {
          localObject2 = (StringAttributeVariable)localJGoObject;
          if (((StringAttributeVariable)localObject2).getName().equals(localObject1)) {
            ((StringAttributeVariable)localObject2).setStoredStringAction(localAttr.getValue());
          }
        }
        localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
      }
    }
    NodeList localNodeList = paramElement.getChildNodes();
    for (int j = 0; j < localNodeList.getLength(); j++)
    {
      localObject1 = localNodeList.item(j);
      if (((Node)localObject1).getNodeType() == 1)
      {
        localObject2 = (Element)localObject1;
        String str1 = ((Element)localObject2).getTagName();
        str1 = filterTag(str1);
        localJGoListPosition = localGCDocument.getFirstObjectPos();
        Object localObject3;
        for (localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
        {
          if ((localJGoObject instanceof IndexVariable))
          {
            localObject3 = (IndexVariable)localJGoObject;
            if (((IndexVariable)localObject3).getName().equals(str1))
            {
              ((IndexVariable)localObject3).setStoredIntAction(((IndexVariable)localObject3).getIntVal() + 1);
              str1 = str1 + ((IndexVariable)localObject3).getIntVal();
            }
          }
          localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
        }
        localJGoListPosition = localGCDocument.getFirstObjectPos();
        for (localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
        {
          if (((localJGoObject instanceof IntegerVariable)) && (!(localJGoObject instanceof IntegerAttributeVariable)))
          {
            localObject3 = (IntegerVariable)localJGoObject;
            if (str1.equals(((IntegerVariable)localObject3).getName())) {
              ((IntegerVariable)localObject3).setStoredIntAction(Integer.parseInt(((Element)localObject2).getFirstChild().getNodeValue()));
            }
          }
          if (((localJGoObject instanceof RealVariable)) && (!(localJGoObject instanceof RealAttributeVariable)))
          {
            localObject3 = (RealVariable)localJGoObject;
            if (str1.equals(((RealVariable)localObject3).getName())) {
              ((RealVariable)localObject3).setStoredRealAction(Double.parseDouble(((Element)localObject2).getFirstChild().getNodeValue()));
            }
          }
          if (((localJGoObject instanceof StringVariable)) && (!(localJGoObject instanceof StringAttributeVariable)))
          {
            localObject3 = (StringVariable)localJGoObject;
            if (str1.equals(((StringVariable)localObject3).getName()))
            {
              String str2 = ((Element)localObject2).getFirstChild().getNodeValue();
              ((StringVariable)localObject3).setStoredStringAction(str2);
            }
          }
          if ((localJGoObject instanceof BooleanVariable))
          {
            localObject3 = (BooleanVariable)localJGoObject;
            if (str1.equals(((BooleanVariable)localObject3).getName())) {
              if (((Element)localObject2).getFirstChild().getNodeValue().equals("1")) {
                ((BooleanVariable)localObject3).setStoredBoolAction(true);
              } else if (((Element)localObject2).getFirstChild().getNodeValue().equals("0")) {
                ((BooleanVariable)localObject3).setStoredBoolAction(false);
              }
            }
          }
          if ((localJGoObject instanceof WorkspaceObject))
          {
            localObject3 = (WorkspaceObject)localJGoObject;
            if (str1.equals(((WorkspaceObject)localObject3).getName())) {
              decodeXMLElement((Element)localObject2, ((WorkspaceObject)localObject3).myContentDocument);
            }
          }
          localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
        }
      }
    }
  }
  
  public String getXMLIdentifier()
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
  
  public String getTopic()
  {
    return this.topic;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  private String filterTag(String paramString)
  {
    return paramString.replace('-', '_');
  }
  
  public void readInput()
  {
    this.oldval = this.val;
    this.val = this.xmlVal;
  }
  
  public boolean receivedNewValue()
  {
    return this.newValueRead;
  }
  
  public void startProcedure()
  {
    GCDocument localGCDocument = getDocument();
    synchronized (localGCDocument)
    {
      Object localObject1;
      Object localObject2;
      if (this.isDynamicProcedureCall)
      {
        this.procedure = null;
        localObject1 = this.procNode.evaluateString();
        if (localObject1 != "")
        {
          Editor.printWarnings = true;
          localObject2 = (SingleExpression)ActionCompiler.compile((String)localObject1, this.procNode.root().symbolTable, ActionCompiler.CompileType.SingleExpression, "procedure name in \"" + getFullName() + "\"", null);
          Editor.printWarnings = false;
          if (localObject2 != null) {
            this.procedure = Utils.referencableToGrafcetProcedure(((SingleExpression)localObject2).getExpr().runtimeDecl());
          }
        }
      }
      else if (this.procedure == null)
      {
        Utils.writeInternalError("procedure was not set during compilation.");
      }
      this.exStep = null;
      if (this.procedure != null)
      {
        localObject1 = new GCDocument();
        ((GCDocument)localObject1).setSkipsUndoManager(true);
        GCLink.setLinkModification(false);
        ((GCDocument)localObject1).copyFromCollection(this.procedure.myContentDocument);
        GCLink.setLinkModification(true);
        ((GCDocument)localObject1).setPaperColor(this.procedure.myContentDocument.getPaperColor());
        ((GCDocument)localObject1).owner = this;
        Editor.printWarnings = true;
        this.paramNode = ((ProcParam)ActionCompiler.compile(this.parameters, (SymbolTableObject)localObject1, ActionCompiler.CompileType.ProcParam, "procedure parameters in \"" + getFullName() + "\"", null));
        Editor.printWarnings = false;
        if (this.paramNode != null)
        {
          this.paramNode.initParameters();
          ((GCDocument)localObject1).owner = this.procedure;
          this.myContentDocumentProc = ((GCDocument)localObject1);
          Editor.singleton.compileDocument(this.myContentDocumentProc, false);
          this.myContentDocumentProc.setProcedure(this.procedure);
          this.myContentDocumentProc.setName(this.procedure.getName());
          this.procedure.getCalls().add(this.myContentDocumentProc);
          this.myContentDocumentProc.setModificationProperty(localGCDocument.isModifiable());
          this.myContentDocument.setSpeed(localGCDocument.getTickTime());
          this.myContentDocumentProc.initializeDocument(localGCDocument, localGCDocument.isSimulating());
          localObject2 = this.myContentDocumentProc.getFirstObjectPos();
          for (JGoObject localJGoObject = this.myContentDocumentProc.getObjectAtPos((JGoListPosition)localObject2); (localJGoObject != null) && (localObject2 != null); localJGoObject = this.myContentDocumentProc.getObjectAtPos((JGoListPosition)localObject2))
          {
            Object localObject3;
            if ((localJGoObject instanceof EnterStep))
            {
              localObject3 = (EnterStep)localJGoObject;
              ((EnterStep)localObject3).activate();
            }
            if ((localJGoObject instanceof ExitStep))
            {
              localObject3 = (ExitStep)localJGoObject;
              this.exStep = ((ExitStep)localObject3);
            }
            if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep)))
            {
              localObject3 = (MacroStep)localJGoObject;
              ((MacroStep)localObject3).myContentDocument.switchUndoManager(true);
            }
            if ((localJGoObject instanceof WorkspaceObject))
            {
              localObject3 = (WorkspaceObject)localJGoObject;
              ((WorkspaceObject)localObject3).myContentDocument.switchUndoManager(true);
            }
            localObject2 = this.myContentDocumentProc.getNextObjectPos((JGoListPosition)localObject2);
          }
          this.procedureCalls.add(this.myContentDocumentProc);
          this.myContentDocumentProc.processStepCall = this;
          this.myContentDocumentProc.terminateWhenReady = true;
        }
      }
    }
  }
  
  public ArrayList getProcedureCalls()
  {
    return this.procedureCalls;
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
    return "LangRef_XML_Object_XMLMessageIn";
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/XMLMessageIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */