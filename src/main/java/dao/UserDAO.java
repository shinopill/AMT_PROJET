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
   
   private final String userFindingQuery = "SELECT * FROM Users WHERE Users.id = ";
   
   private String userCreatingQuery(String name, String mail, String pwd){
       return "INSERT INTO Users(name, email, password) VALUES ("
               + name +", "+ mail + ", " + pwd +")";
    }
   
   // Add business logic below.
    @Resource(lookup = "java:/runChickenRun")
    private DataSource dataSource;
    private Connection connection;
    public UserDAO() throws SQLException {
      // connection =  dataSource.getConnection();
        System.out.print("ntiente");
    }

    public User find(String userMail) {
        
        PreparedStatement preparedStatement    = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(userFindingQuery + userMail+";");
            resultSet = preparedStatement.executeQuery();
        
            if (!resultSet.next()) {
               
                //return resultSet.getString(1);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new User();
    }


   @Override
   public void createUser(User user) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public int updateUser(String colonne) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   @Override
   public void deleteUser(User user) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

    @Override
    public int getAdmin(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getActive(String username){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int setActive(int a ){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
