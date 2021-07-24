package grafchart.sfc;

import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import java.awt.Color;
import java.awt.Point;

public class StringAttributeVariable
  extends StringVariable
{
  public StringAttributeVariable() {}
  
  public StringAttributeVariable(Point paramPoint)
  {
    super(paramPoint);
    this.myBorder.setPen(new JGoPen(1, 2, new Color(15, 15, 15)));
  }
  
  public void setIsConstant(boolean paramBoolean)
  {
    super.setIsConstant(paramBoolean);
    this.myBorder.setPen(new JGoPen(1, this.myBorder.getPen().getWidth(), this.myBorder.getPen().getColor()));
  }
  
  public String getHelpID()
  {
    return "LangRef_XML_Object_StringAttribute";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/StringAttributeVariable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */