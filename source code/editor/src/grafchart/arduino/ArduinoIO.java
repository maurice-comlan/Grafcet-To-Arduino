/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafchart.arduino;

import grafchart.sfc.Utils;

/**
 *
 * @author dimon
 */
public class ArduinoIO {

    public final static int DIGITAL_IN=0, DIGITAL_OUT=1, ANALOG_IN=2, ANALOG_OUT=3;
    private final int pin;
    private final int type;

    public ArduinoIO(int pin, int type) {
        this.pin = pin;
        this.type = type;
    }

    @Override
    public String toString() {
        return getAdresse();
    }

    public String getAdresse() {
        switch(type){
            case DIGITAL_IN:
                return "%I"+pin;
            case ANALOG_IN:
                return "%A"+pin;
            case DIGITAL_OUT:
            case ANALOG_OUT:
                return "%Q"+pin;    
            default:
                    throw new IllegalStateException("Impossible d'avoir l'adresse de l'E/S");
        }        
    }

    public int getPin() {
        return pin;
    }

    public int getType() {
        return type;
    }
        
    public static ArduinoIO parseArduinoIO(String attribute) {
        if(attribute == null || attribute.isEmpty())
            return null;
        
        int t;
        if(attribute.charAt(1) == 'I')
            t = DIGITAL_IN;
        else if(attribute.charAt(1) == 'A')
            t = ANALOG_IN;
        else if(attribute.charAt(1) == 'Q')
            t = DIGITAL_OUT;
        else return null;
               
        
        int p = Utils.parseInt(attribute.substring(2), -1);
        if(p < 0) return null;
        
        return new ArduinoIO(p, t);
    }    

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ArduinoIO){
            return (this.pin == ((ArduinoIO) obj).getPin() && this.type == ((ArduinoIO) obj).getType());
        }
        return false;
    }    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.pin;
        hash = 67 * hash + this.type;
        return hash;
    }
}
