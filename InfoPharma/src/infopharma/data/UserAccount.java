/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

/**
 *
 * @author Abdullah
 */
public class UserAccount {
    
    private static String role;

    public UserAccount(String role) 
    {
        this.role = role;
    }

    //DONT REMOVE!
    public UserAccount() 
    {
    }
    
    //DONT REMOVE!
    public UserAccount(String role, String username) 
    {
        this.role = role;
    }
    
    public static String getRole()
    {
        return role;
    }
    
    public static void setRole(String role)
    {
        UserAccount.role = role;
    }
    
}
