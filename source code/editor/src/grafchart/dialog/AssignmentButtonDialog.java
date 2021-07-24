package grafchart.dialog;

import com.nwoods.jgo.JGoText;
import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.AssignmentButton;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AssignmentButtonDialog
  extends MyJDialog
{
  private MyJTextField tText = new MyJTextField();
  private JCheckBox activeStatusBox = new JCheckBox();
  private MyJTextField tActions = new MyJTextField();
  private GCDocument myObject;
  private AssignmentButton s;
  
  public AssignmentButtonDialog(Frame paramFrame, GCDocument paramGCDocument, AssignmentButton paramAssignmentButton, GCView paramGCView)
  {
    super(paramFrame, "Action Button");
    this.myObject = paramGCDocument;
    this.s = paramAssignmentButton;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 190));
    localJPanel.setPreferredSize(new Dimension(294, 190));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 160, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 160, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("Text");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel1);
    this.tText.setBounds(new Rectangle(50, 40, 200, 24));
    this.tText.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tText);
    JLabel localJLabel2 = new JLabel("Actions");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 70, 220, 24));
    localJPanel.add(localJLabel2);
    this.tActions.setBounds(new Rectangle(50, 100, 200, 24));
    this.tActions.setEnabled(true);
    this.tActions.setFontMonospaced();
    localJPanel.add(this.tActions);
    this.activeStatusBox.setText("Enabled when stopped");
    this.activeStatusBox.setBounds(new Rectangle(45, 130, 200, 24));
    localJPanel.add(this.activeStatusBox);
  }
  
  void updateDialog()
  {
    this.tText.setText(this.s.myName.getText());
    this.tActions.setText(this.s.actionString);
    this.activeStatusBox.setSelected(this.s.enabledWhenStopped);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.myName.setText(this.tText.getText());
    this.s.actionString = this.tActions.getText();
    this.s.modifySize();
    this.s.enabledWhenStopped = this.activeStatusBox.isSelected();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/AssignmentButtonDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */