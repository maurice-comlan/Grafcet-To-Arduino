package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDesktopPane;
import grafchart.graphics.MyJDialog;
import grafchart.sfc.AppAction;
import grafchart.sfc.CallSource;
import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.GrafcetProcedure;
import grafchart.sfc.ObservableList;
import grafchart.sfc.Utils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProcedureCallsDialog
  extends MyJDialog
  implements Observer
{
  private JList calls;
  private ObservableList<GCDocument> callsList;
  private JScrollPane spCalls;
  private CallSource gp;
  private GCView v;
  
  public ProcedureCallsDialog(Frame paramFrame, CallSource paramCallSource, GCView paramGCView)
  {
    super(paramFrame, "Procedure Calls", false);
    this.gp = paramCallSource;
    this.v = paramGCView;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(494, 300));
    localJPanel.setPreferredSize(new Dimension(494, 300));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton = createOKButton();
    localMyJButton.setText("Close");
    localMyJButton.setBounds(new Rectangle(200, 260, 79, 22));
    localJPanel.add(localMyJButton);
    JLabel localJLabel = new JLabel("Procedure Calls");
    localJLabel.setHorizontalAlignment(2);
    localJPanel.add(localJLabel);
    this.calls = new JList(new DefaultListModel());
    updateList();
    this.gp.getCalls().addObserver(this);
    this.calls.setSelectionMode(0);
    this.calls.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        if (paramAnonymousListSelectionEvent.getValueIsAdjusting()) {
          return;
        }
        JList localJList = (JList)paramAnonymousListSelectionEvent.getSource();
        if (localJList.isSelectionEmpty()) {
          return;
        }
        if (!localJList.isSelectionEmpty())
        {
          int i = localJList.getSelectedIndex();
          if (i != -1) {
            try
            {
              GCDocument localGCDocument = (GCDocument)ProcedureCallsDialog.this.callsList.get(i);
              if (localGCDocument != null)
              {
                JInternalFrame localJInternalFrame = null;
                localJInternalFrame = new JInternalFrame("", true, true, true, true);
                localJInternalFrame.setDefaultCloseOperation(2);
                localGCDocument.frame = localJInternalFrame;
                final GCView localGCView = new GCView(localGCDocument);
                localGCView.layer = (ProcedureCallsDialog.this.v.layer + 1);
                localGCView.initialize(ProcedureCallsDialog.this.v.getBasicApp(), localJInternalFrame, true);
                localJInternalFrame.addInternalFrameListener(new InternalFrameListener()
                {
                  public void internalFrameActivated(InternalFrameEvent paramAnonymous2InternalFrameEvent)
                  {
                    Editor.myCurrentView = localGCView;
                    Editor.topLevelView = false;
                    localGCView.requestFocus();
                    AppAction.updateAllActions();
                  }
                  
                  public void internalFrameDeactivated(InternalFrameEvent paramAnonymous2InternalFrameEvent) {}
                  
                  public void internalFrameOpened(InternalFrameEvent paramAnonymous2InternalFrameEvent) {}
                  
                  public void internalFrameClosing(InternalFrameEvent paramAnonymous2InternalFrameEvent) {}
                  
                  public void internalFrameClosed(InternalFrameEvent paramAnonymous2InternalFrameEvent)
                  {
                    Editor.myCurrentView = ProcedureCallsDialog.this.v;
                    AppAction.updateAllActions();
                  }
                  
                  public void internalFrameIconified(InternalFrameEvent paramAnonymous2InternalFrameEvent) {}
                  
                  public void internalFrameDeiconified(InternalFrameEvent paramAnonymous2InternalFrameEvent) {}
                });
                Container localContainer = localJInternalFrame.getContentPane();
                localContainer.setLayout(new BorderLayout());
                localContainer.add(localGCView);
                Editor.getDesktop().add(localJInternalFrame, new Integer(localGCView.layer));
                if (ProcedureCallsDialog.this.gp.getBounds() == null)
                {
                  localJInternalFrame.setSize(400, 400);
                  Point localPoint = ProcedureCallsDialog.this.gp.getLocation();
                  ProcedureCallsDialog.this.gp.getDocument().getView().convertDocToView(localPoint);
                  localJInternalFrame.setLocation((int)localPoint.getX() + 10, (int)localPoint.getY() + 40);
                }
                else
                {
                  localGCDocument.setPaperColor(new Color(ProcedureCallsDialog.this.gp.getColor()));
                  localGCView.setScale(ProcedureCallsDialog.this.gp.getDocument().currentScale);
                  localJInternalFrame.setBounds(ProcedureCallsDialog.this.gp.getBounds());
                }
                localJInternalFrame.setFrameIcon(GCDocument.runIcon);
                localJInternalFrame.show();
                Editor.myCurrentView = localGCView;
                Editor.topLevelView = false;
                localGCView.requestFocus();
                AppAction.updateAllActions();
                localGCDocument.setModificationProperty(localGCDocument.modificationProperty);
                ProcedureCallsDialog.this.close();
              }
            }
            catch (Exception localException)
            {
              Utils.writeException(localException);
            }
          }
        }
      }
    });
    this.spCalls = new JScrollPane(this.calls);
    localJLabel.setBounds(new Rectangle(50, 10, 148, 24));
    this.spCalls.setVerticalScrollBarPolicy(22);
    localJPanel.add(this.spCalls);
    this.spCalls.setBounds(new Rectangle(50, 40, 400, 200));
  }
  
  private void updateList()
  {
    DefaultListModel localDefaultListModel = (DefaultListModel)this.calls.getModel();
    synchronized (localDefaultListModel)
    {
      localDefaultListModel.removeAllElements();
      this.callsList = ((ObservableList)this.gp.getCalls().clone());
      Iterator localIterator = this.callsList.iterator();
      while (localIterator.hasNext())
      {
        GCDocument localGCDocument = (GCDocument)localIterator.next();
        localDefaultListModel.addElement(new String(localGCDocument.getFullName() + " (" + localGCDocument.proc.getName() + ")"));
      }
    }
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        ProcedureCallsDialog.this.updateList();
      }
    });
  }
  
  public void close()
  {
    this.gp.getCalls().deleteObserver(this);
    super.close();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/ProcedureCallsDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */