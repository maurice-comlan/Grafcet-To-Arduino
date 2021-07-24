package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCClass;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SetSuperClassDialog
  extends MyJDialog
{
  private MyJTextField classField = new MyJTextField();
  private GCClass cl;
  
  public SetSuperClassDialog(Frame paramFrame, GCClass paramGCClass, GCView paramGCView)
  {
    super(paramFrame, "Set Superclass");
    this.cl = paramGCClass;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 200));
    localJPanel.setPreferredSize(new Dimension(294, 200));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createHelpButton(null);
    localMyJButton1.setBounds(new Rectangle(210, 160, 79, 22));
    localMyJButton1.setEnabled(false);
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createOKButton();
    localMyJButton2.setBounds(new Rectangle(30, 160, 79, 22));
    localJPanel.add(localMyJButton2);
    MyJButton localMyJButton3 = createCancelButton();
    localMyJButton3.setBounds(new Rectangle(120, 160, 79, 22));
    localJPanel.add(localMyJButton3);
    JLabel localJLabel = new JLabel("Superclass");
    localJLabel.setHorizontalAlignment(2);
    localJPanel.add(localJLabel);
    localJLabel.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(this.classField);
    this.classField.setBounds(new Rectangle(50, 40, 200, 24));
    this.classField.setEnabled(true);
  }
  
  void updateDialog()
  {
    this.classField.setText(this.cl.superClass);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.cl.superClass = this.classField.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/SetSuperClassDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */