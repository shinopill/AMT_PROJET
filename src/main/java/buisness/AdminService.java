package buisness;

import dao.UserDAO;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import model.User;


public class AdminService implements AdminServiceLocal {


    private UserDAO userDAO;

    @Override
    public boolean resetUserPassword(User user) {

        try {
            User userToReset = userDAO.find(user.getEmail());
            if (userToReset == null) {
                return false;
            } else {
                
                // Create a new password
                String passwd = getRandomString();
                
                // Assign it to the user and to the database
                userDAO.updateUser(userToReset.getEmail(),"passwd", passwd);
                // send it by mail
                // TO DO
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

    private String getRandomString() {

        // we generate an 8 length string
        byte[] array = new byte[8]; 
        new Random().nextBytes(array);
        
        return new String(array, Charset.forName("UTF-8"));
    }

}
