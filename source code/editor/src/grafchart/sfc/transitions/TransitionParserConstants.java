package grafchart.sfc.transitions;

public abstract interface TransitionParserConstants
{
  public static final int EOF = 0;
  public static final int LPAR = 6;
  public static final int RPAR = 7;
  public static final int COMMA = 8;
  public static final int COLON = 9;
  public static final int NOT = 10;
  public static final int AND = 11;
  public static final int OR = 12;
  public static final int MUL = 13;
  public static final int ADD = 14;
  public static final int SUB = 15;
  public static final int DIV = 16;
  public static final int MOD = 17;
  public static final int LE = 18;
  public static final int LT = 19;
  public static final int GE = 20;
  public static final int GT = 21;
  public static final int EQ = 22;
  public static final int NEQ = 23;
  public static final int QM = 24;
  public static final int DOT = 25;
  public static final int REF = 26;
  public static final int BSL = 27;
  public static final int NUMBER = 28;
  public static final int VAR = 29;
  public static final int LETTER = 30;
  public static final int DIGIT = 31;
  public static final int STRING = 32;
  public static final int ERROR = 33;
  public static final int DEFAULT = 0;
  public static final String[] tokenImage = { "<EOF>", "\" \"", "\"\\n\"", "\"\\t\"", "\"\\r\"", "<token of kind 5>", "\"(\"", "\")\"", "\",\"", "\":\"", "\"!\"", "\"&\"", "\"|\"", "\"*\"", "\"+\"", "\"-\"", "\"/\"", "\"%\"", "\"<=\"", "\"<\"", "\">=\"", "\">\"", "\"==\"", "\"!=\"", "\"?\"", "\".\"", "\"^\"", "\"\\\\\"", "<NUMBER>", "<VAR>", "<LETTER>", "<DIGIT>", "<STRING>", "<ERROR>" };
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/TransitionParserConstants.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */