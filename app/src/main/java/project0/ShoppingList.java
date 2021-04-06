package project0;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Max Lee
 * @version 1.0
 * 
 * The Shopping List data structure
 */
public class ShoppingList
{
    private String listName;
    private List<ListItem> shoppingList;
    private int checkedIndex;

    /**
     * Creates a basic empty shopping list with a name
     * @param listName name of the shopping list
     */
    public ShoppingList(String listName)
    {
        this.listName = listName;
        shoppingList = new ArrayList<>();
        checkedIndex = 0;
    }

    /**
     * Creates an already populated shopping list with data from the DB
     * @param listName name of the Shopping list
     * @param titles all titles of all list items in the shopping list
     * @param bodies all bodies
     * @param checkedIndex index where list items start ebing checked
     */
    public ShoppingList(String listName, Array titles, Array bodies, int checkedIndex)
    {
        this.listName = listName;
        shoppingList = new ArrayList<>();
        this.checkedIndex = checkedIndex;

        String[] titlesStr = {};
        try {
            titlesStr = (String[])titles.getArray();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        String[] bodiesStr = {};
        try {
            bodiesStr = (String[])bodies.getArray();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < titlesStr.length; i++)
        {
            shoppingList.add(new ListItem(titlesStr[i], bodiesStr[i]));
        }
    }

    /**
     * Gets the name of the list
     * @return shopping list name
     */
    public String getListName()
    {
        return listName;
    }

    /**
     * Gets all titles
     * @return list of titles contained in the shopping list
     */
    public List<String> getTitles()
    {
        List<String> titles = new ArrayList<>();

        for(ListItem item : shoppingList)
        {
            titles.add(item.getTitle());
        }

        return titles;
    } 

    /**
     * Gets all bodies
     * @return list of bodies
     */
    public List<String> getBodies()
    {
        List<String> bodies = new ArrayList<>();

        for(ListItem item : shoppingList)
        {
            bodies.add(item.getBody());
        }

        return bodies;
    } 

    /**
     * Adds an item to the shopping list
     * @param title title of the list item
     * @param body body of the list item
     */
    public void addItem(String title, String body)
    {
        shoppingList.add(checkedIndex, new ListItem(title, body));
        checkedIndex++;
    }

    /**
     * Changes the title of the ListItem at the index
     * @param index index of the item being editted
     * @param newTitle the new title for the item
     */
    public void editItemTitle(int index, String newTitle)
    {
        shoppingList.get(index).setTitle(newTitle);
    }

    /**
     * Changes the body of the ListItem at the index
     * @param index index of the item being editted
     * @param newBody the new body for the item
     */
    public void editItemBody(int index, String newBody)
    {
        shoppingList.get(index).setBody(newBody);
    }

    /**
     * Removes the ListItem at the index
     * @param index index of item to delete
     */
    public void deleteItem(int index)
    {
        shoppingList.remove(index);
        if(index < checkedIndex)
        {
            checkedIndex--;
        }
    }

    /**
     * Easy way to see if Shopping List is empty
     * @return if its empty or not
     */
    public boolean isEmpty()
    {
        return shoppingList.isEmpty();
    }

    /**
     * Easy way to get the size of the Shopping List
     * @return size of the list
     */
    public int size()
    {
        return shoppingList.size();
    }

    /**
     * Getter for the checked index 
     * @return checkedIndex
     */
    public int getCheckedIndex()
    {
        return checkedIndex;
    }

    /**
     * Checks an item by putting it at the end of the list and then deleting it,
     * this makes it so that we have the same number of items but, checked index is lower
     * @param index index of item being checked
     */
    public void checkItem(int index)
    {
        shoppingList.add(shoppingList.get(index));
        deleteItem(index);
    }

    /**
     * Displays the whole shopping list with a check in front to show if it has been "checked" or not
     */
    public void displayList()
    {
        for(int i = 0; i < size(); i++)
        {
            StringBuilder line = new StringBuilder();
            
            line.append((i+1) + "- ");
            if(i < checkedIndex)
            {
                line.append("[ ] ");
            }
            else
            {
                line.append("[x] ");
            }
            line.append(shoppingList.get(i));

            System.out.println(line);
        }
    }

    /**
     * toString in the same format as the display method
     */
    @Override
    public String toString()
    {
        StringBuilder stringRepresentation = new StringBuilder();

        for(int i = 0; i < size(); i++)
        {
            StringBuilder line = new StringBuilder();
            
            line.append((i+1) + "- ");
            if(i < checkedIndex)
            {
                line.append("[ ] ");
            }
            else
            {
                line.append("[x] ");
            }
            line.append(shoppingList.get(i));

            stringRepresentation.append(line);
        }

        return stringRepresentation.toString();
    }
    
    /**
     * Private inner class to represent each item in the ShoppingList.
     * Has basic getters, setters and toString methods
     */
    private class ListItem
    {
        private String title;
        private String body;

        /**
         * Creates an item with a title and body
         * 
         * @param title 
         * @param body
         */
        public ListItem(String title, String body)
        {
            this.title = title;
            this.body = body;
        }

        public String getTitle()
        {
            return title;
        }

        public String getBody()
        {
            return body;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public void setBody(String body)
        {
            this.body = body;
        }

        @Override
        public String toString()
        {
            return getTitle() + ": " + getBody();
        }
    }
}