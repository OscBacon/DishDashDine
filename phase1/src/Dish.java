import java.util.ArrayList;
import java.util.HashMap;

public class Dish {


    private String name;
    private ArrayList<String> additions;
    private ArrayList<String> subtractions;
    private int tableNumber;
    private Waiter waiter;
    private double price;
    private int dishId;
    private static int numOfDishes;
    private HashMap ingredients;

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

    public Waiter getWaiter(){
        return waiter;
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

    @Override
    public String toString() {
        String fullDish = this.name;
        for (String addition : this.additions) {
            fullDish += "+" + addition;
        }
        for (String eachSubtraction: this.subtractions) {
            fullDish += "-" + eachSubtraction;
        }
        return fullDish;
    }

}
