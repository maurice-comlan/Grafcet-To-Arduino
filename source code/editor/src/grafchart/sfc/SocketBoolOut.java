package grafchart.sfc;

import com.nwoods.jgo.JGoText;
import java.awt.Point;

public class SocketBoolOut
  extends SocketOut
  implements Readable
{
  public boolean val;
  public boolean oldval;
  private boolean setHigh = false;
  private boolean setLow = false;
  
  public SocketBoolOut() {}
  
  public SocketBoolOut(Point paramPoint)
  {
    super(paramPoint, "B");
  }
  
  public void setNormalAction(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.setHigh = true;
    } else {
      this.setLow = true;
    }
  }
  
  public void effectuateNormalActions()
  {
    if (this.setHigh) {
      setStoredBoolAction(true);
    } else if (this.setLow) {
      setStoredBoolAction(false);
    }
    this.setLow = false;
    this.setHigh = false;
  }
  
  public void setStoredBoolAction(boolean paramBoolean)
  {
    this.oldval = this.val;
    this.val = paramBoolean;
    if ((getSendMode() == SocketOut.SendMode.Assigned) || (this.val != this.oldval))
    {
      String str = this.val ? "1" : "0";
      this.myValue.setText(str);
      getDocument().socketSend(getSocketIdentifier(), this.procelMode ? str + ".0" : str, this.procelMode);
      layoutChildren();
    }
  }
  
  public boolean isBoolean()
  {
    return true;
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
    boolean bool = getBoolVal();
    if (bool) {
      return 1;
    }
    return 0;
  }
  
  public int getOldIntVal()
  {
    boolean bool = getOldBoolVal();
    if (bool) {
      return 1;
    }
    return 0;
  }
  
  public double getRealVal()
  {
    boolean bool = getBoolVal();
    if (bool) {
      return 1.0D;
    }
    return 0.0D;
  }
  
  public double getOldRealVal()
  {
    boolean bool = getOldBoolVal();
    if (bool) {
      return 1.0D;
    }
    return 0.0D;
  }
  
  public String getStringVal()
  {
    boolean bool = getBoolVal();
    if (bool) {
      return "1.0";
    }
    return "0.0";
  }
  
  public String getOldStringVal()
  {
    boolean bool = getOldBoolVal();
    if (bool) {
      return "1.0";
    }
    return "0.0";
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_Socket_BoolOut";
  }
  
  public String toString()
  {
    return getName() + ": " + getIntVal();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/SocketBoolOut.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */