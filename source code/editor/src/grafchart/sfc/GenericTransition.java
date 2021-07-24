package grafchart.sfc;

import com.nwoods.jgo.JGoPort;
import com.nwoods.jgo.JGoText;
import grafchart.sfc.transitions.Start;
import grafchart.sfc.variables.Variable;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public abstract class GenericTransition
  extends GrafcetObject
  implements Helpable
{
  public transient Start node;
  protected boolean forced = false;
  protected JGoText myCondition = null;
  public boolean conditionVisible = true;
  public String conditionString = "";
  public boolean conditionValue = true;
  public Variable conditionVariable = null;
  public boolean markedForFire = false;
  public int priority = Integer.MAX_VALUE;
  public boolean hasPriority = false;
  public ArrayList<GrafcetObject> precedingSteps = new ArrayList();
  
  public abstract String getLabelText();
  
  public abstract void setTextColor(Color paramColor);
  
  public abstract void testAndFire();
  
  public abstract void preTestAndFire();
  
  public abstract void addSucceedingStep(GrafcetObject paramGrafcetObject);
  
  public abstract void addExitStep(MacroStep paramMacroStep, JGoPort paramJGoPort);
  
  public abstract void addEnterStep(MacroStep paramMacroStep, JGoPort paramJGoPort);
  
  public abstract void compileStructure();
  
  public abstract void showCondition();
  
  public abstract void hideCondition();
  
  public abstract void showPriority();
  
  public abstract void hidePriority();
  
  public static boolean equalSteps(ArrayList<GrafcetObject> paramArrayList1, ArrayList<GrafcetObject> paramArrayList2)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramArrayList2.iterator();
    while (localIterator.hasNext())
    {
      GrafcetObject localGrafcetObject = (GrafcetObject)localIterator.next();
      if (!(localGrafcetObject instanceof ExitStep)) {
        localArrayList.add(localGrafcetObject);
      }
    }
    if (paramArrayList1 != null) {
      return (paramArrayList1.containsAll(localArrayList)) && (localArrayList.containsAll(paramArrayList1));
    }
    return false;
  }
  
  public static void partitionTransitions(ArrayList<Partition> paramArrayList, ArrayList<GenericTransition> paramArrayList1)
  {
    Iterator localIterator = paramArrayList1.iterator();
    Object localObject1;
    while (localIterator.hasNext())
    {
      localObject1 = (GenericTransition)localIterator.next();
      int i = 0;
      Object localObject2;
      if (!((GenericTransition)localObject1).precedingSteps.isEmpty())
      {
        localObject2 = paramArrayList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Partition localPartition = (Partition)((Iterator)localObject2).next();
          if (equalSteps(localPartition.steps, ((GenericTransition)localObject1).precedingSteps))
          {
            localPartition.addTransition((GenericTransition)localObject1);
            i = 1;
          }
        }
      }
      if (i == 0)
      {
        localObject2 = new Partition();
        ((Partition)localObject2).addTransition((GenericTransition)localObject1);
        ((Partition)localObject2).addSteps(((GenericTransition)localObject1).precedingSteps);
        paramArrayList.add((Partition) localObject2);
      }
    }
    localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      localObject1 = (Partition)localIterator.next();
      Collections.sort(((Partition)localObject1).transitions, new Comparator()
      {
        public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
        {
          GenericTransition localGenericTransition1 = (GenericTransition)paramAnonymousObject1;
          GenericTransition localGenericTransition2 = (GenericTransition)paramAnonymousObject2;
          if (((localGenericTransition1 instanceof ExceptionTransition)) && ((localGenericTransition2 instanceof ExceptionTransition))) {
            return new Integer(localGenericTransition1.priority).compareTo(new Integer(localGenericTransition2.priority));
          }
          if (((localGenericTransition1 instanceof ExceptionTransition)) && ((localGenericTransition2 instanceof GCTransition))) {
            return -1;
          }
          if (((localGenericTransition1 instanceof GCTransition)) && ((localGenericTransition2 instanceof ExceptionTransition))) {
            return 1;
          }
          if (((localGenericTransition1 instanceof GCTransition)) && ((localGenericTransition2 instanceof GCTransition))) {
            return new Integer(localGenericTransition1.priority).compareTo(new Integer(localGenericTransition2.priority));
          }
          return 0;
        }
      });
    }
  }
  
  public static void applyPriorities(ArrayList<Partition> paramArrayList)
  {
    Iterator localIterator1 = paramArrayList.iterator();
    while (localIterator1.hasNext())
    {
      Partition localPartition = (Partition)localIterator1.next();
      int i = 0;
      int j = 0;
      Iterator localIterator2 = localPartition.transitions.iterator();
      while (localIterator2.hasNext())
      {
        GenericTransition localGenericTransition = (GenericTransition)localIterator2.next();
        if ((localGenericTransition.markedForFire) && (i == 0))
        {
          i = localGenericTransition.priority;
          if ((localGenericTransition instanceof ExceptionTransition)) {
            j = 1;
          }
        }
        else if (((localGenericTransition.markedForFire) && (localGenericTransition.priority > i)) || ((localGenericTransition.markedForFire) && ((localGenericTransition instanceof GCTransition)) && (j != 0)))
        {
          localGenericTransition.markedForFire = false;
        }
      }
    }
  }
  
  public String getCondition()
  {
    return this.myCondition.getText();
  }
  
  public void setCondition(String paramString)
  {
    this.conditionString = paramString;
    if (this.myCondition != null) {
      this.myCondition.setText(paramString);
    }
  }
  
  public void setCondition() {
        String condStr = "";
        if(this.conditionValue){
            if(this.conditionVariable == null){
                condStr += "1";
            } else {
                condStr += this.conditionVariable + " = 1";
            }
        } else {
            this.conditionValue = false;
            if(this.conditionVariable == null){
                condStr += "0";
            } else {
                condStr += this.conditionVariable + " = 0";
            }
        }
        this.setCondition(condStr);
    }
  
  public String toString()
  {
    return this.myCondition.getText();
  }
  
  protected void loadVariableXML(){
      
  }
  
  protected void storeVariableXML(){
      
  }    
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GenericTransition.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */