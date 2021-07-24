package grafchart.sfc.builtin;

import grafchart.dpws.db.DPWSPortType;
import grafchart.sfc.DPWSObject;
import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import grafchart.sfc.actions.Expr;

public class FuncDPWSUnsubscribe
  extends AbstractExecutable
{
  public FuncDPWSUnsubscribe()
  {
    super("dpwsUnsubscribe", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    DPWSObject localDPWSObject = (DPWSObject)((Expr)paramArrayOfEvaluatable[0]).runtimeDecl();
    if (!localDPWSObject.isValidPortType(this)) {
      return 0.0D;
    }
    if (!localDPWSObject.getPortType().isEventSource())
    {
      runtimeError("The porttype does not support eventing.");
      return 0.0D;
    }
    localDPWSObject.unsubscribe();
    return 0.0D;
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
      if (!Editor.singleton.getCurrentView().getDoc().isDPWSEventingConfigured()) {
        return "DPWS Eventing has not been configured for this application.";
      }
      str = "First argument to \"" + getName() + "\" must be a DPWSObject: " + paramArrayOfEvaluatable[0];
      if ((paramArrayOfEvaluatable[0] instanceof Expr))
      {
        Expr localExpr = (Expr)paramArrayOfEvaluatable[0];
        if ((localExpr.hasReferences()) || (localExpr.declMissing()))
        {
          str = null;
        }
        else if ((localExpr.staticDecl() instanceof DPWSObject))
        {
          DPWSObject localDPWSObject = (DPWSObject)localExpr.staticDecl();
          DPWSPortType localDPWSPortType = localDPWSObject.getPortType();
          if ((localDPWSPortType != null) && (!localDPWSPortType.isEventSource())) {
            str = "First argument to \"" + getName() + "\" has a bound porttype that does not support eventing: " + localDPWSObject.getName();
          } else {
            str = null;
          }
        }
      }
    }
    return str;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncDPWSUnsubscribe.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */