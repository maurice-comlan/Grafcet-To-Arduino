package grafchart.dpws.db;

import grafchart.dpws.ServicesDialogNode;
import grafchart.sfc.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.wsdl.Definition;
import javax.wsdl.Input;
import javax.wsdl.Operation;
import javax.wsdl.OperationType;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.PortType;
import javax.xml.namespace.QName;
import org.soda.dpws.DeviceProxy;
import org.soda.dpws.ServiceProxy;
import org.soda.dpws.wsdl.WSDLInfo;
import org.w3c.dom.Element;

public class DPWSOperation
  implements DPWSElement
{
  private static final String WSA_URI = "http://schemas.xmlsoap.org/ws/2004/08/addressing";
  private Operation operation;
  private DPWSPortType parent;
  private ArrayList<DPWSParameter> inputs;
  private ArrayList<DPWSParameter> outputs;
  
  public DPWSOperation(Operation paramOperation, DPWSPortType paramDPWSPortType)
  {
    this.operation = paramOperation;
    this.parent = paramDPWSPortType;
    this.inputs = new ArrayList();
    this.outputs = new ArrayList();
  }
  
  public String getDeviceMetadata()
  {
    return this.parent.getDeviceMetadata();
  }
  
  public String getServiceDocumentation()
  {
    return this.parent.getServiceDocumentation();
  }
  
  public String getPortTypeDocumentation()
  {
    return this.parent.getPortTypeDocumentation();
  }
  
  public String getOperationDocumentation()
  {
    if (this.operation.getDocumentationElement() != null) {
      return Util.washDoc(this.operation.getDocumentationElement().getTextContent());
    }
    return "*** No documentation ***";
  }
  
  public String getParameterDocumentation()
  {
    return null;
  }
  
  public DPWSDevice getDevice()
  {
    return this.parent.getDevice();
  }
  
  public DeviceProxy getDeviceProxy()
  {
    return this.parent.getDeviceProxy();
  }
  
  public DPWSService getService()
  {
    return this.parent.getService();
  }
  
  public ServiceProxy getServiceProxy()
  {
    return this.parent.getServiceProxy();
  }
  
  public Definition getWSDL()
  {
    return this.parent.getWSDL();
  }
  
  public String getWSDLLocation()
  {
    return this.parent.getWSDLLocation();
  }
  
  public WSDLInfo getWSDLInfo()
  {
    return this.parent.getWSDLInfo();
  }
  
  public PortType getPortType()
  {
    return this.parent.getPortType();
  }
  
  public Operation getOperation()
  {
    return this.operation;
  }
  
  public ServicesDialogNode createJTreeNode()
  {
    String str = "";
    if (this.operation.getStyle() == OperationType.ONE_WAY) {
      str = "One Way";
    } else if (this.operation.getStyle() == OperationType.REQUEST_RESPONSE) {
      str = "Request Response";
    } else if (this.operation.getStyle() == OperationType.SOLICIT_RESPONSE) {
      str = "Solicit Response";
    } else if (this.operation.getStyle() == OperationType.NOTIFICATION) {
      str = "Notification";
    } else {
      str = "?";
    }
    return Util.createServicesDialogNode(this.operation.getName() + " : " + str, this);
  }
  
  public DPWSParameter addInput(Part paramPart)
  {
    DPWSParameter localDPWSParameter = new DPWSParameter(paramPart, this);
    this.inputs.add(localDPWSParameter);
    return localDPWSParameter;
  }
  
  public DPWSParameter addOutput(Part paramPart)
  {
    DPWSParameter localDPWSParameter = new DPWSParameter(paramPart, this);
    this.outputs.add(localDPWSParameter);
    return localDPWSParameter;
  }
  
  public List<DPWSParameter> getInputs()
  {
    return this.inputs;
  }
  
  public List<DPWSParameter> getOutputs()
  {
    return this.outputs;
  }
  
  public OperationType getOperationType()
  {
    return this.operation.getStyle();
  }
  
  public String getName()
  {
    return this.operation.getName();
  }
  
  private String getDefaultActionBase()
  {
    return this.parent.getName() + "/" + this.operation.getName();
  }
  
  public String getInputAction()
  {
    Input localInput = this.operation.getInput();
    if (localInput != null)
    {
      QName localQName = (QName)localInput.getExtensionAttribute(new QName("http://schemas.xmlsoap.org/ws/2004/08/addressing", "Action"));
      if (localQName != null) {
        return localQName.getLocalPart();
      }
      if (this.operation.getStyle() == OperationType.ONE_WAY) {
        return getDefaultActionBase();
      }
      if (this.operation.getStyle() == OperationType.REQUEST_RESPONSE) {
        return getDefaultActionBase() + "Request";
      }
      if (this.operation.getStyle() == OperationType.SOLICIT_RESPONSE) {
        return getDefaultActionBase() + "Response";
      }
      Utils.writeInternalError("Unexpected OperationType " + this.operation.getStyle());
    }
    return null;
  }
  
  public String getOutputAction()
  {
    Output localOutput = this.operation.getOutput();
    if (localOutput != null)
    {
      QName localQName = (QName)localOutput.getExtensionAttribute(new QName("http://schemas.xmlsoap.org/ws/2004/08/addressing", "Action"));
      if (localQName != null) {
        return localQName.getLocalPart();
      }
      if (this.operation.getStyle() == OperationType.REQUEST_RESPONSE) {
        return getDefaultActionBase() + "Response";
      }
      if (this.operation.getStyle() == OperationType.SOLICIT_RESPONSE) {
        return getDefaultActionBase() + "Solicit";
      }
      if (this.operation.getStyle() == OperationType.NOTIFICATION) {
        return getDefaultActionBase();
      }
      Utils.writeInternalError("Unexpected OperationType " + this.operation.getStyle());
    }
    return null;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/db/DPWSOperation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */