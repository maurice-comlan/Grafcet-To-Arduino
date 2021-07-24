package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.Editor;
import grafchart.sfc.GCView;
import grafchart.sfc.GenericTransition;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.variables.StandardVariable;
import grafchart.sfc.variables.TransitionSession;
import grafchart.sfc.variables.Variable;
import grafchart.util.MyComboBoxModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TransitionDialog
        extends MyJDialog {

    private JCheckBox usesPrio = new JCheckBox();
    private MyJTextField prioValue = new MyJTextField();
    private GenericTransition t;
    private JRadioButton stdRadioButton;
    private JRadioButton strRadioButton;
    private JComboBox<Variable> variableCombo;
    private JComboBox<Object> valueCombo;

    public TransitionDialog(Frame paramFrame, GenericTransition paramGenericTransition, GCView paramGCView) {
        super(paramFrame, "Transition");
        this.t = paramGenericTransition;
        init();
        pack();
        setLocationRelativeTo(paramGCView);
        updateDialog();
    }

    private final void init() {
        JLabel jLabel1 = new javax.swing.JLabel();
        stdRadioButton = new javax.swing.JRadioButton();
        strRadioButton = new javax.swing.JRadioButton();
        variableCombo = new javax.swing.JComboBox<>();
        JLabel jLabel2 = new javax.swing.JLabel();
        valueCombo = new javax.swing.JComboBox<>();
        JButton newButton = new javax.swing.JButton();
        usesPrio = new javax.swing.JCheckBox();
        JLabel jLabel3 = new javax.swing.JLabel();
        JPanel jPanel1 = new javax.swing.JPanel();
        MyJButton jButton2 = createOKButton();
        MyJButton jButton3 = createCancelButton();
        MyJButton jButton4 = createHelpButton("LangRef_TextLang_Conditions");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Condition");

        stdRadioButton.setText("Standard Variable");
        strRadioButton.setText("Section Transition");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(stdRadioButton);
        buttonGroup.add(strRadioButton);  
        
        stdRadioButton.addActionListener((ActionEvent e) -> {     
            MyComboBoxModel model1 = new MyComboBoxModel<>(this.t.getDocument().stdVariables);
        model1.insertElementAt("Set to", 0);
            variableCombo.setModel(model1);
        });
        strRadioButton.addActionListener((ActionEvent e) -> {     
            MyComboBoxModel model2 = new MyComboBoxModel<>(this.t.getDocument().transitionSession);
            variableCombo.setModel(model2);
        });
        stdRadioButton.doClick();

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("=");

        valueCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1 (true)", "0 (false)"}));

        newButton.setText("...");
        newButton.addActionListener((java.awt.event.ActionEvent evt) -> {            
            int tab = VariableEditorDialog.STD_VARIABLE_TAB;
            JRadioButton radio = stdRadioButton;
            if(strRadioButton.isSelected()){
                tab = VariableEditorDialog.COND_SESSION_TAB;
                radio = strRadioButton;
            }
            Editor.singleton.variablesEditorAction(tab);
            radio.doClick();
        });

        usesPrio.setText("Use Priority");
        this.usesPrio.addActionListener((ActionEvent paramAnonymousActionEvent) -> {
            TransitionDialog.this.updateUsesPriority();
        });
        jLabel3.setText("Priority");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(100, 100, 125)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton3)
                                .addComponent(jButton4))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel1)
                                                        .addComponent(usesPrio)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(prioValue)
                                                                .addGap(149, 149, 149))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(stdRadioButton)
                                                                        .addComponent(variableCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(newButton)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jLabel2)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(valueCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(strRadioButton))))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(stdRadioButton)
                                        .addComponent(strRadioButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(variableCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(valueCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(newButton))
                                .addGap(18, 18, 18)
                                .addComponent(usesPrio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(prioValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void updateDialog() {
        this.usesPrio.setSelected(this.t.hasPriority);
        if (this.t.hasPriority) {
            if (this.t.priority == Integer.MAX_VALUE) {
                this.prioValue.setText("");
            } else {
                this.prioValue.setText("" + this.t.priority);
            }
        } else {
            this.prioValue.setText("");
        }        
        
        if(this.t.conditionVariable != null){
            Variable var = this.t.conditionVariable;
            if(var instanceof StandardVariable){
                stdRadioButton.doClick();
            } else if( var instanceof TransitionSession) { // autre instanceif(var instanceof ){
                strRadioButton.doClick();                
            }
            variableCombo.setSelectedItem(var);
        } 
        
        if(this.t.conditionValue){
            this.valueCombo.setSelectedIndex(0);
        } else {
            this.valueCombo.setSelectedIndex(1);
        }
        updateUsesPriority();
    }

    @Override
    protected void saveData() throws IllegalValueException {
        boolean bool = this.usesPrio.isSelected();
        int i = Integer.MAX_VALUE;
        if (bool) {
            i = parseInt(this.prioValue, 1);
        }
        
        if(stdRadioButton.isSelected() && this.variableCombo.getSelectedIndex() == 0){
            this.t.conditionVariable = null;
        } else {
            this.t.conditionVariable = (Variable) this.variableCombo.getSelectedItem();
        }
        
        if(valueCombo.getSelectedIndex() == 0){
            this.t.conditionValue = true;
        } else if(valueCombo.getSelectedIndex() == 1){
            this.t.conditionValue = false;
        }
        
        this.t.setCondition();
        this.t.hasPriority = bool;
        this.t.priority = i;
        if (bool) {
            this.t.showPriority();
        } else {
            this.t.hidePriority();
        }
    }

    protected void updateUsesPriority() {
        this.prioValue.setEnabled(this.usesPrio.isSelected());
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/TransitionDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
