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
import java.util.ArrayList;
import java.util.LinkedList;

public class ViewServlet extends javax.servlet.http.HttpServlet {

    private User user;
    @EJB
    UserDAOLocal userDao;
    @EJB
    ApplicationDAOLocal appDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Application> list = null;
        int isAdmin = 0;
        System.out.println("TEST");
        try {
            HttpSession session = request.getSession(false);
            if(session != null && session.getAttribute("email") != null) {
                System.out.println((String) session.getAttribute("email"));
                list = appDao.getAllApplications((String) session.getAttribute("email"));
                isAdmin = (int) session.getAttribute("admin");
                System.out.println(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("applist",list);
        request.setAttribute("admin", isAdmin);
        request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);
    }
}
