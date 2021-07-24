package grafchart.dialog;

import com.nwoods.jgo.JGoText;
import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextArea;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.IllegalValueException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class TextPropsDialog
  extends MyJDialog
{
  private MyJTextField heightField = new MyJTextField();
  private MyJTextField tX = new MyJTextField();
  private MyJTextField tY = new MyJTextField();
  private JCheckBox visibleBox = new JCheckBox();
  private JCheckBox selectableBox = new JCheckBox();
  private JCheckBox resizableBox = new JCheckBox();
  private JCheckBox draggableBox = new JCheckBox();
  private JCheckBox editableBox = new JCheckBox();
  private JCheckBox boldBox = new JCheckBox();
  private JCheckBox italicBox = new JCheckBox();
  private JCheckBox underlineBox = new JCheckBox();
  private JCheckBox strikeBox = new JCheckBox();
  private MyJTextField tText = new MyJTextField();
  private JComboBox faceBox = new JComboBox();
  private String selectedFace = "";
  private ButtonGroup alignGroup = new ButtonGroup();
  private JRadioButton alignLeftRadio = new JRadioButton();
  private JRadioButton alignCenterRadio = new JRadioButton();
  private JRadioButton alignRightRadio = new JRadioButton();
  private MyJTextField tFontSize = new MyJTextField();
  private JCheckBox transparentBox = new JCheckBox();
  private MyJTextArea textArea = new MyJTextArea();
  private JScrollPane textAreaScroll = new JScrollPane(this.textArea);
  private JCheckBox editSingle = new JCheckBox();
  private JCheckBox selectBack = new JCheckBox();
  private JCheckBox twoDScale = new JCheckBox();
  private JCheckBox clipping = new JCheckBox();
  private JCheckBox autoResize = new JCheckBox();
  private Color myTextColor;
  private Color myBkColor;
  private JGoText myObject;
  
  public TextPropsDialog(Frame paramFrame, String paramString, JGoText paramJGoText)
  {
    super(paramFrame, paramString);
    try
    {
      jbInit();
      this.myObject = paramJGoText;
      UpdateDialog();
      pack();
      setLocationRelativeTo(paramFrame);
      if (this.myObject.isMultiline()) {
        this.textArea.requestFocus();
      } else {
        this.tText.requestFocus();
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  void jbInit()
    throws Exception
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(565, 310));
    localJPanel.setPreferredSize(new Dimension(565, 310));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = new MyJButton("Text Color...", 84);
    localMyJButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        TextPropsDialog.this.onTextColor();
      }
    });
    MyJButton localMyJButton2 = new MyJButton("Background...", 66);
    localMyJButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        TextPropsDialog.this.onBackgroundColor();
      }
    });
    setTitle("Text Properties");
    MyJButton localMyJButton3 = createOKButton();
    localMyJButton3.setBounds(new Rectangle(56, 272, 79, 22));
    localJPanel.add(localMyJButton3);
    MyJButton localMyJButton4 = createCancelButton();
    localMyJButton4.setBounds(new Rectangle(264, 272, 79, 22));
    localJPanel.add(localMyJButton4);
    MyJButton localMyJButton5 = createApplyButton();
    localMyJButton5.setBounds(new Rectangle(161, 272, 79, 22));
    localJPanel.add(localMyJButton5);
    JLabel localJLabel1 = new JLabel("Height");
    localJLabel1.setHorizontalAlignment(4);
    localJLabel1.setBounds(new Rectangle(24, 36, 48, 24));
    localJPanel.add(localJLabel1);
    this.heightField.setEditable(false);
    this.heightField.setBounds(new Rectangle(84, 36, 36, 24));
    localJPanel.add(this.heightField);
    JLabel localJLabel2 = new JLabel("x");
    localJLabel2.setHorizontalAlignment(4);
    localJLabel2.setBounds(new Rectangle(24, 60, 48, 24));
    localJPanel.add(localJLabel2);
    this.tX.setBounds(new Rectangle(84, 60, 36, 24));
    localJPanel.add(this.tX);
    JLabel localJLabel3 = new JLabel("y");
    localJLabel3.setHorizontalAlignment(4);
    localJLabel3.setBounds(new Rectangle(24, 84, 48, 24));
    localJPanel.add(localJLabel3);
    this.tY.setBounds(new Rectangle(84, 84, 36, 24));
    localJPanel.add(this.tY);
    JLabel localJLabel4 = new JLabel("Font Size");
    localJLabel4.setHorizontalAlignment(4);
    localJLabel4.setBounds(new Rectangle(12, 108, 64, 24));
    localJPanel.add(localJLabel4);
    this.tFontSize.setBounds(new Rectangle(84, 108, 36, 24));
    localJPanel.add(this.tFontSize);
    JLabel localJLabel5 = new JLabel("Text");
    localJLabel5.setHorizontalAlignment(4);
    localJLabel5.setBounds(new Rectangle(132, 36, 40, 24));
    localJPanel.add(localJLabel5);
    this.tText.setBounds(new Rectangle(180, 36, 324, 24));
    localJPanel.add(this.tText);
    this.textAreaScroll.setBounds(new Rectangle(180, 36, 209, 67));
    localJPanel.add(this.textAreaScroll);
    JLabel localJLabel6 = new JLabel("Face");
    localJLabel6.setHorizontalAlignment(4);
    localJLabel6.setBounds(new Rectangle(136, 108, 36, 24));
    localJPanel.add(localJLabel6);
    this.faceBox.setBounds(new Rectangle(180, 108, 324, 24));
    this.faceBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JComboBox localJComboBox = (JComboBox)paramAnonymousActionEvent.getSource();
        TextPropsDialog.this.selectedFace = ((String)localJComboBox.getSelectedItem());
      }
    });
    localJPanel.add(this.faceBox);
    this.visibleBox.setText("Visible");
    this.visibleBox.setBounds(new Rectangle(24, 144, 96, 24));
    localJPanel.add(this.visibleBox);
    this.selectableBox.setText("Selectable");
    this.selectableBox.setBounds(new Rectangle(24, 168, 96, 24));
    localJPanel.add(this.selectableBox);
    this.resizableBox.setText("Resizable");
    this.resizableBox.setBounds(new Rectangle(24, 192, 96, 24));
    localJPanel.add(this.resizableBox);
    this.draggableBox.setText("Draggable");
    this.draggableBox.setBounds(new Rectangle(24, 216, 96, 24));
    localJPanel.add(this.draggableBox);
    this.twoDScale.setText("2D Scale");
    this.twoDScale.setBounds(new Rectangle(24, 240, 90, 24));
    localJPanel.add(this.twoDScale);
    this.autoResize.setText("AutoResize");
    this.autoResize.setBounds(new Rectangle(132, 144, 95, 24));
    localJPanel.add(this.autoResize);
    this.clipping.setText("Clipping");
    this.clipping.setBounds(new Rectangle(132, 168, 90, 24));
    localJPanel.add(this.clipping);
    this.editableBox.setText("Editable");
    this.editableBox.setBounds(new Rectangle(132, 192, 84, 24));
    localJPanel.add(this.editableBox);
    this.editSingle.setText("Single Click Edit");
    this.editSingle.setBounds(new Rectangle(132, 240, 130, 24));
    localJPanel.add(this.editSingle);
    this.boldBox.setText("Bold");
    this.boldBox.setBounds(new Rectangle(228, 144, 84, 24));
    localJPanel.add(this.boldBox);
    this.italicBox.setText("Italic");
    this.italicBox.setBounds(new Rectangle(228, 168, 84, 24));
    localJPanel.add(this.italicBox);
    this.underlineBox.setText("Underline");
    this.underlineBox.setBounds(new Rectangle(228, 192, 90, 24));
    localJPanel.add(this.underlineBox);
    this.strikeBox.setText("Strike");
    this.strikeBox.setBounds(new Rectangle(228, 216, 84, 24));
    localJPanel.add(this.strikeBox);
    JLabel localJLabel7 = new JLabel("Alignment");
    localJLabel7.setBounds(324, 144, 90, 24);
    localJPanel.add(localJLabel7);
    this.alignLeftRadio.setText("Left");
    this.alignLeftRadio.setBounds(new Rectangle(324, 168, 90, 24));
    this.alignGroup.add(this.alignLeftRadio);
    localJPanel.add(this.alignLeftRadio);
    this.alignCenterRadio.setText("Center");
    this.alignCenterRadio.setBounds(new Rectangle(324, 192, 84, 24));
    this.alignGroup.add(this.alignCenterRadio);
    localJPanel.add(this.alignCenterRadio);
    this.alignRightRadio.setText("Right");
    this.alignRightRadio.setBounds(new Rectangle(324, 216, 95, 24));
    this.alignGroup.add(this.alignRightRadio);
    localJPanel.add(this.alignRightRadio);
    localMyJButton1.setBounds(new Rectangle(420, 136, 117, 24));
    localJPanel.add(localMyJButton1);
    localMyJButton2.setBounds(new Rectangle(420, 166, 117, 24));
    localJPanel.add(localMyJButton2);
    this.transparentBox.setText("Transparent");
    this.transparentBox.setBounds(new Rectangle(420, 192, 100, 24));
    localJPanel.add(this.transparentBox);
    this.selectBack.setText("Select Background");
    this.selectBack.setBounds(new Rectangle(420, 216, 140, 24));
    localJPanel.add(this.selectBack);
  }
  
  void UpdateDialog()
  {
    Rectangle localRectangle = this.myObject.getBoundingRect();
    this.heightField.setText(String.valueOf(localRectangle.height));
    Point localPoint = this.myObject.getLocation();
    this.tX.setText(String.valueOf(localPoint.x));
    this.tY.setText(String.valueOf(localPoint.y));
    this.tFontSize.setText(String.valueOf(this.myObject.getFontSize()));
    this.visibleBox.setSelected(this.myObject.isVisible());
    this.selectableBox.setSelected(this.myObject.isSelectable());
    this.resizableBox.setSelected(this.myObject.isResizable());
    this.draggableBox.setSelected(this.myObject.isDraggable());
    this.editableBox.setSelected(this.myObject.isEditable());
    this.boldBox.setSelected(this.myObject.isBold());
    this.italicBox.setSelected(this.myObject.isItalic());
    this.underlineBox.setSelected(this.myObject.isUnderline());
    this.strikeBox.setSelected(this.myObject.isStrikeThrough());
    this.tText.setText(this.myObject.getText());
    this.textArea.setText(this.myObject.getText());
    if (this.myObject.isMultiline())
    {
      this.tText.setVisible(false);
      this.textAreaScroll.setVisible(true);
    }
    else
    {
      this.tText.setVisible(true);
      this.textAreaScroll.setVisible(false);
    }
    String[] arrayOfString = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Serif");
    localArrayList.add("SansSerif");
    localArrayList.add("Monospaced");
    localArrayList.add("Dialog");
    localArrayList.add("DialogInput");
    for (int i = 0; i < arrayOfString.length; i++) {
      if (localArrayList.indexOf(arrayOfString[i]) == -1) {
        localArrayList.add(arrayOfString[i]);
      }
    }
    int i = 0;
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      String localObject = (String)localIterator.next();
      this.faceBox.insertItemAt(localObject, i);
      i++;
    }
    int j = 0;
    i = 0;
    Object localObject = localArrayList.iterator();
    String str;
    while (((Iterator)localObject).hasNext())
    {
      str = (String)((Iterator)localObject).next();
      if (str.equals(this.myObject.getFaceName()))
      {
        this.faceBox.setSelectedIndex(i);
        j = 1;
        this.selectedFace = this.myObject.getFaceName();
      }
      i++;
    }
    if (j == 0)
    {
      i = 0;
      localObject = localArrayList.iterator();
      while (((Iterator)localObject).hasNext())
      {
        str = (String)((Iterator)localObject).next();
        if (str.equals("Serif"))
        {
          this.selectedFace = "Serif";
          this.faceBox.setSelectedIndex(i);
        }
        i++;
      }
    }
    int k = this.myObject.getAlignment();
    if (k == 1) {
      this.alignLeftRadio.setSelected(true);
    } else if (k == 3) {
      this.alignRightRadio.setSelected(true);
    } else {
      this.alignCenterRadio.setSelected(true);
    }
    this.myTextColor = this.myObject.getTextColor();
    this.myBkColor = this.myObject.getBkColor();
    this.transparentBox.setSelected(this.myObject.isTransparent());
    this.editSingle.setSelected(this.myObject.isEditOnSingleClick());
    this.twoDScale.setSelected(this.myObject.is2DScale());
    this.clipping.setSelected(this.myObject.isClipping());
    this.autoResize.setSelected(this.myObject.isAutoResize());
    this.selectBack.setSelected(this.myObject.isSelectBackground());
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    int i = parseInt(this.tX);
    int j = parseInt(this.tY);
    int k = parseInt(this.tFontSize);
    this.myObject.setLocation(new Point(i, j));
    this.myObject.setFontSize(k);
    this.myObject.setVisible(this.visibleBox.isSelected());
    this.myObject.setSelectable(this.selectableBox.isSelected());
    this.myObject.setResizable(this.resizableBox.isSelected());
    this.myObject.setDraggable(this.draggableBox.isSelected());
    this.myObject.setEditable(this.editableBox.isSelected());
    this.myObject.setBold(this.boldBox.isSelected());
    this.myObject.setItalic(this.italicBox.isSelected());
    this.myObject.setUnderline(this.underlineBox.isSelected());
    this.myObject.setStrikeThrough(this.strikeBox.isSelected());
    if (this.myObject.isMultiline()) {
      this.myObject.setText(this.textArea.getText());
    } else {
      this.myObject.setText(this.tText.getText());
    }
    this.myObject.setFaceName(this.selectedFace);
    int m;
    if (this.alignLeftRadio.isSelected()) {
      m = 1;
    } else if (this.alignRightRadio.isSelected()) {
      m = 3;
    } else {
      m = 2;
    }
    this.myObject.setAlignment(m);
    this.myObject.setTextColor(this.myTextColor);
    this.myObject.setBkColor(this.myBkColor);
    this.myObject.setTransparent(this.transparentBox.isSelected());
    this.myObject.setEditOnSingleClick(this.editSingle.isSelected());
    this.myObject.set2DScale(this.twoDScale.isSelected());
    this.myObject.setClipping(this.clipping.isSelected());
    this.myObject.setAutoResize(this.autoResize.isSelected());
    this.myObject.setSelectBackground(this.selectBack.isSelected());
  }
  
  void onTextColor()
  {
    Color localColor = JColorChooser.showDialog(this, "Foreground Color", this.myTextColor);
    if (localColor != null) {
      this.myTextColor = localColor;
    }
  }
  
  void onBackgroundColor()
  {
    Color localColor = JColorChooser.showDialog(this, "Foreground Color", this.myBkColor);
    if (localColor != null) {
      this.myBkColor = localColor;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/TextPropsDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */