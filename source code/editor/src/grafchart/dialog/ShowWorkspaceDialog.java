package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.ShowWorkspaceButton;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowWorkspaceDialog
  extends MyJDialog
{
  private MyJTextField tWorkspace = new MyJTextField();
  private GCDocument myObject;
  private ShowWorkspaceButton s;
  
  public ShowWorkspaceDialog(Frame paramFrame, GCDocument paramGCDocument, ShowWorkspaceButton paramShowWorkspaceButton, GCView paramGCView)
  {
    super(paramFrame, "Show Workspace Button");
    this.myObject = paramGCDocument;
    this.s = paramShowWorkspaceButton;
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
    JLabel localJLabel = new JLabel("Workspace");
    localJLabel.setHorizontalAlignment(2);
    localJLabel.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel);
    this.tWorkspace.setBounds(new Rectangle(50, 40, 200, 24));
    this.tWorkspace.setEnabled(this.myObject.isModifiable());
    this.tWorkspace.setFontMonospaced();
    localJPanel.add(this.tWorkspace);
  }
  
  void updateDialog()
  {
    this.tWorkspace.setText(this.s.ws);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.ws = this.tWorkspace.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/ShowWorkspaceDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */