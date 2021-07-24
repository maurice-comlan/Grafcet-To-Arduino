package grafchart.dialog;

import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJPopupMenu;
import grafchart.sfc.AppAction;
import grafchart.sfc.Editor;
import grafchart.sfc.GCStep;
import grafchart.sfc.GCView;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.actions.Action;
import grafchart.sfc.actions.ActionsTableModel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
 * Edition de l'action relative à une étape
 *
 * @author dimon
 */
public class StepDialog extends MyJDialog {

    AppAction InsertNewAction = new AppAction("Insert new", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            StepDialog.this.insertNewAction(true);
        }
    };
    
    AppAction MoveUpAction = new AppAction("Move Up", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            StepDialog.this.moveUpAction();
        }
    };
    
    AppAction MoveDownAction = new AppAction("Move Down", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            StepDialog.this.moveDownAction();
        }
    };
    
    AppAction DuplicateAction = new AppAction("Duplicate", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            StepDialog.this.duplicateAction();
        }
    };
    
    AppAction EditAction = new AppAction("Edit", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            StepDialog.this.editAction();
        }
    };
    
    AppAction DeleteAction = new AppAction("Delete", Editor.singleton) {
        @Override
        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
            StepDialog.this.deleteAction();
        }
    };
    
    private GCStep step;
    private JTextField nameField;
    private JTable actionsTable;
    private ActionsTableModel actionTableModel;
    private final Frame frame;
    protected MyJPopupMenu contextMenu = new MyJPopupMenu();

    public StepDialog(Frame parentFrame, GCStep step, GCView paramGCView) {
        super(parentFrame, "Step", false);
        this.step = step;
        this.frame = parentFrame;
        init();
        initPopupMenu();
        pack();
        setLocationRelativeTo(paramGCView);
        updateDialog();
    }

    private void init() {
        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        JPanel jPanel2 = new javax.swing.JPanel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        actionsTable = new javax.swing.JTable();
        JButton addButton = new javax.swing.JButton();
        JPanel jPanel3 = new javax.swing.JPanel();
        JButton okButton = createOKButton();
        JButton cancelButton = createCancelButton();
        JButton helpButton = createHelpButton("LanguageOverview_ActionLanguage");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("General")));

        jLabel1.setText("Step Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameField)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Actions"));
        
        jScrollPane1.setViewportView(actionsTable);

        addButton.setText("Add Action");
        addButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            insertNewAction(false);
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addButton)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(93, Short.MAX_VALUE)
                                .addComponent(okButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(helpButton)
                                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(okButton)
                                .addComponent(cancelButton)
                                .addComponent(helpButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    private void updateDialog() {
        this.nameField.setText(this.step.getName());
        this.nameField.setFont(new Font("Dialog", Font.BOLD, 14));
        
        actionTableModel = new ActionsTableModel(step.actionsList);
        actionsTable.setModel(actionTableModel);
        actionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        actionsTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        actionsTable.setAutoCreateRowSorter(true);    
        actionsTable.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        actionsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = actionsTable.rowAtPoint(e.getPoint());
                actionsTable.setRowSelectionInterval(i, i);
                int row = actionsTable.getSelectedRow();
                if(e.getButton() == MouseEvent.BUTTON3){
                    contextMenu.show(actionsTable, e.getX(), e.getY());
                }
            }            
        });
        actionsTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int row = actionsTable.getSelectedRow();
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
        this.contextMenu.add(this.MoveUpAction, KeyEvent.VK_P);
        this.contextMenu.add(this.MoveDownAction, KeyEvent.VK_O);
        this.contextMenu.add(new JPopupMenu.Separator());
        this.contextMenu.add(this.InsertNewAction, KeyEvent.VK_N);
    }

    @Override
    protected void saveData() throws IllegalValueException {
        this.step.setName(this.nameField.getText());
        this.step.setActionText(this.actionTableModel.getActionsText());
    }

    private void insertNewAction(boolean b) {
        Action newAction = new Action();            
            new NewActionDialog(this.frame, newAction, this.step.getDocument()).setVisible(true);
            if(newAction.isModify){
                int i = actionTableModel.addRow(newAction);
                actionsTable.setRowSelectionInterval(i,i);
            }
    }

    private void moveUpAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void moveDownAction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void duplicateAction() {
        int i = actionsTable.getSelectedRow();
        Action newAction;
        try {
            newAction =  actionTableModel.getActionAt(i).clone();
            actionTableModel.insertRow(newAction, i++);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }
        actionsTable.setRowSelectionInterval(i,i);
    }

    private void editAction() {
        int i = actionsTable.getSelectedRow();
        Action action = actionTableModel.getActionAt(i);   
        new NewActionDialog(this.frame, action, this.step.getDocument()).setVisible(true);
        if(action.isModify){
            actionTableModel.updateRow(action, i);
            actionsTable.setRowSelectionInterval(i,i);                
        }
    }

    private void deleteAction() {
        int row = actionsTable.getSelectedRow();
        if (row >= 0) {
            int decision = JOptionPane.showConfirmDialog(rootPane, "Are you sure ?", "Delete action", JOptionPane.YES_NO_OPTION);
            if (decision == JOptionPane.YES_OPTION) {
                actionTableModel.removeRow(row);
            }
        }
    }
}

