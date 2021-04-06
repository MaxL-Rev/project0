package project0;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Data Access Object for interacting between any Shopping Lists and the DB
 */
public class ShoppingListDao
{
    private Connection connection;

    /**
     * Gives the DAO a connection to the DB
     * @param connection
     */
    public ShoppingListDao(Connection connection)
    {
        this.connection = connection;
    }

    /**
     * Inserts a Shopping List into the DB connected to the user
     * @param shoppingList Shopping List user has been interacting with
     * @param userId ID of the current user
     * @return the status of the insert 0=fail otherwise number of rows affected
     */
    public int insert(ShoppingList shoppingList, int userId) 
    {
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO lists (userId, listName, titles, bodies, checkedIndex) VALUES (?, ?, ?, ?, ?);");

            pStatement.setInt(1, userId);
            pStatement.setString(2, shoppingList.getListName());
            Array sqlTitles = connection.createArrayOf("text", shoppingList.getTitles().toArray());
            pStatement.setArray(3, sqlTitles);
            Array sqlBodies = connection.createArrayOf("text", shoppingList.getBodies().toArray());
            pStatement.setArray(4, sqlBodies);
            pStatement.setInt(5, shoppingList.getCheckedIndex());

            return pStatement.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Gets all Shopping Lists from the DB
     * @return List of all shopping lists
     */
    public List<ShoppingList> getAll() 
    {
        List<ShoppingList> listOfShoppingLists = new ArrayList<>();
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM lists;");
            ResultSet rSet = pStatement.executeQuery();

            while(rSet.next())
            {
                String listName = rSet.getString("listName");
                Array titles = rSet.getArray("titles");
                Array bodies = rSet.getArray("bodies");
                int checkedIndex = rSet.getInt("checkedIndex");

                listOfShoppingLists.add(new ShoppingList(listName, titles, bodies, checkedIndex));
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfShoppingLists;
    }

    /**
     * Gets all Shopping Lists connected to the user
     * @param user the User currently selected 
     * @return List of all Shopping Lists connected to the user
     */
    public List<ShoppingList> getAllFromUser(User user) 
    {
        List<ShoppingList> listOfShoppingLists = new ArrayList<>();
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM lists WHERE userId="+user.getUserID()+";");
            ResultSet rSet = pStatement.executeQuery();

            while(rSet.next())
            {
                String listName = rSet.getString("listName");
                Array titles = rSet.getArray("titles");
                Array bodies = rSet.getArray("bodies");
                int checkedIndex = rSet.getInt("checkedIndex");

                listOfShoppingLists.add(new ShoppingList(listName, titles, bodies, checkedIndex));
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfShoppingLists;
    } 

    /**
     * Updates the shopping list in the DB with the new data gathered from the program
     * @param shoppingList current shopping list
     * @return status of the query
     */
    public int update(ShoppingList shoppingList) 
    {
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("UPDATE lists SET titles = ?, bodies = ?, checkedIndex = ? WHERE listName = ?;");
            
            Array sqlTitles = connection.createArrayOf("text", shoppingList.getTitles().toArray());
            pStatement.setArray(1, sqlTitles);
            Array sqlBodies = connection.createArrayOf("text", shoppingList.getBodies().toArray());
            pStatement.setArray(2, sqlBodies);
            pStatement.setInt(3, shoppingList.getCheckedIndex());
            pStatement.setString(4, shoppingList.getListName());

            return pStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Deletes the shopping list from the DB
     * @param shoppingList current shopping list
     * @return status of the query
     */
    public int delete(ShoppingList shoppingList) 
    {
        try 
        {
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM lists WHERE listName = ?");

            pStatement.setString(1, shoppingList.getListName());

            return pStatement.executeUpdate();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
