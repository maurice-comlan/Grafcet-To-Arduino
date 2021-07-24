package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.AppAction;
import grafchart.sfc.Editor;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerDialog
  extends MyJDialog
{
  private MyJTextField tHost = new MyJTextField();
  private MyJTextField tPort = new MyJTextField();
  private MyJTextField tName = new MyJTextField();
  
  public ServerDialog(Frame paramFrame)
  {
    super(paramFrame, "CCOM Server Properties");
    init();
    pack();
    updateDialog();
    setLocationRelativeTo(paramFrame);
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 235));
    localJPanel.setPreferredSize(new Dimension(294, 235));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 200, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 200, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("CCOM Server IP address");
    localJLabel1.setBounds(new Rectangle(50, 20, 220, 24));
    localJLabel1.setHorizontalAlignment(2);
    localJPanel.add(localJLabel1);
    this.tHost.setBounds(new Rectangle(50, 50, 200, 24));
    this.tHost.setEnabled(true);
    localJPanel.add(this.tHost);
    JLabel localJLabel2 = new JLabel("CCOM Server port");
    localJLabel2.setBounds(new Rectangle(50, 80, 220, 24));
    localJLabel2.setHorizontalAlignment(2);
    localJPanel.add(localJLabel2);
    this.tPort.setBounds(new Rectangle(50, 110, 200, 24));
    this.tPort.setEnabled(true);
    localJPanel.add(this.tPort);
    JLabel localJLabel3 = new JLabel("CCOM Login Name");
    localJLabel3.setBounds(new Rectangle(50, 140, 220, 24));
    localJLabel3.setHorizontalAlignment(2);
    localJPanel.add(localJLabel3);
    this.tName.setBounds(new Rectangle(50, 170, 200, 24));
    this.tName.setEnabled(true);
    localJPanel.add(this.tName);
  }
  
  void updateDialog()
  {
    this.tHost.setText(Editor.serverIP);
    this.tPort.setText(Editor.port);
    this.tName.setText(Editor.loginName);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    Editor.serverIP = this.tHost.getText();
    Editor.port = this.tPort.getText();
    Editor.loginName = this.tName.getText();
    AppAction.updateAllActions();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/ServerDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */