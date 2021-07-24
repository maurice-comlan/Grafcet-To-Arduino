/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafchart.arduino;

/**
 *
 * @author dimon
 */
public class ArduinoBoard {
    private String name;
    private String protocol;
    private String maximum_size;
    private String speed;
    private String mcu;
    private String f_cpu;
    private String core;
    private String variant;    
    
    private boolean connected = false;
    private String portCOM = "";
    
    public static final ArduinoBoard BOARDS[] = new ArduinoBoard[]{
        new ArduinoBoard("Arduino Uno",  "arduino", "32256", "115200", "atmega328p", "16000000L", "arduino", "standard"),
        new ArduinoBoard("Arduino Mega", "arduino", "126976", "57600", "atmega1280", "16000000L", "arduino", "mega")
    };

    public ArduinoBoard(String name) {
        this.name = name;
    }

    public ArduinoBoard(String name, String protocol, String maximum_size, String speed, String mcu, String f_cpu, String core, String variant) {
        this.name = name;
        this.protocol = protocol;
        this.maximum_size = maximum_size;
        this.speed = speed;
        this.mcu = mcu;
        this.f_cpu = f_cpu;
        this.core = core;
        this.variant = variant;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMaximum_size() {
        return maximum_size;
    }

    public void setMaximum_size(String maximum_size) {
        this.maximum_size = maximum_size;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getCpu() { 
        return mcu;
    }

    public void setMcu(String mcu) {
        this.mcu = mcu;
    }

    public String getF_cpu() {
        return f_cpu;
    }

    public void setF_cpu(String f_cpu) {
        this.f_cpu = f_cpu;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    
    public String getPortCOM() {
        return portCOM;
    }

    public void setPortCOM(String portCOM) {
        this.portCOM = portCOM;
    }

    @Override
    public String toString() {
        return this.name +" ("+ this.mcu +")";
    }    

    public boolean testConnexionAction() {
        return !"".equals(this.portCOM);
    }
}
