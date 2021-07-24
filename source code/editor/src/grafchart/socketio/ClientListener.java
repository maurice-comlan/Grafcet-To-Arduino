package grafchart.socketio;

public abstract interface ClientListener
{
  public abstract void clientConnected(Client paramClient);
  
  public abstract void clientDisconnected(Client paramClient);
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/socketio/ClientListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */