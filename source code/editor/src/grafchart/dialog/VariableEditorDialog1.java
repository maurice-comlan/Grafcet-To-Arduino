package grafchart.dialog;

import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJPopupMenu;
import grafchart.graphics.MyJTabbedPane;
import grafchart.sfc.AppAction;
import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.variables.StandardVariable;
import grafchart.sfc.variables.VariableTableModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;

public class VariableEditorDialog1
        extends MyJDialog {
    
    AppAction InsertNewAction = new AppAction("Insert new", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            VariableEditorDialog1.this.insertNewAction(true);
        }
    };
    
    AppAction DuplicateAction = new AppAction("Duplicate", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            VariableEditorDialog1.this.duplicateAction();
        }
    };
    
    AppAction EditAction = new AppAction("Edit", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            VariableEditorDialog1.this.editAction();
        }
    };
    
    AppAction DeleteAction = new AppAction("Delete", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            VariableEditorDialog1.this.deleteAction();
        }
    };
    
    AppAction AnalyseAction = new AppAction("Analyse", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            VariableEditorDialog1.this.analyseAction();
        }
    };

    private GCDocument myGCDocument;
    private JPanel variablePanel = new JPanel(null);
    private VariableTableModel<StandardVariable> stdVariableModel;
    private JTable stdVarTable = new javax.swing.JTable();
    protected MyJPopupMenu contextMenu = new MyJPopupMenu();
    private final Frame frame;
    private int selectedTab = 0;

    public VariableEditorDialog1(Frame frame, GCDocument gcDocument) {
        super(frame, "Data Editor");
        myGCDocument = gcDocument;
        this.frame = frame;
        init();
        initPopupMenu();
        pack();
        updateDialog();
        setLocationRelativeTo(frame);
    }
    
    private void init() { 
        initVariablePanel();
        
        MyJTabbedPane tabbedPane = new MyJTabbedPane();
        tabbedPane.addTab("Variables", variablePanel);
        tabbedPane.setSelectedIndex(0);
        tabbedPane.addTab("Function Bloc", new JPanel());
        
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
    
    private void initVariablePanel() {
        JPanel jPanel3 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JTextField jTextField1 = new javax.swing.JTextField();
        JButton jButton1 = new javax.swing.JButton();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JPanel jPanel2 = new javax.swing.JPanel();

        jLabel1.setText("Filter by name : ");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                // Lorsquon saisi dans le TextField
            }
        });

        jButton1.setText("New variable");
        jButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
            insertNewAction(false);
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1))
        );
        
        jScrollPane1.setViewportView(stdVarTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(variablePanel);
        variablePanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );       
    }

    /**
     * Initialisation du contenu de la fenètre
     */
    private void updateDialog() {        
        stdVariableModel = new VariableTableModel<>(myGCDocument.stdVariables, StandardVariable.getTableColumns());
        stdVarTable.setModel(stdVariableModel);
        stdVarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stdVarTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        stdVarTable.setAutoCreateRowSorter(true);        
        stdVarTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = stdVarTable.rowAtPoint(e.getPoint());
                stdVarTable.setRowSelectionInterval(i, i);
                int row = stdVarTable.getSelectedRow();
                if(e.getButton() == MouseEvent.BUTTON3){
                    VariableEditorDialog1.this.contextMenu.show(stdVarTable, e.getX(), e.getY());
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            
        });
        stdVarTable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                int row = stdVarTable.getSelectedRow();
                switch(e.getKeyCode()){
                    case KeyEvent.VK_DELETE :      
                        deleteAction();
                        break;
                }}
        });
        
    }

    private void initPopupMenu() {
        this.contextMenu.setLabel("Variable");
        this.contextMenu.add(this.EditAction, KeyEvent.VK_E);
        this.contextMenu.add(this.DeleteAction, KeyEvent.VK_D);
        this.contextMenu.add(this.DuplicateAction, KeyEvent.VK_U);
        this.contextMenu.add(new JPopupMenu.Separator());
        this.contextMenu.add(this.InsertNewAction, KeyEvent.VK_N);
        this.contextMenu.add(new JPopupMenu.Separator());
        this.contextMenu.add(this.AnalyseAction, KeyEvent.VK_A);
    }

    public void insertNewAction(boolean atPos) {
        if(selectedTab == 0){
            StandardVariable newVar = new StandardVariable();            
            new NewStdVariableDialog(myGCDocument.getView().getFrame(), newVar).setVisible(true);
            if(newVar.isModify){
                int i;
                if(atPos){
                    i = stdVarTable.getSelectedRow() + 1;
                    stdVariableModel.insertRow(newVar, i);
                } else {
                    i = stdVariableModel.addRow(newVar);
                }
                stdVarTable.setRowSelectionInterval(i,i);
            }  
        }
    }

    private void duplicateAction() {
        if(selectedTab == 0){        
            int i = stdVarTable.getSelectedRow();
            StandardVariable newVar;
            try {
                newVar = (StandardVariable) stdVariableModel.getVariableAt(i).clone();
                stdVariableModel.insertRow(newVar, i++);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(VariableEditorDialog1.class.getName()).log(Level.SEVERE, null, ex);
            }
            stdVarTable.setRowSelectionInterval(i,i);
        }
    }

    private void editAction() {
        if(selectedTab == 0){          
            int i = stdVarTable.getSelectedRow();
            StandardVariable var = stdVariableModel.getVariableAt(i);            
            new NewStdVariableDialog(myGCDocument.getView().getFrame(), var).setVisible(true);
            if(var.isModify){
                stdVariableModel.updateRow(var, i);
                stdVarTable.setRowSelectionInterval(i,i);                
            }
        }
    }

    private void deleteAction() {
        int row = stdVarTable.getSelectedRow();
        if(row >= 0){
            int decision = JOptionPane.showConfirmDialog(rootPane, "Are you sure ?", "Delete variable", JOptionPane.YES_NO_OPTION);
            if(decision == JOptionPane.YES_OPTION ){
                stdVariableModel.removeRow(row);
            }
        }
    }

    private void analyseAction() {
        System.out.println("Analyse");
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/ServerDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */