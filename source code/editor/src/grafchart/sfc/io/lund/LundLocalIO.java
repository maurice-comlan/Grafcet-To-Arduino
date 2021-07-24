package grafchart.sfc.io.lund;

import grafchart.sfc.io.AnalogInput;
import grafchart.sfc.io.AnalogOutput;
import grafchart.sfc.io.DigitalInput;
import grafchart.sfc.io.DigitalOutput;
import grafchart.sfc.io.LocalIO;

public class LundLocalIO
  implements LocalIO
{
  public AnalogInput createAnalogInput(String paramString)
  {
    return new LundAnalogInput(paramString);
  }
  
  public AnalogOutput createAnalogOutput(String paramString)
  {
    return new LundAnalogOutput(paramString);
  }
  
  public DigitalInput createDigitalInput(String paramString)
  {
    return new LundDigitalInput(paramString);
  }
  
  public DigitalOutput createDigitalOutput(String paramString)
  {
    return new LundDigitalOutput(paramString);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/lund/LundLocalIO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */