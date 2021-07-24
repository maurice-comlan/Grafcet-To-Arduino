package grafchart.sfc.builtin;

import grafchart.sfc.Editor;

public class FuncNew
  extends AbstractExecutable
{
  public FuncNew()
  {
    super("new", 5, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Editor.singleton.newObject(paramArrayOfEvaluatable[0].evaluateString(), paramArrayOfEvaluatable[1].evaluateString(), paramArrayOfEvaluatable[2].evaluateString(), paramArrayOfEvaluatable[3].evaluateInt(), paramArrayOfEvaluatable[4].evaluateInt());
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncNew.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */