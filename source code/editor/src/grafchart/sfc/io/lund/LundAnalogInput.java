package grafchart.sfc.io.lund;

import grafchart.sfc.io.AnalogInput;
import java.io.PrintStream;
import se.lth.control.realtime.AnalogIn;

public class LundAnalogInput
  implements AnalogInput
{
  public transient AnalogIn analogIn = null;
  
  public LundAnalogInput(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      this.analogIn = new AnalogIn(i);
    }
    catch (Exception localException)
    {
      System.out.println(localException);
    }
  }
  
  public double get()
  {
    double d = 0.0D;
    if (this.analogIn != null) {
      try
      {
        d = this.analogIn.get();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        d = 0.0D;
      }
    } else {
      d = 0.0D;
    }
    return d;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/lund/LundAnalogInput.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */