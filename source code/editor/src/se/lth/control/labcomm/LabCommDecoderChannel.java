package se.lth.control.labcomm;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommDispatcher;
import se.lth.control.labcomm.api.LabCommEncoder;
import se.lth.control.labcomm.api.LabCommHandler;

public class LabCommDecoderChannel
  extends Thread
  implements LabCommDecoder
{
  private DataInputStream in;
  private LabCommDecoderRegistry registry;
  
  public LabCommDecoderChannel(InputStream paramInputStream)
  {
    this.in = new DataInputStream(paramInputStream);
    this.registry = new LabCommDecoderRegistry();
  }
  
  private void runOne()
    throws Exception
  {
    int i = 0;
    while (i == 0)
    {
      int j = decodePacked32();
      Object localObject1;
      Object localObject2;
      switch (j)
      {
      case 1: 
      case 2: 
        int k = decodePacked32();
        localObject1 = decodeString();
        localObject2 = new ByteArrayOutputStream();
        collectFlatSignature(new LabCommEncoderChannel((OutputStream)localObject2, false));
        this.registry.add(k, (String)localObject1, ((ByteArrayOutputStream)localObject2).toByteArray());
        break;
      default: 
        LabCommDecoderRegistry.Entry localEntry = this.registry.get(j);
        if (localEntry == null) {
          throw new IOException("Unhandled tag " + j);
        }
        localObject1 = localEntry.getDispatcher();
        if (localObject1 == null) {
          throw new IOException("No dispatcher for '" + localEntry.getName() + "'");
        }
        localObject2 = localEntry.getHandler();
        if (localObject2 == null) {
          throw new IOException("No handler for '" + localEntry.getName() + "'");
        }
        ((LabCommDispatcher)localObject1).decodeAndHandle(this, (LabCommHandler)localObject2);
        i = 1;
      }
    }
  }
  
  public void run()
  {
    try
    {
      String str = decodeString();
      if (!str.equals("LabComm2013")) {
        throw new IOException("LabComm version mismatch " + str + " != " + "LabComm2013");
      }
      while (!isInterrupted()) {
        runOne();
      }
    }
    catch (EOFException|SocketException localEOFException) {}catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void collectFlatSignature(LabCommEncoder paramLabCommEncoder)
    throws IOException
  {
    int i = decodePacked32();
    paramLabCommEncoder.encodePacked32(i);
    int j;
    int k;
    switch (i)
    {
    case 16: 
      j = decodePacked32();
      paramLabCommEncoder.encodePacked32(j);
      for (k = 0; k < j; k++) {
        paramLabCommEncoder.encodePacked32(decodePacked32());
      }
      collectFlatSignature(paramLabCommEncoder);
      break;
    case 17: 
      j = decodePacked32();
      paramLabCommEncoder.encodePacked32(j);
      for (k = 0; k < j; k++)
      {
        paramLabCommEncoder.encodeString(decodeString());
        collectFlatSignature(paramLabCommEncoder);
      }
      break;
    case 32: 
    case 33: 
    case 34: 
    case 35: 
    case 36: 
    case 37: 
    case 38: 
    case 39: 
      break;
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 27: 
    case 28: 
    case 29: 
    case 30: 
    case 31: 
    default: 
      throw new IOException("Unimplemented type=" + i);
    }
    paramLabCommEncoder.end(null);
  }
  
  public void register(LabCommDispatcher paramLabCommDispatcher, LabCommHandler paramLabCommHandler)
    throws IOException
  {
    this.registry.add(paramLabCommDispatcher, paramLabCommHandler);
  }
  
  public boolean decodeBoolean()
    throws IOException
  {
    return this.in.readBoolean();
  }
  
  public byte decodeByte()
    throws IOException
  {
    return this.in.readByte();
  }
  
  public short decodeShort()
    throws IOException
  {
    return this.in.readShort();
  }
  
  public int decodeInt()
    throws IOException
  {
    return this.in.readInt();
  }
  
  public long decodeLong()
    throws IOException
  {
    return this.in.readLong();
  }
  
  public float decodeFloat()
    throws IOException
  {
    return this.in.readFloat();
  }
  
  public double decodeDouble()
    throws IOException
  {
    return this.in.readDouble();
  }
  
  public String decodeString()
    throws IOException
  {
    int i = decodePacked32() & 0xFFFFFFFF;
    byte[] arrayOfByte = new byte[i];
    for (int j = 0; j < i; j++) {
      arrayOfByte[j] = this.in.readByte();
    }
    return new String(arrayOfByte);
  }
  
  public int decodePacked32()
    throws IOException
  {
    long l = 0L;
    int i = 1;
    do
    {
      int j = this.in.readByte();
      l = l << 7 | j & 0x7F;
      i = (j & 0x80) != 0 ? 1 : 0;
    } while (i != 0);
      System.out.println("se.lth.control.labcomm.LabCommDecoderChannel.decodePacked32()");
    //return (int)(l & 0xFFFFFFFFFFFFFFFF);
    return 0;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/labcomm/LabCommDecoderChannel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */