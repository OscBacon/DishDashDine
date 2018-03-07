import java.util.ArrayList;
import java.util.HashMap;

public class MenuItem {
    private int price;
    private HashMap<String, Integer> ingredients;
    private final ArrayList<String> allowedAdditions;
    private final ArrayList<String> allowedSubtractions;

    public int getPrice() {
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
