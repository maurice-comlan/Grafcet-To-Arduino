package grafchart.sfc.actions;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class ActionParser
  implements ActionParserTreeConstants, ActionParserConstants
{
  protected JJTActionParserState jjtree = new JJTActionParserState();
  public ActionParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token;
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos;
  private Token jj_lastpos;
  private int jj_la;
  private int jj_gen;
  private final int[] jj_la1 = new int[28];
  private static int[] jj_la1_0;
  private static int[] jj_la1_1;
  private final JJCalls[] jj_2_rtns = new JJCalls[2];
  private boolean jj_rescan = false;
  private int jj_gc = 0;
  private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  private java.util.List<int[]> jj_expentries = new ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;
  
  public final ProcParam ProcParam()
    throws ParseException
  {
    ProcParam localProcParam1 = new ProcParam(0);
    int i = 1;
    this.jjtree.openNodeScope(localProcParam1);
    try
    {
      ParameterAssignments();
      jj_consume_token(0);
      this.jjtree.closeNodeScope(localProcParam1, true);
      i = 0;
      ProcParam localProcParam2 = localProcParam1;
      return localProcParam2;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localProcParam1);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localProcParam1, true);
      }
    }
  }
  
  public final void ParameterAssignments()
    throws ParseException
  {
    List localList = new List(1);
    int i = 1;
    this.jjtree.openNodeScope(localList);
    System.out.println("grafchart.sfc.actions.ActionParser.actions() - 11");
    try
    {
      for (;;)
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 10: 
        case 34: 
        case 35: 
          break;
        default: 
          this.jj_la1[0] = this.jj_gen;
          break;
        }
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 10: 
          jj_consume_token(10);
          break;
        case 34: 
        case 35: 
          ParameterAssignment();
          jj_consume_token(10);
        }
          //System.out.println("grafchart.sfc.actions.ActionParser.ParameterAssignments() - 107");
          //break;
      }
      /*
      this.jj_la1[1] = this.jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
      */
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localList);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localList, true);
      }
    }
  }
  
  public final void ParameterAssignment()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 34: 
      callByValue();
      break;
    case 35: 
      callByReference();
      break;
    default: 
      this.jj_la1[2] = this.jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }
  
  public final void callByValue()
    throws ParseException
  {
    CallByValue localCallByValue = new CallByValue(3);
    int i = 1;
    this.jjtree.openNodeScope(localCallByValue);
    try
    {
      jj_consume_token(34);
      assignment();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localCallByValue);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localCallByValue, true);
      }
    }
  }
  
  public final void callByReference()
    throws ParseException
  {
    CallByReference localCallByReference = new CallByReference(4);
    int i = 1;
    this.jjtree.openNodeScope(localCallByReference);
    try
    {
      jj_consume_token(35);
      assignment();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localCallByReference);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localCallByReference, true);
      }
    }
  }
  
  public final ProcCall ProcCall()
    throws ParseException
  {
    ProcCall localProcCall1 = new ProcCall(5);
    int i = 1;
    this.jjtree.openNodeScope(localProcCall1);
    try
    {
      expression();
      jj_consume_token(0);
      this.jjtree.closeNodeScope(localProcCall1, true);
      i = 0;
      ProcCall localProcCall2 = localProcCall1;
      return localProcCall2;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localProcCall1);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localProcCall1, true);
      }
    }
  }
  
  public final Statement Statement()
    throws ParseException
  {
    Statement localStatement1 = new Statement(6);
    int i = 1;
    this.jjtree.openNodeScope(localStatement1);
    try
    {
      actions();
      jj_consume_token(0);
      this.jjtree.closeNodeScope(localStatement1, true);
      i = 0;
      Statement localStatement2 = localStatement1;
      return localStatement2;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localStatement1);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localStatement1, true);
      }
    }
  }
  
  public final void actions()
    throws ParseException
  {
    List localList = new List(1);
    int i = 1;
    this.jjtree.openNodeScope(localList);
    System.out.println("grafchart.sfc.actions.ActionParser.actions() - 357");
    try
    {
      for (;;)
      {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 10: 
        case 29: 
        case 30: 
        case 31: 
        case 32: 
        case 33: 
          break;
        default: 
          this.jj_la1[3] = this.jj_gen;
          break;
        }
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 10: 
          jj_consume_token(10);
          break;
        case 29: 
        case 30: 
        case 31: 
        case 32: 
        case 33: 
          Action();
          jj_consume_token(10);
        }
      }
            
      /*
      this.jj_la1[4] = this.jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
      */
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localList);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localList, true);
      }
    }
  }
  
  public final void Action()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 29: 
      jj_consume_token(29);
      NormalAction localNormalAction = new NormalAction(7);
      int i = 1;
      this.jjtree.openNodeScope(localNormalAction);
      try
      {
        dotexpression();
      }
      catch (Throwable localThrowable1)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localNormalAction);
          i = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable1 instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable1);
        }
        if ((localThrowable1 instanceof ParseException)) {
          throw ((ParseException)localThrowable1);
        }
        throw ((Error)localThrowable1);
      }
      finally
      {
        if (i != 0) {
          this.jjtree.closeNodeScope(localNormalAction, 1);
        }
      }
      break;
    case 31: 
      jj_consume_token(31);
      PeriodicAction localPeriodicAction = new PeriodicAction(8);
      int j = 1;
      this.jjtree.openNodeScope(localPeriodicAction);
      try
      {
        stmt();
      }
      catch (Throwable localThrowable2)
      {
        if (j != 0)
        {
          this.jjtree.clearNodeScope(localPeriodicAction);
          j = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable2 instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable2);
        }
        if ((localThrowable2 instanceof ParseException)) {
          throw ((ParseException)localThrowable2);
        }
        throw ((Error)localThrowable2);
      }
      finally
      {
        if (j != 0) {
          this.jjtree.closeNodeScope(localPeriodicAction, 1);
        }
      }
      break;
    case 32: 
      jj_consume_token(32);
      AbortiveAction localAbortiveAction = new AbortiveAction(9);
      int k = 1;
      this.jjtree.openNodeScope(localAbortiveAction);
      try
      {
        stmt();
      }
      catch (Throwable localThrowable3)
      {
        if (k != 0)
        {
          this.jjtree.clearNodeScope(localAbortiveAction);
          k = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable3 instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable3);
        }
        if ((localThrowable3 instanceof ParseException)) {
          throw ((ParseException)localThrowable3);
        }
        throw ((Error)localThrowable3);
      }
      finally
      {
        if (k != 0) {
          this.jjtree.closeNodeScope(localAbortiveAction, 1);
        }
      }
      break;
    case 30: 
      jj_consume_token(30);
      StoredAction localStoredAction = new StoredAction(10);
      int m = 1;
      this.jjtree.openNodeScope(localStoredAction);
      try
      {
        stmt();
      }
      catch (Throwable localThrowable4)
      {
        if (m != 0)
        {
          this.jjtree.clearNodeScope(localStoredAction);
          m = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable4 instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable4);
        }
        if ((localThrowable4 instanceof ParseException)) {
          throw ((ParseException)localThrowable4);
        }
        throw ((Error)localThrowable4);
      }
      finally
      {
        if (m != 0) {
          this.jjtree.closeNodeScope(localStoredAction, 1);
        }
      }
      break;
    case 33: 
      jj_consume_token(33);
      ExitAction localExitAction = new ExitAction(11);
      int n = 1;
      this.jjtree.openNodeScope(localExitAction);
      try
      {
        stmt();
      }
      catch (Throwable localThrowable5)
      {
        if (n != 0)
        {
          this.jjtree.clearNodeScope(localExitAction);
          n = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable5 instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable5);
        }
        if ((localThrowable5 instanceof ParseException)) {
          throw ((ParseException)localThrowable5);
        }
        throw ((Error)localThrowable5);
      }
      finally
      {
        if (n != 0) {
          this.jjtree.closeNodeScope(localExitAction, 1);
        }
      }
      break;
    default: 
      this.jj_la1[5] = this.jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }
  
  public final void stmt()
    throws ParseException
  {
    if (jj_2_1(Integer.MAX_VALUE)) {
      assignment();
    } else {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 6: 
      case 30: 
      case 33: 
      case 36: 
      case 37: 
      case 40: 
        callStmt();
        break;
      default: 
        this.jj_la1[6] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void assignment()
    throws ParseException
  {
    Assignment localAssignment = new Assignment(12);
    int i = 1;
    this.jjtree.openNodeScope(localAssignment);
    try
    {
      dotexpression();
      jj_consume_token(11);
      expression();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localAssignment);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localAssignment, true);
      }
    }
  }
  
  public final void callStmt()
    throws ParseException
  {
    CallStmt localCallStmt = new CallStmt(13);
    int i = 1;
    this.jjtree.openNodeScope(localCallStmt);
    try
    {
      dotexpression();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localCallStmt);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localCallStmt, true);
      }
    }
  }
  
  public final SingleExpression SingleExpression()
    throws ParseException
  {
    SingleExpression localSingleExpression1 = new SingleExpression(14);
    int i = 1;
    this.jjtree.openNodeScope(localSingleExpression1);
    try
    {
      expression();
      jj_consume_token(0);
      this.jjtree.closeNodeScope(localSingleExpression1, true);
      i = 0;
      SingleExpression localSingleExpression2 = localSingleExpression1;
      return localSingleExpression2;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localSingleExpression1);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localSingleExpression1, true);
      }
    }
  }
  
  public final void expression()
    throws ParseException
  {
    logicalor();
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 26: 
      jj_consume_token(26);
      expression();
      jj_consume_token(9);
      ConditionalExpression localConditionalExpression = new ConditionalExpression(15);
      int i = 1;
      this.jjtree.openNodeScope(localConditionalExpression);
      try
      {
        expression();
      }
      catch (Throwable localThrowable)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localConditionalExpression);
          i = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable);
        }
        if ((localThrowable instanceof ParseException)) {
          throw ((ParseException)localThrowable);
        }
        throw ((Error)localThrowable);
      }
      finally
      {
        if (i != 0) {
          this.jjtree.closeNodeScope(localConditionalExpression, 3);
        }
      }
      break;
    default: 
      this.jj_la1[7] = this.jj_gen;
    }
  }
  
  public final void logicalor()
    throws ParseException
  {
    logicaland();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 14: 
        break;
      default: 
        this.jj_la1[8] = this.jj_gen;
        break;
      }
      jj_consume_token(14);
      Or localOr = new Or(16);
      int i = 1;
      this.jjtree.openNodeScope(localOr);
      try
      {
        logicaland();
      }
      catch (Throwable localThrowable)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localOr);
          i = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable);
        }
        if ((localThrowable instanceof ParseException)) {
          throw ((ParseException)localThrowable);
        }
        throw ((Error)localThrowable);
      }
      finally
      {
        if (i != 0) {
          this.jjtree.closeNodeScope(localOr, 2);
        }
      }
    }
  }
  
  public final void logicaland()
    throws ParseException
  {
    equalityexpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 13: 
        break;
      default: 
        this.jj_la1[9] = this.jj_gen;
        break;
      }
      jj_consume_token(13);
      And localAnd = new And(17);
      int i = 1;
      this.jjtree.openNodeScope(localAnd);
      try
      {
        equalityexpression();
      }
      catch (Throwable localThrowable)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localAnd);
          i = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable);
        }
        if ((localThrowable instanceof ParseException)) {
          throw ((ParseException)localThrowable);
        }
        throw ((Error)localThrowable);
      }
      finally
      {
        if (i != 0) {
          this.jjtree.closeNodeScope(localAnd, 2);
        }
      }
    }
  }
  
  public final void equalityexpression()
    throws ParseException
  {
    relationalexpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 24: 
      case 25: 
        break;
      default: 
        this.jj_la1[10] = this.jj_gen;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 24: 
        jj_consume_token(24);
        EQ localEQ = new EQ(18);
        int i = 1;
        this.jjtree.openNodeScope(localEQ);
        try
        {
          relationalexpression();
        }
        catch (Throwable localThrowable1)
        {
          if (i != 0)
          {
            this.jjtree.clearNodeScope(localEQ);
            i = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable1 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable1);
          }
          if ((localThrowable1 instanceof ParseException)) {
            throw ((ParseException)localThrowable1);
          }
          throw ((Error)localThrowable1);
        }
        finally
        {
          if (i != 0) {
            this.jjtree.closeNodeScope(localEQ, 2);
          }
        }
        break;
      case 25: 
        jj_consume_token(25);
        NEQ localNEQ = new NEQ(19);
        int j = 1;
        this.jjtree.openNodeScope(localNEQ);
        try
        {
          relationalexpression();
        }
        catch (Throwable localThrowable2)
        {
          if (j != 0)
          {
            this.jjtree.clearNodeScope(localNEQ);
            j = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable2 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable2);
          }
          if ((localThrowable2 instanceof ParseException)) {
            throw ((ParseException)localThrowable2);
          }
          throw ((Error)localThrowable2);
        }
        finally
        {
          if (j != 0) {
            this.jjtree.closeNodeScope(localNEQ, 2);
          }
        }
        break;
      default: 
        this.jj_la1[11] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void relationalexpression()
    throws ParseException
  {
    additiveexpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 20: 
      case 21: 
      case 22: 
      case 23: 
        break;
      default: 
        this.jj_la1[12] = this.jj_gen;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 21: 
        jj_consume_token(21);
        LT localLT = new LT(20);
        int i = 1;
        this.jjtree.openNodeScope(localLT);
        try
        {
          additiveexpression();
        }
        catch (Throwable localThrowable1)
        {
          if (i != 0)
          {
            this.jjtree.clearNodeScope(localLT);
            i = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable1 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable1);
          }
          if ((localThrowable1 instanceof ParseException)) {
            throw ((ParseException)localThrowable1);
          }
          throw ((Error)localThrowable1);
        }
        finally
        {
          if (i != 0) {
            this.jjtree.closeNodeScope(localLT, 2);
          }
        }
        break;
      case 23: 
        jj_consume_token(23);
        GT localGT = new GT(21);
        int j = 1;
        this.jjtree.openNodeScope(localGT);
        try
        {
          additiveexpression();
        }
        catch (Throwable localThrowable2)
        {
          if (j != 0)
          {
            this.jjtree.clearNodeScope(localGT);
            j = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable2 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable2);
          }
          if ((localThrowable2 instanceof ParseException)) {
            throw ((ParseException)localThrowable2);
          }
          throw ((Error)localThrowable2);
        }
        finally
        {
          if (j != 0) {
            this.jjtree.closeNodeScope(localGT, 2);
          }
        }
        break;
      case 20: 
        jj_consume_token(20);
        LE localLE = new LE(22);
        int k = 1;
        this.jjtree.openNodeScope(localLE);
        try
        {
          additiveexpression();
        }
        catch (Throwable localThrowable3)
        {
          if (k != 0)
          {
            this.jjtree.clearNodeScope(localLE);
            k = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable3 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable3);
          }
          if ((localThrowable3 instanceof ParseException)) {
            throw ((ParseException)localThrowable3);
          }
          throw ((Error)localThrowable3);
        }
        finally
        {
          if (k != 0) {
            this.jjtree.closeNodeScope(localLE, 2);
          }
        }
        break;
      case 22: 
        jj_consume_token(22);
        GE localGE = new GE(23);
        int m = 1;
        this.jjtree.openNodeScope(localGE);
        try
        {
          additiveexpression();
        }
        catch (Throwable localThrowable4)
        {
          if (m != 0)
          {
            this.jjtree.clearNodeScope(localGE);
            m = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable4 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable4);
          }
          if ((localThrowable4 instanceof ParseException)) {
            throw ((ParseException)localThrowable4);
          }
          throw ((Error)localThrowable4);
        }
        finally
        {
          if (m != 0) {
            this.jjtree.closeNodeScope(localGE, 2);
          }
        }
        break;
      default: 
        this.jj_la1[13] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void additiveexpression()
    throws ParseException
  {
    multiplicativeexpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 16: 
      case 17: 
        break;
      default: 
        this.jj_la1[14] = this.jj_gen;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 16: 
        jj_consume_token(16);
        Plus localPlus = new Plus(24);
        int i = 1;
        this.jjtree.openNodeScope(localPlus);
        try
        {
          multiplicativeexpression();
        }
        catch (Throwable localThrowable1)
        {
          if (i != 0)
          {
            this.jjtree.clearNodeScope(localPlus);
            i = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable1 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable1);
          }
          if ((localThrowable1 instanceof ParseException)) {
            throw ((ParseException)localThrowable1);
          }
          throw ((Error)localThrowable1);
        }
        finally
        {
          if (i != 0) {
            this.jjtree.closeNodeScope(localPlus, 2);
          }
        }
        break;
      case 17: 
        jj_consume_token(17);
        Minus localMinus = new Minus(25);
        int j = 1;
        this.jjtree.openNodeScope(localMinus);
        try
        {
          multiplicativeexpression();
        }
        catch (Throwable localThrowable2)
        {
          if (j != 0)
          {
            this.jjtree.clearNodeScope(localMinus);
            j = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable2 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable2);
          }
          if ((localThrowable2 instanceof ParseException)) {
            throw ((ParseException)localThrowable2);
          }
          throw ((Error)localThrowable2);
        }
        finally
        {
          if (j != 0) {
            this.jjtree.closeNodeScope(localMinus, 2);
          }
        }
        break;
      default: 
        this.jj_la1[15] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void multiplicativeexpression()
    throws ParseException
  {
    unaryexpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 15: 
      case 18: 
      case 19: 
        break;
      case 16: 
      case 17: 
      default: 
        this.jj_la1[16] = this.jj_gen;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 15: 
        jj_consume_token(15);
        Mult localMult = new Mult(26);
        int i = 1;
        this.jjtree.openNodeScope(localMult);
        try
        {
          unaryexpression();
        }
        catch (Throwable localThrowable1)
        {
          if (i != 0)
          {
            this.jjtree.clearNodeScope(localMult);
            i = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable1 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable1);
          }
          if ((localThrowable1 instanceof ParseException)) {
            throw ((ParseException)localThrowable1);
          }
          throw ((Error)localThrowable1);
        }
        finally
        {
          if (i != 0) {
            this.jjtree.closeNodeScope(localMult, 2);
          }
        }
        break;
      case 18: 
        jj_consume_token(18);
        Div localDiv = new Div(27);
        int j = 1;
        this.jjtree.openNodeScope(localDiv);
        try
        {
          unaryexpression();
        }
        catch (Throwable localThrowable2)
        {
          if (j != 0)
          {
            this.jjtree.clearNodeScope(localDiv);
            j = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable2 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable2);
          }
          if ((localThrowable2 instanceof ParseException)) {
            throw ((ParseException)localThrowable2);
          }
          throw ((Error)localThrowable2);
        }
        finally
        {
          if (j != 0) {
            this.jjtree.closeNodeScope(localDiv, 2);
          }
        }
        break;
      case 19: 
        jj_consume_token(19);
        Mod localMod = new Mod(28);
        int k = 1;
        this.jjtree.openNodeScope(localMod);
        try
        {
          unaryexpression();
        }
        catch (Throwable localThrowable3)
        {
          if (k != 0)
          {
            this.jjtree.clearNodeScope(localMod);
            k = 0;
          }
          else
          {
            this.jjtree.popNode();
          }
          if ((localThrowable3 instanceof RuntimeException)) {
            throw ((RuntimeException)localThrowable3);
          }
          if ((localThrowable3 instanceof ParseException)) {
            throw ((ParseException)localThrowable3);
          }
          throw ((Error)localThrowable3);
        }
        finally
        {
          if (k != 0) {
            this.jjtree.closeNodeScope(localMod, 2);
          }
        }
        break;
      case 16: 
      case 17: 
      default: 
        this.jj_la1[17] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }
  
  public final void unaryexpression()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 17: 
      jj_consume_token(17);
      Negation localNegation = new Negation(29);
      int i = 1;
      this.jjtree.openNodeScope(localNegation);
      try
      {
        unaryexpression();
      }
      catch (Throwable localThrowable1)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localNegation);
          i = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable1 instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable1);
        }
        if ((localThrowable1 instanceof ParseException)) {
          throw ((ParseException)localThrowable1);
        }
        throw ((Error)localThrowable1);
      }
      finally
      {
        if (i != 0) {
          this.jjtree.closeNodeScope(localNegation, 1);
        }
      }
      break;
    case 12: 
      jj_consume_token(12);
      Not localNot = new Not(30);
      int j = 1;
      this.jjtree.openNodeScope(localNot);
      try
      {
        unaryexpression();
      }
      catch (Throwable localThrowable2)
      {
        if (j != 0)
        {
          this.jjtree.clearNodeScope(localNot);
          j = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable2 instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable2);
        }
        if ((localThrowable2 instanceof ParseException)) {
          throw ((ParseException)localThrowable2);
        }
        throw ((Error)localThrowable2);
      }
      finally
      {
        if (j != 0) {
          this.jjtree.closeNodeScope(localNot, 1);
        }
      }
      break;
    case 6: 
    case 30: 
    case 33: 
    case 36: 
    case 37: 
    case 40: 
      dotexpression();
      break;
    default: 
      this.jj_la1[18] = this.jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }
  
  public final void primaryexpression()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 40: 
      String();
      break;
    case 36: 
      Number();
      break;
    default: 
      this.jj_la1[19] = this.jj_gen;
      if (jj_2_2(2)) {
        Function();
      } else {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 37: 
          VarOptReferences();
          break;
        case 6: 
          jj_consume_token(6);
          expression();
          jj_consume_token(7);
          break;
        default: 
          this.jj_la1[20] = this.jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
    }
  }
  
  public final void Number()
    throws ParseException
  {
    Number localNumber = new Number(31);
    int i = 1;
    this.jjtree.openNodeScope(localNumber);
    try
    {
      Token localToken = jj_consume_token(36);
      this.jjtree.closeNodeScope(localNumber, true);
      i = 0;
      localNumber.setVALUE(localToken.image);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localNumber, true);
      }
    }
  }
  
  public final void VarOptReferences()
    throws ParseException
  {
    Var();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 28: 
        break;
      default: 
        this.jj_la1[21] = this.jj_gen;
        break;
      }
      Reference localReference = new Reference(32);
      int i = 1;
      this.jjtree.openNodeScope(localReference);
      try
      {
        jj_consume_token(28);
      }
      finally
      {
        if (i != 0) {
          this.jjtree.closeNodeScope(localReference, 1);
        }
      }
    }
  }
  
  public final void Var()
    throws ParseException
  {
    Var localVar = new Var(33);
    int i = 1;
    this.jjtree.openNodeScope(localVar);
    try
    {
      Token localToken = jj_consume_token(37);
      this.jjtree.closeNodeScope(localVar, true);
      i = 0;
      localVar.setID(localToken.image);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localVar, true);
      }
    }
  }
  
  public final void String()
    throws ParseException
  {
    StringExpr localStringExpr = new StringExpr(34);
    int i = 1;
    this.jjtree.openNodeScope(localStringExpr);
    try
    {
      Token localToken = jj_consume_token(40);
      this.jjtree.closeNodeScope(localStringExpr, true);
      i = 0;
      localStringExpr.setVALUE(localToken.image.substring(1, localToken.image.length() - 1));
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localStringExpr, true);
      }
    }
  }
  
  public final void Function()
    throws ParseException
  {
    Function localFunction = new Function(35);
    int i = 1;
    this.jjtree.openNodeScope(localFunction);
    try
    {
      Token localToken;
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 37: 
        localToken = jj_consume_token(37);
        localFunction.setID(localToken.image);
        jj_consume_token(6);
        Arguments();
        jj_consume_token(7);
        break;
      case 30: 
      case 33: 
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 33: 
          localToken = jj_consume_token(33);
          break;
        case 30: 
          localToken = jj_consume_token(30);
          break;
        default: 
          this.jj_la1[22] = this.jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        List localList = new List(1);
        int j = 1;
        this.jjtree.openNodeScope(localList);
        try
        {
          switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
          {
          case 6: 
            jj_consume_token(6);
            jj_consume_token(7);
            break;
          default: 
            this.jj_la1[23] = this.jj_gen;
          }
        }
        finally
        {
          if (j != 0) {
            this.jjtree.closeNodeScope(localList, 0);
          }
        }
        this.jjtree.closeNodeScope(localFunction, true);
        i = 0;
        localFunction.setID(localToken.image.toLowerCase());
        break;
      default: 
        this.jj_la1[24] = this.jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localFunction);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localFunction, true);
      }
    }
  }
  
  public final void Arguments()
    throws ParseException
  {
    List localList = new List(1);
    int i = 1;
    this.jjtree.openNodeScope(localList);
    try
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 6: 
      case 12: 
      case 17: 
      case 30: 
      case 33: 
      case 36: 
      case 37: 
      case 40: 
        expression();
        for (;;)
        {
          switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
          {
          case 8: 
            break;
          default: 
            this.jj_la1[25] = this.jj_gen;
            break;
          }
          jj_consume_token(8);
          expression();
        }
      }
      this.jj_la1[26] = this.jj_gen;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localList);
        i = 0;
      }
      else
      {
        this.jjtree.popNode();
      }
      if ((localThrowable instanceof RuntimeException)) {
        throw ((RuntimeException)localThrowable);
      }
      if ((localThrowable instanceof ParseException)) {
        throw ((ParseException)localThrowable);
      }
      throw ((Error)localThrowable);
    }
    finally
    {
      if (i != 0) {
        this.jjtree.closeNodeScope(localList, true);
      }
    }
  }
  
  public final void dotexpression()
    throws ParseException
  {
    primaryexpression();
    for (;;)
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 27: 
        break;
      default: 
        this.jj_la1[27] = this.jj_gen;
        break;
      }
      jj_consume_token(27);
      Dot localDot = new Dot(36);
      int i = 1;
      this.jjtree.openNodeScope(localDot);
      try
      {
        primaryexpression();
      }
      catch (Throwable localThrowable)
      {
        if (i != 0)
        {
          this.jjtree.clearNodeScope(localDot);
          i = 0;
        }
        else
        {
          this.jjtree.popNode();
        }
        if ((localThrowable instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable);
        }
        if ((localThrowable instanceof ParseException)) {
          throw ((ParseException)localThrowable);
        }
        throw ((Error)localThrowable);
      }
      finally
      {
        if (i != 0) {
          this.jjtree.closeNodeScope(localDot, 2);
        }
      }
    }
  }
  
  private boolean jj_2_1(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      boolean bool1 = !jj_3_1();
      return bool1;
    }
    catch (LookaheadSuccess localLookaheadSuccess)
    {
      boolean bool2 = true;
      return bool2;
    }
    finally
    {
      jj_save(0, paramInt);
    }
  }
  
  private boolean jj_2_2(int paramInt)
  {
    this.jj_la = paramInt;
    this.jj_lastpos = (this.jj_scanpos = this.token);
    try
    {
      boolean bool1 = !jj_3_2();
      return bool1;
    }
    catch (LookaheadSuccess localLookaheadSuccess)
    {
      boolean bool2 = true;
      return bool2;
    }
    finally
    {
      jj_save(1, paramInt);
    }
  }
  
  private boolean jj_3R_21()
  {
    if (jj_scan_token(6)) {
      return true;
    }
    if (jj_3R_26()) {
      return true;
    }
    return jj_scan_token(7);
  }
  
  private boolean jj_3R_20()
  {
    return jj_3R_25();
  }
  
  private boolean jj_3_2()
  {
    return jj_3R_13();
  }
  
  private boolean jj_3R_15()
  {
    if (jj_scan_token(27)) {
      return true;
    }
    return jj_3R_14();
  }
  
  private boolean jj_3R_19()
  {
    return jj_3R_24();
  }
  
  private boolean jj_3R_18()
  {
    return jj_3R_23();
  }
  
  private boolean jj_3R_14()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_18())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_19())
      {
        this.jj_scanpos = localToken;
        if (jj_3_2())
        {
          this.jj_scanpos = localToken;
          if (jj_3R_20())
          {
            this.jj_scanpos = localToken;
            if (jj_3R_21()) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  
  private boolean jj_3R_59()
  {
    if (jj_scan_token(19)) {
      return true;
    }
    return jj_3R_50();
  }
  
  private boolean jj_3R_56()
  {
    return jj_3R_12();
  }
  
  private boolean jj_3R_58()
  {
    if (jj_scan_token(18)) {
      return true;
    }
    return jj_3R_50();
  }
  
  private boolean jj_3R_55()
  {
    if (jj_scan_token(12)) {
      return true;
    }
    return jj_3R_50();
  }
  
  private boolean jj_3R_57()
  {
    if (jj_scan_token(15)) {
      return true;
    }
    return jj_3R_50();
  }
  
  private boolean jj_3R_54()
  {
    if (jj_scan_token(17)) {
      return true;
    }
    return jj_3R_50();
  }
  
  private boolean jj_3R_51()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_57())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_58())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_59()) {
          return true;
        }
      }
    }
    return false;
  }
  
  private boolean jj_3R_50()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_54())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_55())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_56()) {
          return true;
        }
      }
    }
    return false;
  }
  
  private boolean jj_3R_37()
  {
    if (jj_scan_token(8)) {
      return true;
    }
    return jj_3R_26();
  }
  
  private boolean jj_3R_53()
  {
    if (jj_scan_token(17)) {
      return true;
    }
    return jj_3R_44();
  }
  
  private boolean jj_3R_52()
  {
    if (jj_scan_token(16)) {
      return true;
    }
    return jj_3R_44();
  }
  
  private boolean jj_3R_45()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_52())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_53()) {
        return true;
      }
    }
    return false;
  }
  
  private boolean jj_3R_44()
  {
    if (jj_3R_50()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_51());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private boolean jj_3R_12()
  {
    if (jj_3R_14()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_15());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private boolean jj_3R_34()
  {
    if (jj_3R_26()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_37());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private boolean jj_3R_31()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_34()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private boolean jj_3R_49()
  {
    if (jj_scan_token(22)) {
      return true;
    }
    return jj_3R_40();
  }
  
  private boolean jj_3R_48()
  {
    if (jj_scan_token(20)) {
      return true;
    }
    return jj_3R_40();
  }
  
  private boolean jj_3R_22()
  {
    if (jj_scan_token(6)) {
      return true;
    }
    return jj_scan_token(7);
  }
  
  private boolean jj_3R_47()
  {
    if (jj_scan_token(23)) {
      return true;
    }
    return jj_3R_40();
  }
  
  private boolean jj_3R_46()
  {
    if (jj_scan_token(21)) {
      return true;
    }
    return jj_3R_40();
  }
  
  private boolean jj_3R_41()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_46())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_47())
      {
        this.jj_scanpos = localToken;
        if (jj_3R_48())
        {
          this.jj_scanpos = localToken;
          if (jj_3R_49()) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private boolean jj_3R_40()
  {
    if (jj_3R_44()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_45());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private boolean jj_3R_36()
  {
    if (jj_scan_token(13)) {
      return true;
    }
    return jj_3R_35();
  }
  
  private boolean jj_3R_43()
  {
    if (jj_scan_token(25)) {
      return true;
    }
    return jj_3R_38();
  }
  
  private boolean jj_3R_39()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_42())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_43()) {
        return true;
      }
    }
    return false;
  }
  
  private boolean jj_3R_42()
  {
    if (jj_scan_token(24)) {
      return true;
    }
    return jj_3R_38();
  }
  
  private boolean jj_3R_38()
  {
    if (jj_3R_40()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_41());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private boolean jj_3R_17()
  {
    Token localToken = this.jj_scanpos;
    if (jj_scan_token(33))
    {
      this.jj_scanpos = localToken;
      if (jj_scan_token(30)) {
        return true;
      }
    }
    localToken = this.jj_scanpos;
    if (jj_3R_22()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private boolean jj_3R_33()
  {
    if (jj_scan_token(14)) {
      return true;
    }
    return jj_3R_32();
  }
  
  private boolean jj_3R_13()
  {
    Token localToken = this.jj_scanpos;
    if (jj_3R_16())
    {
      this.jj_scanpos = localToken;
      if (jj_3R_17()) {
        return true;
      }
    }
    return false;
  }
  
  private boolean jj_3R_16()
  {
    if (jj_scan_token(37)) {
      return true;
    }
    if (jj_scan_token(6)) {
      return true;
    }
    if (jj_3R_31()) {
      return true;
    }
    return jj_scan_token(7);
  }
  
  private boolean jj_3R_35()
  {
    if (jj_3R_38()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_39());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private boolean jj_3R_30()
  {
    if (jj_scan_token(26)) {
      return true;
    }
    if (jj_3R_26()) {
      return true;
    }
    if (jj_scan_token(9)) {
      return true;
    }
    return jj_3R_26();
  }
  
  private boolean jj_3R_32()
  {
    if (jj_3R_35()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_36());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private boolean jj_3R_29()
  {
    if (jj_3R_32()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_33());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private boolean jj_3R_23()
  {
    return jj_scan_token(40);
  }
  
  private boolean jj_3R_26()
  {
    if (jj_3R_29()) {
      return true;
    }
    Token localToken = this.jj_scanpos;
    if (jj_3R_30()) {
      this.jj_scanpos = localToken;
    }
    return false;
  }
  
  private boolean jj_3R_27()
  {
    return jj_scan_token(37);
  }
  
  private boolean jj_3R_28()
  {
    return jj_scan_token(28);
  }
  
  private boolean jj_3_1()
  {
    if (jj_3R_12()) {
      return true;
    }
    return jj_scan_token(11);
  }
  
  private boolean jj_3R_25()
  {
    if (jj_3R_27()) {
      return true;
    }
    Token localToken;
    do
    {
      localToken = this.jj_scanpos;
    } while (!jj_3R_28());
    this.jj_scanpos = localToken;
    return false;
  }
  
  private boolean jj_3R_24()
  {
    return jj_scan_token(36);
  }
  
  private static void jj_la1_init_0()
  {
    jj_la1_0 = new int[] { 1024, 1024, 0, -536869888, -536869888, -536870912, 1073741888, 67108864, 16384, 8192, 50331648, 50331648, 15728640, 15728640, 196608, 196608, 819200, 819200, 1073877056, 0, 64, 268435456, 1073741824, 64, 1073741824, 256, 1073877056, 134217728 };
  }
  
  private static void jj_la1_init_1()
  {
    jj_la1_1 = new int[] { 12, 12, 12, 3, 3, 3, 306, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 306, 272, 32, 0, 2, 0, 34, 0, 306, 0 };
  }
  
  public ActionParser(InputStream paramInputStream)
  {
    this(paramInputStream, null);
  }
  
  public ActionParser(InputStream paramInputStream, String paramString)
  {
    try
    {
      this.jj_input_stream = new SimpleCharStream(paramInputStream, paramString, 1, 1);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException);
    }
    this.token_source = new ActionParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    for (int i = 0; i < 28; i++) {
      this.jj_la1[i] = -1;
    }
    for (int i = 0; i < this.jj_2_rtns.length; i++) {
      this.jj_2_rtns[i] = new JJCalls();
    }
  }
  
  public void ReInit(InputStream paramInputStream)
  {
    ReInit(paramInputStream, null);
  }
  
  public void ReInit(InputStream paramInputStream, String paramString)
  {
    try
    {
      this.jj_input_stream.ReInit(paramInputStream, paramString, 1, 1);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException);
    }
    this.token_source.ReInit(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
    this.jj_gen = 0;
    for (int i = 0; i < 28; i++) {
      this.jj_la1[i] = -1;
    }
    for (int i = 0; i < this.jj_2_rtns.length; i++) {
      this.jj_2_rtns[i] = new JJCalls();
    }
  }
  
  public ActionParser(Reader paramReader)
  {
    this.jj_input_stream = new SimpleCharStream(paramReader, 1, 1);
    this.token_source = new ActionParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    for (int i = 0; i < 28; i++) {
      this.jj_la1[i] = -1;
    }
    for (int i = 0; i < this.jj_2_rtns.length; i++) {
      this.jj_2_rtns[i] = new JJCalls();
    }
  }
  
  public void ReInit(Reader paramReader)
  {
    this.jj_input_stream.ReInit(paramReader, 1, 1);
    this.token_source.ReInit(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
    this.jj_gen = 0;
    for (int i = 0; i < 28; i++) {
      this.jj_la1[i] = -1;
    }
    for (int i = 0; i < this.jj_2_rtns.length; i++) {
      this.jj_2_rtns[i] = new JJCalls();
    }
  }
  
  public ActionParser(ActionParserTokenManager paramActionParserTokenManager)
  {
    this.token_source = paramActionParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    for (int i = 0; i < 28; i++) {
      this.jj_la1[i] = -1;
    }
    for (int i = 0; i < this.jj_2_rtns.length; i++) {
      this.jj_2_rtns[i] = new JJCalls();
    }
  }
  
  public void ReInit(ActionParserTokenManager paramActionParserTokenManager)
  {
    this.token_source = paramActionParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
    this.jj_gen = 0;
    for (int i = 0; i < 28; i++) {
      this.jj_la1[i] = -1;
    }
    for (int i = 0; i < this.jj_2_rtns.length; i++) {
      this.jj_2_rtns[i] = new JJCalls();
    }
  }
  
  private Token jj_consume_token(int paramInt)
    throws ParseException
  {
    Token localToken;
    if ((localToken = this.token).next != null) {
      this.token = this.token.next;
    } else {
      this.token = (this.token.next = this.token_source.getNextToken());
    }
    this.jj_ntk = -1;
    if (this.token.kind == paramInt)
    {
      this.jj_gen += 1;
      if (++this.jj_gc > 100)
      {
        this.jj_gc = 0;
        for (int i = 0; i < this.jj_2_rtns.length; i++) {
          for (JJCalls localJJCalls = this.jj_2_rtns[i]; localJJCalls != null; localJJCalls = localJJCalls.next) {
            if (localJJCalls.gen < this.jj_gen) {
              localJJCalls.first = null;
            }
          }
        }
      }
      return this.token;
    }
    this.token = localToken;
    this.jj_kind = paramInt;
    throw generateParseException();
  }
  
  private boolean jj_scan_token(int paramInt)
  {
    if (this.jj_scanpos == this.jj_lastpos)
    {
      this.jj_la -= 1;
      if (this.jj_scanpos.next == null) {
        this.jj_lastpos = (this.jj_scanpos = this.jj_scanpos.next = this.token_source.getNextToken());
      } else {
        this.jj_lastpos = (this.jj_scanpos = this.jj_scanpos.next);
      }
    }
    else
    {
      this.jj_scanpos = this.jj_scanpos.next;
    }
    if (this.jj_rescan)
    {
      int i = 0;
      Token localToken;
      for ( localToken = this.token; (localToken != null) && (localToken != this.jj_scanpos); localToken = localToken.next) {
        i++;
      }
      if (localToken != null) {
        jj_add_error_token(paramInt, i);
      }
    }
    if (this.jj_scanpos.kind != paramInt) {
      return true;
    }
    if ((this.jj_la == 0) && (this.jj_scanpos == this.jj_lastpos)) {
      throw this.jj_ls;
    }
    return false;
  }
  
  public final Token getNextToken()
  {
    if (this.token.next != null) {
      this.token = this.token.next;
    } else {
      this.token = (this.token.next = this.token_source.getNextToken());
    }
    this.jj_ntk = -1;
    this.jj_gen += 1;
    return this.token;
  }
  
  public final Token getToken(int paramInt)
  {
    Token localToken = this.token;
    for (int i = 0; i < paramInt; i++) {
      if (localToken.next != null) {
        localToken = localToken.next;
      } else {
        localToken = localToken.next = this.token_source.getNextToken();
      }
    }
    return localToken;
  }
  
  private int jj_ntk()
  {
    if ((this.jj_nt = this.token.next) == null) {
      return this.jj_ntk = (this.token.next = this.token_source.getNextToken()).kind;
    }
    return this.jj_ntk = this.jj_nt.kind;
  }
  
  private void jj_add_error_token(int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 100) {
      return;
    }
    if (paramInt2 == this.jj_endpos + 1)
    {
      this.jj_lasttokens[(this.jj_endpos++)] = paramInt1;
    }
    else if (this.jj_endpos != 0)
    {
      this.jj_expentry = new int[this.jj_endpos];
      for (int i = 0; i < this.jj_endpos; i++) {
        this.jj_expentry[i] = this.jj_lasttokens[i];
      }
      Iterator localIterator = this.jj_expentries.iterator();
      while (localIterator.hasNext())
      {
        int[] arrayOfInt = (int[])localIterator.next();
        if (arrayOfInt.length == this.jj_expentry.length)
        {
          for (int j = 0;; j++)
          {
            if (j >= this.jj_expentry.length) {
                System.out.println("grafchart.sfc.actions.ActionParser.jj_add_error_token() - 2583");
                //break label163;
                break;
            }
            if (arrayOfInt[j] != this.jj_expentry[j]) {
              break;
            }
          }
          label163:
          this.jj_expentries.add(this.jj_expentry);
          break;
        }
      }
      if (paramInt2 != 0) {
        this.jj_lasttokens[((this.jj_endpos = paramInt2) - 1)] = paramInt1;
      }
    }
  }
  
  public ParseException generateParseException()
  {
    this.jj_expentries.clear();
    boolean[] arrayOfBoolean = new boolean[42];
    if (this.jj_kind >= 0)
    {
      arrayOfBoolean[this.jj_kind] = true;
      this.jj_kind = -1;
    }
    for (int i = 0; i < 28; i++) {
      if (this.jj_la1[i] == this.jj_gen) {
        for (int j = 0; j < 32; j++)
        {
          if ((jj_la1_0[i] & 1 << j) != 0) {
            arrayOfBoolean[j] = true;
          }
          if ((jj_la1_1[i] & 1 << j) != 0) {
            arrayOfBoolean[(32 + j)] = true;
          }
        }
      }
    }
    for (int i = 0; i < 42; i++) {
      if (arrayOfBoolean[i] != false)
      {
        this.jj_expentry = new int[1];
        this.jj_expentry[0] = i;
        this.jj_expentries.add(this.jj_expentry);
      }
    }
    this.jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] arrayOfInt = new int[this.jj_expentries.size()][];
    for (int j = 0; j < this.jj_expentries.size(); j++) {
      arrayOfInt[j] = ((int[])this.jj_expentries.get(j));
    }
    return new ParseException(this.token, arrayOfInt, tokenImage);
  }
  
  public final void enable_tracing() {}
  
  public final void disable_tracing() {}
  
  private void jj_rescan_token()
  {
    this.jj_rescan = true;
    for (int i = 0; i < 2; i++) {
      try
      {
        JJCalls localJJCalls = this.jj_2_rtns[i];
        do
        {
          if (localJJCalls.gen > this.jj_gen)
          {
            this.jj_la = localJJCalls.arg;
            this.jj_lastpos = (this.jj_scanpos = localJJCalls.first);
            switch (i)
            {
            case 0: 
              jj_3_1();
              break;
            case 1: 
              jj_3_2();
            }
          }
          localJJCalls = localJJCalls.next;
        } while (localJJCalls != null);
      }
      catch (LookaheadSuccess localLookaheadSuccess) {}
    }
    this.jj_rescan = false;
  }
  
  private void jj_save(int paramInt1, int paramInt2)
  {
      JJCalls localJJCalls;
    for (localJJCalls = this.jj_2_rtns[paramInt1]; localJJCalls.gen > this.jj_gen; localJJCalls = localJJCalls.next) {
      if (localJJCalls.next == null)
      {
        localJJCalls = localJJCalls.next = new JJCalls();
        break;
      }
    }
    localJJCalls.gen = (this.jj_gen + paramInt2 - this.jj_la);
    localJJCalls.first = this.token;
    localJJCalls.arg = paramInt2;
  }
  
  static
  {
    jj_la1_init_0();
    jj_la1_init_1();
  }
  
  static final class JJCalls
  {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }
  
  private static final class LookaheadSuccess
    extends Error
  {}
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/actions/ActionParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */