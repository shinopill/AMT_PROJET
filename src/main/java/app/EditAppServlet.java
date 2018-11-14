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

        // Get the information in the URL to get the app informations and fill the app name and description field
        // If this page is accessed without having an app name in the URL, return to view.jsp
        if (request.getParameter("name") != null) {
            HttpSession session = request.getSession(false);
            String name = request.getParameter("name");
            Application app = null;
            String description = null;
            try {
                app = applicationDAO.find((String) session.getAttribute("email"), name);
                description = app.getDescription();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("description", description);
            request.setAttribute("name", name);

            request.getRequestDispatcher("/WEB-INF/pages/editApp.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);

        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Update the app info in the db to match the new ones if fields are corrects
        // If the name is to long or already taken, get back on the edit page with initial app info
        if (req.getParameter("oldname") != null) {
            HttpSession session = req.getSession(false);
            String email = (String) session.getAttribute("email");
            String newName = req.getParameter("appName");
            String description = req.getParameter("description");
            int ok = 1;
            try {
                ok &= applicationDAO.updateName(email, req.getParameter("oldname"), newName);
                ok &= applicationDAO.updateDesciption(email, newName, description);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Check if on of the updates failed
            if(ok != 1){
                req.setAttribute("erreur", "Invalid name (to long or already taken)");
                req.setAttribute("name", req.getParameter("oldname"));
                req.setAttribute("description", description);
                req.getRequestDispatcher("/WEB-INF/pages/editApp.jsp").forward(req, resp);
            }else{
                req.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(req, resp);
            }
        }
    }
}
