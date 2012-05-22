/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package infopharma.data;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Abdullah
 */
public class MiscDBAccess extends DBAccess{
        public UserAccount login(String username, String password)
        {
            UserAccount userAccount = null;
            Connection con = null;
            Statement stat = null;
            ResultSet rs = null;
            String sql = 
                "SELECT * FROM LoginDetails " +
                "INNER JOIN UserRoles " +
                "ON LoginDetails.roleID = UserRoles.roleID " +
                "WHERE LoginDetails.username='" + username + "'" +
                "AND LoginDetails.password='" + password + "'";
            try
            {
                con = makeConnection();
                stat = (Statement) con.createStatement();
                rs = stat.executeQuery(sql);
                if(rs.next())
                {
                    String usr = rs.getString("username");
                    String pwd = rs.getString("password");
                    String role = rs.getString("roleType");
                    userAccount = new UserAccount(role);
                    System.out.println("logged in as: " + role);
                    return userAccount;
                }
                else
                {
                    System.out.println("cant log in");
                }
            }
            catch(SQLException ex) 
            {
                System.out.println(ex.getStackTrace());
            }
            return null;
	}
}
