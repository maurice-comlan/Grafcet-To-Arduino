package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.SocketIn;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SocketInDialog
  extends MyJDialog
{
  private MyJTextField tName = new MyJTextField();
  private MyJTextField tIdentifier = new MyJTextField();
  private SocketIn s;
  
  public SocketInDialog(Frame paramFrame, SocketIn paramSocketIn, GCView paramGCView)
  {
    super(paramFrame, "Socket Input");
    this.s = paramSocketIn;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 130));
    localJPanel.setPreferredSize(new Dimension(294, 130));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 100, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 100, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("Name");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(10, 20, 148, 24));
    localJPanel.add(localJLabel1);
    localJPanel.add(this.tName);
    this.tName.setBounds(new Rectangle(80, 20, 200, 24));
    JLabel localJLabel2 = new JLabel("Identifier");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(10, 50, 148, 24));
    localJPanel.add(localJLabel2);
    this.tIdentifier.setBounds(new Rectangle(80, 50, 200, 24));
    localJPanel.add(this.tIdentifier);
  }
  
  void updateDialog()
  {
    this.tIdentifier.setText(this.s.getSocketIdentifier(false));
    this.tIdentifier.setFont(new Font("Dialog", 1, 14));
    this.tName.setText(this.s.getName());
    this.tName.setFont(new Font("Dialog", 1, 14));
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.setSocketIdentifier(this.tIdentifier.getText());
    this.s.setName(this.tName.getText());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/SocketInDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */