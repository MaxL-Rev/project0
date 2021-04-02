/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App 
{
    public static void main(String[] args) 
    {
        Logger log = LogManager.getLogger(App.class.getName());
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
            e.printStackTrace();
        }
    }
}
