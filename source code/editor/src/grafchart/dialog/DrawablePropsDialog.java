package grafchart.dialog;

import com.nwoods.jgo.JGo3DRect;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoDrawable;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoStroke;
import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.Utils;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DrawablePropsDialog
  extends MyJDialog
{
  private MyJTextField heightField = new MyJTextField();
  private MyJTextField xField = new MyJTextField();
  private MyJTextField yField = new MyJTextField();
  private JCheckBox visibleBox = new JCheckBox();
  private JCheckBox selectableBox = new JCheckBox();
  private JCheckBox resizableBox = new JCheckBox();
  private JCheckBox draggableBox = new JCheckBox();
  private MyJTextField widthField = new MyJTextField();
  private JCheckBox solidBrushBox = new JCheckBox();
  private ButtonGroup penGroup = new ButtonGroup();
  private JRadioButton solidPenButton = new JRadioButton();
  private JRadioButton dashedPenButton = new JRadioButton();
  private JRadioButton dottedPenButton = new JRadioButton();
  private JRadioButton dashdotPenButton = new JRadioButton();
  private JRadioButton dashdotdotPenButton = new JRadioButton();
  private JRadioButton customPenButton = new JRadioButton();
  private JRadioButton noPenButton = new JRadioButton();
  private MyJTextField tPenWidth = new MyJTextField();
  private JCheckBox raised = new JCheckBox();
  private ButtonGroup arrowGroup = new ButtonGroup();
  private JRadioButton noArrowButton = new JRadioButton();
  private JRadioButton fromArrowButton = new JRadioButton();
  private JRadioButton toArrowButton = new JRadioButton();
  private JRadioButton bothArrowButton = new JRadioButton();
  private JCheckBox isCubic = new JCheckBox();
  private Color myBrushColor;
  private Color myPenColor;
  private JGoDrawable myObject;
  
  public DrawablePropsDialog(Frame paramFrame, String paramString, JGoDrawable paramJGoDrawable)
  {
    super(paramFrame, paramString);
    try
    {
      this.myObject = paramJGoDrawable;
      jbInit();
      pack();
      setLocationRelativeTo(paramFrame);
      UpdateDialog();
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
    }
  }
  
  void jbInit()
    throws Exception
  {
    JPanel localJPanel1 = new JPanel(null);
    localJPanel1.setMinimumSize(new Dimension(416, 320));
    localJPanel1.setPreferredSize(new Dimension(416, 320));
    getContentPane().add(localJPanel1);
    MyJButton localMyJButton1 = new MyJButton("Pen Color...", 80);
    localMyJButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        DrawablePropsDialog.this.onPenColor();
      }
    });
    localMyJButton1.setBounds(new Rectangle(127, 258, 108, 24));
    localJPanel1.add(localMyJButton1);
    MyJButton localMyJButton2 = new MyJButton("Fill Color...", 70);
    localMyJButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        DrawablePropsDialog.this.onBrushColor();
      }
    });
    localMyJButton2.setBounds(new Rectangle(283, 258, 108, 24));
    if (!this.myObject.getClass().getName().equals("grafchart.sfc.GCStroke")) {
      localJPanel1.add(localMyJButton2);
    }
    MyJButton localMyJButton3 = createOKButton();
    localMyJButton3.setBounds(new Rectangle(56, 293, 79, 22));
    localJPanel1.add(localMyJButton3);
    MyJButton localMyJButton4 = createCancelButton();
    localMyJButton4.setBounds(new Rectangle(264, 293, 79, 22));
    localJPanel1.add(localMyJButton4);
    MyJButton localMyJButton5 = createApplyButton();
    localMyJButton5.setBounds(new Rectangle(161, 293, 79, 22));
    localJPanel1.add(localMyJButton5);
    JLabel localJLabel1 = new JLabel("Height");
    localJLabel1.setHorizontalAlignment(4);
    localJLabel1.setBounds(new Rectangle(132, 60, 48, 24));
    localJPanel1.add(localJLabel1);
    this.heightField.setBounds(new Rectangle(192, 60, 36, 24));
    localJPanel1.add(this.heightField);
    this.xField.setBounds(new Rectangle(84, 36, 36, 24));
    localJPanel1.add(this.xField);
    JLabel localJLabel2 = new JLabel("x");
    localJLabel2.setHorizontalAlignment(4);
    localJLabel2.setBounds(new Rectangle(24, 36, 48, 24));
    localJPanel1.add(localJLabel2);
    this.yField.setBounds(new Rectangle(84, 60, 36, 24));
    localJPanel1.add(this.yField);
    JLabel localJLabel3 = new JLabel("y");
    localJLabel3.setHorizontalAlignment(4);
    localJLabel3.setBounds(new Rectangle(24, 60, 48, 24));
    localJPanel1.add(localJLabel3);
    if ((this.myObject instanceof JGoStroke))
    {
      this.isCubic.setText("Cubic Bezier");
      this.isCubic.setBounds(new Rectangle(260, 36, 124, 24));
      localJPanel1.add(this.isCubic);
    }
    this.visibleBox.setText("Visible");
    this.visibleBox.setBounds(new Rectangle(24, 96, 72, 24));
    localJPanel1.add(this.visibleBox);
    this.selectableBox.setText("Selectable");
    this.selectableBox.setBounds(new Rectangle(24, 120, 87, 24));
    localJPanel1.add(this.selectableBox);
    this.resizableBox.setText("Resizable");
    this.resizableBox.setBounds(new Rectangle(24, 144, 84, 24));
    localJPanel1.add(this.resizableBox);
    this.draggableBox.setText("Draggable");
    this.draggableBox.setBounds(new Rectangle(24, 168, 89, 24));
    localJPanel1.add(this.draggableBox);
    JLabel localJLabel4 = new JLabel("Width");
    localJLabel4.setHorizontalAlignment(4);
    localJPanel1.add(localJLabel4);
    localJLabel4.setBounds(new Rectangle(132, 36, 48, 24));
    this.widthField.setBounds(new Rectangle(192, 36, 36, 24));
    localJPanel1.add(this.widthField);
    this.solidBrushBox.setText("Solid Brush");
    this.solidBrushBox.setOpaque(false);
    this.solidBrushBox.setBounds(new Rectangle(288, 132, 108, 24));
    if (!this.myObject.getClass().getName().equals("grafchart.sfc.GCStroke")) {
      localJPanel1.add(this.solidBrushBox);
    }
    if (this.myObject.getClass().getName().equals("grafchart.sfc.GCStroke"))
    {
      this.noArrowButton.setText("No Arrows");
      this.noArrowButton.setOpaque(false);
      this.arrowGroup.add(this.noArrowButton);
      localJPanel1.add(this.noArrowButton);
      this.noArrowButton.setBounds(new Rectangle(288, 132, 120, 12));
      this.toArrowButton.setText("To Arrow");
      this.toArrowButton.setOpaque(false);
      this.arrowGroup.add(this.toArrowButton);
      localJPanel1.add(this.toArrowButton);
      this.toArrowButton.setBounds(new Rectangle(288, 149, 120, 12));
      this.fromArrowButton.setText("From Arrow");
      this.fromArrowButton.setOpaque(false);
      this.fromArrowButton.setBounds(new Rectangle(288, 166, 120, 12));
      this.arrowGroup.add(this.fromArrowButton);
      localJPanel1.add(this.fromArrowButton);
      this.bothArrowButton.setText("Both Arrows");
      this.bothArrowButton.setOpaque(false);
      this.bothArrowButton.setBounds(new Rectangle(288, 183, 120, 12));
      this.arrowGroup.add(this.bothArrowButton);
      localJPanel1.add(this.bothArrowButton);
    }
    JLabel localJLabel5 = new JLabel(this.myObject.getClass().getName());
    localJLabel5.setBounds(new Rectangle(26, 4, 364, 24));
    localJPanel1.add(localJLabel5);
    this.solidPenButton.setText("Solid Line Pen");
    this.solidPenButton.setOpaque(false);
    this.solidPenButton.setBounds(new Rectangle(120, 149, 120, 12));
    this.penGroup.add(this.solidPenButton);
    localJPanel1.add(this.solidPenButton);
    this.dashedPenButton.setText("Dashed Line Pen");
    this.dashedPenButton.setOpaque(false);
    this.dashedPenButton.setBounds(new Rectangle(120, 166, 130, 12));
    this.penGroup.add(this.dashedPenButton);
    localJPanel1.add(this.dashedPenButton);
    this.dottedPenButton.setText("Dotted Line Pen");
    this.dottedPenButton.setOpaque(false);
    this.dottedPenButton.setBounds(new Rectangle(120, 183, 125, 12));
    this.penGroup.add(this.dottedPenButton);
    localJPanel1.add(this.dottedPenButton);
    this.dashdotPenButton.setText("Dash Dot Pen");
    this.dashdotPenButton.setOpaque(false);
    this.dashdotPenButton.setBounds(new Rectangle(120, 200, 120, 12));
    this.penGroup.add(this.dashdotPenButton);
    localJPanel1.add(this.dashdotPenButton);
    this.dashdotdotPenButton.setText("Dash Dot Dot Pen");
    this.dashdotdotPenButton.setOpaque(false);
    this.dashdotdotPenButton.setBounds(new Rectangle(120, 217, 140, 12));
    this.penGroup.add(this.dashdotdotPenButton);
    localJPanel1.add(this.dashdotdotPenButton);
    this.customPenButton.setText("Custom Pen");
    this.customPenButton.setOpaque(false);
    this.customPenButton.setBounds(new Rectangle(120, 234, 120, 12));
    this.penGroup.add(this.customPenButton);
    localJPanel1.add(this.customPenButton);
    this.noPenButton.setText("No Pen");
    this.noPenButton.setOpaque(false);
    this.noPenButton.setBounds(new Rectangle(120, 132, 120, 12));
    this.penGroup.add(this.noPenButton);
    localJPanel1.add(this.noPenButton);
    JPanel localJPanel2 = new JPanel(null);
    localJPanel2.setBackground(new Color(224, 224, 224));
    localJPanel2.setBounds(new Rectangle(113, 96, 151, 156));
    localJPanel1.add(localJPanel2);
    JLabel localJLabel6 = new JLabel("Pen Properties");
    localJLabel6.setFont(new Font("Dialog", 2, 12));
    localJLabel6.setBounds(new Rectangle(27, 1, 95, 23));
    localJPanel2.add(localJLabel6);
    JLabel localJLabel7 = new JLabel("Pen Width");
    localJLabel7.setBounds(new Rectangle(25, 217, 67, 27));
    this.tPenWidth.setText("1");
    this.tPenWidth.setBounds(new Rectangle(28, 240, 48, 24));
    JPanel localJPanel3 = new JPanel(new FlowLayout(1, 5, 5));
    localJPanel1.add(localJPanel3);
    localJPanel1.add(localJLabel7, null);
    localJPanel1.add(this.tPenWidth, null);
    localJPanel3.setBackground(new Color(224, 224, 224));
    localJPanel3.setBounds(new Rectangle(276, 96, 132, 156));
    JLabel localJLabel8 = new JLabel();
    if (this.myObject.getClass().getName().equals("grafchart.sfc.GCStroke")) {
      localJLabel8.setText("Arrow Properties");
    } else {
      localJLabel8.setText("Brush Properties");
    }
    localJLabel8.setFont(new Font("Dialog", 2, 12));
    localJPanel3.add(localJLabel8);
    if ((this.myObject instanceof JGo3DRect))
    {
      this.raised.setText("Raised");
      localJPanel1.add(this.raised);
      this.raised.setBounds(new Rectangle(24, 192, 84, 24));
    }
  }
  
  void UpdateDialog()
  {
    Rectangle localRectangle = this.myObject.getBoundingRect();
    this.xField.setText(String.valueOf(localRectangle.x));
    this.yField.setText(String.valueOf(localRectangle.y));
    this.heightField.setText(String.valueOf(localRectangle.height));
    this.widthField.setText(String.valueOf(localRectangle.width));
    this.visibleBox.setSelected(this.myObject.isVisible());
    this.selectableBox.setSelected(this.myObject.isSelectable());
    this.resizableBox.setSelected(this.myObject.isResizable());
    this.draggableBox.setSelected(this.myObject.isDraggable());
    this.noPenButton.setSelected(this.myObject.getPen().getStyle() == 0);
    this.solidPenButton.setSelected(this.myObject.getPen().getStyle() == 65535);
    this.dashedPenButton.setSelected(this.myObject.getPen().getStyle() == 1);
    this.dottedPenButton.setSelected(this.myObject.getPen().getStyle() == 2);
    this.dashdotPenButton.setSelected(this.myObject.getPen().getStyle() == 3);
    this.dashdotdotPenButton.setSelected(this.myObject.getPen().getStyle() == 4);
    this.customPenButton.setSelected(this.myObject.getPen().getStyle() == 65534);
    this.tPenWidth.setText(String.valueOf(this.myObject.getPen().getWidth()));
    this.myPenColor = this.myObject.getPen().getColor();
    JGoBrush localJGoBrush = this.myObject.getBrush();
    if (localJGoBrush.getStyle() == 65535)
    {
      this.solidBrushBox.setSelected(true);
      this.myBrushColor = this.myObject.getBrush().getColor();
      if (this.myBrushColor == null) {
        this.myBrushColor = Color.black;
      }
    }
    else
    {
      this.solidBrushBox.setSelected(false);
      this.myBrushColor = Color.black;
    }
    if ((this.myObject instanceof JGo3DRect)) {
      this.raised.setSelected(((JGo3DRect)this.myObject).getState() == 0);
    }
    if (this.myObject.getClass().getName().equals("grafchart.sfc.GCStroke"))
    {
      JGoStroke localJGoStroke = (JGoStroke)this.myObject;
      this.noArrowButton.setSelected((!localJGoStroke.hasArrowAtStart()) && (!localJGoStroke.hasArrowAtEnd()));
      this.fromArrowButton.setSelected((localJGoStroke.hasArrowAtStart()) && (!localJGoStroke.hasArrowAtEnd()));
      this.toArrowButton.setSelected((!localJGoStroke.hasArrowAtStart()) && (localJGoStroke.hasArrowAtEnd()));
      this.bothArrowButton.setSelected((localJGoStroke.hasArrowAtStart()) && (localJGoStroke.hasArrowAtEnd()));
    }
    if ((this.myObject instanceof JGoStroke)) {
      this.isCubic.setSelected(((JGoStroke)this.myObject).isCubic());
    }
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    int i = parseInt(this.xField);
    int j = parseInt(this.widthField);
    int k = parseInt(this.yField);
    int m = parseInt(this.heightField);
    int n = parseInt(this.tPenWidth);
    this.myObject.setBoundingRect(new Rectangle(i, k, j, m));
    this.myObject.setVisible(this.visibleBox.isSelected());
    this.myObject.setSelectable(this.selectableBox.isSelected());
    this.myObject.setResizable(this.resizableBox.isSelected());
    this.myObject.setDraggable(this.draggableBox.isSelected());
    int i1;
    if (this.solidPenButton.isSelected()) {
      i1 = 65535;
    } else if (this.dashedPenButton.isSelected()) {
      i1 = 1;
    } else if (this.dottedPenButton.isSelected()) {
      i1 = 2;
    } else if (this.dashdotPenButton.isSelected()) {
      i1 = 3;
    } else if (this.dashdotdotPenButton.isSelected()) {
      i1 = 4;
    } else if (this.customPenButton.isSelected()) {
      i1 = 65534;
    } else {
      i1 = 0;
    }
    Color localColor = this.myPenColor;
    this.myObject.setPen(JGoPen.make(i1, n, localColor));
    if (this.solidBrushBox.isSelected()) {
      this.myObject.setBrush(JGoBrush.make(65535, this.myBrushColor));
    } else {
      this.myObject.setBrush(JGoBrush.Null);
    }
    if (this.myObject.getClass().getName().equals("grafchart.sfc.GCStroke"))
    {
      JGoStroke localJGoStroke = (JGoStroke)this.myObject;
      if (this.noArrowButton.isSelected()) {
        localJGoStroke.setArrowHeads(false, false);
      } else if (this.toArrowButton.isSelected()) {
        localJGoStroke.setArrowHeads(false, true);
      } else if (this.fromArrowButton.isSelected()) {
        localJGoStroke.setArrowHeads(true, false);
      } else {
        localJGoStroke.setArrowHeads(true, true);
      }
      localJGoStroke.setBrush(JGoBrush.make(65535, this.myPenColor));
    }
    if ((this.myObject instanceof JGo3DRect)) {
      if (this.raised.isSelected()) {
        ((JGo3DRect)this.myObject).setState(0);
      } else {
        ((JGo3DRect)this.myObject).setState(1);
      }
    }
    if ((this.myObject instanceof JGoStroke)) {
      ((JGoStroke)this.myObject).setCubic(this.isCubic.isSelected());
    }
  }
  
  private void onPenColor()
  {
    Color localColor = JColorChooser.showDialog(this, "Pen Color", this.myPenColor);
    if (localColor != null) {
      this.myPenColor = localColor;
    }
  }
  
  private void onBrushColor()
  {
    Color localColor = JColorChooser.showDialog(this, "Brush Color", this.myBrushColor);
    if (localColor != null) {
      this.myBrushColor = localColor;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/DrawablePropsDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */