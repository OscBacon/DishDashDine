import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class Waiter extends Listener{
    // all paid and unpaid Bills
    private ArrayList<Bill> billList;

    // all dishes ever ordered
    private ArrayList<Dish> dishList;

    private String name;

    public Waiter(String name) {
        this.name = name;
    }

    //TODO: create method bodies
    public void handleInput(String input){
    }

    // helper for .createDish, alters Ingredients based on additions and subtractions
    private HashMap<String, Integer> makeSubstitutions(MenuItem menuItem, HashMap<String, Integer> ingredients,
                                                ArrayList<String> additions, ArrayList<String> subtractions) {
        for (String addition: additions){
            if (menuItem.getAllowedAdditions().contains(addition)) {
                if (ingredients.containsKey(addition)){
                    ingredients.put(addition, ingredients.get(addition) + 1);
                }
                else {
                    ingredients.put(addition, 1);
                }
            }
            else {
                this.printToScreen("You can't add " + addition);
            }
        }
        // removing <subtractions> from <ingredients>
        for (String subtraction: subtractions){
            if (menuItem.getAllowedSubtractions().contains(subtraction)){
                ingredients.remove(subtraction);
            }
            else {
                this.printToScreen("You can't remove " + subtraction);
            }
        }
        return ingredients;
    }

    // Precondition: <item> is on the menu
    public void createDish(String item, ArrayList<String> additions, ArrayList<String> subtractions){
    }

    // Precondition: <item> is on the menu
    public void createDish(String item){
        MenuItem menuItem = Restaurant.getMenu().get(item);
        HashMap<String, Integer> ingredients = new HashMap<String, Integer>(menuItem.getIngredients());
        if (Restaurant.checkInventory(ingredients)){
            Dish dish = new Dish(item);
            Kitchen.addDish(dish);
            for(String ingredient: ingredients.keySet()) {
                Integer quantity = ingredients.get(ingredient);
                Restaurant.removeFromInventory(ingredient, quantity);
            }
        }
        else {
            printToScreen("Can't order dish: ingredients missing");
        }
    }

    private void confirmDishDelivery(int dishID){
    }

    private void recallDish(int dishID){
    }

    public void printToScreen(String output){
        System.out.println(output);
    }

    public String toString(){
        return this.name;
    }
}