package grafchart.sfc;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoRectangle;
import java.awt.Color;
import java.awt.Point;

public class IndexVariable
  extends IntegerVariable
{
  public IndexVariable() {}
  
  public IndexVariable(Point paramPoint)
  {
    super(paramPoint);
    this.myBorder.setBrush(new JGoBrush(65535, new Color(0.8F, 0.8F, 0.8F)));
  }
  
  public String getHelpID()
  {
    return "LangRef_XML_Object_IndexVariable";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/IndexVariable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */