package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.Referencable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import se.lth.control.BoxPanel;

public class StepNameDialog
  extends MyJDialog
{
  private MyJTextField tName = new MyJTextField();
  private Referencable s;
  
  public StepNameDialog(Frame paramFrame, Referencable paramReferencable, GCView paramGCView)
  {
    super(paramFrame, "Step", false);
    this.s = paramReferencable;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel1 = new JPanel(new BorderLayout());
    JPanel localJPanel2 = new JPanel();
    localJPanel2.setLayout(new BorderLayout());
    localJPanel2.add(localJPanel1, "Center");
    JPanel localJPanel3 = new JPanel();
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(130, 104, 79, 22));
    localJPanel3.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(220, 104, 79, 22));
    localJPanel3.add(localMyJButton2);
    localJPanel2.add(localJPanel3, "South");
    BoxPanel localBoxPanel = new BoxPanel(BoxPanel.HORIZONTAL);
    localBoxPanel.addFixed(20);
    localBoxPanel.add(localJPanel2);
    localBoxPanel.addFixed(20);
    localBoxPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
    setContentPane(localBoxPanel);
    JLabel localJLabel = new JLabel("Name");
    localJLabel.setHorizontalAlignment(2);
    localJLabel.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel1.add(localJLabel, "North");
    this.tName.setMinimumSize(new Dimension(400, 24));
    this.tName.setPreferredSize(new Dimension(400, 24));
    localJPanel1.add(this.tName, "South");
  }
  
  void updateDialog()
  {
    this.tName.setText(this.s.getName());
    this.tName.setFont(new Font("Dialog", Font.BOLD, 14));
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.setName(this.tName.getText());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/StepNameDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */