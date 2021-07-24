package grafchart.sfc;

public abstract interface Writable
  extends Referencable
{
  public abstract boolean isBoolean();
  
  public abstract boolean isInteger();
  
  public abstract boolean isString();
  
  public abstract boolean isReal();
  
  public abstract void setStoredBoolAction(boolean paramBoolean);
  
  public abstract void setStoredIntAction(int paramInt);
  
  public abstract void setStoredStringAction(String paramString);
  
  public abstract void setStoredRealAction(double paramDouble);
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Writable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */