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
     * Constructor for Dish object, each dish is initialised with a name and a waiter who adds that dish.
     * @param dishName Precondition: dishName is already in the menu
     * @param waiter Precondition: Waiter exists in the restaurant.
     */
    Dish(String dishName, Waiter waiter) {
        this.name = dishName;
        this.waiter = waiter;
        this.additions = new ArrayList<String>();
        this.subtractions = new ArrayList<String>();

    }

    /**
     * Constructor for Dish object including allowed Additions and Subtractions.
     * @param dishName Precondition: dishName is already on the menu.
     * @param dishAdditions All Dish Additions allowed
     * @param dishSubtractions All Dish subtractions asked by the client
     * @param waiter The waiter who assigned this Dish
     */
    Dish(String dishName, ArrayList<String> dishAdditions, ArrayList<String> dishSubtractions, Waiter waiter) {
        this.name = dishName;
        this.additions = dishAdditions;
        this.subtractions = dishSubtractions;
        this.waiter = waiter;
    }

    /**
     * @return numOfDishes: returns the number of dishes
     */
    public static int getNumOfDishes() {
        return numOfDishes;
    }

    /**
     * @return returns Dish name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns all the additions 
     * @return
     */
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
