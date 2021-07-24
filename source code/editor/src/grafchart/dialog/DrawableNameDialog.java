package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.Icon;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.Referencable;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawableNameDialog
  extends MyJDialog
{
  private MyJTextField tName = new MyJTextField();
  private GCDocument myObject;
  private Referencable cp;
  
  public DrawableNameDialog(Frame paramFrame, GCDocument paramGCDocument, Referencable paramReferencable, GCView paramGCView)
  {
    super(paramFrame, (paramReferencable instanceof Icon) ? "Icon" : "Drawable");
    this.myObject = paramGCDocument;
    this.cp = paramReferencable;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 130));
    localJPanel.setPreferredSize(new Dimension(294, 130));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 100, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 100, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel = new JLabel("Name");
    localJLabel.setHorizontalAlignment(2);
    localJLabel.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel);
    this.tName.setEnabled(this.myObject.isModifiable());
    this.tName.setBounds(new Rectangle(50, 40, 200, 24));
    localJPanel.add(this.tName);
  }
  
  protected void updateDialog()
  {
    this.tName.setText(this.cp.getName());
    this.tName.setFont(new Font("Dialog", 1, 14));
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.cp.setName(this.tName.getText());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/DrawableNameDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */