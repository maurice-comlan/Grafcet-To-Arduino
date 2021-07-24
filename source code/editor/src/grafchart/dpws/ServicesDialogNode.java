package grafchart.dpws;

import grafchart.dpws.db.DPWSElement;
import javax.swing.tree.DefaultMutableTreeNode;
import org.soda.dpws.ClientEndpointReference;
import org.soda.dpws.DeviceProxy;
import org.soda.dpws.ServiceProxy;

public class ServicesDialogNode
  extends DefaultMutableTreeNode
{
  private DPWSElement e;
  
  public ServicesDialogNode(String paramString, DPWSElement paramDPWSElement)
  {
    super(paramString);
    this.e = paramDPWSElement;
  }
  
  public DPWSElement getDPWSElement()
  {
    return this.e;
  }
  
  public String getDeviceAddress()
  {
    return this.e.getDeviceProxy().getAddress();
  }
  
  public String getDeviceMetadata()
  {
    return this.e.getDeviceMetadata();
  }
  
  public String getServiceAddress()
  {
    ServiceProxy localServiceProxy = this.e.getServiceProxy();
    return localServiceProxy != null ? localServiceProxy.getDefaultEndpoint().getAddress() : null;
  }
  
  public String getServiceDocumentation()
  {
    return this.e.getServiceDocumentation();
  }
  
  public String getPortTypeDocumentation()
  {
    return this.e.getPortTypeDocumentation();
  }
  
  public String getOperationDocumentation()
  {
    return this.e.getOperationDocumentation();
  }
  
  public String getParameterDocumentation()
  {
    return this.e.getParameterDocumentation();
  }
  
  public String getWSDLLocation()
  {
    return this.e.getWSDLLocation();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/ServicesDialogNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */