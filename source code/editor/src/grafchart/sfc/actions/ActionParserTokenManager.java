package grafchart.sfc.actions;

import java.io.IOException;
import java.io.PrintStream;

public class ActionParserTokenManager
  implements ActionParserConstants
{
  public PrintStream debugStream = System.out;
  static final long[] jjbitVec0 = { 0L, 0L, -1L, -1L };
  static final int[] jjnextStates = { 9, 10, 12, 9, 10, 14, 12, 11, 13, 15 };
  public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, "(", ")", ",", ":", ";", "=", "!", "&", "|", "*", "+", "-", "/", "%", "<=", "<", ">=", ">", "==", "!=", "?", ".", "^", null, null, null, null, null, null, null, null, null, null, null, null, null };
  public static final String[] lexStateNames = { "DEFAULT" };
  static final long[] jjtoToken = { 3573412790209L };
  static final long[] jjtoSkip = { 62L };
  protected SimpleCharStream input_stream;
  private final int[] jjrounds = new int[17];
  private final int[] jjstateSet = new int[34];
  protected char curChar;
  int curLexState = 0;
  int defaultLexState = 0;
  int jjnewStateCnt;
  int jjround;
  int jjmatchedPos;
  int jjmatchedKind;
  
  public void setDebugStream(PrintStream paramPrintStream)
  {
    this.debugStream = paramPrintStream;
  }
  
  private int jjStopAtPos(int paramInt1, int paramInt2)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    return paramInt1 + 1;
  }
  
  private int jjMoveStringLiteralDfa0_0()
  {
    switch (this.curChar)
    {
    case '\t': 
      this.jjmatchedKind = 3;
      return jjMoveNfa_0(2, 0);
    case '\n': 
      this.jjmatchedKind = 2;
      return jjMoveNfa_0(2, 0);
    case '\r': 
      this.jjmatchedKind = 4;
      return jjMoveNfa_0(2, 0);
    case ' ': 
      this.jjmatchedKind = 1;
      return jjMoveNfa_0(2, 0);
    case '!': 
      this.jjmatchedKind = 12;
      return jjMoveStringLiteralDfa1_0(33554432L);
    case '%': 
      this.jjmatchedKind = 19;
      return jjMoveNfa_0(2, 0);
    case '&': 
      this.jjmatchedKind = 13;
      return jjMoveNfa_0(2, 0);
    case '(': 
      this.jjmatchedKind = 6;
      return jjMoveNfa_0(2, 0);
    case ')': 
      this.jjmatchedKind = 7;
      return jjMoveNfa_0(2, 0);
    case '*': 
      this.jjmatchedKind = 15;
      return jjMoveNfa_0(2, 0);
    case '+': 
      this.jjmatchedKind = 16;
      return jjMoveNfa_0(2, 0);
    case ',': 
      this.jjmatchedKind = 8;
      return jjMoveNfa_0(2, 0);
    case '-': 
      this.jjmatchedKind = 17;
      return jjMoveNfa_0(2, 0);
    case '.': 
      this.jjmatchedKind = 27;
      return jjMoveNfa_0(2, 0);
    case '/': 
      this.jjmatchedKind = 18;
      return jjMoveNfa_0(2, 0);
    case ':': 
      this.jjmatchedKind = 9;
      return jjMoveNfa_0(2, 0);
    case ';': 
      this.jjmatchedKind = 10;
      return jjMoveNfa_0(2, 0);
    case '<': 
      this.jjmatchedKind = 21;
      return jjMoveStringLiteralDfa1_0(1048576L);
    case '=': 
      this.jjmatchedKind = 11;
      return jjMoveStringLiteralDfa1_0(16777216L);
    case '>': 
      this.jjmatchedKind = 23;
      return jjMoveStringLiteralDfa1_0(4194304L);
    case '?': 
      this.jjmatchedKind = 26;
      return jjMoveNfa_0(2, 0);
    case 'A': 
      this.jjmatchedKind = 32;
      return jjMoveNfa_0(2, 0);
    case 'N': 
      this.jjmatchedKind = 29;
      return jjMoveNfa_0(2, 0);
    case 'P': 
      this.jjmatchedKind = 31;
      return jjMoveNfa_0(2, 0);
    case 'R': 
      this.jjmatchedKind = 35;
      return jjMoveNfa_0(2, 0);
    case 'S': 
      this.jjmatchedKind = 30;
      return jjMoveNfa_0(2, 0);
    case 'V': 
      this.jjmatchedKind = 34;
      return jjMoveNfa_0(2, 0);
    case 'X': 
      this.jjmatchedKind = 33;
      return jjMoveNfa_0(2, 0);
    case '^': 
      this.jjmatchedKind = 28;
      return jjMoveNfa_0(2, 0);
    case 'a': 
      this.jjmatchedKind = 32;
      return jjMoveNfa_0(2, 0);
    case 'n': 
      this.jjmatchedKind = 29;
      return jjMoveNfa_0(2, 0);
    case 'p': 
      this.jjmatchedKind = 31;
      return jjMoveNfa_0(2, 0);
    case 'r': 
      this.jjmatchedKind = 35;
      return jjMoveNfa_0(2, 0);
    case 's': 
      this.jjmatchedKind = 30;
      return jjMoveNfa_0(2, 0);
    case 'v': 
      this.jjmatchedKind = 34;
      return jjMoveNfa_0(2, 0);
    case 'x': 
      this.jjmatchedKind = 33;
      return jjMoveNfa_0(2, 0);
    case '|': 
      this.jjmatchedKind = 14;
      return jjMoveNfa_0(2, 0);
    }
    return jjMoveNfa_0(2, 0);
  }
  
  private int jjMoveStringLiteralDfa1_0(long paramLong)
  {
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      return jjMoveNfa_0(2, 0);
    }
    switch (this.curChar)
    {
    case '=': 
      if ((paramLong & 0x100000) != 0L)
      {
        this.jjmatchedKind = 20;
        this.jjmatchedPos = 1;
      }
      else if ((paramLong & 0x400000) != 0L)
      {
        this.jjmatchedKind = 22;
        this.jjmatchedPos = 1;
      }
      else if ((paramLong & 0x1000000) != 0L)
      {
        this.jjmatchedKind = 24;
        this.jjmatchedPos = 1;
      }
      else if ((paramLong & 0x2000000) != 0L)
      {
        this.jjmatchedKind = 25;
        this.jjmatchedPos = 1;
      }
      break;
    }
    return jjMoveNfa_0(2, 1);
  }
  
  private int jjMoveNfa_0(int paramInt1, int paramInt2)
  {
    int i = this.jjmatchedKind;
    int j = this.jjmatchedPos;
    int k;
    this.input_stream.backup(k = paramInt2 + 1);
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException1)
    {
      throw new Error("Internal Error");
    }
    paramInt2 = 0;
    int m = 0;
    this.jjnewStateCnt = 17;
    int n = 1;
    this.jjstateSet[0] = paramInt1;
    int i1 = Integer.MAX_VALUE;
      System.out.println("grafchart.sfc.actions.ActionParserTokenManager.jjMoveNfa_0(), - 216");
    /*for (;;)
    {
      if (++this.jjround == Integer.MAX_VALUE) {
        ReInitRounds();
      }
      long l1;
      if (this.curChar < '@')
      {
        l1 = 1L << this.curChar;
        do
        {
          switch (this.jjstateSet[(--n)])
          {
          case 2: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (i1 > 36) {
                i1 = 36;
              }
              jjCheckNAddTwoStates(3, 4);
            }
            else if (this.curChar == '"')
            {
              jjCheckNAddStates(0, 2);
            }
            else if (this.curChar == '/')
            {
              this.jjstateSet[(this.jjnewStateCnt++)] = 0;
            }
            break;
          case 0: 
            if (this.curChar == '/')
            {
              if (i1 > 5) {
                i1 = 5;
              }
              jjCheckNAdd(1);
            }
            break;
          case 1: 
            if ((0xFFFFFFFFFFFFDBFF & l1) != 0L)
            {
              if (i1 > 5) {
                i1 = 5;
              }
              jjCheckNAdd(1);
            }
            break;
          case 3: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (i1 > 36) {
                i1 = 36;
              }
              jjCheckNAddTwoStates(3, 4);
            }
            break;
          case 4: 
            if (this.curChar == '.') {
              jjCheckNAdd(5);
            }
            break;
          case 5: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (i1 > 36) {
                i1 = 36;
              }
              jjCheckNAdd(5);
            }
            break;
          case 7: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (i1 > 37) {
                i1 = 37;
              }
              this.jjstateSet[(this.jjnewStateCnt++)] = 7;
            }
            break;
          case 8: 
            if (this.curChar == '"') {
              jjCheckNAddStates(0, 2);
            }
            break;
          case 9: 
            if ((0xFFFFFFFBFFFFDBFF & l1) != 0L) {
              jjCheckNAddStates(0, 2);
            }
            break;
          case 11: 
            if ((0x8400000000 & l1) != 0L) {
              jjCheckNAddStates(0, 2);
            }
            break;
          case 12: 
            if ((this.curChar == '"') && (i1 > 40)) {
              i1 = 40;
            }
            break;
          case 13: 
            if ((0xFF000000000000 & l1) != 0L) {
              jjCheckNAddStates(3, 6);
            }
            break;
          case 14: 
            if ((0xFF000000000000 & l1) != 0L) {
              jjCheckNAddStates(0, 2);
            }
            break;
          case 15: 
            if ((0xF000000000000 & l1) != 0L) {
              this.jjstateSet[(this.jjnewStateCnt++)] = 16;
            }
            break;
          case 16: 
            if ((0xFF000000000000 & l1) != 0L) {
              jjCheckNAdd(14);
            }
            break;
          }
        } while (n != m);
      }
      else if (this.curChar < '')
      {
        l1 = 1L << (this.curChar & 0x3F);
        do
        {
          switch (this.jjstateSet[(--n)])
          {
          case 2: 
          case 6: 
            if ((0x7FFFFFE87FFFFFE & l1) != 0L)
            {
              if (i1 > 37) {
                i1 = 37;
              }
              jjCheckNAddTwoStates(6, 7);
            }
            break;
          case 1: 
            if (i1 > 5) {
              i1 = 5;
            }
            this.jjstateSet[(this.jjnewStateCnt++)] = 1;
            break;
          case 7: 
            if ((0x7FFFFFE87FFFFFE & l1) != 0L)
            {
              if (i1 > 37) {
                i1 = 37;
              }
              jjCheckNAdd(7);
            }
            break;
          case 9: 
            if ((0xFFFFFFFFEFFFFFFF & l1) != 0L) {
              jjCheckNAddStates(0, 2);
            }
            break;
          case 10: 
            if (this.curChar == '\\') {
              jjAddStates(7, 9);
            }
            break;
          case 11: 
            if ((0x14404410000000 & l1) != 0L) {
              jjCheckNAddStates(0, 2);
            }
            break;
          }
        } while (n != m);
      }
      else
      {
        int i2 = (this.curChar & 0xFF) >> '\006';
        long l2 = 1L << (this.curChar & 0x3F);
        do
        {
          switch (this.jjstateSet[(--n)])
          {
          case 1: 
            if ((jjbitVec0[i2] & l2) != 0L)
            {
              if (i1 > 5) {
                i1 = 5;
              }
              this.jjstateSet[(this.jjnewStateCnt++)] = 1;
            }
            break;
          case 9: 
            if ((jjbitVec0[i2] & l2) != 0L) {
              jjAddStates(0, 2);
            }
            break;
          }
        } while (n != m);
      }
      if (i1 != Integer.MAX_VALUE)
      {
        this.jjmatchedKind = i1;
        this.jjmatchedPos = paramInt2;
        i1 = Integer.MAX_VALUE;
      }
      paramInt2++;
      if ((n = this.jjnewStateCnt) != (m = 17 - (this.jjnewStateCnt = m))) {
        try
        {
          this.curChar = this.input_stream.readChar();
        }
        catch (IOException localIOException2) {}
      }
    }*/
    if (this.jjmatchedPos > j) {
      return paramInt2;
    }
    int i3 = Math.max(paramInt2, k);
    if (paramInt2 < i3)
    {
      n = i3 - Math.min(paramInt2, k);
      while (n-- > 0) {
        try
        {
          this.curChar = this.input_stream.readChar();
        }
        catch (IOException localIOException3)
        {
          throw new Error("Internal Error : Please send a bug report.");
        }
      }
    }
    if (this.jjmatchedPos < j)
    {
      this.jjmatchedKind = i;
      this.jjmatchedPos = j;
    }
    else if ((this.jjmatchedPos == j) && (this.jjmatchedKind > i))
    {
      this.jjmatchedKind = i;
    }
    return i3;
  }
  
  public ActionParserTokenManager(SimpleCharStream paramSimpleCharStream)
  {
    this.input_stream = paramSimpleCharStream;
  }
  
  public ActionParserTokenManager(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    this(paramSimpleCharStream);
    SwitchTo(paramInt);
  }
  
  public void ReInit(SimpleCharStream paramSimpleCharStream)
  {
    this.jjmatchedPos = (this.jjnewStateCnt = 0);
    this.curLexState = this.defaultLexState;
    this.input_stream = paramSimpleCharStream;
    ReInitRounds();
  }
  
  private void ReInitRounds()
  {
    this.jjround = -2147483647;
    int i = 17;
    while (i-- > 0) {
      this.jjrounds[i] = Integer.MIN_VALUE;
    }
  }
  
  public void ReInit(SimpleCharStream paramSimpleCharStream, int paramInt)
  {
    ReInit(paramSimpleCharStream);
    SwitchTo(paramInt);
  }
  
  public void SwitchTo(int paramInt)
  {
    if ((paramInt >= 1) || (paramInt < 0)) {
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + paramInt + ". State unchanged.", 2);
    }
    this.curLexState = paramInt;
  }
  
  protected Token jjFillToken()
  {
    String str2 = jjstrLiteralImages[this.jjmatchedKind];
    String str1 = str2 == null ? this.input_stream.GetImage() : str2;
    int i = this.input_stream.getBeginLine();
    int k = this.input_stream.getBeginColumn();
    int j = this.input_stream.getEndLine();
    int m = this.input_stream.getEndColumn();
    Token localToken = Token.newToken(this.jjmatchedKind, str1);
    localToken.beginLine = i;
    localToken.endLine = j;
    localToken.beginColumn = k;
    localToken.endColumn = m;
    return localToken;
  }
  
  public Token getNextToken()
  {
    int i = 0;
    do
    {
      try
      {
        this.curChar = this.input_stream.BeginToken();
      }
      catch (IOException localIOException1)
      {
        this.jjmatchedKind = 0;
          Token localToken = jjFillToken();
        return localToken;
      }
      this.jjmatchedKind = Integer.MAX_VALUE;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_0();
      if ((this.jjmatchedPos == 0) && (this.jjmatchedKind > 41)) {
        this.jjmatchedKind = 41;
      }
      if (this.jjmatchedKind == Integer.MAX_VALUE) {
        break;
      }
      if (this.jjmatchedPos + 1 < i) {
        this.input_stream.backup(i - this.jjmatchedPos - 1);
      }
    } while ((jjtoToken[(this.jjmatchedKind >> 6)] & 1L << (this.jjmatchedKind & 0x3F)) == 0L);
    Token localToken = jjFillToken();
    //return localToken;
    int j = this.input_stream.getEndLine();
    int k = this.input_stream.getEndColumn();
    String str = null;
    boolean bool = false;
    try
    {
      this.input_stream.readChar();
      this.input_stream.backup(1);
    }
    catch (IOException localIOException2)
    {
      bool = true;
      str = i <= 1 ? "" : this.input_stream.GetImage();
      if ((this.curChar == '\n') || (this.curChar == '\r'))
      {
        j++;
        k = 0;
      }
      else
      {
        k++;
      }
    }
    if (!bool)
    {
      this.input_stream.backup(1);
      str = i <= 1 ? "" : this.input_stream.GetImage();
    }
    throw new TokenMgrError(bool, this.curLexState, j, k, str, this.curChar, 0);
  }
  
  private void jjCheckNAdd(int paramInt)
  {
    if (this.jjrounds[paramInt] != this.jjround)
    {
      this.jjstateSet[(this.jjnewStateCnt++)] = paramInt;
      this.jjrounds[paramInt] = this.jjround;
    }
  }
  
  private void jjAddStates(int paramInt1, int paramInt2)
  {
    do
    {
      this.jjstateSet[(this.jjnewStateCnt++)] = jjnextStates[paramInt1];
    } while (paramInt1++ != paramInt2);
  }
  
  private void jjCheckNAddTwoStates(int paramInt1, int paramInt2)
  {
    jjCheckNAdd(paramInt1);
    jjCheckNAdd(paramInt2);
  }
  
  private void jjCheckNAddStates(int paramInt1, int paramInt2)
  {
    do
    {
      jjCheckNAdd(jjnextStates[paramInt1]);
    } while (paramInt1++ != paramInt2);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ActionParserTokenManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */