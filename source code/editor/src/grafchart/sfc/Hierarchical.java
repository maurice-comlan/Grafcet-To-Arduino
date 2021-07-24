package grafchart.sfc;

public abstract interface Hierarchical
  extends Referencable
{
  public abstract void setWorkspaceColor(int paramInt);
  
  public abstract int getWorkspaceColor();
  
  public abstract void showWorkspace();
  
  public abstract void showWorkspace(int paramInt1, int paramInt2);
  
  public abstract void hideWorkspace();
  
  public abstract void assignWorkspace(Hierarchical paramHierarchical);
  
  public abstract GCDocument getContentDocument();
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/Hierarchical.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */