package grafchart.sfc.builtin;

import grafchart.sfc.Editor;
import grafchart.sfc.Utils;

public abstract class AbstractExecutable
  implements Executable
{
  private String name;
  private int nParameters;
  BuiltInFunctions.Language validLanguage;
  
  public AbstractExecutable(String paramString, int paramInt)
  {
    this(paramString, paramInt, BuiltInFunctions.Language.All);
  }
  
  public AbstractExecutable(String paramString, int paramInt, BuiltInFunctions.Language paramLanguage)
  {
    this.name = paramString;
    this.nParameters = paramInt;
    this.validLanguage = paramLanguage;
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    return this.nParameters == paramInt;
  }
  
  public String getCorrectNrOfArguments()
  {
    return new Integer(this.nParameters).toString();
  }
  
  public boolean isValidCallObject(Object paramObject)
  {
    return paramObject == null;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public boolean isValidCall(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    if (!isValidCallObject(paramObject))
    {
      runtimeError("Invalid call object " + paramObject + " to function \"" + this.name + "\".");
      return false;
    }
    if (!isValidNrOfArguments(paramArrayOfEvaluatable.length))
    {
      runtimeError("Invalid number of arguments to function \"" + this.name + "\".");
      return false;
    }
    return true;
  }
  
  public boolean isValidLanguage(BuiltInFunctions.Language paramLanguage)
  {
    switch (this.validLanguage)
    {
    case Action: 
      return paramLanguage == BuiltInFunctions.Language.Action;
    case Transition: 
      return paramLanguage == BuiltInFunctions.Language.Transition;
    case All: 
      return true;
    }
    Utils.writeInternalError("Unhandled language \"" + this.validLanguage + "\".");
    return false;
  }
  
  public String compile(Evaluatable[] paramArrayOfEvaluatable, Object paramObject, BuiltInFunctions.Language paramLanguage)
  {
    if (!isValidLanguage(paramLanguage)) {
      return "\"" + this.name + "\" is not available in the " + paramLanguage + " language.";
    }
    if (!isValidCallObject(paramObject))
    {
      if (paramObject == null) {
        return "Method \"" + this.name + "\" must be called on an object.";
      }
      return "Invalid method \"" + this.name + "\" for object \"" + paramObject.toString() + "\".";
    }
    if (!isValidNrOfArguments(paramArrayOfEvaluatable.length)) {
      return "Illegal number of arguments to \"" + this.name + "\". Is " + paramArrayOfEvaluatable.length + ", expected " + getCorrectNrOfArguments() + ".";
    }
    return null;
  }
  
  public boolean executeBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    if (isValidCall(paramArrayOfEvaluatable, paramObject)) {
      return internalExecuteBool(paramArrayOfEvaluatable, paramObject);
    }
    return false;
  }
  
  public double executeReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    if (isValidCall(paramArrayOfEvaluatable, paramObject)) {
      return internalExecuteReal(paramArrayOfEvaluatable, paramObject);
    }
    return 0.0D;
  }
  
  public String executeString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    if (isValidCall(paramArrayOfEvaluatable, paramObject)) {
      return internalExecuteString(paramArrayOfEvaluatable, paramObject);
    }
    return "";
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    runtimeError("Illegal evaluation context. AbstractExecutable.internalExecuteBool() executed.");
    return false;
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    runtimeError("Illegal evaluation context. AbstractExecutable.internalExecuteReal() executed.");
    return 0.0D;
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    runtimeError("Illegal evaluation context. AbstractExecutable.internalExecuteString() executed.");
    return "";
  }
  
  public void error(String paramString)
  {
    Editor.clearMessages();
    Editor.writeMessage(paramString);
    Utils.writeError(paramString);
  }
  
  public void runtimeError(String paramString)
  {
    Utils.writeError("Error executing function/method \"" + this.name + "\": " + paramString);
  }
  
  public void runtimeWarning(String paramString)
  {
    Utils.writeWarning("Warning executing function/method \"" + this.name + "\": " + paramString);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/AbstractExecutable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */