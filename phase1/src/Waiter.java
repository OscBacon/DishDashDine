import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Waiter implements Listener {
    // all unpaid Bills
    private HashMap<Integer, Bill> billList;

    // all dishes ever ordered
    private HashMap<Integer, Dish> dishList;

    private String name;

    public Waiter(String name) {
        this.name = name;
        this.billList = new HashMap<>();
        this.dishList = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void handleEvent(String[] inputArray) {

        if (inputArray.length >= 2) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
        {
            switch (inputArray[0]) {
                case "requested bill":
                    this.showBill(Integer.valueOf(inputArray[1]));
                    break;
                case "ordered": {
                    if (inputArray.length >= 6) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
                    {
                        // Create an array from the String of ingredients separated by commas
                        String[] additionsArray = inputArray[2].split(",");
                        String[] subtractionsArray = inputArray[3].split(",");

                        // Convert those arrays to ArrayLists to match with the necessary datatypes.
                        ArrayList<String> add = new ArrayList<>(Arrays.asList(additionsArray));
                        ArrayList<String> sub = new ArrayList<>(Arrays.asList(subtractionsArray));

                        this.createDish(inputArray[1], add, sub, inputArray[5]);
                    }

                    else if (inputArray.length >= 4) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
                    {
                        this.createDish(inputArray[1], inputArray[3]);
                    }
                }
                    break;
                case "delivered dish":
                    this.confirmDishDelivery(Integer.valueOf(inputArray[1]));
                    break;
                case "recalled dish":
                    this.recallDish(Integer.valueOf(inputArray[1]));
                    break;
                case "new bill":
                    this.createBill(Integer.valueOf(inputArray[1]));
                    break;
                case "pay bill":
                    this.payBill(Integer.valueOf(inputArray[1]));
                    break;
                case "show bill":
                    this.showBill(Integer.valueOf(inputArray[1]));
                    break;
                case "recall dish":
                    this.recallDish(Integer.valueOf(inputArray[1]));
                    break;
                case "remove dish":
                    this.removeDish(Integer.valueOf(inputArray[1]));
                    break;
            }
        }
    }

    // helper for .createDish, alters Ingredients based on additions and subtractions
    private boolean madeSubstitutions(MenuItem menuItem, HashMap<String, Integer> ingredients,
                                                       ArrayList<String> additions, ArrayList<String> subtractions) {
        for (String addition : additions) {
            if (menuItem.getAllowedAdditions().contains(addition)) {
                if (ingredients.containsKey(addition)) {
                    ingredients.put(addition, ingredients.get(addition) + 1);
                } else {
                    ingredients.put(addition, 1);
                }
            } else {
                this.printToScreen("Can't order: You can't add " + addition + ".");
                return false;
            }
        }
        // removing <subtractions> from <ingredients>
        for (String subtraction : subtractions) {
            if (menuItem.getAllowedSubtractions().contains(subtraction)) {
                ingredients.remove(subtraction);
            } else {
                this.printToScreen("Can't order: You can't remove " + subtraction + ".");
                return false;
            }
        }
        return true;
    }

    // Precondition: <item> is on the menu
    private void createDish(String item, ArrayList<String> additions, ArrayList<String> subtractions, String tableNumber) {
        MenuItem menuItem = Restaurant.getMenu().get(item);
        HashMap<String, Integer> ingredients = new HashMap<>(menuItem.getIngredients());

        // Attempt to create dish if substitutions were valid.
        if (madeSubstitutions(menuItem, ingredients, additions, subtractions)){

            ArrayList<String> missingIngredients = Restaurant.checkIngredientsInventory(ingredients);
            // There are sufficient ingredients, create the dish
            if (missingIngredients.isEmpty()) {
                Dish dish = new Dish(item, additions, subtractions, this, Integer.valueOf(tableNumber));
                dishList.put(dish.getDishId(), dish);
                Kitchen.addDish(dish);
                for (String ingredient : ingredients.keySet()) {
                    Integer quantity = ingredients.get(ingredient);
                    Restaurant.removeFromInventory(ingredient, quantity);
                }
            }
            // There are insufficient ingredients to complete the order
            else {
                printToScreen("Can't order dish, insufficient ingredients: " + missingIngredients);
            }
<<<<<<< HEAD
=======
            printToScreen(item + " (Dish id " + dish.getDishId() + ") was ordered for Table " + tableNumber + "!");
            printToScreen("It has the following additions: " + additions + ", and substractions: " + subtractions + "!");
        } else {
            printToScreen("Can't order dish, insufficient ingredients: " + missingIngredients);
>>>>>>> 6740585625a215cc528ad2b18f3e3d5f5668783c
        }
    }

    // Precondition: <item> is on the menu
    private void createDish(String item, String tableNumber) {
        MenuItem menuItem = Restaurant.getMenu().get(item);
        HashMap<String, Integer> ingredients = new HashMap<>(menuItem.getIngredients());
        ArrayList<String> missingIngredients = Restaurant.checkIngredientsInventory(ingredients);
        if (missingIngredients.isEmpty()) {
            Dish dish = new Dish(item, this, Integer.valueOf(tableNumber));
            dishList.put(dish.getDishId(), dish);
            Kitchen.addDish(dish);
            for (String ingredient : ingredients.keySet()) {
                Integer quantity = ingredients.get(ingredient);
                Restaurant.removeFromInventory(ingredient, quantity);
            }
            printToScreen(item + " (Dish id " + dish.getDishId() + ") was ordered for Table " + tableNumber + "!");
        } else {
            printToScreen("Can't order dish, insufficient ingredients: " + missingIngredients);
        }
    }

    private void createBill(int tableNum) {
        Bill bill = new Bill(tableNum, this);
        billList.put(tableNum, bill);
        printToScreen("New bill created for Table " + tableNum + "!");
    }

    /**
     * Pays Bill of the given table.
     * This archives the table's Bill in bills.json.
     * @param tableNum Number of the table whose Bill is paid
     */
    private void payBill(int tableNum) {
        Type billArrayList = new TypeToken<ArrayList<Bill>>() {
        }.getType();
        Gson gson = new Gson();

        try {
            ArrayList<Bill> bills = gson.fromJson(new FileReader("bills.json"), billArrayList);
            bills.add(billList.get(tableNum));

            try {
                FileWriter writer = new FileWriter("bills.json");
                gson.toJson(bills, writer);
                writer.close();
                printToScreen("Successful payment for Table " + tableNum + "!");
            }
            catch (IOException e) {
                System.out.println("bills.txt is busy, can't add bill");
            }

        }
        catch (IOException e) {
            System.out.println("bills.txt is busy, can't read past bills");
        }
    }

    private void confirmDishDelivery(int dishID) {
        Dish dish = dishList.get(dishID);
        Bill bill = billList.get(dish.getTableNumber());
        bill.addDish(dish);
        printToScreen("Dish " + dish + " delivered!");
    }

    private void removeDish(int dishID){
        Dish dish = dishList.get(dishID);
        Bill bill = billList.get(dish.getTableNumber());
        bill.removeDish(dishID);
        printToScreen("Dish " + dish + " removed!");
    }

    private void recallDish(int dishID) {
        removeDish(dishID);
        Dish dish = dishList.get(dishID);
        createDish(dish.getName(), dish.getAdditions(), dish.getSubtractions(), String.valueOf(dish.getTableNumber()));
        printToScreen("Dish " + dish + " recalled!");
    }

    private void showBill(int billID) {
        printToScreen(billList.get(billID).toString());
    }

    public String toString() {
        return this.name;
    }

    public void printToScreen(String s) {
        System.out.println(name + ": " + s);
    }
}