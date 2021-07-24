package grafchart.dpws;

import grafchart.dpws.db.DPWSDevice;
import grafchart.dpws.db.DPWSOperation;
import grafchart.dpws.db.DPWSPortType;
import grafchart.dpws.db.DPWSService;
import grafchart.sfc.Editor;
import grafchart.sfc.Utils;
import grafchart.util.EnumerationIterator;
import grafchart.util.SortedList;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import javax.wsdl.Definition;
import javax.wsdl.Input;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.OperationType;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.PortType;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.stream.XMLStreamReader;
import org.soda.dpws.DPWSContext;
import org.soda.dpws.DPWSException;
import org.soda.dpws.DPWSNetworks;
import org.soda.dpws.DeviceExplorer;
import org.soda.dpws.DeviceProxy;
import org.soda.dpws.DeviceProxyEvent;
import org.soda.dpws.DeviceProxyListener;
import org.soda.dpws.LocalIPAddress;
import org.soda.dpws.ServiceProxy;
import org.soda.dpws.addressing.EndpointReference;
import org.soda.dpws.metadata.api.MetadataSection;
import org.soda.dpws.metadata.api.ServiceMetadata;
import org.soda.dpws.registry.discovery.service.DiscoveryMessageType;
import org.soda.dpws.server.GenEventHandler;
import org.soda.dpws.wsdl.PortTypeInfo;
import org.soda.dpws.wsdl.WSDLInfo;

