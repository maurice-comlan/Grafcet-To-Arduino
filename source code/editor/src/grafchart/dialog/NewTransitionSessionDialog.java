package grafchart.dialog;

import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.variables.FunctionBlock;
import grafchart.sfc.variables.TransitionSession;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class NewTransitionSessionDialog extends MyJDialog {
    private final TransitionSession variable;
    private JTextField nameField;
    private JComboBox<FunctionBlock.Language> langCombo;
    private JTextPane contentText;

    public NewTransitionSessionDialog(Frame paramFrame, TransitionSession var) {        
        super(paramFrame, "New Transition Session", true);
        this.variable = var;        
        init();        
        setLocationRelativeTo(paramFrame);
        //Bricolage
        if(this.variable.getName() != null){
            updateDialog();
        }     
        pack();
    }

    private void init() {    
        nameField = new javax.swing.JTextField();
        JLabel jLabel1 = new javax.swing.JLabel();
        langCombo = new javax.swing.JComboBox<>();
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        contentText = new javax.swing.JTextPane();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JPanel jPanel1 = new javax.swing.JPanel();
        MyJButton jButton1 = createOKButton();
        MyJButton jButton2 = createCancelButton();

        jLabel1.setText("Name");        
        jLabel2.setText("Language");
        jLabel3.setText("Condition");
        contentText.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        //jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setViewportView(contentText);
        langCombo.setModel(new javax.swing.DefaultComboBoxModel<>(FunctionBlock.Language.values()));
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(135, 135, 135))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jButton2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(langCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3))
                        .addGap(0, 79, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(langCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }

    private void updateDialog() {
        setTitle(this.variable.getName() + " - Transition Session");
        this.nameField.setText(this.variable.getName().trim());
        this.langCombo.setSelectedItem(this.variable.getLanguage());
        this.contentText.setText(this.variable.getValue().toString());
        this.nameField.setFont(new Font("Dialog", Font.BOLD, 14));
    }

    /*
    @Override
    protected void onOK() {
        if(this.nameField.getText().trim().isEmpty()){
            this.nameField.setBackground(Color.red);
        } else {
            super.onOK();            
        }
    }
    */
    
    @Override
    protected void saveData() throws IllegalValueException {        
        this.variable.setName(this.nameField.getText().trim());
        this.variable.setLanguage((FunctionBlock.Language)this.langCombo.getSelectedItem());
        this.variable.setValue(this.contentText.getText()); 
        this.variable.isModify = true;
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/StepNameDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
