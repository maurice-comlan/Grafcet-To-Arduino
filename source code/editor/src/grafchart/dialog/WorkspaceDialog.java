package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.ImageFilter;
import grafchart.sfc.WorkspaceObject;
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

public class WorkspaceDialog
  extends MyJDialog
{
  private MyJTextField textScanCycle = new MyJTextField();
  private MyJTextField tFilename = new MyJTextField();
  private JCheckBox isEnabled = new JCheckBox();
  private JCheckBox useIconBox = new JCheckBox();
  private MyJButton SizeButton;
  private MyJButton FileButton;
  private GCDocument myObject;
  private WorkspaceObject wo;
  
  public WorkspaceDialog(Frame paramFrame, GCDocument paramGCDocument, WorkspaceObject paramWorkspaceObject, GCView paramGCView)
  {
    super(paramFrame, "Workspace Object");
    this.myObject = paramGCDocument;
    this.wo = paramWorkspaceObject;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(360, 200));
    localJPanel.setPreferredSize(new Dimension(360, 200));
    getContentPane().add(localJPanel);
    this.SizeButton = new MyJButton("Def. Size", 68);
    this.SizeButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        WorkspaceDialog.this.onDefSize();
      }
    });
    this.FileButton = new MyJButton("Browse...", 66);
    this.FileButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        WorkspaceDialog.this.onBrowse();
      }
    });
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(40, 170, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(150, 170, 79, 22));
    localJPanel.add(localMyJButton2);
    this.SizeButton.setBounds(new Rectangle(260, 170, 85, 22));
    localJPanel.add(this.SizeButton);
    this.FileButton.setBounds(new Rectangle(260, 135, 90, 22));
    localJPanel.add(this.FileButton);
    this.isEnabled.setText("Enabled");
    this.isEnabled.setBounds(new Rectangle(30, 36, 124, 24));
    localJPanel.add(this.isEnabled);
    JLabel localJLabel1 = new JLabel("Rel. scan period");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(30, 10, 120, 24));
    localJPanel.add(localJLabel1);
    this.textScanCycle.setBounds(new Rectangle(150, 10, 40, 24));
    this.textScanCycle.setEnabled(this.myObject.isModifiable());
    this.textScanCycle.setHorizontalAlignment(2);
    localJPanel.add(this.textScanCycle);
    this.useIconBox.setText("Use Icon");
    this.useIconBox.setBounds(new Rectangle(30, 70, 124, 24));
    this.useIconBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        WorkspaceDialog.this.updateEnabled();
      }
    });
    localJPanel.add(this.useIconBox);
    JLabel localJLabel2 = new JLabel("Icon Filename");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(30, 105, 148, 24));
    localJPanel.add(localJLabel2);
    this.tFilename.setBounds(new Rectangle(30, 135, 200, 24));
    this.tFilename.setEnabled(this.myObject.isModifiable());
    localJPanel.add(this.tFilename);
  }
  
  void updateDialog()
  {
    this.isEnabled.setSelected(this.wo.enabled);
    this.useIconBox.setSelected(this.wo.useIcon);
    this.textScanCycle.setText("" + this.wo.scanCycle);
    this.tFilename.setText(this.wo.fileName);
    updateEnabled();
  }
  
  private void updateEnabled()
  {
    this.tFilename.setEnabled(this.useIconBox.isSelected());
    this.FileButton.setEnabled(this.useIconBox.isSelected());
    this.SizeButton.setEnabled(this.useIconBox.isSelected());
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.wo.scanCycle = parseInt(this.textScanCycle, 1);
    this.wo.setEnabled(this.isEnabled.isSelected());
    this.wo.useIcon = this.useIconBox.isSelected();
    this.wo.fileName = this.tFilename.getText();
    this.wo.setFileName();
  }
  
  void onBrowse()
  {
    ImageFilter localImageFilter = new ImageFilter();
    JFileChooser localJFileChooser;
    if (this.tFilename.getText().equals("")) {
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
      this.wo.setNormalSize();
    }
    catch (Exception localException) {}
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/WorkspaceDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */