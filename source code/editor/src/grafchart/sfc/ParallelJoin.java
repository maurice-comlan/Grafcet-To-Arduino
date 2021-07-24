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

public class ParallelJoin
  extends GrafcetObject
  implements GCIdent, Connectable
{
  private String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
  public JGoStroke myTopLine = null;
  public JGoStroke myBottomLine = null;
  public JGoStroke myInLine1 = null;
  public JGoStroke myInLine2 = null;
  public JGoStroke myOutLine = null;
  public GCTransitionInPort myInPort1 = null;
  public GCTransitionInPort myInPort2 = null;
  public GCStepOutPort myOutPort = null;
  private double lineSpacing = 4.0D;
  private double inLineSpacing = 110.0D;
  
  public ParallelJoin() {}
  
  public ParallelJoin(Point paramPoint)
  {
    this();
    setSelectable(true);
    setGrabChildSelection(true);
    setDraggable(true);
    setResizable(true);
    this.myTopLine = new JGoStroke();
    this.myBottomLine = new JGoStroke();
    this.myInLine1 = new JGoStroke();
    this.myInLine2 = new JGoStroke();
    this.myOutLine = new JGoStroke();
    this.myInPort1 = new GCTransitionInPort();
    this.myInPort2 = new GCTransitionInPort();
    this.myOutPort = new GCStepOutPort();
    this.myTopLine.addPoint(0, 0);
    this.myTopLine.addPoint(280, 0);
    this.myTopLine.setSelectable(false);
    this.myBottomLine.addPoint(0, 0);
    this.myBottomLine.addPoint(280, 0);
    this.myBottomLine.setSelectable(false);
    this.myInLine1.addPoint(10, 0);
    this.myInLine1.addPoint(10, 5);
    this.myInLine1.setSelectable(false);
    this.myInLine2.addPoint(10, 0);
    this.myInLine2.addPoint(10, 5);
    this.myInLine2.setSelectable(false);
    this.myOutLine.addPoint(20, 0);
    this.myOutLine.addPoint(20, 5);
    this.myOutLine.setSelectable(false);
    addObjectAtTail(this.myTopLine);
    addObjectAtTail(this.myBottomLine);
    addObjectAtTail(this.myInLine1);
    addObjectAtTail(this.myInLine2);
    addObjectAtTail(this.myOutLine);
    addObjectAtTail(this.myInPort1);
    addObjectAtTail(this.myInPort2);
    addObjectAtTail(this.myOutPort);
    setLocation(paramPoint);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    ParallelJoin localParallelJoin = (ParallelJoin)paramJGoArea;
    localParallelJoin.myTopLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myTopLine));
    localParallelJoin.myBottomLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myBottomLine));
    localParallelJoin.myInLine1 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myInLine1));
    localParallelJoin.myInLine2 = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myInLine2));
    localParallelJoin.myOutLine = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myOutLine));
    localParallelJoin.myInPort1 = ((GCTransitionInPort)paramJGoCopyEnvironment.copy(this.myInPort1));
    localParallelJoin.myInPort2 = ((GCTransitionInPort)paramJGoCopyEnvironment.copy(this.myInPort2));
    localParallelJoin.myOutPort = ((GCStepOutPort)paramJGoCopyEnvironment.copy(this.myOutPort));
    localParallelJoin.addObjectAtTail(localParallelJoin.myTopLine);
    localParallelJoin.addObjectAtTail(localParallelJoin.myBottomLine);
    localParallelJoin.addObjectAtTail(localParallelJoin.myInLine1);
    localParallelJoin.addObjectAtTail(localParallelJoin.myInLine2);
    localParallelJoin.addObjectAtTail(localParallelJoin.myOutLine);
    localParallelJoin.addObjectAtTail(localParallelJoin.myInPort1);
    localParallelJoin.addObjectAtTail(localParallelJoin.myInPort2);
    localParallelJoin.addObjectAtTail(localParallelJoin.myOutPort);
    localParallelJoin.lineSpacing = this.lineSpacing;
    localParallelJoin.inLineSpacing = this.inLineSpacing;
  }
  
  public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    paramVector1.add(this.id);
    paramVector2.add(this);
    paramElement.setAttribute("id", this.id);
    XMLUtil.saveBoundingRect(paramElement, this);
    return paramElement;
  }
  
  public static ParallelJoin loadXML(Element paramElement, Vector paramVector1, Vector paramVector2)
  {
    ParallelJoin localParallelJoin = new ParallelJoin(new Point());
    String str = paramElement.getAttribute("id");
    if (Utils.getSaveVersion(paramElement) >= 2) {
      localParallelJoin.id = str;
    }
    paramVector1.add(str);
    paramVector2.add(localParallelJoin);
    XMLUtil.restoreBoundingRectAny(paramElement, localParallelJoin);
    return localParallelJoin;
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
    localPoint = this.myTopLine.getSpotLocation(2);
    this.myInLine1.setSpotLocation(6, (int)Math.round(localPoint.getX() - this.inLineSpacing), localPoint.y);
    this.myInLine2.setSpotLocation(6, (int)Math.round(localPoint.getX() + this.inLineSpacing), localPoint.y);
    this.myOutLine.setSpotLocation(2, this.myBottomLine, 6);
    this.myInPort1.setSpotLocation(2, this.myInLine1, 2);
    this.myInPort2.setSpotLocation(2, this.myInLine2, 2);
    this.myOutPort.setSpotLocation(6, this.myOutLine, 6);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myTopLine, this.myBottomLine, this.myOutLine, this.myInLine1, this.myInLine2, this.myOutPort, this.myInPort1, this.myInPort2 };
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width != getWidth()) || (paramRectangle.height != getHeight()))
    {
      this.lineSpacing *= getScaleFactorY(paramRectangle);
      this.inLineSpacing *= getScaleFactorX(paramRectangle);
    }
    super.geometryChange(paramRectangle);
  }
  
  public Dimension getMinimumSize()
  {
    int i = 25;
    int j = 25;
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
  
  public void compileUpwards(GCTransition paramGCTransition)
  {
    JGoLink localJGoLink;
    JGoPort localJGoPort;
    GrafcetObject localGrafcetObject;
    Object localObject;
    for (JGoListPosition localJGoListPosition = this.myInPort1.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myInPort1.getNextLinkPos(localJGoListPosition))
    {
      localJGoLink = this.myInPort1.getLinkAtPos(localJGoListPosition);
      localJGoPort = localJGoLink.getFromPort();
      localGrafcetObject = (GrafcetObject)localJGoLink.getFromPort().getParent();
      if ((localGrafcetObject instanceof GCStep))
      {
        localObject = (GCStep)localGrafcetObject;
        paramGCTransition.addPrecedingStep((GrafcetObject)localObject);
      }
      if (((localGrafcetObject instanceof MacroStep)) && (!(localGrafcetObject instanceof ProcedureStep)))
      {
        localObject = (MacroStep)localGrafcetObject;
        paramGCTransition.addExitStep((MacroStep)localObject, localJGoPort);
        paramGCTransition.addPrecedingStep((GrafcetObject)localObject);
      }
      if ((localGrafcetObject instanceof ProcedureStep))
      {
        localObject = (ProcedureStep)localGrafcetObject;
        paramGCTransition.addPrecedingStep((GrafcetObject)localObject);
      }
      if ((localGrafcetObject instanceof ConnectionPostOut))
      {
        localObject = (ConnectionPostOut)localGrafcetObject;
        ((ConnectionPostOut)localObject).compileUpwards(paramGCTransition);
      }
      if ((localGrafcetObject instanceof ParallelJoin))
      {
        localObject = (ParallelJoin)localGrafcetObject;
        ((ParallelJoin)localObject).compileUpwards(paramGCTransition);
      }
    }
      JGoListPosition localJGoListPosition;
    for (localJGoListPosition = this.myInPort2.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myInPort2.getNextLinkPos(localJGoListPosition))
    {
      localJGoLink = this.myInPort2.getLinkAtPos(localJGoListPosition);
      localJGoPort = localJGoLink.getFromPort();
      localGrafcetObject = (GrafcetObject)localJGoLink.getFromPort().getParent();
      if ((localGrafcetObject instanceof GCStep))
      {
        localObject = (GCStep)localGrafcetObject;
        paramGCTransition.addPrecedingStep((GrafcetObject)localObject);
      }
      if (((localGrafcetObject instanceof MacroStep)) && (!(localGrafcetObject instanceof ProcedureStep)))
      {
        localObject = (MacroStep)localGrafcetObject;
        paramGCTransition.addExitStep((MacroStep)localObject, localJGoPort);
        paramGCTransition.addPrecedingStep((GrafcetObject)localObject);
      }
      if ((localGrafcetObject instanceof ProcedureStep))
      {
        localObject = (ProcedureStep)localGrafcetObject;
        paramGCTransition.addPrecedingStep((GrafcetObject)localObject);
      }
      if ((localGrafcetObject instanceof ConnectionPostOut))
      {
        localObject = (ConnectionPostOut)localGrafcetObject;
        ((ConnectionPostOut)localObject).compileUpwards(paramGCTransition);
      }
      if ((localGrafcetObject instanceof ParallelJoin))
      {
        localObject = (ParallelJoin)localGrafcetObject;
        ((ParallelJoin)localObject).compileUpwards(paramGCTransition);
      }
    }
  }
  
  public Vector getAllLinks()
  {
    Vector localVector = new Vector();
    Utils.addLinks(this.myInPort1, localVector);
    Utils.addLinks(this.myInPort2, localVector);
    Utils.addLinks(this.myOutPort, localVector);
    return localVector;
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_ParallelJoin";
  }
  
  public String toString()
  {
    return "   ";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ParallelJoin.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */