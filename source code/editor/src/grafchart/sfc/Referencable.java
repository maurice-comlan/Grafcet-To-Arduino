package grafchart.sfc;

import java.awt.Point;

public abstract interface Referencable
{
  public abstract String getName();
  
  public abstract void setName(String paramString);
  
  public abstract String getFullName();
  
  public abstract void setLocation(int paramInt1, int paramInt2);
  
  public abstract Point getLocation();
  
  public abstract int getWidth();
  
  public abstract int getHeight();
  
  public abstract void setWidth(int paramInt);
  
  public abstract void setHeight(int paramInt);
  
  public abstract void setVisible(boolean paramBoolean);
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Referencable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */