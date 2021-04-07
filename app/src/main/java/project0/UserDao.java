package project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Max Lee
 * @version 1.0
 * 
 * The Data Access Object for User
 */
public class UserDao 
{
    private Connection connection;
    
    public UserDao(Connection connection)
    {
        this.connection = connection;
    }

    /**
     * Inserts the user into the DB
     * @param user current user interacting
     * @return status of the query
     */
    public int insert(User user)
    {
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO users (userName) VALUES (?);", Statement.RETURN_GENERATED_KEYS);

            pStatement.setString(1, user.getUserName());

            int rowsAffected = pStatement.executeUpdate();
            ResultSet rSet = pStatement.getGeneratedKeys();

            if(rSet.next())
            {
                user.setUserID((int)rSet.getLong(1));
            }
            
            return rowsAffected;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Finds the user associated with their name
     * @param userName name of the user
     * @return the found User
     */
    public User findUser(String userName)
    {
        User foundUser = null;
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM users WHERE userName = ?;");

            pStatement.setString(1, userName);

            ResultSet rSet = pStatement.executeQuery();
            rSet.next();
            foundUser = new User(rSet.getInt("userId"), rSet.getString("userName"));
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUser;
    }

    /**
     * Gets all users in the DB
     * @return List of all users in the DB
     */
    public List<User> getAll()
    {
        List<User> users = new ArrayList<>();
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM users;");
            ResultSet rSet = pStatement.executeQuery();

            while(rSet.next())
            {
                users.add(new User(rSet.getInt("userId"), rSet.getString("userName")));
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Updates the user with new data from the program
     * @param user current user
     * @return status of the query
     */
    public int update(User user)
    {
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("UPDATE users SET userName = ? WHERE userId = ?;");

            pStatement.setString(1, user.getUserName());
            pStatement.setInt(2, user.getUserID());

            return pStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Deletes the user from the DB
     * @param user current user
     * @return status of the query
     */
    public int delete(User user)
    {
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM users WHERE userId = ?;");

            pStatement.setInt(1, user.getUserID());

            return pStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
