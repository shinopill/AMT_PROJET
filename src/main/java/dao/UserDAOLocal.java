package dao;

import javax.ejb.Local;
import model.User;

@Local
public interface UserDAOLocal {
   
   public int createUser(User user);
   public int setActive(int a );
   public int updateUser(String colonne);
   public int deleteUser(User user);
   public String getUser();
   public int getAdmin(String username);
   public int getActive(String username);

}
