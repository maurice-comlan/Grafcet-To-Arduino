package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.Icon;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.ImageFilter;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IconDialog
  extends MyJDialog
{
  private MyJTextField tName = new MyJTextField();
  private GCDocument myObject;
  private Icon s;
  
  public IconDialog(Frame paramFrame, GCDocument paramGCDocument, Icon paramIcon, GCView paramGCView)
  {
    super(paramFrame, "Icon");
    this.myObject = paramGCDocument;
    this.s = paramIcon;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(360, 130));
    localJPanel.setPreferredSize(new Dimension(360, 130));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = new MyJButton("Def. Size", 68);
    localMyJButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        IconDialog.this.onDefSize();
      }
    });
    MyJButton localMyJButton2 = new MyJButton("Browse...", 66);
    localMyJButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        IconDialog.this.onBrowse();
      }
    });
    MyJButton localMyJButton3 = createOKButton();
    localMyJButton3.setBounds(new Rectangle(40, 100, 79, 22));
    localJPanel.add(localMyJButton3);
    MyJButton localMyJButton4 = createCancelButton();
    localMyJButton4.setBounds(new Rectangle(150, 100, 79, 22));
    localJPanel.add(localMyJButton4);
    localJPanel.add(localMyJButton1);
    localMyJButton1.setBounds(new Rectangle(260, 100, 85, 22));
    localJPanel.add(localMyJButton2);
    localMyJButton2.setBounds(new Rectangle(260, 40, 90, 24));
    JLabel localJLabel = new JLabel("Filename");
    localJLabel.setHorizontalAlignment(2);
    localJLabel.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel);
    this.tName.setBounds(new Rectangle(50, 40, 200, 24));
    this.tName.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tName);
  }
  
  void updateDialog()
  {
    this.tName.setText(this.s.getFilename());
    this.tName.setFont(new Font("Dialog", 1, 14));
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.loadImage(this.tName.getText());
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
  
  void onBrowse()
  {
    ImageFilter localImageFilter = new ImageFilter();
    JFileChooser localJFileChooser;
    if (this.tName.getText().equals("../iconlib/graphics/icon.gif")) {
      localJFileChooser = new JFileChooser("");
    } else {
      localJFileChooser = new JFileChooser(this.tName.getText());
    }
    localJFileChooser.setFileFilter(localImageFilter);
    int i = localJFileChooser.showOpenDialog(null);
    if (i == 0) {
      this.tName.setText(localJFileChooser.getSelectedFile().getAbsolutePath());
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/IconDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */