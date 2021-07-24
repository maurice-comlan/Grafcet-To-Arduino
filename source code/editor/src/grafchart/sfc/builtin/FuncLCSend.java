package grafchart.sfc.builtin;

import grafchart.sfc.LabCommObject;
import grafchart.sfc.actions.Expr;

public class FuncLCSend
  extends AbstractExecutable
{
  public FuncLCSend()
  {
    super("lcSend", -1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    LabCommObject localLabCommObject = (LabCommObject)((Expr)paramArrayOfEvaluatable[0]).runtimeDecl();
    String str = null;
    if (paramArrayOfEvaluatable.length > 1) {
      str = paramArrayOfEvaluatable[1].evaluateString();
    }
    localLabCommObject.send(str);
    return 0.0D;
  }
  
  public boolean isValidCall(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    if ((paramArrayOfEvaluatable[0] instanceof Expr))
    {
      Expr localExpr = (Expr)paramArrayOfEvaluatable[0];
      if ((localExpr.runtimeDecl() instanceof LabCommObject)) {
        return true;
      }
    }
    runtimeError("First argument to \"" + getName() + "\" must be a LabCommObject: " + paramArrayOfEvaluatable[0]);
    return false;
  }
  
  public String compile(Evaluatable[] paramArrayOfEvaluatable, Object paramObject, BuiltInFunctions.Language paramLanguage)
  {
    String str = super.compile(paramArrayOfEvaluatable, paramObject, paramLanguage);
    if (str == null)
    {
      str = "First argument to \"" + getName() + "\" must be a LabCommObject: " + paramArrayOfEvaluatable[0];
      if ((paramArrayOfEvaluatable[0] instanceof Expr))
      {
        Expr localExpr = (Expr)paramArrayOfEvaluatable[0];
        if ((localExpr.hasReferences()) || (localExpr.declMissing()))
        {
          str = null;
        }
        else if ((localExpr.staticDecl() instanceof LabCommObject))
        {
          LabCommObject localLabCommObject = (LabCommObject)localExpr.staticDecl();
          str = "LabComm Object is not an output: \"" + localLabCommObject.getFullName() + "\"";
          if (localLabCommObject.isOutput()) {
            str = null;
          }
        }
      }
    }
    return str;
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    return (paramInt == 1) || (paramInt == 2);
  }
  
  public String getCorrectNrOfArguments()
  {
    return "1 or 2";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncLCSend.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */