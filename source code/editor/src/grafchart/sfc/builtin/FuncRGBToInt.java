package grafchart.sfc.builtin;

import java.awt.Color;

public class FuncRGBToInt
  extends AbstractExecutable
{
  public FuncRGBToInt()
  {
    super("RGBToInt", 3);
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    Color localColor = new Color((float)paramArrayOfEvaluatable[0].evaluateReal(), (float)paramArrayOfEvaluatable[1].evaluateReal(), (float)paramArrayOfEvaluatable[2].evaluateReal());
    return localColor.getRGB();
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncRGBToInt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */