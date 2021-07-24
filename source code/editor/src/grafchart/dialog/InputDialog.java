package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.InputVariable;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class InputDialog
  extends MyJDialog
{
  private JCheckBox isCyclicUpdatedBox = new JCheckBox();
  private InputVariable cp;
  
  public InputDialog(Frame paramFrame, InputVariable paramInputVariable, GCView paramGCView)
  {
    super(paramFrame, "Input");
    this.cp = paramInputVariable;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 100));
    localJPanel.setPreferredSize(new Dimension(294, 100));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 60, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 60, 79, 22));
    localJPanel.add(localMyJButton2);
    this.isCyclicUpdatedBox.setText("Cyclic Updated");
    this.isCyclicUpdatedBox.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(this.isCyclicUpdatedBox);
  }
  
  void updateDialog()
  {
    this.isCyclicUpdatedBox.setSelected(this.cp.isCyclicUpdated());
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.cp.setCyclicUpdated(this.isCyclicUpdatedBox.isSelected());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/InputDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */