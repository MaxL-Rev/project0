package project0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Max Lee
 * @version 1.0
 * 
 * Class to run the Shopping List program
 * <p>
 * Makes a connection to port 5432 and then the ListController handles all interaction between user and
 * their shopping list. Runs until user quits. Log is displayed in console and saved in logs/app.log
 * 
 * @param args arguments
 */ 
public class App 
{
    private static final Logger log = LogManager.getLogger(App.class.getName());
    public static void main(String[] args) 
    {
        String url = "jdbc:postgresql://localhost:5432/project0";
        String username = "project0";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            ListController controller = new ListController(new ShoppingListDao(connection), new UserDao(connection));
            controller.initialSetup();
            System.out.println("-------------------------------------------");

            boolean continueWriting = true;
            while(continueWriting)
            {
                continueWriting = controller.menu();

                if(continueWriting)
                {
                    System.out.println("-------------------------------------------");
                }
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
