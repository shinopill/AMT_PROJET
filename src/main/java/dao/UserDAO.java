package dao;

import javax.annotation.Resource;
import javax.ejb.ApplicationException;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;


@Stateless
public class UserDAO implements UserDAOLocal{

    // Add business logic below.
    @Resource(lookup = "java:/AMT")
    private DataSource dataSource;
    private Connection connection;

    public UserDAO() throws SQLException {

    }

    public User find(String userMail) throws SQLException {
        connection = dataSource.getConnection();
        String query = "SELECT * FROM dev_users WHERE email LIKE ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userMail);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                return user;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return null;
    }

    @Override
    public int createUser(User user) throws SQLException {
        connection = dataSource.getConnection();
        String sql = "SELECT email FROM dev_users WHERE email LIKE ?;";
        ResultSet resultSet = null;
        int result = 0;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAdd = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getEmail());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                String sqlAdd = "INSERT INTO dev_users(firstName,lastName,email,passwd,isBeingReseted,isDisabled,isAdmin)"
                        + " VALUES(?,?,?,?,0,0,0);";
                try {
                    preparedStatementAdd = connection.prepareStatement(sqlAdd);
                    preparedStatementAdd.setString(1, user.getFirstName());
                    preparedStatementAdd.setString(2, user.getLastName());
                    preparedStatementAdd.setString(3, user.getEmail());
                    preparedStatementAdd.setString(4, user.getPassword());
                    preparedStatementAdd.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    result = 2;
                }

                result = 1;
            } else {
                result = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatementAdd != null && preparedStatement != null) {
                    preparedStatementAdd.close();
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    @Override
    public ArrayList<User> getApplicationPages(int rowNumber, int limit) throws SQLException {
        this.connection = dataSource.getConnection();
        ArrayList<User> users = new ArrayList<User>();
        String query = "SELECT * FROM dev_users ORDER BY email LIMIT ? OFFSET ?" ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,limit);
            preparedStatement.setInt(2,rowNumber);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                User user = new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                users.add(user);

            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return null;
    }


    @Override
    public int getSize() throws SQLException {
        this.connection = dataSource.getConnection();
        String query = "SELECT COUNT(*) FROM dev_users";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement =  connection.prepareStatement(query);
                        resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
               return resultSet.getInt(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return 0;
    }
    @Override
    public int updateUser(String userMail, String colonne, String value) {
 /*
        String sql = "UPDATE dev_users set ?=? WHERE email LIKE ? ;";
        int result = 0;
        
        try {

            connection = dataSource.getConnection();

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, colonne);
            preparedStatement.setString(2, value);
            preparedStatement.setString(3, userMail);
            preparedStatement.executeUpdate();

            result = 1;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
        return 0;
    }

    @Override
    public int updatePassword(String userMail, String value) throws SQLException {

        String sql = "UPDATE dev_users set passwd=? WHERE email LIKE ? ;";
        int result = 0;
        connection = dataSource.getConnection();
        try {



            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, value);
            preparedStatement.setString(2, userMail);
            preparedStatement.executeUpdate();

            result = 1;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            connection.close();
        }

        return result;
    }

    @Override
    public int deleteUser(String email) throws SQLException {
        connection = dataSource.getConnection();
        String deleteUser = "DELETE  FROM dev_users WHERE email LIKE ?";
        String deleteApp = "DELETE  FROM applications WHERE appOwner LIKE ?";
        //TODO delete les users d'une applications une fois faite.
        int result = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(deleteUser);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(deleteApp);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            result = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return result;
    }

    @Override
    public int getAdmin(String username) throws SQLException {
        connection = dataSource.getConnection();
        String query = "SELECT isAdmin FROM dev_users WHERE email LIKE ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return -1;
    }

    @Override
    public int getActive(String username) throws SQLException {
        connection = dataSource.getConnection();
        String query = "SELECT isDisabled FROM dev_users WHERE email LIKE ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return -1;
    }

    @Override
    public int setActive(String email, int a) throws SQLException {
        connection = dataSource.getConnection();
        String query = "UPDATE dev_users SET isDisabled=? WHERE email LIKE ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(1, a);
            preparedStatement.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return -1;
    }

    @Override
    public int setRested(String email, int a) throws SQLException {
        connection = dataSource.getConnection();
        String query = "UPDATE dev_users SET isBeingReseted=? WHERE email LIKE ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(1, a);
            preparedStatement.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return -1;
    }

    @Override
    public ArrayList<User> getAllUsers() throws SQLException {
        connection = dataSource.getConnection();
        ArrayList<User> users = new ArrayList<User>();
        String query = "SELECT * FROM dev_users ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                users.add(user);

            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return null;
    }
}
