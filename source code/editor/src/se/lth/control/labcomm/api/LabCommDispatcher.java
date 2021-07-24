package se.lth.control.labcomm.api;

public abstract interface LabCommDispatcher
{
  public abstract String getSampleIdentifier();
  
  public abstract String getName();
  
  public abstract byte[] getSignature();
  
  public abstract void decodeAndHandle(LabCommDecoder paramLabCommDecoder, LabCommHandler paramLabCommHandler)
    throws Exception;
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/labcomm/api/LabCommDispatcher.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */