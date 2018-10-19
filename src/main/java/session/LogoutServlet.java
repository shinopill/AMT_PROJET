package session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends javax.servlet.http.HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        //invalidate the session if exists
        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);    }
}
