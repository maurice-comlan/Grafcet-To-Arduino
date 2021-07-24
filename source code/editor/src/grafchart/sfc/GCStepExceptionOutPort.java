package grafchart.sfc;

import com.nwoods.jgo.JGoPort;

public class GCStepExceptionOutPort
  extends JGoPort
{
  public GCStepExceptionOutPort()
  {
    setFromSpot(8);
    setSize(6, 6);
    setStyle(5);
    setValidDestination(false);
    setValidSource(true);
  }
  
  public boolean validLink(JGoPort paramJGoPort)
  {
    boolean bool1 = super.validLink(paramJGoPort);
    int i = ((paramJGoPort.getParent() instanceof ExceptionTransition)) || ((paramJGoPort.getParent() instanceof ConnectionPost)) ? 1 : 0;
    boolean bool2 = (bool1) && (i != 0);
    return bool2;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCStepExceptionOutPort.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */