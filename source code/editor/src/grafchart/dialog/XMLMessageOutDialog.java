package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.XMLMessageOut;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class XMLMessageOutDialog
  extends MyJDialog
{
  private MyJTextField tIdentifier = new MyJTextField();
  private JCheckBox publishCheckBox = new JCheckBox();
  private MyJTextField tDestination = new MyJTextField();
  private MyJTextField tType = new MyJTextField();
  private MyJTextField tOrigin = new MyJTextField();
  private MyJTextField tMustReply = new MyJTextField();
  private MyJTextField handleValue = new MyJTextField();
  private GCDocument myObject;
  private XMLMessageOut s;
  
  public XMLMessageOutDialog(Frame paramFrame, GCDocument paramGCDocument, XMLMessageOut paramXMLMessageOut, GCView paramGCView)
  {
    super(paramFrame, "XML Message Out");
    this.myObject = paramGCDocument;
    this.s = paramXMLMessageOut;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 440));
    localJPanel.setPreferredSize(new Dimension(294, 440));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 410, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 410, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("Identifier");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel1);
    this.tIdentifier.setBounds(new Rectangle(50, 40, 200, 24));
    this.tIdentifier.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tIdentifier);
    this.publishCheckBox.setText("Publish (otherwise send)");
    this.publishCheckBox.setBounds(new Rectangle(50, 70, 220, 14));
    localJPanel.add(this.publishCheckBox);
    JLabel localJLabel2 = new JLabel("Topic/Destination");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 100, 220, 24));
    localJPanel.add(localJLabel2);
    this.tDestination.setBounds(new Rectangle(50, 130, 200, 24));
    this.tDestination.setEnabled(true);
    localJPanel.add(this.tDestination);
    JLabel localJLabel3 = new JLabel("Type");
    localJLabel3.setHorizontalAlignment(2);
    localJLabel3.setBounds(new Rectangle(50, 160, 220, 24));
    localJPanel.add(localJLabel3);
    this.tType.setBounds(new Rectangle(50, 190, 200, 24));
    this.tType.setEnabled(true);
    localJPanel.add(this.tType);
    JLabel localJLabel4 = new JLabel("Origin");
    localJLabel4.setHorizontalAlignment(2);
    localJLabel4.setBounds(new Rectangle(50, 220, 220, 24));
    localJPanel.add(localJLabel4);
    this.tOrigin.setBounds(new Rectangle(50, 250, 200, 24));
    this.tOrigin.setEnabled(true);
    localJPanel.add(this.tOrigin);
    JLabel localJLabel5 = new JLabel("MustReply");
    localJLabel5.setHorizontalAlignment(2);
    localJLabel5.setBounds(new Rectangle(50, 280, 220, 24));
    localJPanel.add(localJLabel5);
    this.tMustReply.setBounds(new Rectangle(50, 320, 200, 24));
    this.tMustReply.setEnabled(true);
    localJPanel.add(this.tMustReply);
    JLabel localJLabel6 = new JLabel("Handle");
    localJLabel6.setHorizontalAlignment(2);
    localJLabel6.setBounds(new Rectangle(50, 340, 220, 24));
    localJPanel.add(localJLabel6);
    this.handleValue.setBounds(new Rectangle(50, 370, 200, 24));
    this.handleValue.setEnabled(true);
    localJPanel.add(this.handleValue);
  }
  
  void updateDialog()
  {
    this.tIdentifier.setText(this.s.getXMLIdentifier(false));
    this.publishCheckBox.setSelected(this.s.publish);
    this.tDestination.setText(this.s.destination);
    this.tType.setText(this.s.type);
    this.tOrigin.setText(this.s.origin);
    this.tMustReply.setText(this.s.mustReply);
    this.handleValue.setText("" + this.s.handle);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.handle = parseInt(this.handleValue);
    this.s.setXMLIdentifier(this.tIdentifier.getText());
    this.s.publish = this.publishCheckBox.isSelected();
    this.s.destination = this.tDestination.getText();
    this.s.type = this.tType.getText();
    this.s.origin = this.tOrigin.getText();
    this.s.mustReply = this.tMustReply.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/XMLMessageOutDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */