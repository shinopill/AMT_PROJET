package session;

import dao.ApplicationDAOLocal;
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

public class LoginServlet extends javax.servlet.http.HttpServlet {

    @EJB
    UserDAOLocal userDao;
    @EJB
    ApplicationDAOLocal appDao;

    protected void doGET(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    // Source for session management; https://medium.com/@kasunpdh/session-management-in-java-using-servlet-filters-and-cookies-7c536b40448f
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");
        String message = "";

        if (email != null && password != null) {
            try {
                User userToTest = userDao.find(email);

                if (userToTest != null && email.equals(userToTest.getEmail()) && password.equals(userToTest.getPassword())) {

                    // Generate a new session
                    HttpSession newSession = req.getSession();
                    newSession.setAttribute("email", email);

                    int isAdmin = userToTest.getIsAdmin();
                    int isDisabled = userToTest.getIsDisabled();
                    int isBeingReseted = userToTest.getIsBeingReseted();
                    newSession.setAttribute("admin", isAdmin);
                    newSession.setAttribute("isDisabled", isDisabled);
                    newSession.setAttribute("isBeingReseted",isBeingReseted);
                    newSession.setAttribute("pageUser",0);
                    newSession.setAttribute("pageApp",0);

                    if (isAdmin == 1) {
                        int size = userDao.getSize();
                        int nbElementToShow = size > User.ELEMENT_BY_PAGE ? User.ELEMENT_BY_PAGE : size;
                        List<User> listApp = userDao.getApplicationPages(0,nbElementToShow);
                        req.setAttribute("usersArray", listApp);
                        req.setAttribute("admin", isAdmin);
                        newSession.setAttribute("userToSee",size - nbElementToShow);
                        req.getRequestDispatcher("/WEB-INF/pages/filtered/admin.jsp").forward(req, resp);
                    } else if (isDisabled == 1) {
                        message = "Your account has been disabled";
                        redirectToIndex(req, resp, message);
                    } else if(isBeingReseted == 1){
                        message = "Please change your password";
                        req.setAttribute("reseted",message);
                        req.getRequestDispatcher("/WEB-INF/pages/filtered/changePassword.jsp").forward(req, resp);
                    }else{
                        req.setAttribute("admin", isAdmin);
                        int size = appDao.getSize(userToTest.getEmail());
                        int nbElementToShow = size > Application.ELEMENT_BY_PAGE ? Application.ELEMENT_BY_PAGE : size;
                        ArrayList<Application> list = appDao.getApplicationPages(userToTest.getEmail(),0,nbElementToShow);
                        req.setAttribute("applist", list);
                        newSession.setAttribute("appToSee",size - nbElementToShow);
                        req.getRequestDispatcher("/WEB-INF/pages/filtered/view.jsp").forward(req, resp);
                    }
                } else {
                    message = "Wrong credentials";
                    redirectToIndex(req, resp, message);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void redirectToIndex(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("erreur", message);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
