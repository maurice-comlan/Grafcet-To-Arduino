package grafchart.sfc;

import com.nwoods.jgo.JGoPort;

public class GCStepOutPort
  extends JGoPort
{
  public GCStepOutPort()
  {
    setFromSpot(6);
    setSize(10, 10);
    setStyle(0);
    setValidDestination(false);
    setValidSource(true);
  }
  
  @Override
  public boolean validLink(JGoPort paramJGoPort)
  {
    boolean bool1 = super.validLink(paramJGoPort);
    int i = ((paramJGoPort.getParent() instanceof GCTransition)) || ((paramJGoPort.getParent() instanceof ParallelJoin)) || ((paramJGoPort.getParent() instanceof ConnectionPost)) ? 1 : 0;
    boolean bool2 = (bool1) && (i != 0);
    return bool2;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCStepOutPort.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */