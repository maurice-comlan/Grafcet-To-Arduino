package grafchart.sfc.builtin;

import grafchart.sfc.DPWSObject;
import grafchart.sfc.actions.Expr;

public class FuncDPWSGetFault
  extends AbstractExecutable
{
  public FuncDPWSGetFault()
  {
    super("dpwsGetFault", 1, BuiltInFunctions.Language.Action);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    DPWSObject localDPWSObject = (DPWSObject)((Expr)paramArrayOfEvaluatable[0]).runtimeDecl();
    String str = localDPWSObject.getFault();
    if ((str == null) && (!localDPWSObject.isValidPortType(this, false))) {
      return "Device is unavailable.";
    }
    if (str == null)
    {
      runtimeError("No faults available");
      return "";
    }
    return str;
  }
  
  public boolean isValidCall(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    if ((paramArrayOfEvaluatable[0] instanceof Expr))
    {
      Expr localExpr = (Expr)paramArrayOfEvaluatable[0];
      if ((localExpr.runtimeDecl() instanceof DPWSObject)) {
        return true;
      }
    }
    runtimeError("First argument to \"" + getName() + "\" must be a DPWSObject: " + paramArrayOfEvaluatable[0]);
    return false;
  }
  
  public String compile(Evaluatable[] paramArrayOfEvaluatable, Object paramObject, BuiltInFunctions.Language paramLanguage)
  {
    String str = super.compile(paramArrayOfEvaluatable, paramObject, paramLanguage);
    if (str == null)
    {
      str = "First argument to \"" + getName() + "\" must be a DPWSObject: " + paramArrayOfEvaluatable[0];
      if ((paramArrayOfEvaluatable[0] instanceof Expr))
      {
        Expr localExpr = (Expr)paramArrayOfEvaluatable[0];
        if ((localExpr.hasReferences()) || (localExpr.declMissing()) || ((localExpr.staticDecl() instanceof DPWSObject))) {
          str = null;
        }
      }
    }
    return str;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncDPWSGetFault.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */