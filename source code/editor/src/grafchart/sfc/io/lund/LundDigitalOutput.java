package grafchart.sfc.io.lund;

import grafchart.sfc.io.DigitalOutput;
import java.io.PrintStream;
import se.lth.control.realtime.DigitalOut;

public class LundDigitalOutput
  implements DigitalOutput
{
  public transient DigitalOut digitalOut = null;
  
  public LundDigitalOutput(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      this.digitalOut = new DigitalOut(i);
    }
    catch (Exception localException)
    {
      System.out.println(localException);
    }
  }
  
  public void set(boolean paramBoolean)
  {
    if (this.digitalOut != null) {
      try
      {
        this.digitalOut.set(paramBoolean);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/lund/LundDigitalOutput.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */