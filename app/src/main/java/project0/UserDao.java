package project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao 
{
    private Connection connection;
    
    public UserDao(Connection connection)
    {
        this.connection = connection;
    }

    public int insert(User user)
    {
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO users (userName) VALUES (?);");

            pStatement.setString(1, user.getUserName());

            int rowInsertedInto = pStatement.executeUpdate();
            ResultSet rSet = pStatement.getGeneratedKeys();
            if(rSet.next())
            {
                user.setUserID(rSet.getInt(1));
            }
            
            return rowInsertedInto;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

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
