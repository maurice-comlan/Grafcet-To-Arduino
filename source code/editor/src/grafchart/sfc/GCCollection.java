package grafchart.sfc;

import com.nwoods.jgo.JGoObjectCollection;

public abstract interface GCCollection
  extends JGoObjectCollection, SymbolTableObject
{
  public abstract GrafcetObject getOwner();
  
  public abstract String getName();
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCCollection.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */