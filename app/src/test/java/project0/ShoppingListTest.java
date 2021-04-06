package project0;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShoppingListTest 
{
    private static final String[] titles = {"Banana", "Apples", "Cough Meds", "Steak"};
    private static final String[] bodies = {"Grab 2", "4", "Elderberry spray", "Ribeyes"};

    @Test public void testShoppingListAddItem() 
    {
        ShoppingList shoppingList = new ShoppingList("Testing Add List");
        assertEquals("New shopping list should have size of 0", 0, shoppingList.size());

        shoppingList.addItem("Test Title", "Test Body");
        assertEquals("Shopping list should have size of 1", 1, shoppingList.size());

        populateShoppingList(shoppingList);

        assertEquals("Shopping list should have size of "+(1+titles.length), (1+titles.length), shoppingList.size());
    }
    
    @Test public void testShoppingListDeleteItem()
    {
        ShoppingList shoppingList = new ShoppingList("Testing Delete List");
        populateShoppingList(shoppingList);
        
        shoppingList.deleteItem(0);

        assertEquals("Shopping list should have size of "+(titles.length-1)+" after 1 deletion", (titles.length-1), shoppingList.size());
    }

    @Test public void testShoppingListCheckedItem()
    {
        ShoppingList shoppingList = new ShoppingList("Testing Check List");
        populateShoppingList(shoppingList);

        shoppingList.checkItem(0);

        assertEquals("Shopping list's checked index should be "+ (titles.length-1), (titles.length-1), shoppingList.getCheckedIndex());
    }

    private void populateShoppingList(ShoppingList shoppingList)
    {
        for(int i = 0; i < titles.length; i++)
        {
            shoppingList.addItem(titles[i], bodies[i]);
        }
    }
}
