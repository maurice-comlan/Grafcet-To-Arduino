package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.ByteStreamIn;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ByteStreamInDialog
  extends MyJDialog
{
  private MyJTextField tHost = new MyJTextField();
  private MyJTextField tPort = new MyJTextField();
  private GCDocument myObject;
  private ByteStreamIn s;
  
  public ByteStreamInDialog(Frame paramFrame, GCDocument paramGCDocument, ByteStreamIn paramByteStreamIn, GCView paramGCView)
  {
    super(paramFrame, "ByteStreamIn");
    this.myObject = paramGCDocument;
    this.s = paramByteStreamIn;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 170));
    localJPanel.setPreferredSize(new Dimension(294, 170));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 140, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 140, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("Host");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel1);
    this.tHost.setBounds(new Rectangle(50, 40, 200, 24));
    this.tHost.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tHost);
    JLabel localJLabel2 = new JLabel("Port");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 70, 148, 24));
    localJPanel.add(localJLabel2);
    this.tPort.setBounds(new Rectangle(50, 100, 200, 24));
    this.tPort.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tPort);
  }
  
  void updateDialog()
  {
    this.tHost.setText(this.s.host);
    this.tHost.setFont(new Font("Dialog", 1, 14));
    this.tPort.setText("" + this.s.port);
    this.tPort.setFont(new Font("Dialog", 1, 14));
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.port = parseInt(this.tPort);
    this.s.host = this.tHost.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/ByteStreamInDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */