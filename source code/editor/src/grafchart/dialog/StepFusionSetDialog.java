package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.StepFusionSet;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class StepFusionSetDialog
  extends MyJDialog
{
  private JCheckBox abortBox = new JCheckBox();
  private StepFusionSet s;
  
  public StepFusionSetDialog(Frame paramFrame, StepFusionSet paramStepFusionSet, GCView paramGCView)
  {
    super(paramFrame, "Step Fusion Set");
    this.s = paramStepFusionSet;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 70));
    localJPanel.setPreferredSize(new Dimension(294, 70));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localJPanel.add(localMyJButton1);
    localMyJButton1.setBounds(new Rectangle(60, 40, 79, 22));
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 40, 79, 22));
    localJPanel.add(localMyJButton2);
    this.abortBox.setText("Abortive");
    this.abortBox.setBounds(new Rectangle(50, 10, 220, 14));
    localJPanel.add(this.abortBox);
  }
  
  void updateDialog()
  {
    this.abortBox.setSelected(this.s.abortive);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.abortive = this.abortBox.isSelected();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/StepFusionSetDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */