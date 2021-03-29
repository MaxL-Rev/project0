package project0;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList
{
    private List<ListItem> shoppingList;
    private int checkedIndex;

    public ShoppingList()
    {
        shoppingList = new ArrayList<>();
        checkedIndex = 0;
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
            StringBuilder line = new StringBuilder("");
            
            //System.out.print((i+1) + "- ");
            line.append((i+1) + "- ");
            if(i < checkedIndex)
            {
                line.append("[ ] ");
            }
            else
            {
                line.append("[x] ");
            }
            line.append(shoppingList.get(i).getTitle() + ": " + shoppingList.get(i).getBody());
            //System.out.println(shoppingList.get(i).getTitle() + ": " + shoppingList.get(i).getBody());
            System.out.println(line);
        }
    }

    /*
    public void displayActive()
    {
        for(int i = 0; i < checkedIndex; i++)
        {
            System.out.println(i + ": " + shoppingList.get(i));
        }
    }

    public void displayChecked()
    {
        for(int i = checkedIndex; i < shoppingList.size(); i++)
        {
            System.out.println((i - checkedIndex + 1) + ": " + shoppingList.get(i));
        }
    }
    */
    
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