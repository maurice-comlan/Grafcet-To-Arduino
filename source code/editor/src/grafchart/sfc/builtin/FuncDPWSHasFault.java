package grafchart.sfc.builtin;

import grafchart.sfc.DPWSObject;

public class FuncDPWSHasFault
  extends AbstractExecutable
{
  public FuncDPWSHasFault()
  {
    super("dpwsHasFault", 1);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    DPWSObject localDPWSObject = null;
    if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.actions.Expr)) {
      localDPWSObject = (DPWSObject)((grafchart.sfc.actions.Expr)paramArrayOfEvaluatable[0]).runtimeDecl();
    } else {
      localDPWSObject = (DPWSObject)((grafchart.sfc.transitions.Expr)paramArrayOfEvaluatable[0]).runtimeDecl();
    }
    boolean bool = localDPWSObject.hasFault();
    if ((!bool) && (!localDPWSObject.isValidPortType(this, false))) {
      bool = true;
    }
    return bool;
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return internalExecuteBool(paramArrayOfEvaluatable, paramObject) ? 1.0D : 0.0D;
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return internalExecuteBool(paramArrayOfEvaluatable, paramObject) ? "1" : "0";
  }
  
  public boolean isValidCall(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Object localObject;
    if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.actions.Expr))
    {
      localObject = (grafchart.sfc.actions.Expr)paramArrayOfEvaluatable[0];
      if ((((grafchart.sfc.actions.Expr)localObject).runtimeDecl() instanceof DPWSObject)) {
        return true;
      }
    }
    else if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.transitions.Expr))
    {
      localObject = (grafchart.sfc.transitions.Expr)paramArrayOfEvaluatable[0];
      if ((((grafchart.sfc.transitions.Expr)localObject).runtimeDecl() instanceof DPWSObject)) {
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
      DPWSObject localDPWSObject = null;
      Object localObject;
      if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.actions.Expr))
      {
        localObject = (grafchart.sfc.actions.Expr)paramArrayOfEvaluatable[0];
        if ((((grafchart.sfc.actions.Expr)localObject).hasReferences()) || (((grafchart.sfc.actions.Expr)localObject).declMissing())) {
          str = null;
        } else if ((((grafchart.sfc.actions.Expr)localObject).staticDecl() instanceof DPWSObject)) {
          localDPWSObject = (DPWSObject)((grafchart.sfc.actions.Expr)localObject).staticDecl();
        }
      }
      else if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.transitions.Expr))
      {
        localObject = (grafchart.sfc.transitions.Expr)paramArrayOfEvaluatable[0];
        if ((((grafchart.sfc.transitions.Expr)localObject).hasReferences()) || (((grafchart.sfc.transitions.Expr)localObject).declMissing())) {
          str = null;
        } else if ((((grafchart.sfc.transitions.Expr)localObject).staticDecl() instanceof DPWSObject)) {
          localDPWSObject = (DPWSObject)((grafchart.sfc.transitions.Expr)localObject).staticDecl();
        }
      }
      if (localDPWSObject != null) {
        str = null;
      }
    }
    return str;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncDPWSHasFault.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */