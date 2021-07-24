package se.lth.control.orca;

import grafchart.sfc.Utils;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import se.lth.control.labcomm.api.LabCommDecoder;
import se.lth.control.labcomm.api.LabCommEncoder;

public class OrcaClient
  implements directory.Handler
{
  private Socket socket;
  private LabCommEncoder encoder;
  private LabCommDecoder decoder;
  private directory dir;
  private int version;
  
  public synchronized void handle_directory(directory paramdirectory)
  {
    this.dir = paramdirectory;
  }
  
  public OrcaClient(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    this.version = paramInt2;
    this.socket = new Socket(paramString, paramInt1);
    this.socket.setTcpNoDelay(true);
    this.encoder = LabCommEncoder.Factory.newChannel(this.socket.getOutputStream(), paramInt2);
    select_input.register(this.encoder);
    select_output.register(this.encoder);
    select_parameter.register(this.encoder);
    select_log.register(this.encoder);
    this.decoder = LabCommDecoder.Factory.newChannel(this.socket.getInputStream(), paramInt2);
    directory.register(this.decoder, this);
    this.decoder.start();
    for (int i = 0; i < 300; i++)
    {
      synchronized (this)
      {
        if (this.dir != null) {
          return;
        }
      }
      try
      {
        Thread.sleep(10L);
      }
      catch (InterruptedException localInterruptedException)
      {
        Thread.currentThread().interrupt();
        return;
      }
    }
    Utils.writeError("LabComm Orca directory still not received after 3 seconds.");
  }
  
  public synchronized OrcaClientChannel selectInputTCP(String[] paramArrayOfString)
    throws IOException
  {
    if (this.dir != null) {
      return selectTCP(Select.Input, this.dir.input, paramArrayOfString, Direction.Write, 1);
    }
    return null;
  }
  
  public synchronized OrcaClientChannel selectOutputTCP(String[] paramArrayOfString)
    throws IOException
  {
    if (this.dir != null) {
      return selectTCP(Select.Output, this.dir.output, paramArrayOfString, Direction.Read, 1);
    }
    return null;
  }
  
  public synchronized OrcaClientChannel selectParameterTCP(String[] paramArrayOfString)
    throws IOException
  {
    if (this.dir != null) {
      return selectTCP(Select.Parameter, this.dir.parameter, paramArrayOfString, Direction.ReadWrite, 1);
    }
    return null;
  }
  
  public synchronized OrcaClientChannel selectLogTCP(String[] paramArrayOfString, int paramInt)
    throws IOException
  {
    if (this.dir != null) {
      return selectTCP(Select.Log, this.dir.log, paramArrayOfString, Direction.Read, paramInt);
    }
    return null;
  }
  
  private OrcaClientChannel selectTCP(Select paramSelect, connection_list_t[] paramArrayOfconnection_list_t, String[] paramArrayOfString, Direction paramDirection, int paramInt)
    throws IOException
  {
    int i = 0;
    for (int j = 0; j < paramArrayOfString.length; j++) {
      for (int k = 0; k < paramArrayOfconnection_list_t.length; k++) {
        if (paramArrayOfString[j].compareTo(paramArrayOfconnection_list_t[k].name) == 0)
        {
          i++;
          break;
        }
      }
    }
    Utils.writeInformation("Orca: " + i + " matches");
    if (i == 0) {
      return null;
    }
    ServerSocket localServerSocket = new ServerSocket(0);
    select_t localselect_t = new select_t();
    localselect_t.port = localServerSocket.getLocalPort();
    localselect_t.decimation = paramInt;
    localselect_t.list = new int[i];
    i = 0;
    for (int m = 0; m < paramArrayOfString.length; m++) {
      for (int n = 0; n < paramArrayOfconnection_list_t.length; n++) {
        if (paramArrayOfString[m].compareTo(paramArrayOfconnection_list_t[n].name) == 0)
        {
          Utils.writeInformation("Orca: selection[i] = " + paramArrayOfString[m] + " @ " + paramArrayOfconnection_list_t[n].index);
          localselect_t.list[i] = paramArrayOfconnection_list_t[n].index;
          i++;
          break;
        }
      }
    }
    switch (paramSelect)
    {
    case Input: 
      select_input.encode(this.encoder, localselect_t);
      break;
    case Output: 
      select_output.encode(this.encoder, localselect_t);
      break;
    case Parameter: 
      select_parameter.encode(this.encoder, localselect_t);
      break;
    case Log: 
      select_log.encode(this.encoder, localselect_t);
    }
    Socket localSocket = localServerSocket.accept();
    localSocket.setTcpNoDelay(true);
    OrcaClientChannel localOrcaClientChannel = new OrcaClientChannel();
    if ((paramDirection == Direction.Read) || (paramDirection == Direction.ReadWrite)) {
      localOrcaClientChannel.decoder = LabCommDecoder.Factory.newChannel(localSocket.getInputStream(), this.version);
    }
    if ((paramDirection == Direction.Write) || (paramDirection == Direction.ReadWrite)) {
      localOrcaClientChannel.encoder = LabCommEncoder.Factory.newChannel(localSocket.getOutputStream(), this.version);
    }
    Utils.writeInformation("Orca: Connected");
    localServerSocket.close();
    return localOrcaClientChannel;
  }
  
  private static enum Select
  {
    Input,  Output,  Parameter,  Log;
    
    private Select() {}
  }
  
  private static enum Direction
  {
    Read,  Write,  ReadWrite;
    
    private Direction() {}
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/orca/OrcaClient.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */