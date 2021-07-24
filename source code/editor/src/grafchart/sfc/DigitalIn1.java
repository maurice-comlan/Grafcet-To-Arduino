package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoStroke;
import grafchart.sfc.io.DigitalInput;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DigitalIn1
  extends DigitalIn
  implements Readable
{
  private double circlePositionX = 20.0D;
  public JGoEllipse myCircle = null;
  
  public DigitalIn1() {}
  
  public DigitalIn1(Point paramPoint)
  {
    super(paramPoint);
    this.myCircle = new JGoEllipse();
    this.myCircle.setSize(6, 6);
    this.myCircle.setSelectable(false);
    this.myCircle.setDraggable(false);
    this.myCircle.setPen(greenPen);
    addObjectAtTail(this.myCircle);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    DigitalIn1 localDigitalIn1 = (DigitalIn1)paramJGoArea;
    localDigitalIn1.myCircle = ((JGoEllipse)paramJGoCopyEnvironment.copy(this.myCircle));
    localDigitalIn1.addObjectAtTail(localDigitalIn1.myCircle);
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
    if (this.myCircle != null)
    {
      Point localPoint = this.myBorder.getSpotLocation(8);
      this.myCircle.setSpotLocation(4, (int)Math.round(localPoint.getX() + this.circlePositionX), (int)localPoint.getY());
    }
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    ArrayList localArrayList = new ArrayList(Arrays.asList(super.getGeometryChangeObjects()));
    localArrayList.add(this.myCircle);
    JGoObject[] arrayOfJGoObject = new JGoObject[localArrayList.size()];
    return (JGoObject[])localArrayList.toArray(arrayOfJGoObject);
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width != getWidth()) || (paramRectangle.height != getHeight())) {
      this.circlePositionX *= getScaleFactorX(paramRectangle);
    }
    super.geometryChange(paramRectangle);
  }
  
  protected boolean updateDigitalIn()
  {
    return !this.digIn.get();
  }
  
  protected void drawBorder(JGoPen paramJGoPen)
  {
    super.drawBorder(paramJGoPen);
    this.myCircle.setPen(paramJGoPen == greenPen ? redPen : greenPen);
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_DigitalInputInverse";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/DigitalIn1.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */