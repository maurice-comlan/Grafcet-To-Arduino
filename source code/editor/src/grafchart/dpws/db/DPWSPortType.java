package grafchart.dpws.db;

import grafchart.dpws.ServicesDialogNode;
import grafchart.sfc.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.PortType;
import javax.xml.namespace.QName;
import org.soda.dpws.DeviceProxy;
import org.soda.dpws.ServiceProxy;
import org.soda.dpws.wsdl.WSDLInfo;
import org.w3c.dom.Element;

public class DPWSPortType
  implements DPWSElement
{
  public static final String WSE_URI = "http://schemas.xmlsoap.org/ws/2004/08/eventing";
  private PortType portType;
  private DPWSService parent;
  private ArrayList<DPWSOperation> operations;
  
  public DPWSPortType(PortType paramPortType, DPWSService paramDPWSService)
  {
    this.portType = paramPortType;
    this.parent = paramDPWSService;
    this.operations = new ArrayList();
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
    if (this.portType.getDocumentationElement() != null) {
      return Util.washDoc(this.portType.getDocumentationElement().getTextContent());
    }
    return "*** No documentation ***";
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
    return this.portType;
  }
  
  public Operation getOperation()
  {
    return null;
  }
  
  public ServicesDialogNode createJTreeNode()
  {
    return Util.createServicesDialogNode(this.portType.getQName().getLocalPart(), this);
  }
  
  public DPWSOperation addOperation(Operation paramOperation)
  {
    DPWSOperation localDPWSOperation = new DPWSOperation(paramOperation, this);
    this.operations.add(localDPWSOperation);
    return localDPWSOperation;
  }
  
  public List<DPWSOperation> getOperations()
  {
    return this.operations;
  }
  
  public DPWSOperation getOperation(String paramString)
  {
    Iterator localIterator = this.operations.iterator();
    while (localIterator.hasNext())
    {
      DPWSOperation localDPWSOperation = (DPWSOperation)localIterator.next();
      if (localDPWSOperation.getName().equals(paramString)) {
        return localDPWSOperation;
      }
    }
    return null;
  }
  
  public String getName()
  {
    return this.portType.getQName().getNamespaceURI() + "/" + this.portType.getQName().getLocalPart();
  }
  
  public String getNamespace()
  {
    return this.portType.getQName().getNamespaceURI();
  }
  
  public String toString()
  {
    return "(" + getDevice().toString() + ") " + getName();
  }
  
  public boolean isEventSource()
  {
    QName localQName = (QName)this.portType.getExtensionAttribute(new QName("http://schemas.xmlsoap.org/ws/2004/08/eventing", "EventSource"));
    if (localQName != null) {
      return Utils.xsdBooleanStringToBoolean(localQName.getLocalPart());
    }
    return false;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/db/DPWSPortType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */