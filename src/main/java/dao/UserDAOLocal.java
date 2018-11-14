package dao;

import javax.ejb.Local;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

@Local
public interface UserDAOLocal {
   
   int createUser(User user) throws SQLException;
   int setActive(String email,int a ) throws SQLException;
   int updateUser(String userMail, String colonne, String value);

   int updatePassword(String userMail, String value) throws SQLException;

   int deleteUser(String email) throws SQLException;
   User find(String userMail) throws SQLException;
   int getAdmin(String username) throws SQLException;
   int getActive(String username) throws SQLException;

    int setRested(String email, int a) throws SQLException;

    ArrayList<User> getAllUsers() throws SQLException;

}
