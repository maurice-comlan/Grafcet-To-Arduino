/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafchart.sfc.variables;

import grafchart.arduino.ArduinoIO;
import java.util.Vector;
import org.w3c.dom.Element;

/**
 *
 * @author dimon
 */
public class StandardVariable extends Variable {

    private final static String[] TABLE_COLUMNS = new String[]{"Name", "Type", "Addresse", "Value", "Comment"};

    protected ArduinoIO addresse = null;
    protected Type type;

    public StandardVariable(String name, Object defaultValue, Type type, String comment) {
        this(name, defaultValue, type, comment, null);
    }

    public StandardVariable(String name, Object defaultValue, Type type, String comment, ArduinoIO addrs) {
        super(name, defaultValue, comment);
        this.type = type;
        this.addresse = addrs;
        setValue(defaultValue);
    }

    public StandardVariable() {
    }

    public ArduinoIO getAddresse() {
        return addresse;
    }

    public void setAddresse(ArduinoIO addresse) {
        this.addresse = addresse;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    boolean isDigital(){
        return addresse != null;
    }

    @Override
    public final void setValue(Object value) {
        if(value == null || String.valueOf(value).isEmpty()){
            this.value = "";
            return;
        }
        try {
            if (null == this.type) {
                this.value = String.valueOf(value);
            } else {
                switch (this.type) {
                    case BOOL:
                        this.value = Boolean.parseBoolean(String.valueOf(value));
                        if (String.valueOf(value).trim().equals("1")) {
                            this.value = Boolean.TRUE;
                        }
                        break;
                    case CHAR:
                        this.value = String.valueOf(value).charAt(0);
                        break;
                    case INT:
                    case LONG:
                    case SHORT:
                        this.value = 0;
                        this.value = Long.parseLong(String.valueOf(value));
                        break;
                    case FLOAT:
                    case DOUBLE:
                        this.value = 0.0;
                        this.value = Double.parseDouble(String.valueOf(value));
                        break;
                    default:
                        this.value = String.valueOf(value);
                        break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }   

    public static String[] getTableColumns() {
        return TABLE_COLUMNS;
    }

    @Override
    public String[] toTableRow() {
        String[] row = new String[TABLE_COLUMNS.length];
        row[0] = this.name;
        row[1] = ""+this.type;
        row[2] = this.addresse == null ? "" : this.addresse.getAdresse();
        row[3] = String.valueOf(this.value);
        row[4] = this.comment;
        return row;
    }

    public Element storeXML(Element stdElement, Vector paramVector1, Vector paramVector2) {
        paramVector1.add(this.id);
        paramVector2.add(this);
        stdElement.setAttribute("id", this.id);
        stdElement.setAttribute("name", getName());
        stdElement.setAttribute("type", getType().toString());
        stdElement.setAttribute("addresse", String.valueOf(this.addresse));
        stdElement.setAttribute("defaultValue", String.valueOf(this.value));
        stdElement.setAttribute("comment", this.comment);        
        return stdElement;
    }
    
    public static StandardVariable loadXML(Element varElement, Vector paramVector1, Vector paramVector2) {   
        Type t = null;
        String qStr = varElement.getAttribute("type");
        for (Type value : Type.values()) {
            if(qStr.equals(value.toString())){
                t = value;
                break;
            }
        }
        StandardVariable var = new StandardVariable(
                varElement.getAttribute("name"),
                varElement.getAttribute("defaultValue"),
                t,
                varElement.getAttribute("comment"),
                ArduinoIO.parseArduinoIO(varElement.getAttribute("addresse")));        
        var.id = varElement.getAttribute("id");
        
        paramVector1.add(var.id);
        paramVector2.add(var);
        return var;
    }
    
    public enum Type {
        BOOL, INT, LONG, SHORT, CHAR, STRING, FLOAT, DOUBLE
    }
}
