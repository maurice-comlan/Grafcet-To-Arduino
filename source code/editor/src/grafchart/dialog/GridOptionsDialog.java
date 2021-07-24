package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.SocketOut;
import grafchart.sfc.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Workspace properties
 * @author dimon
 */
public class GridOptionsDialog
  extends MyJDialog
{
  private MyJTextField tWidth = new MyJTextField();
  private MyJTextField tHeight = new MyJTextField();
  private ButtonGroup gridStyleGroup = new ButtonGroup();
  private JRadioButton gridInvisibleRadio = new JRadioButton();
  private JRadioButton gridDotsRadio = new JRadioButton();
  private JRadioButton gridCrossesRadio = new JRadioButton();
  private JRadioButton gridLinesRadio = new JRadioButton();
  private ButtonGroup moveSnapGroup = new ButtonGroup();
  private JRadioButton moveNoSnapRadio = new JRadioButton();
  private JRadioButton moveJumpRadio = new JRadioButton();
  private JRadioButton moveAfterRadio = new JRadioButton();
  private ButtonGroup resizeSnapGroup = new ButtonGroup();
  private JRadioButton resizeAfterRadio = new JRadioButton();
  private JRadioButton resizeJumpRadio = new JRadioButton();
  private JRadioButton resizeNoSnapRadio = new JRadioButton();
  private JCheckBox hori = new JCheckBox();
  private JCheckBox vert = new JCheckBox();
  private Color myPaperColor;
  private JComboBox<SocketOut.SendMode> cbSendMode = new JComboBox(SocketOut.SendMode.values());
  private GCView myView = null;
  
  public GridOptionsDialog(Frame paramFrame, String paramString, GCView paramGCView)
  {
    super(paramFrame, paramString);
    try
    {
      this.myView = paramGCView;
      jbInit();
      UpdateDialog();
      pack();
      setLocationRelativeTo(paramFrame);
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
  }
  
  void jbInit()
    throws Exception
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(800, 185));
    localJPanel.setPreferredSize(new Dimension(800, 185));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = new MyJButton("Paper Color...", 80);
    localMyJButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        GridOptionsDialog.this.onPaperColor();
      }
    });
    localMyJButton1.setBounds(new Rectangle(24, 108, 116, 24));
    localJPanel.add(localMyJButton1);
    this.moveNoSnapRadio.setText("No snap");
    this.moveNoSnapRadio.setBounds(new Rectangle(255, 36, 86, 24));
    this.moveJumpRadio.setText("Jump");
    this.moveJumpRadio.setBounds(new Rectangle(255, 60, 86, 24));
    this.moveAfterRadio.setText("Afterwards");
    this.moveAfterRadio.setBounds(new Rectangle(255, 84, 98, 24));
    JLabel localJLabel1 = new JLabel("Snap on Move");
    localJLabel1.setBounds(new Rectangle(243, 12, 98, 24));
    localJLabel1.setBounds(new Rectangle(339, 13, 98, 24));
    this.resizeAfterRadio.setText("Afterwards");
    this.resizeAfterRadio.setBounds(new Rectangle(354, 82, 95, 24));
    this.resizeJumpRadio.setText("Jump");
    this.resizeJumpRadio.setBounds(new Rectangle(354, 58, 86, 24));
    this.resizeNoSnapRadio.setText("No snap");
    this.resizeNoSnapRadio.setBounds(new Rectangle(354, 34, 86, 24));
    JLabel localJLabel2 = new JLabel("Snap on Resize");
    localJLabel2.setBounds(new Rectangle(340, 12, 98, 24));
    localJPanel.add(localJLabel2, null);
    MyJButton localMyJButton2 = createOKButton();
    localMyJButton2.setBounds(new Rectangle(200, 148, 79, 22));
    localJPanel.add(localMyJButton2);
    MyJButton localMyJButton3 = createCancelButton();
    localMyJButton3.setBounds(new Rectangle(308, 148, 79, 22));
    localJPanel.add(localMyJButton3);
    JLabel localJLabel3 = new JLabel("Width");
    localJLabel3.setHorizontalAlignment(4);
    localJLabel3.setBounds(new Rectangle(30, 36, 48, 24));
    localJPanel.add(localJLabel3);
    this.tWidth.setBounds(new Rectangle(90, 36, 36, 24));
    localJPanel.add(this.tWidth);
    JLabel localJLabel4 = new JLabel("Height");
    localJLabel4.setHorizontalAlignment(4);
    localJLabel4.setBounds(new Rectangle(30, 60, 48, 24));
    localJPanel.add(localJLabel4);
    localJPanel.add(this.tHeight);
    this.tHeight.setBounds(new Rectangle(90, 60, 36, 24));
    this.gridInvisibleRadio.setText("Invisible");
    this.gridInvisibleRadio.setBounds(new Rectangle(161, 36, 86, 24));
    this.gridStyleGroup.add(this.gridInvisibleRadio);
    localJPanel.add(this.gridInvisibleRadio);
    this.gridDotsRadio.setText("Dots");
    this.gridDotsRadio.setBounds(new Rectangle(161, 60, 86, 24));
    this.gridStyleGroup.add(this.gridDotsRadio);
    localJPanel.add(this.gridDotsRadio);
    this.gridCrossesRadio.setText("Crosses");
    this.gridCrossesRadio.setBounds(new Rectangle(161, 84, 86, 24));
    this.gridStyleGroup.add(this.gridCrossesRadio);
    localJPanel.add(this.gridCrossesRadio);
    this.gridLinesRadio.setText("Lines");
    this.gridLinesRadio.setBounds(new Rectangle(161, 108, 86, 24));
    this.gridStyleGroup.add(this.gridLinesRadio);
    localJPanel.add(this.gridLinesRadio);
    JLabel localJLabel5 = new JLabel("Grid Style");
    localJPanel.add(localJLabel5);
    localJLabel5.setBounds(new Rectangle(149, 12, 98, 24));
    JLabel localJLabel6 = new JLabel("Grid Size");
    localJPanel.add(localJLabel6);
    localJLabel6.setBounds(new Rectangle(29, 12, 96, 24));
    this.moveSnapGroup.add(this.moveNoSnapRadio);
    localJPanel.add(this.moveNoSnapRadio, null);
    this.moveSnapGroup.add(this.moveJumpRadio);
    localJPanel.add(this.moveJumpRadio, null);
    this.moveSnapGroup.add(this.moveAfterRadio);
    localJPanel.add(this.moveAfterRadio, null);
    localJPanel.add(localJLabel1);
    this.resizeSnapGroup.add(this.resizeNoSnapRadio);
    localJPanel.add(this.resizeNoSnapRadio, null);
    this.resizeSnapGroup.add(this.resizeJumpRadio);
    localJPanel.add(this.resizeJumpRadio, null);
    this.resizeSnapGroup.add(this.resizeAfterRadio);
    localJPanel.add(this.resizeAfterRadio, null);
    localJLabel1.setBounds(new Rectangle(243, 12, 98, 24));
    localJLabel1.setBounds(new Rectangle(243, 12, 98, 24));
    JLabel localJLabel7 = new JLabel("Scrollbars");
    localJLabel7.setBounds(new Rectangle(445, 12, 130, 24));
    localJPanel.add(localJLabel7);
    this.hori.setText("Horizontal Scrollbar");
    this.hori.setBounds(new Rectangle(445, 34, 170, 24));
    localJPanel.add(this.hori);
    this.vert.setText("Vertical Scrollbar");
    this.vert.setBounds(new Rectangle(445, 56, 150, 24));
    localJPanel.add(this.vert);
    JLabel localJLabel8 = new JLabel("Socket Send Mode");
    localJLabel8.setBounds(new Rectangle(620, 12, 150, 24));
    localJPanel.add(localJLabel8);
    if (this.myView.getDoc().owner == null) {
      this.cbSendMode.removeItem(SocketOut.SendMode.Inherit);
    }
    this.cbSendMode.setBounds(new Rectangle(620, 34, 150, 24));
    localJPanel.add(this.cbSendMode);
  }
  
  void UpdateDialog()
  {
    this.hori.setSelected(this.myView.getDoc().horizontalScrollBar);
    this.vert.setSelected(this.myView.getDoc().verticalScrollBar);
    this.tWidth.setText(String.valueOf(this.myView.getGridWidth()));
    this.tHeight.setText(String.valueOf(this.myView.getGridHeight()));
    int i = this.myView.getGridStyle();
    if (i == 0) {
      this.gridInvisibleRadio.setSelected(true);
    } else if (i == 1) {
      this.gridDotsRadio.setSelected(true);
    } else if (i == 2) {
      this.gridCrossesRadio.setSelected(true);
    } else if (i == 3) {
      this.gridLinesRadio.setSelected(true);
    }
    int j = this.myView.getSnapMove();
    if (j == 0) {
      this.moveNoSnapRadio.setSelected(true);
    } else if (j == 1) {
      this.moveJumpRadio.setSelected(true);
    } else if (j == 2) {
      this.moveAfterRadio.setSelected(true);
    }
    int k = this.myView.getSnapResize();
    if (k == 0) {
      this.resizeNoSnapRadio.setSelected(true);
    } else if (k == 1) {
      this.resizeJumpRadio.setSelected(true);
    } else if (k == 2) {
      this.resizeAfterRadio.setSelected(true);
    }
    this.myPaperColor = this.myView.getDocument().getPaperColor();
    this.cbSendMode.setSelectedItem(this.myView.getDoc().socketSendMode);
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    int i = parseInt(this.tWidth, 1);
    int j = parseInt(this.tHeight, 1);
    this.myView.setGridWidth(i);
    this.myView.setGridHeight(j);
    this.myView.hasHorizontalScrollBar = this.hori.isSelected();
    if (this.hori.isSelected())
    {
      this.myView.setHorizontalScrollBar(this.myView.horizontalScrollBar);
      this.myView.getDoc().horizontalScrollBar = true;
    }
    else
    {
      this.myView.setHorizontalScrollBar(null);
      this.myView.getDoc().horizontalScrollBar = false;
    }
    this.myView.hasVerticalScrollBar = this.vert.isSelected();
    if (this.vert.isSelected())
    {
      this.myView.setVerticalScrollBar(this.myView.verticalScrollBar);
      this.myView.getDoc().verticalScrollBar = true;
    }
    else
    {
      this.myView.setVerticalScrollBar(null);
      this.myView.getDoc().verticalScrollBar = false;
    }
    int k = 0;
    if (this.gridInvisibleRadio.isSelected()) {
      k = 0;
    } else if (this.gridDotsRadio.isSelected()) {
      k = 1;
    } else if (this.gridCrossesRadio.isSelected()) {
      k = 2;
    } else if (this.gridLinesRadio.isSelected()) {
      k = 3;
    }
    this.myView.setGridStyle(k);
    int m = 0;
    if (this.moveNoSnapRadio.isSelected()) {
      m = 0;
    } else if (this.moveJumpRadio.isSelected()) {
      m = 1;
    } else if (this.moveAfterRadio.isSelected()) {
      m = 2;
    }
    this.myView.setSnapMove(m);
    int n = 0;
    if (this.resizeNoSnapRadio.isSelected()) {
      n = 0;
    } else if (this.resizeJumpRadio.isSelected()) {
      n = 1;
    } else if (this.resizeAfterRadio.isSelected()) {
      n = 2;
    }
    this.myView.setSnapResize(n);
    this.myView.getDocument().setPaperColor(this.myPaperColor);
    this.myView.getDoc().socketSendMode = ((SocketOut.SendMode)this.cbSendMode.getSelectedItem());
  }
  
  private void onPaperColor()
  {
    Color localColor = JColorChooser.showDialog(this, "Paper Color...", this.myPaperColor);
    if (localColor != null) {
      this.myPaperColor = localColor;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/GridOptionsDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */