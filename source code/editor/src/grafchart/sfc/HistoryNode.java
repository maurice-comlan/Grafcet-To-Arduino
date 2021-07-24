package grafchart.sfc;

public class HistoryNode
  extends GrafcetObject
{
  public MacroStep macro = null;
  
  public HistoryNode() {}
  
  public HistoryNode(MacroStep paramMacroStep)
  {
    this.macro = paramMacroStep;
  }
  
  public void activate()
  {
    this.macro.activateFromHistory(true);
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/HistoryNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */