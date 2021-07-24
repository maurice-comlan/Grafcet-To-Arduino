package grafchart.graphics;

import grafchart.sfc.Editor;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * Frame devant contenir un G7 c'est un document
 *
 * @author dimon
 */
public class MyJInternalFrame
        extends JInternalFrame {

    private boolean isSubFrame = true;

    /**
     * Crée un nouveau document (frame)
     *
     * @param title titre du document
     * @param isSubFrame
     */
    public MyJInternalFrame(String title, boolean isSubFrame) {
        super(title, true, true, true, true);
        this.isSubFrame = isSubFrame;
    }

    @Override
    public void doDefaultCloseAction() {
        if(isSubFrame){
            super.doDefaultCloseAction();
        } else {
            if(true){ //Editor.myCurrentView.getDoc().isSaved ** pour ne pas ennuyer
                int i = JOptionPane.showConfirmDialog(Editor.myCurrentView.getFrame(), "All non-saved work on " + Editor.myCurrentView.getDoc().getName() + " will be lost.\nReally close application?", "Closing application", 0);
                if(i == JOptionPane.YES_OPTION){
                    try {
                        setClosed(true);
                    } catch (PropertyVetoException ex) {
                    }
                }
            }
        }
    }  
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/graphics/MyJInternalFrame.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
