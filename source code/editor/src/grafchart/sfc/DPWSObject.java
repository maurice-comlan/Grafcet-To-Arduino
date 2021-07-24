package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoText;
import grafchart.dpws.Services;
import grafchart.dpws.db.DPWSDevice;
import grafchart.dpws.db.DPWSPortType;
import grafchart.dpws.db.DPWSService;
import grafchart.graphics.MyJGoImage;
import grafchart.sfc.builtin.AbstractExecutable;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.xml.stream.XMLStreamReader;
import org.jdom.Document;
import org.soda.dpws.ClientEndpointReference;
import org.soda.dpws.DPWSContext;
import org.soda.dpws.DPWSException;
import org.soda.dpws.ServiceProxy;
import org.soda.dpws.Subscription;
import org.soda.dpws.registry.Duration;
import org.soda.dpws.server.EventHandler;
import org.soda.dpws.server.EventHandlerListener;
import org.soda.dpws.server.GenEventHandler;

public class DPWSObject
  extends GrafcetObject
  implements Referencable, Observer
{
  private JGoRectangle myRectangle = null;
  private MyJGoImage myCloud = null;
  private JGoText myName = null;
  private String binding = "";
  private String bindingDeviceAddress = "";
  private String bindingServiceId = "";
  private transient DPWSPortType bindingPortType = null;
  private EventHandler eventHandler = null;
  private Subscription subscription = null;
  private LinkedList<Event> events = null;
  private LinkedList<String> faults = new LinkedList();
  
  public DPWSObject() {}
  
  public DPWSObject(Point paramPoint)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myRectangle = new JGoRectangle();
    this.myRectangle.setSize(60, 60);
    this.myRectangle.setPen(new JGoPen(65535, 2, Color.BLUE));
    this.myRectangle.setSelectable(false);
    this.myRectangle.setDraggable(false);
    this.myCloud = new MyJGoImage();
    this.myCloud.loadImage(Editor.getIconLib() + "/cloud.png");
    this.myCloud.setSize(50, 30);
    this.myCloud.setSelectable(false);
    this.myName = new JGoText("DPWS");
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myCloud);
    addObjectAtTail(this.myName);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    DPWSObject localDPWSObject = (DPWSObject)paramJGoArea;
    localDPWSObject.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localDPWSObject.myCloud = ((MyJGoImage)paramJGoCopyEnvironment.copy(this.myCloud));
    localDPWSObject.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localDPWSObject.addObjectAtHead(localDPWSObject.myRectangle);
    localDPWSObject.addObjectAtTail(localDPWSObject.myCloud);
    localDPWSObject.addObjectAtTail(localDPWSObject.myName);
    localDPWSObject.binding = this.binding;
    localDPWSObject.bindingPortType = this.bindingPortType;
    localDPWSObject.bindingDeviceAddress = this.bindingDeviceAddress;
    localDPWSObject.bindingServiceId = this.bindingServiceId;
    localDPWSObject.bindingLookup();
    localDPWSObject.startObserving();
  }
  
  public org.w3c.dom.Element storeXML(org.w3c.dom.Element paramElement)
  {
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("binding", this.binding);
    paramElement.setAttribute("bindingUUID", this.bindingDeviceAddress);
    paramElement.setAttribute("bindingServiceId", this.bindingServiceId);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    return paramElement;
  }
  
  public static DPWSObject loadXML(org.w3c.dom.Element paramElement)
  {
    DPWSObject localDPWSObject = new DPWSObject(new Point());
    localDPWSObject.setName(paramElement.getAttribute("name"));
    localDPWSObject.binding = paramElement.getAttribute("binding");
    localDPWSObject.bindingDeviceAddress = paramElement.getAttribute("bindingUUID");
    localDPWSObject.bindingServiceId = paramElement.getAttribute("bindingServiceId");
    localDPWSObject.bindingLookup();
    localDPWSObject.removeObject(localDPWSObject.myName);
    XMLUtil.restoreBoundingRect(paramElement, localDPWSObject);
    localDPWSObject.addObjectAtTail(localDPWSObject.myName);
    localDPWSObject.startObserving();
    return localDPWSObject;
  }
  
  public Point getLocation(Point paramPoint)
  {
    return getSpotLocation(1, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    setSpotLocation(1, paramInt1, paramInt2);
  }
  
  public void layoutChildren()
  {
    Point localPoint = this.myRectangle.getSpotLocation(6);
    this.myName.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 10);
    this.myCloud.setSpotLocation(0, this.myRectangle, 0);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle, this.myCloud };
  }
  
  public int getNoScaleBottom(Rectangle paramRectangle)
  {
    return paramRectangle.y + paramRectangle.height - (this.myRectangle.getBoundingRect().y + this.myRectangle.getBoundingRect().height);
  }
  
  public Dimension getMinimumSize()
  {
    return new Dimension(Math.max((int)Math.ceil(this.myName.getBoundingRect().getWidth()), 10), (int)Math.ceil(this.myName.getBoundingRect().getHeight() + 20.0D));
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_DPWSObject";
  }
  
  public String toString()
  {
    return getName();
  }
  
  public String getName()
  {
    return this.myName.getText();
  }
  
  public void setName(String paramString)
  {
    this.myName.setText(paramString);
  }
  
  public String getFullName()
  {
    String str = getName();
    GCDocument localGCDocument = getDocument();
    for (GrafcetObject localGrafcetObject = localGCDocument.owner; localGrafcetObject != null; localGrafcetObject = localGCDocument.owner)
    {
      Referencable localReferencable = (Referencable)localGrafcetObject;
      str = localReferencable.getName() + "." + str;
      localGCDocument = localGrafcetObject.getDocument();
    }
    str = localGCDocument.getName() + "." + str;
    return str;
  }
  
  public String getBinding()
  {
    return this.binding;
  }
  
  public String getBindingDeviceAddress()
  {
    return this.bindingDeviceAddress;
  }
  
  public String getBindingServiceId()
  {
    return this.bindingServiceId;
  }
  
  private void bindingLookup()
  {
    this.bindingPortType = null;
    Iterator localIterator1 = Editor.singleton.getDPWSServices().getDatabases().iterator();
    while (localIterator1.hasNext())
    {
      DPWSDevice localDPWSDevice = (DPWSDevice)localIterator1.next();
      if (this.bindingDeviceAddress.equals(localDPWSDevice.getDeviceAddress()))
      {
        Iterator localIterator2 = localDPWSDevice.getServices().iterator();
        while (localIterator2.hasNext())
        {
          DPWSService localDPWSService = (DPWSService)localIterator2.next();
          if ((this.bindingServiceId.isEmpty()) || (this.bindingServiceId.equals(localDPWSService.getId())))
          {
            Iterator localIterator3 = localDPWSService.getPortTypes().iterator();
            while (localIterator3.hasNext())
            {
              DPWSPortType localDPWSPortType = (DPWSPortType)localIterator3.next();
              if (this.binding.equals(localDPWSPortType.getName()))
              {
                this.bindingPortType = localDPWSPortType;
                return;
              }
            }
          }
        }
      }
    }
  }
  
  public DPWSPortType getPortType()
  {
    return this.bindingPortType;
  }
  
  public void setPortType(String paramString1, String paramString2, String paramString3)
  {
    this.bindingDeviceAddress = paramString1;
    this.bindingServiceId = paramString2;
    this.binding = paramString3;
    bindingLookup();
  }
  
  private void startObserving()
  {
    Editor.singleton.getDPWSServices().addObserver(this);
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    bindingLookup();
  }
  
  public void setEventListener(EventHandlerListener paramEventHandlerListener)
  {
    if (this.subscription != null)
    {
      Utils.writeInformation("startup: Canceling previous subscription.");
      unsubscribe();
    }
    this.eventHandler = null;
    if ((paramEventHandlerListener != null) && (this.bindingPortType != null) && (this.bindingPortType.isEventSource())) {
      try
      {
        this.eventHandler = paramEventHandlerListener.createEventHandler(this.bindingPortType.getWSDLInfo(), new GenEventHandler()
        {
          public Object invoke(DPWSContext paramAnonymousDPWSContext, XMLStreamReader paramAnonymousXMLStreamReader)
            throws DPWSException
          {
            org.jdom.Element localElement1 = paramAnonymousDPWSContext.getCurrentMessageHeader();
            org.jdom.Element localElement2 = localElement1.getChild("Action", localElement1.getNamespace("http://schemas.xmlsoap.org/ws/2004/08/addressing"));
            if (localElement2 != null)
            {
              String str1 = localElement2.getText();
              Document localDocument = Utils.readXML(paramAnonymousXMLStreamReader);
              if (localDocument.getRootElement().getChildren().size() == 0)
              {
                DPWSObject.this.events.add(new DPWSObject.Event(str1, localDocument.getRootElement().getText()));
              }
              else
              {
                String str2 = Utils.getXML(localDocument.getRootElement());
                if (str2 != null) {
                  DPWSObject.this.events.add(new DPWSObject.Event( str1, str2));
                } else {
                  Utils.writeError("DPWS Event callback on \"" + DPWSObject.this.getFullName() + "\": getXML failed.");
                }
              }
            }
            else
            {
              Utils.writeError("DPWS Event callback on \"" + DPWSObject.this.getFullName() + "\": No action field in message header.");
            }
            return null;
          }
        });
        this.events = new LinkedList();
      }
      catch (DPWSException localDPWSException)
      {
        Utils.writeException(localDPWSException);
      }
    }
  }
  
  public void subscribe(Duration paramDuration)
  {
    if (this.eventHandler != null)
    {
      if (this.subscription != null)
      {
        Utils.writeInformation("dpwsSubscribe(): Canceling previous subscription.");
        unsubscribe();
      }
      try
      {
        this.subscription = this.bindingPortType.getServiceProxy().getDefaultEndpoint().subscribe(this.eventHandler, paramDuration, null);
      }
      catch (DPWSException localDPWSException)
      {
        Utils.writeException(localDPWSException, "dpwsSubscribe()");
      }
    }
    else
    {
      Utils.writeError("dpwsSubscribe() failed since DPWS Event has not been set up for this application.");
    }
  }
  
  public void unsubscribe()
  {
    if (this.eventHandler != null)
    {
      if (this.subscription != null)
      {
        try
        {
          this.subscription.cancel();
        }
        catch (DPWSException localDPWSException) {}catch (Throwable localThrowable)
        {
          Utils.writeError("dpwsUnsubscribe(): DPWS4j internal error: " + localThrowable.getMessage());
        }
        this.subscription = null;
      }
      else
      {
        Utils.writeError("dpwsUnsubscribe(): No active subscription");
      }
    }
    else {
      Utils.writeError("dpwsUnsubscribe() failed since DPWS Event has not been set up for this application.");
    }
  }
  
  public boolean hasEvent()
  {
    if (this.events != null) {
      return !this.events.isEmpty();
    }
    printEventingNotSetUpError();
    return false;
  }
  
  public boolean hasEvent(String paramString)
  {
    if (this.events != null)
    {
      Iterator localIterator = this.events.iterator();
      while (localIterator.hasNext())
      {
        Event localEvent = (Event)localIterator.next();
        if (localEvent.getAction().equals(paramString)) {
          return true;
        }
      }
    }
    else
    {
      printEventingNotSetUpError();
    }
    return false;
  }
  
  public String getEvent(String paramString)
  {
    if (this.events != null)
    {
      Iterator localIterator = this.events.iterator();
      while (localIterator.hasNext())
      {
        Event localEvent = (Event)localIterator.next();
        if (localEvent.getAction().equals(paramString))
        {
          localIterator.remove();
          return localEvent.getMessage();
        }
      }
    }
    else
    {
      printEventingNotSetUpError();
    }
    return null;
  }
  
  public void addFault(String paramString)
  {
    this.faults.add(paramString);
  }
  
  public boolean hasFault()
  {
    return !this.faults.isEmpty();
  }
  
  public String getFault()
  {
    if (!this.faults.isEmpty()) {
      return (String)this.faults.pop();
    }
    return null;
  }
  
  private void printEventingNotSetUpError()
  {
    Utils.writeError("DPWSObject.hasEvent() called on \"" + getFullName() + "\" which does not have eventing set up.");
  }
  
  public boolean isValidPortType(AbstractExecutable paramAbstractExecutable)
  {
    return isValidPortType(paramAbstractExecutable, true);
  }
  
  public boolean isValidPortType(AbstractExecutable paramAbstractExecutable, boolean paramBoolean)
  {
    if (this.bindingPortType == null)
    {
      if (paramBoolean) {
        paramAbstractExecutable.runtimeError("DPWS Object has no binding: \"" + getName() + "\"");
      }
      return false;
    }
    if (!this.bindingPortType.getDevice().isDeviceValid())
    {
      if (paramBoolean) {
        paramAbstractExecutable.runtimeError("Device is no longer available.");
      }
      return false;
    }
    return true;
  }
  
  private class Event
  {
    private String action;
    private String message;
    
    public Event(String paramString1, String paramString2)
    {
      this.action = paramString1;
      this.message = paramString2;
    }
    
    public String getAction()
    {
      return this.action;
    }
    
    public String getMessage()
    {
      return this.message;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/DPWSObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */