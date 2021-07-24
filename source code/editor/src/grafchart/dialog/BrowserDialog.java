package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.Browser;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrowserDialog
  extends MyJDialog
{
  private Color myTextColor;
  private Color myFillColor;
  private MyJTextField tFontSize = new MyJTextField();
  private JComboBox faceBox = new JComboBox();
  private String selectedFace = "";
  private JCheckBox boldBox = new JCheckBox();
  private Browser s;
  
  public BrowserDialog(Frame paramFrame, Browser paramBrowser, GCView paramGCView)
  {
    super(paramFrame, "Browser");
    this.s = paramBrowser;
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
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(40, 170, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(150, 170, 79, 22));
    localJPanel.add(localMyJButton2);
    MyJButton localMyJButton3 = new MyJButton("Text Color...", 84);
    localMyJButton3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        BrowserDialog.this.onTextColor();
      }
    });
    MyJButton localMyJButton4 = new MyJButton("Fill Color...", 76);
    localMyJButton4.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        BrowserDialog.this.onFillColor();
      }
    });
    JLabel localJLabel1 = new JLabel("Font Size");
    localJLabel1.setHorizontalAlignment(4);
    localJLabel1.setBounds(new Rectangle(12, 20, 64, 24));
    localJPanel.add(localJLabel1);
    this.tFontSize.setBounds(new Rectangle(84, 20, 36, 24));
    localJPanel.add(this.tFontSize);
    localMyJButton3.setBounds(new Rectangle(12, 50, 117, 24));
    localJPanel.add(localMyJButton3);
    localMyJButton4.setBounds(new Rectangle(135, 50, 117, 24));
    localJPanel.add(localMyJButton4);
    JLabel localJLabel2 = new JLabel("Font Face");
    localJLabel2.setHorizontalAlignment(4);
    localJLabel2.setBounds(new Rectangle(12, 80, 65, 24));
    localJPanel.add(localJLabel2);
    this.faceBox.setBounds(new Rectangle(12, 108, 324, 24));
    localJPanel.add(this.faceBox);
    this.faceBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JComboBox localJComboBox = (JComboBox)paramAnonymousActionEvent.getSource();
        BrowserDialog.this.selectedFace = ((String)localJComboBox.getSelectedItem());
      }
    });
    this.boldBox.setText("Bold");
    this.boldBox.setBounds(new Rectangle(12, 140, 84, 24));
    localJPanel.add(this.boldBox);
  }
  
  void updateDialog()
  {
    this.myTextColor = this.s.getTextColor();
    this.myFillColor = this.s.getFillColor();
    this.boldBox.setSelected(this.s.isBold());
    this.tFontSize.setText(String.valueOf(this.s.getFontSize()));
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
      if (str.equals(this.s.getFaceName()))
      {
        this.faceBox.setSelectedIndex(i);
        j = 1;
        this.selectedFace = this.s.getFaceName();
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
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.s.setFontSize(parseInt(this.tFontSize));
    this.s.setTextColor(this.myTextColor);
    this.s.setFillColor(this.myFillColor);
    this.s.setFaceName(this.selectedFace);
    this.s.setBold(this.boldBox.isSelected());
  }
  
  void onTextColor()
  {
    Color localColor = JColorChooser.showDialog(this, "Text Color", this.myTextColor);
    if (localColor != null) {
      this.myTextColor = localColor;
    }
  }
  
  void onFillColor()
  {
    Color localColor = JColorChooser.showDialog(this, "Fill Color", this.myFillColor);
    if (localColor != null) {
      this.myFillColor = localColor;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/BrowserDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */