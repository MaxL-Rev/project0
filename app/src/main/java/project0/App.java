/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project0;

import java.util.Scanner;

public class App 
{
    public static void main(String[] args) 
    {
        App program = new App();

        ShoppingList shoppingList = new ShoppingList();
        Scanner input = new Scanner(System.in);

        boolean continueWriting = true;
        int menuSelection = 0;
        while(continueWriting)
        {
            program.displayInitialMessage(shoppingList);
            menuSelection = program.menu(input);

            switch(menuSelection)
            {
                case 1:
                    program.addItemToList(shoppingList, input);
                    break;

                case 2:
                    program.editTitle(shoppingList, input);
                    break;

                case 3:
                    program.editBody(shoppingList, input);
                    break;

                case 4:
                    program.deleteItem(shoppingList, input);
                    break;

                case 5:
                    program.checkOffItem(shoppingList, input);
                    break;

                case 6:
                    continueWriting = false;
                    break;
            }

            if(continueWriting)
            {
                System.out.println("-------------------------------------------");
            }
        }
    }

    private void displayInitialMessage(ShoppingList shoppingList)
    {
        if(shoppingList.isEmpty())
        {
            System.out.println("Looks like you don't have a list yet!");
        }
        else
        {
            shoppingList.displayList();
        }
        System.out.println();
    }

    private int menu(Scanner input)
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
        return menuSelection;
    }

    private void addItemToList(ShoppingList shoppingList, Scanner input)
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

    private static int getIndexFromUser(Scanner input, int listSize)
    {
        int index = 0;
        do
        {
            System.out.print("What number item did you want to edit?(1-" + listSize + "): ");

            index = input.nextInt();
            input.nextLine();
        }while(index < 1 || index > listSize);

        return index;
    }

    private void editTitle(ShoppingList shoppingList, Scanner input)
    {
        int index = getIndexFromUser(input, shoppingList.size());
        
        String newTitle;
        System.out.print("What did you want the new title to be?: ");
        newTitle = input.nextLine();

        shoppingList.editItemTitle(index-1, newTitle);
    }

    private void editBody(ShoppingList shoppingList, Scanner input)
    {
        int index = getIndexFromUser(input, shoppingList.size());
        
        String newBody;
        System.out.print("What did you want the new body to be?: ");
        newBody = input.nextLine();

        shoppingList.editItemBody(index-1, newBody);
    }

    private void deleteItem(ShoppingList shoppingList, Scanner input)
    {
        int index = getIndexFromUser(input, shoppingList.size());

        shoppingList.deleteItem(index-1);
    }

    private void checkOffItem(ShoppingList shoppingList, Scanner input)
    {
        int index = getIndexFromUser(input, shoppingList.size());

        shoppingList.checkItem(index-1);
    }
}
