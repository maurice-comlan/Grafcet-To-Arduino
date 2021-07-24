package AST;

public class SignatureLine
{
  private int indent;
  private byte[] data;
  private String comment;
  
  public SignatureLine(int paramInt, byte[] paramArrayOfByte, String paramString)
  {
    this.indent = paramInt;
    this.data = paramArrayOfByte;
    this.comment = paramString;
  }
  
  public int getIndent()
  {
    return this.indent;
  }
  
  public byte[] getData()
  {
    return this.data;
  }
  
  public String getComment()
  {
    return this.comment;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/SignatureLine.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */