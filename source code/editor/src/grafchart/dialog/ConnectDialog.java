package grafchart.dialog;

import grafchart.arduino.ArduinoBoard;
import grafchart.graphics.MyJButton;
import grafchart.graphics.MyJDialog;
import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.IllegalValueException;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectDialog extends MyJDialog {

    private JTextField portCOMTextField;
    private JComboBox<ArduinoBoard> boardCombo;
    private JLabel conStateLabel;
    private GCDocument GCDocument;

    public ConnectDialog(Frame paramFrame,GCDocument document) {        
        super(paramFrame, "Automate connexion", true);   
        this.GCDocument = document;
        init();                    
        pack();
        setLocationRelativeTo(paramFrame);   
        updateDialog(); 
    }

    private void init() {    
        portCOMTextField = new javax.swing.JTextField();
        JLabel jLabel1 = new javax.swing.JLabel();
        boardCombo = new javax.swing.JComboBox<>();
        JLabel jLabel2 = new javax.swing.JLabel();
        conStateLabel = new javax.swing.JLabel();
        JPanel jPanel1 = new javax.swing.JPanel();
        MyJButton okButton = createOKButton();
        MyJButton cancelButton = createCancelButton();
        MyJButton testButton = createApplyButton();
        
        portCOMTextField.setText("ttyACM0");
        
        jLabel1.setText("Port COM");
        boardCombo.setModel(new javax.swing.DefaultComboBoxModel<>(ArduinoBoard.BOARDS));
        boardCombo.setSelectedIndex(-1);  
        jLabel2.setText("Board Type");
        conStateLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        conStateLabel.setText(" ");        
        testButton.setText("Test connextion");      

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(testButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(testButton)
                .addComponent(okButton)
                .addComponent(cancelButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(boardCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(portCOMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(conStateLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(portCOMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boardCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(conStateLabel)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    @Override
    protected void saveData() throws IllegalValueException {    
        ArduinoBoard board = (ArduinoBoard) boardCombo.getSelectedItem();
        if(board != null){
            Editor.myBoard = board;
            Editor.myBoard.setPortCOM(portCOMTextField.getText());
            Editor.myBoard.setConnected(Editor.myBoard.testConnexionAction());
        }
    }
    
    private void updateDialog() {
        if(Editor.myBoard.getName() != null){
            boardCombo.setSelectedItem(Editor.myBoard); 
            portCOMTextField.setText(Editor.myBoard.getPortCOM());
        }
    }

    @Override
    protected void onApply() {
        super.onApply();
        if(Editor.myBoard.isConnected()){
            conStateLabel.setText("Connexion test successfull");
        } else {
            conStateLabel.setText("Connexion test failed");
        }
    }
    
    
}


/* Location:              /home/dimon/GIT5/Memoire/JGrafchart/JGrafchart-2.6.1/lib/JGrafchart.jar!/grafchart/dialog/StepNameDialog.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */
