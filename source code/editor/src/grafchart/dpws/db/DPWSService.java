package grafchart.dpws.db;

import grafchart.dpws.ServicesDialogNode;
import java.util.ArrayList;
import java.util.List;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.PortType;
import org.soda.dpws.DeviceProxy;
import org.soda.dpws.ServiceProxy;
import org.soda.dpws.wsdl.WSDLInfo;
import org.w3c.dom.Element;

public class DPWSService
  implements DPWSElement
{
  private ArrayList<DPWSPortType> portTypes;
  private DPWSDevice parent;
  private ServiceProxy service;
  private Definition wsdl;
  private String wsdlLocation;
  private WSDLInfo wsdlInfo;
  
  public DPWSService(ServiceProxy paramServiceProxy, Definition paramDefinition, String paramString, WSDLInfo paramWSDLInfo, DPWSDevice paramDPWSDevice)
  {
    this.service = paramServiceProxy;
    this.wsdl = paramDefinition;
    this.wsdlLocation = paramString;
    this.wsdlInfo = paramWSDLInfo;
    this.parent = paramDPWSDevice;
    this.portTypes = new ArrayList();
  }
  
  public String getDeviceMetadata()
  {
    return this.parent.getDeviceMetadata();
  }
  
  public String getServiceDocumentation()
  {
    if (this.wsdl.getDocumentationElement() != null) {
      return Util.washDoc(this.wsdl.getDocumentationElement().getTextContent());
    }
    return "*** No documentation ***";
  }
  
  public String getPortTypeDocumentation()
  {
    return null;
  }
  
  public String getOperationDocumentation()
  {
    return null;
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
    return this;
  }
  
  public ServiceProxy getServiceProxy()
  {
    return this.service;
  }
  
  public Definition getWSDL()
  {
    return this.wsdl;
  }
  
  public String getWSDLLocation()
  {
    return this.wsdlLocation;
  }
  
  public WSDLInfo getWSDLInfo()
  {
    return this.wsdlInfo;
  }
  
  public PortType getPortType()
  {
    return null;
  }
  
  public Operation getOperation()
  {
    return null;
  }
  
  public ServicesDialogNode createJTreeNode()
  {
    return Util.createServicesDialogNode(this.service.getId(), this);
  }
  
  public String getId()
  {
    return this.service.getId();
  }
  
  public DPWSPortType addPortType(PortType paramPortType)
  {
    DPWSPortType localDPWSPortType = new DPWSPortType(paramPortType, this);
    this.portTypes.add(localDPWSPortType);
    return localDPWSPortType;
  }
  
  public List<DPWSPortType> getPortTypes()
  {
    return this.portTypes;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/db/DPWSService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */