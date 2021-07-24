package grafchart.sfc;

import grafchart.graphics.MyJGoArea;
import java.util.ArrayList;

public abstract class GrafcetObject
  extends MyJGoArea
  implements Helpable
{
  public boolean compiled = false;
  public boolean curX = false;
  protected boolean newX = false;
  public boolean oldX = false;
  public ArrayList<StepFusionSet> fusionSets = new ArrayList();
  public String helpID = "";
  public int timer = 0;
  
  public double getSeconds()
  {
    return 0.0D;
  }
  
  public void deactivate() {}
  
  public void activate() {}
  
  public void deactivateAlone() {}
  
  public void activateAlone() {}
  
  public boolean isEnabled()
  {
    return false;
  }
  
  public boolean isEnabledAlone()
  {
    Utils.writeInternalError("Method should not be called.");
    return false;
  }
  
  public void layoutChildren() {}
  
  public void executeStoredActions() {}
  
  public String getHelpID()
  {
    return this.helpID;
  }
  
  public void succeedingTransitions(ArrayList<GenericTransition> paramArrayList) {}
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GrafcetObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */