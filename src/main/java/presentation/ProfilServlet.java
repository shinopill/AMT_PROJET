package presentation;

import dao.UserDAOLocal;
import model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ProfilServlet extends javax.servlet.http.HttpServlet {

    @EJB
    UserDAOLocal dao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        int isAdmin = 0;
        try {
            HttpSession session = request.getSession(false);
            user = dao.find((String)session.getAttribute("email"));
            isAdmin = (int)session.getAttribute("admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("user", user);
        request.setAttribute("admin", isAdmin);
        request.getRequestDispatcher("/WEB-INF/pages/filtered/profil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
