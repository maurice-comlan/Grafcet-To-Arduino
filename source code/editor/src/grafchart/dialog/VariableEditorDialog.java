package grafchart.dialog;

import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJPopupMenu;
import grafchart.graphics.MyJTabbedPane;
import grafchart.graphics.VariableTabPanel;
import grafchart.sfc.GCDocument;
import grafchart.sfc.variables.FunctionBlock;
import grafchart.sfc.variables.StandardVariable;
import grafchart.sfc.variables.TransitionSession;
import grafchart.sfc.variables.VariableTableModel;
import java.awt.Frame;
import javax.swing.event.ChangeEvent;

public class VariableEditorDialog extends MyJDialog {
    public static final int STD_VARIABLE_TAB = 0;
    public static final int FUNC_BLOCK_TAB = 1;
    public static final int COND_SESSION_TAB = 2;
    
    public GCDocument myGCDocument;
    protected MyJPopupMenu contextMenu = new MyJPopupMenu();
    private final Frame frame;
    private int selectedTab = STD_VARIABLE_TAB;

    public VariableEditorDialog(Frame frame, GCDocument gcDocument, int selectedTab) {
        super(frame, "Data Editor");
        this.myGCDocument = gcDocument;
        this.frame = frame;  
        this.selectedTab = selectedTab;
        init();
        pack();
        updateDialog();
        setLocationRelativeTo(frame);
    }

    public int getSelectedTab() {
        return selectedTab;
    }
    
    private void init() {             
        VariableTableModel model;
        
        model = new VariableTableModel<>(myGCDocument.stdVariables, StandardVariable.getTableColumns());
        VariableTabPanel stdVariableTab = new VariableTabPanel(this);
        stdVariableTab.setModel(model);
        
        model = new VariableTableModel<>(myGCDocument.functionBlocs, FunctionBlock.getTableColumns());
        VariableTabPanel funcBlockTab = new VariableTabPanel(this);
        funcBlockTab.setModel(model);
        
        model = new VariableTableModel<>(myGCDocument.transitionSession, TransitionSession.getTableColumns());
        VariableTabPanel tranSessionTab = new VariableTabPanel(this);
        tranSessionTab.setModel(model);
        
        MyJTabbedPane tabbedPane = new MyJTabbedPane();
        tabbedPane.addTab("Variables", stdVariableTab);        
        tabbedPane.addTab("Function Bloc", funcBlockTab);
        tabbedPane.addTab("Transition Session", tranSessionTab);
        tabbedPane.setSelectedIndex(selectedTab);        
        tabbedPane.addChangeListener((ChangeEvent e) -> {
            selectedTab = ((MyJTabbedPane) e.getSource()).getSelectedIndex();
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );    
    }
    
    /**
     * Initialisation du contenu de la fenètre
     */
    private void updateDialog() {        
        
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/ServerDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */