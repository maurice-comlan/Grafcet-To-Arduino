package grafchart.sfc;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoObject;

public class DimmerThread
  extends Thread
{
  JGoObject step;
  
  public DimmerThread(JGoObject paramJGoObject)
  {
    this.step = paramJGoObject;
  }
  
  public DimmerThread() {}
  
  public void run()
  {
    Object localObject;
    JGoEllipse localJGoEllipse;
    if ((this.step instanceof GCStep))
    {
      localObject = (GCStep)this.step;
      localJGoEllipse = ((GCStep)localObject).myToken;
    }
    else if ((this.step instanceof MacroStep))
    {
      localObject = (MacroStep)this.step;
      localJGoEllipse = ((MacroStep)localObject).myToken;
    }
    else
    {
      localJGoEllipse = new JGoEllipse();
    }
    try
    {
      sleep(((GCDocument)this.step.getDocument()).threadSpeed * ((GCDocument)this.step.getDocument()).dimTicks);
    }
    catch (InterruptedException localInterruptedException) {}
    if (localJGoEllipse.getBrush() == JGoBrush.lightGray) {
      localJGoEllipse.setBrush(JGoBrush.Null);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/DimmerThread.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */