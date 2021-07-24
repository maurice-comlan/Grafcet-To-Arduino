package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextArea;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.XMLMessageIn;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class XMLMessageInProcedureDialog
  extends MyJDialog
{
  private MyJTextField tProcedure = new MyJTextField();
  private MyJTextArea tParameters = new MyJTextArea();
  private JScrollPane spParameters = new JScrollPane(this.tParameters);
  private JCheckBox useBox = new JCheckBox();
  private GCDocument myObject;
  private XMLMessageIn ps;
  
  public XMLMessageInProcedureDialog(Frame paramFrame, GCDocument paramGCDocument, XMLMessageIn paramXMLMessageIn, GCView paramGCView)
  {
    super(paramFrame, "XMLMessageIn");
    this.myObject = paramGCDocument;
    this.ps = paramXMLMessageIn;
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
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(120, 360, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(300, 360, 79, 22));
    localJPanel.add(localMyJButton2);
    this.useBox.setText("Use procedure");
    this.useBox.setBounds(new Rectangle(50, 10, 200, 24));
    this.useBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        XMLMessageInProcedureDialog.this.updateEnabled();
      }
    });
    localJPanel.add(this.useBox);
    JLabel localJLabel1 = new JLabel("Procedure");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 34, 148, 24));
    localJPanel.add(localJLabel1);
    this.tProcedure.setBounds(new Rectangle(50, 64, 400, 24));
    this.tProcedure.setEnabled(this.myObject.isModifiable());
    this.tProcedure.setFontMonospaced();
    localJPanel.add(this.tProcedure);
    JLabel localJLabel2 = new JLabel("Procedure Parameters");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 104, 200, 24));
    localJPanel.add(localJLabel2);
    this.tParameters.setFontMonospaced();
    this.spParameters.setVerticalScrollBarPolicy(22);
    this.spParameters.setBounds(new Rectangle(50, 144, 400, 200));
    localJPanel.add(this.spParameters);
  }
  
  void updateDialog()
  {
    this.tProcedure.setText(this.ps.gp);
    this.tParameters.setText(this.ps.parameters);
    this.useBox.setSelected(this.ps.useProcedure);
    updateEnabled();
  }
  
  private void updateEnabled()
  {
    this.tProcedure.setEnabled(this.useBox.isSelected());
    this.tParameters.setEnabled(this.useBox.isSelected());
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.ps.gp = this.tProcedure.getText();
    this.ps.parameters = this.tParameters.getText();
    this.ps.useProcedure = this.useBox.isSelected();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/XMLMessageInProcedureDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */