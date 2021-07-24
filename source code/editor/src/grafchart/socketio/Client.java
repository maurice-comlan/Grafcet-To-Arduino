package grafchart.socketio;

import java.net.Socket;

public abstract interface Client
{
  public abstract void send(String paramString1, String paramString2);
  
  public abstract void disconnect();
  
  public abstract Socket getSocket();
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/socketio/Client.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */