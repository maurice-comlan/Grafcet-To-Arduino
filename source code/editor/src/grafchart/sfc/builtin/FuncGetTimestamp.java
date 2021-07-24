package grafchart.sfc.builtin;

import java.util.Date;
import java.util.GregorianCalendar;

public class FuncGetTimestamp
  extends AbstractExecutable
{
  public FuncGetTimestamp()
  {
    super("getTimestamp", 0);
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    localGregorianCalendar.setTime(new Date());
    int i = localGregorianCalendar.get(1);
    int j = localGregorianCalendar.get(2) + 1;
    int k = localGregorianCalendar.get(5);
    int m = localGregorianCalendar.get(11);
    int n = localGregorianCalendar.get(12);
    int i1 = localGregorianCalendar.get(13);
    return i + "-" + (j < 10 ? "0" + j : new StringBuilder().append("").append(j).toString()) + "-" + (k < 10 ? "0" + k : new StringBuilder().append("").append(k).toString()) + "T" + (m < 10 ? "0" + m : new StringBuilder().append("").append(m).toString()) + ":" + (n < 10 ? "0" + n : new StringBuilder().append("").append(n).toString()) + ":" + (i1 < 10 ? "0" + i1 : new StringBuilder().append("").append(i1).toString());
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncGetTimestamp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */