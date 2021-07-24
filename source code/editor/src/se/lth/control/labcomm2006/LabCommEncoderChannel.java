package se.lth.control.labcomm2006;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import se.lth.control.labcomm.api.LabCommDispatcher;
import se.lth.control.labcomm.api.LabCommEncoder;

public class LabCommEncoderChannel
  implements LabCommEncoder
{
  private LabCommWriter writer;
  private ByteArrayOutputStream bytes;
  private DataOutputStream data;
  private LabCommEncoderRegistry registry;
  
  public LabCommEncoderChannel(LabCommWriter paramLabCommWriter, boolean paramBoolean)
    throws IOException
  {
    this.writer = paramLabCommWriter;
    this.bytes = new ByteArrayOutputStream();
    this.data = new DataOutputStream(this.bytes);
    this.registry = new LabCommEncoderRegistry();
    if (paramBoolean) {
      throw new IllegalArgumentException("Labcomm 2006 does not support emitVersion");
    }
  }
  
  public LabCommEncoderChannel(LabCommWriter paramLabCommWriter)
    throws IOException
  {
    this(paramLabCommWriter, false);
  }
  
  public LabCommEncoderChannel(OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException
  {
    this(new WriterWrapper(paramOutputStream), paramBoolean);
  }
  
  public LabCommEncoderChannel(OutputStream paramOutputStream)
    throws IOException
  {
    this(new WriterWrapper(paramOutputStream), false);
  }
  
  public void register(LabCommDispatcher paramLabCommDispatcher)
    throws IOException
  {
    int i = this.registry.add(paramLabCommDispatcher);
    encodePacked32(2L);
    encodePacked32(i);
    encodeString(paramLabCommDispatcher.getName());
    byte[] arrayOfByte = paramLabCommDispatcher.getSignature();
    for (int j = 0; j < arrayOfByte.length; j++) {
      encodeByte(arrayOfByte[j]);
    }
    end(null);
  }
  
  public void begin(String paramString)
    throws IOException
  {
    encodePacked32(this.registry.getTag(paramString));
  }
  
  public void end(String paramString)
    throws IOException
  {
    this.data.flush();
    this.writer.write(this.bytes.toByteArray());
    this.bytes.reset();
  }
  
  public void encodeBoolean(boolean paramBoolean)
    throws IOException
  {
    this.data.writeBoolean(paramBoolean);
  }
  
  public void encodeByte(byte paramByte)
    throws IOException
  {
    this.data.writeByte(paramByte);
  }
  
  public void encodeShort(short paramShort)
    throws IOException
  {
    this.data.writeShort(paramShort);
  }
  
  public void encodeInt(int paramInt)
    throws IOException
  {
    this.data.writeInt(paramInt);
  }
  
  public void encodeLong(long paramLong)
    throws IOException
  {
    this.data.writeLong(paramLong);
  }
  
  public void encodeFloat(float paramFloat)
    throws IOException
  {
    this.data.writeFloat(paramFloat);
  }
  
  public void encodeDouble(double paramDouble)
    throws IOException
  {
    this.data.writeDouble(paramDouble);
  }
  
  public void encodeString(String paramString)
    throws IOException
  {
    this.data.writeShort(0);
    this.data.writeUTF(paramString);
  }
  
  public void encodePacked32(long paramLong)
    throws IOException
  {
    if (paramLong > 2147483647L) {
      throw new IllegalArgumentException("Value too large, must fit in 32 bits");
    }
    encodeInt((int)paramLong);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/labcomm2006/LabCommEncoderChannel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */