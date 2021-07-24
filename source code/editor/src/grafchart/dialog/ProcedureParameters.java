package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextArea;
import grafchart.sfc.GCView;
import grafchart.sfc.GrafcetProcedure;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ProcedureParameters
  extends MyJDialog
{
  private MyJTextArea tParameters = new MyJTextArea();
  private GrafcetProcedure gp;
  
  public ProcedureParameters(Frame paramFrame, GrafcetProcedure paramGrafcetProcedure, GCView paramGCView)
  {
    super(paramFrame, "Procedure Parameters", false);
    this.gp = paramGrafcetProcedure;
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
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(30, 160, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(120, 160, 79, 22));
    localJPanel.add(localMyJButton2);
    MyJButton localMyJButton3 = createHelpButton("LangRef_TextLang_CallParameters");
    localMyJButton3.setBounds(new Rectangle(210, 160, 79, 22));
    localJPanel.add(localMyJButton3);
    JLabel localJLabel = new JLabel("Parameters");
    localJLabel.setHorizontalAlignment(2);
    localJLabel.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel);
    this.tParameters.setFontMonospaced();
    JScrollPane localJScrollPane = new JScrollPane(this.tParameters);
    localJScrollPane.setVerticalScrollBarPolicy(22);
    localJScrollPane.setBounds(new Rectangle(50, 40, 200, 100));
    localJPanel.add(localJScrollPane);
  }
  
  void updateDialog()
  {
    if (this.gp.parameters.equals(""))
    {
      this.gp.defaultParameters = this.gp.getDefaultParameters();
      this.gp.parameters = this.gp.defaultParameters;
    }
    String str = this.gp.getDefaultParameters();
    if (!str.equals(this.gp.defaultParameters))
    {
      this.gp.defaultParameters = str;
      this.gp.parameters = str;
    }
    this.tParameters.setText(this.gp.parameters);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.gp.parameters = this.tParameters.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/ProcedureParameters.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */