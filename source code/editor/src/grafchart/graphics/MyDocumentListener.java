package grafchart.graphics;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class MyDocumentListener
  implements DocumentListener
{
  public void insertUpdate(DocumentEvent paramDocumentEvent)
  {
    update(paramDocumentEvent);
  }
  
  public void removeUpdate(DocumentEvent paramDocumentEvent)
  {
    update(paramDocumentEvent);
  }
  
  public void changedUpdate(DocumentEvent paramDocumentEvent)
  {
    update(paramDocumentEvent);
  }
  
  public abstract void update(DocumentEvent paramDocumentEvent);
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyDocumentListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */