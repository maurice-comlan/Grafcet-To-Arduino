package grafchart.graphics;

import grafchart.sfc.Utils;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

/**
 * Zone réservée à recevoir les documents créés
 * @author dimon
 */
public class MyJDesktopPane
  extends JDesktopPane
{
  private static final BufferedImage IMAGE = initImage();
  private static final int width = IMAGE.getWidth();
  private static final int height = IMAGE.getHeight();
  
  private static BufferedImage initImage()
  {
    File localFile = Utils.newFile("../iconlib/lth.png");
    BufferedImage localBufferedImage = null;
    try
    {
      localBufferedImage = ImageIO.read(localFile);
    }
    catch (IOException localIOException)
    {
      Utils.writeException(localIOException, localFile.getAbsolutePath());
      System.exit(1);
    }
    return localBufferedImage;
  }
  
  @Override
  public void paintComponent(Graphics paramGraphics)
  {
    super.paintComponent(paramGraphics);
    Rectangle localRectangle1 = paramGraphics.getClipBounds();
    int i = localRectangle1.x / width * width;
    int j = localRectangle1.y / height * height;
    int k = (localRectangle1.x + localRectangle1.width + width - 1) / width * width - i;
    int m = (localRectangle1.y + localRectangle1.height + height - 1) / height * height - j;
    Graphics localGraphics = paramGraphics.create(i, j, k, m);
    Rectangle localRectangle2 = new Rectangle(width, height);
    for (localRectangle2.x = 0; localRectangle2.x < k; localRectangle2.x += width) {
      for (localRectangle2.y = 0; localRectangle2.y < m; localRectangle2.y += height) {
        localGraphics.drawImage(IMAGE, localRectangle2.x, localRectangle2.y, this);
      }
    }
    localGraphics.dispose();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJDesktopPane.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */