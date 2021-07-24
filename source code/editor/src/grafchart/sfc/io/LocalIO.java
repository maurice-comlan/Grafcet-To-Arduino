package grafchart.sfc.io;

public abstract interface LocalIO
{
  public abstract AnalogInput createAnalogInput(String paramString);
  
  public abstract AnalogOutput createAnalogOutput(String paramString);
  
  public abstract DigitalInput createDigitalInput(String paramString);
  
  public abstract DigitalOutput createDigitalOutput(String paramString);
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/io/LocalIO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */