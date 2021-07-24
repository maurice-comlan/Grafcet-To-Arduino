package grafchart.dpws;

import com.ibm.wsdl.util.StringUtils;
import grafchart.dpws.db.DPWSDevice;
import grafchart.dpws.db.DPWSOperation;
import grafchart.dpws.db.DPWSParameter;
import grafchart.dpws.db.DPWSPortType;
import grafchart.dpws.db.DPWSService;
import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJFileChooser;
import grafchart.sfc.Editor;
import grafchart.sfc.Utils;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class ServicesDialog
  extends MyJDialog
  implements Observer
{
  private Editor editor;
  private Services services;
  private JTextField tDeviceAddress;
  private JTextArea tDeviceMetadata;
  private JTextField tServiceAddress;
  private JTextArea tService;
  private JTextArea tPortType;
  private JTextArea tOperation;
  private JTextArea tParameter;
  private MyJButton bDiscover;
  private MyJButton bSaveWSDL;
  private JTree tree;
  private DefaultMutableTreeNode treeRoot;
  private DefaultTreeModel treeModel;
  
  public ServicesDialog(Editor paramEditor, Services paramServices)
  {
    super(null, "DPWS Service Registry", false);
    this.editor = paramEditor;
    this.services = paramServices;
    paramServices.addObserver(this);
    init();
    setLocationRelativeTo(paramEditor);
  }
  
  public void close()
  {
    this.editor.dpwsServicesClosed();
    this.services.deleteObserver(this);
    super.close();
  }
  
  private void init()
  {
    this.treeRoot = new DefaultMutableTreeNode("Services");
    this.treeModel = new DefaultTreeModel(this.treeRoot, true);
    this.tree = new JTree(this.treeModel);
    this.tree.setShowsRootHandles(true);
    this.tree.setRootVisible(false);
    this.tree.getSelectionModel().setSelectionMode(1);
    this.tree.addTreeSelectionListener(new TreeSelectionListener()
    {
      public void valueChanged(TreeSelectionEvent paramAnonymousTreeSelectionEvent)
      {
        //ServicesDialog.this.treeSelect(ServicesDialog.access$000(ServicesDialog.this));
      }
    });
    JScrollPane localJScrollPane1 = new JScrollPane();
    localJScrollPane1.setViewportView(this.tree);
    JLabel localJLabel1 = new JLabel("Device Address");
    this.tDeviceAddress = new JTextField(60);
    this.tDeviceAddress.setEditable(false);
    JLabel localJLabel2 = new JLabel("Device Metadata");
    this.tDeviceMetadata = new JTextArea(10, 60);
    this.tDeviceMetadata.setEditable(false);
    JScrollPane localJScrollPane2 = new JScrollPane();
    localJScrollPane2.setViewportView(this.tDeviceMetadata);
    JLabel localJLabel3 = new JLabel("Service Endpoint Address");
    this.tServiceAddress = new JTextField(60);
    this.tServiceAddress.setEditable(false);
    JLabel localJLabel4 = new JLabel("Service Documentation");
    this.tService = new JTextArea(5, 60);
    this.tService.setEditable(false);
    JScrollPane localJScrollPane3 = new JScrollPane();
    localJScrollPane3.setViewportView(this.tService);
    JLabel localJLabel5 = new JLabel("PortType Documentation");
    this.tPortType = new JTextArea(5, 60);
    this.tPortType.setEditable(false);
    JScrollPane localJScrollPane4 = new JScrollPane();
    localJScrollPane4.setViewportView(this.tPortType);
    JLabel localJLabel6 = new JLabel("Operation Documentation");
    this.tOperation = new JTextArea(5, 60);
    this.tOperation.setEditable(false);
    JScrollPane localJScrollPane5 = new JScrollPane();
    localJScrollPane5.setViewportView(this.tOperation);
    JLabel localJLabel7 = new JLabel("Parameter Documentation");
    this.tParameter = new JTextArea(5, 60);
    this.tParameter.setEditable(false);
    JScrollPane localJScrollPane6 = new JScrollPane();
    localJScrollPane6.setViewportView(this.tParameter);
    JPanel localJPanel1 = new JPanel();
    GroupLayout localGroupLayout1 = new GroupLayout(localJPanel1);
    localJPanel1.setLayout(localGroupLayout1);
    localGroupLayout1.setHorizontalGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout1.createSequentialGroup().addContainerGap().addGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(localJLabel1).addComponent(this.tDeviceAddress, -2, -1, -2).addComponent(localJLabel2).addComponent(localJScrollPane2, -2, -1, -2).addComponent(localJLabel3).addComponent(this.tServiceAddress, -2, -1, -2).addComponent(localJLabel4).addComponent(localJScrollPane3, -2, -1, -2).addComponent(localJLabel5).addComponent(localJScrollPane4, -2, -1, -2).addComponent(localJLabel6).addComponent(localJScrollPane5, -2, -1, -2).addComponent(localJLabel7).addComponent(localJScrollPane6, -2, -1, -2)).addContainerGap()));
    localGroupLayout1.setVerticalGroup(localGroupLayout1.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout1.createSequentialGroup().addContainerGap().addComponent(localJLabel1).addComponent(this.tDeviceAddress, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(localJLabel2).addComponent(localJScrollPane2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(localJLabel3).addComponent(this.tServiceAddress, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(localJLabel4).addComponent(localJScrollPane3, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(localJLabel5).addComponent(localJScrollPane4, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(localJLabel6).addComponent(localJScrollPane5, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(localJLabel7).addComponent(localJScrollPane6, -2, -1, -2)));
    this.bDiscover = new MyJButton("Discover", 68);
    this.bDiscover.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ServicesDialog.this.discover((paramAnonymousActionEvent.getModifiers() & 0x1) != 0);
      }
    });
    this.bDiscover.setEnabled(!this.services.isRefreshing());
    this.bSaveWSDL = new MyJButton("Save WSDL", 87);
    this.bSaveWSDL.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ServicesDialog.this.saveWSDL();
      }
    });
    this.bSaveWSDL.setEnabled(false);
    JPanel localJPanel2 = new JPanel();
    GroupLayout localGroupLayout2 = new GroupLayout(localJPanel2);
    localJPanel2.setLayout(localGroupLayout2);
    localGroupLayout2.setHorizontalGroup(localGroupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout2.createSequentialGroup().addContainerGap().addComponent(this.bDiscover).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.bSaveWSDL).addContainerGap()));
    localGroupLayout2.setVerticalGroup(localGroupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout2.createSequentialGroup().addContainerGap().addGroup(localGroupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.bDiscover).addComponent(this.bSaveWSDL)).addContainerGap()));
    JSplitPane localJSplitPane1 = new JSplitPane(0);
    JSplitPane localJSplitPane2 = new JSplitPane(1);
    localJSplitPane2.setDividerSize(0);
    localJSplitPane1.setDividerSize(0);
    localJSplitPane2.setLeftComponent(localJScrollPane1);
    localJSplitPane2.setRightComponent(localJPanel1);
    localJSplitPane2.setResizeWeight(1.0D);
    localJSplitPane1.setLeftComponent(localJSplitPane2);
    localJSplitPane1.setRightComponent(localJPanel2);
    localJSplitPane1.setResizeWeight(1.0D);
    getContentPane().add(localJSplitPane1);
    redrawTree();
    boolean bool = this.services.getDatabases().isEmpty();
    if (bool) {
      this.tree.setPreferredSize(new Dimension(300, this.tree.getPreferredSize().height));
    }
    pack();
    if (bool) {
      this.tree.setPreferredSize(null);
    }
  }
  
  private void discover(boolean paramBoolean)
  {
    this.services.refresh(paramBoolean);
  }
  
  private void saveWSDL()
  {
    String str1 = getSelection().getWSDLLocation();
    MyJFileChooser localMyJFileChooser = new MyJFileChooser();
    localMyJFileChooser.setSelectedFile(new File(str1));
    if (localMyJFileChooser.showSaveDialog(null) == 0)
    {
      File localFile = localMyJFileChooser.getSelectedFile();
      BufferedReader localBufferedReader = null;
      BufferedWriter localBufferedWriter = null;
      try
      {
        localBufferedReader = new BufferedReader(new InputStreamReader(StringUtils.getContentAsInputStream(StringUtils.getURL(null, str1))));
        localBufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(localFile)));
        String str2;
        while ((str2 = localBufferedReader.readLine()) != null) {
          localBufferedWriter.write(str2 + "\n");
        }
      }
      catch (Exception localException1)
      {
        Utils.writeException(localException1);
      }
      try
      {
        localBufferedReader.close();
      }
      catch (Exception localException2) {}
      try
      {
        localBufferedWriter.close();
      }
      catch (Exception localException3) {}
    }
  }
  
  private void redrawTree()
  {
    this.treeRoot.removeAllChildren();
    Iterator localIterator1 = this.services.getDatabases().iterator();
    while (localIterator1.hasNext())
    {
      DPWSDevice localDPWSDevice = (DPWSDevice)localIterator1.next();
      ServicesDialogNode localServicesDialogNode1 = localDPWSDevice.createJTreeNode();
      this.treeRoot.add(localServicesDialogNode1);
      Iterator localIterator2 = localDPWSDevice.getServices().iterator();
      while (localIterator2.hasNext())
      {
        DPWSService localDPWSService = (DPWSService)localIterator2.next();
        ServicesDialogNode localServicesDialogNode2 = localDPWSService.createJTreeNode();
        localServicesDialogNode1.add(localServicesDialogNode2);
        Iterator localIterator3 = localDPWSService.getPortTypes().iterator();
        while (localIterator3.hasNext())
        {
          DPWSPortType localDPWSPortType = (DPWSPortType)localIterator3.next();
          ServicesDialogNode localServicesDialogNode3 = localDPWSPortType.createJTreeNode();
          localServicesDialogNode2.add(localServicesDialogNode3);
          Iterator localIterator4 = localDPWSPortType.getOperations().iterator();
          while (localIterator4.hasNext())
          {
            DPWSOperation localDPWSOperation = (DPWSOperation)localIterator4.next();
            ServicesDialogNode localServicesDialogNode4 = localDPWSOperation.createJTreeNode();
            localServicesDialogNode3.add(localServicesDialogNode4);
            Iterator localIterator5 = localDPWSOperation.getInputs().iterator();
            DPWSParameter localDPWSParameter;
            ServicesDialogNode localServicesDialogNode5;
            while (localIterator5.hasNext())
            {
              localDPWSParameter = (DPWSParameter)localIterator5.next();
              localServicesDialogNode5 = localDPWSParameter.createJTreeNode("Send");
              localServicesDialogNode4.add(localServicesDialogNode5);
            }
            localIterator5 = localDPWSOperation.getOutputs().iterator();
            while (localIterator5.hasNext())
            {
              localDPWSParameter = (DPWSParameter)localIterator5.next();
              localServicesDialogNode5 = localDPWSParameter.createJTreeNode("Receive");
              localServicesDialogNode4.add(localServicesDialogNode5);
            }
          }
        }
      }
    }
    this.treeModel.reload();
    treeSelect(null);
  }
  
  private ServicesDialogNode getSelection()
  {
    return (ServicesDialogNode)this.tree.getLastSelectedPathComponent();
  }
  
  public void setSelection(DPWSPortType paramDPWSPortType)
  {
    Enumeration localEnumeration1 = this.treeRoot.children();
    while (localEnumeration1.hasMoreElements())
    {
      ServicesDialogNode localServicesDialogNode1 = (ServicesDialogNode)localEnumeration1.nextElement();
      Enumeration localEnumeration2 = localServicesDialogNode1.children();
      while (localEnumeration2.hasMoreElements())
      {
        ServicesDialogNode localServicesDialogNode2 = (ServicesDialogNode)localEnumeration2.nextElement();
        Enumeration localEnumeration3 = localServicesDialogNode2.children();
        while (localEnumeration3.hasMoreElements())
        {
          ServicesDialogNode localServicesDialogNode3 = (ServicesDialogNode)localEnumeration3.nextElement();
          if (localServicesDialogNode3.getDPWSElement() == paramDPWSPortType)
          {
            TreePath localTreePath = new TreePath(localServicesDialogNode3.getPath());
            this.tree.setSelectionPath(localTreePath);
            this.tree.expandPath(localTreePath);
          }
        }
      }
    }
  }
  
  private void treeSelect(ServicesDialogNode paramServicesDialogNode)
  {
    if (paramServicesDialogNode == null)
    {
      this.bSaveWSDL.setEnabled(false);
      this.tDeviceAddress.setText("");
      this.tDeviceMetadata.setText("");
      this.tServiceAddress.setText("");
      this.tService.setText("");
      this.tPortType.setText("");
      this.tOperation.setText("");
      this.tParameter.setText("");
      return;
    }
    this.bSaveWSDL.setEnabled(paramServicesDialogNode.getWSDLLocation() != null);
    this.tDeviceAddress.setText(paramServicesDialogNode.getDeviceAddress());
    setText(this.tDeviceMetadata, paramServicesDialogNode.getDeviceMetadata());
    this.tServiceAddress.setText(paramServicesDialogNode.getServiceAddress());
    setText(this.tService, paramServicesDialogNode.getServiceDocumentation());
    setText(this.tPortType, paramServicesDialogNode.getPortTypeDocumentation());
    setText(this.tOperation, paramServicesDialogNode.getOperationDocumentation());
    setText(this.tParameter, paramServicesDialogNode.getParameterDocumentation());
  }
  
  private void setText(JTextArea paramJTextArea, String paramString)
  {
    paramJTextArea.setText(paramString);
    paramJTextArea.setCaretPosition(0);
  }
  
  @Override
  public void update(Observable paramObservable, Object paramObject)
  {
    if (!this.services.isRefreshing())
    {
      redrawTree();
      this.bDiscover.setEnabled(true);
    }
    else
    {
      if (this.treeRoot.getChildCount() != 0)
      {
        this.treeRoot.removeAllChildren();
        this.treeModel.reload();
        treeSelect(null);
      }
      this.bDiscover.setEnabled(false);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dpws/ServicesDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */