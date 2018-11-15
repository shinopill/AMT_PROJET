package presentation;

import buisness.Email;
import dao.UserDAOLocal;
import model.Application;
import model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminServlet extends javax.servlet.http.HttpServlet {

    @EJB
    UserDAOLocal userDao;
    @EJB
    Email email;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Show the list of the users and allow the admin to reset an user password or change the account active status
        ArrayList<User> usersArray = null;
        HttpSession session = request.getSession(false);
        int page = (int) session.getAttribute("pageUser");

        // Check if clicked on previous/next
        if (request.getParameter("do") != null) {
            if (request.getParameter("do").equals("next")) {
                session.setAttribute("pageUser", page + 1);
            } else if (request.getParameter("do").equals("previous")) {
                session.setAttribute("pageUser", page - 1);
            }
        }

        // Check if clicked on enable/disable; change the user's active/inactive status if it's the case
        if (request.getParameter("disable") != null) {
            String name = request.getParameter("disable");
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

        // Check if clicked on reset Password; If it's the case, send a mail to the user with his new password
        if (request.getParameter("reset") != null) {
            String name = request.getParameter("reset");
            int t = UUID.randomUUID().hashCode();
            try {
                email.sendEmail(name, "Gamification engine password reset", "Your password on " +
                        "the gamification engine app has been reset.\nYour new password is " + t +
                        ". Please connect to the app to change it.\nBest regards,\nThe Admin");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                userDao.updatePassword(name, String.valueOf(t));
                userDao.setRested(name, 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            usersArray = userDao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Deal with the pagination
        int nbAppShowed = (int) session.getAttribute("pageUser") * Application.ELEMENT_BY_PAGE;
        int nbAppToShow = usersArray.size() - nbAppShowed;
        int nbElementToShow = nbAppToShow > User.ELEMENT_BY_PAGE ? User.ELEMENT_BY_PAGE : nbAppToShow;
        List<User> listApp = usersArray.subList(nbAppShowed, nbAppShowed + nbElementToShow);

        session.setAttribute("userToSee", usersArray.size() - nbAppShowed - nbElementToShow);
        request.setAttribute("usersArray", listApp);
        request.getRequestDispatcher("/WEB-INF/pages/filtered/admin.jsp").forward(request, response);
    }
}
