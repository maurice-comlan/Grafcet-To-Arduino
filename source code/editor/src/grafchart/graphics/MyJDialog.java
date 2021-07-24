package grafchart.graphics;

import grafchart.sfc.Editor;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.Utils;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * Classe pour la création de dialog permettant de réaliser la majorité des 
 * configurations nécissitant une fenètre de config
 * @author dimon
 */
public class MyJDialog
  extends JDialog
{
  private boolean fComponentsAdjusted = false;
  
  /**
   * Creates a dialog with the specified title.
   * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale.
   * @param title the String to display in the dialog's title bar
   */
  public MyJDialog(String title)
  {
    this(null, title);
  }
  
  /**
   * Creates a dialog with the specified title and the specified owner Dialog.
   * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale.
   * @param owner Dialog from which the dialog is displayed or null if this dialog has no owner
   * @param title the String to display in the dialog's title bar
   */
  public MyJDialog(Frame owner, String title)
  {
    this(owner, title, true);
  }
  
  /**
   * Creates a dialog with the specified title, modality and the specified owner Dialog.
   * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale.
   * @param owner Dialog from which the dialog is displayed or null if this dialog has no owner
   * @param title the String to display in the dialog's title bar
   * @param modal specifies whether dialog blocks user input to other top-level windows when shown. If true, the modality 
   * type property is set to DEFAULT_MODALITY_TYPE, otherwise the dialog is modeless
   */
  public MyJDialog(Frame owner, String title, boolean modal)
  {
    super(owner, title, modal);
    getRootPane().getInputMap(2).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");    
    getRootPane().getActionMap().put("Cancel", new AbstractAction()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        MyJDialog.this.close();
      }
    });
    
    // On ne ferme pas directement le JDialog mais on exécute la méthode close
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(new MyWindowAdapter()
    {
      public void windowClosing(WindowEvent paramAnonymousWindowEvent)
      {
        MyJDialog.this.close();
      }
    });
    setIconImage(Utils.newImageIcon("JGrafchartIcon.png").getImage());
  }
  
  /**
   * Ferme le JDialog
   */
  public void close()
  {
    dispose();
  }
  
  @Override
  /**
    * Makes this Dialog displayable by connecting it to
    * a native screen resource.  Making a dialog displayable will
    * cause any of its children to be made displayable.
    * This method is called internally by the toolkit and should
    * not be called directly by programs.
    */
  public void addNotify()
  {
    Dimension localDimension = getSize();
    super.addNotify();
    if (!this.fComponentsAdjusted)
    {
      Insets localInsets = getInsets();
      setSize(localInsets.left + localInsets.right + localDimension.width, localInsets.top + localInsets.bottom + localDimension.height);
      for (Component localComponent : getComponents())
      {
        Point localPoint = localComponent.getLocation();
        localPoint.translate(localInsets.left, localInsets.top);
        localComponent.setLocation(localPoint);
      }
      this.fComponentsAdjusted = true;
    }
  }
  
  /**
   * Cette méthode doit etre réecrite elle définit les actions a effectuées lorsque 
   * le JDialog est validé
   * @throws IllegalValueException 
   */
  protected void saveData()
    throws IllegalValueException
  {}
  
  /**
   * Callback lié au bouton OK
   */
  protected void onOK()
  {
    try
    {
      saveData();
    }
    catch (IllegalValueException localIllegalValueException)
    {
      return;
    }
    catch (Exception localException)
    {
      Utils.writeException(localException);
      return;
    }
    close();
  }
  
  /**
   * Callback lié au bouton Cancel
   */
  protected void onCancel()
  {
    close();
  }
  
  /**
   * Callback lié au bouton Apply
   */
  protected void onApply()
  {
    try
    {
      saveData();
    }
    catch (IllegalValueException localIllegalValueException) {}catch (Exception localException)
    {
      Utils.writeException(localException);
    }
  }
  
  /**
   * Callback lié au bouton Help
   * @param helpID ID de la section d'aide à ouvrir
   */
  protected void onHelp(String helpID)
  {
    Editor.objectHelp(helpID, true);
  }
  
  /**
   * Crée un bouton Ok préconfiguré avec onOK() comme call back
   * @return  le bouton créé
   */
  protected MyJButton createOKButton()
  {
    MyJButton localMyJButton = new MyJButton("OK");
    localMyJButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        MyJDialog.this.onOK();
      }
    });
    getRootPane().setDefaultButton(localMyJButton);
    return localMyJButton;
  }
  
  /**
   * Créé un bouton cancel toujours avec un callback onCancel
   * @return le bouton créé
   */
  protected MyJButton createCancelButton()
  {
    MyJButton localMyJButton = new MyJButton("Cancel");
    localMyJButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        MyJDialog.this.onCancel();
      }
    });
    return localMyJButton;
  }
  
  /**
   * Créé un bouton apply toujours avec un callback onApply
   * @return le bouton créé
   */
  protected MyJButton createApplyButton()
  {
    MyJButton localMyJButton = new MyJButton("Apply", 65);
    localMyJButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        MyJDialog.this.onApply();
      }
    });
    return localMyJButton;
  }
  
  /**
   * Créé un bouton help avec un callback onHelp
   * @param helpId ID de la section d'aide à ouvrir
   * @return le bouton créé
   */
  protected MyJButton createHelpButton(final String helpId)
  {
    MyJButton localMyJButton = new MyJButton("Help", 72);
    localMyJButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        MyJDialog.this.onHelp(helpId);
      }
    });
    return localMyJButton;
  }
  
  protected int parseInt(JTextField paramJTextField)
    throws IllegalValueException
  {
    return parseInt(paramJTextField, Integer.MIN_VALUE);
  }
  
  protected int parseInt(JTextField paramJTextField, int paramInt)
    throws IllegalValueException
  {
    try
    {
      int i = Integer.parseInt(paramJTextField.getText());
      if (i >= paramInt) {
        return i;
      }
    }
    catch (NumberFormatException localNumberFormatException) {}
    paramJTextField.selectAll();
    paramJTextField.requestFocus();
    throw new IllegalValueException();
  }
  
  protected double parseDouble(JTextField paramJTextField)
    throws IllegalValueException
  {
    try
    {
      return Double.parseDouble(paramJTextField.getText());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      paramJTextField.selectAll();
      paramJTextField.requestFocus();
      throw new IllegalValueException();
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */