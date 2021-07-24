/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafchart.sfc.variables;

import java.util.Vector;
import org.w3c.dom.Element;

/**
 *
 * @author dimon
 */
public class FunctionBlock extends Variable {

    private final static String[] TABLE_COLUMNS = new String[]{"Name", "Language", "Content", "Comment"};
    
    protected Language language;

    public FunctionBlock(String name, String content, Language language, String comment) {
        super(name, content, comment);
        this.language = language;
    }

    public FunctionBlock() {
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    
    public static String[] getTableColumns() {
        return TABLE_COLUMNS;
    }

    @Override
    public String[] toTableRow() {
        String[] row = new String[TABLE_COLUMNS.length];
        row[0] = this.name;
        row[1] = this.language.toString();
        row[2] = (String) this.value;
        row[3] = this.comment;
        return row;
    }

    public Element storeXML(Element stdElement, Vector paramVector1, Vector paramVector2) {
        paramVector1.add(this.id);
        paramVector2.add(this);
        stdElement.setAttribute("id", this.id);
        stdElement.setAttribute("name", getName());
        stdElement.setAttribute("language", getLanguage().toString());
        stdElement.setAttribute("value", (String) this.value);
        stdElement.setAttribute("comment", this.comment);        
        return stdElement;
    }
    
    public static FunctionBlock loadXML(Element varElement, Vector paramVector1, Vector paramVector2) {  
        Language l = null;
        String qStr = varElement.getAttribute("language");
        for (Language value : Language.values()) {
            if(qStr.equals(value.toString())){
                l = value;
                break;
            }
        }
        FunctionBlock var = new FunctionBlock(
                varElement.getAttribute("name"),
                varElement.getAttribute("value"),
                l,
                varElement.getAttribute("comment"));        
        var.id = varElement.getAttribute("id");
        
        paramVector1.add(var.id);
        paramVector2.add(var);
        return var;
    }
    
    public enum Language {
        C, ST, IL
    }
}
