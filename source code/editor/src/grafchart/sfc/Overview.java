package grafchart.sfc;

import com.nwoods.jgo.JGoDocument;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoSelection;
import com.nwoods.jgo.JGoView;
import com.nwoods.jgo.JGoView.JGoViewCanvas;
import com.nwoods.jgo.JGoViewEvent;
import com.nwoods.jgo.JGoViewListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

public class Overview
  extends JGoView
{
  private JGoView myObserved = null;
  private OverviewRectangle myOverviewRect = null;
  
  public Overview()
  {
    setHidingDisabledScrollbars(true);
    setInternalMouseActions(2);
    setScale(0.125D);
  }
  
  public void removeNotify()
  {
    removeListeners();
    this.myObserved = null;
    super.removeNotify();
  }
  
  private void removeListeners()
  {
    if ((this.myObserved != null) && (this.myOverviewRect != null))
    {
      this.myObserved.getDocument().removeDocumentListener(this);
      this.myObserved.removeViewListener(this.myOverviewRect);
      this.myObserved.getCanvas().removeComponentListener(this.myOverviewRect);
    }
  }
  
  public void setObserved(JGoView paramJGoView)
  {
    if ((paramJGoView instanceof Overview)) {
      return;
    }
    JGoView localJGoView = this.myObserved;
    if (localJGoView != paramJGoView)
    {
      removeListeners();
      this.myObserved = paramJGoView;
      if (this.myObserved != null)
      {
        if (this.myOverviewRect == null)
        {
          this.myOverviewRect = new OverviewRectangle(this.myObserved.getViewPosition(), this.myObserved.getExtentSize());
          addObjectAtTail(this.myOverviewRect);
        }
        else
        {
          this.myOverviewRect.setBoundingRect(this.myObserved.getViewRect());
        }
        this.myObserved.getDocument().addDocumentListener(this);
        this.myObserved.addViewListener(this.myOverviewRect);
        this.myObserved.getCanvas().addComponentListener(this.myOverviewRect);
        firePropertyChange("observed", localJGoView, paramJGoView);
        updateView();
      }
    }
  }
  
  public JGoView getObserved()
  {
    return this.myObserved;
  }
  
  public boolean isDropFlavorAcceptable(DropTargetDragEvent paramDropTargetDragEvent)
  {
    return false;
  }
  
  public int computeAcceptableDrop(DropTargetDragEvent paramDropTargetDragEvent)
  {
    return 0;
  }
  
  public OverviewRectangle getOverviewRect()
  {
    return this.myOverviewRect;
  }
  
  public JGoObject pickDocObject(Point paramPoint, boolean paramBoolean)
  {
    if ((getOverviewRect() != null) && (getOverviewRect().isPointInObj(paramPoint))) {
      return getOverviewRect();
    }
    return null;
  }
  
  public void selectInBox(Rectangle paramRectangle) {}
  
  public void doBackgroundClick(int paramInt, Point paramPoint1, Point paramPoint2)
  {
    if (getOverviewRect() != null)
    {
      Rectangle localRectangle = getOverviewRect().getBoundingRect();
      getOverviewRect().setTopLeft(paramPoint1.x - localRectangle.width / 2, paramPoint1.y - localRectangle.height / 2);
    }
  }
  
  public JGoDocument getDocument()
  {
    if (getObserved() != null) {
      return getObserved().getDocument();
    }
    return super.getDocument();
  }
  
  public Dimension getDocumentSize()
  {
    if (getDocument() != null) {
      return getDocument().getDocumentSize();
    }
    return new Dimension();
  }
  
  public boolean isIncludingNegativeCoords()
  {
    if (getObserved() != null) {
      return getObserved().isIncludingNegativeCoords();
    }
    return false;
  }
  
  public String getToolTipText(MouseEvent paramMouseEvent)
  {
    if (getObserved() == null) {
      return null;
    }
    Point localPoint = new Point(paramMouseEvent.getPoint());
    convertViewToDoc(localPoint);
    for (Object localObject = getObserved().pickDocObject(localPoint, false); localObject != null; localObject = ((JGoObject)localObject).getParent())
    {
      String str = ((JGoObject)localObject).getToolTipText();
      if (str != null) {
        return str;
      }
    }
    return null;
  }
  
  public class OverviewRectangle
    extends JGoRectangle
    implements JGoViewListener, ComponentListener
  {
    private boolean myChanging = false;
    
    public OverviewRectangle(Point paramPoint, Dimension paramDimension)
    {
      super(paramPoint, paramDimension);
      setPen(JGoPen.make(65535, 8, new Color(0, 128, 128)));
      setResizable(false);
    }
    
    public void updateRectFromView()
    {
      if (Overview.this.getObserved() == null) {
        return;
      }
      if (this.myChanging) {
        return;
      }
      this.myChanging = true;
      setBoundingRect(Overview.this.getObserved().getViewPosition(), Overview.this.getObserved().getExtentSize());
      Overview.this.scrollRectToVisible(getBoundingRect());
      this.myChanging = false;
    }
    
    public void setBoundingRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (Overview.this.getObserved() != null)
      {
        Point localPoint = Overview.this.getObserved().getDocumentTopLeft();
        Dimension localDimension = Overview.this.getObserved().getDocumentSize();
        if (paramInt1 + paramInt3 > localPoint.x + localDimension.width) {
          paramInt1 = localPoint.x + localDimension.width - paramInt3;
        }
        if (paramInt1 < localPoint.x) {
          paramInt1 = localPoint.x;
        }
        if (paramInt2 + paramInt4 > localPoint.y + localDimension.height) {
          paramInt2 = localPoint.y + localDimension.height - paramInt4;
        }
        if (paramInt2 < localPoint.y) {
          paramInt2 = localPoint.y;
        }
      }
      if (!Overview.this.isIncludingNegativeCoords())
      {
        if (paramInt1 < 0) {
          paramInt1 = 0;
        }
        if (paramInt2 < 0) {
          paramInt2 = 0;
        }
      }
      super.setBoundingRect(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    protected void geometryChange(Rectangle paramRectangle)
    {
      if (Overview.this.getObserved() == null) {
        return;
      }
      if (this.myChanging) {
        return;
      }
      this.myChanging = true;
      Overview.this.getObserved().setViewPosition(getTopLeft());
      this.myChanging = false;
    }
    
    protected void gainedSelection(JGoSelection paramJGoSelection) {}
    
    protected void lostSelection(JGoSelection paramJGoSelection) {}
    
    public void viewChanged(JGoViewEvent paramJGoViewEvent)
    {
      switch (paramJGoViewEvent.getHint())
      {
      case 1: 
        if ((Overview.this.getObserved() != null) && (Overview.this.getObserved().getDocument() != paramJGoViewEvent.getObject()))
        {
          if ((paramJGoViewEvent.getObject() instanceof JGoDocument))
          {
            JGoDocument localJGoDocument = (JGoDocument)paramJGoViewEvent.getObject();
            localJGoDocument.removeDocumentListener((JGoView)paramJGoViewEvent.getSource());
          }
          Overview.this.getObserved().getDocument().addDocumentListener((JGoView)paramJGoViewEvent.getSource());
        }
        updateRectFromView();
        break;
      case 107: 
      case 108: 
        updateRectFromView();
      }
    }
    
    public void componentHidden(ComponentEvent paramComponentEvent) {}
    
    public void componentMoved(ComponentEvent paramComponentEvent) {}
    
    public void componentShown(ComponentEvent paramComponentEvent) {}
    
    public void componentResized(ComponentEvent paramComponentEvent)
    {
      updateRectFromView();
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Overview.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */