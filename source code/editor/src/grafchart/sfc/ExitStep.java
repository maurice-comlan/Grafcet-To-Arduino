package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExitStep
  extends GCStep
  implements GCIdent, Connectable
{
  public JGoStroke myArrow = null;
  
  public ExitStep() {}
  
  public ExitStep(Point paramPoint, String paramString)
  {
    super(paramPoint, paramString);
    this.myArrow = new JGoStroke();
    this.myArrow.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myArrow.addPoint(20, 0);
    this.myArrow.addPoint(40, 0);
    this.myArrow.addPoint(40, 10);
    this.myArrow.addPoint(50, 10);
    this.myArrow.addPoint(30, 20);
    this.myArrow.addPoint(10, 10);
    this.myArrow.addPoint(20, 10);
    this.myArrow.addPoint(20, 0);
    this.myArrow.setSelectable(false);
    this.myActionRectangle.setSize(80, 50);
    removeObject(this.myOutLine);
    removeObject(this.myOutPort);
    addObjectAtTail(this.myArrow);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    ExitStep localExitStep = (ExitStep)paramJGoArea;
    localExitStep.removeObject(localExitStep.myOutLine);
    localExitStep.removeObject(localExitStep.myOutPort);
    localExitStep.myArrow = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myArrow));
    localExitStep.addObjectAtTail(localExitStep.myArrow);
  }
  
  public void layoutChildren()
  {
    super.layoutChildren();
    if (this.myArrow != null) {
      this.myArrow.setSpotLocation(2, this.myRectangle, 6);
    }
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    ArrayList localArrayList = new ArrayList(Arrays.asList(super.getGeometryChangeObjects()));
    localArrayList.remove(this.myOutLine);
    localArrayList.remove(this.myOutPort);
    localArrayList.add(this.myArrow);
    JGoObject[] arrayOfJGoObject = new JGoObject[localArrayList.size()];
    return (JGoObject[])localArrayList.toArray(arrayOfJGoObject);
  }
  
  public void changeState()
  {
    if ((this.curX) && (!this.oldX) && (getDocument().terminateWhenReady))
    {
      deactivate();
      executeNormalActions(false);
      getDocument().removeXML();
      getDocument().stopThread();
    }
    else
    {
      super.changeState();
    }
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_ExitStep";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ExitStep.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */