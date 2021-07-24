package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoDrawable;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoScrollBar;
import com.nwoods.jgo.JGoView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

public class ListArea
  extends JGoArea
{
  private static final int myBarSize = 14;
  private static final Insets myInsets = new Insets(1, 4, 1, 18);
  private final JGoPen myLinePen = JGoPen.make(65535, 1, Color.black);
  private JGoScrollBar myScrollBar = null;
  private Vector<JGoObject> myVector = new Vector();
  public JGoRectangle myRect = null;
  private int myFirstVisibleIndex = 0;
  private int myLastVisibleIndex = -1;
  private transient Dimension myMaxItemSize = new Dimension(-1, -1);
  
  public ListArea() {}
  
  public ListArea(Dimension paramDimension)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    this.myRect = new JGoRectangle();
    this.myRect.setSize(paramDimension);
    this.myRect.setBrush(JGoBrush.makeStockBrush(new Color(230, 230, 230)));
    this.myRect.setSelectable(false);
    this.myScrollBar = new JGoScrollBar();
    this.myScrollBar.setVertical(true);
    this.myScrollBar.setSelectable(false);
    addObjectAtHead(this.myRect);
    addObjectAtTail(this.myScrollBar);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    ListArea localListArea = (ListArea)paramJGoArea;
    localListArea.myFirstVisibleIndex = this.myFirstVisibleIndex;
    localListArea.myLastVisibleIndex = this.myLastVisibleIndex;
    localListArea.myRect = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myRect));
    localListArea.myScrollBar = ((JGoScrollBar)paramJGoCopyEnvironment.copy(this.myScrollBar));
    localListArea.addObjectAtHead(localListArea.myRect);
    for (int i = 0; i < this.myVector.size(); i++)
    {
      JGoObject localJGoObject1 = (JGoObject)this.myVector.get(i);
      JGoObject localJGoObject2 = paramJGoCopyEnvironment.copy(localJGoObject1);
      localListArea.myVector.add(localJGoObject2);
      localListArea.addObjectAtTail(localJGoObject2);
    }
    localListArea.addObjectAtTail(localListArea.myScrollBar);
  }
  
  private void setFirstVisibleIndex(int paramInt)
  {
    int i = this.myFirstVisibleIndex;
    if ((paramInt >= 0) && (paramInt <= getNumItems()) && (i != paramInt))
    {
      this.myFirstVisibleIndex = paramInt;
      layoutChildren();
    }
  }
  
  public int getNumItems()
  {
    return this.myVector.size();
  }
  
  public JGoObject getItem(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.myVector.size())) {
      return null;
    }
    return (JGoObject)this.myVector.get(paramInt);
  }
  
  public void setItem(int paramInt, JGoObject paramJGoObject)
  {
    removeItem(paramInt);
    insertItem(paramInt, paramJGoObject);
  }
  
  public int findItem(JGoObject paramJGoObject)
  {
    for (int i = 0; i < getNumItems(); i++)
    {
      JGoObject localJGoObject = getItem(i);
      if (localJGoObject == paramJGoObject) {
        return i;
      }
    }
    return -1;
  }
  
  public void addItem(JGoObject paramJGoObject)
  {
    insertItem(getNumItems(), paramJGoObject);
  }
  
  public void insertItem(int paramInt, JGoObject paramJGoObject)
  {
    if ((paramInt < 0) || (paramInt > getNumItems())) {
      return;
    }
    this.myVector.add(paramInt, paramJGoObject);
    paramJGoObject.setTopLeft(this.myRect.getLeft(), this.myRect.getTop());
    paramJGoObject.setResizable(false);
    adjustMaxItemSize(paramJGoObject, -1, -1);
    addObjectAtTail(paramJGoObject);
    if (paramInt < this.myFirstVisibleIndex) {
      this.myFirstVisibleIndex += 1;
    }
    layoutChildren();
  }
  
  public void removeItem(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getNumItems())) {
      return;
    }
    JGoObject localJGoObject = getItem(paramInt);
    this.myVector.remove(paramInt);
    super.removeObject(localJGoObject);
    adjustMaxItemSize(null, localJGoObject.getWidth(), localJGoObject.getHeight());
    if (paramInt > this.myLastVisibleIndex)
    {
      updateScrollBar();
    }
    else
    {
      if (paramInt < this.myFirstVisibleIndex) {
        this.myFirstVisibleIndex -= 1;
      }
      layoutChildren();
    }
  }
  
  protected void layoutChildren()
  {
    sendObjectToBack(this.myRect);
    this.myLastVisibleIndex = this.myFirstVisibleIndex;
    int i = this.myRect.getLeft() + myInsets.left;
    int j = this.myRect.getTop() + myInsets.top;
    int k = this.myRect.getHeight() - myInsets.top - myInsets.bottom;
    int m = this.myLinePen.getWidth();
    int n = 0;
    for (int i1 = 0; i1 < getNumItems(); i1++)
    {
      JGoObject localJGoObject = getItem(i1);
      if (i1 < this.myFirstVisibleIndex)
      {
        localJGoObject.setVisible(false);
        localJGoObject.setTopLeft(i, j);
      }
      else
      {
        int i2 = getItemHeight(i1);
        if (n + i2 <= k)
        {
          layoutItem(i1, i, j + n, i2);
          n += i2;
          n += m;
          this.myLastVisibleIndex = i1;
        }
        else
        {
          localJGoObject.setVisible(false);
          localJGoObject.setTopLeft(i, j + k - i2);
          n = k + 1;
        }
      }
    }
    this.myScrollBar.setBoundingRect(this.myRect.getLeft() + this.myRect.getWidth() - 14, this.myRect.getTop(), 14, this.myRect.getHeight());
    updateScrollBar();
  }
  
  private int getItemHeight(int paramInt)
  {
    int i = 0;
    JGoObject localJGoObject = getItem(paramInt);
    if (localJGoObject != null) {
      i = localJGoObject.getHeight();
    }
    return i;
  }
  
  protected void layoutItem(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    JGoObject localJGoObject = getItem(paramInt1);
    localJGoObject.setVisible(true);
    localJGoObject.setTopLeft(paramInt2, paramInt3 + paramInt4 / 2 - localJGoObject.getHeight() / 2);
  }
  
  public void paint(Graphics2D paramGraphics2D, JGoView paramJGoView)
  {
    super.paint(paramGraphics2D, paramJGoView);
    int i = this.myLinePen.getWidth();
    if (i == 0) {
      return;
    }
    int j = this.myRect.getLeft();
    int k = j + this.myRect.getWidth();
    int m = this.myRect.getTop() + myInsets.top;
    int n = this.myRect.getHeight() - myInsets.top - myInsets.bottom;
    int i1 = 0;
    for (int i2 = this.myFirstVisibleIndex; i2 < this.myLastVisibleIndex; i2++)
    {
      int i3 = getItemHeight(i2);
      if (i1 + i3 <= n)
      {
        i1 += i3;
        int i4 = i;
        if (i1 + i4 <= n)
        {
          int i5 = m + i1 + i4 / 2;
          JGoDrawable.drawLine(paramGraphics2D, this.myLinePen, j, i5, k, i5);
        }
        i1 += i4;
      }
    }
  }
  
  private void updateScrollBar()
  {
    this.myScrollBar.setValues(this.myFirstVisibleIndex, this.myLastVisibleIndex - this.myFirstVisibleIndex + 1, 0, getNumItems(), 1, Math.max(this.myLastVisibleIndex - this.myFirstVisibleIndex, 1));
  }
  
  public void update(int paramInt1, int paramInt2, Object paramObject)
  {
    if (paramInt1 == 1003) {
      setFirstVisibleIndex(this.myScrollBar.getValue());
    } else {
      super.update(paramInt1, paramInt2, paramObject);
    }
  }
  
  public Dimension getMinimumSize()
  {
    Dimension localDimension = getMaxItemSize();
    return new Dimension(localDimension.width + myInsets.left + myInsets.right, Math.max(localDimension.height, 15) + myInsets.top + myInsets.bottom);
  }
  
  public void setBoundingRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Dimension localDimension = getMinimumSize();
    super.setBoundingRect(paramInt1, paramInt2, Math.max(paramInt3, localDimension.width), Math.max(paramInt4, localDimension.height));
  }
  
  protected void geometryChange(Rectangle paramRectangle)
  {
    if ((paramRectangle.width != getWidth()) || (paramRectangle.height != getHeight()))
    {
      this.myRect.setBoundingRect(getBoundingRect());
      layoutChildren();
    }
    else
    {
      super.geometryChange(paramRectangle);
    }
  }
  
  protected Rectangle handleResize(Graphics2D paramGraphics2D, JGoView paramJGoView, Rectangle paramRectangle, Point paramPoint, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Dimension localDimension = getMinimumSize();
    return super.handleResize(paramGraphics2D, paramJGoView, paramRectangle, paramPoint, paramInt1, paramInt2, localDimension.width, localDimension.height);
  }
  
  public void adjustMaxItemSize(JGoObject paramJGoObject, int paramInt1, int paramInt2)
  {
    int i = -1;
    int j = -1;
    if (paramJGoObject != null)
    {
      i = paramJGoObject.getWidth();
      j = paramJGoObject.getHeight();
    }
    int k;
    if ((this.myMaxItemSize.width < 0) || (this.myMaxItemSize.height < 0))
    {
      getMaxItemSize();
      k = this.myMaxItemSize.width + myInsets.left + myInsets.right;
      int m = this.myMaxItemSize.height + myInsets.top + myInsets.bottom;
      int n = this.myRect.getWidth() < k ? 1 : 0;
      int i1 = this.myRect.getHeight() < m ? 1 : 0;
      if ((n != 0) || (i1 != 0))
      {
        if (n != 0) {
          this.myRect.setWidth(k);
        }
        if (i1 != 0) {
          this.myRect.setHeight(m);
        }
        layoutChildren();
      }
    }
    else
    {
      if ((i > paramInt1) && (i > this.myMaxItemSize.width))
      {
        this.myMaxItemSize.width = i;
        k = i + myInsets.left + myInsets.right;
        if (this.myRect.getWidth() < k)
        {
          this.myRect.setWidth(k);
          layoutChildren();
        }
      }
      else if ((i < paramInt1) && (paramInt1 == this.myMaxItemSize.width))
      {
        this.myMaxItemSize.width = -1;
      }
      if ((j > paramInt2) && (j > this.myMaxItemSize.height))
      {
        this.myMaxItemSize.height = j;
        k = j + myInsets.top + myInsets.bottom;
        if (this.myRect.getHeight() < k)
        {
          this.myRect.setHeight(k);
          layoutChildren();
        }
      }
      else if ((j < paramInt2) && (paramInt2 == this.myMaxItemSize.height))
      {
        this.myMaxItemSize.height = -1;
      }
    }
  }
  
  public Dimension getMaxItemSize()
  {
    if ((this.myMaxItemSize.width < 0) || (this.myMaxItemSize.height < 0))
    {
      this.myMaxItemSize.width = -1;
      this.myMaxItemSize.height = -1;
      for (int i = 0; i < this.myVector.size(); i++)
      {
        JGoObject localJGoObject = (JGoObject)this.myVector.get(i);
        int j = localJGoObject.getWidth();
        int k = localJGoObject.getHeight();
        if (j > this.myMaxItemSize.width) {
          this.myMaxItemSize.width = j;
        }
        if (k > this.myMaxItemSize.height) {
          this.myMaxItemSize.height = k;
        }
      }
    }
    return this.myMaxItemSize;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/ListArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */