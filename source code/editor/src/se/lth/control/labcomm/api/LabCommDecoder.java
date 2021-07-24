package se.lth.control.labcomm.api;

import grafchart.sfc.Utils;
import java.io.IOException;
import java.io.InputStream;

public abstract interface LabCommDecoder
{
  public abstract void register(LabCommDispatcher paramLabCommDispatcher, LabCommHandler paramLabCommHandler)
    throws IOException;
  
  public abstract boolean decodeBoolean()
    throws IOException;
  
  public abstract byte decodeByte()
    throws IOException;
  
  public abstract short decodeShort()
    throws IOException;
  
  public abstract int decodeInt()
    throws IOException;
  
  public abstract long decodeLong()
    throws IOException;
  
  public abstract float decodeFloat()
    throws IOException;
  
  public abstract double decodeDouble()
    throws IOException;
  
  public abstract String decodeString()
    throws IOException;
  
  public abstract int decodePacked32()
    throws IOException;
  
  public abstract void start();
  
  public static class Factory
  {
    public static LabCommDecoder newChannel(InputStream paramInputStream, int paramInt)
    {
      if (paramInt == 2013) {
        return new se.lth.control.labcomm.LabCommDecoderChannel(paramInputStream);
      }
      if (paramInt == 2006) {
        return new se.lth.control.labcomm2006.LabCommDecoderChannel(paramInputStream);
      }
      Utils.writeInternalError("Unhandled LabComm version: " + paramInt);
      return null;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/labcomm/api/LabCommDecoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */