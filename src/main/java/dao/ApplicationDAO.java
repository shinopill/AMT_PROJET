package dao;

import model.Application;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Stateless
public class ApplicationDAO implements ApplicationDAOLocal {

   // Add business logic below.
   @Resource(lookup = "java:/AMT")
   private DataSource dataSource;

    private Connection connection;

    public ApplicationDAO() throws SQLException {
    }

    @Override
    public ArrayList<Application> getAllApplications() throws SQLException {
        this.connection = dataSource.getConnection();
        ArrayList<Application> applications = new ArrayList<Application>();
        String query = "SELECT * FROM applications";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement =  connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Application app = new Application(resultSet.getString(2),resultSet.getString(1),
                        resultSet.getString(5));
                app.setKeyAPI(resultSet.getInt(3));
                app.setKeySecret(resultSet.getInt(4));
                applications.add(app);
            }

            return applications;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return null;
    }

    @Override
    public int getSize() throws SQLException {
        this.connection = dataSource.getConnection();
        String query = "SELECT COUNT(*) FROM applications ";
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
    public int getSize(String name) throws SQLException {
        this.connection = dataSource.getConnection();
        String query = "SELECT COUNT(*) FROM applications  WHERE appOwner like  ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setString(1,name);

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
    public ArrayList<Application> getApplicationPages(int rowNumber, int limit) throws SQLException {
        this.connection = dataSource.getConnection();
        ArrayList<Application> applications = new ArrayList<Application>();
        String query = "SELECT * FROM applications  ORDER BY appName LIMIT ? OFFSET ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setInt(1,limit);
            preparedStatement.setInt(2,rowNumber);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                Application app = new Application(resultSet.getString(2),resultSet.getString(1),
                        resultSet.getString(5));
                app.setKeyAPI(resultSet.getInt(3));
                app.setKeySecret(resultSet.getInt(4));
                applications.add(app);
            }

            return applications;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return null;
    }

    @Override
    public ArrayList<Application> getApplicationPages(String email, int rowNumber, int limit) throws SQLException {
        this.connection = dataSource.getConnection();
        ArrayList<Application> applications = new ArrayList<Application>();
        String query = "SELECT * FROM applications WHERE appOwner LIKE ? ORDER BY appName LIMIT ? OFFSET ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setInt(2,limit);
            preparedStatement.setInt(3,rowNumber);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                Application app = new Application(resultSet.getString(2),resultSet.getString(1),
                        resultSet.getString(5));
                app.setKeyAPI(resultSet.getInt(3));
                app.setKeySecret(resultSet.getInt(4));
                applications.add(app);
            }

            return applications;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return null;
    }

    @Override
    public ArrayList<Application> getAllApplications(String email) throws SQLException {
        this.connection = dataSource.getConnection();
        ArrayList<Application> applications = new ArrayList<Application>();
        String query = "SELECT * FROM applications WHERE appOwner LIKE ? ";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                Application app = new Application(resultSet.getString(2),resultSet.getString(1),
                        resultSet.getString(5));
                app.setKeyAPI(resultSet.getInt(3));
                app.setKeySecret(resultSet.getInt(4));
                applications.add(app);
            }

            return applications;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return null;
    }

    @Override
    public Application find(String email, String name) throws SQLException {
        this.connection = dataSource.getConnection();
        String sql = "SELECT * FROM applications WHERE appOwner LIKE ? AND appName LIKE ?";
        ResultSet resultSet = null;
        int result = 0;
        PreparedStatement preparedStatement    = null;
        PreparedStatement preparedStatementAdd = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2,name);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                        Application app = new Application(resultSet.getString(2),resultSet.getString(1),
                        resultSet.getString(5));
                app.setKeyAPI(resultSet.getInt(3));
                app.setKeySecret(resultSet.getInt(4));
               return app;

            }else {
                result = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return null;

    }

    @Override
    public int createAppIfNotExist(String email, Application app) throws SQLException {
        this.connection = dataSource.getConnection();
        String sql = "SELECT * FROM applications WHERE appOwner LIKE ? AND appName LIKE ?";
        ResultSet resultSet = null;
        int result = 0;
        PreparedStatement preparedStatement    = null;
        PreparedStatement preparedStatementAdd = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2,app.getName());
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                String sqlAdd = "INSERT INTO applications(appName,appOwner,keyAPI,keySecret,description) VALUES (?,?,?,?,?)";
                preparedStatementAdd = connection.prepareStatement(sqlAdd);
                preparedStatementAdd.setString(2, email);
                preparedStatementAdd.setString(1, app.getName());
                preparedStatementAdd.setInt(3, app.getKeyAPI());
                preparedStatementAdd.setInt(4, app.getKeySecret());
                preparedStatementAdd.setString(5, app.getDescription());
                preparedStatementAdd.executeUpdate();

                result = 1;
            }else {
                result = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return result;
    }

    @Override
    public int deleteApp(String email, String name) throws SQLException {
        this.connection = dataSource.getConnection();
        String sql = "DELETE FROM applications WHERE appOwner LIKE ? AND appName LIKE ?;";
        ResultSet resultSet = null;
        int result = 0;
        PreparedStatement preparedStatement    = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2,name);
            preparedStatement.executeUpdate();

            result = 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return result;
    }

    @Override
    public int updateDesciption(String email, String appName, String description) throws SQLException {
        this.connection = dataSource.getConnection();
        String sql = "UPDATE applications set description=? WHERE appOwner LIKE ? AND appName LIKE ?;";
        int result = 0;
        PreparedStatement preparedStatement    = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, description);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3,appName);
            preparedStatement.executeUpdate();

            result = 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return result;
    }

    @Override
    public int updateName(String email, String appName, String newName) throws SQLException {
        this.connection = dataSource.getConnection();
        String sql = "UPDATE applications set appName=? WHERE appOwner LIKE ? AND appName LIKE ?";
        int result = 0;
        PreparedStatement preparedStatement    = null;

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3,appName);
            preparedStatement.executeUpdate();

            result = 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return result;
    }


}
