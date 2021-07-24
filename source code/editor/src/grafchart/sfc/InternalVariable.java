package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoPen;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoText;
import grafchart.graphics.JGoTextModify;
import grafchart.sfc.actions.SingleExpression;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import org.w3c.dom.Element;

public abstract class InternalVariable
  extends GCVariable
  implements Readable, Writable
{
  protected JGoRectangle myBorder = null;
  protected JGoText myValue = null;
  protected JGoText myName = null;
  private JGoText myTag = null;
  protected InternalVariable redirect = null;
  public boolean isConstant = false;
  public boolean isUpdated = false;
  public transient SingleExpression node = null;
  public String expression = "";
  
  public InternalVariable() {}
  
  public InternalVariable(Point paramPoint, String paramString1, String paramString2)
  {
    this(paramPoint, new Dimension(65, 45), paramString1, paramString2);
  }
  
  public InternalVariable(Point paramPoint, Dimension paramDimension, String paramString1, String paramString2)
  {
    setSelectable(true);
    setGrabChildSelection(false);
    setDraggable(true);
    setResizable(true);
    this.myBorder = new JGoRectangle();
    this.myBorder.setSize(paramDimension);
    this.myBorder.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    this.myBorder.setSelectable(false);
    this.myBorder.setDraggable(false);
    this.myName = new JGoText("Var");
    this.myName.setSelectable(true);
    this.myName.setEditable(true);
    this.myName.setEditOnSingleClick(true);
    this.myName.setDraggable(false);
    this.myName.setAlignment(2);
    this.myName.setTransparent(true);
    this.myValue = new JGoTextModify(paramString2);
    this.myValue.setSelectable(true);
    this.myValue.setEditable(true);
    this.myValue.setEditOnSingleClick(true);
    this.myValue.setDraggable(false);
    this.myValue.setAlignment(1);
    this.myValue.setTransparent(true);
    this.myTag = new JGoText(paramString1);
    this.myTag.setSelectable(false);
    this.myTag.setEditable(false);
    this.myTag.setEditOnSingleClick(false);
    this.myTag.setDraggable(false);
    this.myTag.setTransparent(true);
    addObjectAtHead(this.myBorder);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myValue);
    addObjectAtTail(this.myTag);
    setLocation(paramPoint);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    InternalVariable localInternalVariable = (InternalVariable)paramJGoArea;
    localInternalVariable.myBorder = ((JGoRectangle)paramJGoCopyEnvironment.copy(this.myBorder));
    localInternalVariable.myName = ((JGoText)paramJGoCopyEnvironment.copy(this.myName));
    localInternalVariable.myValue = ((JGoTextModify)paramJGoCopyEnvironment.copy(this.myValue));
    localInternalVariable.myTag = ((JGoText)paramJGoCopyEnvironment.copy(this.myTag));
    localInternalVariable.addObjectAtHead(localInternalVariable.myBorder);
    localInternalVariable.addObjectAtTail(localInternalVariable.myName);
    localInternalVariable.addObjectAtTail(localInternalVariable.myValue);
    localInternalVariable.addObjectAtTail(localInternalVariable.myTag);
    localInternalVariable.setIsConstant(this.isConstant);
    localInternalVariable.isUpdated = this.isUpdated;
    localInternalVariable.expression = this.expression;
  }
  
  private void removeTextFields()
  {
    removeObject(this.myName);
    removeObject(this.myValue);
  }
  
  private void restoreTextFields()
  {
    addObjectAtTail(this.myName);
    addObjectAtTail(this.myValue);
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", this.myName.getText());
    paramElement.setAttribute("value", this.myValue.getText());
    paramElement.setAttribute("initialValue", getInitialValue());
    paramElement.setAttribute("constant", this.isConstant ? "1" : "0");
    paramElement.setAttribute("updated", this.isUpdated ? "1" : "0");
    paramElement.setAttribute("exp", this.expression);
    removeTextFields();
    XMLUtil.saveBoundingRect(paramElement, this);
    restoreTextFields();
    return paramElement;
  }
  
  public static InternalVariable loadXML(Element paramElement)
  {
    Object localObject = null;
    String str1 = paramElement.getTagName();
    if (str1.equals(GCDocument.booleanVarTag)) {
      localObject = new BooleanVariable(new Point());
    } else if (str1.equals(GCDocument.integerVarTag)) {
      localObject = new IntegerVariable(new Point());
    } else if (str1.equals(GCDocument.indexVarTag)) {
      localObject = new IndexVariable(new Point());
    } else if (str1.equals(GCDocument.integerAttributeVarTag)) {
      localObject = new IntegerAttributeVariable(new Point());
    } else if (str1.equals(GCDocument.realAttributeVarTag)) {
      localObject = new RealAttributeVariable(new Point());
    } else if (str1.equals(GCDocument.stringAttributeVarTag)) {
      localObject = new StringAttributeVariable(new Point());
    } else if (str1.equals(GCDocument.stringVarTag)) {
      localObject = new StringVariable(new Point());
    } else if (str1.equals(GCDocument.realVarTag)) {
      localObject = new RealVariable(new Point());
    }
    String str2 = paramElement.getAttribute("name");
    ((InternalVariable)localObject).myName.setText(str2);
    String str3 = paramElement.getAttribute("value");
    ((InternalVariable)localObject).myValue.setText(str3);
    String str4 = paramElement.getAttribute("initialValue");
    try
    {
      ((InternalVariable)localObject).setInitialValue(str4);
    }
    catch (IllegalValueException localIllegalValueException)
    {
      Utils.writeError("Illegal InitialValue \"" + str4 + "\" for variable \"" + str2 + "\".");
    }
    ((InternalVariable)localObject).setIsConstant(XMLUtil.getBool(paramElement, "constant"));
    ((InternalVariable)localObject).isUpdated = XMLUtil.getBool(paramElement, "updated");
    ((InternalVariable)localObject).expression = paramElement.getAttribute("exp");
    if (((localObject instanceof IntegerVariable)) && (!(localObject instanceof IndexVariable)) && (!(localObject instanceof IntegerAttributeVariable))) {
      try
      {
        ((IntegerVariable)localObject).setStoredIntAction(Integer.parseInt(str3));
      }
      catch (NumberFormatException localNumberFormatException1)
      {
        ((InternalVariable)localObject).myValue.setTextColor(Color.RED);
      }
    }
    if ((localObject instanceof IndexVariable)) {
      try
      {
        ((IndexVariable)localObject).setStoredIntAction(Integer.parseInt(str3));
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        ((InternalVariable)localObject).myValue.setTextColor(Color.RED);
      }
    }
    if ((localObject instanceof IntegerAttributeVariable)) {
      try
      {
        ((IntegerAttributeVariable)localObject).setStoredIntAction(Integer.parseInt(str3));
      }
      catch (NumberFormatException localNumberFormatException3)
      {
        ((InternalVariable)localObject).myValue.setTextColor(Color.RED);
      }
    }
    if (((localObject instanceof RealVariable)) && (!(localObject instanceof RealAttributeVariable))) {
      try
      {
        ((RealVariable)localObject).setStoredRealAction(Double.parseDouble(str3));
      }
      catch (NumberFormatException localNumberFormatException4)
      {
        ((InternalVariable)localObject).myValue.setTextColor(Color.RED);
      }
    }
    if ((localObject instanceof RealAttributeVariable)) {
      try
      {
        ((RealAttributeVariable)localObject).setStoredRealAction(Double.parseDouble(str3));
      }
      catch (NumberFormatException localNumberFormatException5)
      {
        ((InternalVariable)localObject).myValue.setTextColor(Color.RED);
      }
    }
    if (((localObject instanceof StringVariable)) && (!(localObject instanceof StringAttributeVariable))) {
      ((StringVariable)localObject).setStoredStringAction(str3);
    }
    if ((localObject instanceof StringAttributeVariable)) {
      ((StringAttributeVariable)localObject).setStoredStringAction(str3);
    }
    if ((localObject instanceof BooleanVariable))
    {
      ((BooleanVariable)localObject).setStoredBoolAction(str3.equals("1"));
      ((InternalVariable)localObject).myValue.setText(str3);
    }
    ((InternalVariable)localObject).removeTextFields();
    XMLUtil.restoreBoundingRectAny(paramElement, (JGoObject)localObject);
    ((InternalVariable)localObject).restoreTextFields();
    return (InternalVariable)localObject;
  }
  
  public void layoutChildren()
  {
    this.myName.setSpotLocation(2, this.myBorder, 6);
    this.myValue.setSpotLocation(8, this.myBorder, 0);
    this.myTag.setSpotLocation(4, this.myBorder, 0);
  }
  
  public JGoObject[] getGeometryChangeObjects()
  {
    return new JGoObject[] { this.myBorder };
  }
  
  public Dimension getMinimumSize()
  {
    int i = (int)Math.ceil(this.myName.getBoundingRect().getWidth() + 10.0D);
    int j = (int)Math.ceil(this.myName.getBoundingRect().getHeight() + 20.0D);
    return new Dimension(i, j);
  }
  
  public int getNoScaleBottom(Rectangle paramRectangle)
  {
    return paramRectangle.y + paramRectangle.height - (this.myBorder.getTop() + this.myBorder.getHeight());
  }
  
  public Point getLocation(Point paramPoint)
  {
    return getSpotLocation(1, paramPoint);
  }
  
  public void setLocation(int paramInt1, int paramInt2)
  {
    setSpotLocation(1, paramInt1, paramInt2);
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
    return false;
  }
  
  public boolean isReal()
  {
    return false;
  }
  
  public String getName()
  {
    return this.myName.getText();
  }
  
  public void setName(String paramString)
  {
    this.myName.setText(paramString);
  }
  
  public String getFullName()
  {
    String str = getName();
    GCDocument localGCDocument = getDocument();
    for (GrafcetObject localGrafcetObject = localGCDocument.owner; localGrafcetObject != null; localGrafcetObject = localGCDocument.owner)
    {
      Referencable localReferencable = (Referencable)localGrafcetObject;
      str = localReferencable.getName() + "." + str;
      localGCDocument = localGrafcetObject.getDocument();
    }
    str = localGCDocument.getName() + "." + str;
    return str;
  }
  
  public boolean getBoolVal()
  {
    return false;
  }
  
  public boolean getOldBoolVal()
  {
    return false;
  }
  
  public double getRealVal()
  {
    return 0.0D;
  }
  
  public double getOldRealVal()
  {
    return 0.0D;
  }
  
  public void setStoredStringAction(String paramString) {}
  
  public String getStringVal()
  {
    return new String("");
  }
  
  public String getOldStringVal()
  {
    return new String("");
  }
  
  public void initializeDisplay() {}
  
  public void initialize() {}
  
  public void setRedirect(InternalVariable paramInternalVariable)
  {
    this.redirect = paramInternalVariable;
    initializeDisplay();
  }
  
  public void setIsConstant(boolean paramBoolean)
  {
    this.isConstant = paramBoolean;
    this.myValue.setEditable(!paramBoolean);
    this.myValue.setSelectable(!paramBoolean);
    if (paramBoolean) {
      this.myBorder.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 1.0F)));
    } else {
      this.myBorder.setPen(new JGoPen(65535, 2, new Color(0.0F, 0.0F, 0.0F)));
    }
  }
  
  public boolean isConstant()
  {
    return this.isConstant;
  }
  
  public void updateValue() {}
  
  public boolean references(InternalVariable paramInternalVariable)
  {
    ArrayList localArrayList = new ArrayList();
    getReferences(localArrayList);
    return localArrayList.indexOf(paramInternalVariable) != -1;
  }
  
  private void getReferences(ArrayList paramArrayList)
  {
    if (this.node != null) {
      this.node.collectReferences(paramArrayList);
    }
  }
  
  public abstract void setInitialValue(String paramString)
    throws IllegalValueException;
  
  public abstract String getInitialValue();
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/InternalVariable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */