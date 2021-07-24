package grafchart.sfc;

import com.nwoods.jgo.JGoText;
import java.awt.Point;

public class SocketStringIn
  extends SocketIn
{
  public String val = "";
  public String oldval = "";
  public String socketVal = "";
  
  public SocketStringIn() {}
  
  public SocketStringIn(Point paramPoint)
  {
    super(paramPoint, "S");
  }
  
  public void setValue(String paramString)
  {
    super.setValue(paramString);
    this.socketVal = paramString;
  }
  
  public void readInput()
  {
    super.readInput();
    this.oldval = this.val;
    this.val = this.socketVal;
    this.myValue.setText(this.val);
  }
  
  public boolean getBoolVal()
  {
    String str = getStringVal();
    return str.equals("1");
  }
  
  public boolean getOldBoolVal()
  {
    String str = getOldStringVal();
    return str.equals("1");
  }
  
  public String getStringVal()
  {
    return this.val;
  }
  
  public String getOldStringVal()
  {
    return this.oldval;
  }
  
  public int getIntVal()
  {
    String str = getStringVal();
    int i = 0;
    try
    {
      i = Integer.parseInt(str);
    }
    catch (Exception localException) {}
    return i;
  }
  
  public int getOldIntVal()
  {
    String str = getOldStringVal();
    int i = 0;
    try
    {
      i = Integer.parseInt(str);
    }
    catch (Exception localException) {}
    return i;
  }
  
  public double getRealVal()
  {
    String str = getStringVal();
    double d = 0.0D;
    try
    {
      d = Double.parseDouble(str);
    }
    catch (Exception localException) {}
    return d;
  }
  
  public double getOldRealVal()
  {
    String str = getOldStringVal();
    double d = 0.0D;
    try
    {
      d = Double.parseDouble(str);
    }
    catch (Exception localException) {}
    return d;
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_Socket_StringIn";
  }
  
  public String toString()
  {
    return getName() + ": " + getStringVal();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/SocketStringIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */