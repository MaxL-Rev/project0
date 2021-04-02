package project0;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListController
{
    private static Scanner input = new Scanner(System.in);
    private ShoppingList shoppingList;
    private User user;
    private ShoppingListDao shoppingListDao;
    private UserDao userDao;

    public ListController(ShoppingListDao shoppingListDao, UserDao userDao)
    {
        this.shoppingListDao = shoppingListDao;
        this.userDao = userDao;
    }

    public void initialSetup()
    {
        System.out.println();
        System.out.println("Welcome to the Shopping List App!");
        getUser();
        getList();
    }

    private void getList()
    {
        List<ShoppingList> usersLists = shoppingListDao.getAllFromUser(user);

        if(usersLists.size() == 0)
        {
            System.out.println("Looks like you don't have a list yet!");
            createNewList();
        }
        else
        {
            pickList(usersLists);
        }
    }

    private void createNewList()
    {
        String newListName = "";
        do
        {
            System.out.print("What did you want your new list to be named? (must be unique): ");
            newListName = input.nextLine();
        }while(newListName.equals(""));
        System.out.println();

        shoppingList = new ShoppingList(newListName);
        shoppingListDao.insert(shoppingList, user.getUserID());
    }

    private void pickList(List<ShoppingList> lists)
    {
        System.out.println("Pick a list from below:");

        List<String> listNames = new ArrayList<>();
        for(ShoppingList list : lists)
        {
            System.out.println(list.getListName());
            listNames.add(list.getListName());
        }
        System.out.println();

        String pickedListName = "";
        do
        {
            System.out.print("Pick your list by typing the name: ");
            pickedListName = input.nextLine();
        }while(pickedListName.equals("") || !listNames.contains(pickedListName));

        for(ShoppingList list : lists)
        {
            if(list.getListName().equals(pickedListName))
            {
                shoppingList = list;
            }
        }
    }

    private void getUser()
    {
        String existingUser = "";

        do
        {
            System.out.print("Are you an existing user?(Y/n): ");
            existingUser = input.nextLine();
        }while(!existingUser.equalsIgnoreCase("Y") && !existingUser.equalsIgnoreCase("N"));
        System.out.println();

        if(existingUser.equalsIgnoreCase("Y"))
        {
            findExistingUser();
        }
        else
        {
            createNewUser();
        }
    }

    private void findExistingUser() 
    {
        User foundUser = null;

        do
        {
            System.out.print("What is your user name?: ");
            foundUser = userDao.findUser(input.nextLine());
        }while(foundUser.equals(null));
        System.out.println();
        
        user = foundUser;
    }

    private void createNewUser() 
    {
        int successfulInsert = 0;
        User newUser = null;

        do
        {
            System.out.print("What is your desired user name?(must be unique): ");
            String newUserName = input.nextLine();
            newUser = new User(newUserName);
            successfulInsert = userDao.insert(newUser);
        }while(successfulInsert == 0);
        System.out.println();

        user = newUser;
    }

    public boolean menu()
    {
        shoppingList.displayList();
        System.out.println();

        int menuSelection = 0;

        do
        {
            System.out.println("Please choose your action:");
            System.out.println("1: Add an item to your list");
            System.out.println("2: Edit title of an item");
            System.out.println("3: Edit body of an item");
            System.out.println("4: Delete an item");
            System.out.println("5: Check off an item");
            System.out.println("6: Save your list");
            System.out.println("7: Quit without saving");
            System.out.print("What did you want to do?(1-7): ");

            menuSelection = input.nextInt();
            input.nextLine();
        }while(menuSelection < 1 || menuSelection > 7);

        System.out.println();
        return processListRequest(menuSelection);
    }

    private boolean processListRequest(int request)
    {
        switch(request)
        {
            case 1:
                addItemToList();
                return true;

            case 2:
                editTitle();
                return true;

            case 3:
                editBody();
                return true;

            case 4:
                deleteItem();
                return true;

            case 5:
                checkOffItem();
                return true;

            case 6:
                shoppingListDao.update(shoppingList);
                return true;

            case 7:
                return false;    
        }
        return true;
    }

    private void addItemToList()
    {
        String title = "";
        while(title.equals(""))
        {
            System.out.print("What is the title for this new item?: ");
            title = input.nextLine();
        }

        String body = "";
        while(body.equals(""))
        {
            System.out.print("What is the body for this new item?: ");
            body = input.nextLine();
            System.out.println();
        }
        
        shoppingList.addItem(title, body);
    }

    private int getIndexFromUser(String message)
    {
        int index = 0;
        do
        {
            System.out.print("What number item did you want to " + message + "?(1-" + this.shoppingList.size() + "): ");

            index = input.nextInt();
            input.nextLine();
        }while(index < 1 || index > this.shoppingList.size());

        return index;
    }

    private void editTitle()
    {
        int index = getIndexFromUser("edit");
        
        String newTitle;
        System.out.print("What did you want the new title to be?: ");
        newTitle = input.nextLine();

        shoppingList.editItemTitle(index-1, newTitle);
    }

    private void editBody()
    {
        int index = getIndexFromUser("edit");
        
        String newBody;
        System.out.print("What did you want the new body to be?: ");
        newBody = input.nextLine();

        shoppingList.editItemBody(index-1, newBody);
    }

    private void deleteItem()
    {
        int index = getIndexFromUser("delete");

        shoppingList.deleteItem(index-1);
    }

    private void checkOffItem()
    {
        int index = getIndexFromUser("check off");

        shoppingList.checkItem(index-1);
    }
}