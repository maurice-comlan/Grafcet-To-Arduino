package grafchart.sfc.builtin;

import grafchart.sfc.LabCommObject;

public class FuncLCReceived
  extends AbstractExecutable
{
  public FuncLCReceived()
  {
    super("lcReceived", 1);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    LabCommObject localLabCommObject;
    if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.actions.Expr)) {
      localLabCommObject = (LabCommObject)((grafchart.sfc.actions.Expr)paramArrayOfEvaluatable[0]).runtimeDecl();
    } else {
      localLabCommObject = (LabCommObject)((grafchart.sfc.transitions.Expr)paramArrayOfEvaluatable[0]).runtimeDecl();
    }
    return localLabCommObject.hasReceived();
  }
  
  public boolean isValidCall(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Object localObject;
    if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.actions.Expr))
    {
      localObject = (grafchart.sfc.actions.Expr)paramArrayOfEvaluatable[0];
      if ((((grafchart.sfc.actions.Expr)localObject).runtimeDecl() instanceof LabCommObject)) {
        return true;
      }
    }
    else if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.transitions.Expr))
    {
      localObject = (grafchart.sfc.transitions.Expr)paramArrayOfEvaluatable[0];
      if ((((grafchart.sfc.transitions.Expr)localObject).runtimeDecl() instanceof LabCommObject)) {
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
      Object localObject;
      LabCommObject localLabCommObject;
      if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.actions.Expr))
      {
        localObject = (grafchart.sfc.actions.Expr)paramArrayOfEvaluatable[0];
        if ((((grafchart.sfc.actions.Expr)localObject).hasReferences()) || (((grafchart.sfc.actions.Expr)localObject).declMissing()))
        {
          str = null;
        }
        else if ((((grafchart.sfc.actions.Expr)localObject).staticDecl() instanceof LabCommObject))
        {
          localLabCommObject = (LabCommObject)((grafchart.sfc.actions.Expr)localObject).staticDecl();
          str = "LabComm Object is not an input: \"" + localLabCommObject.getFullName() + "\"";
          if (localLabCommObject.isInput()) {
            str = null;
          }
        }
      }
      else if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.transitions.Expr))
      {
        localObject = (grafchart.sfc.transitions.Expr)paramArrayOfEvaluatable[0];
        if ((((grafchart.sfc.transitions.Expr)localObject).hasReferences()) || (((grafchart.sfc.transitions.Expr)localObject).declMissing()))
        {
          str = null;
        }
        else if ((((grafchart.sfc.transitions.Expr)localObject).staticDecl() instanceof LabCommObject))
        {
          localLabCommObject = (LabCommObject)((grafchart.sfc.transitions.Expr)localObject).staticDecl();
          str = "LabComm Object is not an input: \"" + localLabCommObject.getFullName() + "\"";
          if (localLabCommObject.isInput()) {
            str = null;
          }
        }
      }
    }
    return str;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncLCReceived.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */