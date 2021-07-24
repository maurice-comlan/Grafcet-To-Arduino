package grafchart.util;

import java.util.LinkedList;
import java.util.ListIterator;

public class SortedList<E extends Comparable>
  extends LinkedList<E>
{
  public boolean add(E paramE)
  {
    ListIterator localListIterator = listIterator();
    while (localListIterator.hasNext())
    {
      Comparable localComparable = (Comparable)localListIterator.next();
      if (((localComparable instanceof String)) && ((paramE instanceof String)))
      {
        if (((String)paramE).compareToIgnoreCase((String)localComparable) < 0)
        {
          localListIterator.previous();
          localListIterator.add(paramE);
          return true;
        }
      }
      else if (paramE.compareTo(localComparable) < 0)
      {
        localListIterator.previous();
        localListIterator.add(paramE);
        return true;
      }
    }
    localListIterator.add(paramE);
    return true;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/util/SortedList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */