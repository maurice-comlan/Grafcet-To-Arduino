package grafchart.dpws.db;

import grafchart.dpws.ServicesDialogNode;
import grafchart.sfc.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.PortType;
import javax.xml.namespace.QName;

import org.soda.dpws.DPWSException;
import org.soda.dpws.DeviceProxy;
import org.soda.dpws.ServiceProxy;
import org.soda.dpws.metadata.api.DeviceInfo;
import org.soda.dpws.metadata.api.DeviceMetadata;
import org.soda.dpws.metadata.api.ModelInfo;
import org.soda.dpws.wsdl.WSDLInfo;

public class DPWSDevice
  implements DPWSElement, Comparable
{
  private DeviceProxy device;
  private ArrayList<DPWSService> services;
  private String metadata;
  private String nodeDisplayString;
  
  public DPWSDevice(DeviceProxy paramDeviceProxy)
  {
    this.device = paramDeviceProxy;
    this.services = new ArrayList();
    this.nodeDisplayString = null;
    this.metadata = "";
    try
    {
      DeviceMetadata localDeviceMetadata = paramDeviceProxy.getDeviceMetadata();
      Object localObject3;
      Object localObject4;
      if (localDeviceMetadata != null)
      {
          ModelInfo localObject1 = localDeviceMetadata.getModelInfo();
        Object localObject5;
        if (localObject1 != null)
        {
          String localObject2 = ((ModelInfo)localObject1).getManufacturer(Locale.ENGLISH);
          if (localObject2 != null)
          {
            this.metadata = (this.metadata + "Manufacturer: " + (String)localObject2 + "\n");
          }
          else
          {
            this.metadata += "Manufacturers:\n";
            localObject3 = ((ModelInfo)localObject1).getManufacturers();
            localObject4 = ((Collection)localObject3).iterator();
            while (((Iterator)localObject4).hasNext())
            {
              localObject5 = ((Iterator)localObject4).next();
              this.metadata = (this.metadata + "  " + localObject5 + "\n");
            }
          }
          localObject3 = ((ModelInfo)localObject1).getManufacturerURL();
          if (localObject3 != null) {
            this.metadata = (this.metadata + "ManufacturerURL: " + (String)localObject3 + "\n");
          }
          localObject4 = ((ModelInfo)localObject1).getModelName(Locale.ENGLISH);
          if (localObject4 != null)
          {
            this.metadata = (this.metadata + "ModelName: " + (String)localObject4 + "\n");
          }
          else
          {
            this.metadata += "ModelNames:\n";
            localObject5 = ((ModelInfo)localObject1).getModelNames().iterator();
            while (((Iterator)localObject5).hasNext())
            {
              Object localObject6 = ((Iterator)localObject5).next();
              this.metadata = (this.metadata + "  " + localObject6 + "\n");
            }
          }
          localObject5 = ((ModelInfo)localObject1).getModelNumber();
          if (localObject5 != null) {
            this.metadata = (this.metadata + "ModelNumber: " + (String)localObject5 + "\n");
          }
          Object localObject6 = ((ModelInfo)localObject1).getModelURL();
          if (localObject6 != null) {
            this.metadata = (this.metadata + "ModelURL: " + (String)localObject6 + "\n");
          }
          String str = ((ModelInfo)localObject1).getPresentationURL();
          if (str != null) {
            this.metadata = (this.metadata + "PresentationURL: " + str + "\n");
          }
        }
        else
        {
          this.metadata += "The device does not specify the mandatory ThisModel metadata.\n";
        }
        this.metadata += "\n";
        DeviceInfo localObject2 = localDeviceMetadata.getDeviceInfo();
        if (localObject2 != null)
        {
          localObject3 = ((DeviceInfo)localObject2).getFriendlyName(Locale.ENGLISH.toString());
          if (localObject3 != null)
          {
            this.metadata = (this.metadata + "FriendlyName: " + (String)localObject3 + "\n");
            this.nodeDisplayString = ((String)localObject3);
          }
          else
          {
            this.metadata += "FriendlyNames:\n";
            localObject4 = ((DeviceInfo)localObject2).getFriendlyNames().iterator();
            while (((Iterator)localObject4).hasNext())
            {
              localObject5 = ((Iterator)localObject4).next();
              if (this.nodeDisplayString == null) {
                this.nodeDisplayString = localObject5.toString();
              }
              this.metadata = (this.metadata + "  " + localObject5 + "\n");
            }
          }
          localObject4 = ((DeviceInfo)localObject2).getFirmwareVersion();
          if (localObject4 != null) {
            this.metadata = (this.metadata + "FirmwareVersion: " + ((DeviceInfo)localObject2).getFirmwareVersion() + "\n");
          }
          localObject5 = ((DeviceInfo)localObject2).getSerialNumber();
          if (localObject5 != null) {
            this.metadata = (this.metadata + "SerialNumber: " + (String)localObject5 + "\n");
          }
        }
        else
        {
          this.metadata += "The device does not specify the mandatory ThisDevice metadata.\n";
        }
      }
      else
      {
        this.metadata += "The device has no metadata. It is lacking the mandatory ThisModel and ThisDevice metadata.\n";
      }
      this.metadata += "\n";
      Object localObject1 = paramDeviceProxy.getTypes();
      if (localObject1 != null)
      {
        this.metadata += "Types:\n";
        Iterator localObject2 = ((List)localObject1).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = ((Iterator)localObject2).next();
          if ((localObject3 instanceof QName))
          {
            localObject4 = (QName)localObject3;
            this.metadata = (this.metadata + "  " + ((QName)localObject4).getNamespaceURI() + "/" + ((QName)localObject4).getLocalPart() + "\n");
          }
          else
          {
            this.metadata = (this.metadata + "  " + localObject3.toString() + "\n");
          }
        }
      }
      else
      {
        this.metadata += "No Types\n";
      }
      Object localObject2 = paramDeviceProxy.getScopes();
      if (localObject2 != null)
      {
        this.metadata += "Scopes:\n";
        localObject3 = ((List)localObject2).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = ((Iterator)localObject3).next();
          this.metadata = (this.metadata + "  " + localObject4.toString() + "\n");
        }
      }
      else
      {
        this.metadata += "No Scopes\n";
      }
    }
    catch (DPWSException localDPWSException)
    {
      Utils.writeException(localDPWSException, "Failed to fetch device metadata.");
      this.metadata = "ERROR: Failed to fetch device metadata.";
    }
    if (this.nodeDisplayString == null) {
      this.nodeDisplayString = "*** No Friendly Name ***";
    }
  }
  
  public String getDeviceMetadata()
  {
    return this.metadata;
  }
  
  public String getServiceDocumentation()
  {
    return null;
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
    return this;
  }
  
  public DeviceProxy getDeviceProxy()
  {
    return this.device;
  }
  
  public DPWSService getService()
  {
    return null;
  }
  
  public ServiceProxy getServiceProxy()
  {
    return null;
  }
  
  public Definition getWSDL()
  {
    return null;
  }
  
  public String getWSDLLocation()
  {
    return null;
  }
  
  public WSDLInfo getWSDLInfo()
  {
    return null;
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
    return Util.createServicesDialogNode(this.nodeDisplayString, this);
  }
  
  public String getDeviceAddress()
  {
    return this.device.getAddress();
  }
  
  public boolean isDeviceValid()
  {
    return this.device.getStatus() != 1;
  }
  
  public DPWSService addService(ServiceProxy paramServiceProxy, Definition paramDefinition, String paramString, WSDLInfo paramWSDLInfo)
  {
    DPWSService localDPWSService = new DPWSService(paramServiceProxy, paramDefinition, paramString, paramWSDLInfo, this);
    this.services.add(localDPWSService);
    return localDPWSService;
  }
  
  public List<DPWSService> getServices()
  {
    return this.services;
  }
  
  public String toString()
  {
    return this.nodeDisplayString;
  }
  
  public int compareTo(Object paramObject)
  {
    return this.nodeDisplayString.compareToIgnoreCase(((DPWSDevice)paramObject).nodeDisplayString);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/db/DPWSDevice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */