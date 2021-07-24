package grafchart.sfc;

import grafchart.util.MyObservable;
import java.util.ArrayList;
import java.util.Observer;

public class ObservableList<E>
  extends ArrayList<E>
{
  private transient MyObservable observable = new MyObservable();
  
  /* Error */
  public Object clone()
  {
      return null;
    // Byte code:
    //   0: aload_0
    //   1: dup
    //   2: astore_1
    //   3: monitorenter
    //   4: aload_0
    //   5: invokespecial 5	java/util/ArrayList:clone	()Ljava/lang/Object;
    //   8: aload_1
    //   9: monitorexit
    //   10: areturn
    //   11: astore_2
    //   12: aload_1
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	16	0	this	ObservableList
    //   2	11	1	Ljava/lang/Object;	Object
    //   11	4	2	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   4	10	11	finally
    //   11	14	11	finally
  }
  
  public boolean add(E paramE)
  {
    boolean bool;
    synchronized (this)
    {
      bool = super.add(paramE);
    }
    notifyObservers();
    return bool;
  }
  
  public boolean remove(Object paramObject)
  {
    boolean bool = false;
    synchronized (this)
    {
      int i = indexOf(paramObject);
      if (i != -1)
      {
        remove(i);
        bool = true;
      }
    }
    if (bool) {
      notifyObservers();
    }
    return bool;
  }
  
  public void clear()
  {
    int i = 0;
    synchronized (this)
    {
      if (!isEmpty())
      {
        super.clear();
        i = 1;
      }
    }
    if (i != 0) {
      notifyObservers();
    }
  }
  
  public synchronized boolean isEmpty()
  {
    return super.isEmpty();
  }
  
  private void notifyObservers()
  {
    this.observable.setChanged();
    this.observable.notifyObservers(this);
  }
  
  public void addObserver(Observer paramObserver)
  {
    this.observable.addObserver(paramObserver);
  }
  
  public void deleteObserver(Observer paramObserver)
  {
    this.observable.deleteObserver(paramObserver);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ObservableList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */