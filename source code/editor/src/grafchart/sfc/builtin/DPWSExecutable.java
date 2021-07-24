package grafchart.sfc.builtin;

import grafchart.dpws.db.DPWSOperation;
import grafchart.dpws.db.DPWSParameter;
import grafchart.dpws.db.DPWSPortType;
import grafchart.sfc.DPWSObject;
import grafchart.sfc.Editor;
import grafchart.sfc.Utils;
import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.wsdl.Definition;
import javax.wsdl.OperationType;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.stax.StaxBuilder;
import org.soda.dpws.DPWSContext;
import org.soda.dpws.DPWSException;
import org.soda.dpws.invocation.Call;
import org.soda.dpws.invocation.ResponseCallback;

public class DPWSExecutable
  extends AbstractExecutable
{
  private String operationName;
  private static ExecutorService executor;
  
  public DPWSExecutable(String paramString)
  {
    super("Generic DPWS Operation", -1);
    this.operationName = paramString;
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
      runtimeError("Unable to interpret response as real: \"" + str + "\"", (DPWSObject)paramObject);
    }
    return 0.0D;
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    DPWSObject localDPWSObject = (DPWSObject)paramObject;
    if (!localDPWSObject.isValidPortType(this))
    {
      localDPWSObject.addFault("Device is unavailable.");
      return "";
    }
    DPWSPortType localDPWSPortType = localDPWSObject.getPortType();
    final DPWSOperation localDPWSOperation = localDPWSPortType.getOperation(this.operationName);
    if (localDPWSOperation == null)
    {
      runtimeError("Operation \"" + this.operationName + "\" not found in PortType \"" + localDPWSPortType.getNamespace() + "\".", localDPWSObject);
      return "";
    }
    Element localElement = null;
    final Object localObject11 = localDPWSOperation.getInputs().iterator();
    //final Object localObject2;
    //final Object localObject3;
    Object localObject4;
    Object localObject5;
    String str1;
    while (((Iterator)localObject11).hasNext())
    {
      final Object localObject2 = (DPWSParameter)((Iterator)localObject11).next();
      final Object localObject3 = paramArrayOfEvaluatable[0].evaluateString();
      localObject4 = ((DPWSParameter)localObject2).getWSDL().getTargetNamespace();
      int i = !((String)localObject4).isEmpty() ? 1 : 0;
      localObject5 = ((DPWSParameter)localObject2).getName();
      str1 = ((QName)localObject5).getNamespaceURI();
      int j = !str1.isEmpty() ? 1 : 0;
      String str3 = "<";
      if (j != 0) {
        str3 = str3 + "ens:";
      }
      str3 = str3 + ((QName)localObject5).getLocalPart();
      if (j != 0) {
        str3 = str3 + " xmlns:ens=\"" + str1 + "\"";
      }
      if (i != 0) {
        str3 = str3 + " xmlns:tns=\"" + (String)localObject4 + "\"";
      }
      str3 = str3 + ">";
      str3 = str3 + (String)localObject3;
      str3 = str3 + "</";
      if (j != 0) {
        str3 = str3 + "ens:";
      }
      str3 = str3 + ((QName)localObject5).getLocalPart();
      str3 = str3 + ">";
      try
      {
        XMLStreamReader localXMLStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(new ByteArrayInputStream(str3.getBytes()));
        Document localDocument = new StaxBuilder().build(localXMLStreamReader);
        localElement = localDocument.getRootElement();
      }
      catch (Throwable localThrowable)
      {
        runtimeError("Problem with XML \"" + (String)localObject3 + "\".", localDPWSObject);
        return "";
      }
    }
    try
    {
      Call localObject1 = new Call(localDPWSPortType.getServiceProxy());
      Element localObject2 = localElement;
      if (localDPWSOperation.getOperationType() == OperationType.ONE_WAY)
      {
        ((Call)localObject1).send(localDPWSOperation.getInputAction(), localElement);
      }
      else if (localDPWSOperation.getOperationType() == OperationType.REQUEST_RESPONSE)
      {
        MyResponseCallback localObject3 = new MyResponseCallback(localDPWSOperation.getOutputAction(), localDPWSObject);
        ((Call)localObject1).setResponseCallback((ResponseCallback)localObject3);
        localObject4 = new Callable()
        {
          public Document call()
            throws DPWSException
          {
            localObject1.invoke(localDPWSOperation.getInputAction(), localObject2);
            return localObject3.getResponse();
          }
        };
        Future localFuture = executor.submit((Callable)localObject4);
        localObject5 = null;
        str1 = null;
        try
        {
          localObject5 = (Document)localFuture.get(30000L, TimeUnit.MILLISECONDS);
        }
        catch (TimeoutException localTimeoutException)
        {
          str1 = "Timed out";
        }
        catch (InterruptedException localInterruptedException)
        {
          str1 = "Interrupted";
        }
        catch (ExecutionException localExecutionException)
        {
          str1 = "ExecutionException";
        }
        String str2;
        if (localObject5 != null)
        {
          if (((Document)localObject5).getRootElement().getChildren().size() == 0) {
            return ((Document)localObject5).getRootElement().getText();
          }
          str2 = Utils.getXML(((Document)localObject5).getRootElement());
          if (str2 != null) {
            return str2;
          }
          runtimeError("getXML failed.", localDPWSObject);
        }
        else
        {
          str2 = "No response";
          if (str1 != null) {
            str2 = str2 + " (" + str1 + ")";
          }
          runtimeError(str2, localDPWSObject);
        }
      }
      else
      {
        runtimeError("Illegal operation type: " + localDPWSOperation.getOperationType(), localDPWSObject);
      }
    }
    catch (DPWSException localDPWSException)
    {
      runtimeError("DPWSException: " + localDPWSException.getMessage(), localDPWSObject);
      if (Editor.debugMode) {
        localDPWSException.printStackTrace();
      }
      return "";
    }
    return "";
  }
  
  public void runtimeError(String paramString)
  {
    runtimeError(paramString, null);
  }
  
  public void runtimeError(String paramString, DPWSObject paramDPWSObject)
  {
    String str = this.operationName + " - " + paramString;
    super.runtimeError(str);
    if (paramDPWSObject != null) {
      paramDPWSObject.addFault(str);
    }
  }
  
  public boolean isValidCall(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return paramObject instanceof DPWSObject;
  }
  
  public static String compile(DPWSPortType paramDPWSPortType, int paramInt, String paramString)
  {
    if (paramDPWSPortType != null)
    {
      DPWSOperation localDPWSOperation = paramDPWSPortType.getOperation(paramString);
      if (localDPWSOperation == null) {
        return "Operation \"" + paramString + "\" not found in porttype \"" + paramDPWSPortType.getName() + "\".";
      }
      if ((localDPWSOperation.getOperationType() != OperationType.ONE_WAY) && (localDPWSOperation.getOperationType() != OperationType.REQUEST_RESPONSE)) {
        return "Operation \"" + paramString + "\" is not of type One Way or Request Response.";
      }
      int i = localDPWSOperation.getInputs().size();
      if ((i == 0) && (paramInt != 0)) {
        return "Opeartion \"" + paramString + "\" does not accept arguments.";
      }
      if ((i != 0) && (paramInt != 1)) {
        return "Operation \"" + paramString + "\" must be called with exactly one argument.";
      }
    }
    return null;
  }
  
  private class MyResponseCallback
    implements ResponseCallback
  {
    private String action;
    private Document response;
    private DPWSObject obj;
    
    public MyResponseCallback(String paramString, DPWSObject paramDPWSObject)
    {
      this.action = paramString;
      this.response = null;
      this.obj = paramDPWSObject;
    }
    
    public Document getResponse()
    {
      return this.response;
    }
    
    public void invoke(DPWSContext paramDPWSContext, String paramString, XMLStreamReader paramXMLStreamReader)
      throws DPWSException
    {
      if (!paramString.equals(this.action))
      {
        DPWSExecutable.this.runtimeError("Unexpected response action \"" + paramString + "\", expected \"" + this.action + "\"", this.obj);
        throw new DPWSException();
      }
      if (paramXMLStreamReader == null)
      {
        DPWSExecutable.this.runtimeError("Reponse body is null.", this.obj);
        throw new DPWSException();
      }
      this.response = Utils.readXML(paramXMLStreamReader);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/DPWSExecutable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */