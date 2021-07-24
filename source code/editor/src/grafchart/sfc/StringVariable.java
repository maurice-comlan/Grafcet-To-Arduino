package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.actions.SingleExpression;
import java.awt.Point;

public class StringVariable
  extends InternalVariable
{
  public String val = new String("");
  public String oldval = new String("");
  private String initialValue = "";
  
  public StringVariable() {}
  
  public StringVariable(Point paramPoint)
  {
    super(paramPoint, "Str ", "");
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    StringVariable localStringVariable = (StringVariable)paramJGoArea;
    localStringVariable.val = this.val;
    localStringVariable.oldval = this.oldval;
    localStringVariable.initialValue = this.initialValue;
  }
  
  public boolean isBoolean()
  {
    return false;
  }
  
  public boolean isInteger()
  {
    return false;
  }
  
  public boolean isString()
  {
    return true;
  }
  
  public boolean getBoolVal()
  {
    return getStringVal().equals("1");
  }
  
  public boolean getOldBoolVal()
  {
    return getOldStringVal().equals("1");
  }
  
  public int getIntVal()
  {
    String str = getStringVal();
    int i = 0;
    try
    {
      i = Integer.parseInt(str);
    }
    catch (NumberFormatException localNumberFormatException) {}
    return i;
  }
  
  public int getOldIntVal()
  {
    String str = getOldStringVal();
    int i = 0;
    try
    {
      i = Integer.parseInt(str);
    }
    catch (NumberFormatException localNumberFormatException) {}
    return i;
  }
  
  public double getRealVal()
  {
    String str = getStringVal();
    double d = 0.0D;
    try
    {
      d = Double.parseDouble(str);
    }
    catch (Exception localException) {}
    return d;
  }
  
  public double getOldRealVal()
  {
    String str = getOldStringVal();
    double d = 0.0D;
    try
    {
      d = Double.parseDouble(str);
    }
    catch (Exception localException) {}
    return d;
  }
  
  public String getStringVal()
  {
    if (this.redirect == null) {
      return this.val;
    }
    String str = this.redirect.getStringVal();
    this.myValue.setText(str);
    return str;
  }
  
  public String getOldStringVal()
  {
    if (this.redirect == null) {
      return this.oldval;
    }
    return this.redirect.getOldStringVal();
  }
  
  public void setStoredIntAction(int paramInt) {}
  
  public void setStoredRealAction(double paramDouble) {}
  
  public void setStoredStringAction(String paramString)
  {
    this.myValue.setText(paramString);
    Inspector.refresh();
    if (this.redirect == null)
    {
      this.oldval = this.val;
      this.val = paramString;
    }
    else
    {
      this.redirect.setStoredStringAction(paramString);
    }
  }
  
  public void setStoredBoolAction(boolean paramBoolean) {}
  
  public void initialize()
  {
    if (!this.initialValue.equals("")) {
      setStoredStringAction(this.initialValue);
    }
    getStringVal();
  }
  
  public void initializeDisplay()
  {
    getStringVal();
  }
  
  public String getHelpID()
  {
    return "LangRef_Var_StringVariable";
  }
  
  public String toString()
  {
    return getName() + ": " + getStringVal();
  }
  
  public void updateValue()
  {
    if (this.node != null) {
      setStoredStringAction(this.node.evaluateString());
    }
  }
  
  public void setInitialValue(String paramString)
    throws IllegalValueException
  {
    this.initialValue = paramString;
  }
  
  public String getInitialValue()
  {
    return this.initialValue;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/StringVariable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */