package se.lth.control.labcomm2006;

import java.io.IOException;
import java.util.HashMap;
import se.lth.control.labcomm.api.LabCommDispatcher;

public class LabCommEncoderRegistry
{
  private int userIndex = 128;
  private HashMap<String, Entry> byIdentifier = new HashMap();
  
  public synchronized int add(LabCommDispatcher paramLabCommDispatcher)
  {
    Entry localEntry = (Entry)this.byIdentifier.get(paramLabCommDispatcher.getSampleIdentifier());
    if (localEntry == null)
    {
      localEntry = new Entry(paramLabCommDispatcher, this.userIndex);
      this.byIdentifier.put(paramLabCommDispatcher.getSampleIdentifier(), localEntry);
      this.userIndex += 1;
    }
    return localEntry.getIndex();
  }
  
  public int getTag(String paramString)
    throws IOException
  {
    Entry localEntry = (Entry)this.byIdentifier.get(paramString);
    if (localEntry == null) {
      throw new IOException("Sample '" + paramString + "' is not registered");
    }
    return localEntry.index;
  }
  
  public static class Entry
  {
    private LabCommDispatcher dispatcher;
    private int index;
    
    public Entry(LabCommDispatcher paramLabCommDispatcher, int paramInt)
    {
      this.dispatcher = paramLabCommDispatcher;
      this.index = paramInt;
    }
    
    public LabCommDispatcher getDispatcher()
    {
      return this.dispatcher;
    }
    
    public int getIndex()
    {
      return this.index;
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/labcomm2006/LabCommEncoderRegistry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */