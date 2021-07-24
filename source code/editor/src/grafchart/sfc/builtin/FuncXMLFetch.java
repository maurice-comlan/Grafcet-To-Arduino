package grafchart.sfc.builtin;

import grafchart.sfc.Utils;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FuncXMLFetch
  extends AbstractExecutable
{
  public FuncXMLFetch()
  {
    super("xmlFetch", -1);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    String str = internalExecuteString(paramArrayOfEvaluatable, paramObject);
    return Utils.xsdBooleanStringToBoolean(str);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    String str = internalExecuteString(paramArrayOfEvaluatable, paramObject);
    if (str == "") {
      return 0.0D;
    }
    try
    {
      return Double.parseDouble(str);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      runtimeError("Unable to interpret response as real: \"" + str + "\"");
    }
    return 0.0D;
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    String str1 = paramArrayOfEvaluatable[0].evaluateString();
    String str2 = paramArrayOfEvaluatable[1].evaluateString();
    XPathExpression localXPathExpression = null;
    try
    {
      localXPathExpression = XPathFactory.newInstance().newXPath().compile(str2);
    }
    catch (XPathExpressionException localXPathExpressionException)
    {
      runtimeError("Invalid XPath \"" + str2 + "\".");
      return "";
    }
    Object localObject1 = null;
    Object localObject2;
    if (paramArrayOfEvaluatable.length > 2)
    {
      localObject2 = paramArrayOfEvaluatable[2].evaluateString();
      if (((String)localObject2).equals("sum")) {
        localObject1 = new SumMultiHandler();
      } else if (((String)localObject2).equals("first")) {
        localObject1 = new FirstMultiHandler();
      } else {
        runtimeError("Unknown MultiHandler \"" + (String)localObject2 + "\", using default.");
      }
    }
    if (localObject1 == null) {
      localObject1 = new FirstMultiHandler();
    }
    if (!str1.isEmpty())
    {
      localObject2 = DocumentBuilderFactory.newInstance();
      try
      {
        DocumentBuilder localDocumentBuilder = ((DocumentBuilderFactory)localObject2).newDocumentBuilder();
        Document localDocument = localDocumentBuilder.parse(new ByteArrayInputStream(str1.getBytes()));
        NodeList localNodeList = (NodeList)localXPathExpression.evaluate(localDocument.getDocumentElement(), XPathConstants.NODESET);
        for (int i = 0; i < localNodeList.getLength(); i++)
        {
          Element localElement = (Element)localNodeList.item(i);
          ((MultiHandler)localObject1).addElement(Utils.getElementText(localElement));
        }
        String str3 = ((MultiHandler)localObject1).getResult();
        if (str3 != null) {
          return str3;
        }
        runtimeError("Invalid request.");
        return "";
      }
      catch (Exception localException)
      {
        Utils.writeException(localException);
      }
    }
    return "";
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 3);
  }
  
  public String getCorrectNrOfArguments()
  {
    return "2 or 3";
  }
  
  private class SumMultiHandler
    implements FuncXMLFetch.MultiHandler
  {
    private double result = 0.0D;
    
    private SumMultiHandler() {}
    
    public void addElement(String paramString)
    {
      try
      {
        this.result += Double.parseDouble(paramString);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Utils.writeError("xmlFetch(..., \"sum\") - Not a number: " + paramString);
      }
    }
    
    public String getResult()
    {
      return Double.toString(this.result);
    }
  }
  
  private class FirstMultiHandler
    implements FuncXMLFetch.MultiHandler
  {
    private String result = null;
    
    private FirstMultiHandler() {}
    
    public void addElement(String paramString)
    {
      if (this.result == null) {
        this.result = paramString;
      }
    }
    
    public String getResult()
    {
      return this.result;
    }
  }
  
  private static abstract interface MultiHandler
  {
    public abstract void addElement(String paramString);
    
    public abstract String getResult();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncXMLFetch.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */