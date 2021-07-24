package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.XMLMessageIn;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class XMLMessageInDialog
  extends MyJDialog
{
  private MyJTextField tIdentifier = new MyJTextField();
  private MyJTextField topicValue = new MyJTextField();
  private MyJTextField subjectValue = new MyJTextField();
  private JLabel lType = new JLabel();
  private JLabel lOrigin = new JLabel();
  private JLabel lReply = new JLabel();
  private JLabel lHandle = new JLabel();
  private GCDocument myObject;
  private XMLMessageIn s;
  
  public XMLMessageInDialog(Frame paramFrame, GCDocument paramGCDocument, XMLMessageIn paramXMLMessageIn, GCView paramGCView)
  {
    super(paramFrame, "XML Message In");
    this.myObject = paramGCDocument;
    this.s = paramXMLMessageIn;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(294, 360));
    localJPanel.setPreferredSize(new Dimension(294, 360));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(60, 320, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(168, 320, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("Identifier");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel1);
    this.tIdentifier.setBounds(new Rectangle(50, 40, 200, 24));
    this.tIdentifier.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tIdentifier);
    JLabel localJLabel2 = new JLabel("Subject");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 70, 220, 24));
    localJPanel.add(localJLabel2);
    this.subjectValue.setBounds(new Rectangle(50, 100, 200, 24));
    this.subjectValue.setEnabled(true);
    localJPanel.add(this.subjectValue);
    JLabel localJLabel3 = new JLabel("Topic");
    localJLabel3.setHorizontalAlignment(2);
    localJLabel3.setBounds(new Rectangle(50, 130, 220, 24));
    localJPanel.add(localJLabel3);
    this.topicValue.setBounds(new Rectangle(50, 160, 200, 24));
    this.topicValue.setEnabled(true);
    localJPanel.add(this.topicValue);
    this.lType.setHorizontalAlignment(2);
    this.lType.setBounds(new Rectangle(50, 190, 220, 24));
    localJPanel.add(this.lType);
    this.lOrigin.setHorizontalAlignment(2);
    this.lOrigin.setBounds(new Rectangle(50, 220, 220, 24));
    localJPanel.add(this.lOrigin);
    this.lReply.setHorizontalAlignment(2);
    this.lReply.setBounds(new Rectangle(50, 250, 220, 24));
    localJPanel.add(this.lReply);
    this.lHandle.setHorizontalAlignment(2);
    this.lHandle.setBounds(new Rectangle(50, 280, 220, 24));
    localJPanel.add(this.lHandle);
  }
  
  void updateDialog()
  {
    this.tIdentifier.setText(this.s.getXMLIdentifier(false));
    this.subjectValue.setText(this.s.subject);
    this.topicValue.setText(this.s.topic);
    this.lType.setText("Type = " + this.s.type);
    this.lOrigin.setText("Origin = " + this.s.origin);
    this.lReply.setText("MustReply = " + this.s.mustReply);
    this.lHandle.setText("Handle = " + this.s.handle);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.setXMLIdentifier(this.tIdentifier.getText());
    this.s.subject = this.subjectValue.getText();
    this.s.topic = this.topicValue.getText();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/XMLMessageInDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */