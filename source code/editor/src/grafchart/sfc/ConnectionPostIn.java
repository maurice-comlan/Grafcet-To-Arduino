package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoLink;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import java.awt.Point;
import java.util.Vector;

public class ConnectionPostIn
  extends ConnectionPost
{
  public ConnectionPostIn() {}
  
  public ConnectionPostIn(Point paramPoint, String paramString)
  {
    super(paramPoint, paramString);
    this.myPort = new GCToPort();
    addObjectAtTail(this.myPort);
    setLocation(paramPoint);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    ConnectionPostIn localConnectionPostIn = (ConnectionPostIn)paramJGoArea;
    localConnectionPostIn.myPort = ((GCToPort)paramJGoCopyEnvironment.copy(this.myPort));
    localConnectionPostIn.addObjectAtTail(localConnectionPostIn.myPort);
  }
  
  public void layoutChildren()
  {
    super.layoutChildren();
    this.myLine.setSpotLocation(6, this.myRectangle, 2);
    if (this.myPort != null) {
      this.myPort.setSpotLocation(2, this.myLine, 2);
    }
  }
  
  public Point getLocation(Point paramPoint)
  {
    return this.myRectangle.getSpotLocation(2, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    this.myRectangle.setSpotLocation(2, paramInt1, paramInt2);
    layoutChildren();
  }
  
  public Vector getAllLinks()
  {
    Vector localVector = new Vector();
    Utils.addLinks(this.myPort, localVector);
    return localVector;
  }
  
  public void compileDownwards(GenericTransition paramGenericTransition)
  {
    if ((this.remote instanceof ConnectionPostOut))
    {
      ConnectionPostOut localConnectionPostOut = (ConnectionPostOut)this.remote;
      GCFromPort localGCFromPort = (GCFromPort)localConnectionPostOut.myPort;
      for (JGoListPosition localJGoListPosition = localGCFromPort.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = localGCFromPort.getNextLinkPos(localJGoListPosition))
      {
        JGoLink localJGoLink = localGCFromPort.getLinkAtPos(localJGoListPosition);
        JGoPort localJGoPort = localJGoLink.getToPort();
        GrafcetObject localGrafcetObject = (GrafcetObject)localJGoPort.getParent();
        Object localObject;
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
            paramGenericTransition.addEnterStep((MacroStep)localObject, localGCFromPort);
            paramGenericTransition.addSucceedingStep((GrafcetObject)localObject);
          }
          else
          {
            HistoryNode localHistoryNode = new HistoryNode((MacroStep)localObject);
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
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ConnectionPostIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */