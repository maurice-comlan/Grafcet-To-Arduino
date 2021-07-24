package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoLink;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.transitions.Start;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;
import org.w3c.dom.Element;

public class ExceptionTransition
  extends GenericTransition
  implements GCIdent, Connectable, Helpable
{
  private String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
  protected JGoRectangle myRectangle = null;
  protected JGoStroke myInLine = null;
  protected JGoStroke myOutLine = null;
  protected ExceptionTransitionInPort myInPort = null;
  protected GCTransitionOutPort myOutPort = null;
  private JGoText myPriority = null;
  private ArrayList<GrafcetObject> succeedingSteps = new ArrayList();
  protected boolean condition = false;
  protected boolean oldCondition = false;
  static Color red = new Color(1.0F, 0.0F, 0.0F);
  static Color green = new Color(0.0F, 1.0F, 0.0F);
  static JGoBrush redSolidBrush = new JGoBrush(65535, red);
  static JGoBrush greenSolidBrush = new JGoBrush(65535, green);
  static JGoBrush noFill = new JGoBrush();
  
  public ExceptionTransition() {}
  
  public ExceptionTransition(Point paramPoint, String paramString1, String paramString2)
  {
    this();
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myRectangle = new JGoRectangle();
    this.myRectangle.setSize(5, 30);
    this.myRectangle.setPen(new JGoPen(65535, 3, new Color(0.0F, 0.0F, 0.0F)));
    this.myRectangle.setSelectable(false);
    this.myRectangle.setDraggable(false);
    this.myCondition = new JGoText(paramString1);
    this.myCondition.setSelectable(true);
    this.myCondition.setEditable(false);
    this.myCondition.setEditOnSingleClick(false);
    this.myCondition.setDraggable(false);
    this.myCondition.setAlignment(1);
    this.myCondition.setAutoResize(true);
    this.myCondition.setClipping(false);
    this.myCondition.setTransparent(true);
    this.myCondition.setBold(true);
    this.myCondition.setFontSize(18);
    this.myCondition.setFaceName("Monospaced");
    this.myPriority = new JGoText(paramString2);
    this.myPriority.setSelectable(false);
    this.myPriority.setEditable(false);
    this.myPriority.setEditOnSingleClick(false);
    this.myPriority.setDraggable(false);
    this.myPriority.setAlignment(1);
    this.myPriority.setAutoResize(true);
    this.myPriority.setClipping(false);
    this.myPriority.setTransparent(true);
    this.myPriority.setBold(true);
    this.myPriority.setFontSize(12);
    this.myPriority.setFaceName("Monospaced");
    this.myInLine = new JGoStroke();
    this.myOutLine = new JGoStroke();
    this.myOutPort = new GCTransitionOutPort();
    this.myInPort = new ExceptionTransitionInPort();
    this.myInLine.addPoint(0, 0);
    this.myInLine.addPoint(5, 0);
    this.myInLine.setSelectable(false);
    this.myInLine.setPen(new JGoPen(65535, 3, new Color(0.0F, 0.0F, 0.0F)));
    this.myOutLine.addPoint(0, 0);
    this.myOutLine.addPoint(5, 0);
    this.myOutLine.setSelectable(false);
    this.myOutPort.setFromSpot(8);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myCondition);
    addObjectAtTail(this.myInLine);
    addObjectAtTail(this.myOutLine);
    addObjectAtTail(this.myInPort);
    addObjectAtTail(this.myOutPort);
    this.conditionString = paramString1;
    setLocation(paramPoint);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    ExceptionTransition localExceptionTransition = (ExceptionTransition)paramJGoArea;
    localExceptionTransition.condition = this.condition;
    localExceptionTransition.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localExceptionTransition.myCondition = ((JGoText)paramJGoCopyEnvironment.copy(this.myCondition));
    localExceptionTransition.myPriority = ((JGoText)paramJGoCopyEnvironment.copy(this.myPriority));
    localExceptionTransition.myInLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myInLine));
    localExceptionTransition.myOutLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myOutLine));
    localExceptionTransition.myInPort = ((ExceptionTransitionInPort)paramJGoCopyEnvironment.copy(this.myInPort));
    localExceptionTransition.myOutPort = ((GCTransitionOutPort)paramJGoCopyEnvironment.copy(this.myOutPort));
    localExceptionTransition.addObjectAtHead(localExceptionTransition.myRectangle);
    localExceptionTransition.addObjectAtTail(localExceptionTransition.myCondition);
    localExceptionTransition.addObjectAtTail(localExceptionTransition.myPriority);
    localExceptionTransition.addObjectAtTail(localExceptionTransition.myInLine);
    localExceptionTransition.addObjectAtTail(localExceptionTransition.myOutLine);
    localExceptionTransition.addObjectAtTail(localExceptionTransition.myInPort);
    localExceptionTransition.addObjectAtTail(localExceptionTransition.myOutPort);
    localExceptionTransition.conditionString = this.conditionString;
    localExceptionTransition.conditionVisible = this.conditionVisible;
    if (!this.conditionVisible) {
      localExceptionTransition.hideCondition();
    }
    localExceptionTransition.priority = this.priority;
    localExceptionTransition.hasPriority = this.hasPriority;
    if (!this.hasPriority) {
      localExceptionTransition.hidePriority();
    }
  }
  
  public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    paramVector1.add(this.id);
    paramVector2.add(this);
    paramElement.setAttribute("id", this.id);
    paramElement.setAttribute("actionText", this.conditionString);
    XMLUtil.setBool(paramElement, "conditionVisible", this.conditionVisible);
    if (this.hasPriority)
    {
      XMLUtil.setInt(paramElement, "priority", this.priority);
      removeObject(this.myPriority);
    }
    if (this.conditionVisible) {
      removeObject(this.myCondition);
    }
    XMLUtil.saveBoundingRect(paramElement, this);
    if (this.conditionVisible) {
      addObjectAtTail(this.myCondition);
    }
    if (this.hasPriority) {
      addObjectAtTail(this.myPriority);
    }
    return paramElement;
  }
  
  public static ExceptionTransition loadXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    ExceptionTransition localExceptionTransition = new ExceptionTransition(new Point(), paramElement.getAttribute("actionText"), "");
    String str = paramElement.getAttribute("id");
    if (Utils.getSaveVersion(paramElement) >= 2) {
      localExceptionTransition.id = str;
    }
    paramVector1.add(str);
    paramVector2.add(localExceptionTransition);
    if (paramElement.hasAttribute("priority"))
    {
      localExceptionTransition.priority = Integer.parseInt(paramElement.getAttribute("priority"));
      localExceptionTransition.hasPriority = true;
      localExceptionTransition.showPriority();
    }
    else
    {
      localExceptionTransition.hidePriority();
    }
    if (XMLUtil.getBool(paramElement, "conditionVisible", true)) {
      localExceptionTransition.showCondition();
    } else {
      localExceptionTransition.hideCondition();
    }
    if (localExceptionTransition.conditionVisible) {
      localExceptionTransition.removeObject(localExceptionTransition.myCondition);
    }
    if (localExceptionTransition.hasPriority) {
      localExceptionTransition.removeObject(localExceptionTransition.myPriority);
    }
    XMLUtil.restoreBoundingRectAny(paramElement, localExceptionTransition);
    if (localExceptionTransition.conditionVisible) {
      localExceptionTransition.addObjectAtTail(localExceptionTransition.myCondition);
    }
    if (localExceptionTransition.hasPriority) {
      localExceptionTransition.addObjectAtTail(localExceptionTransition.myPriority);
    }
    return localExceptionTransition;
  }
  
  public Point getLocation(Point paramPoint)
  {
    return this.myRectangle.getSpotLocation(4, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    this.myRectangle.setSpotLocation(4, paramInt1, paramInt2);
    layoutChildren();
  }
  
  public void layoutChildren()
  {
    Point localPoint1 = this.myRectangle.getSpotLocation(5);
    Point localPoint2 = this.myRectangle.getSpotLocation(3);
    this.myInLine.setSpotLocation(8, this.myRectangle, 4);
    Point localPoint3 = this.myInLine.getSpotLocation(4);
    this.myCondition.setSpotLocation(8, localPoint3.x + 5, localPoint1.y - 3);
    this.myPriority.setSpotLocation(8, localPoint2.x + 4, localPoint2.y);
    this.myOutLine.setSpotLocation(4, this.myRectangle, 8);
    this.myInPort.setSpotLocation(4, this.myInLine, 4);
    this.myOutPort.setSpotLocation(8, this.myOutLine, 8);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle, this.myInLine, this.myOutLine, this.myInPort, this.myOutPort };
  }
  
  public Dimension getMinimumSize()
  {
    if (this.conditionVisible)
    {
      int i = (int)Math.ceil(this.myCondition.getWidth() + 20);
      int j = Math.max(15, (int)Math.ceil(this.myCondition.getHeight() + 10));
      return new Dimension(i, j);
    }
    return new Dimension(15, 15);
  }
  
  public int getNoScaleBottom(Rectangle paramRectangle)
  {
    return paramRectangle.y + paramRectangle.height - this.myRectangle.getSpotLocation(6).y;
  }
  
  public int getNoScaleRight(Rectangle paramRectangle)
  {
    return paramRectangle.x + paramRectangle.width - this.myInLine.getSpotLocation(4).x;
  }
  
  public boolean isTransition()
  {
    return true;
  }
  
  public boolean isStep()
  {
    return false;
  }
  
  public void addSucceedingStep(GrafcetObject paramGrafcetObject)
  {
    this.succeedingSteps.add(paramGrafcetObject);
  }
  
  private void addPrecedingStep(MacroStep paramMacroStep)
  {
    this.precedingSteps.add(paramMacroStep);
  }
  
  public void compileStructure()
  {
    this.precedingSteps.clear();
    this.succeedingSteps.clear();
    if (this.myInPort.hasNoLinks()) {
      Editor.giveLightWarning("Unconnected exception transition in " + getDocument().getFullName());
    }
    JGoLink localJGoLink;
    Object localObject1;
    Object localObject2;
    for (JGoListPosition localJGoListPosition = this.myInPort.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myInPort.getNextLinkPos(localJGoListPosition))
    {
      localJGoLink = this.myInPort.getLinkAtPos(localJGoListPosition);
      localObject1 = (GrafcetObject)localJGoLink.getFromPort().getParent();
      if ((localObject1 instanceof MacroStep))
      {
        localObject2 = (MacroStep)localObject1;
        addPrecedingStep((MacroStep)localObject2);
      }
    }
    if (this.myOutPort.hasNoLinks()) {
      Editor.giveLightWarning("Unconnected exception transition in " + getDocument().getFullName());
    }
      JGoListPosition localJGoListPosition;
    for (localJGoListPosition = this.myOutPort.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myOutPort.getNextLinkPos(localJGoListPosition))
    {
      localJGoLink = this.myOutPort.getLinkAtPos(localJGoListPosition);
      localObject1 = localJGoLink.getToPort();
      localObject2 = (GrafcetObject)localJGoLink.getToPort().getParent();
      Object localObject3;
      if ((localObject2 instanceof GCStep))
      {
        localObject3 = (GCStep)localObject2;
        addSucceedingStep((GrafcetObject)localObject3);
      }
      if ((localObject2 instanceof ParallelSplit))
      {
        localObject3 = (ParallelSplit)localObject2;
        ((ParallelSplit)localObject3).compileDownwards(this);
      }
      if ((localObject2 instanceof ConnectionPostIn))
      {
        localObject3 = (ConnectionPostIn)localObject2;
        ((ConnectionPostIn)localObject3).compileDownwards(this);
      }
      if (((localObject2 instanceof MacroStep)) && (!(localObject2 instanceof ProcedureStep)))
      {
        localObject3 = (MacroStep)localObject2;
        addEnterStep((MacroStep)localObject3, (JGoPort)localObject1);
        addSucceedingStep((GrafcetObject)localObject3);
      }
      if ((localObject2 instanceof ProcedureStep)) {
        addSucceedingStep((GrafcetObject)localObject2);
      }
    }
  }
  
  public void addEnterStep(MacroStep paramMacroStep, JGoPort paramJGoPort)
  {
    int i = 0;
    int j = 0;
    Object localObject1 = paramMacroStep.inputPorts.iterator();
    while ((j == 0) && (((Iterator)localObject1).hasNext()))
    {
      Input localObject2 = (Input)((Iterator)localObject1).next();
      i++;
      if (((Input)localObject2).port == paramJGoPort) {
        j = 1;
      }
    }
    localObject1 = new ArrayList();
    Object localObject2 = paramMacroStep.myContentDocument;
    JGoListPosition localJGoListPosition = ((GCDocument)localObject2).getFirstObjectPos();
    for (JGoObject localJGoObject = ((GCDocument)localObject2).getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = ((GCDocument)localObject2).getObjectAtPos(localJGoListPosition))
    {
      if ((localJGoObject instanceof EnterStep)) {
        ((ArrayList)localObject1).add(localJGoObject);
      }
      localJGoListPosition = ((GCDocument)localObject2).getNextObjectPos(localJGoListPosition);
    }
    Collections.sort((List)localObject1, new Comparator()
    {
      public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        return (int)(((JGoObject)paramAnonymousObject1).getLocation().getX() - ((JGoObject)paramAnonymousObject2).getLocation().getX());
      }
    });
    EnterStep localEnterStep = (EnterStep)((ArrayList)localObject1).remove(i - 1);
    addSucceedingStep(localEnterStep);
  }
  
  public void addExitStep(MacroStep paramMacroStep, JGoPort paramJGoPort) {}
  
  public void preTestAndFire()
  {
    int i = 0;
    this.oldCondition = this.condition;
    this.condition = this.node.evaluateBoolean();
    if ((this.condition) || (this.forced))
    {
      if (!this.oldCondition) {
        this.myRectangle.setBrush(greenSolidBrush);
      }
      i = 1;
      Iterator localIterator = this.precedingSteps.iterator();
      while (localIterator.hasNext())
      {
        GrafcetObject localGrafcetObject = (GrafcetObject)localIterator.next();
        MacroStep localMacroStep = (MacroStep)localGrafcetObject;
        i = (i != 0) && (localMacroStep.curX) ? 1 : 0;
      }
    }
    else if (this.oldCondition)
    {
      this.myRectangle.setBrush(redSolidBrush);
    }
    if (((this.condition) || (this.forced)) && (i != 0))
    {
      if (this.forced)
      {
        this.forced = false;
        if (!this.condition) {
          this.myRectangle.setBrush(redSolidBrush);
        }
      }
      this.markedForFire = true;
    }
  }
  
  public void testAndFire()
  {
    if (this.markedForFire)
    {
      this.markedForFire = false;
      Iterator localIterator = this.precedingSteps.iterator();
      GrafcetObject localGrafcetObject;
      while (localIterator.hasNext())
      {
        localGrafcetObject = (GrafcetObject)localIterator.next();
        MacroStep localMacroStep = (MacroStep)localGrafcetObject;
        localMacroStep.deactivateStrong();
      }
      localIterator = this.succeedingSteps.iterator();
      while (localIterator.hasNext())
      {
        localGrafcetObject = (GrafcetObject)localIterator.next();
        localGrafcetObject.activate();
      }
    }
  }
  
  public void initialize()
  {
    this.condition = this.node.evaluateBoolean();
    this.oldCondition = this.condition;
    if (this.condition) {
      this.myRectangle.setBrush(greenSolidBrush);
    } else {
      this.myRectangle.setBrush(redSolidBrush);
    }
  }
  
  public String getToolTipText()
  {
    if (this.conditionVisible) {
      return null;
    }
    return this.conditionString;
  }
  
  public void stop()
  {
    this.myRectangle.setBrush(noFill);
    this.forced = false;
  }
  
  public String getLabelText()
  {
    return this.myCondition.getText();
  }
  
  public void setTextColor(Color paramColor)
  {
    this.myCondition.setTextColor(paramColor);
  }
  
  public void hideCondition()
  {
    this.conditionString = this.myCondition.getText();
    removeObject(this.myCondition);
    this.conditionVisible = false;
    layoutChildren();
  }
  
  public void showCondition()
  {
    this.myCondition.setText(this.conditionString);
    addObjectAtTail(this.myCondition);
    this.conditionVisible = true;
    layoutChildren();
  }
  
  public void hidePriority()
  {
    removeObject(this.myPriority);
    layoutChildren();
  }
  
  public void showPriority()
  {
    this.myPriority.setText("" + this.priority);
    addObjectAtTail(this.myPriority);
    layoutChildren();
  }
  
  public Vector getAllLinks()
  {
    Vector localVector = new Vector();
    Utils.addLinks(this.myInPort, localVector);
    Utils.addLinks(this.myOutPort, localVector);
    return localVector;
  }
  
  public ExceptionTransitionInPort getInPort()
  {
    return this.myInPort;
  }
  
  public GCTransitionOutPort getOutPort()
  {
    return this.myOutPort;
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_ExceptionTransition";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ExceptionTransition.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */