package dao;

import javax.annotation.Resource;
import javax.ejb.Stateless;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;


@Stateless
public class UserDAO implements UserDAOLocal {
    // Add business logic below.
    @Resource(lookup = "java:/runChickenRun")
    private DataSource dataSource;
    private Connection connection;
    public UserDAO() throws SQLException {
      // connection =  dataSource.getConnection();
        System.out.print("ntiente");
    }

    public String getUser(){
        String sql = "SELECT ID FROM users";
        PreparedStatement preparedStatement    = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {

                return resultSet.getString(1);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }


   @Override
   public void createUser() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void updateUser(String colonne) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void deleteUser(User user) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }


}
