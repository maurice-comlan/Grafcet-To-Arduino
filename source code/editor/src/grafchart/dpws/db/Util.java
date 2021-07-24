package grafchart.dpws.db;

import grafchart.dpws.ServicesDialogNode;

public abstract class Util
{
  public static String washDoc(String paramString)
  {
    paramString = paramString.replaceAll("\t", "");
    return paramString.trim();
  }
  
  public static ServicesDialogNode createServicesDialogNode(String paramString, DPWSElement paramDPWSElement)
  {
    return new ServicesDialogNode(paramString, paramDPWSElement);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/db/Util.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */