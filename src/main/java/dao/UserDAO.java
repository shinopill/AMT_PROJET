package dao;

import javax.ejb.Stateless;
import model.User;

@Stateless
public class UserDAO implements UserDAOLocal {

   @Override
   public void createUser() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void updateUser(String colonne) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void deleteUser(User user) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   
}
