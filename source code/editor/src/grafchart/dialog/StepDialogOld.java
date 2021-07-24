package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextArea;
import grafchart.sfc.GCStep;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import se.lth.control.BoxPanel;

/**
 * Edition de l'action relative à une étape
 * @author dimon
 */
public class StepDialogOld
  extends MyJDialog
{
  private MyJTextArea tActions = new MyJTextArea();
  private GCStep s;
  
  public StepDialogOld(Frame parentFrame, GCStep step, GCView paramGCView)
  {
    super(parentFrame, "Step", false);
    this.s = step;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private void init()
  {    
    JPanel localJPanel1 = new JPanel(null);
    localJPanel1.setMinimumSize(new Dimension(494, 400));
    localJPanel1.setPreferredSize(new Dimension(494, 400));  
    localJPanel1.setLayout(new BorderLayout());  
    JLabel localJLabel = new JLabel("Step Actions");
    localJLabel.setHorizontalAlignment(2);
    localJLabel.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel1.add(localJLabel, "North");
    this.tActions.setFontMonospaced();
    JScrollPane localJScrollPane = new JScrollPane(this.tActions);
    localJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    localJPanel1.add(localJScrollPane, "Center");
    
    JPanel localJPanel3 = new JPanel();
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(130, 360, 79, 22));
    localJPanel3.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(220, 360, 79, 22));
    localJPanel3.add(localMyJButton2);
    MyJButton localMyJButton3 = createHelpButton("LanguageOverview_ActionLanguage");
    localMyJButton3.setBounds(new Rectangle(310, 360, 79, 22));
    localJPanel3.add(localMyJButton3);
    
    JPanel localJPanel2 = new JPanel();
    localJPanel2.setLayout(new BorderLayout());
    localJPanel2.add(localJPanel1, "Center");    
    localJPanel2.add(localJPanel3, "South");
    
    BoxPanel localBoxPanel = new BoxPanel(BoxPanel.HORIZONTAL);
    localBoxPanel.addFixed(20);
    localBoxPanel.add(localJPanel2);
    localBoxPanel.addFixed(20);
    localBoxPanel.setBorder(new EmptyBorder(10, 3, 3, 3));    
    setContentPane(localBoxPanel);   
  }
  
  void updateDialog()
  {
    this.tActions.setText(this.s.getActionText());
  }
  
  @Override
  protected void saveData()
    throws IllegalValueException
  {
    this.s.setActionText(this.tActions.getText());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/StepDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */