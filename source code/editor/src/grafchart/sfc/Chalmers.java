package grafchart.sfc;

public class Chalmers
{
  public static void main(String[] paramArrayOfString)
  {
      
      System.out.println("ici");
      
    EditorAPI localEditorAPI = new EditorAPI(paramArrayOfString);
      System.out.println("ici");
    Editor.singleton = localEditorAPI;
    EditorAPI.hidePaletteAction();
    GCDocument localGCDocument1 = localEditorAPI.openWorkspace("test.xml");
    localGCDocument1.setWorkspaceName("Nisse");
    BooleanList localBooleanList = localGCDocument1.createBooleanList(400, 50, "MYLIST", "");
    WorkspaceObject localWorkspaceObject = localGCDocument1.createWorkspaceObject(300, 50, "Vars");
    GCDocument localGCDocument2 = localWorkspaceObject.getSubWorkspace();
    localGCDocument2.createIntegerVariable(50, 50, "Int1", "99");
    localGCDocument2.createBooleanVariable(50, 120, "Bool1", "1");
    localGCDocument2.createStringVariable(50, 190, "String1", "Hello");
    GCStepInitial localGCStepInitial = localGCDocument1.createInitialStep(100, 50, "", ";");
    localGCStepInitial.setActionText("S println(\"Hej\");");
    GCTransition localGCTransition1 = localGCDocument1.createTransition(100, 130, "1");
    GCStep localGCStep = localGCDocument1.createStep(100, 180, "myStep", "S Vars.Int1 = Vars.Int1 + 1;");
    GCTransition localGCTransition2 = localGCDocument1.createTransition(100, 260, "myStep.s > 4");
    localGCDocument1.connect(localGCStepInitial, localGCTransition1);
    localGCDocument1.connect(localGCTransition1, localGCStep);
    localGCDocument1.connect(localGCStep, localGCTransition2);
    localGCDocument1.connect(localGCTransition2, localGCStepInitial);
    boolean bool = localEditorAPI.compileWorkspace(localGCDocument1);
    if (bool)
    {
      localEditorAPI.startWorkspace(localGCDocument1);
      localBooleanList.add(true);
      localBooleanList.add(false);
    }
    try
    {
      Thread.sleep(10000L);
    }
    catch (InterruptedException localException) {}
     
      System.out.println("grafchart.sfc.Chalmers.main()");
  }
      
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Chalmers.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */