/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafchart.graphics;

import grafchart.dialog.NewFuncBlockDialog;
import grafchart.dialog.NewStdVariableDialog;
import grafchart.dialog.NewTransitionSessionDialog;
import grafchart.dialog.VariableEditorDialog;
import grafchart.sfc.AppAction;
import grafchart.sfc.Editor;
import grafchart.sfc.variables.FunctionBlock;
import grafchart.sfc.variables.StandardVariable;
import grafchart.sfc.variables.TransitionSession;
import grafchart.sfc.variables.Variable;
import grafchart.sfc.variables.VariableTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
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

/**
 *
 * @author dimon
 */
public class VariableTabPanel extends JPanel{

    AppAction InsertNewAction = new AppAction("Insert new", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            insertNewAction(true);
        }
    };
    
    AppAction DuplicateAction = new AppAction("Duplicate", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            duplicateAction();
        }
    };
    
    AppAction EditAction = new AppAction("Edit", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            editAction();
        }
    };
    
    AppAction DeleteAction = new AppAction("Delete", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            deleteAction();
        }
    };
    
    AppAction AnalyseAction = new AppAction("Analyse", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            analyseAction();
        }
    };
    
    private final VariableEditorDialog variableEditor;
    private JTable varTable = new javax.swing.JTable();
    protected MyJPopupMenu contextMenu = new MyJPopupMenu();
    private VariableTableModel variableModel;

    public VariableTabPanel(VariableEditorDialog variableEditor) {
        this.variableEditor = variableEditor;
        init();
        initPopupMenu();
        initTable();
    }

    private void init() {
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
        
        jScrollPane1.setViewportView(varTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this);
        setLayout(jPanel1Layout);
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
    
    private void initTable() {
        varTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        varTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        varTable.setAutoCreateRowSorter(true);        
        varTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = varTable.rowAtPoint(e.getPoint());
                varTable.setRowSelectionInterval(i, i);
                int row = varTable.getSelectedRow();
                if(e.getButton() == MouseEvent.BUTTON3){
                    contextMenu.show(varTable, e.getX(), e.getY());
                }
            }            
        });
        varTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int row = varTable.getSelectedRow();
                switch(e.getKeyCode()){
                    case KeyEvent.VK_DELETE :      
                        deleteAction();
                        break;
                }}
        });
    }

    private void sendToRightDialog(Variable newVar) {
        if(newVar instanceof StandardVariable){
            new NewStdVariableDialog(variableEditor.myGCDocument.getView().getFrame(), (StandardVariable) newVar).setVisible(true);
        }  else if(newVar instanceof TransitionSession){
            new NewTransitionSessionDialog(variableEditor.myGCDocument.getView().getFrame(), (TransitionSession) newVar).setVisible(true);
        } else if(newVar instanceof FunctionBlock){
            new NewFuncBlockDialog(variableEditor.myGCDocument.getView().getFrame(), (FunctionBlock) newVar).setVisible(true);
        }
        
    }

    public void setModel(VariableTableModel variableModel) {
        this.variableModel = variableModel;
        varTable.setModel(variableModel);
    }
    
    public void insertNewAction(boolean atPos) {
        Variable newVar;
        int tab = variableEditor.getSelectedTab();
        if(tab == VariableEditorDialog.STD_VARIABLE_TAB){
            newVar = new StandardVariable(); 
        } else if(tab == VariableEditorDialog.FUNC_BLOCK_TAB) {
            newVar = new FunctionBlock();
        } else if(tab == VariableEditorDialog.COND_SESSION_TAB) {
            newVar = new TransitionSession();
        } else {
            newVar = null;
        }
           
        sendToRightDialog(newVar);
        if(newVar.isModify){
            int i;
            if(atPos){
                i = varTable.getSelectedRow() + 1;
                variableModel.insertRow(newVar, i);
            } else {
                i = variableModel.addRow(newVar);
            }
            varTable.setRowSelectionInterval(i,i);
        }  
    }

    private void duplicateAction() {     
        int i = varTable.getSelectedRow();
        Variable newVar;
        try {
            newVar = (Variable) variableModel.getVariableAt(i).clone();
            variableModel.insertRow(newVar, i++);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(VariableEditorDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        varTable.setRowSelectionInterval(i,i);
    }

    private void editAction() {      
        int i = varTable.getSelectedRow();
        Variable var = variableModel.getVariableAt(i);            
        sendToRightDialog(var);
        if(var.isModify){
            variableModel.updateRow(var, i);
            varTable.setRowSelectionInterval(i,i);                
        }
    }

    private void deleteAction() {
        int row = varTable.getSelectedRow();
        if(row >= 0){
            int decision = JOptionPane.showConfirmDialog(variableEditor.getRootPane(), "Are you sure ?", "Delete variable", JOptionPane.YES_NO_OPTION);
            if(decision == JOptionPane.YES_OPTION ){
                variableModel.removeRow(row);
            }
        }
    }

    private void analyseAction() {
        System.out.println("Analyse");
    }
}
