package se.lth.control.orca;

import java.io.IOException;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommDispatcher;
import se.lth.control.labcomm.api.LabCommEncoder;
import se.lth.control.labcomm.api.LabCommHandler;
import se.lth.control.labcomm.api.LabCommSample;

public class select_parameter
  implements LabCommSample
{
  private static final String name = "select_parameter";
  private static byte[] signature = { 0, 0, 0, 17, 0, 0, 0, 3, 0, 0, 0, 4, 112, 111, 114, 116, 0, 0, 0, 35, 0, 0, 0, 10, 100, 101, 99, 105, 109, 97, 116, 105, 111, 110, 0, 0, 0, 35, 0, 0, 0, 4, 108, 105, 115, 116, 0, 0, 0, 16, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 35 };
  
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
  
  public static void encode(LabCommEncoder paramLabCommEncoder, select_t paramselect_t)
    throws IOException
  {
    paramLabCommEncoder.begin("select_parameter");
    select_t.encode(paramLabCommEncoder, paramselect_t);
    paramLabCommEncoder.end("select_parameter");
  }
  
  public static select_t decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    select_t localselect_t = select_t.decode(paramLabCommDecoder);
    return localselect_t;
  }
  
  private static class Dispatcher
    implements LabCommDispatcher
  {
    public String getSampleIdentifier()
    {
      return "select_parameter";
    }
    
    public String getName()
    {
      return "select_parameter";
    }
    
    public byte[] getSignature()
    {
      return select_parameter.signature;
    }
    
    public void decodeAndHandle(LabCommDecoder paramLabCommDecoder, LabCommHandler paramLabCommHandler)
      throws Exception
    {
      ((select_parameter.Handler)paramLabCommHandler).handle_select_parameter(select_parameter.decode(paramLabCommDecoder));
    }
  }
  
  public static abstract interface Handler
    extends LabCommHandler
  {
    public abstract void handle_select_parameter(select_t paramselect_t)
      throws Exception;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/orca/select_parameter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */