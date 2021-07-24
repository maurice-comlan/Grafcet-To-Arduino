package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoLink;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.variables.Variable;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;
import org.w3c.dom.Element;

public class GCTransition
        extends GenericTransition
        implements GCIdent, Connectable, Helpable {

    private String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
    private JGoRectangle myRectangle = null;
    private JGoStroke myInline = null;
    private JGoStroke myOutline = null;
    private GCTransitionInPort myInPort = null;
    private GCTransitionOutPort myOutPort = null;
    private JGoText myPriority = null;
    public ArrayList<GrafcetObject> succeedingSteps = new ArrayList();
    private boolean condition = false;
    private boolean oldCondition = false;
    private static JGoBrush redSolidBrush = new JGoBrush(65535, new Color(1.0F, 0.0F, 0.0F));
    private static JGoBrush greenSolidBrush = new JGoBrush(65535, new Color(0.0F, 1.0F, 0.0F));

    public GCTransition() {
    }

    public GCTransition(Point paramPoint, String paramString1, String paramString2) {
        this();
        setSelectable(true);
        setGrabChildSelection(false);
        setDraggable(true);
        setResizable(true);
        this.conditionString = paramString1;
        this.myRectangle = new JGoRectangle();
        this.myRectangle.setSize(30, 5);
        this.myRectangle.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
        this.myRectangle.setSelectable(false);
        this.myRectangle.setDraggable(false);
        this.myCondition = new JGoText(paramString1);
        this.myCondition.setSelectable(true);
        this.myCondition.setEditable(false);
        this.myCondition.setEditOnSingleClick(false);
        this.myCondition.setDraggable(false);
        this.myCondition.setAlignment(1);
        this.myCondition.setAutoResize(true);
        this.myCondition.setClipping(false);
        this.myCondition.setTransparent(true);
        this.myCondition.setBold(true);
        this.myCondition.setFontSize(18);
        this.myCondition.setFaceName("Monospaced");
        this.myPriority = new JGoText(paramString2);
        this.myPriority.setSelectable(false);
        this.myPriority.setEditable(false);
        this.myPriority.setEditOnSingleClick(false);
        this.myPriority.setDraggable(false);
        this.myPriority.setAlignment(1);
        this.myPriority.setAutoResize(true);
        this.myPriority.setClipping(false);
        this.myPriority.setTransparent(true);
        this.myPriority.setBold(true);
        this.myPriority.setFontSize(12);
        this.myPriority.setFaceName("Monospaced");
        this.myInline = new JGoStroke();
        this.myInline.addPoint(10, 0);
        this.myInline.addPoint(10, 10);
        this.myInline.setSelectable(false);
        this.myOutline = new JGoStroke();
        this.myOutline.addPoint(20, 0);
        this.myOutline.addPoint(20, 10);
        this.myOutline.setSelectable(false);
        this.myInPort = new GCTransitionInPort();
        this.myOutPort = new GCTransitionOutPort();
        addObjectAtHead(this.myRectangle);
        addObjectAtTail(this.myCondition);
        addObjectAtTail(this.myInline);
        addObjectAtTail(this.myOutline);
        addObjectAtTail(this.myInPort);
        addObjectAtTail(this.myOutPort);
        setLocation(paramPoint);
    }

    public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment) {
        GCTransition localGCTransition = (GCTransition) paramJGoArea;
        localGCTransition.condition = this.condition;
        localGCTransition.myRectangle = ((JGoRectangle) paramJGoCopyEnvironment.copy(this.myRectangle));
        localGCTransition.myCondition = ((JGoText) paramJGoCopyEnvironment.copy(this.myCondition));
        localGCTransition.myPriority = ((JGoText) paramJGoCopyEnvironment.copy(this.myPriority));
        localGCTransition.myInline = ((JGoStroke) paramJGoCopyEnvironment.copy(this.myInline));
        localGCTransition.myOutline = ((JGoStroke) paramJGoCopyEnvironment.copy(this.myOutline));
        localGCTransition.myInPort = ((GCTransitionInPort) paramJGoCopyEnvironment.copy(this.myInPort));
        localGCTransition.myOutPort = ((GCTransitionOutPort) paramJGoCopyEnvironment.copy(this.myOutPort));
        localGCTransition.addObjectAtHead(localGCTransition.myRectangle);
        localGCTransition.addObjectAtTail(localGCTransition.myCondition);
        localGCTransition.addObjectAtTail(localGCTransition.myPriority);
        localGCTransition.addObjectAtTail(localGCTransition.myInline);
        localGCTransition.addObjectAtTail(localGCTransition.myOutline);
        localGCTransition.addObjectAtTail(localGCTransition.myInPort);
        localGCTransition.addObjectAtTail(localGCTransition.myOutPort);
        localGCTransition.conditionString = this.conditionString;
        localGCTransition.conditionVisible = this.conditionVisible;
        if (!this.conditionVisible) {
            localGCTransition.hideCondition();
        }
        localGCTransition.priority = this.priority;
        localGCTransition.hasPriority = this.hasPriority;
        if (!this.hasPriority) {
            localGCTransition.hidePriority();
        }
        localGCTransition.conditionValue = this.conditionValue;
        localGCTransition.conditionVariable = this.conditionVariable;
    }

    public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2) {
        paramVector1.add(this.id);
        paramVector2.add(this);
        paramElement.setAttribute("id", this.id);
        paramElement.setAttribute("actionText", this.conditionString); //Maintenue pour la retro-compatibilité
        XMLUtil.setBool(paramElement, "conditionVisible", this.conditionVisible);
        if (this.hasPriority) {
            XMLUtil.setInt(paramElement, "priority", this.priority);
            removeObject(this.myPriority);
        }
        if (this.conditionVisible) {
            removeObject(this.myCondition);
        }
        XMLUtil.saveBoundingRect(paramElement, this);
        if (this.conditionVisible) {
            addObjectAtTail(this.myCondition);
        }
        if (this.hasPriority) {
            addObjectAtTail(this.myPriority);
        }

        // A déplacer dans GenericTransition en son temps 
        XMLUtil.setBool(paramElement, "conditionValue", this.conditionValue);

        if (this.conditionVariable == null) {
            paramElement.setAttribute("conditionVariable", "0");
        } else {
            int i = paramVector2.indexOf(this.conditionVariable);
            if ((i >= 0) && (i < paramVector1.size())) {
                String varId = (String) paramVector1.get(i);
                if (varId != null) {
                    paramElement.setAttribute("conditionVariable", varId);
                }
            } else {
                System.out.println("variable non retouvé Transition");
                //Variable non définit referencé
            }
        }

        return paramElement;
    }

    public static GCTransition loadXML(Element paramElement, Vector paramVector1, Vector paramVector2) {
        GCTransition transition = new GCTransition(new Point(), "", "");        
        String str = paramElement.getAttribute("id");
        if (Utils.getSaveVersion(paramElement) >= 2) {
            transition.id = str;
        }
        paramVector1.add(str);
        paramVector2.add(transition);
        if (paramElement.hasAttribute("priority")) {
            transition.priority = Integer.parseInt(paramElement.getAttribute("priority"));
            transition.hasPriority = true;
            transition.showPriority();
        } else {
            transition.hidePriority();
        }
        if (XMLUtil.getBool(paramElement, "conditionVisible", true)) {
            transition.showCondition();
        } else {
            transition.hideCondition();
        }
        if (transition.conditionVisible) {
            transition.removeObject(transition.myCondition);
        }
        if (transition.hasPriority) {
            transition.removeObject(transition.myPriority);
        }
        XMLUtil.restoreBoundingRectAny(paramElement, transition);
        if (transition.conditionVisible) {
            transition.addObjectAtTail(transition.myCondition);
        }
        if (transition.hasPriority) {
            transition.addObjectAtTail(transition.myPriority);
        }
        
        transition.conditionValue  = XMLUtil.getBool(paramElement, "conditionValue", true);        
        Variable var = null;
        String varId = paramElement.getAttribute("conditionVariable");
        if(!varId.equals("0")){
            int k = paramVector1.indexOf(varId);        
            if ((k >= 0) && (k < paramVector2.size())) {
                var = (Variable) paramVector2.get(k);
            }
        }
        transition.conditionVariable = var;        
        transition.setCondition();
        return transition;
    }

    public Point getLocation(Point paramPoint) {
        return this.myRectangle.getSpotLocation(2, paramPoint);
    }

    public void setLocation(int paramInt1, int paramInt2) {
        this.myRectangle.setSpotLocation(2, paramInt1, paramInt2);
        layoutChildren();
    }

    public void layoutChildren() {
        Point localPoint1 = this.myRectangle.getSpotLocation(4);
        Point localPoint2 = this.myRectangle.getSpotLocation(1);
        this.myCondition.setSpotLocation(8, localPoint1.x + 8, localPoint1.y);
        this.myPriority.setSpotLocation(4, localPoint2.x - 4, localPoint2.y);
        this.myInline.setSpotLocation(6, this.myRectangle, 2);
        this.myOutline.setSpotLocation(2, this.myRectangle, 6);
        this.myInPort.setSpotLocation(2, this.myInline, 2);
        this.myOutPort.setSpotLocation(6, this.myOutline, 6);
    }

    public JGoObject[] getGeometryChangeObjects() {
        return new JGoObject[]{this.myRectangle, this.myInPort, this.myOutPort, this.myInline, this.myOutline};
    }

    public int getNoScaleRight(Rectangle paramRectangle) {
        return paramRectangle.x + paramRectangle.width - (this.myRectangle.getLeft() + this.myRectangle.getWidth());
    }

    public Dimension getMinimumSize() {
        if (this.conditionVisible) {
            int i = (int) Math.ceil(this.myCondition.getWidth() + 20);
            int j = Math.max(15, (int) Math.ceil(this.myCondition.getHeight()));
            return new Dimension(i, j);
        }
        return new Dimension(15, 15);
    }

    public Vector getAllLinks() {
        Vector localVector = new Vector();
        Utils.addLinks(this.myInPort, localVector);
        Utils.addLinks(this.myOutPort, localVector);
        return localVector;
    }

    public boolean isTransition() {
        return true;
    }

    public boolean isStep() {
        return false;
    }

    public void addSucceedingStep(GrafcetObject paramGrafcetObject) {
        System.out.println("grafchart.sfc.GCTransition.addPrecedingStep() - 279");
        if(paramGrafcetObject instanceof MacroStep)
            return;
        this.succeedingSteps.add(paramGrafcetObject);
    }

    public void addPrecedingStep(GrafcetObject paramGrafcetObject) {
        System.out.println("grafchart.sfc.GCTransition.addPrecedingStep() - 283");
        if(paramGrafcetObject instanceof MacroStep)
            return;
        this.precedingSteps.add(paramGrafcetObject);
    }

    @Override
    public void addExitStep(MacroStep paramMacroStep, JGoPort paramJGoPort) {
        int i = 0;
        int j = 0;
        Object localObject1 = paramMacroStep.outputPorts.iterator();
        while ((j == 0) && (((Iterator) localObject1).hasNext())) {
            Output localObject2 = (Output) ((Iterator) localObject1).next();
            i++;
            if (((Output) localObject2).port == paramJGoPort) {
                j = 1;
            }
        }
        localObject1 = new ArrayList();
        Object localObject2 = paramMacroStep.myContentDocument;
        JGoListPosition localJGoListPosition = ((GCDocument) localObject2).getFirstObjectPos();
        for (JGoObject localJGoObject = ((GCDocument) localObject2).getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = ((GCDocument) localObject2).getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof ExitStep)) {
                ((ArrayList) localObject1).add(localJGoObject);
            }
            localJGoListPosition = ((GCDocument) localObject2).getNextObjectPos(localJGoListPosition);
        }
        Collections.sort((List) localObject1, new Comparator() {
            public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2) {
                return (int) (((JGoObject) paramAnonymousObject1).getLocation().getX() - ((JGoObject) paramAnonymousObject2).getLocation().getX());
            }
        });
        ExitStep localExitStep = (ExitStep) ((ArrayList) localObject1).remove(i - 1);
        addPrecedingStep(localExitStep);
    }

    public void addEnterStep(MacroStep paramMacroStep, JGoPort paramJGoPort) {
        int i = 0;
        int j = 0;
        Object localObject1 = paramMacroStep.inputPorts.iterator();
        while ((j == 0) && (((Iterator) localObject1).hasNext())) {
            Input localObject2 = (Input) ((Iterator) localObject1).next();
            i++;
            if (((Input) localObject2).port == paramJGoPort) {
                j = 1;
            }
        }
        localObject1 = new ArrayList();
        Object localObject2 = paramMacroStep.myContentDocument;
        JGoListPosition localJGoListPosition = ((GCDocument) localObject2).getFirstObjectPos();
        for (JGoObject localJGoObject = ((GCDocument) localObject2).getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = ((GCDocument) localObject2).getObjectAtPos(localJGoListPosition)) {
            if ((localJGoObject instanceof EnterStep)) {
                ((ArrayList) localObject1).add(localJGoObject);
            }
            localJGoListPosition = ((GCDocument) localObject2).getNextObjectPos(localJGoListPosition);
        }
        Collections.sort((List) localObject1, new Comparator() {
            public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2) {
                return (int) (((JGoObject) paramAnonymousObject1).getLocation().getX() - ((JGoObject) paramAnonymousObject2).getLocation().getX());
            }
        });
        EnterStep localEnterStep = (EnterStep) ((ArrayList) localObject1).remove(i - 1);
        addSucceedingStep(localEnterStep);
    }

    @Override
    public void compileStructure() {
        this.precedingSteps.clear();
        this.succeedingSteps.clear();
        if (this.myInPort.hasNoLinks()) {
            Editor.giveLightWarning("Unconnected transition in " + getDocument().getFullName());
        }
        JGoLink localJGoLink;
        JGoPort localJGoPort;
        GrafcetObject localGrafcetObject;
        Object localObject;
        for (JGoListPosition localJGoListPosition = this.myInPort.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myInPort.getNextLinkPos(localJGoListPosition)) {
            localJGoLink = this.myInPort.getLinkAtPos(localJGoListPosition);
            localJGoPort = localJGoLink.getFromPort();
            localGrafcetObject = (GrafcetObject) localJGoPort.getParent();
            if ((localGrafcetObject instanceof GCStep)) {
                localObject = (GCStep) localGrafcetObject;
                addPrecedingStep((GrafcetObject) localObject);
            }
            if (((localGrafcetObject instanceof MacroStep)) && (!(localGrafcetObject instanceof ProcedureStep))) {
                localObject = (MacroStep) localGrafcetObject;
                addExitStep((MacroStep) localObject, localJGoPort);
                addPrecedingStep((GrafcetObject) localObject);
            }
            if ((localGrafcetObject instanceof ProcedureStep)) {
                localObject = (ProcedureStep) localGrafcetObject;
                addPrecedingStep((GrafcetObject) localObject);
            }
            if ((localGrafcetObject instanceof ParallelJoin)) {
                localObject = (ParallelJoin) localGrafcetObject;
                ((ParallelJoin) localObject).compileUpwards(this);
            }
            if ((localGrafcetObject instanceof ConnectionPostOut)) {
                localObject = (ConnectionPostOut) localGrafcetObject;
                ((ConnectionPostOut) localObject).compileUpwards(this);
            }
        }
        if (this.myOutPort.hasNoLinks()) {
            Editor.giveLightWarning("Unconnected transition in " + getDocument().getFullName());
        }
        JGoListPosition localJGoListPosition;
        for (localJGoListPosition = this.myOutPort.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myOutPort.getNextLinkPos(localJGoListPosition)) {
            localJGoLink = this.myOutPort.getLinkAtPos(localJGoListPosition);
            localJGoPort = localJGoLink.getToPort();
            localGrafcetObject = (GrafcetObject) localJGoPort.getParent();
            if ((localGrafcetObject instanceof GCStep)) {
                localObject = (GCStep) localGrafcetObject;
                addSucceedingStep((GrafcetObject) localObject);
            }
            if ((localGrafcetObject instanceof ParallelSplit)) {
                localObject = (ParallelSplit) localGrafcetObject;
                ((ParallelSplit) localObject).compileDownwards(this);
            }
            if ((localGrafcetObject instanceof ConnectionPostIn)) {
                localObject = (ConnectionPostIn) localGrafcetObject;
                ((ConnectionPostIn) localObject).compileDownwards(this);
            }
            if (((localGrafcetObject instanceof MacroStep)) && (!(localGrafcetObject instanceof ProcedureStep))) {
                localObject = (MacroStep) localGrafcetObject;
                if ((localJGoPort instanceof GCStepInPort)) {
                    addEnterStep((MacroStep) localObject, localJGoPort);
                    addSucceedingStep((GrafcetObject) localObject);
                } else {
                    HistoryNode localHistoryNode = new HistoryNode((MacroStep) localObject);
                    addSucceedingStep(localHistoryNode);
                    addSucceedingStep((GrafcetObject) localObject);
                }
            }
            if ((localGrafcetObject instanceof ProcedureStep)) {
                addSucceedingStep(localGrafcetObject);
            }
        }        
    }

    public void preTestAndFire() {
        this.condition = this.node.evaluateBoolean();
        if ((this.condition) || (this.forced)) {
            if (!this.oldCondition) {
                this.myRectangle.setBrush(greenSolidBrush);
                this.oldCondition = true;
            }
            int i = 1;
            Iterator localIterator = this.precedingSteps.iterator();
            while (localIterator.hasNext()) {
                GrafcetObject localGrafcetObject = (GrafcetObject) localIterator.next();
                i = (i != 0) && (localGrafcetObject.isEnabled()) ? 1 : 0;
            }
            if (i != 0) {
                if (this.forced) {
                    this.forced = false;
                    if (!this.condition) {
                        this.myRectangle.setBrush(redSolidBrush);
                        this.oldCondition = false;
                    }
                }
                this.markedForFire = true;
            }
        } else if (this.oldCondition) {
            this.myRectangle.setBrush(redSolidBrush);
            this.oldCondition = false;
        }
    }

    public void testAndFire() {
        if (this.markedForFire) {
            this.markedForFire = false;
            Iterator localIterator = this.precedingSteps.iterator();
            GrafcetObject localGrafcetObject;
            while (localIterator.hasNext()) {
                localGrafcetObject = (GrafcetObject) localIterator.next();
                localGrafcetObject.deactivate();
            }
            localIterator = this.succeedingSteps.iterator();
            while (localIterator.hasNext()) {
                localGrafcetObject = (GrafcetObject) localIterator.next();
                localGrafcetObject.activate();
            }
        }
    }

    public void initialize() {
        this.condition = this.node.evaluateBoolean();
        this.oldCondition = this.condition;
        if (this.condition) {
            this.myRectangle.setBrush(greenSolidBrush);
        } else {
            this.myRectangle.setBrush(redSolidBrush);
        }
    }

    public void stop() {
        this.myRectangle.setBrush(JGoBrush.Null);
        this.forced = false;
    }

    public GCTransitionInPort getInPort() {
        return this.myInPort;
    }

    public GCTransitionOutPort getOutPort() {
        return this.myOutPort;
    }

    public String getLabelText() {
        return this.myCondition.getText();
    }

    public void setTextColor(Color paramColor) {
        this.myCondition.setTextColor(paramColor);
    }

    public void hideCondition() {
        this.conditionString = this.myCondition.getText();
        removeObject(this.myCondition);
        this.conditionVisible = false;
        layoutChildren();
    }

    public void showCondition() {
        this.myCondition.setText(this.conditionString);
        addObjectAtTail(this.myCondition);
        this.conditionVisible = true;
        layoutChildren();
    }

    public void hidePriority() {
        removeObject(this.myPriority);
        layoutChildren();
    }

    public void showPriority() {
        this.myPriority.setText("" + this.priority);
        addObjectAtTail(this.myPriority);
        layoutChildren();
    }

    public String getToolTipText() {
        if (this.conditionVisible) {
            return null;
        }
        return this.conditionString;
    }

    public String getHelpID() {
        return "LangRef_FC_Transition";
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCTransition.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
