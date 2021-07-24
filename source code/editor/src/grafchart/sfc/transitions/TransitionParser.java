package grafchart.sfc.transitions;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionParser
  implements TransitionParserTreeConstants, TransitionParserConstants
{
  protected JJTTransitionParserState jjtree = new JJTTransitionParserState();
  public TransitionParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token;
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos;
  private Token jj_lastpos;
  private int jj_la;
  private int jj_gen;
  private final int[] jj_la1 = new int[18];
  private static int[] jj_la1_0;
  private static int[] jj_la1_1;
  private final JJCalls[] jj_2_rtns = new JJCalls[1];
  private boolean jj_rescan = false;
  private int jj_gc = 0;
  private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  private java.util.List<int[]> jj_expentries = new ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;
  
  public final Start Start()
    throws ParseException
  {
    Start localStart1 = new Start(0);
    int i = 1;
    this.jjtree.openNodeScope(localStart1);
    try
    {
      expression();
      jj_consume_token(0);
      this.jjtree.closeNodeScope(localStart1, true);
      i = 0;
      Start localStart2 = localStart1;
      return localStart2;
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localStart1);
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
        this.jjtree.closeNodeScope(localStart1, true);
      }
    }
  }
  
  public final void expression()
    throws ParseException
  {
    logicalor();
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 24: 
      jj_consume_token(24);
      expression();
      jj_consume_token(9);
      ConditionalExpression localConditionalExpression = new ConditionalExpression(2);
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
      this.jj_la1[0] = this.jj_gen;
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
      case 12: 
        break;
      default: 
        this.jj_la1[1] = this.jj_gen;
        break;
      }
      jj_consume_token(12);
      Or localOr = new Or(3);
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
      case 11: 
        break;
      default: 
        this.jj_la1[2] = this.jj_gen;
        break;
      }
      jj_consume_token(11);
      And localAnd = new And(4);
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
      case 22: 
      case 23: 
        break;
      default: 
        this.jj_la1[3] = this.jj_gen;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 22: 
        jj_consume_token(22);
        EQ localEQ = new EQ(5);
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
      case 23: 
        jj_consume_token(23);
        NEQ localNEQ = new NEQ(6);
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
        this.jj_la1[4] = this.jj_gen;
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
      case 18: 
      case 19: 
      case 20: 
      case 21: 
        break;
      default: 
        this.jj_la1[5] = this.jj_gen;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 19: 
        jj_consume_token(19);
        LT localLT = new LT(7);
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
      case 21: 
        jj_consume_token(21);
        GT localGT = new GT(8);
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
      case 18: 
        jj_consume_token(18);
        LE localLE = new LE(9);
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
      case 20: 
        jj_consume_token(20);
        GE localGE = new GE(10);
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
        this.jj_la1[6] = this.jj_gen;
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
      case 14: 
      case 15: 
        break;
      default: 
        this.jj_la1[7] = this.jj_gen;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 14: 
        jj_consume_token(14);
        Plus localPlus = new Plus(11);
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
      case 15: 
        jj_consume_token(15);
        Minus localMinus = new Minus(12);
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
        this.jj_la1[8] = this.jj_gen;
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
      case 13: 
      case 16: 
      case 17: 
        break;
      case 14: 
      case 15: 
      default: 
        this.jj_la1[9] = this.jj_gen;
        break;
      }
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 13: 
        jj_consume_token(13);
        Mult localMult = new Mult(13);
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
      case 16: 
        jj_consume_token(16);
        Div localDiv = new Div(14);
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
      case 17: 
        jj_consume_token(17);
        Mod localMod = new Mod(15);
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
      case 14: 
      case 15: 
      default: 
        this.jj_la1[10] = this.jj_gen;
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
    case 15: 
      jj_consume_token(15);
      Negation localNegation = new Negation(16);
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
    case 10: 
      jj_consume_token(10);
      Not localNot = new Not(17);
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
    case 16: 
      eventup();
      break;
    case 27: 
      eventdown();
      break;
    case 6: 
    case 28: 
    case 29: 
    case 32: 
      dotexpression();
      break;
    case 7: 
    case 8: 
    case 9: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 30: 
    case 31: 
    default: 
      this.jj_la1[11] = this.jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }
  
  public final void eventup()
    throws ParseException
  {
    EventUp localEventUp = new EventUp(18);
    int i = 1;
    this.jjtree.openNodeScope(localEventUp);
    try
    {
      jj_consume_token(16);
      dotexpression();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localEventUp);
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
        this.jjtree.closeNodeScope(localEventUp, true);
      }
    }
  }
  
  public final void eventdown()
    throws ParseException
  {
    EventDown localEventDown = new EventDown(19);
    int i = 1;
    this.jjtree.openNodeScope(localEventDown);
    try
    {
      jj_consume_token(27);
      dotexpression();
    }
    catch (Throwable localThrowable)
    {
      if (i != 0)
      {
        this.jjtree.clearNodeScope(localEventDown);
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
        this.jjtree.closeNodeScope(localEventDown, true);
      }
    }
  }
  
  public final void primaryexpression()
    throws ParseException
  {
    switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
    {
    case 32: 
      String();
      break;
    case 28: 
      Number();
      break;
    default: 
      this.jj_la1[12] = this.jj_gen;
      if (jj_2_1(2)) {
        Function();
      } else {
        switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
        {
        case 29: 
          VarOptReferences();
          break;
        case 6: 
          jj_consume_token(6);
          expression();
          jj_consume_token(7);
          break;
        default: 
          this.jj_la1[13] = this.jj_gen;
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
    Number localNumber = new Number(20);
    int i = 1;
    this.jjtree.openNodeScope(localNumber);
    try
    {
      Token localToken = jj_consume_token(28);
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
      case 26: 
        break;
      default: 
        this.jj_la1[14] = this.jj_gen;
        break;
      }
      Reference localReference = new Reference(21);
      int i = 1;
      this.jjtree.openNodeScope(localReference);
      try
      {
        jj_consume_token(26);
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
    Var localVar = new Var(22);
    int i = 1;
    this.jjtree.openNodeScope(localVar);
    try
    {
      Token localToken = jj_consume_token(29);
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
    StringExpr localStringExpr = new StringExpr(23);
    int i = 1;
    this.jjtree.openNodeScope(localStringExpr);
    try
    {
      Token localToken = jj_consume_token(32);
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
    Function localFunction = new Function(24);
    int i = 1;
    this.jjtree.openNodeScope(localFunction);
    try
    {
      Token localToken = jj_consume_token(29);
      localFunction.setID(localToken.image);
      jj_consume_token(6);
      Arguments();
      jj_consume_token(7);
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
    List localList = new List(25);
    int i = 1;
    this.jjtree.openNodeScope(localList);
    try
    {
      switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
      {
      case 6: 
      case 10: 
      case 15: 
      case 16: 
      case 27: 
      case 28: 
      case 29: 
      case 32: 
        expression();
        for (;;)
        {
          switch (this.jj_ntk == -1 ? jj_ntk() : this.jj_ntk)
          {
          case 8: 
            break;
          default: 
            this.jj_la1[15] = this.jj_gen;
            break;
          }
          jj_consume_token(8);
          expression();
        }
      }
      this.jj_la1[16] = this.jj_gen;
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
      case 25: 
        break;
      default: 
        this.jj_la1[17] = this.jj_gen;
        break;
      }
      jj_consume_token(25);
      Dot localDot = new Dot(26);
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
  
  private boolean jj_3R_10()
  {
    if (jj_scan_token(29)) {
      return true;
    }
    return jj_scan_token(6);
  }
  
  private boolean jj_3_1()
  {
    return jj_3R_10();
  }
  
  private static void jj_la1_init_0()
  {
    jj_la1_0 = new int[] { 16777216, 4096, 2048, 12582912, 12582912, 3932160, 3932160, 49152, 49152, 204800, 204800, 939623488, 268435456, 536870976, 67108864, 256, 939623488, 33554432 };
  }
  
  private static void jj_la1_init_1()
  {
    jj_la1_1 = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0 };
  }
  
  public TransitionParser(InputStream paramInputStream)
  {
    this(paramInputStream, null);
  }
  
  public TransitionParser(InputStream paramInputStream, String paramString)
  {
    try
    {
      this.jj_input_stream = new SimpleCharStream(paramInputStream, paramString, 1, 1);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException);
    }
    this.token_source = new TransitionParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    for (int i = 0; i < 18; i++) {
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
    for (int i = 0; i < 18; i++) {
      this.jj_la1[i] = -1;
    }
    for (int i = 0; i < this.jj_2_rtns.length; i++) {
      this.jj_2_rtns[i] = new JJCalls();
    }
  }
  
  public TransitionParser(Reader paramReader)
  {
    this.jj_input_stream = new SimpleCharStream(paramReader, 1, 1);
    this.token_source = new TransitionParserTokenManager(this.jj_input_stream);
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    for (int i = 0; i < 18; i++) {
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
    for (int i = 0; i < 18; i++) {
      this.jj_la1[i] = -1;
    }
    for (int i = 0; i < this.jj_2_rtns.length; i++) {
      this.jj_2_rtns[i] = new JJCalls();
    }
  }
  
  public TransitionParser(TransitionParserTokenManager paramTransitionParserTokenManager)
  {
    this.token_source = paramTransitionParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jj_gen = 0;
    for (int i = 0; i < 18; i++) {
      this.jj_la1[i] = -1;
    }
    for (int i = 0; i < this.jj_2_rtns.length; i++) {
      this.jj_2_rtns[i] = new JJCalls();
    }
  }
  
  public void ReInit(TransitionParserTokenManager paramTransitionParserTokenManager)
  {
    this.token_source = paramTransitionParserTokenManager;
    this.token = new Token();
    this.jj_ntk = -1;
    this.jjtree.reset();
    this.jj_gen = 0;
    for (int i = 0; i < 18; i++) {
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
      for (localToken = this.token; (localToken != null) && (localToken != this.jj_scanpos); localToken = localToken.next) {
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
                System.out.println("grafchart.sfc.transitions.TransitionParser.jj_add_error_token() - 1469");
              break;// label163;
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
    boolean[] arrayOfBoolean = new boolean[34];
    if (this.jj_kind >= 0)
    {
      arrayOfBoolean[this.jj_kind] = true;
      this.jj_kind = -1;
    }
    for (int i = 0; i < 18; i++) {
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
    for (int i = 0; i < 34; i++) {
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
    for (int i = 0; i < 1; i++) {
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/transitions/TransitionParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */