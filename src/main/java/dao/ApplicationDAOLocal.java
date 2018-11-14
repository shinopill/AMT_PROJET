package dao;

import model.Application;

import javax.ejb.Local;
import java.sql.SQLException;
import java.util.ArrayList;

@Local
public interface ApplicationDAOLocal {
   ArrayList<Application> getAllApplications() throws SQLException;

   ArrayList<Application> getAllApplications(String email) throws SQLException;
   Application find(String email, String name) throws SQLException;
   int createAppIfNotExist(String email, Application app) throws SQLException;
   int deleteApp(String email, String name) throws SQLException;
   int updateDesciption(String email, String appName, String description) throws SQLException;
   int updateName(String email, String appName, String newName) throws SQLException;

}

