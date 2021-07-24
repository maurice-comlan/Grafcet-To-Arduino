package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IconStep;
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

public class StepIconDialog
  extends MyJDialog
{
  private JCheckBox boxUseIcon = new JCheckBox();
  private MyJTextField tFilename = new MyJTextField();
  public GCDocument myObject;
  public IconStep cp;
  
  public StepIconDialog(Frame paramFrame, GCDocument paramGCDocument, IconStep paramIconStep, GCView paramGCView)
  {
    super(paramFrame, "Step Icon");
    this.myObject = paramGCDocument;
    this.cp = paramIconStep;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 155));
    localJPanel.setPreferredSize(new Dimension(294, 155));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 120, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 120, 79, 22));
    localJPanel.add(localMyJButton2);
    this.boxUseIcon.setText("Use Icon");
    this.boxUseIcon.setBounds(new Rectangle(50, 10, 124, 24));
    localJPanel.add(this.boxUseIcon);
    this.boxUseIcon.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        StepIconDialog.this.updateEnabled();
      }
    });
    JLabel localJLabel = new JLabel("Filename");
    localJLabel.setHorizontalAlignment(2);
    localJLabel.setBounds(new Rectangle(50, 45, 148, 24));
    localJPanel.add(localJLabel);
    this.tFilename.setBounds(new Rectangle(50, 75, 200, 24));
    this.tFilename.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tFilename);
  }
  
  void updateDialog()
  {
    this.boxUseIcon.setSelected(this.cp.isUseIcon());
    this.tFilename.setText(this.cp.getFileName());
    updateEnabled();
  }
  
  private void updateEnabled()
  {
    this.tFilename.setEnabled(this.boxUseIcon.isSelected());
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.cp.setUseIcon(this.boxUseIcon.isSelected());
    this.cp.setFileNameValue(this.tFilename.getText());
    this.cp.setFileName();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/StepIconDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */