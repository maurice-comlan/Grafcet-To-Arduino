package grafchart.sfc;

import com.nwoods.jgo.JGoPort;

public class GCToPort
  extends JGoPort
{
  public GCToPort()
  {
    setToSpot(2);
    setSize(10, 10);
    setStyle(0);
    setValidDestination(true);
    setValidSource(false);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCToPort.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */