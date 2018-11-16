package presentation;

import buisness.AdminServiceLocal;
import buisness.Email;
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
import java.util.UUID;

public class AdminServlet extends javax.servlet.http.HttpServlet {

    @EJB
    UserDAOLocal userDao;
    @EJB
    AdminServiceLocal admin;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Show the list of the users and allow the admin to reset an user password or change the account active status
        ArrayList<User> usersArray = null;
        HttpSession session = request.getSession(false);
        int page = (int) session.getAttribute("pageUser");

        // Check if clicked on previous/next
        if (request.getParameter("do") != null) {
            if (request.getParameter("do").equals("next")) {
                session.setAttribute("pageUser", page + 1);
            } else if (request.getParameter("do").equals("previous")) {
                session.setAttribute("pageUser", page - 1);
            }
        }

        // Check if clicked on enable/disable; change the user's active/inactive status if it's the case
        if (request.getParameter("disable") != null) {
            String name = request.getParameter("disable");
            admin.changeActive(name);
        }

        // Check if clicked on delete; delete the user if it's the case
        if(request.getParameter("delete") != null){
            String email = request.getParameter("delete");
            try {
                userDao.deleteUser(email);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Check if clicked on promote/demote; inverse admin status if it's the case
        if(request.getParameter("up") != null){
            String email = request.getParameter("up");
            try {
                userDao.setAdmin(email, userDao.getAdmin(email) == 0 ? 1 : 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Check if clicked on reset Password; If it's the case, send a mail to the user with his new password
        if (request.getParameter("reset") != null) {
            String name = request.getParameter("reset");
            admin.resetPassword(name);
        }


        int size = 0;
        // Deal with the pagination
        int nbUserShowed = (int) session.getAttribute("pageUser") * Application.ELEMENT_BY_PAGE;
        try {
           size = userDao.getSize();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int  nbUserToShow = size - nbUserShowed;
        int nbElementToShow = nbUserToShow > User.ELEMENT_BY_PAGE ? User.ELEMENT_BY_PAGE : nbUserToShow;
        List<User> listApp = null;
        try {
            listApp = userDao.getApplicationPages(nbUserShowed,nbElementToShow);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("userToSee", nbUserToShow - nbElementToShow);
        request.setAttribute("usersArray", listApp);
        request.getRequestDispatcher("/WEB-INF/pages/filtered/admin.jsp").forward(request, response);
    }
}
