package grafchart.util;

import com.nwoods.jgo.JGoText;
import grafchart.sfc.Editor;
import grafchart.sfc.SymbolTableObject;
import grafchart.sfc.Utils;
import grafchart.sfc.actions.ActionParser;
import grafchart.sfc.actions.Goal;
import grafchart.sfc.actions.ParseException;
import grafchart.sfc.actions.Statement;
import java.awt.Color;
import java.io.StringReader;

public class ActionCompiler
{
  public static Statement compileStatement(String paramString1, SymbolTableObject paramSymbolTableObject, String paramString2, JGoText paramJGoText)
  {
    return (Statement)compile(paramString1, paramSymbolTableObject, CompileType.Statement, paramString2, paramJGoText);
  }
  
  public static Goal compile(String paramString1, SymbolTableObject paramSymbolTableObject, CompileType paramCompileType, String paramString2, JGoText paramJGoText)
  {
    Object localObject = null;
    ActionParser localActionParser = new ActionParser(new StringReader(paramString1));
    try
    {
      switch (paramCompileType)
      {
      case ProcCall: 
        localObject = localActionParser.ProcCall();
        break;
      case ProcParam: 
        localObject = localActionParser.ProcParam();
        break;
      case SingleExpression: 
        localObject = localActionParser.SingleExpression();
        break;
      case Statement: 
        localObject = localActionParser.Statement();
        break;
      default: 
        String str = "ActionCompiler called with unknown compileType \"" + paramCompileType + "\" for " + paramString2 + ".";
        Utils.writeInternalError(str);
        Editor.giveWarning("Internal Error: " + str);
      }
    }
    catch (ParseException localParseException)
    {
      Editor.giveWarning("Parsing problem for " + paramString2 + ": " + localParseException.getMessage());
    }
    catch (Throwable localThrowable1)
    {
      Utils.writeException(localThrowable1);
      Editor.giveWarning("Unhandled exception during actions parsing of " + paramString2 + ".");
    }
    if (localObject != null) {
      try
      {
        boolean bool = ((Goal)localObject).compile(paramSymbolTableObject);
        if (!((Goal)localObject).messages().isEmpty())
        {
          Editor.giveInformation("Compilation messages for " + paramString2 + ".");
          Editor.giveWarnings(((Goal)localObject).messages());
        }
        if (!bool) {
          localObject = null;
        }
      }
      catch (Throwable localThrowable2)
      {
        Editor.giveWarning("Unhandled exception during compilation of " + paramString2 + ".");
        localThrowable2.printStackTrace();
        localObject = null;
      }
    }
    if (localObject != null) {
      setLabelColor(paramJGoText, Editor.black);
    } else {
      setLabelColor(paramJGoText, Editor.red);
    }
    return (Goal)localObject;
  }
  
  private static void setLabelColor(JGoText paramJGoText, Color paramColor)
  {
    if (paramJGoText != null) {
      paramJGoText.setTextColor(paramColor);
    }
  }
  
  public static enum CompileType
  {
    ProcCall,  ProcParam,  SingleExpression,  Statement;
    
    private CompileType() {}
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/util/ActionCompiler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */