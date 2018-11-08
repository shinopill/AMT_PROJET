package session;

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
            if(email.equals("toto") && password.equals("123")){


                System.out.println("login correct");
                // Generate a new session
                HttpSession newSession = req.getSession();
                newSession.setAttribute("email", email);
                Cookie cookie = new Cookie("email", email);

                System.out.println(cookie.getValue());
                resp.addCookie(cookie);

                //TODO: admin vs user
                int admin = 0;//dao.getAdmin(email);
                int isActive = 1;// dao.getActive(email);
                newSession.setAttribute("admin",admin);
                newSession.setAttribute("active",isActive);
                if(admin == 1){
                    ArrayList<User> usersArray = userDao.getAllUsers();
                    req.setAttribute("usersArray",usersArray);
                    req.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(req, resp);
                }else if(isActive == 0 ){
                    message = "Your account has been disabled";
                    redirectToIndex(req,resp,message);
                }

                User user =  userDao.find(req.getParameter("email"));
                ArrayList<Application> list = new ArrayList<>();  //appDao.getAllApplications(user);
                list.add(new Application("coucou","c'est cool"));
                req.setAttribute("applist",list);
                req.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(req, resp);
            }else {
                message = "Wrong credentials";
                redirectToIndex(req,resp,message);
            }
        }

    }

    private void redirectToIndex(HttpServletRequest req, HttpServletResponse resp,String message) throws ServletException, IOException {
        req.setAttribute("erreur",message);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
