package AST;

import beaver.Parser;
import beaver.Parser.Events;
import beaver.ParsingTables;
import beaver.Scanner;
import beaver.Scanner.Exception;
import beaver.Symbol;

public class LabCommParser
  extends Parser
{
  static final ParsingTables PARSING_TABLES = new ParsingTables("U9oTaqbl4q4GHh#PiRDXnoJOayrs4jk4DK0I288pXmZ1ZGjm9XA80q8WLW4IFvCzxFk#GuetdKuyM0wHwD7hgkwghkfgrKYwgPgAwbPTNTgaNkKrh16LLL5LenhJk2PLCrC77ZdjLBysjFLOT1vzgtieqZeyCihQ#xPPDYlMW0Orf9AzqmvxguAzKOysfYnxj5uJIGo5zjfUsIrrhjXshMXRLL93spLDdfAsCUUrsrvgnbxOS#NjcJsr9$RO7ccBSlPGKrGMSeyeYKl41c$HmsrpdEjLWPnzL1LA2l28oLyaxkeUe$$mgBWiISLXsonB5IIl4#DHXMueyt9DIuKNhheODMmXBzIqDRM821tC4NbY8dJnHIvMw7A7gI$sipHxGsxqGptHbs4K83I6Nq#dR14Sy7enVIxbinVcu20SWcDm7Cx2EJWF5#0YN8BBS0B6IBdeOro1gp3lroUzlERb55o7KtvzfkKE#s2WPM$Eovb4BjVdzOMKNsUxkluNnYJiRzaxaguhjN$OowDUdaxPPfU4$RAVziFhd#6JFN3wVRXhT#ostRC5hJ7icvTVuGjyz#kFyC5#epReE450$mMk1ol8XBeZwTi8ClHeGfPEpK2MxWp#Od0DXc2OioF8iYFfqeWR113nfmMEYJyBaPiE");
  
  public LabCommParser()
  {
    super(PARSING_TABLES);
    this.report = new Events();
  }
  
  protected Symbol invokeReduceAction(int paramInt1, int paramInt2)
  {
    Symbol localSymbol1;
    Object localObject1;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    Object localObject5;
    Symbol localSymbol2;
    switch (paramInt1)
    {
    case 0: 
      return new Program();
    case 1: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (List)localSymbol1.value;
      return new Program((List)localObject1);
    case 2: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (Decl)localSymbol1.value;
      return new List().add((ASTNode)localObject1);
    case 3: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (List)localSymbol1.value;
      localObject2 = this._symbols[(paramInt2 + 2)];
      localObject3 = (Decl)((Symbol)localObject2).value;
      return ((List)localObject1).add((ASTNode)localObject3);
    case 4: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (TypeDecl)localSymbol1.value;
      return (Symbol)localObject1;
    case 5: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (SampleDecl)localSymbol1.value;
      return (Symbol)localObject1;
    case 6: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (Field)localSymbol1.value;
      return new List().add((ASTNode)localObject1);
    case 7: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (List)localSymbol1.value;
      localObject2 = this._symbols[(paramInt2 + 2)];
      localObject3 = (Field)((Symbol)localObject2).value;
      return ((List)localObject1).add((ASTNode)localObject3);
    case 8: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (Type)localSymbol1.value;
      localObject2 = this._symbols[(paramInt2 + 2)];
      localObject3 = this._symbols[(paramInt2 + 3)];
      return new Field((Type)localObject1, (String)((Symbol)localObject2).value);
    case 9: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (Type)localSymbol1.value;
      localObject2 = this._symbols[(paramInt2 + 2)];
      localObject3 = this._symbols[(paramInt2 + 3)];
      localObject4 = (List)((Symbol)localObject3).value;
      localObject5 = this._symbols[(paramInt2 + 4)];
      return new Field(new ParseArrayType((Type)localObject1, (List)localObject4), (String)((Symbol)localObject2).value);
    case 10: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = this._symbols[(paramInt2 + 2)];
      localObject2 = (Type)((Symbol)localObject1).value;
      localObject3 = this._symbols[(paramInt2 + 3)];
      localObject4 = this._symbols[(paramInt2 + 4)];
      return new TypeDecl((Type)localObject2, (String)((Symbol)localObject3).value);
    case 11: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = this._symbols[(paramInt2 + 2)];
      localObject2 = (Type)((Symbol)localObject1).value;
      localObject3 = this._symbols[(paramInt2 + 3)];
      localObject4 = this._symbols[(paramInt2 + 4)];
      localObject5 = (List)((Symbol)localObject4).value;
      localSymbol2 = this._symbols[(paramInt2 + 5)];
      return new TypeDecl(new ParseArrayType((Type)localObject2, (List)localObject5), (String)((Symbol)localObject3).value);
    case 12: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = this._symbols[(paramInt2 + 2)];
      localObject2 = (Type)((Symbol)localObject1).value;
      localObject3 = this._symbols[(paramInt2 + 3)];
      localObject4 = this._symbols[(paramInt2 + 4)];
      return new SampleDecl((Type)localObject2, (String)((Symbol)localObject3).value);
    case 13: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = this._symbols[(paramInt2 + 2)];
      localObject2 = (Type)((Symbol)localObject1).value;
      localObject3 = this._symbols[(paramInt2 + 3)];
      localObject4 = this._symbols[(paramInt2 + 4)];
      localObject5 = (List)((Symbol)localObject4).value;
      localSymbol2 = this._symbols[(paramInt2 + 5)];
      return new SampleDecl(new ParseArrayType((Type)localObject2, (List)localObject5), (String)((Symbol)localObject3).value);
    case 14: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (PrimType)localSymbol1.value;
      return (Symbol)localObject1;
    case 15: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (UserType)localSymbol1.value;
      return (Symbol)localObject1;
    case 16: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (StructType)localSymbol1.value;
      return (Symbol)localObject1;
    case 17: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (VoidType)localSymbol1.value;
      return (Symbol)localObject1;
    case 18: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new PrimType((String)localSymbol1.value, 32);
    case 19: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new PrimType((String)localSymbol1.value, 33);
    case 20: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new PrimType((String)localSymbol1.value, 34);
    case 21: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new PrimType((String)localSymbol1.value, 35);
    case 22: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new PrimType((String)localSymbol1.value, 36);
    case 23: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new PrimType((String)localSymbol1.value, 37);
    case 24: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new PrimType((String)localSymbol1.value, 38);
    case 25: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new PrimType((String)localSymbol1.value, 39);
    case 26: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new UserType((String)localSymbol1.value);
    case 27: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = this._symbols[(paramInt2 + 2)];
      localObject2 = this._symbols[(paramInt2 + 3)];
      localObject3 = (List)((Symbol)localObject2).value;
      localObject4 = this._symbols[(paramInt2 + 4)];
      return new StructType((List)localObject3);
    case 28: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new VoidType();
    case 29: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (Dim)localSymbol1.value;
      return new List().add((ASTNode)localObject1);
    case 30: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (List)localSymbol1.value;
      localObject2 = this._symbols[(paramInt2 + 2)];
      localObject3 = (Dim)((Symbol)localObject2).value;
      return ((List)localObject1).add((ASTNode)localObject3);
    case 31: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = this._symbols[(paramInt2 + 2)];
      localObject2 = (List)((Symbol)localObject1).value;
      localObject3 = this._symbols[(paramInt2 + 3)];
      return new Dim((List)localObject2);
    case 32: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (Exp)localSymbol1.value;
      return new List().add((ASTNode)localObject1);
    case 33: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      localObject1 = (List)localSymbol1.value;
      localObject2 = this._symbols[(paramInt2 + 2)];
      localObject3 = this._symbols[(paramInt2 + 3)];
      localObject4 = (Exp)((Symbol)localObject3).value;
      return ((List)localObject1).add((ASTNode)localObject4);
    case 34: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new IntegerLiteral((String)localSymbol1.value);
    case 35: 
      localSymbol1 = this._symbols[(paramInt2 + 1)];
      return new VariableSize();
    }
    throw new IllegalArgumentException("unknown production #" + paramInt1);
  }
  
  class Events
    extends Parser.Events
  {
    Events() {}
    
    public void syntaxError(Symbol paramSymbol)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(Symbol.getLine(paramSymbol.getStart()) + ", " + Symbol.getColumn(paramSymbol.getStart()) + "\n");
      localStringBuffer.append("  *** Syntactic error: unexpected token " + LabCommParser.Terminals.NAMES[paramSymbol.getId()]);
      throw new LabCommParser.SourceError(localStringBuffer.toString());
    }
    
    public void scannerError(Scanner.Exception paramException)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(paramException.line + ", " + paramException.column + "\n");
      localStringBuffer.append("  *** Lexical error: " + paramException.getMessage());
      throw new LabCommParser.SourceError(localStringBuffer.toString());
    }
  }
  
  public static class SourceError
    extends Error
  {
    public SourceError(String paramString)
    {
      super();
    }
  }
  
  public static class Terminals
  {
    public static final short EOF = 0;
    public static final short TYPEDEF = 1;
    public static final short IDENTIFIER = 2;
    public static final short SEMICOLON = 3;
    public static final short SAMPLE = 4;
    public static final short STRUCT = 5;
    public static final short LBRACE = 6;
    public static final short RBRACE = 7;
    public static final short LBRACK = 8;
    public static final short RBRACK = 9;
    public static final short BOOLEAN = 10;
    public static final short BYTE = 11;
    public static final short SHORT = 12;
    public static final short INT = 13;
    public static final short LONG = 14;
    public static final short FLOAT = 15;
    public static final short DOUBLE = 16;
    public static final short STRING = 17;
    public static final short VOID = 18;
    public static final short COMMA = 19;
    public static final short INTEGER_LITERAL = 20;
    public static final short UNDERSCORE = 21;
    public static final String[] NAMES = { "EOF", "TYPEDEF", "IDENTIFIER", "SEMICOLON", "SAMPLE", "STRUCT", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "BOOLEAN", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "STRING", "VOID", "COMMA", "INTEGER_LITERAL", "UNDERSCORE" };
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/AST/LabCommParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */