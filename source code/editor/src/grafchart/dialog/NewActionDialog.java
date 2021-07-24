package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.Utils;
import grafchart.sfc.actions.Action;
import grafchart.sfc.variables.StandardVariable;
import grafchart.sfc.variables.Variable;
import grafchart.util.MyComboBoxModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

public class NewActionDialog extends MyJDialog {
    private final Action action;
    private JComboBox<Object> qualifierCombo;
    private JPanel timePanel;
    private JSpinner timeSpinner;
    private JComboBox<Object> variableCombo;
    private JRadioButton fbRadioButton;
    private JRadioButton stdRadioButton;
    private final GCDocument document;
    private JLabel jLabel3;

    public NewActionDialog(Frame paramFrame, Action a, GCDocument doc) {        
        super(paramFrame, "Action", true);
        this.action = a;        
        this.document = doc;
        init();
        pack();
        setLocationRelativeTo(paramFrame);
        //Bricolage
        updateDialog();
    }

    private final void init() {    
        JLabel jLabel1 = new javax.swing.JLabel();
        qualifierCombo = new javax.swing.JComboBox<>();
        JLabel jLabel2 = new javax.swing.JLabel();
        timePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        timeSpinner = new javax.swing.JSpinner();
        JLabel jLabel4 = new javax.swing.JLabel();
        stdRadioButton = new javax.swing.JRadioButton();
        fbRadioButton = new javax.swing.JRadioButton();
        variableCombo = new javax.swing.JComboBox<>();
        JButton newButton = new javax.swing.JButton();
        JPanel jPanel1 = new javax.swing.JPanel();
        MyJButton OkButton = createOKButton();
        MyJButton cancelButton = createCancelButton();

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Qualifier");        
        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Time (ms)");
        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Action Variable");       
        
        enableTimePanel(false);        
        
        qualifierCombo.setModel(new DefaultComboBoxModel<>(Action.Qualifier.values()));
        qualifierCombo.addItemListener((ItemEvent e) -> {
            Action.Qualifier q = (Action.Qualifier)e.getItem();
            if(q.hasTime()){
                enableTimePanel(true); 
                
            } else {
                enableTimePanel(false); 
            }
        });
        
        stdRadioButton.setText("Standard ");
        fbRadioButton.setText("Function Block");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(stdRadioButton);
        buttonGroup.add(fbRadioButton);    
        stdRadioButton.addActionListener((ActionEvent e) -> {
            ArrayList<StandardVariable> varList = new ArrayList<>();
            for (StandardVariable var : this.document.stdVariables) {
                if(var.getType() == StandardVariable.Type.BOOL){
                    varList.add(var);
                }
            }
            MyComboBoxModel model = new MyComboBoxModel<>(varList);
            variableCombo.setModel(model);
        });
        fbRadioButton.addActionListener((ActionEvent e) -> {
            MyComboBoxModel model = new MyComboBoxModel<>(this.document.functionBlocs);
            variableCombo.setModel(model);
        });
        stdRadioButton.doClick();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(timePanel);
        timePanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        
        newButton.setText("...");
        newButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            int tab = VariableEditorDialog.STD_VARIABLE_TAB;
            JRadioButton radio = stdRadioButton;
            if(fbRadioButton.isSelected()){
                tab = VariableEditorDialog.FUNC_BLOCK_TAB;
                radio = fbRadioButton;
            }
            Editor.singleton.variablesEditorAction(tab);
            radio.doClick(); 
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(OkButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(OkButton)
                .addComponent(cancelButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(qualifierCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(timePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(variableCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(newButton))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(stdRadioButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fbRadioButton))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(qualifierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stdRadioButton)
                            .addComponent(fbRadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(variableCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newButton))))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void updateDialog() {
        if(this.action.getVariable()!= null){
            this.qualifierCombo.setSelectedItem(this.action.getQualifier());
            if(this.action.getQualifier().hasTime()){
                this.timeSpinner.setValue(this.action.getTime());
            }
            Variable var = this.action.getVariable();
            if(var instanceof StandardVariable){
                stdRadioButton.doClick();
            } else { // autre instanceif(var instanceof ){
                fbRadioButton.doClick();
            }
            variableCombo.setSelectedItem(var);             
        } 
    }   

    @Override
    protected void saveData() throws IllegalValueException {        
        this.action.setQualifier((Action.Qualifier) this.qualifierCombo.getSelectedItem());
        this.action.setTime(0);
        if(this.action.getQualifier().hasTime()){
            this.action.setTime(Utils.parseLong(timeSpinner.getValue().toString()));
        }
        this.action.setVariable((Variable)this.variableCombo.getSelectedItem());
        this.action.isModify = true;
    }

    private void enableTimePanel(boolean b) {
        timePanel.setEnabled(b);
        jLabel3.setEnabled(b);
        timeSpinner.setEnabled(b);
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/StepNameDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
