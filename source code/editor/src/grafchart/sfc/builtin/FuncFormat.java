package grafchart.sfc.builtin;

import grafchart.sfc.RealVariable;
import java.text.DecimalFormat;

public class FuncFormat
  extends AbstractExecutable
{
  public FuncFormat()
  {
    super("format", 1);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    return RealVariable.df.format(paramArrayOfEvaluatable[0].evaluateReal());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncFormat.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */