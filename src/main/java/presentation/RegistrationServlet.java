package presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends javax.servlet.http.HttpServlet  {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registrationForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPwd = req.getParameter("confirmPassword");
        if(password != null && confirmPwd != null && password.equals(confirmPwd)){
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/pages/registrationForm.jsp").forward(req, resp);
        }
    }
}
