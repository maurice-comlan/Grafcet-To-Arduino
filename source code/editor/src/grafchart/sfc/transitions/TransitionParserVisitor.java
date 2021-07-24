package grafchart.sfc.transitions;

public abstract interface TransitionParserVisitor
{
  public abstract Object visit(SimpleNode paramSimpleNode, Object paramObject);
  
  public abstract Object visit(Start paramStart, Object paramObject);
  
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
  
  public abstract Object visit(EventUp paramEventUp, Object paramObject);
  
  public abstract Object visit(EventDown paramEventDown, Object paramObject);
  
  public abstract Object visit(Number paramNumber, Object paramObject);
  
  public abstract Object visit(Reference paramReference, Object paramObject);
  
  public abstract Object visit(Var paramVar, Object paramObject);
  
  public abstract Object visit(StringExpr paramStringExpr, Object paramObject);
  
  public abstract Object visit(Function paramFunction, Object paramObject);
  
  public abstract Object visit(List paramList, Object paramObject);
  
  public abstract Object visit(Dot paramDot, Object paramObject);
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/TransitionParserVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */