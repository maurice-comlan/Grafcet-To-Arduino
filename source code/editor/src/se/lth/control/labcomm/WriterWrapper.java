package se.lth.control.labcomm;

import java.io.IOException;
import java.io.OutputStream;

class WriterWrapper
  implements LabCommWriter
{
  private OutputStream os;
  
  public WriterWrapper(OutputStream paramOutputStream)
  {
    this.os = paramOutputStream;
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    this.os.write(paramArrayOfByte);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/labcomm/WriterWrapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */