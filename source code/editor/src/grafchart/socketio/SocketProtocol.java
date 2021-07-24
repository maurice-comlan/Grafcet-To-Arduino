package grafchart.socketio;

import java.util.StringTokenizer;

public class SocketProtocol
{
  public static String getTag(String paramString)
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "|");
    return localStringTokenizer.nextToken();
  }
  
  public static String getValue(String paramString)
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString, "|");
    localStringTokenizer.nextToken();
    if (localStringTokenizer.hasMoreTokens()) {
      return localStringTokenizer.nextToken();
    }
    return "";
  }
  
  public static String create(String paramString1, String paramString2)
  {
    return paramString1 + "|" + paramString2;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/socketio/SocketProtocol.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */