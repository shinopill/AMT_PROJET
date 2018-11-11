package presentation;

import dao.ApplicationDAOLocal;
import dao.UserDAOLocal;
import model.Application;
import model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ViewServlet extends javax.servlet.http.HttpServlet {

    private User user;
    @EJB
    UserDAOLocal userDao;
    @EJB
    ApplicationDAOLocal appDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In view.doGet");
        ArrayList<Application> list = null;
        int isAdmin = 0;
        try {
            list = appDao.getAllApplications((String)request.getAttribute("email"));
            isAdmin = userDao.getAdmin("email");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("applist",list);
        request.setAttribute("admin", isAdmin);
        System.out.println("Admin = " + isAdmin);
        request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);
    }
}
