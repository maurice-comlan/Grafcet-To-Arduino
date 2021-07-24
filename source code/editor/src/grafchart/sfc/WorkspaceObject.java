package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoText;
import grafchart.graphics.MyJDesktopPane;
import grafchart.graphics.MyJGoImage;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WorkspaceObject
  extends GrafcetObject
  implements Readable, Writable, Hierarchical, Workspace, SymbolTableObject
{
  protected double rectSeparation = 5.0D;
  private double rectSpacing1 = 10.0D;
  private double rectSpacing2 = 25.0D;
  private double rectSpacing3 = 40.0D;
  public String fileName = "";
  public boolean useIcon = false;
  public MyJGoImage icon = null;
  public JGoRectangle myRectangle = null;
  public JGoRectangle myRectangle1 = null;
  public JGoRectangle myRectangle2 = null;
  public JGoRectangle myRectangle3 = null;
  public JGoText myName = null;
  public JGoText classLabel = null;
  public GCDocument myContentDocument = null;
  public transient JInternalFrame frame = null;
  public transient GCView parentView = null;
  public transient GCView view = null;
  public Rectangle bounds = null;
  public Point point = new Point(0, 0);
  public int rgbColor;
  public int stepCounterInt = 2;
  public boolean enabled = true;
  public int scanCycle = 1;
  public int scanCounter = 1;
  
  public WorkspaceObject() {}
  
  public WorkspaceObject(Point paramPoint, String paramString)
  {
    this(paramPoint, paramString, null);
  }
  
  public WorkspaceObject(Point paramPoint, String paramString1, String paramString2)
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
    this.myRectangle1 = new JGoRectangle(new Rectangle(10, 8));
    this.myRectangle1.setSelectable(false);
    this.myRectangle1.setDraggable(false);
    this.myRectangle2 = new JGoRectangle(new Rectangle(10, 8));
    this.myRectangle2.setSelectable(false);
    this.myRectangle2.setDraggable(false);
    this.myRectangle3 = new JGoRectangle(new Rectangle(10, 8));
    this.myRectangle3.setSelectable(false);
    this.myRectangle3.setDraggable(false);
    this.myName = new JGoText(paramString1);
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    if (paramString2 != null)
    {
      this.classLabel = new JGoText(paramString2);
      this.classLabel.setSelectable(true);
      this.classLabel.setEditable(false);
      this.classLabel.setEditOnSingleClick(false);
      this.classLabel.setDraggable(false);
      this.classLabel.setAlignment(3);
      this.classLabel.setTransparent(true);
    }
    else
    {
      this.classLabel = new JGoText("");
      this.classLabel.setSelectable(true);
      this.classLabel.setEditable(false);
      this.classLabel.setEditOnSingleClick(false);
      this.classLabel.setDraggable(false);
      this.classLabel.setAlignment(3);
      this.classLabel.setTransparent(true);
    }
    this.classLabel.setSelectable(false);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myRectangle1);
    addObjectAtTail(this.myRectangle2);
    addObjectAtTail(this.myRectangle3);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.classLabel);
    this.myContentDocument = new GCDocument(this);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    WorkspaceObject localWorkspaceObject = (WorkspaceObject)paramJGoArea;
    localWorkspaceObject.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localWorkspaceObject.myRectangle3 = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle3));
    localWorkspaceObject.myRectangle2 = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle2));
    localWorkspaceObject.myRectangle1 = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle1));
    localWorkspaceObject.addObjectAtHead(localWorkspaceObject.myRectangle);
    localWorkspaceObject.addObjectAtTail(localWorkspaceObject.myRectangle3);
    localWorkspaceObject.addObjectAtTail(localWorkspaceObject.myRectangle2);
    localWorkspaceObject.addObjectAtTail(localWorkspaceObject.myRectangle1);
    if (this.icon != null)
    {
      localWorkspaceObject.icon = ((MyJGoImage)paramJGoCopyEnvironment.copy(this.icon));
      localWorkspaceObject.addObjectAtTail(localWorkspaceObject.icon);
    }
    localWorkspaceObject.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localWorkspaceObject.classLabel = ((JGoText)paramJGoCopyEnvironment.copy(this.classLabel));
    localWorkspaceObject.addObjectAtTail(localWorkspaceObject.myName);
    localWorkspaceObject.addObjectAtTail(localWorkspaceObject.classLabel);
    localWorkspaceObject.myContentDocument = new GCDocument(localWorkspaceObject);
    localWorkspaceObject.myContentDocument.copyFromCollection(this.myContentDocument);
    localWorkspaceObject.rectSeparation = this.rectSeparation;
    localWorkspaceObject.rectSpacing1 = this.rectSpacing1;
    localWorkspaceObject.rectSpacing2 = this.rectSpacing2;
    localWorkspaceObject.rectSpacing3 = this.rectSpacing3;
    localWorkspaceObject.bounds = this.bounds;
    localWorkspaceObject.myContentDocument.currentScale = this.myContentDocument.currentScale;
    localWorkspaceObject.point = this.point;
    localWorkspaceObject.rgbColor = this.rgbColor;
    localWorkspaceObject.fileName = this.fileName;
    localWorkspaceObject.useIcon = this.useIcon;
    localWorkspaceObject.scanCycle = this.scanCycle;
  }
  
  public void setFileName()
  {
    if ((this.useIcon) && (this.icon == null))
    {
      removeObject(this.myRectangle1);
      removeObject(this.myRectangle2);
      removeObject(this.myRectangle3);
      this.icon = new MyJGoImage(new Point(), this.myRectangle.getSize());
      this.icon.setSelectable(false);
      this.icon.setDraggable(false);
      addObjectAtTail(this.icon);
      this.icon.loadImage(this.fileName);
      this.myRectangle.setPen(new JGoPen(0, 1, new Color(0.0F, 0.0F, 0.0F)));
      layoutChildren();
    }
    else if ((!this.useIcon) && (this.icon != null))
    {
      removeObject(this.icon);
      this.icon = null;
      addObjectAtTail(this.myRectangle1);
      addObjectAtTail(this.myRectangle2);
      addObjectAtTail(this.myRectangle3);
      this.myRectangle.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
      layoutChildren();
    }
  }
  
  public void setNormalSize()
  {
    if ((this.useIcon) && (this.icon != null))
    {
      Dimension localDimension = this.icon.getNaturalSize();
      if ((localDimension.getWidth() > 0.0D) && (localDimension.getHeight() > 0.0D))
      {
        removeObject(this.myName);
        removeObject(this.classLabel);
        setSize(localDimension);
        addObjectAtTail(this.myName);
        addObjectAtTail(this.classLabel);
        layoutChildren();
      }
    }
  }
  
  private void removeTextFields()
  {
    removeObject(this.myName);
    removeObject(this.classLabel);
  }
  
  private void restoreTextFields()
  {
    addObjectAtTail(this.myName);
    addObjectAtTail(this.classLabel);
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("ownerClass", this.classLabel.getText());
    XMLUtil.setBool(paramElement, "enabled", this.enabled);
    XMLUtil.setInt(paramElement, "scanCycle", this.scanCycle);
    XMLUtil.setBool(paramElement, "useIcon", this.useIcon);
    paramElement.setAttribute("fileName", this.fileName);
    XMLUtil.setBool(paramElement, "horizontalScrollBar", this.myContentDocument.horizontalScrollBar);
    XMLUtil.setBool(paramElement, "verticalScrollBar", this.myContentDocument.verticalScrollBar);
    removeTextFields();
    XMLUtil.saveBoundingRect(paramElement, this);
    restoreTextFields();
    this.myContentDocument.storeXMLRec(paramElement, this.bounds, this.point);
    return paramElement;
  }
  
  public static WorkspaceObject loadXML(Element paramElement)
  {
    String str = paramElement.getAttribute("name");
    Object localObject = null;
    if (paramElement.getTagName().equals(GCDocument.workspaceObjectTag)) {
      localObject = new WorkspaceObject(new Point(), str);
    } else {
      localObject = new StepFusionSet(new Point(), str);
    }
    ((WorkspaceObject)localObject).classLabel.setText(paramElement.getAttribute("ownerClass"));
    ((WorkspaceObject)localObject).enabled = XMLUtil.getBool(paramElement, "enabled", true);
    ((WorkspaceObject)localObject).scanCycle = XMLUtil.getInt(paramElement, "scanCycle", 1);
    ((WorkspaceObject)localObject).fileName = paramElement.getAttribute("fileName");
    ((WorkspaceObject)localObject).useIcon = XMLUtil.getBool(paramElement, "useIcon");
    if (((WorkspaceObject)localObject).useIcon) {
      ((WorkspaceObject)localObject).setFileName();
    }
    ((WorkspaceObject)localObject).myContentDocument.horizontalScrollBar = XMLUtil.getBool(paramElement, "horizontalScrollBar", true);
    ((WorkspaceObject)localObject).myContentDocument.verticalScrollBar = XMLUtil.getBool(paramElement, "verticalScrollBar", true);
    if ((localObject instanceof StepFusionSet)) {
      ((StepFusionSet)localObject).abortive = XMLUtil.getBool(paramElement, "abortive");
    }
    ((WorkspaceObject)localObject).removeTextFields();
    XMLUtil.restoreBoundingRectAny(paramElement, (JGoObject)localObject);
    ((WorkspaceObject)localObject).restoreTextFields();
    NodeList localNodeList = paramElement.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        Element localElement = (Element)localNode;
        if (localElement.getTagName().equals(GCDocument.documentTag))
        {
          ((WorkspaceObject)localObject).bounds = XMLUtil.getWorkspaceBoundingRect(localElement);
          ((WorkspaceObject)localObject).myContentDocument.currentScale = XMLUtil.getDouble(localElement, "scale", 1.0D);
          ((WorkspaceObject)localObject).point = XMLUtil.getViewPosition(localElement);
          ((WorkspaceObject)localObject).rgbColor = Integer.parseInt(localElement.getAttribute("color"));
          ((WorkspaceObject)localObject).myContentDocument.setPaperColor(new Color(((WorkspaceObject)localObject).rgbColor));
          ((WorkspaceObject)localObject).myContentDocument.loadXMLRec(localElement);
        }
      }
    }
    return (WorkspaceObject)localObject;
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
    if (this.icon != null) {
      this.icon.setSpotLocation(0, this.myRectangle, 0);
    }
    this.myRectangle1.setSpotLocation(2, (int)Math.round(localPoint.getX() - this.rectSeparation), (int)Math.round(localPoint.getY() + this.rectSpacing1));
    this.myRectangle2.setSpotLocation(2, (int)Math.round(localPoint.getX() + this.rectSeparation), (int)Math.round(localPoint.getY() + this.rectSpacing2));
    this.myRectangle3.setSpotLocation(2, (int)Math.round(localPoint.getX() - this.rectSeparation), (int)Math.round(localPoint.getY() + this.rectSpacing3));
    localPoint = this.myRectangle.getSpotLocation(6);
    if (this.classLabel.getText().equals("")) {
      this.myName.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 10);
    } else {
      this.myName.setSpotLocation(1, (int)localPoint.getX(), (int)localPoint.getY() + 10);
    }
    localPoint = this.myName.getSpotLocation(1);
    this.classLabel.setSpotLocation(3, (int)localPoint.getX(), (int)localPoint.getY());
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle, this.myRectangle1, this.myRectangle2, this.myRectangle3, this.icon };
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
  
  public double getScaleFactorY(Rectangle paramRectangle)
  {
    double d1 = 1.0D;
    if (paramRectangle.height != 0)
    {
      double d2 = paramRectangle.getY() + paramRectangle.getHeight();
      double d3 = d2 - this.myRectangle.getSpotLocation(6).getY();
      d1 = (getBoundingRect().getHeight() - d3) / (paramRectangle.getHeight() - d3);
    }
    return d1;
  }
  
  public ArrayList<Referencable> getSymbolTable()
  {
    return Utils.collectionToSymbolTable(this.myContentDocument);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    if ((!this.enabled) && (paramBoolean)) {
      this.enabled = paramBoolean;
    } else if ((this.enabled) && (!paramBoolean)) {
      this.enabled = paramBoolean;
    }
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
      final WorkspaceObject localWorkspaceObject = this;
      this.myContentDocument.setPaperColor(new Color(255, 255, 255));
      localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
      {
        public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent)
        {
          Editor.myCurrentObject = localWorkspaceObject;
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
          WorkspaceObject.this.rgbColor = WorkspaceObject.this.myContentDocument.getPaperColor().getRGB();
          WorkspaceObject.this.bounds = WorkspaceObject.this.frame.getBounds();
          WorkspaceObject.this.myContentDocument.currentScale = WorkspaceObject.this.view.getScale();
          WorkspaceObject.this.point = WorkspaceObject.this.view.getViewPosition();
          WorkspaceObject.this.stepCounterInt = WorkspaceObject.this.view.getStepCounter();
          Editor.myCurrentView = WorkspaceObject.this.parentView;
          WorkspaceObject.this.frame = null;
          WorkspaceObject.this.view = null;
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
      this.myContentDocument.setModificationProperty(this.myContentDocument.modificationProperty);
      this.frame.setResizable(this.myContentDocument.modificationProperty);
      this.frame.setMaximizable(this.myContentDocument.modificationProperty);
      if (this.myContentDocument.modificationProperty) {
        this.frame.setFrameIcon(GCDocument.defaultIcon);
      } else {
        this.frame.setFrameIcon(GCDocument.lockIcon);
      }
      if ((this.myContentDocument.executing) && (this.enabled)) {
        this.frame.setFrameIcon(GCDocument.runIcon);
      }
      if (this.myContentDocument.horizontalScrollBar)
      {
        this.view.setHorizontalScrollBar(this.view.getHorizontalScrollBar());
        this.view.hasHorizontalScrollBar = true;
      }
      else
      {
        this.view.setHorizontalScrollBar(null);
        this.view.hasHorizontalScrollBar = false;
      }
      if (this.myContentDocument.verticalScrollBar)
      {
        this.view.setVerticalScrollBar(this.view.getVerticalScrollBar());
        this.view.hasVerticalScrollBar = true;
      }
      else
      {
        this.view.setVerticalScrollBar(null);
        this.view.hasVerticalScrollBar = false;
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
      final WorkspaceObject localWorkspaceObject = this;
      this.myContentDocument.setPaperColor(new Color(255, 255, 255));
      localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
      {
        public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent)
        {
          Editor.myCurrentObject = localWorkspaceObject;
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
          WorkspaceObject.this.rgbColor = WorkspaceObject.this.myContentDocument.getPaperColor().getRGB();
          WorkspaceObject.this.bounds = WorkspaceObject.this.frame.getBounds();
          WorkspaceObject.this.myContentDocument.currentScale = WorkspaceObject.this.view.getScale();
          WorkspaceObject.this.point = WorkspaceObject.this.view.getViewPosition();
          WorkspaceObject.this.stepCounterInt = WorkspaceObject.this.view.getStepCounter();
          Editor.myCurrentView = WorkspaceObject.this.parentView;
          WorkspaceObject.this.frame = null;
          WorkspaceObject.this.view = null;
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
      this.myContentDocument.setModificationProperty(this.myContentDocument.modificationProperty);
      this.frame.setResizable(this.myContentDocument.modificationProperty);
      this.frame.setMaximizable(this.myContentDocument.modificationProperty);
      if (this.myContentDocument.modificationProperty) {
        this.frame.setFrameIcon(GCDocument.defaultIcon);
      } else {
        this.frame.setFrameIcon(GCDocument.lockIcon);
      }
      if ((this.myContentDocument.executing) && (this.enabled)) {
        this.frame.setFrameIcon(GCDocument.runIcon);
      }
      if (this.myContentDocument.horizontalScrollBar)
      {
        this.view.setHorizontalScrollBar(this.view.getHorizontalScrollBar());
        this.view.hasHorizontalScrollBar = true;
      }
      else
      {
        this.view.setHorizontalScrollBar(null);
        this.view.hasHorizontalScrollBar = false;
      }
      if (this.myContentDocument.verticalScrollBar)
      {
        this.view.setVerticalScrollBar(this.view.getVerticalScrollBar());
        this.view.hasVerticalScrollBar = true;
      }
      else
      {
        this.view.setVerticalScrollBar(null);
        this.view.hasVerticalScrollBar = false;
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
  
  public String getHelpID()
  {
    return "LangRef_FC_WorkspaceObject";
  }
  
  public int getTickTime()
  {
    return this.scanCycle * getDocument().getTickTime();
  }
  
  public GCDocument getSubWorkspace()
  {
    return this.myContentDocument;
  }
  
  public String toString()
  {
    String str = getName();
    if (str.equals("")) {
      return "    ";
    }
    return getName();
  }
  
  public void sampleAll() {}
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/WorkspaceObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */