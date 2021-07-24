package grafchart.util;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator<E>
  implements Iterable<E>, Iterator<E>
{
  private Enumeration<E> e;
  
  public EnumerationIterator(Enumeration<E> paramEnumeration)
  {
    this.e = paramEnumeration;
  }
  
  public boolean hasNext()
  {
    return this.e.hasMoreElements();
  }
  
  public E next()
  {
    return (E)this.e.nextElement();
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  public Iterator<E> iterator()
  {
    return this;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/util/EnumerationIterator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */