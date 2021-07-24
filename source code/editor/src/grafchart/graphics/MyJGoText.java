package grafchart.graphics;

import com.nwoods.jgo.JGoText;
import grafchart.util.MyObservable;
import java.util.Observer;

public class MyJGoText
  extends JGoText
{
  private transient MyObservable observable;
  
  public MyJGoText()
  {
    init();
  }
  
  public MyJGoText(String paramString)
  {
    super(paramString);
    init();
  }
  
  private void init()
  {
    this.observable = new MyObservable();
  }
  
  public void addObserver(Observer paramObserver)
  {
    this.observable.addObserver(paramObserver);
  }
  
  public void doEndEdit()
  {
    super.doEndEdit();
    this.observable.setChanged();
    this.observable.notifyObservers(this);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJGoText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */