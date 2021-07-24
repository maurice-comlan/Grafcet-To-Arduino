package grafchart.util;

import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCGroup;
import grafchart.sfc.GrafcetObject;
import grafchart.sfc.GrafchartStorage;
import grafchart.sfc.Referencable;
import grafchart.sfc.SymbolTableObject;
import grafchart.sfc.Utils;
import java.util.ArrayList;
import java.util.Iterator;

public class SymbolTableHelper
{
  public static void warnAboutNameConflicts(GCDocument paramGCDocument)
  {
    warnAboutNameConflictsTopLevel();
    warnAboutNameConflicts((SymbolTableObject) paramGCDocument);
  }
  
  private static void warnAboutNameConflictsTopLevel()
  {
      System.out.println("grafchart.util.SymbolTableHelper.warnAboutNameConflictsTopLevel(), 24");
      ArrayList<Referencable> list1 = new ArrayList<>();
      list1.addAll(Editor.topGrafcharts.getStorage());
      
      warnAboutNameConflicts(list1, "<TopLevel>");
  }
  
  private static void warnAboutNameConflicts(SymbolTableObject paramSymbolTableObject)
  {
    warnAboutNameConflicts(paramSymbolTableObject.getSymbolTable(), paramSymbolTableObject.getFullName());
  }
  
  private static void warnAboutNameConflicts(ArrayList<Referencable> paramArrayList, String paramString)
  {
    Iterator localIterator1 = paramArrayList.iterator();
    while (localIterator1.hasNext())
    {
      Referencable localReferencable1 = (Referencable)localIterator1.next();
      if (localIterator1.hasNext())
      {
        Iterator localIterator2 = paramArrayList.iterator();
        for (Referencable localReferencable2 = null; localReferencable2 != localReferencable1; localReferencable2 = (Referencable)localIterator2.next()) {}
        while (localIterator2.hasNext())
        {
            Referencable localReferencable2 = (Referencable)localIterator2.next();
          if ((localReferencable1.getName().equals(localReferencable2.getName())) && (!localReferencable1.getName().isEmpty())) {
            Editor.giveLightWarning("Multiple definitions of \"" + localReferencable2.getName() + "\" in \"" + paramString + "\".");
          }
        }
      }
        System.out.println("grafchart.util.SymbolTableHelper.warnAboutNameConflicts(), 54");
      /*
        if (((localReferencable1 instanceof SymbolTableObject)) && (paramArrayList != Editor.topGrafcharts.getStorage())) {
        warnAboutNameConflicts((SymbolTableObject)localReferencable1);
      }
        */
    }
  }
  
  public static Referencable lookup(SymbolTableObject paramSymbolTableObject, String paramString, boolean paramBoolean)
  {
    return lookup(paramSymbolTableObject, paramString, paramBoolean, false);
  }
  
  public static Referencable lookup(SymbolTableObject paramSymbolTableObject, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramSymbolTableObject == null) {
      return null;
    }
    Referencable localReferencable = lookupRec(paramSymbolTableObject, paramString, paramBoolean1);
    if ((localReferencable == null) && (!paramBoolean1)) {
      localReferencable = lookupTopLevel(paramString);
    }
    if ((localReferencable == null) && (paramBoolean2)) {
      Utils.writeError("Name lookup failed. \"" + paramString + "\" could not be found.");
    }
    return localReferencable;
  }
  
  private static Referencable lookupRec(SymbolTableObject paramSymbolTableObject, String paramString, boolean paramBoolean)
  {
    if (paramSymbolTableObject != null)
    {
      Object localObject = paramSymbolTableObject.getSymbolTable().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Referencable localReferencable = (Referencable)((Iterator)localObject).next();
        if (localReferencable.getName().equals(paramString)) {
          return localReferencable;
        }
      }
      if (!paramBoolean)
      {
        localObject = lookupRec(getParentSymbolTableObject(paramSymbolTableObject), paramString, false);
        if (localObject != null) {
          return (Referencable)localObject;
        }
      }
    }
    return null;
  }
  
  private static Referencable lookupTopLevel(String paramString)
  {
    Iterator localIterator = Editor.topGrafcharts.getStorage().iterator();
    while (localIterator.hasNext())
    {
      Referencable localReferencable = (Referencable)localIterator.next();
      if (localReferencable.getName().equals(paramString)) {
        return localReferencable;
      }
    }
    return null;
  }
  
  public static SymbolTableObject getParentSymbolTableObject(SymbolTableObject paramSymbolTableObject)
  {
    if ((paramSymbolTableObject instanceof GCGroup)) {
      return ((GCGroup)paramSymbolTableObject).getDocument();
    }
    GrafcetObject localGrafcetObject = ((GCDocument)paramSymbolTableObject).owner;
    if (localGrafcetObject != null) {
      return localGrafcetObject.getDocument();
    }
    if (!Editor.topGrafcharts.getStorage().contains(paramSymbolTableObject))
    {
      Utils.writeInternalError("\"" + paramSymbolTableObject.getName() + "\" doesn't have an owner and is not a top-level workspace.");
      try
      {
        throw new Exception();
      }
      catch (Exception localException)
      {
        Utils.writeException(localException);
      }
    }
    return null;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/util/SymbolTableHelper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */