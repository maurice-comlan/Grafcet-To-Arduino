package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoLink;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoStroke;
import grafchart.util.XMLUtil;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;
import org.w3c.dom.Element;

public class ParallelSplit
  extends GrafcetObject
  implements GCIdent, Connectable
{
  private String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
  public JGoStroke myTopLine = null;
  public JGoStroke myBottomLine = null;
  public JGoStroke myInLine = null;
  public JGoStroke myOutLine1 = null;
  public JGoStroke myOutLine2 = null;
  public GCStepInPort myInPort = null;
  public GCTransitionOutPort myOutPort1 = null;
  public GCTransitionOutPort myOutPort2 = null;
  private double lineSpacing = 4.0D;
  private double outLineSpacing = 110.0D;
  
  public ParallelSplit() {}
  
  public ParallelSplit(Point paramPoint)
  {
    this();
    setSelectable(true);
    setGrabChildSelection(true);
    setDraggable(true);
    setResizable(true);
    this.myTopLine = new JGoStroke();
    this.myTopLine.addPoint(0, 0);
    this.myTopLine.addPoint(280, 0);
    this.myTopLine.setSelectable(false);
    this.myBottomLine = new JGoStroke();
    this.myBottomLine.addPoint(0, 0);
    this.myBottomLine.addPoint(280, 0);
    this.myBottomLine.setSelectable(false);
    this.myInLine = new JGoStroke();
    this.myInLine.addPoint(10, 0);
    this.myInLine.addPoint(10, 5);
    this.myInLine.setSelectable(false);
    this.myOutLine1 = new JGoStroke();
    this.myOutLine1.addPoint(20, 0);
    this.myOutLine1.addPoint(20, 5);
    this.myOutLine1.setSelectable(false);
    this.myOutLine2 = new JGoStroke();
    this.myOutLine2.addPoint(20, 0);
    this.myOutLine2.addPoint(20, 5);
    this.myOutLine2.setSelectable(false);
    this.myInPort = new GCStepInPort();
    this.myOutPort1 = new GCTransitionOutPort();
    this.myOutPort2 = new GCTransitionOutPort();
    addObjectAtTail(this.myTopLine);
    addObjectAtTail(this.myBottomLine);
    addObjectAtTail(this.myInLine);
    addObjectAtTail(this.myOutLine1);
    addObjectAtTail(this.myOutLine2);
    addObjectAtTail(this.myInPort);
    addObjectAtTail(this.myOutPort1);
    addObjectAtTail(this.myOutPort2);
    setLocation(paramPoint);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    ParallelSplit localParallelSplit = (ParallelSplit)paramJGoArea;
    localParallelSplit.myTopLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myTopLine));
    localParallelSplit.myBottomLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBottomLine));
    localParallelSplit.myInLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myInLine));
    localParallelSplit.myOutLine1 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myOutLine1));
    localParallelSplit.myOutLine2 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myOutLine2));
    localParallelSplit.myInPort = ((GCStepInPort)paramJGoCopyEnvironment.copy(this.myInPort));
    localParallelSplit.myOutPort1 = ((GCTransitionOutPort)paramJGoCopyEnvironment.copy(this.myOutPort1));
    localParallelSplit.myOutPort2 = ((GCTransitionOutPort)paramJGoCopyEnvironment.copy(this.myOutPort2));
    localParallelSplit.addObjectAtTail(localParallelSplit.myTopLine);
    localParallelSplit.addObjectAtTail(localParallelSplit.myBottomLine);
    localParallelSplit.addObjectAtTail(localParallelSplit.myInLine);
    localParallelSplit.addObjectAtTail(localParallelSplit.myOutLine1);
    localParallelSplit.addObjectAtTail(localParallelSplit.myOutLine2);
    localParallelSplit.addObjectAtTail(localParallelSplit.myInPort);
    localParallelSplit.addObjectAtTail(localParallelSplit.myOutPort1);
    localParallelSplit.addObjectAtTail(localParallelSplit.myOutPort2);
    localParallelSplit.lineSpacing = this.lineSpacing;
    localParallelSplit.outLineSpacing = this.outLineSpacing;
  }
  
  public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    paramVector1.add(this.id);
    paramVector2.add(this);
    paramElement.setAttribute("id", this.id);
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static ParallelSplit loadXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    ParallelSplit localParallelSplit = new ParallelSplit(new Point());
    String str = paramElement.getAttribute("id");
    if (Utils.getSaveVersion(paramElement) >= 2) {
      localParallelSplit.id = str;
    }
    paramVector1.add(str);
    paramVector2.add(localParallelSplit);
    XMLUtil.restoreBoundingRectAny(paramElement, localParallelSplit);
    return localParallelSplit;
  }
  
  public Point getLocation(Point paramPoint)
  {
    return this.myTopLine.getSpotLocation(2, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    this.myTopLine.setSpotLocation(2, paramInt1, paramInt2);
    layoutChildren();
  }
  
  public void layoutChildren()
  {
    Point localPoint = this.myTopLine.getSpotLocation(6);
    this.myBottomLine.setSpotLocation(2, (int)localPoint.getX(), (int)(localPoint.getY() + this.lineSpacing));
    this.myInLine.setSpotLocation(6, this.myTopLine, 2);
    localPoint = this.myBottomLine.getSpotLocation(6);
    this.myOutLine1.setSpotLocation(2, (int)Math.round(localPoint.getX() - this.outLineSpacing), localPoint.y);
    this.myOutLine2.setSpotLocation(2, (int)Math.round(localPoint.getX() + this.outLineSpacing), localPoint.y);
    this.myInPort.setSpotLocation(2, this.myInLine, 2);
    this.myOutPort1.setSpotLocation(6, this.myOutLine1, 6);
    this.myOutPort2.setSpotLocation(6, this.myOutLine2, 6);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myTopLine, this.myBottomLine, this.myInLine, this.myOutLine1, this.myOutLine2, this.myInPort, this.myOutPort1, this.myOutPort2 };
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width != getWidth()) || (paramRectangle.height != getHeight()))
    {
      this.lineSpacing *= getScaleFactorY(paramRectangle);
      this.outLineSpacing *= getScaleFactorX(paramRectangle);
    }
    super.geometryChange(paramRectangle);
  }
  
  public Dimension getMinimumSize()
  {
    int i = 20;
    int j = 20;
    return new Dimension(i, j);
  }
  
  public boolean isTransition()
  {
    return true;
  }
  
  public boolean isStep()
  {
    return true;
  }
  
  public void compileDownwards(GenericTransition paramGenericTransition)
  {
    JGoLink localJGoLink;
    JGoPort localJGoPort;
    GrafcetObject localGrafcetObject;
    Object localObject;
    HistoryNode localHistoryNode;
    for (JGoListPosition localJGoListPosition = this.myOutPort1.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myOutPort1.getNextLinkPos(localJGoListPosition))
    {
      localJGoLink = this.myOutPort1.getLinkAtPos(localJGoListPosition);
      localJGoPort = localJGoLink.getToPort();
      localGrafcetObject = (GrafcetObject)localJGoLink.getToPort().getParent();
      if ((localGrafcetObject instanceof GCStep))
      {
        localObject = (GCStep)localGrafcetObject;
        paramGenericTransition.addSucceedingStep((GrafcetObject)localObject);
      }
      if (((localGrafcetObject instanceof MacroStep)) && (!(localGrafcetObject instanceof ProcedureStep)))
      {
        localObject = (MacroStep)localGrafcetObject;
        if ((localJGoPort instanceof GCStepInPort))
        {
          paramGenericTransition.addEnterStep((MacroStep)localObject, localJGoPort);
          paramGenericTransition.addSucceedingStep((GrafcetObject)localObject);
        }
        else
        {
          localHistoryNode = new HistoryNode((MacroStep)localObject);
          paramGenericTransition.addSucceedingStep(localHistoryNode);
          paramGenericTransition.addSucceedingStep((GrafcetObject)localObject);
        }
      }
      if ((localGrafcetObject instanceof ProcedureStep))
      {
        localObject = (ProcedureStep)localGrafcetObject;
        paramGenericTransition.addSucceedingStep((GrafcetObject)localObject);
      }
      if ((localGrafcetObject instanceof ConnectionPostIn))
      {
        localObject = (ConnectionPostIn)localGrafcetObject;
        ((ConnectionPostIn)localObject).compileDownwards(paramGenericTransition);
      }
      if ((localGrafcetObject instanceof ParallelSplit))
      {
        localObject = (ParallelSplit)localGrafcetObject;
        ((ParallelSplit)localObject).compileDownwards(paramGenericTransition);
      }
    }
      JGoListPosition localJGoListPosition;
    for (localJGoListPosition = this.myOutPort2.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myOutPort2.getNextLinkPos(localJGoListPosition))
    {
      localJGoLink = this.myOutPort2.getLinkAtPos(localJGoListPosition);
      localJGoPort = localJGoLink.getToPort();
      localGrafcetObject = (GrafcetObject)localJGoLink.getToPort().getParent();
      if ((localGrafcetObject instanceof GCStep))
      {
        localObject = (GCStep)localGrafcetObject;
        paramGenericTransition.addSucceedingStep((GrafcetObject)localObject);
      }
      if (((localGrafcetObject instanceof MacroStep)) && (!(localGrafcetObject instanceof ProcedureStep)))
      {
        localObject = (MacroStep)localGrafcetObject;
        if ((localJGoPort instanceof GCStepInPort))
        {
          paramGenericTransition.addEnterStep((MacroStep)localObject, localJGoPort);
          paramGenericTransition.addSucceedingStep((GrafcetObject)localObject);
        }
        else
        {
          localHistoryNode = new HistoryNode((MacroStep)localObject);
          paramGenericTransition.addSucceedingStep(localHistoryNode);
          paramGenericTransition.addSucceedingStep((GrafcetObject)localObject);
        }
      }
      if ((localGrafcetObject instanceof ProcedureStep))
      {
        localObject = (ProcedureStep)localGrafcetObject;
        paramGenericTransition.addSucceedingStep((GrafcetObject)localObject);
      }
      if ((localGrafcetObject instanceof ConnectionPostIn))
      {
        localObject = (ConnectionPostIn)localGrafcetObject;
        ((ConnectionPostIn)localObject).compileDownwards(paramGenericTransition);
      }
      if ((localGrafcetObject instanceof ParallelSplit))
      {
        localObject = (ParallelSplit)localGrafcetObject;
        ((ParallelSplit)localObject).compileDownwards(paramGenericTransition);
      }
    }
  }
  
  public Vector getAllLinks()
  {
    Vector localVector = new Vector();
    Utils.addLinks(this.myInPort, localVector);
    Utils.addLinks(this.myOutPort1, localVector);
    Utils.addLinks(this.myOutPort2, localVector);
    return localVector;
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_ParallelSplit";
  }
  
  public String toString()
  {
    return "   ";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ParallelSplit.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */