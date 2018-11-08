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
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/*
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        System.out.println(session);

        if (session != null && session.getAttribute("email") != null && session.getAttribute("active") != null ) {   //checking whether the session exists
            // pass the request along the filter chain
            chain.doFilter(request, response);
        } else {
            this.context.log("Unauthorized access request");
            RequestDispatcher rd =  req.getRequestDispatcher("/index.jsp");
            rd.include(req,resp);
        }
        */

        chain.doFilter(request, response);

    }

    public void destroy() {
        //close any resources here
    }
}