package presentation;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AdminServlet extends javax.servlet.http.HttpServlet {

    UserDAO dao;

    {
        try {
            dao = new UserDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print(dao.getUser());
        request.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(request, response);
    }
}
