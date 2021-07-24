package se.lth.control.labcomm;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import se.lth.control.labcomm.api.LabCommDispatcher;
import se.lth.control.labcomm.api.LabCommHandler;

public class LabCommDecoderRegistry
{
  private HashMap<String, Entry> byIdentifier = new HashMap();
  private HashMap<Integer, Entry> byIndex = new HashMap();
  
  public synchronized void add(LabCommDispatcher paramLabCommDispatcher, LabCommHandler paramLabCommHandler)
    throws IOException
  {
    Object localObject = (Entry)this.byIdentifier.get(paramLabCommDispatcher.getSampleIdentifier());
    if (localObject != null)
    {
      ((Entry)localObject).check(paramLabCommDispatcher.getName(), paramLabCommDispatcher.getSignature());
      ((Entry)localObject).setHandler(paramLabCommHandler);
    }
    else
    {
      Iterator localIterator = this.byIndex.values().iterator();
      while (localIterator.hasNext())
      {
        Entry localEntry = (Entry)localIterator.next();
        if (localEntry.match(paramLabCommDispatcher.getName(), paramLabCommDispatcher.getSignature()))
        {
          localEntry.setDispatcher(paramLabCommDispatcher);
          localEntry.setHandler(paramLabCommHandler);
          localObject = localEntry;
          break;
        }
      }
      if (localObject == null)
      {
        localObject = new Entry(paramLabCommDispatcher, paramLabCommHandler);
        this.byIdentifier.put(paramLabCommDispatcher.getSampleIdentifier(), (Entry) localObject);
      }
    }
  }
  
  public synchronized void add(int paramInt, String paramString, byte[] paramArrayOfByte)
    throws IOException
  {
    Object localObject = (Entry)this.byIndex.get(Integer.valueOf(paramInt));
    if (localObject != null)
    {
      ((Entry)localObject).check(paramString, paramArrayOfByte);
    }
    else
    {
      Iterator localIterator = this.byIdentifier.values().iterator();
      while (localIterator.hasNext())
      {
        Entry localEntry = (Entry)localIterator.next();
        if (localEntry.match(paramString, paramArrayOfByte))
        {
          localEntry.setIndex(paramInt);
          localObject = localEntry;
          break;
        }
      }
      if (localObject == null) {
        localObject = new Entry(paramInt, paramString, paramArrayOfByte);
      }
      this.byIndex.put(Integer.valueOf(paramInt), (Entry) localObject);
    }
  }
  
  public synchronized Entry get(int paramInt)
  {
    return (Entry)this.byIndex.get(Integer.valueOf(paramInt));
  }
  
  public static class Entry
  {
    private LabCommDispatcher dispatcher;
    private LabCommHandler handler;
    private int index;
    private String name;
    private byte[] signature;
    
    public Entry(LabCommDispatcher paramLabCommDispatcher, LabCommHandler paramLabCommHandler)
    {
      this.dispatcher = paramLabCommDispatcher;
      this.name = paramLabCommDispatcher.getName();
      this.signature = paramLabCommDispatcher.getSignature();
      this.handler = paramLabCommHandler;
    }
    
    public Entry(int paramInt, String paramString, byte[] paramArrayOfByte)
    {
      this.index = paramInt;
      this.name = paramString;
      this.signature = paramArrayOfByte;
    }
    
    public LabCommDispatcher getDispatcher()
    {
      return this.dispatcher;
    }
    
    public void setDispatcher(LabCommDispatcher paramLabCommDispatcher)
    {
      this.dispatcher = paramLabCommDispatcher;
    }
    
    public LabCommHandler getHandler()
    {
      return this.handler;
    }
    
    public void setHandler(LabCommHandler paramLabCommHandler)
    {
      this.handler = paramLabCommHandler;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public int getIndex()
    {
      return this.index;
    }
    
    public void setIndex(int paramInt)
      throws IOException
    {
      if ((this.index != 0) && (this.index != paramInt)) {
        throw new IOException("Index mismatch " + this.index + " != " + paramInt);
      }
      this.index = paramInt;
    }
    
    public boolean matchName(String paramString)
    {
      return this.name.equals(paramString);
    }
    
    public boolean matchSignature(byte[] paramArrayOfByte)
    {
      boolean bool = this.signature.length == paramArrayOfByte.length;
      for (int i = 0; (bool) && (i < paramArrayOfByte.length); i++) {
        bool = this.signature[i] == paramArrayOfByte[i];
      }
      return bool;
    }
    
    public boolean match(String paramString, byte[] paramArrayOfByte)
    {
      return (matchName(paramString)) && (matchSignature(paramArrayOfByte));
    }
    
    public void check(String paramString, byte[] paramArrayOfByte)
      throws IOException
    {
      if (!matchName(paramString)) {
        throw new IOException("Name mismatch '" + this.name + "' != '" + paramString + "'");
      }
      if (!matchSignature(paramArrayOfByte)) {
        throw new IOException("Signature mismatch");
      }
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/se/lth/control/labcomm/LabCommDecoderRegistry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */