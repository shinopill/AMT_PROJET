package presentation;

import dao.UserDAOLocal;
import model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfilServlet extends javax.servlet.http.HttpServlet {

    @EJB
    UserDAOLocal dao;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user =  dao.find(request.getParameter("email"));
        request.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(request, response);
    }
    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException){

    }
    */
}
