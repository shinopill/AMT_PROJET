package presentation;

import dao.UserDAO;
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
import java.util.List;

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
        HttpSession session = request.getSession(false);
        int nbAppShowed = (int)session.getAttribute("pageApp") * Application.ELEMENT_BY_PAGE;
        int nbAppToShow = usersArray.size() - nbAppShowed;
        int nbElementToShow = nbAppToShow > User.ELEMENT_BY_PAGE ? User.ELEMENT_BY_PAGE : nbAppToShow;
        List<User> listApp = usersArray.subList(nbAppShowed,nbAppShowed + nbElementToShow);
        request.setAttribute("usersArray",usersArray);
        request.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
