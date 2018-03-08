import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Waiter extends Listener {
    // all unpaid Bills
    private HashMap<Integer, Bill> billList;

    // all dishes ever ordered
    private HashMap<Integer, Dish> dishList;

    private String name;

    public Waiter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void handleEvent(String[] inputArray) {
        if (inputArray.length >= 4) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
        {
            if (inputArray[0].trim().equals("ordered")) {
                // Create an array from the String of ingredients separated by commas
                String[] additionsArray = inputArray[2].split("(,)?");
                String[] subtractionsArray = inputArray[3].split("(,)?");

                // Convert those arrays to ArrayLists to match with the necessary datatypes.
                ArrayList<String> add = new ArrayList<>(Arrays.asList(additionsArray));
                ArrayList<String> sub = new ArrayList<>(Arrays.asList(subtractionsArray));

                this.createDish(inputArray[1].trim(), add, sub);
            }
        } else if (inputArray.length >= 2) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
        {
            switch (inputArray[0].trim()) {
                case "requested bill":
                    this.showBill(Integer.valueOf(inputArray[1].trim()));
                    break;
                case "ordered":
                    this.createDish(inputArray[1].trim());
                    break;
                case "delivered dish":
                    this.confirmDishDelivery(Integer.valueOf(inputArray[1].trim()));
                    break;
                case "recalled dish":
                    this.recallDish(Integer.valueOf(inputArray[1].trim()));
                    break;
                case "new bill":
                    this.createBill(Integer.valueOf(inputArray[1].trim()));
                case "pay bill":
                    this.payBill(Integer.valueOf(inputArray[1].trim()));
            }
        }
    }

    // helper for .createDish, alters Ingredients based on additions and subtractions
    private HashMap<String, Integer> makeSubstitutions(MenuItem menuItem, HashMap<String, Integer> ingredients,
                                                       ArrayList<String> additions, ArrayList<String> subtractions) {
        for (String addition : additions) {
            if (menuItem.getAllowedAdditions().contains(addition)) {
                if (ingredients.containsKey(addition)) {
                    ingredients.put(addition, ingredients.get(addition) + 1);
                } else {
                    ingredients.put(addition, 1);
                }
            } else {
                this.printToScreen("You can't add " + addition);
            }
        }
        // removing <subtractions> from <ingredients>
        for (String subtraction : subtractions) {
            if (menuItem.getAllowedSubtractions().contains(subtraction)) {
                ingredients.remove(subtraction);
            } else {
                this.printToScreen("You can't remove " + subtraction);
            }
        }
        return ingredients;
    }

    // Precondition: <item> is on the menu
    private void createDish(String item, ArrayList<String> additions, ArrayList<String> subtractions) {
        MenuItem menuItem = Restaurant.getMenu().get(item);
        HashMap<String, Integer> ingredients = new HashMap<>(menuItem.getIngredients());
        ingredients = makeSubstitutions(menuItem, ingredients, additions, subtractions);

        ArrayList<String> missingIngredients = Restaurant.checkIngredientsInventory(ingredients);
        if (missingIngredients.isEmpty()) {
            Dish dish = new Dish(item, additions, subtractions, this);
            dishList.put(dish.getDishId(), dish);
            Kitchen.addDish(dish);
            for (String ingredient : ingredients.keySet()) {
                Integer quantity = ingredients.get(ingredient);
                Restaurant.removeFromInventory(ingredient, quantity);
            }
        } else {
            printToScreen("Can't order dish, insufficient ingredients: " + missingIngredients);
        }
    }

    // Precondition: <item> is on the menu
    private void createDish(String item) {
        MenuItem menuItem = Restaurant.getMenu().get(item);
        HashMap<String, Integer> ingredients = new HashMap<>(menuItem.getIngredients());
        ArrayList<String> missingIngredients = Restaurant.checkIngredientsInventory(ingredients);
        if (missingIngredients.isEmpty()) {
            Dish dish = new Dish(item, this);
            dishList.put(dish.getDishId(), dish);
            Kitchen.addDish(dish);
            for (String ingredient : ingredients.keySet()) {
                Integer quantity = ingredients.get(ingredient);
                Restaurant.removeFromInventory(ingredient, quantity);
            }
        } else {
            printToScreen("Can't order dish, insufficient ingredients: " + missingIngredients);
        }
    }

    private void createBill(int tableNum) {
        Bill bill = new Bill(tableNum, this);
        billList.put(tableNum, bill);
    }

    private void payBill(int tableNum) {
    }

    private void confirmDishDelivery(int dishID) {
        Dish dish = dishList.get(dishID);
        Bill bill = billList.get(dish.getTableNumber());
        bill.addDish(dish);
    }

    private void recallDish(int dishID) {

    }

    private void showBill(int billID) {
        printToScreen(billList.get(billID).toString());
    }

    public String toString() {
        return this.name;
    }
}