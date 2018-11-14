package presentation;

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

public class PasswordServlet extends javax.servlet.http.HttpServlet {
    @EJB
    UserDAOLocal userDao;
    @EJB
    ApplicationDAOLocal appDao;
    @Override


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/changePassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IN doPOST password");

        String oldPass = req.getParameter("oldPass");
        String newPass = req.getParameter("newPass");
        String newPassConf = req.getParameter("newPassConf");


        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            User user = null;
            try {
                 user = userDao.find((String) session.getAttribute("email"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(user.getPassword());
            System.out.println(newPass + "    " + oldPass + "    " + newPassConf);
            if(user != null && user.getPassword().equals(oldPass) && newPass.equals(newPassConf)) {
                System.out.println(newPass);
                int ok = 0;
                try {
                    ok = userDao.updatePassword((String) session.getAttribute("email"), newPass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (ok == 1) {
                    try {
                        userDao.setRested(user.getEmail(),0);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    req.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(req, resp);
                } else {
                    req.setAttribute("erreur", "Problem while updating the password please try again later");
                    req.getRequestDispatcher("/WEB-INF/pages/changePassword.jsp").forward(req, resp);
                }
            }else{
                req.setAttribute("erreur", "The passwords don't match");
                req.getRequestDispatcher("/WEB-INF/pages/changePassword.jsp").forward(req, resp);
            }
        }else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

}
