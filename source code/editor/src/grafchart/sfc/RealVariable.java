package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.actions.SingleExpression;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class RealVariable
  extends InternalVariable
{
  public double val = 0.0D;
  public double oldval = 0.0D;
  public static DecimalFormat df = new DecimalFormat();
  private double initialValue = 0.0D;
  private boolean hasInitialValue = false;
  
  public RealVariable() {}
  
  public RealVariable(Point paramPoint)
  {
    super(paramPoint, new Dimension(75, 45), "Real ", "0.0");
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    RealVariable localRealVariable = (RealVariable)paramJGoArea;
    localRealVariable.val = this.val;
    localRealVariable.oldval = this.oldval;
    localRealVariable.initialValue = this.initialValue;
    localRealVariable.hasInitialValue = this.hasInitialValue;
  }
  
  public boolean isInteger()
  {
    return false;
  }
  
  public boolean isReal()
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
    return (int)getRealVal();
  }
  
  public int getOldIntVal()
  {
    return (int)getOldRealVal();
  }
  
  public double getRealVal()
  {
    if (this.redirect == null) {
      return this.val;
    }
    double d = this.redirect.getRealVal();
    setText(df.format(d));
    return d;
  }
  
  public double getOldRealVal()
  {
    if (this.redirect == null) {
      return this.oldval;
    }
    double d = this.redirect.getOldRealVal();
    return d;
  }
  
  public String getStringVal()
  {
    return "" + getRealVal();
  }
  
  public String getOldStringVal()
  {
    return "" + getOldRealVal();
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
    setText(df.format(paramDouble));
    Inspector.refresh();
    if (this.redirect == null)
    {
      this.oldval = this.val;
      this.val = paramDouble;
    }
    else
    {
      this.redirect.setStoredRealAction(paramDouble);
    }
  }
  
  public void setStoredBoolAction(boolean paramBoolean) {}
  
  public void setStoredStringAction(String paramString) {}
  
  public void initializeDisplay()
  {
    getRealVal();
  }
  
  public void initialize()
  {
    if (this.hasInitialValue) {
      setStoredRealAction(this.initialValue);
    }
    getRealVal();
  }
  
  public String getHelpID()
  {
    return "LangRef_Var_RealVariable";
  }
  
  public String toString()
  {
    return getName() + ": " + getRealVal();
  }
  
  public void updateValue()
  {
    if (this.node != null) {
      setStoredRealAction(this.node.evaluateReal());
    }
  }
  
  public void setInitialValue(String paramString)
    throws IllegalValueException
  {
    this.hasInitialValue = false;
    if (!paramString.isEmpty()) {
      try
      {
        this.initialValue = Double.parseDouble(paramString);
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
    return this.hasInitialValue ? Double.toString(this.initialValue) : "";
  }
  
  static
  {
    DecimalFormatSymbols localDecimalFormatSymbols = df.getDecimalFormatSymbols();
    localDecimalFormatSymbols.setDecimalSeparator('.');
    df.setGroupingUsed(false);
    df.setMaximumFractionDigits(3);
    df.setMinimumFractionDigits(1);
    df.setDecimalFormatSymbols(localDecimalFormatSymbols);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/RealVariable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */