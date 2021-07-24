package grafchart.dialog;

import grafchart.graphics.MyDocumentListener;
import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextArea;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.LabCommObject;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;

public class LabCommObjectDialog
  extends MyJDialog
{
  private LabCommObject obj;
  private MyJTextField tSocketServer = new MyJTextField();
  private JCheckBox cbIsSocketServer = new JCheckBox();
  private MyJTextField tSocketPort = new MyJTextField();
  private MyJTextField tVersion = new MyJTextField();
  private MyJTextArea tSpecification = new MyJTextArea();
  private JCheckBox cbInput = new JCheckBox("Input");
  private JCheckBox cbOutput = new JCheckBox("Output");
  private JCheckBox cbOrca = new JCheckBox("Orca");
  
  public LabCommObjectDialog(Frame paramFrame, LabCommObject paramLabCommObject, GCView paramGCView)
  {
    super(paramFrame, "LabComm Object", false);
    this.obj = paramLabCommObject;
    init();
    updateDialog();
    pack();
    setLocationRelativeTo(paramGCView);
  }
  
  private void init()
  {
    JLabel localJLabel1 = new JLabel("Socket server");
    this.cbIsSocketServer.setText("Be socket server");
    this.cbIsSocketServer.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        LabCommObjectDialog.this.updateEnabled();
      }
    });
    JLabel localJLabel2 = new JLabel("Socket port");
    JLabel localJLabel3 = new JLabel("Version (2006 or 2013)");
    this.tVersion.getDocument().addDocumentListener(new MyDocumentListener()
    {
      public void update(DocumentEvent paramAnonymousDocumentEvent)
      {
        LabCommObjectDialog.this.updateEnabled();
      }
    });
    JLabel localJLabel4 = new JLabel("LabComm sample specification");
    this.tSpecification = new MyJTextArea();
    this.tSpecification.setText(this.obj.getSpecification());
    this.tSpecification.setFontMonospaced();
    JScrollPane localJScrollPane = new JScrollPane(this.tSpecification);
    localJScrollPane.setVerticalScrollBarPolicy(22);
    MyJButton localMyJButton1 = createOKButton();
    MyJButton localMyJButton2 = createCancelButton();
    GroupLayout localGroupLayout = new GroupLayout(getContentPane());
    getContentPane().setLayout(localGroupLayout);
    localGroupLayout.setHorizontalGroup(localGroupLayout.createSequentialGroup().addContainerGap().addGroup(localGroupLayout.createParallelGroup().addComponent(localJLabel1).addComponent(this.tSocketServer).addComponent(this.cbIsSocketServer).addComponent(localJLabel2).addComponent(this.tSocketPort).addComponent(localJLabel3).addComponent(this.tVersion).addComponent(localJLabel4).addComponent(localJScrollPane, 0, 400, 32767).addGroup(GroupLayout.Alignment.LEADING, localGroupLayout.createSequentialGroup().addComponent(this.cbInput).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbOutput).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbOrca)).addGroup(GroupLayout.Alignment.TRAILING, localGroupLayout.createSequentialGroup().addComponent(localMyJButton1, 80, 80, 80).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(localMyJButton2, 80, 80, 80))).addContainerGap());
    localGroupLayout.setVerticalGroup(localGroupLayout.createSequentialGroup().addContainerGap().addComponent(localJLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.tSocketServer, -2, -2, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.cbIsSocketServer).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(localJLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.tSocketPort, -2, -2, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(localJLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.tVersion, -2, -2, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(localJLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(localJScrollPane, localJScrollPane.getMinimumSize().height, 300, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.cbInput).addComponent(this.cbOutput).addComponent(this.cbOrca)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(localMyJButton2).addComponent(localMyJButton1)).addContainerGap());
  }
  
  private void updateDialog()
  {
    this.tSocketServer.setText(this.obj.getSocketHost());
    this.cbIsSocketServer.setSelected(this.obj.isSocketServer());
    this.tSocketPort.setText("" + this.obj.getSocketPort());
    this.tVersion.setText("" + this.obj.getVersion());
    this.tSpecification.setText(this.obj.getSpecification());
    this.cbInput.setSelected(this.obj.isInput());
    this.cbOutput.setSelected(this.obj.isOutput());
    this.cbOrca.setSelected(this.obj.isOrca());
    updateEnabled();
  }
  
  private void updateEnabled()
  {
    this.tSocketServer.setEnabled(!this.cbIsSocketServer.isSelected());
    this.cbOrca.setEnabled((!this.cbIsSocketServer.isSelected()) && (this.tVersion.getText().equals("2006")));
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    int i = parseInt(this.tVersion);
    int j = parseInt(this.tSocketPort);
    this.obj.setSocketHost(this.tSocketServer.getText());
    this.obj.setSocketServer(this.cbIsSocketServer.isSelected());
    this.obj.setSocketPort(j);
    this.obj.setVersion(i);
    this.obj.setSpecification(this.tSpecification.getText());
    this.obj.setInput(this.cbInput.isSelected());
    this.obj.setOutput(this.cbOutput.isSelected());
    this.obj.setOrca(this.cbOrca.isSelected());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/LabCommObjectDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */