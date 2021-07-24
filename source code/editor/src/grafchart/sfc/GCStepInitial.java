package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class GCStepInitial
  extends GCStep
{
  private JGoRectangle myInnerRectangle = null;
  
  public GCStepInitial() {}
  
  public GCStepInitial(Point paramPoint, String paramString)
  {
    super(paramPoint, paramString);
    this.myInnerRectangle = new JGoRectangle();
    this.myInnerRectangle.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myInnerRectangle.setSize(50, 50);
    this.myInnerRectangle.setSelectable(false);
    this.myInnerRectangle.setDraggable(false);
    addObjectAtHead(this.myInnerRectangle);
    bringObjectToFront(this.myToken);
    layoutChildren();
  }
  
  @Override
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    GCStepInitial localGCStepInitial = (GCStepInitial)paramJGoArea;
    localGCStepInitial.myInnerRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myInnerRectangle));
    localGCStepInitial.addObjectAtHead(localGCStepInitial.myInnerRectangle);
  }
  
  @Override
  public void layoutChildren()
  {
    super.layoutChildren();
    if (this.myInnerRectangle != null) {
      this.myInnerRectangle.setSpotLocation(0, this.myRectangle, 0);
    }
  }
  
  @Override
  public JGoObject[] getGeometryChangeObjects()
  {
    ArrayList localArrayList = new ArrayList(Arrays.asList(super.getGeometryChangeObjects()));
    localArrayList.add(this.myInnerRectangle);
    JGoObject[] arrayOfJGoObject = new JGoObject[localArrayList.size()];
    return (JGoObject[])localArrayList.toArray(arrayOfJGoObject);
  }
  
  @Override
  public String getHelpID()
  {
    return "LangRef_FC_InitialStep";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCStepInitial.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */