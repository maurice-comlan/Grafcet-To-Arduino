package grafchart.dialog;

import grafchart.arduino.ArduinoIO;
import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.graphics.MyJTextField;
import grafchart.sfc.IllegalValueException;
import grafchart.sfc.variables.StandardVariable;
import grafchart.sfc.variables.Variable;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class NewStdVariableDialog extends MyJDialog {
    private final StandardVariable variable;
    private MyJTextField nameField;
    private JComboBox<StandardVariable.Type> typeCombo;
    private JTextField initialValueField;
    private JComboBox<ArduinoIO> addresseCombo;
    private JTextPane commentTextPane;

    public NewStdVariableDialog(Frame paramFrame, StandardVariable var) {        
        super(paramFrame, "New Standard Variable", true);
        this.variable = var;        
        init();
        setLocationRelativeTo(paramFrame);
        //Bricolage
        if(this.variable.getName() != null){            
            updateDialog();
        }              
        pack();
    }

    private final void init() {    
        JLabel jLabel1 = new javax.swing.JLabel();
        nameField = new MyJTextField();
        JLabel jLabel2 = new javax.swing.JLabel();
        typeCombo = new javax.swing.JComboBox<>();
        JLabel jLabel3 = new javax.swing.JLabel();
        initialValueField = new javax.swing.JTextField();
        JLabel jLabel4 = new javax.swing.JLabel();
        addresseCombo = new javax.swing.JComboBox<>();
        JLabel jLabel5 = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        commentTextPane = new javax.swing.JTextPane();
        JPanel jPanel1 = new javax.swing.JPanel();
        MyJButton okButton = createOKButton();
        MyJButton cancelButton = createCancelButton();

        jLabel1.setText("Name");
        jLabel2.setText("Type");    
        jLabel3.setText("Initial Value");
        jLabel4.setText("Adresse");
        jLabel5.setText("Comment");
        
        typeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(StandardVariable.Type.values()));
        typeCombo.addItemListener((ItemEvent e) -> {
            if(!(((StandardVariable.Type) e.getItem()).equals(StandardVariable.Type.BOOL)
                    || ((StandardVariable.Type) e.getItem()).equals(StandardVariable.Type.INT))){
                addresseCombo.setSelectedIndex(0);
            }
        });
        addresseCombo.setModel(new javax.swing.DefaultComboBoxModel<>(Variable.DIGITALS_ADDR));
        addresseCombo.addItemListener((ItemEvent e) -> {
            if(addresseCombo.getSelectedIndex() > 0){
                switch(((ArduinoIO) e.getItem()).getType()){
                    case ArduinoIO.ANALOG_IN:
                        typeCombo.setSelectedItem(StandardVariable.Type.INT);
                        break;
                    case ArduinoIO.DIGITAL_IN:
                    case ArduinoIO.DIGITAL_OUT:
                        typeCombo.setSelectedItem(StandardVariable.Type.BOOL);
                        break;
                }               
            }
        });
        jScrollPane1.setViewportView(commentTextPane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(okButton)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(okButton)
                .addComponent(cancelButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(initialValueField)
                            .addComponent(typeCombo, 0, 200, Short.MAX_VALUE)
                            .addComponent(nameField))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(addresseCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addresseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(typeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(initialValueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }

    private void updateDialog() {
        this.setTitle(this.variable.getName() + " - Standard Variable");
        this.nameField.setText(this.variable.getName().trim());
        this.commentTextPane.setText(this.variable.getComment().trim());
        this.initialValueField.setText(this.variable.getValue().toString());
        this.typeCombo.setSelectedItem(this.variable.getType());
        this.addresseCombo.setSelectedItem(this.variable.getAddresse());
        /*
        this.variable.setAddresse(this.addresseCombo.getSelectedItem().toString());        
        this.variable.setType(this.typeCombo.getSelectedItem().toString());     
        */
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
        this.variable.setAddresse((ArduinoIO) this.addresseCombo.getSelectedItem());
        this.variable.setComment(this.commentTextPane.getText().trim());
        this.variable.setType((StandardVariable.Type) this.typeCombo.getSelectedItem());
        this.variable.setValue(this.initialValueField.getText()); 
//        if(this.variable.getAddresse() != null)
//            this.variable.setType(StandardVariable.Type.BOOL);
        this.variable.isModify = true;
    }
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/StepNameDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
