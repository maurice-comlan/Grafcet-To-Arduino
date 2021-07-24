package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoLink;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoText;
import com.nwoods.jgo.JGoView;
import grafchart.graphics.MyJGoImage;
import grafchart.sfc.actions.Action;
import grafchart.sfc.actions.Statement;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Classe représentant l'élément graphique step
 *
 * @author dimon
 */
public class GCStep
        extends GrafcetObject
        implements GCIdent, Readable, Connectable, IconStep {

    private static final int MY_ACTIONS_OFFSET_X = 5;
    private static final int MY_ACTIONS_OFFSET_Y = 1;
    private static String actionTag = "Action";
    public String fileName = "";
    public boolean useIcon = false;
    public MyJGoImage icon = null;
    private String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
    public JGoRectangle myRectangle = null;
    public JGoText myName = null;
    public JGoStroke myInLine = null;
    protected JGoStroke myOutLine = null;
    protected JGoStroke myActionStroke = null;
    protected GCStepInPort myInPort = null;
    protected GCStepOutPort myOutPort = null;
    public JGoEllipse myToken = null;
    protected JGoRectangle myActionRectangle = null;
    protected JGoText myActions = null;
    private Dimension myActionsMinDimension = null;
    private String actionText = ";";
    public boolean actionBlockVisible = false;
    public transient Statement node = null;
    public static JGoPen standardPen = new JGoPen();
    public transient ArrayList<Action> actionsList = new ArrayList<>();

    public GCStep() {
    }

    /**
     * Crée un nouvelle étape
     *
     * @param position Coordonée aux quelles placé le Step
     * @param name Nom de l'étape
     */
    public GCStep(Point position, String name) {
        this();
        setSelectable(true);
        setGrabChildSelection(false);
        setDraggable(true);
        setResizable(true);
        this.myRectangle = new JGoRectangle();
        this.myRectangle.setSize(60, 60);
        this.myRectangle.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
        this.myRectangle.setSelectable(false);
        this.myRectangle.setDraggable(false);
        this.myName = new JGoText(name);
        this.myName.setSelectable(true);
        this.myName.setEditable(true);
        this.myName.setEditOnSingleClick(true);
        this.myName.setDraggable(false);
        this.myName.setAlignment(3);
        this.myName.setTransparent(true);
        this.myInLine = new JGoStroke();
        this.myInLine.addPoint(10, 0);
        this.myInLine.addPoint(10, 5);
        this.myInLine.setSelectable(false);
        this.myOutLine = new JGoStroke();
        this.myOutLine.addPoint(20, 0);
        this.myOutLine.addPoint(20, 5);
        this.myOutLine.setSelectable(false);
        this.myInPort = new GCStepInPort();
        this.myOutPort = new GCStepOutPort();
        this.myToken = new JGoEllipse();
        this.myToken.setSize(20, 20);
        this.myToken.setSelectable(false);
        this.myToken.setDraggable(false);
        this.myToken.setPen(JGoPen.Null);
        this.myToken.setBrush(JGoBrush.Null);
        this.myActionStroke = new JGoStroke();
        this.myActionStroke.addPoint(0, 10);
        this.myActionStroke.addPoint(30, 10);
        this.myActionStroke.setPen(JGoPen.Null);
        this.myActionStroke.setBrush(JGoBrush.Null);
        this.myActionStroke.setSelectable(false);
        this.myActionRectangle = new JGoRectangle();
        this.myActionRectangle.setSize(110, 50);
        this.myActionRectangle.setPen(JGoPen.Null);
        this.myActionRectangle.setBrush(JGoBrush.Null);
        this.myActionRectangle.setSelectable(false);
        this.myActionRectangle.setDraggable(false);
        addObjectAtHead(this.myRectangle);
        addObjectAtTail(this.myName);
        addObjectAtTail(this.myInLine);
        addObjectAtTail(this.myOutLine);
        addObjectAtTail(this.myInPort);
        addObjectAtTail(this.myOutPort);
        addObjectAtTail(this.myToken);
        setLocation(position);
    }

    public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment) {
        GCStep localGCStep = (GCStep) paramJGoArea;
        localGCStep.myRectangle = ((JGoRectangle) paramJGoCopyEnvironment.copy(this.myRectangle));
        localGCStep.myName = ((JGoText) paramJGoCopyEnvironment.copy(this.myName));
        localGCStep.myInLine = ((JGoStroke) paramJGoCopyEnvironment.copy(this.myInLine));
        localGCStep.myOutLine = ((JGoStroke) paramJGoCopyEnvironment.copy(this.myOutLine));
        localGCStep.myInPort = ((GCStepInPort) paramJGoCopyEnvironment.copy(this.myInPort));
        localGCStep.myOutPort = ((GCStepOutPort) paramJGoCopyEnvironment.copy(this.myOutPort));
        localGCStep.myToken = ((JGoEllipse) paramJGoCopyEnvironment.copy(this.myToken));
        localGCStep.myActionStroke = ((JGoStroke) paramJGoCopyEnvironment.copy(this.myActionStroke));
        localGCStep.myActionRectangle = ((JGoRectangle) paramJGoCopyEnvironment.copy(this.myActionRectangle));
        localGCStep.myActions = ((JGoText) paramJGoCopyEnvironment.copy(this.myActions));
        localGCStep.myToken.setBrush(JGoBrush.Null);
        localGCStep.addObjectAtHead(localGCStep.myRectangle);
        localGCStep.addObjectAtTail(localGCStep.myName);
        localGCStep.addObjectAtTail(localGCStep.myInLine);
        localGCStep.addObjectAtTail(localGCStep.myOutLine);
        localGCStep.addObjectAtTail(localGCStep.myInPort);
        localGCStep.addObjectAtTail(localGCStep.myOutPort);
        localGCStep.addObjectAtTail(localGCStep.myToken);
        if (this.actionBlockVisible) {
            localGCStep.addObjectAtTail(localGCStep.myActionStroke);
            localGCStep.addObjectAtTail(localGCStep.myActionRectangle);
            localGCStep.addObjectAtTail(localGCStep.myActions);
        }
        if (this.icon != null) {
            localGCStep.icon = ((MyJGoImage) paramJGoCopyEnvironment.copy(this.icon));
            localGCStep.addObjectAtTail(localGCStep.icon);
        }
        localGCStep.actionBlockVisible = this.actionBlockVisible;
        localGCStep.actionText = this.actionText;
        localGCStep.useIcon = this.useIcon;
        localGCStep.fileName = this.fileName;
        //localGCStep.actionsList = this.actionsList;
    }

    public boolean isUseIcon() {
        return this.useIcon;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setUseIcon(boolean paramBoolean) {
        this.useIcon = paramBoolean;
    }

    public void setFileNameValue(String paramString) {
        this.fileName = paramString;
    }

    public void setFileName() {
        if ((this.useIcon) && (this.icon == null)) {
            this.icon = new MyJGoImage(getTopLeft(), this.myRectangle.getSize());
            this.icon.setSelectable(false);
            this.icon.setDraggable(false);
            addObjectAtHead(this.icon);
            this.icon.loadImage(this.fileName);
            layoutChildren();
        } else if ((!this.useIcon) && (this.icon != null)) {
            removeObject(this.icon);
            this.icon = null;
            layoutChildren();
        }
    }

    private void removeTextFields() {
        removeObject(this.myName);
        removeObject(this.myActions);
    }

    private void restoreTextFields() {
        addObjectAtTail(this.myName);
        addObjectAtTail(this.myActions);
    }

    public Element storeXML(Element paramElement, Vector paramVector1, Vector paramVector2) {
        paramVector1.add(this.id);
        paramVector2.add(this);
        paramElement.setAttribute("id", this.id);
        paramElement.setAttribute("name", getName());
        XMLUtil.setBool(paramElement, "useIcon", this.useIcon);
        paramElement.setAttribute("fileName", this.fileName);
        paramElement.setAttribute("actionText", this.actionBlockVisible ? this.myActions.getText() : this.actionText);
        XMLUtil.setBool(paramElement, "actionBlockVisible", this.actionBlockVisible);

        Document localDocument = paramElement.getOwnerDocument();
        Iterator<Action> it = this.actionsList.iterator();
        while (it.hasNext()) {
            Action action = it.next();
            Element actionElement = localDocument.createElement(GCStep.actionTag);   
            Element returnElement = action.storeXML(actionElement, paramVector1, paramVector2);
            if(returnElement != null){
                paramElement.appendChild(returnElement);
            }
            
        }       

        removeTextFields();
        XMLUtil.saveBoundingRect(paramElement, this);
        restoreTextFields();
        return paramElement;
    }

    public static GCStep loadXML(Element paramElement, Vector paramVector1, Vector paramVector2) {
        String name = paramElement.getAttribute("name");
        String tag = paramElement.getTagName();
        GCStep step = null;
        if (tag.equals(GCDocument.gcStepTag)) {
            step = new GCStep(new Point(), name);
        } else if (tag.equals(GCDocument.initialGCStepTag)) {
            step = new GCStepInitial(new Point(), name);
        } else if (tag.equals(GCDocument.enterStepTag)) {
            step = new EnterStep(new Point(), name);
        } else if (tag.equals(GCDocument.exitStepTag)) {
            step = new ExitStep(new Point(), name);
        }
        
        //Recupération des ations
        NodeList actionsList = paramElement.getChildNodes();
        Node node;
        Element actionElement;
        for (int i = 0; i < actionsList.getLength(); i++) {
            node = actionsList.item(i);
            if (node.getNodeType() == 1) {
                actionElement = (Element) node;
                //Standard variable
                if (actionElement.getTagName().equals(actionTag)) {
                    Action  ac = Action.loadXML(actionElement, paramVector1, paramVector2);
                    step.actionsList.add(ac);
                }
            }
        }
        
        
        String str3 = paramElement.getAttribute("id");
        if (Utils.getSaveVersion(paramElement) >= 2) {
            step.id = str3;
        }
        paramVector1.add(str3);
        paramVector2.add(step);
        if (paramElement.hasAttribute("fileName")) {
            step.fileName = paramElement.getAttribute("fileName");
        } else {
            step.fileName = "";
        }
        if (paramElement.hasAttribute("useIcon")) {
            String str4 = paramElement.getAttribute("useIcon");
            boolean bool = false;
            if (str4.equals("1")) {
                bool = true;
            }
            step.useIcon = bool;
        } else {
            step.useIcon = false;
        }
        if (step.useIcon) {
            step.setFileName();
        }
        String str4 = paramElement.getAttribute("actionText");
        if (Utils.getSaveVersion(paramElement) < 1) {
            str4 = Utils.newlineHack(str4);
        }
        step.actionText = str4;
        String str5 = paramElement.getAttribute("actionBlockVisible");
        if (str5.equals("1")) {
            step.actionBlockVisible = true;
            step.showActionBlock();
            step.myActions.setText(str4);
            step.updateActionFonts();
        } else {
            step.actionBlockVisible = false;
        }
        step.removeTextFields();
        if ((!step.actionBlockVisible) && (Utils.getSaveVersion(paramElement) < 3)) {
            step.addObjectAtTail(step.myActionStroke);
            step.addObjectAtTail(step.myActionRectangle);
        }
        XMLUtil.restoreBoundingRectAny(paramElement, (GrafcetObject) step, step.myRectangle, 2);
        if ((!step.actionBlockVisible) && (Utils.getSaveVersion(paramElement) < 3)) {
            step.removeObject(step.myActionStroke);
            step.removeObject(step.myActionRectangle);
        }
        step.restoreTextFields();
        return (GCStep) step;
    }

    public Point getLocation(Point paramPoint) {
        return this.myRectangle.getSpotLocation(2, paramPoint);
    }

    public void setLocation(int paramInt1, int paramInt2) {
        this.myRectangle.setSpotLocation(2, paramInt1, paramInt2);
        layoutChildren();
    }

    public void layoutChildren() {
        Point localPoint1 = this.myRectangle.getSpotLocation(8);
        this.myName.setSpotLocation(4, (int) localPoint1.getX() - 8, (int) localPoint1.getY());
        if (this.icon != null) {
            this.icon.setSpotLocation(0, this.myRectangle, 0);
        }
        this.myInLine.setSpotLocation(6, this.myRectangle, 2);
        this.myOutLine.setSpotLocation(2, this.myRectangle, 6);
        this.myInPort.setSpotLocation(2, this.myInLine, 2);
        this.myOutPort.setSpotLocation(6, this.myOutLine, 6);
        this.myToken.setSpotLocation(0, this.myRectangle, 0);
        this.myActionStroke.setSpotLocation(8, this.myRectangle, 4);
        this.myActionRectangle.setSpotLocation(8, this.myActionStroke, 4);
        if (this.myActions != null) {
            Point localPoint2 = this.myActionRectangle.getSpotLocation(1);
            this.myActions.setSpotLocation(1, localPoint2.x + 5, localPoint2.y + 1);
        }
    }

    public JGoObject[] getGeometryChangeObjects() {
        return new JGoObject[]{this.myRectangle, this.myInPort, this.myOutPort, this.myInLine, this.myOutLine, this.myActionStroke, this.myActionRectangle, this.icon, this.myToken};
    }

    public void geometryChange(Rectangle paramRectangle) {
        super.geometryChange(paramRectangle);
        if ((paramRectangle.width != getWidth()) || (paramRectangle.height != getHeight())) {
            updateActionFonts();
        }
    }

    public int getNoScaleLeft(Rectangle paramRectangle) {
        return this.myRectangle.getLeft() - paramRectangle.x;
    }

    protected Rectangle handleResize(Graphics2D paramGraphics2D, JGoView paramJGoView, Rectangle paramRectangle, Point paramPoint, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        Dimension localDimension = getMinimumSize();
        return super.handleResize(paramGraphics2D, paramJGoView, paramRectangle, paramPoint, paramInt1, paramInt2, localDimension.width, localDimension.height);
    }

    public Dimension getMinimumSize() {
        Dimension localDimension = new Dimension(0, 0);
        if ((this.myName != null) && (this.myActions != null)) {
            localDimension.width = ((int) Math.ceil(this.myName.getWidth() + this.myActionsMinDimension.width + 60));
            localDimension.height = ((int) Math.ceil(Math.max(this.myName.getHeight(), this.myActionsMinDimension.height) + 10));
        } else if (this.myName != null) {
            localDimension.width = ((int) Math.ceil(this.myName.getWidth() + 30));
            localDimension.height = ((int) Math.ceil(this.myName.getHeight() + 10));
        }
        return localDimension;
    }

    public Vector getAllLinks() {
        Vector localVector = new Vector();
        Utils.addLinks(this.myInPort, localVector);
        Utils.addLinks(this.myOutPort, localVector);
        return localVector;
    }

    public boolean isTransition() {
        return false;
    }

    public boolean isStep() {
        return true;
    }

    public void activateAlone() {
        this.myToken.setBrush(JGoBrush.black);
        if ((getParent() instanceof GCGroup)) {
            ((GCGroup) getParent()).showToken();
        }
        executeStoredActions();
        this.newX = true;
    }

    public void activate() {
        if (!this.fusionSets.isEmpty()) {
            Iterator localIterator = this.fusionSets.iterator();
            while (localIterator.hasNext()) {
                StepFusionSet localStepFusionSet = (StepFusionSet) localIterator.next();
                localStepFusionSet.activate();
            }
        } else {
            activateAlone();
        }
    }

    public void deactivate() {
        if (!this.fusionSets.isEmpty()) {
            Iterator localIterator = this.fusionSets.iterator();
            while (localIterator.hasNext()) {
                StepFusionSet localStepFusionSet = (StepFusionSet) localIterator.next();
                localStepFusionSet.deactivate(this);
            }
        } else {
            deactivateAlone();
        }
    }

    public void deactivateAlone() {
        this.myToken.setBrush(JGoBrush.Null);
        if ((getParent() instanceof GCGroup)) {
            ((GCGroup) getParent()).hideToken();
        }
        this.newX = false;
        this.timer = 0;
        executeExitActions();
        if (getDocument().dimming) {
            this.myToken.setBrush(JGoBrush.lightGray);
            DimmerThread localDimmerThread = new DimmerThread(this);
            localDimmerThread.start();
        }
    }

    public void changeState() {
        if (!this.curX) {
            this.timer = 0;
        } else {
            this.timer += 1;
            if (this.newX) {
                executePeriodicActions();
            }
        }
        this.oldX = this.curX;
        this.curX = this.newX;
        if (((this.curX) && (!this.oldX)) || ((!this.curX) && (this.oldX))) {
            executeNormalActions(this.curX);
        }
    }

    public double getSeconds() {
        return this.timer * getDocument().getTickTime() / 1000.0D;
    }

    public void executeStoredActions() {
        if (this.node != null) {
            this.node.executeStoredActions();
        }
    }

    public void executeNormalActions(boolean paramBoolean) {
        if (this.node != null) {
            this.node.executeNormalActions(paramBoolean);
        }
    }

    public void updateNormalActions() {
        if (this.node != null) {
            this.node.effectuateNormalActions();
        }
    }

    public void executeExitActions() {
        if (this.node != null) {
            this.node.executeExitActions();
        }
    }

    public void executePeriodicActions() {
        if (this.node != null) {
            this.node.executePeriodicActions();
        }
    }

    public void executeAbortiveActions() {
        if (this.node != null) {
            this.node.executeAbortiveActions();
        }
    }

    public void showActionBlock() {
        this.actionBlockVisible = true;
        this.myActionStroke.setPen(JGoPen.black);
        this.myActionRectangle.setPen(JGoPen.black);
        this.myActions = new JGoText(this.actionText);
        this.myActions.setSelectable(true);
        this.myActions.setEditable(false);
        this.myActions.setEditOnSingleClick(false);
        this.myActions.setDraggable(false);
        this.myActions.setAlignment(1);
        this.myActions.setTransparent(true);
        this.myActions.setBold(true);
        this.myActions.setMultiline(true);
        addObjectAtTail(this.myActionStroke);
        addObjectAtTail(this.myActionRectangle);
        addObjectAtTail(this.myActions);
        updateActionFonts();
        layoutChildren();
    }

    public void hideActionBlock() {
        this.actionBlockVisible = false;
        this.myActionStroke.setPen(JGoPen.Null);
        this.myActionRectangle.setPen(JGoPen.Null);
        this.actionText = this.myActions.getText();
        removeObject(this.myActionStroke);
        removeObject(this.myActionRectangle);
        removeObject(this.myActions);
        this.myActions = null;
        layoutChildren();
    }

    public String getToolTipText() {
        if ((!this.actionBlockVisible) && (!this.actionText.equals("")) && (!this.actionText.equals(";"))) {
            String str = this.actionText;
            str = str.replace('\n', ' ');
            return str;
        }
        return null;
    }

    public boolean isEnabled() {
        if (!this.fusionSets.isEmpty()) {
            boolean bool = true;
            Iterator localIterator = this.fusionSets.iterator();
            while (localIterator.hasNext()) {
                StepFusionSet localStepFusionSet = (StepFusionSet) localIterator.next();
                bool = (bool) && (localStepFusionSet.isEnabled(this));
            }
            return bool;
        }
        return isEnabledAlone();
    }

    public boolean isEnabledAlone() {
        return this.curX;
    }

    public GCStepInPort getInPort() {
        return this.myInPort;
    }

    public GCStepOutPort getOutPort() {
        return this.myOutPort;
    }

    protected JGoEllipse getToken() {
        return this.myToken;
    }

    public String getName() {
        return this.myName.getText();
    }

    public void setName(String paramString) {
        this.myName.setText(paramString);
    }

    public String getFullName() {
        String str = getName();
        GCDocument localGCDocument = getDocument();
        for (GrafcetObject localGrafcetObject = localGCDocument.owner; localGrafcetObject != null; localGrafcetObject = localGCDocument.owner) {
            Referencable localReferencable = (Referencable) localGrafcetObject;
            str = localReferencable.getName() + "." + str;
            localGCDocument = localGrafcetObject.getDocument();
        }
        str = localGCDocument.getName() + "." + str;
        return str;
    }

    public boolean getBoolVal() {
        return false;
    }

    public boolean getOldBoolVal() {
        return false;
    }

    public int getIntVal() {
        return 0;
    }

    public int getOldIntVal() {
        return 0;
    }

    public double getRealVal() {
        return 0.0D;
    }

    public double getOldRealVal() {
        return 0.0D;
    }

    public String getStringVal() {
        return new String("");
    }

    public String getOldStringVal() {
        return new String("");
    }

    public String getHelpID() {
        return "LangRef_FC_Step";
    }

    public String getActionText() {
        if (this.myActions == null) {
            return this.actionText;
        }
        return this.myActions.getText();
    }

    public void setActionText(String paramString) {
        if (this.myActions == null) {
            this.actionText = paramString;
        } else {
            this.myActions.setText(paramString);
            updateActionFonts();
        }
    }

    public String toString() {
        String str = getName();
        if (str.equals("")) {
            return "    ";
        }
        return getName();
    }

    public void updateActionFonts() {
        if (this.myActions != null) {
            int i = 2;
            this.myActions.setFaceName("Monospaced");
            this.myActions.setFontSize(i);
            this.myActionsMinDimension = new Dimension(this.myActions.getWidth() + 5, this.myActions.getHeight() + 1);
            this.myActions.setFontSize(12);
            while ((this.myActions.getFontSize() > i) && ((this.myActions.getWidth() + 5 >= this.myActionRectangle.getWidth()) || (this.myActions.getHeight() + 1 >= this.myActionRectangle.getHeight()))) {
                this.myActions.setFontSize(this.myActions.getFontSize() - 1);
            }
        } else {
            this.myActionsMinDimension = null;
        }
    }

    @Override
    public void succeedingTransitions(ArrayList<GenericTransition> paramArrayList) {
        for (JGoListPosition localJGoListPosition = this.myOutPort.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = this.myOutPort.getNextLinkPos(localJGoListPosition)) {
            JGoLink localJGoLink = this.myOutPort.getLinkAtPos(localJGoListPosition);
            JGoPort localJGoPort = localJGoLink.getToPort();
            if ((localJGoPort.getParent() instanceof GenericTransition)) {
                GenericTransition localGenericTransition = (GenericTransition) localJGoPort.getParent();
                paramArrayList.add(localGenericTransition);
            }
        }
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCStep.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
