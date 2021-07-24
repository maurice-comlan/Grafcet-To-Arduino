package grafchart.sfc;

import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoDocument;
import com.nwoods.jgo.JGoDocumentEvent;
import com.nwoods.jgo.JGoDocumentListener;
import com.nwoods.jgo.JGoGridView;
import com.nwoods.jgo.JGoLink;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoSelection;
import com.nwoods.jgo.JGoStroke;
import com.nwoods.jgo.JGoViewEvent;
import com.nwoods.jgo.JGoViewListener;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import javax.swing.JInternalFrame;
import javax.swing.JScrollBar;

/**
 * Vues rerésentant abritant un document
 * @author dimon
 */
public class GCView
  extends JGoGridView
  implements JGoViewListener, MouseWheelListener
{
  private int stepCounter = 0;
  protected Point myDefaultLocation = new Point(10, 10);
  protected Editor myApp = null;
  public JInternalFrame myInternalFrame = null;
  public JScrollBar horizontalScrollBar = null;
  public JScrollBar verticalScrollBar = null;
  public boolean hasHorizontalScrollBar = true;
  public boolean hasVerticalScrollBar = true;
  public int layer = 1;
  public static int offsetLevel = 1;
  public boolean pastePointSet = false;
  public int pastePointX = 0;
  public int pastePointY = 0;
  private JGoRectangle myGhost = new JGoRectangle(new Point(), new Dimension(10, 10));
  private static final int MOUSE_STATE_NONE = 0;
  private static final int MOUSE_STATE_ROUTING_LINK = 1;
  private static final int STYLE_STATE_STROKE = 0;
  private static final int STYLE_STATE_SPLINE = 1;
  private static int styleState = 1;
  private static int mouseState = 0;
  private JGoObject currentObject = null;
  public static JGoBrush standardBrush = new JGoBrush();
  transient ArrayList myMoveArrayList = new ArrayList();
  
  public GCView()
  {
    init();
  }
  
  public GCView(GCDocument paramGCDocument)
  {
    super(paramGCDocument);
    init();
  }
  
  public GCView(GCDocument paramGCDocument, JInternalFrame paramJInternalFrame)
  {
    super(paramGCDocument);
    init();
    this.myInternalFrame = paramJInternalFrame;
  }
  
  private void init()
  {
    setSnapMove(1);
    setGridWidth(10);
    setGridHeight(10);
    setKeyEnabled(true);
    setDragsRealtime(true);
    setDragsSelectionImage(true);
    setDefaultPortGravity(150);
    setIncludingNegativeCoords(true);
    getDoc().setModifiable(true);
    getDoc().setView(this);
    this.hasHorizontalScrollBar = true;
    this.horizontalScrollBar = getHorizontalScrollBar();
    this.hasVerticalScrollBar = true;
    this.verticalScrollBar = getVerticalScrollBar();
    addMouseWheelListener(this);
    setDefaultSecondarySelectionColor(getDefaultPrimarySelectionColor());
  }
  
  public GCDocument getDoc()
  {
    return (GCDocument)getDocument();
  }
  
  @Override
  public void moveSelection(JGoSelection paramJGoSelection, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((getCurrentObject() instanceof AssignmentButton)) {
      ((AssignmentButton)getCurrentObject()).doUnpress();
    }
    int i = getSnapMove();
    if ((i == 1) || ((i == 2) && (paramInt4 == 3)))
    {
      Point localPoint1 = new Point();
      ArrayList localArrayList = computeEffectiveSelection(paramJGoSelection);
      Object localObject1 = null;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        JGoObject localJGoObject1 = (JGoObject)localIterator.next();
        if (!(localJGoObject1 instanceof GCLink))
        {
          localObject1 = localJGoObject1;
          break;
        }
      }
      int j = 0;
      int k = 0;
      int n;
      if (localObject1 != null)
      {
          Point localObject2 = ((JGoObject)localObject1).getLocation(localPoint1);
        int m = ((Point)localObject2).x;
        n = ((Point)localObject2).y;
        findNearestGridPoint(m + paramInt2, n + paramInt3, localPoint1);
        Utils.locationToTopLeft((JGoObject)localObject1, localPoint1);
        Rectangle localRectangle = ((JGoObject)localObject1).getBoundingRect();
        j = localPoint1.x - localRectangle.x;
        k = localPoint1.y - localRectangle.y;
      }
      Object localObject2 = localArrayList.iterator();
      JGoObject localJGoObject2;
      int i1;
      while (((Iterator)localObject2).hasNext())
      {
        localJGoObject2 = (JGoObject)((Iterator)localObject2).next();
        if ((localJGoObject2 instanceof GCLink))
        {
          n = localJGoObject2.getLeft();
          i1 = localJGoObject2.getTop();
          localJGoObject2.setBoundingRect(n + j, i1 + k, localJGoObject2.getWidth(), localJGoObject2.getHeight());
        }
      }
      localObject2 = localArrayList.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localJGoObject2 = (JGoObject)((Iterator)localObject2).next();
        if (localJGoObject2.isDraggable())
        {
          Point localPoint2 = localJGoObject2.getLocation(localPoint1);
          i1 = localPoint2.x + paramInt2;
          int i2 = localPoint2.y + paramInt3;
          if ((getGridHeight() > 0) && (getGridWidth() > 0)) {
            findNearestGridPoint(i1, i2, localPoint1);
          }
          Utils.locationToTopLeft(localJGoObject2, localPoint1);
          localJGoObject2.setTopLeft(localPoint1);
        }
      }
    }
    else
    {
      super.moveSelection(paramJGoSelection, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public void mySnapObject(JGoObject paramJGoObject)
  {
    Point localPoint1 = new Point();
    JGoObject localJGoObject = paramJGoObject.getTopLevelObject();
    if (localJGoObject == paramJGoObject)
    {
      Point localPoint2 = paramJGoObject.getLocation(localPoint1);
      findNearestGridPoint(localPoint2.x, localPoint2.y, localPoint1);
      paramJGoObject.setLocation(localPoint1);
    }
  }
  
  public Editor getBasicApp()
  {
    return this.myApp;
  }
  
  public JInternalFrame getInternalFrame()
  {
    return this.myInternalFrame;
  }
  
  public void initialize(Editor paramEditor, JInternalFrame paramJInternalFrame, boolean paramBoolean)
  {
    GCDocument.defaultIcon = paramJInternalFrame.getFrameIcon();
    this.myApp = paramEditor;
    this.myInternalFrame = paramJInternalFrame;
    addViewListener(this);
    updateTitle(paramBoolean);
    addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent paramAnonymousKeyEvent)
      {
        int i = paramAnonymousKeyEvent.getKeyCode();
        Object localObject;
        if (i == KeyEvent.VK_DELETE)
        {
          if (!GCView.this.getDoc().executing)
          {
            localObject = GCView.this.getSelection();
            GCView.this.deleteHierarchies((JGoSelection)localObject);
            GCView.this.deleteSelection();
          }
        }
        else if (i == KeyEvent.VK_CONTEXT_MENU)
        {
          localObject = GCView.this.getSelection().getPrimarySelection();
          if (localObject != null)
          {
            Editor.singleton.selectedObject = ((JGoObject)localObject);
            Editor.updateActions();
            Editor.singleton.callDialog((JGoObject)localObject, ((JGoObject)localObject).getSpotLocation(4));
          }
        }
      }
    });
    getDocument().addDocumentListener(new JGoDocumentListener()
    {
      public void documentChanged(JGoDocumentEvent paramAnonymousJGoDocumentEvent)
      {
        if (paramAnonymousJGoDocumentEvent.getHint() == 65536) {
          GCView.this.updateTitle();
        }
        GCView.this.myApp.processDocChange(paramAnonymousJGoDocumentEvent);
      }
    });
  }
  
  public void initialize(Editor paramEditor, JInternalFrame paramJInternalFrame)
  {
    initialize(paramEditor, paramJInternalFrame, false);
  }
  
  public void deleteSelectionSpecial()
  {
    JGoSelection localJGoSelection = getSelection();
    deleteHierarchies(localJGoSelection);
    deleteSelection();
  }
  
  public void setStepCounter(int paramInt)
  {
    this.stepCounter = paramInt;
  }
  
  public int getStepCounter()
  {
    return this.stepCounter;
  }
  
  public void updateTitle()
  {
    updateTitle(false);
  }
  
  public void updateTitle(boolean paramBoolean)
  {
    if (getInternalFrame() != null)
    {
      String str = getDoc().getFullName();
      if (paramBoolean) {
        str = "PC: " + str + " (" + getDoc().getName() + ")";
      } else if (getDoc().owner == null) {
        //str = "Top: " + str; On pourait remplacer le top par le langage du doc like G7
        str = "" + str;
      }
      getInternalFrame().setTitle(str);
      getInternalFrame().repaint();
    }
  }
  
  public void removeSockets(JGoSelection paramJGoSelection)
  {
    JGoListPosition localJGoListPosition = paramJGoSelection.getFirstObjectPos();
    for (JGoObject localJGoObject = paramJGoSelection.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = paramJGoSelection.getObjectAtPos(localJGoListPosition))
    {
      if ((localJGoObject instanceof SocketIn))
      {
        SocketIn localSocketIn = (SocketIn)localJGoObject;
        SocketIn.removeSocketInput(localSocketIn);
      }
      localJGoListPosition = paramJGoSelection.getNextObjectPos(localJGoListPosition);
    }
  }
  
  public void deleteHierarchies(JGoSelection paramJGoSelection)
  {
    JGoListPosition localJGoListPosition = paramJGoSelection.getFirstObjectPos();
    for (JGoObject localJGoObject = paramJGoSelection.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = paramJGoSelection.getObjectAtPos(localJGoListPosition))
    {
      Object localObject;
      if ((localJGoObject instanceof MacroStep))
      {
        localObject = (MacroStep)localJGoObject;
        if (((MacroStep)localObject).myContentDocument != null) {
          deleteHierarchyDocs(((MacroStep)localObject).myContentDocument);
        }
        if (((MacroStep)localObject).frame != null) {
          try
          {
            ((MacroStep)localObject).frame.setClosed(true);
          }
          catch (Exception localException1) {}
        }
      }
      if ((localJGoObject instanceof GrafcetProcedure))
      {
        localObject = (GrafcetProcedure)localJGoObject;
        deleteHierarchyDocs(((GrafcetProcedure)localObject).myContentDocument);
        if (((GrafcetProcedure)localObject).frame != null) {
          try
          {
            ((GrafcetProcedure)localObject).frame.setClosed(true);
          }
          catch (Exception localException2) {}
        }
      }
      if ((localJGoObject instanceof WorkspaceObject))
      {
        localObject = (WorkspaceObject)localJGoObject;
        deleteHierarchyDocs(((WorkspaceObject)localObject).myContentDocument);
        if (((WorkspaceObject)localObject).frame != null) {
          try
          {
            ((WorkspaceObject)localObject).frame.setClosed(true);
          }
          catch (Exception localException3) {}
        }
      }
      if ((localJGoObject instanceof SFCPlotter))
      {
        localObject = (SFCPlotter)localJGoObject;
        if (((SFCPlotter)localObject).frame != null) {
          try
          {
            ((SFCPlotter)localObject).frame.setClosed(true);
            ((SFCPlotter)localObject).frame = null;
          }
          catch (Exception localException4) {}
        }
      }
      localJGoListPosition = paramJGoSelection.getNextObjectPos(localJGoListPosition);
    }
  }
  
  public void deleteHierarchyDocs(GCDocument paramGCDocument)
  {
    JGoListPosition localJGoListPosition = paramGCDocument.getFirstObjectPos();
    for (JGoObject localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = paramGCDocument.getObjectAtPos(localJGoListPosition))
    {
      Object localObject;
      if (((localJGoObject instanceof MacroStep)) && (!(localJGoObject instanceof ProcedureStep)))
      {
        localObject = (MacroStep)localJGoObject;
        deleteHierarchyDocs(((MacroStep)localObject).myContentDocument);
        if (((MacroStep)localObject).frame != null)
        {
          try
          {
            ((MacroStep)localObject).frame.setClosed(true);
          }
          catch (Exception localException1) {}
          ((MacroStep)localObject).frame = null;
          Editor.myCurrentView = ((MacroStep)localObject).parentView;
          Editor.myCurrentView.requestFocus();
          AppAction.updateAllActions();
        }
      }
      if ((localJGoObject instanceof GrafcetProcedure))
      {
        localObject = (GrafcetProcedure)localJGoObject;
        deleteHierarchyDocs(((GrafcetProcedure)localObject).myContentDocument);
        if (((GrafcetProcedure)localObject).frame != null)
        {
          try
          {
            ((GrafcetProcedure)localObject).frame.setClosed(true);
          }
          catch (Exception localException2) {}
          ((GrafcetProcedure)localObject).frame = null;
          Editor.myCurrentView = ((GrafcetProcedure)localObject).parentView;
          Editor.myCurrentView.requestFocus();
          AppAction.updateAllActions();
        }
      }
      if ((localJGoObject instanceof WorkspaceObject))
      {
        localObject = (WorkspaceObject)localJGoObject;
        deleteHierarchyDocs(((WorkspaceObject)localObject).myContentDocument);
        if (((WorkspaceObject)localObject).frame != null)
        {
          try
          {
            ((WorkspaceObject)localObject).frame.setClosed(true);
          }
          catch (Exception localException3) {}
          ((WorkspaceObject)localObject).frame = null;
          Editor.myCurrentView = ((WorkspaceObject)localObject).parentView;
          Editor.myCurrentView.requestFocus();
          AppAction.updateAllActions();
        }
      }
      if ((localJGoObject instanceof SFCPlotter))
      {
        localObject = (SFCPlotter)localJGoObject;
        if (((SFCPlotter)localObject).frame != null)
        {
          try
          {
            ((SFCPlotter)localObject).frame.setClosed(true);
          }
          catch (Exception localException4) {}
          ((SFCPlotter)localObject).frame = null;
          Editor.myCurrentView = ((SFCPlotter)localObject).parentView;
          Editor.myCurrentView.requestFocus();
          AppAction.updateAllActions();
        }
      }
      localJGoListPosition = paramGCDocument.getNextObjectPos(localJGoListPosition);
    }
  }
  
  public void viewChanged(JGoViewEvent paramJGoViewEvent)
  {
    this.myApp.processViewChange(paramJGoViewEvent);
  }
  
  public void dragOver(DropTargetDragEvent paramDropTargetDragEvent)
  {
    super.dragOver(paramDropTargetDragEvent);
    if (paramDropTargetDragEvent.getDropAction() != 0)
    {
      if (this.myGhost.getView() != this) {
        addObjectAtTail(this.myGhost);
      }
      this.myGhost.setTopLeft(viewToDocCoords(paramDropTargetDragEvent.getLocation()));
    }
  }
  
  public void dragExit(DropTargetEvent paramDropTargetEvent)
  {
    if (this.myGhost.getView() == this) {
      removeObject(this.myGhost);
    }
    super.dragExit(paramDropTargetEvent);
  }
  
  public boolean isDropFlavorAcceptable(DropTargetDragEvent paramDropTargetDragEvent)
  {
    return (super.isDropFlavorAcceptable(paramDropTargetDragEvent)) || (paramDropTargetDragEvent.isDataFlavorSupported(DataFlavor.stringFlavor));
  }
  
  public void newLink(JGoPort paramJGoPort1, JGoPort paramJGoPort2)
  {
    if ((!connected(paramJGoPort1, paramJGoPort2)) && (!multipleConnections(paramJGoPort1, paramJGoPort2)) && (!getDoc().executing))
    {
      if (((paramJGoPort1.getTopLevelObject() instanceof GCGroup)) && (((GCGroup)paramJGoPort1.getTopLevelObject()).isHidden())) {
        return;
      }
      if (((paramJGoPort2.getTopLevelObject() instanceof GCGroup)) && (((GCGroup)paramJGoPort2.getTopLevelObject()).isHidden())) {
        return;
      }
      GCDocument localGCDocument = getDoc();
      GCLink localGCLink = new GCLink(paramJGoPort1, paramJGoPort2);
      if (((paramJGoPort1 instanceof GCStepExceptionOutPort)) || ((paramJGoPort2 instanceof GCStepHistoryInPort))) {
        localGCLink.setWide();
      }
      localGCDocument.addObjectAtTail(localGCLink);
      localGCDocument.endTransaction("new GCLink");
    }
  }
  
  private boolean multipleConnections(JGoPort paramJGoPort1, JGoPort paramJGoPort2)
  {
    return (((paramJGoPort1.getParent() instanceof GenericTransition)) && (paramJGoPort1.getNumLinks() != 0)) || (((paramJGoPort2.getParent() instanceof GenericTransition)) && (paramJGoPort2.getNumLinks() != 0)) || (((paramJGoPort1.getParent() instanceof ParallelSplit)) && (paramJGoPort1.getNumLinks() != 0)) || (((paramJGoPort2.getParent() instanceof ParallelJoin)) && (paramJGoPort2.getNumLinks() != 0));
  }
  
  public boolean connected(JGoPort paramJGoPort1, JGoPort paramJGoPort2)
  {
    boolean bool = false;
    for (JGoListPosition localJGoListPosition = paramJGoPort1.getFirstLinkPos(); localJGoListPosition != null; localJGoListPosition = paramJGoPort1.getNextLinkPos(localJGoListPosition))
    {
      JGoLink localJGoLink = paramJGoPort1.getLinkAtPos(localJGoListPosition);
      bool = (bool) || (paramJGoPort2 == localJGoLink.getToPort());
    }
    return bool;
  }
  
  public boolean validLink(JGoPort paramJGoPort1, JGoPort paramJGoPort2)
  {
    return paramJGoPort1.validLink(paramJGoPort2);
  }
  
  public void drop(DropTargetDropEvent paramDropTargetDropEvent)
  {
    JGoCopyEnvironment localJGoCopyEnvironment = getDoc().createDefaultCopyEnvironment();
    getDocument().startTransaction();
    if (doDrop(paramDropTargetDropEvent, localJGoCopyEnvironment))
    {
      Iterator localIterator = localJGoCopyEnvironment.values().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        JGoObject localJGoObject = (JGoObject)localObject;
        mySnapObject(localJGoObject);
        if ((localObject instanceof GCStep))
        {
          GCStep localGCStep = (GCStep)localObject;
          GCDocument localGCDocument = getDoc();
          localGCDocument.setSuspendUpdates(true);
          localGCDocument.addObjectAtTail(localGCStep);
          localGCDocument.setSuspendUpdates(false);
        }
      }
      getDocument().endTransaction("drop");
    }
    else
    {
      paramDropTargetDropEvent.rejectDrop();
      getDocument().endTransaction(false);
    }
    Editor.singleton
            .setCurrentView(this);
  }
  
  public JGoObject getTopLeftObject(JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    Object localObject = null;
    Iterator localIterator = paramJGoCopyEnvironment.entrySet().iterator();
    while (localIterator.hasNext())
    {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
      JGoObject localJGoObject = (JGoObject)localEntry.getValue();
      if ((localJGoObject.isTopLevel()) && (!(localJGoObject instanceof JGoLink)) && ((localObject == null) || ((localJGoObject.getLocation().x < ((JGoObject)localObject).getLocation().x) && (localJGoObject.getLocation().y < localJGoObject.getLocation().y)))) {
        localObject = localJGoObject;
      }
    }
    return (JGoObject)localObject;
  }
  
  public void removeHandlesFromSelection()
  {
    JGoSelection localJGoSelection = getSelection();
    JGoListPosition localJGoListPosition = localJGoSelection.getFirstObjectPos();
    for (JGoObject localJGoObject = localJGoSelection.getObjectAtPos(localJGoListPosition); (localJGoObject != null) && (localJGoListPosition != null); localJGoObject = localJGoSelection.getObjectAtPos(localJGoListPosition))
    {
      localJGoSelection.hideHandles(localJGoObject);
      localJGoSelection.clearSelectionHandles(localJGoObject);
      localJGoSelection.clearSelectionHandles(null);
      localJGoSelection.deleteHandles(localJGoObject);
      localJGoListPosition = localJGoSelection.getNextObjectPos(localJGoListPosition);
    }
  }
  
  public JGoCopyEnvironment pasteFromClipboard(Clipboard paramClipboard)
  {
    JGoCopyEnvironment localJGoCopyEnvironment = super.pasteFromClipboard(paramClipboard);
    if (localJGoCopyEnvironment != null)
    {
      getSelection().clearSelection();
      Object localObject;
      if (!this.pastePointSet)
      {
        localObject = localJGoCopyEnvironment.entrySet().iterator();
        Map.Entry localEntry1;
        JGoObject localJGoObject1;
        Point localPoint1;
        while (((Iterator)localObject).hasNext())
        {
          localEntry1 = (Map.Entry)((Iterator)localObject).next();
          localJGoObject1 = (JGoObject)localEntry1.getValue();
          if ((localJGoObject1.isTopLevel()) && ((localJGoObject1 instanceof JGoLink)))
          {
            localPoint1 = localJGoObject1.getLocation();
            localJGoObject1.setLocation((int)localPoint1.getX() + offsetLevel * getGridWidth(), (int)localPoint1.getY() + offsetLevel * getGridHeight());
            getSelection().extendSelection(localJGoObject1);
          }
        }
        localObject = localJGoCopyEnvironment.entrySet().iterator();
        while (((Iterator)localObject).hasNext())
        {
          localEntry1 = (Map.Entry)((Iterator)localObject).next();
          localJGoObject1 = (JGoObject)localEntry1.getValue();
          if ((localJGoObject1.isTopLevel()) && (!(localJGoObject1 instanceof JGoLink)))
          {
            localPoint1 = localJGoObject1.getLocation();
            localJGoObject1.setLocation((int)localPoint1.getX() + offsetLevel * getGridWidth(), (int)localPoint1.getY() + offsetLevel * getGridHeight());
            getSelection().extendSelection(localJGoObject1);
          }
        }
        offsetLevel += 1;
      }
      else
      {
        localObject = getTopLeftObject(localJGoCopyEnvironment);
        int i = ((JGoObject)localObject).getLocation().x;
        int j = ((JGoObject)localObject).getLocation().y;
        int k = this.pastePointX - i;
        int m = this.pastePointY - j;
        Iterator localIterator = localJGoCopyEnvironment.entrySet().iterator();
        Map.Entry localEntry2;
        JGoObject localJGoObject2;
        Point localPoint2;
        while (localIterator.hasNext())
        {
          localEntry2 = (Map.Entry)localIterator.next();
          localJGoObject2 = (JGoObject)localEntry2.getValue();
          if ((localJGoObject2.isTopLevel()) && ((localJGoObject2 instanceof JGoLink)))
          {
            getSelection().extendSelection(localJGoObject2);
            localPoint2 = localJGoObject2.getLocation();
            localJGoObject2.setLocation((int)localPoint2.getX() + k, (int)localPoint2.getY() + m);
            getSelection().extendSelection(localJGoObject2);
          }
        }
        localIterator = localJGoCopyEnvironment.entrySet().iterator();
        while (localIterator.hasNext())
        {
          localEntry2 = (Map.Entry)localIterator.next();
          localJGoObject2 = (JGoObject)localEntry2.getValue();
          if ((localJGoObject2.isTopLevel()) && (!(localJGoObject2 instanceof JGoLink)))
          {
            localPoint2 = localJGoObject2.getLocation();
            localJGoObject2.setLocation((int)localPoint2.getX() + k, (int)localPoint2.getY() + m);
            getSelection().extendSelection(localJGoObject2);
          }
        }
      }
    }
    else if (paramClipboard.getContents(this) == null)
    {
      Utils.writeInformation("Nothing to paste.");
    }
    else
    {
      Utils.writeError("Paste failed. Unsupported clipboard content.");
    }
    return localJGoCopyEnvironment;
  }
  
  void toggleRoutingLink(boolean paramBoolean)
  {
    if (mouseState != 1)
    {
      mouseState = 1;
      if (paramBoolean) {
        styleState = 1;
      } else {
        styleState = 0;
      }
      this.currentObject = null;
      setCursor(Cursor.getPredefinedCursor(1));
    }
    else
    {
      endRoutedLink();
    }
  }
  
  public void doBackgroundClick(int paramInt, Point paramPoint1, Point paramPoint2)
  {
    if ((paramInt & 0x4) != 0)
    {
      Editor.myCurrentView = this;
      AppAction.updateAllActions();
      this.myApp.gridAction();
    }
    else
    {
      Editor.myCurrentView = this;
      AppAction.updateAllActions();
    }
  }
  
  public boolean doMouseDblClick(int paramInt, Point paramPoint1, Point paramPoint2)
  {
    if (mouseState == 1)
    {
      endRoutedLink();
      return true;
    }
    return super.doMouseDblClick(paramInt, paramPoint1, paramPoint2);
  }
  
  public Cursor getDefaultCursor()
  {
    return this.myApp.getCursor();
  }
  
  public boolean doMouseDown(int paramInt, Point paramPoint1, Point paramPoint2)
  {
    if (mouseState == 1)
    {
      if (this.currentObject == null) {
        startRoutedLink(paramPoint1);
      } else {
        addPointToRoutedLink(paramPoint1);
      }
      return true;
    }
    if ((getGridHeight() > 0) && (getGridWidth() > 0))
    {
      Point localPoint = findNearestGridPoint(paramPoint1.x, paramPoint1.y, null);
      this.pastePointX = localPoint.x;
      this.pastePointY = localPoint.y;
    }
    else
    {
      this.pastePointX = paramPoint1.x;
      this.pastePointY = paramPoint1.y;
    }
    this.pastePointSet = true;
    boolean bool = super.doMouseDown(paramInt, paramPoint1, paramPoint2);
    if ((getCurrentObject() instanceof AssignmentButton)) {
      ((AssignmentButton)getCurrentObject()).doPress(paramInt);
    }
    return bool;
  }
  
  public boolean doMouseUp(int paramInt, Point paramPoint1, Point paramPoint2)
  {
    if ((paramInt & 0x4) != 0) // Relachement du click droite cela ouvre le menu contextuel)
    {
      JGoObject localJGoObject = pickDocObject(paramPoint1, true);
      if (localJGoObject != null)
      {
        doCancelMouse();
        this.myApp.selectedObject = localJGoObject;
        AppAction.updateAllActions();
        this.myApp.callDialog(localJGoObject, paramPoint2);
        return true;
      }
    }
    if ((getCurrentObject() instanceof AssignmentButton)) {
      ((AssignmentButton)getCurrentObject()).doUnpress();
    }
    return super.doMouseUp(paramInt, paramPoint1, paramPoint2);
  }
  
  void startRoutedLink(Point paramPoint)
  {
    GCStroke localGCStroke = new GCStroke();
    if (styleState == 1) {
      localGCStroke.setCubic(true);
    }
    localGCStroke.addPoint(paramPoint);
    localGCStroke.addPoint(paramPoint);
    addObjectAtTail(localGCStroke);
    this.currentObject = localGCStroke;
  }
  
  public boolean doMouseMove(int paramInt, Point paramPoint1, Point paramPoint2)
  {
    if (mouseState == 1)
    {
      followPointerForRoutedLink(paramInt, paramPoint1);
      return true;
    }
    return super.doMouseMove(paramInt, paramPoint1, paramPoint2);
  }
  
  void followPointerForRoutedLink(int paramInt, Point paramPoint)
  {
    JGoStroke localJGoStroke = (JGoStroke)this.currentObject;
    if (localJGoStroke != null)
    {
      int i = localJGoStroke.getNumPoints();
      if (((paramInt & 0x2) != 0) && (i > 1))
      {
        Point localPoint1 = localJGoStroke.getPoint(i - 2);
        Point localPoint2 = new Point(paramPoint);
        if (Math.abs(paramPoint.x - localPoint1.x) > Math.abs(paramPoint.y - localPoint1.y)) {
          localPoint2.y = localPoint1.y;
        } else {
          localPoint2.x = localPoint1.x;
        }
        localJGoStroke.setPoint(i - 1, localPoint2);
      }
      else
      {
        localJGoStroke.setPoint(i - 1, paramPoint);
      }
    }
  }
  
  void addPointToRoutedLink(Point paramPoint)
  {
    JGoStroke localJGoStroke = (JGoStroke)this.currentObject;
    if (localJGoStroke != null) {
      localJGoStroke.addPoint(paramPoint);
    }
  }
  
  public void onKeyEvent(KeyEvent paramKeyEvent)
  {
    int i = paramKeyEvent.getKeyCode();
    if (i == 27) {
      doCancelMouse();
    } else {
      super.onKeyEvent(paramKeyEvent);
    }
  }
  
  public void doCancelMouse()
  {
    if (mouseState == 1) {
      endRoutedLink();
    } else {
      super.doCancelMouse();
    }
  }
  
  public boolean closePoints(GCStroke paramGCStroke)
  {
    Point localPoint1 = new Point();
    Point localPoint2 = new Point();
    if ((getGridHeight() > 0) && (getGridHeight() > 0))
    {
      findNearestGridPoint(paramGCStroke.getPoint(0).x, paramGCStroke.getPoint(0).y, localPoint1);
      findNearestGridPoint(paramGCStroke.getPoint(paramGCStroke.getNumPoints() - 1).x, paramGCStroke.getPoint(paramGCStroke.getNumPoints() - 1).y, localPoint2);
      return (localPoint1.x == localPoint2.x) && (localPoint1.y == localPoint2.y);
    }
    return (paramGCStroke.getPoint(0).x == paramGCStroke.getPoint(paramGCStroke.getNumPoints() - 1).x) && (paramGCStroke.getPoint(0).y == paramGCStroke.getPoint(paramGCStroke.getNumPoints() - 1).y);
  }
  
  void endRoutedLink()
  {
    if (this.currentObject != null)
    {
      GCStroke localGCStroke = (GCStroke)this.currentObject;
      localGCStroke.removePoint(localGCStroke.getNumPoints() - 1);
      removeObject(localGCStroke);
      if (closePoints(localGCStroke))
      {
        localGCStroke.removePoint(localGCStroke.getNumPoints() - 1);
        GCPolygon localGCPolygon = new GCPolygon();
        if (styleState == 1) {
          localGCPolygon.setCubic(true);
        }
        localGCPolygon.setBrush(new JGoBrush(65535, new Color(1.0F, 1.0F, 1.0F)));
        Vector localVector = localGCStroke.getPoints();
        localGCPolygon.setPoints(localVector);
        getDoc().addObjectAtTail(localGCPolygon);
        localGCStroke = null;
      }
      else
      {
        getDoc().addObjectAtTail(localGCStroke);
      }
      this.currentObject = null;
      setCursor(getDefaultCursor());
      mouseState = 0;
    }
  }
  
  public void doUncapturedMouseMove(int paramInt, Point paramPoint1, Point paramPoint2)
  {
    if (ConnectionPost.colored1 != null)
    {
      ConnectionPost.colored1.myRectangle.setBrush(standardBrush);
      ConnectionPost.colored2.myRectangle.setBrush(standardBrush);
    }
    super.doUncapturedMouseMove(paramInt, paramPoint1, paramPoint2);
  }
  
  public void mouseWheelMoved(MouseWheelEvent paramMouseWheelEvent)
  {
    Object localObject;
    if (paramMouseWheelEvent.isControlDown())
    {
      localObject = paramMouseWheelEvent.getPoint();
      convertViewToDoc((Point)localObject);
      if (paramMouseWheelEvent.getWheelRotation() > 0) {
        Editor.singleton.zoomOutAction();
      } else {
        Editor.singleton.zoomInAction();
      }
      Point localPoint = paramMouseWheelEvent.getPoint();
      convertViewToDoc(localPoint);
      setViewPosition(getViewPosition().x + (((Point)localObject).x - localPoint.x), getViewPosition().y + (((Point)localObject).y - localPoint.y));
    }
    else
    {
      if (paramMouseWheelEvent.isShiftDown()) {
        localObject = getHorizontalScrollBar();
      } else {
        localObject = getVerticalScrollBar();
      }
      if (localObject == null) {
        return;
      }
      int i = paramMouseWheelEvent.getWheelRotation();
      int j;
      if (paramMouseWheelEvent.getScrollType() == 1) {
        j = ((JScrollBar)localObject).getBlockIncrement(i);
      } else {
        j = ((JScrollBar)localObject).getUnitIncrement(i);
      }
      ((JScrollBar)localObject).setValue(((JScrollBar)localObject).getValue() + i * j);
    }
  }
  
  public void copySelection(JGoSelection paramJGoSelection, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramJGoSelection == null) {
      paramJGoSelection = getSelection();
    }
    if (paramJGoSelection.isEmpty()) {
      return;
    }
    int i = 0;
    int j = getSnapMove();
    if ((j == 1) || ((j == 2) && (paramInt4 == 3))) {
      i = 1;
    }
    JGoDocument localJGoDocument = getDocument();
    Point localPoint1 = new Point(paramInt2, paramInt3);
    JGoCopyEnvironment localJGoCopyEnvironment = localJGoDocument.createDefaultCopyEnvironment();
    localJGoDocument.copyFromCollection(paramJGoSelection, localPoint1, localJGoCopyEnvironment);
    ArrayList localArrayList = this.myMoveArrayList;
    localArrayList.clear();
    Iterator localIterator = localJGoCopyEnvironment.entrySet().iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((localEntry.getKey() instanceof JGoObject))
      {
        localObject = (JGoObject)localEntry.getKey();
        if ((paramJGoSelection.isSelected((JGoObject)localObject)) && ((localEntry.getValue() instanceof JGoObject)))
        {
          JGoObject localJGoObject1 = (JGoObject)localEntry.getValue();
          localArrayList.add(localJGoObject1);
        }
      }
    }
    paramJGoSelection.clearSelection();
    for (int k = 0; k < localArrayList.size(); k++) {
      paramJGoSelection.extendSelection((JGoObject)localArrayList.get(k));
    }
    if (i != 0)
    {
      paramInt2 = 0;
      paramInt3 = 0;
        int k = getGridSpot();
      localObject = null;
      for (int m = 0; m < localArrayList.size(); m++)
      {
        JGoObject localJGoObject2 = (JGoObject)localArrayList.get(m);
        if (!(localJGoObject2 instanceof JGoLink))
        {
          localObject = localJGoObject2;
          break;
        }
      }
        int m = paramInt2;
      int n = paramInt3;
      int i3;
      int i4;
      int i5;
      if ((localObject != null) && (i != 0))
      {
        Point localPoint2 = ((JGoObject)localObject).getSpotLocation(k, localPoint1);
        int i2 = localPoint2.x;
        i3 = localPoint2.y;
        findNearestGridPoint(i2 + paramInt2, i3 + paramInt3, localPoint1);
        i4 = localPoint1.x;
        i5 = localPoint1.y;
        m = i4 - i2;
        n = i5 - i3;
      }
      JGoObject localJGoObject3;
      for (int i1 = 0; i1 < localArrayList.size(); i1++)
      {
        localJGoObject3 = (JGoObject)localArrayList.get(i1);
        if ((localJGoObject3 instanceof JGoLink))
        {
          i3 = localJGoObject3.getLeft();
          i4 = localJGoObject3.getTop();
          localJGoObject3.setBoundingRect(i3 + m, i4 + n, localJGoObject3.getWidth(), localJGoObject3.getHeight());
        }
      }
      for (int i1 = 0; i1 < localArrayList.size(); i1++)
      {
        localJGoObject3 = (JGoObject)localArrayList.get(i1);
        if (!(localJGoObject3 instanceof JGoLink))
        {
          Point localPoint3 = localJGoObject3.getSpotLocation(k, localPoint1);
          i4 = localPoint3.x + paramInt2;
          i5 = localPoint3.y + paramInt3;
          findNearestGridPoint(i4, i5, localPoint1);
          localJGoObject3.setSpotLocation(k, localPoint1);
        }
      }
    }
    localArrayList.clear();
  }
  
  protected double getPrintScale(Graphics2D paramGraphics2D, PageFormat paramPageFormat)
  {
      Rectangle2D.Double localDouble = getPrintPageRect(paramGraphics2D, paramPageFormat);
    Dimension localDimension = getPrintDocumentSize();
    return Math.min(localDouble.getWidth() / localDimension.getWidth(), localDouble.getHeight() / localDimension.getHeight());
  }
  
  protected void printDecoration(Graphics2D paramGraphics2D, PageFormat paramPageFormat, int paramInt1, int paramInt2) {}
  
  public void doLayout()
  {
    super.doLayout();
    if (getHorizontalScrollBar() != null) {
      getHorizontalScrollBar().setUnitIncrement(50);
    }
    if (getVerticalScrollBar() != null) {
      getVerticalScrollBar().setUnitIncrement(50);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCView.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */