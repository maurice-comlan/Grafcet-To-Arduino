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

public class ConnectionPostOut
  extends ConnectionPost
{
  public ConnectionPostOut() {}
  
  public ConnectionPostOut(Point paramPoint, String paramString)
  {
    super(paramPoint, paramString);
    this.myPort = new GCFromPort();
    addObjectAtTail(this.myPort);
    setLocation(paramPoint);
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    ConnectionPostOut localConnectionPostOut = (ConnectionPostOut)paramJGoArea;
    localConnectionPostOut.myPort = ((GCFromPort)paramJGoCopyEnvironment.copy(this.myPort));
    localConnectionPostOut.addObjectAtTail(localConnectionPostOut.myPort);
  }
  
  public void layoutChildren()
  {
    super.layoutChildren();
    this.myLine.setSpotLocation(2, this.myRectangle, 6);
    if (this.myPort != null) {
      this.myPort.setSpotLocation(6, this.myLine, 6);
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
  
  public void compileUpwards(GCTransition paramGCTransition)
  {
    if ((this.remote instanceof ConnectionPostIn))
    {
      ConnectionPostIn localConnectionPostIn = (ConnectionPostIn)this.remote;
      GCToPort localGCToPort = (GCToPort)localConnectionPostIn.myPort;
      for (JGoListPosition localJGoListPosition = localGCToPort.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = localGCToPort.getNextLinkPos(localJGoListPosition))
      {
        JGoLink localJGoLink = localGCToPort.getLinkAtPos(localJGoListPosition);
        JGoPort localJGoPort = localJGoLink.getFromPort();
        GrafcetObject localGrafcetObject = (GrafcetObject)localJGoPort.getParent();
        Object localObject;
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
        if ((localGrafcetObject instanceof ConnectionPostOut))
        {
          localObject = (ConnectionPostOut)localGrafcetObject;
          ((ConnectionPostOut)localObject).compileUpwards(paramGCTransition);
        }
        if ((localGrafcetObject instanceof ProcedureStep))
        {
          localObject = (ProcedureStep)localGrafcetObject;
          paramGCTransition.addPrecedingStep((GrafcetObject)localObject);
        }
        if ((localGrafcetObject instanceof ParallelJoin))
        {
          localObject = (ParallelJoin)localGrafcetObject;
          ((ParallelJoin)localObject).compileUpwards(paramGCTransition);
        }
      }
    }
  }
  
  public String toString()
  {
    return getName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ConnectionPostOut.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */