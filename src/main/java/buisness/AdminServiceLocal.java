package buisness;

import java.util.ArrayList;
import model.User;

public interface AdminServiceLocal {
    
    void resetPassword(String name);

    void changeActive(String name);
}
