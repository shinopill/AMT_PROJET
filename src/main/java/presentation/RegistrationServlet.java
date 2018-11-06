package presentation;

import dao.UserDAOLocal;
import model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends javax.servlet.http.HttpServlet  {

    @EJB
    UserDAOLocal dao;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registrationForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPwd = req.getParameter("confirmPassword");
        if(!password.equals("") && !confirmPwd.equals("") && password.equals(confirmPwd)){

            User user = new User(firstname,email,password);
            dao.createUser(user);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/pages/registrationForm.jsp").forward(req, resp);
        }
    }
}
