package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.ConnectionPost;
import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnectionPostDialog
  extends MyJDialog
{
  private MyJTextField tRemote = new MyJTextField();
  private GCDocument myObject;
  private ConnectionPost cp;
  
  public ConnectionPostDialog(Frame paramFrame, GCDocument paramGCDocument, ConnectionPost paramConnectionPost, GCView paramGCView)
  {
    super(paramFrame, "Connection Post");
    this.myObject = paramGCDocument;
    this.cp = paramConnectionPost;
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
    JLabel localJLabel = new JLabel("Remote Connection Post (full path)");
    localJLabel.setHorizontalAlignment(2);
    localJLabel.setBounds(new Rectangle(50, 10, 230, 24));
    localJPanel.add(localJLabel);
    this.tRemote.setFontMonospaced();
    this.tRemote.setEnabled(this.myObject.isModifiable());
    this.tRemote.setBounds(new Rectangle(50, 40, 200, 24));
    localJPanel.add(this.tRemote);
  }
  
  void updateDialog()
  {
    this.tRemote.setText(this.cp.remoteString);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.cp.remoteString = this.tRemote.getText();
    this.cp.remote = ((ConnectionPost)Editor.findObject(this.cp.remoteString));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/ConnectionPostDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */