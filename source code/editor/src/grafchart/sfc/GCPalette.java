package grafchart.sfc;

import com.nwoods.jgo.JGoDocument;
import com.nwoods.jgo.JGoView;
import com.nwoods.jgo.JGoViewEvent;
import com.nwoods.jgo.JGoViewListener;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;

public class GCPalette
  extends JGoView
  implements JGoViewListener, MouseWheelListener
{
  private Editor myApp = null;
  
  public GCPalette(Editor paramEditor, String paramString)
  {
    super(new GCDocument());
    this.myApp = paramEditor;
    init(paramString);
  }
  
  private void init(String paramString)
  {
    addViewListener(this);
    setBorder(new TitledBorder(paramString));
    getDocument().setModifiable(false);
    setHorizontalScrollBar(null);
    addMouseWheelListener(this);
    setDefaultSecondarySelectionColor(getDefaultPrimarySelectionColor());
  }
  
  public JGoDocument getDoc()
  {
    return getDocument();
  }
  
  public Dimension getPreferredSize()
  {
    return new Dimension(140, 400);
  }
  
  public Dimension getMinimumSize()
  {
    return new Dimension(140, 400);
  }
  
  public void viewChanged(JGoViewEvent paramJGoViewEvent)
  {
    if (((paramJGoViewEvent.getJGoObject() instanceof ShowWorkspaceButton)) && (!Editor.objectHelpMode)) {
      return;
    }
    this.myApp.processViewChange(paramJGoViewEvent);
  }
  
  public boolean doMouseUp(int paramInt, Point paramPoint1, Point paramPoint2)
  {
    if ((paramInt & 0x4) != 0)
    {
      doCancelMouse();
      return true;
    }
    return super.doMouseUp(paramInt, paramPoint1, paramPoint2);
  }
  
  public boolean doMouseDblClick(int paramInt, Point paramPoint1, Point paramPoint2)
  {
    doCancelMouse();
    return true;
  }
  
  public Cursor getDefaultCursor()
  {
    return this.myApp.getCursor();
  }
  
  public void mouseWheelMoved(MouseWheelEvent paramMouseWheelEvent)
  {
    JScrollBar localJScrollBar = getVerticalScrollBar();
    int i = paramMouseWheelEvent.getWheelRotation();
    int j;
    if (paramMouseWheelEvent.getScrollType() == 1) {
      j = localJScrollBar.getBlockIncrement(i);
    } else {
      j = localJScrollBar.getUnitIncrement(i);
    }
    localJScrollBar.setValue(localJScrollBar.getValue() + i * j);
  }
  
  public void doLayout()
  {
    super.doLayout();
    getVerticalScrollBar().setUnitIncrement(50);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GCPalette.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */