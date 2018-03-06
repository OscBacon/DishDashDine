import java.util.ArrayList;

public class Bill
{

    private int tableNumber;
    private Waiter server;
    private ArrayList<Dish> dishList;
    private int billId;
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

    public void removeDish(int currentDishID) {
        for(int i = 0; i < this.dishList.size(); i++) {
            if (dishList.get(i).getDishId() == currentDishID) {
                dishList.remove(i);
            }
        }
    }

    public int getbillId() {
        return this.billId;
    }

}
