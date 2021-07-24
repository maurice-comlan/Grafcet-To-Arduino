package se.lth.control.labcomm2006;

import java.io.IOException;

public abstract interface LabCommWriter
{
  public abstract void write(byte[] paramArrayOfByte)
    throws IOException;
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/labcomm2006/LabCommWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */