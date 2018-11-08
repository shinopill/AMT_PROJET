package dao;

import javax.ejb.Local;
import model.User;

import java.util.ArrayList;

@Local
public interface UserDAOLocal {
   
   public void createUser(User user);
   public int setActive(int a );
   public int updateUser(String colonne);
   public void deleteUser(User user);
   public User find(String userMail);
   public int getAdmin(String username);
   public int getActive(String username);
   public ArrayList<User> getAllUsers();

}
