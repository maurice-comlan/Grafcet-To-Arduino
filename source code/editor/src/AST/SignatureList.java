package AST;

import java.util.ArrayList;

public class SignatureList
{
  private int indent;
  private final int ver;
  private ArrayList list = new ArrayList();
  
  public SignatureList(int paramInt)
  {
    this.ver = paramInt;
  }
  
  public void add(byte[] paramArrayOfByte, String paramString)
  {
    this.list.add(new SignatureLine(this.indent, paramArrayOfByte, paramString));
  }
  
  public void addInt(int paramInt, String paramString)
  {
    switch (this.ver)
    {
    case 2006: 
      byte[] arrayOfByte1 = new byte[4];
      for (int i = 0; i < 4; i++) {
        arrayOfByte1[(3 - i)] = ((byte)(paramInt >> 8 * i & 0xFF));
      }
      add(arrayOfByte1, paramString);
      break;
    case 2013: 
      byte[] arrayOfByte2 = new byte[5];
      long l = paramInt & 0xFFFFFFFF;
      int j = 0;
      while ((j == 0) || (l != 0L))
      {
        arrayOfByte2[j] = ((byte)(int)(l & 0x7F));
        j++;
        l >>= 7;
      }
      byte[] arrayOfByte3 = new byte[j];
      j -= 1;
      for (int k = 0; j >= 0; k++)
      {
        arrayOfByte3[k] = ((byte)(arrayOfByte2[j] | (j != 0 ? '' : 0)));
        j--;
      }
      add(arrayOfByte3, paramString);
      break;
    default: 
      throw new RuntimeException("Version = " + this.ver + ". This should never happen.");
    }
  }
  
  public void addString(String paramString1, String paramString2)
  {
    addInt(paramString1.length(), paramString2);
    byte[] arrayOfByte = new byte[paramString1.length()];
    for (int i = 0; i < paramString1.length(); i++) {
      arrayOfByte[i] = ((byte)(paramString1.charAt(i) & 0xFF));
    }
    add(arrayOfByte, null);
  }
  
  public int size()
  {
    return this.list.size();
  }
  
  public String getIndent(int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = ((SignatureLine)this.list.get(paramInt)).getIndent();
    for (paramInt = 0; paramInt < i; paramInt++) {
      localStringBuffer.append("  ");
    }
    return localStringBuffer.toString();
  }
  
  public byte[] getData(int paramInt)
  {
    return ((SignatureLine)this.list.get(paramInt)).getData();
  }
  
  public String getComment(int paramInt)
  {
    return ((SignatureLine)this.list.get(paramInt)).getComment();
  }
  
  public void indent()
  {
    this.indent += 1;
  }
  
  public void unindent()
  {
    this.indent -= 1;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/SignatureList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */