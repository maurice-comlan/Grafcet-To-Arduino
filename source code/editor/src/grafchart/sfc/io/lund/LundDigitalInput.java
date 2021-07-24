package grafchart.sfc.io.lund;

import grafchart.sfc.io.DigitalInput;
import java.io.PrintStream;
import se.lth.control.realtime.DigitalIn;

public class LundDigitalInput
  implements DigitalInput
{
  public transient DigitalIn digitalIn = null;
  
  public LundDigitalInput(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      this.digitalIn = new DigitalIn(i);
    }
    catch (Exception localException)
    {
      System.out.println(localException);
    }
  }
  
  public boolean get()
  {
    boolean bool = false;
    if (this.digitalIn != null) {
      try
      {
        bool = this.digitalIn.get();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        bool = false;
      }
    } else {
      bool = false;
    }
    return bool;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/lund/LundDigitalInput.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */