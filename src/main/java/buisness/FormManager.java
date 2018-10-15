package buisness;

import javax.ejb.Stateless;
import dao.UserDAO;
import javax.ejb.EJB;

/**
 *
 * @author Antoine
 */
@Stateless
public class FormManager {

   @EJB
   private UserDAO userDAO;
   
   public boolean isUserValid(String userName) {
      // TO DO
      return false;
   }
   
   public boolean isCredentialsValid(String userName, String password) {
      // TO DO 
      return false;
   }
}
