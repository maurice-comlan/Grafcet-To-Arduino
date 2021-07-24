package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.GraphicalButton;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.ImageFilter;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicalButtonDialog
  extends MyJDialog
{
  private MyJTextField tFilename = new MyJTextField();
  private JCheckBox activeStatusBox = new JCheckBox();
  private MyJTextField tActions = new MyJTextField();
  private GCDocument myObject;
  private GraphicalButton s;
  
  public GraphicalButtonDialog(Frame paramFrame, GCDocument paramGCDocument, GraphicalButton paramGraphicalButton, GCView paramGCView)
  {
    super(paramFrame, "Graphical Action Button");
    this.myObject = paramGCDocument;
    this.s = paramGraphicalButton;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(360, 190));
    localJPanel.setPreferredSize(new Dimension(360, 190));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = new MyJButton("Def. Size", 68);
    localMyJButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        GraphicalButtonDialog.this.onDefSize();
      }
    });
    MyJButton localMyJButton2 = new MyJButton("Browse...", 66);
    localMyJButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        GraphicalButtonDialog.this.onBrowse();
      }
    });
    MyJButton localMyJButton3 = createOKButton();
    localMyJButton3.setBounds(new Rectangle(40, 160, 79, 22));
    localJPanel.add(localMyJButton3);
    MyJButton localMyJButton4 = createCancelButton();
    localMyJButton4.setBounds(new Rectangle(150, 160, 79, 22));
    localJPanel.add(localMyJButton4);
    localJPanel.add(localMyJButton1);
    localMyJButton1.setBounds(new Rectangle(260, 160, 85, 22));
    localJPanel.add(localMyJButton2);
    localMyJButton2.setBounds(new Rectangle(260, 40, 90, 22));
    JLabel localJLabel1 = new JLabel("File");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel1);
    this.tFilename.setBounds(new Rectangle(50, 40, 200, 24));
    this.tFilename.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tFilename);
    JLabel localJLabel2 = new JLabel("Actions");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 70, 220, 24));
    localJPanel.add(localJLabel2);
    this.tActions.setBounds(new Rectangle(50, 100, 200, 24));
    this.tActions.setEnabled(true);
    this.tActions.setFontMonospaced();
    localJPanel.add(this.tActions);
    this.activeStatusBox.setText("Enabled when stopped");
    this.activeStatusBox.setBounds(new Rectangle(45, 130, 200, 24));
    localJPanel.add(this.activeStatusBox);
  }
  
  void updateDialog()
  {
    this.tFilename.setText(this.s.getFilename());
    this.tActions.setText(this.s.actionString);
    this.activeStatusBox.setSelected(this.s.enableWhenStopped);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.loadImage(this.tFilename.getText());
    this.s.actionString = this.tActions.getText();
    this.s.enableWhenStopped = this.activeStatusBox.isSelected();
  }
  
  void onBrowse()
  {
    ImageFilter localImageFilter = new ImageFilter();
    JFileChooser localJFileChooser;
    if (this.tFilename.getText().equals("../doc/tutorial/button10.gif")) {
      localJFileChooser = new JFileChooser("");
    } else {
      localJFileChooser = new JFileChooser(this.tFilename.getText());
    }
    localJFileChooser.setFileFilter(localImageFilter);
    int i = localJFileChooser.showOpenDialog(null);
    if (i == 0) {
      this.tFilename.setText(localJFileChooser.getSelectedFile().getAbsolutePath());
    }
  }
  
  void onDefSize()
  {
    try
    {
      saveData();
      this.s.setNormalSize();
    }
    catch (Exception localException) {}
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/GraphicalButtonDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */