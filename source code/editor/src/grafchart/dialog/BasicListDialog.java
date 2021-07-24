package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.BasicList;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BasicListDialog
  extends MyJDialog
{
  private MyJTextField tInitialValues = new MyJTextField();
  private JList list;
  private JScrollPane areaScrollPane;
  private BasicList gp;
  
  public BasicListDialog(Frame paramFrame, BasicList paramBasicList, GCView paramGCView)
  {
    super(paramFrame, "List Contents");
    this.gp = paramBasicList;
    init();
    pack();
    setLocationRelativeTo(paramGCView);
    updateDialog();
    this.tInitialValues.requestFocus();
  }
  
  private final void init()
  {
    JPanel localJPanel = new JPanel(null);
    localJPanel.setMinimumSize(new Dimension(494, 370));
    localJPanel.setPreferredSize(new Dimension(494, 370));
    getContentPane().add(localJPanel);
    MyJButton localMyJButton1 = createOKButton();
    localMyJButton1.setBounds(new Rectangle(150, 330, 79, 22));
    localJPanel.add(localMyJButton1);
    MyJButton localMyJButton2 = createCancelButton();
    localMyJButton2.setBounds(new Rectangle(240, 330, 79, 22));
    localJPanel.add(localMyJButton2);
    JLabel localJLabel1 = new JLabel("List Contents");
    localJLabel1.setHorizontalAlignment(2);
    localJPanel.add(localJLabel1);
    this.list = new JList(getFormattedList(this.gp.getValues()).toArray());
    this.areaScrollPane = new JScrollPane(this.list);
    localJLabel1.setBounds(new Rectangle(50, 10, 148, 24));
    this.areaScrollPane.setVerticalScrollBarPolicy(22);
    localJPanel.add(this.areaScrollPane);
    this.areaScrollPane.setBounds(new Rectangle(50, 40, 400, 200));
    JLabel localJLabel2 = new JLabel("Initial Values");
    localJLabel2.setHorizontalAlignment(2);
    localJPanel.add(localJLabel2);
    localJLabel2.setBounds(new Rectangle(50, 260, 148, 24));
    localJPanel.add(this.tInitialValues);
    this.tInitialValues.setBounds(new Rectangle(50, 290, 400, 24));
  }
  
  void updateDialog()
  {
    this.tInitialValues.setText(this.gp.getInitialValues());
  }
  
  public List getFormattedList(List paramList)
  {
    ArrayList localArrayList = new ArrayList();
    synchronized (paramList)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Object localObject1 = localIterator.next();
        if ((localObject1 instanceof Integer)) {
          localArrayList.add("" + ((Integer)localObject1).intValue());
        }
        if ((localObject1 instanceof Double)) {
          localArrayList.add("" + ((Double)localObject1).doubleValue());
        }
        if ((localObject1 instanceof Boolean)) {
          localArrayList.add(((Boolean)localObject1).booleanValue() ? "1" : "0");
        }
        if ((localObject1 instanceof String)) {
          localArrayList.add("\"" + (String)localObject1 + "\"");
        }
      }
    }
    return localArrayList;
  }
  
  protected void saveData()
    throws IllegalValueException
  {
    this.gp.setInitialValues(this.tInitialValues.getText());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/BasicListDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */