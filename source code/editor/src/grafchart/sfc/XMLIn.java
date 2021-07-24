package grafchart.sfc;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class XMLIn
{
  public static ArrayList<XMLInSource> xmlInputs = new ArrayList();
  public static ArrayList<XMLInSource> xmlInputsTemp = new ArrayList();
  
  public static void addXMLInput(XMLInSource paramXMLInSource)
  {
    if (!xmlInputs.contains(paramXMLInSource))
    {
      xmlInputs.add(paramXMLInSource);
      if (!paramXMLInSource.getTopic().isEmpty()) {
        Editor.subscribe(paramXMLInSource.getTopic());
      }
    }
  }
  
  public static void removeXMLInput(XMLInSource paramXMLInSource)
  {
    while (xmlInputs.remove(paramXMLInSource)) {}
    if (!paramXMLInSource.getTopic().isEmpty()) {
      Editor.unSubscribe(paramXMLInSource.getTopic());
    }
  }
  
  public static void setXMLIn(String paramString1, String paramString2)
  {
    try
    {
      xmlInputsTemp.clear();
      xmlInputsTemp.addAll(xmlInputs);
      Iterator localIterator = xmlInputsTemp.iterator();
      while (localIterator.hasNext())
      {
        XMLInSource localXMLInSource = (XMLInSource)localIterator.next();
        if (paramString1.equals(localXMLInSource.getXMLIdentifier()))
        {
          XMLMessageIn localXMLMessageIn;
          if (paramString1.equals("ChemMessageContent"))
          {
            if ((hasSubject(paramString2)) && ((localXMLInSource.getSubject().isEmpty()) || (getSubject(paramString2).equals(localXMLInSource.getSubject()))))
            {
              localXMLInSource.setValue(paramString2);
              if ((localXMLInSource instanceof XMLMessageIn))
              {
                localXMLMessageIn = (XMLMessageIn)localXMLInSource;
                if (localXMLMessageIn.useProcedure) {
                  localXMLMessageIn.startProcedure();
                }
              }
            }
          }
          else
          {
            localXMLInSource.setValue(paramString2);
            if ((localXMLInSource instanceof XMLMessageIn))
            {
              localXMLMessageIn = (XMLMessageIn)localXMLInSource;
              if (localXMLMessageIn.useProcedure) {
                localXMLMessageIn.startProcedure();
              }
            }
          }
        }
      }
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
  }
  
  public static String getSubject(String paramString)
  {
    int i = paramString.indexOf("<SUBJECT>");
    if (i != -1)
    {
      paramString = paramString.substring(i + "<SUBJECT>".length());
      int j = paramString.indexOf("</SUBJECT>");
      if (j != -1) {
        return paramString.substring(0, j);
      }
    }
    return "";
  }
  
  public static boolean hasSubject(String paramString)
  {
    return (paramString.indexOf("<SUBJECT>") != -1) && (paramString.indexOf("</SUBJECT>") != -1);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/XMLIn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */