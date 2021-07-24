package se.lth.control.orca;

import java.io.IOException;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;
import se.lth.control.labcomm.api.LabCommType;

public class connection_list_t
  implements LabCommType
{
  public int index;
  public String name;
  public byte[] signature;
  
  public static void encode(LabCommEncoder paramLabCommEncoder, connection_list_t[] paramArrayOfconnection_list_t)
    throws IOException
  {
    paramLabCommEncoder.encodeInt(paramArrayOfconnection_list_t.length);
    int i = paramArrayOfconnection_list_t.length;
    for (int j = 0; j < i; j++)
    {
      paramLabCommEncoder.encodeInt(paramArrayOfconnection_list_t[j].index);
      paramLabCommEncoder.encodeString(paramArrayOfconnection_list_t[j].name);
      paramLabCommEncoder.encodeInt(paramArrayOfconnection_list_t[j].signature.length);
      int k = paramArrayOfconnection_list_t[j].signature.length;
      for (int m = 0; m < k; m++) {
        paramLabCommEncoder.encodeByte(paramArrayOfconnection_list_t[j].signature[m]);
      }
    }
  }
  
  public static connection_list_t[] decode(LabCommDecoder paramLabCommDecoder)
    throws IOException
  {
    int i = paramLabCommDecoder.decodeInt();
    connection_list_t[] arrayOfconnection_list_t = new connection_list_t[i];
    for (int j = 0; j < i; j++)
    {
      arrayOfconnection_list_t[j] = new connection_list_t();
      arrayOfconnection_list_t[j].index = paramLabCommDecoder.decodeInt();
      arrayOfconnection_list_t[j].name = paramLabCommDecoder.decodeString();
      int k = paramLabCommDecoder.decodeInt();
      arrayOfconnection_list_t[j].signature = new byte[k];
      for (int m = 0; m < k; m++) {
        arrayOfconnection_list_t[j].signature[m] = paramLabCommDecoder.decodeByte();
      }
    }
    return arrayOfconnection_list_t;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/orca/connection_list_t.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */