package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextArea;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.ProcedureStep;
import grafchart.sfc.ProcessStep;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProcedureStepDialog
  extends MyJDialog
{
  private MyJTextField tProcedure = new MyJTextField();
  private MyJTextArea tParameters = new MyJTextArea();
  private MyJTextArea tActions = new MyJTextArea();
  private ProcedureStep ps;
  
  public ProcedureStepDialog(Frame paramFrame, ProcedureStep paramProcedureStep, GCView paramGCView)
  {
    super(paramFrame, (paramProcedureStep instanceof ProcessStep) ? "Process Step" : "Procedure Step");
    this.ps = paramProcedureStep;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(494, 650));
    localJPanel.setPreferredSize(new Dimension(494, 650));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(120, 620, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(300, 620, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("Procedure");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel1);
    this.tProcedure.setFontMonospaced();
    this.tProcedure.setBounds(new Rectangle(50, 40, 400, 24));
    localJPanel.add(this.tProcedure);
    JLabel localJLabel2 = new JLabel("Procedure Parameters:");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 80, 200, 24));
    localJPanel.add(localJLabel2);
    this.tParameters.setFontMonospaced();
    JScrollPane localJScrollPane1 = new JScrollPane(this.tParameters);
    localJScrollPane1.setVerticalScrollBarPolicy(22);
    localJPanel.add(localJScrollPane1);
    localJScrollPane1.setBounds(new Rectangle(50, 120, 400, 200));
    JLabel localJLabel3 = new JLabel("Step Actions");
    localJLabel3.setHorizontalAlignment(2);
    localJLabel3.setBounds(new Rectangle(50, 360, 148, 24));
    localJPanel.add(localJLabel3);
    this.tActions.setFontMonospaced();
    JScrollPane localJScrollPane2 = new JScrollPane(this.tActions);
    localJScrollPane2.setVerticalScrollBarPolicy(22);
    localJScrollPane2.setBounds(new Rectangle(50, 400, 400, 200));
    localJPanel.add(localJScrollPane2);
  }
  
  void updateDialog()
  {
    this.tProcedure.setText(this.ps.gp);
    this.tParameters.setText(this.ps.parameters);
    this.tActions.setText(this.ps.actionText);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.ps.gp = this.tProcedure.getText();
    this.ps.parameters = this.tParameters.getText();
    this.ps.actionText = this.tActions.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/ProcedureStepDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */