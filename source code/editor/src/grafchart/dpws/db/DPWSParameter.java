package grafchart.dpws.db;

import grafchart.dpws.ServicesDialogNode;
import grafchart.sfc.Utils;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.wsdl.PortType;
import javax.xml.namespace.QName;
import org.soda.dpws.DeviceProxy;
import org.soda.dpws.ServiceProxy;
import org.soda.dpws.wsdl.WSDLInfo;
import org.w3c.dom.Element;

public class DPWSParameter
  implements DPWSElement
{
  private Part part;
  private DPWSOperation parent;
  
  public DPWSParameter(Part paramPart, DPWSOperation paramDPWSOperation)
  {
    this.part = paramPart;
    this.parent = paramDPWSOperation;
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
    return this.parent.getOperationDocumentation();
  }
  
  public String getParameterDocumentation()
  {
    if (this.part.getDocumentationElement() != null) {
      return Util.washDoc(this.part.getDocumentationElement().getTextContent());
    }
    return "*** No documentation ***";
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
    return this.parent.getOperation();
  }
  
  public ServicesDialogNode createJTreeNode()
  {
    Utils.writeInternalError("DPWSParameter.createJTreeNode() called. Use createJTreeNode(String) instead.");
    return createJTreeNode("?");
  }
  
  public ServicesDialogNode createJTreeNode(String paramString)
  {
    ServicesDialogNode localServicesDialogNode = Util.createServicesDialogNode(paramString + ": " + this.part.getName() + ":" + getName(), this);
    localServicesDialogNode.setAllowsChildren(false);
    return localServicesDialogNode;
  }
  
  public QName getName()
  {
    if (this.part.getElementName() != null) {
      return this.part.getElementName();
    }
    return this.part.getTypeName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/db/DPWSParameter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */