package grafchart.sfc;

public abstract interface Readable
  extends Referencable
{
  public abstract boolean getBoolVal();
  
  public abstract boolean getOldBoolVal();
  
  public abstract int getIntVal();
  
  public abstract int getOldIntVal();
  
  public abstract String getStringVal();
  
  public abstract String getOldStringVal();
  
  public abstract double getRealVal();
  
  public abstract double getOldRealVal();
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Readable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */