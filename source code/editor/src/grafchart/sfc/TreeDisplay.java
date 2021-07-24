package grafchart.sfc;

import com.nwoods.jgo.JGoDrawable;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoObjectCollection;
import com.nwoods.jgo.JGoSelection;
import com.nwoods.jgo.JGoText;
import grafchart.dialog.AssignmentButtonDialog;
import grafchart.dialog.BasicListDialog;
import grafchart.dialog.ConnectionPostDialog;
import grafchart.dialog.DPWSObjectDialog;
import grafchart.dialog.DrawableNameDialog;
import grafchart.dialog.GraphicalButtonDialog;
import grafchart.dialog.InternalVariableDialog;
import grafchart.dialog.LabCommObjectDialog;
import grafchart.dialog.SocketInDialog;
import grafchart.dialog.SocketOutDialog;
import grafchart.graphics.MyJFrame;
import grafchart.graphics.MyJPopupMenu;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class TreeDisplay
  extends MyJFrame
{
  Editor gui;
  public DefaultMutableTreeNode top = null;
  public JTree tree = null;
  private static ImageIcon workspaceObjectIcon = Utils.newImageIcon("workspaceobject.gif");
  private static ImageIcon stepIcon = Utils.newImageIcon("step.gif");
  private static ImageIcon enterstepIcon = Utils.newImageIcon("enterstep.gif");
  private static ImageIcon exitstepIcon = Utils.newImageIcon("exitstep.gif");
  private static ImageIcon transitionIcon = Utils.newImageIcon("transition.gif");
  private static ImageIcon freeTextIcon = Utils.newImageIcon("freetext.gif");
  private static ImageIcon initialstepIcon = Utils.newImageIcon("initialstep.gif");
  private static ImageIcon macrostepIcon = Utils.newImageIcon("macrostep.gif");
  private static ImageIcon procedurestepIcon = Utils.newImageIcon("procedurestep.gif");
  private static ImageIcon processstepIcon = Utils.newImageIcon("processstep.gif");
  private static ImageIcon procedureIcon = Utils.newImageIcon("procedure.gif");
  private static ImageIcon exceptiontransitionIcon = Utils.newImageIcon("exceptiontransition.gif");
  private static ImageIcon parallelsplitIcon = Utils.newImageIcon("parallelsplit.gif");
  private static ImageIcon paralleljoinIcon = Utils.newImageIcon("paralleljoin.gif");
  private static ImageIcon connectionpostinIcon = Utils.newImageIcon("connectionpostin.gif");
  private static ImageIcon connectionpostoutIcon = Utils.newImageIcon("connectionpostout.gif");
  private static ImageIcon stepfusionsetIcon = Utils.newImageIcon("stepfusionset.gif");
  private static ImageIcon booleanvariableIcon = Utils.newImageIcon("booleanvariable.gif");
  private static ImageIcon integervariableIcon = Utils.newImageIcon("integervariable.gif");
  private static ImageIcon realvariableIcon = Utils.newImageIcon("realvariable.gif");
  private static ImageIcon stringvariableIcon = Utils.newImageIcon("stringvariable.gif");
  private static ImageIcon stringlistIcon = Utils.newImageIcon("stringlist.gif");
  private static ImageIcon booleanlistIcon = Utils.newImageIcon("booleanlist.gif");
  private static ImageIcon integerlistIcon = Utils.newImageIcon("integerlist.gif");
  private static ImageIcon reallistIcon = Utils.newImageIcon("reallist.gif");
  private static ImageIcon rectangleIcon = Utils.newImageIcon("rectangleicon.gif");
  private static ImageIcon ellipseIcon = Utils.newImageIcon("ellipseicon.gif");
  private static ImageIcon polygoneIcon = Utils.newImageIcon("polygoneicon.gif");
  private static ImageIcon threedrectangleIcon = Utils.newImageIcon("3drectangleicon.gif");
  private static ImageIcon lineIcon = Utils.newImageIcon("lineicon.gif");
  private static ImageIcon splineIcon = Utils.newImageIcon("splineicon.gif");
  private static ImageIcon iconIcon = Utils.newImageIcon("iconicon.gif");
  private static ImageIcon actionbuttonIcon = Utils.newImageIcon("actionbutton.gif");
  private static ImageIcon graphicalbuttonIcon = Utils.newImageIcon("graphicalbutton.gif");
  private static ImageIcon plotterIcon = Utils.newImageIcon("plottericon.gif");
  private static ImageIcon browserIcon = Utils.newImageIcon("browsericon.gif");
  private static ImageIcon showworkspaceIcon = Utils.newImageIcon("showworkspaceicon.gif");
  private static ImageIcon chemIcon = Utils.newImageIcon("chemsmallicon.gif");
  private static ImageIcon lucasIcon = Utils.newImageIcon("lucasicon.gif");
  private static ImageIcon xmlmessageinIcon = Utils.newImageIcon("xmlmessagein.gif");
  private static ImageIcon xmlmessageoutIcon = Utils.newImageIcon("xmlmessageout.gif");
  private static ImageIcon indexvariableIcon = Utils.newImageIcon("indexvariable.gif");
  private static ImageIcon integerattributeIcon = Utils.newImageIcon("integerattribute.gif");
  private static ImageIcon realattributeIcon = Utils.newImageIcon("realattribute.gif");
  private static ImageIcon stringattributeIcon = Utils.newImageIcon("stringattribute.gif");
  private static ImageIcon digitalinIcon = Utils.newImageIcon("digitalin.gif");
  private static ImageIcon digitaloutIcon = Utils.newImageIcon("digitalout.gif");
  private static ImageIcon digitalin1Icon = Utils.newImageIcon("digitalin1.gif");
  private static ImageIcon digitalout1Icon = Utils.newImageIcon("digitalout1.gif");
  private static ImageIcon analoginIcon = Utils.newImageIcon("analogin.gif");
  private static ImageIcon analogoutIcon = Utils.newImageIcon("analogout.gif");
  private static ImageIcon socketboolinIcon = Utils.newImageIcon("socketboolin.gif");
  private static ImageIcon socketintinIcon = Utils.newImageIcon("socketintin.gif");
  private static ImageIcon socketrealinIcon = Utils.newImageIcon("socketrealin.gif");
  private static ImageIcon socketstringinIcon = Utils.newImageIcon("socketstringin.gif");
  private static ImageIcon socketbooloutIcon = Utils.newImageIcon("socketboolout.gif");
  private static ImageIcon socketintoutIcon = Utils.newImageIcon("socketintout.gif");
  private static ImageIcon socketrealoutIcon = Utils.newImageIcon("socketrealout.gif");
  private static ImageIcon socketstringoutIcon = Utils.newImageIcon("socketstringout.gif");
  private static ImageIcon byteStreamInIcon = Utils.newImageIcon("byteStreamIn.png");
  private static ImageIcon byteStreamOutIcon = Utils.newImageIcon("byteStreamOut.png");
  private static ImageIcon dpwsObjectIcon = Utils.newImageIcon("dpwsObject.png");
  private static ImageIcon labCommObjectIcon = Utils.newImageIcon("labcommObject.png");
  
  public TreeDisplay(Editor paramEditor, GCDocument paramGCDocument)
  {
    super("Tree");
    setLocation(50, 50);
    setDefaultCloseOperation(2);
    this.gui = paramEditor;
    this.top = new DefaultMutableTreeNode(paramGCDocument);
    createNodes(this.top, paramGCDocument);
    this.tree = new JTree(this.top);
    setupIcons();
    final JScrollPane localJScrollPane = new JScrollPane(this.tree);
    JComponent localJComponent = (JComponent)getContentPane();
    setSize(350, 700);
    localJComponent.add(localJScrollPane);
    pack();
    setVisible(true);
    this.tree.getSelectionModel().setSelectionMode(1);
    this.tree.addTreeSelectionListener(new TreeSelectionListener()
    {
      public void valueChanged(TreeSelectionEvent paramAnonymousTreeSelectionEvent)
      {
        Object localObject = ((DefaultMutableTreeNode)TreeDisplay.this.tree.getLastSelectedPathComponent()).getUserObject();
        if ((localObject instanceof JGoObject))
        {
          JGoObject localJGoObject = (JGoObject)localObject;
          GCView localGCView = ((GCDocument)((JGoObject)localObject).getDocument()).getView();
          if (localGCView != null)
          {
            localGCView.getSelection().clearSelection();
            localGCView.getSelection().extendSelection(localJGoObject);
          }
        }
      }
    });
    this.tree.addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent paramAnonymousMouseEvent)
      {
        int i = TreeDisplay.this.tree.getRowForLocation(paramAnonymousMouseEvent.getX(), paramAnonymousMouseEvent.getY());
        TreePath localTreePath = TreeDisplay.this.tree.getPathForLocation(paramAnonymousMouseEvent.getX(), paramAnonymousMouseEvent.getY());
        if ((i != -1) && (paramAnonymousMouseEvent.getClickCount() == 1) && (paramAnonymousMouseEvent.getButton() == 3))
        {
          DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)localTreePath.getLastPathComponent();
          Object localObject = localDefaultMutableTreeNode.getUserObject();
          if ((localObject instanceof JGoObject))
          {
            if (localJScrollPane.getViewport() != null) {
              paramAnonymousMouseEvent.translatePoint(-localJScrollPane.getViewport().getViewPosition().x, -localJScrollPane.getViewport().getViewPosition().y);
            }
            Point localPoint = new Point(paramAnonymousMouseEvent.getX(), paramAnonymousMouseEvent.getY());
            TreeDisplay.this.showContextMenu((JGoObject)localObject, localPoint);
          }
        }
      }
    });
    this.tree.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent paramAnonymousKeyEvent)
      {
        int i = paramAnonymousKeyEvent.getKeyCode();
        if (i == 525)
        {
          int[] arrayOfInt = TreeDisplay.this.tree.getSelectionRows();
          if ((arrayOfInt != null) && (arrayOfInt.length == 1))
          {
            Object localObject = ((DefaultMutableTreeNode)TreeDisplay.this.tree.getLastSelectedPathComponent()).getUserObject();
            Rectangle localRectangle = TreeDisplay.this.tree.getRowBounds(TreeDisplay.this.tree.getSelectionRows()[0]);
            if ((localObject instanceof JGoObject))
            {
              GCView localGCView = ((GCDocument)((JGoObject)localObject).getDocument()).getView();
              if (localGCView != null)
              {
                JGoObject localJGoObject = localGCView.getSelection().getPrimarySelection();
                if (localJGoObject != null) {
                  TreeDisplay.this.showContextMenu(localJGoObject, new Point(localRectangle.x + localRectangle.width, localRectangle.y + localRectangle.height / 2));
                }
              }
            }
          }
        }
      }
    });
  }
  
  private void showContextMenu(JGoObject paramJGoObject, Point paramPoint)
  {
    Editor.singleton.selectedObject = paramJGoObject;
    Editor.updateActions();
    JComponent localJComponent = (JComponent)getContentPane();
    if ((paramJGoObject instanceof GCGroup))
    {
      new DrawableNameDialog(this.gui, ((GCGroup)paramJGoObject).getDocument(), (GCGroup)paramJGoObject, null).setVisible(true);
      Editor.singleton.selectedObject = null;
    }
    if ((paramJGoObject instanceof GCStep)) {
      Editor.singleton.contextStep.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
    }
    if ((paramJGoObject instanceof GenericTransition)) {
      if (!Editor.slideMode) {
        Editor.singleton.contextTransition.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
      } else {
        Editor.singleton.transitionForceAction();
      }
    }
    if (((paramJGoObject instanceof MacroStep)) && (!(paramJGoObject instanceof ProcedureStep))) {
      Editor.singleton.contextMacroStep.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
    }
    if ((paramJGoObject instanceof GrafcetProcedure)) {
      Editor.singleton.contextProcedure.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
    }
    if ((paramJGoObject instanceof StepFusionSet)) {
      Editor.singleton.contextStepFusionSet.show(localJComponent, paramPoint.x, paramPoint.y);
    }
    if ((paramJGoObject instanceof SFCPlotter)) {
      Editor.singleton.contextSFCPlotter.show(localJComponent, paramPoint.x, paramPoint.y);
    }
    Object localObject;
    if (((paramJGoObject instanceof WorkspaceObject)) && (!(paramJGoObject instanceof StepFusionSet)))
    {
      localObject = (WorkspaceObject)paramJGoObject;
      if (Editor.slideMode)
      {
        Editor.singleton.handleWorkspaceObject((WorkspaceObject)localObject);
        Editor.singleton.selectedObject = null;
        AppAction.updateAllActions();
      }
      else
      {
        Editor.singleton.contextWorkspaceObject.show(localJComponent, paramPoint.x, paramPoint.y);
      }
    }
    if ((paramJGoObject instanceof AssignmentButton))
    {
      new AssignmentButtonDialog(this.gui, ((AssignmentButton)paramJGoObject).getDocument(), (AssignmentButton)paramJGoObject, null).setVisible(true);
      Editor.singleton.selectedObject = null;
    }
    if ((paramJGoObject instanceof GraphicalButton))
    {
      new GraphicalButtonDialog(this.gui, (GCDocument)((GraphicalButton)paramJGoObject).getDocument(), (GraphicalButton)paramJGoObject, null).setVisible(true);
      Editor.singleton.selectedObject = null;
    }
    if (((paramJGoObject instanceof ProcedureStep)) && (!(paramJGoObject instanceof ProcessStep))) {
      Editor.singleton.contextProcedureStep.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
    }
    if ((paramJGoObject instanceof ProcessStep)) {
      Editor.singleton.contextProcessStep.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
    }
    if ((paramJGoObject instanceof ConnectionPost))
    {
      new ConnectionPostDialog(this.gui, ((ConnectionPost)paramJGoObject).getDocument(), (ConnectionPost)paramJGoObject, null).setVisible(true);
      Editor.singleton.selectedObject = null;
    }
    if ((paramJGoObject instanceof BasicList))
    {
      new BasicListDialog(this.gui, (BasicList)paramJGoObject, null).setVisible(true);
      Editor.singleton.selectedObject = null;
    }
    if ((paramJGoObject instanceof InternalVariable))
    {
      new InternalVariableDialog(this.gui, (InternalVariable)paramJGoObject, null).setVisible(true);
      Editor.singleton.selectedObject = null;
    }
    if ((paramJGoObject instanceof JGoText)) {
      Editor.singleton.contextText.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
    }
    if ((paramJGoObject instanceof SocketIn))
    {
      localObject = (SocketIn)paramJGoObject;
      new SocketInDialog(this.gui, (SocketIn)localObject, null).setVisible(true);
      Editor.singleton.selectedObject = null;
      AppAction.updateAllActions();
    }
    if ((paramJGoObject instanceof SocketOut))
    {
      localObject = (SocketOut)paramJGoObject;
      new SocketOutDialog(this.gui, (SocketOut)localObject, null).setVisible(true);
      Editor.singleton.selectedObject = null;
      AppAction.updateAllActions();
    }
    if ((paramJGoObject instanceof XMLMessageIn)) {
      Editor.singleton.contextXMLMessageIn.show(localJComponent, paramPoint.x, paramPoint.y);
    }
    if ((paramJGoObject instanceof XMLMessageOut)) {
      Editor.singleton.contextXMLMessageOut.show(localJComponent, paramPoint.x, paramPoint.y);
    }
    if (((paramJGoObject instanceof JGoDrawable)) && (!(paramJGoObject instanceof GCLink))) {
      Editor.singleton.contextDrawable.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
    }
    if ((paramJGoObject instanceof Browser)) {
      Editor.singleton.contextBrowser.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
    }
    if ((paramJGoObject instanceof Icon)) {
      Editor.singleton.contextIcon.show(localJComponent, (int)paramPoint.getX(), (int)paramPoint.getY());
    }
    if ((paramJGoObject instanceof DPWSObject)) {
      new DPWSObjectDialog(null, (DPWSObject)paramJGoObject, null).setVisible(true);
    }
    if ((paramJGoObject instanceof LabCommObject)) {
      new LabCommObjectDialog(null, (LabCommObject)paramJGoObject, null).setVisible(true);
    }
  }
  
  public void remove() {}
  
  public void setupIcons()
  {
    this.tree.setCellRenderer(new MyRenderer());
  }
  
  private void createNodes(DefaultMutableTreeNode paramDefaultMutableTreeNode, JGoObjectCollection paramJGoObjectCollection)
  {
    if (paramJGoObjectCollection != null)
    {
      ArrayList localArrayList = new ArrayList();
      JGoListPosition localJGoListPosition = paramJGoObjectCollection.getFirstObjectPos();
      for (JGoObject localJGoObject1 = paramJGoObjectCollection.getObjectAtPos(localJGoListPosition); (localJGoObject1 != null) && (localJGoListPosition != null); localJGoObject1 = paramJGoObjectCollection.getObjectAtPos(localJGoListPosition))
      {
        localArrayList.add(localJGoObject1);
        localJGoListPosition = paramJGoObjectCollection.getNextObjectPosAtTop(localJGoListPosition);
      }
      Collections.sort(localArrayList, new Comparator()
      {
        public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
        {
          Point localPoint1 = ((JGoObject)paramAnonymousObject1).getLocation();
          Point localPoint2 = ((JGoObject)paramAnonymousObject2).getLocation();
          if (localPoint1.y != localPoint2.y) {
            return localPoint1.y - localPoint2.y;
          }
          return localPoint1.x - localPoint2.x;
        }
      });
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        JGoObject localJGoObject2 = (JGoObject)localIterator.next();
        if ((!(localJGoObject2 instanceof GCLink)) && ((!(paramJGoObjectCollection instanceof GCGroup)) || (!((GCGroup)paramJGoObjectCollection).isBoundingRect(localJGoObject2))))
        {
          DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode(localJGoObject2);
          if ((localJGoObject2 instanceof Hierarchical))
          {
            Hierarchical localHierarchical = (Hierarchical)localJGoObject2;
            createNodes(localDefaultMutableTreeNode, localHierarchical.getContentDocument());
          }
          else if ((localJGoObject2 instanceof GrafcetProcedure))
          {
            createNodes(localDefaultMutableTreeNode, ((GrafcetProcedure)localJGoObject2).myContentDocument);
          }
          else if ((localJGoObject2 instanceof GCGroup))
          {
            createNodes(localDefaultMutableTreeNode, (GCGroup)localJGoObject2);
          }
          paramDefaultMutableTreeNode.add(localDefaultMutableTreeNode);
        }
      }
    }
  }
  
  class MyRenderer
    extends DefaultTreeCellRenderer
  {
    public MyRenderer() {}
    
    public Component getTreeCellRendererComponent(JTree paramJTree, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt, boolean paramBoolean4)
    {
      super.getTreeCellRendererComponent(paramJTree, paramObject, paramBoolean1, paramBoolean2, paramBoolean3, paramInt, paramBoolean4);
      if (isDocument(paramObject))
      {
        DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
        Object localObject = localDefaultMutableTreeNode.getUserObject();
        GCDocument localGCDocument = (GCDocument)localObject;
        if (localGCDocument.owner != null)
        {
          if ((localGCDocument.owner instanceof StepFusionSet))
          {
            setIcon(TreeDisplay.stepfusionsetIcon);
            return this;
          }
          if ((localGCDocument.owner instanceof WorkspaceObject))
          {
            setIcon(TreeDisplay.workspaceObjectIcon);
            return this;
          }
          if ((localGCDocument.owner instanceof MacroStep))
          {
            setIcon(TreeDisplay.macrostepIcon);
            return this;
          }
          if ((localGCDocument.owner instanceof GrafcetProcedure))
          {
            setIcon(TreeDisplay.procedureIcon);
            return this;
          }
          if ((localGCDocument.owner instanceof XMLMessageIn))
          {
            setIcon(TreeDisplay.xmlmessageinIcon);
            return this;
          }
          if ((localGCDocument.owner instanceof XMLMessageOut))
          {
            setIcon(TreeDisplay.xmlmessageoutIcon);
            return this;
          }
        }
      }
      if (isStepFusionSet(paramObject))
      {
        setIcon(TreeDisplay.stepfusionsetIcon);
        return this;
      }
      if (isWorkspaceObject(paramObject))
      {
        setIcon(TreeDisplay.workspaceObjectIcon);
        return this;
      }
      if (isEnterStep(paramObject))
      {
        setIcon(TreeDisplay.enterstepIcon);
        return this;
      }
      if (isExitStep(paramObject))
      {
        setIcon(TreeDisplay.exitstepIcon);
        return this;
      }
      if (isInitialStep(paramObject))
      {
        setIcon(TreeDisplay.initialstepIcon);
        return this;
      }
      if (isProcessStep(paramObject))
      {
        setIcon(TreeDisplay.processstepIcon);
        return this;
      }
      if (isProcedureStep(paramObject))
      {
        setIcon(TreeDisplay.procedurestepIcon);
        return this;
      }
      if (isMacroStep(paramObject))
      {
        setIcon(TreeDisplay.macrostepIcon);
        return this;
      }
      if (isProcedure(paramObject))
      {
        setIcon(TreeDisplay.procedureIcon);
        return this;
      }
      if (isConnectionPostIn(paramObject))
      {
        setIcon(TreeDisplay.connectionpostinIcon);
        return this;
      }
      if (isConnectionPostOut(paramObject))
      {
        setIcon(TreeDisplay.connectionpostoutIcon);
        return this;
      }
      if (isParallelSplit(paramObject))
      {
        setIcon(TreeDisplay.parallelsplitIcon);
        return this;
      }
      if (isParallelJoin(paramObject))
      {
        setIcon(TreeDisplay.paralleljoinIcon);
        return this;
      }
      if (isStep(paramObject))
      {
        setIcon(TreeDisplay.stepIcon);
        return this;
      }
      if (isExceptionTransition(paramObject))
      {
        setIcon(TreeDisplay.exceptiontransitionIcon);
        return this;
      }
      if (isTransition(paramObject))
      {
        setIcon(TreeDisplay.transitionIcon);
        return this;
      }
      if (isFreeText(paramObject))
      {
        setIcon(TreeDisplay.freeTextIcon);
        return this;
      }
      if (isXMLMessageIn(paramObject))
      {
        setIcon(TreeDisplay.xmlmessageinIcon);
        return this;
      }
      if (isXMLMessageOut(paramObject))
      {
        setIcon(TreeDisplay.xmlmessageoutIcon);
        return this;
      }
      if (isIndexVariable(paramObject))
      {
        setIcon(TreeDisplay.indexvariableIcon);
        return this;
      }
      if (isIntegerAttribute(paramObject))
      {
        setIcon(TreeDisplay.integerattributeIcon);
        return this;
      }
      if (isRealAttribute(paramObject))
      {
        setIcon(TreeDisplay.realattributeIcon);
        return this;
      }
      if (isStringAttribute(paramObject))
      {
        setIcon(TreeDisplay.stringattributeIcon);
        return this;
      }
      if (isBooleanVariable(paramObject))
      {
        setIcon(TreeDisplay.booleanvariableIcon);
        return this;
      }
      if (isIntegerVariable(paramObject))
      {
        setIcon(TreeDisplay.integervariableIcon);
        return this;
      }
      if (isRealVariable(paramObject))
      {
        setIcon(TreeDisplay.realvariableIcon);
        return this;
      }
      if (isStringVariable(paramObject))
      {
        setIcon(TreeDisplay.stringvariableIcon);
        return this;
      }
      if (isStringList(paramObject))
      {
        setIcon(TreeDisplay.stringlistIcon);
        return this;
      }
      if (isIntegerList(paramObject))
      {
        setIcon(TreeDisplay.integerlistIcon);
        return this;
      }
      if (isRealList(paramObject))
      {
        setIcon(TreeDisplay.reallistIcon);
        return this;
      }
      if (isBooleanList(paramObject))
      {
        setIcon(TreeDisplay.booleanlistIcon);
        return this;
      }
      if (isRectangle(paramObject))
      {
        setIcon(TreeDisplay.rectangleIcon);
        return this;
      }
      if (isEllipse(paramObject))
      {
        setIcon(TreeDisplay.ellipseIcon);
        return this;
      }
      if (isPolygone(paramObject))
      {
        setIcon(TreeDisplay.polygoneIcon);
        return this;
      }
      if (isThreeDRectangle(paramObject))
      {
        setIcon(TreeDisplay.threedrectangleIcon);
        return this;
      }
      if (isLine(paramObject))
      {
        setIcon(TreeDisplay.lineIcon);
        return this;
      }
      if (isSpline(paramObject))
      {
        setIcon(TreeDisplay.splineIcon);
        return this;
      }
      if (isIcon(paramObject))
      {
        setIcon(TreeDisplay.iconIcon);
        return this;
      }
      if (isActionButton(paramObject))
      {
        setIcon(TreeDisplay.actionbuttonIcon);
        return this;
      }
      if (isGraphicalButton(paramObject))
      {
        setIcon(TreeDisplay.graphicalbuttonIcon);
        return this;
      }
      if (isPlotter(paramObject))
      {
        setIcon(TreeDisplay.plotterIcon);
        return this;
      }
      if (isBrowser(paramObject))
      {
        setIcon(TreeDisplay.browserIcon);
        return this;
      }
      if (isShowWorkspace(paramObject))
      {
        setIcon(TreeDisplay.showworkspaceIcon);
        return this;
      }
      if (isChemIcon(paramObject))
      {
        setIcon(TreeDisplay.chemIcon);
        return this;
      }
      if (isLucasIcon(paramObject))
      {
        setIcon(TreeDisplay.lucasIcon);
        return this;
      }
      if (isDigitalIn1(paramObject))
      {
        setIcon(TreeDisplay.digitalin1Icon);
        return this;
      }
      if (isDigitalIn(paramObject))
      {
        setIcon(TreeDisplay.digitalinIcon);
        return this;
      }
      if (isDigitalOut1(paramObject))
      {
        setIcon(TreeDisplay.digitalout1Icon);
        return this;
      }
      if (isDigitalOut(paramObject))
      {
        setIcon(TreeDisplay.digitaloutIcon);
        return this;
      }
      if (isAnalogIn(paramObject))
      {
        setIcon(TreeDisplay.analoginIcon);
        return this;
      }
      if (isAnalogOut(paramObject))
      {
        setIcon(TreeDisplay.analogoutIcon);
        return this;
      }
      if (isSocketBoolIn(paramObject))
      {
        setIcon(TreeDisplay.socketboolinIcon);
        return this;
      }
      if (isSocketIntIn(paramObject))
      {
        setIcon(TreeDisplay.socketintinIcon);
        return this;
      }
      if (isSocketRealIn(paramObject))
      {
        setIcon(TreeDisplay.socketrealinIcon);
        return this;
      }
      if (isSocketStringIn(paramObject))
      {
        setIcon(TreeDisplay.socketstringinIcon);
        return this;
      }
      if (isSocketBoolOut(paramObject))
      {
        setIcon(TreeDisplay.socketbooloutIcon);
        return this;
      }
      if (isSocketIntOut(paramObject))
      {
        setIcon(TreeDisplay.socketintoutIcon);
        return this;
      }
      if (isSocketRealOut(paramObject))
      {
        setIcon(TreeDisplay.socketrealoutIcon);
        return this;
      }
      if (isSocketStringOut(paramObject))
      {
        setIcon(TreeDisplay.socketstringoutIcon);
        return this;
      }
      if (isByteStreamIn(paramObject))
      {
        setIcon(TreeDisplay.byteStreamInIcon);
        return this;
      }
      if (isByteStreamOut(paramObject))
      {
        setIcon(TreeDisplay.byteStreamOutIcon);
        return this;
      }
      if (isDPWSObject(paramObject))
      {
        setIcon(TreeDisplay.dpwsObjectIcon);
        return this;
      }
      if (isLabCommObject(paramObject))
      {
        setIcon(TreeDisplay.labCommObjectIcon);
        return this;
      }
      return this;
    }
    
    protected boolean isWorkspaceObject(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof WorkspaceObject);
    }
    
    protected boolean isStep(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GCStep);
    }
    
    protected boolean isEnterStep(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof EnterStep);
    }
    
    protected boolean isExitStep(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ExitStep);
    }
    
    protected boolean isInitialStep(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GCStepInitial);
    }
    
    protected boolean isMacroStep(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof MacroStep);
    }
    
    protected boolean isProcedureStep(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ProcedureStep);
    }
    
    protected boolean isProcessStep(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ProcessStep);
    }
    
    protected boolean isProcedure(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GrafcetProcedure);
    }
    
    protected boolean isStepFusionSet(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof StepFusionSet);
    }
    
    protected boolean isParallelSplit(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ParallelSplit);
    }
    
    protected boolean isParallelJoin(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ParallelJoin);
    }
    
    protected boolean isConnectionPostIn(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ConnectionPostIn);
    }
    
    protected boolean isConnectionPostOut(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ConnectionPostOut);
    }
    
    protected boolean isTransition(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GCTransition);
    }
    
    protected boolean isExceptionTransition(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ExceptionTransition);
    }
    
    protected boolean isFreeText(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof FreeText);
    }
    
    protected boolean isBooleanVariable(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof BooleanVariable);
    }
    
    protected boolean isRealVariable(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof RealVariable);
    }
    
    protected boolean isIntegerVariable(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof IntegerVariable);
    }
    
    protected boolean isStringVariable(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof StringVariable);
    }
    
    protected boolean isStringList(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof StringList);
    }
    
    protected boolean isBooleanList(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof BooleanList);
    }
    
    protected boolean isRealList(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof RealList);
    }
    
    protected boolean isIntegerList(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof IntegerList);
    }
    
    protected boolean isRectangle(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GCRectangle);
    }
    
    protected boolean isEllipse(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GCEllipse);
    }
    
    protected boolean isPolygone(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GCPolygon);
    }
    
    protected boolean isThreeDRectangle(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GC3DRect);
    }
    
    protected boolean isLine(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      if ((localObject instanceof GCStroke))
      {
        GCStroke localGCStroke = (GCStroke)localObject;
        if (!localGCStroke.isCubic()) {
          return true;
        }
      }
      return false;
    }
    
    protected boolean isSpline(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      if ((localObject instanceof GCStroke))
      {
        GCStroke localGCStroke = (GCStroke)localObject;
        if (localGCStroke.isCubic()) {
          return true;
        }
      }
      return false;
    }
    
    protected boolean is3DRectangle(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GC3DRect);
    }
    
    protected boolean isIcon(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof Icon);
    }
    
    protected boolean isActionButton(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof AssignmentButton);
    }
    
    protected boolean isGraphicalButton(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GraphicalButton);
    }
    
    protected boolean isPlotter(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof SFCPlotter);
    }
    
    protected boolean isBrowser(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof Browser);
    }
    
    protected boolean isShowWorkspace(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ShowWorkspaceButton);
    }
    
    protected boolean isChemIcon(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ChemIcon);
    }
    
    protected boolean isLucasIcon(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof LUCASLogo);
    }
    
    protected boolean isXMLMessageIn(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof XMLMessageIn);
    }
    
    protected boolean isXMLMessageOut(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof XMLMessageOut);
    }
    
    protected boolean isIndexVariable(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof IndexVariable);
    }
    
    protected boolean isIntegerAttribute(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof IntegerAttributeVariable);
    }
    
    protected boolean isStringAttribute(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof StringAttributeVariable);
    }
    
    protected boolean isDocument(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof GCDocument);
    }
    
    protected boolean isRealAttribute(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof RealAttributeVariable);
    }
    
    protected boolean isDigitalIn(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof DigitalIn);
    }
    
    protected boolean isDigitalIn1(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof DigitalIn1);
    }
    
    protected boolean isDigitalOut(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof DigitalOut);
    }
    
    protected boolean isDigitalOut1(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof DigitalOut1);
    }
    
    protected boolean isAnalogIn(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof AnalogIn);
    }
    
    protected boolean isAnalogOut(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof AnalogOut);
    }
    
    protected boolean isSocketBoolIn(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof SocketBooleanIn);
    }
    
    protected boolean isSocketIntIn(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof SocketIntIn);
    }
    
    protected boolean isSocketRealIn(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof SocketRealIn);
    }
    
    protected boolean isSocketStringIn(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof SocketStringIn);
    }
    
    protected boolean isSocketBoolOut(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof SocketBoolOut);
    }
    
    protected boolean isSocketIntOut(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof SocketIntOut);
    }
    
    protected boolean isSocketRealOut(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof SocketRealOut);
    }
    
    protected boolean isSocketStringOut(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof SocketStringOut);
    }
    
    protected boolean isByteStreamIn(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ByteStreamIn);
    }
    
    protected boolean isByteStreamOut(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof ByteStreamOut);
    }
    
    protected boolean isDPWSObject(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof DPWSObject);
    }
    
    protected boolean isLabCommObject(Object paramObject)
    {
      DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)paramObject;
      Object localObject = localDefaultMutableTreeNode.getUserObject();
      return (localObject instanceof LabCommObject);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/TreeDisplay.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */