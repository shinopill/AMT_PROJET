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

public class ApplicationServlet extends javax.servlet.http.HttpServlet {
    @EJB
    ApplicationDAOLocal appDao;
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String appName = req.getParameter("appName");
        String description = req.getParameter("descripton");

        System.out.println(appName + description);
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            int ok = 0;
            Application app = new Application((String) session.getAttribute("email"), appName, description);
            try {
                ok = appDao.createAppIfNotExist((String)session.getAttribute("email"), app);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (ok == 1) {
                resp.sendRedirect("view");
            } else {
                req.setAttribute("erreur", "Invalid name (to long or already taken)");
                req.getRequestDispatcher("/WEB-INF/pages/filtered/applicationForm.jsp").forward(req, resp);
            }
        }else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/filtered/applicationForm.jsp").forward(req, resp);
    }
}
