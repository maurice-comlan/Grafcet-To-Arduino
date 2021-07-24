package grafchart.sfc;

import java.util.ArrayList;

/**
 * Garde trace de l'emplacement des documents ouvrerts
 * @author dimon
 */
public class GrafchartStorage
{
  private ArrayList<GCDocument> store = new ArrayList();
  
  /**
   * Ajoute un document à la liste des documents ouverts
   * @param gcDoc document à ajouter
   */
  public void add(GCDocument gcDoc)
  {
    this.store.add(gcDoc);
  }
  
  /**
   * Enlève un document de la liste
   * @param gcDoc documement à enlever
   */
  public void remove(GCDocument gcDoc)
  {
    if (this.store.contains(gcDoc)) {
      this.store.remove(this.store.indexOf(gcDoc));
    }
  }
  
  /**
   * Return la liste des documents ouverts
   * @return 
   */
  public ArrayList<GCDocument> getStorage()
  {
    return this.store;
  }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/sfc/GrafchartStorage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */