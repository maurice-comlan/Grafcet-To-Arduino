package grafchart.graphics;

import grafchart.sfc.Utils;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class MyJTextField
  extends JTextField
{
  private boolean isFocusedByMouse = false;
  private UndoManager undoManager = new UndoManager();
  private boolean registerUndo = true;
  
  public MyJTextField()
  {
    addFocusListener(new FocusListener()
    {
      public void focusGained(FocusEvent paramAnonymousFocusEvent)
      {
        if (!MyJTextField.this.isFocusedByMouse) {
          MyJTextField.this.selectAll();
        }
      }
      
      public void focusLost(FocusEvent paramAnonymousFocusEvent)
      {
        MyJTextField.this.select(0, 0);
      }
    });
    addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent paramAnonymousMouseEvent)
      {
        MyJTextField.this.isFocusedByMouse = true;
      }
      
      public void mouseReleased(MouseEvent paramAnonymousMouseEvent)
      {
        MyJTextField.this.isFocusedByMouse = false;
      }
    });
    getDocument().addUndoableEditListener(new UndoableEditListener()
    {
      public void undoableEditHappened(UndoableEditEvent paramAnonymousUndoableEditEvent)
      {
        if (MyJTextField.this.registerUndo) {
          MyJTextField.this.undoManager.addEdit(paramAnonymousUndoableEditEvent.getEdit());
        }
      }
    });
    getInputMap().put(KeyStroke.getKeyStroke(90, 2), "Undo");
    getActionMap().put("Undo", new AbstractAction()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        if (MyJTextField.this.undoManager.canUndo()) {
          try
          {
            MyJTextField.this.undoManager.undo();
          }
          catch (CannotUndoException localCannotUndoException)
          {
            Utils.writeException(localCannotUndoException);
          }
        }
      }
    });
    getInputMap().put(KeyStroke.getKeyStroke(90, 3), "Redo");
    getInputMap().put(KeyStroke.getKeyStroke(89, 2), "Redo");
    getActionMap().put("Redo", new AbstractAction()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        if (MyJTextField.this.undoManager.canRedo()) {
          try
          {
            MyJTextField.this.undoManager.redo();
          }
          catch (CannotRedoException localCannotRedoException)
          {
            Utils.writeException(localCannotRedoException);
          }
        }
      }
    });
  }
  
  public void setFontMonospaced()
  {
    setFont(new Font("Monospaced", 1, 12));
  }
  
  public void setText(String paramString)
  {
    this.registerUndo = false;
    super.setText(paramString);
    this.registerUndo = true;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJTextField.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */