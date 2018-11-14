package app;

import dao.ApplicationDAO;
import dao.UserDAO;
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
    ApplicationDAO applicationDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("name") != null){
            HttpSession session = request.getSession(false);
            String name = request.getParameter("name");
            Application app = null;
            try {
                app = applicationDAO.find((String)session.getAttribute("email"), name);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String description = app.getDescription();

            request.setAttribute("name", name);
            request.setAttribute("description", description);

            request.getRequestDispatcher("/WEB-INF/pages/editApp.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);

        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
