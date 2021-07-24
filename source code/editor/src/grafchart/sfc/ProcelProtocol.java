package grafchart.sfc;

import java.util.StringTokenizer;

public class ProcelProtocol
{
  public static String getTag(String paramString)
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "=");
    return localStringTokenizer.nextToken();
  }
  
  public static String getValue(String paramString)
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "=");
    localStringTokenizer.nextToken();
    return localStringTokenizer.nextToken();
  }
  
  public static String create(String paramString1, String paramString2)
  {
    if (!paramString2.equals("")) {
      return "<execute><var>" + paramString1 + "=" + paramString2 + "</var></execute>";
    }
    return "<evaluate><var>" + paramString1 + "</var></evaluate>";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ProcelProtocol.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */