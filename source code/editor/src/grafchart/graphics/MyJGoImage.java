package grafchart.graphics;

import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoImage;
import com.nwoods.jgo.JGoObject;
import grafchart.sfc.Editor;
import grafchart.sfc.Utils;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MyJGoImage
  extends JGoImage
{
  private String path;
  
  public MyJGoImage() {}
  
  public MyJGoImage(Point paramPoint, Dimension paramDimension)
  {
    super(paramPoint, paramDimension);
  }
  
  public JGoObject copyObject(JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    MyJGoImage localMyJGoImage = (MyJGoImage)super.copyObject(paramJGoCopyEnvironment);
    if (localMyJGoImage != null) {
      localMyJGoImage.path = this.path;
    }
    return localMyJGoImage;
  }
  
  public boolean loadImage(String paramString)
  {
    this.path = paramString;
    if (JGoImage.getDefaultBase() == null) {
      try
      {
        JGoImage.setDefaultBase(Utils.newFile(System.getProperty("user.dir")).toURI().toURL());
      }
      catch (MalformedURLException localMalformedURLException)
      {
        Utils.writeException(localMalformedURLException, "Invalid user.dir: \"" + System.getProperty("user.dir") + "\"");
        System.exit(1);
      }
    }
    boolean bool = super.loadImage(this.path, true);
    if (!bool)
    {
      File localFile = Utils.newFileLight(this.path);
      if (localFile.isFile())
      {
        bool = super.loadImage(this.path, true);
        if (bool) {
          update();
        } else {
          Utils.writeError("Failed to load image: \"" + this.path + "\"");
        }
      }
      else
      {
        Utils.writeError("File not found: \"" + this.path + "\"");
        bool = super.loadImage(Editor.getFileNotFoundImagePath(), true);
        if (bool) {
          update();
        } else {
          Utils.writeError("Cannot load FileNotFound image.");
        }
      }
    }
    return bool;
  }
  
  public boolean loadImage(Image paramImage, boolean paramBoolean)
  {
    Utils.writeInternalError("MyJGoImage.loadImage(Image, boolean) should not be called.");
    return false;
  }
  
  public boolean loadImage(String paramString, boolean paramBoolean)
  {
    return loadImage(paramString);
  }
  
  public boolean loadImage(URL paramURL, boolean paramBoolean)
  {
    Utils.writeInternalError("MyJGoImage.loadImage(URL, boolean) should not be called.");
    return false;
  }
  
  public String getFilename()
  {
    return this.path;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJGoImage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */