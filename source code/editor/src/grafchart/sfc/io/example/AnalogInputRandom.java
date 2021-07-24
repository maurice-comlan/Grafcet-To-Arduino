package grafchart.sfc.io.example;

import grafchart.sfc.io.AnalogInput;

public class AnalogInputRandom
  implements AnalogInput
{
  public AnalogInputRandom(String paramString) {}
  
  public double get()
  {
    return Math.random();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/example/AnalogInputRandom.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */