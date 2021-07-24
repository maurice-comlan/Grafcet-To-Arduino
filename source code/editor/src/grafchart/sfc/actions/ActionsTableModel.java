package grafchart.sfc.actions;

import grafchart.sfc.variables.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dimon
 */
public class ActionsTableModel extends AbstractTableModel {

    private ArrayList<Action> data = new ArrayList<>(); 
    private String[] columnNames = new String[]{"Qualifier", "Time", "Variable"};

    public ActionsTableModel(ArrayList<Action> data) { 
        this.data = data;
        fireTableStructureChanged();        
    }  
    
    

    public int addRow(Action next) {
        insertRow(next, data.size());
        return data.size()-1;
    }
    
    public void insertRow(Action action, int rowIndex) {
        data.add(rowIndex, action);
        changeRow(action, rowIndex);    
        fireTableRowsInserted(rowIndex, rowIndex);
    }
    
    private void changeRow(Action action, int rowIndex) {
        /*
        setValueAt(action.getQualifier(), rowIndex, 0);
        setValueAt(action.getTime(), rowIndex, 1);
        setValueAt(action.getVariable(), rowIndex, 2);
        */
    }    
    
    public void updateRow(Action action, int rowIndex){
        data.set(rowIndex, action);
        changeRow(action, rowIndex);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
    
    public Action getActionAt(int rowIndex){
        return data.get(rowIndex);
    }
    
    public boolean removeRow(int rowIndex){
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
        return true;
    }
    
    public ArrayList getActions(){
        return data;
    }
            
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Action a = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getQualifier();
            case 1:
                return a.getTime();
            case 2:
                return a.getVariable();
            default:
                throw new ArrayIndexOutOfBoundsException("Illegal column index");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public String getActionsText() {
        String text = "";
        Iterator<Action> it = data.iterator();
        while (it.hasNext()) {
            text += it.next() + "\n";
            
        }
        
        return text;
    }

    
    
    

    
    
    
    
    
    
      
    
}
