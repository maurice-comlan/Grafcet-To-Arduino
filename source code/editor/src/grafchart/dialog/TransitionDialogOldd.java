package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCView;
import grafchart.sfc.GenericTransition;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransitionDialogOldd
  extends MyJDialog
{
  private MyJTextField tCondition = new MyJTextField();
  private JCheckBox usesPrio = new JCheckBox();
  private MyJTextField prioValue = new MyJTextField();
  private GenericTransition t;
  
  public TransitionDialogOldd(Frame paramFrame, GenericTransition paramGenericTransition, GCView paramGCView)
  {
    super(paramFrame, "Transition");
    this.t = paramGenericTransition;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(510, 200));
    localJPanel.setPreferredSize(new Dimension(510, 200));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(130, 150, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(220, 150, 79, 22));
    localJPanel.add(localMyJButton2);
    MyJButton localMyJButton3 = createHelpButton("LangRef_TextLang_Conditions");
    localMyJButton3.setBounds(new Rectangle(310, 150, 79, 22));
    localJPanel.add(localMyJButton3);
    JLabel localJLabel1 = new JLabel("Condition");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(30, 10, 100, 24));
    localJPanel.add(localJLabel1);
    this.tCondition.setBounds(new Rectangle(30, 40, 450, 24));
    this.tCondition.setFontMonospaced();
    localJPanel.add(this.tCondition);
    this.usesPrio.setText("Use Priority");
    this.usesPrio.setBounds(new Rectangle(30, 70, 124, 24));
    this.usesPrio.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        TransitionDialogOldd.this.updateUsesPriority();
      }
    });
    localJPanel.add(this.usesPrio);
    JLabel localJLabel2 = new JLabel("Priority");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(30, 100, 70, 24));
    localJPanel.add(localJLabel2);
    this.prioValue.setBounds(new Rectangle(100, 100, 100, 24));
    localJPanel.add(this.prioValue);
  }
  
  void updateDialog()
  {
    this.tCondition.setText(this.t.getCondition());
    this.usesPrio.setSelected(this.t.hasPriority);
    if (this.t.hasPriority)
    {
      if (this.t.priority == Integer.MAX_VALUE) {
        this.prioValue.setText("");
      } else {
        this.prioValue.setText("" + this.t.priority);
      }
    }
    else {
      this.prioValue.setText("");
    }
    updateUsesPriority();
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    boolean bool = this.usesPrio.isSelected();
    int i = Integer.MAX_VALUE;
    if (bool) {
      i = parseInt(this.prioValue, 1);
    }
    this.t.setCondition(this.tCondition.getText());
    this.t.hasPriority = bool;
    this.t.priority = i;
    if (bool) {
      this.t.showPriority();
    } else {
      this.t.hidePriority();
    }
  }
  
  protected void updateUsesPriority()
  {
    this.prioValue.setEnabled(this.usesPrio.isSelected());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/TransitionDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */