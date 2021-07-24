package grafchart.sfc;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLProtocol
{
  static DocumentBuilderFactory factory;
  static DocumentBuilder builder;
  
  public static Document parseXML(String paramString)
  {
    try
    {
      return builder.parse(new ByteArrayInputStream(paramString.getBytes()));
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
    return null;
  }
  
  public static String getTag(Document paramDocument)
  {
    String str1 = "";
    Element localElement1 = paramDocument.getDocumentElement();
    NodeList localNodeList = localElement1.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        Element localElement2 = (Element)localNode;
        String str2 = localElement2.getTagName();
        if (str2.equals("Name")) {
          str1 = localElement2.getFirstChild().getNodeValue();
        }
      }
    }
    return str1;
  }
  
  public static String getValue(Document paramDocument)
  {
    String str1 = "";
    Element localElement1 = paramDocument.getDocumentElement();
    NodeList localNodeList = localElement1.getChildNodes();
    for (int i = 0; i < localNodeList.getLength(); i++)
    {
      Node localNode = localNodeList.item(i);
      if (localNode.getNodeType() == 1)
      {
        Element localElement2 = (Element)localNode;
        String str2 = localElement2.getTagName();
        if (str2.equals("Current_value")) {
          str1 = localElement2.getFirstChild().getNodeValue();
        }
      }
    }
    return str1;
  }
  
  public static String create(String paramString1, String paramString2)
  {
    return "<Process_variable> <Name>" + paramString1 + "</Name> <Current_value>" + paramString2 + "</Current_value> </Process_variable>";
  }
  
  static
  {
    try
    {
      factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/XMLProtocol.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */