package presentation;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewServlet extends javax.servlet.http.HttpServlet  {
    private User user;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    if(username.equals("romain") && password.equals("123")){
        req.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(req, resp);
    }else{
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}
}
