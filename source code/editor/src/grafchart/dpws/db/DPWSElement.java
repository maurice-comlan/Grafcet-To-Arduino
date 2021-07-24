package grafchart.dpws.db;

import grafchart.dpws.ServicesDialogNode;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.PortType;
import org.soda.dpws.DeviceProxy;
import org.soda.dpws.ServiceProxy;
import org.soda.dpws.wsdl.WSDLInfo;

public abstract interface DPWSElement
{
  public abstract String getDeviceMetadata();
  
  public abstract String getServiceDocumentation();
  
  public abstract String getPortTypeDocumentation();
  
  public abstract String getOperationDocumentation();
  
  public abstract String getParameterDocumentation();
  
  public abstract DPWSDevice getDevice();
  
  public abstract DeviceProxy getDeviceProxy();
  
  public abstract DPWSService getService();
  
  public abstract ServiceProxy getServiceProxy();
  
  public abstract Definition getWSDL();
  
  public abstract String getWSDLLocation();
  
  public abstract WSDLInfo getWSDLInfo();
  
  public abstract PortType getPortType();
  
  public abstract Operation getOperation();
  
  public abstract ServicesDialogNode createJTreeNode();
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/db/DPWSElement.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */