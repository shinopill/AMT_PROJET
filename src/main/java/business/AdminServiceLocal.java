package business;

import java.util.ArrayList;
import model.User;

public interface AdminServiceLocal {
    
    boolean resetUserPassword(User user);
    boolean suspendAccount(User user);
    ArrayList<User> getDeveloppersDetails();
}
