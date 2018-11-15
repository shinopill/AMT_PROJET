package session;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) {
        this.context = fConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("email") != null && session.getAttribute("isDisabled") != null && (int)session.getAttribute("isDisabled") == 0  ) {   //checking whether the session exists
            // pass the request along the filter chain
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/amt_project/");
        }
    }

    public void destroy() {
        //close any resources here
    }
}