package presentation;

import dao.ApplicationDAOLocal;
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
import java.util.LinkedList;
import java.util.List;

public class ViewServlet extends javax.servlet.http.HttpServlet {

    private User user;
    @EJB
    UserDAOLocal userDao;
    @EJB
    ApplicationDAOLocal appDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Application> list = null;
        ArrayList<Application> list2 = null;
        int isAdmin = 0;
        HttpSession session = request.getSession(false);
        int nbAppShowed = (int)session.getAttribute("pageApp") * Application.ELEMENT_BY_PAGE;
        try {

            if(session != null && session.getAttribute("email") != null) {
                System.out.println((String) session.getAttribute("email"));
                list2 = appDao.getAllApplications();
                list = appDao.getAllApplications((String) session.getAttribute("email"));
                isAdmin = (int) session.getAttribute("admin");
                System.out.println(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(isAdmin == 0) {

            int nbAppToShow = list.size() - nbAppShowed;
            int nbElementToShow = nbAppToShow > Application.ELEMENT_BY_PAGE ? Application.ELEMENT_BY_PAGE : nbAppToShow;
            List<Application> listApp = list.subList(nbAppShowed,nbAppShowed + nbElementToShow);
            request.setAttribute("applist", listApp);
        }else{
            int nbAppToShow = list2.size() - nbAppShowed;
            int nbElementToShow = nbAppToShow > Application.ELEMENT_BY_PAGE ? Application.ELEMENT_BY_PAGE : nbAppToShow;
            List<Application> listApp = list2.subList(nbAppShowed,nbAppShowed + nbElementToShow);
            request.setAttribute("applist", listApp);
        }
        request.setAttribute("admin", isAdmin);
        request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);
    }
}
