/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafchart.sfc.actions;

import grafchart.sfc.Utils;
import grafchart.sfc.variables.Variable;
import java.util.Locale;
import java.util.UUID;
import java.util.Vector;
import org.w3c.dom.Element;

/**
 *
 * @author dimon
 */
public class Action implements Cloneable {
    private String id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
    private Qualifier qualifier;
    private long time = 0;
    private Variable variable;
    public boolean isModify = false;

    public Action(Qualifier qualifier, long time, Variable variable) {
        this.qualifier = qualifier;
        this.time = time;
        this.variable = variable;
    }
    
    public Action(Qualifier qualifier, Variable variable) {
        this(qualifier, 0, variable);
    }

    public Action() {}

    public Qualifier getQualifier() {
        return qualifier;
    }

    public void setQualifier(Qualifier qualifier) {
        this.qualifier = qualifier;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
        Qualifier[] a = Qualifier.values();
    }    

    @Override
    public String toString() {
        if(qualifier.hasTime()){
        return String.format("%-2s %-4s %s;", qualifier, time, variable);
        } else {
            return String.format("%-2s %s;", qualifier, variable);
        }
    }   

    public Element storeXML(Element stdElement, Vector paramVector1, Vector paramVector2) {               
        int i = paramVector2.indexOf(this.getVariable());
        if ((i >= 0) && (i < paramVector1.size())) {
            String varId = (String) paramVector1.get(i);
            if (varId != null) {
                stdElement.setAttribute("variable", varId);
            }
        } else {
            System.out.println("variable non retouvé Action");
            //Variable non définit referencé l'action n'est pas sauvegardé
            return null;
        }
        /* Non utilisé
        paramVector1.add(this.id);
        paramVector2.add(this);
        stdElement.setAttribute("id", this.id);
        */                
        stdElement.setAttribute("qualifier", getQualifier().toString());
        stdElement.setAttribute("time", String.valueOf(getTime()));      
        return stdElement;
    }
    
    public static Action loadXML(Element varElement, Vector paramVector1, Vector paramVector2) {
        Qualifier q = null;
        String qStr = varElement.getAttribute("qualifier");
        for (Qualifier value : Qualifier.values()) {
            if(qStr.equals(value.toString())){
                q = value;
                break;
            }
        }
        
        long time = Utils.parseLong(varElement.getAttribute("time"));
        
        String varId = varElement.getAttribute("variable");
        int k = paramVector1.indexOf(varId);
        Variable var = null;
        if ((k >= 0) && (k < paramVector2.size())) {
            var = (Variable) paramVector2.get(k);
        }
        Action action = new Action(q, time, var);
        /* Non utilisé
        action.id = varElement.getAttribute("id");
        paramVector1.add(action.id);
        paramVector2.add(action);
        */        
        return action;
    }
        
    public enum Qualifier {
        N(false), S(false), R(false), D(true), L(true), DS(true);
        
        private final boolean hasTime;
        private Qualifier(boolean hasTime) {
            this.hasTime = hasTime;
        }
        
        public boolean hasTime(){
            return this.hasTime;
        }
        
        public static String[] getValues(){
            Qualifier[] qs = values();
            int l = qs.length;
            String[] str = new String[l];            
            for (int i = 0; i < l; i++) {
                str[i] = qs[i].toString();
            }
            return str;           
        }
    }

    @Override
    public Action clone() throws CloneNotSupportedException {
        Action a = (Action) super.clone(); 
        a.id = UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
        return a;
    }
    
    
}