public class Services
  extends Observable
{
  private WSDLReader wsdlReader = WSDLFactory.newInstance().newWSDLReader();
  private DeviceExplorer deviceExplorer;
  private SortedList<DPWSDevice> devices;
  private boolean isRefreshing;
  private static Class genEventHandler = GenEventHandler.class;
  private Method genEventCallback;
  
  public Services(String paramString)
    throws DPWSException, WSDLException
  {
    this.wsdlReader.setFeature("javax.wsdl.verbose", false);
    LocalIPAddress localLocalIPAddress1 = null;
    if (paramString != null)
    {
      int i = 0;
      try
      {
        Iterator localIterator1 = new EnumerationIterator(NetworkInterface.getNetworkInterfaces()).iterator();
        while (localIterator1.hasNext())
        {
          NetworkInterface localNetworkInterface = (NetworkInterface)localIterator1.next();
          boolean bool = localNetworkInterface.getName().equals(paramString);
          Iterator localIterator2 = new EnumerationIterator(localNetworkInterface.getInetAddresses()).iterator();
          while (localIterator2.hasNext())
          {
            InetAddress localInetAddress = (InetAddress)localIterator2.next();
            if (((localInetAddress instanceof Inet4Address)) && ((bool) || (localInetAddress.getHostAddress().equals(paramString)))) {
              try
              {
                localLocalIPAddress1 = DPWSNetworks.getLocalIPAddress(localInetAddress);
                localLocalIPAddress1.getCache();
              }
              catch (DPWSException localDPWSException)
              {
                Utils.writeException(localDPWSException, "getLocalIPAddress() failed.");
                i = 1;
              }
            }
          }
          if ((bool) && (localLocalIPAddress1 == null) && (i == 0))
          {
            Utils.writeError("Cannot use network interface \"" + paramString + "\" for WS-Discovery (IPv4 required).");
            i = 1;
          }
        }
      }
      catch (SocketException localSocketException)
      {
        Utils.writeError("Unable to list network interfaces.");
        i = 1;
      }
      if ((localLocalIPAddress1 == null) && (i == 0))
      {
        Utils.writeError("dpwsDiscovery: \"" + paramString + "\" not found.");
        i = 1;
      }
    }
    this.deviceExplorer = new DeviceExplorer(localLocalIPAddress1 == null);
    if (localLocalIPAddress1 != null)
    {
      this.deviceExplorer.addLocalIPAddress(localLocalIPAddress1);
    }
    else
    {
        String localObject = "N/A";
      LocalIPAddress localLocalIPAddress2 = (LocalIPAddress)this.deviceExplorer.getLocalIPAddresses().get(0);
      if (localLocalIPAddress2 != null) {
        localObject = localLocalIPAddress2.getAddress().getHostAddress();
      }
      if (!Editor.isTest) {
        Utils.writeInformation("Using default IP (" + (String)localObject + ") for WS-Discovery.");
      }
    }
    this.deviceExplorer.addDeviceProxyListener(new DeviceProxyListener()
    {
      public void handleDeviceProxyEvent(DeviceProxyEvent paramAnonymousDeviceProxyEvent)
        throws DPWSException
      {
        String str = paramAnonymousDeviceProxyEvent.getEndpointReference().getAddress();
        int i = paramAnonymousDeviceProxyEvent.getMsgType().id;
        int j = i == 1 ? 1 : 0;
        int k = i == 2 ? 1 : 0;
        if ((j != 0) || (k != 0))
        {
          if (j != 0) {
            Utils.writeInformation("WS-Discovery Hello: " + str);
          } else {
            Utils.writeInformation("WS-Discovery Bye:   " + str);
          }
          Iterator localIterator = Services.this.devices.iterator();
          while (localIterator.hasNext())
          {
            DPWSDevice localDPWSDevice = (DPWSDevice)localIterator.next();
            if (str.equals(localDPWSDevice.getDeviceAddress()))
            {
              localIterator.remove();
              Services.this.setChanged();
            }
          }
          if ((Services.this.hasChanged()) && (j != 0))
          {
            Utils.writeInformation("Unclean shutdown of device detected. Emptying cache to avoid problems during execution.");
            Services.this.deviceExplorer.emptyCache();
          }
          Services.this.notifyObservers();
          if (j != 0) {
            Services.this.addDevice(Services.this.deviceExplorer.getCachedDevice(str), false);
          }
        }
        else
        {
          Utils.writeInternalError("Unhandled DeviceProxyEvent (" + i + "): " + str);
        }
      }
    });
    this.deviceExplorer.startDiscoveryListen();
    Object[] localObject = new Class[2];
    localObject[0] = DPWSContext.class;
    localObject[1] = XMLStreamReader.class;
    try
    {
      this.genEventCallback = genEventHandler.getMethod("invoke", (Class[])localObject);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new DPWSException("invoke method not found in org.soda.dpws.server.GenEventHandler interface.", localNoSuchMethodException);
    }
    this.devices = new SortedList();
  }
  
  public void refresh(final boolean paramBoolean)
  {
    synchronized (this)
    {
      if (this.isRefreshing)
      {
        Utils.writeInformation("DPWS discovery is already running...");
        return;
      }
      this.isRefreshing = true;
    }
    Utils.writeInformation("DPWS discover...");
    synchronized (this)
    {
      this.devices.clear();
    }
    setChanged();
    notifyObservers();
    this.deviceExplorer.emptyCache();
    new Thread(new Runnable()
    {
      public void run()
      {
        List localList = null;
        try
        {
          localList = Services.this.deviceExplorer.lookup(new ArrayList(), new ArrayList());
        }
        catch (DPWSException localDPWSException)
        {
          Utils.writeException(localDPWSException, "DPWS discovery failed.");
        }
        if (localList != null)
        {
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            DeviceProxy localDeviceProxy = (DeviceProxy)localIterator.next();
            Services.this.addDevice(localDeviceProxy, paramBoolean);
          }
        }
        Services.this.isRefreshing = false;
        Services.this.setChanged();
        Services.this.notifyObservers();
      }
    }).start();
  }
  
  public boolean isRefreshing()
  {
    return this.isRefreshing;
  }
  
  private void addDevice(DeviceProxy paramDeviceProxy, boolean paramBoolean)
  {
    String str1 = "Device: " + paramDeviceProxy.getAddress();
    boolean bool1 = print(str1, false, paramBoolean);
    Collection localCollection = null;
    try
    {
      localCollection = paramDeviceProxy.getHostedServices();
    }
    catch (DPWSException localDPWSException1)
    {
      bool1 = print(str1, bool1, true);
      Utils.writeError("  Failed to list services in device.");
      return;
    }
    if (((localCollection == null) || (localCollection.size() == 0)) && (paramBoolean)) {
      Utils.writeInformation("  No services.");
    }
    DPWSDevice localDPWSDevice = new DPWSDevice(paramDeviceProxy);
    Iterator localIterator1 = localCollection.iterator();
    while (localIterator1.hasNext())
    {
      ServiceProxy localServiceProxy = (ServiceProxy)localIterator1.next();
      String str2 = "  Service: " + localServiceProxy.getId();
      boolean bool2 = print(str2, false, paramBoolean);
      ServiceMetadata localServiceMetadata = null;
      try
      {
        localServiceMetadata = localServiceProxy.getServiceMetadata();
      }
      catch (DPWSException localDPWSException2)
      {
        if (paramBoolean) {
          Utils.writeInformation("    Not a DPWS Service.");
        }
      }
      //continue;
      List localList = localServiceMetadata.getWsdls();
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
      {
        MetadataSection localMetadataSection = (MetadataSection)localIterator2.next();
        String str3 = "    WSDL: " + localMetadataSection.getLocation();
        boolean bool3 = print(str3, false, paramBoolean);
        Definition localDefinition = null;
        String str4 = null;
        try
        {
          str4 = localMetadataSection.getLocation();
          localDefinition = this.wsdlReader.readWSDL(str4);
        }
        catch (WSDLException localWSDLException)
        {
          bool1 = print(str1, bool1, true);
          bool2 = print(str2, bool2, true);
          bool3 = print(str3, bool3, true);
          Utils.writeError("      Failed to load WSDL: " + localWSDLException.getMessage());
          continue;
        }
        
        WSDLInfo localWSDLInfo = new WSDLInfo();
        DPWSService localDPWSService = localDPWSDevice.addService(localServiceProxy, localDefinition, str4, localWSDLInfo);
        Iterator localIterator3 = localDefinition.getPortTypes().values().iterator();
        while (localIterator3.hasNext())
        {
          PortType localPortType = (PortType)localIterator3.next();
          DPWSPortType localDPWSPortType = localDPWSService.addPortType(localPortType);
          PortTypeInfo localPortTypeInfo = new PortTypeInfo(localPortType.getQName(), null, genEventHandler, true);
          localWSDLInfo.addPortType(localPortTypeInfo);
          Iterator localIterator4 = localPortType.getOperations().iterator();
          while (localIterator4.hasNext())
          {
            Operation localOperation = (Operation)localIterator4.next();
            if (localOperation.getStyle() != OperationType.SOLICIT_RESPONSE)
            {
              DPWSOperation localDPWSOperation = localDPWSPortType.addOperation(localOperation);
              Iterator localIterator5;
              Part localPart;
              if ((localOperation.getStyle() == OperationType.ONE_WAY) || (localOperation.getStyle() == OperationType.REQUEST_RESPONSE))
              {
                localIterator5 = localOperation.getInput().getMessage().getOrderedParts(null).iterator();
                while (localIterator5.hasNext())
                {
                  localPart = (Part)localIterator5.next();
                  localDPWSOperation.addInput(localPart);
                }
              }
              if ((localOperation.getStyle() == OperationType.REQUEST_RESPONSE) || (localOperation.getStyle() == OperationType.NOTIFICATION))
              {
                localIterator5 = localOperation.getOutput().getMessage().getOrderedParts(null).iterator();
                while (localIterator5.hasNext())
                {
                  localPart = (Part)localIterator5.next();
                  localDPWSOperation.addOutput(localPart);
                }
              }
              if (localOperation.getStyle() == OperationType.NOTIFICATION) {
                localPortTypeInfo.addOperation(localOperation.getName(), this.genEventCallback, null, localDPWSOperation.getOutputAction(), true);
              }
            }
          }
        }
      }
    }
    if (!localDPWSDevice.getServices().isEmpty())
    {
      this.devices.add(localDPWSDevice);
      setChanged();
      notifyObservers();
    }
  }
  
  private boolean print(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean2) && (!paramBoolean1))
    {
      Utils.writeInformation(paramString);
      paramBoolean1 = true;
    }
    return paramBoolean1;
  }
  
  public List<DPWSDevice> getDatabases()
  {
    return this.devices;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/Services.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */