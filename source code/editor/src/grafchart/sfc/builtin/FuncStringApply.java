package grafchart.sfc.builtin;

import grafchart.sfc.Utils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class FuncStringApply
  extends AbstractExecutable
{
  public FuncStringApply()
  {
    super("stringApply", 0);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    String str = paramArrayOfEvaluatable[2].evaluateString();
    boolean bool = false;
    if (str.equals("boolean"))
    {
      Class localClass = String.class;
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      int j = paramArrayOfEvaluatable.length;
      int i = 3;
      while (i < j)
      {
          String localObject = paramArrayOfEvaluatable[i].evaluateString();
        if (((String)localObject).equals("string"))
        {
          localArrayList1.add(String.class);
          localArrayList2.add(paramArrayOfEvaluatable[(i + 1)].evaluateString());
        }
        else if (((String)localObject).equals("int"))
        {
          localArrayList1.add(Integer.TYPE);
          localArrayList2.add(new Integer(paramArrayOfEvaluatable[(i + 1)].evaluateInt()));
        }
        else if (((String)localObject).equals("real"))
        {
          localArrayList1.add(Double.TYPE);
          localArrayList2.add(new Double(paramArrayOfEvaluatable[(i + 1)].evaluateReal()));
        }
        else if (((String)localObject).equals("boolean"))
        {
          localArrayList1.add(Boolean.TYPE);
          localArrayList2.add(new Boolean(paramArrayOfEvaluatable[(i + 1)].evaluateBoolean()));
        }
        i += 2;
      }
      Object localObject = new Class[localArrayList1.size()];
      localObject = (Class[])localArrayList1.toArray((Object[])localObject);
      Object[] arrayOfObject = localArrayList2.toArray();
      try
      {
        Method localMethod = localClass.getMethod(paramArrayOfEvaluatable[1].evaluateString(), (Class[])localObject);
        if (str.equals("boolean")) {
          bool = ((Boolean)localMethod.invoke(paramArrayOfEvaluatable[0].evaluateString(), arrayOfObject)).booleanValue();
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Utils.writeException(localNoSuchMethodException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Utils.writeException(localIllegalAccessException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Utils.writeException(localInvocationTargetException);
      }
      return bool;
    }
    return bool;
  }
  
  public double internalExecuteReal(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    double d = 0.0D;
    String str = paramArrayOfEvaluatable[2].evaluateString();
    if ((str.equals("real")) || (str.equals("int")))
    {
      Class localClass = String.class;
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      int j = paramArrayOfEvaluatable.length;
      int i = 3;
      while (i < j)
      {
          String localObject = paramArrayOfEvaluatable[i].evaluateString();
        if (((String)localObject).equals("string"))
        {
          localArrayList1.add(String.class);
          localArrayList2.add(paramArrayOfEvaluatable[(i + 1)].evaluateString());
        }
        else if (((String)localObject).equals("int"))
        {
          localArrayList1.add(Integer.TYPE);
          localArrayList2.add(new Integer(paramArrayOfEvaluatable[(i + 1)].evaluateInt()));
        }
        else if (((String)localObject).equals("real"))
        {
          localArrayList1.add(Double.TYPE);
          localArrayList2.add(new Double(paramArrayOfEvaluatable[(i + 1)].evaluateReal()));
        }
        else if (((String)localObject).equals("boolean"))
        {
          localArrayList1.add(Boolean.TYPE);
          localArrayList2.add(new Boolean(paramArrayOfEvaluatable[(i + 1)].evaluateBoolean()));
        }
        i += 2;
      }
      Object localObject = new Class[localArrayList1.size()];
      localObject = (Class[])localArrayList1.toArray((Object[])localObject);
      Object[] arrayOfObject = localArrayList2.toArray();
      try
      {
        Method localMethod = localClass.getMethod(paramArrayOfEvaluatable[1].evaluateString(), (Class[])localObject);
        if (str.equals("real")) {
          d = ((Double)localMethod.invoke(paramArrayOfEvaluatable[0].evaluateString(), arrayOfObject)).doubleValue();
        } else {
          d = ((Integer)localMethod.invoke(paramArrayOfEvaluatable[0].evaluateString(), arrayOfObject)).intValue();
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Utils.writeException(localNoSuchMethodException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Utils.writeException(localIllegalAccessException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Utils.writeException(localInvocationTargetException);
      }
      return d;
    }
    return d;
  }
  
  public String internalExecuteString(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    String str1 = paramArrayOfEvaluatable[2].evaluateString();
    String str2 = "";
    if (str1.equals("string"))
    {
      Class localClass = String.class;
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      int j = paramArrayOfEvaluatable.length;
      int i = 3;
      while (i < j)
      {
          String localObject = paramArrayOfEvaluatable[i].evaluateString();
        if (((String)localObject).equals("string"))
        {
          localArrayList1.add(String.class);
          localArrayList2.add(paramArrayOfEvaluatable[(i + 1)].evaluateString());
        }
        else if (((String)localObject).equals("int"))
        {
          localArrayList1.add(Integer.TYPE);
          localArrayList2.add(new Integer(paramArrayOfEvaluatable[(i + 1)].evaluateInt()));
        }
        else if (((String)localObject).equals("real"))
        {
          localArrayList1.add(Double.TYPE);
          localArrayList2.add(new Double(paramArrayOfEvaluatable[(i + 1)].evaluateReal()));
        }
        else if (((String)localObject).equals("boolean"))
        {
          localArrayList1.add(Boolean.TYPE);
          localArrayList2.add(new Boolean(paramArrayOfEvaluatable[(i + 1)].evaluateBoolean()));
        }
        i += 2;
      }
      Object localObject = new Class[localArrayList1.size()];
      localObject = (Class[])localArrayList1.toArray((Object[])localObject);
      Object[] arrayOfObject = localArrayList2.toArray();
      try
      {
        Method localMethod = localClass.getMethod(paramArrayOfEvaluatable[1].evaluateString(), (Class[])localObject);
        if (str1.equals("string")) {
          str2 = (String)localMethod.invoke(paramArrayOfEvaluatable[0].evaluateString(), arrayOfObject);
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Utils.writeException(localNoSuchMethodException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Utils.writeException(localIllegalAccessException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Utils.writeException(localInvocationTargetException);
      }
      return str2;
    }
    return str2;
  }
  
  public boolean isValidNrOfArguments(int paramInt)
  {
    return paramInt > 0;
  }
  
  public String getCorrectNrOfArguments()
  {
    return ">0";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncStringApply.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */