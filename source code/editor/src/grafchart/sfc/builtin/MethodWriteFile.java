package grafchart.sfc.builtin;

import grafchart.sfc.Browser;

public class MethodWriteFile
  extends AbstractExecutable
{
  public MethodWriteFile()
  {
    super("writeFile", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Browser localBrowser = (Browser)paramObject;
    localBrowser.writeFile(paramArrayOfEvaluatable[0].evaluateString());
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof Browser;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodWriteFile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */