package grafchart.sfc.builtin;

import grafchart.sfc.Utils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class FuncApplyStaticMethod
  extends AbstractExecutable
{
  public FuncApplyStaticMethod()
  {
    super("applyStaticMethod", 0);
  }
  
  public boolean internalExecuteBool(Evaluatable[] paramArrayOfEvaluatable, Object paramObject)
  {
    String str = paramArrayOfEvaluatable[2].evaluateString();
    boolean bool = false;
    if (str.equals("boolean"))
    {
      Class localClass = null;
      try
      {
        localClass = Class.forName(paramArrayOfEvaluatable[0].evaluateString());
      }
      catch (Exception localException)
      {
        Utils.writeException(localException);
      }
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
          bool = ((Boolean)localMethod.invoke(null, arrayOfObject)).booleanValue();
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
    String str1 = paramArrayOfEvaluatable[2].evaluateString();
    if ((str1.equals("real")) || (str1.equals("int")))
    {
      Class localClass = null;
      String str2 = paramArrayOfEvaluatable[0].evaluateString();
      try
      {
        localClass = Class.forName(str2);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Utils.writeError("Class \"" + str2 + "\" not found.");
        return 0.0D;
      }
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
        if (str1.equals("real")) {
          d = ((Double)localMethod.invoke(null, arrayOfObject)).doubleValue();
        } else {
          d = ((Integer)localMethod.invoke(null, arrayOfObject)).intValue();
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
      Class localClass = null;
      try
      {
        localClass = Class.forName(paramArrayOfEvaluatable[0].evaluateString());
      }
      catch (Exception localException)
      {
        Utils.writeException(localException);
      }
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
          str2 = (String)localMethod.invoke(null, arrayOfObject);
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


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/builtin/FuncApplyStaticMethod.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */