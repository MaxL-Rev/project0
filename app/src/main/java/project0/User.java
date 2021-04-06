package project0;

/**
 * @author Max Lee
 * @version 1.0
 * 
 * Represents the user in the DB
 */
public class User 
{
    private int userID;
    private String userName;

    /**
     * Used for making new users that haven't been assigned an ID
     * @param userName
     */
    public User(String userName) {
        this.userName = userName;
    }

    /**
     * Used for making users that already had data in the DB
     * @param userID
     * @param userName
     */
    public User(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}