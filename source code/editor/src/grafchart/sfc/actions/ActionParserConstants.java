package grafchart.sfc.actions;

public abstract interface ActionParserConstants
{
  public static final int EOF = 0;
  public static final int LPAR = 6;
  public static final int RPAR = 7;
  public static final int COMMA = 8;
  public static final int COLON = 9;
  public static final int SEMICOLON = 10;
  public static final int ASSIGN = 11;
  public static final int NOT = 12;
  public static final int AND = 13;
  public static final int OR = 14;
  public static final int MUL = 15;
  public static final int ADD = 16;
  public static final int SUB = 17;
  public static final int DIV = 18;
  public static final int MOD = 19;
  public static final int LE = 20;
  public static final int LT = 21;
  public static final int GE = 22;
  public static final int GT = 23;
  public static final int EQ = 24;
  public static final int NEQ = 25;
  public static final int QM = 26;
  public static final int DOT = 27;
  public static final int REF = 28;
  public static final int N = 29;
  public static final int S = 30;
  public static final int P = 31;
  public static final int A = 32;
  public static final int X = 33;
  public static final int V = 34;
  public static final int R = 35;
  public static final int NUMBER = 36;
  public static final int VAR = 37;
  public static final int LETTER = 38;
  public static final int DIGIT = 39;
  public static final int STRING = 40;
  public static final int ERROR = 41;
  public static final int DEFAULT = 0;
  public static final String[] tokenImage = { "<EOF>", "\" \"", "\"\\n\"", "\"\\t\"", "\"\\r\"", "<token of kind 5>", "\"(\"", "\")\"", "\",\"", "\":\"", "\";\"", "\"=\"", "\"!\"", "\"&\"", "\"|\"", "\"*\"", "\"+\"", "\"-\"", "\"/\"", "\"%\"", "\"<=\"", "\"<\"", "\">=\"", "\">\"", "\"==\"", "\"!=\"", "\"?\"", "\".\"", "\"^\"", "\"N\"", "\"S\"", "\"P\"", "\"A\"", "\"X\"", "\"V\"", "\"R\"", "<NUMBER>", "<VAR>", "<LETTER>", "<DIGIT>", "<STRING>", "<ERROR>" };
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ActionParserConstants.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */