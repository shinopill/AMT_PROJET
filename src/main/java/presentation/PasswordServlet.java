package presentation;

import dao.UserDAOLocal;
import model.User;
import org.openqa.selenium.remote.http.HttpResponse;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/changePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

            if(user != null && user.getPassword().equals(oldPass) && newPass.equals(newPassConf)) {
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
                    resp.sendRedirect("profil");
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
