package grafchart.sfc;

import com.nwoods.jgo.JGoText;
import java.awt.Point;

public class SocketBooleanIn
  extends SocketIn
{
  public boolean val;
  public boolean oldval;
  public boolean socketVal = false;
  
  public SocketBooleanIn() {}
  
  public SocketBooleanIn(Point paramPoint)
  {
    super(paramPoint, "B");
  }
  
  public void setValue(String paramString)
  {
    super.setValue(paramString);
    int i = Integer.parseInt(paramString);
    if (i == 0) {
      this.socketVal = false;
    }
    if (i == 1) {
      this.socketVal = true;
    }
  }
  
  public void readInput()
  {
    super.readInput();
    this.oldval = this.val;
    this.val = this.socketVal;
    if (this.val) {
      this.myValue.setText("1");
    } else {
      this.myValue.setText("0");
    }
  }
  
  public boolean getBoolVal()
  {
    return this.val;
  }
  
  public boolean getOldBoolVal()
  {
    return this.oldval;
  }
  
  public int getIntVal()
  {
    return getBoolVal() ? 1 : 0;
  }
  
  public int getOldIntVal()
  {
    return getOldBoolVal() ? 1 : 0;
  }
  
  public double getRealVal()
  {
    return getBoolVal() ? 1.0D : 0.0D;
  }
  
  public double getOldRealVal()
  {
    return getOldBoolVal() ? 1.0D : 0.0D;
  }
  
  public String getStringVal()
  {
    return getBoolVal() ? "1" : "0";
  }
  
  public String getOldStringVal()
  {
    return getOldBoolVal() ? "1" : "0";
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_Socket_BoolIn";
  }
  
  public String toString()
  {
    return getName() + ": " + getIntVal();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/SocketBooleanIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */