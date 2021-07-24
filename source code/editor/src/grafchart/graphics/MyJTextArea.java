package grafchart.graphics;

import grafchart.sfc.Utils;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class MyJTextArea
  extends JTextArea
{
  private UndoManager undoManager = new UndoManager();
  private boolean registerUndo = true;
  
  public MyJTextArea()
  {
    getDocument().addUndoableEditListener(new UndoableEditListener()
    {
      public void undoableEditHappened(UndoableEditEvent paramAnonymousUndoableEditEvent)
      {
        if (MyJTextArea.this.registerUndo) {
          MyJTextArea.this.undoManager.addEdit(paramAnonymousUndoableEditEvent.getEdit());
        }
      }
    });
    getInputMap().put(KeyStroke.getKeyStroke(9, 0), "FocusForward");
    getActionMap().put("FocusForward", new AbstractAction()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ((MyJTextArea)paramAnonymousActionEvent.getSource()).transferFocus();
      }
    });
    getInputMap().put(KeyStroke.getKeyStroke(9, 1), "FocusBackward");
    getActionMap().put("FocusBackward", new AbstractAction()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        ((MyJTextArea)paramAnonymousActionEvent.getSource()).transferFocusBackward();
      }
    });
    getInputMap().put(KeyStroke.getKeyStroke(90, 2), "Undo");
    getActionMap().put("Undo", new AbstractAction()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        if (MyJTextArea.this.undoManager.canUndo()) {
          try
          {
            MyJTextArea.this.undoManager.undo();
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
        if (MyJTextArea.this.undoManager.canRedo()) {
          try
          {
            MyJTextArea.this.undoManager.redo();
          }
          catch (CannotRedoException localCannotRedoException)
          {
            Utils.writeException(localCannotRedoException);
          }
        }
      }
    });
  }
  
  public void setText(String paramString)
  {
    this.registerUndo = false;
    super.setText(paramString);
    this.registerUndo = true;
  }
  
  public void setFontMonospaced()
  {
    setFont(new Font("Monospaced", 1, 14));
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJTextArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */