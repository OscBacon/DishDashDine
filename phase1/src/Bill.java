import java.util.ArrayList;

public class Bill
{
    
    private int tableNumber;
    private Waiter server;
    private ArrayList<Dish> dishList;
    private int FinalId;
    private static int numOfBills;

    public Bill(int tableNumber, Waiter server) {
        this.tableNumber = tableNumber;
        this.server = server;
    }

    public String toString() {
        return "";
    }

    public void addDish(Dish dish) {
        dishList.add(dish);
    }

    public void removeDish(int dishID) {
        for(int i = 0; i < this.dishList.size(); i++) {
            if (dishList.get(i).getID == dishID) {
                dishList.remove(dishList[i]);
            }
        }
    }

}
