package grafchart.sfc.transitions;

public abstract interface TransitionParserTreeConstants
{
  public static final int JJTSTART = 0;
  public static final int JJTVOID = 1;
  public static final int JJTCONDITIONALEXPRESSION = 2;
  public static final int JJTOR = 3;
  public static final int JJTAND = 4;
  public static final int JJTEQ = 5;
  public static final int JJTNEQ = 6;
  public static final int JJTLT = 7;
  public static final int JJTGT = 8;
  public static final int JJTLE = 9;
  public static final int JJTGE = 10;
  public static final int JJTPLUS = 11;
  public static final int JJTMINUS = 12;
  public static final int JJTMULT = 13;
  public static final int JJTDIV = 14;
  public static final int JJTMOD = 15;
  public static final int JJTNEGATION = 16;
  public static final int JJTNOT = 17;
  public static final int JJTEVENTUP = 18;
  public static final int JJTEVENTDOWN = 19;
  public static final int JJTNUMBER = 20;
  public static final int JJTREFERENCE = 21;
  public static final int JJTVAR = 22;
  public static final int JJTSTRINGEXPR = 23;
  public static final int JJTFUNCTION = 24;
  public static final int JJTLIST = 25;
  public static final int JJTDOT = 26;
  public static final String[] jjtNodeName = { "Start", "void", "ConditionalExpression", "Or", "And", "EQ", "NEQ", "LT", "GT", "LE", "GE", "Plus", "Minus", "Mult", "Div", "Mod", "Negation", "Not", "EventUp", "EventDown", "Number", "Reference", "Var", "StringExpr", "Function", "List", "Dot" };
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/TransitionParserTreeConstants.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */