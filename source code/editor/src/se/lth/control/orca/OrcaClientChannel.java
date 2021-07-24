package se.lth.control.orca;

import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;

public class OrcaClientChannel
{
  LabCommEncoder encoder;
  LabCommDecoder decoder;
  
  public LabCommEncoder getEncoder()
  {
    return this.encoder;
  }
  
  public LabCommDecoder getDecoder()
  {
    return this.decoder;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/orca/OrcaClientChannel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */