package grafchart.sfc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import se.lth.control.DoublePoint;
import se.lth.control.plot.LinearAxis;

public class PlotPanel
  extends JPanel
{
  private PlotComponent plotter;
  private PlotThread plotThread;
  private ArrayList[] plotData;
  private double xBottom = 0.0D;
  private double xRange = 10.0D;
  private int xDivTicks = 5;
  private int xDivGrid = 5;
  private double yBottom = -10.0D;
  private double yRange = 20.0D;
  private int yDivTicks = 4;
  private int yDivGrid = 4;
  private int priority;
  private double time;
  private int freqCount = 0;
  private int freq = 1;
  
  public PlotPanel(int paramInt1, int paramInt2)
  {
    super(null);
    this.priority = paramInt2;
    this.plotData = new ArrayList[paramInt1];
    for (int i = 0; i < paramInt1; i++) {
      this.plotData[i] = new ArrayList();
    }
    this.plotter = new PlotComponent(paramInt1, this.plotThread);
    this.plotter.setYAxis(new LinearAxis(this.yRange, this.yBottom, this.yDivTicks, this.yDivGrid));
    this.plotter.setXAxis(new LinearAxis(this.xRange, this.xBottom, this.xDivTicks, this.xDivGrid));
    this.plotter.update();
    add(this.plotter);
    addComponentListener(new ComponentListener()
    {
      public void componentHidden(ComponentEvent paramAnonymousComponentEvent) {}
      
      public void componentMoved(ComponentEvent paramAnonymousComponentEvent) {}
      
      public void componentShown(ComponentEvent paramAnonymousComponentEvent) {}
      
      public void componentResized(ComponentEvent paramAnonymousComponentEvent)
      {
        PlotPanel.this.repaint();
      }
    });
  }
  
  public void paintComponent(Graphics paramGraphics)
  {
    super.paintComponent(paramGraphics);
    Dimension localDimension = getSize();
    this.plotter.setBounds(3, 3, localDimension.width - 6, localDimension.height - 6);
  }
  
  public synchronized void setXAxis(double paramDouble, int paramInt1, int paramInt2)
  {
    this.plotter.setXAxis(new LinearAxis(paramDouble, this.xBottom, paramInt1, paramInt2));
    this.plotter.update();
    this.xRange = paramDouble;
    this.xDivTicks = paramInt1;
    this.xDivGrid = paramInt2;
  }
  
  public synchronized void setYAxis(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
  {
    this.plotter.setYAxis(new LinearAxis(paramDouble1, paramDouble2, paramInt1, paramInt2));
    this.plotter.update();
    this.yBottom = paramDouble2;
    this.yRange = paramDouble1;
    this.yDivTicks = paramInt1;
    this.yDivGrid = paramInt2;
  }
  
  public synchronized void setUpdateFreq(int paramInt)
  {
    this.freq = paramInt;
  }
  
  public void setTitle(String paramString)
  {
    this.plotter.setTitle(paramString);
  }
  
  private synchronized int getFreq()
  {
    return this.freq;
  }
  
  public void start()
  {
    this.plotThread = new PlotThread();
    this.plotter.setPlotThread(this.plotThread);
    this.plotThread.start();
  }
  
  public void stopThread()
  {
    this.plotThread.interrupt();
  }
  
  public void putData(double paramDouble1, double paramDouble2)
  {
    synchronized (this.plotThread)
    {
      this.plotData[0].add(new DoublePoint(paramDouble1, paramDouble2));
      this.time = paramDouble1;
      this.freqCount += 1;
      if (this.freqCount >= getFreq())
      {
        this.plotThread.notify();
        this.freqCount = 0;
      }
    }
  }
  
  public void putData(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    synchronized (this.plotThread)
    {
      this.plotData[0].add(new DoublePoint(paramDouble1, paramDouble2));
      this.plotData[1].add(new DoublePoint(paramDouble1, paramDouble3));
      this.time = paramDouble1;
      this.freqCount += 1;
      if (this.freqCount >= getFreq())
      {
        this.plotThread.notify();
        this.freqCount = 0;
      }
    }
  }
  
  public void putData(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    synchronized (this.plotThread)
    {
      this.plotData[0].add(new DoublePoint(paramDouble1, paramDouble2));
      this.plotData[1].add(new DoublePoint(paramDouble1, paramDouble3));
      this.plotData[2].add(new DoublePoint(paramDouble1, paramDouble4));
      this.time = paramDouble1;
      this.freqCount += 1;
      if (this.freqCount >= getFreq())
      {
        this.plotThread.notify();
        this.freqCount = 0;
      }
    }
  }
  
  public void putData(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5)
  {
    synchronized (this.plotThread)
    {
      this.plotData[0].add(new DoublePoint(paramDouble1, paramDouble2));
      this.plotData[1].add(new DoublePoint(paramDouble1, paramDouble3));
      this.plotData[2].add(new DoublePoint(paramDouble1, paramDouble4));
      this.plotData[3].add(new DoublePoint(paramDouble1, paramDouble5));
      this.time = paramDouble1;
      this.freqCount += 1;
      if (this.freqCount >= getFreq())
      {
        this.plotThread.notify();
        this.freqCount = 0;
      }
    }
  }
  
  public void setColor(int paramInt, Color paramColor)
  {
    this.plotter.setColor(paramInt - 1, paramColor);
  }
  
  public Color getColor(int paramInt)
  {
    return this.plotter.getColor(paramInt - 1);
  }
  
  private class PlotThread
    extends Thread
  {
    private PlotThread() {}
    
    public void run()
    {
      setPriority(PlotPanel.this.priority);
      try
      {
        while (!isInterrupted())
        {
          synchronized (this)
          {
            wait();
            PlotPanel.this.xBottom = (PlotPanel.this.time - PlotPanel.this.xRange);
            while (((DoublePoint)PlotPanel.this.plotData[0].get(0)).x < PlotPanel.this.xBottom) {
              for (int i = 0; i < PlotPanel.this.plotData.length; i++) {
                PlotPanel.this.plotData[i].remove(0);
              }
            }
            PlotPanel.this.plotter.setXAxis(new LinearAxis(PlotPanel.this.xRange, PlotPanel.this.xBottom, PlotPanel.this.xDivTicks, PlotPanel.this.xDivGrid));
            for (int i = 0; i < PlotPanel.this.plotData.length; i++) {
              PlotPanel.this.plotter.setPlot(i, PlotPanel.this.plotData[i]);
            }
          }
          PlotPanel.this.plotter.update();
        }
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/PlotPanel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */