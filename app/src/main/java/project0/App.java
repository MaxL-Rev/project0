/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project0;

import java.util.Scanner;

public class App 
{
    public String getGreeting() 
    {
        return "Hello World!";
    }

    public static void main(String[] args) 
    {
        System.out.println(new App().getGreeting());

        ShoppingList shoppingList = new ShoppingList();
        Scanner input = new Scanner(System.in);

        boolean continueWriting = true;
        int menuSelection = 0;
        while(continueWriting)
        {
            displayInitialMessage(shoppingList);
            menuSelection = menu(input);

            switch(menuSelection)
            {
                case 1:
                    editTitle(shoppingList, input);
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;
                case 5:
                    continueWriting = false;
                    break;
            }
        }
    }

    private static void displayInitialMessage(ShoppingList shoppingList)
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

    private static int menu(Scanner input)
    {
        int menuSelection = 0;

        do
        {
            System.out.println("Please choose your action:");
            System.out.println("1: Edit title of an item");
            System.out.println("2: Edit body of an item");
            System.out.println("3: Delete an item");
            System.out.println("4: Check an item");
            System.out.println("5: Quit");
            System.out.print("What did you want to do?(1-5): ");

            menuSelection = input.nextInt();
        }while(menuSelection < 1 || menuSelection > 5);

        System.out.println();
        return menuSelection;
    }

    private static void editTitle(ShoppingList shoppingList, Scanner input)
    {
        int index = 0;
        do
        {
            System.out.print("What number item did you want to edit?(1-" + shoppingList.size() + "): ");

            index = input.nextInt();
        }while(index < 1 || index > shoppingList.size());
        
    }
}
