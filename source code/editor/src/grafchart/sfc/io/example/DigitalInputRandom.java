package grafchart.sfc.io.example;

import grafchart.sfc.io.DigitalInput;

public class DigitalInputRandom
  implements DigitalInput
{
  public DigitalInputRandom(String paramString) {}
  
  public boolean get()
  {
    return Math.random() > 0.5D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/example/DigitalInputRandom.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */