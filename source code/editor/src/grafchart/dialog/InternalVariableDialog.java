package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.InternalVariable;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InternalVariableDialog
  extends MyJDialog
{
  private MyJTextField tInitialValue = new MyJTextField();
  private JCheckBox isConst = new JCheckBox();
  private JCheckBox isUpdated = new JCheckBox();
  private MyJTextField tUpdateExpression = new MyJTextField();
  private InternalVariable var;
  
  public InternalVariableDialog(Frame paramFrame, InternalVariable paramInternalVariable, GCView paramGCView)
  {
    super(paramFrame, "Variable");
    this.var = paramInternalVariable;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 255));
    localJPanel.setPreferredSize(new Dimension(294, 255));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 220, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 220, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("Initial value");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel1);
    this.tInitialValue.setBounds(new Rectangle(50, 40, 200, 24));
    localJPanel.add(this.tInitialValue);
    this.isConst.setText("Constant");
    this.isConst.setBounds(new Rectangle(50, 70, 140, 24));
    this.isConst.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        InternalVariableDialog.this.updateEnabled();
      }
    });
    localJPanel.add(this.isConst);
    this.isUpdated.setText("Automatic Update");
    this.isUpdated.setBounds(new Rectangle(50, 100, 150, 24));
    this.isUpdated.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        InternalVariableDialog.this.updateEnabled();
      }
    });
    localJPanel.add(this.isUpdated);
    JLabel localJLabel2 = new JLabel("Update expression");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 130, 148, 24));
    localJPanel.add(localJLabel2);
    this.tUpdateExpression.setBounds(new Rectangle(50, 160, 200, 24));
    this.tUpdateExpression.setFontMonospaced();
    localJPanel.add(this.tUpdateExpression);
  }
  
  void updateDialog()
  {
    this.tInitialValue.setText(this.var.getInitialValue());
    this.tInitialValue.setFont(new Font("Dialog", 1, 14));
    this.isConst.setSelected(this.var.isConstant);
    this.isUpdated.setSelected(this.var.isUpdated);
    this.tUpdateExpression.setText(this.var.expression);
    this.tUpdateExpression.setEnabled(this.var.isUpdated);
    updateEnabled();
  }
  
  private void updateEnabled()
  {
    this.isUpdated.setEnabled(!this.isConst.isSelected());
    this.tUpdateExpression.setEnabled((!this.isConst.isSelected()) && (this.isUpdated.isSelected()));
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    try
    {
      this.var.setInitialValue(this.tInitialValue.getText());
    }
    catch (IllegalValueException localIllegalValueException)
    {
      this.tInitialValue.selectAll();
      this.tInitialValue.requestFocus();
      throw localIllegalValueException;
    }
    this.var.setIsConstant(this.isConst.isSelected());
    this.var.isUpdated = this.isUpdated.isSelected();
    this.var.expression = this.tUpdateExpression.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/InternalVariableDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */