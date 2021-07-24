package grafchart.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalButtonUI;

public class MyJButton
  extends JButton
{
  public MyJButton()
  {
    this("");
  }
  
  public MyJButton(String name)
  {
    super(name);
    getInputMap().put(KeyStroke.getKeyStroke(10, 0, true), getInputMap().get(KeyStroke.getKeyStroke(32, 0, true)));
    getInputMap().put(KeyStroke.getKeyStroke(10, 0, false), getInputMap().get(KeyStroke.getKeyStroke(32, 0, false)));
    setFont(new Font("Dialog", 0, 12));
    if (UIManager.getLookAndFeel().getID().equals("Metal")) {
      setUI(new MyButtonUI());
    }
  }
  
  /**
   * Crée un nouveau bouton avec le text name et un mnemonique 
   * @param name
   * @param mnemonic 
   */
  public MyJButton(String name, int mnemonic)
  {
    this(name);
    setMnemonic(mnemonic);
  }
  
  private class MyButtonUI
    extends MetalButtonUI
  {
    private MyButtonUI() {}
    
    protected void paintFocus(Graphics paramGraphics, AbstractButton paramAbstractButton, Rectangle paramRectangle1, Rectangle paramRectangle2, Rectangle paramRectangle3)
    {
      paramGraphics.setColor(getFocusColor());
      paramGraphics.drawRect(1, 1, paramAbstractButton.getWidth() - 3, paramAbstractButton.getHeight() - 3);
      paramGraphics.drawRect(2, 2, paramAbstractButton.getWidth() - 5, paramAbstractButton.getHeight() - 5);
      paramGraphics.drawRect(3, 3, paramAbstractButton.getWidth() - 7, paramAbstractButton.getHeight() - 7);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJButton.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */