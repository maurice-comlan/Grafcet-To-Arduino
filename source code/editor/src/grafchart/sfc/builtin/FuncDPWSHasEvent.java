package grafchart.sfc.builtin;

import grafchart.dpws.db.DPWSOperation;
import grafchart.dpws.db.DPWSPortType;
import grafchart.sfc.DPWSObject;
import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCView;
import javax.wsdl.OperationType;

public class FuncDPWSHasEvent
  extends AbstractExecutable
{
  public FuncDPWSHasEvent()
  {
    super("dpwsHasEvent", 2);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    DPWSObject localDPWSObject = null;
    if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.actions.Expr)) {
      localDPWSObject = (DPWSObject)((grafchart.sfc.actions.Expr)paramArrayOfEvaluatable[0]).runtimeDecl();
    } else {
      localDPWSObject = (DPWSObject)((grafchart.sfc.transitions.Expr)paramArrayOfEvaluatable[0]).runtimeDecl();
    }
    if (!localDPWSObject.isValidPortType(this)) {
      return false;
    }
    DPWSPortType localDPWSPortType = localDPWSObject.getPortType();
    if (!localDPWSPortType.isEventSource())
    {
      runtimeError("The porttype does not support eventing.");
      return false;
    }
    String str = paramArrayOfEvaluatable[1].evaluateString();
    DPWSOperation localDPWSOperation = localDPWSPortType.getOperation(str);
    if (localDPWSOperation == null)
    {
      runtimeError("Illegal operation name \"" + str + "\"");
      return false;
    }
    if (localDPWSOperation.getOperationType() != OperationType.NOTIFICATION)
    {
      runtimeError("Illegal operation type for \"" + str + "\"");
      return false;
    }
    boolean bool = localDPWSObject.hasEvent(localDPWSOperation.getOutputAction());
    return bool;
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return internalExecuteBool(paramArrayOfEvaluatable, paramObject) ? 1.0D : 0.0D;
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return internalExecuteBool(paramArrayOfEvaluatable, paramObject) ? "1.0" : "0.0";
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
    String str1 = super.compile(paramArrayOfEvaluatable, paramObject, paramLanguage);
    if (str1 == null)
    {
      if (!Editor.singleton.getCurrentView().getDoc().isDPWSEventingConfigured()) {
        return "DPWS Eventing has not been configured for this application.";
      }
      str1 = "First argument to \"" + getName() + "\" must be a DPWSObject: " + paramArrayOfEvaluatable[0];
      DPWSObject localDPWSObject = null;
      Object localObject;
      if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.actions.Expr))
      {
        localObject = (grafchart.sfc.actions.Expr)paramArrayOfEvaluatable[0];
        if ((((grafchart.sfc.actions.Expr)localObject).hasReferences()) || (((grafchart.sfc.actions.Expr)localObject).declMissing())) {
          str1 = null;
        } else if ((((grafchart.sfc.actions.Expr)localObject).staticDecl() instanceof DPWSObject)) {
          localDPWSObject = (DPWSObject)((grafchart.sfc.actions.Expr)localObject).staticDecl();
        }
      }
      else if ((paramArrayOfEvaluatable[0] instanceof grafchart.sfc.transitions.Expr))
      {
        localObject = (grafchart.sfc.transitions.Expr)paramArrayOfEvaluatable[0];
        if ((((grafchart.sfc.transitions.Expr)localObject).hasReferences()) || (((grafchart.sfc.transitions.Expr)localObject).declMissing())) {
          str1 = null;
        } else if ((((grafchart.sfc.transitions.Expr)localObject).staticDecl() instanceof DPWSObject)) {
          localDPWSObject = (DPWSObject)((grafchart.sfc.transitions.Expr)localObject).staticDecl();
        }
      }
      if (localDPWSObject != null)
      {
        localObject = localDPWSObject.getPortType();
        if ((localObject != null) && (!((DPWSPortType)localObject).isEventSource()))
        {
          str1 = "First argument to \"" + getName() + "\" has a bound porttype that does not support eventing: " + localDPWSObject.getName();
        }
        else
        {
          str1 = null;
          if (localObject != null)
          {
            String str2 = null;
            if ((paramArrayOfEvaluatable[1] instanceof grafchart.sfc.actions.StringExpr)) {
              str2 = ((grafchart.sfc.actions.StringExpr)paramArrayOfEvaluatable[1]).getVALUE();
            } else if ((paramArrayOfEvaluatable[1] instanceof grafchart.sfc.transitions.StringExpr)) {
              str2 = ((grafchart.sfc.transitions.StringExpr)paramArrayOfEvaluatable[1]).getVALUE();
            }
            if (str2 != null)
            {
              DPWSOperation localDPWSOperation = ((DPWSPortType)localObject).getOperation(str2);
              if (localDPWSOperation == null) {
                str1 = "Illegal operation name to \"" + getName() + "\": " + str2;
              } else if (localDPWSOperation.getOperationType() != OperationType.NOTIFICATION) {
                str1 = "Illegal operation type for \"" + getName() + "\": " + str2;
              }
            }
          }
        }
      }
    }
    return str1;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncDPWSHasEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */