package grafchart.sfc;

import com.nwoods.jgo.JGoPort;

public class GCFromPort
  extends JGoPort
{
  public GCFromPort()
  {
    setFromSpot(6);
    setSize(10, 10);
    setStyle(0);
    setValidDestination(false);
    setValidSource(true);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCFromPort.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */