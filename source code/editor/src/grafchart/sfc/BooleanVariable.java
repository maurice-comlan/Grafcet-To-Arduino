package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.actions.SingleExpression;
import java.awt.Point;

public class BooleanVariable
  extends InternalVariable
{
  public boolean val = false;
  public boolean oldval = false;
  public boolean setLow = false;
  public boolean setHigh = false;
  private boolean initialValue = false;
  private boolean hasInitialValue = false;
  
  public BooleanVariable() {}
  
  public BooleanVariable(Point paramPoint)
  {
    super(paramPoint, "Bool ", "0");
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    BooleanVariable localBooleanVariable = (BooleanVariable)paramJGoArea;
    localBooleanVariable.val = this.val;
    localBooleanVariable.oldval = this.oldval;
    localBooleanVariable.initialValue = this.initialValue;
    localBooleanVariable.hasInitialValue = this.hasInitialValue;
  }
  
  public void setNormalAction(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.setHigh = true;
    } else {
      this.setLow = true;
    }
  }
  
  public void setStoredBoolAction(boolean paramBoolean)
  {
    if (this.redirect == null)
    {
      this.oldval = this.val;
      this.val = paramBoolean;
    }
    else
    {
      this.redirect.setStoredBoolAction(paramBoolean);
    }
    Inspector.refresh();
    if (paramBoolean) {
      this.myValue.setText("1");
    } else {
      this.myValue.setText("0");
    }
  }
  
  public void effectuateNormalActions()
  {
    if (this.setHigh) {
      setStoredBoolAction(true);
    } else if (this.setLow) {
      setStoredBoolAction(false);
    }
    this.setLow = false;
    this.setHigh = false;
  }
  
  public void setStoredIntAction(int paramInt) {}
  
  public void setStoredRealAction(double paramDouble) {}
  
  public boolean getBoolVal()
  {
    if (this.redirect == null) {
      return this.val;
    }
    boolean bool = this.redirect.getBoolVal();
    this.myValue.setText(bool ? "1" : "0");
    return bool;
  }
  
  public boolean getOldBoolVal()
  {
    if (this.redirect == null) {
      return this.oldval;
    }
    return this.redirect.getOldBoolVal();
  }
  
  public int getIntVal()
  {
    return getBoolVal() ? 1 : 0;
  }
  
  public int getOldIntVal()
  {
    return getOldBoolVal() ? 1 : 0;
  }
  
  public String getStringVal()
  {
    return getBoolVal() ? "1" : "0";
  }
  
  public String getOldStringVal()
  {
    return getOldBoolVal() ? "1" : "0";
  }
  
  public double getRealVal()
  {
    return getBoolVal() ? 1.0D : 0.0D;
  }
  
  public double getOldRealVal()
  {
    return getOldBoolVal() ? 1.0D : 0.0D;
  }
  
  public boolean isBoolean()
  {
    return true;
  }
  
  public void initializeDisplay()
  {
    getBoolVal();
  }
  
  public void initialize()
  {
    if (this.hasInitialValue) {
      setStoredBoolAction(this.initialValue);
    }
    getBoolVal();
  }
  
  public String getHelpID()
  {
    return "LangRef_Var_BooleanVariable";
  }
  
  public String toString()
  {
    return getName() + ": " + getIntVal();
  }
  
  public void updateValue()
  {
    if (this.node != null) {
      setStoredBoolAction(this.node.evaluateBoolean());
    }
  }
  
  public void setInitialValue(String paramString)
    throws IllegalValueException
  {
    this.hasInitialValue = false;
    if (!paramString.isEmpty()) {
      if (paramString.equals("1"))
      {
        this.hasInitialValue = true;
        this.initialValue = true;
      }
      else if (paramString.equals("0"))
      {
        this.hasInitialValue = true;
        this.initialValue = false;
      }
      else
      {
        throw new IllegalValueException();
      }
    }
  }
  
  public String getInitialValue()
  {
    return this.hasInitialValue ? "0" : this.initialValue ? "1" : "";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/BooleanVariable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */