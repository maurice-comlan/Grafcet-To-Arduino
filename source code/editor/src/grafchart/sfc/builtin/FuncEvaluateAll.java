package grafchart.sfc.builtin;

import grafchart.sfc.GCDocument;
import grafchart.sfc.Referencable;
import grafchart.sfc.SocketIn;
import java.io.PrintWriter;

public class FuncEvaluateAll
  extends AbstractExecutable
{
  public FuncEvaluateAll()
  {
    super("evaluateAll", 0);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    SocketIn localSocketIn = convertArg(paramArrayOfEvaluatable[0]);
    if (localSocketIn != null)
    {
      GCDocument localGCDocument = localSocketIn.getDocument();
      PrintWriter localPrintWriter = localGCDocument.getOutSocket();
      if (localPrintWriter != null)
      {
        String str = "";
        str = "<evaluate>" + getVarText(paramArrayOfEvaluatable) + "</evaluate>";
        localPrintWriter.println(str);
      }
      else
      {
        runtimeWarning("Not connected.");
      }
    }
    return 0.0D;
  }
  
  private String getVarText(Evaluatable[] paramArrayOfEvaluatable)
  {
    String str = "";
    for (int i = 0; i < paramArrayOfEvaluatable.length; i++)
    {
      SocketIn localSocketIn = convertArg(paramArrayOfEvaluatable[i]);
      if (localSocketIn != null) {
        str = str + SocketIn.evaluateOne(localSocketIn);
      }
    }
    return str;
  }
  
  private SocketIn convertArg(Evaluatable paramEvaluatable)
  {
    Referencable localReferencable = null;
    Object localObject;
    if ((paramEvaluatable instanceof grafchart.sfc.actions.Expr))
    {
      localObject = (grafchart.sfc.actions.Expr)paramEvaluatable;
      localReferencable = ((grafchart.sfc.actions.Expr)localObject).runtimeDecl();
    }
    else if ((paramEvaluatable instanceof grafchart.sfc.transitions.Expr))
    {
      localObject = (grafchart.sfc.transitions.Expr)paramEvaluatable;
      localReferencable = ((grafchart.sfc.transitions.Expr)localObject).runtimeDecl();
    }
    else
    {
      runtimeError("Illegal argument: \"" + paramEvaluatable + "\".");
      return null;
    }
    if ((localReferencable instanceof SocketIn)) {
      return (SocketIn)localReferencable;
    }
    runtimeError("Argument is not a SocketIn: \"" + paramEvaluatable + "\".");
    return null;
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    return paramInt > 0;
  }
  
  public String getCorrectNrOfArguments()
  {
    return ">0";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncEvaluateAll.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */