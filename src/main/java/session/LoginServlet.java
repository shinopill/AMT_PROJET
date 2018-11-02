package session;

import dao.UserDAO;
import dao.UserDAOLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends javax.servlet.http.HttpServlet {

    @EJB
    UserDAOLocal dao;


    // Source for session management; https://medium.com/@kasunpdh/session-management-in-java-using-servlet-filters-and-cookies-7c536b40448f
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("In login.Post");
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");

        if(email != null && password != null){
            //TODO: verif avec la db
            if(email.equals(dao.getUser()) && password.equals("123")){
                System.out.println("login correct");
                // Get the old session and invalidate it
                HttpSession oldSession = req.getSession(false);

                if(oldSession!= null){
                    oldSession.invalidate();
                }

                // Generate a new session
                HttpSession newSession = req.getSession(true);

                Cookie cookie = new Cookie("email", email);
                System.out.println(cookie.getValue());
                resp.addCookie(cookie);

                //TODO: admin vs user
                req.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(req, resp);
            }else {
                System.out.println("login faux");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }
    }
}
