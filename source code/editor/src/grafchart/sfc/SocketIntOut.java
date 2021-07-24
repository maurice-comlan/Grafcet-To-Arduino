package grafchart.sfc;

import com.nwoods.jgo.JGoText;
import java.awt.Point;

public class SocketIntOut
  extends SocketOut
  implements Readable
{
  public int val;
  public int oldval;
  
  public SocketIntOut() {}
  
  public SocketIntOut(Point paramPoint)
  {
    super(paramPoint, "I");
  }
  
  public void setStoredIntAction(int paramInt)
  {
    this.oldval = this.val;
    this.val = paramInt;
    if ((getSendMode() == SocketOut.SendMode.Assigned) || (this.val != this.oldval))
    {
      this.myValue.setText("" + this.val);
      getDocument().socketSend(getSocketIdentifier(), "" + this.val, this.procelMode);
      layoutChildren();
    }
  }
  
  public boolean isInteger()
  {
    return true;
  }
  
  public boolean getBoolVal()
  {
    return false;
  }
  
  public boolean getOldBoolVal()
  {
    return false;
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
    return this.val;
  }
  
  public double getOldRealVal()
  {
    return this.oldval;
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
    return "LangRef_IO_Socket_IntOut";
  }
  
  public String toString()
  {
    return getName() + ": " + getIntVal();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/SocketIntOut.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */