package grafchart.sfc.builtin;

import grafchart.sfc.BasicList;
import grafchart.sfc.BooleanList;
import grafchart.sfc.IntegerList;
import grafchart.sfc.RealList;
import grafchart.sfc.StringList;
import grafchart.sfc.actions.Expr;

public class MethodAppend
  extends AbstractExecutable
{
  public MethodAppend()
  {
    super("append", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    ((BasicList)paramObject).append((BasicList)((Expr)paramArrayOfEvaluatable[0]).runtimeDecl());
    return 0.0D;
  }
  
  public boolean isValidCall(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    String str = "First argument to \"" + getName() + "\" must be a list: " + paramArrayOfEvaluatable[0];
    if ((paramArrayOfEvaluatable[0] instanceof Expr))
    {
      Expr localExpr = (Expr)paramArrayOfEvaluatable[0];
      if ((localExpr.runtimeDecl() instanceof BasicList)) {
        str = isCompatibleTypes(paramObject, localExpr.runtimeDecl());
      }
    }
    if (str == null) {
      return true;
    }
    runtimeError(str);
    return false;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof BasicList;
  }
  
  public String compile(Evaluatable[] paramArrayOfEvaluatable, Object paramObject, BuiltInFunctions.Language paramLanguage)
  {
    String str = super.compile(paramArrayOfEvaluatable, paramObject, paramLanguage);
    if (str == null)
    {
      str = "First argument to \"" + getName() + "\" must be a list but was: " + paramArrayOfEvaluatable[0];
      if ((paramArrayOfEvaluatable[0] instanceof Expr))
      {
        Expr localExpr = (Expr)paramArrayOfEvaluatable[0];
        if ((localExpr.hasReferences()) || (localExpr.declMissing())) {
          str = null;
        } else if ((localExpr.staticDecl() instanceof BasicList)) {
          str = isCompatibleTypes(paramObject, localExpr.staticDecl());
        }
      }
    }
    return str;
  }
  
  private String isCompatibleTypes(Object paramObject1, Object paramObject2)
  {
    String str = "Incompatible types, cannot append " + paramObject2 + " to " + paramObject1 + ".";
    if ((((paramObject1 instanceof RealList)) && ((paramObject2 instanceof RealList))) || (((paramObject1 instanceof StringList)) && ((paramObject2 instanceof StringList))) || (((paramObject1 instanceof IntegerList)) && ((paramObject2 instanceof IntegerList))) || (((paramObject1 instanceof BooleanList)) && ((paramObject2 instanceof BooleanList)))) {
      str = null;
    }
    return str;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodAppend.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */