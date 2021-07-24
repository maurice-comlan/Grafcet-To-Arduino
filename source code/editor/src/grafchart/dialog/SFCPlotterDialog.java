package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.SFCPlotter;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SFCPlotterDialog
  extends MyJDialog
{
  private MyJTextField tChannel1 = new MyJTextField();
  private MyJTextField tChannel2 = new MyJTextField();
  private MyJTextField fieldXRangeSeconds = new MyJTextField();
  private MyJTextField tXTicks = new MyJTextField();
  private MyJTextField tXGrid = new MyJTextField();
  private MyJTextField tYmin = new MyJTextField();
  private MyJTextField tYmax = new MyJTextField();
  private MyJTextField tYTicks = new MyJTextField();
  private MyJTextField tYGrid = new MyJTextField();
  private MyJTextField tUpdateFreq = new MyJTextField();
  private MyJTextField tLegend = new MyJTextField();
  private Color color1;
  private Color color2;
  private GCDocument myObject;
  private SFCPlotter s;
  
  public SFCPlotterDialog(Frame paramFrame, GCDocument paramGCDocument, SFCPlotter paramSFCPlotter, GCView paramGCView)
  {
    super(paramFrame, "Plotter Channels");
    this.myObject = paramGCDocument;
    this.s = paramSFCPlotter;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(460, 360));
    localJPanel.setPreferredSize(new Dimension(460, 360));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = new MyJButton("Color 1...", 49);
    localMyJButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        SFCPlotterDialog.this.onColorChannel1();
      }
    });
    MyJButton localMyJButton2 = new MyJButton("Color 2...", 50);
    localMyJButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        SFCPlotterDialog.this.onColorChannel2();
      }
    });
    MyJButton localMyJButton3 = createOKButton();
    localMyJButton3.setBounds(new Rectangle(100, 325, 79, 22));
    localJPanel.add(localMyJButton3);
    MyJButton localMyJButton4 = createCancelButton();
    localMyJButton4.setBounds(new Rectangle(316, 325, 79, 22));
    localJPanel.add(localMyJButton4);
    MyJButton localMyJButton5 = createApplyButton();
    localMyJButton5.setBounds(new Rectangle(208, 325, 79, 22));
    localJPanel.add(localMyJButton5);
    JLabel localJLabel1 = new JLabel("Channel 1");
    localJLabel1.setHorizontalAlignment(2);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    localJPanel.add(localJLabel1);
    this.tChannel1.setBounds(new Rectangle(50, 40, 290, 24));
    this.tChannel1.setEnabled(this.myObject.isModifiable());
    this.tChannel1.setFontMonospaced();
    localJPanel.add(this.tChannel1);
    localMyJButton1.setBounds(new Rectangle(350, 40, 90, 24));
    localJPanel.add(localMyJButton1);
    JLabel localJLabel2 = new JLabel("Channel 2");
    localJLabel2.setHorizontalAlignment(2);
    localJLabel2.setBounds(new Rectangle(50, 70, 220, 24));
    localJPanel.add(localJLabel2);
    this.tChannel2.setBounds(new Rectangle(50, 100, 290, 24));
    this.tChannel2.setEnabled(true);
    this.tChannel2.setFontMonospaced();
    localJPanel.add(this.tChannel2);
    localMyJButton2.setBounds(new Rectangle(350, 100, 90, 24));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel3 = new JLabel("X Range (s)");
    localJLabel3.setHorizontalAlignment(2);
    localJLabel3.setBounds(new Rectangle(50, 130, 80, 24));
    localJPanel.add(localJLabel3);
    this.fieldXRangeSeconds.setBounds(new Rectangle(50, 160, 80, 24));
    this.fieldXRangeSeconds.setEnabled(true);
    localJPanel.add(this.fieldXRangeSeconds);
    JLabel localJLabel4 = new JLabel("X # Ticks");
    localJLabel4.setHorizontalAlignment(2);
    localJLabel4.setBounds(new Rectangle(140, 130, 80, 24));
    localJPanel.add(localJLabel4);
    this.tXTicks.setBounds(new Rectangle(140, 160, 70, 24));
    this.tXTicks.setEnabled(true);
    localJPanel.add(this.tXTicks);
    JLabel localJLabel5 = new JLabel("X # Grid");
    localJLabel5.setHorizontalAlignment(2);
    localJLabel5.setBounds(new Rectangle(220, 130, 80, 24));
    localJPanel.add(localJLabel5);
    this.tXGrid.setBounds(new Rectangle(220, 160, 70, 24));
    this.tXGrid.setEnabled(true);
    localJPanel.add(this.tXGrid);
    JLabel localJLabel6 = new JLabel("Y min");
    localJLabel6.setHorizontalAlignment(2);
    localJLabel6.setBounds(new Rectangle(50, 190, 80, 24));
    localJPanel.add(localJLabel6);
    this.tYmin.setBounds(new Rectangle(50, 220, 80, 24));
    this.tYmin.setEnabled(true);
    localJPanel.add(this.tYmin);
    JLabel localJLabel7 = new JLabel("Y max");
    localJLabel7.setHorizontalAlignment(2);
    localJLabel7.setBounds(new Rectangle(140, 190, 80, 24));
    localJPanel.add(localJLabel7);
    this.tYmax.setBounds(new Rectangle(140, 220, 80, 24));
    this.tYmax.setEnabled(true);
    localJPanel.add(this.tYmax);
    JLabel localJLabel8 = new JLabel("Y # Ticks");
    localJLabel8.setHorizontalAlignment(2);
    localJLabel8.setBounds(new Rectangle(230, 190, 80, 24));
    localJPanel.add(localJLabel8);
    this.tYTicks.setBounds(new Rectangle(230, 220, 70, 24));
    this.tYTicks.setEnabled(true);
    localJPanel.add(this.tYTicks);
    JLabel localJLabel9 = new JLabel("Y # Grid");
    localJLabel9.setHorizontalAlignment(2);
    localJLabel9.setBounds(new Rectangle(320, 190, 80, 24));
    localJPanel.add(localJLabel9);
    this.tYGrid.setBounds(new Rectangle(320, 220, 70, 24));
    this.tYGrid.setEnabled(true);
    localJPanel.add(this.tYGrid);
    JLabel localJLabel10 = new JLabel("Update Freq");
    localJLabel10.setHorizontalAlignment(2);
    localJLabel10.setBounds(new Rectangle(50, 250, 80, 24));
    localJPanel.add(localJLabel10);
    this.tUpdateFreq.setBounds(new Rectangle(50, 280, 70, 24));
    this.tUpdateFreq.setEnabled(true);
    localJPanel.add(this.tUpdateFreq);
    JLabel localJLabel11 = new JLabel("Legend");
    localJLabel11.setHorizontalAlignment(2);
    localJLabel11.setBounds(new Rectangle(140, 250, 80, 24));
    localJPanel.add(localJLabel11);
    this.tLegend.setBounds(new Rectangle(140, 280, 240, 24));
    this.tLegend.setEnabled(true);
    localJPanel.add(this.tLegend);
  }
  
  void updateDialog()
  {
    this.tChannel1.setText(this.s.chan1String);
    this.tChannel2.setText(this.s.chan2String);
    this.fieldXRangeSeconds.setText("" + this.s.getXRangeSeconds());
    this.fieldXRangeSeconds.setFont(new Font("Dialog", 1, 14));
    this.tXTicks.setText("" + this.s.getXTicks());
    this.tXTicks.setFont(new Font("Dialog", 1, 14));
    this.tXGrid.setText("" + this.s.getXGrid());
    this.tXGrid.setFont(new Font("Dialog", 1, 14));
    this.tYTicks.setText("" + this.s.getYTicks());
    this.tYTicks.setFont(new Font("Dialog", 1, 14));
    this.tYGrid.setText("" + this.s.getYGrid());
    this.tYGrid.setFont(new Font("Dialog", 1, 14));
    this.tYmin.setText("" + this.s.getYMin());
    this.tYmin.setFont(new Font("Dialog", 1, 14));
    this.tYmax.setText("" + this.s.getYMax());
    this.tYmax.setFont(new Font("Dialog", 1, 14));
    this.tUpdateFreq.setText("" + this.s.getUpdateFreq());
    this.tUpdateFreq.setFont(new Font("Dialog", 1, 14));
    this.tLegend.setText(this.s.getLegend());
    this.tLegend.setFont(new Font("Dialog", 1, 14));
    this.color1 = this.s.getColor1();
    this.color2 = this.s.getColor2();
  }
  
  @Override
  protected void saveData()
    throws IllegalValueException
  {
    double d1 = parseDouble(this.fieldXRangeSeconds);
    int i = parseInt(this.tXTicks);
    int j = parseInt(this.tXGrid);
    double d2 = parseDouble(this.tYmin);
    double d3 = parseDouble(this.tYmax);
    int k = parseInt(this.tYTicks);
    int m = parseInt(this.tYGrid);
    int n = parseInt(this.tUpdateFreq);
    this.s.chan1String = this.tChannel1.getText();
    this.s.chan2String = this.tChannel2.getText();
    this.s.setXRangeSeconds(d1);
    this.s.setXTicks(i);
    this.s.setXGrid(j);
    this.s.setYMin(d2);
    this.s.setYMax(d3);
    this.s.setYTicks(k);
    this.s.setYGrid(m);
    this.s.setUpdateFreq(n);
    this.s.setLegend(this.tLegend.getText());
    this.s.setColor1(this.color1);
    this.s.setColor2(this.color2);
    this.s.updatePlotter();
  }
  
  void onColorChannel1()
  {
    Color localColor = JColorChooser.showDialog(this, "Color 1", this.color1);
    if (localColor != null) {
      this.color1 = localColor;
    }
  }
  
  void onColorChannel2()
  {
    Color localColor = JColorChooser.showDialog(this, "Color 2", this.color2);
    if (localColor != null) {
      this.color2 = localColor;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/SFCPlotterDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */