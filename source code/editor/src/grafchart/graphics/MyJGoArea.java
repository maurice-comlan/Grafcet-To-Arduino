package grafchart.graphics;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoView;
import grafchart.sfc.GCDocument;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class MyJGoArea
  extends JGoArea
{
  public abstract void layoutChildren();
  
  public Dimension getMinimumSize()
  {
    return new Dimension(0, 0);
  }
  
  protected Rectangle handleResize(Graphics2D paramGraphics2D, JGoView paramJGoView, Rectangle paramRectangle, Point paramPoint, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Dimension localDimension = getMinimumSize();
    Rectangle localRectangle = super.handleResize(paramGraphics2D, paramJGoView, paramRectangle, paramPoint, paramInt1, paramInt2, localDimension.width, localDimension.height);
    return localRectangle;
  }
  
  public double getMaxScaleX()
  {
    Dimension localDimension1 = getSize();
    Dimension localDimension2 = getMinimumSize();
    if (localDimension1.getWidth() <= localDimension2.getWidth()) {
      return 1.0D;
    }
    return localDimension1.getWidth() / localDimension2.getWidth();
  }
  
  public double getMaxScaleY()
  {
    Dimension localDimension1 = getSize();
    Dimension localDimension2 = getMinimumSize();
    if (localDimension1.getHeight() <= localDimension2.getHeight()) {
      return 1.0D;
    }
    return localDimension1.getHeight() / localDimension2.getHeight();
  }
  
  public double getScaleFactorX(Rectangle paramRectangle)
  {
    double d = 1.0D;
    if (paramRectangle.width != 0) {
      d = (getBoundingRect().getWidth() - (getNoScaleLeft(paramRectangle) + getNoScaleRight(paramRectangle))) / (paramRectangle.getWidth() - (getNoScaleLeft(paramRectangle) + getNoScaleRight(paramRectangle)));
    }
    return d;
  }
  
  public double getScaleFactorY(Rectangle paramRectangle)
  {
    double d = 1.0D;
    if (paramRectangle.height != 0) {
      d = (getBoundingRect().getHeight() - (getNoScaleTop(paramRectangle) + getNoScaleBottom(paramRectangle))) / (paramRectangle.getHeight() - (getNoScaleTop(paramRectangle) + getNoScaleBottom(paramRectangle)));
    }
    return d;
  }
  
  public int getNoScaleLeft(Rectangle paramRectangle)
  {
    return 0;
  }
  
  public int getNoScaleRight(Rectangle paramRectangle)
  {
    return 0;
  }
  
  public int getNoScaleTop(Rectangle paramRectangle)
  {
    return 0;
  }
  
  public int getNoScaleBottom(Rectangle paramRectangle)
  {
    return 0;
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return null;
  }
  
  public void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width == getWidth()) && (paramRectangle.height == getHeight()))
    {
      super.geometryChange(paramRectangle);
    }
    else
    {
      Rectangle localRectangle1 = getBoundingRect();
      double d1 = getScaleFactorX(paramRectangle);
      double d2 = getScaleFactorY(paramRectangle);
      int i = getNoScaleLeft(paramRectangle);
      int j = getNoScaleTop(paramRectangle);
      JGoObject[] arrayOfJGoObject1 = getGeometryChangeObjects();
      if (arrayOfJGoObject1 != null) {
        for (JGoObject localJGoObject : arrayOfJGoObject1) {
          if (localJGoObject != null)
          {
            Rectangle localRectangle2 = localJGoObject.getBoundingRect();
            localJGoObject.setBoundingRect(localRectangle1.x + i + (int)Math.round((localRectangle2.x - (paramRectangle.x + i)) * d1), localRectangle1.y + j + (int)Math.round((localRectangle2.y - (paramRectangle.y + j)) * d2), (int)Math.round(localRectangle2.width * d1), (int)Math.round(localRectangle2.height * d2));
          }
        }
      }
      layoutChildren();
    }
  }
  
  public GCDocument getDocument()
  {
    return (GCDocument)super.getDocument();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJGoArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */