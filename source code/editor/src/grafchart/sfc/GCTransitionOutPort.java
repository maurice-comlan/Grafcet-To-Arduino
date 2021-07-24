package grafchart.sfc;

import com.nwoods.jgo.JGoPort;

public class GCTransitionOutPort
  extends JGoPort
{
  public GCTransitionOutPort()
  {
    setFromSpot(6);
    setSize(10, 10);
    setStyle(0);
    setValidDestination(false);
    setValidSource(true);
  }
  
  public boolean validLink(JGoPort paramJGoPort)
  {
    boolean bool1 = super.validLink(paramJGoPort);
    int i = ((paramJGoPort.getParent() instanceof GCStep)) || ((paramJGoPort.getParent() instanceof ParallelSplit)) || ((paramJGoPort.getParent() instanceof MacroStep)) || ((paramJGoPort.getParent() instanceof ConnectionPost)) ? 1 : 0;
    boolean bool2 = (bool1) && (i != 0);
    return bool2;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCTransitionOutPort.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */