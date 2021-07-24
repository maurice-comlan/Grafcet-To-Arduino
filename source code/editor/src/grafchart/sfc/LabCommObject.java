package grafchart.sfc;

import AST.LabCommParser;
import AST.LabCommScanner;
import AST.Program;
import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoText;
import grafchart.socketio.Client;
import grafchart.socketio.ClientListener;
import grafchart.socketio.SocketServer;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.w3c.dom.Element;
import se.lth.control.orca.OrcaClient;

public class LabCommObject
  extends GrafcetObject
  implements Referencable, SymbolTableObject
{
  private JGoRectangle myRectangle = null;
  private JGoText myLogo = null;
  private JGoText myName = null;
  private String socketHost = "";
  private boolean isSocketServer = false;
  private int socketPort = -1;
  private String specification = "";
  private boolean input = true;
  private boolean output = true;
  private boolean orca = false;
  private int version = getDefaultVersion();
  private transient MySocketServer socketServer = null;
  private transient Socket socket = null;
  private transient OrcaClient orcaClient = null;
  private transient GCDocument myContentDocument = new GCDocument(this);
  private transient Program labcommAST = null;
  
  public LabCommObject() {}
  
  public LabCommObject(Point paramPoint)
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
    this.myLogo = new JGoText("LC");
    this.myLogo.setSelectable(false);
    this.myLogo.setEditable(false);
    this.myLogo.setDraggable(false);
    this.myLogo.setAlignment(2);
    this.myLogo.setFontSize(20);
    this.myLogo.setTransparent(true);
    this.myName = new JGoText("LabComm");
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    addObjectAtHead(this.myRectangle);
    addObjectAtTail(this.myLogo);
    addObjectAtTail(this.myName);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    LabCommObject localLabCommObject = (LabCommObject)paramJGoArea;
    localLabCommObject.myRectangle = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRectangle));
    localLabCommObject.myLogo = ((JGoText)paramJGoCopyEnvironment.copy(this.myLogo));
    localLabCommObject.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localLabCommObject.addObjectAtHead(localLabCommObject.myRectangle);
    localLabCommObject.addObjectAtTail(localLabCommObject.myLogo);
    localLabCommObject.addObjectAtTail(localLabCommObject.myName);
    localLabCommObject.socketHost = this.socketHost;
    localLabCommObject.isSocketServer = this.isSocketServer;
    localLabCommObject.socketPort = this.socketPort;
    localLabCommObject.specification = this.specification;
    localLabCommObject.input = this.input;
    localLabCommObject.output = this.output;
    localLabCommObject.orca = this.orca;
    localLabCommObject.version = this.version;
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("socketHost", this.socketHost);
    XMLUtil.setBool(paramElement, "isSocketServer", this.isSocketServer);
    XMLUtil.setInt(paramElement, "socketPort", this.socketPort);
    XMLUtil.setInt(paramElement, "version", this.version);
    XMLUtil.setBool(paramElement, "input", this.input);
    XMLUtil.setBool(paramElement, "output", this.output);
    XMLUtil.setBool(paramElement, "orca", this.orca);
    paramElement.setAttribute("specification", this.specification);
    removeObject(this.myName);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    return paramElement;
  }
  
  public static LabCommObject loadXML(Element paramElement)
  {
    LabCommObject localLabCommObject = new LabCommObject(new Point());
    localLabCommObject.setName(paramElement.getAttribute("name"));
    localLabCommObject.setSocketHost(paramElement.getAttribute("socketHost"));
    localLabCommObject.setSocketServer(XMLUtil.getBool(paramElement, "isSocketServer"));
    localLabCommObject.setSocketPort(XMLUtil.getInt(paramElement, "socketPort"));
    localLabCommObject.setVersion(XMLUtil.getInt(paramElement, "version", localLabCommObject.getDefaultVersion()));
    localLabCommObject.setInput(XMLUtil.getBool(paramElement, "input", true));
    localLabCommObject.setOutput(XMLUtil.getBool(paramElement, "output", true));
    localLabCommObject.setOrca(XMLUtil.getBool(paramElement, "orca", false));
    localLabCommObject.setSpecification(paramElement.getAttribute("specification"));
    localLabCommObject.removeObject(localLabCommObject.myName);
    XMLUtil.restoreBoundingRect(paramElement, localLabCommObject);
    localLabCommObject.addObjectAtTail(localLabCommObject.myName);
    return localLabCommObject;
  }
  
  public void layoutChildren()
  {
    Point localPoint = this.myRectangle.getSpotLocation(0);
    this.myLogo.setSpotLocation(0, localPoint.x, localPoint.y);
    localPoint = this.myRectangle.getSpotLocation(6);
    this.myName.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 10);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myRectangle, this.myLogo };
  }
  
  public int getNoScaleBottom(Rectangle paramRectangle)
  {
    return paramRectangle.y + paramRectangle.height - (this.myRectangle.getBoundingRect().y + this.myRectangle.getBoundingRect().height);
  }
  
  public Dimension getMinimumSize()
  {
    return new Dimension(Math.max((int)Math.ceil(this.myName.getBoundingRect().getWidth()), 40), (int)Math.ceil(this.myName.getBoundingRect().getHeight() + 20.0D));
  }
  
  public String getHelpID()
  {
    return "LangRef_IO_LabCommObject";
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
  
  public String getSocketHost()
  {
    return this.socketHost;
  }
  
  public void setSocketHost(String paramString)
  {
    this.socketHost = paramString;
  }
  
  public boolean isSocketServer()
  {
    return this.isSocketServer;
  }
  
  public void setSocketServer(boolean paramBoolean)
  {
    this.isSocketServer = paramBoolean;
  }
  
  public int getSocketPort()
  {
    return this.socketPort;
  }
  
  public void setSocketPort(int paramInt)
  {
    this.socketPort = paramInt;
  }
  
  public boolean isValidVersion(int paramInt)
  {
    return (paramInt == 2006) || (paramInt == 2013);
  }
  
  public int getVersion()
  {
    return this.version;
  }
  
  public void setVersion(int paramInt)
  {
    this.version = paramInt;
  }
  
  public int getDefaultVersion()
  {
    return 2013;
  }
  
  public boolean isInput()
  {
    return this.input;
  }
  
  public void setInput(boolean paramBoolean)
  {
    this.input = paramBoolean;
  }
  
  public boolean isOutput()
  {
    return this.output;
  }
  
  public void setOutput(boolean paramBoolean)
  {
    this.output = paramBoolean;
  }
  
  public boolean isOrca()
  {
    return this.orca;
  }
  
  public void setOrca(boolean paramBoolean)
  {
    this.orca = paramBoolean;
  }
  
  public String getSpecification()
  {
    return this.specification;
  }
  
  public void setSpecification(String paramString)
  {
    this.specification = paramString;
  }
  
  public boolean isConfigured()
  {
    return ((this.isSocketServer) || (!this.socketHost.isEmpty())) && (this.socketPort != -1) && ((this.input) || (this.output));
  }
  
  public void compile()
  {
    this.labcommAST = null;
    this.myContentDocument = new GCDocument(this);
    if (!isConfigured()) {
      Editor.giveLightWarning("LabComm Object not configured \"" + getFullName() + "\".");
    }
    if (isValidVersion(getVersion())) {
      try
      {
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(this.specification.getBytes());
        LabCommScanner localLabCommScanner = new LabCommScanner(new InputStreamReader(localByteArrayInputStream));
        LabCommParser localLabCommParser = new LabCommParser();
        Program localProgram = (Program)localLabCommParser.parse(localLabCommScanner);
        if (localProgram.getNumDecl() != 0)
        {
          localProgram.fcNode = this;
          localProgram.version = this.version;
          localProgram.input = this.input;
          localProgram.output = this.output;
          localProgram.orca = this.orca;
          LinkedList localLinkedList = new LinkedList();
          localProgram.errorCheck(localLinkedList);
          if (localLinkedList.isEmpty())
          {
            this.labcommAST = localProgram;
            localProgram.generateGCDocument(this.myContentDocument);
          }
          else
          {
            Editor.giveWarning("Error in specification for LabComm Object \"" + getFullName() + "\":");
            Iterator localIterator = localLinkedList.iterator();
            while (localIterator.hasNext())
            {
              String str = (String)localIterator.next();
              Editor.giveInformation(str);
            }
          }
        }
        else
        {
          Editor.giveLightWarning("LabComm Object has empty specification \"" + getFullName() + "\".");
        }
      }
      catch (Throwable localThrowable)
      {
        Editor.giveWarning("Error in specification for LabComm Object \"" + getFullName() + "\": " + localThrowable.getMessage());
        this.myContentDocument = new GCDocument(this);
      }
    } else {
      Editor.giveWarning("Invalid LabComm version (" + getVersion() + ") for \"" + getFullName() + "\".");
    }
  }
  
  public void initialize()
  {
    if (isConfigured()) {
      if ((this.isSocketServer) && (this.socketServer == null))
      {
        try
        {
          this.socketServer = new MySocketServer(this.socketPort);
        }
        catch (IOException localIOException1)
        {
          Utils.writeError("Socket server setup failed: " + localIOException1.getMessage());
        }
      }
      else if (!this.isSocketServer)
      {
        if (this.socket != null) {
          try
          {
            this.socket.close();
          }
          catch (IOException localIOException2)
          {
            Utils.writeException(localIOException2, "Close socket failed.");
          }
          finally
          {
            this.socket = null;
          }
        }
        this.orcaClient = null;
        connectToServer();
      }
    }
  }
  
  public void tick()
  {
    if (this.labcommAST != null) {
      this.labcommAST.tick();
    }
  }
  
  public boolean hasReceived()
  {
    if (this.labcommAST != null) {
      return this.labcommAST.hasReceived();
    }
    return false;
  }
  
  private void connectToServer()
  {
    if ((!this.orca) || (this.version != 2006))
    {
      this.socket = null;
      for (int i = 1; (i < 10) && (this.socket == null); i++) {
        try
        {
          Socket localSocket = new Socket(this.socketHost, this.socketPort);
          try
          {
            if (this.input) {
              localSocket.getInputStream();
            }
            if (this.output) {
              localSocket.getOutputStream();
            }
            this.socket = localSocket;
          }
          catch (Exception localException2)
          {
            Utils.writeError("Could not open input/output stream for \"" + getFullName() + "\".");
            break;
          }
        }
        catch (Exception localException1)
        {
          Utils.writeInformation("Attempt " + i + ": \"" + getFullName() + "\" could not establish connection with \"" + this.socketHost + "\" on port " + this.socketPort + ".");
          try
          {
            Thread.sleep(1000L);
          }
          catch (Exception localException3) {}
        }
      }
      if (this.socket != null) {
        registerSocket(this.socket);
      } else {
        Utils.writeError("Giving up.");
      }
    }
    else
    {
      try
      {
        this.orcaClient = new OrcaClient(this.socketHost, this.socketPort, this.version);
        this.labcommAST.register(this.orcaClient);
      }
      catch (IOException localIOException)
      {
        Utils.writeError("Could not establish Orca connection with \"" + this.socketHost + "\" on port " + this.socketPort + ".");
      }
    }
  }
  
  private void registerSocket(Socket paramSocket)
  {
    try
    {
      this.labcommAST.register(paramSocket);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void send(String paramString)
  {
    if (this.labcommAST != null) {
      this.labcommAST.send(paramString);
    }
  }
  
  public ArrayList<Referencable> getSymbolTable()
  {
    if (!Editor.warningAboutNameConflicts) {
      return Utils.collectionToSymbolTable(this.myContentDocument);
    }
    return new ArrayList();
  }
  
  private class MySocketServer
    implements ClientListener
  {
    private ArrayList<Client> clients = new ArrayList();
    private SocketServer server;
    
    public MySocketServer(int paramInt)
      throws IOException
    {
      this.server = new SocketServer(paramInt, this, null);
      this.server.start();
    }
    
    public synchronized void clientConnected(Client paramClient)
    {
      this.clients.add(paramClient);
      LabCommObject.this.registerSocket(paramClient.getSocket());
    }
    
    public synchronized void clientDisconnected(Client paramClient)
    {
      this.clients.remove(paramClient);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/LabCommObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */