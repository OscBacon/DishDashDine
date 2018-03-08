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
    private HashMap<String, Integer> ingredients;
    private int tableNumber;
    private Waiter waiter;
    private String cook;
    private double price;
    private int dishId;

    /**
     * Constructor for Dish object, each dish is initialised with a name and a waiter who adds that dish.
     * @param dishName Precondition: dishName is already in the menu
     * @param waiter Precondition: Waiter exists in the restaurant.
     */
    Dish(String dishName, Waiter waiter, int tableNumber) {
        this.name = dishName;
        this.waiter = waiter;
        this.additions = new ArrayList<String>();
        this.subtractions = new ArrayList<String>();
        this.price = Restaurant.getMenu().get(dishName).getPrice();
        this.dishId = numOfDishes;
        this.tableNumber = tableNumber;
        this.ingredients = new HashMap<>();
        numOfDishes++;
    }

    /**
     * Constructor for Dish object including allowed Additions and Subtractions.
     * @param dishName Precondition: dishName is already on the menu.
     * @param dishAdditions All Dish Additions allowed
     * @param dishSubtractions All Dish subtractions asked by the client
     * @param waiter The waiter who assigned this Dish
     */
    Dish(String dishName, ArrayList<String> dishAdditions, ArrayList<String> dishSubtractions, Waiter waiter, int tableNumber) {
        this.name = dishName;
        this.additions = dishAdditions;
        this.subtractions = dishSubtractions;
        this.waiter = waiter;
        this.price = Restaurant.getMenu().get(dishName).getPrice();
        this.dishId = numOfDishes;
        this.tableNumber = tableNumber;
        this.ingredients = new HashMap<>();
        numOfDishes++;
    }

    /**
     * Sets the ingredients HashMap equal to the input parameter.
     * @param ingredients Represents the ingredients that are necessary for the preparation of this dish.
     */
    public void setIngredients(HashMap<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Returns the dish's name.
     * @return String representing the Dish name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns all the additions of the dish
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
     * Returns the table this particular dish has been assigned
     * @return tableNumber
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * Returns the Waiter that has assigned this Dish.
     * @return Waiter
     */
    public Waiter getWaiter() {
        return waiter;
    }

    /**
     * Returns the cook who accepts and prepares this particular dish
     * @return String Name of Cook
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
     * @return A double representing the price of this dish.
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
