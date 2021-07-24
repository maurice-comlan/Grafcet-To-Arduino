package grafchart.sfc;

import java.util.ArrayList;
import java.util.Iterator;

public class Partition
{
  public ArrayList<GrafcetObject> steps = new ArrayList();
  public ArrayList<GenericTransition> transitions = new ArrayList();
  
  public void addTransitions(ArrayList<GenericTransition> paramArrayList)
  {
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      GenericTransition localGenericTransition = (GenericTransition)localIterator.next();
      addTransition(localGenericTransition);
    }
  }
  
  public void addTransition(GenericTransition paramGenericTransition)
  {
    if (!this.transitions.contains(paramGenericTransition)) {
      this.transitions.add(paramGenericTransition);
    }
  }
  
  public void addStep(GrafcetObject paramGrafcetObject)
  {
    if (!this.steps.contains(paramGrafcetObject)) {
      this.steps.add(paramGrafcetObject);
    }
  }
  
  public void addSteps(ArrayList<GrafcetObject> paramArrayList)
  {
    Iterator localIterator1 = paramArrayList.iterator();
    while (localIterator1.hasNext())
    {
      GrafcetObject localGrafcetObject1 = (GrafcetObject)localIterator1.next();
      if (!(localGrafcetObject1 instanceof ExitStep)) {
        addStep(localGrafcetObject1);
      }
      if (!localGrafcetObject1.fusionSets.isEmpty())
      {
        Iterator localIterator2 = localGrafcetObject1.fusionSets.iterator();
        while (localIterator2.hasNext())
        {
          StepFusionSet localStepFusionSet = (StepFusionSet)localIterator2.next();
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator3 = localStepFusionSet.steps.iterator();
          while (localIterator3.hasNext())
          {
            GrafcetObject localGrafcetObject2 = (GrafcetObject)localIterator3.next();
            localGrafcetObject2.succeedingTransitions(localArrayList);
          }
          addTransitions(localArrayList);
        }
      }
    }
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Partition.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */