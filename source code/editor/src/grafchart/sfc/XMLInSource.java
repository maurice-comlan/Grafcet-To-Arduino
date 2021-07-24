package grafchart.sfc;

public abstract interface XMLInSource
  extends Readable
{
  public abstract void setValue(String paramString);
  
  public abstract String getXMLIdentifier();
  
  public abstract String getTopic();
  
  public abstract boolean receivedNewValue();
  
  public abstract String getSubject();
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/XMLInSource.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */