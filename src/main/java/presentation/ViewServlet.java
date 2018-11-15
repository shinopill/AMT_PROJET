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
        int nbAppShowed = 0;
        HttpSession session = request.getSession(false);
        int page = (int)session.getAttribute("pageApp");
        int nbElementToShow = 0;
        int nbAppToShow = 0;
        int appListSize = 0;
        try {

            if(session != null && session.getAttribute("email") != null) {

                //test for the click in next/previous
                if(request.getParameter("do") != null) {
                    if (request.getParameter("do").equals("next")) {
                        session.setAttribute("pageApp", page + 1);
                    } else if (request.getParameter("do").equals("previous")) {
                        session.setAttribute("pageApp", page - 1);
                    }
                }

                //test for delete an app
                if(request.getParameter("delete") != null){
                    appDao.deleteApp((String)session.getAttribute("email"),request.getParameter("delete"));
                }

                isAdmin = (int) session.getAttribute("admin");
                appListSize =  isAdmin == 1 ?  appDao.getSize() : appDao.getSize((String)session.getAttribute("email"));

                nbAppToShow = appListSize - nbAppShowed;
                nbElementToShow = nbAppToShow > Application.ELEMENT_BY_PAGE ? Application.ELEMENT_BY_PAGE : nbAppToShow;

                nbAppShowed = (int)session.getAttribute("pageApp") * Application.ELEMENT_BY_PAGE;

                list =  isAdmin == 1 ? appDao.getApplicationPages(nbAppShowed,nbElementToShow) : appDao.getApplicationPages((String)session.getAttribute("email"),nbAppShowed,nbElementToShow);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        request.setAttribute("applist", list);
        session.setAttribute("appToSee",appListSize - nbAppShowed - nbElementToShow);
        request.setAttribute("admin", isAdmin);
        request.getRequestDispatcher("/WEB-INF/pages/view.jsp").forward(request, response);
    }
}
