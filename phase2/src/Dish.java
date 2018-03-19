import java.util.ArrayList;
import java.util.HashMap;

/**
 * A dish object is created when the customer (in the Restaurant) orders a dish and a Waiter initialises a new Dish.
 * A waiter can initialise a dish with or without substitutions.
 */
public class Dish {

     /**
     * static numOfDishes: keeps track of all the numOfDishes made.
     */
     private static int numOfDishes;
     /**
     * String name: This Dish's name
     */
     private String name;
     /**
     * ArrayList additions: All allowed additions required by the Restaurant's customer for this dish.
     */
     private ArrayList<String> additions;
     /**
     * ArrayList subtractions: All allowed subtractions required by the Restaurant's customer for this dish.
     */
     private ArrayList<String> subtractions;
     /**int tableNumber: The table this dish belongs to.
     */
     private int tableNumber;
     /** Waiter waiter: The particular waiter to handle this dish.
     */
     private Waiter waiter;
     /** String cook: The particular cook who accepts and prepares this dish.
     */
     private String cook;
     /**
     * double price: The price of each dish which is held constant regardless of the substitutions of this dish. The
     *               price is obtained from the menu.
     */
     private double price;
     /**
     * int dishId: Equal to the this dish's particular numOfDishes, non-static attribute that helps keep track of the
     *             particular dish.
     */
     private int dishId;
     /**
     * HashMap ingredients: The ingredients required to prepare this dish, including the substitutions.
     */
     private HashMap<String, Integer> ingredients;


    /**
     * Constructor for Dish object including allowed Additions and Subtractions.
     * @param dishName The particular name of the dish from the menu.
     * @param dishAdditions Dish additions requested by the customer.
     * @param dishSubtractions Dish subtractions requested by the customer.
     * @param waiter The waiter who assigned this Dish.
     * @param tableNumber The tableNumber that this dish belongs to.
     * Precondition: dishName is already in the menu. Waiter exists in the restaurant. tableNumber is in Restaurant.
     *               All substitutions are allowed.
     */
    public Dish(String dishName, ArrayList<String> dishAdditions, ArrayList<String> dishSubtractions, Waiter waiter, int tableNumber) {
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
     * Getter for NumOfDishes
     * Sets the ingredients HashMap equal to the input parameter.
     * @param ingredients Represents the ingredients that are necessary for the preparation of this dish.
     */
    public void setIngredients(HashMap<String, Integer> ingredients) {
        this.ingredients = ingredients;
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
     * @return HashMap
     */
    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    /**
     * The standard toString method has been overridden to show all the additions and subtractions of each dish.
     * @return String representation of the particular dish.
     */
    @Override
    public String toString() {
        ArrayList<String> dishStrings = new ArrayList<>();
        dishStrings.add(name);
        if (!additions.isEmpty()) {
            for (String addition : additions) {
                if (!addition.equals("")) {
                    dishStrings.add("+ " + addition);
                }
            }
        }
        if (!subtractions.isEmpty()) {
            for (String subtraction : subtractions) {
                if (!subtraction.equals("")) {
                    dishStrings.add("- " + subtraction);
                }
            }
        }
        return String.join(" ", dishStrings);
    }
}
