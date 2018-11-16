package buisness;


import java.sql.SQLException;
import java.util.UUID;
import dao.UserDAOLocal;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

@Stateless
public class AdminService extends RuntimeException implements AdminServiceLocal   {

    @EJB
    Email email;

    @EJB
    UserDAOLocal userDao;

    private UserTransaction user;
    @Override
    public void resetPassword(String name){
        int t = UUID.randomUUID().hashCode();

        try {
            userDao.updatePassword(name, String.valueOf(t));
            userDao.setRested(name, 1);
        } catch (SQLException e) {
            e.printStackTrace();

        }

        try {
            email.sendEmail(name, "Gamification engine password reset", "Your password on " +
                    "the gamification engine app has been reset.\nYour new password is " + t +
                    ". Please connect to the app to change it.\nBest regards,\nThe Admin");
        } catch (MessagingException e) {

        }
    }

    @Override
    public void changeActive(String name){
        int n = 0;
        try {
            n = userDao.getActive(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            userDao.setActive(name, n == 1 ? 0 : 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
