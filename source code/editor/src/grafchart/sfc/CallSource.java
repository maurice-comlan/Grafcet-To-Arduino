package grafchart.sfc;

import java.awt.Point;
import java.awt.Rectangle;

public abstract interface CallSource
{
  public abstract ObservableList<GCDocument> getCalls();
  
  public abstract String getName();
  
  public abstract Point getLocation();
  
  public abstract Rectangle getBounds();
  
  public abstract int getColor();
  
  public abstract GCDocument getDocument();
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/CallSource.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */