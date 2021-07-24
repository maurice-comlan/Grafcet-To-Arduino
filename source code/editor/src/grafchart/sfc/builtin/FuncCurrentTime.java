package grafchart.sfc.builtin;

import java.text.DateFormat;
import java.util.GregorianCalendar;

public class FuncCurrentTime
  extends AbstractExecutable
{
  public FuncCurrentTime()
  {
    super("currentTime", 0);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    return DateFormat.getDateTimeInstance(2, 2).format(localGregorianCalendar.getTime());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncCurrentTime.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */