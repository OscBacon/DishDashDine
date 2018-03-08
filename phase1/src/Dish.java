import java.util.ArrayList;
import java.util.HashMap;

/**
 * A dish object is created when the customer (in the Restaurant) orders a dish and a Waiter initialises a new Dish.
 * A waiter can initialise a dish with or without substitutions.
 */
public class Dish {

    /**
     * static numOfDishes: keeps track of all the numOfDishes made.
     * String name: This Dish's name
     * ArrayList additions: All allowed additions required by the Restaurant's customer for this dish.
     * ArrayList subtractions: All allowed subtractions required by the Restaurant's customer for this dish.
     * int tableNumber: The table this dish belongs to.
     * Waiter waiter: The particular waiter to handle this dish.
     * String cook: The particular cook who accepts and prepares this dish.
     * double price: The price of each dish which is held constant regardless of the substitutions of this dish. The
     *               price is obtained from the menu.
     * int dishId: Equal to the this dish's particular numOfDishes, non-static attribute that helps keep track of the
     *             particular dish.
     * HashMap ingredients: The ingredients required to prepare this dish, including the substitutions.
     */
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
     * Constructor for Dish object, each dish is initialised with a name, a waiter who adds that dish and tableNumber.
     * @param dishName The particular name of the dish from the menu.
     * @param waiter The waiter who assigned the dish.
     * @param tableNumber The tableNumber that this dish belongs to.
     * Preconditions: dishName is already in the menu. Waiter exists in the restaurant. tableNumber is in Restaurant.
     */
    Dish(String dishName, Waiter waiter, int tableNumber) {
        this.name = dishName;
        this.waiter = waiter;
        this.additions = new ArrayList<String>();
        this.subtractions = new ArrayList<String>();
        this.price = Restaurant.getMenu().get(dishName).getPrice();
        this.dishId = numOfDishes;
        this.tableNumber = tableNumber;
        numOfDishes++;
    }

    /**
     * Constructor for Dish object including allowed Additions and Subtractions.
     * @param dishName The particular name of the dish from the menu.
     * @param dishAdditions All Dish Additions allowed.
     * @param dishSubtractions All Dish subtractions asked by the client.
     * @param waiter The waiter who assigned this Dish.
     * @param tableNumber The tableNumber that this dish belongs to.
     * Precondition: dishName is already in the menu. Waiter exists in the restaurant. tableNumber is in Restaurant.
     *               All substitutions are allowed.
     */
    Dish(String dishName, ArrayList<String> dishAdditions, ArrayList<String> dishSubtractions, Waiter waiter, int tableNumber) {
        this.name = dishName;
        this.additions = dishAdditions;
        this.subtractions = dishSubtractions;
        this.waiter = waiter;
        this.price = Restaurant.getMenu().get(dishName).getPrice();
        this.dishId = numOfDishes;
        this.tableNumber = tableNumber;
        numOfDishes++;
    }

    /**
     * Getter for NumOfDishes
     * @return numOfDishes: returns the number of dishes
     */
    public static int getNumOfDishes() {
        return numOfDishes;
    }

    /**
     * Getter that returns the String for Dish name
     * @return returns Dish name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter that Returns all the additions of the dish
     * @return additions
     */
    public ArrayList<String> getAdditions() {
        return additions;
    }

    /**
     * Returns all the subtractions required by the Restaurant's customer.
     * @return subtractions
     */
    public ArrayList<String> getSubtractions() {
        return subtractions;
    }

    /**
     * Getter that returns the table this particular dish has been assigned
     * @return tableNumber
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     *  Getter that returns the Waiter that has assigned this Dish.
     * @return Waiter
     */
    public Waiter getWaiter() {
        return waiter;
    }

    /**
     * Returns the cook who accepts and prepares this particular dish
     * @return cook: Name of Cook
     */
    public String getCook() {
        return cook;
    }

    /**
     * Sets the cook who will accept and prepare the dish
     * @param cook The particular cook who will accept and prepare the dish.
     */
    public void setCook(String cook) {
        this.cook = cook;
    }

    /**
     * Get the price of the dishName
     * @return double price
     */
    public double getPrice() {
        return price;
    }

    /**
     * ID of each particular dish
     * @return int dishID
     */
    public int getDishId() {
        return dishId;
    }

    /**
     * Each particular ingredient of the Dish
     * @return Hashmap
     */
    public HashMap getIngredients() {
        return ingredients;
    }

    /**
     * The standard toString method has been overridden to show all the additions and subtractions of each dish.
     * @return String representation of the particular dish.
     */
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
