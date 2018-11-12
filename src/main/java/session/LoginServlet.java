package session;

import buisness.Email;
import dao.ApplicationDAOLocal;
import dao.UserDAO;
import dao.UserDAOLocal;
import model.Application;
import model.User;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    /* TODO mettre l'email chez tout le monde
    @EJB
    Email e;
    */
    @EJB
    UserDAOLocal userDao;
    @EJB
    ApplicationDAOLocal appDao;
    protected void doGET(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    // Source for session management; https://medium.com/@kasunpdh/session-management-in-java-using-servlet-filters-and-cookies-7c536b40448f
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("In login.Post");
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");
        String message = "";

        if(email != null && password != null){
            //TODO: verif avec la db
            try {
                User userToTest = userDao.find(email);

                if(userToTest != null && email.equals(userToTest.getEmail()) && password.equals(userToTest.getPassword())){

                    // Generate a new session
                    HttpSession newSession = req.getSession();
                    newSession.setAttribute("email", email);

                    int isAdmin = userToTest.getIsAdmin();
                    int isDisabled = userToTest.getIsDisabled();

                    newSession.setAttribute("admin",isAdmin);
                    newSession.setAttribute("isDisabled",isDisabled);
                    System.out.println(userToTest);
                    System.out.println(userDao.getAdmin(userToTest.getEmail()));
                    if(isAdmin == 1){
                        ArrayList<User> usersArray = userDao.getAllUsers();
                        req.setAttribute("usersArray",usersArray);
                        req.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(req, resp);
                    }else if(isDisabled == 1 ){
                        message = "Your account has been disabled";
                        redirectToIndex(req,resp,message);
                    }

                    //e.sendEmail("shinopill@gmail.com","test","test");
                    req.setAttribute("admin",isAdmin);
                    ArrayList<Application> list = appDao.getAllApplications(userToTest.getEmail());
                    req.setAttribute("applist",list);
                    req.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(req, resp);
                }else {
                    message = "Wrong credentials";
                    redirectToIndex(req,resp,message);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void redirectToIndex(HttpServletRequest req, HttpServletResponse resp,String message) throws ServletException, IOException {
        req.setAttribute("erreur",message);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
