package grafchart.sfc.io.example;

import grafchart.sfc.io.AnalogOutput;
import java.io.PrintStream;

public class AnalogOutputPrint
  implements AnalogOutput
{
  private String chan;
  
  public AnalogOutputPrint(String paramString)
  {
    this.chan = paramString;
  }
  
  public void set(double paramDouble)
  {
    System.out.println("Channel " + this.chan + " Value " + paramDouble);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/example/AnalogOutputPrint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */