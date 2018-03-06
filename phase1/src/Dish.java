import java.util.HashMap;

public class Dish {


    private String name;
    private String [] additions;
    private String [] substractions;
    private int tableNumber;
    private double price;
    private int dishId;
    private static int numOfDishes;
    private HashMap ingredients;

    public Dish(String dishName) {
        this.name = dishName;
    }

    public Dish(String name, String [] dishAdditions, String [] dishSubtractions) {
        this.name = name;
        this.additions = dishAdditions;
        this.substractions = dishSubtractions;
    }

    // Getters for all variables
    public String getName() {
        return name;
    }

    public String[] getAdditions() {
        return additions;
    }

    public String[] getSubstractions() {
        return substractions;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public double getPrice() {
        return price;
    }

    public int getDishId() {
        return dishId;
    }

    public static int getNumOfDishes() {
        return numOfDishes;
    }

    public HashMap getIngredients() {
        return ingredients;
    }
}
