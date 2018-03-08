import java.util.ArrayList;
import java.util.HashMap;

/**
 * A dish object is created when the customer (in the Restaurant) orders a dish and a Waiter initialises a new Dish.
 * A waiter can initialise a dish with or without substitutions.
 */
public class Dish {

    private static int numOfDishes;
    private String name;
    private ArrayList<String> additions;
    private ArrayList<String> subtractions;
    private int tableNumber;
    private Waiter waiter;
    private String cook;
    private double price;
    private int dishId;
    private HashMap ingredients;

    /**
     * Constructor for Dish object, each dish
     * @param dishName
     * @param waiter
     */
    public Dish(String dishName, Waiter waiter) {
        this.name = dishName;
        this.waiter = waiter;
    }

    public Dish(String name, ArrayList<String> dishAdditions, ArrayList<String> dishSubtractions, Waiter waiter) {
        this.name = name;
        this.additions = dishAdditions;
        this.subtractions = dishSubtractions;
        this.waiter = waiter;
    }

    public static int getNumOfDishes() {
        return numOfDishes;
    }

    // Getters for all variables
    public String getName() {
        return name;
    }

    public ArrayList<String> getAdditions() {
        return additions;
    }

    public ArrayList<String> getSubtractions() {
        return subtractions;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public String getCook() {
        return cook;
    }

    // One setter, for the cook attribute
    public void setCook(String cook) {
        this.cook = cook;
    }

    public double getPrice() {
        return price;
    }

    public int getDishId() {
        return dishId;
    }

    public HashMap getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        String fullDish = this.name;
        for (String addition : this.additions) {
            fullDish += "+" + addition;
        }
        for (String eachSubtraction : this.subtractions) {
            fullDish += "-" + eachSubtraction;
        }
        return fullDish;
    }

}
