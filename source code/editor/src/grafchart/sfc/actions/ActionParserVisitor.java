package grafchart.sfc.actions;

public abstract interface ActionParserVisitor
{
  public abstract Object visit(SimpleNode paramSimpleNode, Object paramObject);
  
  public abstract Object visit(ProcParam paramProcParam, Object paramObject);
  
  public abstract Object visit(List paramList, Object paramObject);
  
  public abstract Object visit(CallByValue paramCallByValue, Object paramObject);
  
  public abstract Object visit(CallByReference paramCallByReference, Object paramObject);
  
  public abstract Object visit(ProcCall paramProcCall, Object paramObject);
  
  public abstract Object visit(Statement paramStatement, Object paramObject);
  
  public abstract Object visit(NormalAction paramNormalAction, Object paramObject);
  
  public abstract Object visit(PeriodicAction paramPeriodicAction, Object paramObject);
  
  public abstract Object visit(AbortiveAction paramAbortiveAction, Object paramObject);
  
  public abstract Object visit(StoredAction paramStoredAction, Object paramObject);
  
  public abstract Object visit(ExitAction paramExitAction, Object paramObject);
  
  public abstract Object visit(Assignment paramAssignment, Object paramObject);
  
  public abstract Object visit(CallStmt paramCallStmt, Object paramObject);
  
  public abstract Object visit(SingleExpression paramSingleExpression, Object paramObject);
  
  public abstract Object visit(ConditionalExpression paramConditionalExpression, Object paramObject);
  
  public abstract Object visit(Or paramOr, Object paramObject);
  
  public abstract Object visit(And paramAnd, Object paramObject);
  
  public abstract Object visit(EQ paramEQ, Object paramObject);
  
  public abstract Object visit(NEQ paramNEQ, Object paramObject);
  
  public abstract Object visit(LT paramLT, Object paramObject);
  
  public abstract Object visit(GT paramGT, Object paramObject);
  
  public abstract Object visit(LE paramLE, Object paramObject);
  
  public abstract Object visit(GE paramGE, Object paramObject);
  
  public abstract Object visit(Plus paramPlus, Object paramObject);
  
  public abstract Object visit(Minus paramMinus, Object paramObject);
  
  public abstract Object visit(Mult paramMult, Object paramObject);
  
  public abstract Object visit(Div paramDiv, Object paramObject);
  
  public abstract Object visit(Mod paramMod, Object paramObject);
  
  public abstract Object visit(Negation paramNegation, Object paramObject);
  
  public abstract Object visit(Not paramNot, Object paramObject);
  
  public abstract Object visit(Number paramNumber, Object paramObject);
  
  public abstract Object visit(Reference paramReference, Object paramObject);
  
  public abstract Object visit(Var paramVar, Object paramObject);
  
  public abstract Object visit(StringExpr paramStringExpr, Object paramObject);
  
  public abstract Object visit(Function paramFunction, Object paramObject);
  
  public abstract Object visit(Dot paramDot, Object paramObject);
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ActionParserVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */