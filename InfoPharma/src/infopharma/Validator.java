/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma;

import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Abdullah
 */
public class Validator {

    public static boolean isFilledIn(Object[] fields) {
        for(Object field : fields) {
            if(field == null || field.equals("")) {
                return false;
            }
        }
        return true;
    }
    
    public static void validateTuple(Treeple[] tuples) throws Exception {
        for(Treeple treeple : tuples) {
            Object field = treeple.field;
            if(treeple.size != null) { 
                int size = Integer.parseInt(treeple.size.toString());
                if(size != 0) {
                    if(field == null || field.toString().length() != size) {
                        String title = treeple.title.toString();
                        throw new Exception(title + " must be " + size + " characters long.");
                    }
                } 
            } else if(field == null || field.equals("")) {
                throw new Exception("Please fill in all the details.");
            }
        }
    }
}
