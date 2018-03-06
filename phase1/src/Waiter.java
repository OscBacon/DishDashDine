import java.util.ArrayList;

/**
 *
 */
public class Waiter { //implements Listener{ TODO: Listener interface not yet created

    // all paid and unpaid Bills
    private ArrayList<Bill> billList;

    // all dishes ever ordered
    private ArrayList<Dish> dishList;

    private String name;

    public Waiter(String name) {
        this.name = name;
    }

    //TODO: create method bodies
    public void handleInput(String input){
    }

    public void createDish(String item){
    }

    public void createDish(String item, ArrayList<String> additions, ArrayList<String> subtractions){
    }

    private void confirmDishDelivery(int dishID){
    }

    private void recallDish(int dishID){
    }

    public void printToScreen(String output){
        System.out.println(output);
    }

    public String toString(){
        return this.name;
    }
}