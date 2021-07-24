package grafchart.sfc;

import com.nwoods.jgo.JGoText;
import java.awt.Point;

public class SocketIntIn
  extends SocketIn
{
  public int val;
  public int oldval;
  public int socketVal;
  
  public SocketIntIn() {}
  
  public SocketIntIn(Point paramPoint)
  {
    super(paramPoint, "I");
  }
  
  public void setValue(String paramString)
  {
    try
    {
      this.socketVal = Integer.parseInt(paramString);
      super.setValue(paramString);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      Utils.writeError("Socket input for \"" + getName() + "\" is not an integer: \"" + paramString + "\".");
    }
  }
  
  public void readInput()
  {
    super.readInput();
    this.oldval = this.val;
    this.val = this.socketVal;
    this.myValue.setText("" + this.val);
  }
  
  public boolean getBoolVal()
  {
    return getIntVal() == 1;
  }
  
  public boolean getOldBoolVal()
  {
    return getOldIntVal() == 1;
  }
  
  public int getIntVal()
  {
    return this.val;
  }
  
  public int getOldIntVal()
  {
    return this.oldval;
  }
  
  public double getRealVal()
  {
    return getIntVal();
  }
  
  public double getOldRealVal()
  {
    return getOldIntVal();
  }
  
  public String getStringVal()
  {
    return "" + this.val;
  }
  
  public String getOldStringVal()
  {
    return "" + this.oldval;
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_Socket_IntIn";
  }
  
  public String toString()
  {
    return getName() + ": " + getIntVal();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/SocketIntIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */