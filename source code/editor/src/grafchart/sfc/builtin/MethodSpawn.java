package grafchart.sfc.builtin;

import grafchart.sfc.GrafcetProcedure;
import grafchart.sfc.SymbolTableObject;
import grafchart.sfc.actions.ASTNode;
import grafchart.sfc.actions.Goal;

public class MethodSpawn
  extends AbstractExecutable
{
  public MethodSpawn()
  {
    super("spawn", 1, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    GrafcetProcedure localGrafcetProcedure = (GrafcetProcedure)paramObject;
    ASTNode localASTNode = (ASTNode)paramArrayOfEvaluatable[0];
    SymbolTableObject localSymbolTableObject = localASTNode.root().symbolTable;
    localGrafcetProcedure.start(paramArrayOfEvaluatable[0].evaluateString(), localSymbolTableObject);
    return 0.0D;
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject instanceof GrafcetProcedure;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/MethodSpawn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */