package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPolygon;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.graphics.MyJGoImage;
import grafchart.sfc.actions.Expr;
import grafchart.sfc.actions.Goal;
import grafchart.sfc.actions.ProcCall;
import grafchart.sfc.actions.ProcParam;
import grafchart.sfc.actions.SingleExpression;
import grafchart.util.ActionCompiler;
import grafchart.util.ActionCompiler.CompileType;
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
import javax.swing.JInternalFrame;
import org.w3c.dom.Element;

public class ProcedureStep
  extends MacroStep
  implements Readable, Connectable, IconStep
{
  private String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
  public JGoPolygon myTopLeftPolygon = null;
  public JGoPolygon myTopRightPolygon = null;
  public JGoPolygon myBottomLeftPolygon = null;
  public JGoPolygon myBottomRightPolygon = null;
  public GrafcetProcedure procedure = null;
  public transient GCView viewOwner = null;
  public ExitStep exStep = null;
  public String gp = new String("");
  public String parameters = new String("");
  public static Color black = new Color(0.0F, 0.0F, 0.0F);
  public static JGoBrush blackBrush = new JGoBrush(65535, black);
  public transient ProcCall procNode = null;
  public transient ProcParam paramNode = null;
  public transient boolean isDynamicProcedureCall = false;
  
  public ProcedureStep() {}
  
  public ProcedureStep(Point paramPoint, String paramString)
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
    this.myTopLeftPolygon.setBrush(blackBrush);
    this.myTopLeftPolygon.setSelectable(false);
    this.myTopLeftPolygon.setDraggable(false);
    this.myTopRightPolygon = new JGoPolygon();
    this.myTopRightPolygon.addPoint(0, 0);
    this.myTopRightPolygon.addPoint(15, 0);
    this.myTopRightPolygon.addPoint(15, 15);
    this.myTopRightPolygon.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myTopRightPolygon.setBrush(blackBrush);
    this.myTopRightPolygon.setSelectable(false);
    this.myTopRightPolygon.setDraggable(false);
    this.myBottomLeftPolygon = new JGoPolygon();
    this.myBottomLeftPolygon.addPoint(0, 0);
    this.myBottomLeftPolygon.addPoint(15, 15);
    this.myBottomLeftPolygon.addPoint(0, 15);
    this.myBottomLeftPolygon.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myBottomLeftPolygon.setBrush(blackBrush);
    this.myBottomLeftPolygon.setSelectable(false);
    this.myBottomLeftPolygon.setDraggable(false);
    this.myBottomRightPolygon = new JGoPolygon();
    this.myBottomRightPolygon.addPoint(0, 15);
    this.myBottomRightPolygon.addPoint(15, 0);
    this.myBottomRightPolygon.addPoint(15, 15);
    this.myBottomRightPolygon.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
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
    this.myExcOutPort = new GCStepExceptionOutPort();
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
    this.myContentDocument = null;
    setLocation(paramPoint);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    ProcedureStep localProcedureStep = (ProcedureStep)paramJGoArea;
    localProcedureStep.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localProcedureStep.myTopLeftPolygon = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myTopLeftPolygon));
    localProcedureStep.myTopRightPolygon = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myTopRightPolygon));
    localProcedureStep.myBottomLeftPolygon = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myBottomLeftPolygon));
    localProcedureStep.myBottomRightPolygon = ((JGoPolygon)paramJGoCopyEnvironment.copy(this.myBottomRightPolygon));
    localProcedureStep.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localProcedureStep.myInLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myInLine));
    localProcedureStep.myOutLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myOutLine));
    localProcedureStep.myInPort = ((GCStepInPort)paramJGoCopyEnvironment.copy(this.myInPort));
    localProcedureStep.myOutPort = ((GCStepOutPort)paramJGoCopyEnvironment.copy(this.myOutPort));
    localProcedureStep.myExcOutPort = ((GCStepExceptionOutPort)paramJGoCopyEnvironment.copy(this.myExcOutPort));
    localProcedureStep.myToken = ((JGoEllipse)paramJGoCopyEnvironment.copy(this.myToken));
    localProcedureStep.myToken.setBrush(JGoBrush.Null);
    localProcedureStep.addObjectAtHead(localProcedureStep.myRectangle);
    localProcedureStep.addObjectAtTail(localProcedureStep.myTopLeftPolygon);
    localProcedureStep.addObjectAtTail(localProcedureStep.myTopRightPolygon);
    localProcedureStep.addObjectAtTail(localProcedureStep.myBottomLeftPolygon);
    localProcedureStep.addObjectAtTail(localProcedureStep.myBottomRightPolygon);
    localProcedureStep.addObjectAtTail(localProcedureStep.myName);
    localProcedureStep.addObjectAtTail(localProcedureStep.myInLine);
    localProcedureStep.addObjectAtTail(localProcedureStep.myOutLine);
    localProcedureStep.addObjectAtTail(localProcedureStep.myInPort);
    localProcedureStep.addObjectAtTail(localProcedureStep.myOutPort);
    localProcedureStep.addObjectAtTail(localProcedureStep.myExcOutPort);
    localProcedureStep.addObjectAtTail(localProcedureStep.myToken);
    if (this.icon != null)
    {
      localProcedureStep.icon = ((MyJGoImage)paramJGoCopyEnvironment.copy(this.icon));
      localProcedureStep.addObjectAtTail(localProcedureStep.icon);
    }
    localProcedureStep.gp = this.gp;
    localProcedureStep.parameters = this.parameters;
    localProcedureStep.actionText = this.actionText;
    localProcedureStep.fileName = this.fileName;
    localProcedureStep.useIcon = this.useIcon;
  }
  
  public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    paramVector1.add(this.id);
    paramVector2.add(this);
    paramElement.setAttribute("id", this.id);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("grafcetProcedure", this.gp);
    paramElement.setAttribute("parameters", this.parameters);
    paramElement.setAttribute("actionText", this.actionText);
    XMLUtil.setBool(paramElement, "useIcon", this.useIcon);
    paramElement.setAttribute("fileName", this.fileName);
    return paramElement;
  }
  
  public static ProcedureStep loadProcedureXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    ProcedureStep localProcedureStep = new ProcedureStep(new Point(), paramElement.getAttribute("name"));
    String str1 = paramElement.getAttribute("id");
    if (Utils.getSaveVersion(paramElement) >= 2) {
      localProcedureStep.id = str1;
    }
    paramVector1.add(str1);
    paramVector2.add(localProcedureStep);
    localProcedureStep.useIcon = XMLUtil.getBool(paramElement, "useIcon");
    if (localProcedureStep.useIcon) {
      localProcedureStep.setFileName();
    }
    localProcedureStep.gp = paramElement.getAttribute("grafcetProcedure");
    String str2 = paramElement.getAttribute("parameters");
    if (Utils.getSaveVersion(paramElement) < 1) {
      str2 = Utils.newlineHack(str2);
    }
    localProcedureStep.parameters = str2;
    String str3 = ";";
    if (paramElement.hasAttribute("actionText"))
    {
      str3 = paramElement.getAttribute("actionText");
      if (Utils.getSaveVersion(paramElement) < 1) {
        str3 = Utils.newlineHack(str3);
      }
    }
    localProcedureStep.actionText = str3;
    localProcedureStep.removeObject(localProcedureStep.myName);
    XMLUtil.restoreBoundingRectAny(paramElement, localProcedureStep, localProcedureStep.myRectangle, 0);
    localProcedureStep.addObjectAtTail(localProcedureStep.myName);
    localProcedureStep.layoutChildren();
    return localProcedureStep;
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
    this.myExcOutPort.setSpotLocation(8, this.myRectangle, 8);
    this.myToken.setSpotLocation(0, this.myRectangle, 0);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle, this.myTopLeftPolygon, this.myTopRightPolygon, this.myBottomLeftPolygon, this.myBottomRightPolygon, this.myInLine, this.myOutLine, this.myInPort, this.myOutPort, this.myExcOutPort, this.icon, this.myToken };
  }
  
  public int getNoScaleRight(Rectangle paramRectangle)
  {
    return paramRectangle.x + paramRectangle.width - this.myRectangle.getSpotLocation(4).x;
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
    this.myToken.setBrush(JGoBrush.black);
    if ((getParent() instanceof GCGroup)) {
      ((GCGroup)getParent()).showToken();
    }
    executeStoredActions();
    this.newX = true;
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
      else
      {
        this.procedure = Utils.referencableToGrafcetProcedure(this.procNode.getExpr().runtimeDecl());
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
        this.myContentDocument = ((GCDocument)localObject1);
        Editor.singleton.compileDocument(this.myContentDocument, false);
        this.myContentDocument.setProcedure(this.procedure);
        this.myContentDocument.setName(this.procedure.getName());
        this.procedure.getCalls().add(this.myContentDocument);
        localObject2 = getDocument();
        this.myContentDocument.setModificationProperty(((GCDocument)localObject2).isModifiable());
        this.myContentDocument.setSpeed(((GCDocument)localObject2).getTickTime());
        this.myContentDocument.initializeDocument((GCDocument)localObject2, ((GCDocument)localObject2).isSimulating());
        JGoListPosition localJGoListPosition = this.myContentDocument.getFirstObjectPos();
        for (JGoObject localJGoObject = this.myContentDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = this.myContentDocument.getObjectAtPos(localJGoListPosition))
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
          localJGoListPosition = this.myContentDocument.getNextObjectPos(localJGoListPosition);
        }
      }
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
    Object localObject1;
    if (getDocument().dimming)
    {
      this.myToken.setBrush(JGoBrush.lightGray);
      localObject1 = new DimmerThread(this);
      ((DimmerThread)localObject1).start();
    }
    if (this.myContentDocument != null)
    {
      localObject1 = this.myContentDocument.getFirstObjectPos();
      for (JGoObject localJGoObject = this.myContentDocument.getObjectAtPos((JGoListPosition)localObject1); (localJGoObject != null) && (localObject1 != null); localJGoObject = this.myContentDocument.getObjectAtPos((JGoListPosition)localObject1))
      {
        Object localObject2;
        if ((localJGoObject instanceof ExitStep))
        {
          localObject2 = (ExitStep)localJGoObject;
          ((ExitStep)localObject2).deactivate();
        }
        if ((localJGoObject instanceof StepFusionSet))
        {
          localObject2 = (StepFusionSet)localJGoObject;
          Iterator localIterator = ((StepFusionSet)localObject2).steps.iterator();
          while (localIterator.hasNext())
          {
            GrafcetObject localGrafcetObject = (GrafcetObject)localIterator.next();
            int i = localGrafcetObject.fusionSets.indexOf(localObject2);
            if (i != -1) {
              localGrafcetObject.fusionSets.remove(i);
            }
          }
          ((StepFusionSet)localObject2).steps.clear();
        }
        localObject1 = this.myContentDocument.getNextObjectPos((JGoListPosition)localObject1);
      }
      executeExitActions();
      if (this.myContentDocument.proc != null) {
        this.myContentDocument.proc.getCalls().remove(this.myContentDocument);
      }
      if (this.frame != null)
      {
        try
        {
          this.frame.setClosed(true);
        }
        catch (Exception localException1) {}
        this.frame = null;
        this.viewOwner.getBasicApp().setCurrentView(this.viewOwner);
      }
      if (this.myContentDocument.frame != null)
      {
        try
        {
          this.myContentDocument.frame.setClosed(true);
        }
        catch (Exception localException2) {}
        this.myContentDocument.frame = null;
        this.viewOwner.getBasicApp().setCurrentView(this.viewOwner);
      }
      this.myContentDocument.removeXML();
      this.myContentDocument = null;
      this.exStep = null;
    }
  }
  
  public void deactivateStrong()
  {
    this.myToken.setBrush(JGoBrush.Null);
    this.newX = false;
    if (getDocument().dimming)
    {
      this.myToken.setBrush(JGoBrush.lightGray);
      DimmerThread localDimmerThread = new DimmerThread(this);
      localDimmerThread.start();
    }
    deactivateBody(this.myContentDocument);
    executeAbortiveActions();
    if ((this.myContentDocument != null) && (this.myContentDocument.proc != null)) {
      this.myContentDocument.proc.getCalls().remove(this.myContentDocument);
    }
    this.exStep = null;
    if (this.frame != null)
    {
      try
      {
        this.frame.setClosed(true);
      }
      catch (Exception localException1) {}
      this.frame = null;
      this.viewOwner.getBasicApp().setCurrentView(this.viewOwner);
    }
    if ((this.myContentDocument != null) && (this.myContentDocument.frame != null))
    {
      try
      {
        this.myContentDocument.frame.setClosed(true);
      }
      catch (Exception localException2) {}
      this.myContentDocument.frame = null;
      this.viewOwner.getBasicApp().setCurrentView(this.viewOwner);
    }
    this.myContentDocument = null;
  }
  
  private void deactivateBody(GCDocument paramGCDocument)
  {
    if (paramGCDocument != null)
    {
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
            ((GCStep)localObject).executeAbortiveActions();
          }
          ((GCStep)localObject).curX = false;
          ((GCStep)localObject).newX = false;
        }
        if ((localJGoObject instanceof MacroStep))
        {
          localObject = (MacroStep)localJGoObject;
          if (((MacroStep)localObject).curX)
          {
            ((MacroStep)localObject).deactivateStrong();
            ((MacroStep)localObject).curX = false;
          }
        }
        localJGoListPosition = paramGCDocument.getNextObjectPos(localJGoListPosition);
      }
    }
  }
  
  public boolean isEnabledAlone(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return this.curX;
    }
    return (this.curX) && ((this.exStep == null) || (this.exStep.curX));
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
  
  public Vector getAllLinks()
  {
    Vector localVector = new Vector();
    Utils.addLinks(this.myInPort, localVector);
    Utils.addLinks(this.myOutPort, localVector);
    Utils.addLinks(this.myHistoryPort, localVector);
    Utils.addLinks(this.myExcOutPort, localVector);
    return localVector;
  }
  
  public String getName()
  {
    return this.myName.getText();
  }
  
  public double getSeconds()
  {
    return this.timer * getDocument().getTickTime() / 1000.0D;
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_ProcedureStep";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ProcedureStep.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */