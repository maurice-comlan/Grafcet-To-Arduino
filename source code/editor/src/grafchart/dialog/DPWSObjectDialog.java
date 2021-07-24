package grafchart.dialog;

import grafchart.dpws.Services;
import grafchart.dpws.db.DPWSDevice;
import grafchart.dpws.db.DPWSPortType;
import grafchart.dpws.db.DPWSService;
import grafchart.graphics.MyDocumentListener;
import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.sfc.DPWSObject;
import grafchart.sfc.Editor;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;

public class DPWSObjectDialog
  extends MyJDialog
  implements Observer
{
  private DPWSObject obj;
  private JComboBox cbPortTypes;
  private JTextField tDeviceAddress;
  private JTextField tServiceId;
  private JTextField tPortType;
  private boolean isProgrammaticSelect = false;
  private boolean isDefaultData = true;
  
  public DPWSObjectDialog(Frame paramFrame, DPWSObject paramDPWSObject, GCView paramGCView)
  {
    super(paramFrame, "DPWS Object", false);
    this.obj = paramDPWSObject;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
    Editor.singleton.getDPWSServices().addObserver(this);
  }
  
  private void init()
  {
    JLabel localJLabel1 = new JLabel("PortTypes");
    JLabel localJLabel2 = new JLabel("Device Address");
    JLabel localJLabel3 = new JLabel("Service Id (optional)");
    JLabel localJLabel4 = new JLabel("PortType");
    MyDocumentListener local1 = new MyDocumentListener()
    {
      public void update(DocumentEvent paramAnonymousDocumentEvent)
      {
        if (!DPWSObjectDialog.this.isProgrammaticSelect)
        {
          DPWSObjectDialog.this.isDefaultData = false;
          DPWSObjectDialog.this.cbPortTypes.setSelectedIndex(-1);
        }
      }
    };
    this.tDeviceAddress = new JTextField();
    this.tDeviceAddress.getDocument().addDocumentListener(local1);
    this.tServiceId = new JTextField();
    this.tServiceId.getDocument().addDocumentListener(local1);
    this.tPortType = new JTextField();
    this.tPortType.getDocument().addDocumentListener(local1);
    MyJButton localMyJButton1 = createOKButton();
    MyJButton localMyJButton2 = createCancelButton();
    final MyJButton localMyJButton3 = new MyJButton("Documentation", 68);
    localMyJButton3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        DPWSPortType localDPWSPortType = (DPWSPortType)DPWSObjectDialog.this.cbPortTypes.getSelectedItem();
        Editor.singleton.dpwsServiceDocumentation(localDPWSPortType);
      }
    });
    this.cbPortTypes = new JComboBox();
    this.cbPortTypes.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        localMyJButton3.setEnabled(DPWSObjectDialog.this.cbPortTypes.getSelectedIndex() > 0);
        if (!DPWSObjectDialog.this.isProgrammaticSelect) {
          DPWSObjectDialog.this.isDefaultData = false;
        }
        if (DPWSObjectDialog.this.cbPortTypes.getSelectedIndex() >= 0)
        {
          String str1 = null;
          String str2 = null;
          String str3 = null;
          if (DPWSObjectDialog.this.cbPortTypes.getSelectedIndex() == 0)
          {
            str1 = DPWSObjectDialog.this.obj.getBindingDeviceAddress();
            str2 = DPWSObjectDialog.this.obj.getBindingServiceId();
            str3 = DPWSObjectDialog.this.obj.getBinding();
          }
          else
          {
            DPWSPortType localDPWSPortType = (DPWSPortType)DPWSObjectDialog.this.cbPortTypes.getSelectedItem();
            str1 = localDPWSPortType.getDevice().getDeviceAddress();
            str2 = localDPWSPortType.getService().getId();
            str3 = localDPWSPortType.getName();
          }
          boolean bool = DPWSObjectDialog.this.isProgrammaticSelect;
          DPWSObjectDialog.this.isProgrammaticSelect = true;
          DPWSObjectDialog.this.tDeviceAddress.setText(str1);
          DPWSObjectDialog.this.tServiceId.setText(str2);
          DPWSObjectDialog.this.tPortType.setText(str3);
          DPWSObjectDialog.this.isProgrammaticSelect = bool;
        }
      }
    });
    GroupLayout localGroupLayout = new GroupLayout(getContentPane());
    getContentPane().setLayout(localGroupLayout);
    localGroupLayout.setHorizontalGroup(localGroupLayout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(localGroupLayout.createSequentialGroup()
            .addContainerGap()
                   .addGroup(localGroupLayout
                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(localGroupLayout.createSequentialGroup()
                                    .addGroup(localGroupLayout
                                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(localJLabel1).addComponent(localJLabel2)
                                            .addComponent(localJLabel3).addComponent(localJLabel4))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(this.cbPortTypes, 0, 600, 32767)
                                            .addComponent(localMyJButton3, 140, 140, 140)
                                            .addComponent(this.tDeviceAddress, 0, -2, 32767)
                                            .addComponent(this.tServiceId, 0, -2, 32767)
                                            .addComponent(this.tPortType, 0, -2, 32767)))
                            .addGroup(GroupLayout.Alignment.TRAILING, localGroupLayout
                                    .createSequentialGroup()
                                    .addComponent(localMyJButton1, 80, 80, 80)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(localMyJButton2, 80, 80, 80)))
                    .addContainerGap()));
    localGroupLayout.setVerticalGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout.createSequentialGroup().addContainerGap().addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(localJLabel1).addComponent(this.cbPortTypes)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(localMyJButton3).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(localJLabel2).addComponent(this.tDeviceAddress)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(localJLabel3).addComponent(this.tServiceId)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(localJLabel4).addComponent(this.tPortType)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, -1, 32767).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(localMyJButton2).addComponent(localMyJButton1)).addContainerGap()));
  }
  
  private void updateDialog()
  {
    this.isProgrammaticSelect = true;
    this.cbPortTypes.removeAllItems();
    String str = this.obj.getBinding();
    this.cbPortTypes.addItem(str);
    DPWSPortType localDPWSPortType1 = this.obj.getPortType();
    if (!this.isDefaultData)
    {
      localDPWSPortType1 = null;
      if (this.cbPortTypes.getSelectedIndex() > 0) {
        localDPWSPortType1 = (DPWSPortType)this.cbPortTypes.getSelectedItem();
      }
    }
    Iterator localIterator1 = Editor.singleton.getDPWSServices().getDatabases().iterator();
    while (localIterator1.hasNext())
    {
      DPWSDevice localDPWSDevice = (DPWSDevice)localIterator1.next();
      Iterator localIterator2 = localDPWSDevice.getServices().iterator();
      while (localIterator2.hasNext())
      {
        DPWSService localDPWSService = (DPWSService)localIterator2.next();
        Iterator localIterator3 = localDPWSService.getPortTypes().iterator();
        while (localIterator3.hasNext())
        {
          DPWSPortType localDPWSPortType2 = (DPWSPortType)localIterator3.next();
          this.cbPortTypes.addItem(localDPWSPortType2);
          if (localDPWSPortType1 == localDPWSPortType2) {
            this.cbPortTypes.setSelectedItem(localDPWSPortType2);
          }
        }
      }
    }
    this.isProgrammaticSelect = false;
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.obj.setPortType(this.tDeviceAddress.getText(), this.tServiceId.getText(), this.tPortType.getText());
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    this.obj.update(paramObservable, paramObject);
    String str1 = this.tDeviceAddress.getText();
    String str2 = this.tServiceId.getText();
    String str3 = this.tPortType.getText();
    int i = this.cbPortTypes.getSelectedIndex() == -1 ? 1 : 0;
    updateDialog();
    if (i != 0)
    {
      this.tDeviceAddress.setText(str1);
      this.tServiceId.setText(str2);
      this.tPortType.setText(str3);
    }
  }
  
  public void close()
  {
    Editor.singleton.getDPWSServices().deleteObserver(this);
    super.close();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/DPWSObjectDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */