package grafchart.util;

import com.nwoods.jgo.JGoObject;
import grafchart.graphics.MyJGoArea;
import grafchart.sfc.GrafcetObject;
import grafchart.sfc.Utils;
import java.awt.Point;
import java.awt.Rectangle;
import org.w3c.dom.Element;

public class XMLUtil
{
  public static void saveRect(Element paramElement, Rectangle paramRectangle)
  {
    paramElement.setAttribute("x", Integer.toString(paramRectangle.x));
    paramElement.setAttribute("y", Integer.toString(paramRectangle.y));
    paramElement.setAttribute("width", Integer.toString(paramRectangle.width));
    paramElement.setAttribute("height", Integer.toString(paramRectangle.height));
  }
  
  public static void saveBoundingRect(Element paramElement, JGoObject paramJGoObject)
  {
    saveRect(paramElement, paramJGoObject.getBoundingRect());
  }
  
  public static void restoreBoundingRect(Element paramElement, JGoObject paramJGoObject)
  {
    paramJGoObject.setTopLeft(Integer.parseInt(paramElement.getAttribute("x")), Integer.parseInt(paramElement.getAttribute("y")));
    if ((paramJGoObject instanceof MyJGoArea)) {
      ((MyJGoArea)paramJGoObject).layoutChildren();
    }
    paramJGoObject.setSize(Integer.parseInt(paramElement.getAttribute("width")), Integer.parseInt(paramElement.getAttribute("height")));
  }
  
  public static void restoreBoundingRectOld(Element paramElement, JGoObject paramJGoObject)
  {
    paramJGoObject.setTopLeft((int)Math.round(Double.parseDouble(paramElement.getAttribute("x"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("y"))));
    if ((paramJGoObject instanceof MyJGoArea)) {
      ((MyJGoArea)paramJGoObject).layoutChildren();
    }
    paramJGoObject.setSize((int)Math.round(Double.parseDouble(paramElement.getAttribute("width"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("height"))));
  }
  
  public static void restoreBoundingRectAny(Element paramElement, JGoObject paramJGoObject)
  {
    if (Utils.getSaveVersion(paramElement) >= 3) {
      restoreBoundingRect(paramElement, paramJGoObject);
    } else {
      restoreBoundingRectOld(paramElement, paramJGoObject);
    }
  }
  
  public static void restoreBoundingRectAny(Element paramElement, GrafcetObject paramGrafcetObject, JGoObject paramJGoObject, int paramInt)
  {
    if (Utils.getSaveVersion(paramElement) >= 3)
    {
      restoreBoundingRect(paramElement, paramGrafcetObject);
    }
    else
    {
      paramGrafcetObject.setSize((int)Math.round(Double.parseDouble(paramElement.getAttribute("width"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("height"))));
      paramJGoObject.setSpotLocation(paramInt, (int)Math.round(Double.parseDouble(paramElement.getAttribute("x"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("y"))));
      paramGrafcetObject.layoutChildren();
    }
  }
  
  public static Rectangle getWorkspaceBoundingRect(Element paramElement)
  {
    if (Utils.getSaveVersion(paramElement) >= 3) {
      return new Rectangle(Integer.parseInt(paramElement.getAttribute("x")), Integer.parseInt(paramElement.getAttribute("y")), Integer.parseInt(paramElement.getAttribute("width")), Integer.parseInt(paramElement.getAttribute("height")));
    }
    return new Rectangle((int)Math.round(Double.parseDouble(paramElement.getAttribute("x"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("y"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("boundsWidth"))), (int)Math.round(Double.parseDouble(paramElement.getAttribute("boundsHeight"))));
  }
  
  public static void setViewPosition(Element paramElement, Point paramPoint)
  {
    if (paramPoint != null)
    {
      setInt(paramElement, "viewPositionX", paramPoint.x);
      setInt(paramElement, "viewPositionY", paramPoint.y);
    }
  }
  
  public static Point getViewPosition(Element paramElement)
  {
    if ((paramElement.hasAttribute("viewPositionX")) && (paramElement.hasAttribute("viewPositionY")))
    {
      if (Utils.getSaveVersion(paramElement) >= 3) {
        return new Point(getInt(paramElement, "viewPositionX"), getInt(paramElement, "viewPositionY"));
      }
      return new Point((int)Math.round(getDouble(paramElement, "viewPositionX")), (int)Math.round(getDouble(paramElement, "viewPositionY")));
    }
    return null;
  }
  
  public static void setBool(Element paramElement, String paramString, boolean paramBoolean)
  {
    paramElement.setAttribute(paramString, paramBoolean ? "1" : "0");
  }
  
  public static boolean getBool(Element paramElement, String paramString)
  {
    return paramElement.getAttribute(paramString).equals("1");
  }
  
  public static boolean getBool(Element paramElement, String paramString, boolean paramBoolean)
  {
    if (paramElement.hasAttribute(paramString)) {
      return getBool(paramElement, paramString);
    }
    return paramBoolean;
  }
  
  public static void setInt(Element paramElement, String paramString, int paramInt)
  {
    paramElement.setAttribute(paramString, Integer.toString(paramInt));
  }
  
  public static int getInt(Element paramElement, String paramString)
  {
    return getInt(paramElement, paramString, 0);
  }
  
  public static int getInt(Element paramElement, String paramString, int paramInt)
  {
    if (paramElement.hasAttribute(paramString)) {
      try
      {
        return Integer.parseInt(paramElement.getAttribute(paramString));
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    return paramInt;
  }
  
  public static void setDouble(Element paramElement, String paramString, double paramDouble)
  {
    paramElement.setAttribute(paramString, Double.toString(paramDouble));
  }
  
  public static double getDouble(Element paramElement, String paramString)
  {
    return getDouble(paramElement, paramString, 0.0D);
  }
  
  public static double getDouble(Element paramElement, String paramString, double paramDouble)
  {
    if (paramElement.hasAttribute(paramString)) {
      try
      {
        return Double.parseDouble(paramElement.getAttribute(paramString));
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    return paramDouble;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/util/XMLUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */