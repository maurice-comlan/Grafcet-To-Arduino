package grafchart.sfc;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageFilter
  extends FileFilter
{
  public boolean accept(File paramFile)
  {
    if (paramFile.isDirectory()) {
      return true;
    }
    String str = Utils.getExtension(paramFile);
    if (str != null) {
      return (str.equals("gif")) || (str.equals("jpeg")) || (str.equals("jpg")) || (str.equals("png"));
    }
    return false;
  }
  
  public String getDescription()
  {
    return "Supported Image Files";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ImageFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */