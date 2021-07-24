package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoDocument;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoObjectSimpleCollection;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoUndoManager;
import grafchart.sfc.variables.FunctionBlock;
import grafchart.sfc.variables.StandardVariable;
import grafchart.sfc.variables.TransitionSession;
import grafchart.socketio.Client;
import grafchart.socketio.ClientListener;
import grafchart.socketio.JGSocketReceiver;
import grafchart.socketio.SocketServer;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.soda.dpws.DPWSNetworks;
import org.soda.dpws.LocalIPAddress;
import org.soda.dpws.registry.defaultimpl.Registry;
import org.soda.dpws.server.EventHandlerListener;
import org.soda.jetty.JettyServletContainer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Document contenant les éléments JGo
 *
 * @author dimon
 */
public class GCDocument
        extends JGoDocument
        implements Referencable, Workspace, GCCollection, SymbolTableObject {

    public static String documentCollectionTag = new String("GCDocumentCollection");
    public static String documentTag = new String("GCDocument");
    public static String initialGCStepTag = new String("GCInitialStep");
    public static String gcStepTag = new String("GCStep");
    public static String enterStepTag = new String("EnterStep");
    public static String exitStepTag = new String("ExitStep");
    public static String grafcetProcTag = new String("GrafcetProcedure");
    public static String procedureStepTag = new String("ProcedureStep");
    public static String processStepTag = new String("ProcessStep");
    public static String parallelSplitTag = new String("ParallelSplit");
    public static String parallelJoinTag = new String("ParallelJoin");
    public static String digitalIn1Tag = new String("DigitalIn1");
    public static String digitalInTag = new String("DigitalIn");
    public static String digitalOut0Tag = new String("DigitalOut0");
    public static String digitalOut1Tag = new String("DigitalOut1");
    public static String digitalOutTag = new String("DigitalOut");
    public static String booleanVarTag = new String("BooleanVariable");
    public static String integerVarTag = new String("IntegerVariable");
    public static String indexVarTag = new String("IndexVariable");
    public static String integerAttributeVarTag = new String("IntegerAttributeVariable");
    public static String realAttributeVarTag = new String("RealAttributeVariable");
    public static String stringAttributeVarTag = new String("StringAttributeVariable");
    public static String stringVarTag = new String("StringVariable");
    public static String realVarTag = new String("RealVariable");
    public static String booleanListTag = new String("BooleanList");
    public static String integerListTag = new String("IntegerList");
    public static String realListTag = new String("RealList");
    public static String stringListTag = new String("StringList");
    public static String sBooleanInTag = new String("SocketBooleanIn");
    public static String sBooleanOutTag = new String("SocketBooleanOut");
    public static String sIntInTag = new String("SocketIntIn");
    public static String sIntOutTag = new String("SocketIntOut");
    public static String sRealInTag = new String("SocketRealIn");
    public static String sRealOutTag = new String("SocketRealOut");
    public static String sStringInTag = new String("SocketStringIn");
    public static String sStringOutTag = new String("SocketStringOut");
    public static String gcGroupTag = new String("GCGroup");
    public static String gcLinkTag = new String("GCLink");
    public static String xIntInTag = new String("XMLIntIn");
    public static String xIntOutTag = new String("XMLIntOut");
    public static String xBooleanInTag = new String("XMLBooleanIn");
    public static String xBooleanOutTag = new String("XMLBooleanOut");
    public static String xRealInTag = new String("XMLRealIn");
    public static String xRealOutTag = new String("XMLRealOut");
    public static String xStringInTag = new String("XMLStringIn");
    public static String xStringOutTag = new String("XMLStringOut");
    public static String xAssignmentButtonTag = new String("AssignmentButton");
    public static String xGraphicalButtonTag = new String("GraphicalButton");
    public static String xBrowserTag = new String("Browser");
    public static String connectionPostInTag = new String("ConnectionPostIn");
    public static String connectionPostOutTag = new String("ConnectionPostOut");
    public static String plotterTag = new String("SFCPlotter");
    public static String byteStreamInTag = new String("ByteStreamIn");
    public static String byteStreamOutTag = new String("ByteStreamOut");
    public static String dpwsObjectTag = new String("DPWSObject");
    public static String labCommObjectTag = new String("LabCommObject");
    public static String varRacineTag = "Variables";
    public static String stdVariableTag = "StandardVariable";
    public static String funcBlockTag = "FunctionBlock";
    public static String tranSessionTag = "TransitionSession";
    private String gcClassTag = new String("GCClass");
    private String freeTextTag = new String("FreeText");
    private String gcRectangleTag = new String("GCRectangle");
    private String gcEllipseTag = new String("GCEllipse");
    private String gc3DRectTag = new String("GC3DRect");
    private String gcPolygonTag = new String("GCPolygon");
    private String gcStrokeTag = new String("GCStroke");
    private String analogInTag = new String("AnalogIn");
    private String analogOutTag = new String("AnalogOut");
    private String chemIconTag = new String("ChemIcon");
    private String iconTag = new String("Icon");
    private String lucasLogoTag = new String("LUCASLogo");
    private String showWorkspaceButtonTag = new String("ShowWorkspaceButton");
    private String gcTransitionTag = new String("GCTransition");
    private String exTransitionTag = new String("ExceptionTransition");
    private String macroStepTag = new String("MacroStep");    
    public static String workspaceObjectTag = new String("WorkspaceObject");
    public static String xmlMessageInTag = new String("XMLMessageIn");
    public static String xmlMessageOutTag = new String("XMLMessageOut");
    private String stepFusionSetTag = new String("StepFusionSet");
    private String myName = "";
    public boolean simulation = true;
    public int threadSpeed = 40;
    public static final int NAME_CHANGED = 65536;
    public boolean dimming = false;
    public int dimTicks = 25;
    private int dpwsPort = -1;
    private String dpwsInterface = "";
    private EventHandlerListener dpwsEventListener = null;
    public SocketOut.SendMode socketSendMode = SocketOut.SendMode.Inherit;
    public boolean partitioningDone = false;
    public transient ArrayList<Partition> partitions;
    public Rectangle bounds = null;
    public double currentScale = 1.0D;
    public Point point = new Point(0, 0);
    public int rgbColor;
    public GrafcetObject owner = null;
    private String myReadLocation = "";
    private String myWriteLocation = "";
    private transient GrafcetThread grafcetThread;
    public boolean executing = false;
    public boolean paused = false;
    public boolean compiledOK = false;
    public boolean terminateWhenReady = false;
    public GrafcetProcedure proc = null;
    public ProcessStepAble processStepCall = null;
    public boolean markForRemoval = false;
    public transient JInternalFrame frame = null;
    public transient GCView myView = null;
    public transient ArrayList<Referencable> inputs = new ArrayList();
    private transient ArrayList<Readable> nonInputs = new ArrayList();
    public double realTime = 0.0D;
    public transient ArrayList<GenericTransition> transitions = new ArrayList();
    public transient ArrayList<MacroStep> macros = new ArrayList();
    public transient ArrayList<ProcedureStep> procedures = new ArrayList();
    public transient ArrayList<ProcessStep> processes = new ArrayList();
    public transient ArrayList<XMLMessageIn> XMLMessageIns = new ArrayList();
    public transient ArrayList<WorkspaceObject> workspaces = new ArrayList();
    public transient ArrayList<SFCPlotter> plotters = new ArrayList();
    public transient ArrayList<GCStep> steps = new ArrayList();
    public transient ArrayList<DigitalOut> digitalouts = new ArrayList();
    public transient ArrayList<BooleanVariable> booleans = new ArrayList();
    public transient ArrayList<ByteStreamIn> bytestreams = new ArrayList();
    public transient ArrayList<StandardVariable> stdVariables = new ArrayList();
    public transient ArrayList<FunctionBlock> functionBlocs = new ArrayList();  
    public transient ArrayList<TransitionSession> transitionSession = new ArrayList();      
    //public transient ArrayList<Action> actions = new ArrayList();

    private boolean isSocketServer = false;
    private transient MySocketServer socketServer;
    public String host = "";
    public int port = -1;
    public transient Socket socket;
    public transient PrintWriter out;
    public transient BufferedReader in;
    private transient SocketReader socketThread;
    public boolean isConnected = false;
    public boolean modificationProperty = true;
    public boolean horizontalScrollBar = true;
    public boolean verticalScrollBar = true;
    public static ImageIcon lockIcon = Utils.newImageIcon("icon-lock2.gif");
    public static ImageIcon runIcon = Utils.newImageIcon("runner5.jpeg");
    public static javax.swing.Icon defaultIcon;
    
    public int getDPWSPort() {
        return this.dpwsPort;
    }

    public void setDPWSPort(int paramInt) {
        this.dpwsPort = paramInt;
    }

    public String getDPWSInterface() {
        return this.dpwsInterface;
    }

    public void setDPWSInterface(String paramString) {
        this.dpwsInterface = paramString;
    }

    private EventHandlerListener getDPWSEventListener() {
        return this.dpwsEventListener;
    }

    private void stopDPWSEventListener() {
        if (this.dpwsEventListener != null) {
            this.dpwsEventListener.stop();
            this.dpwsEventListener = null;
        }
    }

    public GrafcetObject getOwner() {
        return this.owner;
    }

    public synchronized boolean isSocketServer() {
        return this.isSocketServer;
    }

    public synchronized void setSocketServer(boolean paramBoolean) {
        this.isSocketServer = paramBoolean;
    }

    public GCDocument() {
        this(null);
    }

    public GCDocument(GrafcetObject paramGrafcetObject) {
        this.owner = paramGrafcetObject;
        if (paramGrafcetObject == null) {
            this.socketSendMode = SocketOut.SendMode.Changed;
        }
        JGoUndoManager localJGoUndoManager = new JGoUndoManager();
        localJGoUndoManager.setLimit(100);
        setUndoManager(localJGoUndoManager);
        setPaperColor(Color.white);
    }

    public void setName(String paramString) {
        String str = getName();
        if (!str.equals(paramString)) {
            this.myName = paramString;
        }
    }

    public void setView(GCView paramGCView) {
        this.myView = paramGCView;
    }

    public GCView getView() {
        return this.myView;
    }

    public String getName() {
        return this.myName;
    }

    public String getFullName() {
        if (this.owner != null) {
            return ((Referencable) this.owner).getFullName();
        }
        return getName();
    }

    public boolean isSimulating() {
        return this.simulation;
    }

    public int getSpeed() {
        return this.threadSpeed;
    }

    public void setSpeed(int paramInt) {
        this.threadSpeed = paramInt;
    }

    public ArrayList<Referencable> getSymbolTable() {
        return Utils.collectionToSymbolTable(this);
    }

    public void propagateDimmingInfo(boolean paramBoolean, int paramInt1, int paramInt2) {
        this.dimming = paramBoolean;
        this.dimTicks = paramInt1;
        this.threadSpeed = paramInt2;
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof MacroStep)) {
                MacroStep localMacroStep = (MacroStep) localJGoObject;
                if (localMacroStep.myContentDocument != null) {
                    localMacroStep.myContentDocument.propagateDimmingInfo(paramBoolean, paramInt1, paramInt2);
                }
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
    }

    public void setReadFileLocation(String paramString) {
        String str = getReadFileLocation();
        if (!str.equals(paramString)) {
            this.myReadLocation = paramString;
        }
    }

    public String getReadFileLocation() {
        return this.myReadLocation;
    }

    public void setWriteFileLocation(String paramString) {
        String str = getWriteFileLocation();
        if (!str.equals(paramString)) {
            this.myWriteLocation = paramString;
        }
    }

    /**
     * Retourne le chemin du fichier dans leque le document est enrégistré
     *
     * @return
     */
    public String getWriteFileLocation() {
        return this.myWriteLocation;
    }

    /**
     * Ecrit le document dans un doc XML
     *
     * @param xmlDoc objet XML dans lequel les propriétés seront enrégistrées
     */
    public void storeXML(Document xmlDoc) {
        Element xmlElement1 = xmlDoc.createElement(documentTag);
        xmlElement1.setAttribute("name", this.myName);
        XMLUtil.setInt(xmlElement1, "threadSpeed", this.threadSpeed);
        XMLUtil.setBool(xmlElement1, "simulationMode", this.simulation);
        XMLUtil.setBool(xmlElement1, "tokenLuminance", this.dimming);
        XMLUtil.setInt(xmlElement1, "dimTicks", this.dimTicks);
        XMLUtil.setDouble(xmlElement1, "scale", this.currentScale);
        XMLUtil.saveRect(xmlElement1, this.bounds);
        XMLUtil.setViewPosition(xmlElement1, this.point);
        XMLUtil.setInt(xmlElement1, "color", this.rgbColor);
        xmlElement1.setAttribute("socketHost", this.host);
        XMLUtil.setBool(xmlElement1, "socketIsServer", this.isSocketServer);
        XMLUtil.setInt(xmlElement1, "socketPort", this.port);
        xmlElement1.setAttribute("dpwsInterface", this.dpwsInterface);
        XMLUtil.setInt(xmlElement1, "dpwsPort", this.dpwsPort);
        XMLUtil.setBool(xmlElement1, "modifiable", this.modificationProperty);
        XMLUtil.setBool(xmlElement1, "horizontalScrollBar", this.horizontalScrollBar);
        XMLUtil.setBool(xmlElement1, "verticalScrollBar", this.verticalScrollBar);

        Element localElement2 = null;
        if (xmlDoc.hasChildNodes()) {
            localElement2 = xmlDoc.getDocumentElement();
        }
        if (localElement2 != null) {
            localElement2.appendChild(storeXMLRec(xmlElement1));
        } else {
            xmlDoc.appendChild(storeXMLRec(xmlElement1));
        }
    }

    public void storeXMLRec(Element xmlElement, Rectangle paramRectangle, Point paramPoint) {
        Element localElement = xmlElement.getOwnerDocument().createElement(documentTag);
        if (paramRectangle == null) {
            paramRectangle = new Rectangle(400, 600, 400, 400);
        }
        XMLUtil.saveRect(localElement, paramRectangle);
        XMLUtil.setViewPosition(localElement, paramPoint);
        XMLUtil.setDouble(localElement, "scale", this.currentScale);
        XMLUtil.setInt(localElement, "color", this.rgbColor);
        storeXMLRec(localElement);
        xmlElement.appendChild(localElement);
    }

    public Element storeXMLRec(Element xmlElement) {
        xmlElement.setAttribute("color", Integer.toString(getPaperColor().getRGB()));
        xmlElement.setAttribute("socketSendMode", this.socketSendMode.name());
        Vector objetIDVector = new Vector(); //Utilise par GCLink pour récuperer les id
        Vector objectVector = new Vector();  //Utilise par GCLink pour stocker les Objet eux meme
        
        storeVariables(xmlElement, objetIDVector, objectVector); //Sauvegarde les variables
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); localJGoListPosition != null; localJGoObject = getObjectAtPos(localJGoListPosition)) {
            storeObjectAtTag(localJGoObject, xmlElement, objetIDVector, objectVector);
            localJGoListPosition = getNextObjectPosAtTop(localJGoListPosition);
        }
        localJGoListPosition = getFirstObjectPos();
        JGoObject localJGoObject = null;
        for (localJGoObject = getObjectAtPos(localJGoListPosition); localJGoListPosition != null; localJGoObject = getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof GCGroup)) {
                storeGCGroupAtTag((GCGroup) localJGoObject, xmlElement, objetIDVector, objectVector);
            }
            localJGoListPosition = getNextObjectPosAtTop(localJGoListPosition);
        }
        localJGoListPosition = getFirstObjectPos();
        for (localJGoObject = getObjectAtPos(localJGoListPosition); localJGoListPosition != null; localJGoObject = getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof GCLink)) {
                storeLinkAtTag((GCLink) localJGoObject, xmlElement, objetIDVector, objectVector);
            }
            localJGoListPosition = getNextObjectPosAtTop(localJGoListPosition);
        }
        return xmlElement;
    }

    void storeGCGroupAtTag(GCGroup paramGCGroup, Element xmlElement, Vector paramVector1, Vector paramVector2) {
        Document localDocument = xmlElement.getOwnerDocument();
        if (paramGCGroup != null) {
            Element localElement = localDocument.createElement(gcGroupTag);
            xmlElement.appendChild(paramGCGroup.storeXML(localElement, paramVector1, paramVector2));
        }
    }

    void storeLinkAtTag(GCLink paramGCLink, Element xmlElement, Vector paramVector1, Vector paramVector2) {
        Document localDocument = xmlElement.getOwnerDocument();
        if (paramGCLink != null) {
            Element localElement = localDocument.createElement(gcLinkTag);
            xmlElement.appendChild(paramGCLink.storeXML(localElement, paramVector1, paramVector2));
        }
    }

    private void storeVariables(Element xmlElement, Vector paramVector1, Vector paramVector2) {
        Document localDocument = xmlElement.getOwnerDocument();       
        Element variablesElement = localDocument.createElement(varRacineTag);
        Document variablesDocument = variablesElement.getOwnerDocument();
        //Standard variables
        Iterator<StandardVariable> it = stdVariables.iterator();
        while (it.hasNext()) {
            StandardVariable nextStdVar = it.next();
            Element stdElement = variablesDocument.createElement(stdVariableTag);
            variablesElement.appendChild(nextStdVar.storeXML(stdElement, paramVector1, paramVector2));            
        }
        //Function block
        Iterator<FunctionBlock> it2 = functionBlocs.iterator();
        while (it2.hasNext()) {
            FunctionBlock nextStdVar = it2.next();
            Element stdElement = variablesDocument.createElement(funcBlockTag);
            variablesElement.appendChild(nextStdVar.storeXML(stdElement, paramVector1, paramVector2));            
        }
        //Transition session
        Iterator<TransitionSession> it3 = transitionSession.iterator();
        while (it3.hasNext()) {
            TransitionSession nextStdVar = it3.next();
            Element stdElement = variablesDocument.createElement(tranSessionTag);
            variablesElement.appendChild(nextStdVar.storeXML(stdElement, paramVector1, paramVector2));            
        }        
        
        xmlElement.appendChild(variablesElement);
        
    }

    void storeObjectAtTag(JGoObject paramJGoObject, Element xmlElement, Vector paramVector1, Vector paramVector2) {
        Document localDocument = xmlElement.getOwnerDocument();
        if (paramJGoObject != null) {
            Object localObject;
            Element localElement;
            if ((paramJGoObject instanceof GCStepInitial)) {
                localObject = (GCStepInitial) paramJGoObject;
                localElement = localDocument.createElement(initialGCStepTag);
                xmlElement.appendChild(((GCStepInitial) localObject).storeXML(localElement, paramVector1, paramVector2));
            } else if ((paramJGoObject instanceof EnterStep)) {
                localObject = (EnterStep) paramJGoObject;
                localElement = localDocument.createElement(enterStepTag);
                xmlElement.appendChild(((EnterStep) localObject).storeXML(localElement, paramVector1, paramVector2));
            } else if ((paramJGoObject instanceof ExitStep)) {
                localObject = (ExitStep) paramJGoObject;
                localElement = localDocument.createElement(exitStepTag);
                xmlElement.appendChild(((ExitStep) localObject).storeXML(localElement, paramVector1, paramVector2));
            } else if ((paramJGoObject instanceof GCStep)) {
                localObject = (GCStep) paramJGoObject;
                localElement = localDocument.createElement(gcStepTag);
                xmlElement.appendChild(((GCStep) localObject).storeXML(localElement, paramVector1, paramVector2));
            } else if ((paramJGoObject instanceof ProcessStep)) {
                localObject = (ProcessStep) paramJGoObject;
                localElement = localDocument.createElement(processStepTag);
                xmlElement.appendChild(((ProcessStep) localObject).storeXML(localElement, paramVector1, paramVector2));
            } else if ((paramJGoObject instanceof ProcedureStep)) {
                localObject = (ProcedureStep) paramJGoObject;
                localElement = localDocument.createElement(procedureStepTag);
                xmlElement.appendChild(((ProcedureStep) localObject).storeXML(localElement, paramVector1, paramVector2));
            } else if ((paramJGoObject instanceof MacroStep)) {
                localObject = (MacroStep) paramJGoObject;
                localElement = localDocument.createElement(this.macroStepTag);
                xmlElement.appendChild(((MacroStep) localObject).storeXML(localElement, paramVector1, paramVector2));
            }
            if ((paramJGoObject instanceof GCTransition)) {
                localObject = (GCTransition) paramJGoObject;
                localElement = localDocument.createElement(this.gcTransitionTag);
                xmlElement.appendChild(((GCTransition) localObject).storeXML(localElement, paramVector1, paramVector2));
            }
            if ((paramJGoObject instanceof ExceptionTransition)) {
                localObject = (ExceptionTransition) paramJGoObject;
                localElement = localDocument.createElement(this.exTransitionTag);
                xmlElement.appendChild(((ExceptionTransition) localObject).storeXML(localElement, paramVector1, paramVector2));
            }
            if ((paramJGoObject instanceof ParallelSplit)) {
                localObject = (ParallelSplit) paramJGoObject;
                localElement = localDocument.createElement(parallelSplitTag);
                xmlElement.appendChild(((ParallelSplit) localObject).storeXML(localElement, paramVector1, paramVector2));
            }
            if ((paramJGoObject instanceof ParallelJoin)) {
                localObject = (ParallelJoin) paramJGoObject;
                localElement = localDocument.createElement(parallelJoinTag);
                xmlElement.appendChild(((ParallelJoin) localObject).storeXML(localElement, paramVector1, paramVector2));
            } else if ((paramJGoObject instanceof GrafcetProcedure)) {
                localObject = (GrafcetProcedure) paramJGoObject;
                localElement = localDocument.createElement(grafcetProcTag);
                xmlElement.appendChild(((GrafcetProcedure) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof StepFusionSet)) {
                localObject = (StepFusionSet) paramJGoObject;
                localElement = localDocument.createElement(this.stepFusionSetTag);
                xmlElement.appendChild(((StepFusionSet) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof WorkspaceObject)) {
                localObject = (WorkspaceObject) paramJGoObject;
                localElement = localDocument.createElement(workspaceObjectTag);
                xmlElement.appendChild(((WorkspaceObject) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof XMLMessageIn)) {
                localObject = (XMLMessageIn) paramJGoObject;
                localElement = localDocument.createElement(xmlMessageInTag);
                xmlElement.appendChild(((XMLMessageIn) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof XMLMessageOut)) {
                localObject = (XMLMessageOut) paramJGoObject;
                localElement = localDocument.createElement(xmlMessageOutTag);
                xmlElement.appendChild(((XMLMessageOut) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof GCClass)) {
                localObject = (GCClass) paramJGoObject;
                localElement = localDocument.createElement(this.gcClassTag);
                xmlElement.appendChild(((GCClass) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof DigitalIn1)) {
                localObject = (DigitalIn1) paramJGoObject;
                localElement = localDocument.createElement(digitalIn1Tag);
                xmlElement.appendChild(((DigitalIn1) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof DigitalIn)) {
                localObject = (DigitalIn) paramJGoObject;
                localElement = localDocument.createElement(digitalInTag);
                xmlElement.appendChild(((DigitalIn) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof DigitalOut1)) {
                localObject = (DigitalOut1) paramJGoObject;
                localElement = localDocument.createElement(digitalOut1Tag);
                xmlElement.appendChild(((DigitalOut1) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof DigitalOut)) {
                localObject = (DigitalOut) paramJGoObject;
                localElement = localDocument.createElement(digitalOutTag);
                xmlElement.appendChild(((DigitalOut) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof AnalogIn)) {
                localObject = (AnalogIn) paramJGoObject;
                localElement = localDocument.createElement(this.analogInTag);
                xmlElement.appendChild(((AnalogIn) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof AnalogOut)) {
                localObject = (AnalogOut) paramJGoObject;
                localElement = localDocument.createElement(this.analogOutTag);
                xmlElement.appendChild(((AnalogOut) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof BooleanVariable)) {
                localObject = (BooleanVariable) paramJGoObject;
                localElement = localDocument.createElement(booleanVarTag);
                xmlElement.appendChild(((BooleanVariable) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof BooleanList)) {
                localObject = (BooleanList) paramJGoObject;
                localElement = localDocument.createElement(booleanListTag);
                xmlElement.appendChild(((BooleanList) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof IntegerList)) {
                localObject = (IntegerList) paramJGoObject;
                localElement = localDocument.createElement(integerListTag);
                xmlElement.appendChild(((IntegerList) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof RealList)) {
                localObject = (RealList) paramJGoObject;
                localElement = localDocument.createElement(realListTag);
                xmlElement.appendChild(((RealList) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof StringList)) {
                localObject = (StringList) paramJGoObject;
                localElement = localDocument.createElement(stringListTag);
                xmlElement.appendChild(((StringList) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof IndexVariable)) {
                localObject = (IndexVariable) paramJGoObject;
                localElement = localDocument.createElement(indexVarTag);
                xmlElement.appendChild(((IndexVariable) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof IntegerAttributeVariable)) {
                localObject = (IntegerAttributeVariable) paramJGoObject;
                localElement = localDocument.createElement(integerAttributeVarTag);
                xmlElement.appendChild(((IntegerAttributeVariable) localObject).storeXML(localElement));
            } else if (((paramJGoObject instanceof IntegerVariable)) && (!(paramJGoObject instanceof IndexVariable)) && (!(paramJGoObject instanceof IntegerAttributeVariable))) {
                localObject = (IntegerVariable) paramJGoObject;
                localElement = localDocument.createElement(integerVarTag);
                xmlElement.appendChild(((IntegerVariable) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof StringAttributeVariable)) {
                localObject = (StringAttributeVariable) paramJGoObject;
                localElement = localDocument.createElement(stringAttributeVarTag);
                xmlElement.appendChild(((StringAttributeVariable) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof RealAttributeVariable)) {
                localObject = (RealAttributeVariable) paramJGoObject;
                localElement = localDocument.createElement(realAttributeVarTag);
                xmlElement.appendChild(((RealAttributeVariable) localObject).storeXML(localElement));
            } else if (((paramJGoObject instanceof StringVariable)) && (!(paramJGoObject instanceof StringAttributeVariable))) {
                localObject = (StringVariable) paramJGoObject;
                localElement = localDocument.createElement(stringVarTag);
                xmlElement.appendChild(((StringVariable) localObject).storeXML(localElement));
            } else if (((paramJGoObject instanceof RealVariable)) && (!(paramJGoObject instanceof RealAttributeVariable))) {
                localObject = (RealVariable) paramJGoObject;
                localElement = localDocument.createElement(realVarTag);
                xmlElement.appendChild(((RealVariable) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof SocketBooleanIn)) {
                localObject = (SocketBooleanIn) paramJGoObject;
                localElement = localDocument.createElement(sBooleanInTag);
                xmlElement.appendChild(((SocketBooleanIn) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof SocketBoolOut)) {
                localObject = (SocketBoolOut) paramJGoObject;
                localElement = localDocument.createElement(sBooleanOutTag);
                xmlElement.appendChild(((SocketBoolOut) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof SocketIntIn)) {
                localObject = (SocketIntIn) paramJGoObject;
                localElement = localDocument.createElement(sIntInTag);
                xmlElement.appendChild(((SocketIntIn) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof SocketIntOut)) {
                localObject = (SocketIntOut) paramJGoObject;
                localElement = localDocument.createElement(sIntOutTag);
                xmlElement.appendChild(((SocketIntOut) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof SocketRealIn)) {
                localObject = (SocketRealIn) paramJGoObject;
                localElement = localDocument.createElement(sRealInTag);
                xmlElement.appendChild(((SocketRealIn) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof SocketRealOut)) {
                localObject = (SocketRealOut) paramJGoObject;
                localElement = localDocument.createElement(sRealOutTag);
                xmlElement.appendChild(((SocketRealOut) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof SocketStringIn)) {
                localObject = (SocketStringIn) paramJGoObject;
                localElement = localDocument.createElement(sStringInTag);
                xmlElement.appendChild(((SocketStringIn) localObject).storeXML(localElement));
            } else if ((paramJGoObject instanceof SocketStringOut)) {
                localObject = (SocketStringOut) paramJGoObject;
                localElement = localDocument.createElement(sStringOutTag);
                xmlElement.appendChild(((SocketStringOut) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof FreeText)) {
                localObject = (FreeText) paramJGoObject;
                localElement = localDocument.createElement(this.freeTextTag);
                xmlElement.appendChild(((FreeText) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof GCRectangle)) {
                localObject = (GCRectangle) paramJGoObject;
                localElement = localDocument.createElement(this.gcRectangleTag);
                xmlElement.appendChild(((GCRectangle) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof GCEllipse)) {
                localObject = (GCEllipse) paramJGoObject;
                localElement = localDocument.createElement(this.gcEllipseTag);
                xmlElement.appendChild(((GCEllipse) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof GC3DRect)) {
                localObject = (GC3DRect) paramJGoObject;
                localElement = localDocument.createElement(this.gc3DRectTag);
                xmlElement.appendChild(((GC3DRect) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof GCPolygon)) {
                localObject = (GCPolygon) paramJGoObject;
                localElement = localDocument.createElement(this.gcPolygonTag);
                xmlElement.appendChild(((GCPolygon) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof GCStroke)) {
                localObject = (GCStroke) paramJGoObject;
                localElement = localDocument.createElement(this.gcStrokeTag);
                xmlElement.appendChild(((GCStroke) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof ChemIcon)) {
                localObject = (ChemIcon) paramJGoObject;
                localElement = localDocument.createElement(this.chemIconTag);
                xmlElement.appendChild(((ChemIcon) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof Icon)) {
                localObject = (Icon) paramJGoObject;
                localElement = localDocument.createElement(this.iconTag);
                xmlElement.appendChild(((Icon) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof LUCASLogo)) {
                localObject = (LUCASLogo) paramJGoObject;
                localElement = localDocument.createElement(this.lucasLogoTag);
                xmlElement.appendChild(((LUCASLogo) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof ShowWorkspaceButton)) {
                localObject = (ShowWorkspaceButton) paramJGoObject;
                localElement = localDocument.createElement(this.showWorkspaceButtonTag);
                xmlElement.appendChild(((ShowWorkspaceButton) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof AssignmentButton)) {
                localObject = (AssignmentButton) paramJGoObject;
                localElement = localDocument.createElement(xAssignmentButtonTag);
                xmlElement.appendChild(((AssignmentButton) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof Browser)) {
                localObject = (Browser) paramJGoObject;
                localElement = localDocument.createElement(xBrowserTag);
                xmlElement.appendChild(((Browser) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof GraphicalButton)) {
                localObject = (GraphicalButton) paramJGoObject;
                localElement = localDocument.createElement(xGraphicalButtonTag);
                xmlElement.appendChild(((GraphicalButton) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof ConnectionPostIn)) {
                localObject = (ConnectionPostIn) paramJGoObject;
                localElement = localDocument.createElement(connectionPostInTag);
                xmlElement.appendChild(((ConnectionPostIn) localObject).storeXML(localElement, paramVector1, paramVector2));
            }
            if ((paramJGoObject instanceof ConnectionPostOut)) {
                localObject = (ConnectionPostOut) paramJGoObject;
                localElement = localDocument.createElement(connectionPostOutTag);
                xmlElement.appendChild(((ConnectionPostOut) localObject).storeXML(localElement, paramVector1, paramVector2));
            }
            if ((paramJGoObject instanceof ByteStreamIn)) {
                localObject = (ByteStreamIn) paramJGoObject;
                localElement = localDocument.createElement(byteStreamInTag);
                xmlElement.appendChild(((ByteStreamIn) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof ByteStreamOut)) {
                localObject = (ByteStreamOut) paramJGoObject;
                localElement = localDocument.createElement(byteStreamOutTag);
                xmlElement.appendChild(((ByteStreamOut) localObject).storeXML(localElement));
            }
            if ((paramJGoObject instanceof DPWSObject)) {
                xmlElement.appendChild(((DPWSObject) paramJGoObject).storeXML(localDocument.createElement(dpwsObjectTag)));
            }
            if ((paramJGoObject instanceof LabCommObject)) {
                xmlElement.appendChild(((LabCommObject) paramJGoObject).storeXML(localDocument.createElement(labCommObjectTag)));
            }
            if ((paramJGoObject instanceof SFCPlotter)) {
                localObject = (SFCPlotter) paramJGoObject;
                localElement = localDocument.createElement(plotterTag);
                xmlElement.appendChild(((SFCPlotter) localObject).storeXML(localElement));
            }
        }
    }

    public boolean loadXML(Element xmlElement) {
        boolean bool = false;
        if (xmlElement != null) {
            try {
                if (xmlElement.getTagName().equals(documentTag)) {
                    this.myName = xmlElement.getAttribute("name");
                    this.threadSpeed = XMLUtil.getInt(xmlElement, "threadSpeed", 40);
                    this.simulation = XMLUtil.getBool(xmlElement, "simulationMode", true);
                    this.dimming = XMLUtil.getBool(xmlElement, "tokenLuminance");
                    this.dimTicks = XMLUtil.getInt(xmlElement, "dimTicks", 25);
                    this.bounds = XMLUtil.getWorkspaceBoundingRect(xmlElement);
                    this.currentScale = XMLUtil.getDouble(xmlElement, "scale", 1.0D);
                    this.point = XMLUtil.getViewPosition(xmlElement);
                    this.rgbColor = XMLUtil.getInt(xmlElement, "color", 16777215);
                    this.host = xmlElement.getAttribute("socketHost");
                    if (Utils.getSaveVersion(xmlElement) >= 7) {
                        this.isSocketServer = XMLUtil.getBool(xmlElement, "socketIsServer");
                    }
                    this.port = XMLUtil.getInt(xmlElement, "socketPort", -1);
                    this.dpwsInterface = xmlElement.getAttribute("dpwsInterface");
                    this.dpwsPort = XMLUtil.getInt(xmlElement, "dpwsPort", -1);
                    this.horizontalScrollBar = XMLUtil.getBool(xmlElement, "horizontalScrollBar", true);
                    this.verticalScrollBar = XMLUtil.getBool(xmlElement, "verticalScrollBar", true);
                    loadXMLRec(xmlElement);
                    propagateDimmingInfo(this.dimming, this.dimTicks, this.threadSpeed);
                    this.modificationProperty = XMLUtil.getBool(xmlElement, "modifiable", true);
                    bool = true;
                } else {
                    JOptionPane.showMessageDialog(null, "The file contains a collection of workspaces and must thus be opened with \"OpenAll\".", "Open Document Error", 0);
                }
            } catch (Throwable localThrowable) {
                JOptionPane.showMessageDialog(null, "Unhandled exception.", "Load Document Failed", 0);
                Utils.writeException(localThrowable, "Unhandled exception when loading document.");
            }
        }
        return bool;
    }

    public void loadXMLRec(Element xmlElement) {
        setPaperColor(new Color(XMLUtil.getInt(xmlElement, "color", getPaperColor().getRGB())));
        if (Utils.getSaveVersion(xmlElement) >= 7) {
            try {
                this.socketSendMode = SocketOut.SendMode.valueOf(xmlElement.getAttribute("socketSendMode"));
            } catch (IllegalArgumentException localIllegalArgumentException) {
                if (!Editor.isTest) {
                    Utils.writeError("Illegal socket send mode: \"" + xmlElement.getAttribute("socketSendMode") + "\"");
                }
            }
        }
        //Conserve les id des élement afin de les retrouver au niveai des link et actions
        Vector localVector1 = new Vector(); 
        Vector localVector2 = new Vector();
        NodeList localNodeList = xmlElement.getChildNodes();
        Node localNode;
        Element localElement;
        
        NodeList variablesNodeList = xmlElement.getElementsByTagName(varRacineTag);
        if(variablesNodeList.getLength() > 0){
            localElement = (Element) variablesNodeList.item(0);
            if (localElement.getTagName().equals(varRacineTag)) {
                loadVariablesAtTag(localElement, localVector1, localVector2);
            }
        } 
        
        for (int i = 0; i < localNodeList.getLength(); i++) {
            localNode = localNodeList.item(i);
            if (localNode.getNodeType() == 1) {
                localElement = (Element) localNode;
                loadObjectAtTag(localElement, null, localVector1, localVector2);
            }
        }
        for (int i = 0; i < localNodeList.getLength(); i++) {
            localNode = localNodeList.item(i);
            if (localNode.getNodeType() == 1) {
                localElement = (Element) localNode;
                if (localElement.getTagName().equals(gcGroupTag)) {
                    loadGCGroupAtTag(localElement, null, localVector1, localVector2);
                }
            }
        }
        for (int i = 0; i < localNodeList.getLength(); i++) {
            localNode = localNodeList.item(i);
            if (localNode.getNodeType() == 1) {
                localElement = (Element) localNode;
                if (localElement.getTagName().equals(gcLinkTag)) {
                    loadLinkAtTag(localElement, null, localVector1, localVector2);
                }
            }
        }        
               
    }
    
    private void loadVariablesAtTag(Element variablesElement, Vector localVector1, Vector localVector2) {
        //REcup des paramètre global sur variablesElement si necessaire
        NodeList variablesList = variablesElement.getChildNodes();
        Node node;
        Element varElement;
        for (int i = 0; i < variablesList.getLength(); i++) {
            node = variablesList.item(i);
            if (node.getNodeType() == 1) {
                varElement = (Element) node;
                //Standard variable
                if (varElement.getTagName().equals(stdVariableTag)) {
                    StandardVariable stdVar = StandardVariable.loadXML(varElement, localVector1, localVector2);
                    stdVariables.add(stdVar);
                }
                //Function Block
                if (varElement.getTagName().equals(funcBlockTag)) {
                    FunctionBlock var = FunctionBlock.loadXML(varElement, localVector1, localVector2);
                    functionBlocs.add(var);
                }
                //Transition session
                if (varElement.getTagName().equals(tranSessionTag)) {
                    TransitionSession var = (TransitionSession) TransitionSession.loadXML(varElement, localVector1, localVector2);
                    transitionSession.add(var);
                }
            }
        }
    }

    void loadObjectAtTag(Element xmlElement, JGoArea paramJGoArea, Vector paramVector1, Vector paramVector2) {
        String str = xmlElement.getTagName();
        Object localObject = null;
        if ((str.equals(gcStepTag)) || (str.equals(initialGCStepTag)) || (str.equals(enterStepTag)) || (str.equals(exitStepTag))) {
            localObject = GCStep.loadXML(xmlElement, paramVector1, paramVector2);
        }
        if ((str.equals(connectionPostInTag)) || (str.equals(connectionPostOutTag))) {
            localObject = ConnectionPost.loadXML(xmlElement, paramVector1, paramVector2);
        }
        if (xmlElement.getTagName().equals(this.macroStepTag)) {
            localObject = MacroStep.loadXML(xmlElement, paramVector1, paramVector2);
        }
        if (xmlElement.getTagName().equals(this.gcTransitionTag)) {
            localObject = GCTransition.loadXML(xmlElement, paramVector1, paramVector2);
        }
        if (xmlElement.getTagName().equals(this.exTransitionTag)) {
            localObject = ExceptionTransition.loadXML(xmlElement, paramVector1, paramVector2);
        }
        if (xmlElement.getTagName().equals(parallelSplitTag)) {
            localObject = ParallelSplit.loadXML(xmlElement, paramVector1, paramVector2);
        }
        if (xmlElement.getTagName().equals(parallelJoinTag)) {
            localObject = ParallelJoin.loadXML(xmlElement, paramVector1, paramVector2);
        }
        if (xmlElement.getTagName().equals(grafcetProcTag)) {
            localObject = GrafcetProcedure.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(procedureStepTag)) {
            localObject = ProcedureStep.loadProcedureXML(xmlElement, paramVector1, paramVector2);
        }
        if (xmlElement.getTagName().equals(processStepTag)) {
            localObject = ProcessStep.loadProcedureXML(xmlElement, paramVector1, paramVector2);
        }
        if (xmlElement.getTagName().equals(xmlMessageInTag)) {
            localObject = XMLMessageIn.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(xmlMessageOutTag)) {
            localObject = XMLMessageOut.loadXML(xmlElement);
        }
        if (str.equals(workspaceObjectTag)) {
            localObject = WorkspaceObject.loadXML(xmlElement);
        }
        if (str.equals(this.stepFusionSetTag)) {
            localObject = StepFusionSet.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.gcClassTag)) {
            localObject = GCClass.loadXML(xmlElement);
        }
        if ((str.equals(digitalInTag)) || (str.equals(digitalIn1Tag))) {
            localObject = DigitalIn.loadXML(xmlElement);
        }
        if ((str.equals(digitalOutTag)) || (str.equals(digitalOut0Tag)) || (str.equals(digitalOut1Tag))) {
            localObject = DigitalOut.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.analogInTag)) {
            localObject = AnalogIn.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.analogOutTag)) {
            localObject = AnalogOut.loadXML(xmlElement);
        }
        if ((str.equals(booleanVarTag)) || (str.equals(integerVarTag)) || (str.equals(stringVarTag)) || (str.equals(realVarTag)) || (str.equals(indexVarTag)) || (str.equals(integerAttributeVarTag)) || (str.equals(realAttributeVarTag)) || (str.equals(stringAttributeVarTag))) {
            localObject = InternalVariable.loadXML(xmlElement);
        }
        if ((str.equals(booleanListTag)) || (str.equals(integerListTag)) || (str.equals(stringListTag)) || (str.equals(realListTag))) {
            localObject = BasicList.loadXML(xmlElement);
        }
        if ((str.equals(sBooleanInTag)) || (str.equals(sIntInTag)) || (str.equals(sRealInTag)) || (str.equals(sStringInTag))) {
            localObject = SocketIn.loadXML(xmlElement);
        }
        if ((str.equals(xBooleanOutTag)) || (str.equals(xIntOutTag)) || (str.equals(xRealOutTag)) || (str.equals(xStringOutTag)) || (str.equals(xBooleanInTag)) || (str.equals(xIntInTag)) || (str.equals(xRealInTag)) || (str.equals(xStringInTag))) {
            Utils.writeWarning("Obsolete object ignored: \"" + str + "\"");
        }
        if ((str.equals(sBooleanOutTag)) || (str.equals(sIntOutTag)) || (str.equals(sRealOutTag)) || (str.equals(sStringOutTag))) {
            localObject = SocketOut.loadXML(xmlElement);
        }
        if (str.equals(byteStreamInTag)) {
            localObject = ByteStreamIn.loadXML(xmlElement);
        }
        if (str.equals(byteStreamOutTag)) {
            localObject = ByteStreamOut.loadXML(xmlElement);
        }
        if (str.equals(dpwsObjectTag)) {
            localObject = DPWSObject.loadXML(xmlElement);
        }
        if (str.equals(labCommObjectTag)) {
            localObject = LabCommObject.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.freeTextTag)) {
            localObject = FreeText.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.gcRectangleTag)) {
            localObject = GCRectangle.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.gcEllipseTag)) {
            localObject = GCEllipse.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.gc3DRectTag)) {
            localObject = GC3DRect.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.gcPolygonTag)) {
            localObject = GCPolygon.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.gcStrokeTag)) {
            localObject = GCStroke.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.chemIconTag)) {
            localObject = ChemIcon.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.iconTag)) {
            localObject = Icon.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.lucasLogoTag)) {
            localObject = LUCASLogo.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(this.showWorkspaceButtonTag)) {
            localObject = ShowWorkspaceButton.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(xAssignmentButtonTag)) {
            localObject = AssignmentButton.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(xBrowserTag)) {
            localObject = Browser.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(xGraphicalButtonTag)) {
            localObject = GraphicalButton.loadXML(xmlElement);
        }
        if (xmlElement.getTagName().equals(plotterTag)) {
            localObject = SFCPlotter.loadXML(xmlElement);
        }
        if (paramJGoArea != null) {
            paramJGoArea.addObjectAtTail((JGoObject) localObject);
        } else {
            addObjectAtTail((JGoObject) localObject);
        }
    }

    void loadGCGroupAtTag(Element xmlElement, JGoArea paramJGoArea, Vector paramVector1, Vector paramVector2) {
        if (xmlElement.getTagName().equals(gcGroupTag)) {
            GCGroup localGCGroup = GCGroup.loadXML(xmlElement, paramVector1, paramVector2, this);
            if (localGCGroup != null) {
                if (paramJGoArea != null) {
                    paramJGoArea.addObjectAtTail(localGCGroup);
                } else {
                    addObjectAtTail(localGCGroup);
                }
            }
        }
    }

    void loadLinkAtTag(Element xmlElement, JGoArea paramJGoArea, Vector paramVector1, Vector paramVector2) {
        if (xmlElement.getTagName().equals(gcLinkTag)) {
            GCLink localGCLink = GCLink.loadXML(xmlElement, paramVector1, paramVector2);
            if (localGCLink != null) {
                if (paramJGoArea != null) {
                    setSuspendUpdates(true);
                    paramJGoArea.addObjectAtTail(localGCLink);
                    setSuspendUpdates(false);
                } else {
                    setSuspendUpdates(true);
                    addObjectAtTail(localGCLink);
                    setSuspendUpdates(false);
                }
            }
        }
    }

    public void endTransaction(String paramString) {
        super.endTransaction(paramString);
        AppAction.updateAllActions();
    }

    public synchronized void executeOnce() {
        synchronized (this.inputs) {
            Iterator localIterator = this.inputs.iterator();
            while (localIterator.hasNext()) {
                Referencable localReferencable = (Referencable) localIterator.next();
                if ((localReferencable instanceof LabCommObject)) {
                    ((LabCommObject) localReferencable).tick();
                }
                if ((localReferencable instanceof DigitalIn)) {
                    ((DigitalIn) localReferencable).readInput();
                }
                if ((localReferencable instanceof AnalogIn)) {
                    ((AnalogIn) localReferencable).readInput();
                }
                if ((localReferencable instanceof SocketIn)) {
                    ((SocketIn) localReferencable).readInput();
                }
                if ((localReferencable instanceof ByteStreamIn)) {
                    ((ByteStreamIn) localReferencable).readInput();
                }
                if ((localReferencable instanceof XMLMessageIn)) {
                    ((XMLMessageIn) localReferencable).newValueRead = false;
                    ((XMLMessageIn) localReferencable).readInput();
                    if (((XMLMessageIn) localReferencable).newValue) {
                        ((XMLMessageIn) localReferencable).newValueRead = true;
                        ((XMLMessageIn) localReferencable).newValue = false;
                    }
                }
                if ((localReferencable instanceof InternalVariable)) {
                    InternalVariable localInternalVariable = (InternalVariable) localReferencable;
                    if ((localInternalVariable.isUpdated) && (!localInternalVariable.isConstant)) {
                        localInternalVariable.updateValue();
                    }
                }
            }
        }
        preStepCodeDocument();
        stepCodeDocument(this.realTime);
        changeStateDocument();
        updateNormalActions();
        updatePlotters();
    }

    void preStepCodeDocument() {
        Iterator localIterator1 = this.transitions.iterator();
        //Object localObject;
        while (localIterator1.hasNext()) {
            GenericTransition localObject = (GenericTransition) localIterator1.next();
            ((GenericTransition) localObject).preTestAndFire();
        }
        localIterator1 = this.macros.iterator();
        while (localIterator1.hasNext()) {
            MacroStep localObject = (MacroStep) localIterator1.next();
            ((MacroStep) localObject).myContentDocument.preStepCodeDocument();
        }
        localIterator1 = this.workspaces.iterator();
        while (localIterator1.hasNext()) {
            WorkspaceObject localObject = (WorkspaceObject) localIterator1.next();
            if (((WorkspaceObject) localObject).enabled) {
                localObject.scanCounter -= 1;
                if (((WorkspaceObject) localObject).scanCounter == 0) {
                    ((WorkspaceObject) localObject).myContentDocument.preStepCodeDocument();
                }
            }
        }
        localIterator1 = this.procedures.iterator();
        while (localIterator1.hasNext()) {
            ProcedureStep localObject = (ProcedureStep) localIterator1.next();
            if ((((ProcedureStep) localObject).myContentDocument != null) && (((ProcedureStep) localObject).timer > 0)) {
                ((ProcedureStep) localObject).myContentDocument.preStepCodeDocument();
            }
        }
        localIterator1 = this.processes.iterator();
        Iterator localIterator2;
        GCDocument localGCDocument;
        while (localIterator1.hasNext()) {
            ProcessStep localObject = (ProcessStep) localIterator1.next();
            localIterator2 = ((ProcessStep) localObject).getCalls().iterator();
            while (localIterator2.hasNext()) {
                localGCDocument = (GCDocument) localIterator2.next();
                localGCDocument.preStepCodeDocument();
            }
        }
        localIterator1 = this.XMLMessageIns.iterator();
        while (localIterator1.hasNext()) {
            XMLMessageIn localObject = (XMLMessageIn) localIterator1.next();
            localIterator2 = ((XMLMessageIn) localObject).getCalls().iterator();
            while (localIterator2.hasNext()) {
                localGCDocument = (GCDocument) localIterator2.next();
                localGCDocument.preStepCodeDocument();
            }
        }
    }

    void stepCodeDocument(double paramDouble) {
        this.realTime = paramDouble;
        if (!this.partitioningDone) {
            this.partitioningDone = true;
            this.partitions = new ArrayList();
            GenericTransition.partitionTransitions(this.partitions, this.transitions);
        }
        GenericTransition.applyPriorities(this.partitions);
        Iterator localIterator1 = this.transitions.iterator();
        Object localObject;
        while (localIterator1.hasNext()) {
            localObject = (GenericTransition) localIterator1.next();
            ((GenericTransition) localObject).testAndFire();
        }
        localIterator1 = this.macros.iterator();
        while (localIterator1.hasNext()) {
            localObject = (MacroStep) localIterator1.next();
            ((MacroStep) localObject).myContentDocument.stepCodeDocument(this.realTime);
        }
        localIterator1 = this.workspaces.iterator();
        while (localIterator1.hasNext()) {
            localObject = (WorkspaceObject) localIterator1.next();
            if ((((WorkspaceObject) localObject).enabled) && (((WorkspaceObject) localObject).scanCounter == 0)) {
                ((WorkspaceObject) localObject).myContentDocument.stepCodeDocument(this.realTime);
            }
        }
        localIterator1 = this.procedures.iterator();
        while (localIterator1.hasNext()) {
            localObject = (ProcedureStep) localIterator1.next();
            if ((((ProcedureStep) localObject).myContentDocument != null) && (((ProcedureStep) localObject).timer > 0)) {
                ((ProcedureStep) localObject).myContentDocument.stepCodeDocument(this.realTime);
            }
        }
        localIterator1 = this.processes.iterator();
        Iterator localIterator2;
        GCDocument localGCDocument;
        while (localIterator1.hasNext()) {
            localObject = (ProcessStep) localIterator1.next();
            localIterator2 = ((ProcessStep) localObject).getCalls().iterator();
            while (localIterator2.hasNext()) {
                localGCDocument = (GCDocument) localIterator2.next();
                localGCDocument.stepCodeDocument(this.realTime);
            }
        }
        localIterator1 = this.XMLMessageIns.iterator();
        while (localIterator1.hasNext()) {
            localObject = (XMLMessageIn) localIterator1.next();
            localIterator2 = ((XMLMessageIn) localObject).getCalls().iterator();
            while (localIterator2.hasNext()) {
                localGCDocument = (GCDocument) localIterator2.next();
                localGCDocument.stepCodeDocument(this.realTime);
            }
        }
    }

    void changeStateDocument() {
        Iterator localIterator1 = this.steps.iterator();
        Object localObject1;
        while (localIterator1.hasNext()) {
            localObject1 = (GCStep) localIterator1.next();
            ((GCStep) localObject1).changeState();
        }
        localIterator1 = this.macros.iterator();
        while (localIterator1.hasNext()) {
            localObject1 = (MacroStep) localIterator1.next();
            ((MacroStep) localObject1).myContentDocument.changeStateDocument();
            ((MacroStep) localObject1).myContentDocument.updateNormalActions();
            ((MacroStep) localObject1).changeState();
        }
        localIterator1 = this.workspaces.iterator();
        while (localIterator1.hasNext()) {
            localObject1 = (WorkspaceObject) localIterator1.next();
            if ((((WorkspaceObject) localObject1).enabled) && (((WorkspaceObject) localObject1).scanCounter == 0)) {
                ((WorkspaceObject) localObject1).myContentDocument.changeStateDocument();
                ((WorkspaceObject) localObject1).myContentDocument.updateNormalActions();
                ((WorkspaceObject) localObject1).scanCounter = ((WorkspaceObject) localObject1).scanCycle;
            }
        }
        localIterator1 = this.procedures.iterator();
        while (localIterator1.hasNext()) {
            localObject1 = (ProcedureStep) localIterator1.next();
            if (((ProcedureStep) localObject1).myContentDocument != null) {
                ((ProcedureStep) localObject1).myContentDocument.changeStateDocument();
                ((ProcedureStep) localObject1).myContentDocument.updateNormalActions();
            }
            ((ProcedureStep) localObject1).changeState();
        }
        localIterator1 = this.processes.iterator();
        ObservableList localObservableList;
        ArrayList localArrayList;
        Iterator localIterator2;
        GCDocument localGCDocument;
        while (localIterator1.hasNext()) {
            localObject1 = (ProcessStep) localIterator1.next();
            localObservableList = ((ProcessStep) localObject1).getCalls();
            synchronized (localObservableList) {
                localArrayList = new ArrayList();
                localIterator2 = localObservableList.iterator();
                while (localIterator2.hasNext()) {
                    localGCDocument = (GCDocument) localIterator2.next();
                    if (localGCDocument != null) {
                        localGCDocument.changeStateDocument();
                        localGCDocument.updateNormalActions();
                        if (localGCDocument.markForRemoval) {
                            localArrayList.add(localGCDocument);
                        }
                    } else {
                        Utils.writeError("doc is null");
                    }
                }
                localIterator2 = localArrayList.iterator();
                while (localIterator2.hasNext()) {
                    localGCDocument = (GCDocument) localIterator2.next();
                    localObservableList.remove(localGCDocument);
                }
            }
            ((ProcessStep) localObject1).changeState();
        }
        localIterator1 = this.XMLMessageIns.iterator();
        while (localIterator1.hasNext()) {
            localObject1 = (XMLMessageIn) localIterator1.next();
            localObservableList = ((XMLMessageIn) localObject1).getCalls();
            synchronized (localObservableList) {
                try {
                    localArrayList = new ArrayList();
                    localIterator2 = ((XMLMessageIn) localObject1).getCalls().iterator();
                    while (localIterator2.hasNext()) {
                        localGCDocument = (GCDocument) localIterator2.next();
                        if (localGCDocument != null) {
                            localGCDocument.changeStateDocument();
                            if (localGCDocument.markForRemoval) {
                                localArrayList.add(localGCDocument);
                            }
                        }
                    }
                    localIterator2 = localArrayList.iterator();
                    while (localIterator2.hasNext()) {
                        localGCDocument = (GCDocument) localIterator2.next();
                        localObservableList.remove(localGCDocument);
                    }
                } catch (Exception localException) {
                    Utils.writeException(localException);
                }
            }
        }
    }

    void updateNormalActions() {
        Iterator localIterator = this.steps.iterator();
        Object localObject;
        while (localIterator.hasNext()) {
            localObject = (GCStep) localIterator.next();
            ((GCStep) localObject).updateNormalActions();
        }
        localIterator = this.macros.iterator();
        while (localIterator.hasNext()) {
            localObject = (MacroStep) localIterator.next();
            ((MacroStep) localObject).updateNormalActions();
        }
    }

    void updatePlotters() {
        Iterator localIterator1 = this.macros.iterator();
        Object localObject;
        while (localIterator1.hasNext()) {
            localObject = (MacroStep) localIterator1.next();
            ((MacroStep) localObject).myContentDocument.updatePlotters();
        }
        localIterator1 = this.workspaces.iterator();
        while (localIterator1.hasNext()) {
            localObject = (WorkspaceObject) localIterator1.next();
            if ((((WorkspaceObject) localObject).enabled) && (((WorkspaceObject) localObject).scanCounter == 0)) {
                ((WorkspaceObject) localObject).myContentDocument.updatePlotters();
            }
        }
        localIterator1 = this.procedures.iterator();
        while (localIterator1.hasNext()) {
            localObject = (ProcedureStep) localIterator1.next();
            if ((((ProcedureStep) localObject).myContentDocument != null) && (((ProcedureStep) localObject).timer > 0)) {
                ((ProcedureStep) localObject).myContentDocument.updatePlotters();
            }
        }
        localIterator1 = this.processes.iterator();
        Iterator localIterator2;
        GCDocument localGCDocument;
        while (localIterator1.hasNext()) {
            localObject = (ProcessStep) localIterator1.next();
            localIterator2 = ((ProcessStep) localObject).getCalls().iterator();
            while (localIterator2.hasNext()) {
                localGCDocument = (GCDocument) localIterator2.next();
                localGCDocument.updatePlotters();
            }
        }
        localIterator1 = this.XMLMessageIns.iterator();
        while (localIterator1.hasNext()) {
            localObject = (XMLMessageIn) localIterator1.next();
            localIterator2 = ((XMLMessageIn) localObject).getCalls().iterator();
            while (localIterator2.hasNext()) {
                localGCDocument = (GCDocument) localIterator2.next();
                localGCDocument.updatePlotters();
            }
        }
        localIterator1 = this.plotters.iterator();
        while (localIterator1.hasNext()) {
            localObject = (SFCPlotter) localIterator1.next();
            ((SFCPlotter) localObject).updatePlot(this.realTime);
        }
    }

    void initializeDocumentVariables() {
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            Object localObject;
            if ((localJGoObject instanceof InternalVariable)) {
                localObject = (InternalVariable) localJGoObject;
                ((InternalVariable) localObject).initialize();
            }
            if ((localJGoObject instanceof WorkspaceObject)) {
                localObject = (WorkspaceObject) localJGoObject;
                ((WorkspaceObject) localObject).myContentDocument.initializeDocumentVariables();
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
    }

    void initializeDocument(GCDocument paramGCDocument, boolean paramBoolean) {
        initializeDocument_socket();
        initializeDocument_DPWSEventing();
        initializeDocument_setup(paramGCDocument, paramBoolean);
        initializeDocument_activateInitSteps(null);        
    }
    
    void initializeDocument2(GCDocument paramGCDocument) {        
        this.transitions.clear();
        this.macros.clear();
        this.steps.clear();
        initializeDocument_setup2(paramGCDocument);
    }

    synchronized void initializeDocument_socket() {
        if (isUsingSockets()) {
            if ((this.isSocketServer) && (this.socketServer == null)) {
                try {
                    this.socketServer = new MySocketServer(this.port);
                } catch (IOException localIOException) {
                    Utils.writeError("Socket server setup failed: " + localIOException.getMessage());
                }
            } else if ((!this.isSocketServer) && (!this.isConnected)) {
                boolean bool = connect();
                if (bool) {
                    this.isConnected = true;
                    this.socketThread = new SocketReader();
                    this.socketThread.start();
                }
            }
        }
    }

    public boolean isDPWSEventingConfigured() {
        return (!this.dpwsInterface.isEmpty()) && (this.dpwsPort != -1);
    }

    void initializeDocument_DPWSEventing() {
        stopDPWSEventListener();
        if (isDPWSEventingConfigured()) {
            try {
                NetworkInterface localNetworkInterface = NetworkInterface.getByName(this.dpwsInterface);
                if (localNetworkInterface == null) {
                    Utils.writeError("DPWS Event - Network interface \"" + this.dpwsInterface + "\" not found.");
                    return;
                }
                Enumeration localEnumeration = localNetworkInterface.getInetAddresses();
                LocalIPAddress localLocalIPAddress = null;
                while ((localEnumeration.hasMoreElements()) && (localLocalIPAddress == null)) {
                    InetAddress localObject = (InetAddress) localEnumeration.nextElement();
                    if ((localObject instanceof Inet4Address)) {
                        localLocalIPAddress = DPWSNetworks.getLocalIPAddress((InetAddress) localObject);
                    }
                }
                if (localLocalIPAddress == null) {
                    Utils.writeError("DPWS Event - No IPv4-address found on network interface \"" + this.dpwsInterface + "\".");
                    return;
                }
                Object localObject = File.createTempFile("DPWSDummy", ".xml");
                ((File) localObject).deleteOnExit();
                Registry localRegistry = new Registry(((File) localObject).getAbsolutePath(), false);
                localLocalIPAddress.bindRegistry(localRegistry);
                this.dpwsEventListener = new EventHandlerListener(new JettyServletContainer(), localRegistry, this.dpwsPort);
                this.dpwsEventListener.defaultStart();
            } catch (Exception localException) {
                Utils.writeException(localException, "DPWS Event setup failed.");
                this.dpwsEventListener = null;
            }
        }
    }

    void initializeDocument_setup(GCDocument paramGCDocument, boolean paramBoolean) {
        this.simulation = paramBoolean;
        this.executing = true;
        if ((getView() != null) && (getView().getInternalFrame() != null)) {
            getView().getInternalFrame().setFrameIcon(runIcon);
        }
        this.bytestreams.clear();
        this.transitions.clear();
        this.macros.clear();
        this.procedures.clear();
        this.processes.clear();
        this.XMLMessageIns.clear();
        this.workspaces.clear();
        this.plotters.clear();
        this.steps.clear();
        this.digitalouts.clear();
        this.booleans.clear();
        this.partitioningDone = false;
        setSkipsUndoManager(true);
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        Object localObject;
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof LabCommObject)) {
                paramGCDocument.addInput((LabCommObject) localJGoObject);
            }
            if ((localJGoObject instanceof SocketIn)) {
                localObject = (SocketIn) localJGoObject;
                SocketIn.addSocketInput((SocketIn) localObject);
                paramGCDocument.addInput((Referencable) localObject);
            }
            if ((localJGoObject instanceof XMLInSource)) {
                localObject = (XMLInSource) localJGoObject;
                XMLIn.addXMLInput((XMLInSource) localObject);
                paramGCDocument.addInput((Referencable) localObject);
            }
            if ((localJGoObject instanceof DigitalIn)) {
                localObject = (DigitalIn) localJGoObject;
                ((DigitalIn) localObject).initialize();
                if (((DigitalIn) localObject).isCyclicUpdated()) {
                    paramGCDocument.addInput((Referencable) localObject);
                } else {
                    paramGCDocument.addNonInput((Readable) localObject);
                }
            }
            if ((localJGoObject instanceof DigitalOut)) {
                localObject = (DigitalOut) localJGoObject;
                ((DigitalOut) localObject).compile();
                this.digitalouts.add((DigitalOut) localObject);
            }
            if ((localJGoObject instanceof AnalogOut)) {
                localObject = (AnalogOut) localJGoObject;
                ((AnalogOut) localObject).compile();
            }
            if ((localJGoObject instanceof AnalogIn)) {
                localObject = (AnalogIn) localJGoObject;
                ((AnalogIn) localObject).initialize();
                if (((AnalogIn) localObject).isCyclicUpdated()) {
                    paramGCDocument.addInput((Referencable) localObject);
                } else {
                    paramGCDocument.addNonInput((Readable) localObject);
                }
            }
            if ((localJGoObject instanceof ByteStreamIn)) {
                localObject = (ByteStreamIn) localJGoObject;
                ((ByteStreamIn) localObject).initialize();
                paramGCDocument.addInput((Referencable) localObject);
                this.bytestreams.add((ByteStreamIn) localObject);
            }
            if ((localJGoObject instanceof ByteStreamOut)) {
                localObject = (ByteStreamOut) localJGoObject;
                ((ByteStreamOut) localObject).initialize();
            }
            if ((localJGoObject instanceof InternalVariable)) {
                localObject = (InternalVariable) localJGoObject;
                ((InternalVariable) localObject).initialize();
                if (((InternalVariable) localObject).isUpdated) {
                    paramGCDocument.addInput((Referencable) localObject);
                }
            }
            if ((localJGoObject instanceof BasicList)) {
                localObject = (BasicList) localJGoObject;
                ((BasicList) localObject).initialize();
            }
            if ((localJGoObject instanceof DPWSObject)) {
                localObject = (DPWSObject) localJGoObject;
                ((DPWSObject) localObject).setEventListener(paramGCDocument.getDPWSEventListener());
            }
            if ((localJGoObject instanceof LabCommObject)) {
                localObject = (LabCommObject) localJGoObject;
                ((LabCommObject) localObject).initialize();
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
        localJGoListPosition = getFirstObjectPos();
        JGoObject localJGoObject;
        for (localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof GCTransition)) {
                localObject = (GCTransition) localJGoObject;
                ((GCTransition) localObject).initialize();
                this.transitions.add((GenericTransition) localObject);
            }
            if ((localJGoObject instanceof ExceptionTransition)) {
                localObject = (ExceptionTransition) localJGoObject;
                ((ExceptionTransition) localObject).initialize();
                this.transitions.add((GenericTransition) localObject);
            }
            if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep))) {
                localObject = (MacroStep) localJGoObject;
                ((MacroStep) localObject).resetInitActivated();
                this.macros.add((MacroStep) localObject);
                ((MacroStep) localObject).myContentDocument.initializeDocument_setup(paramGCDocument, paramBoolean);
            }
            if ((localJGoObject instanceof ProcedureStep)) {
                if ((localJGoObject instanceof ProcessStep)) {
                    this.processes.add((ProcessStep) localJGoObject);
                } else {
                    this.procedures.add((ProcedureStep) localJGoObject);
                }
            }
            if ((localJGoObject instanceof WorkspaceObject)) {
                localObject = (WorkspaceObject) localJGoObject;
                this.workspaces.add((WorkspaceObject) localObject);
                ((WorkspaceObject) localObject).myContentDocument.initializeDocument_setup(paramGCDocument, paramBoolean);
            }
            if ((localJGoObject instanceof XMLMessageIn)) {
                localObject = (XMLMessageIn) localJGoObject;
                this.XMLMessageIns.add((XMLMessageIn) localObject);
                ((XMLMessageIn) localObject).myContentDocument.initializeDocument_setup(paramGCDocument, paramBoolean);
            }
            if ((localJGoObject instanceof XMLMessageOut)) {
                localObject = (XMLMessageOut) localJGoObject;
                ((XMLMessageOut) localObject).myContentDocument.initializeDocument_setup(paramGCDocument, paramBoolean);
            }
            if ((localJGoObject instanceof SFCPlotter)) {
                this.plotters.add((SFCPlotter) localJGoObject);
                ((SFCPlotter) localJGoObject).plotter.start();
                ((SFCPlotter) localJGoObject).running = true;
            }
            if ((localJGoObject instanceof GCStep)) {
                this.steps.add((GCStep) localJGoObject);
            }
            if ((localJGoObject instanceof BooleanVariable)) {
                this.booleans.add((BooleanVariable) localJGoObject);
            }
            if ((localJGoObject instanceof StepFusionSet)) {
                localObject = (StepFusionSet) localJGoObject;
                ((StepFusionSet) localObject).compile();
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
    }
    
    void initializeDocument_setup2(GCDocument gcDocument) {               
        JGoListPosition localJGoListPosition = getFirstObjectPos();      
        JGoObject jGoObject;
        for (jGoObject = getObjectAtPos(localJGoListPosition); (jGoObject != null) && (localJGoListPosition != null); jGoObject = getObjectAtPos(localJGoListPosition)) {
            if ((jGoObject instanceof GCTransition)) {
                gcDocument.transitions.add((GCTransition) jGoObject);
                
                /*System.out.println((GCTransition) jGoObject +" / "+ jGoObject.getDocument());
                System.out.println(((GCTransition) jGoObject).precedingSteps);
                System.out.println(((GCTransition) jGoObject).succeedingSteps);
                System.out.println("");    */    
            }
            if ((jGoObject instanceof ExceptionTransition)) {
                gcDocument.transitions.add((ExceptionTransition) jGoObject);
            }
            if (((jGoObject instanceof MacroStep)) && (!(jGoObject instanceof ProcedureStep))) {
                gcDocument.macros.add((MacroStep) jGoObject);
                ((MacroStep) jGoObject).myContentDocument.initializeDocument_setup2(gcDocument);
            }            
            if ((jGoObject instanceof GCStep)) {
                gcDocument.steps.add((GCStep) jGoObject);
            }            
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
    }

    void initializeDocument_activateInitSteps(Vector<MacroStep> paramVector) {
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            Object localObject1;
            Object localObject2;
            if ((localJGoObject instanceof GCStepInitial)) {
                localObject1 = (GCStepInitial) localJGoObject;
                if (paramVector != null) {
                    localObject2 = paramVector.iterator();
                    while (((Iterator) localObject2).hasNext()) {
                        MacroStep localMacroStep = (MacroStep) ((Iterator) localObject2).next();
                        localMacroStep.activateInit();
                    }
                }
                ((GCStepInitial) localObject1).activate();
                ((GCStepInitial) localObject1).executeNormalActions(true);
            }
            if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep))) {
                localObject1 = (MacroStep) localJGoObject;
                if (paramVector != null) {
                    localObject2 = (Vector) paramVector.clone();
                } else {
                    localObject2 = new Vector();
                }
                ((Vector) localObject2).add(localObject1);
                ((MacroStep) localObject1).myContentDocument.initializeDocument_activateInitSteps((Vector) localObject2);
            }
            if ((localJGoObject instanceof WorkspaceObject)) {
                localObject1 = (WorkspaceObject) localJGoObject;
                ((WorkspaceObject) localObject1).myContentDocument.initializeDocument_activateInitSteps(null);
            }
            if ((localJGoObject instanceof XMLMessageIn)) {
                localObject1 = (XMLMessageIn) localJGoObject;
                ((XMLMessageIn) localObject1).myContentDocument.initializeDocument_activateInitSteps(null);
            }
            if ((localJGoObject instanceof XMLMessageOut)) {
                localObject1 = (XMLMessageOut) localJGoObject;
                ((XMLMessageOut) localObject1).myContentDocument.initializeDocument_activateInitSteps(null);
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
    }

    void setDragAndDrop(boolean paramBoolean) {
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            Object localObject;
            if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep))) {
                localObject = (MacroStep) localJGoObject;
                if (((MacroStep) localObject).view != null) {
                    ((MacroStep) localObject).view.setDragDropEnabled(paramBoolean);
                }
                ((MacroStep) localObject).myContentDocument.setDragAndDrop(paramBoolean);
            }
            if ((localJGoObject instanceof WorkspaceObject)) {
                localObject = (WorkspaceObject) localJGoObject;
                if (((WorkspaceObject) localObject).view != null) {
                    ((WorkspaceObject) localObject).view.setDragDropEnabled(paramBoolean);
                }
                ((WorkspaceObject) localObject).myContentDocument.setDragAndDrop(paramBoolean);
            }
            if ((localJGoObject instanceof XMLMessageIn)) {
                localObject = (XMLMessageIn) localJGoObject;
                if (((XMLMessageIn) localObject).view != null) {
                    ((XMLMessageIn) localObject).view.setDragDropEnabled(paramBoolean);
                }
                ((XMLMessageIn) localObject).myContentDocument.setDragAndDrop(paramBoolean);
            }
            if ((localJGoObject instanceof XMLMessageOut)) {
                localObject = (XMLMessageOut) localJGoObject;
                if (((XMLMessageOut) localObject).view != null) {
                    ((XMLMessageOut) localObject).view.setDragDropEnabled(paramBoolean);
                }
                ((XMLMessageOut) localObject).myContentDocument.setDragAndDrop(paramBoolean);
            }
            if ((localJGoObject instanceof GrafcetProcedure)) {
                localObject = (GrafcetProcedure) localJGoObject;
                if (((GrafcetProcedure) localObject).view != null) {
                    ((GrafcetProcedure) localObject).view.setDragDropEnabled(paramBoolean);
                }
                ((GrafcetProcedure) localObject).myContentDocument.setDragAndDrop(paramBoolean);
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
    }

    void removeXML() {
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof XMLInSource)) {
                XMLInSource localXMLInSource = (XMLInSource) localJGoObject;
                XMLIn.removeXMLInput(localXMLInSource);
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
    }

    void stopDocument() {
        this.executing = false;
        stopDPWSEventListener();
        if ((getView() != null) && (getView().getInternalFrame() != null)) {
            if (isModifiable()) {
                getView().getInternalFrame().setFrameIcon(defaultIcon);
            } else {
                getView().getInternalFrame().setFrameIcon(lockIcon);
            }
        }
        synchronized (this.inputs) {
            this.inputs.clear();
            this.nonInputs.clear();
        }
        setSkipsUndoManager(false);
        JGoListPosition var = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos((JGoListPosition) var); (localJGoObject != null) && (var != null); localJGoObject = getObjectAtPos((JGoListPosition) var)) {
            Object localObject2;
            if ((localJGoObject instanceof SocketIn)) {
                localObject2 = (SocketIn) localJGoObject;
                SocketIn.removeSocketInput((SocketIn) localObject2);
            }
            if ((localJGoObject instanceof XMLInSource)) {
                localObject2 = (XMLInSource) localJGoObject;
                XMLIn.removeXMLInput((XMLInSource) localObject2);
            }
            if ((localJGoObject instanceof GCStep)) {
                localObject2 = (GCStep) localJGoObject;
                if (((GCStep) localObject2).curX) {
                    ((GCStep) localObject2).deactivate();
                    ((GCStep) localObject2).changeState();
                }
            }
            if ((localJGoObject instanceof GCTransition)) {
                localObject2 = (GCTransition) localJGoObject;
                ((GCTransition) localObject2).stop();
            }
            if ((localJGoObject instanceof ExceptionTransition)) {
                localObject2 = (ExceptionTransition) localJGoObject;
                ((ExceptionTransition) localObject2).stop();
            }
            if ((localJGoObject instanceof DigitalIn)) {
                localObject2 = (DigitalIn) localJGoObject;
                ((DigitalIn) localObject2).stop();
            }
            if ((localJGoObject instanceof AnalogIn)) {
                localObject2 = (AnalogIn) localJGoObject;
                ((AnalogIn) localObject2).stop();
            }
            if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep))) {
                localObject2 = (MacroStep) localJGoObject;
                ((MacroStep) localObject2).historySteps.clear();
                ((MacroStep) localObject2).myContentDocument.stopDocument();
                if (((MacroStep) localObject2).curX) {
                    ((MacroStep) localObject2).deactivate();
                    ((MacroStep) localObject2).changeState();
                }
            }
            if ((localJGoObject instanceof WorkspaceObject)) {
                localObject2 = (WorkspaceObject) localJGoObject;
                ((WorkspaceObject) localObject2).myContentDocument.stopDocument();
            }
            GCDocument localGCDocument;
            if ((localJGoObject instanceof XMLMessageIn)) {
                localObject2 = (XMLMessageIn) localJGoObject;
                ((XMLMessageIn) localObject2).myContentDocument.stopDocument();
                Iterator localIterator1 = ((XMLMessageIn) localObject2).getCalls().iterator();
                while (localIterator1.hasNext()) {
                    localGCDocument = (GCDocument) localIterator1.next();
                    localGCDocument.stopDocument();
                    try {
                        localGCDocument.frame.setClosed(true);
                    } catch (Exception localException2) {
                    }
                    localGCDocument.frame = null;
                    localGCDocument.proc.getCalls().remove(localGCDocument);
                }
            }
            if ((localJGoObject instanceof XMLMessageOut)) {
                localObject2 = (XMLMessageOut) localJGoObject;
                ((XMLMessageOut) localObject2).myContentDocument.stopDocument();
            }
            if (((localJGoObject instanceof ProcedureStep)) && (!(localJGoObject instanceof ProcessStep))) {
                localObject2 = (ProcedureStep) localJGoObject;
                if (((ProcedureStep) localObject2).myContentDocument != null) {
                    ((ProcedureStep) localObject2).myContentDocument.stopDocument();
                    try {
                        ((ProcedureStep) localObject2).frame.setClosed(true);
                    } catch (Exception localException1) {
                    }
                    ((ProcedureStep) localObject2).frame = null;
                    ((ProcedureStep) localObject2).view = null;
                    if (((ProcedureStep) localObject2).myContentDocument.proc != null) {
                        ((ProcedureStep) localObject2).myContentDocument.proc.getCalls().remove(((ProcedureStep) localObject2).myContentDocument);
                    }
                }
                ((ProcedureStep) localObject2).myContentDocument = null;
                if (((ProcedureStep) localObject2).curX) {
                    ((ProcedureStep) localObject2).deactivate();
                    ((ProcedureStep) localObject2).changeState();
                }
            }
            if ((localJGoObject instanceof ProcessStep)) {
                localObject2 = (ProcessStep) localJGoObject;
                Iterator localIterator2 = ((ProcessStep) localObject2).getCalls().iterator();
                while (localIterator2.hasNext()) {
                    localGCDocument = (GCDocument) localIterator2.next();
                    localGCDocument.stopDocument();
                    try {
                        localGCDocument.frame.setClosed(true);
                    } catch (Exception localException3) {
                    }
                    localGCDocument.frame = null;
                    localGCDocument.proc.getCalls().remove(localGCDocument);
                }
                ((ProcessStep) localObject2).getCalls().clear();
                if (((ProcessStep) localObject2).curX) {
                    ((ProcessStep) localObject2).deactivate();
                    ((ProcessStep) localObject2).changeState();
                }
            }
            if (((localJGoObject instanceof SFCPlotter)) && (((SFCPlotter) localJGoObject).running)) {
                ((SFCPlotter) localJGoObject).plotter.stopThread();
                ((SFCPlotter) localJGoObject).running = false;
            }
            var = getNextObjectPos((JGoListPosition) var);
        }
    }

    public synchronized void start() {
        this.executing = true;
        this.grafcetThread = new GrafcetThread();
        this.grafcetThread.start();
    }

    public synchronized void shutdownSocket() {
        if (isSocketActive()) {
            if ((this.isSocketServer) && (this.socketServer != null)) {
                this.socketServer.shutdown();
                this.socketServer = null;
            } else if ((!this.isSocketServer) && (this.isConnected)) {
                this.isConnected = false;
                try {
                    this.socket.close();
                } catch (Exception localException) {
                    Utils.writeException(localException, "Close socket failed.");
                }
                this.socket = null;
            }
        }
    }

    private synchronized boolean isUsingSockets() {
        return ((this.isSocketServer) || (!this.host.isEmpty())) && (this.port != -1);
    }

    public synchronized boolean isSocketActive() {
        return ((this.isSocketServer) && (this.socketServer != null)) || ((!this.isSocketServer) && (this.isConnected));
    }

    public SocketOut.SendMode getSendMode() {
        if (this.socketSendMode != SocketOut.SendMode.Inherit) {
            return this.socketSendMode;
        }
        return getOwner().getDocument().getSendMode();
    }

    public boolean connect() {
        this.in = null;
        this.out = null;
        int i = 0;
        while (i == 0) {
            try {
                this.socket = new Socket(this.host, this.port);
                i = 1;
                try {
                    this.out = new PrintWriter(this.socket.getOutputStream(), true);
                } catch (Exception localException1) {
                    Utils.writeException(localException1, "Could not open output stream.");
                    break;
                }
                try {
                    this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                } catch (Exception localException2) {
                    this.out = null;
                    Utils.writeException(localException2, "Could not open input stream.");
                    break;
                }
            } catch (Exception localException3) {
                String str = "Application \"" + getFullName() + "\" could not connect to \"" + this.host + "\" on port " + this.port + ".";
                Utils.writeError(str);
                String[] arrayOfString = {"Retry", "Ignore"};
                int j = JOptionPane.showOptionDialog(Editor.singleton, str, "Socket I/O Error", 0, 0, null, arrayOfString, arrayOfString[0]);
                if (j == 0) {
                    Utils.writeInformation("User chose to retry.");
                } else {
                    Utils.writeInformation("User chose to ignore.");
                    break;
                }
            }
        }
        return this.in != null;
    }

    public void socketSend(String paramString1, String paramString2, boolean paramBoolean) {
        GCDocument localGCDocument = getApplication();
        synchronized (localGCDocument) {
            if (localGCDocument.isSocketServer) {
                if (localGCDocument.socketServer != null) {
                    localGCDocument.socketServer.send(paramString1, paramString2);
                } else {
                    Utils.writeError("SocketServer not running. Not sending message '" + paramString1 + "|" + paramString2 + "'");
                }
            } else if (localGCDocument.isConnected) {
                PrintWriter localPrintWriter = localGCDocument.out;
                if (localPrintWriter != null) {
                    if (!paramBoolean) {
                        localPrintWriter.println(SocketProtocol.create(paramString1, paramString2));
                    } else {
                        localPrintWriter.println(ProcelProtocol.create(paramString1, paramString2));
                    }
                }
            }
        }
    }

    public GCDocument getApplication() {
        GCDocument localGCDocument;
        for (localGCDocument = this; localGCDocument.owner != null; localGCDocument = localGCDocument.owner.getDocument()) {
        }
        return localGCDocument;
    }

    public PrintWriter getOutSocket() {
        return getApplication().out;
    }

    public void stopThread() {
        this.executing = false;
        if ((this.grafcetThread != null) && (this.grafcetThread != Thread.currentThread())) {
            this.grafcetThread.interrupt();
            while (this.grafcetThread.isAlive()) {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException localInterruptedException) {
                }
            }
        }
        this.grafcetThread = null;
        if (this.proc != null) {
            synchronized (this.proc) {
                this.proc.getCalls().remove(this);
            }
        }
        if (this.processStepCall != null) {
            int i = this.processStepCall.getProcedureCalls().indexOf(this);
            if (i != -1) {
                ((GCDocument) this.processStepCall.getProcedureCalls().get(i)).markForRemoval = true;
            }
        }
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof StepFusionSet)) {
                StepFusionSet localStepFusionSet = (StepFusionSet) localJGoObject;
                Iterator localIterator = localStepFusionSet.steps.iterator();
                while (localIterator.hasNext()) {
                    GrafcetObject localGrafcetObject = (GrafcetObject) localIterator.next();
                    int j = localGrafcetObject.fusionSets.indexOf(localStepFusionSet);
                    if (j != -1) {
                        localGrafcetObject.fusionSets.remove(j);
                    }
                }
                localStepFusionSet.steps.clear();
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
        if (this.frame != null) {
            try {
                this.frame.setClosed(true);
            } catch (Exception localException) {
            }
            this.frame = null;
        }
    }

    public synchronized void stepThread() {
        if (this.grafcetThread != null) {
            synchronized (this.grafcetThread) {
                this.grafcetThread.notifyAll();
            }
        }
    }

    public synchronized void pauseThread() {
        this.paused = true;
    }

    public synchronized void resumeThread() {
        this.paused = false;
        if (this.grafcetThread != null) {
            synchronized (this.grafcetThread) {
                this.grafcetThread.notifyAll();
            }
        }
    }

    public void setProcedure(GrafcetProcedure paramGrafcetProcedure) {
        this.proc = paramGrafcetProcedure;
    }

    public void addInput(Referencable paramReferencable) {
        this.inputs.add(paramReferencable);
    }

    public void addNonInput(Readable paramReadable) {
        this.nonInputs.add(paramReadable);
    }

    public void removeInput(Readable paramReadable) {
        for (int i = this.inputs.indexOf(paramReadable); i != -1; i = this.inputs.indexOf(paramReadable)) {
            this.inputs.remove(i);
        }
    }

    public void sortInputs() {
        Collections.sort(this.inputs, new Comparator() {
            public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2) {
                int i = 0;
                if ((paramAnonymousObject2 instanceof InternalVariable)) {
                    i = -1;
                } else {
                    i = 1;
                }
                return i;
            }
        });
        Collections.sort(this.inputs, new Comparator() {
            public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2) {
                int i = 0;
                if (((paramAnonymousObject1 instanceof InternalVariable)) && ((paramAnonymousObject2 instanceof InternalVariable))) {
                    InternalVariable localInternalVariable1 = (InternalVariable) paramAnonymousObject1;
                    InternalVariable localInternalVariable2 = (InternalVariable) paramAnonymousObject2;
                    if ((localInternalVariable1.references(localInternalVariable2)) && (localInternalVariable2.references(localInternalVariable1))) {
                        Utils.writeWarning("Cyclic dependency between \"" + localInternalVariable1.getFullName() + "\" and \"" + localInternalVariable2.getFullName() + "\"");
                    }
                    if ((localInternalVariable1.references(localInternalVariable2)) && (!localInternalVariable2.references(localInternalVariable1))) {
                        i = 1;
                    } else if ((localInternalVariable2.references(localInternalVariable1)) && (!localInternalVariable1.references(localInternalVariable2))) {
                        i = -1;
                    }
                }
                return i;
            }
        });
    }

    public void setLocation(int paramInt1, int paramInt2) {
    }

    public Point getLocation() {
        return null;
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public void setWidth(int paramInt) {
    }

    public void setHeight(int paramInt) {
    }

    public void setVisible(boolean paramBoolean) {
    }

    public void switchUndoManager(boolean paramBoolean) {
        setSkipsUndoManager(paramBoolean);
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            Object localObject;
            if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep))) {
                localObject = (MacroStep) localJGoObject;
                ((MacroStep) localObject).myContentDocument.switchUndoManager(paramBoolean);
            }
            if ((localJGoObject instanceof WorkspaceObject)) {
                localObject = (WorkspaceObject) localJGoObject;
                ((WorkspaceObject) localObject).myContentDocument.switchUndoManager(paramBoolean);
            }
            if ((localJGoObject instanceof XMLMessageIn)) {
                localObject = (XMLMessageIn) localJGoObject;
                ((XMLMessageIn) localObject).myContentDocument.switchUndoManager(paramBoolean);
            }
            if ((localJGoObject instanceof XMLMessageOut)) {
                localObject = (XMLMessageOut) localJGoObject;
                ((XMLMessageOut) localObject).myContentDocument.switchUndoManager(paramBoolean);
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
    }

    public int getTickTime() {
        if ((this.owner instanceof GrafcetProcedure)) {
            return getSpeed();
        }
        if ((this.owner instanceof Workspace)) {
            return ((Workspace) this.owner).getTickTime();
        }
        if (this.owner != null) {
            return this.owner.getDocument().getTickTime();
        }
        return getSpeed();
    }

    public void changeIcon(boolean paramBoolean) {
        if (paramBoolean) {
            if ((this.executing) && (getView() != null) && (getView().getInternalFrame() != null)) {
                getView().getInternalFrame().setFrameIcon(runIcon);
            }
        } else if ((this.executing) && (getView() != null) && (getView().getInternalFrame() != null)) {
            if (isModifiable()) {
                getView().getInternalFrame().setFrameIcon(defaultIcon);
            } else {
                getView().getInternalFrame().setFrameIcon(lockIcon);
            }
        }
        JGoListPosition localJGoListPosition = getFirstObjectPos();
        for (JGoObject localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
            Object localObject;
            if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep))) {
                localObject = (MacroStep) localJGoObject;
                ((MacroStep) localObject).myContentDocument.changeIcon(paramBoolean);
            }
            if ((localJGoObject instanceof WorkspaceObject)) {
                localObject = (WorkspaceObject) localJGoObject;
                if (((WorkspaceObject) localObject).enabled) {
                    ((WorkspaceObject) localObject).myContentDocument.changeIcon(paramBoolean);
                }
            }
            if ((localJGoObject instanceof XMLMessageIn)) {
                localObject = (XMLMessageIn) localJGoObject;
                ((XMLMessageIn) localObject).myContentDocument.changeIcon(paramBoolean);
            }
            if ((localJGoObject instanceof XMLMessageOut)) {
                localObject = (XMLMessageOut) localJGoObject;
                ((XMLMessageOut) localObject).myContentDocument.changeIcon(paramBoolean);
            }
            localJGoListPosition = getNextObjectPos(localJGoListPosition);
        }
    }

    public void setModificationProperty(boolean paramBoolean) {
        JGoListPosition localJGoListPosition;
        JGoObject localJGoObject;
        Object localObject;
        if ((isModifiable()) && (!paramBoolean)) {
            setModifiable(false);
            if ((getView() != null) && (getView().getInternalFrame() != null)) {
                getView().getInternalFrame().setResizable(false);
                getView().getInternalFrame().setMaximizable(false);
                getView().getInternalFrame().setFrameIcon(lockIcon);
            }
            this.modificationProperty = false;
            localJGoListPosition = getFirstObjectPos();
            for (localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
                if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep))) {
                    localObject = (MacroStep) localJGoObject;
                    ((MacroStep) localObject).myContentDocument.setModificationProperty(false);
                }
                if ((localJGoObject instanceof WorkspaceObject)) {
                    localObject = (WorkspaceObject) localJGoObject;
                    ((WorkspaceObject) localObject).myContentDocument.setModificationProperty(false);
                }
                if ((localJGoObject instanceof XMLMessageIn)) {
                    localObject = (XMLMessageIn) localJGoObject;
                    ((XMLMessageIn) localObject).myContentDocument.setModificationProperty(false);
                }
                if ((localJGoObject instanceof XMLMessageOut)) {
                    localObject = (XMLMessageOut) localJGoObject;
                    ((XMLMessageOut) localObject).myContentDocument.setModificationProperty(false);
                }
                localJGoListPosition = getNextObjectPos(localJGoListPosition);
            }
            return;
        }
        if ((!isModifiable()) && (paramBoolean)) {
            setModifiable(true);
            if ((getView() != null) && (getView().getInternalFrame() != null)) {
                getView().getInternalFrame().setResizable(true);
                getView().getInternalFrame().setMaximizable(true);
                getView().getInternalFrame().setFrameIcon(defaultIcon);
            }
            this.modificationProperty = true;
            localJGoListPosition = getFirstObjectPos();
            for (localJGoObject = getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = getObjectAtPos(localJGoListPosition)) {
                if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep))) {
                    localObject = (MacroStep) localJGoObject;
                    ((MacroStep) localObject).myContentDocument.setModificationProperty(true);
                }
                if ((localJGoObject instanceof WorkspaceObject)) {
                    localObject = (WorkspaceObject) localJGoObject;
                    ((WorkspaceObject) localObject).myContentDocument.setModificationProperty(true);
                }
                if ((localJGoObject instanceof XMLMessageIn)) {
                    localObject = (XMLMessageIn) localJGoObject;
                    ((XMLMessageIn) localObject).myContentDocument.setModificationProperty(true);
                }
                if ((localJGoObject instanceof XMLMessageOut)) {
                    localObject = (XMLMessageOut) localJGoObject;
                    ((XMLMessageOut) localObject).myContentDocument.setModificationProperty(true);
                }
                localJGoListPosition = getNextObjectPos(localJGoListPosition);
            }
            return;
        }
    }

    public void newObject(String paramString1, String paramString2, int paramInt1, int paramInt2) {
        Object localObject;
        if (paramString1.equals("Rectangle")) {
            localObject = new GCRectangle(new Point(paramInt1, paramInt2), new Dimension(50, 50));
            ((GCRectangle) localObject).setName(paramString2);
            ((GCRectangle) localObject).setBrush(JGoBrush.makeStockBrush(Color.lightGray));
            addObjectAtTail((JGoObject) localObject);
            return;
        }
        if (paramString1.equals("Ellipse")) {
            localObject = new GCEllipse(new Point(paramInt1, paramInt2), new Dimension(50, 50));
            ((GCEllipse) localObject).setName(paramString2);
            ((GCEllipse) localObject).setBrush(JGoBrush.makeStockBrush(Color.lightGray));
            addObjectAtTail((JGoObject) localObject);
            return;
        }
    }

    public GCTransition createTransition(int paramInt1, int paramInt2, String paramString) {
        GCTransition localGCTransition = new GCTransition(new Point(paramInt1, paramInt2), paramString, "");
        addObjectAtTail(localGCTransition);
        return localGCTransition;
    }

    public GCStep createStep(int paramInt1, int paramInt2, String paramString1, String paramString2) {
        GCStep localGCStep = new GCStep(new Point(paramInt1, paramInt2), paramString1);
        addObjectAtTail(localGCStep);
        localGCStep.setActionText(paramString2);
        return localGCStep;
    }

    public GCStepInitial createInitialStep(int paramInt1, int paramInt2, String paramString1, String paramString2) {
        GCStepInitial localGCStepInitial = new GCStepInitial(new Point(paramInt1, paramInt2), paramString1);
        addObjectAtTail(localGCStepInitial);
        localGCStepInitial.setActionText(paramString2);
        return localGCStepInitial;
    }

    public IntegerVariable createIntegerVariable(int paramInt1, int paramInt2, String paramString1, String paramString2) {
        IntegerVariable localIntegerVariable = new IntegerVariable(new Point(paramInt1, paramInt2));
        try {
            localIntegerVariable.setInitialValue(paramString2);
        } catch (IllegalValueException localIllegalValueException) {
            Utils.writeWarning("GCDocument.createIntegerVariable - Illegal initialValue");
        }
        localIntegerVariable.myName.setText(paramString1);
        addObjectAtTail(localIntegerVariable);
        return localIntegerVariable;
    }

    public BooleanVariable createBooleanVariable(int paramInt1, int paramInt2, String paramString1, String paramString2) {
        BooleanVariable localBooleanVariable = new BooleanVariable(new Point(paramInt1, paramInt2));
        try {
            localBooleanVariable.setInitialValue(paramString2);
        } catch (IllegalValueException localIllegalValueException) {
            Utils.writeWarning("GCDocument.createBooleanVariable - Illegal initialValue");
        }
        localBooleanVariable.myName.setText(paramString1);
        addObjectAtTail(localBooleanVariable);
        return localBooleanVariable;
    }

    public StringVariable createStringVariable(int paramInt1, int paramInt2, String paramString1, String paramString2) {
        StringVariable localStringVariable = new StringVariable(new Point(paramInt1, paramInt2));
        try {
            localStringVariable.setInitialValue(paramString2);
        } catch (IllegalValueException localIllegalValueException) {
            Utils.writeWarning("GCDocument.createStringVariable - Illegal initialValue");
        }
        localStringVariable.myName.setText(paramString1);
        addObjectAtTail(localStringVariable);
        return localStringVariable;
    }

    public BooleanList createBooleanList(int paramInt1, int paramInt2, String paramString1, String paramString2) {
        BooleanList localBooleanList = new BooleanList(new Point(paramInt1, paramInt2));
        localBooleanList.setInitialValues(paramString2);
        localBooleanList.setName(paramString1);
        addObjectAtTail(localBooleanList);
        return localBooleanList;
    }

    public WorkspaceObject createWorkspaceObject(int paramInt1, int paramInt2, String paramString) {
        WorkspaceObject localWorkspaceObject = new WorkspaceObject(new Point(paramInt1, paramInt2), paramString);
        addObjectAtTail(localWorkspaceObject);
        return localWorkspaceObject;
    }

    public void connect(GrafcetObject paramGrafcetObject1, GrafcetObject paramGrafcetObject2) {
        Object localObject1 = null;
        Object localObject2 = null;
        if ((paramGrafcetObject1 instanceof GCStep)) {
            localObject1 = ((GCStep) paramGrafcetObject1).getOutPort();
        }
        if ((paramGrafcetObject1 instanceof GCTransition)) {
            localObject1 = ((GCTransition) paramGrafcetObject1).getOutPort();
        }
        if ((paramGrafcetObject2 instanceof GCStep)) {
            localObject2 = ((GCStep) paramGrafcetObject2).getInPort();
        }
        if ((paramGrafcetObject2 instanceof GCTransition)) {
            localObject2 = ((GCTransition) paramGrafcetObject2).getInPort();
        }
        if ((localObject1 != null) && (localObject2 != null)) {
            GCLink localGCLink = new GCLink((JGoPort) localObject1, (JGoPort) localObject2);
            addObjectAtHead(localGCLink);
        } else {
            Utils.writeInternalError("Ports = null");
        }
    }

    public void setFrameRectangle(Rectangle paramRectangle) {
        getView().getInternalFrame().setBounds(paramRectangle);
    }

    public void setWorkspaceName(String paramString) {
        setName(paramString);
        getView().updateTitle();
    }

    public String toString() {
        return getName();
    }

    public void sampleAll() {
        synchronized (this.nonInputs) {
            Iterator localIterator = this.nonInputs.iterator();
            while (localIterator.hasNext()) {
                Readable localReadable = (Readable) localIterator.next();
                if ((localReadable instanceof DigitalIn)) {
                    ((DigitalIn) localReadable).readInput();
                }
                if ((localReadable instanceof AnalogIn)) {
                    ((AnalogIn) localReadable).readInput();
                }
            }
        }
    }

    public JGoCopyEnvironment copyFromCollection(JGoObjectSimpleCollection paramJGoObjectSimpleCollection) {
        JGoCopyEnvironment localJGoCopyEnvironment = super.copyFromCollection(paramJGoObjectSimpleCollection);
        if ((paramJGoObjectSimpleCollection instanceof GCDocument)) {
            GCDocument localGCDocument = (GCDocument) paramJGoObjectSimpleCollection;
            if (localGCDocument.owner != null) {
                this.socketSendMode = localGCDocument.socketSendMode;
            }
        }
        return localJGoCopyEnvironment;
    }      

    private class SocketReader
            extends Thread {

        private SocketReader() {
        }

        public boolean isSocketProtocol(String paramString) {
            return (!paramString.startsWith("<evaluate_r>")) && (!paramString.startsWith("<executer>"));
        }

        public boolean isMoppMessage(String paramString) {
            return paramString.startsWith("<BatchList");
        }

        public void decodeProcelMessage(String paramString) {
            int i;
            while ((i = paramString.indexOf("<var>")) != -1) {
                int j = paramString.indexOf("</var>");
                String str = paramString.substring(i + 5, j);
                paramString = paramString.substring(j + 5);
                decodeProcelVariable(str);
            }
        }

        public void decodeProcelVariable(String paramString) {
            String str1 = ProcelProtocol.getTag(paramString);
            String str2 = ProcelProtocol.getValue(paramString);
            SocketIn.setSocketIn(str1, str2);
        }

        public void run() {
            String str1 = null;
            String str2 = null;
            String str3 = null;
            try {
                for (;;) {
                    str3 = GCDocument.this.in.readLine();
                    if (str3 == null) {
                        Utils.writeWarning("Socket connection closed by server.");
                        break;
                    }
                    if (isMoppMessage(str3)) {
                        Editor.mockUpdate(str3);
                    } else if (isSocketProtocol(str3)) {
                        str1 = SocketProtocol.getTag(str3);
                        str2 = SocketProtocol.getValue(str3);
                        SocketIn.setSocketIn(str1, str2);
                    } else {
                        decodeProcelMessage(str3);
                    }
                }
            } catch (SocketException localSocketException) {
            } catch (Exception localException1) {
                Utils.writeException(localException1);
                try {
                    GCDocument.this.socket.close();
                } catch (Exception localException2) {
                }
            }
            GCDocument.this.isConnected = false;
            GCDocument.this.socket = null;
            GCDocument.this.in = null;
            GCDocument.this.out = null;
            Editor.updateActions();
        }
    }

    private class MySocketServer
            implements ClientListener, JGSocketReceiver {

        private ArrayList<Client> clients = new ArrayList();
        private SocketServer server;

        public MySocketServer(int paramInt)
                throws IOException {
            this.server = new SocketServer(paramInt, this, this);
            this.server.start();
        }

        public synchronized void shutdown() {
            this.server.shutdown();
            while (this.clients.size() > 0) {
                ((Client) this.clients.get(0)).disconnect();
            }
        }

        public synchronized void send(String paramString1, String paramString2) {
            if (this.clients.isEmpty()) {
                Utils.writeWarning("No socket clients connected. Message not sent: '" + paramString1 + "|" + paramString2 + "'");
            }
            Iterator localIterator = this.clients.iterator();
            while (localIterator.hasNext()) {
                Client localClient = (Client) localIterator.next();
                localClient.send(paramString1, paramString2);
            }
        }

        public synchronized void clientConnected(Client paramClient) {
            this.clients.add(paramClient);
        }

        public synchronized void clientDisconnected(Client paramClient) {
            this.clients.remove(paramClient);
        }

        public void receive(Client paramClient, String paramString1, String paramString2) {
            SocketIn.setSocketIn(paramString1, paramString2);
        }
    }

    private class BurstProfile_GrafcetThread
            extends Thread {

        private BurstProfile_GrafcetThread() {
        }

        public void run() {
            long l1 = 0L;
            long l2 = 0L;
            GCDocument.this.executeOnce();
            long l3 = System.currentTimeMillis();
            long l4 = System.nanoTime();
            while ((!isInterrupted()) && (l1 < 1000000L)) {
                GCDocument.this.realTime += GCDocument.this.getSpeed() / 1000.0D;
                GCDocument.this.executeOnce();
                l1 += 1L;
            }
            l2 += System.nanoTime() - l4;
            System.out.println("Execution times:             " + l1);
            System.out.println("Total execution time (ns):   " + l2);
            System.out.println("Average execution time (ns): " + l2 / l1);
        }
    }

    private class Profile_GrafcetThread
            extends Thread {

        private Profile_GrafcetThread() {
        }

        public void run() {
            long l1 = 0L;
            long l2 = 0L;
            long l3 = System.currentTimeMillis();
            while (!isInterrupted()) {
                int i = GCDocument.this.getSpeed();
                GCDocument.this.realTime += i / 1000.0D;
                long l5 = System.nanoTime();
                GCDocument.this.executeOnce();
                l2 += System.nanoTime() - l5;
                l1 += 1L;
                l3 += i;
                long l4 = l3 - System.currentTimeMillis();
                if (l4 > 0L) {
                    try {
                        sleep(l4);
                    } catch (InterruptedException localInterruptedException) {
                    }
                } else if (l4 < 0L) {
                    Utils.writeWarning("Overrun");
                }
            }
            System.out.println("Execution times:             " + l1);
            System.out.println("Total execution time (ns):   " + l2);
            System.out.println("Average execution time (ns): " + l2 / l1);
        }
    }

    private class GrafcetThread
            extends Thread {

        private GrafcetThread() {
        }

        public void run() {
            int i = 0;
            long l1 = System.currentTimeMillis();
            try {
                while (!isInterrupted()) {
                    int j = GCDocument.this.getSpeed();
                    GCDocument.this.realTime += j / 1000.0D;
                    try {
                        GCDocument.this.executeOnce();
                    } catch (Exception localException) {
                        Utils.writeException(localException, "Incomplete scan cycle execution.");
                    }
                    boolean bool;
                    synchronized (this) {
                        bool = GCDocument.this.paused;
                    }
                    if (bool) {
                        synchronized (this) {
                            wait();
                            l1 = System.currentTimeMillis();
                        }
                    } else {
                        l1 += j;
                        long l2 = l1 - System.currentTimeMillis();
                        if (l2 > 0L) {
                            if (i > 0) {
                                Utils.writeWarning("Overruns (consecutive): " + i);
                            }
                            i = 0;
                            sleep(l2);
                        } else if ((l2 < 0L) && (!Editor.debugMode)) {
                            if (i == 0) {
                                if (j + l2 < 0L) {
                                    Utils.writeWarning("Overruns (consecutive)...");
                                    i++;
                                } else {
                                    Utils.writeWarning("Overrun");
                                }
                            } else {
                                i++;
                            }
                        }
                    }
                }
            } catch (InterruptedException localInterruptedException) {
            }
        }
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCDocument.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
