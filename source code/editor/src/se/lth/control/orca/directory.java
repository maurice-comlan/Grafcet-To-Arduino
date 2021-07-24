package se.lth.control.orca;

import java.io.IOException;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommDispatcher;
import se.lth.control.labcomm.api.LabCommEncoder;
import se.lth.control.labcomm.api.LabCommHandler;
import se.lth.control.labcomm.api.LabCommSample;

public class directory
  implements LabCommSample
{
  private static final String name = "directory";
  public connection_list_t[] input;
  public connection_list_t[] output;
  public connection_list_t[] parameter;
  public connection_list_t[] log;
  private static byte[] signature = { 0, 0, 0, 17, 0, 0, 0, 4, 0, 0, 0, 5, 105, 110, 112, 117, 116, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 3, 0, 0, 0, 5, 105, 110, 100, 101, 120, 0, 0, 0, 35, 0, 0, 0, 4, 110, 97, 109, 101, 0, 0, 0, 39, 0, 0, 0, 9, 115, 105, 103, 110, 97, 116, 117, 114, 101, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 6, 111, 117, 116, 112, 117, 116, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 3, 0, 0, 0, 5, 105, 110, 100, 101, 120, 0, 0, 0, 35, 0, 0, 0, 4, 110, 97, 109, 101, 0, 0, 0, 39, 0, 0, 0, 9, 115, 105, 103, 110, 97, 116, 117, 114, 101, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 9, 112, 97, 114, 97, 109, 101, 116, 101, 114, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 3, 0, 0, 0, 5, 105, 110, 100, 101, 120, 0, 0, 0, 35, 0, 0, 0, 4, 110, 97, 109, 101, 0, 0, 0, 39, 0, 0, 0, 9, 115, 105, 103, 110, 97, 116, 117, 114, 101, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 3, 108, 111, 103, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 17, 0, 0, 0, 3, 0, 0, 0, 5, 105, 110, 100, 101, 120, 0, 0, 0, 35, 0, 0, 0, 4, 110, 97, 109, 101, 0, 0, 0, 39, 0, 0, 0, 9, 115, 105, 103, 110, 97, 116, 117, 114, 101, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 33 };
  
  public static void register(LabCommDecoder paramLabCommDecoder, Handler paramHandler)
    throws IOException
  {
    paramLabCommDecoder.register(new Dispatcher(), paramHandler);
  }
  
  public static void register(LabCommEncoder paramLabCommEncoder)
    throws IOException
  {
    paramLabCommEncoder.register(new Dispatcher());
  }
  
  public static void encode(LabCommEncoder paramLabCommEncoder, directory paramdirectory)
    throws IOException
  {
    paramLabCommEncoder.begin("directory");
    connection_list_t.encode(paramLabCommEncoder, paramdirectory.input);
    connection_list_t.encode(paramLabCommEncoder, paramdirectory.output);
    connection_list_t.encode(paramLabCommEncoder, paramdirectory.parameter);
    connection_list_t.encode(paramLabCommEncoder, paramdirectory.log);
    paramLabCommEncoder.end("directory");
  }
  
  public static directory decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    directory localdirectory = new directory();
    localdirectory.input = connection_list_t.decode(paramLabCommDecoder);
    localdirectory.output = connection_list_t.decode(paramLabCommDecoder);
    localdirectory.parameter = connection_list_t.decode(paramLabCommDecoder);
    localdirectory.log = connection_list_t.decode(paramLabCommDecoder);
    return localdirectory;
  }
  
  private static class Dispatcher
    implements LabCommDispatcher
  {
    public String getSampleIdentifier()
    {
      return "directory";
    }
    
    public String getName()
    {
      return "directory";
    }
    
    public byte[] getSignature()
    {
      return directory.signature;
    }
    
    public void decodeAndHandle(LabCommDecoder paramLabCommDecoder, LabCommHandler paramLabCommHandler)
      throws Exception
    {
      ((directory.Handler)paramLabCommHandler).handle_directory(directory.decode(paramLabCommDecoder));
    }
  }
  
  public static abstract interface Handler
    extends LabCommHandler
  {
    public abstract void handle_directory(directory paramdirectory)
      throws Exception;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/orca/directory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */