import java.util.ArrayList;
import java.util.HashMap;

public class MenuItem {
    private final ArrayList<String> allowedAdditions;
    private final ArrayList<String> allowedSubtractions;
    private double price;
    private HashMap<String, Integer> ingredients;

    public MenuItem(double price, HashMap<String, Integer> ingredients, ArrayList<String> allowedAdditions, ArrayList<String> allowedSubtractions) {
        this.price = price;
        this.ingredients = ingredients;
        this.allowedAdditions = allowedAdditions;
        this.allowedSubtractions = allowedSubtractions;
    }

    public double getPrice() {
        return price;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getAllowedAdditions() {
        return allowedAdditions;
    }

    public ArrayList<String> getAllowedSubtractions() {
        return allowedSubtractions;
    }

    @Override
    public String toString() {
        return "{" +
                "price=" + price +
                ", ingredients=" + ingredients +
                ", allowedAdditions=" + allowedAdditions.toString() +
                ", allowedSubstractions=" + allowedSubtractions.toString() +
                '}';
    }
}
