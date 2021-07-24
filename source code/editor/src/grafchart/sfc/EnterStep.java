package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnterStep
  extends GCStep
  implements GCIdent, Connectable
{
  public JGoStroke myArrow = null;
  
  public EnterStep() {}
  
  public EnterStep(Point paramPoint, String paramString)
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
    removeObject(this.myInLine);
    removeObject(this.myInPort);
    addObjectAtTail(this.myArrow);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    EnterStep localEnterStep = (EnterStep)paramJGoArea;
    localEnterStep.removeObject(localEnterStep.myInLine);
    localEnterStep.removeObject(localEnterStep.myInPort);
    localEnterStep.myArrow = ((JGoStroke)paramJGoCopyEnvironment.copy(this.myArrow));
    localEnterStep.addObjectAtTail(localEnterStep.myArrow);
  }
  
  public void layoutChildren()
  {
    super.layoutChildren();
    if (this.myArrow != null) {
      this.myArrow.setSpotLocation(6, this.myRectangle, 2);
    }
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    ArrayList localArrayList = new ArrayList(Arrays.asList(super.getGeometryChangeObjects()));
    localArrayList.remove(this.myInLine);
    localArrayList.remove(this.myInPort);
    localArrayList.add(this.myArrow);
    JGoObject[] arrayOfJGoObject = new JGoObject[localArrayList.size()];
    return (JGoObject[])localArrayList.toArray(arrayOfJGoObject);
  }
  
  public void activate()
  {
    this.myToken.setBrush(JGoBrush.black);
    this.newX = true;
  }
  
  public void activateAlone()
  {
    this.myToken.setBrush(JGoBrush.black);
    if ((getParent() instanceof GCGroup)) {
      ((GCGroup)getParent()).showToken();
    }
    this.newX = true;
  }
  
  public void deactivate()
  {
    this.myToken.setBrush(JGoBrush.Null);
    this.newX = false;
    executeExitActions();
    if (getDocument().dimming)
    {
      this.myToken.setBrush(JGoBrush.lightGray);
      DimmerThread localDimmerThread = new DimmerThread(this);
      localDimmerThread.start();
    }
  }
  
  public void changeState()
  {
    super.changeState();
    if ((this.curX) && (!this.oldX)) {
      executeStoredActions();
    }
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_EnterStep";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/EnterStep.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */