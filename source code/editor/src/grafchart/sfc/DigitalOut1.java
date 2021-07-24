package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DigitalOut1
  extends DigitalOut
{
  public JGoEllipse myCircle = null;
  
  public DigitalOut1() {}
  
  public DigitalOut1(Point paramPoint, String paramString)
  {
    super(paramPoint, paramString);
    this.myCircle = new JGoEllipse();
    this.myCircle.setSize(6, 6);
    this.myCircle.setSelectable(false);
    this.myCircle.setDraggable(false);
    this.myCircle.setPen(greenPen);
    addObjectAtTail(this.myCircle);
    if (paramString.equals("1")) {
      this.myCircle.setPen(redPen);
    } else {
      this.myCircle.setPen(greenPen);
    }
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    DigitalOut1 localDigitalOut1 = (DigitalOut1)paramJGoArea;
    localDigitalOut1.myCircle = ((JGoEllipse)paramJGoCopyEnvironment.copy(this.myCircle));
    localDigitalOut1.addObjectAtTail(localDigitalOut1.myCircle);
  }
  
  public Point getLocation(Point paramPoint)
  {
    return getSpotLocation(1, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    setSpotLocation(1, paramInt1, paramInt2);
  }
  
  public void layoutChildren()
  {
    super.layoutChildren();
    if (this.myCircle != null) {
      this.myCircle.setSpotLocation(8, this.myBorder, 4);
    }
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    ArrayList localArrayList = new ArrayList(Arrays.asList(super.getGeometryChangeObjects()));
    localArrayList.add(this.myCircle);
    JGoObject[] arrayOfJGoObject = new JGoObject[localArrayList.size()];
    return (JGoObject[])localArrayList.toArray(arrayOfJGoObject);
  }
  
  protected void drawBorder(JGoPen paramJGoPen)
  {
    super.drawBorder(paramJGoPen);
    if (paramJGoPen == standardPen) {
      this.myCircle.setPen(paramJGoPen);
    } else {
      this.myCircle.setPen(paramJGoPen == greenPen ? redPen : greenPen);
    }
  }
  
  protected void updateDigitalOut(boolean paramBoolean)
  {
    super.updateDigitalOut(!paramBoolean);
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_DigitalOutputInverse";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/DigitalOut1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */