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
    private static String username;

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
        this.username = username;
    }
    
    public static String getRole()
    {
        return role;
    }
    
    public static void setRole(String role)
    {
        UserAccount.role = role;
    }
    
    public static String getUsername()
    {
        return username;
    }
    
    public static void setUsername(String username)
    {
        UserAccount.username = username;
    }
    
}
