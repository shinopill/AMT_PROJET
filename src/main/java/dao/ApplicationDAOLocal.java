package dao;

import model.Application;
import model.User;

import javax.ejb.Local;
import java.util.LinkedList;

@Local
public interface ApplicationDAOLocal {
   LinkedList<Application> getAllApplications(User user);

}
