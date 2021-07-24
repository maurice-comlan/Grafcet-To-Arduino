package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.SocketOut;
import grafchart.sfc.SocketOut.SendMode;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SocketOutDialog
  extends MyJDialog
{
  private MyJTextField tIdentifier = new MyJTextField();
  private MyJTextField tName = new MyJTextField();
  private JCheckBox modeBox = new JCheckBox();
  private JComboBox cbSendMode = new JComboBox(SocketOut.SendMode.values());
  private SocketOut s;
  
  public SocketOutDialog(Frame paramFrame, SocketOut paramSocketOut, GCView paramGCView)
  {
    super(paramFrame, "Socket Output");
    this.s = paramSocketOut;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(314, 180));
    localJPanel.setPreferredSize(new Dimension(314, 180));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 145, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 145, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("Name");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(10, 20, 148, 24));
    localJPanel.add(localJLabel1);
    this.tName.setBounds(new Rectangle(100, 20, 200, 24));
    localJPanel.add(this.tName);
    JLabel localJLabel2 = new JLabel("Identifier");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(10, 50, 148, 24));
    localJPanel.add(localJLabel2);
    this.tIdentifier.setBounds(new Rectangle(100, 50, 200, 24));
    localJPanel.add(this.tIdentifier);
    JLabel localJLabel3 = new JLabel("Send Mode");
    localJLabel3.setHorizontalAlignment(2);
    localJLabel3.setBounds(new Rectangle(10, 80, 90, 24));
    localJPanel.add(localJLabel3);
    this.cbSendMode.setBounds(new Rectangle(100, 80, 200, 24));
    localJPanel.add(this.cbSendMode);
    this.modeBox.setText("Procel mode");
    this.modeBox.setBounds(new Rectangle(100, 115, 220, 14));
    localJPanel.add(this.modeBox);
  }
  
  void updateDialog()
  {
    this.tIdentifier.setText(this.s.getSocketIdentifier(false));
    this.tIdentifier.setFont(new Font("Dialog", 1, 14));
    this.tName.setText(this.s.getName());
    this.tName.setFont(new Font("Dialog", 1, 14));
    this.cbSendMode.setSelectedItem(this.s.sendMode);
    this.modeBox.setSelected(this.s.procelMode);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.setSocketIdentifier(this.tIdentifier.getText());
    this.s.setName(this.tName.getText());
    this.s.sendMode = ((SocketOut.SendMode)this.cbSendMode.getSelectedItem());
    this.s.procelMode = this.modeBox.isSelected();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/SocketOutDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */