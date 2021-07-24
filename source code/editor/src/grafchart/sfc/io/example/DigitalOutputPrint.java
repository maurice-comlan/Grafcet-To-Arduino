package grafchart.sfc.io.example;

import grafchart.sfc.io.DigitalOutput;
import java.io.PrintStream;

public class DigitalOutputPrint
  implements DigitalOutput
{
  private String chan;
  
  public DigitalOutputPrint(String paramString)
  {
    this.chan = paramString;
  }
  
  public void set(boolean paramBoolean)
  {
    System.out.println("Channel " + this.chan + " Value " + paramBoolean);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/example/DigitalOutputPrint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */