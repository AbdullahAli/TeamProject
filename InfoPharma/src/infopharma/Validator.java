/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma;

import java.util.ArrayList;

/**
 *
 * @author Abdullah
 */
public class Validator {
    public static boolean isFilledIn(ArrayList<String> fields)
    {
        for(String field : fields)
        {
            if(field.equals(""))
            {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isFilledIn(String username, String password)
    {
        if(username.equals("") || password.equals(""))
        {
            return false;
        }
        return true;
    }
}
