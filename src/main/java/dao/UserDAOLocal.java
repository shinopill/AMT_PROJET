package dao;

import javax.ejb.Local;
import model.User;

@Local
public interface UserDAOLocal {
   
   public void createUser();
   public void updateUser(String colonne);
   public void deleteUser(User user);

}
