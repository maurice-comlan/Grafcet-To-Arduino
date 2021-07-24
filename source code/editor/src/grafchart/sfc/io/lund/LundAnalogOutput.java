package grafchart.sfc.io.lund;

import grafchart.sfc.io.AnalogOutput;
import java.io.PrintStream;
import se.lth.control.realtime.AnalogOut;

public class LundAnalogOutput
  implements AnalogOutput
{
  public transient AnalogOut analogOut = null;
  
  public LundAnalogOutput(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      this.analogOut = new AnalogOut(i);
    }
    catch (Exception localException)
    {
      System.out.println(localException);
    }
  }
  
  public void set(double paramDouble)
  {
    if (this.analogOut != null) {
      try
      {
        this.analogOut.set(paramDouble);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/lund/LundAnalogOutput.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */