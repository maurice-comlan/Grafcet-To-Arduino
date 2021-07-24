package grafchart.sfc.builtin;

import grafchart.sfc.Editor;

public class FuncRemovePalette
  extends AbstractExecutable
{
  public FuncRemovePalette()
  {
    super("removePalette", 0, BuiltInFunctions.Language.Action);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Editor.hidePaletteAction();
    return 0.0D;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncRemovePalette.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */