/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafchart.arduino;

import grafchart.sfc.Editor;
import grafchart.sfc.GCDocument;
import grafchart.sfc.GCStep;
import grafchart.sfc.GCStepInitial;
import grafchart.sfc.GCTransition;
import grafchart.sfc.GrafcetObject;
import grafchart.sfc.actions.Action;
import grafchart.sfc.variables.FunctionBlock;
import grafchart.sfc.variables.StandardVariable;
import grafchart.sfc.variables.TransitionSession;
import grafchart.sfc.variables.Variable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permet de produire le code c
 * @author dimon
 */
public class CGeneretor {
    private String destination;
    private final GCDocument doc;
    private PrintStream sourceCodeStream;
    private ArrayList<FunctionBlock> usedBlockFunction = new ArrayList();  

    public CGeneretor(GCDocument doc) {
        this.doc = doc;
        this.destination = doc.getWriteFileLocation()+"_Arduino.ino";
    }

    public CGeneretor(GCDocument gcDoc, String path) {
        this.doc = gcDoc;
        this.destination = path;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
     
    public void generate() {
        try {
            sourceCodeStream = new PrintStream(this.destination);
            
            usedBlockFunction.clear();        
            for (int i = 0; i < doc.steps.size(); i++) {
                GCStep step = doc.steps.get(i);
                for (int j = 0; j < step.actionsList.size(); j++) {
                    Action action = step.actionsList.get(j);
                    Variable var = action.getVariable();
                    if(var instanceof FunctionBlock){
                        if(usedBlockFunction.indexOf(var) == -1){
                            usedBlockFunction.add((FunctionBlock) var);
                        }
                    }
                }                  
            }
            
            System.out.println("BlocFunctioon : "+doc.functionBlocs.size()+" - used :"+usedBlockFunction.size());
            
            printComments();
            printDefines();
            printHeader();
            printVariables();            
            //printActionThreads();
            printStepList();
            printTrabsitionList();
            printInitialSituation();      
            printSetInputs();
            printSetOutputs(); 
            printGetAnalogInputs(); 
            printInitializeDigitals();
            printTestConditions();            
            printActionsCode();
            printFooter();        
            sourceCodeStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GCDocument.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void generate(String destinationPath) {
        this.destination = destinationPath;
        generate();
    }
    
    private void printComments() {
        sourceCodeStream.println("/*");
        sourceCodeStream.println(" * Generated file");
        Date d = new Date();
        Calendar.getInstance();
        String date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        sourceCodeStream.println(" * Created on " + date);
        sourceCodeStream.println(" */");
    }

    private void printHeader() throws FileNotFoundException {
        Scanner headerSc = new Scanner(new File("../arduino/c_template/header.cc"));
        while (headerSc.hasNextLine()) {
            sourceCodeStream.println(headerSc.nextLine());
        }
    }

    private void printFooter() throws FileNotFoundException {
        Scanner footerSc = new Scanner(new File("../arduino/c_template/footer.cc"));
        while (footerSc.hasNextLine()) {
            sourceCodeStream.println(footerSc.nextLine());
        }
    }

    private void printVariables() {
        sourceCodeStream.println("//VARIABLES");
        Iterator<StandardVariable> it = doc.stdVariables.iterator();
        while (it.hasNext()) {
            StandardVariable next = it.next();
            sourceCodeStream.print(next.getType().toString().toLowerCase()+" "+next.getName());   
            if(!String.valueOf(next.getValue()).isEmpty())
                sourceCodeStream.print(" = "+next.getValue());
            sourceCodeStream.println(";");
        }
        sourceCodeStream.println("//END VARIABLES");
    }

    private void printTestConditions() {
        sourceCodeStream.println("//TRANSITIONS CONDITIONS");
        sourceCodeStream.println("bool testCondition(int trabsitionID){");
        sourceCodeStream.println("\tswitch(trabsitionID){");
                
        for (int i = 0; i < doc.transitions.size(); i++) {
            GCTransition trans = (GCTransition)doc.transitions.get(i);
            sourceCodeStream.println("\t\tcase "+i+":");
            sourceCodeStream.print("\t\t\treturn (");
            Variable var = trans.conditionVariable;
            if(var == null){
                sourceCodeStream.print(trans.conditionValue);
            }else if(var instanceof StandardVariable){
                if(((StandardVariable) var).getAddresse() == null){
                    sourceCodeStream.print("((bool) "+var.getName()+") == "+trans.conditionValue);
                } else { // A revoir
                    sourceCodeStream.print("((bool) "+var.getName()+") == "+trans.conditionValue);
                }
            } else if(var instanceof TransitionSession){
                sourceCodeStream.print(var.getValue());
            }
            sourceCodeStream.println(");");
            sourceCodeStream.println("\t\t\tbreak;");
        }
        sourceCodeStream.println("\t\tdefault: return false;");
        sourceCodeStream.println("\t}\n}");     
        sourceCodeStream.println("//END TRANSITIONS CONDITIONS");
    }

    private void printActionsCode() {
        sourceCodeStream.println("//ACTIONS CODES");
        sourceCodeStream.println("void executeBlock(int actionID){");
        sourceCodeStream.println("\tswitch(actionID){");
        
        for (int i = 0; i < usedBlockFunction.size(); i++) {
            sourceCodeStream.println("\t\tcase "+i+":");
            Variable var = usedBlockFunction.get(i);
            sourceCodeStream.println(var.getValue());            
            sourceCodeStream.println("\t\t\tbreak;");
        }        
        sourceCodeStream.println("\t}\n}");
        sourceCodeStream.println("//END ACTIONS CODES");
    }

    private void printStepList() {
        sourceCodeStream.println("//STEPS LIST");
        sourceCodeStream.println("void defineSteps(){");
        sourceCodeStream.println("\tStep* s;");
        sourceCodeStream.println("\tActionThread* th;");
        for (int i = 0; i < doc.steps.size(); i++) {
            GCStep step = doc.steps.get(i);
            sourceCodeStream.println(
                    String.format("\ts = new Step(%d, %d, \"%s\");", i, step.actionsList.size(), step.getName()));
            for (int j = 0; j < step.actionsList.size(); j++) {
                Action action = step.actionsList.get(j);
                Variable var = action.getVariable();
                if(var instanceof StandardVariable){
                    sourceCodeStream.println(String
                        .format("\ts->actions[%d] = new Action(&%s, %s, %d);", j, var.getName(), action.getQualifier(), action.getTime()));
                } else if(var instanceof FunctionBlock){
                    //Action::Action(Thread* th, short q, long t){
                    int varId = usedBlockFunction.indexOf(var);
                    sourceCodeStream.println(String.format("\tth = new ActionThread(%d);", varId));
                    sourceCodeStream.println("\tactionsController.add(th);");
                    sourceCodeStream.println(String
                        .format("\ts->actions[%d] = new Action(th, %s, %d);", j, action.getQualifier(), action.getTime()));
                }                
            }            
            sourceCodeStream.println(String.format("\tstepList[%d] = s;", i));       
        }
        sourceCodeStream.println("}");
        sourceCodeStream.println("//END STEPS LIST");
    }

    private void printTrabsitionList() {
        sourceCodeStream.println("//TRANSITIONS LIST");
        sourceCodeStream.println("void defineTransitions(){");
        sourceCodeStream.println("\tTransition* t;");
        for (int i = 0; i < doc.transitions.size(); i++) {
            GCTransition trans = (GCTransition)doc.transitions.get(i);
            sourceCodeStream.println(String.format("\tt = new Transition(%d, %d, %d);", i, trans.precedingSteps.size(), trans.succeedingSteps.size()));
            for (int j = 0; j < trans.precedingSteps.size(); j++) {
                GrafcetObject pStep = trans.precedingSteps.get(j);
                if(doc.steps.indexOf(pStep) > -1){ //On écarte les actions contenu dans les macros
                    sourceCodeStream.println(String.format("\tt->preceedingSteps[%d] = %d;", j, doc.steps.indexOf(pStep)));
                }                
            }
            for (int j = 0; j < trans.succeedingSteps.size(); j++) {
                GrafcetObject sStep = trans.succeedingSteps.get(j);
                if(doc.steps.indexOf(sStep) > -1){ //On écarte les actions contenu dans les macros
                    sourceCodeStream.println(String.format("\tt->succeedingSteps[%d] = %d;", j, doc.steps.indexOf(sStep)));
                }
            }            
            sourceCodeStream.println(String.format("\ttransitionList[%d] = t;", i));            
        }
        sourceCodeStream.println("}");
        sourceCodeStream.println("//END TRANSITIONS LIST");        
    }

    private void printInitialSituation() {
        boolean hasInitial = false;
        sourceCodeStream.println("//INITIALS STEPS");
        sourceCodeStream.println("void initialSitutation(){");
        for (int i = 0; i < doc.steps.size(); i++) {
            GCStep step = doc.steps.get(i);
            if(step instanceof GCStepInitial){
                sourceCodeStream.println("\tstepList["+i+"]->activate();");
                hasInitial = true;
            }
        }        
        sourceCodeStream.println("}");
        sourceCodeStream.println("//END INITIALS STEPS");
        if(!hasInitial){
            throw new IllegalStateException("There is no initial step");
        }       
    }

    private void printInitializeDigitals() {
        sourceCodeStream.println("//INITIALIZE DIGITALS");
        sourceCodeStream.println("void initializeDigitals(){");
        for (int i = 0; i < doc.stdVariables.size(); i++) {
            ArduinoIO dig = doc.stdVariables.get(i).getAddresse();
            if(dig != null){                
                String t;
                if(dig.getType() == ArduinoIO.DIGITAL_IN){
                    sourceCodeStream.println(String.format("\tpinMode(%d,  INPUT); //%s", dig.getPin(), doc.stdVariables.get(i)));
                    sourceCodeStream.println(String.format("\tPCintPort::attachInterrupt(%d, &setInputs, CHANGE);", dig.getPin()));
                    //résistance de pull up?
                    sourceCodeStream.println(String.format("\tdigitalWrite(%d, HIGH);", dig.getPin()));
                }
                else if(dig.getType() == ArduinoIO.DIGITAL_OUT){
                    sourceCodeStream.println(String.format("\tpinMode(%d, OUTPUT); //%s", dig.getPin(), doc.stdVariables.get(i)));
                }               
            }
        }        
        sourceCodeStream.println("}");
        sourceCodeStream.println("//END INITIALIZE DIGITALS");
    }

    private void printDefines() {
        //sourceCodeStream.println("#include <cstdlib>");
        //sourceCodeStream.println("#include <iostream>");
        
        String consignes = "/*\n" +
                "  1. Download each External Library using the link provide.\n" +
                "  2. Unzip them, you must modify the Folder name to \"ArduinoThread\" (Remove the '-master') for ArduinoThread Library\n" +
                "  3. Paste the Unzip folders on your Library folder (On your `Libraries` folder inside Sketchbooks or Arduino software).\n" +
                "  4. Restart Arduino IDE and enjoy (*_*).\n" +
                "*/\n";
        
        sourceCodeStream.println("#include <TimerOne.h>          // http://code.google.com/p/arduino-timerone/");
        //sourceCodeStream.println("#define PINMODE"); 
        sourceCodeStream.println("#include <PinChangeInt.h>      // http://code.google.com/p/arduino-pinchangeint/");   
        sourceCodeStream.println("#include <Thread.h>            // https://github.com/ivanseidel/ArduinoThread/archive/master.zip");   
        sourceCodeStream.println("#include <ThreadController.h>  // https://github.com/ivanseidel/ArduinoThread/archive/master.zip"); 
        sourceCodeStream.println(consignes);
        sourceCodeStream.println("#include <Arduino.h>");
        sourceCodeStream.println();
        sourceCodeStream.println("#define ACTIVED 0");
        sourceCodeStream.println("#define DEACTIVED 1");
        sourceCodeStream.println("#define N_STEPS " + doc.steps.size());
        sourceCodeStream.println("#define N_TRANS " + doc.transitions.size());
        sourceCodeStream.println("#define N_THREADS " + usedBlockFunction.size());
        
        int am = 0;
        for (GCStep step : doc.steps) {
            if(step.actionsList.size() > am)
                am = step.actionsList.size();
        }
        sourceCodeStream.println("#define ACTION_MAX " + am);
        sourceCodeStream.println();
        int i = 0;
        for (Action.Qualifier q : Action.Qualifier.values()) {            
            sourceCodeStream.println("#define " + q + " " + i++);
        }        
        sourceCodeStream.println();
    }    

    private void printSetInputs() {
        sourceCodeStream.println("//SET INPUTS AFTER PIN CHANGE INTERRUPT");
        sourceCodeStream.println("void setInputs(){");
        sourceCodeStream.println("//Un swtch à la longue");
        for (int i = 0; i < doc.stdVariables.size(); i++) {
            ArduinoIO dig = doc.stdVariables.get(i).getAddresse();
            if(dig != null){                
                String t;
                if(dig.getType() == ArduinoIO.DIGITAL_IN){
                    sourceCodeStream.println(String.format("\t%s = (bool)digitalRead(%d);", doc.stdVariables.get(i), dig.getPin()));
                }
            }
        }        
        sourceCodeStream.println("}");
        sourceCodeStream.println("//END SET INPUTS");
    }

    private void printSetOutputs() {
        sourceCodeStream.println("//SET OUTPUTS AFTER TIMER INTERRUPT");
        sourceCodeStream.println("void setOutputs(){");
        for (int i = 0; i < doc.stdVariables.size(); i++) {
            ArduinoIO dig = doc.stdVariables.get(i).getAddresse();
            if(dig != null){                
                String t;
                if(dig.getType() == ArduinoIO.ANALOG_OUT || dig.getType() == ArduinoIO.DIGITAL_OUT){
                    sourceCodeStream.println(String.format("\tdigitalWrite(%d, (int)%s);", dig.getPin(), doc.stdVariables.get(i)));
                }
            }
        }        
        sourceCodeStream.println("}");
        sourceCodeStream.println("//END SET OUTPUTS");
    }

    private void printGetAnalogInputs() {
        sourceCodeStream.println("//READ ANALOG INPUTS AFTER TIMER INTERRUPT");
        sourceCodeStream.println("void getAnalogs(){");
        sourceCodeStream.println(String.format("\tint analogVal;"));
        sourceCodeStream.println("\t//map() convertit la valeur lu en une valeur entre 0 et 5000 mV");
        for (int i = 0; i < doc.stdVariables.size(); i++) {
            ArduinoIO dig = doc.stdVariables.get(i).getAddresse();
            if(dig != null){                
                String t;
                if(dig.getType() == ArduinoIO.ANALOG_IN){
                    sourceCodeStream.println(String.format("\tanalogVal = analogRead(%d);", dig.getPin()));
                    sourceCodeStream.println(String.format("\t%s = map(analogVal, 0, 1023, 0, 5000);", doc.stdVariables.get(i)));
                }
            }
        }        
        sourceCodeStream.println("}");
        sourceCodeStream.println("//END SET INPUTS");
    }

    private void printActionThreads() {
        //defineActionThreads();
        sourceCodeStream.println("//ACTION THREADS");
        sourceCodeStream.println("void defineActionThreads(){");    
        sourceCodeStream.println("\tActionThread* th;");
        for (int i = 0; i < usedBlockFunction.size(); i++) {
            FunctionBlock var = usedBlockFunction.get(i);
            sourceCodeStream.println(String.format("\tth = new ActionThread(%d);", i));
            sourceCodeStream.println(String.format("\tthreadList[%d] = s;", i));      
        }        
        sourceCodeStream.println("\t}\n}");
        sourceCodeStream.println("//END ACTIONS CODES");
    }
    
    public static void updateMakefileVariables() {
        PrintStream sourceStream = null;
        try {
            sourceStream = new PrintStream("../arduino/variables.mk");
            sourceStream.println("# Malefile variables");
            String date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
            sourceStream.println("# Created on " + date);
            sourceStream.println("");            
            sourceStream.println("COM_PORT = /dev/" + Editor.myBoard.getPortCOM());
            sourceStream.println("ARDUINO_VERSION = 105");
            sourceStream.println("ARDUINO_CPU = " + Editor.myBoard.getCpu());
            sourceStream.println("ARDUINO_F_CPU = " + Editor.myBoard.getF_cpu());
            sourceStream.println("ARDUINO_PROTOCOL = " + Editor.myBoard.getProtocol());
            sourceStream.println("ARDUINO_SPEED = " + Editor.myBoard.getSpeed());
            sourceStream.println("");
            //sourceStream.println("ARDUINO_BASE_DIR = /usr/share/arduino/hardware/arduino");
            //sourceStream.println("AVR_BIN_DIR = /usr/share/arduino/hardware/tools/avr/bin");
            //sourceStream.println("AVR_DUDE = /usr/share/arduino/hardware/tools/avrdude -C/usr/share/arduino/hardware/tools/avrdude.conf");
            //sourceStream.println("SOURCES_DIR = compile_dir");
            //sourceStream.println("SOURCE_FILE = source.cpp");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CGeneretor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sourceStream.close();
        }
    }
}
