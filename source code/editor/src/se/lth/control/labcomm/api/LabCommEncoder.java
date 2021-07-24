package se.lth.control.labcomm.api;

import grafchart.sfc.Utils;
import java.io.IOException;
import java.io.OutputStream;

public abstract interface LabCommEncoder
{
  public abstract void register(LabCommDispatcher paramLabCommDispatcher)
    throws IOException;
  
  public abstract void begin(String paramString)
    throws IOException;
  
  public abstract void end(String paramString)
    throws IOException;
  
  public abstract void encodeBoolean(boolean paramBoolean)
    throws IOException;
  
  public abstract void encodeByte(byte paramByte)
    throws IOException;
  
  public abstract void encodeShort(short paramShort)
    throws IOException;
  
  public abstract void encodeInt(int paramInt)
    throws IOException;
  
  public abstract void encodeLong(long paramLong)
    throws IOException;
  
  public abstract void encodeFloat(float paramFloat)
    throws IOException;
  
  public abstract void encodeDouble(double paramDouble)
    throws IOException;
  
  public abstract void encodeString(String paramString)
    throws IOException;
  
  public abstract void encodePacked32(long paramLong)
    throws IOException;
  
  public static class Factory
  {
    public static LabCommEncoder newChannel(OutputStream paramOutputStream, int paramInt)
      throws IOException
    {
      if (paramInt == 2013) {
        return new se.lth.control.labcomm.LabCommEncoderChannel(paramOutputStream);
      }
      if (paramInt == 2006) {
        return new se.lth.control.labcomm2006.LabCommEncoderChannel(paramOutputStream);
      }
      Utils.writeInternalError("Unhandled LabComm version: " + paramInt);
      return null;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/labcomm/api/LabCommEncoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */