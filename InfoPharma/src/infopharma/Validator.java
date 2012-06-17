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
}
