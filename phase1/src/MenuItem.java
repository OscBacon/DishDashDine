import java.util.Arrays;
import java.util.HashMap;

public class MenuItem {
    private int price;
    private HashMap<String, Integer> ingredients;
    private String[] allowedAdditions = {};
    private String[] allowedSubstractions = {};

    public int getPrice() {
        return price;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public String[] getAllowedAdditions() {
        return allowedAdditions;
    }

    public String[] getAllowedSubstractions() {
        return allowedSubstractions;
    }

    @Override
    public String toString() {
        return "{" +
                "price=" + price +
                ", ingredients=" + ingredients +
                ", allowedAdditions=" + Arrays.toString(allowedAdditions) +
                ", allowedSubstractions=" + Arrays.toString(allowedSubstractions) +
                '}';
    }
}
