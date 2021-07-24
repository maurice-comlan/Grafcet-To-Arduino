package grafchart.sfc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JComponent;
import se.lth.control.DoublePoint;
import se.lth.control.StringPosition;
import se.lth.control.plot.Axis;
import se.lth.control.plot.AxisPoint;

public class PlotComponent
  extends JComponent
{
  private static final int inset = 6;
  private static final int tickLength = 6;
  private static final int tickToText = 3;
  protected boolean updateFlag;
  protected int channels;
  protected Dimension preferredSize = new Dimension(600, 200);
  protected Dimension minimumSize = new Dimension(100, 100);
  protected String title;
  protected String titleTemp;
  private boolean titleChange = false;
  protected Axis xAxis = null;
  protected Axis xAxisTemp = null;
  private boolean xAxisChange = false;
  protected Axis yAxis = null;
  protected Axis yAxisTemp = null;
  private boolean yAxisChange = false;
  protected StringPosition ct = new StringPosition(StringPosition.CENTER_TOP);
  protected StringPosition rm = new StringPosition(StringPosition.RIGHT_MIDDLE);
  protected StringPosition rb = new StringPosition(StringPosition.RIGHT_BOTTOM);
  protected Color gridColor = Color.lightGray;
  protected DoublePoint[][] plotData;
  protected List[] plotDataTemp;
  private boolean plotDataChange = false;
  protected Color[] plotColor;
  protected String labelPattern = "-00.0";
  protected Dimension lastSize = new Dimension(0, 0);
  protected Dimension size;
  protected Point origin = new Point(0, 0);
  protected Rectangle plotRect = new Rectangle();
  protected int xlength = 0;
  protected int ylength = 0;
  Thread pT = null;
  protected double xmin;
  protected double xscale;
  protected double ymin;
  protected double yscale;
  protected int[] xv = new int[1];
  protected int[] yv = new int[1];
  Color saveColor;
  
  public PlotComponent(int paramInt, Thread paramThread)
  {
    this.pT = paramThread;
    if (paramInt <= 0) {
      throw new IllegalArgumentException("'channels' must be > 0");
    }
    this.channels = paramInt;
    this.plotColor = new Color[paramInt];
    setDoubleBuffered(true);
    this.plotData = new DoublePoint[paramInt][];
    for (int i = 0; i < paramInt; i++)
    {
      this.plotData[i] = new DoublePoint[0];
      this.plotColor[i] = Color.black;
    }
    if (paramInt > 1) {
      this.plotColor[1] = Color.red;
    }
    if (paramInt > 2) {
      this.plotColor[2] = Color.green;
    }
    if (paramInt > 3) {
      this.plotColor[3] = Color.blue;
    }
    this.plotDataTemp = new List[paramInt];
  }
  
  public void setColor(int paramInt, Color paramColor)
  {
    this.plotColor[paramInt] = paramColor;
  }
  
  public Color getColor(int paramInt)
  {
    return this.plotColor[paramInt];
  }
  
  public boolean isOpaque()
  {
    return true;
  }
  
  public synchronized void setXAxis(Axis paramAxis)
  {
    this.xAxisTemp = paramAxis;
    this.xAxisChange = true;
  }
  
  public synchronized void setYAxis(Axis paramAxis)
  {
    this.yAxisTemp = paramAxis;
    this.yAxisChange = true;
  }
  
  public synchronized void setPlot(int paramInt, List paramList)
  {
    if (paramInt >= this.channels) {
      throw new IllegalArgumentException();
    }
    this.plotDataTemp[paramInt] = paramList;
    this.plotDataChange = true;
  }
  
  public void setPlot(List paramList)
  {
    setPlot(0, paramList);
  }
  
  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
  
  public void update()
  {
    this.updateFlag = true;
    repaint();
  }
  
  protected synchronized void updateInternal()
  {
    if (this.xAxisChange)
    {
      this.xAxis = this.xAxisTemp;
      this.xAxisChange = false;
      this.xmin = this.xAxis.getMin();
      this.xscale = (1.0D / (this.xAxis.getMax() - this.xmin));
    }
    if (this.yAxisChange)
    {
      this.yAxis = this.yAxisTemp;
      this.yAxisChange = false;
      this.ymin = this.yAxis.getMin();
      this.yscale = (1.0D / (this.yAxis.getMax() - this.ymin));
    }
    if (this.titleChange)
    {
      this.title = this.titleTemp;
      this.titleChange = false;
    }
    if (this.plotDataChange)
    {
      for (int i = 0; i < this.channels; i++)
      {
        List localList = this.plotDataTemp[i];
        DoublePoint[] arrayOfDoublePoint = this.plotData[i];
        if (localList != null)
        {
          int j = localList.size();
          if (arrayOfDoublePoint.length != j) {
            arrayOfDoublePoint = new DoublePoint[j];
          }
          arrayOfDoublePoint = (DoublePoint[])localList.toArray(arrayOfDoublePoint);
        }
        else
        {
          arrayOfDoublePoint = new DoublePoint[0];
        }
        this.plotData[i] = arrayOfDoublePoint;
        if (this.xv.length < arrayOfDoublePoint.length)
        {
          this.xv = new int[arrayOfDoublePoint.length];
          this.yv = new int[arrayOfDoublePoint.length];
        }
      }
      this.plotDataChange = false;
    }
    this.updateFlag = false;
  }
  
  public Dimension getPreferredSize()
  {
    return this.preferredSize;
  }
  
  public Dimension getMinimumSize()
  {
    return this.minimumSize;
  }
  
  public void paintComponent(Graphics paramGraphics)
  {
    synchronized (this.pT)
    {
      if (this.updateFlag) {
        updateInternal();
      }
      this.size = getSize();
      if ((this.size.width != this.lastSize.width) || (this.size.height != this.lastSize.height))
      {
        this.lastSize = this.size;
        int k = paramGraphics.getFontMetrics().stringWidth(this.labelPattern);
        int m = paramGraphics.getFontMetrics().getHeight();
        int i = 6 + k + 3 + 6;
        int j = 6 + m;
        this.plotRect.setLocation(i, j);
        i = this.size.width - i - 6 - k / 2;
        j = this.size.height - j - m - 6 - 3 - 6;
        this.plotRect.setSize(i, j);
      }
      this.saveColor = paramGraphics.getColor();
      paramGraphics.setColor(Color.white);
      paramGraphics.fillRect(0, 0, this.size.width, this.size.height);
      paramGraphics.translate(this.plotRect.x, this.plotRect.y);
      paintGrid(paramGraphics);
      paramGraphics.setColor(this.saveColor);
      if (this.title != null) {
        paintTitle(paramGraphics);
      }
      if (this.xAxis != null) {
        paintXAxis(paramGraphics);
      }
      if (this.yAxis != null) {
        paintYAxis(paramGraphics);
      }
      if (this.plotData != null) {
        paintData(paramGraphics);
      }
      paramGraphics.translate(-this.plotRect.x, -this.plotRect.y);
    }
  }
  
  public void setPlotThread(Thread paramThread)
  {
    this.pT = paramThread;
  }
  
  protected void paintGrid(Graphics paramGraphics)
  {
    int n = this.plotRect.height;
    int i1 = this.plotRect.width;
    paramGraphics.setColor(this.gridColor);
    int m;
    int i;
    double d;
    if (this.xAxis != null)
    {
      m = this.xAxis.getGridSize();
      for (i = 0; i < m; i++)
      {
        d = this.xAxis.getGridPoint(i);
        int j = (int)Math.round(d * i1);
        paramGraphics.drawLine(j, 0, j, n);
      }
    }
    if (this.yAxis != null)
    {
      m = this.yAxis.getGridSize();
      for (i = 0; i < m; i++)
      {
        d = this.yAxis.getGridPoint(i);
        int k = n - (int)Math.round(d * n);
        paramGraphics.drawLine(0, k, i1, k);
      }
    }
  }
  
  protected void paintData(Graphics paramGraphics)
  {
    int k = 0;
    for (int j = 0; j < this.channels; j++)
    {
      DoublePoint[] arrayOfDoublePoint = this.plotData[j];
      if (arrayOfDoublePoint != null)
      {
        k = arrayOfDoublePoint.length;
        for (int i = 0; i < k; i++)
        {
          DoublePoint localDoublePoint = arrayOfDoublePoint[i];
          double d1 = this.xscale * (localDoublePoint.x - this.xmin);
          double d2 = this.yscale * (localDoublePoint.y - this.ymin);
          this.xv[i] = ((int)Math.round(d1 * this.plotRect.width));
          this.yv[i] = (this.plotRect.height - (int)Math.round(d2 * this.plotRect.height));
        }
        paramGraphics.setColor(this.plotColor[j]);
        paramGraphics.drawPolyline(this.xv, this.yv, k);
      }
    }
  }
  
  protected void paintTitle(Graphics paramGraphics)
  {
    this.rb.put(this.title, paramGraphics, this.plotRect.width, 0);
  }
  
  protected void paintXAxis(Graphics paramGraphics)
  {
    int i1 = this.xAxis.getAxisSize();
    int k = this.plotRect.height;
    int m = k + 6;
    int n = m + 3;
    int i2 = this.plotRect.width;
    paramGraphics.drawLine(0, k, i2, k);
    for (int i = 0; i < i1; i++)
    {
      AxisPoint localAxisPoint = this.xAxis.getAxisPoint(i);
      int j = (int)Math.round(localAxisPoint.position * i2);
      paramGraphics.drawLine(j, k, j, m);
      this.ct.put(localAxisPoint.label, paramGraphics, j, n);
    }
  }
  
  protected void paintYAxis(Graphics paramGraphics)
  {
    int k = -9;
    int m = this.plotRect.height;
    int n = this.yAxis.getAxisSize();
    paramGraphics.drawLine(0, 0, 0, m);
    for (int i = 0; i < n; i++)
    {
      AxisPoint localAxisPoint = this.yAxis.getAxisPoint(i);
      int j = m - (int)Math.round(localAxisPoint.position * m);
      paramGraphics.drawLine(0, j, -6, j);
      this.rm.put(localAxisPoint.label, paramGraphics, k, j);
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/PlotComponent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */