package presentation;

import dao.UserDAO;
import dao.UserDAOLocal;
import model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminServlet extends javax.servlet.http.HttpServlet {

    @EJB
    UserDAOLocal userDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> usersArray = null;
        try {
            usersArray = userDao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("usersArray",usersArray);
        // System.out.print(userDao.find("Someone@mail.com"));
        request.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
