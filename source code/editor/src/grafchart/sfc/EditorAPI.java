package grafchart.sfc;

import grafchart.util.SymbolTableHelper;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import org.w3c.dom.Document;

public class EditorAPI
  extends Editor
{
  public EditorAPI(String[] paramArrayOfString)
  {
    super(paramArrayOfString);
  }
  
  public GCDocument newWorkspace()
  {
    return newAction();
  }
  
  public boolean compileWorkspace(GCDocument paramGCDocument)
  {
    paramGCDocument.compiledOK = true;
    generateWarnings = true;
    eraseWarnings();
    SymbolTableHelper.warnAboutNameConflicts(paramGCDocument);
    compileDocument(paramGCDocument, false);
    compileDocument(paramGCDocument, false);
    return paramGCDocument.compiledOK;
  }
  
  public void startWorkspace(GCDocument paramGCDocument)
  {
    Utils.writeDebug("StartWorkspace called");
    if (paramGCDocument.compiledOK)
    {
      Utils.writeDebug("CompiledOK");
      paramGCDocument.initializeDocument(paramGCDocument, paramGCDocument.isSimulating());
      paramGCDocument.getView().setDragDropEnabled(false);
      paramGCDocument.setDragAndDrop(false);
      paramGCDocument.start();
    }
  }
  
  public void stopWorkspace(GCDocument paramGCDocument)
  {
    if (paramGCDocument.executing)
    {
      paramGCDocument.stopThread();
      paramGCDocument.stopDocument();
      paramGCDocument.getView().setDragDropEnabled(true);
      paramGCDocument.setDragAndDrop(true);
      paramGCDocument.setSkipsUndoManager(false);
    }
  }
  
  public void deleteWorkspace(GCDocument paramGCDocument)
  {
    try
    {
      paramGCDocument.myView.getInternalFrame().setClosed(true);
    }
    catch (Exception localException) {}
  }
  
  public GCDocument openWorkspace(String paramString)
  {
    GCDocument localGCDocument = null;
    String str = paramString;
    Document localDocument = loadXMLDocumentFromFile(str);
    if (localDocument != null)
    {
      localGCDocument = new GCDocument();
      localGCDocument.setSuspendUpdates(true);
      localGCDocument.loadXML(localDocument.getDocumentElement());
      topGrafcharts.add(localGCDocument);
    }
    localGCDocument.setWriteFileLocation(str);
    localGCDocument.setReadFileLocation(str);
    final GCView localGCView = new GCView(localGCDocument);
    localGCDocument.setView(localGCView);
    JInternalFrame localJInternalFrame = new JInternalFrame(localGCDocument.getName(), true, false, true, true);
    localJInternalFrame.setDefaultCloseOperation(2);
    localGCView.initialize(this, localJInternalFrame);
    localGCView.setScale(localGCDocument.currentScale);
    if (localGCDocument.point != null) {
      localGCView.setViewPosition(localGCDocument.point);
    }
    localJInternalFrame.setBounds(localGCDocument.bounds);
    localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
    {
      public void internalFrameActivated(InternalFrameEvent paramAnonymousInternalFrameEvent)
      {
        Editor.myCurrentView = localGCView;
        Editor.compilationView = true;
        Editor.topLevelView = true;
        localGCView.requestFocus();
        AppAction.updateAllActions();
      }
      
      public void internalFrameDeactivated(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
      
      public void internalFrameOpened(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
      
      public void internalFrameClosing(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
      
      public void internalFrameClosed(InternalFrameEvent paramAnonymousInternalFrameEvent)
      {
        EditorAPI.this.closeMacros(localGCView.getDoc());
        Editor.topGrafcharts.remove(localGCView.getDoc());
        Editor.compilationView = false;
        Editor.myCurrentView = null;
        AppAction.updateAllActions();
      }
      
      public void internalFrameIconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
      
      public void internalFrameDeiconified(InternalFrameEvent paramAnonymousInternalFrameEvent) {}
    });
    localJInternalFrame.getContentPane().setLayout(new BorderLayout());
    localJInternalFrame.getContentPane().add(localGCView);
    getDesktop().add(localJInternalFrame);
    localJInternalFrame.show();
    localGCView.initializeDragDropHandling();
    localGCDocument.setSuspendUpdates(false);
    if (!localGCDocument.modificationProperty) {
      localGCDocument.setModificationProperty(false);
    }
    localGCView.hasHorizontalScrollBar = localGCDocument.horizontalScrollBar;
    if (!localGCDocument.horizontalScrollBar) {
      localGCView.setHorizontalScrollBar(null);
    }
    localGCView.hasVerticalScrollBar = localGCDocument.verticalScrollBar;
    if (!localGCDocument.verticalScrollBar) {
      localGCView.setVerticalScrollBar(null);
    }
    return localGCDocument;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/EditorAPI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */