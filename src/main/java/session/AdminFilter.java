package session;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) {
        this.context = fConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        HttpSession session = req.getSession(false);

        if (session.getAttribute("admin") != null && (int)session.getAttribute("admin") == 1) {  // checking whether the user is an admin
            // pass the request along the filter chain
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/amt_project/filtered/view");
        }
    }

    public void destroy() {
        //close any resources here
    }
}