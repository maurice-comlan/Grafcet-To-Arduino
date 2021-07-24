/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafchart.sfc.variables;

import grafchart.arduino.ArduinoIO;
import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;

/**
 *
 * @author dimon
 */
public abstract class Variable implements Serializable, Cloneable{
    public static final String INT = "INT";
    public static final ArduinoIO[] DIGITALS_ADDR = new ArduinoIO[] { null,
        ArduinoIO.parseArduinoIO("%A0"),
        ArduinoIO.parseArduinoIO("%A1"),
        ArduinoIO.parseArduinoIO("%A2"),
        ArduinoIO.parseArduinoIO("%I2"),
        ArduinoIO.parseArduinoIO("%I3"),
        ArduinoIO.parseArduinoIO("%I4"),
        ArduinoIO.parseArduinoIO("%Q5"),
        ArduinoIO.parseArduinoIO("%Q6"),
        ArduinoIO.parseArduinoIO("%Q7"),
        ArduinoIO.parseArduinoIO("%Q8"),
        ArduinoIO.parseArduinoIO("%Q9"),
        ArduinoIO.parseArduinoIO("%Q10"),
        ArduinoIO.parseArduinoIO("%Q11")};   
    
    protected String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
    protected String name;
    protected Object value;
    protected String comment;
    public boolean isModify = false;

    public Variable(String name, Object value, String comment) {
        this.name = name;       
        this.comment = comment;
        this.value = value;
    }

    public Variable() {
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
      
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }  

    @Override
    public String toString() {
        return this.name;
    }
    
    public static String[] getTableColumns(){
        throw new UnsupportedOperationException("Method à réecrire");
    }
    public abstract String[] toTableRow();

    @Override
    public Variable clone() throws CloneNotSupportedException {
        Variable v = (Variable) super.clone(); 
        v.id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
        return v;
    }    
}
