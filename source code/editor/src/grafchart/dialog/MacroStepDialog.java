package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextArea;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.MacroStep;
import grafchart.sfc.MacroStep.ResumeMode;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MacroStepDialog
  extends MyJDialog
{
  private MyJTextArea tActions = new MyJTextArea();
  private JComboBox cbResumeMode = new JComboBox(MacroStep.ResumeMode.values());
  private MacroStep s;
  
  public MacroStepDialog(Frame paramFrame, MacroStep paramMacroStep, GCView paramGCView)
  {
    super(paramFrame, "Macro Step");
    this.s = paramMacroStep;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(494, 410));
    localJPanel.setPreferredSize(new Dimension(494, 410));
    getContentPane().add(localJPanel);
    JLabel localJLabel1 = new JLabel("Macro Step Actions", 2);
    localJLabel1.setBounds(50, 10, 148, 24);
    localJPanel.add(localJLabel1);
    this.tActions.setFontMonospaced();
    JScrollPane localJScrollPane = new JScrollPane(this.tActions);
    localJScrollPane.setVerticalScrollBarPolicy(22);
    localJScrollPane.setBounds(50, 30, 400, 300);
    localJPanel.add(localJScrollPane);
    JLabel localJLabel2 = new JLabel("Resume Mode", 2);
    localJLabel2.setBounds(50, 340, 100, 22);
    localJPanel.add(localJLabel2);
    this.cbResumeMode.setBounds(160, 340, 100, 22);
    localJPanel.add(this.cbResumeMode);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(130, 380, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(220, 380, 79, 22));
    localJPanel.add(localMyJButton2);
    MyJButton localMyJButton3 = createHelpButton("LanguageOverview_ActionLanguage");
    localMyJButton3.setBounds(new Rectangle(310, 380, 79, 22));
    localJPanel.add(localMyJButton3);
  }
  
  void updateDialog()
  {
    this.tActions.setText(this.s.actionText);
    this.cbResumeMode.setSelectedItem(this.s.getResumeMode());
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.actionText = this.tActions.getText();
    this.s.setResumeMode((MacroStep.ResumeMode)this.cbResumeMode.getSelectedItem());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/MacroStepDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */