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


   private UserDAO userDAO;


   public boolean isUserValid(String userName) {

      return false;
   }

   public boolean isCredentialsValid(String userName, String password) {
      //TODO
      return false;
   }
}
