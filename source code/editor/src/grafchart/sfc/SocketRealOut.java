package grafchart.sfc;

import com.nwoods.jgo.JGoText;
import java.awt.Point;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class SocketRealOut
  extends SocketOut
  implements Readable
{
  public double val;
  public double oldval;
  public static DecimalFormat df = new DecimalFormat();
  
  public SocketRealOut() {}
  
  public SocketRealOut(Point paramPoint)
  {
    super(paramPoint, "R");
  }
  
  public void setStoredRealAction(double paramDouble)
  {
    this.oldval = this.val;
    this.val = paramDouble;
    if ((getSendMode() == SocketOut.SendMode.Assigned) || (this.val != this.oldval))
    {
      this.myValue.setText(df.format(this.val));
      getDocument().socketSend(getSocketIdentifier(), "" + this.val, this.procelMode);
      layoutChildren();
    }
  }
  
  public boolean isReal()
  {
    return true;
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
    return (int)this.val;
  }
  
  public int getOldIntVal()
  {
    return (int)this.oldval;
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
    return "LangRef_IO_Socket_RealOut";
  }
  
  public String toString()
  {
    return getName() + ": " + getRealVal();
  }
  
  static
  {
    DecimalFormatSymbols localDecimalFormatSymbols = df.getDecimalFormatSymbols();
    localDecimalFormatSymbols.setDecimalSeparator('.');
    df.setGroupingUsed(false);
    df.setMaximumFractionDigits(3);
    df.setMinimumFractionDigits(1);
    df.setDecimalFormatSymbols(localDecimalFormatSymbols);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/SocketRealOut.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */