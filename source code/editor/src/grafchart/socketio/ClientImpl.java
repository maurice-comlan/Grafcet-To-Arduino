package grafchart.socketio;

import grafchart.sfc.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientImpl
  extends Thread
  implements Client
{
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;
  private ClientListener clientListener;
  private JGSocketReceiver jgSocketReceiver;
  private boolean disconnectRequested;
  
  public ClientImpl(Socket paramSocket, ClientListener paramClientListener, JGSocketReceiver paramJGSocketReceiver)
    throws IOException
  {
    this.out = new PrintWriter(paramSocket.getOutputStream(), true);
    this.in = new BufferedReader(new InputStreamReader(paramSocket.getInputStream()));
    this.socket = paramSocket;
    this.clientListener = paramClientListener;
    this.jgSocketReceiver = paramJGSocketReceiver;
    this.disconnectRequested = false;
    if (paramJGSocketReceiver != null) {
      start();
    }
    paramClientListener.clientConnected(this);
  }
  
  public void run()
  {
    try
    {
      String str = null;
      while ((str = this.in.readLine()) != null) {
        this.jgSocketReceiver.receive(this, SocketProtocol.getTag(str), SocketProtocol.getValue(str));
      }
      Utils.writeInformation("Socket client closed connection.");
      disconnect();
    }
    catch (IOException localIOException)
    {
      if (!this.disconnectRequested)
      {
        Utils.writeException(localIOException);
        disconnect();
      }
    }
    Utils.writeInformation("Socket client disconnected.");
  }
  
  public void send(String paramString1, String paramString2)
  {
    if (this.out != null) {
      this.out.println(SocketProtocol.create(paramString1, paramString2));
    } else {
      Utils.writeWarning("Not connected. Ignoring: " + paramString1 + " :: " + paramString2);
    }
  }
  
  public void disconnect()
  {
    this.clientListener.clientDisconnected(this);
    this.disconnectRequested = true;
    try
    {
      this.socket.close();
    }
    catch (IOException localIOException) {}
  }
  
  public Socket getSocket()
  {
    if (this.jgSocketReceiver == null) {
      return this.socket;
    }
    Utils.writeInternalError("getSocket() called for ClientImpl with receiver.");
    return null;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/socketio/ClientImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */