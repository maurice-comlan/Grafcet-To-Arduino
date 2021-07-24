package grafchart.util;

public class CompilationMessage
  implements Comparable
{
  private Kind kind;
  private String message;
  
  public CompilationMessage(String paramString, Kind paramKind)
  {
    this.message = paramString;
    this.kind = paramKind;
  }
  
  public boolean isError()
  {
    return this.kind == Kind.eError;
  }
  
  public boolean isWarning()
  {
    return this.kind == Kind.eWarning;
  }
  
  public String toString()
  {
    String str = "";
    if (this.kind == Kind.eWarning) {
      str = str + "Warning";
    } else {
      str = str + "Error";
    }
    str = str + ": " + this.message;
    return str;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public int compareTo(Object paramObject)
  {
    CompilationMessage localCompilationMessage = (CompilationMessage)paramObject;
    return this.message.compareToIgnoreCase(localCompilationMessage.message);
  }
  
  public static enum Kind
  {
    eError,  eWarning;
    
    private Kind() {}
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/util/CompilationMessage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */