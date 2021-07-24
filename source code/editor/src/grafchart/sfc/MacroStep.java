package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoLink;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.graphics.MyJDesktopPane;
import grafchart.graphics.MyJGoImage;
import grafchart.sfc.actions.Statement;
import grafchart.util.XMLUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MacroStep
  extends GrafcetObject
  implements GCIdent, Readable, Connectable, Hierarchical, IconStep, SymbolTableObject
{
  private String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
  public String fileName = "";
  public boolean useIcon = false;
  public MyJGoImage icon = null;
  private JGoStroke stubRef = null;
  private boolean isInitActivation = false;
  private boolean isInitActivated;
  private boolean isOwnActivationByFusionSet = false;
  private ResumeMode resumeMode = ResumeMode.Default;
  public JGoRectangle myRectangle = null;
  public JGoStroke myTopLeftStroke = null;
  public JGoStroke myTopRightStroke = null;
  public JGoStroke myBottomLeftStroke = null;
  public JGoStroke myBottomRightStroke = null;
  public JGoText myName = null;
  public JGoStroke myInLine = null;
  protected JGoStroke myOutLine = null;
  protected JGoStroke myActionStroke = null;
  protected GCStepInPort myInPort = null;
  protected GCStepOutPort myOutPort = null;
  protected GCStepExceptionOutPort myExcOutPort = null;
  public String actionText = ";";
  public transient Statement node = null;
  protected GCStepHistoryInPort myHistoryPort = null;
  public ArrayList historySteps = new ArrayList();
  public ArrayList<Input> inputPorts = new ArrayList();
  public ArrayList<Output> outputPorts = new ArrayList();
  protected JGoEllipse myToken = null;
  public GCDocument myContentDocument = null;
  public transient JInternalFrame frame = null;
  public transient GCView parentView = null;
  public transient GCView view = null;
  public Rectangle bounds = null;
  public Point point = new Point(0, 0);
  public int rgbColor;
  protected ArrayList succeedingTransitions = new ArrayList();
  protected ArrayList precedingTransitions = new ArrayList();
  public int stepCounterInt = 2;
  
  public MacroStep() {}
  
  public MacroStep(Point paramPoint, String paramString)
  {
    this();
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myRectangle = new JGoRectangle();
    this.myRectangle.setSize(60, 60);
    this.myRectangle.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myRectangle.setSelectable(false);
    this.myRectangle.setDraggable(false);
    this.myTopLeftStroke = new JGoStroke();
    this.myTopLeftStroke.addPoint(0, 15);
    this.myTopLeftStroke.addPoint(15, 0);
    this.myTopLeftStroke.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myTopLeftStroke.setSelectable(false);
    this.myTopLeftStroke.setDraggable(false);
    this.myTopRightStroke = new JGoStroke();
    this.myTopRightStroke.addPoint(0, 0);
    this.myTopRightStroke.addPoint(15, 15);
    this.myTopRightStroke.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myTopRightStroke.setSelectable(false);
    this.myTopRightStroke.setDraggable(false);
    this.myBottomLeftStroke = new JGoStroke();
    this.myBottomLeftStroke.addPoint(0, 0);
    this.myBottomLeftStroke.addPoint(15, 15);
    this.myBottomLeftStroke.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myBottomLeftStroke.setSelectable(false);
    this.myBottomLeftStroke.setDraggable(false);
    this.myBottomRightStroke = new JGoStroke();
    this.myBottomRightStroke.addPoint(0, 15);
    this.myBottomRightStroke.addPoint(15, 0);
    this.myBottomRightStroke.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myBottomRightStroke.setSelectable(false);
    this.myBottomRightStroke.setDraggable(false);
    this.myName = new JGoText(paramString);
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(3);
    this.myName.setTransparent(true);
    this.inputPorts.add(new Input(this));
    this.outputPorts.add(new Output(this));
    this.myExcOutPort = new GCStepExceptionOutPort();
    this.myHistoryPort = new GCStepHistoryInPort();
    this.myToken = new JGoEllipse();
    this.myToken.setSize(20, 20);
    this.myToken.setSelectable(false);
    this.myToken.setDraggable(false);
    this.myToken.setPen(JGoPen.Null);
    this.myToken.setBrush(JGoBrush.Null);
    this.stubRef = new JGoStroke();
    this.stubRef.addPoint(0, 0);
    this.stubRef.addPoint(0, 5);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myTopLeftStroke);
    addObjectAtTail(this.myTopRightStroke);
    addObjectAtTail(this.myBottomLeftStroke);
    addObjectAtTail(this.myBottomRightStroke);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myExcOutPort);
    addObjectAtTail(this.myHistoryPort);
    addObjectAtTail(this.myToken);
    this.myContentDocument = new GCDocument(this);
    setLocation(paramPoint);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    MacroStep localMacroStep = (MacroStep)paramJGoArea;
    localMacroStep.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localMacroStep.myTopLeftStroke = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myTopLeftStroke));
    localMacroStep.myTopRightStroke = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myTopRightStroke));
    localMacroStep.myBottomLeftStroke = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBottomLeftStroke));
    localMacroStep.myBottomRightStroke = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBottomRightStroke));
    localMacroStep.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localMacroStep.addObjectAtHead(localMacroStep.myRectangle);
    localMacroStep.addObjectAtTail(localMacroStep.myTopLeftStroke);
    localMacroStep.addObjectAtTail(localMacroStep.myTopRightStroke);
    localMacroStep.addObjectAtTail(localMacroStep.myBottomLeftStroke);
    localMacroStep.addObjectAtTail(localMacroStep.myBottomRightStroke);
    localMacroStep.addObjectAtTail(localMacroStep.myName);
    Iterator localIterator = this.inputPorts.iterator();
    Object localObject1;
    Object localObject2;
    while (localIterator.hasNext())
    {
      localObject1 = (Input)localIterator.next();
      localObject2 = new Input((JGoStroke)paramJGoCopyEnvironment.copy(((Input)localObject1).line), (GCStepInPort)paramJGoCopyEnvironment.copy(((Input)localObject1).port));
      localMacroStep.inputPorts.add((Input) localObject2);
      localMacroStep.addObjectAtTail(((Input)localObject2).line);
      localMacroStep.addObjectAtTail(((Input)localObject2).port);
    }
    localIterator = this.outputPorts.iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (Output)localIterator.next();
      localObject2 = new Output((JGoStroke)paramJGoCopyEnvironment.copy(((Output)localObject1).line), (GCStepOutPort)paramJGoCopyEnvironment.copy(((Output)localObject1).port));
      localMacroStep.outputPorts.add((Output) localObject2);
      localMacroStep.addObjectAtTail(((Output)localObject2).line);
      localMacroStep.addObjectAtTail(((Output)localObject2).port);
    }
    localMacroStep.myExcOutPort = ((GCStepExceptionOutPort)paramJGoCopyEnvironment.copy(this.myExcOutPort));
    localMacroStep.myHistoryPort = ((GCStepHistoryInPort)paramJGoCopyEnvironment.copy(this.myHistoryPort));
    localMacroStep.myToken = ((JGoEllipse)paramJGoCopyEnvironment.copy(this.myToken));
    localMacroStep.myToken.setBrush(JGoBrush.Null);
    localMacroStep.stubRef = ((JGoStroke)paramJGoCopyEnvironment.copy(this.stubRef));
    localMacroStep.addObjectAtTail(localMacroStep.myExcOutPort);
    localMacroStep.addObjectAtTail(localMacroStep.myHistoryPort);
    localMacroStep.addObjectAtTail(localMacroStep.myToken);
    if (this.icon != null)
    {
      localMacroStep.icon = ((MyJGoImage)paramJGoCopyEnvironment.copy(this.icon));
      localMacroStep.addObjectAtTail(localMacroStep.icon);
    }
    localMacroStep.myContentDocument = new GCDocument(localMacroStep);
    localMacroStep.myContentDocument.copyFromCollection(this.myContentDocument);
    localMacroStep.actionText = this.actionText;
    localMacroStep.useIcon = this.useIcon;
    localMacroStep.fileName = this.fileName;
  }
  
  public boolean isUseIcon()
  {
    return this.useIcon;
  }
  
  public String getFileName()
  {
    return this.fileName;
  }
  
  public void setUseIcon(boolean paramBoolean)
  {
    this.useIcon = paramBoolean;
  }
  
  public void setFileNameValue(String paramString)
  {
    this.fileName = paramString;
  }
  
  public void setFileName()
  {
    if ((this.useIcon) && (this.icon == null))
    {
      this.icon = new MyJGoImage(getTopLeft(), this.myRectangle.getSize());
      this.icon.setSelectable(false);
      this.icon.setDraggable(false);
      addObjectAtHead(this.icon);
      this.icon.loadImage(this.fileName);
      layoutChildren();
    }
    else if ((!this.useIcon) && (this.icon != null))
    {
      removeObject(this.icon);
      this.icon = null;
      layoutChildren();
    }
  }
  
  public ResumeMode getResumeMode()
  {
    return this.resumeMode;
  }
  
  public void setResumeMode(ResumeMode paramResumeMode)
  {
    this.resumeMode = paramResumeMode;
  }
  
  public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    paramVector1.add(this.id);
    paramVector2.add(this);
    paramElement.setAttribute("id", this.id);
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("actionText", this.actionText);
    XMLUtil.setBool(paramElement, "useIcon", this.useIcon);
    paramElement.setAttribute("fileName", this.fileName);
    paramElement.setAttribute("resumeMode", this.resumeMode.name());
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    this.myContentDocument.storeXMLRec(paramElement, this.bounds, this.point);
    return paramElement;
  }
  
  public static MacroStep loadXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    MacroStep localMacroStep = new MacroStep(new Point(), paramElement.getAttribute("name"));
    String str1 = paramElement.getAttribute("id");
    if (Utils.getSaveVersion(paramElement) >= 2) {
      localMacroStep.id = str1;
    }
    paramVector1.add(str1);
    paramVector2.add(localMacroStep);
    localMacroStep.fileName = paramElement.getAttribute("fileName");
    localMacroStep.useIcon = XMLUtil.getBool(paramElement, "useIcon");
    if (localMacroStep.useIcon) {
      localMacroStep.setFileName();
    }
    String str2 = ";";
    if (paramElement.hasAttribute("actionText"))
    {
      str2 = paramElement.getAttribute("actionText");
      if (Utils.getSaveVersion(paramElement) < 1) {
        str2 = Utils.newlineHack(str2);
      }
    }
    localMacroStep.actionText = str2;
    localMacroStep.resumeMode = ResumeMode.Default;
    if (Utils.getSaveVersion(paramElement) >= 6) {
      try
      {
        localMacroStep.resumeMode = ResumeMode.valueOf(paramElement.getAttribute("resumeMode"));
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        if (!Editor.isTest) {
          Utils.writeError("Illegal resume mode \"" + paramElement.getAttribute("resumeMode") + "\" for Macro Step \"" + localMacroStep.getName() + "\"");
        }
      }
    }
    NodeList localNodeList = paramElement.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        Element localElement = (Element)localNode;
        if (localElement.getTagName().equals(GCDocument.documentTag))
        {
          localMacroStep.bounds = XMLUtil.getWorkspaceBoundingRect(localElement);
          String str3 = localElement.getAttribute("scale");
          localMacroStep.myContentDocument.currentScale = Double.parseDouble(str3);
          localMacroStep.point = XMLUtil.getViewPosition(localElement);
          localMacroStep.rgbColor = XMLUtil.getInt(localElement, "color", -1);
          localMacroStep.myContentDocument.setPaperColor(new Color(localMacroStep.rgbColor));
          localMacroStep.myContentDocument.loadXMLRec(localElement);
          localMacroStep.updateStubs();
        }
      }
    }
    localMacroStep.removeObject(localMacroStep.myName);
    XMLUtil.restoreBoundingRectAny(paramElement, localMacroStep, localMacroStep, 0);
    localMacroStep.addObjectAtTail(localMacroStep.myName);
    localMacroStep.layoutChildren();
    return localMacroStep;
  }
  
  public Point getLocation(Point paramPoint)
  {
    return this.myRectangle.getSpotLocation(0, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    this.myRectangle.setSpotLocation(0, paramInt1, paramInt2);
    layoutChildren();
  }
  
  public void layoutChildren()
  {
    if (this.icon != null) {
      this.icon.setSpotLocation(0, this.myRectangle, 0);
    }
    this.myTopLeftStroke.setSpotLocation(1, this.myRectangle, 1);
    this.myTopRightStroke.setSpotLocation(3, this.myRectangle, 3);
    this.myBottomLeftStroke.setSpotLocation(7, this.myRectangle, 7);
    this.myBottomRightStroke.setSpotLocation(5, this.myRectangle, 5);
    Point localPoint = this.myRectangle.getSpotLocation(8);
    this.myName.setSpotLocation(4, localPoint.x - 8, localPoint.y + 10);
    localPoint = this.myRectangle.getSpotLocation(1);
    int i = this.myRectangle.getWidth() / (this.inputPorts.size() + 1);
    int j = 1;
    Iterator localIterator = this.inputPorts.iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (Input)localIterator.next();
      ((Input)localObject).line.setSpotLocation(6, localPoint.x + j * i, localPoint.y);
      ((Input)localObject).port.setSpotLocation(2, ((Input)localObject).line, 2);
      j++;
    }
    localPoint = this.myRectangle.getSpotLocation(7);
    i = this.myRectangle.getWidth() / (this.outputPorts.size() + 1);
    j = 1;
    localIterator = this.outputPorts.iterator();
    while (localIterator.hasNext())
    {
      localObject = (Output)localIterator.next();
      ((Output)localObject).line.setSpotLocation(2, localPoint.x + j * i, localPoint.y);
      ((Output)localObject).port.setSpotLocation(6, ((Output)localObject).line, 6);
      j++;
    }
    this.myExcOutPort.setSpotLocation(8, this.myRectangle, 8);
    this.myHistoryPort.setSpotLocation(4, this.myRectangle, 4);
    this.myToken.setSpotLocation(0, this.myRectangle, 0);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    ArrayList localArrayList = new ArrayList(Arrays.asList(new JGoObject[] { this.myRectangle, this.myTopLeftStroke, this.myTopRightStroke, this.myBottomLeftStroke, this.myBottomRightStroke, this.myExcOutPort, this.myHistoryPort, this.icon, this.myToken, this.stubRef }));
    Object localObject1 = this.inputPorts.iterator();
    Object localObject2;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Input)((Iterator)localObject1).next();
      localArrayList.add(((Input)localObject2).line);
    }
    localObject1 = this.outputPorts.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Output)((Iterator)localObject1).next();
      localArrayList.add(((Output)localObject2).line);
    }
    localObject1 = this.inputPorts.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Input)((Iterator)localObject1).next();
      localArrayList.add(((Input)localObject2).port);
    }
    localObject1 = this.outputPorts.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Output)((Iterator)localObject1).next();
      localArrayList.add(((Output)localObject2).port);
    }
    localObject1 = new JGoObject[localArrayList.size()];
    return (JGoObject[])localArrayList.toArray((Object[])localObject1);
  }
  
  public Dimension getMinimumSize()
  {
    int i = (int)Math.ceil(this.myName.getBoundingRect().getWidth() + 20.0D);
    int j = (int)Math.ceil(this.myName.getBoundingRect().getHeight() + 10.0D);
    return new Dimension(i, j);
  }
  
  public int getNoScaleLeft(Rectangle paramRectangle)
  {
    return this.myRectangle.getLeft() - paramRectangle.x;
  }
  
  public boolean isTransition()
  {
    return false;
  }
  
  public boolean isStep()
  {
    return true;
  }
  
  public void activate()
  {
    if (!this.fusionSets.isEmpty())
    {
      this.isOwnActivationByFusionSet = true;
      Iterator localIterator = this.fusionSets.iterator();
      while (localIterator.hasNext())
      {
        StepFusionSet localStepFusionSet = (StepFusionSet)localIterator.next();
        localStepFusionSet.activate();
      }
      this.isOwnActivationByFusionSet = false;
    }
    else
    {
      activateAlone(false);
    }
  }
  
  public void resetInitActivated()
  {
    this.isInitActivated = false;
  }
  
  public void activateInit()
  {
    if (!this.isInitActivated)
    {
      this.isInitActivation = true;
      activate();
      this.isInitActivation = false;
      executeNormalActions(true);
      this.isInitActivated = true;
    }
  }
  
  public void activateAlone(boolean paramBoolean)
  {
    if (this.myToken.getBrush() == JGoBrush.Null)
    {
      this.myToken.setBrush(JGoBrush.black);
      if ((getParent() instanceof GCGroup)) {
        ((GCGroup)getParent()).showToken();
      }
    }
    if (!isActive(true)) {
      this.newX = true;
    }
    executeStoredActions();
    if ((paramBoolean) && (!this.isInitActivation) && (!this.isOwnActivationByFusionSet))
    {
      int i = 0;
      switch (this.resumeMode)
      {
      case Never: 
      case Default: 
        break;
      case Always: 
        if (!this.historySteps.isEmpty()) {
          i = 1;
        } else {
          Utils.writeDebug("No stored state to resume.");
        }
        break;
      default: 
        Utils.writeInternalError("Unhandled ResumeMode");
      }
      if (i == 0)
      {
        GCDocument localGCDocument = this.myContentDocument;
        if (localGCDocument != null)
        {
          JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos();
          for (JGoObject localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
          {
            if ((localJGoObject instanceof EnterStep))
            {
              EnterStep localEnterStep = (EnterStep)localJGoObject;
              localEnterStep.activateAlone();
            }
            localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
          }
        }
      }
      else
      {
        activateFromHistory(true);
      }
    }
  }
  
  private boolean isActive(boolean paramBoolean)
  {
    boolean bool = false;
    GCDocument localGCDocument = this.myContentDocument;
    if (localGCDocument != null)
    {
      JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos();
      for (JGoObject localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
      {
        Object localObject;
        if ((localJGoObject instanceof GCStep))
        {
          localObject = (GCStep)localJGoObject;
          if (paramBoolean) {
            bool = (bool) || (((GCStep)localObject).curX);
          } else {
            bool = (bool) || (((GCStep)localObject).newX);
          }
        }
        if ((localJGoObject instanceof MacroStep))
        {
          localObject = (MacroStep)localJGoObject;
          if (paramBoolean) {
            bool = (bool) || (((MacroStep)localObject).curX);
          } else {
            bool = (bool) || (((MacroStep)localObject).newX);
          }
        }
        localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
      }
    }
    return bool;
  }
  
  public void deactivateAlone(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      GCDocument localGCDocument = this.myContentDocument;
      if (localGCDocument != null)
      {
        JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos();
        for (JGoObject localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
        {
          if ((localJGoObject instanceof ExitStep))
          {
            ExitStep localExitStep = (ExitStep)localJGoObject;
            localExitStep.deactivateAlone();
          }
          localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
        }
      }
    }
    deactivateCommon(!isActive(false));
    executeExitActions();
  }
  
  private void deactivateCommon(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.myToken.setBrush(JGoBrush.Null);
      if ((getParent() instanceof GCGroup)) {
        ((GCGroup)getParent()).hideToken();
      }
    }
    this.newX = false;
    if ((paramBoolean) && (getDocument().dimming))
    {
      this.myToken.setBrush(JGoBrush.lightGray);
      DimmerThread localDimmerThread = new DimmerThread(this);
      localDimmerThread.start();
    }
  }
  
  public void deactivate()
  {
    if (!this.fusionSets.isEmpty())
    {
      Iterator localIterator = this.fusionSets.iterator();
      while (localIterator.hasNext())
      {
        StepFusionSet localStepFusionSet = (StepFusionSet)localIterator.next();
        localStepFusionSet.deactivate(this);
      }
    }
    else
    {
      deactivateAlone(false);
    }
  }
  
  public void deactivateStrong()
  {
    deactivateCommon(true);
    deactivateBody(this.myContentDocument);
    executeAbortiveActions();
  }
  
  private void deactivateBody(GCDocument paramGCDocument)
  {
    this.historySteps.clear();
    JGoListPosition localJGoListPosition = paramGCDocument.getFirstObjectPos();
    for (JGoObject localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition))
    {
      Object localObject;
      if ((localJGoObject instanceof GenericTransition))
      {
        localObject = (GenericTransition)localJGoObject;
        ((GenericTransition)localObject).markedForFire = false;
      }
      if ((localJGoObject instanceof GCStep))
      {
        localObject = (GCStep)localJGoObject;
        if (((GCStep)localObject).curX)
        {
          ((GCStep)localObject).myToken.setBrush(JGoBrush.Null);
          if (this.resumeMode != ResumeMode.Never) {
            this.historySteps.add(localObject);
          }
          ((GCStep)localObject).executeAbortiveActions();
          if (paramGCDocument.dimming)
          {
            ((GCStep)localObject).myToken.setBrush(JGoBrush.lightGray);
            DimmerThread localDimmerThread = new DimmerThread((JGoObject)localObject);
            localDimmerThread.start();
          }
        }
        ((GCStep)localObject).curX = false;
        ((GCStep)localObject).newX = false;
      }
      if (((localJGoObject instanceof EnterStep)) && (this.resumeMode == ResumeMode.Never)) {
        this.historySteps.add(localJGoObject);
      }
      if ((localJGoObject instanceof MacroStep))
      {
        localObject = (MacroStep)localJGoObject;
        if (((MacroStep)localObject).curX)
        {
          if (this.resumeMode != ResumeMode.Never) {
            this.historySteps.add(localObject);
          }
          ((MacroStep)localObject).deactivateStrong();
        }
        ((MacroStep)localObject).curX = false;
      }
      localJGoListPosition = paramGCDocument.getNextObjectPos(localJGoListPosition);
    }
  }
  
  public void activateFromHistory(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      this.myToken.setBrush(JGoBrush.black);
      if ((getParent() instanceof GCGroup)) {
        ((GCGroup)getParent()).showToken();
      }
      this.newX = true;
      executeStoredActions();
    }
    if (this.historySteps.isEmpty())
    {
      Utils.writeError("No saved history state in " + getFullName());
    }
    else
    {
      Iterator localIterator = this.historySteps.iterator();
      while (localIterator.hasNext())
      {
        GrafcetObject localGrafcetObject = (GrafcetObject)localIterator.next();
        if ((localGrafcetObject instanceof GCStep)) {
          localGrafcetObject.activate();
        }
        if ((localGrafcetObject instanceof MacroStep))
        {
          MacroStep localMacroStep = (MacroStep)localGrafcetObject;
          localMacroStep.activateFromHistory(false);
        }
      }
    }
    this.historySteps.clear();
  }
  
  public void changeState()
  {
    if (!this.curX)
    {
      this.timer = 0;
    }
    else
    {
      this.timer += 1;
      if (this.newX) {
        executePeriodicActions();
      }
    }
    this.oldX = this.curX;
    this.curX = this.newX;
    if (((this.curX) && (!this.oldX)) || ((!this.curX) && (this.oldX))) {
      executeNormalActions(this.curX);
    }
    if ((this.oldX) && (!this.curX) && (isActive(true)))
    {
      this.curX = true;
      executePeriodicActions();
    }
  }
  
  public ArrayList<Referencable> getSymbolTable()
  {
    return Utils.collectionToSymbolTable(this.myContentDocument);
  }
  
  public Vector getAllLinks()
  {
    Vector localVector = new Vector();
    Iterator localIterator = this.inputPorts.iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (Input)localIterator.next();
      Utils.addLinks(((Input)localObject).port, localVector);
    }
    localIterator = this.outputPorts.iterator();
    while (localIterator.hasNext())
    {
      localObject = (Output)localIterator.next();
      Utils.addLinks(((Output)localObject).port, localVector);
    }
    Utils.addLinks(this.myHistoryPort, localVector);
    Utils.addLinks(this.myExcOutPort, localVector);
    return localVector;
  }
  
  public void executeStoredActions()
  {
    if (this.node != null) {
      this.node.executeStoredActions();
    }
  }
  
  public void executeNormalActions(boolean paramBoolean)
  {
    if (this.node != null) {
      this.node.executeNormalActions(paramBoolean);
    }
  }
  
  public void updateNormalActions()
  {
    if (this.node != null) {
      this.node.effectuateNormalActions();
    }
  }
  
  public void executePeriodicActions()
  {
    if (this.node != null) {
      this.node.executePeriodicActions();
    }
  }
  
  public void executeExitActions()
  {
    if (this.node != null) {
      this.node.executeExitActions();
    }
  }
  
  public void executeAbortiveActions()
  {
    if (this.node != null) {
      this.node.executeAbortiveActions();
    }
  }
  
  public String getToolTipText()
  {
    if ((!this.actionText.equals("")) && (!this.actionText.equals(";")))
    {
      String str = this.actionText;
      str = str.replace('\n', ' ');
      return str;
    }
    return null;
  }
  
  public GCStepInPort getInPort()
  {
    return this.myInPort;
  }
  
  public GCStepOutPort getOutPort()
  {
    return this.myOutPort;
  }
  
  public GCStepExceptionOutPort getExceptionOutPort()
  {
    return this.myExcOutPort;
  }
  
  public void updateStubs()
  {
    GCDocument localGCDocument = this.myContentDocument;
    int i = 0;
    JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos();
    for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition))
    {
      if ((localJGoObject instanceof EnterStep)) {
        i++;
      }
      localJGoListPosition = getNextObjectPos(localJGoListPosition);
    }
    Input localInput;
    if (i > this.inputPorts.size()) {
      while (this.inputPorts.size() < i)
      {
        localInput = new Input(this);
        localInput.line.setHeight(this.stubRef.getHeight());
        this.inputPorts.add(localInput);
      }
    }
    if (i < this.inputPorts.size()) {
      while (i < this.inputPorts.size())
      {
        localInput = (Input)this.inputPorts.remove(0);
        removeObject(localInput.line);
        removeObject(localInput.port);
      }
    }
    int j = 0;
    localJGoListPosition = localGCDocument.getFirstObjectPos();
      JGoObject localJGoObject;
    for (localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition))
    {
      if ((localJGoObject instanceof ExitStep)) {
        j++;
      }
      localJGoListPosition = getNextObjectPos(localJGoListPosition);
    }
    Output localOutput;
    if (j > this.outputPorts.size()) {
      while (this.outputPorts.size() < j)
      {
        localOutput = new Output(this);
        localOutput.line.setHeight(this.stubRef.getHeight());
        this.outputPorts.add(localOutput);
      }
    }
    if (j < this.outputPorts.size()) {
      while (j < this.outputPorts.size())
      {
        localOutput = (Output)this.outputPorts.remove(0);
        removeObject(localOutput.line);
        removeObject(localOutput.port);
      }
    }
    layoutChildren();
  }
  
  public boolean isEnabledAlone(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return this.curX;
    }
    GCDocument localGCDocument = this.myContentDocument;
    int i = 0;
    if (localGCDocument != null)
    {
      JGoListPosition localJGoListPosition = localGCDocument.getFirstObjectPos();
      for (JGoObject localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localGCDocument.getObjectAtPos(localJGoListPosition))
      {
        if ((localJGoObject instanceof ExitStep))
        {
          ExitStep localExitStep = (ExitStep)localJGoObject;
          i = (i != 0) || (localExitStep.isEnabledAlone()) ? 1 : 0;
        }
        localJGoListPosition = localGCDocument.getNextObjectPos(localJGoListPosition);
      }
    }
    return (this.curX) && (i != 0);
  }
  
  public boolean isEnabled()
  {
    boolean bool1 = false;
    if (!this.fusionSets.isEmpty())
    {
      boolean bool2 = true;
      Iterator localIterator = this.fusionSets.iterator();
      while (localIterator.hasNext())
      {
        StepFusionSet localStepFusionSet = (StepFusionSet)localIterator.next();
        bool2 = (bool2) && (localStepFusionSet.isEnabled(this));
      }
      bool1 = bool2;
    }
    else
    {
      bool1 = isEnabledAlone(true);
    }
    return bool1;
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
  
  public void setWorkspaceColor(int paramInt)
  {
    this.myContentDocument.setPaperColor(new Color(paramInt));
  }
  
  public int getWorkspaceColor()
  {
    Color localColor = this.myContentDocument.getPaperColor();
    return localColor.getRGB();
  }
  
  private boolean isShowing()
  {
    return (this.frame != null) && (this.frame.isShowing());
  }
  
  public void showWorkspace()
  {
    this.myContentDocument.setName(this.myName.getText());
    JInternalFrame localJInternalFrame = new JInternalFrame(this.myName.getText(), true, true, true, true);
    localJInternalFrame.setDefaultCloseOperation(2);
    final GCView localGCView = new GCView(this.myContentDocument);
    this.myContentDocument.setView(localGCView);
    localGCView.setStepCounter(this.stepCounterInt);
    localGCView.initialize(Editor.singleton, localJInternalFrame);
    localGCView.layer = (Editor.myCurrentView.layer + 1);
    final MacroStep localMacroStep = this;
    this.myContentDocument.setPaperColor(new Color(255, 255, 255));
    localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
    {
      public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent)
      {
        Editor.myCurrentObject = localMacroStep;
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
        MacroStep.this.rgbColor = MacroStep.this.myContentDocument.getPaperColor().getRGB();
        MacroStep.this.bounds = MacroStep.this.frame.getBounds();
        MacroStep.this.myContentDocument.currentScale = MacroStep.this.view.getScale();
        MacroStep.this.point = MacroStep.this.view.getViewPosition();
        MacroStep.this.stepCounterInt = MacroStep.this.view.getStepCounter();
        Editor.myCurrentView = MacroStep.this.parentView;
        MacroStep.this.frame = null;
        MacroStep.this.view = null;
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
  
  public void showWorkspace(int paramInt1, int paramInt2)
  {
    this.myContentDocument.setName(this.myName.getText());
    JInternalFrame localJInternalFrame = new JInternalFrame(this.myName.getText(), true, true, true, true);
    localJInternalFrame.setDefaultCloseOperation(2);
    final GCView localGCView = new GCView(this.myContentDocument);
    this.myContentDocument.setView(localGCView);
    localGCView.setStepCounter(this.stepCounterInt);
    localGCView.initialize(Editor.singleton, localJInternalFrame);
    localGCView.layer = (Editor.myCurrentView.layer + 1);
    final MacroStep localMacroStep = this;
    this.myContentDocument.setPaperColor(new Color(255, 255, 255));
    localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
    {
      public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent)
      {
        Editor.myCurrentObject = localMacroStep;
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
        MacroStep.this.rgbColor = MacroStep.this.myContentDocument.getPaperColor().getRGB();
        MacroStep.this.bounds = MacroStep.this.frame.getBounds();
        MacroStep.this.myContentDocument.currentScale = MacroStep.this.view.getScale();
        MacroStep.this.point = MacroStep.this.view.getViewPosition();
        MacroStep.this.stepCounterInt = MacroStep.this.view.getStepCounter();
        Editor.myCurrentView = MacroStep.this.parentView;
        MacroStep.this.frame = null;
        MacroStep.this.view = null;
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
      localGCView.setViewPosition(new Point(paramInt1, paramInt2));
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
  
  private boolean hasFrame()
  {
    return this.frame != null;
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
  
  public double getSeconds()
  {
    return this.timer * getDocument().getTickTime() / 1000.0D;
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_MacroStep";
  }
  
  public GCDocument getContentDocument()
  {
    return this.myContentDocument;
  }
  
  public void assignWorkspace(Hierarchical paramHierarchical)
  {
    Editor.writeMessage("Macro steps cannot be assigned");
  }
  
  public String toString()
  {
    String str = getName();
    if (str.equals("")) {
      return "    ";
    }
    return getName();
  }
  
  public void succeedingTransitions(ArrayList<GenericTransition> paramArrayList)
  {
    Object localObject2;
    Object localObject3;
    for (JGoListPosition localJGoListPosition = this.myExcOutPort.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myExcOutPort.getNextLinkPos(localJGoListPosition))
    {
        JGoLink localObject1 = this.myExcOutPort.getLinkAtPos(localJGoListPosition);
      localObject2 = ((JGoLink)localObject1).getToPort();
      if ((((JGoPort)localObject2).getParent() instanceof GenericTransition))
      {
        localObject3 = (GenericTransition)((JGoPort)localObject2).getParent();
        paramArrayList.add((GenericTransition) localObject3);
      }
    }
    Object localObject1 = this.outputPorts.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Output)((Iterator)localObject1).next();
        JGoListPosition localJGoListPosition;
      for (localJGoListPosition = ((Output)localObject2).port.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = ((Output)localObject2).port.getNextLinkPos(localJGoListPosition))
      {
        localObject3 = ((Output)localObject2).port.getLinkAtPos(localJGoListPosition);
        JGoPort localJGoPort = ((JGoLink)localObject3).getToPort();
        if ((localJGoPort.getParent() instanceof GenericTransition))
        {
          GenericTransition localGenericTransition = (GenericTransition)localJGoPort.getParent();
          paramArrayList.add(localGenericTransition);
        }
      }
    }
  }
  
  public static enum ResumeMode
  {
    Default,  Always,  Never;
    
    private ResumeMode() {}
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/MacroStep.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */