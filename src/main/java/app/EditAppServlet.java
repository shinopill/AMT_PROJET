package app;

import dao.ApplicationDAOLocal;
import model.Application;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class EditAppServlet extends javax.servlet.http.HttpServlet {

    @EJB
    ApplicationDAOLocal applicationDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("name") != null) {
            HttpSession session = request.getSession(false);
            String name = request.getParameter("name");
            Application app = null;
            try {
                app = applicationDAO.find((String) session.getAttribute("email"), name);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String description = app.getDescription();

            request.setAttribute("description", description);
            request.setAttribute("name", name);
            System.out.println("Description = " + description);

            request.getRequestDispatcher("/WEB-INF/pages/editApp.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);

        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("oldname") != null) {
            HttpSession session = req.getSession(false);
            String email = (String) session.getAttribute("email");
            String newName = req.getParameter("appName");
            String description = req.getParameter("description");
            try {
                applicationDAO.updateName(email, req.getParameter("oldname"), newName);
                applicationDAO.updateDesciption(email, newName, description);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(req, resp);
    }
}
