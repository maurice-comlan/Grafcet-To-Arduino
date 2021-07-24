package grafchart.sfc.transitions;

import java.io.IOException;
import java.io.PrintStream;

public class TransitionParserTokenManager
  implements TransitionParserConstants
{
  public PrintStream debugStream = System.out;
  static final long[] jjbitVec0 = { 0L, 0L, -1L, -1L };
  static final int[] jjnextStates = { 9, 10, 12, 9, 10, 14, 12, 11, 13, 15 };
  public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, "(", ")", ",", ":", "!", "&", "|", "*", "+", "-", "/", "%", "<=", "<", ">=", ">", "==", "!=", "?", ".", "^", "\\", null, null, null, null, null, null };
  public static final String[] lexStateNames = { "DEFAULT" };
  static final long[] jjtoToken = { 13958643649L };
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
  
  private final int jjStopStringLiteralDfa_0(int paramInt, long paramLong)
  {
    switch (paramInt)
    {
    case 0: 
      if ((paramLong & 0x10000) != 0L) {
        return 0;
      }
      return -1;
    }
    return -1;
  }
  
  private final int jjStartNfa_0(int paramInt, long paramLong)
  {
    return jjMoveNfa_0(jjStopStringLiteralDfa_0(paramInt, paramLong), paramInt + 1);
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
    case '!': 
      this.jjmatchedKind = 10;
      return jjMoveStringLiteralDfa1_0(8388608L);
    case '%': 
      return jjStopAtPos(0, 17);
    case '&': 
      return jjStopAtPos(0, 11);
    case '(': 
      return jjStopAtPos(0, 6);
    case ')': 
      return jjStopAtPos(0, 7);
    case '*': 
      return jjStopAtPos(0, 13);
    case '+': 
      return jjStopAtPos(0, 14);
    case ',': 
      return jjStopAtPos(0, 8);
    case '-': 
      return jjStopAtPos(0, 15);
    case '.': 
      return jjStopAtPos(0, 25);
    case '/': 
      return jjStartNfaWithStates_0(0, 16, 0);
    case ':': 
      return jjStopAtPos(0, 9);
    case '<': 
      this.jjmatchedKind = 19;
      return jjMoveStringLiteralDfa1_0(262144L);
    case '=': 
      return jjMoveStringLiteralDfa1_0(4194304L);
    case '>': 
      this.jjmatchedKind = 21;
      return jjMoveStringLiteralDfa1_0(1048576L);
    case '?': 
      return jjStopAtPos(0, 24);
    case '\\': 
      return jjStopAtPos(0, 27);
    case '^': 
      return jjStopAtPos(0, 26);
    case '|': 
      return jjStopAtPos(0, 12);
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
      jjStopStringLiteralDfa_0(0, paramLong);
      return 1;
    }
    switch (this.curChar)
    {
    case '=': 
      if ((paramLong & 0x40000) != 0L) {
        return jjStopAtPos(1, 18);
      }
      if ((paramLong & 0x100000) != 0L) {
        return jjStopAtPos(1, 20);
      }
      if ((paramLong & 0x400000) != 0L) {
        return jjStopAtPos(1, 22);
      }
      if ((paramLong & 0x800000) != 0L) {
        return jjStopAtPos(1, 23);
      }
      break;
    }
    return jjStartNfa_0(0, paramLong);
  }
  
  private int jjStartNfaWithStates_0(int paramInt1, int paramInt2, int paramInt3)
  {
    this.jjmatchedKind = paramInt2;
    this.jjmatchedPos = paramInt1;
    try
    {
      this.curChar = this.input_stream.readChar();
    }
    catch (IOException localIOException)
    {
      return paramInt1 + 1;
    }
    return jjMoveNfa_0(paramInt3, paramInt1 + 1);
  }
  
  private int jjMoveNfa_0(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.jjnewStateCnt = 17;
    int j = 1;
    this.jjstateSet[0] = paramInt1;
    int k = Integer.MAX_VALUE;
    
      System.out.println("grafchart.sfc.transitions.TransitionParserTokenManager.jjMoveNfa_0() - 160");
    /*
    for (;;)
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
          switch (this.jjstateSet[(--j)])
          {
          case 2: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 28) {
                k = 28;
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
              if (k > 5) {
                k = 5;
              }
              jjCheckNAdd(1);
            }
            break;
          case 1: 
            if ((0xFFFFFFFFFFFFDBFF & l1) != 0L)
            {
              if (k > 5) {
                k = 5;
              }
              jjCheckNAdd(1);
            }
            break;
          case 3: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 28) {
                k = 28;
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
              if (k > 28) {
                k = 28;
              }
              jjCheckNAdd(5);
            }
            break;
          case 7: 
            if ((0x3FF000000000000 & l1) != 0L)
            {
              if (k > 29) {
                k = 29;
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
            if ((this.curChar == '"') && (k > 32)) {
              k = 32;
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
        } while (j != i);
      }
      else if (this.curChar < '')
      {
        l1 = 1L << (this.curChar & 0x3F);
        do
        {
          switch (this.jjstateSet[(--j)])
          {
          case 2: 
          case 6: 
            if ((0x7FFFFFE87FFFFFE & l1) != 0L)
            {
              if (k > 29) {
                k = 29;
              }
              jjCheckNAddTwoStates(6, 7);
            }
            break;
          case 1: 
            if (k > 5) {
              k = 5;
            }
            this.jjstateSet[(this.jjnewStateCnt++)] = 1;
            break;
          case 7: 
            if ((0x7FFFFFE87FFFFFE & l1) != 0L)
            {
              if (k > 29) {
                k = 29;
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
        } while (j != i);
      }
      else
      {
        int m = (this.curChar & 0xFF) >> '\006';
        long l2 = 1L << (this.curChar & 0x3F);
        do
        {
          switch (this.jjstateSet[(--j)])
          {
          case 1: 
            if ((jjbitVec0[m] & l2) != 0L)
            {
              if (k > 5) {
                k = 5;
              }
              this.jjstateSet[(this.jjnewStateCnt++)] = 1;
            }
            break;
          case 9: 
            if ((jjbitVec0[m] & l2) != 0L) {
              jjAddStates(0, 2);
            }
            break;
          }
        } while (j != i);
      }
      if (k != Integer.MAX_VALUE)
      {
        this.jjmatchedKind = k;
        this.jjmatchedPos = paramInt2;
        k = Integer.MAX_VALUE;
      }
      paramInt2++;
      if ((j = this.jjnewStateCnt) == (i = 17 - (this.jjnewStateCnt = i))) {
        return paramInt2;
      }
      try
      {
        this.curChar = this.input_stream.readChar();
      }
      catch (IOException localIOException) {}
    }
    */
    return paramInt2;
  }
  
  public TransitionParserTokenManager(SimpleCharStream paramSimpleCharStream)
  {
    this.input_stream = paramSimpleCharStream;
  }
  
  public TransitionParserTokenManager(SimpleCharStream paramSimpleCharStream, int paramInt)
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
      for (;;)
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
        try
        {
          this.input_stream.backup(0);
            System.out.println("grafchart.sfc.transitions.TransitionParserTokenManager.getNextToken() - 458");
          
          while ((this.curChar <= ' ') && ((0x00002600 & 1L << this.curChar) != 0L)) { //0x100002600
            this.curChar = this.input_stream.BeginToken();
          }
        }
        catch (IOException localIOException2) {break;}
      }
      this.jjmatchedKind = Integer.MAX_VALUE;
      this.jjmatchedPos = 0;
      i = jjMoveStringLiteralDfa0_0();
      if ((this.jjmatchedPos == 0) && (this.jjmatchedKind > 33)) {
        this.jjmatchedKind = 33;
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
    catch (IOException localIOException3)
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/TransitionParserTokenManager.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */