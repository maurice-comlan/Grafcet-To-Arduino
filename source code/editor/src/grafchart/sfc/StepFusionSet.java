package grafchart.sfc;

import com.nwoods.jgo.JGoArea;
import com.nwoods.jgo.JGoBrush;
import com.nwoods.jgo.JGoCopyEnvironment;
import com.nwoods.jgo.JGoEllipse;
import com.nwoods.jgo.JGoListPosition;
import com.nwoods.jgo.JGoObject;
import com.nwoods.jgo.JGoRectangle;
import com.nwoods.jgo.JGoText;
import grafchart.util.XMLUtil;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.Element;

public class StepFusionSet
  extends WorkspaceObject
{
  public boolean abortive = false;
  public ArrayList<GrafcetObject> steps = new ArrayList();
  
  public StepFusionSet() {}
  
  public StepFusionSet(Point paramPoint, String paramString)
  {
    this(paramPoint, paramString, null);
  }
  
  public StepFusionSet(Point paramPoint, String paramString1, String paramString2)
  {
    super(paramPoint, paramString1, paramString2);
    removeObject(this.myRectangle1);
    removeObject(this.myRectangle2);
    removeObject(this.myRectangle3);
    this.myRectangle1 = new JGoRectangle(new Rectangle(20, 18));
    this.myRectangle1.setBrush(new JGoBrush(65535, new Color(1.0F, 1.0F, 1.0F)));
    this.myRectangle1.setSelectable(false);
    this.myRectangle1.setDraggable(false);
    this.myRectangle2 = new JGoRectangle(new Rectangle(20, 18));
    this.myRectangle2.setBrush(new JGoBrush(65535, new Color(1.0F, 1.0F, 1.0F)));
    this.myRectangle2.setSelectable(false);
    this.myRectangle2.setDraggable(false);
    this.myRectangle3 = new JGoRectangle(new Rectangle(20, 18));
    this.myRectangle3.setBrush(new JGoBrush(65535, new Color(1.0F, 1.0F, 1.0F)));
    this.myRectangle3.setSelectable(false);
    this.myRectangle3.setDraggable(false);
    addObjectAtTail(this.myRectangle3);
    addObjectAtTail(this.myRectangle2);
    addObjectAtTail(this.myRectangle1);
    layoutChildren();
  }
  
  public void copyChildren(JGoArea paramJGoArea, JGoCopyEnvironment paramJGoCopyEnvironment)
  {
    super.copyChildren(paramJGoArea, paramJGoCopyEnvironment);
    StepFusionSet localStepFusionSet = (StepFusionSet)paramJGoArea;
    localStepFusionSet.abortive = this.abortive;
    Iterator localIterator = this.steps.iterator();
    while (localIterator.hasNext())
    {
      GrafcetObject localGrafcetObject = (GrafcetObject)localIterator.next();
      int i = localGrafcetObject.fusionSets.indexOf(this);
      if (i != -1) {
        localGrafcetObject.fusionSets.remove(i);
      }
    }
    this.steps.clear();
  }
  
  public void layoutChildren()
  {
    Point localPoint = this.myRectangle.getSpotLocation(0);
    this.myRectangle1.setSpotLocation(0, (int)Math.round(localPoint.getX() - this.rectSeparation), (int)Math.round(localPoint.getY() + this.rectSeparation));
    this.myRectangle2.setSpotLocation(0, (int)Math.round(localPoint.getX()), (int)Math.round(localPoint.getY()));
    this.myRectangle3.setSpotLocation(0, (int)Math.round(localPoint.getX() + this.rectSeparation), (int)Math.round(localPoint.getY() - this.rectSeparation));
    localPoint = this.myRectangle.getSpotLocation(6);
    if (this.classLabel.getText().equals("")) {
      this.myName.setSpotLocation(2, (int)localPoint.getX(), (int)localPoint.getY() + 10);
    } else {
      this.myName.setSpotLocation(1, (int)localPoint.getX(), (int)localPoint.getY() + 10);
    }
    if (this.classLabel != null)
    {
      localPoint = this.myName.getSpotLocation(1);
      this.classLabel.setSpotLocation(3, (int)localPoint.getX(), (int)localPoint.getY());
    }
  }
  
  public Element storeXML(Element paramElement)
  {
    paramElement.setAttribute("name", getName());
    paramElement.setAttribute("ownerClass", this.classLabel.getText());
    XMLUtil.setBool(paramElement, "abortive", this.abortive);
    removeObject(this.myName);
    removeObject(this.classLabel);
    XMLUtil.saveBoundingRect(paramElement, this);
    addObjectAtTail(this.myName);
    addObjectAtTail(this.classLabel);
    this.myContentDocument.storeXMLRec(paramElement, this.bounds, this.point);
    return paramElement;
  }
  
  public void compile()
  {
    Object localObject1 = this.steps.iterator();
    while (((Iterator)localObject1).hasNext())
    {
        GrafcetObject localObject2 = (GrafcetObject)((Iterator)localObject1).next();
      int i = ((GrafcetObject)localObject2).fusionSets.indexOf(this);
      if (i != -1) {
        ((GrafcetObject)localObject2).fusionSets.remove(i);
      }
    }
    this.steps.clear();
    localObject1 = this.myContentDocument;
    Object localObject2 = ((GCDocument)localObject1).getFirstObjectPos();
    for (JGoObject localJGoObject1 = ((GCDocument)localObject1).getObjectAtPos((JGoListPosition)localObject2); (localJGoObject1 != null) && (localObject2 != null); localJGoObject1 = ((GCDocument)localObject1).getObjectAtPos((JGoListPosition)localObject2))
    {
      if ((localJGoObject1 instanceof FreeText))
      {
        FreeText localFreeText = (FreeText)localJGoObject1;
        String str = localFreeText.getText();
        Object localObject3;
        Object localObject4;
        if (str.indexOf(".") != -1)
        {
          localObject3 = Editor.findObject(localFreeText.getText());
          if (((localObject3 instanceof GCStep)) || ((localObject3 instanceof MacroStep)))
          {
            localObject4 = (GrafcetObject)localObject3;
            if (this.steps.indexOf(localObject4) == -1)
            {
              this.steps.add((GrafcetObject) localObject4);
              if (((GrafcetObject)localObject4).fusionSets.indexOf(this) == -1) {
                ((GrafcetObject)localObject4).fusionSets.add(this);
              }
            }
          }
        }
        else
        {
          localObject3 = getDocument();
          localObject4 = ((GCDocument)localObject3).getFirstObjectPos();
          for (JGoObject localJGoObject2 = ((GCDocument)localObject3).getObjectAtPos((JGoListPosition)localObject4); (localJGoObject2 != null) && (localObject4 != null); localJGoObject2 = ((GCDocument)localObject3).getObjectAtPos((JGoListPosition)localObject4))
          {
            if ((((localJGoObject2 instanceof GCStep)) || ((localJGoObject2 instanceof MacroStep))) && (((Referencable)localJGoObject2).getName().equals(str)))
            {
              GrafcetObject localGrafcetObject = (GrafcetObject)localJGoObject2;
              if (this.steps.indexOf(localGrafcetObject) == -1)
              {
                this.steps.add(localGrafcetObject);
                Utils.writeDebug(((Referencable)localGrafcetObject).getFullName() + " added to FusionSet");
                if (localGrafcetObject.fusionSets.indexOf(this) == -1) {
                  localGrafcetObject.fusionSets.add(this);
                }
              }
            }
            localObject4 = ((GCDocument)localObject3).getNextObjectPos((JGoListPosition)localObject4);
          }
        }
      }
      localObject2 = ((GCDocument)localObject1).getNextObjectPos((JGoListPosition)localObject2);
    }
  }
  
  public boolean isEnabled(GrafcetObject paramGrafcetObject)
  {
    GrafcetObject localGrafcetObject;
    MacroStep localMacroStep;
    if (!this.abortive)
    {
        boolean bool = true;
        Iterator<GrafcetObject> localIterator = this.steps.iterator();
      while (localIterator.hasNext())
      {
        localGrafcetObject = (GrafcetObject)localIterator.next();
        if ((localGrafcetObject instanceof MacroStep))
        {
          localMacroStep = (MacroStep)localGrafcetObject;
          bool = (bool) && (localMacroStep.isEnabledAlone(true));
        }
        else
        {
          bool = (bool) && (localGrafcetObject.isEnabledAlone());
        }
      }
      return bool;
    }
    boolean bool = true;
    Iterator localIterator = this.steps.iterator();
    while (localIterator.hasNext())
    {
      localGrafcetObject = (GrafcetObject)localIterator.next();
      if ((localGrafcetObject instanceof MacroStep))
      {
        localMacroStep = (MacroStep)localGrafcetObject;
        if (localMacroStep != paramGrafcetObject) {
          bool = (bool) && (localMacroStep.isEnabledAlone(false));
        } else {
          bool = (bool) && (localMacroStep.isEnabledAlone(true));
        }
      }
      else
      {
        bool = (bool) && (localGrafcetObject.isEnabledAlone());
      }
    }
    return bool;
  }
  
  public void deactivate(GrafcetObject paramGrafcetObject)
  {
    Iterator localIterator;
    GrafcetObject localGrafcetObject;
    Object localObject;
    if (!this.abortive)
    {
      localIterator = this.steps.iterator();
      while (localIterator.hasNext())
      {
        localGrafcetObject = (GrafcetObject)localIterator.next();
        if ((localGrafcetObject instanceof MacroStep))
        {
          localObject = (MacroStep)localGrafcetObject;
          ((MacroStep)localObject).deactivateAlone(true);
        }
        else
        {
          localGrafcetObject.deactivateAlone();
        }
      }
    }
    else
    {
      localIterator = this.steps.iterator();
      while (localIterator.hasNext())
      {
        localGrafcetObject = (GrafcetObject)localIterator.next();
        if ((localGrafcetObject instanceof MacroStep))
        {
          localObject = (MacroStep)localGrafcetObject;
          if (localObject != paramGrafcetObject) {
            ((MacroStep)localObject).deactivateStrong();
          } else {
            ((MacroStep)localObject).deactivateAlone(true);
          }
        }
        else if ((localGrafcetObject instanceof GCStep))
        {
          localObject = (GCStep)localGrafcetObject;
          if (localObject != paramGrafcetObject)
          {
            ((GCStep)localObject).myToken.setBrush(JGoBrush.Null);
            if (((GCStep)localObject).curX) {
              ((GCStep)localObject).executeAbortiveActions();
            }
            ((GCStep)localObject).curX = false;
            ((GCStep)localObject).newX = false;
          }
          else
          {
            ((GCStep)localObject).deactivateAlone();
          }
        }
      }
    }
  }
  
  public void activate()
  {
    Iterator localIterator = this.steps.iterator();
    while (localIterator.hasNext())
    {
      GrafcetObject localGrafcetObject = (GrafcetObject)localIterator.next();
      if (((localGrafcetObject instanceof MacroStep)) && (!(localGrafcetObject instanceof ProcedureStep)))
      {
        MacroStep localMacroStep = (MacroStep)localGrafcetObject;
        localMacroStep.activateAlone(true);
      }
      else
      {
        localGrafcetObject.activateAlone();
      }
    }
  }
  
  public String getHelpID()
  {
    return "LangRef_FC_StepFusionSet";
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/StepFusionSet.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */