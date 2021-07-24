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
public class TransitionSession extends FunctionBlock {

    private final static String[] TABLE_COLUMNS = new String[]{"Name", "Language", "Condition", "Comment"};
    
    public TransitionSession(String name, String content, Language language, String comment) {
        super(name, content, language, comment);
    }

    public TransitionSession() {}
    
    public static String[] getTableColumns() {
        return TABLE_COLUMNS;
    }
    
    public static TransitionSession loadXML(Element varElement, Vector paramVector1, Vector paramVector2) {  
        Language l = null;
        String qStr = varElement.getAttribute("language");
        for (Language value : Language.values()) {
            if(qStr.equals(value.toString())){
                l = value;
                break;
            }
        }
        TransitionSession var = new TransitionSession(
                varElement.getAttribute("name"),
                varElement.getAttribute("value"),
                l,
                varElement.getAttribute("comment"));        
        var.id = varElement.getAttribute("id");
        
        paramVector1.add(var.id);
        paramVector2.add(var);
        return var;
    }
}
