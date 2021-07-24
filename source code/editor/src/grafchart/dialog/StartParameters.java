package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCView;
import grafchart.sfc.GrafcetProcedure;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartParameters
  extends MyJDialog
{
  private MyJTextField tScanCycleTime = new MyJTextField();
  private GrafcetProcedure gp;
  
  public StartParameters(Frame paramFrame, GrafcetProcedure paramGrafcetProcedure, GCView paramGCView)
  {
    super(paramFrame, "Execution Parameters");
    this.gp = paramGrafcetProcedure;
    this.gp.viewReference = paramGCView;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 120));
    localJPanel.setPreferredSize(new Dimension(294, 120));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 90, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 90, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel = new JLabel("Scan Cycle Time (ms)");
    localJLabel.setHorizontalAlignment(2);
    localJLabel.setBounds(new Rectangle(50, 10, 170, 24));
    localJPanel.add(localJLabel);
    this.tScanCycleTime.setBounds(new Rectangle(50, 40, 200, 24));
    this.tScanCycleTime.setEnabled(true);
    localJPanel.add(this.tScanCycleTime);
  }
  
  void updateDialog()
  {
    this.tScanCycleTime.setText(new Integer(this.gp.threadSpeed).toString());
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.gp.threadSpeed = parseInt(this.tScanCycleTime);
    this.gp.simulation = true;
    this.gp.startProcedure(true);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/StartParameters.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */