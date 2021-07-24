package grafchart.sfc.io.example;

import grafchart.sfc.io.AnalogInput;
import grafchart.sfc.io.AnalogOutput;
import grafchart.sfc.io.DigitalInput;
import grafchart.sfc.io.DigitalOutput;
import grafchart.sfc.io.LocalIO;

public class ExampleIO
  implements LocalIO
{
  public AnalogInput createAnalogInput(String paramString)
  {
    return new AnalogInputRandom(paramString);
  }
  
  public AnalogOutput createAnalogOutput(String paramString)
  {
    return new AnalogOutputPrint(paramString);
  }
  
  public DigitalInput createDigitalInput(String paramString)
  {
    return new DigitalInputRandom(paramString);
  }
  
  public DigitalOutput createDigitalOutput(String paramString)
  {
    return new DigitalOutputPrint(paramString);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/example/ExampleIO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */