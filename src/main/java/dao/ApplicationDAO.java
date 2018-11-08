package dao;

import model.Application;
import model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.util.LinkedList;

@Stateless
public class ApplicationDAO implements ApplicationDAOLocal {

   // Add business logic below.
    @Resource(lookup = "java:/AMT")
    private DataSource dataSource;

    @Override
    public LinkedList<Application> getAllApplications(User users){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
