package project0;

import java.util.Scanner;

public class ListController
{
    private static Scanner input = new Scanner(System.in);
    private ShoppingList shoppingList;

    public ListController(ShoppingList shoppingList)
    {
        this.shoppingList = shoppingList;
    }

    public void displayInitialMessage()
    {
        if(shoppingList.isEmpty())
        {
            System.out.println("Looks like you don't have a list yet!");
        }
        else
        {
            this.shoppingList.displayList();
        }
        System.out.println();
    }

    public boolean menu()
    {
        int menuSelection = 0;

        do
        {
            System.out.println("Please choose your action:");
            System.out.println("1: Add an item to your list");
            System.out.println("2: Edit title of an item");
            System.out.println("3: Edit body of an item");
            System.out.println("4: Delete an item");
            System.out.println("5: Check off an item");
            System.out.println("6: Quit");
            System.out.print("What did you want to do?(1-6): ");

            menuSelection = input.nextInt();
            input.nextLine();
        }while(menuSelection < 1 || menuSelection > 6);

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