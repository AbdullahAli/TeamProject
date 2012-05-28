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
    
    private String role;

    public UserAccount(String role) {
        this.role = role;
    }
    
    public String getRole(){
        return role;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
}
