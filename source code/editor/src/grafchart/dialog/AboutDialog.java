package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutDialog
  extends MyJDialog
{
  public AboutDialog(Frame paramFrame)
  {
    super(paramFrame, "About JGrafchart");
    init();
    pack();
    setLocationRelativeTo(paramFrame);
  }
  
  private void init()
  {
    JPanel localJPanel = new JPanel(null);
    JLabel localJLabel1 = new JLabel("JGrafchart 2.6.1");
    localJLabel1.setFont(new Font("SansSerif", 1, 20));
    localJLabel1.setHorizontalAlignment(0);
    localJPanel.add(localJLabel1);
    JLabel localJLabel2 = new JLabel("developed by");
    localJLabel2.setFont(new Font("SansSerif", 1, 16));
    localJLabel2.setHorizontalAlignment(0);
    localJPanel.add(localJLabel2);
    JLabel localJLabel3 = new JLabel("Karl-Erik Årzén");
    localJLabel3.setFont(new Font("SansSerif", 1, 16));
    localJLabel3.setHorizontalAlignment(0);
    localJPanel.add(localJLabel3);
    JLabel localJLabel4 = new JLabel("Department of Automatic Control");
    localJLabel4.setFont(new Font("SansSerif", 1, 16));
    localJLabel4.setHorizontalAlignment(0);
    localJPanel.add(localJLabel4);
    JLabel localJLabel5 = new JLabel("Lund University");
    localJLabel5.setFont(new Font("SansSerif", 1, 16));
    localJLabel5.setHorizontalAlignment(0);
    localJPanel.add(localJLabel5);
    MyJButton localMyJButton = createOKButton();
    localJPanel.add(localMyJButton);
    int i = 380;
    i = Math.max(i, localJLabel1.getPreferredSize().width + 40);
    i = Math.max(i, localJLabel2.getPreferredSize().width + 40);
    i = Math.max(i, localJLabel3.getPreferredSize().width + 40);
    i = Math.max(i, localJLabel4.getPreferredSize().width + 40);
    i = Math.max(i, localJLabel5.getPreferredSize().width + 40);
    localJLabel1.setBounds(new Rectangle(0, 20, i, 24));
    localJLabel2.setBounds(new Rectangle(0, 50, i, 24));
    localJLabel3.setBounds(new Rectangle(0, 80, i, 24));
    localJLabel4.setBounds(new Rectangle(0, 100, i, 24));
    localJLabel5.setBounds(new Rectangle(0, 120, i, 24));
    localMyJButton.setBounds(new Rectangle(i / 2 - 40, 160, 80, 22));
    localJPanel.setMinimumSize(new Dimension(i, 200));
    localJPanel.setPreferredSize(new Dimension(i, 200));
    getContentPane().add(localJPanel);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/AboutDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */