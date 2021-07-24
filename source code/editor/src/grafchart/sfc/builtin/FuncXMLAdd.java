package grafchart.sfc.builtin;

public class FuncXMLAdd
  extends AbstractExecutable
{
  public FuncXMLAdd()
  {
    super("xmlAdd", -1, BuiltInFunctions.Language.Action);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    String str1 = paramArrayOfEvaluatable[0].evaluateString();
    String str2 = paramArrayOfEvaluatable[1].evaluateString();
    String str3 = "";
    if (paramArrayOfEvaluatable.length > 2) {
      str3 = paramArrayOfEvaluatable[2].evaluateString();
    }
    return str3 + "<" + str1 + ">" + str2 + "</" + str1 + ">";
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    return (paramInt == 2) || (paramInt == 3);
  }
  
  public String getCorrectNrOfArguments()
  {
    return "2 or 3";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncXMLAdd.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */