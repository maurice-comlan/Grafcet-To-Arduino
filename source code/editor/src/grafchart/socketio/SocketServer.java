package grafchart.socketio;

import grafchart.sfc.Utils;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
  extends Thread
{
  private ServerSocket server;
  private ClientListener clientListener;
  private JGSocketReceiver jgSocketReceiver;
  private boolean shutdownRequested;
  
  public SocketServer(int paramInt, ClientListener paramClientListener, JGSocketReceiver paramJGSocketReceiver)
    throws IOException
  {
    this.server = new ServerSocket(paramInt);
    this.clientListener = paramClientListener;
    this.jgSocketReceiver = paramJGSocketReceiver;
    this.shutdownRequested = false;
    if (paramClientListener == null) {
      Utils.writeInformation("SocketServer without receiver.");
    }
  }
  
  public void run()
  {
    while (!this.shutdownRequested) {
      try
      {
        Socket localSocket = this.server.accept();
        Utils.writeInformation("Socket client connected: " + localSocket.getRemoteSocketAddress() + " " + localSocket.getLocalSocketAddress());
        new ClientImpl(localSocket, this.clientListener, this.jgSocketReceiver);
      }
      catch (IOException localIOException)
      {
        if (!this.shutdownRequested) {
          Utils.writeException(localIOException);
        }
      }
    }
    Utils.writeInformation("Socket server shut down.");
  }
  
  public void shutdown()
  {
    if (isAlive())
    {
      Utils.writeInformation("Shutting down server...");
      this.shutdownRequested = true;
      try
      {
        this.server.close();
      }
      catch (IOException localIOException) {}
    }
    else
    {
      Utils.writeError("Server not running.");
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/socketio/SocketServer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */