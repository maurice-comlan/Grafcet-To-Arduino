package grafchart.sfc;

import java.awt.Container;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 * Definie une action dans l'application.
 * @author dimon
 */
public abstract class AppAction
  extends AbstractAction
{
  protected Container myApp;
  private static Vector<AppAction> myAllActions = new Vector();
  
  public Editor getApp()
  {
    return (Editor)this.myApp;
  }
  
  public GCView getView()
  {
    return getApp().getCurrentView();
  }
  
  /**
   * Instanitie une nouvelle action avec le nom name.
   * @param name nom de l'action
   * @param parent le Container contenant l'action, c'est l'instance de Editor en cours
   */
  public AppAction(String name, Container parent)
  {
    super(name);
    init(parent);
  }
  
  /**
   * Instanitie une nouvelle action avec le nom name et une icone.
   * @param name nom de l'action
   * @param parent le Container contenant l'action, c'est l'instance de Editor en cours
   * @param icon le fichier icone
   */
  public AppAction(String name, Icon icon, Container parent)
  {
    super(name, icon);
    init(parent);
  }
  
  private final void init(Container paramContainer)
  {
    this.myApp = paramContainer;
    myAllActions.add(this);
  }
  
  @Override
  public String toString()
  {
    return (String)getValue("Name");
  }
  
  /**
   * Definie si l'action peut etre exécuter, cela permet de definir setEnabled
   * @return true si oui et false sinon
   */
  public boolean canAct()
  {
    return getView() != null;
  }
  
  /**
   * Actualise l'état (Enabled ou non) de l'action en fonction de canAct().
   */
  public void updateEnabled()
  {
    setEnabled(canAct());
  }
  
  public void free()
  {
    myAllActions.removeElement(this);
    this.myApp = null;
  }
  
  /**
   * Actualise l'état (Enabled ou non) de toutes les actions.
   */
  public static void updateAllActions()
  {
    for (int i = 0; i < myAllActions.size(); i++)
    {
      AppAction localAppAction = (AppAction)myAllActions.elementAt(i);
      localAppAction.updateEnabled();
    }
  }
  
  /**
   * La liste des actions enrégistrées
   * @return un Vector representant la liste
   */
  public static Vector allActions()
  {
    return myAllActions;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/AppAction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */