package grafchart.graphics;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MyJMenu
  extends JMenu
{
  /**
   * Crée et ajoute, complètement en bas, un JMeneItem au menu courant, sous forme de
   * d'action. mnmonic correspond au code d'une touche qui exécute l'action lorsque la combinaison
   * alt+ cette touche est effectuée et que l'élément est dans le focus.
   * @param action action à attaché au JMenuItem
   * @param mnemonic code correspondant à une touche du clavier, comme 
   * défint dans java.awt.envent.KeyEvent.
   * @return le JMenuItem crée.
   */
  public JMenuItem add(Action action, int mnemonic)
  {
    JMenuItem localJMenuItem = add(action);
    localJMenuItem.setMnemonic(mnemonic);
    return localJMenuItem;
  }
  
  /**
   * Crée et ajoute, complètement en bas, un JMeneItem au menu courant, sous forme de
   * d'action. Et définit la combinaison de touche que déclenche l'action en question.
   * mnmonic correspond au code d'une touche qui exécute l'action lorsque la combinaison
   * alt+ cette est effectuée et que l'élément est dans le focus.
   * @param action action à attaché au JMenuItem
   * @param keyStroke la combinaison de touche
   * @param mnemonic code correspondant à une touche du clavier, comme 
   * défint dans java.awt.envent.KeyEvent.
   * @return le JMenuItem crée.
   */
  public JMenuItem add(Action action, KeyStroke keyStroke, int mnemonic)
  {
    JMenuItem localJMenuItem = add(action);
    localJMenuItem.setAccelerator(keyStroke);
    localJMenuItem.setMnemonic(mnemonic);
    return localJMenuItem;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */