package grafchart.sfc.builtin;

import java.awt.Color;

public class FuncIntToBlue
  extends AbstractExecutable
{
  public FuncIntToBlue()
  {
    super("IntToBlue", 1);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Color localColor = new Color(paramArrayOfEvaluatable[0].evaluateInt());
    return localColor.getBlue();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncIntToBlue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */