package grafchart.sfc.variables;

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
 * @param <T>
 */
public class VariableTableModel<T extends Variable> extends AbstractTableModel {

    private ArrayList<T> data = new ArrayList<>(); 
    private final String[] columnNames;

    public VariableTableModel(ArrayList<T> data, String[] columns) { 
        this.data = data;
        //Iterator<T> it = data.iterator();
        columnNames = columns;
        /*
        while (it.hasNext()) {
            addRow(it.next());             
        }
        */
        fireTableStructureChanged();        
    }  
    
    

    public int addRow(T next) {
        insertRow(next, data.size());
        return data.size()-1;
    }
    
    public void insertRow(T variable, int rowIndex) {
        data.add(rowIndex, variable);
        String[] rowData = variable.toTableRow();  
        changeRow(rowData, rowIndex);    
        fireTableRowsInserted(rowIndex, rowIndex);
    }
    
    private void changeRow(String[] rowData, int rowIndex) {
        for (int colIndex = 0; colIndex < rowData.length; colIndex++) {
            setValueAt(rowData[colIndex], rowIndex, colIndex);
        }
    }    
    
    public void updateRow(T variable, int rowIndex){
        data.set(rowIndex, variable);
        changeRow(variable.toTableRow(), rowIndex);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
    
    public T getVariableAt(int rowIndex){
        return data.get(rowIndex);
    }
    
    public boolean removeRow(int rowIndex){
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
        return true;
    }
    
    public ArrayList getVariables(){
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
        T v = data.get(rowIndex);
        String[] row = v.toTableRow();
        return row[columnIndex];
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        super.removeTableModelListener(l); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
}
