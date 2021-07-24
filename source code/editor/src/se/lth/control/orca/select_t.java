package se.lth.control.orca;

import java.io.IOException;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;
import se.lth.control.labcomm.api.LabCommType;

public class select_t
  implements LabCommType
{
  public int port;
  public int decimation;
  public int[] list;
  
  public static void encode(LabCommEncoder paramLabCommEncoder, select_t paramselect_t)
    throws IOException
  {
    paramLabCommEncoder.encodeInt(paramselect_t.port);
    paramLabCommEncoder.encodeInt(paramselect_t.decimation);
    paramLabCommEncoder.encodeInt(paramselect_t.list.length);
    int i = paramselect_t.list.length;
    for (int j = 0; j < i; j++) {
      paramLabCommEncoder.encodeInt(paramselect_t.list[j]);
    }
  }
  
  public static select_t decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    select_t localselect_t = new select_t();
    localselect_t.port = paramLabCommDecoder.decodeInt();
    localselect_t.decimation = paramLabCommDecoder.decodeInt();
    int i = paramLabCommDecoder.decodeInt();
    localselect_t.list = new int[i];
    for (int j = 0; j < i; j++) {
      localselect_t.list[j] = paramLabCommDecoder.decodeInt();
    }
    return localselect_t;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/orca/select_t.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */