package grafchart.sfc;

public abstract interface InputVariable
{
  public abstract boolean isCyclicUpdated();
  
  public abstract void setCyclicUpdated(boolean paramBoolean);
  
  public abstract double getSample();
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/InputVariable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */