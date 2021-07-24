package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPolygon;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.graphics.MyJGoImage;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;
import org.w3c.dom.Element;

public class ProcessStep
  extends ProcedureStep
  implements Readable, Connectable, CallSource, ProcessStepAble, IconStep
{
  public static Color grey = new Color(0.8F, 0.8F, 0.8F);
  public static JGoBrush greyBrush = new JGoBrush(65535, grey);
  private ObservableList<GCDocument> procedureCalls = null;
  private String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
  
  public ProcessStep() {}
  
  public ProcessStep(Point paramPoint, String paramString)
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
    this.myTopLeftPolygon = new JGoPolygon();
    this.myTopLeftPolygon.addPoint(0, 0);
    this.myTopLeftPolygon.addPoint(0, 15);
    this.myTopLeftPolygon.addPoint(15, 0);
    this.myTopLeftPolygon.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myTopLeftPolygon.setBrush(greyBrush);
    this.myTopLeftPolygon.setSelectable(false);
    this.myTopLeftPolygon.setDraggable(false);
    this.myTopRightPolygon = new JGoPolygon();
    this.myTopRightPolygon.addPoint(0, 0);
    this.myTopRightPolygon.addPoint(15, 0);
    this.myTopRightPolygon.addPoint(15, 15);
    this.myTopRightPolygon.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myTopRightPolygon.setBrush(greyBrush);
    this.myTopRightPolygon.setSelectable(false);
    this.myTopRightPolygon.setDraggable(false);
    this.myBottomLeftPolygon = new JGoPolygon();
    this.myBottomLeftPolygon.addPoint(0, 0);
    this.myBottomLeftPolygon.addPoint(15, 15);
    this.myBottomLeftPolygon.addPoint(0, 15);
    this.myBottomLeftPolygon.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myBottomLeftPolygon.setBrush(greyBrush);
    this.myBottomLeftPolygon.setSelectable(false);
    this.myBottomLeftPolygon.setDraggable(false);
    this.myBottomRightPolygon = new JGoPolygon();
    this.myBottomRightPolygon.addPoint(0, 15);
    this.myBottomRightPolygon.addPoint(15, 0);
    this.myBottomRightPolygon.addPoint(15, 15);
    this.myBottomRightPolygon.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myBottomRightPolygon.setBrush(greyBrush);
    this.myBottomRightPolygon.setSelectable(false);
    this.myBottomRightPolygon.setDraggable(false);
    this.myName = new JGoText(paramString);
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(1);
    this.myName.setTransparent(true);
    this.myInLine = new JGoStroke();
    this.myInLine.addPoint(10, 0);
    this.myInLine.addPoint(10, 5);
    this.myInLine.setSelectable(false);
    this.myOutLine = new JGoStroke();
    this.myOutLine.addPoint(20, 0);
    this.myOutLine.addPoint(20, 5);
    this.myOutLine.setSelectable(false);
    this.myInPort = new GCStepInPort();
    this.myOutPort = new GCStepOutPort();
    this.myToken = new JGoEllipse();
    this.myToken.setSize(20, 20);
    this.myToken.setSelectable(false);
    this.myToken.setDraggable(false);
    this.myToken.setPen(JGoPen.Null);
    this.myToken.setBrush(JGoBrush.Null);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myTopLeftPolygon);
    addObjectAtTail(this.myTopRightPolygon);
    addObjectAtTail(this.myBottomLeftPolygon);
    addObjectAtTail(this.myBottomRightPolygon);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myInLine);
    addObjectAtTail(this.myOutLine);
    addObjectAtTail(this.myInPort);
    addObjectAtTail(this.myOutPort);
    addObjectAtTail(this.myExcOutPort);
    addObjectAtTail(this.myToken);
    this.myContentDocument = new GCDocument();
    setLocation(paramPoint);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    ProcessStep localProcessStep = (ProcessStep)paramJGoArea;
    localProcessStep.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localProcessStep.myTopLeftPolygon = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myTopLeftPolygon));
    localProcessStep.myTopRightPolygon = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myTopRightPolygon));
    localProcessStep.myBottomLeftPolygon = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myBottomLeftPolygon));
    localProcessStep.myBottomRightPolygon = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myBottomRightPolygon));
    localProcessStep.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localProcessStep.myInLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myInLine));
    localProcessStep.myOutLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myOutLine));
    localProcessStep.myInPort = ((GCStepInPort)paramJGoCopyEnvironment.copy(this.myInPort));
    localProcessStep.myOutPort = ((GCStepOutPort)paramJGoCopyEnvironment.copy(this.myOutPort));
    localProcessStep.myToken = ((JGoEllipse)paramJGoCopyEnvironment.copy(this.myToken));
    localProcessStep.myToken.setBrush(JGoBrush.Null);
    localProcessStep.addObjectAtHead(localProcessStep.myRectangle);
    localProcessStep.addObjectAtTail(localProcessStep.myTopLeftPolygon);
    localProcessStep.addObjectAtTail(localProcessStep.myTopRightPolygon);
    localProcessStep.addObjectAtTail(localProcessStep.myBottomLeftPolygon);
    localProcessStep.addObjectAtTail(localProcessStep.myBottomRightPolygon);
    localProcessStep.addObjectAtTail(localProcessStep.myName);
    localProcessStep.addObjectAtTail(localProcessStep.myInLine);
    localProcessStep.addObjectAtTail(localProcessStep.myOutLine);
    localProcessStep.addObjectAtTail(localProcessStep.myInPort);
    localProcessStep.addObjectAtTail(localProcessStep.myOutPort);
    localProcessStep.addObjectAtTail(localProcessStep.myToken);
    if (this.icon != null)
    {
      localProcessStep.icon = ((MyJGoImage)paramJGoCopyEnvironment.copy(this.icon));
      localProcessStep.addObjectAtTail(localProcessStep.icon);
    }
    localProcessStep.procedureCalls = new ObservableList();
    localProcessStep.gp = this.gp;
    localProcessStep.parameters = this.parameters;
    localProcessStep.actionText = this.actionText;
    localProcessStep.fileName = this.fileName;
    localProcessStep.useIcon = this.useIcon;
  }
  
  public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    paramVector1.add(this.id);
    paramVector2.add(this);
    paramElement.setAttribute("id", this.id);
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("grafcetProcedure", this.gp);
    paramElement.setAttribute("parameters", this.parameters);
    paramElement.setAttribute("actionText", this.actionText);
    XMLUtil.setBool(paramElement, "useIcon", this.useIcon);
    paramElement.setAttribute("fileName", this.fileName);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    return paramElement;
  }
  
  public static ProcedureStep loadProcedureXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    ProcessStep localProcessStep = new ProcessStep(new Point(), paramElement.getAttribute("name"));
    String str1 = paramElement.getAttribute("id");
    if (Utils.getSaveVersion(paramElement) >= 2) {
      localProcessStep.id = str1;
    }
    paramVector1.add(str1);
    paramVector2.add(localProcessStep);
    localProcessStep.useIcon = XMLUtil.getBool(paramElement, "useIcon");
    if (localProcessStep.useIcon) {
      localProcessStep.setFileName();
    }
    localProcessStep.gp = paramElement.getAttribute("grafcetProcedure");
    String str2 = paramElement.getAttribute("parameters");
    if (Utils.getSaveVersion(paramElement) < 1) {
      str2 = Utils.newlineHack(str2);
    }
    localProcessStep.parameters = str2;
    String str3 = ";";
    if (paramElement.hasAttribute("actionText"))
    {
      str3 = paramElement.getAttribute("actionText");
      if (Utils.getSaveVersion(paramElement) < 1) {
        str3 = Utils.newlineHack(str3);
      }
    }
    localProcessStep.actionText = str3;
    localProcessStep.removeObject(localProcessStep.myName);
    XMLUtil.restoreBoundingRectAny(paramElement, localProcessStep, localProcessStep.myRectangle, 0);
    localProcessStep.addObjectAtTail(localProcessStep.myName);
    return localProcessStep;
  }
  
  public void layoutChildren()
  {
    if (this.icon != null) {
      this.icon.setSpotLocation(0, this.myRectangle, 0);
    }
    this.myTopLeftPolygon.setSpotLocation(1, this.myRectangle, 1);
    this.myTopRightPolygon.setSpotLocation(3, this.myRectangle, 3);
    this.myBottomLeftPolygon.setSpotLocation(7, this.myRectangle, 7);
    this.myBottomRightPolygon.setSpotLocation(5, this.myRectangle, 5);
    Point localPoint = this.myRectangle.getSpotLocation(4);
    this.myName.setSpotLocation(8, (int)localPoint.getX() + 8, (int)localPoint.getY() + 10);
    this.myInLine.setSpotLocation(6, this.myRectangle, 2);
    this.myOutLine.setSpotLocation(2, this.myRectangle, 6);
    this.myInPort.setSpotLocation(2, this.myInLine, 2);
    this.myOutPort.setSpotLocation(6, this.myOutLine, 6);
    this.myToken.setSpotLocation(0, this.myRectangle, 0);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle, this.icon, this.myTopLeftPolygon, this.myTopRightPolygon, this.myBottomLeftPolygon, this.myBottomRightPolygon, this.myInLine, this.myOutLine, this.myInPort, this.myOutPort, this.myToken };
  }
  
  public Dimension getMinimumSize()
  {
    int i = (int)Math.ceil(this.myName.getBoundingRect().getWidth() + 20.0D);
    int j = (int)Math.ceil(this.myName.getBoundingRect().getHeight() + 10.0D);
    return new Dimension(i, j);
  }
  
  public void activate()
  {
    if (!this.fusionSets.isEmpty())
    {
      Iterator localIterator = this.fusionSets.iterator();
      while (localIterator.hasNext())
      {
        StepFusionSet localStepFusionSet = (StepFusionSet)localIterator.next();
        localStepFusionSet.activate();
      }
    }
    else
    {
      activateAlone();
    }
  }
  
  public void activateAlone()
  {
    super.activateAlone();
    if ((this.procedure != null) && (this.paramNode != null))
    {
      this.procedureCalls.add(this.myContentDocument);
      this.myContentDocument.processStepCall = this;
      this.myContentDocument.terminateWhenReady = true;
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
  
  public void deactivateAlone(boolean paramBoolean)
  {
    this.myToken.setBrush(JGoBrush.Null);
    if ((getParent() instanceof GCGroup)) {
      ((GCGroup)getParent()).hideToken();
    }
    this.newX = false;
    if (getDocument().dimming)
    {
      this.myToken.setBrush(JGoBrush.lightGray);
      DimmerThread localDimmerThread = new DimmerThread(this);
      localDimmerThread.start();
    }
    executeExitActions();
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
    if ((this.oldX) && (!this.curX))
    {
      this.myToken.setBrush(JGoBrush.Null);
      if ((getParent() instanceof GCGroup)) {
        ((GCGroup)getParent()).hideToken();
      }
      if (getDocument().dimming)
      {
        this.myToken.setBrush(JGoBrush.lightGray);
        DimmerThread localDimmerThread = new DimmerThread(this);
        localDimmerThread.start();
      }
    }
  }
  
  public boolean isEnabledAlone(boolean paramBoolean)
  {
    return this.curX;
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
    return "LangRef_FC_ProcessStep";
  }
  
  public ArrayList getProcedureCalls()
  {
    return this.procedureCalls;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ProcessStep.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */