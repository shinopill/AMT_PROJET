package presentation;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewServlet extends javax.servlet.http.HttpServlet {
    private User user;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In view.doGet");
        request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);
    }
}