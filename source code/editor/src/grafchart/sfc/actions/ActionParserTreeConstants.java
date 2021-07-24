package grafchart.sfc.actions;

public abstract interface ActionParserTreeConstants
{
  public static final int JJTPROCPARAM = 0;
  public static final int JJTLIST = 1;
  public static final int JJTVOID = 2;
  public static final int JJTCALLBYVALUE = 3;
  public static final int JJTCALLBYREFERENCE = 4;
  public static final int JJTPROCCALL = 5;
  public static final int JJTSTATEMENT = 6;
  public static final int JJTNORMALACTION = 7;
  public static final int JJTPERIODICACTION = 8;
  public static final int JJTABORTIVEACTION = 9;
  public static final int JJTSTOREDACTION = 10;
  public static final int JJTEXITACTION = 11;
  public static final int JJTASSIGNMENT = 12;
  public static final int JJTCALLSTMT = 13;
  public static final int JJTSINGLEEXPRESSION = 14;
  public static final int JJTCONDITIONALEXPRESSION = 15;
  public static final int JJTOR = 16;
  public static final int JJTAND = 17;
  public static final int JJTEQ = 18;
  public static final int JJTNEQ = 19;
  public static final int JJTLT = 20;
  public static final int JJTGT = 21;
  public static final int JJTLE = 22;
  public static final int JJTGE = 23;
  public static final int JJTPLUS = 24;
  public static final int JJTMINUS = 25;
  public static final int JJTMULT = 26;
  public static final int JJTDIV = 27;
  public static final int JJTMOD = 28;
  public static final int JJTNEGATION = 29;
  public static final int JJTNOT = 30;
  public static final int JJTNUMBER = 31;
  public static final int JJTREFERENCE = 32;
  public static final int JJTVAR = 33;
  public static final int JJTSTRINGEXPR = 34;
  public static final int JJTFUNCTION = 35;
  public static final int JJTDOT = 36;
  public static final String[] jjtNodeName = { "ProcParam", "List", "void", "CallByValue", "CallByReference", "ProcCall", "Statement", "NormalAction", "PeriodicAction", "AbortiveAction", "StoredAction", "ExitAction", "Assignment", "CallStmt", "SingleExpression", "ConditionalExpression", "Or", "And", "EQ", "NEQ", "LT", "GT", "LE", "GE", "Plus", "Minus", "Mult", "Div", "Mod", "Negation", "Not", "Number", "Reference", "Var", "StringExpr", "Function", "Dot" };
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ActionParserTreeConstants.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */