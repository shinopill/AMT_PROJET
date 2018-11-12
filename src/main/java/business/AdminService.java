package business;

import javax.ejb.Stateless;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJB;
import model.User;

/**
 *
 * @author Antoine
 */
@Stateless
public class AdminService implements AdminServiceLocal{
    
    @EJB
    private UserDAO userDAO;

    @Override
    public boolean resetUserPassword(User user) {
        
        try {
            User userToReset = userDAO.find(user.getEmail());
            if (userToReset == null) {
                // L'user don't find
                return false;
            } else {
                // Generate a new password
                
                // Assign it to the user and to the database
                
                // send it
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean suspendAccount(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> getDeveloppersDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

    

    
}
