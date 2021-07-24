package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.actions.SingleExpression;
import java.awt.Color;
import java.awt.Point;

public class IntegerVariable
  extends InternalVariable
{
  public int val = 0;
  public int oldval = 0;
  private int initialValue = 0;
  private boolean hasInitialValue = false;
  
  public IntegerVariable() {}
  
  public IntegerVariable(Point paramPoint)
  {
    super(paramPoint, "Int ", "0");
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    IntegerVariable localIntegerVariable = (IntegerVariable)paramJGoArea;
    localIntegerVariable.val = this.val;
    localIntegerVariable.oldval = this.oldval;
    localIntegerVariable.initialValue = this.initialValue;
    localIntegerVariable.hasInitialValue = this.hasInitialValue;
  }
  
  public boolean isInteger()
  {
    return true;
  }
  
  private void setText(String paramString)
  {
    this.myValue.setText(paramString);
    this.myValue.setTextColor(Color.BLACK);
  }
  
  public boolean getBoolVal()
  {
    return getIntVal() == 1;
  }
  
  public boolean getOldBoolVal()
  {
    return getOldIntVal() == 1;
  }
  
  public int getIntVal()
  {
    if (this.redirect == null) {
      return this.val;
    }
    int i = this.redirect.getIntVal();
    setText("" + i);
    return i;
  }
  
  public double getRealVal()
  {
    return getIntVal();
  }
  
  public int getOldIntVal()
  {
    if (this.redirect == null) {
      return this.oldval;
    }
    int i = this.redirect.getOldIntVal();
    return i;
  }
  
  public double getOldRealVal()
  {
    return getOldIntVal();
  }
  
  public String getStringVal()
  {
    return "" + getIntVal();
  }
  
  public String getOldStringVal()
  {
    return "" + getOldIntVal();
  }
  
  public void setStoredIntAction(int paramInt)
  {
    setText("" + paramInt);
    Inspector.refresh();
    if (this.redirect == null)
    {
      this.oldval = this.val;
      this.val = paramInt;
    }
    else
    {
      this.redirect.setStoredIntAction(paramInt);
    }
  }
  
  public void setStoredRealAction(double paramDouble)
  {
    setStoredIntAction((int)paramDouble);
    Inspector.refresh();
  }
  
  public void setStoredBoolAction(boolean paramBoolean) {}
  
  public void setStoredStringAction(String paramString) {}
  
  public void initializeDisplay()
  {
    getIntVal();
  }
  
  public void initialize()
  {
    if (this.hasInitialValue) {
      setStoredIntAction(this.initialValue);
    }
    getIntVal();
  }
  
  public String getHelpID()
  {
    return "LangRef_Var_IntegerVariable";
  }
  
  public String toString()
  {
    return getName() + ": " + getIntVal();
  }
  
  public void updateValue()
  {
    if (this.node != null) {
      setStoredIntAction(this.node.evaluateInt());
    }
  }
  
  public void setInitialValue(String paramString)
    throws IllegalValueException
  {
    this.hasInitialValue = false;
    if (!paramString.isEmpty()) {
      try
      {
        this.initialValue = Integer.parseInt(paramString);
        this.hasInitialValue = true;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new IllegalValueException();
      }
    }
  }
  
  public String getInitialValue()
  {
    return this.hasInitialValue ? Integer.toString(this.initialValue) : "";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/IntegerVariable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */