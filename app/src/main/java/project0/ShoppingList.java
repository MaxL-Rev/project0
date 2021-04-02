package project0;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList
{
    private String listName;
    private List<ListItem> shoppingList;
    private int checkedIndex;

    public ShoppingList(String listName)
    {
        this.listName = listName;
        shoppingList = new ArrayList<>();
        checkedIndex = 0;
    }

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

    public String getListName()
    {
        return listName;
    }

    public List<String> getTitles()
    {
        List<String> titles = new ArrayList<>();

        for(ListItem item : shoppingList)
        {
            titles.add(item.getTitle());
        }

        return titles;
    } 

    public List<String> getBodies()
    {
        List<String> bodies = new ArrayList<>();

        for(ListItem item : shoppingList)
        {
            bodies.add(item.getBody());
        }

        return bodies;
    } 

    public void addItem(String title, String body)
    {
        shoppingList.add(checkedIndex, new ListItem(title, body));
        checkedIndex++;
    }

    public void editItemTitle(int index, String newTitle)
    {
        shoppingList.get(index).setTitle(newTitle);
    }

    public void editItemBody(int index, String newBody)
    {
        shoppingList.get(index).setBody(newBody);
    }

    public void deleteItem(int index)
    {
        shoppingList.remove(index);
        if(index < checkedIndex)
        {
            checkedIndex--;
        }
    }

    public boolean isEmpty()
    {
        return shoppingList.isEmpty();
    }

    public int size()
    {
        return shoppingList.size();
    }

    public int getCheckedIndex()
    {
        return checkedIndex;
    }

    public void checkItem(int index)
    {
        shoppingList.add(shoppingList.get(index));
        deleteItem(index);
    }

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
    
    private class ListItem
    {
        private String title;
        private String body;

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